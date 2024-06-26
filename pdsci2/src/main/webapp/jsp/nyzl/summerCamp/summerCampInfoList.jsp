<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .cur{ background-color: pink;}
        .basic td,tr{border: 0}
    </style>
    <script type="text/javascript">
        $(function(){
            $("#fwhName").likeSearchInit({});
        });
        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            if($("#fwhName").val() != ""){
                $("#fwhFlow").val($("#fwhName").attr("flow"));
            }else{
                $("#fwhFlow").val("");
            }
            var fwhFlow=$("#fwhFlow").val();
            if('${adminFlag}'=="Y"){
                if(fwhFlow==""){
                    jboxTip("请选择分委会！");
                    return;
                }
            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function leadTo(){
            jboxOpen("<s:url value='/nyzl/retestStudent/leadToSummerCamp'/>?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}","导入",500,200);
        }

        function addRecord(recordFlow){
            var url = "<s:url value='/nyzl/retestStudent/showEditStudent'/>?recordFlow="+recordFlow+"&stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}";
            jboxOpen(url,'编辑复试学员信息',800,600);
        }

        function exportStudentInfo(){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/nyzl/retestStudent/exportStudentInfo?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
        }

        function editRecord(recordFlow,registerStatus){
            var flag=registerStatus=='${nyzlRegisterStatusEnumNotReport.id}'?"未报到":"报到";
            var url = "<s:url value='/nyzl/retestStudent/updateRetestStudent'/>?recordFlow="+recordFlow+"&registerStatus="+registerStatus;
            jboxConfirm("确认修改成"+flag+"状态？", function(){
                jboxPost(url, null, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                });
            });

        }
    </script>
    <style type="text/css">
        .table tr td, .table tr th{border-bottom: 0px; }
        .table1 td{border: none;}
        .table1{border: none;}
        .basicData{border:1px solid #bbbbbb;}
        .basicData th,.basicData td { border-right:1px solid #bbbbbb; border-bottom:1px solid #bbbbbb; height:35px;}
        .basicData tbody th { background:#f9f9f9; color:#333; height:35px; text-align:center;}
        .basicData td { text-align:center; line-height:140%;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="recSearchForm" method="post" action="<s:url value='/nyzl/retestStudent/retestStudentList?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>">
            <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
                <tr>
                    <td style="border: none;">

                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>
                                    姓&#12288;&#12288;名：<input type="text" name="stuName" value="${param.stuName}" style="width: 137px;"/>
                                    &#12288;专业名称：<input type="text" name="speName" value="${param.speName}" style="width: 137px;"/>
                                    <%--<select style="width: 141px;" name="speId" id="speId">--%>
                                        <%--<option value="">全部</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumNyzlSpeList}" var="nyzlSpe">--%>
                                            <%--<option value="${nyzlSpe.dictId}" <c:if test="${param.speId eq nyzlSpe.dictId}">selected="selected"</c:if>>${nyzlSpe.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    &#12288;学位类型：<select style="width: 141px;" id="degreeTypeId" name="degreeTypeId">
                                        <option value="">全部</option>
                                        <c:forEach items="${dictTypeEnumNyzlDegreeTypeList}" var="degreeType">
                                            <option value="${degreeType.dictId}" <c:if test="${param.degreeTypeId==degreeType.dictId}">selected="selected"</c:if>>${degreeType.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;方&#12288;&#12288;向：<input type="text" name="directionName" value="${param.directionName}" style="width: 137px;"/>
                                <%--状&#12288;&#12288;态：--%>
                                    <%--<input type="radio" name="registerStatusId" id="registerStatusAll"--%>
                                           <%--<c:if test="${empty param.auditStatusId}">checked="checked"</c:if>--%>
                                           <%--value=""/><label for="registerStatusAll">全部</label>--%>
                                    <%--<input type="radio" name="registerStatusId" id="registerStatusAll_Report"--%>
                                           <%--<c:if test="${param.registerStatusId eq nyzlRegisterStatusEnumReport.id }">checked="checked"</c:if>--%>
                                           <%--value="${nyzlRegisterStatusEnumReport.id }"/><label--%>
                                            <%--for="registerStatusAll_Report">${nyzlRegisterStatusEnumReport.name }</label>--%>
                                    <%--<input type="radio" name="registerStatusId" id="registerStatusAll_NotReport"--%>
                                           <%--<c:if test="${param.registerStatusId eq nyzlRegisterStatusEnumNotReport.id }">checked="checked"</c:if>--%>
                                           <%--value="${nyzlRegisterStatusEnumNotReport.id }"/><label--%>
                                            <%--for="registerStatusAll_NotReport">${nyzlRegisterStatusEnumNotReport.name}</label>--%>
                                    <br/>
                                    <%--<select style="width: 141px;" name="directionId" id="directionId">--%>
                                        <%--<option value="">全部</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumNyzlDirectionList}" var="direction">--%>
                                            <%--<option value="${direction.dictId}" <c:if test="${param.directionId eq direction.dictId}">selected="selected"</c:if>>${direction.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    年&#12288;&#12288;份：<input style="width:137px;" value="${(empty param.recruitYear)?thisYear:param.recruitYear}" name="recruitYear" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
                                    <c:if test="${adminFlag eq 'Y'}">
                                    &#12288;分&ensp;委&ensp;会：<input id="fwhName" type="text" name="fwhName" value="${param.fwhName}" autocomplete="off" title="${param.fwhName}" onmouseover="this.title = this.value" flow="${param.fwhFlow}" style="width: 137px;" />
                                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:287px;">
                                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                            <c:forEach items="${deptList}" var="dept">
                                                <p class="item" flow="${dept.deptFlow}" value="${dept.deptName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${dept.deptName}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <input type="hidden" id="fwhFlow" name="fwhFlow" value="${param.fwhFlow}"/>
                                    <%--<select style="width: 141px;" name="orgFlow">--%>
                                        <%--<option value="">请选择</option>--%>
                                        <%--<c:forEach items="${orgs}" var="org">--%>
                                            <%--<option value="${org.orgFlow}" <c:if test="${param.orgFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName }</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                    </c:if>
                                    <input type="button" name="" class="search" onclick="search();" value="查&#12288;询"/>
                                    <c:if test="${adminFlag ne 'Y'}">
                                        <input type="button" name="" class="search" onclick="addRecord();" value="新&#12288;增"/>
                                    </c:if>
                                    <c:if test="${adminFlag eq 'Y'}">
                                        <input type="button" name="" class="search" onclick="leadTo();" value="人员导入" />
                                    </c:if>
                                    <input type="button" name="" class="search" onclick="exportStudentInfo();" value="人员导出" />
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        <table class="basicData" style="width: 100%;">
            <tr style="font-weight: bold;">
                <td>考试编号</td>
                <td>姓名</td>
                <td>性别</td>
                <td style="width: 100px;">毕业单位</td>
                <td>联系方式</td>
                <td style="width: 120px;">分委会</td>
                <td style="width: 100px;">报考专业名称</td>
                <td style="width: 120px;">方向</td>
                <td>学位类型</td>
                <td>报考类型</td>
                <td>年级排名百分比</td>
                <td>六级成绩</td>
                <td>院校类型</td>
                <%--<td>状态</td>--%>
                <c:if test="${adminFlag eq 'Y'}">
                    <td>操作</td>
                </c:if>
            </tr>
            <c:forEach items="${recordList}" var="record">
                <tr id="${record.recordFlow}">
                    <td>${record.stuNo}</td>
                    <td>${record.stuName}</td>
                    <td>${record.sexName}</td>
                    <td>${record.graduationUnit}</td>
                    <td>${record.contactPhone}</td>
                    <td>${record.fwhName}</td>
                    <td>${record.speName}</td>
                    <td>${record.directionName}</td>
                    <td>${record.degreeTypeName}</td>
                    <td>${record.applicationCategoryName}</td>
                    <td>${record.gradeRankingPercentage}</td>
                    <td>${record.sixGrade}</td>
                    <td>${record.institutionTypeName}</td>
                    <%--<td>${record.registerStatusName}</td>--%>
                    <c:if test="${adminFlag eq 'Y'}">
                        <td>
                            <a href="javascript:void(0);" onclick="addRecord('${record.recordFlow}')" style="color: blue;">编辑</a>
                                <%--<c:if test="${record.registerStatusId eq nyzlRegisterStatusEnumReport.id}">--%>
                                <%--<a href="javascript:void(0);" onclick="editRecord('${record.recordFlow}','${nyzlRegisterStatusEnumNotReport.id}')" style="color: blue;">${nyzlRegisterStatusEnumNotReport.name}</a>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${record.registerStatusId eq nyzlRegisterStatusEnumNotReport.id}">--%>
                                <%--<a href="javascript:void(0);" onclick="editRecord('${record.recordFlow}','${nyzlRegisterStatusEnumReport.id}')" style="color: blue;">${nyzlRegisterStatusEnumReport.name}</a>--%>
                                <%--</c:if>--%>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty recordList}">
                <tr>
                    <td colspan="99" >无记录！</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(recordList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="slideDiv"></div>
</body>
</html>