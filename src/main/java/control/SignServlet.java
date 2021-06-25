package control;

import java.io.*;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.SignLocal;
import com.google.gson.*;
import entity.User;

@WebServlet("/signServlet")
public class SignServlet extends HttpServlet {
    @EJB
    private SignLocal s;
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

            User u = new User();
            u.setId((String)map.get("phone"));
            u.setPassword((String)map.get("password"));
            u.setName((String)map.get("username"));
            u.setMail((String)map.get("mail"));

            int i = s.sign(u);

            PrintWriter out = response.getWriter();
            out.write("{ \"sign\": " + i + " }");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
