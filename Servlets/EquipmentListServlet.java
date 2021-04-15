package Servlets;

import com.alibaba.fastjson.JSON;
import dao.EquipmentDao;
import entity.Equipment;
import entity.ReturnData;
import service.EquipmentService;

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

@WebServlet(name="EquipmentListServlet",urlPatterns = "/servlet/equip/queryList")
public class EquipmentListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String queryName=req.getParameter("queryName");
            String queryType = req.getParameter("queryType");
            String pageNumber=req.getParameter("pageIndex");
            String offset=req.getParameter("offset");
            String pageSize=req.getParameter("pageSize");
            try {
                backPageing(queryName,queryType,pageNumber,offset,pageSize,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        private void backPageing(String queryName, String queryType,String pageNumber, String offset, String pageSize, HttpServletResponse resp) throws IOException, SQLException {
            ReturnData<Equipment> returnData=new ReturnData<>();
            resp.setContentType("text/json;charset=utf-8");
            List<Equipment> querylist=new ArrayList<>();
            int ioffset=Integer.parseInt(offset);
            int ipageNumber=Integer.parseInt(pageNumber);
            int ipageSize=Integer.parseInt(pageSize);
            String condition = null;
            if(queryName!=null&& !queryName.isEmpty()){
                condition=" and name like'%"+queryName+"%'";
            }
            if(queryType!=null&&!queryType.isEmpty())
            {
                condition=" and  type like '%"+queryType+"%'";
            }
            querylist=new EquipmentService().selectBy(condition,ipageNumber,ipageSize);
//        returnData.setTotal(8);
        //    querylist = new EquipmentDao().selectAll();
            int rows=new EquipmentDao().countRows(condition);
            returnData.setTotal(rows);
            returnData.setRows(querylist);
            String result= JSON.toJSONString(returnData);
            PrintWriter out=resp.getWriter();
            out.println(result);
            out.flush();
            out.close();
        }
}
