<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Simple forum</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />
</head>
<body>
    <div class="center">
        <table>
            <thead>
                <tr>
                    <th class="titles">
                        Titles
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>
                        <c:forEach items="${titles}" var="title" varStatus="status">
                            <tr>
                                <td class="title">
                                    <a href='<c:url value="/messages/${title.id}/1"/>'><c:out value="${title.name}" /></a>
                                </td>
                                <td class="buttonDelete">
                                    <a href='<c:url value="/removeTitle/${title.id}/${page}"/>'>Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </th>
                    <c:if test="${pagePrev != 0 or pageNext != 1}">
                        <th>
                            <tr>
                                <c:if test="${pagePrev != 0}">
                                    <td>
                                        <a href='<c:url value="/titles/${pagePrev}"/>'>Prev</a>
                                    </td>
                                </c:if>
                                <c:if test="${pageNext != 1}">
                                    <td>
                                        <a href='<c:url value="/titles/${pageNext}"/>'>Next</a>
                                    </td>
                                </c:if>
                            </tr>
                        </th>
                    </c:if>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>