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
	<jsp:param name="jquery_validation" value="false"/>
	<jsp:param name="jquery_datePicker" value="false"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="false"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="treetable" value="true"/>
</jsp:include>
	<script type="text/javascript">
		$(function () {

		//$("#treeTable1").treetable({onNodeExpand:function(){alert(1);}});
		$("#treeTable").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
		$("#treeTable").treetable("collapseAll");
		//	calculate();
			calculate2();
		});
		function toggleTr(obj){
			$('#treeTable').treetable('collapseOrexpand',$(obj).parents('tr').attr("data-tt-id"));
		}

		function calculate(){
			var trs = $("#fundTb tr");
			$.each(trs,function(i,n){
				if(!$(n).attr("data-tt-parent-id")){
					var hasChild = false;

					var a = parseFloat($(n).find("td").eq(4).text())?parseFloat($(n).find("td").eq(4).text()):0;
					var b = parseFloat($(n).find("td").eq(5).text())?parseFloat($(n).find("td").eq(5).text()):0;
					var c = parseFloat($(n).find("td").eq(6).text())?parseFloat($(n).find("td").eq(6).text()):0;
					var d = parseFloat($(n).find("td").eq(7).text())?parseFloat($(n).find("td").eq(7).text()):0;
					var e = parseFloat($(n).find("td").eq(8).text())?parseFloat($(n).find("td").eq(8).text()):0;
					var f = parseFloat($(n).find("td").eq(9).text())?parseFloat($(n).find("td").eq(9).text()):0;
					var x = 0;
					var y = 0;
					var z = 0;
					$.each(trs,function(j,m){
						if($(m).attr("data-tt-parent-id") && $(m).attr("data-tt-parent-id") == $(n).attr("data-tt-id")){
							hasChild = true;
							a += parseFloat($(m).find("td").eq(4).text())?parseFloat($(m).find("td").eq(4).text()):0;
							b += parseFloat($(m).find("td").eq(5).text())?parseFloat($(m).find("td").eq(5).text()):0;
							c += parseFloat($(m).find("td").eq(6).text())?parseFloat($(m).find("td").eq(6).text()):0;
							x += parseFloat($(m).find("td").eq(7).text())?parseFloat($(m).find("td").eq(7).text()):0;
							y += parseFloat($(m).find("td").eq(8).text())?parseFloat($(m).find("td").eq(8).text()):0;
							z += parseFloat($(m).find("td").eq(9).text())?parseFloat($(m).find("td").eq(9).text()):0;
						}
					});
					$(n).find("td").eq(4).text(parseFloat(a.toFixed(4)));
					$(n).find("td").eq(5).text(parseFloat(b.toFixed(4)));
					$(n).find("td").eq(6).text(parseFloat(c.toFixed(4)));
					if(hasChild){
						$(n).find("td").eq(7).text(parseFloat(x.toFixed(4)));
						$(n).find("td").eq(8).text(parseFloat(y.toFixed(4)));
						$(n).find("td").eq(9).text(parseFloat(z.toFixed(4)));
					}else{
						$(n).find("td").eq(7).text(parseFloat(d.toFixed(4)));
						$(n).find("td").eq(8).text(parseFloat(e.toFixed(4)));
						$(n).find("td").eq(9).text(parseFloat(f.toFixed(4)));
					}

				}
			});

		}

		function calculate2(){
			var trs = $("#fundTb tr");
			var amount = 0;
			var paymentGove = 0;
			var paymentOrg = 0;
			$.each(trs,function(i,n){
				if(!$(n).attr("data-tt-parent-id")) {
					var a = parseFloat($(n).find("td").eq(4).text())?parseFloat($(n).find("td").eq(4).text()):0;
					var b = parseFloat($(n).find("td").eq(5).text())?parseFloat($(n).find("td").eq(5).text()):0;
					var c = parseFloat($(n).find("td").eq(6).text())?parseFloat($(n).find("td").eq(6).text()):0;
					amount +=a;
					paymentGove+=b;
					paymentOrg+=c;
				}
			});
			$("#paymentAmount").text(parseFloat(amount.toFixed(2)));
			$("#paymentGove").text(parseFloat(paymentGove.toFixed(2)));
			$("#paymentOrg").text(parseFloat(paymentOrg.toFixed(2)));
		}

		function paymentList(fundDetailFlow,type) {
			var url = "<s:url value='/srm/fund/paymentList'/>?fundDetailFlow="+fundDetailFlow+"&type="+type;
			var dg = dialog({
				id: 'openDialog',
				fixed: true,
				padding: 5,
				title: "费用详情",
				url: url,
				width: 600,
				height: 350,
				cancelDisplay: false,
				cancelValue: '关闭',
				backdropOpacity: 0.1,
			});
			dg.showModal();
		}

		function editItem(fundFlow , projFlow){
			//var url =rootPath()+'/srm/fund/details?fundFlow='+fundFlow+"&projFlow="+projFlow;
			var rpath = rootPath();
			if(rpath.indexOf('srm') < 0){
				rpath += "/srm";
			}
			var url =rpath+'/fund/feeDetails?fundFlow='+fundFlow+"&projFlow="+projFlow+"&view=Y";
			var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='600px' height='350px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
			jboxMessager(iframe,'管理费',600 , 350);

		}
	</script>
<style type="text/css">
	.xllist td,.xllist th{text-align: left;padding-left: 15px;}
	.xllist td span{font-weight: bold;color:#333;font-size: 13px;}
	table{margin-bottom: 10px;}
</style>
</head>
<body>
    <div id="main">
        <div class="mainright">
            <div class="content">
            <div class="title1 clearfix">
                    <div>
			   	        <table class="xllist nofix">
			   		        <tr><th colspan="4" style="font-size: 14px;">项目信息</th></tr>
					        <tr>
			        	        <td width="30%"><span>项目名称：</span>${proj.projName}</td>
								<td width="22%"><span>课题编号：</span>${proj.projNo}</td>
			                    <td width="26%"><span>课题账号：</span>${proj.accountNo}</td>
			                    <td width="22%"><span>计划类别：</span>${proj.planTypeName}</td>
			                </tr>
					        <tr>
			        	        <td><span>承担单位：</span>${proj.applyOrgName}</td>
			                    <td><span>负责人：</span>${proj.applyUserName}</td>
			                    <td><span>起止日期：</span>${proj.projStartTime}~${proj.projEndTime}</td>
								<td><span>预算金额：</span>${pdfn:transMultiply(fundInfo.budgetAmount,10000)} (元)</td>
			                </tr>
							<tr>
								<td><span>到账金额：</span>${pdfn:transMultiply(fundInfo.realityAmount,10000)} (元)</td>
								<td><span>下拨到账金额：</span>${pdfn:transMultiply(fundInfo.realityGoveAmount,10000)} (元)</td>
								<td><span>预算到账金额：</span>${pdfn:transMultiply(fundInfo.realityOrgAmount,10000)} (元)</td>
								<td><span>剩余金额：</span>${pdfn:transMultiply(fundInfo.realityBalance,10000)}(元)</td>
							</tr>
							<tr>
								<td><span>已报销金额：</span><label id="paymentAmount"></label><%--${fundInfo.yetPaymentAmount}--%> (元)</td>
								<td><span>下拨已报销金额：</span><label id="paymentGove"></label><%--${fundInfo.yetPaymentGove}--%> (元)</td>
								<td><span>配套已报销金额：</span><label id="paymentOrg"></label><%--${fundInfo.yetPaymentOrg}--%> (元)</td>
								<td><span>转入个人账户金额：</span> ${pdfn:transMultiply(fundInfo.surplusFund,10000)} (元) </td>
							</tr>
				        </table>
	   		        </div>
	   		    </div>
            
                <p style="padding-top: 15px; padding-bottom:10px; font-weight: bold;">支出费用：</p>
				<table id="treeTable" class="xllist linetable">
  	                <thead>
                        <tr>
                            <th style="text-align: center" rowspan="2">报销项目</th>
                            <th style="text-align: center" colspan="3">预算金额(元)</th>
                            <th style="text-align: center" colspan="3">报销金额(元)</th>
                            <th style="text-align: center" colspan="3">剩余金额(元)</th>
                            <%--<th rowspan="2">备注</th>--%>
                        </tr>
					<tr>
						<th width="70px">总&#12288;计</th>
						<th width="70px">下拨金额</th>
						<th width="70px">配套金额</th>
						<th width="70px">总&#12288;计</th>
						<th width="70px">下拨金额</th>
						<th width="70px">配套金额</th>
						<th width="70px">总&#12288;计</th>
						<th width="70px">下拨金额</th>
						<th width="70px">配套金额</th>
					</tr>
                    </thead>
					<tbody id="fundTb">
					<c:forEach var="n" items="${calculateFormList}">
						<c:if test="${(empty n.itemPid) or (n.itemPid eq '0')}">
							<tr data-tt-id="${n.itemId}">
								<%--<c:set var="${n.itemId}" value="0"></c:set>--%>
								<td ondblclick="toggleTr(this)" style="text-align: left;cursor:pointer">${n.itemName}
								</td>
								<td>
										${pdfn:transMultiply(n.money,10000)}
								</td>
								<td>
										${pdfn:transMultiply(n.allocateMoney,10000)}
								</td>
								<td>
										${pdfn:transMultiply(n.matchingMoney==0?"--":n.matchingMoney,10000)}
								</td>
									<c:choose>
										<c:when test="${n.itemId eq 'guanlifei'}">
											<td style="color: #0000ff;cursor:pointer" onclick="editItem('${fundInfo.fundFlow}','${proj.projFlow}')">
													${pdfn:transMultiply(n.yetPaymentAmount,10000)}

											</td>
											<td style="color: #0000ff;cursor:pointer" onclick="editItem('${fundInfo.fundFlow}','${proj.projFlow}')">
													${pdfn:transMultiply(n.yetPaymentAllocate,10000)}
											</td>
											<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${n.fundDetailFlow}','Matching')">
													${pdfn:transMultiply(n.yetPaymentMatching,10000)}
											</td>
										</c:when>
										<c:otherwise>
											<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${n.fundDetailFlow}','')">
													${pdfn:transMultiply(n.yetPaymentAmount,10000)}

											</td>
											<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${n.fundDetailFlow}','Allocate')">
													${pdfn:transMultiply(n.yetPaymentAllocate,10000)}
											</td>
											<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${n.fundDetailFlow}','Matching')">
													${pdfn:transMultiply(n.yetPaymentMatching,10000)}
											</td>
										</c:otherwise>
									</c:choose>

								<td>
										${pdfn:transMultiply(n.balanceAmount,10000)}
								</td>
								<td>
										${pdfn:transMultiply(n.balanceAllocate,10000)}
								</td>
								<td>
										${pdfn:transMultiply(n.balanceMatching,10000)}
								</td>
								<%--<td>
										${n.remark}
								</td>--%>
							</tr>
							<c:forEach var="childDtl" items="${calculateFormList}">
								<c:if test="${(not empty childDtl.itemPid) and (n.itemId eq childDtl.itemPid)}">
									<tr data-tt-id="${childDtl.itemId}" data-tt-parent-id="${n.itemId}">
										<%--<c:set var="${n.itemId}" value="${n.itemId + childDtl.money}"></c:set>--%>
										<td ondblclick="toggleTr(this)" style="text-align: left;cursor:pointer">${childDtl.itemName}
										</td>
										<td>
												${pdfn:transMultiply(childDtl.money,10000)}
										</td>
										<td>
												${pdfn:transMultiply(childDtl.allocateMoney,10000)}
										</td>
										<td>
												${pdfn:transMultiply(childDtl.matchingMoney,10000)}
										</td>
										<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${childDtl.fundDetailFlow}','')">
											   ${pdfn:transMultiply(childDtl.yetPaymentAmount,10000)}
										</td>
										<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${childDtl.fundDetailFlow}','Allocate')">
												${pdfn:transMultiply(childDtl.yetPaymentAllocate,10000)}
										</td>
										<td style="color: #0000ff;cursor:pointer" onclick="paymentList('${childDtl.fundDetailFlow}','Matching')">
												${pdfn:transMultiply(childDtl.yetPaymentMatching,10000)}
										</td>
										<td>
												${pdfn:transMultiply(childDtl.balanceAmount,10000)}
										</td>
										<td>
												${pdfn:transMultiply(childDtl.balanceAllocate,10000)}
										</td>
										<td>
												${pdfn:transMultiply(childDtl.balanceMatching,10000)}
										</td>
										<%--<td>
												${childDtl.remark}
										</td>--%>
									</tr>
								</c:if>
							</c:forEach>

						</c:if>
					</c:forEach>
					</tbody>
                </table>
                <p align="center" style="width:100%;padding-top: 10px;">
  	                <input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();" />
                </p>
 	<%-- <c:set var="pageView" value="${pdfn:getPageView(reseachrepList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/> --%>
            </div>
        </div> 	
    </div>
</body>
</html>