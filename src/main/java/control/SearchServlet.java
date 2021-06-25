package control;

import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.SearchLocal;
import com.google.gson.*;
import entity.Book;

@WebServlet("/searchServlet")
public class SearchServlet extends HttpServlet {
    @EJB
    private SearchLocal s;
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
            Book rq = new Book();
            rq.setId((String)map.get("isbn"));
            rq.setTitle((String)map.get("bookname"));
            rq.setAuthor((String)map.get("author"));
            rq.setSort((String)map.get("sort"));
            String startStr = (String)map.get("start");
            String endStr = (String)map.get("end");
            Date start = null;
            Date end = null;
            if (!startStr.equals("")) {
                start = Date.valueOf(startStr.replace("/", "-"));
            }
            if (!endStr.equals("")) {
                end = Date.valueOf(endStr.replace("/", "-"));
            }
            double down = Double.parseDouble((String)map.get("down"));
            double up = Double.parseDouble((String)map.get("up"));

            String result = s.search(rq, start, end, down, up);
            PrintWriter out = response.getWriter();
            if (!result.equals("[]")) {
                out.write("{ \"search\": true, " + "\"books\": " + result + " }");
            }
            else {
                out.write("{ \"search\": false }" );
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
