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
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){

		<c:if test="${not empty list}">
			var style={"margin-top":"5px","width":"940px"};
				var options ={
					"colums":2//根据固定列的数量
				};
			var widths=['80','60','150','200','60','100','100','130','120','120','120'];
			$("#dataTable").Scorll(options,style,false,null);
		</c:if>

		$("#currentPage").val("${param.currentPage}");
	});

	function  showSignup(signupFlow)
	{
		var url = "<s:url value ='/jsres/examSignUp/showSignup'/>?signupFlow="+signupFlow;
		<c:if test="${typeId eq 'Theory'}">
		jboxOpen(url,"查看理论补考报名",800,240);
		</c:if>
		<c:if test="${typeId eq 'Skill'}">
		jboxOpen(url,"查看技能补考报名",800,240);
		</c:if>
	}
	function  auditSignup(signupFlow)
	{
		var url = "<s:url value ='/jsres/examSignUp/auditSignup'/>?signupFlow="+signupFlow;
		<c:if test="${typeId eq 'Theory'}">
		jboxOpen(url,"审核理论补考报名",800,300);
		</c:if>
		<c:if test="${typeId eq 'Skill'}">
		jboxOpen(url,"审核技能补考报名",800,300);
		</c:if>
	}

	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
	function heightFiexed(){
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
		var fixTrTd = $(".fixTrTd");
		var fixTrTh = $(".fixTrTh");
		var fixTd = $(".fix");
		$(fixTrTd).each(function(i)
		{
			var maxheight=-1;
			$(fixTrTd[i]).find(".by").each(function(){
				if($(this).height()>maxheight) maxheight=$(this).height();
			});
			if(maxheight!=-1) {
				$(fixTrTh[i]).find(".fix").each(function(){
					$(this).height(maxheight);
				});
			}
		});
	}

	onresize = function(){
		heightFiexed();
	};
</script>
		<c:if test="${empty list}">
			<div class="search_table" style="width: 100%;">
				<table border="0" cellpadding="0" cellspacing="0" class="grid">
					<tr>
						<th>地市</th>
						<th>申请年份</th>
						<th>姓名</th>
						<th>培训基地</th>
						<th>培训年级</th>
						<th>培训类别</th>
						<th>培训专业</th>
						<th>报考专业</th>
						<th>培训年限</th>
						<th>证件号码</th>
						<th>审核状态</th>
						<th>操作</th>
					</tr>
					<tr>
						<td colspan="12" >无记录！</td>
					</tr>
				</table>
			</div>
		</c:if>
<c:if test="${not empty list}">
	<div class="main_bd clearfix" style="width: 100%;">
		<table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid" >
			<thead>
			<tr>
				<th style="min-width: 100px; max-width: 100px; "   class="toFiexdDept">操作</th>
				<th style="min-width: 80px; max-width: 80px; "   class="toFiexdDept">姓名</th>
				<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
					<th style="min-width: 120px; max-width: 120px; " class="fixedBy">地市</th>
				</c:if>
				<th style="min-width: 100px; max-width: 100px; " class="fixedBy">申请年份</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训基地</th>
				<th style="min-width: 100px; max-width: 100px; " class="fixedBy">培训年级</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训类别</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训专业</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">报考专业</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">培训年限</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">证件号码</th>
				<th style="min-width: 150px; max-width: 150px; " class="fixedBy">审核状态</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${list}" var="s">
				<tr class="fixTrTd">
					<td style="min-width: 100px; max-width: 100px; " class="by">
						<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<c:if test="${f eq 'Y'}">
								<a class="btn"  onclick="auditSignup('${s.signupFlow}');" style="margin-top: 5px;margin-bottom: 5px;padding:0 20px">${s.auditStatusId eq 'Auditing'?'审核':'重新审核'}</a>
							</c:if>
							<c:if test="${f ne 'Y'}">
								<a class="btn"  onclick="showSignup('${s.signupFlow}');" style="margin-top: 5px;margin-bottom: 5px;">查看</a>
							</c:if>
						</c:if>
						<c:if test="${roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
							<a class="btn"  onclick="showSignup('${s.signupFlow}');" style="margin-top: 5px;margin-bottom: 5px;">查看</a>
						</c:if>
					</td>
					<td style="min-width: 80px; max-width: 80px; " class="by">${s.userName}</td>
					<c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
						<td style="min-width: 120px; max-width: 120px; " class="by">${s.orgCityName}</td>
					</c:if>
					<td style="min-width: 100px; max-width: 100px; " class="by">${s.signupYear}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${s.orgName}</td>
					<td style="min-width: 100px; max-width: 100px; " class="by">${s.sessionNumber}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${s.trainingTypeName}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${s.trainingSpeName}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${s.changeSpeName}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">
						<c:if test="${'OneYear' eq s.trainingYears}">一年</c:if>
						<c:if test="${'TwoYear' eq s.trainingYears}">两年</c:if>
						<c:if test="${'ThreeYear' eq s.trainingYears}">三年</c:if>
					</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${s.idNo}</td>
					<td style="min-width: 150px; max-width: 150px; " class="by">${s.auditStatusName}</td>
				</tr>
			</c:forEach>
			</tbody>
	</table>
	</div>
	</c:if>

    <div class="page" style="padding-right: 40px;">
		<c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
		<pd:pagination-jsres toPage="toPage"/>	 
	</div>
      
