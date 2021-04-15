package Servlets;

import com.alibaba.fastjson.JSON;
import dao.EquipmentDao;
import entity.Equipment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "QueryAllEquipmentType",urlPatterns = "/servlet/equip/queryAllEquipmentType")
public class QueryAllEquipmentType extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");
        EquipmentDao equipmentDao = new EquipmentDao();
        String type = req.getParameter("equipmentType");
        String c = "and type = '"+type+"'";
        try {
            List<Equipment> list = equipmentDao.selectEquInfoBy(c);
            String result = JSON.toJSONString(list);
            PrintWriter out = resp.getWriter();
            out.println(result);
            out.flush();
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
