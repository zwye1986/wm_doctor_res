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

        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function leadTo(){
            jboxOpen("<s:url value='/nyzl/recruitQuota/leadTo'/>?stuSignFlag=${stuSignFlag}","导入",500,200);
        }

        function exportRecruitQuota(){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/nyzl/recruitQuota/exportRecruitQuota?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
        }

        function editRecord(deptFlow,recordFlow){
            if(!$("#editForm").validationEngine("validate")){
                return;
            }
            var adminFlag='${adminFlag}';
            var recruitYear=$("#"+deptFlow+"TrData").find("td[id='recruitYear']").html();
            var tkAcademicNum=$("#"+deptFlow+"TrData").find("input[id='tkAcademicNum']").val();
            var tkSpecialNum=$("#"+deptFlow+"TrData").find("input[id='tkSpecialNum']").val();
            var tmsAcademicNum=$("#"+deptFlow+"TrData").find("input[id='tmsAcademicNum']").val();
            var tmsSpecialNum=$("#"+deptFlow+"TrData").find("input[id='tmsSpecialNum']").val();
            var data = {
                "recordFlow":recordFlow,
				"recruitYear":recruitYear,
				"stuSign":'${stuSignFlag}',
				"fwhFlow":deptFlow,
				"tkAcademicNum":tkAcademicNum,
				"tkSpecialNum":tkSpecialNum,
				"tmsAcademicNum":tmsAcademicNum,
                "tmsSpecialNum":tmsSpecialNum
            };
            console.log("-------");
            console.log(data);
            console.log("+++++++++");
            var t = {'data':data,'adminFlag':'${adminFlag}'};
            $('#jsondata').val(JSON.stringify(t));
            var url = "<s:url value='/nyzl/recruitQuota/updateRecruitQuotas'/>";
            jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    jboxTip(resp);
                    search();
                }else{
                    jboxTip(resp);
                }
            }, null, true);
        }

        function releaseInfo(recruitYear){
            var url = "<s:url value='/nyzl/recruitQuota/publishQuota?recruitYear='/>"+recruitYear+"&stuSignFlag=${stuSignFlag}";
            jboxConfirm("确认发布"+recruitYear+"招生指标？", function(){
                jboxPost(url, null, function(resp){
                    if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                        jboxTip("发布成功！");
                        search();
                    }else{
                        jboxTip(resp);
                    }
                }, null, true);
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
        .basicData td { text-align:center; line-height:35px;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="recSearchForm" method="post" action="<s:url value='/nyzl/recruitQuota/recruitQuotaList?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>">
            <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
                <tr>
                    <td style="border: none;">
                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>年&#12288;&#12288;份：<input style="width:137px;" value="${(empty param.recruitYear)?thisYear:param.recruitYear}" name="recruitYear" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
                                    <c:if test="${adminFlag eq 'Y'}">
                                        &#12288;分&ensp;委&ensp;会：<select style="width: 147px;" name="fwhFlow">
                                            <option value="">全部</option>
                                            <c:forEach items="${deptList}" var="dept">
                                                <option value="${dept.deptFlow}" <c:if test="${param.fwhFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName }</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <input type="button" name="" class="search" onclick="search();" value="查&#12288;询"/>
                                    <c:if test="${adminFlag eq 'Y'}">
                                        <input type="button" name="" class="search" onclick="releaseInfo('${(empty param.recruitYear)?thisYear:param.recruitYear}');" value="发&#12288;布"/>
                                        <input type="button" name="" class="search" onclick="leadTo();" value="导&#12288;入" />
                                        <input type="button" name="" class="search" onclick="exportRecruitQuota();" value="导&#12288;出" />
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label style="font-size: 14px;color: red;">统考学术指标总和：${countQuota["TK_ACADEMIC_NUM"]}&#12288;统考专业指标总和：${countQuota["TK_SPECIAL_NUM"]}&#12288;推免生（学术型）总和：${countQuota["TMS_ACADEMIC_NUM"]}&#12288;
                                        推免生（专业型）总和：${countQuota["TMS_SPECIAL_NUM"]}&#12288;总指标：${countQuota["TK_ACADEMIC_NUM"]+countQuota["TK_SPECIAL_NUM"]+countQuota["TMS_ACADEMIC_NUM"]+countQuota["TMS_SPECIAL_NUM"]}&#12288;</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        <form id="editForm">
        <table class="basicData" style="width: 100%;">
            <tr style="font-weight: bold;">
                <td>年份</td>
                <td>分委会</td>
                <c:if test="${adminFlag ne 'Y'}">
                    <td>指标发布者</td>
                </c:if>
                <td>统考学术型指标</td>
                <td>统考专业型指标</td>
                <td>推免生数（学术型）</td>
                <td>推免生数（专业型）</td>
                <td>总指标数</td>
                <c:if test="${adminFlag eq 'Y'}">
                    <td>操作</td>
                </c:if>
            </tr>
            <c:forEach items="${recordList}" var="record">
                <!-- 统考学术型指标 -->
                <c:set var="tkAcademicNum" value="${(empty record.TK_ACADEMIC_NUM)?0:record.TK_ACADEMIC_NUM}"/>
                <!-- 统考专业型指标 -->
                <c:set var="tkSpecialNum" value="${(empty record.TK_SPECIAL_NUM)?0:record.TK_SPECIAL_NUM}"/>
                <!-- 推免生数（学术型） -->
                <c:set var="tmsAcademicNum" value="${(empty record.TMS_ACADEMIC_NUM)?0:record.TMS_ACADEMIC_NUM}"/>
                <!-- 推免生数（专业型） -->
                <c:set var="tmsSpecialNum" value="${(empty record.TMS_SPECIAL_NUM)?0:record.TMS_SPECIAL_NUM}"/>
                <!-- 总指标数 -->
                <c:set var="totleNum" value="${tkAcademicNum+tkSpecialNum+tmsAcademicNum+tmsSpecialNum}"/>

                <tr id="${record.DEPT_FLOW}TrData">
                    <td id="recruitYear">${(empty param.recruitYear)?thisYear:param.recruitYear}</td>
                    <td>${record.DEPT_NAME}</td>
                    <c:if test="${adminFlag ne 'Y'}">
                        <td>${record.PUBLISHER_NAME}</td>
                    </c:if>
                    <td>
                        <input type="text" id="tkAcademicNum" name="tkAcademicNum" value="${(empty record.TK_ACADEMIC_NUM)?0:record.TK_ACADEMIC_NUM}" style="width: 60px;" class="validate[custom[positiveNum],custom[integer]]"/>
                    </td>
                    <td>
                        <input type="text" id="tkSpecialNum" name="tkSpecialNum" value="${(empty record.TK_SPECIAL_NUM)?0:record.TK_SPECIAL_NUM}" style="width: 60px;" class="validate[custom[positiveNum],custom[integer]]"/>
                    </td>
                    <td>
                        <input type="text" id="tmsAcademicNum" name="tmsAcademicNum" value="${(empty record.TMS_ACADEMIC_NUM)?0:record.TMS_ACADEMIC_NUM}" style="width: 60px;" class="validate[custom[positiveNum],custom[integer]]"/>
                    </td>
                    <td>
                        <input type="text" id="tmsSpecialNum" name="tmsSpecialNum" value="${(empty record.TMS_SPECIAL_NUM)?0:record.TMS_SPECIAL_NUM}" style="width: 60px;" class="validate[custom[positiveNum],custom[integer]]"/>
                    </td>
                    <td>${totleNum}</td>
                    <c:if test="${adminFlag eq 'Y'}">
                        <td>
                            <a href="javascript:void(0);" onclick="editRecord('${record.DEPT_FLOW}','${record.RECORD_FLOW}')" style="color: blue;">分配</a>
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
        </form>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(recordList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
