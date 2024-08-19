<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".show").on("mouseenter mouseleave",
                function(){
                    $(".info",this).toggle(100);
                }
        );
        <c:if test="${not empty doctorList}">
        var style={"margin":"5px 40px","width":"1400px"};
        var options ={
            "colums":5//根据固定列的数量
        };
        $("#dataTable").Scorll(options,style,false,null);
        </c:if>
    });

    function changeCheck(checkbox){
        if (checkbox.checked) {
            $("input[name='scoreFlow']").prop("checked", true);
        } else {
            $("input[name='scoreFlow']").prop("checked", false);
        }
    }
    
    function showDetail(scoreFlow,theroyScoreFlow) {
        var scoreFlows = scoreFlow+","+theroyScoreFlow;
        var url = "<s:url value='/jsres/doctorTheoryScore/updateAffirm'/>?scoreFlows="+scoreFlows;
        jboxConfirm("是否确认操作？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    toPage();
                    jboxTip("操作成功！");
                }else{
                    jboxTip("操作失败！");
                }
            }, null, true);
        });
    }
    function delDetail(scoreFlow,theroyScoreFlow) {
        var scoreFlows = scoreFlow+","+theroyScoreFlow;
        var url = "<s:url value='/jsres/doctorTheoryScore/updateNotAffirm'/>?scoreFlows="+scoreFlows;
        jboxConfirm("是否确认操作？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    toPage();
                    jboxTip("操作成功！");
                }else{
                    jboxTip("操作失败！");
                }
            }, null, true);
        });
    }

</script>
<c:if test="${empty doctorList}">
    <div class="search_table" >
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>结业确认<th>
                <th>姓名</th>
                <th>合格批次</th>
                <th>证件号</th>
                <th>年级</th>
                <th>地市</th>
                <th>培训<br>类别</th>
                <th>国家<br>基地</th>
                <th>培训<br>基地</th>
                <th>培训<br>专业</th>
                <th>基地审<br>核意见</th>
                <th>市局审<br>核意见</th>
                <th>省厅审<br>核意见</th>
                <th>理论考<br>试编号</th>
                <th>技能考<br>试编号</th>
            </tr>
            <tr>
                <td colspan="99" >无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not  empty doctorList}">
    <div class="main_bd clearfix">
<div class="search_table" style="padding:0;width: 60%">
    <table class="grid" style="width: 100%;" id="dataTable">
        <thead>
        <tr>
            <th style="min-width: 60px; max-width: 60px; ">全选/反选
                <br>
                <input type="checkbox" id="checkAll" onclick="changeCheck(this)">
            </th>
            <th style="min-width: 100px; max-width: 100px; ">操作</th>
            <th style="min-width: 60px; max-width: 60px; ">结业确认</th>
            <th style="min-width: 60px; max-width: 60px; ">姓名</th>
            <th style="min-width: 60px; max-width: 60px; ">合格批次</th>
            <th  style="min-width: 150px; max-width: 150px; " >证件号</th>
            <th style="min-width: 60px; max-width: 60px; ">年级</th>
            <th style="min-width: 60px; max-width: 60px; ">地市</th>
            <th style="min-width: 60px; max-width: 60px; ">培训类别</th>
            <th  style="min-width: 150px; max-width: 150px; " >国家基地</th>
            <th  style="min-width: 150px; max-width: 150px; " >培训基地</th>
            <th style="min-width: 60px; max-width: 60px; ">培训专业</th>
            <th  style="min-width: 150px; max-width: 150px; " >基地审核意见</th>
            <th  style="min-width: 150px; max-width: 150px; " >市局审核意见</th>
            <th  style="min-width: 150px; max-width: 150px; " >省厅审核意见</th>
            <th style="min-width: 60px; max-width: 60px; ">理论考试编号</th>
            <th style="min-width: 60px; max-width: 60px; ">技能考试编号</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${doctorList}" var="doctor">
            <tr class="fixTrTd">
                <c:if test="${maintenance ne 'Y'}">
                <td style="min-width: 60px; max-width: 60px;" class="by">
                    <input type="checkbox" id="scoreFlow" name="scoreFlow" value="${doctor.resScore.scoreFlow},${doctor.theroyScoreFlow}"/>
                </td>
                <c:if test="${empty doctor.certificateIssuingStatus}"><td>
                    <a href="javascript:void(0);" class="btn" onclick="showDetail('${doctor.resScore.scoreFlow}','${doctor.theroyScoreFlow}');">确认</a>
                </td></c:if>
                <c:if test="${not empty doctor.certificateIssuingStatus}"><td>
                    <a href="javascript:void(0);" class="btn" onclick="delDetail('${doctor.resScore.scoreFlow}','${doctor.theroyScoreFlow}')">取消确认</a>
                </td>
                </c:if>
                </c:if>

                <c:if test="${empty doctor.certificateIssuingStatus}"><td style="min-width: 60px; max-width: 60px;" class="by">未确认</td></c:if>
                <c:if test="${not empty doctor.certificateIssuingStatus}"><td style="min-width: 60px; max-width: 60px;" class="by">已确认</td></c:if>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.sysUser.userName}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">
                    <c:if test="${not empty doctor.theoryTestId  && not empty doctor.skillTestId}">
                        ${doctor.theoryTestId > doctor.skillTestId ? doctor.theoryTestId : doctor.skillTestId}
                    </c:if>
                    <c:if test="${empty doctor.theoryTestId}">
                        ${doctor.skillTestId}
                    </c:if>
                    <c:if test="${empty doctor.skillTestId}">
                        ${doctor.theoryTestId}
                    </c:if>
                </td>
                <td style="min-width: 150px; max-width: 150px;" class="by">${doctor.sysUser.idNo}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.sessionNumber}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.placeName}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.catSpeName}</td>
                <td style="min-width: 150px; max-width: 150px;" class="by">${doctor.countryOrgName}</td>
                <td style="min-width: 150px; max-width: 150px;" class="by">${doctor.orgName}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.speName}</td>
                <td style="min-width: 150px; max-width: 150px;" class="by">${doctor.localReason}</td>
                <td style="min-width: 150px; max-width: 150px;" class="by">${doctor.cityReason}</td>
                <td style="min-width: 150px; max-width: 150px;" class="by">${doctor.globalReason}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.theoryTestId}</td>
                <td style="min-width: 60px; max-width: 60px;" class="by">${doctor.skillTestId}</td>
                <%--<td style="min-width: 150px; max-width: 150px;" class="by" <c:if test="${not empty doctor.resScore.scorePhaseId}">title="${doctor.resScore.scorePhaseId}年份"</c:if>>
                        ${doctor.resScore.theoryScore eq '1'?'合格':(doctor.resScore.theoryScore eq '0'?'不合格':(doctor.resScore.theoryScore eq '2'?'缺考':'-'))}
                </td>
                <td style="min-width: 150px; max-width: 150px;" class="by" <c:if test="${not empty doctor.resScore.scorePhaseName}">title="${doctor.resScore.scorePhaseName}年份"</c:if>>
                    <c:if test="${doctor.resScore.skillScore eq GlobalConstant.PASS}">合格</c:if>
                    <c:if test="${doctor.resScore.skillScore eq GlobalConstant.UNPASS}">不合格</c:if>
                    <c:if test="${doctor.resScore.skillScore eq '2'}">缺考</c:if>
                    <c:if test="${empty doctor.resScore.skillScore}">-</c:if>
                </td>--%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
    </div>
</c:if>
<div class="page" style="width: 906px;margin: 15px auto;">
    <c:set var="pageView" value="${pdfn:getPageView(doctorList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>

