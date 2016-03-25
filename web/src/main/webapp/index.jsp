<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="jquery.min.js"></script>
</head>
<body>
<input type="text" name="param"> <br>
<button onclick="send()">提交</button>
<script>
    function send() {
        $.get("send?param=" + $("input[name='param']").val(), function (response) {
            console.log(response);
        });
    }
</script>
</body>
</html>
