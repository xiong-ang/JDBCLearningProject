package connectionPool;

import jdbc.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtilsClient {
    @Test
    public void test_JdbcUtils() {
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

    @Test
    public void test_JdbcUtilsWithC3P0() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtilsWithC3P0.getConnection();
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
            JdbcUtilsWithC3P0.cleanResource(rs, stmt, conn);
        }
    }

    @Test
    public void test_JdbcUtilsWithDruid() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtilsWithDruid.getConnection();
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
            JdbcUtilsWithDruid.cleanResource(rs, stmt, conn);
        }
    }
}
