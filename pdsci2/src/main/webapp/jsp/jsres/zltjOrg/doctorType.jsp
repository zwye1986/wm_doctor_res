<script>
	function heightFiexed(){
		//修正高度
		var maxheight=-1;
		var headTrTh=$(".headTrTh");
		$(headTrTh).each(function(i){

			$(headTrTh[i]).find(".fixedBy").each(function(){
				if($(this).height()>maxheight) maxheight=$(this).height();
			});
		});
		if(maxheight!=-1) {
			$(".toFiexdDept").each(function(){
				$(this).height(maxheight+1);
			});
		}
		var fixTrTd = $(".fixTrTd");
		var fixTrTh = $(".fixTrTh");
		$(fixTrTd).each(function(i)
		{
			var maxheight=-1;
			$(fixTrTd[i]).find(".by").each(function(){
				if($(this).height()>maxheight) maxheight=$(this).height();
			});
			if(maxheight!=-1) {
				$(fixTrTh[i]).find(".fix").each(function(){
					$(this).height(maxheight+1);
				});
			}
		});
	}
	onresize = function(){
		heightFiexed();
	};
	$(function(){
		heightFiexed();
	});
	function showSubList(obj){
		var subSize=$(obj).attr("subSize");
		var cityId=$(obj).attr("cityId");
		var orgFlow=$(obj).attr("orgFlow");
		var rowspan=$("#"+cityId+"First").attr("rowspan")||1;
		var rowspan2=$("#"+cityId+"Second").attr("rowspan")||1;
		if(parseInt(subSize)>0)
		{
			if($("."+orgFlow).is(":hidden")){
				rowspan=parseInt(rowspan)+parseInt(subSize);
				rowspan2=parseInt(rowspan2)+parseInt(subSize);
				$("."+orgFlow).show();
			}else{
				rowspan=parseInt(rowspan)-parseInt(subSize);
				rowspan2=parseInt(rowspan2)-parseInt(subSize);
				$("."+orgFlow).hide();
			}
			$("#"+cityId+"First").attr("rowspan",rowspan);
			$("#"+cityId+"Second").attr("rowspan",rowspan2);
		}
	}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}
</script>
<style>
    .grid th {
		background: #EDF3FF;
    }
</style>

<div class="div_search">
	<div id="tableContext"style=" height:600px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 0px;" onscroll="tableFixed(this);">
		<!--第一次 -->
		<div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			<table class="grid">
				<tr class="headTrTh">
					<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">
						<th style="width:10% ;border: 1px solid #e7e7eb;" class="toFiexdDept">地市名称</th>
					</c:if>
					<th style="width: 18%;border: 1px solid #e7e7eb;" class="toFiexdDept">基地名称</th>

					<c:forEach items="${resDocTypeEnumList}" var="dict">
							<th style="min-width: 110px;max-width: 150px;border: 1px solid #e7e7eb;"class="fixedBy">
								${dict.name}
							</th>
					</c:forEach>
					<th style="width: 10%;border: 1px solid #e7e7eb;"class="fixedBy">总计</th>
				</tr>
			</table>
		</div>
<%--		<!--第二次 -->--%>
<%--		<div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">--%>
<%--			<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">--%>
<%--			<table class="grid" style="min-width:250px;max-width: 250px;border-right: 0px">--%>
<%--			</c:if>--%>
<%--			<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}">--%>
<%--			<table class="grid" style="min-width:150px;max-width: 150px;border-right: 0px">--%>
<%--			</c:if>--%>
<%--				<tr class="headTrTh">--%>
<%--					<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">--%>
<%--						<th style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" class="toFiexdDept">地市名称</th>--%>
<%--					</c:if>--%>
<%--						<th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;" class="toFiexdDept">基地名称</th>--%>
<%--				</tr>--%>
<%--				<c:forEach items="${citys}" var="city">--%>
<%--					<c:set var="orgs" value="${cityOrgsMap[city.cityId]}"></c:set>--%>
<%--					<c:set var="rowspan" value="${fn:length(orgs)}"></c:set>--%>
<%--					<c:if test="${not empty orgs and (city.cityId eq orgCityId || empty orgCityId)}">--%>
<%--						<c:forEach items="${orgs}" var="org" varStatus="s">--%>
<%--							<c:set var="jointOrgs" value="${jointOrgsMap[org.orgFlow]}"></c:set>--%>
<%--							<c:set var="subSize" value="${fn:length(jointOrgs)}"></c:set>--%>
<%--							<c:set var="shwwSubClick" value="${--%>
<%--							(empty param.orgFlow and empty param.orgLevel or param.jointOrgFlag eq 'Y' )--%>
<%--							 and (not empty jointOrgs) }"></c:set>--%>
<%--							<c:if test="${s.first}">--%>
<%--								<tr class="fixTrTh">--%>
<%--								<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">--%>
<%--									<td  style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;background-color: white;" rowspan="${rowspan}" id="${city.cityId}First" >--%>
<%--											${city.cityName}--%>
<%--									</td>--%>
<%--								</c:if>--%>
<%--									<td <c:if test="${ shwwSubClick}">style="color:#459ae9;cursor: pointer;background-color: white;min-width: 100px;max-width: 100px;" onclick="showSubList(this);" </c:if>--%>
<%--										<c:if test="${!shwwSubClick}">style="background-color: white;min-width: 100px;max-width: 100px;" </c:if>--%>
<%--										class="fix"  cityId="${city.cityId}" orgFlow="${org.orgFlow}" subSize="${subSize}">--%>
<%--											${org.orgName}--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</c:if>--%>
<%--							<c:if test="${!s.first}">--%>
<%--								<tr class="fixTrTh">--%>
<%--									<td <c:if test="${shwwSubClick}">style="color:#459ae9;cursor: pointer;background-color: white;min-width: 100px;max-width: 100px;" onclick="showSubList(this);" </c:if>--%>
<%--										<c:if test="${!shwwSubClick}">style="background-color: white;min-width: 100px;max-width: 100px;" </c:if>--%>
<%--										class="fix"  cityId="${city.cityId}" orgFlow="${org.orgFlow}" subSize="${subSize}">--%>
<%--											${org.orgName}--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</c:if>--%>
<%--							<c:forEach items="${jointOrgs}" var="jointOrg">--%>
<%--								<tr style="display: none;" class="fixTrTh ${org.orgFlow}">--%>
<%--									<th style="font-weight: normal;"class="fix">--%>
<%--											${jointOrg.orgName}--%>
<%--									</th>--%>
<%--								</tr>--%>
<%--							</c:forEach>--%>
<%--						</c:forEach>--%>
<%--					</c:if>--%>
<%--				</c:forEach>--%>
<%--				<tr class="fixTrTh">--%>
<%--					<td style="background-color: white;" <c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">colspan="2"</c:if>>${(empty param.orgFlow and empty param.orgLevel or param.jointOrgFlag eq 'Y' ) ?'合计(已包含协同)':'合计'}</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
<%--		</div>--%>
<%--		<!--第三次  -->--%>
<%--		<div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">--%>
<%--			<table class="grid" style="width: auto;border-right: 0px" >--%>
<%--				<tr class="headTrTh">--%>
<%--					<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">--%>
<%--						<th style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" class="toFiexdDept">地市名称</th>--%>
<%--					</c:if>--%>
<%--					<th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;" class="toFiexdDept">基地名称</th>--%>
<%--				</tr>--%>
<%--			</table>--%>
<%--		</div>--%>
		<div style=" overflow: visible;   top: 0px; left: 0px;">
			<table border="0" cellpadding="0" cellspacing="0" class="grid" id="dataTable">
				<tr class="headTrTh">
					<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">
						<th style="width:10% ;border: 1px solid #e7e7eb;">地市名称</th>
					</c:if>
					<th style="width: 18%;border: 1px solid #e7e7eb;">基地名称</th>

					<c:forEach items="${resDocTypeEnumList}" var="dict">
							<th style="min-width: 110px;max-width: 150px;border: 1px solid #e7e7eb;"class="fixedBy">
									${dict.name}
							</th>
					</c:forEach>
					<th style="min-width: 80px;max-width: 80px;border: 1px solid #e7e7eb;">总计</th>
				</tr>
				<c:set var="zongji" value="0"></c:set>
				<c:forEach items="${citys}" var="city">
					<c:set var="orgs" value="${cityOrgsMap[city.cityId]}"></c:set>
					<c:set var="rowspan" value="${fn:length(orgs)}"></c:set>
					<c:if test="${not empty orgs and (city.cityId eq orgCityId || empty orgCityId)}">
						<c:forEach items="${orgs}" var="org" varStatus="s">
							<c:set var="jointOrgs" value="${jointOrgsMap[org.orgFlow]}"></c:set>
							<c:set var="subSize" value="${fn:length(jointOrgs)}"></c:set>
							<c:if test="${s.first}">
								<tr class="fixTrTh">
								<c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">
										<td  style="width:10% ;border: 1px solid #e7e7eb;" rowspan="${rowspan}" id="${city.cityId}Second">
												${city.cityName}
										</td>
								</c:if>
									<td  style="width: 12%" cityId="${city.cityId}" orgFlow="${org.orgFlow}" subSize="${subSize}">
											${org.orgName}
									</td>
									<c:set var="sum" value="0"></c:set>
									<c:forEach items="${resDocTypeEnumList}" var="dict">
											<c:set var="key" value="${org.orgFlow}${dict.id}"></c:set>
												<c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
												<td>${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
									</c:forEach>
									<td>
										${sum+0}
										<c:set var="zongji" value="${sum+zongji}"></c:set>
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr class="fixTrTh">
									<td  cityId="${city.cityId}" orgFlow="${org.orgFlow}" subSize="${subSize}">
											${org.orgName}
									</td>
									<c:set var="sum" value="0"></c:set>
									<c:forEach items="${resDocTypeEnumList}" var="dict">
											<c:set var="key" value="${org.orgFlow}${dict.id}"></c:set>
												<c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
												<td>${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
									</c:forEach>
									<td>
											${sum+0}
										<c:set var="zongji" value="${sum+zongji}"></c:set>
									</td>
								</tr>
							</c:if>
							<c:forEach items="${jointOrgs}" var="jointOrg">
								<tr style="display: none;" class="fixTrTh ${org.orgFlow}">
									<th style="border: 1px solid #e7e7eb;font-weight: normal;">
											${jointOrg.orgName}
									</th>
									<c:set var="sum" value="0"></c:set>
									<c:forEach items="${resDocTypeEnumList}" var="dict">
										<c:set var="key" value="${jointOrg.orgFlow}${dict.id}"></c:set>
										<c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
										<th style="border: 1px solid #e7e7eb;font-weight: normal;">${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</th>
									</c:forEach>
									<th style="border: 1px solid #e7e7eb;font-weight: normal;">${sum}</th>
								</tr>
							</c:forEach>
						</c:forEach>
					</c:if>
				</c:forEach>
				<tr class="fixTrTh">
					<td <c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_CHARGE}">colspan="2"</c:if>>${ (empty param.orgFlow and empty param.orgLevel or param.jointOrgFlag eq 'Y' )?'合计(已包含协同)':'合计'}</td>
					<c:forEach items="${resDocTypeEnumList}" var="dict">
						<td>${empty typeNumMap[dict.id]?0:typeNumMap[dict.id]}</td>
					</c:forEach>
					<td>${zongji}</td>
				</tr>
			</table>
		</div>
	</div>
</div>