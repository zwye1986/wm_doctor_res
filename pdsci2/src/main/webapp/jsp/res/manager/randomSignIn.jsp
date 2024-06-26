<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html style="height: 100%;">
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker2" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        $(document).ready(function () {
            tabClick("randomSign");
        });

        // tab页切换
        function tabClick(tabType) {
            if ("randomSign" == tabType) {
                $("#randomSignDiv").show();
            }
        }

        function saveRandom(lectureFlow) {
            var codeStratTime = $('#codeStartTime').val();
            if (codeStratTime == "") {
                jboxTip("请选择开始时间!");
                return;
            }
            var codeEndTime = $('#codeEndTime').val();
            if (codeEndTime == "") {
                jboxTip("请选择结束时间!");
                return;
            }
            if (codeEndTime <= codeStratTime) {
                jboxTip("开始时间不能大于结束时间!");
                return;
            }
            var codeStatusType = $("input[name='codeStatusType']:checked").val();
            if (codeStatusType == "" || codeStatusType == null) {
                jboxTip("请选择二维码状态!");
                return;
            }
            jboxStartLoading();
            var url = "<s:url value='/res/manager/saveRandomSign'/>";
            jboxPost(url, $('#randomFrom').serialize(), function (resp) {
                jboxEndLoading();
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    searchRandomSign(lectureFlow);
                }
            }, null, true);
        }

        function searchRandomSign(lectureFlow) {
            var url = "<s:url value='/res/manager/randomSignIn'/>?lectureFlow=" + lectureFlow;
            location.replace(url);
        }
    </script>
</head>
<body>
<div class="main_hd">
    <div class="title_tab">
        <ul>
            <li id="randomSign" class="tab_select" onclick="tabClick('randomSign');searchRandomSign('${lectureFlow}')">
                <a>随机签到设置</a></li>
        </ul>
    </div>
</div>
<div class="main_bd">
    <div style="padding:10px 25px 0;">
        <form id="randomFrom" method="post">
            <input type="hidden" name="lectureFlow" value="${lectureFlow}"/>
            <table class="searchTable" style="border-collapse: separate;border-spacing: 0px 10px;line-height: normal;">
                <tr>
                    <td class="td_right">签到二维码时效：</td>
                    <td class="td_left">
                        <input class="input validate[required] input" name="codeStartTime" id="codeStartTime"
                               type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})">~
                        <input class="input validate[required] input" name="codeEndTime" id="codeEndTime" type="text"
                               readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm:ss'})">
                    </td>
                </tr>
                <tr>
                    <td class="td_right">签到二维码状态：</td>
                    <td class="td_left">
                        <input type="radio" name="codeStatusType" id="isRandom_Y" value="${GlobalConstant.FLAG_Y }"
                               <c:if test="${randomSign.codeStatusType eq 'Y'}">checked</c:if>>
                        动态二维码
                        <input type="radio" name="codeStatusType" id="isRandom_N" value="${GlobalConstant.FLAG_N }"
                               <c:if test="${randomSign.codeStatusType eq 'N'}">checked</c:if>>
                        静态二维码
                    </td>
                    <td>
                        &#12288;<input type="button" class="btn_green" onclick="saveRandom('${lectureFlow}');" value="设&#12288;置"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table" style="height: 350px;overflow: auto">
        <table class="grid">
            <tr>
                <th>日期</th>
                <th>签到二维码时效</th>
                <th>签到二维码状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${randomSignList}" var="list">
                <tr>
                    <td>${date}</td>
                    <td>${list.codeStartTime}--${list.codeEndTime}</td>
                    <td>${list.codeStatusName}</td>
                    <td><a href="<s:url value='/res/manager/randomSignUrl'/>?randomFlow=${list.randomFlow}">查看二维码</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty randomSignList}">
                <tr>
                    <td colspan="4">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>
