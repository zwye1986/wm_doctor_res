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
	<script type="text/javascript">
		function detailShow(span,clazz){
			console.log(clazz);
			$("."+clazz+"Show").fadeToggle(100);
		}

		function showMsg(value) {
			console.log(value);
			$("tr[class^='Show'],td[class$='Show']").each(function(){
				$(this).hide();
			});
			if(value == ""){
				$("tr[class^='Show']").each(function(){
					$(this).show();
				});
			}else{
				$(".Show"+value).show();
			}
		}

		function exportGradeSearchDoc(recFlow){
			if(!recFlow){
				recFlow = "";
			}
			jboxStartLoading();
			jboxTip("导出中…………");
			var url = "<s:url value='/thres/head/thExportGradeSearchDoc'/>?gradeRole=${param.gradeRole}&recFlow="+recFlow+"&keyCode=${param.keyCode}&operStartDate=${param.startDate}&operEndDate=${param.endDate}&date=${param.date}";
			window.location.href=url;
			jboxEndLoading();
		}
	</script>

</head>
<body>
<div style="width:100%;max-height: 500px;overflow: auto">
	<div style="float:right;margin: 10px;">
		年&nbsp;&nbsp;级:
		<%--<select  class="qselect"  onchange="showMsg(this)"  >
            <option  value="all">全部</option>
            <option  value="_2016">2016</option>
            <option  value="_2017">2017</option>
            <option  value="_2018">2018</option>

        </select>--%>
		<input style="width:137px;" onClick="WdatePicker({dateFmt:'yyyy'});"  onchange="showMsg(this.value)"/>
	</div>
	<table class="basic list" width="100%">
		<colgroup>
			<col width="35%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="25%"/>
		</colgroup>

		<tr>
			<th style="text-align: left;padding-left: 10px;"colspan="3">
				学员姓名
			</th>
			<th style="text-align: center;padding:0" colspan="2">年级</th>
			<th style="text-align: center;padding:0"><a style="color: #459ae9;cursor: pointer;" onclick="exportGradeSearchDoc();">批量导出</a></th>
		</tr>

		<c:forEach items="${recList}" var="rec">
			<c:set var="key" value="${rec.operUserFlow}${rec.recFlow}"/>
			<c:set var="teacherGradeInfo" value="${gradeInfoMap[rec.recFlow]}"/>
			<tr class="Show${rec.sessionNumber}">
				<th style="text-align: left;padding-left: 10px;"colspan="3">
					<span style="cursor: pointer;color: blue;font-size: 0.8em;line-height: 5px;" onclick="detailShow(this,'${key}');">${rec.operUserName}</span>
				</th>
				<th style="font-weight:normal;text-align: center;padding-right: 0px;" colspan="2"> ${rec.sessionNumber}</th>
				<th style="font-weight:normal;text-align: center;"><a style="color: #459ae9;" onclick="exportGradeSearchDoc('${rec.recFlow}')">[导出]</a></th>
			</tr>
			<tr>
				<td class="${key}Show" style="text-align: left;display: none;">轮转科室：${rec.deptName}</td>
				<td class="${key}Show" style="text-align: left;display: none;" colspan="2">轮转时间：</td>
				<td class="${key}Show" style="text-align: left;display: none;" colspan="3">${rec.schStartDate}&nbsp;&nbsp;至&nbsp;&nbsp;${rec.schEndDate}</td>
			</tr>
			<tr>
				<td class="${key}Show" style="text-align: left;display: none;">
					推选本科室优秀带教老师:
				</td>
				<td class="${key}Show" style="text-align: left;display: none;" colspan="5" >
					${teacherGradeInfo.teacherNameOne}&nbsp;&nbsp;${teacherGradeInfo.teacherNameTwo}&nbsp;&nbsp;${teacherGradeInfo.teacherNameThree}
				</td>
			</tr>
			<tr>
				<td class="${key}Show" style="text-align: left;display: none;" >
					考评项
				</td>
				<td class="${key}Show" style="text-align: center;display: none;" >
					优
				</td>
				<td class="${key}Show" style="text-align: center;display: none;" >
					良
				</td>
				<td class="${key}Show" style="text-align: center;display: none;">
					中
				</td>
				<td class="${key}Show" style="text-align: center;display: none;" >
					差
				</td>
				<td class="${key}Show" style="text-align: center;display: none;">
					备注
				</td>
			</tr>

			<c:set var="gradeMap" value="${gradesMap[rec.recFlow]}"/>
			<c:forEach items="${titleFormList}" var="title">

				<c:forEach items="${title.itemList}" var="item">
					<c:set var="scoreKey" value="${key}${item.id}"/>

					<tr>
						<td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="">${item.name}</td>
						<td class="${key}Show" style="text-align: center;display: none;" >${gradeMap[item.id]['score'] eq '1' ?'√':''}</td>
						<td class="${key}Show" style="text-align: center;display: none;" >${gradeMap[item.id]['score'] eq '2' ?'√':''}</td>
						<td class="${key}Show" style="text-align: center;display: none;" >${gradeMap[item.id]['score'] eq '3' ?'√':''}</td>
						<td class="${key}Show" style="text-align: center;display: none;" >${gradeMap[item.id]['score'] eq '4' ?'√':''}</td>
						<td class="${key}Show" style="text-align: center;display: none;" >${gradeMap[item.id]['lostReason']} </td>
					</tr>

				</c:forEach>
			</c:forEach>
			<tr>
				<td class="${key}Show" style="text-align: left;display: none;" >有无专人带教</td>
				<td class="${key}Show" style="text-align: center;display: none;" colspan="2" >有${gradeMap['teach'] eq 'yes' ?'√':''} </td>
				<td class="${key}Show" style="text-align: center;display: none;" colspan="3" >无${gradeMap['teach'] eq 'no' ?'√':''}</td>
			</tr>
			<tr>
				<td class="${key}Show" style="text-align: left;display: none;" >特色教学活动或亮点</td>
				<td class="${key}Show" style="text-align: center;display: none;" colspan="5" >${gradeMap['activty']}</td>
			</tr>
			<tr>
				<td class="${key}Show" style="text-align: left;display: none;" >您对本科室整体带教的建议和意见</td>
				<td class="${key}Show" style="text-align: center;display: none;" colspan="5" >${gradeMap['jianyi']}</td>
			</tr>
		</c:forEach>
		<c:if test="${empty recList}">
			<tr>
				<td style="text-align: center" colspan="5">
					无记录
				</td>
			</tr>
		</c:if>
	</table>
	<center style="margin-top: 10px;"><input type="button"  class="search" value="关&#12288;闭" onclick="jboxClose();"/></center>
</div>
</body>
</html>