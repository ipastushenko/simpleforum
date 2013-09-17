<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='<c:out value="resources/backbonedev/css/bootstrap.css"/>' rel="stylesheet">
    <link href='<c:out value="resources/backbonedev/css/main.css"/>' rel="stylesheet">
    <script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/jquery-1.10.2.js"/>' language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/json2.js"/>' language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/underscore.js"/>' language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/backbone.js"/>' language="javascript"> </script>

    <script type="text/javascript" src='<c:url value="/resources/backbonedev/js/view.js"/>' language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/backbonedev/js/controller.js"/>' language="javascript"> </script>

    <title>Development frontend SPA with Backbone</title>
</head>
<body>
<script type="text/template" id="topic_template">
  <p>Topics</p>
  <input type="button" id="to_messages_button" value="To Messages" />
</script>
<script type="text/template" id="message_template">
  <p>Messages</p>
</script>
<div id="container" class="container">
</div>
</body>
</html>