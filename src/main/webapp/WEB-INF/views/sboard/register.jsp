<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@include file="../include/header.jsp" %>

<style>
    .fileDrop {
        width: 80%;
        height: 100px;
        border: 1px dotted gray;
        background-color: lightslategrey;
        margin: auto;

    }
</style>

<!-- Main contents -->
<section class="contents">
    <div class="row">
        <!-- left column -->
        <div class="col-md-12">
            <!-- general form elements -->
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">REGISTER BOARD</h3>
                </div>
                <!-- /.box-header -->

                <form id='registerForm' role="form" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Title</label>
                            <input type="text"
                                   name='title' class="form-control" placeholder="Enter Title">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="contents" rows="3"
                      placeholder="Enter ..."></textarea>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Writer</label>
                            <input type="text"
                                   name="writer" class="form-control" value="${login.uid}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">File DROP Here</label>
                        <div class="fileDrop"></div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <div>
                            <hr>
                        </div>
                        <ul class="mailbox-attachments clearfix uploadedList"></ul>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>

            </div>
            <!-- /.box -->
        </div>
        <!--/.col (left) -->

    </div>
    <!-- /.row -->
</section>
<!-- /.contents -->
</div>
<!-- /.contents-wrapper -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script id="template" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img">
            <img src="{{imgsrc}}" alt="Attachment">
        </span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i>
            </a>
        </div>
    </li>
</script>

<script>
    var template = Handlebars.compile($("#template").html());

    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });
    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];
        var formData = new FormData();
        formData.append("file", file);

        $.ajax({
            url : '/uploadAjax',
            data : formData,
            dataType : 'text',
            processData : false,
            contentType : false,
            type : 'POST',
            success : function (data) {
                var fileInfo = getFileInfo(data);
                var html = template(fileInfo);
                $(".uploadedList").append(html);
            }
        });
    });

    function getFileInfo(fullName) {
        var fileName, imgsrc, getLink;
        var fileLink;

        if(checkImageType(fullName)) {
            imgsrc = "/displayFile?fileName="+fullName;
            fileLink = fullName.substr(14);

            var front = fullName.substr(0, 12);
            var end = fullName.substr(14);

            getLink = "/displayFile?fileName="+front+end;
        } else {
            imgsrc = "/resources/dist/img/file.png";
            fileLink = fullName.substr(12);
            getLink = "/displayFile?fileName="+fullName;
        }
        fileName = fileLink.substr(fileLink.indexOf("_")+1);

        return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
    }

    function checkImageType(fileName) {
        var pattern = /jpg|gif|png|jpeg/i;
        return fileName.match(pattern);
    }
</script>

<script>
    $("#registerForm").submit(function (event) {
        event.preventDefault();
        var str = "";
        $(".uploadedList .delbtn").each(function (index) {
            str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'> ";
        });
        $(this).append(str);
        $(this).get(0).submit();
    });
</script>

<%@include file="../include/footer.jsp" %>
