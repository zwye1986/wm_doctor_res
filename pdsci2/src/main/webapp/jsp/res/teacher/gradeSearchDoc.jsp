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
		var url = "<s:url value='/res/head/exportGradeSearchDoc'/>?gradeRole=${param.gradeRole}&recFlow="+recFlow+"&keyCode=${param.keyCode}&operStartDate=${param.startDate}&operEndDate=${param.endDate}&date=${param.date}&role=${param.role}";
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
			<col width="55%"/>
			<col width="15%"/>
			<col width="10%"/>
			<col width="10%"/>
			<col width="10%"/>
		</colgroup>

		<tr>
			<th style="text-align: left;padding-left: 10px;">
				<c:if test="${param.gradeRole eq 'teacher'}">
					学员姓名
				</c:if>
				<c:if test="${param.gradeRole eq 'head'}">
					学员姓名
				</c:if>
			</th>
			<th style="text-align: center;padding:0">年级</th>
			<th style="text-align: center;padding:0">标准分</th>
			<th style="text-align: center;padding:0">总分</th>
			<th style="text-align: center;padding:0"><a style="color: #459ae9;cursor: pointer;" onclick="exportGradeSearchDoc();">批量导出</a></th>
		</tr>

		<c:forEach items="${recList}" var="rec">
			<c:set var="key" value="${rec.operUserFlow}${rec.recFlow}"/>
			<tr class="Show${rec.sessionNumber}">
				<th style="text-align: left;padding-left: 10px;">
					<span style="cursor: pointer;color: blue;font-size: 0.8em;line-height: 5px;" onclick="detailShow(this,'${key}');">${rec.operUserName}</span>
				</th>
				<th style="font-weight:normal;text-align: center;padding-right: 0px;"> ${rec.sessionNumber}</th>
				<th style="font-weight:normal;text-align: center;padding-right: 0px;">${scoreSumMap[key]}</th>
				<th style="text-align: center;padding-right: 0px;">${scoreMap[key]}</th>
				<th style="font-weight:normal;text-align: center;"><a style="color: #459ae9;" onclick="exportGradeSearchDoc('${rec.recFlow}')">[导出]</a></th>
			</tr>
			<c:forEach items="${assessCfgList}" var="title">
				<tr>
					<td class="${key}Show" colspan="5" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
						${title.name}
					</td>
				</tr>
				<c:forEach items="${title.itemList}" var="item">
					<c:set var="scoreKey" value="${key}${item.id}"/>
					<tr>
						<td class="${key}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="2">${item.name}</td>
						<td class="${key}Show" style="display: none;text-align: center;padding-left: 0px;">${item.score}</td>
						<td class="${key}Show" style="display: none;text-align: center;padding-left: 0px;">${scoreMap[scoreKey]}</td>
						<td class="${key}Show" style="display: none;text-align: center;padding-left: 0px;"></td>
					</tr>
				</c:forEach>
			</c:forEach>
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