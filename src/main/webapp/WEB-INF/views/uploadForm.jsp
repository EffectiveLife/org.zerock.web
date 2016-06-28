<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2016-06-08
  Time: 오후 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>업로드 연습</title>
    <style>
        iframe {
            width: 0px;
            height: 0px;
            border: 0px;
        }
    </style>
</head>
<body>
<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="zeroFrame">
    <input type="file" name="file"><input type="submit">
</form>

<iframe name="zeroFrame"></iframe>

<script>
    function addFilePath(msg) {
        alert(msg);
        document.getElementById("form1").reset();
    }
</script>

</body>
</html>
