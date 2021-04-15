package dao;

import entity.Area;
import entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBUtils;
import utils.MD5Utils;

import java.sql.SQLException;
import java.util.List;

public class AreaDao {
    public  boolean insert(Area area) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "INSERT INTO area(building,floor) VALUES (?, ?)";
        Object[] params = new Object[]{area.getBuilding(),area.getFloor()};
        // 执行查询，并返回所需要的类型
        int rows = 0;
        try {
            rows = queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        if(rows>0){
            return true;
        }
        return false;
    }
    public  boolean update(Area area) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "update area set building = ? , floor = ? where id = ?";
        Object[] params = new Object[]{area.getBuilding(),area.getFloor(),area.getId()};
        // 执行查询，并返回所需要的类型
        int rows = 0;
        try {
            rows = queryRunner.update(sql,params);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        if(rows>0){
            return true;
        }
        return false;
    }
    public  boolean delete(int id){
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "delete from area where id = ?";
        Object[] params = new Object[]{id};
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
    //全部查询
    public  List<Area> selectAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from area";
        return queryRunner.query(sql, new BeanListHandler<Area>(Area.class));
    }
    //迷糊查询
    public  List<Area> selectBy(String condition,int pageNum,int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select id,building,floor from area where 1=1";
        if(condition!=null && !condition.isEmpty()){
            sql +=condition;
        }
        if(pageSize>0){
            sql+=" limit "+pageSize*(pageNum-1)+" , "+pageSize;
        }
        return queryRunner.query(sql,new BeanListHandler<Area>(Area.class));
    }
    //统计总页数
    public  int countRows(String condition) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select count(id) from area where 1=1 ";
        if(condition!=null && !condition.isEmpty()){
            sql +=condition;
        }
        Number query = queryRunner.query(sql,new ScalarHandler<>());
        return query.intValue();
    }

    public Area selectOne(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from area where id =?";
        Object[] params = new Object[]{id};
        return queryRunner.query(sql, new BeanHandler<Area>(Area.class),params);
    }
    public List<String> selectAllBuilding() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select distinct building from area";
        List<String> list = queryRunner.query(sql,new ColumnListHandler<>("building"));
        return list;
    }
    public List<Area> selectAreaInfoBy(String building) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql="select * from area  where 1=1 ";
        if(building!=null&&!building.isEmpty()){
            sql+=building;
        }
//        System.out.println(sql);
        List<Area> areaInfoList=queryRunner.query(sql,new BeanListHandler<Area>(Area.class));
        return areaInfoList;
    }

}
