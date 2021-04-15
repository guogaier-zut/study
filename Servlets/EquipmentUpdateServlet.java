package Servlets;

import com.alibaba.fastjson.JSONObject;
import dao.EquipmentDao;
import entity.Area;
import entity.Equipment;
import service.AreaService;
import service.EquipmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EquipmentUpdateServlet",urlPatterns = "/servlet/equip/update")
public class EquipmentUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String type=req.getParameter("type");
        String manager = req.getParameter("manager");
        String tip = req.getParameter("tip");
        String id = req.getParameter("id");
        JSONObject jsonObject=new JSONObject();
        boolean update=new EquipmentService().update(new Equipment(Integer.parseInt(id),name,type,manager,tip));
        if(update){
            jsonObject.put("message","操作成功");
            jsonObject.put("status","success");
        }else {
            jsonObject.put("message","操作失败");
            jsonObject.put("status","fail");
        }


        resp.setContentType("application/json;charset=utf-8");
        String jsonString=jsonObject.toJSONString();
        resp.getWriter().println(jsonString);
    }
}
