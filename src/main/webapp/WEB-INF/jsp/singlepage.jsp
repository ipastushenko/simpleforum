<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Simple forum</title>
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/main.css"/>' />
    <script type="text/javascript" src='<c:url value="/resources/js/libs/jquery-1.10.2.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/libs/jquery-ui.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/libs/json.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/main.js"/>'  language="javascript"> </script>
</head>
<body>
<spring:url var = "titles" value='/json/titles' />
<script type="text/javascript">
addAjax("${titles}");
</script>
${countElements}
<ul class="items">
   <li>content</li>
   <li>content</li>
</ul>
<div id="lastPostsLoader"></div>
</body>
</html>