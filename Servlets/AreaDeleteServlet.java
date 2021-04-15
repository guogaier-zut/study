package Servlets;

import com.alibaba.fastjson.JSONObject;
import dao.AreaDao;
import service.AreaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="AreaDeleteServlet",urlPatterns = {"/servlet/area/delete"})
public class AreaDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        JSONObject jsonObject=new JSONObject();
        if(id!=null){
            boolean delete=new AreaService().delete(Integer.parseInt(id));
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
