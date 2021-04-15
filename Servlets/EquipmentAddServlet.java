package Servlets;

import com.alibaba.fastjson.JSONObject;
import dao.EquipmentDao;
import entity.Equipment;
import service.EquipmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="EquipmentAddServlet",urlPatterns = "/servlet/equip/add")
public class EquipmentAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String type=req.getParameter("type");
        String manager = req.getParameter("manager");
        String tip = req.getParameter("tip");
        boolean insert=new EquipmentService().insert(new Equipment(name,type,manager,tip));
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