package Servlets;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.AreaDao;
import dao.UserDao;
import entity.Area;
import entity.ReturnData;
import entity.User;

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

@WebServlet(name = "AjaxServlet",urlPatterns = "/Servlets/ajax")
public class AjaxServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
    //    super.doPost(req, resp);
        String queryName=req.getParameter("queryName");
        String pageNumber=req.getParameter("pageIndex");
        String offset=req.getParameter("offset");
        String pageSize=req.getParameter("pageSize");
        //前端分页方法
//        frontPageing(response);
        try {
            backPaging(queryName,pageNumber,offset,pageSize,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void backPaging(String queryName,String pageNumber,String offset,String pageSize,HttpServletResponse response) throws IOException, SQLException {
        //后端分页输出
        ReturnData<Area> returnData = new ReturnData<>();
        //设置为json类型输出
        response.setContentType("application/json;charset=utf-8");
        List<Area> querylist=new ArrayList<>();
        String condition = null;
        int ipageNumber=Integer.parseInt(pageNumber);
        int ipageSize=Integer.parseInt(pageSize);
        if(queryName!=null && !queryName.isEmpty()){
            condition=" and building like'%"+queryName+"%'";
        }
        querylist=new AreaDao().selectBy(condition,ipageNumber,ipageSize);
        int rows=new AreaDao().countRows(condition);
        returnData.setTotal(rows);
        returnData.setRows(querylist);
        String result= JSON.toJSONString(returnData);
        PrintWriter out=response.getWriter();
        out.println(result);
        out.flush();
        out.close();
    }
    private void frontPageing(HttpServletResponse response) throws IOException{
        //前端分页数据输出
        //设置为Json类型输出
        response.setContentType("application/json;charset=utf-8");
        List<Area> list = new ArrayList<>();
        list.add(new Area(1,"1号楼","1层"));
        list.add(new Area(2,"2号楼","5层"));
        list.add(new Area(3,"3号楼","2层"));
        list.add(new Area(4,"2号楼","3层"));
        list.add(new Area(5,"3号楼","5层"));
        list.add(new Area(6,"3号楼","6层"));
        list.add(new Area(7,"1号楼","4层"));
        list.add(new Area(8,"1号楼","2层"));
        list.add(new Area(9,"2号楼","4层"));
        list.add(new Area(10,"3号楼","4层"));
        list.add(new Area(11,"3号楼","3层"));
        list.add(new Area(12,"1号楼","1层"));
        String result = JSONObject.toJSONString(list);

        PrintWriter out = response.getWriter();
        out.println(result);
        System.out.println(result);
        out.flush();
        out.close();

    }
}

