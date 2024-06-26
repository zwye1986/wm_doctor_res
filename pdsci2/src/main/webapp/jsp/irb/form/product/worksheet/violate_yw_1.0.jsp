<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
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
<script>
if ("${param.type}" == 'show') {
	$(document).ready(function(){
		hideButton();
	});
}
function hideButton() {
	$(":input[type='button'][showFlag!='${GlobalConstant.FLAG_Y }']").each(function(){
		$(this).hide();
	});
	$('input').attr("readonly","readonly");
	$('textarea').attr("readonly","readonly");
	$("select").attr("disabled", "disabled");
	$(":checkbox").attr("disabled", "disabled");
	$(":radio").attr("disabled", "disabled");
}

function saveForm(){
	if(false==$("#initApplicationForm").validationEngine("validate")){
		return;
	}
	jboxPost("<s:url value='/irb/committee/saveWorksheet'/>?formFileName=${formFileName}&irbFlow=${irbForm.irb.irbFlow}&irbUserFlow=${irbUserFlow}",
			$('#initApplicationForm').serialize(),function(){
		if ("${param.source}" == "quickOpinion") {	//更新快审主审综合意见的主审决定和意见
			window.parent.frames['mainIframe'].$("#authDecision_${irbUserFlow}").html($(":input[name='decision']:checked").attr("desc"));
			window.parent.frames['mainIframe'].$("#authNote_${irbUserFlow}").html($("#irbSuggest").val());
		}
	},null,true);
}

function selectSingle(obj) {
	var id = $(obj).attr("id");
	var name = $(obj).attr("name");
	$("input[name="+name+"][id!="+id+"]:checked").attr("checked",false);
}
function print(){
	var url ="<s:url value='/irb/print'/>?watermarkFlag=${GlobalConstant.FLAG_N}&userFlow=${userFlow}&irbFlow=${irbForm.irb.irbFlow}&recTypeId=${irbRecTypeEnumViolateWorksheet.id}";
	window.location.href= url;
}

function doClose() {
	top.$.jBox.close('jbox-iframe');
}
</script> 
</head>
<body>
	<div class="mainright">
	<div class="content" align="center">
	<div style="margin-top: 5px;text-align: center;width: 100%;">
		<font size="3"><b>违背方案审查工作表</b></font>
	</div>
	<div class="title1 clearfix">
		<table width="100%" cellpadding="0" cellspacing="0" class="basic">
			<tr>
				<th colspan="4" style="text-align: left;">&nbsp;项目信息</th>
			</tr>	
      		<tr>
				<td width="20%" style="text-align: right;">项目名称：</td>
				<td width="30%" colspan="3">
					${proj.projName}
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align: right;">项目来源：</td>
				<td width="30%" colspan="3">
					${proj.projDeclarer}
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align: right;">方案版本号：</td>
				<td width="30%">
					${proVersion}
				</td>
				<td width="20%" style="text-align: right;">方案版本日期：</td>
				<td width="30%">
					${proVersionDate}
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align: right;">知情同意书版本号：</td>
				<td width="30%">
					${icfVersion}
				</td>
				<td width="20%" style="text-align: right;">知情同意书版本日期：</td>
				<td width="30%">
					${icfVersionDate}
				</td>
			</tr>
			<tr>
				<td width="20%" style="text-align: right;">受理号：</td>
				<td width="30%">
					${irbForm.irb.irbNo }
				</td>
				<td width="20%" style="text-align: right;">主审委员：</td>
				<td width="30%">
					${irbUserName }
				</td>
			</tr>
		</table>
</div>
<form id="initApplicationForm" >
	<div class="title1 clearfix">
		<table width="100%" cellpadding="0" cellspacing="0" class="basic">
			<tr>
      			<th style="text-align: left;">&nbsp;审查要素</th>
      		</tr>
      		<tr>
      			<td>
      				&nbsp;●&#12288;是否影响受试者的安全：<input type="checkbox" id="reviewElement11" name="reviewElement1" value="1" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement1']=='1'}">checked</c:if>><label for="reviewElement11">是</label>
      				&#12288;<input type="checkbox" id="reviewElement10" name="reviewElement1" value="0" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement1']=='0'}">checked</c:if>><label for="reviewElement10">否</label>
      			</td>
      		</tr>
      		<tr>
      			<td>
      			&nbsp;●&#12288;是否影响受试者的权益： <input type="checkbox" id="reviewElement21" name="reviewElement2" value="1" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement2']=='1'}">checked</c:if>><label for="reviewElement21">是</label>
      				&#12288;<input type="checkbox" id="reviewElement20" name="reviewElement2" value="0" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement2']=='0'}">checked</c:if>><label for="reviewElement20">否</label>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				&nbsp;●&#12288;是否对研究结果产生显著影响：<input type="checkbox" id="reviewElement31" name="reviewElement3" value="1" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement3']=='1'}">checked</c:if>><label for="reviewElement31">是</label>
      				&#12288;<input type="checkbox" id="reviewElement30" name="reviewElement3" value="0" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement3']=='0'}">checked</c:if>><label for="reviewElement30">否</label>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				&nbsp;●&#12288;违背方案事件的性质、程度与造成的后果是否“严重”：<input type="checkbox" id="reviewElement41" name="reviewElement4" value="1" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement4']=='1'}">checked</c:if>><label for="reviewElement41">是</label>
      				&#12288;<input type="checkbox" id="reviewElement40" name="reviewElement4" value="0" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement4']=='0'}">checked</c:if>><label for="reviewElement40">否</label>
      			</td>
      		</tr>
      		<tr>
      			<td>
      				&nbsp;●&#12288;对违背方案的情况，研究者是否“坚持不改”： <input type="checkbox" id="reviewElement51" name="reviewElement5" value="1" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement5']=='1'}">checked</c:if>><label for="reviewElement51">是</label>
      				&#12288;<input type="checkbox" id="reviewElement50" name="reviewElement5" value="0" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement5']=='0'}">checked</c:if>><label for="reviewElement50">否</label>
      			</td>
      		</tr>
      		<tr>
      			<td>
      			&nbsp;●&#12288;已对违背方案采取了合适的处理措施：<input type="checkbox" id="reviewElement61" name="reviewElement6" value="1" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement6']=='1'}">checked</c:if>><label for="reviewElement61">是</label>
      				&#12288;<input type="checkbox" id="reviewElement60" name="reviewElement6" value="0" onclick="selectSingle(this);" <c:if test="${formDataMap['reviewElement6']=='0'}">checked</c:if>><label for="reviewElement60">否</label>
      			</td>
      		</tr>
		</table>
		</div>
		<div class="title1 clearfix">
			<table width="100%" cellpadding="0" cellspacing="0" class="basic">
				<tr>
					<th colspan="2" style="text-align: left;">&nbsp;审查意见</th>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">
					建议：<textarea class="validate[maxSize[1000]]" style="width: 100%;border: 0;min-height: 70px;" id="irbSuggest" name="suggest" placeholder="请填写建议">${formDataMap['suggest'] }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: left;">&nbsp;
						<c:forEach items="${irbDecisionEnumList}" var="dec">
							<c:if test="${pdfn:countMatches(dec.irbTypeId,irbForm.irb.irbTypeId)==1 }">
							<input type="checkbox" id="decision_${dec.id }" name="decision" desc="${dec.name }" value="${dec.id}" onclick="selectSingle(this);"<c:if test="${formDataMap['decision']==dec.id}">checked="checked"</c:if>  />
							<label for="decision_${dec.id }" >${dec.name }</label>&#12288;
							</c:if>
						</c:forEach>
					</td>
				</tr>
				<%-- <c:if test="${irbForm.irb.reviewWayId== irbReviewTypeEnumFast.id}">
					<tr>
						<td colspan="2" style="text-align: left;">&nbsp;
							<input type="checkbox" id="submitMeetingReview" name="submitMeetingReview" value="1" onclick="selectSingle(this);"<c:if test="${formDataMap['submitMeetingReview']=='1'}">checked="checked"</c:if>  />
							<label for="submitMeetingReview" >提交会议审查</label>
						</td>
					</tr>
				</c:if> --%>
				<tr>
					<td width="20%" style="text-align: center;">伦理委员会</td>
					<td width="80%">
					${irbInfo.irbName }
					</td>
				</tr>
				<tr>
					<td width="20%" style="text-align: center;">主审委员声明</td>
					<td width="80%">
					作为审查人员,我与该研究项目之间不存在相关的利益冲突
					</td>
				</tr>
				<tr>
					<td width="20%" style="text-align: center;">签名</td>
					<td width="80%">
						<input type="text" name="preCommitteeName" value="${empty formDataMap['preCommitteeName'] ?irbUserName:formDataMap['preCommitteeName']}">
					</td>
				</tr>
				<tr>
					<td width="20%" style="text-align: center;">日期</td>
					<td width="80%">
						<input type="text" name="signDate" value="${empty formDataMap['signDate']?pdfn:getCurrDate():formDataMap['signDate'] }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
					</td>
				</tr>
			</table>
		</div>
</form>
<div class="button" >
		<input class="search" type="button" value="保&#12288;存" onclick="saveForm();" />
	<input class="search" type="button" showFlag="${GlobalConstant.FLAG_Y }" value="打&#12288;印" onclick="print();" />
	<c:if test="${param.open==GlobalConstant.FLAG_Y }">
		<input class="search" type="button" value="关&#12288;闭" onclick="doClose();" />
	</c:if>
</div>
</div></div>
</body>
</html>