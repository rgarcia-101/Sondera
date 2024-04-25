<%@include file="head.jsp"%>
<%@include file="taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="note.js"></script>
</head>
<body>

<main>

    <button type="button" onclick="note();">Save</button>
    <p class="text-success" id="saveText"></p>
    <label for="textInput"></label>
    <textarea class="w-50" style="resize: none" id="textInput">${textContent}</textarea>

</main>

</body>
</html>
