<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres_en.jsp">
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>


<script type="text/javascript">


    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    //页面加载完成时调用
    $(function(){
        $("#suggestion").likeSearchInit({

        });
    });

    $(document).ready(function(){
		$('.datepicker').datepicker({format:'yyyy-mm-dd'});
		calculate();
	});

	/*
	 计算进修总费用
	 */
	function calculate(){
		//押金
		var according = Number($("#according").val());
		//工作服
		var coveralFee = Number($("#coverallNum").val())*Number(${workCloths});
		//学费
		var tuition = Number($("#tuition").val());

		$("#totalFee").val(Number(according+coveralFee+tuition));
	}


	//修改时间并重新计算费用
	function reCalculate(){
		jboxConfirm("Modify the time will recalculate the cost,Confirm?",function(){
			jboxPost("<s:url value='/gzzyjxres/hospital/reCalculate'/>",$("#auditForm").serialize(),
					function(data){
						if(data!="" && data!=null){
							$("#tuition").val(data);
						}
						jboxTip("After recalculation the cost is "+data);
						calculate();//重新计算总费用
					}
					,null,false);
		});

	}
	Date.prototype.Format = function (fmt) { //author: meizz
		var o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"h+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if(this=="")
			return "";
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}

	function changeDate(){
		$('.datepicker').datepicker({

		});
		$('.twoDate1').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(d) {
			var s=d.date.Format("yyyy-MM-dd");
			var e=$(this).parent().find(".twoDate2").val();
			if(s&&e&&s>e)
			{
				jboxTip("Start time can not be greater than the end time");
				$(this).val("");
				return false;
			}
		});
		$('.twoDate2').datepicker({format:'yyyy-mm-dd'}).on('changeDate', function(d) {
			var e= d.date.Format("yyyy-MM-dd");
			var s=$(this).parent().find(".twoDate1").val();
			if(s&&e&&s>e)
			{
				jboxTip("Start time can not be greater than the end time");
				$(this).val("");
				return false;
			}
		});
	}

	function audit(statusId,title){
		$("input[name='statusId']").val(statusId);
		if(title=="Admission"){

			if($("#tuition").val()==""){
				jboxTip("Please fill in the tuition!");
				return;
			}
			if($("#according").val()==""){
				jboxTip("Please fill in the deposit!");
				return;
			}
			if($("#coverallNum").val()==""){
				jboxTip("Please fill in the number of work clothes!");
				return;
			}
		}
		if(false == $("#payInfoForm").validationEngine("validate")){
			return;
		}
		if("${stuStatusEnumUnPassed.id}"==statusId||statusId=="${stuStatusEnumUnRecruitted.id}"){
			if($.trim($("#suggestion").val())==""){
				jboxTip("The audit opinion shall not be null!");
				return;
			}
		}

        console.log($("#auditForm").serialize());
		jboxConfirm("Confirm "+title+"?",function(){
			jboxPost("<s:url value='/gzzyjxres/hospital/auditOptionEn'/>",$("#auditForm").serialize(),
					function(resp){
						if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
							window.parent.search();
							jboxClose();
						}
					}
					,null,true);
		});
	}
	//退回操作
	function returnInfo(resumeFlow){
		if(false == $("#payInfoForm").validationEngine("validate")){
			return;
		}
		if($.trim($("#suggestion").val())==""){
			jboxTip("The audit opinion shall not be null!");
			return;
		}
		var reason = $("#suggestion").val();
		var userFlow = "${user.userFlow}";
		jboxConfirm("Confirmation of return?", function(){
			jboxPost("<s:url value='/gzzyjxres/hospital/returnInfo'/>",{"resumeFlow":resumeFlow,"reason":reason,"userFlow":userFlow}, function(resp){
				jboxTip(resp);
				window.parent.search();
				jboxClose();
			} , null , true);
		});
	}
</script>

<form id="auditForm">
	<input type="hidden" value="${stuUser.resumeFlow}" name="resumeFlow"/>
	<input type="hidden" value="${user.userFlow}" name="userFlow"/>
	<input type="hidden" name="statusId">
<div class="infoAudit2" style="padding:0;">
	<div class="main_bd">
		<ul class="div_table">
			<li class="score_frame">
				<div class="div_table">
					<h4>Essential Information</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<colgroup>
								<col width="20%"/>
								<col width="20%"/>
								<col width="20%"/>
								<col width="20%"/>
								<col width="20%"/>
							</colgroup>
							<tr>
								<th>Name:</th>
								<td>${extInfo.userName}</td>
								<th>Date Of Birth:</th>
								<td>${extInfo.userBirthday}</td>
								<td rowspan="5" style="text-align: center;padding-top:5px;">
									<img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}"style="max-width: 130px;max-height: 170px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
								</td>
							</tr>
							<tr>
								<th>Gender:</th>
								<td>${extInfo.sexId}</td>
								<th>Age:</th>
								<td>${stuUser.userAge}</td>
							</tr>
							<tr>
								<th>Phone Number:</th>
								<td>${extInfo.userPhone}</td>
								<th>China Phone Number:</th>
								<td>
									${extInfo.chinaPhone}
								</td>
							</tr>
							<tr>
								<th>Email:</th>
								<td>${extInfo.userEmail}</td>
								<th>Nationality:</th>
								<td>
									${user.nationalityId}
								</td>
							</tr>
							<tr>
								<th>Work Clothes:</th>
								<td>${extInfo.workClother}</td>
								<th>Size Of Work Clothes:</th>
								<td>${stuUser.clotherSizeId}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>Department Of Study Purpose</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="grid">
							<colgroup>
								<col width="50%"/>
								<col width="50%"/>
							</colgroup>
							<tr style="font-weight: bold;">
								<th style="text-align: center;">Department</th>
								<th style="text-align: center;">The Length</th>
							</tr>

							<c:forEach var="speForm" items="${extInfo.speFormList}" varStatus="status">
								<tr>
									<td>
										<input name="extInfo.speFormList[${status.index}].speId" class="input" readonly="readonly" <c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">disabled="disabled"</c:if> style="width: 360px;margin-left: 0px;" value="${speForm.speId}"></input>
									</td>
									<td>
										<input   onchange="changeDate()" class="input datepicker twoDate1" name="extInfo.speFormList[${status.index}].beginDate" type="text" value="${speForm.beginDate}" readonly="readonly" <c:if test="${stuUser.stuStatusId ne stuStatusEnumPassed.id or param.isHide eq 'Y' or flag eq 'Y'}">disabled="disabled"</c:if> style="width: 40%"/>-
										<input   onchange="changeDate()" class="input datepicker twoDate2" name="extInfo.speFormList[${status.index}].endDate" type="text"  value="${speForm.endDate}" readonly="readonly" <c:if test="${stuUser.stuStatusId ne stuStatusEnumPassed.id or param.isHide eq 'Y' or flag eq 'Y'}">disabled="disabled"</c:if> style="width: 40%"/>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>

				<div class="div_table">
					<h4>Experience In Medical Education</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="grid">
							<colgroup>
								<col width="23%"/>
								<col width="20%"/>
								<col width="20%"/>
								<col width="10%"/>
								<col width="10%"/>
								<col width="10%"/>
							</colgroup>
							<tr style="font-weight: bold;">
								<th style="text-align: center;">Beginning And Ending Time</th>
								<th style="text-align: center;">Name Of School</th>
								<th style="text-align: center;">Name Of Major</th>
								<th style="text-align: center;">Length Of Schooling</th>
								<th style="text-align: center;">Record Of Formal Schooling</th>
								<th style="text-align: center;">Degree</th>
							</tr>
							<c:forEach items="${eduDateList}" var="eduDate">
								<c:forEach var="resume" items="${extInfo.educationList}" varStatus="status">
									<c:if test="${eduDate eq resume.eduRoundDate}">
										<tr>
											<td>${resume.eduRoundDate}</td>
											<td>${resume.schoolName}</td>
											<td>${resume.speName}</td>
											<td>${resume.length}</td>
											<td>${resume.education}</td>
											<td>${resume.degree}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>Experience In Medical Related Social(Work) </h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="grid">
							<colgroup>
								<col width="22%"/>
								<col width="22%"/>
								<col width="22%"/>
								<col width="22%"/>
							</colgroup>
							<tr style="font-weight: bold;">
								<th>Beginning And Ending Time</th>
								<th>Name Of The Company</th>
								<th>Major In Work</th>
								<th>Position</th>
							</tr>
							<c:forEach items="${workDateList}" var="workDate">
								<c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">
									<c:if test="${workDate eq resume.clinicalRoundDate}">
										<tr>
											<td>${resume.clinicalRoundDate}</td>
											<td>${resume.hospitalName}</td>
											<td>${resume.workDescription}</td>
											<td>${resume.postName}</td>
										</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>Documents And Files</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<colgroup>
								<col width="40%"/>
								<col width="60%"/>
							</colgroup>
							<tr>
								<th><font color="red" id="zgzs"></font>Passport:</th>
								<td colspan="3">
									<%--${empty extInfo.passportNo?"Nonting":extInfo.passportNo}&#12288;--%>
									<c:if test='${not empty extInfo.passportUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.passportUri}" target="_blank">View Picture</a>]&#12288;
										    	</span>
									</c:if>
								</td>
							</tr>
							<tr class="yszz">
								<th>Medical Certificate Or Medical License:</th>
								<td colspan="3">
									<%--${empty extInfo.certifiedNo?"Nonthing":extInfo.certifiedNo}&#12288;--%>
									<c:if test='${not empty extInfo.certifiedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">View Picture</a>]&#12288;
										    	</span>
									</c:if>
								</td>
							</tr>
							<c:if test="${empty extInfo.regList}">
								<tr>
								<th>Other Qualification Certificat:</th>
								<td></td>
								</tr>
							</c:if>
							<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
								<tr>
									<th>Other Qualification Certificat${status.index+1}:</th>
									<td>
											<%--${empty reg.regNo?"Nonthing":reg.regNo}--%>
												<span>
													[<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">View Picture</a>]&#12288;
										    	</span>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<c:if test="${stuUser.stuStatusId ne stuStatusEnumPassing.id and stuUser.stuStatusId ne stuStatusEnumUnPassed.id}">
	<div class="div_table">
		<form id="payInfoForm" enctype="multipart/form-data">
			<h4>Payment Information</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th>Deposit:</th>
					<td>
						<input id="according" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if>--%> type="text" name="extInfo.according" value="${deposit}"  style="width: 150px;height: 22px; margin-left: 0px"/>CNY
					</td>
					<th>Tuition Fee:</th>
					<td>
						<input id="tuition" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if>--%> type="text" name="extInfo.tuition" value="${tuition}"  onchange="calculate()" style="width: 150px;height: 22px;margin-left: 0px"/>CNY
					</td>
				</tr>
				<tr>
					<th>Work Uniform:</th>
					<td>
						<input id="coverallNum" class='input validate[custom[integer],min[0]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.coverallNum" value="${extInfo.coverallNum}" placeholder="number"  onchange="calculate()" style="width: 150px;height: 22px;margin-left: 0px"/>Piece
					</td>
					<th>Total Cost:</th>
					<td>
						<input id="totalFee" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if>--%> type="text" <%--name="extInfo.totalFee" value="${extInfo.totalFee}"--%>  style="width: 150px;height: 22px;margin-left: 0px"/>CNY
					</td>
				</tr>
			</table>
		</form>
	</div>
    </c:if>
	<div class="div_table" <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">style="display: none;" </c:if>>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		    <caption style="text-align: left;font-weight: bolder;">Audit Opinion</caption>
			<tr>
                <td colspan="4" style="padding-top:10px;padding-left:0;">
                    <input id="suggestion" style="width: 250px" class="toggleView input" type="text" name="reason" value="${param.reason}" autocomplete="off" title="${param.reason}" onmouseover="this.title = this.value"/>
                    <%--<input id="speId"  type="hidden" name="speId" value="${param.speId}"/>--%>
                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left:4px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 250px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${dictTypeEnumGzzyjxSuggestionList}" var="dict">
                                    <p class="item"   value="${dict.dictDesc}" style="height: 30px;padding-left: 10px;">${dict.dictDesc}</p>
                                </c:forEach>
                        </div>
                    </div>
                </td>
				    <%--<select id="auditAgree" name="reason" class="select" style="width: 150px">--%>
						<%--<option value=""></option>--%>
						<%--<c:forEach items="${dictTypeEnumGzzyjxSuggestionList}" var="dict">--%>
							<%--<option value="${dict.dictDesc}">${dict.dictDesc}</option>--%>
						<%--</c:forEach>--%>
					<%--</select>--%>
			</tr>
		</table>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">
			<input type="button" style="width:100px;" class="btn_green" onclick="audit('${stuStatusEnumPassed.id}','Audited');" value="Agree"/>
			<input type="button" style="width:100px;" class="btn_red" onclick="audit('${stuStatusEnumUnPassed.id}','Audit not through');" value="Disagree"/>
			<input type="button" style="width:100px;" class="btn_green" onclick="returnInfo('${stuUser.resumeFlow}')" value="Return"/>
			</c:if>
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassed.id}">
			<input type="button" style="width:120px;" class="btn_green" onclick="reCalculate();" value="ReCalculate"/>
			<input type="button" style="width:100px;" class="btn_green" onclick="audit('${stuStatusEnumRecruitted.id}','Admission');" value="Admission"/>
			<input type="button" style="width:150px;" class="btn_red" onclick="audit('${stuStatusEnumUnRecruitted.id}','Non admission');" value="Non admission"/>
			</c:if>
			<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();" value="Close"/>
		</div>
	</div>
</div>
</form>
