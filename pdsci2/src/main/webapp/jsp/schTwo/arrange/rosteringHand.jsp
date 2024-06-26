<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_sortable" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .selDoc {
            color: red;
        }

        #main {
            width: 100%;
            height: 98%;
            padding-top: 1%;
        }

        .side1 {
            margin: 0;
        }
    </style>
    <script type="text/javascript">
        var lastOperResultFlow = "";
        //按条件查询
        function search() {
            if ($("[name='doctorCategoryId']").val() != "${recDocCategoryEnumIntern.id}") {
                $("[name='rosteringType']").attr("disabled", true);
            }
            $("#doctorForm").submit();
        }
        //选择医师加载排班
        function selDoc(tr, doctorFlow) {
            var orgFlow = $(tr).attr("orgFlow") || "";
            var rotationFlow = $(tr).attr("rotationFlow") || "";
            var secondRotationFlow = $(tr).attr("secondRotationFlow") || "";
            if (orgFlow && rotationFlow) {
                $(".selDoc").removeClass("selDoc");
                $(tr).addClass("selDoc");
                jboxLoad("rosteringHand", "<s:url value='/schTwo/arrange/rosteringHandDept'/>?doctorFlow=" + doctorFlow
                        + "&startDate=" + ($("#startDate").val() || ""), true);
            } else {
                jboxTip("请为学员添加轮转方案！")
            }
        }
        //轮转方案说明
        function openDetail(rotationName, rotationFlow) {
            var url = "<s:url value='/schTwo/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow=" + rotationFlow;
            jboxOpen(url, rotationName, 800, 500);
        }
        function refreshResult(){
            $(".selDoc").click();
        }
    </script>
</head>
<body>
<div class="main_fix" style="overflow: auto; top: 0">
    <div id="main">
        <div style="margin:10px 10px;">
            Tip：<font color="red">*</font>表示未完成排班学员！
        </div>
        <div class="side" style="width: 20%;">
            <form id="doctorForm" method="post" action="<s:url value='/schTwo/arrange/rosteringHand'/>">
                <table class="xllist" style="margin-bottom: 20px;">
                    <tr>
                        <td style="text-align: left;padding-left: 10px;">
                            人员类型：
                            <select name="doctorCategoryId" style="width: 60%;" onchange="search();">
                                <option/>
                                <c:forEach items="${recDocCategoryEnumList}" var="category">
                                    <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                    <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
                                        <option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tbody class="byDoctor">
                    <tr>
                        <td style="text-align: left;padding-left: 10px;">
                            年&#12288;&#12288;级：
                            <select name="sessionNumber" style="width: 60%;" onchange="search();">
                                <option/>
                                <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
                                    <option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: left;padding-left: 10px;">
                            培训专业：
                            <select name="trainingSpeId" style="width: 60%;" onchange="search();">
                                <option/>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 10px;">
                            是否排班：
                            <select name="schFlag" style="width: 60%;" onchange="search();">
                                <option value="All">全部</option>
                                <option value="Y" ${param.schFlag eq "Y"?'selected':''}>已排班</option>
                                <option value="N" ${param.schFlag eq "N" or (empty param.schFlag and empty schFlag)?'selected':''}>未排班</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 10px;">
                            姓&#12288;&#12288;名：
                            <input type="text" name="doctorName" value="${doctor.doctorName}" style="width: 58%;"
                                   onchange="search();"/>
                        </td>
                    </tr>
                    </tbody>
                    <c:forEach items="${doctorList}" var="doctor">
                        <tr style="cursor: pointer;" onclick="selDoc(this,'${doctor.doctorFlow}');"
                            orgFlow="${doctor.orgFlow}" rotationFlow="${doctor.rotationFlow}"
                            rotationName="${doctor.rotationName}"
                            secondRotationFlow="${doctor.secondRotationFlow}"
                            secondRotationName="${doctor.secondRotationName}">
                            <td>
                                <c:if test="${!(doctor.schFlag eq GlobalConstant.FLAG_Y) && !empty doctor.rotationFlow}">
                                    <font id="${doctor.doctorFlow}WaitSel" color="red">*</font>
                                </c:if>
                                    ${doctor.doctorName}
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty doctorList}">
                        <tr>
                            <td>无记录</td>
                        </tr>
                    </c:if>
                </table>
            </form>
        </div>
        <div id="rosteringHand" style="width: 78%;position: absolute;right: 0px;" class="side">
            <table class="basic" style="margin-left: 10px;width: 98%;">
                <tr>
                    <td>
                        请选择医师！
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>