package control;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.AddBookLocal;
import biz.BuyAllLocal;
import biz.GetOrderLocal;
import com.google.gson.*;
import entity.Book;

@WebServlet("/buyAllServlet")
public class BuyAllServlet extends HttpServlet {
    @EJB
    private BuyAllLocal ba;
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

            boolean result = ba.buy((String) map.get("username"), (String) map.get("info"));
            PrintWriter out = response.getWriter();
            out.write("{ \"is\": " + result + " }" );
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
