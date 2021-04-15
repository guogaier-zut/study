package Servlets;

import com.alibaba.fastjson.JSONObject;
import dao.OrderDao;
import dao.UserDao;
import entity.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;

//@WebServlet(name = "OrderUpdateServlet",urlPatterns = "/servlet/order/update")
public class OrderUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println("id = " + id);
        String SN = req.getParameter("sN");//订单号
        String areaid = req.getParameter("floor");//区域id
        String location = req.getParameter("location");//具体位置
        String equipmentid = req.getParameter("equipmentName");//设备id
        int userid = 0;
        try {
            userid = new UserDao().findID(req.getParameter("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject=new JSONObject();
        String imgurl = req.getParameter("imageURL");
        String content = req.getParameter("remark");//备注
        if(id.isEmpty()|areaid.isEmpty()|equipmentid.isEmpty())
        {
            jsonObject.put("message","更新失败");
            jsonObject.put("status","fail");
        }
        else {
            Order order = new Order(Integer.parseInt(id), SN, Integer.parseInt(areaid), Integer.parseInt(equipmentid), userid, content, imgurl, location, new Date());
            boolean update = new OrderDao().update(order);

            if (update) {
                jsonObject.put("message", "更新成功");
                jsonObject.put("status", "success");
            } else {
                jsonObject.put("message", "更新失败");
                jsonObject.put("status", "fail");
            }
        }
        resp.setContentType("application/json;charset=utf-8");
        String jsonString=jsonObject.toJSONString();
        System.out.println(jsonString);
        resp.getWriter().println(jsonString);
    }
}
