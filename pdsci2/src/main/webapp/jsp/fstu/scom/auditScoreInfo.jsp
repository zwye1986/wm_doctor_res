<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div{margin-top:5px;}
    </style>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/fstu/score/saveScore'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            }, null , true);
        }
        function pass(recordFlow){
            jboxConfirm("是否确认审核?", function(){
                var json = {"recordLst":[recordFlow],"auditStatusId":"Passed","roleFlag":"${param.roleFlag}"};
                var url = "<s:url value='/fstu/score/auditScore'/>";
                jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, true);
            });
        }
        function back(recordFlow){
            jboxConfirm("是否确认退回?", function(){
                var json = {"recordLst":[recordFlow],"auditStatusId":"Backed","roleFlag":"${param.roleFlag}"};
                var url = "<s:url value='/fstu/score/auditScore'/>";
                jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, true);
            });
        }
        function backPassing(recordFlow){//即提交操作变更为未审核状态
            jboxConfirm("是否确认取消审核?", function(){
                var url = "<s:url value='/fstu/score/submitScore?roleFlag=${param.roleFlag}&recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, true);
            });
        }
        function del(recordFlow){
            jboxConfirm("是否确认删除?", function(){
                var url = "<s:url value='/fstu/score/delScore?recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="myForm">
                <table class="basic" style="width: 100%;">
                  <tr>
                      <th style="width: 20%;"><span style="color:red;">*</span>学分年份：</th>
                      <td colspan="3">
                          <select name="sessionNumber" style="width:140px;" disabled="disabled">
                              <option/>
                              <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                                  <option value="${dict.dictName}" <c:if test="${dict.dictName eq score.sessionNumber}">selected</c:if>>${dict.dictName}</option>
                              </c:forEach>
                          </select>
                          <input type="hidden" name="score.recordFlow" value="${score.recordFlow}">
                          <input type="hidden" name="score.configRecordFlow" value="${score.configRecordFlow}">
                          <input type="hidden" name="score.firstMiurAuditFlag" value="${score.firstMiurAuditFlag}">
                      </td>
                      <th style="width: 20%;">学员姓名：</th>
                      <td colspan="3">
                          <input type="text" name="userName" value="${score.userName}" readonly="readonly" style="width:137px;" readonly="readonly">
                          <input type="hidden" name="userFlow" value="${score.userFlow}">
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;">学员工号：</th>
                      <td colspan="3">
                          <input type="text" name="userCode" value="${score.userCode}" readonly="readonly" style="width:137px;" readonly="readonly">
                      </td>
                      <th style="width: 20%;">用户所属部门：</th>
                      <td colspan="3">
                          <input type="text" name="userDeptName" value="${score.userDeptName}" readonly="readonly" style="width:137px;" readonly="readonly">
                          <input type="hidden" name="userDeptFlow" value="${score.userDeptFlow}">
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;"><span style="color:red;">*</span>学分类别：</th>
                      <td colspan="3">
                          <select name="firstScoreTypeId" style="width:140px;" disabled="disabled">
                              <option/>
                              <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                  <option value="${dict.dictId}" <c:if test="${dict.dictId eq score.firstScoreTypeId}">selected</c:if>>${dict.dictName}</option>
                              </c:forEach>
                          </select>
                          <input type="hidden" name="firstScoreTypeName" value="${score.firstScoreTypeName}">
                      </td>
                      <th style="width: 20%;"><span style="color:red;">*</span>项目大类：</th>
                      <td colspan="3">
                          <select name="firstProjTypeId" style="width:140px;" disabled="disabled">
                              <option/>
                              <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                  <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                  <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                      <option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test="${scope.dictId eq score.firstProjTypeId and dict.dictId eq score.firstScoreTypeId}">selected</c:if>>${scope.dictName}</option>
                                  </c:forEach>
                              </c:forEach>
                          </select>
                          <input type="hidden" name="firstProjTypeName" value="${score.firstProjTypeName}">
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;"><span style="color:red;">*</span>评分项：</th>
                      <td colspan="3">
                          <select name="firstScoreItemId" style="width:140px;" disabled="disabled">
                              <option/>
                              <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                  <option value="${dict.dictId}" <c:if test="${dict.dictId eq score.firstScoreItemId}">selected</c:if>>${dict.dictName}</option>
                              </c:forEach>
                          </select>
                          <input type="hidden" name="firstScoreItemName" value="${score.firstScoreItemName}">
                      </td>
                      <th style="width: 20%;"><span style="color:red;">*</span>完成情况：</th>
                      <td colspan="3">
                          <select name="firstExecutionId" style="width:140px;" disabled="disabled">
                              <option/>
                              <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                  <option value="${dict.dictId}" <c:if test="${dict.dictId eq score.firstExecutionId}">selected</c:if>>${dict.dictName}</option>
                              </c:forEach>
                          </select>
                          <input type="hidden" name="firstExecutionName" value="${score.firstExecutionName}">
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;">名称：</th>
                      <td colspan="3">
                          <input type="text" name="scoreName" value="${score.scoreName}" style="width:137px;" readonly="readonly">
                      </td>
                      <th style="width: 20%;">编号：</th>
                      <td colspan="3">
                          <input type="text" name="scoreNumber" value="${score.scoreNumber}" style="width:137px;" readonly="readonly">
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;">开始时间：</th>
                      <td colspan="3">
                          <input type="text" readonly="readonly" style="width:137px;" name="beginTime" value="${score.beginTime}">
                      </td>
                      <th style="width: 20%;">结束时间：</th>
                      <td colspan="3">
                          <input type="text" readonly="readonly" style="width:137px;" name="endTime" value="${score.endTime}">
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;">说明：</th>
                      <td colspan="8">
                          <textarea type="text" name="instruction" style="width:550px;height:80px;" placeholder="支持250字符" readonly="readonly">${score.instruction}</textarea>
                      </td>
                  </tr>
                  <tr>
                      <th style="width: 20%;"><span style="color:red;">*</span>分值：</th>
                      <td colspan="3">
                          <input type="text" name="score.myScore" value="${score.myScore}" class="validate[required,custom[number]]" style="width:137px;">
                      </td>
                      <th style="width: 20%;">最大分值：</th>
                      <td colspan="3">
                          <input type="text" name="firstMaxScore" value="${score.firstMaxScore}" readonly="readonly" style="width:137px;">
                          <input type="hidden" name="firstScore" value="${score.firstScore}">
                      </td>
                  </tr>
                </table>

            <div style="text-align: center;margin-top:20px;">
                <c:if test="${(param.roleFlag eq 'header' && score.headerAuditStatusId eq 'Passing') || (param.roleFlag ne 'header' && score.auditStatusId eq 'Passing')}">
                    <input type="button" class="search" onclick="pass('${score.recordFlow}');" value="同&#12288;意"/>
                    <input type="button" class="search" onclick="back('${score.recordFlow}');" value="退&#12288;回"/>
                </c:if>
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
                <c:if test="${param.roleFlag ne 'header' && score.auditStatusId eq 'Passed'}">
                    <input type="button" class="search" onclick="backPassing('${score.recordFlow}');" value="取消审核"/>
                </c:if>
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>