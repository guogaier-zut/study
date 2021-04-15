package dao;

import entity.Equipment;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class EquipmentDao {

    public static boolean insert(Equipment equipment) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "INSERT INTO equipment(name,type,manager,tip,date) VALUES (?, ?, ?, ?, ?)";
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        String current_time=dateFormat.format(new Date());
        Object[] params = new Object[]{equipment.getName(),equipment.getType(),equipment.getManager(),equipment.getTip(),current_time};
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
    public static boolean update(Equipment equipment) {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "update Equipment set name = ? ,type =? ,manager = ? ,tip = ? ,date = ?where id = ?";
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        String current_time=dateFormat.format(new Date());
        Object[] params = new Object[]{equipment.getName(),equipment.getType(),equipment.getManager(),equipment.getTip(),current_time,equipment.getId()};
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
    public static boolean delete(int id){
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        // 提供执行的sql
        String sql = "delete from equipment where id = ?";
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
    public static List<Equipment> selectAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from Equipment";
        return queryRunner.query(sql, new BeanListHandler<Equipment>(Equipment.class));
    }
    //模糊查询
    public static List<Equipment> selectBy(String condition, int pageNum, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select id,name,type,manager,tip,date from Equipment where 1=1";
        if(condition!=null && !condition.isEmpty()){
            sql +=condition;
        }
//        System.out.println(sql);
        if(pageSize>0){
            sql+=" limit "+pageSize*(pageNum-1)+" , "+pageSize;
        }
        return queryRunner.query(sql,new BeanListHandler<Equipment>(Equipment.class));
    }
    //统计总页数
    public static int countRows(String condition) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select count(id) from Equipment where 1=1 ";
        if(condition!=null && !condition.isEmpty()){
            sql +=condition;
        }
        Number query = queryRunner.query(sql,new ScalarHandler<>());
        return query.intValue();
    }

    public Equipment selectOne(int id) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select * from Equipment where id =?";
        Object[] params = new Object[]{id};
        return queryRunner.query(sql, new BeanHandler<Equipment>(Equipment.class),params);
    }

    public List<String> selectAllEquipType() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());
        String sql = "select distinct type from equipment";
        List<String> list = queryRunner.query(sql,new ColumnListHandler<String>("type"));
        return list;
    }
    public List<Equipment> selectEquInfoBy(String equ_style) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

        String sql="select * from equipment  where 1=1 ";
        if(equ_style!=null&&!equ_style.isEmpty()){
            sql+=equ_style;
        }
//        System.out.println(sql);

        List<Equipment> areaInfoList=queryRunner.query(sql,new BeanListHandler<Equipment>(Equipment.class));
        return areaInfoList;
    }

}
