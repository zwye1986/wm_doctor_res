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
            $("#orgName").likeSearchInit({});
        });
        function toPage(page){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            $("#currentPage").val(page);
            search();
        }
        function search(){
            if($("#orgName").val() != ""){
                $("#orgFlow").val($("#orgName").attr("flow"));
            }else{
                $("#orgFlow").val("");
            }
            var orgFlow=$("#orgFlow").val();
            if('${adminFlag}'=="Y"){
                if(orgFlow==""){
                    jboxTip("请选择培养单位！");
                    return;
                }
            }
            var form = $("#recSearchForm");
            jboxStartLoading();
            form.submit();
        }
        function leadTo(){
            jboxOpen("<s:url value='/nyzl/admissionSubject/leadTo'/>?stuSignFlag=${stuSignFlag}","导入",500,200);
        }

        function exportRecruitQuota(){
            if(false==$("#recSearchForm").validationEngine("validate")){
                return;
            }
            var url = "<s:url value='/nyzl/admissionSubject/exportAdmissionSubject?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#recSearchForm"), url, null, null, false);
            jboxEndLoading();
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
        <form id="recSearchForm" method="post" action="<s:url value='/nyzl/admissionSubject/admissionSubjectList?stuSignFlag=${stuSignFlag}&adminFlag=${adminFlag}'/>">
            <table style="width: 100%;margin: 10px 0px 5px -10px;border: none;">
                <tr>
                    <td style="border: none;">
                        <table class="basic table1" style="width: 980px">
                            <input id="currentPage" type="hidden" name="currentPage"/>
                            <input type="hidden" name="from" value="${param.from }"/>
                            <input type="hidden" name="flag" value="${flag}"/>
                            <tr>
                                <td>年&#12288;&#12288;份：<input style="width:137px;" value="${(empty param.recruitYear)?thisYear:param.recruitYear}" name="recruitYear" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" />
                                    &#12288;专业名称：<select style="width: 141px;" name="speId">
                                        <option value="">全部</option>
                                        <c:forEach items="${dictTypeEnumNyzlSpeList}" var="spe">
                                            <option value="${spe.dictId}" <c:if test="${param.speId==spe.dictId}">selected="selected"</c:if>>${spe.dictName }</option>
                                        </c:forEach>
                                    </select>
                                    &#12288;方&#12288;&#12288;向：<select style="width: 141px;" name="directionId">
                                        <option value="">全部</option>
                                        <c:forEach items="${dictTypeEnumNyzlDirectionList}" var="direction">
                                            <option value="${direction.dictId}" <c:if test="${param.directionId==direction.dictId}">selected="selected"</c:if>>${direction.dictName }</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${adminFlag eq 'Y'}">
                                        &#12288;培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}" style="width: 137px;" />
                                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:734px;">
                                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                            <c:forEach items="${orgs}" var="org">
                                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow}"/>
                                    <%--<select style="width: 147px;" name="orgFlow">--%>
                                            <%--<option value="">请选择</option>--%>
                                            <%--<c:forEach items="${orgs}" var="org">--%>
                                                <%--<option value="${org.orgFlow}" <c:if test="${param.orgFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName }</option>--%>
                                            <%--</c:forEach>--%>
                                        <%--</select>--%>
                                    </c:if>
                            </tr>
                            <tr>
                                <td>
                                    <input type="button" name="" class="search" onclick="search();" value="查&#12288;询"/>
                                    <c:if test="${adminFlag ne 'Y'}">
                                        <input type="button" name="" class="search" onclick="leadTo();" value="导&#12288;入" />
                                    </c:if>
                                    <input type="button" name="" class="search" onclick="exportRecruitQuota();" value="导&#12288;出" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label style="font-size: 14px;color: red;">
                                        统考学术指标总和：${countQuota["TK_ACADEMIC_NUM"]}&#12288;
                                        实际录取人数：${countQuota["ACADEMIC_ADMISSION_NUM"]}&#12288;
                                        剩余指标人数：${countQuota["ACADEMIC_SURPLUS_NUM"]}&#12288;
                                        统考专业指标总和：${countQuota["TK_SPECIAL_NUM"]}&#12288;
                                        实际录取人数：${countQuota["SPECIAL_ADMISSION_NUM"]}&#12288;
                                        剩余指标人数：${countQuota["SPECIAL_SURPLUS_NUM"]}&#12288;<br/>
                                        推免生（学术型）总和：${countQuota["TMS_ACADEMIC_NUM"]}&#12288;
                                        推免生（专业型）总和：${countQuota["TMS_SPECIAL_NUM"]}&#12288;
                                        总指标：${countQuota["TK_ACADEMIC_NUM"]+countQuota["TK_SPECIAL_NUM"]+countQuota["TMS_ACADEMIC_NUM"]+countQuota["TMS_SPECIAL_NUM"]}&#12288;</label>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </form>
        <form>
            <table class="basicData" style="width: 100%;">
                <tr style="font-weight: bold;">
                    <td>年份</td>
                    <td>专业名称</td>
                    <td>方向</td>
                    <td>统考学术型指标</td>
                    <td>实际录取人数</td>
                    <td>剩余指标数</td>
                    <td>统考专业型指标</td>
                    <td>实际录取人数</td>
                    <td>剩余指标数</td>
                    <td>推免生数（学术型）</td>
                    <td>推免生数（专业型）</td>
                    <td>总指标数</td>
                </tr>
                <c:forEach items="${recordList}" var="record">
                    <!-- 统考学术型指标 -->
                    <c:set var="tkAcademicNum" value="${empty record.tkAcademicNum?0:record.tkAcademicNum}"/>
                    <!-- 统考专业型指标 -->
                    <c:set var="tkSpecialNum" value="${empty record.tkSpecialNum?0:record.tkSpecialNum}"/>
                    <!-- 推免生数（学术型） -->
                    <c:set var="tmsAcademicNum" value="${empty record.tmsAcademicNum?0:record.tmsAcademicNum}"/>
                    <!-- 推免生数（专业型） -->
                    <c:set var="tmsSpecialNum" value="${empty record.tmsSpecialNum?0:record.tmsSpecialNum}"/>
                    <!-- 总指标数 -->
                    <c:set var="totleNum" value="${tkAcademicNum+tkSpecialNum+tmsAcademicNum+tmsSpecialNum}"/>

                    <tr id="${record.orgFlow}TrData">
                        <td>${record.recruitYear}</td>
                        <td>${record.speName}</td>
                        <td>${record.directionName}</td>
                        <td>${record.tkAcademicNum}</td>
                        <td>${record.academicAdmissionNum}</td>
                        <td>${record.academicSurplusNum}</td>
                        <td>${record.tkSpecialNum}</td>
                        <td>${record.specialAdmissionNum}</td>
                        <td>${record.specialSurplusNum}</td>
                        <td>${record.tmsAcademicNum}</td>
                        <td>${record.tmsSpecialNum}</td>
                        <td>${totleNum}</td>
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
