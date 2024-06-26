<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(submitFlag){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var datas = [];
            var tbl = $("#userTbl");
            $.each(tbl, function (i, n) {
                var userFlow = $(n).find("input[name='userFlow']").val();
                var studyOrgName = $(n).find("input[name='studyOrgName']").val();
                var studyCourse = $(n).find("input[name='studyCourse']").val();
                var studyMonth = $(n).find("input[name='studyMonth']").val();
                var studyBeginDate = $(n).find("input[name='studyBeginDate']").val();
                var studyEndDate = $(n).find("input[name='studyEndDate']").val();
                var memo = $(n).find("input[name='memo']").val();
                var data = {
                    "userFlow": userFlow,
                    "studyOrgName": studyOrgName,
                    "studyCourse": studyCourse,
                    "studyMonth": studyMonth,
                    "studyBeginDate": studyBeginDate,
                    "studyEndDate": studyEndDate,
                    "memo": memo
                };
                datas.push(data);
            });
            var requestData = JSON.stringify(datas);
            var url = "<s:url value='/fstu/score/saveUserStudy?submitFlag='/>"+submitFlag;
            jboxStartLoading();
            jboxPostJson(url, requestData, function (resp) {
                if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].$("#searchForm").submit();
                    window.location.reload();
                }
            }, null, true);
        }
        function checkYear(obj) {
            var dates = $(':text', $(obj).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function auditOpt(statusId){
            var tip = "审核通过";
            if(statusId == "UnPassed") tip="审核不通过";
            var auditMemo = $("textarea[name='auditMemo']").val();
            jboxConfirm("确认"+tip+"？",function(){
                jboxPost("<s:url value='/fstu/score/auditStudyOpt?userFlow=${userStudy.userFlow}&auditStatusId='/>"+statusId+"&auditStatusName="+tip+"&auditMemo="+auditMemo,null,function(resp){
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        window.parent.frames['mainIframe'].$("#searchForm").submit();
                        window.location.reload();
                    }
                },null,true);
            },null);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="width:100%;">
            <c:if test="${param.flag eq 'global'}"><div style="margin:0px 0px 10px 20px;font-size:16px;">单&#12288;&#12288;位：${userStudy.orgName}</div></c:if>
            <table class="xllist" id="userTbl" style="margin-top:20px;">
                <tr>
                    <th style="width:12%">姓名</th>
                    <td style="width:13%">${userStudy.userName}<input type="hidden" name="userFlow" value="${userStudy.userFlow}"></td>
                    <th style="width:12%">性别</th>
                    <td style="width:13%">${userStudy.sexName}</td>
                    <th style="width:12%">出生年月</th>
                    <td style="width:13%">${userStudy.userBirthday}</td>
                    <th style="width:12%">科室</th>
                    <td style="width:13%">${userStudy.deptName}</td>
                </tr>
                <tr>
                    <th>职务</th>
                    <td>${userStudy.postName}</td>
                    <th>职称</th>
                    <td>${userStudy.titleName}</td>
                    <th>进修单位</th>
                    <td>
                        <c:if test="${empty param.optFlag || param.optFlag eq '2'}">${userStudy.studyOrgName}</c:if>
                        <c:if test="${param.optFlag eq '1'}">
                            <input type="text" style="width:80px;" name="studyOrgName" class='validate[required]' value="${userStudy.studyOrgName}">
                        </c:if>
                    </td>
                    <th>进修科目</th>
                    <td>
                        <c:if test="${empty param.optFlag || param.optFlag eq '2'}">${userStudy.studyCourse}</c:if>
                        <c:if test="${param.optFlag eq '1'}">
                            <input type="text" style="width:80px;" name="studyCourse" class='validate[required]' value="${userStudy.studyCourse}">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>起止时间</th>
                    <td colspan="3">
                        <c:if test="${empty param.optFlag || param.optFlag eq '2'}">${userStudy.studyBeginDate} ~ ${userStudy.studyEndDate}</c:if>
                        <c:if test="${param.optFlag eq '1'}">
                            <input type="text" style="width:80px;" readonly="readonly" name="studyBeginDate" class='validate[required]' onchange="checkYear(this)" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${userStudy.studyBeginDate}"/>
                            ~
                            <input type="text" style="width:80px;" readonly="readonly" name="studyEndDate" class='validate[required]' onchange="checkYear(this)" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${userStudy.studyEndDate}"/>
                        </c:if>
                    </td>
                    <th>进修月份</th>
                    <td>
                        <c:if test="${empty param.optFlag || param.optFlag eq '2'}">${userStudy.studyMonth}</c:if>
                        <c:if test="${param.optFlag eq '1'}">
                            <input type="text" style="width:80px;" name="studyMonth" class='validate[required]' value="${userStudy.studyMonth}">
                        </c:if>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
            </table>
            <div style="margin-top:20px;">
                <span style="padding-left:28px;"></span>备&#12288;&#12288;注：<textarea style="width:87.5%;height:100px;" name="memo" ${empty param.optFlag || param.optFlag eq '2'?'readonly':''}>${userStudy.memo}</textarea>
            </div>
            <c:if test="${param.flag eq 'global' || (param.flag eq 'local' && !empty userStudy.auditStatusId && userStudy.auditStatusId ne 'Backed' ) }">
                <div style="margin-top:20px;">
                    <span style="padding-left:28px;"></span>审核意见：<textarea style="width:87.5%;height:100px;" name="auditMemo" ${empty param.optFlag?'readonly':''}>${userStudy.auditMemo}</textarea>
                </div>
            </c:if>
        </div>
        <div class="button" style="width: 100%;">
            <c:if test="${param.optFlag eq '1'}">
                <input class="search" type="button" value="保&#12288;存" onclick="save();"/>
                <input class="search" type="button" value="提&#12288;交" onclick="save('submit');"/>
            </c:if>
            <c:if test="${param.optFlag eq '2'}">
                <input class="search" type="button" value="通&#12288;过" onclick="auditOpt('Passed');"/>
                <input class="search" type="button" value="不通过" onclick="auditOpt('UnPassed');"/>
            </c:if>
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
</div>
</body>
</html>