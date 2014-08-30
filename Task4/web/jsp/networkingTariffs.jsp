<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Networking tariffs</h3>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Status</th>
        <th>Monthly fee</th>
        <th>Sms charge</th>
        <th>Internal call charge</th>
        <th>External call charge</th>
        <th>Connection speed</th>
    </tr>
    </thead>

    <c:forEach var="tariff" items="${tariffSet}">
        <c:if test="${tariff['class'].simpleName eq 'NetworkingTariff'}" >
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.tariffName}</td>
                <td>${tariff.status}</td>
                <td>${tariff.monthlyFee}</td>
                <td>${tariff.smsCharge}</td>
                <td>${tariff.internalCallCharge}</td>
                <td>${tariff.externalCallCharge}</td>
                <td>${tariff.connectionSpeed}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>