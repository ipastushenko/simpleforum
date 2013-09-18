<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Simple forum</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='<c:out value="resources/backbonedev/css/bootstrap.css"/>' rel="stylesheet">
    <link href='<c:out value="resources/backbonedev/css/main.css"/>' rel="stylesheet">
</head>
<body>
<!-- Body -->
<div class="container">
    <div class="row">
        <div class="span4">
            <h1>SimpleForum</h1>
        </div>
        <div id="topic-name" class="span8 div-topic">
        </div>
    </div>
    <div class="row">
        <div id="form-box">
        </div>
        <div class="span12">
            <table class="table null-height">
                <thead id="table-head">
                </thead>
            </table>
        </div>
        <div id="table-body-container" class="scroll-div span12">
            <table class="table table-hover table-striped">
            <tbody id="table-body">
            </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="span1">
            <a href='<c:url value="/SimpleForum/backbonedev"/>'>Home</a>
        </div>
        <div class="span2">
            <a href='#'>Popular topic</a>
        </div>
        <div class="span2">
            <a id="last-update-topic" href='#'>Last updated topic</a>
        </div>
        <div class="span7">
            <p class="text-right">©2013, 7bits</p>
        </div>
    </div>
</div>

<!-- Templates -->

<!-- topic name -->
<script type="text/template" id="show-topic-name">
    <p id="topic-name-text" class="text-center topic">Temp</p>
</script>
<script type="text/template" id="hide-topic-name">
</script>

<!-- form box -->
<script type="text/template" id="topic-form-box">
    <form id="topic-form">
        <div class="span10">
            <textarea id="new-topic-name" class="form-control full-width" rows="2" />
        </div>
        <div class="span2">
            <input id="create-topic-btn" class="btn btn-block message-box" type="submit" value="Create topic" />
        </div>
    </form>
</script>
<script type="text/template" id="message-form-box">
    <form id="message-form">
        <div class="span10">
            <input id="id-title-message-form" type="hidden" name="titleId" value=""/>
            <textarea id="message-text" class="form-control full-width" rows="2" />
        </div>
        <div class="span2">
            <input id="send-message-btn" class="btn btn-block message-box" type="submit" value="Send message" />
        </div>
    </form>
</script>

<!-- table head -->
<script type="text/template" id="topic-table-head">
    <tr>
        <th class="span3"><p class="text-center"><a id="creation-date-sort" href='#'>Creation date</a></p></th>
        <th class="span3"><p class="text-center"><a id="last-update-date-sort" href='#'>Last update date</a></p></th>
        <th class="span5"><p class="text-center">Topic name</p></th>
        <th class="span1"></th>
    </tr>
</script>
<script type="text/template" id="message-table-head">
    <tr>
        <th class="span3"><p class="text-center">Creation date</p></th>
        <th class="span8"><p class="text-center">Text message</p></th>
        <th class="span1"></th>
    </tr>
</script>

<!-- JS -->
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/jquery-1.10.2.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/json2.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/underscore.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/libs/backbone.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/models/model.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/controller.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/utils/utils.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/utils/message.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/utils/topic.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/utils/main.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/views/topicNameView.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/views/formBoxView.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/views/tableHeadView.js"/>' language="javascript"> </script>
<script type="text/javascript" src='<c:url value="/resources/backbonedev/js/views/tableBodyView.js"/>' language="javascript"> </script>
</body>
</html>