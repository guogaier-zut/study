package Servlets;

import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        User u = new User(name,password);
        System.out.println(u);
        try {
            if(new UserDao().check(u))
            {
                //创建session
                request.getSession().setAttribute("mess", u.getName());
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }
            else
            {
                PrintWriter out = response.getWriter();
                out.print("<html>\n" +
                        "<head>\n" +
                        "    <title>登录失败</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "登陆失败，账号或密码错误，请重新登录！！！"+
                        "<a href=\"index.jsp\">点击登录</a>\n" +
                        "</body>\n" +
                        "</html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
