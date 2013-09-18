<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Simple forum</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='<c:out value="resources/libs/bootstrap/css/bootstrap.min.css"/>' rel="stylesheet">
    <link href='<c:out value="resources/css/main.css"/>' rel="stylesheet">
    <script type="text/javascript" src='<c:url value="/resources/js/libs/jquery-1.10.2.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/libs/json.min.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/libs/bootstrap/js/bootstrap.min.js"/>' language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/utils.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/topic.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/message.js"/>'  language="javascript"> </script>
    <script type="text/javascript" src='<c:url value="/resources/js/main.js"/>'  language="javascript"> </script>
</head>
<body>
<spring:url var = "prefixUrl" value='/' />
<script type="text/javascript">
    addAjax("${prefixUrl}");
</script>
<div class="container">
    <div class="row">
        <div class="span4">
            <h1>SimpleForum</h1>
        </div>
        <div id="buttonCreateTopic" class="span3 offset5 margintop">
            <a href="#modalCreateTopic" class="btn btn-large btn-block" data-toggle="modal">Create topic</a>
        </div>
        <div id="labelTopicName" class="deleted">
            <div class="span8 divtopic"><p id="topicName" class="text-center topic"></p></div>
            <!--<div id="deleteTopicOnMessages" class="span1 divtopic"></div>-->
        </div>
    </div>
    <div class="row">
        <div id="sendMessageBox" class="hidden">

                <form id="textMessageForm">
                    <div class="span11">
                        <input id="titleIdMessageForm" type="hidden" name="titleId" value=""/>
                        <textarea id="textMessage" class="form-control fullwidth" rows="2"></textarea>
                    </div>
                </form>
            <div class="span1"><button id="btnSendMessage" class="btn btn-block messagesBox" type="submit">Send</button></div>
        </div>
        <div id="errorMessageBox" class="deleted">
            <div class="span12"><span id="errorMessage" class="label label-important messagesBox fullwidth">Important</span></div>
        </div>
        <div id="tableTopicHead" class="span12">
            <table class="table nullheight">
                <thead>
                    <tr>
                        <th class="span3"><p class="text-center"><a id="creationDateSort" href='#'>Creation date</a></p></th>
                        <th class="span3"><p class="text-center"><a id="lastUpdateDateSort" href='#'>Last update date</a></p></th>
                        <th class="span5"><p class="text-center">Topic name</p></th>
                        <th class="span1"></th>
                    </tr>
                </thead>
            </table>
        </div>
        <div id="tableMessagesHead" class="span12 deleted">
            <table class="table nullheight">
                <thead>
                    <tr>
                        <th class="span3"><p class="text-center">Creation date</p></th>
                        <th class="span8"><p class="text-center">Text message</p></th>
                        <th class="span1"></th>
                    </tr>
                </thead>
            </table>
        </div>
        <div id="tableTopicBody" onscroll='scrollTopic("/SimpleForum/", this)' class="scrolldiv span12">
            <table class="table table-hover table-striped">
            <tbody id="topicBody">
            </tbody>
            </table>
        </div>
        <div id="tableMessageBody" onscroll='scrollMessage("/SimpleForum/", this)' class="scrolldiv span12 deleted">
            <table class="table table-hover table-striped">
            <tbody id="messageBody">
            </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="span1">
            <a href='<c:url value="/"/>'>Home</a>
        </div>
        <div class="span2">
            <a href='#'>Popular topic</a>
        </div>
        <div class="span2">
            <a id="lastUpdateTopic" href='#'>Last updated topic</a>
        </div>
        <div class="span7">
            <p class="text-right">©2013, 7bits</p>
        </div>
    </div>
</div>
<div id="modalCreateTopic" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="modalCreate" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Create new Topic</h3>
    </div>
    <div class="modal-body">
        <form id="textTopicName">
            <input id="inputNameTopic" type="text" name="name" placeholder="Input name topic…">
            <span id="errorCreateTopic" class="label label-important"></span>
        </form>
    </div>
    <div class="modal-footer">
        <button id="closeCreateTopic" class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
        <button id="btnCreateNewTopic" class="btn btn-primary">Create</button>
    </div>
</div>
</body>
</html>