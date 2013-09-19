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
        <div class="span8 div-topic js-topic-name">
        </div>
    </div>
    <div class="row">
        <div class="js-form-box">
        </div>
        <div class="span12">
            <table class="table null-height">
                <thead class="js-table-head">
                </thead>
            </table>
        </div>
        <div class="scroll-div span12 js-table-body-container">
            <table class="table table-hover table-striped">
            <tbody class="js-table-body">
            </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="span1">
            <a href='<c:url value="/backbonedev"/>'>Home</a>
        </div>
        <div class="span2">
            <a href='#'>Popular topic</a>
        </div>
        <div class="span2">
            <a class="js-last-update-topic" href='#'>Last updated topic</a>
        </div>
        <div class="span7">
            <p class="text-right">©2013, 7bits</p>
        </div>
    </div>
</div>

<!-- Templates -->

<!-- topic name -->
<script type="text/template" id="show-topic-name">
    <p class="text-center topic js-topic-name-text">Temp</p>
</script>
<script type="text/template" id="hide-topic-name">
</script>

<!-- form box -->
<script type="text/template" id="topic-form-box">
    <form class="js-topic-form">
        <div class="span10">
            <textarea class="form-control full-width js-new-topic-name" rows="2" />
        </div>
        <div class="span2">
            <input class="btn btn-block message-box js-create-topic-btn" type="submit" value="Create topic" />
        </div>
    </form>
</script>
<script type="text/template" id="message-form-box">
    <form class="js-message-form">
        <div class="span10">
            <textarea class="form-control full-width js-message-text" rows="2" />
        </div>
        <div class="span2">
            <input class="btn btn-block message-box js-send-message-btn" type="submit" value="Send message" />
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