<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        .basic .inputText {
            text-align: left;
        }
    </style>
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
                form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
                $('#nxt').attr({"disabled": "disabled"});
                $('#prev').attr({"disabled": "disabled"});
                jboxStartLoading();
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
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm" style="position: relative;">
                    <input type="hidden" id="pageName" name="pageName" value="step3"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">三、主要完成人情况表（一）</font>

                        <table class="basic" style="width: 100%;margin-top: 10px;">

                            <tr>
                                <th style="text-align: center;">第一完成人</th>
                                <th><font color="red">*</font>姓&#12288;&#12288;名：</th>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required]"
                                           name="applicant_one_name" value="${resultMap.applicant_one_name}" style="width: 80%"/>
                                </td>
                                <th ><font color="red">*</font>性&#12288;&#12288;别：</th>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required]"
                                           name="applicant_one_sexId"
                                           value="${resultMap.applicant_one_sexId}" style="width: 80%"/>
                                </td>
                                <th ><font color="red">*</font>民&#12288;&#12288;族：</th>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText validate[required]"
                                           name="applicant_one_nation"
                                           value="${resultMap.applicant_one_nation}" style="width: 80%"/>
                                </td>

                            </tr>
                            <tr>
                                <th ><font color="red">*</font>出 生 地：</th>
                                <td style="text-align: left;" colspan="4">
                                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                                    <div id="provCityAreaId">
                                        <select id="orgProvId" name="applicant_one_provId" class="province validate[required] inputText" data-value="${resultMap.applicant_one_provId}" data-first-title="选择省"></select>
                                        <select id="orgCityId" name="applicant_one_cityId" class="city validate[required] inputText" data-value="${resultMap.applicant_one_cityId}" data-first-title="选择市"></select>
                                        <select id="orgAreaId" name="applicant_one_areaId" class="area validate[required] inputText" data-value="${resultMap.applicant_one_areaId}" data-first-title="选择地区"></select>
                                    </div>
                                    <input id="orgProvName" name="applicant_one_provName" type="hidden" value="${resultMap.applicant_one_provName}">
                                    <input id="orgCityName" name="applicant_one_cityName" type="hidden" value="${resultMap.applicant_one_cityName}">
                                    <input id="orgAreaName" name="applicant_one_areaName" type="hidden" value="${resultMap.applicant_one_areaName}">
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
                                        <input class="inputText" type="text" value="${resultMap.applicant_one_provName}">
                                        <input class="inputText" type="text" value="${resultMap.applicant_one_cityName}">
                                        <input class="inputText" type="text" value="${resultMap.applicant_one_areaName}">
                                    </c:if>
                                </td>
                                <th ><font color="red">*</font>生&#12288;&#12288;日：</th>
                                <td style="text-align: left;">
                                    <input type="text" class="inputText ctime validate[required]" name="applicant_one_birthDay" value="${resultMap.applicant_one_birthDay}"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                           style="width: 90%"/>
                                </td>
                            </tr>
                            <tr>
                                <th >政治面貌：</th>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_one_political"
                                           value="${resultMap.applicant_one_political}" style="width: 80%"/>
                                </td>
                                <th >留学国家：</th>
                                <td style="text-align: left;" colspan="3">
                                    <input type="text" class="inputText"
                                           name="applicant_one_study"
                                           value="${resultMap.applicant_one_study}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <th >工作单位：</th>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_one_org"
                                           value="${resultMap.applicant_one_org}" style="width: 80%"/>
                                </td>
                                <th >联系电话：</th>
                                <td style="text-align: left;" colspan="3">
                                    <input type="text" class="inputText validate[custom[phone]]"
                                           name="applicant_one_phone"
                                           value="${resultMap.applicant_one_phone}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <th >通讯地址：</th>
                                <td style="text-align: left;" colspan="6">
                                    <input type="text" class="inputText"
                                           name="applicant_one_address"
                                           value="${resultMap.applicant_one_address}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <th >毕业学校：</th>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_one_school"
                                           value="${resultMap.applicant_one_school}" style="width: 80%"/>
                                </td>
                                <th >学&#12288;&#12288;历：</th>
                                <td style="text-align: left;" >
                                    <input type="text" class="inputText"
                                           name="applicant_one_diploma"
                                           value="${resultMap.applicant_one_diploma}" style="width: 80%"/>
                                </td>
                                <th >学&#12288;&#12288;位：</th>
                                <td style="text-align: left;" >
                                    <input type="text" class="inputText"
                                           name="applicant_one_degree"
                                           value="${resultMap.applicant_one_degree}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <th >职&#12288;&#12288;称：</th>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_one_title"
                                           value="${resultMap.applicant_one_title}" style="width: 80%"/>
                                </td>
                                <th >专&#12288;&#12288;业：</th>
                                <td style="text-align: left;" >
                                    <input type="text" class="inputText"
                                           name="applicant_one_major"
                                           value="${resultMap.applicant_one_major}" style="width: 80%"/>
                                </td>
                                <th >毕业单位：</th>
                                <td style="text-align: left;" >
                                    <input type="text" class="inputText"
                                           name="applicant_one_graduationUnit"
                                           value="${resultMap.applicant_one_graduationUnit}" style="width: 80%"/>
                                </td>
                            </tr>
                            <tr>
                                <th >外语语种：</th>
                                <td style="text-align: left;" colspan="2">
                                    <input type="text" class="inputText"
                                           name="applicant_one_languages"
                                           value="${resultMap.applicant_one_languages}" style="width: 80%"/>
                                </td>
                                <th >熟练程度：</th>
                                <td style="text-align: left;" colspan="3">
                                    <select class="inputText" name="applicant_one_level" style="width: 80%">
                                        <option value="">--请选择--</option>
                                        <option <c:if test="${resultMap.applicant_one_level eq '精通'}">selected="selected"</c:if> value="精通">精通</option>
                                        <option <c:if test="${resultMap.applicant_one_level eq '熟练'}">selected="selected"</c:if> value="熟练">熟练</option>
                                        <option <c:if test="${resultMap.applicant_one_level eq '良好'}">selected="selected"</c:if> value="良好">良好</option>
                                        <option <c:if test="${resultMap.applicant_one_level eq '一般'}">selected="selected"</c:if> value="一般">一般</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="7" style="text-align: left;">
                                    <span style="font-weight: bold">曾获奖励及荣誉称号情况</span>
                                <textarea class="xltxtarea" name="applicant_one_honor"
                                          style="width:98%;height: 150px;">${resultMap.applicant_one_honor}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align: left"  colspan="2">&#12288;参加本项目的起止时间：</th>
                                <td style="text-align: left;" colspan="4">
                                    <input type="text" class="inputText ctime" name="applicant_one_startTime" value="${resultMap.applicant_one_startTime}"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM'})" onchange="checkBDDate(this)" readonly="readonly"
                                           style="width: 40%"/> 至
                                    <input type="text" class="inputText ctime" name="applicant_one_endTime" value="${resultMap.applicant_one_endTime}"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM'})" onchange="checkBDDate(this)" readonly="readonly"
                                           style="width: 40%"/>
                                </td>
                            </tr>
                            <tr>
                                <th>所作贡献</th>
                                <td colspan="6" style="text-align: left;">
                                    <textarea class="xltxtarea" name="applicant_one_contribution"
                                              style="width:98%;height: 150px;">${resultMap.applicant_one_contribution}</textarea>
                                </td>
                            </tr>
                        </table>
                </form>
            </div>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                <div align="center" style="margin-top: 10px">
                    <input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="上一步"/>
                    <input id="nxt" type="button" onclick="nextOpt('step4')" class="search" value="下一步"/>
                </div>
            </c:if>
        </div>
    </div>
</div>
</div>
</body>
</html>