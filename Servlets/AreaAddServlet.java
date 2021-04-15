package Servlets;

import com.alibaba.fastjson.JSONObject;
import dao.AreaDao;
import entity.Area;
import service.AreaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="AreaAddServlet",urlPatterns = {"/servlet/area/add"})
public class AreaAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String building=req.getParameter("building");
        String floor=req.getParameter("floor");
        boolean insert=new AreaService().insert(new Area(building,floor));
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
//        System.out.println(jsonString);
        resp.getWriter().println(jsonString);
    }
}
