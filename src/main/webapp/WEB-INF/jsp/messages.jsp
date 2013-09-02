<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Simple forum</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>" />
</head>
<body>
    <div class="center">
        <a href="titles.html?page=0">Go to titles</a>
        Title: <c:out value="${title}" />
        <table>
            <thead>
                <tr>
                    <th class="messages">
                        Messages
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>
                        <c:forEach items="${messages}" var="message" varStatus="status">
                            <tr>
                                <td class="message">
                                    <c:out value="${message.textMessage}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </th>
                    <c:if test="${pagePrev != -1 or pageNext != 0}">
                        <th>
                            <tr>
                                <c:if test="${pagePrev != -1}">
                                    <td>
                                        <a href="messages.html?titleId=<c:out value="${titleId}" />&page=<c:out value="${pagePrev}" />">Prev</a>
                                    </td>
                                </c:if>
                                <c:if test="${pageNext != 0}">
                                    <td>
                                        <a href="messages.html?titleId=<c:out value="${titleId}" />&page=<c:out value="${pageNext}" />">Next</a>
                                    </td>
                                </c:if>
                            </tr>
                        </th>
                    </c:if>
                </tr>
                <tr>
                    <th>
                        <tr>
                            <td>
                                <form action="messages.html" method="post">
                                    <input type="hidden" name="titleId" value="<c:out value="${titleId}" />">
                                    <input type="hidden" name="page" value="<c:out value="${page}" />">
                                    <input type="text" name="textMessage">
                                    <input type="submit" value="Send message">
                                </form>
                            </td>
                        </tr>
                    </th>
                </tr>
            </tbody>
        </table>

    </div>
</body>
</html>