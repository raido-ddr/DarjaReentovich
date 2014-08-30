<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title></title>
</head>
<style type="text/css">
    td {
        border: double black medium;
        padding: 3;
        background-color: #cbffa2;
    }

    th {
        border: outset beige medium;
        padding: 3;
        background-color: antiquewhite;
    }
</style>

<body>

    <div>
        <i><c:out value="Parsed via: ${parserType}" /></i>
    </div>

    <%@ include file="localTariffs.jsp"%>

    <%@ include file="roamingTariffs.jsp"%>

    <%@ include file="networkingTariffs.jsp"%>

    <%@ include file="guestTariffs.jsp"%>

    <%@ include file="bonusTariffs.jsp"%>

</body>
</html>
