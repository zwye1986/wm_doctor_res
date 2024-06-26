<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>

    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function expertPlan(planFlow) {
            top.jboxExp(null, "<s:url value='/jsres/phyAss/expertPlan'/>?planFlow="+planFlow);
        }
    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table" style="height: 700px">
        <c:if test="${isExpert eq 'Y'}">
            <div style="text-align: right">
                <input type="button"  class="btn_green" onclick="expertPlan('${plan.planFlow}');" value="导&#12288;出"/>
            </div>
        </c:if>
        <h3 style="text-align: center;font-size: 16px">${plan.planContent}</h3>
        <div style="margin-top: 20px;font-size: 14px" >
            <div style="margin-left: 20px">
                ${plan.introduce}
            </div>
        </div>
        <h3 style="text-align: left;margin-top: 20px;font-size: 14px">一、培训计划</h3>
        <div style="margin-top: 20px;font-size: 14px;margin-left: 20px">
           ${plan.planContent}
        </div>
        <h3 style="text-align: left;margin-top: 20px;font-size: 14px">二、报名日期</h3>
        <div style="margin-top: 20px;font-size: 14px">
            <div style="margin-left: 20px">
                ${plan.enrollStartTime} ~ ${plan.enrollEndTime}
            </div>
        </div>
        <h3 style="text-align: left;margin-top: 20px;font-size: 14px">三、培训日期</h3>
        <div style="margin-top: 20px;font-size: 14px">
            <div style="margin-left: 20px">
                ${plan.planStartTime} ~ ${plan.planEndTime}
            </div>
        </div>
        <h3 style="text-align: left;margin-top: 20px;font-size: 14px">四、培训计划</h3>
        <div style="margin-top: 20px;font-size: 14px">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" id="plan"
                   style="margin-left: 2%;width: 98%">
                <colgroup>
                    <col width="8%"/>
                    <col width="8%"/>
                    <col width="14%"/>
                    <col width="14%"/>
                    <col width="19%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <th style="border: 1px solid #d7d7d7;text-align: center">培训专业</th>
                    <th style="border: 1px solid #d7d7d7;text-align: center">计划班级数</th>
                    <th style="border: 1px solid #d7d7d7;text-align: center">计划（每）班级人数</th>
                    <th style="border: 1px solid #d7d7d7;text-align: center">培训地点</th>
                    <th style="border: 1px solid #d7d7d7;text-align: center">培训对象</th>
                    <th style="border: 1px solid #d7d7d7;text-align: center">备注</th>
                </tr>
                <tbody id="msgInfo">
                <c:if test="${not empty msgList}">
                    <c:forEach items="${msgList}" var="m">
                        <tr>
                            <td style="text-align: center">
                                ${m.speName}
                            </td>
                            <td style="text-align: center">
                                ${m.classNum}
                            </td>
                            <td style="text-align: center">
                                ${m.peopleNum}
                            </td>
                            <td style="text-align: center">
                                ${m.adress}
                            </td>
                            <td style="text-align: left">
                               <c:if test="${pdfn:contains(m.planTarget,'1')}">主任医师&#12288;</c:if>
                               <c:if test="${pdfn:contains(m.planTarget,'2')}">副主任医师&#12288;</c:if>
                               <c:if test="${pdfn:contains(m.planTarget,'3')}">主治医师&#12288;</c:if>
                               <c:if test="${pdfn:contains(m.planTarget,'4')}">住院医师&#12288;</c:if>
                               <c:if test="${pdfn:contains(m.planTarget,'5')}">医士</c:if>
                            </td>
                            <td style="text-align: center">
                                ${empty m.remark?"无":m.remark}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <h3 style="text-align: left;margin-top: 20px;font-size: 14px">五、培训要求</h3>
        <div style="margin-top: 20px;font-size: 14px">
            <div style="margin-left: 20px">
                ${plan.askContent}
            </div>
        </div>
    </div>
</div>
</body>
</html>