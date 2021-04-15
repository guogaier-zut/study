package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    private static DataSource dataSource;
    //静态代码块 - 随着该类的字节码文件加载而加载，整个类的生命周期中，只记载一次。
    static {
        // 左边是接口
        URL resource = DBUtils.class.getResource("/hikari.properties");
//        String path = "lib/hikari.properties";
        HikariConfig configuration = new HikariConfig(resource.getPath());
        dataSource = new HikariDataSource(configuration);
    }

    public static Connection getConnection() {
        Connection connection = null;
        // 第二步 创建连接对象
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void myClose(ResultSet rs, PreparedStatement ps, Connection connection){
        if(rs !=null){
            try{
                rs.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try{
                ps.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection connection){

        if(connection!=null){
            try{
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static DataSource getDataSource() {
        return  dataSource;
    }
}
