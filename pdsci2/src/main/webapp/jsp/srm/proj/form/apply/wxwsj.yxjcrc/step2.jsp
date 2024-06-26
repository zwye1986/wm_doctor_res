<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        .basic td {
            text-align: center;
            padding-left: 0px;
        }
    </style>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function nextOpt(step) {
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

            function add(templateId) {
                if (templateId) {
                    $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                }
            }

            function del(templateId) {
                if (templateId) {
                    if (!$('.' + templateId + ' .toDel:checked').length) {
                        return jboxTip('请选择需要删除的项目！');
                    }
                    jboxConfirm('确认删除？', function () {
                        $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    }, null);
                }
            }

            function checkBDDate(dt){
                var dates = $(':text',$(dt).closest("td"));
                if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
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
                    <input type="hidden" id="pageName" name="pageName" value="step2"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">二、医学杰出人才简历</font>
                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th colspan="5" style="text-align: left;padding-left: 15px;">学历（高中以上）
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                    <span style="float: right;padding-right: 10px">
                                        <img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('education');"/>
                                        <img title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('education');"/>
                                    </span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                                <td style="text-align: center;" width="5%">选择</td>
                            </c:if>
                            <td style="text-align: center;" width="30%"><font color="red">*</font>起讫日期</td>
                            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                            <td style="text-align: center;" width="25%"><font color="red">*</font>单位</td>
                            </c:if>
                            <td style="text-align: center;"><font color="red">*</font>学历</td>
                            <td style="text-align: center;"><font color="red">*</font>学位</td>
                        </tr>
                        <tbody class="education">
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <c:forEach var="education" items="${resultMap.education}">
                                <tr>
                                    <td><input type="checkbox" class="toDel"></td>
                                    <td>
                                        <input type="text" class="inputText ctime validate[required]" name="education_startDate" onchange="checkBDDate(this)" value="${education.objMap.education_startDate}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;"/>
                                        ~
                                        <input type="text" class="inputText ctime validate[required,past[$]]" name="education_endDate" onchange="checkBDDate(this)" value="${education.objMap.education_endDate}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="education_org"  value="${education.objMap.education_org}" style="width: 80%"/>
                                    </td>
                                    <td>
                                        <select name="education_education" class="inputText validate[required]" style="width: 80%;">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                                <option value="${dict.dictName}" <c:if test="${education.objMap.education_education eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select name="education_userdegree" class="inputText validate[required]" style="width: 80%;">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                                <option value="${dict.dictName}" <c:if test="${education.objMap.education_userdegree eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                            <c:forEach var="education" items="${resultMap.education}">
                                <tr>
                                    <td>
                                            ${education.objMap.education_startDate}~${education.objMap.education_endDate}
                                    </td>
                                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                                    <td>${education.objMap.education_org}</td>
                                    </c:if>
                                    <td>${education.objMap.education_education}</td>
                                    <td>${education.objMap.education_userdegree}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th colspan="4" style="text-align: left;padding-left: 15px;">
                                学习进修（国内外）
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                    <span style="float: right;padding-right: 10px">
                                        <img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('study');"/>
                                        <img title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('study');"/>
                                    </span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                                <td style="text-align: center;" width="5%">选择</td>
                            </c:if>
                            <td style="text-align: center;" width="30%"><font color="red">*</font>起讫日期</td>
                            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                            <td style="text-align: center;" width="25%"><font color="red">*</font>单位</td>
                            </c:if>
                            <td style="text-align: center;"><font color="red">*</font>进修内容(限20字)</td>
                        </tr>
                        <tbody class="study">
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <c:forEach var="study" items="${resultMap.study}">
                                <tr>
                                    <td><input type="checkbox" class="toDel"></td>
                                    <td>
                                        <input type="text" class="inputText ctime validate[required]" name="study_startDate" value="${study.objMap.study_startDate}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                                        ~
                                        <input type="text" class="inputText ctime validate[required]" name="study_endDate" value="${study.objMap.study_endDate}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="study_org" value="${study.objMap.study_org}" style="width: 80%"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required,maxSize[20]]" name="study_studyInfo" value="${study.objMap.study_studyInfo}" style="width: 80%"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                            <c:forEach var="study" items="${resultMap.study}">
                                <tr>
                                    <td>
                                        ${study.objMap.study_startDate}~${study.objMap.study_endDate}
                                    </td>
                                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                                    <td>${study.objMap.study_org}</td>
                                    </c:if>
                                    <td>${study.objMap.study_studyInfo}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <table class="basic" style="width: 100%;margin-top: 10px;">
                        <tr>
                            <th colspan="5" style="text-align: left;padding-left: 15px;">工作简历
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
                                    <span style="float: right;padding-right: 10px">
                                        <img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('work');"/>
                                        <img title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('work');"/>
                                    </span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                                <td style="text-align: center;" width="5%">选择</td>
                            </c:if>
                            <td style="text-align: center;" width="30%"><font color="red">*</font>起讫日期</td>
                            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                            <td style="text-align: center;" width="20%"><font color="red">*</font>单位</td>
                            </c:if>
                            <td style="text-align: center;" width="10%"><font color="red">*</font>技术职称</td>
                            <td style="text-align: center;"><font color="red">*</font>主要工作(限20字)</td>
                        </tr>
                        <tbody class="work">
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <c:forEach var="work" items="${resultMap.work}">
                                <tr>
                                    <td><input type="checkbox" class="toDel"></td>
                                    <td>
                                        <input type="text" class="inputText ctime validate[required]" name="work_startDate" value="${work.objMap.work_startDate}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                                        ~
                                        <input type="text" class="inputText ctime validate[required]" name="work_endDate" value="${work.objMap.work_endDate}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="work_org" value="${work.objMap.work_org}" style="width: 80%"/>
                                    </td>
                                    <td>
                                        <select name="work_technologyTitle" class="inputText validate[required]" style="width: 80%;">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                                <option value="${dict.dictName }"
                                                    <c:if test="${work.objMap.work_technologyTitle eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required,maxSize[20]]" name="work_workInfo" value="${work.objMap.work_workInfo}" style="width: 80%"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                            <c:forEach var="work" items="${resultMap.work}">
                                <tr>
                                    <td>
                                        ${work.objMap.work_startDate}~${work.objMap.work_endDate}
                                    </td>
                                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
                                    <td>${work.objMap.work_org}</td>
                                    </c:if>
                                    <td>${work.objMap.work_technologyTitle}</td>
                                    <td>${work.objMap.work_workInfo}</td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                        <div align="center" style="margin-top: 10px">
                            <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
                            <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
                        </div>
                    </c:if>
                </form>

                <table id="template" style="display: none;">
                    <tr id="education">
                        <td><input type="checkbox" class="toDel"></td>
                        <td>
                            <input type="text" class="inputText ctime validate[required]" name="education_startDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                            ~
                            <input type="text" class="inputText ctime validate[required]" name="education_endDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="education_org" value="" style="width: 80%"/>
                        </td>
                        <td>
                            <select name="education_education" class="inputText validate[required]" style="width: 80%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                                    <option value="${dict.dictName}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="education_userdegree" class="inputText validate[required]" style="width: 80%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserDegreeList}">
                                    <option value="${dict.dictName}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr id="study">
                        <td><input type="checkbox" class="toDel"></td>
                        <td>
                            <input type="text" class="inputText ctime validate[required]" name="study_startDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                            ~
                            <input type="text" class="inputText ctime validate[required]" name="study_endDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="study_org" value="" style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required,maxSize[20]]" name="study_studyInfo" value="" style="width: 80%"/>
                        </td>
                    </tr>

                    <tr id="work">
                        <td><input type="checkbox" class="toDel"></td>
                        <td>
                            <input type="text" class="inputText ctime validate[required]" name="work_startDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                            ~
                            <input type="text" class="inputText ctime validate[required]" name="work_endDate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" onchange="checkBDDate(this)" style="width: 110px;"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="work_org" value="" style="width: 80%"/>
                        </td>
                        <td>
                            <select name="work_technologyTitle" class="inputText validate[required]" style="width: 80%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                    <option value="${dict.dictName }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required,maxSize[20]]" name="work_workInfo" value="" style="width: 80%"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>