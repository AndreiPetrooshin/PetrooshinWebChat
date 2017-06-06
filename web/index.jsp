<%--
  Created by IntelliJ IDEA.
  User: draqo
  Date: 06.06.2017
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Simple Chat</title>

    <div align="center">
      <h2> Hello to our chat</h2>
      <a>Your name is: </a>
      ${sessionScope.name}
    </div>


    <div align="center">
       <textarea cols="50" rows="20">
      <c:choose>
        <c:when test="${requestScope.toChat eq null}"/>

        <c:otherwise>
          ${requestScope.toChat}
        </c:otherwise>
      </c:choose>
    </textarea>

      <form action="chat.do" method="post">

        <input type="text" name="text">
        <input type="submit" value="Отправить">
      </form>
    </div>

  <br>
  <hr>
    <div align="center">
      <input type="button" value="reload!" onclick="reload()"  />
      <script>
        function reload() {
            location.reload()
        }
      </script>
    </div>







  </head>
  <body>
  </body>
</html>
