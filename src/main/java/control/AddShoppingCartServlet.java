package control;

import java.io.*;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.AddCartLocal;
import com.google.gson.*;

@WebServlet("/addShoppingCartServlet")
public class AddShoppingCartServlet extends HttpServlet {
    @EJB
    private AddCartLocal ac;
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

            boolean b = ac.add((String)map.get("username"), (String)map.get("isbn"));
            PrintWriter out = response.getWriter();
            out.write("{ \"add\": " + b + " }");
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
