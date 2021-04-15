package Servlets;

import com.alibaba.fastjson.JSON;
import dao.AreaDao;
import entity.Area;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "QueryAllBuilding" ,urlPatterns = "/servlet/area/queryAllBuilding")
public class QueryAllBuilding extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        String building = req.getParameter("building");
        AreaDao areaDao = new AreaDao();


        List<Area> list = null;
        try {
            String s = "and building ='"+building+"'";
            list = areaDao.selectAreaInfoBy(s);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String result = JSON.toJSONString(list);
        PrintWriter out = resp.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
}
