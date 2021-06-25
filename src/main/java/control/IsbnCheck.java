package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/isbnCheck")
public class IsbnCheck extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            request.setCharacterEncoding("UTF-8");

            String msg = request.getParameter("isbn");
            PrintWriter out = response.getWriter();
            out.write(check(msg));
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String check (String isbn) {
        if (isbn == null) {
            return "{ \"check\": false }";
        }
        isbn = isbn.replace(" ", "").replace("-", "");
        if (isbn.length() != 13) {
            return "{ \"check\": false }";
        }
        int a = 0;
        for (int i = 0; i < 12; i = i + 2) {
            a = a + isbn.charAt(i) - '0';
        }
        for (int i = 1; i < 12; i = i + 2) {
            a = a + 3 * (isbn.charAt(i) - '0');
        }
        a = 10 - a % 10;
        if (a == isbn.charAt(12) - '0') {
            return "{ \"check\": true }";
        }
        return "{ \"check\": false }";
    }
}
