
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <style type="text/css">
        caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
    </style>

    <script type="text/javascript">

        function search(){
            toPage(1);
        }

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);
            $("#examRoomForm").submit();

        }

        function addExamRoom(){
            jboxOpen("<s:url value='/recruit/examRoom/addExamRoomFrom'/>", "新增考场", 600, 285 ,true);
        }

        function editExamRoom(roomFlow){
            jboxOpen("<s:url value='/recruit/examRoom/editExamRoom'/>?roomFlow="+roomFlow, "修改考场", 600, 285 ,true);
        }

        function changeExamRoomStatus(roomFlow,recordStatus) {
            var msg = "";
            if (recordStatus == '${GlobalConstant.RECORD_STATUS_N}') {
                recordStatus = '${GlobalConstant.RECORD_STATUS_Y}';
                msg = "启用";
            } else if (recordStatus == '${GlobalConstant.RECORD_STATUS_Y}') {
                recordStatus = '${GlobalConstant.RECORD_STATUS_N}';
                msg = "停用";
            }
            jboxConfirm("确认" + msg + "该记录吗？", function () {
                var url = "<s:url value='/recruit/examRoom/changeExamRoomStatus'/>?roomFlow=" + roomFlow + "&recordStatus=" + recordStatus;
                jboxPost(url, null, function (resp) {
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        toPage($("#currentPage").val());
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="examRoomForm" action="<s:url value='/recruit/examRoom/searchExamRoomList'/>" method="post">
                    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
                    <input type="hidden" id="orgFlow" name="orgFlow" value="${orgFlow}"/>
                    <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">考场名称：</label>
                        <input id="roomName" name="roomName" type="text"
                               class="qtext" value="${roomName}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考场地址：</label>
                        <input id="roomAddress" name="roomAddress" type="text"
                               class="qtext" value="${roomAddress}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考场规模：</label>
                        <input id="examNum" name="examNum" type="text"
                               class="qtext" value="${examNum}"/>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="新&#12288;增"
                                class="searchInput" onclick="addExamRoom();"/>
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist examRoomData" style="width: 100%;">
            <tr>
                <th width="5%" >序号</th>
                <th width="10%">考场名称</th>
                <th width="20%" style="text-align: center"
                    class="rotationName">考场地址</th>
                <th width="15%" style="text-align: center">考场规模</th>
                <th width="10%" style="text-align: center">备注说明</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${examRoomList}" var="examRoom" varStatus="seq">
                <%--<tr class="body _${examRoom.speId}">--%>
                <tr>
                    <td>${seq.index + 1}</td>
                    <td>${examRoom.roomName}</td>
                    <td>${examRoom.roomAddress}</td>
                    <td>${examRoom.examNum}人</td>
                    <td>${examRoom.roomDemo}</td>
                    <td>
                        <c:if test="${map[examRoom.roomFlow] eq 'N'}" >
                            <a href="javascript:editExamRoom('${examRoom.roomFlow}')" style="color: blue">编辑</a>
                            |
                            <%--<a href="javascript:deleteExamRoom('${examRoom.roomFlow}')" style="color: blue">停用</a>--%>
                            <a href="javascript:changeExamRoomStatus('${examRoom.roomFlow}','${examRoom.recordStatus}')"
                               style="color: blue">
                                <c:if test="${GlobalConstant.RECORD_STATUS_Y eq examRoom.recordStatus}">
                                    停用
                                </c:if>
                                <c:if test="${GlobalConstant.RECORD_STATUS_Y ne examRoom.recordStatus}">
                                    启用
                                </c:if>
                            </a>
                        </c:if>
                        <c:if test="${map[examRoom.roomFlow] eq 'Y'}">
                            <a style="color:black;">考场使用中...</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty examRoomList}">
                <tr><td align="center" colspan="8">无记录</td></tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(examRoomList)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>