<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<body>

<table border="2">
    <tr>
        <th><fmt:message key="header.value"/></th>
    </tr>
    <c:forEach var="crl" items="${carList}">
        <tr>
            <td><c:out value="${crl.id}"/></td>
            <td><c:out value="${crl.brand}"/></td>
            <td><c:out value="${crl.model}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>