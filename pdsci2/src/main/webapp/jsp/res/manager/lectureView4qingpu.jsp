<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
            var score = "${evaMap[lecture.lectureFlow]}";
            $("."+"${lecture.lectureFlow}").find("li:lt("+score+")").css("color","#ffb60b");
            </c:forEach>
        });
        function search(){
            $("#searchForm").submit();
        }
        function detail(lectureFlow) {
            jboxOpen("<s:url value='/res/lecture4qingpu/manager/lectureDetail'/>?lectureFlow=" + lectureFlow, '讲座信息', 1000, 460);
        }
        function del(lectureFlow){
            jboxConfirm("确认删除?",function(){
                jboxPost("<s:url value='/res/lecture4qingpu/manager/delLecture'/>?lectureFlow="+lectureFlow,null,function(resp){
                    jboxEndLoading();
                    if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                        jboxTip("删除成功！");
                        search();
                    }
                },null,false);
            });
        }
        function getEva(lectureFlow,flag) {
            jboxOpen("<s:url value='/res/lecture4qingpu/manager/getEva'/>?lectureFlow=" + lectureFlow+"&flag="+flag, '评价视图查看', 650, 600);
        }
        function signUrl(lectureFlow){
            jboxOpen("<s:url value='/res/lecture4qingpu/manager/signUrl'/>?lectureFlow=" + lectureFlow,'签到二维码',305,305);
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function uploadFile(lectureFlow) {
            jboxOpen("<s:url value='/res/lecture4qingpu/uploadFile'/>?lectureFlow="+lectureFlow,"上传附件",450,150);
        }

        function delFile(lectureFlow) {
            jboxConfirm("确认删除？",function(){
                jboxPost("<s:url value='/res/lecture4qingpu/delFile'/>?lectureFlow="+lectureFlow,null,function(){
                    $("#"+lectureFlow+"Del").hide();
                    $("#"+lectureFlow+"Span").hide();
                    $("#"+lectureFlow+"Up").text("上传");
                    $("#"+lectureFlow+"Value").val("");
                },null,false);
            })
        }

        function exportExl(){
            var url = "<s:url value='/res/lecture4qingpu/exportLectureInfo'/>";
            jboxTip("导出中…………");
            jboxExp($("#searchForm"),url);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/res/lecture4qingpu/manager/lectureView'/>" method="post">
                <input value="" name="currentPage" type="hidden" id="currentPage">
                <div class="queryDiv" style="min-width: 800px;max-width: 800px;">
                    <div class="inputDiv">
                        <label class="qlable">讲座日期：</label>
                        <input type="text" name="lectureTrainDate" value="${param.lectureTrainDate}" class="qtext"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">讲座类型：</label>
                        <select name="lectureTypeId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${qingpuLectureTypeEnumList}" var="dict">
                                <option value="${dict.id}" <c:if test="${dict.id eq param.lectureTypeId}">selected</c:if>> ${dict.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">主讲人：</label>
                        <input type="text" name="lectureTeacherName" value="${param.lectureTeacherName}" class="qtext">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">讲座标题：</label>
                        <input type="text" name="lectureContent" value="${param.lectureContent}" class="qtext">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">讲座地点：</label>
                        <input type="text" name="lectureTrainPlace" value="${param.lectureTrainPlace}" class="qtext">
                    </div>
                    <div class="lastDiv" style="min-width: 270px;max-width: 270px;">
                        &#12288;
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                        <input type="button" class="searchInput" value="新&#12288;增" onclick="detail('','')">
                        <input type="button" class="searchInput" value="导&#12288;出" onclick="exportExl()">
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <th style="min-width:200px;width:1%;">讲座标题</th>
                <th style="min-width:100px;width:1%;">主讲人</th>
                <th style="min-width:120px;width:1%;">讲座日期</th>
                <th style="min-width:120px;width:1%;">开始时间</th>
                <th style="min-width:120px;width:1%;">结束时间</th>
                <th style="min-width:250px;width:1%;">讲座地点</th>
                <th style="min-width:200px;width:1%;">讲座内容</th>
                <th style="min-width:70px;width:1%;">课件</th>
                <th style="min-width:150px;width:1%;">评价查看</th>
                <th style="min-width:150px;width:93%;">操作</th>
            </tr>
            <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
                <tr>
                 <td>${lecture.lectureContent}(${lecture.lectureTypeName})</td>
                 <td>${lecture.lectureTeacherName}</td>
                 <td>${lecture.lectureTrainDate}</td>
                 <td>${lecture.lectureStartTime}</td>
                 <td>${lecture.lectureEndTime}</td>
                 <td>${lecture.lectureTrainPlace}</td>
                 <td>${lecture.lectureDescription}</td>
                 <td>
                 <c:if test="${pdfn:getCurrDate() ge lecture.lectureTrainDate}">
                     <span id="${lecture.lectureFlow}Span" style="display:${empty lecture.coursewareUrl?'none':''} ">
			            <a href="${sysCfgMap['upload_base_url']}${lecture.coursewareUrl}" target="_blank" style="color:blue">查看</a>
                     </span>
                     <a id="${lecture.lectureFlow}Up" href="javascript:uploadFile('${lecture.lectureFlow}');" style="color:blue">${empty lecture.coursewareUrl?'':'重新'}上传</a>
                     <a id="${lecture.lectureFlow}Del" href="javascript:delFile('${lecture.lectureFlow}');"  style="color:blue;${empty lecture.coursewareUrl?'display:none':''}">删除</a>
                 </c:if>
                 </td>
                 <%--<c:if test="${lecture.lectureTypeId eq qingpuLectureTypeEnumTrainingForTeachers.id}">--%>
                <c:if test="${empty evaMap[lecture.lectureFlow]}">
                    <td onclick="getEva('${lecture.lectureFlow}','Y')" style="cursor: pointer;padding-left: 38px;">
                        <ul class="star ${lecture.lectureFlow}">
                            <li style="float: left;margin-right: 5px;">☆</li>
                            <li style="float: left;margin-right: 5px">☆</li>
                            <li style="float: left;margin-right: 5px">☆</li>
                            <li style="float: left;margin-right: 5px">☆</li>
                            <li style="float: left;margin-right: 5px">☆</li>
                        </ul>
                    </td>
                </c:if>
                <c:if test="${!(empty evaMap[lecture.lectureFlow])}">
                    <td onclick="getEva('${lecture.lectureFlow}','N')" style="cursor: pointer;padding-left: 38px;">
                        <ul class="star ${lecture.lectureFlow}">
                            <li style="float: left;margin-right: 5px;">★</li>
                            <li style="float: left;margin-right: 5px">★</li>
                            <li style="float: left;margin-right: 5px">★</li>
                            <li style="float: left;margin-right: 5px">★</li>
                            <li style="float: left;margin-right: 5px">★</li>
                        </ul>
                    </td>
                </c:if>
                 <%--</c:if>--%>
                 <%--<c:if test="${lecture.lectureTypeId ne qingpuLectureTypeEnumTrainingForTeachers.id}">--%>
                    <%--<td>--%>
                        <%--<c:if test="${empty evaMap[lecture.lectureFlow]}">--%>
                            <%--<a onclick="getEva('${lecture.lectureFlow}','Y')" style="cursor: pointer;">暂无评价</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${!(empty evaMap[lecture.lectureFlow])}">--%>
                            <%--<a onclick="getEva('${lecture.lectureFlow}','N')" style="cursor: pointer;">${evaMap[lecture.lectureFlow]}</a>--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                 <%--</c:if>--%>
                 <td>
                    <a onclick="detail('${lecture.lectureFlow}','')" style="cursor: pointer">[编辑]</a>
                    <a onclick="del('${lecture.lectureFlow}')" style="cursor: pointer">[删除]</a>
                    <a onclick="signUrl('${lecture.lectureFlow}')" style="cursor: pointer">[二维码查看]</a>
                 </td>
                </tr>

            </c:forEach>
            <c:if test="${empty lectureInfos}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div  class="page"  style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(lectureInfos)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>
