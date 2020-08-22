package jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public final class JdbcUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driverClass;

    static {
        Properties prop = new Properties();
        try {
            prop.load(JdbcUtils.class.getClassLoader().getResourceAsStream("mysql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
        driverClass = prop.getProperty("driverClass");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void cleanResource(Statement stmt, Connection conn)
    {
        cleanResource(null, stmt, conn);
    }

    public static void cleanResource(ResultSet rs, Statement stmt, Connection conn)
    {
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
