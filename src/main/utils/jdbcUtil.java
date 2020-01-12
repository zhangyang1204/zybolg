package main.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class jdbcUtil {
    private static DataSource ds;
    static {
        try {
            Properties properties = new Properties();
            InputStream is = jdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
            properties.load(is);
            ds= DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDatasource(){
        return ds;
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
