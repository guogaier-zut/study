package Servlets;

import com.alibaba.fastjson.JSON;
import dao.OrderDao;
import entity.Order;
import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
@WebServlet(name = "Order_Area_Equipment_Servlet",urlPatterns = "/servlet/order/queryAreaAndEquipList")
public class Order_Area_Equipment_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        OrderService orderService = new OrderService();
        Map<String, List> stringListMap = orderService.queryAreaAndEquipList();
        //List转json
        String result = JSON.toJSONString(stringListMap);
//        System.out.println(result);
        PrintWriter out = resp.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
}
