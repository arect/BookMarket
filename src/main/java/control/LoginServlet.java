package control;

import java.io.*;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.LoginLocal;
import com.google.gson.*;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @EJB
    private LoginLocal l;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            request.setCharacterEncoding("UTF-8");

            BufferedReader reader = request.getReader();
            String msg = reader.readLine();
            HashMap map = new Gson().fromJson(msg, HashMap.class);
            reader.close();

            Boolean b = l.login((String)map.get("phone"), (String)map.get("password"));
            PrintWriter out = response.getWriter();
            out.write("{ \"login\": " + b + " }");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
