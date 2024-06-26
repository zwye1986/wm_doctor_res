<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
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
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script>

        function detail(flow){
            jboxOpen("<s:url value='/gzykdx/recruit/getDetail'/>?userFlow="+flow, "复试考生信息",800,600);
        }
        var width=(window.screen.width)*0.6;
        var height=(window.screen.height)*0.6;
        function  selectTeacher(degreeTypeId,orgFlow,stuSpeId){
            if(${user.isOwnerStu ne 'Y' and pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.outSchool_adjust_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.outSchool_adjust_end_date}){
                if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.org_adjust_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.org_adjust_end_date}){
                    jboxOpen("<s:url value='/gzykdx/teaAndDoc/showNotFullTeachers'/>?degreeTypeId="+degreeTypeId+"&orgFlow="+orgFlow+"&stuSpeId="+stuSpeId, "院内调剂",width,height);
                }else if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.inSchool_adjust_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.inSchool_adjust_end_date}){
                    jboxOpen("<s:url value='/gzykdx/teaAndDoc/showNotFullOrgs'/>?degreeTypeId="+degreeTypeId+"&stuSpeId="+stuSpeId, "校内调剂",width,height);
                }else{
                    jboxTip("当前时间不可调剂！");
                }
            }else if(${(user.isOwnerStu eq 'Y' and pdfn:getCurrDateTime('yyyy-MM-dd') lt sysCfgMap.outSchool_adjust_start_date)
                        or (user.isOwnerStu eq 'Y' and pdfn:getCurrDateTime('yyyy-MM-dd') gt sysCfgMap.outSchool_adjust_end_date)}){
                if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.org_adjust_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.org_adjust_end_date}){
                    jboxOpen("<s:url value='/gzykdx/teaAndDoc/showNotFullTeachers'/>?degreeTypeId="+degreeTypeId+"&orgFlow="+orgFlow+"&stuSpeId="+stuSpeId, "院内调剂",width,height);
                }else if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.inSchool_adjust_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.inSchool_adjust_end_date}){
                    jboxOpen("<s:url value='/gzykdx/teaAndDoc/showNotFullOrgs'/>?degreeTypeId="+degreeTypeId+"&stuSpeId="+stuSpeId, "校内调剂",width,height);
                }else{
                    jboxTip("当前时间不可调剂！");
                }
            }else{
                jboxTip("当前时间不可调剂！");
            }

        }
        function showAuditList(doctorFlow){
            jboxOpen("<s:url value='/gzykdx/teaAndDoc/auditStatusList'/>?doctorFlow="+doctorFlow, "审核意见",width,height);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div id="gradeDiv" class="labelDiv"><br/>
            <table class="xllist" style="width:100%;">
                <tr>
                    <th>复试号</th>
                    <th>报考专业</th>
                    <th style="max-width: 150px;">研究方向</th>
                    <th>报考导师</th>
                    <th>报考机构</th>
                    <th>初试成绩</th>
                    <th>复试成绩</th>
                    <th>总成绩</th>
                    <th>录取专业</th>
                    <th style="max-width: 150px;">录取方向</th>
                    <th>录取导师</th>
                    <th>录取机构</th>
                    <th>录取状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="info" varStatus="i">
                    <tr>
                        <td>${infoForm.fsh}</td>
                        <td>${info.speName}[${info.speId}]</td>
                        <td style="max-width: 150px;">${info.researchAreaName}</td>
                        <td>${info.userName}</td>
                        <td>${info.orgName}</td>
                        <td>${infoForm.cscj}</td>
                        <td>${infoForm.fscj}</td>
                        <td>${infoForm.zcj}</td>
                        <td>${info.finalSpeName}[${info.finalSpeId}]</td>
                        <td style="max-width: 150px;">${info.finalResearchAreaName}</td>
                        <td>${info.finalUserName}</td>
                        <td>${info.finalOrgName}</td>
                        <td>
                            <a onclick="showAuditList('${info.doctorFlow}')" style="cursor:pointer;color:#4195c5;">
                                ${info.schoolAuditStatusName}
                            </a>
                        </td>
                        <td>
                            <c:if test="${info.schoolAuditStatusId eq gzykdxAdmissionStatusEnumUnPassed.id or info.auditStatusId eq gzykdxAdmissionStatusEnumUnPassed.id}">
                                <a onclick="selectTeacher('${info.degreeTypeId}','${info.orgFlow}','${info.speId}')" style="cursor:pointer;color:#4195c5;">调剂</a>
                            </c:if>
                            <a onclick="detail('${info.doctorFlow}')" style="cursor:pointer;color:#4195c5;">详情</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty list}">
                    <tr><td colspan="99">暂无记录</td></tr>
                </c:if>
            </table>
            <div id="detail"></div>
        </div>
    </div>
</div>
</body>
</html>
