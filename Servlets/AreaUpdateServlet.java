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
import java.sql.SQLException;
@WebServlet(name="AreaUpdateServlet",urlPatterns = {"/servlet/area/update"})
public class AreaUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String building=req.getParameter("building");
        String floor=req.getParameter("floor");
        JSONObject jsonObject=new JSONObject();
        boolean update=new AreaService().update(new Area(Integer.parseInt(id),building,floor));
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