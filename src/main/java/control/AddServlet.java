package control;

import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import biz.AddBookLocal;
import com.google.gson.*;
import entity.Book;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    @EJB
    private AddBookLocal ab;
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            request.setCharacterEncoding("UTF-8");

            BufferedReader reader = request.getReader();
            String msg = reader.readLine();
            reader.close();
            class myBook {
                public int key;
                public String bookname;
                public String author;
                public String isbn;
                public String sort;
                public String date;
                public String price;
                public String amount;
                public boolean isCheck;
                public String isbnHint;
            }
            ArrayList temp = new Gson().fromJson(msg, ArrayList.class);
            for (Object o : temp) {
                String bStr = new Gson().toJson(o);
                HashMap map = new Gson().fromJson(bStr, HashMap.class);
                Book b = new Book();
                try {
                    b.setId((String)map.get("isbn"));
                    b.setTitle((String)map.get("bookname"));
                    b.setAuthor((String)map.get("author"));
                    b.setPrice(Float.parseFloat((String)map.get("price")));
                    b.setAmount(Integer.parseInt((String)map.get("amount")));
                    b.setSort((String)map.get("sort"));
                    b.setPublish(Date.valueOf(((String)map.get("date")).replace("/", "-")));
                } catch (Exception ignored){}
                ab.add(b);
            }
            ArrayList<Integer> err = ab.insert();
            StringBuilder result = new StringBuilder();
            if (err.size() != 0) {
                result = new StringBuilder("{ \"add\": false, \"error\": [" + err.get(0));
                for (int i = 1; i < err.size(); i++) {
                    result.append(", ").append(err.get(i));
                }
                result.append("] }");
            }
            else {
                result = new StringBuilder("{ \"add\": true }");
            }
            PrintWriter out = response.getWriter();
            out.write(result.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
