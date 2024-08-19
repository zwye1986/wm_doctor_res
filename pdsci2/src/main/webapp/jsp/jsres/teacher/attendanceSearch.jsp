<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--pageEncoding="UTF-8"%>--%>
<%--<%@include file="/jsp/common/doctype.jsp" %>--%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<html>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<head>
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red;}
        .searchTable{
            width: auto;
        }
        .searchTable td{
            width: auto;
            height: auto;
            line-height: auto;
            text-align: left;
            max-width: 150px;;
        }
        .searchTable .td_left{
            word-wrap:break-word;
            width:5em;
            height: auto;
            line-height: auto;
            text-align: right;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#startDate').datepicker();
            ;
            $('#endDate').datepicker();
            ;
            $(".showInfo").on("mouseover mouseout",
                    function () {
                        $(".theInfo", this).toggle();
                    }
            );
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
            if("${data}"=="${type.id}"){
                $("#"+"${data}").attr("checked","checked");
            }
            </c:forEach>
            <c:if test="${empty datas}">
            $("#"+"${type.id}").attr("checked","checked");
            </c:if>
            </c:forEach>
        });
        function checkAll(obj){
            var f=false;
            if($(obj).attr("checked")=="checked")
            {
                f=true;
            }
            $(".docType").each(function(){
                if(f)
                {
                    $(this).attr("checked","checked");
                }else {
                    $(this).removeAttr("checked");
                }

            });
        }
        function changeAllBox(){
            if($(".docType:checked").length==$(".docType").length)
            {
                $("#all").attr("checked","checked");
            }else{
                $("#all").removeAttr("checked");
            }
        }

        function attendanceSearch2(form)
        {
            <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_BASE eq sessionScope.userListScope}">
                jboxPostLoad("mainDiv", "<s:url value='/jsres/teacher/attendanceSearch/${roleId}'/>?baseFlag=${param.baseFlag}", form, true);
            </c:if>
            <c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq sessionScope.userListScope}">
                attendanceSearch(form);
            </c:if>
        }
        function toPage(page) {
            page = page || "${param.currentPage}";
            $("#currentPage").val(page);
            var form = $("#searchForm").serialize();
            attendanceSearch2(form);
        }
        function shenHe(obj) {
            var s = $(obj).val();
            if (s == "Y") {
                $(obj).val("");
                b = $(obj).val();
            }
            if (s == "") {
                $(obj).val("Y");
                b = $(obj).val();
            }
            var form = $("#searchForm").serialize();
            attendanceSearch2(form);
        }
        function search7day2() {
            $("#currentPage").val(1);
            <c:set var="currDate" value="${pdfn:getCurrDate()}" scope="page"></c:set>
            <c:set var="startDate" value="${pdfn:addDate(currDate,-7)}"></c:set>
            var exp = "search7day";
            $("#searchType").val(exp);
            $("#startDate").val("${startDate}");
            $("#endDate").val("${currDate}");
            var form = $("#searchForm").serialize();
            attendanceSearch2(form);
        }
        function searchMonth2() {
            $("#currentPage").val(1);
            <c:set var="currDate" value="${pdfn:getCurrDate()}" scope="page"></c:set>
            <c:set var="startDate" value="${pdfn:getMonth()}"></c:set>
            var exp = "searchMonth";
            $("#searchType").val(exp);
            $("#startDate").val("${startDate}" + "-01");
            $("#endDate").val("${currDate}");
            var form = $("#searchForm").serialize();
            attendanceSearch2(form);
        }
        function searchAtendanceByitems() {
            console.log("searchAtendanceByitems");
            var startDate = $("#startDate").val();
            var endDate = $("#endDate").val();
            var currentDate = "${pdfn:getCurrDate()}";
            if (endDate > currentDate) {
                var startDate = $("#itemsDate").attr("schStartDate");
                var endDate = $("#itemsDate").attr("schEndDate");
                $("#startDate").val(startDate);
                $("#endDate").val(endDate);
                jboxTip("只能查询当前日期之前的记录");
                return;
            }
            if (startDate > endDate) {
                var startDate = $("#itemsDate").attr("schStartDate");
                var endDate = $("#itemsDate").attr("schEndDate");
                $("#startDate").val(startDate);
                $("#endDate").val(endDate);
                jboxTip("开始日期不能小于截止日期");
                return;
            }
            $("#currentPage").val(1);
            var form = $("#searchForm").serialize();
            attendanceSearch2(form);
        }
        function saveAttend(attendanceFlow, statueId, statueName, doctorFlow, attendDate, exp) {
            var remarks = $(exp).val();
            if (typeof(remarks) == "undefined") {
                remarks = "";
            }
            var url = "<s:url value='/jsres/teacher/modifyAttendance?attendanceFlow='/>" + attendanceFlow + "&statueId=" + statueId + "&statueName=" + encodeURIComponent(encodeURIComponent(statueName)) + "&doctorFlow=" + doctorFlow + "&attendDate=" + attendDate + "&remarks=" + encodeURIComponent(encodeURIComponent(remarks));
            jboxPost(url, null, function () {
                var form = $("#searchForm").serialize();
                attendanceSearch2(form);
            }, null, true);
        }

        function returnAttend(attendanceFlow) {

            var url = "<s:url value='/jsres/teacher/returnAttendance?attendanceFlow='/>" + attendanceFlow;
            jboxPost(url, null, function () {
                var form = $("#searchForm").serialize();
                attendanceSearch2(form);
            }, null, true);
        }

        function saveAttendAll() {
            var attendanceFlows = new Array();
            var attendDates = new Array();
            var doctorFlows = new Array();
            var i = 0;
            $("td input[type='checkbox'][class='attendanceFlow']:checked").each(function () {
                attendanceFlows[i] = $(this).attr("attendanceFlow");
                doctorFlows[i] = $(this).attr("doctorFlow");
                attendDates[i] = $(this).parent().next().html();
                i++;
            });
            if (attendanceFlows.length == 0) {
                jboxTip("您还没有勾选！");
                return;
            }
            var attendanceFlowsStr = attendanceFlows.join(",");
            var attendDatesStr = attendDates.join(",");
            var doctorStr = doctorFlows.join(",");
            var url = "<s:url value='/jsres/teacher/modifySelected'/>";
            var data = {
                "attendanceFlowsStr": attendanceFlowsStr,
                "doctorStr": doctorStr,
                "attendDatesStr": attendDatesStr
            }
            jboxConfirm("确认所勾选学员审核通过？", function () {
                jboxPost(url, data, function () {
                    var form = $("#searchForm").serialize();
                    attendanceSearch2(form);
                }, null, true);
            });
        }
        function selectAll(exp) {
            var select = $(exp).attr("checked");
            if (typeof(select) == "undefined") {
                select = false;
            }
            $("td input[type='checkbox']").attr("checked", select);
        }
        function exportAttendance() {
            if (${empty jsResAttendanceExts}) {
                jboxTip("当前无记录!");
                return;
            }
            var roleId = "${roleId}";
            var url = "<s:url value='/jsres/teacher/exportAttendance?roleId='/>" + roleId+"&baseFlag=${param.baseFlag}";
            console.log(url);
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
        }
        function searchDeptList(orgFlow){
            jboxPost("<s:url value='/jsres/teacher/searchDeptList'/>?orgFlow="+orgFlow, null, function (resp) {
                $("#deptFlow").empty();
                $("#deptFlow").append("<option value=''>请选择</option>");
                if (null != resp && resp.length > 0) {
                    for(var i= 0;i<resp.length;i++){
                        $("#deptFlow").append("<option value='"+resp[i].deptFlow+"'>"+resp[i].deptName+"</option>");
                    }
                }
            },null,false);
        }
    </script>
</head>

<body>
<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope}">
    <%--<div class="main_hd">--%>
        <%--<h2 class="underline">学员考勤查询</h2>--%>
    <%--</div>--%>
</c:if>
<c:if test="${GlobalConstant.RES_ROLE_SCOPE_TEACHER eq sessionScope.userListScope}">
    <div class="main_hd">
        <h2 class="underline">学员考勤审核</h2>
    </div>
</c:if>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
            <input id="itemsDate" type="hidden" schStartDate="${schStartDate}" schEndDate="${schEndDate}"/>
            <input id="searchType" type="hidden" name="searchType" value=""/>
            <%--基地管理员角色--%>

            <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_BASE eq sessionScope.userListScope}">

                <div class="form_search">
                    <div class="form_item">
                        <div class="form_label">出勤状态：</div>
                        <div class="form_content">
                            <select class="select" id="statueId" name="statueId" >
                                <option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部
                                </option>
                                <option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤
                                </option>
                                <option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤
                                </option>
                                <option value="-1" <c:if test="${param.statueId==-1}">selected="selected"</c:if>>轮休
                                </option>
                            </select>
                        </div>
                    </div>


                    <div class="form_item">
                        <div class="form_label">学生姓名：</div>
                        <div class="form_content">
                            <input type="text" name="studentName" class="input" value="${param.studentName}"  />
                        </div>
                    </div>


                    <div class="form_item">
                        <div class="form_label">带教老师：</div>
                        <div class="form_content">
                            <input type="text" name="teacherName" class="input" value="${param.teacherName}"  />
                        </div>
                    </div>





                    <c:if test="${JointOrgCount ne '0'}">
                    <div class="form_item">
                        <div class="form_label">培训基地：</div>
                        <div class="form_content">
                            <select class="select" id="orgFlow" name="orgFlow"  onchange="searchDeptList(this.value)">
                                <option value="" <c:if test="${empty param.orgFlow}">selected="selected"</c:if>>全部</option>
                                <c:forEach items="${orgList}" var="org">
                                    <option value="${org.orgFlow}" <c:if test="${param.orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    </c:if>

                    <div class="form_item">
                        <div class="form_label">科&#12288;&#12288;室：</div>
                        <div class="form_content">
                            <select class="select" id="deptFlow" name="deptFlow" >
                                <option value="">全部</option>
                                <c:if test="${not empty param.orgFlow or JointOrgCount eq '0'}">
                                    <c:forEach items="${deptList}" var="dept" varStatus="num">
                                        <option value="${dept.deptFlow}" name="${dept.deptFlow}"
                                                <c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>


                <div class="form_item">
                    <div class="form_label">考勤时间：</div>
                    <div class="form_content">
                        <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                               class="input datepicker" readonly="readonly" style="width: 80px;"/>
                        ~
                        <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                               class="input datepicker" readonly="readonly" style="width: 80px;"/>
                    </div>
                </div>

                    

                    <div class="form_item">
                        <div class="form_label">快速查询：</div>
                        <div class="form_content">
                            <input id="search7day" name="searchMonth" type="radio" value="" onclick="search7day2();"
                                   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>
                            <label for="search7day">最近7天</label>&#12288;
                            <input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"
                                   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>
                            <label for="searchMonth">本月</label>
                        </div>
                    </div>


                     <div class="form_item" >
                        <div class="form_label" style="width: auto">是否有考勤记录：</div>
                        <div class="form_content">
                            <select class="select" id="kqType" name="kqType" >
                                <option value="" <c:if test='${kqType eq ""}'>selected="selected"</c:if>>全部
                                </option>
                                <option value="Y" <c:if test='${kqType eq "Y"}'>selected="selected"</c:if>>是
                                </option>
                                <option value="N" <c:if test='${kqType eq "N"}'>selected="selected"</c:if>>否
                                </option>
                            </select>
                        </div>
                    </div>

                     <div class="form_item" style="width: 400px">
                        <div class="form_label">人员类型：</div>
                        <div class="form_content">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </div>
                    </div>


                </div>

                <div class="form_btn">
                    <input type="button" class="btn_green" onclick="searchAtendanceByitems();"  value="查&#12288;询"/>
                    <input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>
                </div>



<%--                <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--                    <tr>--%>
<%--                        <td class="td_left">出勤状态：</td>--%>
<%--                        <td>--%>
<%--                            <select class="select" id="statueId" name="statueId" style="width: 100px;margin-left: 0px">--%>
<%--                                <option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部--%>
<%--                                </option>--%>
<%--                                <option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤--%>
<%--                                </option>--%>
<%--                                <option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤--%>
<%--                                </option>--%>
<%--                                <option value="-1" <c:if test="${param.statueId==-1}">selected="selected"</c:if>>轮休--%>
<%--                                </option>--%>
<%--                            </select>--%>
<%--                        </td>--%>
<%--                        <td class="td_left">学生姓名：</td>--%>
<%--                        <td>--%>
<%--                            <input type="text" name="studentName" class="input" value="${param.studentName}"  style="width: 100px;margin-left: 0px"/>--%>
<%--                        </td>--%>
<%--                        <td class="td_left">考勤时间：</td>--%>
<%--                        <td colspan="3">--%>
<%--                            <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"--%>
<%--                                   class="input datepicker" readonly="readonly" style="width: 90px;"/>--%>
<%--                            ~--%>
<%--                            <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"--%>
<%--                                   class="input datepicker" readonly="readonly" style="width: 90px;"/>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <c:if test="${JointOrgCount ne '0'}">--%>
<%--                            <td class="td_left">培训基地：</td>--%>
<%--                            <td>--%>
<%--                                <select class="select" id="orgFlow" name="orgFlow" style="width: 106px;" onchange="searchDeptList(this.value)">--%>
<%--                                    <option value="" <c:if test="${empty param.orgFlow}">selected="selected"</c:if>>全部</option>--%>
<%--                                    <c:forEach items="${orgList}" var="org">--%>
<%--                                        <option value="${org.orgFlow}" <c:if test="${param.orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            </td>--%>
<%--                        </c:if>--%>
<%--                        <td class="td_left">科&#12288;&#12288;室：</td>--%>
<%--                        <td>--%>
<%--                            <select class="select" id="deptFlow" name="deptFlow" style="width: 106px;">--%>
<%--                                <option value="">全部</option>--%>
<%--                                <c:if test="${not empty param.orgFlow or JointOrgCount eq '0'}">--%>
<%--                                    <c:forEach items="${deptList}" var="dept" varStatus="num">--%>
<%--                                        <option value="${dept.deptFlow}" name="${dept.deptFlow}"--%>
<%--                                                <c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </c:if>--%>
<%--                            </select>--%>
<%--                        </td>--%>
<%--                        <td class="td_left">带教老师：</td>--%>
<%--                        <td>--%>
<%--                            <input type="text" name="teacherName" class="input" value="${param.teacherName}"  style="width: 100px;margin-left: 0px"/>--%>
<%--                        </td>--%>
<%--                        <td class="td_left">快速查询：</td>--%>
<%--                        <td >--%>
<%--                            <input id="search7day" name="searchMonth" type="radio" value="" onclick="search7day2();"--%>
<%--                                   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>--%>
<%--                            <label for="search7day">最近7天</label>&#12288;--%>
<%--                            <input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"--%>
<%--                                   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>--%>
<%--                            <label for="searchMonth">本月</label>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        <td class="td_left">人员类型：</td>--%>
<%--                        <td colspan="3">--%>
<%--                            <c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--                                <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>--%>
<%--                            </c:forEach>--%>
<%--                        </td>--%>
<%--                        <td style="width: 110px">是否有考勤记录：</td>--%>
<%--                        <td colspan="3">--%>
<%--                            <select class="select" id="kqType" name="kqType" style="width: 100px;margin-left: 0px">--%>
<%--                                <option value="" <c:if test='${kqType eq ""}'>selected="selected"</c:if>>全部--%>
<%--                                </option>--%>
<%--                                <option value="Y" <c:if test='${kqType eq "Y"}'>selected="selected"</c:if>>是--%>
<%--                                </option>--%>
<%--                                <option value="N" <c:if test='${kqType eq "N"}'>selected="selected"</c:if>>否--%>
<%--                                </option>--%>
<%--                            </select>--%>
<%--                        </td>--%>
<%--                        <td colspan="6">--%>
<%--                            <input type="button" class="btn_green" onclick="searchAtendanceByitems();"  value="查&#12288;询"/>--%>
<%--                            &#12288;<input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </table>--%>
            </c:if>
            <c:if test="${'quality' eq sessionScope.userListScope}">
                <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td class="td_left" style="width: 70px">出勤状态：</td>
                        <td>
                            <select class="select" id="statueId" name="statueId" style="width: 100px;margin-left: 0px">
                                <option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部
                                </option>
                                <option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤
                                </option>
                                <option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤
                                </option>
                                <option value="-1" <c:if test="${param.statueId==-1}">selected="selected"</c:if>>轮休
                                </option>
                            </select>
                        </td>
                        <td class="td_left"  style="width: 70px">学生姓名：</td>
                        <td>
                            <input type="text" name="studentName" class="input" value="${param.studentName}"  style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left"  style="width: 70px">考勤时间：</td>
                        <td colspan="3">
                            <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                                   class="input datepicker" readonly="readonly" style="width: 90px;"/>
                            ~
                            <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                                   class="input datepicker" readonly="readonly" style="width: 90px;"/>
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${JointOrgCount ne '0'}">
                            <td class="td_left">培训基地：</td>
                            <td>
                                <select class="select" id="orgFlow" name="orgFlow" style="width: 106px;" onchange="searchDeptList(this.value)">
                                    <option value="" <c:if test="${empty param.orgFlow}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${param.orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </c:if>
                        <td class="td_left">科&#12288;&#12288;室：</td>
                        <td>
                            <select class="select" id="deptFlow" name="deptFlow" style="width: 106px;">
                                <option value="">全部</option>
                                <c:if test="${not empty param.orgFlow or JointOrgCount eq '0'}">
                                    <c:forEach items="${deptList}" var="dept" varStatus="num">
                                        <option value="${dept.deptFlow}" name="${dept.deptFlow}"
                                                <c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </td>
                        <td class="td_left">带教老师：</td>
                        <td>
                            <input type="text" name="teacherName" class="input" value="${param.teacherName}"  style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left"  style="width: 70px">快速查询：</td>
                        <td >
                            <input id="search7day" name="searchMonth" type="radio" value="" onclick="search7day2();"
                                   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>
                            <label for="search7day">最近7天</label>&#12288;
                            <input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"
                                   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>
                            <label for="searchMonth">本月</label>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="9">
                            <input type="button" class="btn_green" onclick="searchAtendanceByitems();"  value="查&#12288;询"/>
                            &#12288;<input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>
                        </td>
                    </tr>
                </table>
            </c:if>
            <%--带教老师角色--%>
            <c:if test="${'teacher' eq roleId}">

                <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td class="td_left">出勤状态：</td>
                        <td>
                            <select class="select" id="statueId" name="statueId" style="width: 100px;margin-left: 0px">
                                <option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部
                                </option>
                                <option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤
                                </option>
                                <option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤
                                </option>
                                <option value="-1" <c:if test="${param.statueId==-1}">selected="selected"</c:if>>轮休
                                </option>
                            </select>
                        </td>
                        <td class="td_left">学生姓名：</td>
                        <td>
                            <input type="text" name="studentName" class="input" value="${param.studentName}"  style="width: 100px;margin-left: 0px"/>
                        </td>
                        <td class="td_left">考勤时间：</td>
                        <td colspan="3">
                            <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                                   class="input datepicker" readonly="readonly" style="width: 90px;"/>
                            ~
                            <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                                   class="input datepicker" readonly="readonly" style="width: 90px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="8">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                            &#12288;快速查询：&#12288;
                            <input id="search7day" name="searchMonth" type="radio" value="" onclick="search7day2();"
                                   <c:if test='${searchType eq "search7day"}'>checked="checked"</c:if>>
                            <label for="search7day">最近7天</label>&#12288;
                            <input id="searchMonth" name="searchMonth" type="radio" value="" onclick="searchMonth2();"
                                   <c:if test='${searchType eq "searchMonth"}'>checked="checked"</c:if>>
                            <label for="searchMonth">本月</label>&#12288;
                            <input type="button" class="btn_green" onclick="searchAtendanceByitems();"  value="查&#12288;询"/>
                            &#12288;<input type="button" class="btn_green" onclick="exportAttendance();" value="导&#12288;出"/>
                            &#12288;<input type="button" class="btn_green" onclick="saveAttendAll();" value="一键通过"/>
                        </td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td colspan="99">--%>
<%--                            <font color="red">注：一键通过之后，无法修改学员的签到状态。</font>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
                </table>
            </c:if>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <c:if test="${'teacher' eq roleId}">
                    <col style="width: 20px;">
                </c:if>
                <col style="width: 90px;">
                <col style="width: 90px;">
                <c:if test="${'hospital' eq roleId}">
                    <col style="width: 80px;">
                    <col style="width: 80px;">
                </c:if>
                <col style="width: 100px;">
                <c:choose>
                    <c:when test="${'teacher' eq roleId}">
                        <col style="width: 90px;">
                    </c:when>
                    <c:otherwise>
                        <col style="width: 100px;">
                    </c:otherwise>
                </c:choose>
                <col style="width: 100px;">
                <col style="width: 100px;">
                <col style="width: 100px;">
            </colgroup>
            <tr>
                <c:if test="${'teacher' eq roleId}">
                    <th>
                        <c:if test="${pdfn:getCurrDate()>=jsResAttendanceExt.jsresAttendance.attendDate}">
                            <input type="checkbox" onclick="selectAll(this)"/>
                        </c:if>
                    </th>
                </c:if>
                <th>日期</th>
                <th>姓名</th>
                <th>人员类型</th>
                <c:if test="${'hospital' eq roleId}">
                    <th>科室</th>
                    <th>带教老师</th>
                </c:if>
                <th>考勤记录</th>
                <c:choose>
                    <c:when test="${'teacher' eq roleId}">
                        <th>操作</th>
                    </c:when>
                    <c:otherwise>
                        <th>出勤状态</th>
                    </c:otherwise>
                </c:choose>
                <th>备注</th>
                <th>学员备注</th>
            </tr>
            <c:forEach items="${jsResAttendanceExts}" var="jsResAttendanceExt">
                <tr>
                    <c:if test="${'teacher' eq roleId}">
                        <td style="width: 20px;">

                            <c:if test="${pdfn:getCurrDate()>=jsResAttendanceExt.jsresAttendance.attendDate}">
                                <c:if test="${jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing'
                                 or empty jsResAttendanceExt.jsresAttendance.attendStatus}">

                                 <input doctorFlow="${jsResAttendanceExt.jsresAttendance.doctorFlow}" class="attendanceFlow" type="checkbox" attendanceFlow="${jsResAttendanceExt.jsresAttendance.attendanceFlow}"/>

                                </c:if>
                            </c:if>
                        </td>
                    </c:if>
                    <td>${jsResAttendanceExt.jsresAttendance.attendDate}</td>
                    <td>${jsResAttendanceExt.userName}</td>
                    <td>${jsResAttendanceExt.doctorTypeName}</td>
                    <c:if test="${'hospital' eq roleId}">
                        <td>${jsResAttendanceExt.sysDept.deptName}</td>
                        <td>${jsResAttendanceExt.jsresAttendance.teacherName}</td>
                    </c:if>
                    <td>
                        <c:choose>
                            <c:when test="${ not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">
                                <c:set var="length"
                                       value="${fn:length(attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow])}"></c:set>
                                <c:if test="${length >'4'}">
                                    <c:forEach
                                            items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}"
                                            var="jsresAttendanceDetail" varStatus="status">
                                        <c:if test="${status.count=='1'or status.count=='2'}">
                                            <span class="titleName"
                                                  title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
                                        </c:if>
                                        <c:if test="${length >'4' and status.count=='2'}">...&nbsp;&nbsp;</c:if>
                                        <c:if test="${length >'4'and status.count==(length) or	status.count==(length-1)}">
                                            <span class="titleName"
                                                  title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${length <='4'}">
                                    <c:forEach
                                            items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}"
                                            var="jsresAttendanceDetail" varStatus="status">
                                        <span class="titleName"
                                              title="${jsresAttendanceDetail.attendLocal}">${pdfn:cutString(jsresAttendanceDetail.attendTime,6,true,3) }&nbsp;&nbsp;</span>
                                    </c:forEach>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                暂无签到记录！
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${'teacher' eq roleId}">
                                <c:if test="${jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing' or empty jsResAttendanceExt.jsresAttendance.attendStatus}">

                                    <c:if test="${pdfn:getCurrDate()>=jsResAttendanceExt.jsresAttendance.attendDate}">
                                        <a
                                                <c:if test="${'0' eq jsResAttendanceExt.jsresAttendance.attendStatus||
                                            '-1' eq jsResAttendanceExt.jsresAttendance.attendStatus||
                                            null==jsResAttendanceExt.jsresAttendance.attendStatus}">style="color:grey"</c:if>
                                                href="javascript:saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}',1,'出勤','${jsResAttendanceExt.jsresAttendance.doctorFlow}','${jsResAttendanceExt.jsresAttendance.attendDate}');">出勤</a>/
                                        <a
                                                <c:if test="${'1' eq jsResAttendanceExt.jsresAttendance.attendStatus||
                                            '-1' eq jsResAttendanceExt.jsresAttendance.attendStatus}">style="color:grey"</c:if>
                                                href="javascript:saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}',0,'缺勤','${jsResAttendanceExt.jsresAttendance.doctorFlow}','${jsResAttendanceExt.jsresAttendance.attendDate}');">缺勤</a>/
                                        <a
                                                <c:if test="${'0' eq jsResAttendanceExt.jsresAttendance.attendStatus||
                                            '1' eq jsResAttendanceExt.jsresAttendance.attendStatus||
                                            null==jsResAttendanceExt.jsresAttendance.attendStatus}">style="color:grey"</c:if>
                                                href="javascript:saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}',-1,'轮休','${jsResAttendanceExt.jsresAttendance.doctorFlow}','${jsResAttendanceExt.jsresAttendance.attendDate}');">轮休</a>
                                    </c:if>
                                    <c:if test="${pdfn:getCurrDate()<jsResAttendanceExt.jsresAttendance.attendDate}">
                                        ${jsResAttendanceExt.jsresAttendance.attendStatusName}
                                    </c:if>
                                </c:if>
                                <c:if test="${!(jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing' or empty jsResAttendanceExt.jsresAttendance.attendStatus)}">
                                    ${jsResAttendanceExt.jsresAttendance.attendStatusName}
                                    <a href="javascript:returnAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}');">退回</a>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing' or empty jsResAttendanceExt.jsresAttendance.attendStatus}">
                                    待审核
                                </c:if>
                                <c:if test="${!(jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing' or empty jsResAttendanceExt.jsresAttendance.attendStatus)}">
                                    ${jsResAttendanceExt.jsresAttendance.attendStatusName}
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${'teacher' eq roleId}">

                                <c:if test="${jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing' or empty jsResAttendanceExt.jsresAttendance.attendStatus}">
                                    <c:if test="${pdfn:getCurrDate()>=jsResAttendanceExt.jsresAttendance.attendDate}">
                                        <input class="input" style="width: 80px;"
                                               attendanceFlow="${jsResAttendanceExt.jsresAttendance.attendanceFlow}"
                                               onchange="saveAttend('${jsResAttendanceExt.jsresAttendance.attendanceFlow}','${jsResAttendanceExt.jsresAttendance.attendStatus}','${jsResAttendanceExt.jsresAttendance.attendStatusName}','${jsResAttendanceExt.jsresAttendance.doctorFlow}','${jsResAttendanceExt.jsresAttendance.attendDate}',this);"
                                               value="${jsResAttendanceExt.jsresAttendance.teacherRemarks}"/>
                                    </c:if>
                                    <c:if test="${pdfn:getCurrDate()<jsResAttendanceExt.jsresAttendance.attendDate}">
                                        ${jsResAttendanceExt.jsresAttendance.teacherRemarks}
                                    </c:if>
                                </c:if>
                                <c:if test="${!(jsResAttendanceExt.jsresAttendance.auditStatusId eq 'Auditing' or empty jsResAttendanceExt.jsresAttendance.attendStatus)}">
                                    ${jsResAttendanceExt.jsresAttendance.teacherRemarks}
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                ${jsResAttendanceExt.jsresAttendance.teacherRemarks}
                            </c:otherwise>
                        </c:choose>
                    </td>
                        <%--<td--%>
                        <%--<c:if test="${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">--%>
                        <%--class="title" title="--%>
                        <%--<table style='width:300px'>--%>
                        <%--<tr>--%>
                        <%--<td align='center' style='width:30px'>签到时间</td>--%>
                        <%--<td align='center' style='width:135px'>签到地点</td>--%>
                        <%--<td align='center' style='width:135px'>特殊备注</td>--%>
                        <%--</tr>--%>
                        <%--<c:forEach items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}" var="jsresAttendanceDetail">--%>
                        <%--<tr>--%>
                        <%--<td align='center'>${jsresAttendanceDetail.attendTime}</td>--%>
                        <%--<td align='center'>${jsresAttendanceDetail.selfRemarks}</td>--%>
                        <%--<td align='center'>${jsresAttendanceDetail.attendLocal}</td>--%>
                        <%--</tr>--%>
                        <%--</c:forEach>--%>
                        <%--</table>--%>
                        <%--"--%>
                        <%--</c:if>--%>
                        <%-->--%>
                        <%--<c:if test="${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">--%>
                        <%--备注详情--%>
                        <%--</c:if>--%>
                        <%--</td>--%>
                    <td style="padding-left: 25px;" <c:if
                            test='${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}'>
                        class="showInfo"
                    </c:if> >
                        <c:if test="${not empty attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}">
                            备注详情
                        </c:if>
                        <div style="width: 0px;height: 0px;overflow: visible;">
                            <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-290px;width: 400px;"
                                 class="theInfo">
                                <table class="grid" style="background: white;width: 350px;">
                                    <tr>
                                        <th align="center" style="width:80px">签到时间</th>
                                        <th align="center" style="width:135px">签到地点</th>
                                        <th align="center" style="width:135px">特殊备注</th>
                                    </tr>
                                    <c:forEach
                                            items="${attendanceDetailMap[jsResAttendanceExt.jsresAttendance.attendanceFlow]}"
                                            var="jsresAttendanceDetail">
                                        <tr>
                                            <td align="center">${jsresAttendanceDetail.attendTime}</td>
                                            <td align="center">${jsresAttendanceDetail.attendLocal}</td>
                                            <td align="center">${jsresAttendanceDetail.selfRemarks}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty jsResAttendanceExts}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>

    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(jsResAttendanceExts)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

</body>
</html>
 