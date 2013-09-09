<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Simple forum</title>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/main.css"/>' />
    <script type="text/javascript" src='<c:url value="/resources/js/libs/jquery-1.10.2.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/libs/jquery-ui.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/libs/json.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/formValidate.js"/>'  language="javascript"> </script>
</head>
<body>
    <div>
        <a href='<c:url value="/titles/1"/>'>Go to titles</a>
        Title: <c:out value="${title}" />
        <table>
            <thead>
                <tr>
                    <th>
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
                                <td class="buttonDelete">
                                    <a href='<c:url value="/removeMessage/${message.id}/${page}"/>'>Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </th>
                    <c:if test="${pagePrev != 0 or pageNext != 1}">
                        <th>
                            <tr>
                                <c:if test="${pagePrev != 0}">
                                    <td>
                                        <a href='<c:url value="/messages/${titleId}/${pagePrev}"/>'>Prev</a>
                                    </td>
                                </c:if>
                                <c:if test="${pageNext != 1}">
                                    <td>
                                        <a href='<c:url value="/messages/${titleId}/${pageNext}"/>'>Next</a>
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
                                <spring:url var = "action" value='/json/messages' />
                                <form id="sendMessage" method="post" action="${action}">
                                    <input type="hidden" name="titleId" value="<c:out value="${titleId}" />"/>
                                    <input type="text" name="textMessage" maxlength="256" />
                                    <input type="submit" value="Send message" />
                                </form>
                                <script type="text/javascript">
                                     addAjax("${action}");
                                </script>
                            </td>
                        </tr>
                    </th>
                </tr>
            </tbody>
        </table>
    </div>

    <div id="send" title="Create new Title">
      <p class="validateTips">All form fields are required.</p>

      <form id = "newSend">
      <fieldset>
        <input type="hidden" name="titleId" value="<c:out value="${titleId}" />"/>
        <input type="text" name="textMessage" maxlength="256" />
      </fieldset>
      </form>
    </div>

</body>
</html>