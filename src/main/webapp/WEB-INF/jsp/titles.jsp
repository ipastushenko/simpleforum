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
                                    <a href="messages.html?titleId=<c:out value="${title.id}" />&page=0"><c:out value="${title.name}" /></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </th>
                    <c:if test="${pagePrev != -1 or pageNext != 0}">
                        <th>
                            <tr>
                                <c:if test="${pagePrev != -1}">
                                    <td>
                                        <a href="titles.html?page=<c:out value="${pagePrev}" />">Prev</a>
                                    </td>
                                </c:if>
                                <c:if test="${pageNext != 0}">
                                    <td>
                                        <a href="titles.html?page=<c:out value="${pageNext}" />">Next</a>
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