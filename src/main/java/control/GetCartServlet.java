package control;

import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.GetOrderLocal;
import biz.SearchLocal;
import com.google.gson.*;
import entity.Book;

@WebServlet("/getCartServlet")
public class GetCartServlet extends HttpServlet {
    @EJB
    private GetOrderLocal go;
    @Override
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

            String result = go.getOrders((String) map.get("username"), false);
            PrintWriter out = response.getWriter();
            if (!result.equals("[]")) {
                out.write("{ \"is\": true, " + "\"orders\": " + result + " }");
            }
            else {
                out.write("{ \"is\": false }" );
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
