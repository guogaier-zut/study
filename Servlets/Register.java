package Servlets;

import dao.UserDao;
import entity.User;
import utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("username").trim();
        String password = MD5Utils.getMD5(request.getParameter("password").trim(),32);
        User u = new User(name,password);
        System.out.println(u);
        if(new UserDao().add(u))
        {
            PrintWriter out = response.getWriter();
            out.print("<html>\n" +
                    "<head>\n" +
                    "    <title>注册成功</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "注册成功，请牢记账号密码！！！"+
                    "<a href=\"index.jsp\">点击登录</a>\n" +
                    "</body>\n" +
                    "</html>");
//                Thread.sleep(3000);
//                response.sendRedirect("index.jsp");
        }
        else
        {
            PrintWriter out = response.getWriter();
            out.print("<html>\n" +
                    "<head>\n" +
                    "    <title>注册成功</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "注册失败，账号已存在，请重新注册！！！"+
                    "<a href=\"register.jsp\">再次注册</a>\n" +
                    "</body>\n" +
                    "</html>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
