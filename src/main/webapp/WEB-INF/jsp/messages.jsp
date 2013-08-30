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
                                    <c:out value="${message}" />
                                </td>
                            </tr>
                        </c:forEach>
                    </th>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>