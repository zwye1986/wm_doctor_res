<script type="text/javascript">
var sessionId = '${sessionScope.sessionId}';
$(document).ready(function(){
	if ('${orderByClo}'=='activityName' && '${orderByFall}'==''){
		$("#orderByNameup").css("display","none");
		$("#orderByNamedown").css("display","");
	}else if ('${orderByClo}'=='activityName' && '${orderByFall}'=='DESC'){
		$("#orderByNamedown").css("display","none");
		$("#orderByNameup").css("display","");
	}

	if ('${orderByClo}'=='activityType' && '${orderByFall}'==''){
		$("#orderByTypeup").css("display","none");
		$("#orderByTypedown").css("display","");
	}else if ('${orderByClo}'=='activityType' && '${orderByFall}'=='DESC'){
		$("#orderByTypedown").css("display","none");
		$("#orderByTypeup").css("display","");
	}

	if ('${orderByClo}'=='deptName' && '${orderByFall}'==''){
		$("#orderByDeptup").css("display","none");
		$("#orderByDeptdown").css("display","");
	}else if ('${orderByClo}'=='deptName' && '${orderByFall}'=='DESC'){
		$("#orderByDeptdown").css("display","none");
		$("#orderByDeptup").css("display","");
	}

	if ('${orderByClo}'=='userName' && '${orderByFall}'==''){
		$("#orderByUserNameup").css("display","none");
		$("#orderByUserNamedown").css("display","");
	}else if ('${orderByClo}'=='userName' && '${orderByFall}'=='DESC'){
		$("#orderByUserNamedown").css("display","none");
		$("#orderByUserNameup").css("display","");
	}
	// 查看链接点击后变色,当前登陆有效
	var visitedLink = getCookie(sessionId + "_linkVisited");
	if(visitedLink) {
		var linkedArr = visitedLink.split(",");
		linkedArr.forEach(function(item){
			$("." + item).css("color", "#771caa");
		});
	}
});
function delTarget(targetFlow)
{
	var url = '<s:url value="/jsres/activityTarget/delTarget"/>?targetFlow='+targetFlow;
	jboxConfirm("确认删除？" , function(){
		jboxPost(url , null , function(resp){
			toPage(${currentPage});
		} , null , true);
	});
}
function regiestInfo(activityFlow)
{
	var url = '<s:url value="/jsres/activityQuery/joinActivity"/>?activityFlow='+activityFlow;
	jboxConfirm("确认报名参加此活动？" , function(){
		jboxPost(url , null , function(resp){
			toPage(${currentPage});
		} , null , true);
	});
}
function cannelRegiest(activityFlow,resultFlow)
{
	var url = '<s:url value="/jsres/activityQuery/cannelRegiest"/>?activityFlow='+activityFlow+"&resultFlow="+resultFlow;
	jboxConfirm("确认取消报名此活动？" , function(){
		jboxPost(url , null , function(resp){
			toPage(${currentPage});
		} , null , true);
	});
}
function signUrl(activityFlow){
	jboxOpen("<s:url value='/jsres/activityQuery/signUrl'/>?activityFlow=" + activityFlow,'二维码',450,500);
}

function showInfo(activityFlow,roleFlag){
	// 查看链接点击后变色
	var linkVisited = getCookie(sessionId + "_linkVisited");
	if(linkVisited) {
		var visitedArr = linkVisited.split(",");
		if(-1 == visitedArr.indexOf(activityFlow)) {
			$("." + activityFlow).css("color", "#771caa");
			linkVisited += "," + activityFlow;
			setCookie(sessionId + "_linkVisited", linkVisited, 1);
		}
	}else {
		$("." + activityFlow).css("color", "#771caa");
		setCookie(sessionId + "_linkVisited", activityFlow, 1);
	}
	jboxOpen("<s:url value='/jsres/activityQuery/activityDetail'/>?activityFlow=" + activityFlow+"&roleFlag="+roleFlag,'活动详情',800,500);
}

//清除cookie
function delCookie(name) {
	setCookie(name, "", -1);
}
//设置cookie
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*60*60*1000));
	var expires = "expires="+d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires+";path=/";
}
//获取cookie
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1);
		if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
	}
	return "";
}

function showInfo2(activityFlow,roleFlag,obj) {
	$(obj).attr("src", "<s:url value='/jsp/jsres/activity/img/show2.png'/>");
	jboxOpen("<s:url value='/jsres/activityQuery/activityDetail'/>?activityFlow=" + activityFlow+"&roleFlag="+roleFlag,'活动详情',800,500);
}
function effectiveInfo(activityFlow,roleFlag,isEffective,flag,_this){
 	var __this = _this
	debugger;
		var msg="";
		if(isEffective=="Y")
		{
			msg="确定认可此活动信息？";
			if(flag == "Y"){
				msg="当前活动已经认可，无需再次认可";
				jboxConfirm(msg);
				return;
			}
		}
		var url = "<s:url value='/jsres/activityQuery/effectiveActivity'/>?activityFlow=" + activityFlow+"&isEffective="+isEffective;
		jboxConfirm(msg, function(){
			jboxPost(url,null,function(resp){
				if(resp=="审核成功"){
					if(isEffective=="Y")
					{
						__this.style.color = "#459ae9"
					}else{
						__this.style.color = "#ccc"
					}
				}
				jboxEndLoading();
			},null,true);
			var page = $("#currentPage").val();
			if (null==page|| page=="" || page=="undefined"){
				page=1;
			}
			toPage(page);
		});

}

function effectiveInfo2(activityFlow,roleFlag,isEffective,flag,_this,currentPage){
	var __this = _this
	debugger;
	var msg="";
	isEffective="N";
	msg="确定不认可此活动信息？若确认，将不统计该活动信息";
	if(flag == "N"){
		msg="当前活动已经不认可，无需再次操作";
		jboxTip(msg);
		return;
	}
	jboxOpen("<s:url value='/jsres/activityQuery/effectiveActivityInfo'/>?activityFlow=" + activityFlow + "&isEffective="+isEffective  + "&currentPage="+currentPage,'不认可此活动',500,270,false);
}

function delActivity(activityFlow,currentPage,scanNum){

	let msg;

	if(scanNum==='0'){
		msg = "确认删除";
	}else {
		msg = "该活动已有签到人员，是否删除";
	}

	jboxConfirm(msg, function(){
		var url = "<s:url value='/jsres/activityQuery/delActivity'/>?activityFlow=" + activityFlow+"&currentPage="+currentPage;
		jboxGet(url, null, function(resp){
			if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
				toPage(1);
			}
		});
	});
}
function upload(activityFlow,isUpload){
    var url = "<s:url value='/jszy/base/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
    if(isUpload=="Y")
    {
        jboxOpen(url, "上传活动图片",700,550);
    }else{
        jboxOpen(url, "查看活动图片",700,550);
    }
}
function showEval(activityFlow,roleFlag){
	jboxOpen("<s:url value='/jsres/activityQuery/showEvalType'/>?activityFlow=" + activityFlow+"&roleFlag="+roleFlag,'活动评价详情',1000,600,false);
}
function showDocEval(resultFlow){
	jboxOpen("<s:url value='/jsres/activityQuery/showDocEval'/>?resultFlow=" + resultFlow,'评价详情',800,600);
}
function evalInfo(resultFlow){
	var url = "<s:url value='/jsres/activityQuery/checkEval'/>?resultFlow=" + resultFlow;
	jboxGet(url, null, function(resp){
		if(resp == ''){
			jboxOpen("<s:url value='/jsres/activityQuery/evalInfo'/>?resultFlow=" + resultFlow,'评价',800,600);
		}else{
			jboxTip(resp);
		}
	},null,false);
}
var indexActivityFlow="";
var indexFileFlow="";
var indexObj=null;
function uploadFile(activityFlow,fileFlow,obj)
{
	indexActivityFlow=activityFlow;
	indexFileFlow=fileFlow;
	indexObj=obj;

	$("#file").click();
}
/**
 * 上传头像
 */
function uploadImage(file){
	var fileName=$(file).val();
	if(fileName=="")
	{
		jboxTip("请选择文件！");
		return ;
	}
	if(indexActivityFlow=="")
	{
		jboxTip("请选择教学活动！");
		return ;
	}
	if(indexObj==null)
	{
		jboxTip("请选择教学活动！");
		return ;
	}
	$.ajaxFileUpload({
		url:"<s:url value='/jsres/activityQuery/saveActivityFile'/>?activityFlow="+indexActivityFlow+"&fileFlow="+indexFileFlow,
		secureuri:false,
		fileElementId:'file',
		dataType: 'json',
		success: function (resp, status){
			var json=eval("("+resp+")");
			if(json.code!="1"){
				jboxTip(json.msg);
			}else{
				var fileFlow=json.fileFlow;
				var fileName=json.fileName;
				console.log(fileName);
				var url="<s:url value='/jsres/activityQuery/downFile'/>?fileFlow="+fileFlow;
				var html='<a href="'+url+'"><img style="cursor:pointer" title="'+fileName+'" src="<s:url value='/jsp/jsres/images/manual.png'/>"></a>'
				+'<a href="javascript:void(0);" onclick="uploadFile(\''+indexActivityFlow+'\',\''+fileFlow+'\',this);">重传</a>';
				$(indexObj).parent().html(html);
				jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
				fileName="";
				indexObj=null;
			}
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
			$("#file").val("");
			indexActivityFlow="";
			indexFileFlow="";
		}
	});
}

function searchFile(activityFlow,role) {
	jboxOpen("<s:url value='/jsres/activityQuery/editActivityFile'/>?activityFlow=" + activityFlow+"&role="+role,'编辑附件',800,400);
}

function lookSearchFile(activityFlow,role) {
	jboxOpen("<s:url value='/jsres/activityQuery/lookSearchFile'/>?activityFlow=" + activityFlow+"&role="+role,'查看附件',800,400);
}

	function exportExcel2(activityFlow) {
		var url = "<s:url value='/jsres/activityQuery/IndicatorExport'/>?activityFlow="+activityFlow;
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}

	function orderByCol(col,fall,disId,obj) {
		$("#orderByClo").val(col);
		$("#orderByFall").val(fall);
		$(obj).css("display","none");
		document.getElementById(disId).style.display = "";
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/activityQuery/list'/>?roleFlag=${param.roleFlag}",$("#searchForm").serialize(),false);
	}
</script>

<input type="file" id="file" name="file" onchange="uploadImage();" style="display: none"/>
<style type="text/css">
	.bg{
		width: 60px;
		height: 16px;
		background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
		margin-left: 15px;
	}
	.img{
		width: 20px;
		height: 20px;
		margin-left: 5px;
	}
	.over{
		height:16px;
		background:url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
	}
	.grid>tbody>tr td:last-child img{
		vertical-align: inherit;
	}
	.gray{
		color: #ccc;
	}
	.gray:hover{
		text-indent: initial;
	}
</style>
    <div class="div_table">
        <table border="0" cellpadding="0" cellspacing="0"  class="grid" style="word-break: break-all;">
        	<%--<colgroup>--%>
				<%--<col width="10%" />--%>
				<%--<col width="10%" />--%>
				<%--<col width="10%" />--%>
				<%--<col width="10%" />--%>
				<%--<col width="10%" />--%>
				<%--<col width="20%" />--%>
				<%--<c:if test="${param.roleFlag ne 'doctor'}">--%>
					<%--<col width="10%" />--%>
				<%--</c:if>--%>
				<%--<col width="5%" />--%>
				<%--<col width="5%" />--%>
				<%--<col width="15%" />--%>
				<%--<col width="15%" />--%>
				<%--<col width="15%" />--%>
			<%--</colgroup>--%>
            <tr>
                <th style="width:88px">
					<div>
						<img id="orderByNameup" src="<s:url value='/jsp/jsres/images/up.png'/>" onclick="orderByCol('activityName','','orderByNamedown',this);"/>
						<img id="orderByNamedown" src="<s:url value='/jsp/jsres/images/down.png'/>" onclick="orderByCol('activityName','DESC','orderByNameup',this);" style="display: none"  />
					</div>
					活动名称

				</th>
                <th style="width:56px">
					<div>
						<img id="orderByTypeup" src="<s:url value='/jsp/jsres/images/up.png'/>" onclick="orderByCol('activityType','','orderByTypedown',this);"/>
						<img id="orderByTypedown" src="<s:url value='/jsp/jsres/images/down.png'/>" onclick="orderByCol('activityType','DESC','orderByTypeup',this);" style="display: none"  />
					</div>
					活动形式</th>
                <th>活动地点</th>
                <th style="width:45px">
					<div>
						<img id="orderByUserNameup" src="<s:url value='/jsp/jsres/images/up.png'/>" onclick="orderByCol('userName','','orderByUserNamedown',this);"/>
						<img id="orderByUserNamedown" src="<s:url value='/jsp/jsres/images/down.png'/>" onclick="orderByCol('userName','DESC','orderByUserNameup',this);" style="display: none"  />
					</div>
					主讲人</th>
                <th>
					<div>
						<img id="orderByDeptup" src="<s:url value='/jsp/jsres/images/up.png'/>" onclick="orderByCol('deptName','','orderByDeptdown',this);"/>
						<img id="orderByDeptdown" src="<s:url value='/jsp/jsres/images/down.png'/>" onclick="orderByCol('deptName','DESC','orderByDeptup',this);" style="display: none"  />
					</div>
					所在科室</th>
                <th style="${param.roleFlag eq 'teach' or param.roleFlag eq 'head'?"width: 120px;":""}">活动时间</th>
				<c:if test="${param.roleFlag ne 'doctor'}">
     	           <th>评价</th>
				</c:if>
                <th>签到人数</th>
                <c:if test="${param.roleFlag ne 'doctor'}">
				   <th style="width:64px">活动状态</th>
				</c:if>
                <th>附件</th>
                <th style="text-align: right;">操作&活动图片</th>
				<c:if test="${param.roleFlag eq 'teach' or param.roleFlag eq 'head' or param.roleFlag eq 'secretary'}">
					<th <%--style="display: ${param.roleFlag eq 'teach' or param.roleFlag eq 'head'?"":"none"} "--%>>是否认可</th>
				</c:if>
				<th>
					导出评价
				</th>

            </tr>
				<c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_teach" var="keyinfo"/>
				<c:set value="${pdfn:jsresPowerCfgMap(keyinfo)}" var="keyinfoteach"/>
				<c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_teach_day" var="teachKey"/>
				<c:set value="${pdfn:jsresPowerCfgMap(teachKey)}" var="teachday"/>

				<c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_kzr" var="infokzr"/>
				<c:set value="${pdfn:jsresPowerCfgMap(infokzr)}" var="keyinfokzr"/>
				<c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_kzr_day" var="kzrKey"/>
				<c:set value="${pdfn:jsresPowerCfgMap(kzrKey)}" var="kzrday"/>


				<c:forEach items="${list}" var="b" varStatus="s">
	             <tr>
	                <td title="${b.activityName}">${pdfn:cutString(b.activityName,12,true,3)}</td>
	                <td>${b.activityTypeName}</td>
	                <td title="${b.activityAddress}">${pdfn:cutString(b.activityAddress,6,true,3)}</td>
	                <td>${b.userName}</td>
	                <td>${b.deptName}</td>
	                <td>${b.startTime}<br/>${b.endTime}</td>
					<c:if test="${param.roleFlag ne 'doctor'}">
						<td <c:if test="${param.roleFlag ne 'doctor'}">onclick="showEval('${b.activityFlow}','${param.roleFlag}');" style="cursor: pointer;" </c:if>>
							<div class="bg">
								<div class="over" style="width:${12*b.evalScore}px"></div>
							</div>
						</td>
					</c:if>
                    <%--签到人数--%>
                     <td>
						 <font style="font-size: 17px">${b.scanNum}</font>
					 </td>
					<%--教学活动审核状态--%>
					 <c:if test="${param.roleFlag ne 'doctor'}">
						 <c:if test="${b.activityStatus eq 'audit'}">
						     <c:if test="${b.audit eq 'Y'}">
							     <td style="cursor:pointer;color: #459ae9;" onclick="editActivity('${b.activityFlow}','${param.roleFlag}','audit');">待审核</td>
							 </c:if>
							 <c:if test="${b.audit ne 'Y'}">
								 <td>待审核</td>
						     </c:if>
						 </c:if>
						 <c:if test="${b.activityStatus eq 'pass'}">
							 <td>已通过</td>
						 </c:if>
						 <c:if test="${b.activityStatus eq 'unpass'}">
							 <td>未通过</td>
						 </c:if>
						 <c:if test="${b.activityStatus eq 'over'}">
							 <td>已过期</td>
						 </c:if>
					 </c:if>
                     <%-- 附件 --%>
	                <td>
						<c:if test="${(param.roleFlag eq 'teach' or param.roleFlag eq 'head') and sessionScope.currUser.userFlow eq b.speakerFlow
										or param.roleFlag eq 'secretary'}">
							<c:if test="${not empty b.fileFlows}">
<%--								<a href="<s:url value='/jsres/activityQuery/downFile'/>?fileFlow=${b.fileFlow}"><img style="cursor:pointer" title="${b.fileName}" src="<s:url value='/jsp/jsres/images/manual.png'/>"></a>--%>
<%--								<a  href="javascript:void(0);" onclick="uploadFile('${b.activityFlow}','${b.fileFlow}',this);">重传</a>--%>
								<a  href="javascript:void(0);" onclick="searchFile('${b.activityFlow}','${param.roleFlag}');">查看</a>
							</c:if>
							<c:if test="${empty b.fileFlows}">
<%--								<a  href="javascript:void(0);" onclick="uploadFile('${b.activityFlow}','${b.fileFlow}',this);">上传</a>--%>
								<a  href="javascript:void(0);" onclick="searchFile('${b.activityFlow}','${param.roleFlag}');">上传</a>
							</c:if>
						</c:if>
						<c:if test="${!(((param.roleFlag eq 'teach'or param.roleFlag eq 'head') and sessionScope.currUser.userFlow eq b.speakerFlow)
											or param.roleFlag eq 'secretary')}">
							<c:if test="${empty b.fileFlows}">
								无
							</c:if>
							<c:if test="${not empty b.fileFlows}">
<%--								<a href="<s:url value='/jsres/activityQuery/downFile'/>?fileFlow=${b.fileFlow}">--%>
<%--									<img style="cursor:pointer" title="${b.fileName}" src="<s:url value='/jsp/jsres/images/manual.png'/>"></a>--%>
								<a  href="javascript:void(0);" onclick="lookSearchFile('${b.activityFlow}','${param.roleFlag}');">查看</a>
							</c:if>
						</c:if>
					</td>
                     <%-- 操作 --%>
	          		<td  style="text-align: left; white-space: nowrap ;" >
						<c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_teacher" var="key1"/>
						<c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_secretary" var="key2"/>
						<c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_head" var="key3"/>
						<c:if test="${param.roleFlag eq 'teach' or param.roleFlag eq 'head' or param.roleFlag eq 'secretary'}">

							<c:set value="${pdfn:dayCompore(keyinfoteach,b.startTime,teachday)}" var="keyinfo1"/>
							<c:set value="${pdfn:dayCompore(keyinfokzr,b.startTime,kzrday)}" var="keyinfo2"/>

							<c:if test="${keyinfo1 eq 'Y' or keyinfo2 eq 'Y'}">
								<c:set value="1" var="countNum"/>
								<img class="img" style="cursor:pointer"  onclick="editActivity('${b.activityFlow}','${param.roleFlag}','','${b.scanNum}','${currentPage}');" title="编辑" src="<s:url value='/jsp/jsres/activity/img/edit.png'/>"></a>
							</c:if>
							<c:if test="${(fn:length(resultMap[b.activityFlow])+0)<=0 and countNum ne '1'}">
								<img class="img" style="cursor:pointer"  onclick="editActivity('${b.activityFlow}','${param.roleFlag}','','${b.scanNum}','${currentPage}');" title="编辑" src="<s:url value='/jsp/jsres/activity/img/edit.png'/>"></a>
							</c:if>

							<c:if test="${(fn:length(resultMap[b.activityFlow])+0)<=0 }">
								<c:if test="${(param.roleFlag eq 'teach' && pdfn:jsresPowerCfgMap(key1) eq 'Y') || (param.roleFlag eq 'head' && pdfn:jsresPowerCfgMap(key3) eq 'Y') || (param.roleFlag eq 'secretary' && pdfn:jsresPowerCfgMap(key2) eq 'Y')}">
									<img class="img" style="cursor:pointer" onclick="delActivity('${b.activityFlow}','${currentPage}')" title="删除" src="<s:url value='/jsp/jsres/activity/img/del.png'/>"></a>
								</c:if>
							</c:if>
							<img class="img" style="cursor:pointer" onclick="signUrl('${b.activityFlow}')" title="二维码" src="<s:url value='/jsp/jsres/activity/img/code.png'/>"></a>
						</c:if>
						<a style="cursor:pointer" onclick="delActivity('${b.activityFlow}','${currentPage}','${b.scanNum}')" >删除</a>&nbsp
						<c:if test="${param.roleFlag eq 'local' and b.IS_EFFECTIVE eq 'Y'}">
						<a style="cursor:pointer" class='${b.activityFlow}' onclick="showInfo('${b.activityFlow}','${param.roleFlag}')">查看</a>&nbsp
						<font style="color: red" >认可</font>
								<a style="cursor:pointer"  onclick="effectiveInfo2('${b.activityFlow}','${param.roleFlag}','N','${b.IS_EFFECTIVE}',this,'${currentPage}')" id="brk" class="brk" style="display: ${activity.isEffective eq 'N'?'none':''}" >不认可</a>&nbsp
						</c:if>
						<c:if test="${param.roleFlag eq 'local' and b.IS_EFFECTIVE eq 'N'}">
							<a style="cursor:pointer" class='${b.activityFlow}' onclick="showInfo('${b.activityFlow}','${param.roleFlag}')">查看</a>
							<a style="cursor:pointer"  onclick="effectiveInfo('${b.activityFlow}','${param.roleFlag}','Y','${b.IS_EFFECTIVE}',this)" id="rk"  style="display: ${activity.isEffective eq 'Y'?'none':''}"  title="认可该活动" >认可</a>&nbsp<font title="${b.reasonForDisagreement}" style="color: red">不认可</font>
						</c:if>
						<c:if test="${(param.roleFlag eq 'teach' or param.roleFlag eq 'head' or param.roleFlag eq 'secretary') and b.startTime <= nowDate}">
							<c:if test="${empty b.isLook || b.isLook ne 'Y'}">
								<img class="img" style="cursor:pointer" onclick="showInfo2('${b.activityFlow}','${param.roleFlag}',this)" title="详情" src="<s:url value='/jsp/jsres/activity/img/show.png'/>"></a>
							</c:if>
							<c:if test="${not empty b.isLook and b.isLook eq 'Y'}">
								<img class="img" style="cursor:pointer" onclick="showInfo2('${b.activityFlow}','${param.roleFlag}',this)" title="详情" src="<s:url value='/jsp/jsres/activity/img/show2.png'/>"></a>
							</c:if>
						</c:if>
						<c:if test="${param.roleFlag eq 'doctor'}">
							<c:if test="${param.isNew eq 'Y'}">
								<c:if test="${b.isRegiest ne 'Y' and b.startTime >= nowDate}">
									<img class="img" style="cursor:pointer" onclick="regiestInfo('${b.activityFlow}')" title="报名" src="<s:url value='/jsp/jsres/activity/img/regiest.png'/>"></a>
								</c:if>
							</c:if>
							<c:if test="${not empty b.resultFlow and b.isRegiest eq 'Y' and b.isScan ne 'Y'}">
								<img class="img" style="cursor:pointer" onclick="cannelRegiest('${b.activityFlow}','${b.resultFlow}')" title="取消" src="<s:url value='/jsp/jsres/activity/img/cannel.png'/>"></a>
							</c:if>
							<c:if test="${param.isEval eq 'Y' and b.isScan eq 'Y' and b.isScan2 eq 'Y'}">
								<c:if test="${empty b.evalScore2}">
									<img class="img" style="cursor:pointer" onclick="evalInfo('${b.resultFlow}')" title="评价" src="<s:url value='/jsp/jsres/activity/img/eval.png'/>"></a>
								</c:if>
								<c:if test="${not empty b.evalScore2}">
									<img class="img" style="cursor:pointer" onclick="showDocEval('${b.resultFlow}')" title="查看评价" src="<s:url value='/jsp/jsres/activity/img/showEval.png'/>"></a>
								</c:if>
							</c:if>
							<c:if test="${ b.HaveImg eq 'Y'}">
								<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','N')" title="查看活动图片" src="<s:url value='/jsp/jsres/activity/img/showUpload.png'/>"></a>
							</c:if>
						</c:if>
						<c:if test="${param.roleFlag eq 'teach' or param.roleFlag eq 'head' or param.roleFlag eq 'secretary'}">
							<c:if test="${sessionScope.currUser.userFlow eq b.speakerFlow or param.roleFlag eq 'secretary'}">
								<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','Y')" title="上传活动图片" src="<s:url value='/jsp/jsres/activity/img/upload.png'/>"></a>
							</c:if>
							<c:if test="${sessionScope.currUser.userFlow ne b.speakerFlow and param.roleFlag ne 'secretary'}">
								<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','N')" title="查看活动图片" src="<s:url value='/jsp/jsres/activity/img/showUpload.png'/>"></a>
							</c:if>
						</c:if>
						<c:if test="${param.roleFlag eq 'local' and b.HaveImg eq 'Y'}">
							<img class="img" style="cursor:pointer" onclick="upload('${b.activityFlow}','N')" title="查看活动图片" src="<s:url value='/jsp/jsres/activity/img/showUpload.png'/>"></a>
						</c:if>
					</td>

						 <!--yuh-->
						 <c:if test="${param.roleFlag eq 'teach' or param.roleFlag eq 'head' or param.roleFlag eq 'secretary'}">
					         <td>
							 <c:if test="${ b.IS_EFFECTIVE eq 'N'}">
								 <font title="${b.reasonForDisagreement}">不认可</font>
							 </c:if>
							 <c:if test="${ b.IS_EFFECTIVE eq 'Y'}">
								 <font>认可</font>
							 </c:if>
					        </td>
						 </c:if>
					<td>
						<input class="btn_green" type="button" style="width: 68px;margin-top: 23px" value="导出" onclick="exportExcel2('${b.activityFlow}');"/>&nbsp;
					</td>
	            </tr>
            </c:forEach>
            <c:if test="${empty list}">
				<tr>
					<c:if test="${param.roleFlag ne 'doctor'}">
						<td colspan="99" >无记录！</td>
					</c:if>
					<c:if test="${param.roleFlag eq 'doctor'}">
						<td colspan="99" >无记录！</td>
					</c:if>
				</tr>
      	  	</c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
