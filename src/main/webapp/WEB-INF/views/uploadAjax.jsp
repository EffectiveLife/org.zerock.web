<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2016-06-08
  Time: 오후 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upload with Ajax</title>
    <style>
        .fileDrop {
            width: 100%;
            height: 200px;
            border: 1px dotted deepskyblue;
        }

        small {
            margin-left: 3px;
            font-weight: bold;
            color: grey;
        }
    </style>
</head>
<body>
<h3>Ajax File Upload</h3>
<div class="fileDrop"></div>
<div class="uploadList"></div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script>
    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });
    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();
        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];
//        console.log(file);
        var formData = new FormData();
        formData.append("file", file);

        $.ajax({
            url : "/uploadAjax",
            data : formData,
            dataType : 'text',
            processData : false,
            contentType : false,
            type : 'POST',
            success : function (data) {
                var str = "";
                if(checkImageType(data)) {
                    str = "<div><a href=displayFile?fileName=" +getImageLink(data) +">"+ "<img src='displayFile?fileName=" + data + "'/>" +"</a><small data-src="+ data + ">X</small></div>";
                } else {
                    str = "<div><a href='displayFile?fileName=" + data +"'>"+getOriginalName(data) + "</a>"
                        +"<small data-src='" +data+ "'>X</small></div></div>";
                }

                $(".uploadList").append(str);
            }
        });
    });

    function  checkImageType(fileName) {
        var pattern = /jpg|gif|png|jpeg/i;
        return fileName.match(pattern);
    }

    function getOriginalName(fileName) {
        if(checkImageType(fileName)) {
            return;
        }
        var idx = fileName.indexOf("_") + 1;
        return fileName.substr(idx);
    }

    function getImageLink(fileName) {
        if(!checkImageType(fileName)) {
            return;
        }
        var front = fileName.substr(0, 12);
        var end = fileName.substr(14);

        return front + end;
    }

    $(".uploadList").on("click", "small", function (event) {
        var that = $(this);
        $.ajax({
            url : "deleteFile",
            type : "post",
            data : {fileName : $(this).attr("data-src")},
            dataType : "text",
            success : function (result) {
                if(result === 'deleted') {
                    that.parent("div").remove();
                } else {
                    alert("삭제 실패");
                }
            }
        })
    });

</script>
</body>
</html>
