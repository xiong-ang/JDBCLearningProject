package connectionPool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import jdbc.JdbcUtils;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtilsWithC3P0 {
    private static DataSource ds;

    static {
        Properties prop = new Properties();
        try {
            prop.load(JdbcUtils.class.getClassLoader().getResourceAsStream("mysql.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String driverClass = prop.getProperty("driverClass");

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ComboPooledDataSource cds = new ComboPooledDataSource();
        try {
            cds.setDriverClass(driverClass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cds.setJdbcUrl(url);
        cds.setUser(user);
        cds.setPassword(password);

        ds = cds;
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void cleanResource(Statement stmt, Connection conn)
    {
        cleanResource(null, stmt, conn);
    }

    public static void cleanResource(ResultSet rs, Statement stmt, Connection conn) {
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

    public static DataSource getDataSource(){
        return ds;
    }
}
