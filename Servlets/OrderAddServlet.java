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
import java.sql.SQLException;
import java.util.Date;

@WebServlet(name = "OrderAddServlet",urlPatterns = "/servlet/order/add")
public class OrderAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String SN = req.getParameter("sN");//订单号
        String building = req.getParameter("building");
        String floor = req.getParameter("floor");//区域id
        String location = req.getParameter("location");//具体位置
        String equipmentname = req.getParameter("equipmentName");//设备id
        int userid = 0;
        try {
            userid = new UserDao().findID(req.getParameter("username"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String equipmenttype = req.getParameter("equipmentType");
        String imgurl = req.getParameter("imageURL");//现在有只了但是空的，刚才好像不是，我忘记改哪了
        String content = req.getParameter("remark");//备注
//        System.out.println("订单号："+SN);
//        System.out.println(building);
//        System.out.println("区域id："+floor);
//        System.out.println("具体位置："+location);
//        System.out.println("设备id："+equipmentname);
//        System.out.println("设备类型："+equipmenttype);
//        System.out.println("图片url："+imgurl);
//        System.out.println("文字描述："+content);
        Order order = new Order(SN,Integer.parseInt(floor),Integer.parseInt(equipmentname),userid,imgurl,location,0,new Date(),content);
        boolean insert = new OrderDao().insert(order);
        JSONObject jsonObject=new JSONObject();
        if(insert){
            jsonObject.put("message","添加成功");
            jsonObject.put("status","success");
        }else {
            jsonObject.put("message","添加失败");
            jsonObject.put("status","fail");
        }
        resp.setContentType("application/json;charset=utf-8");
        String jsonString=jsonObject.toJSONString();
        System.out.println(jsonString);
        resp.getWriter().println(jsonString);
    }
}
