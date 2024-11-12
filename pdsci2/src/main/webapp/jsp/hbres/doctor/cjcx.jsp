<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="true"/>
</jsp:include>

<script type="text/javascript">
$(document).ready(function(){
	var fcombobox = $("#orgFlow").scombobox({
		forbidInvalid : true,
		invalidAsValue : true,
		expandOnFocus : false
	});
	$(".scombobox-display").addClass("validate[required]");
	initBindSpeInfo(fcombobox , "speId");
    initBindOrgInfo();
    <c:if test="${GlobalConstant.FLAG_Y eq applicationScope.sysCfgMap['res_hbres_on'] && GlobalConstant.FLAG_Y eq confirmFlag }">
        <c:if test="${sysCfgMap[rotationSwitch] eq GlobalConstant.RECORD_STATUS_Y}">
            initTrain('${recruitFlow}');
        </c:if>
    </c:if>
});

    function initBindSpeInfo(combobox , speId){
        combobox.bind("change" , function(){
            var orgFlow = $(this).val();
            var url = "<s:url value='/hbres/singup/doctor/findspe'/>?orgFlow="+orgFlow;
            jboxGet(url , null , function(resp){
                $("#"+speId).empty();
                $("#"+speId).append("<option value=''>请选择</option>");
                $.each(resp , function(i , n){
//               临时逻辑，只显示全科医学科
//                 if(n.speName == '全科医学科'){
                     $("#"+speId).append("<option value='"+n.recordFlow+"'>"+n.speName+"</option>");
//                 }
                });
            } , null , false);
        });
    }

    function initBindOrgInfo(){
        $("#spe_1").bind("change" , function(){
            var speId = $(this).val();
            var url = "<s:url value='/hbres/singup/doctor/findorg'/>?speId="+speId;
            jboxGet(url , null , function(resp){
                console.log(resp)
                $("#org_1").empty();
                $("#org_1").append("<option value=''>请选择</option>");
                $.each(resp , function(i , n){
                    $("#org_1").append("<option value='"+n.orgFlow+"' recordFlow='"+n.recordFlow+"'>"+n.orgName+"</option>");
                });
            } , null , false);
        });
    }
    
	function submitRecruit(){
        if(!$("#recruitForm").validationEngine("validate")){
            return
        }
        jboxConfirm("确认提交？" , function(){
            var speId = $("#org_1 option:selected").attr("recordFlow");
            $("#speId_1").val(speId);
            jboxStartLoading();
            $("#recruitForm").submit();

        });
	}

    function submitRegist(){
        jboxPost('<s:url value="/hbres/singup/doctor/checkInfo?doctorFlow=${doctorRecruit.doctorFlow}"/>',null,function(resp){
            if(resp=='1'){
                jboxOpen('<s:url value="/hbres/singup/doctor/comleteInfo?doctorFlow=${doctorRecruit.doctorFlow}"/>',"请补全个人信息",600,500,true);

            }
        },null,false);

        if(!$("#registerForm").validationEngine("validate")){
            return;
        }
        jboxConfirm("确认提交？" , function(){
                jboxStartLoading();
                $("#registerForm").submit();
        });
    }

    function delRegist(flow){
        jboxConfirm("确认撤销？" , function(){
            jboxStartLoading();
           jboxPost('<s:url value="/hbres/singup/delRegist?recordFlow="/>'+flow,null,function(resp){
               if(resp==1) jboxTip("操作成功");
               window.location.reload();
           },null,false)
        });
    }
	
	function showIntro(){
		var swapFlag = $("#swapFlag");
		if(swapFlag.attr("checked")){
			jboxConfirm("勾选后表示当您未被当前填报专业录取后同意院方调剂至其他专业!" , function(){
			}  , function(){
				swapFlag.attr("checked" , false);
			});
		}
	}
	
	function confirmRecruit(recruitFlow , confirmFlag){
		var tip = "确认";
		if(confirmFlag=="Y"){
			tip=tip+"录取？";
		}else if(confirmFlag=="N"){
			tip = tip+"拒绝录取？";
		}
		jboxConfirm(tip , function(){
			jboxPost("<s:url value='/hbres/singup/doctor/confirmrecruit'/>" , {"recruitFlow":recruitFlow,"confirmFlag":confirmFlag} , function(resp){
				if(resp=="1"){
					jboxTip("操作成功");
					location.href="<s:url value='/hbres/singup/doctor/scoreSearch'/>?recruitFlow="+recruitFlow;
				}else{
					jboxTip("操作失败");
				}
			} , null , false);
		});
	}
	
	function delRecruit(recruitFlow){
		jboxConfirm("确认撤销？", function(){
			jboxGet("<s:url value='/hbres/singup/doctor/delrecruit'/>?recruitFlow="+recruitFlow , null , function(resp){
				if(resp=="1"){
					top.jboxTip("操作成功");
					location.href="<s:url value='/hbres/singup/doctor/scoreSearch'/>";
				}else{
					jboxTip("操作失败");
				}
			} , null , false);
		});
	}
	
	function showJidiRecruitInfo(){
		jboxOpen("<s:url value='/hbres/singup/doctor/showjidirecruitinfo'/>","基地招录指标",1000,500);
	}

    function showJidiLine(){
        jboxOpen("<s:url value='/hbres/singup/doctor/showJidiLine'/>","基地招生划线",1000,500);
    }
	
	function removeMsg(){
		$("#msg").html("");
	}
	
	function initTrain(recruitFlow){
		window.open("<s:url value='/hbres/singup/doctor/initTrain'/>?recruitFlow="+recruitFlow);
	}

    function changeInfo(doctorFlow){
        var url = "<s:url value='/hbres/singup/changeInfo'/>?doctorFlow="+doctorFlow;
        var mainIframe = window.parent.frames["mainIframe"];
        var width = 1000;
        var height = 550;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'用户信息',width,height);
    }
</script>
</head>

<body>
  <div class="main_wrap">
      <c:if test="${!noTestFlag && isUnderLine ne 'Y'}">
        <div class="zy_contain bs_search">
          <h1>笔试成绩查询</h1>
          <ul>
            <li class="score">您的笔试分数为：<span class="score_inner">${examDoctor.examResult}</span>分</li>
            <li>${fillMsg}</li>
          </ul>
        </div>
      </c:if>
    <c:if test="${noTestFlag && !empty fillMsg}">
        <div class="zy_contain bs_search">
            <h1>招录时间</h1>
            <ul>
                <li style="font-size: 22px">${fillMsg}</li>
            </ul>
        </div>
    </c:if>
    <c:if test="${showCfgDate!='N'}">
        <div class="zy_contain cjcx_tips"  style="font-size: 22px">
            <c:if test="${isUnderLine ne 'Y'}">
                <span style="width: 46%"><img src="<s:url value='/jsp/hbres/images/ui-tips.png'/>"/>&nbsp;<font>志愿填报日期：</font>${recruitCfg.wishBeginDate}~${recruitCfg.wishEndDate}</span>
                <span style="width: 46%"><img src="<s:url value='/jsp/hbres/images/ui-tips.png'/>"/>&nbsp;<font>确认录取结果日期：</font>${recruitCfg.admitBeginDate}~${recruitCfg.admitEndDate}</span>
                <span style="width: 46%"><img src="<s:url value='/jsp/hbres/images/ui-tips.png'/>"/>&nbsp;<font>学员调剂日期：</font>${recruitCfg.swapBeginDate}~${recruitCfg.swapEndDate}</span>
            </c:if>
            <span style="width: 46%"><img src="<s:url value='/jsp/hbres/images/ui-tips.png'/>"/>&nbsp;<font>开放注册和报到日期：</font>${recruitCfg.registrationBeginDate}~${recruitCfg.registrationEndDate}</span>
        </div>
    </c:if>

      <!-- 报到开始 -->
      <%--原逻辑：线上和线下注册人员进入报到--%>
      <c:if test="${isUnderLine eq 'Y' || doctorRecruit.confirmFlag eq 'Y'}">
      <%--临时逻辑：线上不可报到 线下注册人员进入报到--%>
      <%--<c:if test="${doctorRecruit.confirmFlag eq 'Y'}">--%>
          <c:if test="${isUnderLine ne 'Y'}">
              <script>
                  $(function(){
                      var orgFlow ='${doctorRecruit.orgFlow}';
                      var speId ='${doctorRecruit.speId}';
                      $("#orgFlow option[value="+orgFlow+"]").attr('selected','selected');
                  })
              </script>
          </c:if>
          <form id="registerForm" method="post" action="<s:url value='/hbres/singup/submitRegist'/>">
              <div class="zy_contain bs_tbzy">
                  <h1>报到信息${(!isRegistrationDate)?'(不在报到时段)':''}</h1>
                  <table cellpadding="0" cellspacing="0" border="0" class="grey_tab">
                      <tr>
                          <th>序号</th>
                          <th>基地${doctorRecruit.orgFlow}</th>
                          <th>专业</th>
                          <th>操作/结果</th>
                      </tr>
                      <c:set var="isNotPassed" value="N"></c:set>
                      <c:forEach items="${registerList}" var="register" varStatus="s">
                          <tr>
                              <td>${s.index+1}</td>
                              <td>${register.orgName}</td>
                              <td>${register.speName}</td>
                              <td <c:if test="${register.auditStatusId eq baseStatusEnumNotPassed.id}">title="退回原因：${register.auditInfo}"
                                    <c:set var="isNotPassed" value="Y"></c:set>
                                    <c:set var="doctorFlow" value="${register.doctorFlow}"></c:set>
                                  </c:if>
                              >
                                      ${register.auditStatusName}
                                  <c:if test="${register.auditStatusId eq baseStatusEnumAuditing.id}">
                                      <br/><a class="btn_blue" href="javascript:void(0);" onclick="delRegist('${register.recordFlow}');">撤销</a>
                                  </c:if>
                              </td>
                          </tr>
                      </c:forEach>
                      <c:if test='${canRegist && isRegistrationDate}'>
                          <tr class="odd">
                              <td>${registerList.size()+1}</td>
                              <td style="width: 300px;">
                                  <c:if test="${isUnderLine eq 'Y'}">
                                  <select class="validate[required]" id="orgFlow" name="orgFlow" style="width:230px;">
                                      <option value="请选择">请检索</option>
                                      <c:forEach var="sysOrg" items="${hospitals}">
                                          <option value="${sysOrg.orgFlow}">${sysOrg.orgName}</option>
                                      </c:forEach>
                                  </select>
                                  </c:if>
                                  <c:if test="${isUnderLine ne 'Y'}">
                                      ${doctorRecruit.orgName}
                                  </c:if>
                              </td>
                              <td>
                                <c:if test="${isUnderLine eq 'Y'}">
                                  <select id="speId" name="speId" class="validate[required] select" style=" width:230px;" onchange="removeMsg();">
                                      <option value="">请选择</option>
                                  </select>
                                </c:if>
                                  <c:if test="${isUnderLine ne 'Y'}">
                                      ${doctorRecruit.speName}
                                  <input name="orgFlow2" type="hidden" value="${doctorRecruit.orgFlow}"/>
                                  <input name="speId2" type="hidden" value="${doctorRecruit.speId}"/>
                                  <input name="speName2" type="hidden" value="${doctorRecruit.speName}"/>
                                  </c:if>
                              </td>
                              <td></td>
                          </tr>
                      </c:if>
                  </table>
                  <c:if test='${canRegist && isRegistrationDate}'>
                      <div class="button">
                          <input type="button" class="btn_blue" style=" width:100px;" onclick="submitRegist();" value="提交"/>
                          &#12288;<input type="button" class="btn_blue" style=" width:100px;" onclick="changeInfo('${empty doctorFlow ?sessionScope.currUser.userFlow :doctorFlow}');" value="修改信息"/>
                      </div>
                  </c:if>
              </div>
          </form>
      </c:if>
      <!-- 报到结束 -->

    <c:if test='${isShowRecruits && isUnderLine ne "Y"}'>
    <form id="recruitForm" name="recruitForm" method="post" action="<s:url value='/hbres/singup/doctor/submitrecruit'/>">
    <input type="hidden" name="examResult" value="${examDoctor.examResult}"/>
    <div class="zy_contain bs_tbzy">
      <h1>填报志愿
          <span style="color: red;font-size: 18px;" id="msg">
          	<c:choose>
          		<c:when test="${msg eq '0' }">志愿填报成功</c:when>
          		<c:when test="${msg eq '1' }">该基地所填报志愿专业人数已满</c:when>
          		<c:when test="${msg eq '-1' }">所填志愿专业没有招录计划</c:when>
          		<c:when test="${msg eq '-2' }">${gradeBorderlineOrgName}设置了独立分数线${gradeBorderlineOrg}分，您的分数未达标，不能填报</c:when>
          		<c:otherwise>
                </c:otherwise>
          	</c:choose>
          </span>
          <%--<c:if test='${isSwapFill eq "Y" }'>--%>
              <%--<span class="fr" style="margin-right:10px;margin-top:13px;"><a href="javascript:void(0);" class="btn_green" onclick="showJidiLine();">各基地招生划线</a></span>--%>
              <span class="fr" style="margin-right:10px;margin-top:13px;"><a href="javascript:void(0);" class="btn_green" onclick="showJidiRecruitInfo();">查看基地指标</a></span>
          <%--</c:if>--%>
      </h1>
      <table cellpadding="0" cellspacing="0" border="0" class="grey_tab">
          <tr>
            <th>序号</th>
            <th>专业</th>
            <th>基地</th>
            <th>服从专业调剂</th>
            <th>填报类型</th>
            <th>技能<br/>/面试成绩</th>
            <th>总成绩<br/>(理论成绩折合成100分)</th>
            <th>操作/结果</th>
          </tr>
          <c:set var="inx" value="0"></c:set>
          <c:set var="confirmFlag" value="" />
          <c:set var="recruitFlow" value="" />
          <c:forEach items="${doctorRecruits}" varStatus="status" var="recruit">
             <c:set var="inx" value="${status.count}"></c:set>
             <c:set var="confirmFlag" value="${recruit.confirmFlag }" />
             <c:if test="${ recruit.recruitFlag eq 'Y'}"> 
             	<c:set var="recruitFlow" value="${recruit.recruitFlow }" />
             	<c:set value="jswjw_${recruit.orgFlow}_P001" var="rotationSwitch"/>
             </c:if>
             <tr>
              <td>${status.count}</td>
              <td >
                 ${recruit.speName}
            </td>
            <td>
                ${recruit.orgName}
            </td>
            <td><input name="swapFlag" type="checkbox" readonly="readonly" disabled="disabled" <c:if test='${recruit.swapFlag eq "Y"}'>checked="checked"</c:if>/></td>
            <td>${recruit.recruitTypeName}</td>
            <td>
                <c:if test="${showScoreMap[recruit.orgFlow] eq 'Y'}">
                    ${recruit.operResult}/${recruit.auditionResult}
                </c:if>
                <c:if test="${showScoreMap[recruit.orgFlow] ne 'Y'}">
                    未发布
                </c:if>
            </td>
            <td><c:if test="${showScoreMap[recruit.orgFlow] eq 'Y'}">${recruit.totleResult}</c:if>
                <c:if test="${showScoreMap[recruit.orgFlow] ne 'Y'}">
                    未发布
                </c:if>
            </td>
            <td style="color: red">
                <c:choose>
                    <c:when test='${recruit.confirmFlag eq "F"}'>
                        <span>过期</span>
                    </c:when>
                    <c:when test='${recruit.confirmFlag eq "N"}'>
                        <span>放弃录取</span>
                    </c:when>
                    <c:when test='${recruit.confirmFlag eq "Y"}'>
                        <span>已确认(请按录取通知时间报道)</span>
                    </c:when>
                    <c:when test='${recruit.recruitFlag eq "N"}'>
                        <span>未录取</span>
                    </c:when>
                    <c:when test="${recruit.returnBackFlag eq 'N'}">
                        <span title="原因：${empty recruit.returnBackRemark?'无':recruit.returnBackRemark}">基地退回.</span>
                    </c:when>
                    <c:when test='${(recruit.recruitFlag eq "Y") and (empty recruit.confirmFlag)}'>
                        <c:if test='${isConfirm eq "Y"}'>
                            <input type="button" onclick="confirmRecruit('${recruit.recruitFlow}' , '${GlobalConstant.FLAG_Y}');" class="btn_green" value="确认录取"/>
                            <input type="button" onclick="confirmRecruit('${recruit.recruitFlow}' , '${GlobalConstant.FLAG_N}');" class="btn_blue"  value="放弃"/>
                        </c:if>
                        <c:if test='${isConfirm eq "N"}'>
                            <span>当前录取结果未公布，请耐心等待...</span>
                        </c:if>
                        <c:if test='${isConfirm eq "F"}'>
                            <span>确认录取时间已截止</span>
                        </c:if>
                    </c:when>
                    <c:when test="${recruit.retestFlag eq 'N'}">
                        <span>志愿已填报，请等待基地通知.</span><br/>
                        <c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),recruitCfg.wishEndDate)<=1}">
                            <a class="btn_blue" href="javascript:void(0);" onclick="delRecruit('${recruit.recruitFlow}');">撤销</a>
                        </c:if>
                    </c:when>
                    <c:when test="${recruit.retestFlag eq 'Y'}">
                        <span>复试通知已发送.</span>
                    </c:when>
                </c:choose>
            </td>
            </tr>
          </c:forEach>
          <c:if test='${isCanFill eq "Y" || isSwapFill eq "Y"}'>
          <tr class="odd">
            <td>${inx+1}</td>
            <td style="width: 300px;">
                <select class="validate[required] select" style=" width:230px;" id="spe_1">
                    <option value="">请选择</option>
                    <c:if test="${speLimitedFlag1}">
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="s">
                            <c:if test="${dict.dictId eq '0700'}">
                                <option value="${dict.dictId}">${dict.dictName}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${speLimitedFlag2}">
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="s">
                            <c:if test="${dict.dictId eq '0200' ||
                            dict.dictId eq '0500' ||
                            dict.dictId eq '0700'
                            }">
                                <option value="${dict.dictId}">${dict.dictName}</option>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!speLimitedFlag1 && !speLimitedFlag2}">
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="s">
                            <%--常规代码--%>
                            <option value="${dict.dictId}">${dict.dictName}</option>
                            <%--临时代码20180720开始--%>
                            <%--<c:if test="${dict.dictId eq '0700'}">--%>
                                <%--<option value="${dict.dictId}">${dict.dictName}</option>--%>
                            <%--</c:if>--%>
                            <%--临时代码结束--%>
                        </c:forEach>
                    </c:if>
                </select>
            </td>
            <td>
                <select id="org_1" class="validate[required] select" name="orgFlow" style="width:230px;">
                </select>
                <input type="hidden" name="speId" id="speId_1">
            </td>
            <td><input id="swapFlag" name="swapFlag" type="checkbox" value="Y" onchange="showIntro();"/></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
          </c:if>
        </table>
        
        <div class="button">
        <c:if test='${isCanFill eq "Y" || isSwapFill eq "Y"}'>
        	<input type="button" class="btn_blue" style=" width:100px;" onclick="submitRecruit();" value="提交"/>
        </c:if>
        </div>
    </div>
    </form>
    </c:if>

   <div id='recruitInfo'>
   <!-- 复试通知 -->
   <c:if test="${doctorRecruit.retestFlag eq 'Y'}">
    <div class="zy_contain bs_fstz">
      <h1>复试通知</h1>
      <div class="form_news">
        <h3>${doctorRecruit.orgName}通知</h3>
        <div>
            ${doctorRecruit.retestNotice}
        </div>
      </div>
    </div>
    </c:if>
    
    <!-- 复试成绩开始 -->
    <c:if test="${doctorRecruit.operResult!=null and doctorRecruit.auditionResult!=null and cfg.cfgValue eq 'Y'}">
    <div class="zy_contain bs_fscj">
      <h1>复试成绩</h1>
      <ul>
        <li class="bs_yy">${doctorRecruit.orgName} : ${doctorRecruit.speName}</li>
        <li class="bs_cj"><!-- <img src="<s:url value='/jsp/hbres/images/basic/bhg_small.png'/>" /> --></li>
        <li class="bs_cj"><span class="blue">${doctorRecruit.operResult}分</span> <br />技能成绩</li>
        <li class="bs_cj"><span class="yellow">${doctorRecruit.auditionResult}分</span> <br />面试成绩</li>
      </ul>
    </div>
    </c:if>
    <!-- 复试成绩结束 -->
    
    <!-- 录取通知 -->
   <c:if test="${doctorRecruit.admitFlag eq 'Y'}">
    <div class="zy_contain bs_fstz">
      <h1>录取通知</h1>
      <div class="form_news">
        <h3>${doctorRecruit.orgName}通知</h3>
        <div>${doctorRecruit.admitNotice}</div>
      </div>
    </div>
    </c:if>
    <!-- 录取通知结束 -->
   </div> 
  </div>
</body>
</html>
