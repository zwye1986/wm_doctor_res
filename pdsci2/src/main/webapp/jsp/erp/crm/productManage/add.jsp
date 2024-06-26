
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="true"/>
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
	<jsp:param name="jquery_mask" value="true"/>
</jsp:include>
	<style type="text/css">
		.checkboxB{
			display: none;
			background-color:ghostwhite;
		}
	</style>
<script type="text/javascript">

	$(document).ready(function(){
		changeChoesdForm();
	});
function save() {
	if(!$("#manageForm").validationEngine("validate")){
		return false;
	}
	var count= 0;
	$(":checkbox[name='userFlow']:checked").each(function(i){
		count++;
	});
	if(count>5)
	{
		jboxTip("参与人最多可选择5人！");
		return false;
	}
	jboxConfirm("确认保存信息？",function(){
		var url = "<s:url value='/erp/crm/productManage/saveManage'/>";
		jboxPost(url,$("#manageForm").serialize(),function(resp){
			if('1'==resp){
				jboxTip("保存成功");
				setTimeout(function(){
					window.parent.frames['mainIframe'].window.toPage(1);
					jboxClose();
				},2000)
			}else
			{
				jboxTip(resp);
			}
		},null,false);
	},null);
}

//右侧页面滑动效果
function rightIn(){
	$("#usersDiv").slideInit({
		width:400,
		speed:500,
		outClose:true
	});
	$("#usersDiv").rightSlideOpen();
}
//模糊查询
function likeSearch(name){
	if(name){
		$("[userName]").hide();
		$("[userName*='"+name+"']").show();
	}else{
		$("[userName]").show();
	}
}

function changeChoesdForm(){
	$("#chosedFormDiv").html("");
	var count= 0;
	var chosedForm="";
	$(":checkbox[name='userFlow']:checked").each(function(i){
		count++;
		chosedForm += "<a style='color: blue;' href='javascript:removeUser(\""+$(this).val()+"\");'>"+$(this).attr("uname")+"</a>"+"<br/>";
	});
	if(count>0)
	{
		$("#chosedFrom").val("已选择"+count+"人");
	}else{
		$("#chosedFrom").val("");
	}
	$("#chosedFormDiv").html(chosedForm);
}
	function removeUser(userFlow)
	{
		$(":checkbox[name='userFlow'][value='"+userFlow+"']").attr("checked",false);
		changeChoesdForm();
	}
function changeChoesdForm2(obj){
	$("#chosedFormDiv").html("");
	var count= 0;
	var chosedForm="";
	if($(":checkbox[name='userFlow']:checked").length>5)
	{
		jboxTip("参与人最多可选择5人！");
		$(obj).attr("checked",false);
	}
	$(":checkbox[name='userFlow']:checked").each(function(i){
		count++;
		chosedForm += "<a style='color: blue;' href='javascript:removeUser(\""+$(this).val()+"\");'>"+$(this).attr("uname")+"</a>"+"<br/>";
	});
	if(count>0)
	{
		$("#chosedFrom").val("已选择"+count+"人");
	}else{
		$("#chosedFrom").val("");
	}
	$("#chosedFormDiv").html(chosedForm);
}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
			<form id="manageForm" >
				<input name="manageFlow" value="${manage.manageFlow}" type="hidden">
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="30%"/>
						<col width="70%"/>
					</colgroup>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							项目名称：
						</td>
						<td>
							<input type="text" value="${manage.productName}"  name="productName" class="xltext validate[required,maxSize[90]]" autocomplete="off" style="width:140px;"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							客户名称：
						</td>
						<td>
							<input type="text" value="${manage.consumerName}"  name="consumerName" class="xltext validate[required,maxSize[50]]" autocomplete="off" style="width:140px;"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							立项时间：
						</td>
						<td>
							<input type="text" value="${manage.approvalTime}" name="approvalTime" class="ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:140px;"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
							参与人：
						</td>
						<td>
							<input id="chosedFrom" placeholder="点击选择参与人" title="点击选择参与人"
								   style="width: 140px;cursor: pointer" type="text"
								   readonly="readonly" class="xltext validate[required]"
								   onclick="rightIn()";	/>
		                </td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">预计完成日：</td>
						<td>
							<input type="text" value="${manage.etcTime}" name="etcTime" class="ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:140px;"/>
						</td>
					</tr>
				</table>

				<div class="checkboxB" id="usersDiv">
					<div style="text-align: left;margin-left: 5px;">
						<input type="text" class="xltext" placeholder="名字检索" onkeyup="likeSearch(this.value);">
					</div>
					<div style="overflow: auto; margin-left:5px;height: 58%">
						<c:forEach items="${userList}" var="u">
							<div style="width: 48%;float: left;margin-top: 10px; " userName="${u.userName}">
								<label>
									<input type="checkbox" class="validate[required]" name="userFlow" uname="${u.userName}"
										   onchange="changeChoesdForm2(this)"
										   <c:if test="${pdfn:contain(u.userFlow, userFlows) }">checked</c:if>  value="${u.userFlow}"/>
										${u.userName}
								</label>
							</div>
						</c:forEach>
					</div>
					<div style="bottom:0;margin: 5px;overflow: auto;height: 30%;border: 1px solid silver">
						<div style="float: left;">已选参与人：<br>点击可取消</div>
						<div style="float: left;text-align: left;"
							 class="chosedForm" id="chosedFormDiv">
						</div>
					</div>
				</div>
				<div class="button">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
</html>