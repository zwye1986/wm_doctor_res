<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css"/>
<script type="text/javascript">


$(document).ready(function () {
    toPage();
});

function toPage(page) {
    if (page != undefined) {
        $("#currentPage").val(page);
    }
    search();
}

function activate(userFlow) {
    jboxConfirm("确认恢复启用该用户吗？", function () {
        var url = "<s:url value='/sys/user/activate?userFlow='/>" + userFlow;
        jboxGet(url, null, function () {
            search();
        });
    });
}

function unLock(userFlow) {
    jboxConfirm("确认解锁该用户吗？", function () {
        var url = "<s:url value='/sys/user/activate?userFlow='/>" + userFlow;
        jboxGet(url, null, function () {
            search();
        });
    });
}

function stop(userFlow) {
    var url = "<s:url value='/jsres/manage/reasonForLockUser?userFlow='/>" + userFlow;
    jboxOpen(url, "停用用户", 500, 200);
}

function search() {
    var url = "<s:url value='/jsres/doctor/accountList'/>?baseFlag=${param.baseFlag}";
    jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
}

<%--
function lock(userFlow){
    var title = "确认锁定该用户吗？锁定后该用户将不能登录系统！";
    jboxConfirm(title, function () {
        var url = "<s:url value='/jsres/doctor/lock?userFlow='/>"+userFlow;
        jboxGet(url,null,function(){
            search();
        });
    });
}

function unLock(userFlow){
    var title = "确认解锁该用户吗？";
    jboxConfirm(title, function () {
        var url = "<s:url value='/jsres/doctor/unLock?userFlow='/>"+userFlow;
        jboxGet(url,null,function(){
            search();
        });
    });
}
--%>

function resetPasswd(userFlow) {
    var title = "确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？";
    jboxConfirm(title, function () {
        var url = "<s:url value='/jsres/doctor/resetPasswd?userFlow='/>" + userFlow;
        jboxGet(url, null, function () {
            //search();
        });
    });
}

function savecfg(obj) {

    if ($(obj).attr("checked") == "checked") {
        $("#cfgValue").val("Y");
    } else {
        $("#cfgValue").val("N");
    }
    var url = "<s:url value='/jsres/powerCfg/saveOne'/>";
    jboxPost(url, $('#sysCfgForm').serialize(), function (resp) {
        if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
            jboxTip("操作成功！");
        } else {
            jboxTip("操作失败！");
        }
        ;
    }, null, false);
}

function removeBind(doctorFlow) {
    jboxConfirm("确认解绑?一旦解绑后，无法撤回!", function () {
        jboxPost("<s:url value='/res/manager/removeBind'/>?doctorFlow=" + doctorFlow, null, function () {
            search();
            jboxClose();
        }, null, true);
    });
}

function unlockLogin(doctorFlow, userName) {
    var url = "<s:url value='/res/manager/queryStudentUntiedRecording'/>?doctorFlow=" + doctorFlow + "&userName=" + userName;
    jboxOpen(url, "解锁", 700, 500);
}

function lokeStudentUntiedRecording(doctorFlow) {
    var url = "<s:url value='/res/manager/studentUntiedRecordingList'/>?doctorFlow=" + doctorFlow;
    jboxOpen(url, "解锁记录详情", 800, 600);
}

function changeTrainSpes(t) {
    var trainCategoryid = $("#trainingTypeId").val();
    if (trainCategoryid == "") {
        $("select[name=trainingSpeId] option[value != '']").remove();
        return false;
    }
    $("select[name=trainingSpeId] option[value != '']").remove();
    $("#" + trainCategoryid + "_select").find("option").each(function () {
        $(this).clone().appendTo($("#trainingSpeId"));
    });
    return false;
}

function checkSingel(obj) {
    if (!$(obj).is(':checked')) {
        $("#checkAll").prop("checked", false);
    } else {
        var checkAllLen = $("input[type='checkbox'][class='check']").length;
        var checkLen = $("input[type='checkbox'][class='check']:checked").length;
        if (checkAllLen == checkLen) {
            $("#checkAll").prop("checked", true);
        }
    }
}

function allCheck() {
    if ($("#checkAll").is(':checked')) {
        $(".check").prop("checked", true);
    } else {
        $(".check").prop("checked", false);
    }
}

function batchUnlock() {
    var checkLen = $(":checkbox[class='check']:checked").length;
    if (checkLen == 0) {
        jboxTip("请勾选解锁学员！");
        return;
    }
    var userList = [];
    $(":checkbox[class='check']:checked").each(function () {
        userList.push(this.value);
    })

    jboxConfirm("确认批量解锁勾选学员？", function () {
        var url = "<s:url value='/sys/user/batchUnlock?userList='/>" + userList;
        jboxGet(url, null, function () {
            search();
        });
    });
}



 //显示隐藏
let flag = false;
function showOrHide(){

	if(flag){
		$(`.form_item_hide`).hide();
		// document.getElementById("hideForm").style.display='none';
		$("#open").text("展开")
		flag = false;
	}else {
		$(`.form_item_hide`).css('display','flex');
		// document.getElementById("hideForm").style.display='flex';
		$("#open").text("收起")
		flag = true;
	}

}

</script>


<div class="main_hd">
    <h2 class="underline">学员帐号管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">

        <form id="sysCfgForm">
            <input type="hidden" id="cfgCode" value="${sessionScope.currUser.orgFlow}_bind" name="cfgCode"/>
            <input type="hidden" id="cfgValue" name="${sessionScope.currUser.orgFlow}_bind"/>
            <input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }"
                   name="${sessionScope.currUser.orgFlow}_bind_ws_id"/>
            <input type="hidden" id="desc" name="${sessionScope.currUser.orgFlow}_bind_desc">
        </form>
        <form id="searchForm">
            <div class="form_search">

                <div class="form_item">

                    <div class="form_label">姓&#12288;&#12288;名：</div>
                    <div class="form_content">
                        <input type="text" class="input" name="userName" value="${param.userName}"/>
                    </div>
                </div>


                <div class="form_item">
                    <div class="form_label">手机号码：</div>
                    <div class="form_content">
                        <input type="text" class="input" name="userPhone" value="${param.userPhone}"/>
                    </div>
                </div>


                <div class="form_item">
                    <div class="form_label">证件号码：</div>
                    <div class="form_content">
                        <input type="text" class="input" name="idNo" value="${param.idNo}"/>
                    </div>
                </div>


                <div class="form_item">
                    <div class="form_label">注册帐号：</div>
                    <div class="form_content">
                        <input type="text" class="input" name="userCode" value="${param.userCode}"/>
                    </div>
                </div>


                <div class="form_item form_item_hide">
                    <div class="form_label">培训类别：</div>
                    <div class="form_content">
                        <select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')"
                                >
                            <option value="DoctorTrainingSpe">住院医师</option>
                            <%--<option value="">请选择</option>
                            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                                <option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                            </c:forEach>--%>
                        </select>
                    </div>
                </div>


                <div class="form_item form_item_hide">
                    <div class="form_label">培训专业：</div>
                    <div class="form_content">
                        <select name="trainingSpeId" id="trainingSpeId" class="select" >
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>


                <div class="form_item form_item_hide">
                    <div class="form_label">账号状态：</div>
                    <div class="form_content">
                        <select name="trainingSpeId" id="trainingSpeId" class="select" >
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>

            </div>

            <div class="form_btn">
                <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>
                <input class="btn_green" type="button" onclick="batchUnlock()" value="批量解锁"/>

                <a style="color: #54B2E5; margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
            </div>


            <%--			<table class="basic" width="100%" style="margin-top: 10px;">--%>
            <%--			<input type="hidden" name="currentPage" id="currentPage"/>--%>
            <%--			<tr>--%>
            <%--				<td>姓&#12288;名:</td>--%>
            <%--				<td><input type="text" class="input" name="userName" value="${param.userName}" style="width:100px; margin:0px"/></td>--%>
            <%--				<td>手机号码：</td>--%>
            <%--				<td><input type="text" class="input" name="userPhone" value="${param.userPhone}" style="width:100px; margin:0px"/>&#12288;</td>--%>
            <%--				<td>证件号码：</td>--%>
            <%--				<td><input type="text" class="input" name="idNo" value="${param.idNo}" style="width:100px;margin: 0 0"/>&#12288;</td>--%>
            <%--				<td >注册帐号：</td>--%>
            <%--				<td><input type="text" class="input" name="userCode" value="${param.userCode}" style="width:160px;"/>&#12288;</td>--%>
            <%--			</tr>--%>
            <%--			<tr>--%>
            <%--				<td class="td_left">培训类别：</td>--%>
            <%--				<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
            <%--					<option value="DoctorTrainingSpe">住院医师</option>--%>
            <%--					&lt;%&ndash;<option value="">请选择</option>--%>
            <%--					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
            <%--						<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
            <%--					</c:forEach>&ndash;%&gt;--%>
            <%--				</select>--%>
            <%--				</td>--%>
            <%--				<td class="td_left">培训专业：</td>--%>
            <%--				<td><select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 107px;">--%>
            <%--					<option value="">全部</option>--%>
            <%--				</select>--%>
            <%--				</td>--%>
            <%--				&lt;%&ndash;<td>专&#12288;业：</td>--%>
            <%--				<td>--%>
            <%--					<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 107px;">--%>
            <%--						<option value="">全部</option>--%>
            <%--						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">--%>
            <%--							<option value="${spe.dictId}"--%>
            <%--									<c:if test="${param.trainingSpeId eq spe.dictId}">selected</c:if> >${spe.dictName}--%>
            <%--							</option>activate--%>
            <%--						</c:forEach>--%>
            <%--					</select>--%>
            <%--				</td>&ndash;%&gt;--%>
            <%--				<td>账号状态:</td>--%>
            <%--				<td>--%>
            <%--					<select name="lockStatus" id="lockStatus" class="select" style="width:107px;" >--%>
            <%--						<option value="">全部</option>--%>
            <%--						<option value="Activated">已激活</option>--%>
            <%--						<option value="SysLocked">已锁定</option>--%>
            <%--						<option value="Locked">已停用</option>--%>
            <%--					</select>&#12288;--%>
            <%--				</td>--%>

            <%--				<td  colspan="1"> <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/></td>--%>
            <%--				<td  colspan="1"> <input class="btn_green" type="button" onclick="batchUnlock()" value="批量解锁"/></td>--%>

            <%--			</tr>--%>
            <%--			<tr>--%>
            <%--				<td colspan="2">--%>
            <%--					<c:set var="key" value="${sessionScope.currUser.orgFlow}_bind"/>--%>
            <%--					<input type="checkbox"   class="validate[required]"  name="agree" onchange="savecfg(this)"--%>
            <%--						   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked="checked"</c:if> />&#12288;是否开启手机绑定功能--%>
            <%--				</td>--%>
            <%--			</tr>--%>
            <%--			</table>--%>
        </form>
    </div>

    <div id="contentDiv">

    </div>
    <div style="display: none;">
        <select id="WMFirst_select">
            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                    <option
                            <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                            value="${dict.dictId}">${dict.dictName}</option>
                </c:if>
            </c:forEach>
        </select>
        <select id="WMSecond_select">
            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                    <option
                            <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                            value="${dict.dictId}">${dict.dictName}</option>
                </c:if>
            </c:forEach>
        </select>
        <select id="AssiGeneral_select">
            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                    <option
                            <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                            value="${dict.dictId}">${dict.dictName}</option>
                </c:if>
            </c:forEach>
        </select>
        <select id="DoctorTrainingSpe_select">
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                    <option
                            <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
                            value="${dict.dictId}">${dict.dictName}</option>
                </c:if>
            </c:forEach>
        </select>

    </div>
</div>
