package draqo.classes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by draqo on 06.06.2017.
 */
public class MyServlet extends HttpServlet{

    public static String text = "";
    public String prev = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();

        if(session.isNew()){
            session.setAttribute("name", req.getParameter("name"));
        }


        RequestDispatcher dispatcher  = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        String name = (String) req.getSession().getAttribute("name");
        String line = req.getParameter("text");
        if(line.isEmpty() || line.equals(prev)){

        }
        else {
            String temp  = name + " говорит: "   + line + '\n';
            text += temp;
        }

        prev = line;
        req.setAttribute("toChat", text);

        RequestDispatcher dispatcher  = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req,resp);

    }
}
