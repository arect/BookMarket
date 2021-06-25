package control;

// 导入必需的 java 库
import biz.TestLocal;

import java.io.*;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

// 扩展 HttpServlet 类
@WebServlet("/test")
public class TestServlet extends HttpServlet {

    private String message;

    @EJB
    private TestLocal t;


    public void init() throws ServletException
    {
        // 执行必需的初始化
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");

        t.test(2,"222222");
    }

    public void destroy()
    {
        // 什么也不做
    }
}
