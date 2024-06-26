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
</jsp:include>
<script type="text/javascript">
	function nextOpt(step){
		if(false==$("#projForm").validationEngine("validate")){
			return false;
		}
		var form = $('#projForm');
		var action = form.attr('action');
		action+="?nextPageName="+step;
		form.attr("action" , action);
		form.submit();
	}

	function defaultIfEmpty(obj){
		if(isNaN(obj)){
			return 0;
		}else{
			return obj;
		}
	}

	function setTotalAmount(){
		var amountFund = 0.0;
		var appAmount = parseFloat( $("input[name='appAmount']").val()) ;
		var orgBuildAmount = parseFloat( $("input[name='orgBuildAmount']").val()) ;
		var deptBuildAmount = parseFloat( $("input[name='deptBuildAmount']").val()) ;
		var otherAmount = parseFloat( $("input[name='otherAmount']").val()) ;
		amountFund =  defaultIfEmpty(appAmount) + defaultIfEmpty(orgBuildAmount) + defaultIfEmpty(deptBuildAmount) + defaultIfEmpty(otherAmount);
		$("input[name='totalAmount']").val(parseFloat(amountFund.toFixed(3)));
	}

	function setTotal(op){
		var amountFund = 0.0;
		var opone = parseFloat( $("input[name='one_"+op+"']").val()) ;
		var optwo= parseFloat(  $("input[name='two_"+op+"']").val()) ;
		var opthree =  parseFloat( $("input[name='three_"+op+"']").val()) ;
		var opfour =  parseFloat( $("input[name='four_"+op+"']").val()) ;
		var opfive =  parseFloat( $("input[name='five_"+op+"']").val()) ;
		var opsix =  parseFloat( $("input[name='six_"+op+"']").val()) ;
		var opseven =  parseFloat( $("input[name='seven_"+op+"']").val()) ;
		var opeight =  parseFloat( $("input[name='eight_"+op+"']").val()) ;
		var opnine =  parseFloat( $("input[name='nine_"+op+"']").val()) ;
		var opten =  parseFloat( $("input[name='ten_"+op+"']").val()) ;
		amountFund =  defaultIfEmpty(opone) + defaultIfEmpty(optwo) + defaultIfEmpty(opthree) + defaultIfEmpty(opfour) + defaultIfEmpty(opfive) +
				defaultIfEmpty(opsix) + defaultIfEmpty(opseven) + defaultIfEmpty(opeight) + defaultIfEmpty(opnine) + defaultIfEmpty(opten);
		$("input[name='eleven_"+op+"']").val(parseFloat(amountFund.toFixed(3)));
	}
</script>
<style type="text/css">
 .bs_tb tbody td input{text-align: left;margin-left: 10px;}
</style>
</head>
<body>
	<div id="main">
		<div class="mainright">
			<div class="content">
			   <div style="margin-top: 10px;">
					<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
						<input type="hidden" id="pageName" name="pageName" value="step10" />
						<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
						<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
						<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
						<font style="font-size: 14px; font-weight:bold;color: #333;">十、经费预算</font>
						<table class="basic" style="width: 100%; margin-top: 10px;">
							<tr>
								<td style="text-align: center;">申请资助经费</td>
								<td style="text-align: left;" colspan="5"><input type="text" name="appAmount" value="${resultMap.appAmount}" onchange="setTotalAmount()" class="inputText validate[custom[number]]" style="width: 100px;"/> （万元）</td>
							</tr>
							<tr>
								<td style="text-align: center;">单位建设经费</td>
								<td style="text-align: left;" colspan="5"><input type="text" name="orgBuildAmount" value="${resultMap.orgBuildAmount}" onchange="setTotalAmount()" class="inputText validate[custom[number]]" style="width: 100px;"/> （万元）</td>
							</tr>
							<tr>
								<td style="text-align: center;">主管部门建设经费</td>
								<td style="text-align: left;" colspan="5"><input type="text" name="deptBuildAmount" value="${resultMap.deptBuildAmount}" onchange="setTotalAmount()" class="inputText validate[custom[number]]" style="width: 100px;"/> （万元）</td>
							</tr>
							<tr>
								<td style="text-align: center;">其他来源经费</td>
								<td style="text-align: left;" colspan="5"><input type="text" name="otherAmount" value="${resultMap.otherAmount}" onchange="setTotalAmount()" class="inputText validate[custom[number]]" style="width: 100px;"/> （万元）</td>
							</tr>
							<tr>
								<td style="text-align: center;">合&#12288;计</td>
								<td style="text-align: left;" colspan="5"><input type="text" name="totalAmount" value="${resultMap.totalAmount}" class="inputText" readonly="readonly" title="自动计算合计，不可修改" style="width: 100px;"/> （万元）</td>
							</tr>
							<tr>
								<td style="text-align: left; font-weight: bold;" width="170px;">经费预算支出科目</td>
								<td style="text-align: center;font-weight: bold;">第一年</td>
								<td style="text-align: center;font-weight: bold;">第二年</td>
								<td style="text-align: center;font-weight: bold;">第三年</td>
								<td style="text-align: center;font-weight: bold;">第四年</td>
								<td style="text-align: center;font-weight: bold;">第五年</td>
							</tr>
							<tr>
								<td style="text-align: left;">一、国内外进修费用</td>
								<td style="text-align: center;"><input type="text" name="one_one" value="${resultMap.one_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="one_two" value="${resultMap.one_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="one_three" value="${resultMap.one_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="one_four" value="${resultMap.one_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="one_five" value="${resultMap.one_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">二、学术交流费用</td>
								<td style="text-align: center;"><input type="text" name="two_one" value="${resultMap.two_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="two_two" value="${resultMap.two_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="two_three" value="${resultMap.two_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="two_four" value="${resultMap.two_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="two_five" value="${resultMap.two_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">三、导师指导研究工作费用</td>
								<td style="text-align: center;"><input type="text" name="three_one" value="${resultMap.three_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="three_two" value="${resultMap.three_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="three_three" value="${resultMap.three_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="three_four" value="${resultMap.three_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="three_five" value="${resultMap.three_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;" colspan="6">四、研究项目费用</td>
							</tr>
							<tr>
								<td style="text-align: left;">1、仪器设备费</td>
								<td style="text-align: center;"><input type="text" name="four_one" value="${resultMap.four_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="four_two" value="${resultMap.four_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="four_three" value="${resultMap.four_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="four_four" value="${resultMap.four_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="four_five" value="${resultMap.four_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">2、实验材料费</td>
								<td style="text-align: center;"><input type="text" name="five_one" value="${resultMap.five_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="five_two" value="${resultMap.five_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="five_three" value="${resultMap.five_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="five_four" value="${resultMap.five_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="five_five" value="${resultMap.five_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">3、实验动物费</td>
								<td style="text-align: center;"><input type="text" name="six_one" value="${resultMap.six_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="six_two" value="${resultMap.six_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="six_three" value="${resultMap.six_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="six_four" value="${resultMap.six_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="six_five" value="${resultMap.six_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">4、业务费</td>
								<td style="text-align: center;"><input type="text" name="seven_one" value="${resultMap.seven_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="seven_two" value="${resultMap.seven_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="seven_three" value="${resultMap.seven_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="seven_four" value="${resultMap.seven_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="seven_five" value="${resultMap.seven_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">5、购买图书、资料费</td>
								<td style="text-align: center;"><input type="text" name="eight_one" value="${resultMap.eight_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eight_two" value="${resultMap.eight_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eight_three" value="${resultMap.eight_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eight_four" value="${resultMap.eight_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eight_five" value="${resultMap.eight_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">6、其他研究费用</td>
								<td style="text-align: center;"><input type="text" name="nine_one" value="${resultMap.nine_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="nine_two" value="${resultMap.nine_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="nine_three" value="${resultMap.nine_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="nine_four" value="${resultMap.nine_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="nine_five" value="${resultMap.nine_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;">五、其他费用</td>
								<td style="text-align: center;"><input type="text" name="ten_one" value="${resultMap.ten_one}" onchange="setTotal('one')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="ten_two" value="${resultMap.ten_two}" onchange="setTotal('two')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="ten_three" value="${resultMap.ten_three}" onchange="setTotal('three')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="ten_four" value="${resultMap.ten_four}" onchange="setTotal('four')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="ten_five" value="${resultMap.ten_five}" onchange="setTotal('five')" class="inputText validate[custom[number]]" style="width: 80%;"/></td>
							</tr>
							<tr>
								<td style="text-align: left;font-weight:bold;">合&#12288;计</td>
								<td style="text-align: center;"><input type="text" name="eleven_one" value="${resultMap.eleven_one}" class="inputText" readonly="readonly" title="自动计算合计，不可修改" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eleven_two" value="${resultMap.eleven_two}" class="inputText" readonly="readonly" title="自动计算合计，不可修改" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eleven_three" value="${resultMap.eleven_three}" class="inputText" readonly="readonly" title="自动计算合计，不可修改" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eleven_four" value="${resultMap.eleven_four}" class="inputText" readonly="readonly" title="自动计算合计，不可修改" style="width: 80%;"/></td>
								<td style="text-align: center;"><input type="text" name="eleven_five" value="${resultMap.eleven_five}" class="inputText" readonly="readonly" title="自动计算合计，不可修改" style="width: 80%;"/></td>
							</tr>
						</table>

						<table class="basic tbdashed" style="width: 100%; margin-top: 10px;border:1px dashed #f2d4b2;">
							<tr><td style="border:1px dashed #f2d4b2;font-weight:bold;">填表说明：</td></tr>
							<tr><td style="border:1px dashed #f2d4b2;">&#12288;1、经费预算请遵照《省政府关于深化省级财政科研项目和资金管理改革的意见》（苏政发〔2015〕15号）编制。</td></tr>
							<tr><td style="border:1px dashed #f2d4b2;">&#12288;2、仪器设备费：指项目专用仪器的购置费和运杂、包装、安装费、自制仪器设备的材料、配件和外协加工费。</td></tr>
							<tr><td style="border:1px dashed #f2d4b2;">&#12288;3、大型仪器设备应充分利用本单位、本地区现有条件。</td></tr>
							<tr><td style="border:1px dashed #f2d4b2;">&#12288;4、交通运输设备一般不得列入，如特殊需要应说明理由，经批准后方可购买。</td></tr>
							<tr><td style="border:1px dashed #f2d4b2;">&#12288;5、单台件在二千元以上的仪器设备须逐项填写名称、规格、型号、单价、数量。</td></tr>
							<tr><td style="border:1px dashed #f2d4b2;">&#12288;6、实验材料费：指科研用消耗性材料、试剂、药品等购置等，标本、样品采集加工和运杂包装费。</td></tr>
						</table>
					</form>
					<div class="button" style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
						<input id="prev" type="button" onclick="nextOpt('step9_1')" class="search" value="上一步"/>
						<input id="nxt" type="button" onclick="nextOpt('step11')" class="search" value="下一步"/>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>