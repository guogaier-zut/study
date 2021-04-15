package Servlets;

import com.alibaba.fastjson.JSONObject;
import dao.EquipmentDao;
import service.EquipmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "EquipmentDeleteServlet",urlPatterns = "/servlet/equip/delete")
public class EquipmentDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        JSONObject jsonObject=new JSONObject();
        if(id!=null){
            boolean delete=new EquipmentService().delete(Integer.parseInt(id));
            if(delete){
                jsonObject.put("message","删除成功");
                jsonObject.put("status","success");
            }else {
                jsonObject.put("message","删除失败");
                jsonObject.put("status","fail");
            }
        }
        resp.setContentType("application/json;charset=utf-8");
        String jsonString=jsonObject.toJSONString();
        resp.getWriter().println(jsonString);
    }
}
