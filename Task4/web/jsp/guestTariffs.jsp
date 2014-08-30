<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Guest tariffs</h3>
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
        <th>Stay-length-based discount</th>
    </tr>
    </thead>

    <c:forEach var="tariff" items="${tariffSet}">
        <c:if test="${tariff['class'].simpleName eq 'GuestTariff'}" >
            <tr>
                <td>${tariff.id}</td>
                <td>${tariff.tariffName}</td>
                <td>${tariff.status}</td>
                <td>${tariff.monthlyFee}</td>
                <td>${tariff.smsCharge}</td>
                <td>${tariff.internalCallCharge}</td>
                <td>${tariff.externalCallCharge}</td>
                <td>${tariff.stayLengthBasedDiscount}</td>
            </tr>
        </c:if>
    </c:forEach>
</table>