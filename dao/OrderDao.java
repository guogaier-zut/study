package dao;

import entity.Order;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBUtils;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderDao {
    static QueryRunner queryRunner = new QueryRunner(DBUtils.getDataSource());

    public  boolean insert(Order order) {

        // 提供执行的sql
        String sql = "INSERT INTO orders(SN,areaid,equipmentid,userid,content,imgurl,location,status,starttime) VALUES (?, ?, ?, ?, ?,?, ?, ?,?)";
        Object[] params = new Object[]{order.getSN(),order.getAreaid(),order.getEquipmentid(),order.getUserid(),order.getContent(),order.getImg(),order.getLocation(),order.getStatus(),order.getStarttime()};
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
    public  boolean update(Order order) {
        // 提供执行的sql
        String sql = "UPDATE orders SET SN = ? ,areaid = ? ,equipmentid = ?, userid = ? ,content=? ,imgurl = ?,location =? WHERE id=?";
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        String current_time=dateFormat.format(new Date());
        Object[] params = new Object[]{order.getSN(),order.getAreaid(),order.getEquipmentid(),order.getUserid(),order.getContent(),order.getImg(),order.getLocation(),order.getId()};
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
        // 提供执行的sql
        String sql = "delete from orders where id = ?";
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
    //全部查询   订单id？？查询的id呢，不是，主键id 没查
    public  List<Order> selectAll() throws SQLException {
        String sql = "SELECT   SN,area.`building`,area.`floor`,equipment.`id`,NAME AS 'equipmentname',user.`username`,orders.`content`,orders.`imgurl` as 'img',orders.`location`,orders.`status`,orders.`starttime`\n" +
                "FROM USER,orders,AREA,equipment \n" +
                "WHERE area.`id`=orders.`areaid` AND equipment.`id`=orders.`equipmentid` AND user.`id`=orders.`userid`" ;
        return queryRunner.query(sql, new BeanListHandler<Order>(Order.class));
    }
    //迷糊查询
    public  List<Order> selectBy(String condition, int pageNum, int pageSize) throws SQLException {
//        System.out.println("约束条件:"+condition);
        String sql = "SELECT  SN,area.`building`,area.`floor`,equipment.`id`,name AS 'equipmentname',user.`username`,orders.`content`,orders.`imgurl` as 'img',orders.`location`,orders.`status`,orders.`starttime`\n" +
                "                FROM USER,orders,AREA,equipment \n" +
                "                WHERE area.`id`=orders.`areaid` AND equipment.`id`=orders.`equipmentid` AND user.`id`=orders.`userid`";
        if(condition!=null && !condition.isEmpty()){
            sql +=condition;
        }
        if(pageSize>0){
            sql+=" limit "+pageSize*(pageNum-1)+" , "+pageSize;
        }
//        System.out.println("sql语句"+sql);
        return queryRunner.query(sql,new BeanListHandler<Order>(Order.class));
    }
    //统计总页数
    public  int countRows(String condition) throws SQLException {
        String sql2 = "SELECT COUNT(SN) FROM orders,equipment WHERE  equipment.`id`=orders.`equipmentid` ";
        if(condition!=null && !condition.isEmpty()){
            sql2 +=condition;
        }
        Number query = queryRunner.query(sql2,new ScalarHandler<>());
        return query.intValue();
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(new OrderDao().selectAll());
    }
}
