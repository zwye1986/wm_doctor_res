<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
.head_bg{
    width: 900px;
    height: 180px;
    padding: 20px;
    box-sizing: border-box;
    background: url(" <s:url value='/jsp/inx/jsres/images/apply.png'/>") no-repeat;
}
.head_ft{
    display: block;
    text-align: center;
    color: #FFF;
}
.head_ft_T{
    font-size: 36px;
    letter-spacing: 11px;
}
.head_ft_B{
    font-size: 24px;
    margin: 10px 0px;
}
.head_btn{
    display: block;
    background: #FFF;
    width: 180px;
    height: 30px;
    border-radius: 20px;
    margin: 0px auto;
    color: skyblue;
}

</style>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function(){

    });

    function signUp() {
        var isTempUser = '${isTempUser}';
        if (isTempUser == 'Y') {
            jboxConfirm("您属于异常结业报考人员，审核时有被驳回风险，请关注最终审核结果。请问是否继续？",function(){
                if (${isAllowApply eq "Y"}) {
                    var url = "<s:url value ='/jsres/examSignUp/signUp'/>?typeId=${param.typeId}";
                    jboxOpen(url, "补考认证", 800, 350);
                } else {
                    jboxTip("您不符合补考报名条件")
                }
            },null);
        } else {
            if (${isAllowApply eq "Y"}) {
                var url = "<s:url value ='/jsres/examSignUp/signUp'/>?typeId=${param.typeId}";
                jboxOpen(url, "补考认证", 800, 350);
            } else {
                jboxTip("您不符合补考报名条件")
            }
        }

    }

    function  showSignup(signupFlow) {
        var url = "<s:url value ='/jsres/examSignUp/showSignup'/>?signupFlow="+signupFlow;
        jboxOpen(url,"补考报名详情",800,350);
    }
</script>
<div class="main_hd">
        <%--<h2 class="underline">补考报名</h2>--%>
</div>

<div  style="width: 95%;margin: 0px auto;">
<div class="main_bd clearfix" style="margin-top:20px;">
    <div class="main_bd clearfix" >
        <c:if test="${inTime}">
            <div class="head_bg" style="width: 100%;">
                <span class="head_ft head_ft_T">${currYear}年考试报名正在进行中</span>
                <span class="head_ft head_ft_B">Registration for the ${currYear} exam is in progress</span>
                <input type="button" class="head_btn" id="submitBtn" onclick="signUp();" value="点击报名"/>
            </div>
        </c:if>
    </div>
    <div class="main_bd clearfix" style="margin-top:20px;" >
            <h4>报名记录</h4>
<%--            <input type="button" class="btn_green"   value="报名记录"/>--%>
    </div>
    <c:if test="${empty signups}">
    <div class="search_table"  style="padding: 0 10px 0 0;margin-top:20px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>序号</th>
                <th>考试编号</th>
                <th>报名时间</th>
                <th>补考类型</th>
                <th>审核状态</th>
                <th>基地审核意见</th>
                <th>市局审核意见</th>
                <th>省厅审核意见</th>
                <%--<th>操作</th>--%>
            </tr>
            <tr>
                <td colspan="9" >无记录！</td>
            </tr>
        </table>
        </div>
    </c:if>
    <c:if test="${not empty signups}">
    <div class="main_bd clearfix" style="margin-top:20px;">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
            <thead>
            <tr>
                <th style="width: 8%"   class="toFiexdDept">序号</th>
                <th style="width: 8%"   class="toFiexdDept">考试编号</th>
                <th style="width: 16%" class="fixedBy">报名时间</th>
                <th style="width: 8%" class="fixedBy">补考类型</th>
                <th style="width: 12%" class="fixedBy">审核状态</th>
                <th style="width: 13.3%" class="fixedBy">基地审核意见</th>
                <th style="width: 13.3%" class="fixedBy">市局审核意见</th>
                <th style="width: 13.3%" class="fixedBy">省厅审核意见</th>
                <%--<th style="width: 8%" class="fixedBy">操作</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${signups}" var="s" varStatus="vars">
                <tr class="fixTrTd">
                    <td style="min-width: 80px; max-width: 80px; " class="by">${vars.index + 1}</td>
                    <td style="min-width: 80px; max-width: 80px; " class="by">${s.testId}</td>
                    <td style="min-width: 80px; max-width: 80px; " class="by">${pdfn:transDateTime(s.createTime)}</td>
                    <td style="min-width: 80px; max-width: 80px; " class="by">
                        <c:if test="${s.signupTypeId eq 'Skill'}">
                            技能补考
                        </c:if>
                        <c:if test="${s.signupTypeId eq 'Theory'}">
                            理论补考
                        </c:if>
                    </td>
                    <c:set var="k" value="${s.testId}_make_up"/>
                    <c:if test="${ sysCfgMap[k] eq 'Y'}">
                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.auditStatusName}</td>
                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.localReason}</td>
                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.cityReason}</td>
                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.globalReason}</td>
                    </c:if>
                    <c:if test="${ sysCfgMap[k] eq 'N' or empty sysCfgMap[k] }">
                        <c:choose>
                            <c:when test="${not empty s.globalAuditStatusId}">
                                <td style="min-width: 80px; max-width: 80px; " class="by">省厅审核中</td>
                            </c:when>
                            <c:otherwise>
                                <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.auditStatusName}</td>
                            </c:otherwise>
                        </c:choose>
                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.localReason}</td>
                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.cityReason}</td>
                        <td style="min-width: 80px; max-width: 80px; " class="by"> </td>
                    </c:if>
                    <%--<td style="min-width: 100px; max-width: 100px; " class="by">--%>
                        <%--<a class="btn"  onclick="showSignup('${s.signupFlow}');" style="margin-top: 5px;margin-bottom: 5px;">查看</a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </c:if>
</div>
</div>
