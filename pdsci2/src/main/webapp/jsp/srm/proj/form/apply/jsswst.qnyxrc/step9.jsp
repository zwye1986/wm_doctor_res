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
                    }else{
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

                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="6" style="text-align: left;padding-left: 20px;">十、经费预算</th>
                        </tr>
                        <tr>
                            <td style="text-align: center;">申请资助经费</td>
                            <td colspan="5">
                                <input type="text" name="applySupportFunds" value="${resultMap.applySupportFunds}"
                                       class="inputText validate[custom[number]]" calc="fa" style="width:100px;"/>
                                (万元)
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">单位培养经费</td>
                            <td colspan="5">
                                <input type="text" name="orgCultrueFunds" value="${resultMap.orgCultrueFunds}"
                                       class="inputText validate[custom[number]]" calc="fa" style="width:100px"/>
                                (万元)
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">主管部门培养经费</td>
                            <td colspan="5">
                                <input type="text" name="deptCultrueFunds" value="${resultMap.deptCultrueFunds}"
                                       class="inputText validate[custom[number]]" calc="fa" style="width:100px"/>
                                (万元)
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center;">其他经费来源</td>
                            <td colspan="5">
                                <input type="text" name="otherFundsSource" value="${resultMap.otherFundsSource}"
                                       class="inputText validate[custom[number]]" calc="fa" style="width:100px"/>
                                (万元)
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">合计</td>
                            <td colspan="5">
                                <input type="text" name="supportTotle" value="${resultMap.supportTotle}"
                                       class="inputText validate[custom[number]] totle" calc="fa" style="width:100px"
                                       readonly="readonly"/>
                                (万元)
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">经费预算支出科目</td>
                            <td style="text-align: center;">第一年</td>
                            <td style="text-align: center;">第二年</td>
                            <td style="text-align: center;">第三年</td>
                            <td style="text-align: center;">第四年</td>
                            <td style="text-align: center;">第五年</td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">一、国内外进修费用</td>
                            <td style="text-align: center;">
                                <input type="text" name="studyFirstYear" value="${resultMap.studyFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="studySecondYear" value="${resultMap.studySecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="studyThirdYear" value="${resultMap.studyThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="studyFourthYear" value="${resultMap.studyFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="studyFifthYear" value="${resultMap.studyFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">二、学术交流费用</td>
                            <td style="text-align: center;">
                                <input type="text" name="learningDiscussFirstYear"
                                       value="${resultMap.learningDiscussFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="learningDiscussSecondYear"
                                       value="${resultMap.learningDiscussSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="learningDiscussThirdYear"
                                       value="${resultMap.learningDiscussThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="learningDiscussFourthYear"
                                       value="${resultMap.learningDiscussFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="learningDiscussFifthYear"
                                       value="${resultMap.learningDiscussFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">三、导师指导研究工作费用</td>
                            <td style="text-align: center;">
                                <input type="text" name="tutorFirstYear" value="${resultMap.tutorFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="tutorSecondYear" value="${resultMap.tutorSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="tutorThirdYear" value="${resultMap.tutorThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="tutorFourthYear" value="${resultMap.tutorFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="tutorFifthYear" value="${resultMap.tutorFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;"  >四、研究项目费用</td>
                            <td style="text-align:center;" colspan="5"></td>
                            <%--<td style="text-align: center;">
                            <input type="text" name="projectFirstYear" value="${resultMap.projectFirstYear}" class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                            <input type="text" name="projectSecondYear" value="${resultMap.projectSecondYear}" class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                            <input type="text" name="projectThirdYear" value="${resultMap.projectThirdYear}" class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                            <input type="text" name="projectFourthYear" value="${resultMap.projectFourthYear}" class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                            <input type="text" name="projectFifthYear" value="${resultMap.projectFifthYear}" class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>--%>
                        </tr>
                        <tr>
                            <td style="text-align: center;">1、仪器设备费</td>
                            <td style="text-align: center;">
                                <input type="text" name="machineFirstYear" value="${resultMap.machineFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="machineSecondYear" value="${resultMap.machineSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="machineThirdYear" value="${resultMap.machineThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="machineFourthYear" value="${resultMap.machineFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="machineFifthYear" value="${resultMap.machineFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">2、实验材料费</td>
                            <td style="text-align: center;">
                                <input type="text" name="testFirstYear" value="${resultMap.testFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="testSecondYear" value="${resultMap.testSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="testThirdYear" value="${resultMap.testThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="testFourthYear" value="${resultMap.testFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="testFifthYear" value="${resultMap.testFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">3、实验动物费</td>
                            <td style="text-align: center;">
                                <input type="text" name="animalFirstYear" value="${resultMap.animalFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="animalSecondYear" value="${resultMap.animalSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="animalThirdYear" value="${resultMap.animalThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="animalFourthYear" value="${resultMap.animalFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="animalFifthYear" value="${resultMap.animalFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">4、业务费</td>
                            <td style="text-align: center;">
                                <input type="text" name="businessFirstYear" value="${resultMap.businessFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="businessSecondYear" value="${resultMap.businessSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="businessThirdYear" value="${resultMap.businessThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="businessFourthYear" value="${resultMap.businessFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="businessFifthYear" value="${resultMap.businessFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">5、购买图书、资料费</td>
                            <td style="text-align: center;">
                                <input type="text" name="bookFirstYear" value="${resultMap.bookFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="bookSecondYear" value="${resultMap.bookSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="bookThirdYear" value="${resultMap.bookThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="bookFourthYear" value="${resultMap.bookFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="bookFifthYear" value="${resultMap.bookFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">6、其他研究费用</td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFirstYear" value="${resultMap.otherFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherSecondYear" value="${resultMap.otherSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherThirdYear" value="${resultMap.otherThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFourthYear" value="${resultMap.otherFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFifthYear" value="${resultMap.otherFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">五、其他费用</td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFundsFirstYear" value="${resultMap.otherFundsFirstYear}"
                                       class="inputText validate[custom[number]]" calc="fa1" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFundsSecondYear" value="${resultMap.otherFundsSecondYear}"
                                       class="inputText validate[custom[number]]" calc="fa2" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFundsThirdYear" value="${resultMap.otherFundsThirdYear}"
                                       class="inputText validate[custom[number]]" calc="fa3" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFundsFourthYear" value="${resultMap.otherFundsFourthYear}"
                                       class="inputText validate[custom[number]]" calc="fa4" style="width: 80%"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="otherFundsFifthYear" value="${resultMap.otherFundsFifthYear}"
                                       class="inputText validate[custom[number]]" calc="fa5" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;">合&#12288;计</td>
                            <td style="text-align: center;">
                                <input type="text" name="totleFirstYear" value="${resultMap.totleFirstYear}"
                                       class="inputText validate[custom[number]] totle" calc="fa1" style="width: 80%"
                                       readonly="readonly"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="totleSecondYear" value="${resultMap.totleSecondYear}"
                                       class="inputText validate[custom[number]] totle" calc="fa2" style="width: 80%"
                                       readonly="readonly"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="totleThirdYear" value="${resultMap.totleThirdYear}"
                                       class="inputText validate[custom[number]] totle" calc="fa3" style="width: 80%"
                                       readonly="readonly"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="totleFourthYear" value="${resultMap.totleFourthYear}"
                                       class="inputText validate[custom[number]] totle" calc="fa4" style="width: 80%"
                                       readonly="readonly"/>
                            </td>
                            <td style="text-align: center;">
                                <input type="text" name="totleFifthYear" value="${resultMap.totleFifthYear}"
                                       class="inputText validate[custom[number]] totle" calc="fa5" style="width: 80%"
                                       readonly="readonly"/>
                            </td>
                        </tr>
                    </table>
                    <table class="basic tbdashed" style="width: 100%; margin-top: 10px;border:1px dashed #f2d4b2;">
                        <tr><td style="border:1px dashed #f2d4b2;font-weight:bold;">填表说明：</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;1、经费预算请遵照《国务院关于改进加强中央财政科研项目和资金管理的若干意见》（国发〔2014〕11号）编制。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;2、仪器设备费：指项目专用仪器的购置费和运杂、包装、安装费、自制仪器设备的材料、配件和外协加工费。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;3、大型仪器设备应充分利用本单位、本地区现有条件。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;4、交通运输设备一般不得列入，如特殊需要应说明理由，经批准后方可购买。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;5、单台件在二千元以上的仪器设备须逐项填写名称、规格、型号、单价、数量。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;6、实验材料费：指科研用消耗性材料、试剂、药品等购置等，标本、样品采集加工和运杂包装费。</td></tr>
                    </table>
                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                        <div class="button" style="width: 100%;">
                            <input id="prev" type="button" onclick="nextOpt('step8_1')" class="search" value="上一步"/>
                            <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		