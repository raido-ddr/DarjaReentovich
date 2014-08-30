<%--
  Created by IntelliJ IDEA.
  User: Raido_DDR
  Date: 8/22/2014
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Roaming tariffs</h3>
<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Status</th>
        <th>Monthly fee</th>
        <th>Sms charge</th>
        <th>Incoming call charge</th>
        <th>Outgoing call charge</th>
    </tr>
    </thead>

    <c:forEach var="tariff" items="${tariffSet}">
        <c:if test="${tariff['class'].simpleName eq 'RoamingTariff'}" >
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.tariffName}</td>
                <td>${tariff.status}</td>
                <td>${tariff.monthlyFee}</td>
                <td>${tariff.smsCharge}</td>
                <td>${tariff.incomingCallCharge}</td>
                <td>${tariff.outgoingCallCharge}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>
