<html>
<head>
        <jsp:include page="/jsp/common/htmlhead.jsp">
            <jsp:param name="res_edu_jbox" value="true"/>
            <jsp:param name="basic" value="true"/>
            <jsp:param name="jquery_form" value="false"/>
            <jsp:param name="jquery_ui_tooltip" value="true"/>
            <jsp:param name="jquery_ui_combobox" value="false"/>
            <jsp:param name="jquery_ui_sortable" value="false"/>
            <jsp:param name="jquery_cxselect" value="true"/>
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
    <c:set value="${!empty param.isRead}" var="isOpen"></c:set>
    <c:set value="${isOpen && param.isRead}" var="isRead"></c:set>
    <script type="text/javascript">

        function countScore() {
            var count = 0;
            $(".scoreCount<c:if test="${!(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)}">:checked</c:if>").each(function () {
                var score = $(this).val();
                if ($.trim(score) != "" && !isNaN(score)) {
                    count += parseFloat(score);
                }
            });
            $("#totalScore").val(count.toFixed(1));
        }



        $(function () {
            <c:if test="${isRead || rec.statusId eq recStatusEnumSubmit.id}">
            $("input:text").attr("readonly", true);
            </c:if>
            <c:if test="${not empty rec}">
            $("input:text").attr("readonly", true);
            </c:if>
        });

        function closeGrade() {
            top.jboxClose();
        }
    </script>
</head>
<body>
<c:if test="${formCount eq '0'}">
    <div>
		<span style="color: red;">
			<font size="120px">
			未配置评分表，请联系管理员
		</font></span>
    </div>
</c:if>
<c:if test="${formCount ne '0'}">
    <div class="mainright">
        <div class="content">
            <div class="title1 clearfix">
                <div style="padding-top: 10px;padding-left: 5px;padding-right: 5px;padding-bottom: 5px;">
                    <form id="resRecGradeForm" style="position: relative;">
                        <input type="hidden" name="schDeptFlow" value="${empty rec?param.schDeptFlow:rec.schDeptFlow}"/>
                        <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                        <input type="hidden" name="recTypeId" value="${empty rec?param.recTypeId:rec.recTypeId}"/>
                        <input type="hidden" name="processFlow" value="${param.processFlow}"/>
                        <input type="hidden" name="cfgCodeName" id="cfgCodeName" value="${assessCfg.cfgCodeName}"/>
                        <c:forEach items="${titleFormList}" var="title">
                            <table class="xllist nofix"  style="margin-top: 10px;">
                                <div style="width: 50%;float: left;box-sizing: border-box;font-size: 15px;">&#12288;&#12288;培训专业：${resDoctor.trainingSpeName}</div>
                                <div style="width: 50%;float: left;box-sizing: border-box;padding-left: 20%;font-size: 15px;" >&#12288;&#12288;住院医师：${resDoctor.doctorName}</div>
                                <br/>
                                <div style="width: 50%;float: left;box-sizing: border-box;font-size: 15px;">&#12288;&#12288;培训日期：${recruit.recruitDate}</div>
                                <div style="width: 50%;float: left;box-sizing: border-box;padding-left: 20%;font-size: 15px;" >&#12288;&#12288;身份类型：${resDoctor.doctorTypeName}</div>
                                <br/>
                                <br>
                                    <%--								<tr>--%>
                                    <%--									<td style="text-align:left;padding-left: 4px;" colspan="3">培训专业：${resDoctor.trainingSpeName}</td>--%>
                                    <%--									<td style="text-align:left;padding-left: 4px;">住院医师：${resDoctor.doctorName}</td>--%>
                                    <%--								</tr>--%>
                                    <%--								<tr>--%>
                                    <%--									<td style="text-align:left;padding-left: 4px;"colspan="3">培训日期：${recruit.recruitDate}</td>--%>
                                    <%--									<td style="text-align:left;padding-left: 4px;">身份类型：${resDoctor.doctorTypeName}</td>--%>
                                    <%--								</tr>--%>
                                <tr>
                                    <th style="text-align: center;width: 15%" colspan="1">&#12288;核心能力分类</th>
                                    <th style="text-align: center;width: 55%" colspan="2">&#12288;${title.name}</th>
                                    <th style="text-align: center;width: 30%" colspan="2">&#12288;分值：
                                        <c:if test="${(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)}">
                                            ${item.score}
                                        </c:if>
                                        <c:if test="${(assessCfg.assessTypeId eq resAssessScoreTypeEnumNine.id)}">
                                            9
                                        </c:if>
                                        <c:if test="${(assessCfg.assessTypeId eq resAssessScoreTypeEnumFive.id)}">
                                            5
                                        </c:if>
                                    </th>
                                </tr>
                                <c:forEach items="${title.itemList}" var="item">
                                    <tr>
                                        <c:if test="${not empty item.type}">
                                            <td style="text-align: left;"  colspan="1"<c:if test="${not empty item.row}">rowspan="${item.row}" </c:if> >&#12288;&#12288;${item.type}</td>
                                        </c:if>
                                        <td style="text-align: left;"  colspan="2">&#12288;&#12288;${item.name}</td>
                                        <td style="text-align: left;width: ${(assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id)?300:600}px;">
                                            <input type="hidden" name="assessId" value="${item.id}"/>
                                            <c:if test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumPercentile.id}">
                                                <input style="width: 150px;" name="score" type="text" class="inputText  validate[required,custom[integer],max[${item.score}],min[0]] scoreCount" value="${gradeMap[item.id]['score']}" onkeyup="countScore();"/>
                                            </c:if>

                                            <c:if test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumNine.id}">
                                                <c:if test="${!(isRead || rec.statusId eq recStatusEnumSubmit.id)}">
                                                    有待加强
                                                    <label>
                                                        <input name="${item.id}score" type="radio"  class="validate[required] scoreCount" value="1" <c:if test="${gradeMap[item.id]['score'] == '1'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        1
                                                    </label>
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="2" <c:if test="${gradeMap[item.id]['score'] == '2'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        2
                                                    </label>
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="3" <c:if test="${gradeMap[item.id]['score'] == '3'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        3
                                                    </label>
                                                    |
                                                    合格
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="4" <c:if test="${gradeMap[item.id]['score'] == '4'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        4
                                                    </label>
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="5" <c:if test="${gradeMap[item.id]['score'] == '5'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        5
                                                    </label>
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="6" <c:if test="${gradeMap[item.id]['score'] == '6'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        6
                                                    </label>
                                                    |
                                                    优良
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="7" <c:if test="${gradeMap[item.id]['score'] == '7'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        7
                                                    </label>
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="8" <c:if test="${gradeMap[item.id]['score'] == '8'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        8
                                                    </label>
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="9" <c:if test="${gradeMap[item.id]['score'] == '9'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                        9
                                                    </label>
                                                </c:if>
                                                <c:if test="${isRead || rec.statusId eq recStatusEnumSubmit.id}">
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('123',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;(有待加强)
                                                    </c:if>
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('456',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;(合格)
                                                    </c:if>
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('789',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;(优良)
                                                    </c:if>
                                                </c:if>
                                                <input id="${item.id}scoreSel" name="score" type="hidden" value="${gradeMap[item.id]['score']}"/>
                                            </c:if>
                                            <c:if test="${assessCfg.assessTypeId eq resAssessScoreTypeEnumFive.id}">
                                                <c:if test="${!(isRead || rec.statusId eq recStatusEnumSubmit.id)}">
                                                    不合格
                                                    <label>
                                                        <input name="${item.id}score" type="radio"  class="validate[required] scoreCount" value="1" <c:if test="${gradeMap[item.id]['score'] == '1'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                    </label>
                                                    基本合格
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="2" <c:if test="${gradeMap[item.id]['score'] == '2'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                    </label>
                                                    合格
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="3" <c:if test="${gradeMap[item.id]['score'] == '3'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                    </label>
                                                    良好
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="4" <c:if test="${gradeMap[item.id]['score'] == '4'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                    </label>
                                                    优秀
                                                    <label>
                                                        <input name="${item.id}score" type="radio" class="validate[required] scoreCount" value="5" <c:if test="${gradeMap[item.id]['score'] == '5'}">checked</c:if> onchange="$('#${item.id}scoreSel').val(this.value);countScore();"/>
                                                    </label>
                                                </c:if>
                                                <c:if test="${isRead || rec.statusId eq recStatusEnumSubmit.id}">
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('1',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;（不合格）
                                                    </c:if>
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('2',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;（基本合格）
                                                    </c:if>
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('3',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;（合格）
                                                    </c:if>
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('4',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;（良好）
                                                    </c:if>
                                                    <c:if test="${!empty gradeMap[item.id]['score'] && (fn:indexOf('5',gradeMap[item.id]['score'])>-1)}">
                                                        ${gradeMap[item.id]['score']}&nbsp;（优秀）
                                                    </c:if>
                                                </c:if>
                                                <input id="${item.id}scoreSel" name="score" type="hidden" value="${gradeMap[item.id]['score']}"/>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td style="text-align: left;" colspan = "3"></td>
                                    <td style="text-align: left;">&#12288;总分：${empty gradeMap['totalScore']?0:gradeMap['totalScore']}</td>
                                </tr>
                                <tr style="height:150px;">
                                    <td style="text-align: left;" >&#12288;主观评价</td>
                                    <td style="text-align: left;"  colspan="3">${gradeMap['lostReason']}</td>
                                </tr>
                            </table>
                        </c:forEach>

                        <br/>
                        <div style="width: 50%;float: left;box-sizing: border-box;font-size: 15px;">&#12288;&#12288;评价人:${rec.operUserName}</div>
                        <div style="width: 50%;float: left;box-sizing: border-box;padding-left: 20%;font-size: 15px;" >&#12288;&#12288;评价时间:${openTime}</div>
                    </form>

                    <div style="text-align: center;margin-top: 5px;">
                        <input type="button" value="关&#12288;闭" class="search" onclick="closeGrade();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>