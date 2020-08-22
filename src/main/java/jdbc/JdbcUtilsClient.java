package jdbc;

import java.sql.*;

public class JdbcUtilsClient {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            // Define SQL
            stmt = conn.prepareStatement("select * from salarytbl");
            // Execute SQL
            rs = stmt.executeQuery();
            // Process result
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id") +
                        "\t name: " + rs.getString("name") +
                        "\t salary: " + rs.getFloat("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.cleanResource(rs, stmt, conn);
        }
    }
}
