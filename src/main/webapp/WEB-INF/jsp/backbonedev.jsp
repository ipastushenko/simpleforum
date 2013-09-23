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
            <p class="georgia italic logo">Simple Forum</p>
        </div>
    </div>
    <div class="row">
        <div class="js-topic-name">
        </div>
        <div class="js-form-box">
        </div>
        <div class="span12 js-table-head">
        </div>
        <div class="table-body-container scroll-div span12 js-table-body-container">
            <table class="table">
            <tbody class="js-table-body">
            </tbody>
            </table>
        </div>
    </div>
    <div class="row footer-text arial">
        <div class="span1 first-footer main-footer">
            <p class="text-left full-width">
                <a href='<c:url value="/backbonedev"/>'>
                    <span class="footer-text arial underline-footer">Home</span>
                </a>
            </p>
        </div>
        <div class="span4 footer main-footer">
            <p class="text-left full-width">
                <a class="js-last-update-topic" href='#'>
                    <span class="footer-text arial underline-footer">Last updated topic</span>
                </a>
            </p>
        </div>
        <div class="span7 end-footer main-footer">
            <p class="text-right copyright">©2013, 7bits</p>
        </div>
    </div>
</div>

<!-- Templates -->

<!-- topic name -->
<script type="text/template" id="show-topic-name">
    <div class="span10 first-footer color-topic-name">
        <p class="topic-name arial js-topic-name-text"></p>
    </div>
    <div class="span2 end-footer color-topic-name">
        <p class="span2 count-message arial bold-text js-count-messages"></p>
    </div>
</script>
<script type="text/template" id="hide-topic-name">
</script>

<!-- form box -->
<script type="text/template" id="topic-form-box">
    <form class="js-topic-form">
        <div class="span9">
            <input type=text placeholder="Enter topic name" maxlength="38" class="new-topic-name arial full-width js-new-topic-name"/>
        </div>
        <div class="span3 create-topic-btn">
            <input class="btn-color create-topic-btn js-create-topic-btn georgia italic" type="submit" value="Create topic" />
        </div>
    </form>
</script>
<script type="text/template" id="message-form-box">
    <form class="js-message-form">
        <div class="span10 color-topic-name message">
            <textarea placeholder="Enter message" class="arial form-control full-width message-text js-message-text" rows="3" />
        </div>
        <div class="span2 color-topic-name btn-send">
            <input class="btn-color send-message-btn georgia italic js-send-message-btn" type="submit" value="Send" />
        </div>
    </form>
</script>

<!-- table head -->
<script type="text/template" id="topic-table-head">
    <table class="table null-height table-head">
        <thead>
            <tr>
                <th class="span3">
                    <p class="text-center topic-head">
                        <a id="creation-date-sort" href='#'>
                            <span class="topic-head-text arial underline-head">Creation date</span>
                            <img class="ref-image" src='<c:url value="/resources/backbonedev/img/ref_image_03.png"/>'>
                        </a>
                    </p>
                </th>
                <th class="span3">
                    <p class="text-center topic-head">
                        <a id="last-update-date-sort" href='#'>
                            <span class="topic-head-text arial underline-head">Last update date</span>
                            <img class="ref-image" src='<c:url value="/resources/backbonedev/img/ref_image_03.png"/>'>
                        </a>
                    </p>
                </th>
                <th class="span5"><p class="text-center topic-head-text arial">Topic name</p></th>
                <th class="span1"></th>
            </tr>
        </thead>
    </table>
</script>
<script type="text/template" id="message-table-head">
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