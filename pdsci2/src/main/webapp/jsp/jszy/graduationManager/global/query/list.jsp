<script type="text/javascript">
    $(document).ready(function () {
    });
    function selectAll(exp) {
        $(".search_table input[type='checkbox']").attr("checked", "checked");
    }
    function Contrary(exp) {
        var selects = $(".search_table input[type='checkbox']");
        for (var i = 0; i < selects.length; i++) {
            var exp = $(selects[i]).attr("checked");
            if (exp == "checked") {
                $(selects[i]).attr("checked", false);
            } else {
                $(selects[i]).attr("checked", "checked");
            }
        }
    }
    function editGraduation(recordFlow, recordStatus, auditStatusId) {
        var data = {
            "recordFlow": recordFlow,
            "recordStatus": recordStatus,
            "auditStatusId": auditStatusId,
            "roleFlag": "${roleFlag}"
        };
        var masg = "确认审核通过?";
        if("Cancel" == auditStatusId){
            masg = "确认撤销?";
        }
        jboxConfirm(masg,
            function () {
                jboxPost("<s:url value='/jszy/graduationManager/checkDoctorGraduationInfo'/>", data, function (resp) {
                    jboxTip(resp);
                    toPage(1);
                }, null, true);
            }, null
        );
    }
</script>
<div style="overflow: auto;max-width: 950px;width: 950px;">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag and 'GlobalAudit' eq tabType}">
                    <th style="min-width: 80px;"><a href="javascript:void(0);" onclick="selectAll();">全选</a>/<a
                            href="javascript:void(0);" onclick="Contrary();">反选</a></th>
                </c:if>
                <c:choose>
                    <c:when test="${'Query' eq pageType}">

                    </c:when>
                    <c:when test="${(GlobalConstant.USER_LIST_GLOBAL eq roleFlag and 'GlobalAuditEd' eq tabType)}">
                        <th style="min-width: 80px;">生成证书</th>
                    </c:when>
                    <c:when test="${(GlobalConstant.USER_LIST_LOCAL eq roleFlag and 'GlobalAuditEd' eq tabType)
                    or (GlobalConstant.USER_LIST_GLOBAL eq roleFlag and 'LocalAudit' eq tabType)}">

                    </c:when>
                    <c:otherwise>
                        <th style="min-width: 80px;">操作</th>
                    </c:otherwise>
                </c:choose>
                <th style="min-width: 80px;">结业年份</th>
                <th style="min-width: 80px;">姓名</th>
                <th style="min-width: 80px;">证件号码</th>
                <th style="min-width: 80px;">培训基地</th>
                <th style="min-width: 80px;">所在单位</th>
                <th style="min-width: 80px;">培训专业</th>
                <th style="min-width: 80px;">实际培训<br/>开始时间</th>
                <th style="min-width: 80px;">实际培训<br/>结束时间</th>
                <th style="min-width: 80px;">理论考试<br/>通过年度</th>
                <th style="min-width: 80px;">年度考核<br/>是否通过</th>
                <th style="min-width: 80px;">培训时间<br/>是否完成</th>
                <th style="min-width: 80px;">登记手册<br/>是否完整</th>
                <th style="min-width: 80px;">跟师考核<br/>是否合格</th>
                <c:if test="${'Query' eq pageType}">
                    <th style="min-width: 80px;">证书编号</th>
                    <th style="min-width: 80px;">证书样式</th>
                </c:if>
            </tr>
            <c:forEach items="${graduationInfos}" var="graduation">
                <tr>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag and 'GlobalAudit' eq tabType}">
                        <td><input recordFlow="${graduation.recordFlow}" type="checkbox"/></td>
                    </c:if>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}">
                        <c:if test="${'Import' eq pageType}">
                            <td>
                                <c:if test="${empty graduation.certificateNumber}">
                                    <a href="javascript:void(0);"
                                       onclick="editInfo('${graduation.recordFlow}');">编辑</a>
                                    <a href="javascript:void(0);" onclick="delInfo('${graduation.recordFlow}');">删除</a>
                                </c:if>
                            </td>
                        </c:if>
                        <c:if test="${'GlobalAudit' eq tabType}">
                            <td>
                                <c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq roleFlag and org.orgFlow eq graduation.trainingBaseFlow )or (GlobalConstant.USER_LIST_GLOBAL eq roleFlag)}">
                                    <a href="javascript:void(0);"
                                       onclick="editGraduation('${graduation.recordFlow}','','${jszyBaseStatusEnumGlobalPassed.id}');">确认</a>
                                </c:if>
                            </td>
                        </c:if>
                        <c:if test="${'GlobalAuditEd' eq tabType}">
                            <td>
                                <c:if test="${empty graduation.certificateNumber}">
                                    <a href="javascript:void(0);"
                                       onclick="createCertificate('${graduation.recordFlow}');">生成证书</a>
                                </c:if>
                            </td>
                        </c:if>
                    </c:if>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL eq roleFlag}">
                        <c:if test="${'LocalAudit' eq tabType}">
                            <td>
                                <a href="javascript:void(0);"
                                   onclick="editGraduation('${graduation.recordFlow}','','${jszyBaseStatusEnumLocalPassed.id}');">确认</a>
                            </td>
                        </c:if>
                        <c:if test="${'GlobalAudit' eq tabType}">
                            <td>
                                <a href="javascript:void(0);"
                                   onclick="editGraduation('${graduation.recordFlow}','','Cancel');">撤销</a>
                            </td>
                        </c:if>
                    </c:if>
                    <td>${graduation.graduationYear}</td>
                    <td>${graduation.doctorName}</td>
                    <td>${graduation.idNo}</td>
                    <td>${graduation.trainingBaseName}</td>
                    <td>${graduation.company}</td>
                    <td>${graduation.trainingTypeName}</td>
                    <td>${graduation.trainingStartTime}</td>
                    <td>${graduation.trainingEndTime}</td>
                    <td>${graduation.passTheoryAssessmentYear}</td>
                    <td>${graduation.ifPassAnnualAssessment}</td>
                    <td>${graduation.ifCompleteTrainingTime}</td>
                    <td>${graduation.ifCompleteRegisterManual}</td>
                    <td>${graduation.ifPassDiscipleAssessment}</td>
                    <c:if test="${'Query' eq pageType}">
                        <td>${graduation.certificateNumber}</td>
                        <td>
                            <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag}">
                                <a style="cursor: pointer;"
                                   href="<s:url value='/jszy/graduationManager/showCertificate?isGlobal=Y&recordFlow=${graduation.recordFlow}'/>"
                                   target="_blank">查看</a>
                            </c:if>
                            <c:if test="${GlobalConstant.USER_LIST_GLOBAL ne roleFlag}">
                                <a style="cursor: pointer;"
                                   href="<s:url value='/jszy/graduationManager/showCertificate?recordFlow=${graduation.recordFlow}'/>"
                                   target="_blank">查看</a>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty graduationInfos}">
                <tr>
                    <td colspan="16">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(graduationInfos)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>

