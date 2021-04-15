package dao;

import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBUtils;
import utils.MD5Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private static Connection conn = null;
    private static ResultSet rs = null;
    private  static PreparedStatement ps = null;
    public boolean add(User user) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "INSERT INTO user(username,password) VALUES (?, ?)";
        Object[] params = new Object[]{user.getName(),user.getPassword()};
        // 执行查询，并返回所需要的类型
        int rows = 0;
        try {
            rows = queryRunner.update(sql,params);
        } catch (SQLException e) {
            return false;
        }
        if(rows>0){
            return true;
        }
        return false;
    }
    public boolean check(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from user where username = ?";
        Object[] param = {user.getName()};
        User u1 = queryRunner.query(sql, new BeanHandler<User>(User.class), param);
        if(u1==null)
            return false;
        else if(u1.getPassword().equals(MD5Utils.getMD5(user.getPassword(),32))){
            return true;
        }
        return false;
    }
    public int findID(String name) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select id from user where username = ?";
        Object[] param = {name};
        return queryRunner.query(sql, new ScalarHandler<>(),param);
    }
    public String findName(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select name from user where id = ?";
        Object[] param = {id};
        return queryRunner.query(sql, new ScalarHandler<>(),param);
    }
    public boolean addrole(User user){
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "INSERT INTO user(username,password,roleid) VALUES (?, ? ,?)";
        Object[] params = new Object[]{user.getName(),user.getPassword(),user.getRoleid()};
        // 执行查询，并返回所需要的类型
        int rows = 0;
        try {
            rows = queryRunner.update(sql,params);
        } catch (SQLException e) {
            return false;
        }
        if(rows>0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new UserDao().addrole(new User("22","233",1)));
    }
}
