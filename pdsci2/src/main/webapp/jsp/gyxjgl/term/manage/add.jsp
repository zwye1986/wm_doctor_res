<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
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
	<style type="text/css">
		.boxHome .item:HOVER{background-color: #eee;}
		.cur{color:red}
	</style>
	<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>"></script>
	<script type="text/javascript">
		function doClose(){
			window.parent.frames["mainIframe"].window.search();
		}
		function add(courseFlow){
			if(!$("#form").validationEngine("validate")){
				return ;
			}
			var termStartTime=$("[name='termStartTime']").val();
			var termEndTime=$("[name='termEndTime']").val();
			if(termEndTime<termStartTime)
			{
				jboxTip("学期开始时间不得大于学期结束时间！");
				return;
			}
			jboxConfirm('确认保存?',function(){
					var url="<s:url value='/gyxjgl/term/manage/saveTerm'/>";
					var fromSerizalize=$("#form").serialize();
					jboxPost(url,fromSerizalize,function(resp){
						jboxTip(resp);
						if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
							setTimeout(function(){
								window.parent.frames['mainIframe'].window.search();
								jboxClose();
							},1000);
						}

					},null,true);
			});
		}
		function setTime(value)
		{
			var termStartTime="";
			var termEndTime="";
			if(value){
				var startTime = new Date();
				var endTime=new Date();
				if(value=="autumn") {
					startTime.setFullYear(startTime.getFullYear(), 9, 5);
					endTime.setFullYear(endTime.getFullYear() + 1, 1, 13);
				}else{
					startTime.setFullYear(startTime.getFullYear(), 2, 13);
					endTime.setFullYear(endTime.getFullYear(), 6, 30);
				}
				termStartTime = startTime.getFullYear() + "-" + startTime.getMonth() + "-" + startTime.getDate();
				termEndTime = endTime.getFullYear() + "-" + endTime.getMonth() + "-" + endTime.getDate();
			}
			$("[name='termStartTime']").val(termStartTime);
			$("[name='termEndTime']").val(termEndTime);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form  id="form" method="post">
				<table class="basic" style="width: 100%;margin: 10px 0px;">
					<input type="hidden" name="recordFlow" value="${term.recordFlow}"/>
					<tr>
						<th><span style="color: red">*</span>&nbsp;年&emsp;&emsp;份：</th>
						<td  style="text-align: left;">
							<input type="text" class="validate[required]" <c:if test="${param.isEdit eq GlobalConstant.FLAG_Y}">disabled="disabled"</c:if> onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" style="width: 200px;" name="sessionNumber" value="${term.sessionNumber}"/>
						</td>
						<th><span style="color: red">*</span>&nbsp;学期：</th>
						<td style="text-align: left;">
							<select class="validate[required]" <c:if test="${param.isEdit eq GlobalConstant.FLAG_Y}">disabled="disabled"</c:if> name="gradeTermId" style="width: 204px;" >
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumGyTermSeasonList}" var="recruitSeason">
								<option value="${recruitSeason.dictId}" <c:if test="${term.gradeTermId eq recruitSeason.dictId}"> selected="selected"</c:if> >${recruitSeason.dictName}</option>
							</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th><span style="color: red">*</span>&nbsp;课&emsp;&emsp;程：</th>
						<td style="text-align: left;">
							<select style="width: 204px;" <c:if test="${param.isEdit eq GlobalConstant.FLAG_Y}">disabled="disabled"</c:if> class="validate[required]" name="classId">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumGyXjClassList}" var="xjclass">
									<option value="${xjclass.dictId}" ${term.classId eq xjclass.dictId?'selected':''}>${xjclass.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<th><span style="color: red">*</span>&nbsp;培养层次：</th><!--  -->
						<td style="text-align: left;">
							<select style="width: 204px;" <c:if test="${param.isEdit eq GlobalConstant.FLAG_Y}">disabled="disabled"</c:if> name="gradationId" id="gradationId" class="validate[required]">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
									<option value="${trainType.dictId}" <c:if test="${term.gradationId==trainType.dictId}">selected="selected"</c:if>>${trainType.dictName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><span style="color: red">*</span>&nbsp;学期开始时间：</th>
						<td style="text-align: left;"><input style="width: 200px;" name="termStartTime" value="${term.termStartTime}" type="text"  class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
						<th><span style="color: red">*</span>&nbsp;学期结束时间：</th>
						<td style="text-align: left;"><input style="width: 200px;" name="termEndTime" value="${term.termEndTime}" type="text" class="validate[required]"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/></td>
					</tr>
					<tr><td colspan="4"><font color="red">注意：选择班级与培养层次时，请选择同一层次！比如（班级：2016级硕士研究生一教学班；培养层次：应该为硕士研究生。)</font></td></tr>
				</table>
			</form>
			<div style="text-align: center;">
				<input type="button" class="search" onclick="add();" value="保&#12288;存"/>
				<input type="button" class="search" value="关&#12288;闭" onclick="doClose();"/>
			</div>
		</div>
	</div>
</div>
</body>
</html>