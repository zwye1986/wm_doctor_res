<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">
        function save() {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }

            var changeSpeId="";
            var changeSpeName="";
            <c:if test="${needChange eq 'Y'}">
            changeSpeId=$("#changeSpeId").val();
            changeSpeName=$("#changeSpeId").find('option:selected').text();
            if(!changeSpeId){
                jboxTip("请选择报考专业！");
                return;
            }
            </c:if>

            if ($(":checkbox[name=signupTypeIds]:checked").size() == 0) {
                jboxTip("请选择补考类型！");
                return ;
            };
            $("#changeSpeName").val(changeSpeName);
            var url = "<s:url value='/jsres/examSignUp/saveSignUp'/>";
            jboxConfirm("确认提交报名？提交后无法修改",function(){
                jboxStartLoading();
                jboxSubmit($('#addForm'),url,function(resp) {
                    jboxEndLoading();
                    if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                        setTimeout(function(){
                         window.parent.examination("${param.typeId}");
                        },1500);
                        setTimeout(function(){
                            jboxClose();
                        },1500);
                    }else {
                        jboxTip(resp);
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="basic" style="max-height: 392px;overflow: auto;">
        <form id="addForm" style="position: relative;">


            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;width: 88px;">姓&#12288;&#12288;名：</th>
                    <td>
                        ${user.userName}
                    </td>
                    <th style="text-align: right;width: 88px;">申请年份：</th>
                    <td>
                        <input hidden name="signupYear" value="${pdfn:getCurrYear()}"/>
                        ${pdfn:getCurrYear()}
                    </td>

                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">证件类型：</th>
                    <td>
                        ${user.cretTypeName}
                    </td>
                    <th style="text-align: right;width: 88px;">证件号码：</th>
                    <td>
                        ${user.idNo}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训年级：</th>
                    <td>
                        <input hidden name="sessionNumber" value="${doctor.sessionNumber}"/>
                        ${doctor.sessionNumber}
                    </td>
                    <th style="text-align: right;width: 88px;">培养年限：</th>
                    <td>
                        <input hidden name="trainingYears" value="${doctor.trainingYears}"/>
                        <c:if test="${'OneYear' eq doctor.trainingYears}">一年</c:if>
                        <c:if test="${'TwoYear' eq doctor.trainingYears}">两年</c:if>
                        <c:if test="${'ThreeYear' eq doctor.trainingYears}">三年</c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训基地：</th>
                    <td>
                        <input hidden name="orgFlow" value="${doctor.orgFlow}"/>
                        <input hidden name="orgName" value="${doctor.orgName}"/>
                        ${doctor.orgName}
                    </td>
                    <th style="text-align: right;width: 88px;">培训类别：</th>
                    <td>
                        <input hidden name="trainingTypeId" value="${doctor.trainingTypeId}"/>
                        <input hidden name="trainingTypeName" value="${doctor.trainingTypeName}"/>
                        ${doctor.trainingTypeName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">培训专业：</th>
                    <td>
                        <input hidden name="trainingSpeId" value="${doctor.trainingSpeId}"/>
                        <input hidden name="trainingSpeName" value="${doctor.trainingSpeName}"/>
                        ${doctor.trainingSpeName}
                    </td>
                    <th style="text-align: right;width: 88px;">报考专业：</th>
                    <td>

                        <c:if test="${needChange ne 'Y'}">
                            <input hidden name="changeSpeId" value="${doctor.trainingSpeId}"/>
                            <input hidden name="changeSpeName" value="${doctor.trainingSpeName}"/>
                            ${doctor.trainingSpeName}
                        </c:if>
                        <c:if test="${needChange eq 'Y'}">
                            <input hidden id="changeSpeName" name="changeSpeName" value=""/>
                                <select id="changeSpeId" name="changeSpeId">
                                    <option value="">请选择</option>
                                    <c:forEach items="${spes}" var="spe">
                                        <option value="${spe.speId}">${spe.speName}</option>
                                    </c:forEach>
                                </select>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;width: 88px;">补考类型：</th>
                    <td colspan="3">
                        <input type="checkbox" name="signupTypeIds" value="Theory">理论补考
                        <input type="checkbox" name="signupTypeIds" value="Skill">技能补考
                    </td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="btn_green" value="提交认证"/>&#12288;
            <input type="button" onclick="jboxClose();" class="btn_green" value="取&#12288;消"/>
        </p>
    </div>
</div>
</body>
</html>
