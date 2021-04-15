package Servlets;

import com.alibaba.fastjson.JSON;
import dao.OrderDao;
import entity.Order;
import entity.ReturnData;
import service.EquipmentService;

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

@WebServlet(name = "OrderListServlet",urlPatterns = "/servlet/order/queryList")
public class OrderListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        String queryName=req.getParameter("queryName");
        String queryType = req.getParameter("queryType");
        String status = req.getParameter("status");
        String pageNumber=req.getParameter("pageIndex");
        String offset=req.getParameter("offset");
        String pageSize=req.getParameter("pageSize");
//        System.out.println("queryName:"+queryName+"\n"+"queryType:"+queryType+"\n"+"status:"+status);
        try {
            backPageing(queryName,queryType,status,pageNumber,offset,pageSize,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void backPageing(String queryName, String queryType,String status,String pageNumber, String offset, String pageSize, HttpServletResponse resp) throws IOException, SQLException {
        ReturnData<Order> returnData=new ReturnData<>();
        resp.setContentType("text/json;charset=utf-8");
        List<Order> querylist=new ArrayList<>();
        int ioffset=Integer.parseInt(offset);
        int ipageNumber=Integer.parseInt(pageNumber);
        int ipageSize=Integer.parseInt(pageSize);
        String condition = "";
        if(queryName!=null&& !queryName.isEmpty()){
            condition+="and  equipment.`name` LIKE'%"+queryName+"%'";
        }
        if(queryType!=null&&!queryType.isEmpty())
        {
            condition+=" and equipment.`type` like '%"+queryType+"%'";
        }
        if(status!=null&&!status.isEmpty())
        {
            condition+=" and  status = '"+status+"'";
        }
//        System.out.println("condition:"+condition);
        querylist=new OrderDao().selectBy(condition,ipageNumber,ipageSize);
        int rows=new OrderDao().countRows(condition);
        returnData.setTotal(rows);
        returnData.setRows(querylist);
        String result= JSON.toJSONString(returnData);
        System.out.println(result);
        PrintWriter out=resp.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
}
