<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error!</title>
</head>
<style type="text/css">
    #error {
        border: 2;
        background-color: #ffb999;
        max-width: 500;
    }
</style>
<body>

    <h3>
        Error details:
    </h3>

    <div id="error">
        <c:out value="${pageContext.errorData.statusCode}: " />
        <c:out value="${exceptionMessage}" />
    </div>

    <p>
        <a href="/index.jsp"><i>Try again</i></a>
    </p>

</body>
</html>
