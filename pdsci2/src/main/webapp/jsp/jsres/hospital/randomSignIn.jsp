<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html style="height: 100%;">
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style>
        .addBorder th,.addBorder td{
            border-right: 1px solid #e7e7eb;
        }
        .addBorder th,.addBorder td:last-child{
            border-right: 0;
        }
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            selTag("randomSign");
        });

        function selTag(tabTag){
            if("randomSign"==tabTag)
            {
                $("#randomSign").removeClass("tab");
                $("#randomSign").addClass("tab_select");
                $("#randomCode").addClass("tab");
                $("#randomCode").removeClass("tab_select");
                $("#randomSignDiv").show();
            }
        }

        function saveRandom(lectureFlow) {
            var codeStratTime =  $('#codeStartTime').val();
            if(codeStratTime == ""){
                jboxTip("请选择开始时间!");
                return;
            }
            var codeEndTime = $('#codeEndTime').val();
            if(codeEndTime == ""){
                jboxTip("请选择结束时间!");
                return;
            }
            if(codeEndTime <= codeStratTime){
                jboxTip("开始时间不能大于结束时间!");
                return;
            }
            var codeStatusType = $("input[name='codeStatusType']:checked").val();
            if(codeStatusType == "" || codeStatusType == null){
                jboxTip("请选择二维码状态!");
                return;
            }
            jboxStartLoading();
            var url = "<s:url value='/jsres/lecture/saveRandomSign'/>";
            jboxPost(url,$('#randomFrom').serialize(),function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                    searchRandomSign(lectureFlow);
                    jboxEndLoading();
                }
            },null,true);
        }
        function searchRandomSign(lectureFlow){
            var url = "<s:url value='/jsres/lecture/randomSignIn'/>?lectureFlow=" + lectureFlow;
            location.replace(url);
        }

    </script>
</head>
<body>
<div class="main_hd">
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="randomSign" class="tab" onclick="selTag('randomSign');searchRandomSign('${lectureFlow}');">随机签到设置</li>
        </ul>
    </div>
</div>

<div id="randomSignDiv" style="display: none;overflow: auto;height: calc(100% - 46px);">
    <form id="randomFrom">
        <table>
            <input type="hidden" id="lectureFlow" name="lectureFlow" value="${lectureFlow}">
            <div>
            <tr>
                <td style="padding: 30px 0 0 0;">签到二维码时效：
                    <input class="validate[required] input" name="codeStartTime" id="codeStartTime" type="text" readonly="readonly"  onclick="WdatePicker({dateFmt:'HH:mm:ss'})">~
                    <input class="validate[required] input" name="codeEndTime" id="codeEndTime" type="text" readonly="readonly"  onclick="WdatePicker({dateFmt:'HH:mm:ss'})">
                </td>
                <td style="padding: 30px 0 0 40px;">签到二维码状态：
                    <input type="radio" name="codeStatusType" id="isRandom_Y" value="${GlobalConstant.FLAG_Y }" <c:if test="${randomSign.codeStatusType eq 'Y'}">checked</c:if>>
                    动态二维码
                    <input type="radio" name="codeStatusType" id="isRandom_N" value="${GlobalConstant.FLAG_N }" <c:if test="${randomSign.codeStatusType eq 'N'}">checked</c:if>>
                    静态二维码
                </td>

            </tr>
            <tr style="text-align: center;">
                <td colspan="2" style="padding-top: 20px;">
                    <input  type="button" class="btn_green" onclick="saveRandom('${lectureFlow}');"  value="设&#12288;置"/>&nbsp;
                </td>
            </tr>
            </div>
        </table>
    </form>

    <div class="search_table" style="padding: 0;margin-top: 20px;" id="content">
        <table class="grid addBorder">
            <tr>
                <th style="width: 10%;">日期</th>
                <th style="width: 10%;">签到二维码时效</th>
                <th style="width: 10%;">签到二维码状态</th>
                <th style="width: 10%;">操作</th>
            </tr>

                <c:forEach items="${randomSignList}" var="list">
            <tr>
                <td style="width: 10%;">${date}</td>
                <td style="width: 10%;">${list.codeStartTime}--${list.codeEndTime}</td>
                <td style="width: 10%;">${list.codeStatusName}</td>
                <td style="width: 10%;"><a href="<s:url value='/jsres/lecture/randomSignUrl'/>?randomFlow=${list.randomFlow}">查看二维码</a></td>
            </tr>
                </c:forEach>

                <c:if test="${empty randomSignList}">
                    <td colspan="4">无数据</td>
                </c:if>

        </table>
    </div>
</div>
</body>
</html>
