package Servlets;


import com.alibaba.fastjson.JSON;
import dao.OrderDao;
import dao.UserDao;
import entity.Order;
import entity.ReturnData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="RepairAsignListServlet",urlPatterns = "/servlet/maintenance/queryList1")
public class RepairAsignListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        String queryName=req.getParameter("queryName");
        String queryresponsible = req.getParameter("queryresponsible");
        String status = req.getParameter("status");
        String pageNumber=req.getParameter("pageIndex");
        String offset=req.getParameter("offset");
        String pageSize=req.getParameter("pageSize");
        try {
            backPageing(queryName,queryresponsible,status,pageNumber,offset,pageSize,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void backPageing(String queryName, String queryresponsible,String status,String pageNumber, String offset, String pageSize, HttpServletResponse resp) throws IOException, SQLException {
        ReturnData<Order> returnData=new ReturnData<>();
        resp.setContentType("text/json;charset=utf-8");
        List<Order> querylist=new ArrayList<>();
        int ioffset=Integer.parseInt(offset);
        int ipageNumber=Integer.parseInt(pageNumber);
        int ipageSize=Integer.parseInt(pageSize);
        String condition = "";
        if(queryName!=null&& !queryName.isEmpty()){
            condition+=" AND  equ_info.`equ_name` LIKE'%"+queryName+"%'";
        }
        if(queryresponsible!=null&&!queryresponsible.isEmpty())
        {
            condition+=" and repair_info.maintenanceUserID=user.id and user. `user_name`like '%"+queryresponsible+"%'";
        }
        if(status!=null&&!status.isEmpty())
        {
            condition+=" and  status = '"+status+"'";
        }
        querylist=new OrderDao().selectBy(condition,ipageNumber,ipageSize);//现在时显示全部数据应该和condition没关系嗯，就是setuser_name，没成功不出名字
        for(int i=0;i<querylist.size();i++){
            if(querylist.get(i).getUserid()>0){//你不应该是操作一条数据的一个维修工对象？嗯querylist里有好几个吧？嗯，改成对象试试？
//                System.out.println(new UserDao().selectbyID(querylist.get(i).getMaintenanceUserID()));
                querylist.get(i).setUsername(new UserDao().findName(querylist.get(i).getUserid()));//设置维修人根据getMaintenanceUserID
            }else{//这个不出来，查出来他就一个？//我不才甲两次维修人吗，我有点迷，就是有维修人号的才进行设置维修人
                querylist.get(i).setUsername("");
            }
        }
        int rows=new OrderDao().countRows(condition);
        returnData.setTotal(rows);
        returnData.setRows(querylist);
        String result= JSON.toJSONString(returnData);
        System.out.println(result);//没有维修人
        PrintWriter out=resp.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
}
