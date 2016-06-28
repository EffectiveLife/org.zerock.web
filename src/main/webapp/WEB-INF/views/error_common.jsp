<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2016-06-01
  Time: 오후 4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Common Error</title>
</head>
<body>
    <h4>${exception.getMessage()}</h4>
    <ul>
        <c:forEach items="${exception.getStackTrace()}" var="stack">
            <li>${stack.toString()}</li>
        </c:forEach>
    </ul>
</body>
</html>