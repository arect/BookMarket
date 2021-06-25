package control;

import biz.GetOrderLocal;
import biz.LoginLocal;
import com.google.gson.Gson;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/printServlet")
public class PrintServlet extends HttpServlet {
    @EJB
    private LoginLocal l;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            String msg = req.getParameter("username");
            int identity = l.identity(msg);

            if (msg != null) {
                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                String fileName =  "/Orders-" + msg + "-" + formatter.format(date) + ".json";

                String filePath = this.getServletContext().getRealPath(fileName);
                if (identity == 2) {
                    msg = "#ALL#;" + filePath;
                }
                else {
                    msg = msg + ";" + filePath;
                }

                Context context = new InitialContext();
                ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
                Connection connection = connectionFactory.createConnection("testJNDI", "123456");
                Destination destination = (Destination) context.lookup("queue/test");
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                MessageProducer messageProducer = session.createProducer(destination);
                connection.start();
                TextMessage textMessage = session.createTextMessage(msg);

                messageProducer.send(textMessage);

                context.close();
                connection.close();

                try {
                    Thread.sleep(2000);
                }catch (Exception ignored) {}

                File file = new File(filePath);
                if (file.exists()) {
                    fileName = URLEncoder.encode(file.getName(),"UTF-8");
                    resp.reset();
                    resp.setContentType("text/xml");
                    resp.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    int fileLength = (int) file.length();
                    resp.setContentLength(fileLength);
                    if (fileLength != 0) {
                        InputStream inStream = new FileInputStream(file);
                        byte[] buf = new byte[4096];
                        ServletOutputStream servletOS = resp.getOutputStream();
                        int readLength;
                        while (((readLength = inStream.read(buf)) != -1)) {
                            servletOS.write(buf, 0, readLength);
                        }
                        inStream.close();
                        servletOS.flush();
                        servletOS.close();
                    }
                }
                else{
                    System.out.println("文件不存在");
                    PrintWriter out = resp.getWriter();
                    out.println("文件 \"" + fileName +"\" 不存在");
                    out.close();
                }
            }
            else {
                PrintWriter out = resp.getWriter();
                out.println("PRINT SERVLET");
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
