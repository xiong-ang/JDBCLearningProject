package jdbc;

import org.junit.Test;

import java.sql.*;

public class BasicJdbc {
    @Test
    public void basic_usage() {
        Connection conn = null;
        Statement stmt = null;
        try {
            // Register driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get connection
            conn = DriverManager.getConnection("jdbc:mysql:///salary", "root", "root");
            // Define SQL
            String sql = "update salarytbl set salary=50000 where id=1";
            // Get SQL statement
            stmt = conn.createStatement();
            // Execute SQL
            int count = stmt.executeUpdate(sql);
            // Process result
            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Test
    public void preparedStatement_usage() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Register driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get connection
            conn = DriverManager.getConnection("jdbc:mysql:///salary", "root", "root");
            // Define SQL
            String sql = "update salarytbl set salary=? where id=?";
            // Get SQL prepared statement
            stmt = conn.prepareStatement(sql);
            stmt.setFloat(1, 100000);
            stmt.setInt(2, 2);
            // Execute SQL
            int count = stmt.executeUpdate();
            // Process result
            System.out.println(count);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Test
    public void resultSet_usage() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Register driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get connection
            conn = DriverManager.getConnection("jdbc:mysql:///salary", "root", "root");
            // Define SQL
            String sql = "select * from salarytbl";
            // Get SQL statement
            stmt = conn.prepareStatement(sql);
            // Execute SQL
            rs = stmt.executeQuery();
            // Process result
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id") +
                        "\t name: " + rs.getString("name") +
                        "\t salary: " + rs.getFloat("salary"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != rs) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (null != stmt) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Test
    public void transaction_usage() {
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            // Register driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Get connection
            conn = DriverManager.getConnection("jdbc:mysql:///salary", "root", "root");
            // Define SQL
            String sql1 = "update salarytbl set salary = salary + ? where id=?";
            String sql2 = "update salarytbl set salary = salary - ? where id=?";
            // Get SQL prepared statement
            stmt1 = conn.prepareStatement(sql1);
            stmt1.setFloat(1, 10000);
            stmt1.setInt(2, 1);
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setFloat(1, 10000);
            stmt2.setInt(2, 2);
            // Open transaction
            conn.setAutoCommit(false);
            // Execute SQL
            stmt1.executeUpdate();
            int i = 1/0;
            stmt2.executeUpdate();
            // Commit transaction
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // Rollback transaction
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            if(null != stmt1) {
                try {
                    stmt1.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(null != stmt2) {
                try {
                    stmt2.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if(null != conn){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
