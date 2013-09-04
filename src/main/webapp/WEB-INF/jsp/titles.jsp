<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Simple forum</title>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/main.css"/>' />
    <script type="text/javascript" src='<c:url value="/resources/js/libs/jquery-1.10.2.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/formValidate.js"/>'  language="javascript"> </script>
</head>
<body>
    <div>
        <table>
            <thead>
                <tr>
                    <th>
                        Titles
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>
                        <c:forEach items="${titles}" var="title" varStatus="status">
                            <tr>
                                <td>
                                    <a href='<c:url value="/messages/${title.id}/1"/>'><c:out value="${title.name}" /></a>
                                </td>
                                <td>
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
                <tr>
                    <th>
                        <tr>
                            <td>
                                <spring:url var = "action" value='/titles' />
                                <form method="post" action="${action}">
                                    <input type="hidden" name="page" value="<c:out value="${page}" />"/>
                                    <input type="text" name="name" maxlength="256" />
                                    <input type="submit" value="Add new titler" />
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