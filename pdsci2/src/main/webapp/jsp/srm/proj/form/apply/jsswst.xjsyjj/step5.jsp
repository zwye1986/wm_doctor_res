<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            var orgProvNameText = $("#orgProvId option:selected").text();
            if(orgProvNameText=="选择省"){
                orgProvNameText = "";
            }
            var orgCityNameText = $("#orgCityId option:selected").text();
            if(orgCityNameText=="选择市"){
                orgCityNameText = "";
            }
            var orgAreaNameText = $("#orgAreaId option:selected").text();
            if(orgAreaNameText=="选择地区"){
                orgAreaNameText = "";
            }
            $("#orgProvName").val(orgProvNameText);
            $("#orgCityName").val(orgCityNameText);
            $("#orgAreaName").val(orgAreaNameText);
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }
        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }
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
                    <input type="hidden" id="pageName" name="pageName" value="step5"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">五、主要完成人情况表（三）</font>

                    <table class="basic" style="width: 100%;margin-top: 10px;">

                        <tr>
                            <th style="text-align: center;">第一完成人</th>
                            <th>姓&#12288;&#12288;名：</th>
                            <td style="text-align: left;">
                                <input type="text" class="inputText"
                                       name="applicant_three_name" value="${resultMap.applicant_three_name}" style="width: 80%"/>
                            </td>
                            <th>性&#12288;&#12288;别：</th>
                            <td style="text-align: left;">
                                <input type="text" class="inputText"
                                       name="applicant_three_sexId"
                                       value="${resultMap.applicant_three_sexId}" style="width: 80%"/>
                            </td>
                            <th>民&#12288;&#12288;族：</th>
                            <td style="text-align: left;">
                                <input type="text" class="inputText"
                                       name="applicant_three_nation"
                                       value="${resultMap.applicant_three_nation}" style="width: 80%"/>
                            </td>

                        </tr>
                        <tr>
                            <th>出 生 地：</th>
                            <td style="text-align: left;" colspan="4">
                                <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                                <div id="provCityAreaId">
                                    <select id="orgProvId" name="applicant_three_provId" class="province inputText" data-value="${resultMap.applicant_three_provId}" data-first-title="选择省"></select>
                                    <select id="orgCityId" name="applicant_three_cityId" class="city inputText" data-value="${resultMap.applicant_three_cityId}" data-first-title="选择市"></select>
                                    <select id="orgAreaId" name="applicant_three_areaId" class="area inputText" data-value="${resultMap.applicant_three_areaId}" data-first-title="选择地区"></select>
                                </div>
                                <input id="orgProvName" name="applicant_three_provName" type="hidden" value="${resultMap.applicant_three_provName}">
                                <input id="orgCityName" name="applicant_three_cityName" type="hidden" value="${resultMap.applicant_three_cityName}">
                                <input id="orgAreaName" name="applicant_three_areaName" type="hidden" value="${resultMap.applicant_three_areaName}">

                                <script type="text/javascript">
                                    // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                    $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                    $.cxSelect.defaults.nodata = "none";

                                    $("#provCityAreaId").cxSelect({
                                        selects : ["province", "city","area"],
                                        nodata : "none",
                                        firstValue : ""
                                    });
                                </script>
                                </c:if>
                                <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                                    <input class="inputText" type="text" value="${resultMap.applicant_three_provName}">
                                    <input class="inputText" type="text" value="${resultMap.applicant_three_cityName}">
                                    <input class="inputText" type="text" value="${resultMap.applicant_three_areaName}">
                                </c:if>
                            </td>
                            <th>生&#12288;&#12288;日：</th>
                            <td style="text-align: left;">
                                <input type="text" class="inputText ctime" name="applicant_three_birthDay" value="${resultMap.applicant_three_birthDay}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 90%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>政治面貌：</th>
                            <td style="text-align: left;" colspan="2">
                                <input type="text" class="inputText"
                                       name="applicant_three_political"
                                       value="${resultMap.applicant_three_political}" style="width: 80%"/>
                            </td>
                            <th>留学国家：</th>
                            <td style="text-align: left;" colspan="3">
                                <input type="text" class="inputText"
                                       name="applicant_three_study"
                                       value="${resultMap.applicant_three_study}" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>工作单位：</th>
                            <td style="text-align: left;" colspan="2">
                                <input type="text" class="inputText"
                                       name="applicant_three_org"
                                       value="${resultMap.applicant_three_org}" style="width: 80%"/>
                            </td>
                            <th>联系电话：</th>
                            <td style="text-align: left;" colspan="3">
                                <input type="text" class="inputText validate[custom[phone]]"
                                       name="applicant_three_phone"
                                       value="${resultMap.applicant_three_phone}" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>通讯地址：</th>
                            <td style="text-align: left;" colspan="6">
                                <input type="text" class="inputText"
                                       name="applicant_three_address"
                                       value="${resultMap.applicant_three_address}" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>毕业学校：</th>
                            <td style="text-align: left;" colspan="2">
                                <input type="text" class="inputText"
                                       name="applicant_three_school"
                                       value="${resultMap.applicant_three_school}" style="width: 80%"/>
                            </td>
                            <th>学&#12288;&#12288;历：</th>
                            <td style="text-align: left;" >
                                <input type="text" class="inputText"
                                       name="applicant_three_diploma"
                                       value="${resultMap.applicant_three_diploma}" style="width: 80%"/>
                            </td>
                            <th>学&#12288;&#12288;位：</th>
                            <td style="text-align: left;" >
                                <input type="text" class="inputText"
                                       name="applicant_three_degree"
                                       value="${resultMap.applicant_three_degree}" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>职&#12288;&#12288;称：</th>
                            <td style="text-align: left;" colspan="2">
                                <input type="text" class="inputText"
                                       name="applicant_three_title"
                                       value="${resultMap.applicant_three_title}" style="width: 80%"/>
                            </td>
                            <th>专&#12288;&#12288;业：</th>
                            <td style="text-align: left;" >
                                <input type="text" class="inputText"
                                       name="applicant_three_major"
                                       value="${resultMap.applicant_three_major}" style="width: 80%"/>
                            </td>
                            <th>毕业单位：</th>
                            <td style="text-align: left;" >
                                <input type="text" class="inputText"
                                       name="applicant_three_graduationUnit"
                                       value="${resultMap.applicant_three_graduationUnit}" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>外语语种：</th>
                            <td style="text-align: left;" colspan="2">
                                <input type="text" class="inputText"
                                       name="applicant_three_languages"
                                       value="${resultMap.applicant_three_languages}" style="width: 80%"/>
                            </td>
                            <th>熟练程度：</th>
                            <td style="text-align: left;" colspan="3">
                                <select class="inputText" name="applicant_three_level" style="width: 80%">
                                    <option value="">--请选择--</option>
                                    <option <c:if test="${resultMap.applicant_three_level eq '精通'}">selected="selected"</c:if> value="精通">精通</option>
                                    <option <c:if test="${resultMap.applicant_three_level eq '熟练'}">selected="selected"</c:if> value="熟练">熟练</option>
                                    <option <c:if test="${resultMap.applicant_three_level eq '良好'}">selected="selected"</c:if> value="良好">良好</option>
                                    <option <c:if test="${resultMap.applicant_three_level eq '一般'}">selected="selected"</c:if> value="一般">一般</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="7" style="text-align: left;">
                                <span style="font-weight: bold">曾获奖励及荣誉称号情况</span>
                                <textarea class="xltxtarea" name="applicant_three_honor"
                                          style="width:98%;height: 150px;">${resultMap.applicant_three_honor}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align: left;" colspan="2">&#12288;参加本项目的起止时间：</th>
                            <td style="text-align: left;" colspan="4">
                                <input type="text" class="inputText ctime" name="applicant_three_startTime" value="${resultMap.applicant_three_startTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" onchange="checkBDDate(this)" readonly="readonly"
                                       style="width: 40%"/> 至
                                <input type="text" class="inputText ctime" name="applicant_three_endTime" value="${resultMap.applicant_three_endTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" onchange="checkBDDate(this)" readonly="readonly"
                                       style="width: 40%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>所作贡献</th>
                            <td colspan="6" style="text-align: left;">
                                    <textarea class="xltxtarea" name="applicant_three_contribution"
                                              style="width:98%;height: 150px;">${resultMap.applicant_three_contribution}</textarea>
                            </td>
                        </tr>
                    </table>
                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
