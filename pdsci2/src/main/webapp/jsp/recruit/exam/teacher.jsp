
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
        /*table{ margin:10px 0;border-collapse: collapse;}*/
        /*caption,th,td{height:40px;}*/
        caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
        /*th{text-align:right;font-weight:500;padding-right:5px; color:#333;}*/
        /*td{text-align:left; padding-left:5px; color:#999;}*/
    </style>

    <script type="text/javascript">

        function search(){
            toPage(1);
        }

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);
            $("#examTeacherForm").submit();
        }

        function addExamTeacher(){
            jboxOpen("<s:url value='/recruit/examTeacher/addExamTeacherForm'/>", "新增监考教师", 600, 285 ,true);
        }


        function editExamTeacher(teacherFlow){
            jboxOpen("<s:url value='/recruit/examTeacher/editExamTeacher'/>?teacherFlow="+teacherFlow, "修改监考教室", 600, 285 ,true);
        }

        function deleteExamTeacher(teacherFlow) {
            jboxConfirm("确认删除吗？", function () {
                var url = "<s:url value='/recruit/examTeacher/deleteExamTeacher'/>?teacherFlow=" + teacherFlow;
                jboxPost(url, null, function (resp) {
                    if (resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                        search();
                    }
                }, null, true);
            });
        }


        // $(function(){
        //     $(".oper").each(function(){
        //         if($(this).find("a").length>1){
        //             $(this).find("a:not(:last)").after('<label>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</label>')
        //         }
        //     });
        //     $("[onclick]").click(function(e){e.stopPropagation();});
        // });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="examTeacherForm" name="examTeacherForm" action="<s:url value='/recruit/examTeacher/searchExamTeacherList'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">教师姓名：</label>
                        <input id="teaName" name="teaName" type="text"
                               class="qtext" value="${teaName}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考场名称：</label>
                        <select class="xlselect" name="roomName" id="roomName">
                            <option value="">--请选择--</option>
                            <c:forEach items="${examRoomNameList}" var="examRoomName">
                                <option value="${examRoomName}" <c:if test="${roomName eq examRoomName}">selected="selected"</c:if>>${examRoomName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">角色：</label>
                        <select class="xlselect" name="teaRole" id="teaRole">
                            <option value="">--请选择--</option>
                            <option value="teacher" <c:if test="${teaRole eq 'teacher'}">selected="selected"</c:if>>
                                带教老师
                            </option>
                            <option value="secretary" <c:if test="${teaRole eq 'secretary'}">selected="selected"</c:if>>
                                规培秘书
                            </option>
                            <option value="head" <c:if test="${teaRole eq 'head'}">selected="selected"</c:if>>
                                科主任
                            </option>
                        </select>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="新&#12288;增"
                               class="searchInput" onclick="addExamTeacher();"/>
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist examRoomData" style="width: 100%;">
            <tr>
                <th width="5%" >序号</th>
                <th width="10%">监考教师</th>
                <th width="20%" style="text-align: center"
                    class="rotationName">考场</th>
                <th width="15%" style="text-align: center">角色</th>
                <th width="10%" style="text-align: center">备注说明</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${examTeacherList}" var="examTeacher" varStatus="seq">
                <%--<tr class="body _${examRoom.speId}">--%>
                <tr>
                    <td>${seq.index + 1}</td>
                    <td>${examTeacher.teaName}</td>
                    <td>${map[examTeacher.roomFlow]}</td>
                    <td>
                        <c:if test="${examTeacher.teaRole eq 'teacher'}">带教老师</c:if>
                        <c:if test="${examTeacher.teaRole eq 'secretary'}">规培秘书</c:if>
                        <c:if test="${examTeacher.teaRole eq 'head'}">科主任</c:if>
                    </td>
                    <td>${examTeacher.teaDemo}</td>
                    <td>
                        <a href="javascript:editExamTeacher('${examTeacher.teacherFlow}')" style="color: blue">编辑</a>
                        |
                        <a href="javascript:deleteExamTeacher('${examTeacher.teacherFlow}')" style="color: blue">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty examTeacherList}">
                <tr><td align="center" colspan="8">无记录</td></tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(examTeacherList)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>