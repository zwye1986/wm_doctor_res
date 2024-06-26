<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function nextOpt(step) {
                if (false == $("#projForm").validationEngine("validate")) {
                    return false;
                }
                var form = $('#projForm');
                var action = form.attr('action');
                action += "?nextPageName=" + step;
                form.attr("action", action);
                $('#nxt').attr({"disabled": "disabled"});
                $('#prev').attr({"disabled": "disabled"});
                form.submit();
            }

            $(function () {
                $('[calc]').on('keyup', function () {
                    var v = $(this).attr('calc');
                    var totle = 0;
                    $('[calc="' + v + '"]:not(.totle)').each(function () {
                        var val = this.value;
                        if (val && !isNaN(val)) {
                            totle += parseFloat(val);
                        }
                    });
                    if (totle % 1 == 0) {
                        totle = totle.toFixed(0);
                    } else {
                        totle = parseFloat(totle.toFixed(4));
                    }
                    $('[calc="' + v + '"].totle').val(totle);
                });
            });
        </script>
    </c:if>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step9"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
                    <font style="font-size: 14px; font-weight:bold;color: #333;">八、保证与审核</font>
                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <td style="text-align: left;">
                                <span>申报课题牵头单位审核意见(就是否同意申请提出明确意见，并对申请人学风做出评价)</span>
                                <br/>
                                <textarea class="xltxtarea" name="leadUnitOpinion"
                                          style="width:90%;height: 150px">${resultMap.leadUnitOpinion}</textarea><br/>
                                本单位保证在本课题获得资助后做到以下几点(在方框中划√)：<br/>
                                <input type="checkbox" name="agreeOpinion1"
                                       <c:if test="${not empty resultMap.agreeOpinion1}">checked="checked"</c:if>
                                       value="√"/>严格遵守科研基金使用及管理的有关规定；<br/>
                                <input type="checkbox" name="agreeOpinion2"
                                       <c:if test="${not empty resultMap.agreeOpinion2}">checked="checked"</c:if>
                                       value="√"/>提供本课题实施过程中所需人力、物力和工作时间等条件的支持；<br/>
                                <input type="checkbox" name="agreeOpinion3"
                                       <c:if test="${not empty resultMap.agreeOpinion3}">checked="checked"</c:if>
                                       value="√"/>督促本单位科管部门及课题组按时报送有关材料；<br/>
                                <input type="checkbox" name="agreeOpinion4"
                                       <c:if test="${not empty resultMap.agreeOpinion4}">checked="checked"</c:if>
                                       value="√"/>愿意匹配研究经费，匹配额度<input type="text" name="matchingQuota"
                                                                      value="${resultMap.matchingQuota}"
                                                                      class="inputText" style="width:50px"/>%。
                                <br/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">
                                <span>合作单位审核意见(同上)</span>
                                <br/>
                                <textarea class="xltxtarea" name="jointUnitOpinion"
                                          style="width:90%;height: 150px">${resultMap.jointUnitOpinion}</textarea>
                                <br/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">
                                <span>市卫生局、厅直属单位审核意见(请对本课题填报内容和提供的材料的真实性、经费匹配情况、课题的学术水平等签署意见)</span>
                                <br/>
                                <textarea class="xltxtarea" name="directlyUnderOpinion"
                                          style="width:90%;height: 150px">${resultMap.directlyUnderOpinion}</textarea>
                                <br/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: left;">
                                <span>省中医药局审核意见</span>
                                <br/>
                                <textarea class="xltxtarea" name="officeOpinion"
                                          style="width:90%;height: 150px">${resultMap.officeOpinion}</textarea>
                                <br/>
                            </td>
                        </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px; width: 100%">
                        <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		