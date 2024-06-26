<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }
        $(document).ready(function () {
            $(".expectFruit").bind("change",function(){
                var checkboxs = $(".expectFruit:checkbox:checked");
                var expectFruit="";
                $.each(checkboxs , function(i , o){
                    expectFruit += " "+$(o).val();
                });
//                alert(userCategory);
                $("#expectFruit").val(expectFruit);
            });

        });
    </script>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step4"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">三、项目预期成效</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                       <%-- <tr>
                            <th>预期成果作用</th>
                            <td colspan="4">
                                <input type="hidden" id="expectFruit" name="expectFruit" value="${resultMap.expectFruit}">
                                <input type="checkbox" class="expectFruit" <c:if test="${pdfn:contains(resultMap.expectFruit,'形成自主研发能力')}">checked="checked"</c:if> value="形成自主研发能力" />1.形成自主研发能力&#12288;
                                <input type="checkbox" class="expectFruit" <c:if test="${pdfn:contains(resultMap.expectFruit,'规模开发')}">checked="checked"</c:if> value="规模开发" />2.规模开发&#12288;
                                <input type="checkbox" class="expectFruit" <c:if test="${pdfn:contains(resultMap.expectFruit,'试点示范')}">checked="checked"</c:if> value="试点示范" />3.试点示范&#12288;
                                <input type="checkbox" class="expectFruit" <c:if test="${pdfn:contains(resultMap.expectFruit,'推广应用')}">checked="checked"</c:if> value="推广应用" />4.推广应用&#12288;
                                <input type="checkbox" class="expectFruit" <c:if test="${pdfn:contains(resultMap.expectFruit,'突破关键技术')}">checked="checked"</c:if> value="突破关键技术" />5.突破关键技术&#12288;
                                <input type="checkbox" class="expectFruit" <c:if test="${pdfn:contains(resultMap.expectFruit,'其他')}">checked="checked"</c:if> value="其他" />6.其他
                            </td>
                        </tr>--%>
                        <tr>
                        <th rowspan="4">申请知识产权数量（项）</th>
                        <th style="text-align: center">发明专利</th>
                        <th style="text-align: center">实用新型</th>
                        <th style="text-align: center">外观设计</th>
                        <th style="text-align: center">软件著作权</th>
                    </tr>
                        <tr>
                            <td><input type="text" name="patentNum" value="${resultMap.patentNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
                            <td>
                                <input type="text" name="utilityModel" value="${resultMap.utilityModel}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="exteriorDesign" value="${resultMap.exteriorDesign}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="softwareCopyright" value="${resultMap.softwareCopyright}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: center">医疗器械注册证</th>
                            <th style="text-align: center">新药证书</th>
                            <th colspan="2" style="text-align: center">药物临床批件</th>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="apparatusRegister" value="${resultMap.apparatusRegister}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="newDrug" value="${resultMap.newDrug}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td colspan="2">
                                <input type="text" name="medicineDocument" value="${resultMap.medicineDocument}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="4">论著标准情况</th>
                            <th style="text-align: center">论文总数（篇）</th>
                            <th style="text-align: center">核心期刊（篇）</th>
                            <th style="text-align: center">科学引文索引（SCI）（篇）</th>
                            <th style="text-align: center">工程索引（EI）（篇）</th>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="thesisTotal" value="${resultMap.thesisTotal}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="coreThesisNum" value="${resultMap.coreThesisNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="SCIThesisNum" value="${resultMap.SCIThesisNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="EIThesisNum" value="${resultMap.EIThesisNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: center">科技专著（部）</th>
                            <th style="text-align: center">研究（咨询）报告（份）</th>
                            <th colspan="2" style="text-align: center">制定技术标准（个）</th>
                        </tr>
                        <tr>
                            <td>
                                <input type="text" name="bookNum" value="${resultMap.bookNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td>
                                <input type="text" name="reportNum" value="${resultMap.reportNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                            <td colspan="2">
                                <input type="text" name="standardNum" value="${resultMap.standardNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>人才培养情况（人）</th>
                            <th style="text-align: center">培养研究生人数</th>
                            <td colspan="3">
                                <input type="text" name="cultureStudentNum" value="${resultMap.cultureStudentNum}"
                                       class="inputText validate[custom[number]]" style="width: 80%"/>
                            </td>

                        </tr>
                           <tr>
                               <th>其他</th>
                               <td colspan="4">
                                   <input type="text" name="otherExpect" value="${resultMap.otherExpect}"
                                          class="inputText" style="width: 80%"/>
                               </td>

                           </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="保&#12288;存"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
