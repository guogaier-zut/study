package Servlets;


import com.alibaba.fastjson.JSON;
import dao.AreaDao;
import entity.Area;
import entity.ReturnData;
import service.AreaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="AreaListServlet",urlPatterns = "/servlet/area/queryList")
public class AreaListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        String queryName=req.getParameter("queryName");
        String pageNumber=req.getParameter("pageIndex");
        String offset=req.getParameter("offset");
        String pageSize=req.getParameter("pageSize");
        try {
            backPageing(queryName,pageNumber,offset,pageSize,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void backPageing(String queryName, String pageNumber, String offset, String pageSize, HttpServletResponse resp) throws IOException, SQLException {
        ReturnData<Area> returnData=new ReturnData<>();
        resp.setContentType("text/json;charset=utf-8");
        List<Area> querylist=new ArrayList<>();
        int ioffset=Integer.parseInt(offset);
        int ipageNumber=Integer.parseInt(pageNumber);
        int ipageSize=Integer.parseInt(pageSize);
        String condition = null;
        if(queryName!=null&& !queryName.isEmpty()){
            condition=" and building like'%"+queryName+"%'";
        }
        querylist=new AreaService().selectBy(condition,ipageNumber,ipageSize);
        int rows=new AreaDao().countRows(condition);
        returnData.setTotal(rows);
        returnData.setRows(querylist);
        String result= JSON.toJSONString(returnData);
        PrintWriter out=resp.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
}