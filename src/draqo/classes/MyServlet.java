package draqo.classes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by draqo on 06.06.2017.
 */
public class MyServlet extends HttpServlet{

    public static String text = "";
    private Map<String, String> userMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        //Создаем сессию с пользователем
        HttpSession session = req.getSession();

        if(session.isNew() && !userMap.containsKey(req.getParameter("name")) ){
            //Задаем имя пользователя сессии и добавляем в список
            session.setAttribute("name", req.getParameter("name"));
            userMap.put(req.getParameter("name"), "");
        }
        else{
            throw  new ServletException("A user with the same name already exists \n " +
                    "Or you have been already registered");
        }

        RequestDispatcher dispatcher  = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        //Получаем имя по сессии и отправленный текст
        String name = (String) req.getSession().getAttribute("name");
        String line = req.getParameter("text");

        if(!userMap.get(name).equalsIgnoreCase(line)){
            String temp  = name + " говорит: "   + line + '\n';
            text += temp;
            userMap.put(name,line);
        }


        req.setAttribute("toChat", text);

        RequestDispatcher dispatcher  = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req,resp);

    }
}
