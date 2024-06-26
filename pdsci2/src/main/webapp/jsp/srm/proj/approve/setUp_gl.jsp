<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ztree" value="true"/>
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
<%--<link rel="stylesheet" type="text/css" href="<s:url value='/css/validationEngine.jquery.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>--%>
<%--<script type="text/javascript" src="<s:url value='/js/jquery.validationEngine-zh_CN.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<%--<script type="text/javascript" src="<s:url value='/js/jquery.validationEngine${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<script type="text/javascript">
$(function(){
	$("#evalSetDetailA").toggle(
			  function () {
			    $("#evalSetDetail").show();
			  },
			  function () {
			    $("#evalSetDetail").hide();
			  }
			);
	$("#projForm").validationEngine("attach",{
		binded:false,
		promptPosition : "bottomLeft",
		scroll:true,
		autoPositionUpdate: true,
		autoHidePrompt:true,
		maxErrorsPerField:1,
		showOneMessage : true
		});
});
	//立项编号
	function checkProjNo(obj){
		var projNo = obj.value;
		if(projNo == ''){
			return;
		}
		var url = "<s:url value = '/srm/proj/approve/checkProjNo?projNo='/>"+ projNo;
		jboxStartLoading();
		jboxGet(url ,null , function(data){
			if(data == "${GlobalConstant.FLAG_N}"){
		    	 $("#projNoMessage").text("项目编号已存在！");
                $('#projNoMessage').css('color','red');
		    	 $("input[name=isSave]").val("${GlobalConstant.FLAG_N}");
			}else{
		    	 $("#projNoMessage").text("可以使用此编号！");
                $('#projNoMessage').css('color','green');
		    	 $("input[name=isSave]").val("${GlobalConstant.FLAG_Y}");
			}
		}, null , false);
	}

	function saveApprove(result){
		$("#projForm").validationEngine("hideAll");
		var tip = "是否确认";
		if("${GlobalConstant.FLAG_Y}"==result){
			tip+="立项？";
			$('#projNo').addClass("validate[required,maxSize[50]]");
			$('#sug').removeClass("validate[required,maxSize[200]]");
			var form = $('#projForm');
			if(false==form.validationEngine("validate")){
				return ;
			}
			if(false == $("#authorForm").validationEngine("validate")){
				return ;
			}
		}else if("${GlobalConstant.FLAG_N}"==result){
			tip+='不立项？';
			$('#projNo').removeClass("validate[required,maxSize[50]]");
			$('#sug').addClass("validate[required,maxSize[200]]");
			$("input[name=isSave]").val("${GlobalConstant.FLAG_Y}");
		}

		var isSave = $("input[name=isSave]").val();
		if(isSave == "${GlobalConstant.FLAG_N}"){
			jboxTip("项目编号已存在！");
			return false;
		}
		jboxConfirm(tip , function(){
			var url ="<s:url value='/srm/proj/approve/saveSetUp'/>";
			var trs = $('#appendTbody').children();
			var datas = [];
			$.each(trs, function (i, n) {
				var projRanking = $(n).find("input[name='projRanking']").val();
				var authorName = $(n).find("input[name='authorName']").val();
				var userFlow =  $(n).find("input[name='userFlow']").val();
				var deptFlow = $(n).find("input[name='deptFlow']").val();
				var deptName = $(n).find("input[name='deptName']").val();
				var scoreFlow = $(n).find("input[name='scoreFlow']").val();
				var scoreName = $(n).find("input[name='scoreName']").val();

				var data = {
					"projRanking":projRanking,
					"authorName": authorName,
					"userFlow": userFlow,
					"deptFlow": deptFlow,
					"deptName": deptName,
					"scoreName": scoreName,
					"scoreFlow": scoreFlow
				};
				datas.push(data);
			});
			$("#jsondata").val(JSON.stringify(datas));
			$('#result').val(result);
			jboxStartLoading();
			jboxPost(url ,  $('#projForm').serialize() , function(resp){
				if(resp=='${GlobalConstant.FLAG_Y}'){
					jboxTip("操作成功");
					window.parent.frames['mainIframe'].window.searchProj();
					jboxClose();
				}else if(resp=='${GlobalConstant.FLAG_N}'){
					jboxTip("课题编号已存在");
				}

			} , null , false);
		});

	}

	function doClose() {
		jboxClose();
	}
function getAmountFund(){
	var goveFund = parseFloat(parseFloat($("#goveFund").val()).toFixed("2"));
	var orgFund = parseFloat(parseFloat($("#orgFund").val()).toFixed("2"));
	if(isNaN(goveFund)){
		goveFund=0;
		$("#goveFund").val("");
	}
	if(isNaN(orgFund)){
		orgFund=0;
		$("#orgFund").val("");
	}
	var amount = goveFund+orgFund;

        if(parseFloat(amount)){
            $("#amountFund").val(parseFloat(amount.toFixed("2")));
        }else{
			$("#amountFund").val("0");
        }
}
//function ckeckNum(obj){
//    var maxLimit = parseFloat($(obj).val());
////    var re =/^[0-9]+.?[0-9]*$/;//正数正则
////    var reg = /^([0-9]|[0-9]+.?[0-9]{1,2})$/;
//    if(maxLimit){
//		$(obj).val(parseFloat(maxLimit.toFixed("2")));
//		getAmountFund();
//    }
//}

var setting = {
	view: {
		dblClickExpand: false,
		showIcon: false,
		showTitle: true,
		selectedMulti: false
	},
	data: {
		simpleData: {
			enable: true
		},
		key: {
			title:"t"
		}
	},
	callback: {
		beforeClick: beforeClick,
		onClick: onClick
	}
};

function beforeClick(treeId, treeNode) {
	var check = (treeNode.id != 0);
	if (!check) {
		jboxTip('不能选择根节点');
		return check;
	}
	if (treeNode.isParent) {
		jboxTip("该项不允许选择，请选择子项...");
		return false;
	}
	if((treeNode.id).substring(0,4) && (treeNode.id).substring(0,4) == 'type'){
		jboxTip("该项不允许选择，请尝试选择其它项... ");
		return false;
	}
}

function onClick(e, treeId, treeNode) {
	/*if (treeNode.isParent) {
	 alert("这个 是父节点 ， 去点击子节点吧... ");
	 return false;
	 }*/

	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
	id = "";
	nodes.sort(function compare(a, b) {
		return a.id - b.id;
	});
	for (var i = 0, l = nodes.length; i < l; i++) {
		v += nodes[i].name + ",";
		id += nodes[i].id + ",";
	}

	if (v.length > 0) v = v.substring(0, v.length - 1);
	if (id.length > 0) id = id.substring(0, id.length - 1);

//        var cityObj = $("#scoreName");
//        $("#scoreFlow").val(id);
//        cityObj.attr("value", v);
	$(flowObj).val(id);
	$(nameObj).val(v);
}
var flowObj;
var nameObj;
function showMenu(obj) {
	nameObj = $(obj);
	flowObj = $(obj).next("input[name='scoreFlow']");
	var nameOffset = $(obj).offset();
	$("#menuContent").css({
		left: nameOffset.left + "px",
		top: nameOffset.top + nameObj.outerHeight() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

$(document).ready(function () {
	var url = "<s:url value='/srm/ach/score/getScoreDataJson'/>";
	jboxPostJson(url, null, function (data) {
		//console.log(data);
		if (data) {
			zNodes = $.parseJSON(data);
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	}, null, false);
});

	function addAuthor(){

		var tr = $("#addAuthor tr:eq(0)").clone();
		var trlength = $('#appendTbody').find("tr").length;
		$(tr).find("input[name='deptFlow']").attr("id","deptFlow_"+trlength);
		$(tr).find("input[name='deptName']").attr("id","deptName_"+trlength);
		$(tr).find("input[name='userFlow']").attr("id","userFlow_"+trlength);
		$(tr).find("input[name='authorName']").attr("id","authorName_"+trlength);
		$('#appendTbody').append(tr);
		addDept(trlength);
	}
function delAuthorTr(obj) {
	jboxConfirm("确认删除？", function () {
		obj.parentNode.parentNode.remove();
	});
}
function loadUsers(obj) {
	var tr = $(obj).parents("tr");
	var deptFlow = $(tr).find("input[name='deptFlow']").val();
	var userDatas = [];
	jboxPost('<s:url value="/srm/proj/add/getUsersByDept"/>', {deptFlow: deptFlow}, function (resp) {

		for (var index in resp) {
			var user = resp[index];

			var u = {};
			u.id = user.userFlow;
			u.text = user.userName;
			userDatas.push(u);
		}
	}, null, false);
	var userFlow = $(tr).find("input[name='userFlow']").prop("id");
	var authorName = $(tr).find("input[name='authorName']").prop("id");
	$("#"+userFlow).val('');
	$("#"+authorName).val('');
	var itemSelectFuntion = function () {
		$("#"+userFlow).val(this.id);
	};
	$.selectSuggest(authorName, userDatas, itemSelectFuntion, userFlow, true);
}
	/*function changeUser(obj){
		$(obj).next().val($(obj).find("option:selected").text());
	}*/

var deptDatas = [];
$(document).ready(function () {

	<c:forEach items="${depts}" var="dept">
			var d = {};
			d.id = '${dept.deptFlow}';
			d.text = '${dept.deptName}';
			deptDatas.push(d);
	</c:forEach>
	/*var itemSelectFuntion = function () {
		$("#deptFlow").val(this.id);
	};
	$.selectSuggest('trainDept', deptDatas, itemSelectFuntion, "deptFlow", true);
	var trlength = $('#appendTbody').find("tr").length;
	for(var i =0;i<trlength;i++){
		var itemSelectFuntion2 = function () {
			$("#deptFlow_"+i).val(this.id);
		};
		$.selectSuggest('deptName_'+ i, deptDatas, itemSelectFuntion2, "deptFlow_"+i, true);
	}*/
});
function addDept(index){
	var itemSelectFuntion = function () {
		$("#deptFlow_"+index).val(this.id);
		loadUsers($("#deptFlow_"+index));
	};
	$.selectSuggest('deptName_'+index, deptDatas, itemSelectFuntion, "deptFlow_"+index, true);
}
</script>

</head>
<body>
	<div class="mainright">
    	<div class="content">
        	<div class="title1 clearfix">
        		<div>
        		<input type="hidden" name="isSave" value="${GlobalConstant.FLAG_N}"/>
        		<table>
        		<tr height="30px">
        			<td style="font-weight: bold;">项目名称：</td><td colspan="3"><a target="_blank" style="color:blue;" href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>">${proj.projName}</a></td>
        		</tr>
        		<tr  height="30px">
        			<%--<td style="font-weight: bold;">项目类型：</td>--%>
        			<%--<td width="200px">${proj.projTypeName}</td>--%>
					<td style="font-weight: bold;">项目来源：</td>
					<td width="200px">一级来源&#12288;${proj.projDeclarer}<br />二级来源&#12288;${proj.projSecondSourceName}</td>
        			<td style="font-weight: bold;">起止时间：</td>
        			<td>${proj.projStartTime}~${proj.projEndTime}</td>
        		</tr>
        		<tr  height="30px">
        			<td style="font-weight: bold;">承担单位：</td>
        			<td>${proj.applyOrgName}</td>
        			<td style="font-weight: bold;">负责人：</td>
        			<td>${proj.applyUserName}</td>
        		</tr>
              </table>
              </div>

              <c:if test="${not empty evalSet}">
              <div>
              	<%--<c:set var='agreeCount' value='0'></c:set>
					<c:set var='noAgreeCount' value='0'></c:set>
					<c:set var='noEvalCount' value='0'></c:set>
                  <c:set var='firstAgreeCount' value='0'></c:set>
					<c:forEach var="expertProj" items="${expertProjList}">
						<c:if test="${empty expertProj.scoreResultId }">
							<c:set var='noEvalCount' value='${noEvalCount+1}'></c:set>
						</c:if>
                        <c:if test='${expertProj.scoreResultId eq expertScoreResultEnumFirstAgree.id}'>
                            <c:set var='firstAgreeCount' value='${firstAgreeCount+1}'></c:set>
                        </c:if>
						<c:if test='${expertProj.scoreResultId eq expertScoreResultEnumAgree.id}'>
							<c:set var='agreeCount' value='${agreeCount+1}'></c:set>
						</c:if>
						<c:if test='${expertProj.scoreResultId eq expertScoreResultEnumNotAgree.id}'>
							<c:set var='noAgreeCount' value='${noAgreeCount+1}'></c:set>
						</c:if>
					</c:forEach>--%>
              	<table class="xllist">
              		<tr><th style="text-align: left;"> &#12288;立项评审信息：
              		${evalSet.evaluationWayName}&#12288;评审专家数：${expertProjList.size()}&#12288;
              		<c:choose>
              			<c:when test="${evalSet.evaluationWayId == evaluationWayEnumNetWorkWay.id }">
                            <%--&#12288;资助A：${firstAgreeCount}&#12288;备选B：${agreeCount}&#12288;不资助C：${noAgreeCount}&#12288;未评：${noEvalCount}&#12288;--%>
							<c:forEach items="${dictTypeEnumExpertScoreResultList}" var="dict">
								&#12288;${dict.dictName}：${empty expertMap[dict.dictId] ? 0 : expertMap[dict.dictId]}
							</c:forEach>
							&#12288;未评：${expertMap['noEvalCount']}
              			</c:when>
              			<c:otherwise>
              				会评日期：${meeting.meetingDate }
              			</c:otherwise>
              		</c:choose>
              		&#12288;&#12288;[<a href='javascript:void(0);' id="evalSetDetailA">查看详细</a>]
              		</th></tr>
              	</table>
	        	  <div id='evalSetDetail' style="display: none;margin-bottom: 10px">
	        	  	<c:if test="${evalSet.evaluationWayId == evaluationWayEnumNetWorkWay.id }">
		        	  	<table class="xllist" >
							<thead>
								<tr>
									<th width="10%">姓名</th>
									<th width="10%">结果</th>
									<th width="10%">总分</th>
									<th width="70%">意见</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="expertProj" items="${expertProjList}">
									<tr>
										<td>${expertProj.userExt.userName}</td>
										<td><c:if test='${ empty expertProj.scoreResultName}'>未评审</c:if><c:if test='${not empty expertProj.scoreResultName}'>${expertProj.scoreResultName}</c:if></td>
										<td>${expertProj.scoreTotal}</td>
										<td>${expertProj.expertOpinion}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
					<c:if test="${evalSet.evaluationWayId == evaluationWayEnumMeetingWay.id }">
						<table class="xllist" >
							<tr><td style="text-align: left;padding-left: 10px;">
							<b style="font-size: 15px;font-weight: bold;">会评意见：</b>${evalSet.evalOpinion}
							<td></tr>
						 </table>
					</c:if>
	        	  </div>
        	  </div>
        	  </c:if>
				<form id="authorForm">
				<div>
					<table class="basic" style="width: 100%;margin-top: 10px;">
						<tr>
							<th colspan="5" style="text-align: left;">
								主要研究开发人员
								<a style="float: right" href="javascript:void(0)"><img
										src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
										onclick="addAuthor();" title="添加"/>
								</a>
							</th>
						</tr>
						<tr>
							<th style="text-align: center" width="10%">排名</th>
							<th style="width: 25%;text-align: center">科室</th>
							<th style="width: 25%;text-align: center">姓名</th>
							<th style="width: 25%;text-align: center">积分项</th>
							<th style="width: 10%;text-align: center">操作</th>
						</tr>
						<tbody id="appendTbody">

						</tbody>

					</table>
				</div>
				</form>
        	  <form  method="post" id="projForm" onsubmit="return false;">
	        	  <input type="hidden" name="projFlow" value="${proj.projFlow}"/>
	        	  <input type="hidden" name="result" id="result"/>
				  <input type="hidden" name="jsondata" id="jsondata">

	        	  <table class='basic' width="100%" style="margin-top:10px;">
	        	  	<tr>
							<th colspan="4" style="text-align: left;">
	                            &#12288;<span style="font-weight: bold">项目立项意见：<font color="red"> 项目立项意见(最多200个字)</font></span>
	                        </th>
						</tr>
						<tr>
							<th>课题编号：</th>
							<td colspan="3">
								<input id="projNo" name="projNo" class="validate[required]" value="${proj.projNo}" style="width: 230px" onblur="checkProjNo(this)"/>
								<b id="projNoMessage" style="margin-left: 0px; text-decoration: none;"></b>
							</td>

						</tr>

					  <tr>
						  <th>伦理审查：</th>
						  <td>
							  <input type="radio" class="validate[required]" name="isEthicalFlag" value="Y" id="isEthicalFlag_Y" /><label for="isEthicalFlag_Y">&#12288;是</label>&#12288;&#12288;
							  <input type="radio" class="validate[required]" name="isEthicalFlag" value="N" id="isEthicalFlag_N" /><label for="isEthicalFlag_N">&#12288;否</label>
						  </td>
						  <th>计划类别<%--<span style="color: red;">*</span>--%>：</th>
						  <td>
							  <select name="planTypeId" class="validate[required]" style="width: 80%;">
								  <option value="" >请选择</option>
								  <c:forEach items="${dictTypeEnumPlanCategoryList}" var="dict" varStatus="status">
									  <option  value="${dict.dictId}"
											   <c:if test="${proj.planTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
								  </c:forEach>
							  </select>
						  </td>
					  </tr>

					  <tr>
						  <th width="15%">经费方案：</th>
						  <td width="40%">
							  <select name="schemeFlow" class="validate[required]" style="width: 230px">
								  <option></option>
								  <c:forEach items="${schemeList}" var="scheme">
									  <option value="${scheme.schemeFlow}">${scheme.schemeName}</option>
								  </c:forEach>
							  </select>

						  </td>
						  <th width="10%">课题账号：</th>
						  <td width="35%">
							  <input type="text" name="accountNo" class="validate[required,custom[onlyLetterNumber],maxSize[30]]" style="width: 230px;" />
						  </td>
					  </tr>
					  	<tr>
							<th>经费信息(万元)：</th>

							<td>
								下拨：<input name="goveFund" id="goveFund" onblur="getAmountFund()" class="validate[required,maxSize[10],custom[number,,min[0]]]" style="width: 100px"/>
								配套：<input name="orgFund" id="orgFund" onblur="getAmountFund()" class="validate[required,maxSize[10],custom[number,,min[0]]]" style="width: 100px"/>
							</td>
							<th>
								总经费：
							</th>
							<td>
								<input id="amountFund" name="amountFund" readonly="readonly" class="validate[required,maxSize[10]]"/>
							</td>
						</tr>
						<tr>
	                        <th>
								评审意见：
	                        </th>
	                        <td colspan="3" >
	                            <textarea id="sug" name="sug" class="validate[maxSize[200]]" rows="5" cols="20" style="margin-top:5px;margin-bottom:5px;width:95%; resize :none" placeholder="请填写意见..."></textarea>
	                        </td>
	                    </tr>
	        	  </table>
	        	  <c:if test='${setupFormPath!=null}'>
	        	      <jsp:include page="/jsp/${setupFormPath}.jsp"></jsp:include>
	        	  </c:if>
        	  </form>
        	  <div style='text-align: center;margin-top: 20px;'>
        	  		<input onclick="saveApprove('${GlobalConstant.FLAG_Y}');"  class="search" type="button" value="立&#12288;项"/>
        	  		<input onclick="saveApprove('${GlobalConstant.FLAG_N}');"  class="search" type="button" value="不&nbsp;立&nbsp;项"/>
               		<input onclick="doClose();" class="search" type="button"  value="关&#12288;闭"/>
        	  </div>
				<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
					<ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;height: 300px"></ul>
				</div>
				<table id="addAuthor" class="basic" style="display: none;" style="width: 100%">
					<tr>
						<td>
							<%--<input type="text" name="projRanking" style="width: 85%;" />--%>
								<select name="projRanking" class="validate[required]" style="width: 80%;">
									<option value="" >请选择</option>
									<c:forEach items="${dictTypeEnumProjAuthorRankList}" var="dict" varStatus="status">
										<option  value="${dict.dictId}">${dict.dictName}</option>
									</c:forEach>
								</select>
						</td>
						<%--<td>
							<select name="deptFlow" class="validate[required]"
									style="width: 80%;text-align: left" onchange="loadUsers(this.value,this);">
								<option value="">请选择</option>
								<c:forEach items="${depts}" var="dept">
									<option value="${dept.deptFlow}">${dept.deptName}</option>
								</c:forEach>
								&lt;%&ndash;<option value="">其他部门</option>&ndash;%&gt;
							</select>
							<input type="hidden" name="deptName"/>
						</td>--%>
						<td>
							<input type="text" style="width: 85%;" name="deptName" <%--oninput="loadUsers(this);" --%>/>
							<input type="hidden" name="deptFlow"/>
						</td>
						<td>
							<%--<select name="userFlow" style="width: 80%;text-align: left" onchange="changeUser(this);"
									class="validate[required]">
								<option value="">请选择</option>
							</select>
							<input type="hidden" name="authorName"/>--%>
								<input type="text" style="width: 85%;" name="authorName" />
								<input type="hidden" name="userFlow"/>
						</td>
						<td>
							<input type="text"  name="scoreName" style="width: 85%;" value="${author.scoreName}" readonly="readonly"
									   onclick="showMenu(this); return false;"/>
							<input type="hidden" value="${author.scoreFlow}" name="scoreFlow" />
						</td>
						<td style="text-align: center;">
							[<a onclick="delAuthorTr(this)" style="cursor: pointer;">删除</a>]
						</td>
					</tr>
				</table>
        	</div>
      	</div>
 	</div>
</body>
</html>