<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="font" value="true"/>
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
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
        function detailShow(span,clazz){
            console.log(clazz);
            $("."+clazz+"Show").fadeToggle(100);
        }

        function showMsg(value) {
            console.log(value);
            $("tr[class^='Show'],td[class$='Show']").each(function(){
                $(this).hide();
            });
            if(value == ""){
                $("tr[class^='Show']").each(function(){
                    $(this).show();
                });
            }else{
                $(".Show"+value).show();
            }
        }

        function exportGradeSearchDoc(recFlow){
            if(!recFlow){
                recFlow = "";
            }
            jboxStartLoading();
            jboxTip("导出中…………");
            var url = "<s:url value='/res/evaluateHospitalResult/exportGradeSearchDoc'/>?gradeRole=${param.gradeRole}&recFlow="+recFlow+"&keyCode=${param.keyCode}&operStartDate=${param.startDate}&operEndDate=${param.endDate}&date=${param.date}&role=${param.role}";
            window.location.href=url;
            jboxEndLoading();
        }

        function doClose() {
            top.jboxClose();
        }
    </script>

</head>
<body>
<div style="width:100%;max-height: 500px;overflow: auto">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <c:if test="${param.gradeRole ne 'doctor'}">
            <colgroup>
                <col width="15%"/>
                <col width="25%"/>
                <col width="30%"/>
                <col width="15%"/>
                <col width="15%"/>
            </colgroup>
        </c:if>
        <c:if test="${param.gradeRole eq 'doctor'}">
            <colgroup>
                <col width="10%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="30%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
            </colgroup>
        </c:if>

        <tr>
            <c:if test="${param.gradeRole ne 'doctor' and param.gradeRole ne '360doctor' and param.gradeRole ne 'ManageDoctorAssess360'}">
                <th style="text-align: left;padding-left: 10px;">学员姓名</th>
                <th style="text-align: center;padding:0">评价日期</th>
            </c:if>
            <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor' or param.gradeRole eq 'ManageDoctorAssess360'}">
                <th style="text-align: center;padding:0">评价人</th>
                <th style="text-align: center;padding:0">角色</th>
<%--            <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">--%>
                <th style="text-align: center;padding:0">科室</th>
<%--            </c:if>--%>
            </c:if>
<%--            <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">--%>
                <th style="text-align: center;padding:0">轮转时间</th>
<%--            </c:if>--%>
            <th style="text-align: center;padding:0">总分</th>
            <th style="text-align: center;padding:0">得分</th>
            <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">
                <%--<th style="text-align: center;padding:0">操作</th>--%>
                <%--<th style="font-weight:normal;text-align: center;"><a style="color: #459ae9;" onclick="exportGradeSearchDoc()">[批量导出]</a></th>--%>
            </c:if>
        </tr>

        <c:forEach items="${recList}" var="rec">
            <c:set var="key" value="${rec.operUserFlow}${rec.recFlow}"/>
            <tr class="Show${rec.sessionNumber}">
                <th style="text-align: left;padding-left: 10px;"><span style="cursor: pointer;color: blue;font-size: 0.8em;line-height: 5px;" onclick="detailShow(this,'${key}');">${rec.operUserName}</span></th>
                <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor' or param.gradeRole eq 'ManageDoctorAssess360'}">
                    <th style="text-align: center;padding-right: 0px;">${rec.roleName}</th>
<%--                <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">--%>
                    <th style="text-align: center;padding-right: 0px;">${rec.deptName}</th>
<%--                </c:if>--%>
                </c:if>
                <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">
                    <th style="font-weight:normal;text-align: center;padding-right: 0px;"> ${rec.operTime}</th>
                </c:if>
<%--                <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">--%>
                    <th style="font-weight:normal;text-align: center;padding-right: 0px;"> ${rec.schStartDate}~${rec.schEndDate}</th>
<%--                </c:if>--%>
                <th style="font-weight:normal;text-align: center;padding-right: 0px;">${scoreSumMap[key]}</th>
                <th style="text-align: center;padding-right: 0px;">${scoreMap[key]}</th>
                <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor' and tdFlag ne 'ManageDoctorAssess360'}">
                    <%--<th style="font-weight:normal;text-align: center;"><a style="color: #459ae9;" onclick="exportGradeSearchDoc('${rec.recFlow}')">[导出]</a></th>--%>
                </c:if>
            </tr>
            <c:forEach items="${assessCfgLists[key]}" var="title">
                <tr>
                    <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor'}">
                    <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor'}">
                        <td class="${key}Show" colspan="6" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                ${title.name}
                        </td>
                    </c:if>
                        <c:if test="${tdFlag eq 'doctor' or tdFlag eq '360doctor'}">
                            <td class="${key}Show" colspan="6" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                    ${title.name}
                            </td>
                        </c:if>
                    </c:if>
                    <c:if test="${param.gradeRole ne 'doctor' and tdFlag ne '360doctor'}">
                        <td class="${key}Show" colspan="5" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
                                ${title.name}
                        </td>
                    </c:if>
                </tr>
                <c:forEach items="${title.itemList}" var="item">
                    <c:set var="scoreKey" value="${key}${item.id}"/>
                    <tr>
                        <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor'}">
                            <c:if test="${tdFlag eq 'doctor' or tdFlag eq '360doctor'}">
                                <td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="4">${item.name}</td>
                            </c:if>
                            <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor'}">
                                <td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="4">${item.name}</td>
                            </c:if>
                        </c:if>
                        <c:if test="${param.gradeRole ne 'doctor' and param.gradeRole ne '360doctor'}">
                            <td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="3">${item.name}</td>
                        </c:if>
                        <td class="${key}Show" style="display: none;text-align: center;padding-left: 0px;">${item.score}</td>
                        <td class="${key}Show" style="display: none;text-align: center;padding-left: 0px;">${scoreMap[scoreKey]}</td>
                        <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor'}">
                            <%--<td class="${key}Show" style="display: none;text-align: center;padding-left: 0px;"></td>--%>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:forEach>
        </c:forEach>
        <c:if test="${empty recList}">
            <tr>
                <c:if test="${param.gradeRole ne 'doctor' and param.gradeRole ne '360doctor'}">
                    <td style="text-align: center" colspan="5">
                        无记录
                    </td>
                </c:if>
                <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor'}">
                    <c:if test="${tdFlag ne 'doctor' and tdFlag ne '360doctor'}">
                        <td style="text-align: center" colspan="6">
                            无记录
                        </td>
                    </c:if>
                    <c:if test="${tdFlag eq 'doctor' or tdFlag eq '360doctor'}">
                        <td style="text-align: center" colspan="4">
                            无记录
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:if>
    </table>
    <center style="margin-top: 10px;">
        <input type="button"  class="btn_green" value="关&#12288;闭" onclick="doClose();"/>
    </center>
</div>
</body>
</html>