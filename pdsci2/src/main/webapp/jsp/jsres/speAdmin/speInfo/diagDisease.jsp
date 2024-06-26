<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript"
		src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	.div_table h4 {
		color: #000000;
		font: 15px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info th {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 500;
	}
	.base_info td {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 400;
	}
</style>
<script type="text/javascript">
	function showTable(obj) {
		var oDiv = document.getElementById(obj);
		var aDisplay = oDiv.style.display;
		if ("none" == aDisplay) {
			oDiv.style.display = "";

			var imgDivUp = document.getElementById(obj+"up");
			var imgDivDown = document.getElementById(obj+"down");
			imgDivUp.style.display = "";
			imgDivDown.style.display = "none";
		} else {
			oDiv.style.display = "none";
			var imgDivUp = document.getElementById(obj+"up");
			var imgDivDown = document.getElementById(obj+"down");
			imgDivUp.style.display = "none";
			imgDivDown.style.display = "";
		}
	}
</script>
<form id='BaseInfoForm' style="position:relative;">
	<div class="main_bd"
			<c:if test="${isJoin eq 'Y' }"> style="position: relative;overflow-y: auto;" </c:if>
			<c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;" </c:if>  >
		<div class="div_table">
			<h4 style="border-left: 4px solid #54b2e5">诊疗疾病范围</h4>
			如某项的年诊治/完成例数为0，则可省略不填。
			<br>
			<br>
			<c:forEach items="${resultList}" var="r" varStatus="status">
				<h4 style="height: 30px;line-height: 30px;margin-left: 20px">${r.standardDeptName}</h4>

				<img id="${status.index}down" src="<s:url value='/jsp/jsres/images/down3.png'/>" onclick="showTable('${status.index}');"  title="展开"
					 style="float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>
				<img id="${status.index}up" src="<s:url value='/jsp/jsres/images/up3.png'/>" onclick="showTable('${status.index}');"  title="收缩"
					 style="display: none;float:right;width: 20px;height: 20px;margin-right: 40px;margin-top: -35px"/>

				<%--<span style="color: blue;cursor: pointer" onclick="showTable('${status.index}')"> ${r.standardDeptName}</span>--%>
				<c:if test="${r.tableAllArrNum eq '2'}">
					<table cellspacing="0" cellpadding="0" class="base_info" id="${status.index}"
						   style="margin-bottom: 40px;display: none;margin-left: 20px;width: 95%">
						<colgroup>
							<col width="60%"/>
							<col width="40%"/>
						</colgroup>
						<tbody>
						<c:forEach items="${r.infoList}" var="t" varStatus="status">
							<tr>
								<c:if test="${t.isTh eq 'Y'}">
									<th style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
									<th style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrTwo}</th>
								</c:if>

								<c:if test="${t.isTd eq 'Y'}">
									<td style="text-align: left">${t.arrOne}</td>
									<td>${t.info}</td>
								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${r.tableAllArrNum eq '3'}">
					<table cellspacing="0" cellpadding="0" class="base_info" id="${status.index}"
						   style="margin-bottom: 40px;display: none;margin-left: 20px;width: 95%">
						<colgroup>
							<col width="30%"/>
							<col width="40%"/>
							<col width="30%"/>
						</colgroup>
						<tbody>
						<c:forEach items="${r.infoList}" var="t" varStatus="status">
							<tr>
								<c:if test="${t.isTh eq 'Y'}">
									<c:if test="${t.arrOneClo eq t.arrNum}">
										<th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
											style=" <c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
									</c:if>

									<c:if test="${t.arrOneClo ne t.arrNum}">
										<th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
											style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>

										<c:if test="${empty t.arrTwoClo}">
											<th colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}"
												style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrThree}</th>
										</c:if>

									</c:if>
								</c:if>


								<c:if test="${t.isTd eq 'Y'}">
									<c:if test="${not empty t.arrOneClo}">
										<td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
												<c:if test="${empty t.arrTwoClo}"> style="text-align: right;padding-right: 10px;" </c:if>
												<c:if test="${not empty t.arrTwoClo}"> style="text-align: center" </c:if>>${t.arrOne}</td>
										<c:if test="${empty t.arrTwoClo}">
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
												<td>${t.info}</td>
											</c:if>
										</c:if>

										<c:if test="${not empty t.arrTwoClo}">
											<td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
												<td>${t.info}</td>
											</c:if>
										</c:if>

									</c:if>

									<c:if test="${empty t.arrOneClo}">
										<td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
										<c:if test="${not empty t.arrTwoClo}">
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
												<td>${t.info}</td>
											</c:if>
										</c:if>
									</c:if>

								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${r.tableAllArrNum eq '4'}">
					<table cellspacing="0" cellpadding="0" class="base_info" id="${status.index}"
						   style="margin-bottom: 40px;display: none;margin-left: 20px;width: 95%">
						<colgroup>
							<col width="20%"/>
							<col width="30%"/>
							<col width="30%"/>
							<col width="20%"/>
						</colgroup>
						<tbody>
						<c:forEach items="${r.infoList}" var="t" varStatus="status">
							<tr>
								<c:if test="${t.isTh eq 'Y'}">
									<c:if test="${t.arrOneClo eq t.arrNum}">
										<th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
											style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
									</c:if>

									<c:if test="${t.arrOneClo ne t.arrNum}">
										<th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
											style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>

										<c:if test="${empty t.arrTwoClo}">
											<c:if test="${empty t.arrThreeClo}">
												<th colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}"
													style=" <c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFour}</th>
											</c:if>
											<c:if test="${not empty t.arrThreeClo}">
												<th colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}"
													style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrThree}</th>
											</c:if>
										</c:if>

									</c:if>
								</c:if>


								<c:if test="${t.isTd eq 'Y'}">
									<c:if test="${not empty t.arrOneClo}">
										<td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}" style="text-align: center">${t.arrOne}</td>
										<c:if test="${not empty t.arrTwoClo}">
											<td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
												<td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
													<td>${t.info}</td>
												</c:if>
											</c:if>
											<c:if test="${empty t.arrThreeClo }">
												<td><c:if test="${haveInfo eq 'Y'}">${t.info}</c:if></td>
											</c:if>
										</c:if>
									</c:if>

									<c:if test="${empty t.arrOneClo}">
										<c:if test="${empty t.arrTwoClo}">
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
												<td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
													<td>${t.info}</td>
												</c:if>
											</c:if>
										</c:if>
										<c:if test="${not empty t.arrTwoClo}">
											<td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
												<td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
													<td>${t.info}</td>
												</c:if>
											</c:if>
											<c:if test="${ empty t.arrThreeClo }">
												<td>${t.info}</td>
											</c:if>
										</c:if>
									</c:if>

								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
				<c:if test="${r.tableAllArrNum eq '5'}">
					<table cellspacing="0" cellpadding="0" class="base_info" id="${status.index}"
						   style="margin-bottom: 40px;display: none;margin-left: 20px;width: 95%">
						<colgroup>
							<col width="15%"/>
							<col width="15%"/>
							<col width="20%"/>
							<col width="30%"/>
							<col width="20%"/>
						</colgroup>
						<tbody>
						<c:forEach items="${r.infoList}" var="t" varStatus="status">
							<tr>
								<c:if test="${t.isTh eq 'Y'}">
									<th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
										style=" <c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
									<c:if test="${not empty t.arrFourClo}">
										<th colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}"
											style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFour}</th>
									</c:if>
									<th colspan="${t.arrFiveClo}" rowspan="${t.arrFiveRow}"
										style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFive}</th>
								</c:if>


								<c:if test="${t.isTd eq 'Y'}">
									<c:if test="${not empty t.arrOneClo}">
										<td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}" style="text-align: center">${t.arrOne}</td>
										<c:if test="${not empty t.arrTwoClo}">
											<td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>

											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
												<td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
													<td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
													<td>${t.info}</td>
												</c:if>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
													<td>${t.info}</td>
													<td>${t.infoTwo}</td>
												</c:if>
											</c:if>
											<c:if test="${empty t.arrThreeClo}">
												<td>${t.arrFour}</td>
												<td>${t.info}</td>
											</c:if>
										</c:if>
									</c:if>

									<c:if test="${empty t.arrOneClo}">
										<c:if test="${empty t.arrTwoClo}">
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
												<td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
													<td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
													<td>${t.info}</td>
												</c:if>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
													<td>${t.info}</td>
													<td>${t.infoTwo}</td>
												</c:if>
											</c:if>
											<c:if test="${ empty t.arrThreeClo}">
												<td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
												<td>${t.info}</td>
											</c:if>
										</c:if>
										<c:if test="${not empty t.arrTwoClo}">
											<td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
											<c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
												<td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
													<td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
													<td>${t.info}</td>
												</c:if>
												<c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
													<td>${t.info}</td>
													<td>${t.infoTwo}</td>
												</c:if>
											</c:if>
											<c:if test="${ empty t.arrThreeClo }">
												<c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
													<td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
													<td>${t.info}</td>
												</c:if>
												<c:if test="${ empty t.arrFourClo and t.arrFourClo ne '0'}">
													<td>${t.info}</td>
												</c:if>
											</c:if>
										</c:if>
									</c:if>

								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</c:if>
			</c:forEach>
		</div>
	</div>
</form>
