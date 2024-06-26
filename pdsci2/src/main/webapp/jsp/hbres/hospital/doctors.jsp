<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
    function toPage(page){
        if(page){
            searchDoctor(page);
        }
    }
	
	function getInfo(recruitFlow){
		jboxOpen("<s:url value='/hbres/singup/hospital/doctorInfo'/>?recruitFlow="+recruitFlow,"人员信息",1000,550);
	}
	
	function searchDoctor(page){
        var personType = $(".tab_select").attr("id");
        var datas = [];
        $(".docType:checked").each(function(){
            datas.push($(this).val());
        })
		recruitUsers("${param.speId}", $("#graduatedId").val() , $("#key").val() , $('#batchOper').val() , ${assignPlan} ,${confirmResult},
		 $("#retestFlag").val(),$("#admitFlag").val(),page,$("#order:checked").val(),personType,$("#adminSwapFlag:checked").val(),datas
        );
	}

	//复试通知
	function noticeRetestMain(recruitFlow){
		jboxOpen("<s:url value='/hbres/singup/hospital/noticeRetestMain'/>?operType=single&recruitFlow="+recruitFlow,"复试通知",1000,500);
	}
    //撤销复试/录取通知
    function noticeBack(recruitFlow,flag){
        jboxConfirm("确认撤销？",function(){
            jboxPost("<s:url value='/hbres/singup/hospital/noticeBackOpt?recruitFlow='/>"+recruitFlow+"&flag="+flag,null,
                    function(resp){
                        if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
                            jboxTip("${GlobalConstant.OPERATE_SUCCESSED}");
                            window.parent.searchDoctor('');
                            jboxClose();
                        }
                    }
                    ,null,false);
        })
    }
	
	function noticeRetestMainForBatch(){
		if ($("input[name='recruitFlow_check']:checked").length<1) {
			jboxTip("请至少选择一条记录！");
			return;
		}
		jboxOpen("<s:url value='/hbres/singup/hospital/noticeRetestMain'/>?operType=batch","复试通知",1000,500);
	}
	
	//成绩录入
	function gradeEdit(recruitFlow,examResult){
        if(examResult == "" || examResult == null){
            jboxTip("需知笔试成绩方可计算总成绩！");
            return;
        }
		jboxOpen("<s:url value='/hbres/singup/hospital/gradeedit'/>?recruitFlow="+recruitFlow , "成绩录入" , 400 , 200);
	}

	//是否录取操作
	function admitOper(recruitFlow , admitFlag , adminSwapFlag){
        if(admitFlag=='Y'){//如果是录取，需要查询数量够不够了
            jboxPost("<s:url value='/hbres/singup/hospital/checkRecruitNum'/>",{"recruitFlow":recruitFlow,"assignPlan":'${assignPlan}'},function(resp){
                if(resp=='0'){
                    jboxTip("录取人数不能超过计划人数！");
                    return;
                }else{
                    if(admitFlag=='${GlobalConstant.FLAG_Y}'){
                        jboxOpen("<s:url value='/hbres/singup/hospital/noticeRecruitMain'/>?operType=single&recruitFlow="+recruitFlow+"&adminSwapFlag="+adminSwapFlag,"录取通知",1000,500);
                    }else if(admitFlag=='${GlobalConstant.FLAG_N}'){
                        jboxConfirm("确认不录取？", function(){
                            jboxPost("<s:url value='/hbres/singup/hospital/notrecruit'/>" , {"recruitFlow":recruitFlow} , function(resp){
                                if(resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                                    jboxTip("${GlobalConstant.OPERATE_SUCCESSED}");
                                    searchDoctor('');
                                    jboxClose();
                                }
                            } , null , false);
                        });
                    }
                }
            },null,false)
        }else{
            if(admitFlag=='${GlobalConstant.FLAG_Y}'){
                jboxOpen("<s:url value='/hbres/singup/hospital/noticeRecruitMain'/>?operType=single&recruitFlow="+recruitFlow+"&adminSwapFlag="+adminSwapFlag,"录取通知",1000,500);
            }else if(admitFlag=='${GlobalConstant.FLAG_N}'){
                jboxConfirm("确认不录取？", function(){
                    jboxPost("<s:url value='/hbres/singup/hospital/notrecruit'/>" , {"recruitFlow":recruitFlow} , function(resp){
                        if(resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                            jboxTip("${GlobalConstant.OPERATE_SUCCESSED}");
                            searchDoctor('');
                            jboxClose();
                        }
                    } , null , false);
                });
            }
        }
	}

    //不发复试通知直接退回页面
    function returnBackPage(recruitFlow){
        jboxOpen("<s:url value='/hbres/singup/hospital/returnBackPage'/>?recruitFlow="+recruitFlow,"退回原因",1000,400);
    }
	
	function noticeRecruitMainForBatch(){
        var numBanch = $("input[name='recruitFlow_check']:checked").length;
		if (numBanch<1) {
			jboxTip("请至少选择一条记录！");
			return;
		}
        <%--var num = parseInt('${assignPlan}') - parseInt('${confirmResult}');--%>
        var recruitFlow = $("input[name='recruitFlow_check']:checked").eq(0).val();
//        if ($("input[name='recruitFlow_check']:checked").length>num) {
//            jboxTip("录取人数不能超过计划人数！");
//            return;
//        }
        jboxPost("<s:url value='/hbres/singup/hospital/checkRecruitNum'/>",{"recruitFlow":recruitFlow,"assignPlan":'${assignPlan}',
            "numBanch":numBanch},function(resp){
            if(resp=='0'){
                jboxTip("录取人数不能超过计划人数！");
                return;
            }else{
                jboxOpen("<s:url value='/hbres/singup/hospital/noticeRecruitMain'/>?operType=batch","录取通知",1000,500);
            }
        },null,false)
		<%--jboxOpen("<s:url value='/hbres/singup/hospital/noticeRecruitMain'/>?operType=batch","录取通知",1000,500);--%>
	}
	
	//调剂操作
	function swapOper(recruitFlow , speId){
		jboxOpen("<s:url value='/hbres/singup/hospital/noticeSwapMain'/>?recruitFlow="+recruitFlow+"&speId="+speId,"调剂专业",1000,500);
	}

	function checkAll(obj) {
		if (obj.checked) {
			$(":checkbox[name='recruitFlow_check']").attr("checked",true);
		}else {
			$(":checkbox[name='recruitFlow_check']").attr("checked",false);
		}
	}
	
	function speciftedOper(recruitFlow){
		jboxOpen("<s:url value='/hbres/singup/hospital/getSpecifiedOper'/>?recruitFlow="+recruitFlow , '操作' , 400 , 300 , true);
	}

    function sendRetestInfo(recruitFlow,flag){
        jboxOpen("<s:url value='/hbres/singup/hospital/retestNoticeSearch'/>?recruitFlow="+recruitFlow+"&flag="+flag, '已发'+(flag=='fs'?'复试':'录取')+'通知查看' , 800 , 500 , true);
    }

    $(document).ready(function(){
        $(".show").on("mouseenter mouseleave",
                function(){
                    $(".info",this).toggle(100);
                }
        );
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            $("#batchOper").val('');
            searchDoctor('');
        });
        var id = '${param.personType}';
        $("#"+id).removeClass("tab").addClass("tab_select");

        <c:forEach items="${hBRecDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if ("${data}" == "${type.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#" + "${type.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
    });

    function changeInfo(doctorFlow){
        var url = "<s:url value='/hbres/singup/changeInfo'/>?doctorFlow="+doctorFlow+"&role=hospital";
        var mainIframe = window.parent.frames["mainIframe"];
        var width = 1000;
        var height = 550;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'用户信息',width,height);
    }
    function checkAll2(obj) {
        var f = false;
        if ($(obj).attr("checked") == "checked") {
            f = true;
        }
        $(".docType").each(function () {
            if (f) {
                $(this).attr("checked", "checked");
            } else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox() {
        if ($(".docType:checked").length == $(".docType").length) {
            $("#all").attr("checked", "checked");
        } else {
            $("#all").removeAttr("checked");
        }
    }
    function showScore(value) {
        var url = "<s:url value='/hbres/singup/hospital/showScore'/>?value="+value;
        jboxPost(url,null,function (resp) {
            if(resp=='1') jboxTip('操作成功')
        },null,false);
    }
</script>
<%--<div class="main_bd" id="div_table_0" > --%>
   <div class="main_hd">
       <h2><span>当前专业：${currSpe.speName}</span></h2>
       <div class="title_tab" id="toptab">
           <ul>
               <li class="tab" id="signStu"><a>报名学员</a></li>
               <li class="tab" id="reExamStu"><a>复试学员</a></li>
               <li class="tab" id="enterStu"><a>录取信息</a></li>
           </ul>
       </div>
   </div>
   <div class="main_bd" id="div_table_0" >
	   <div class="div_search" style="">
           <c:if test='${param.personType eq "signStu"}'>
               <label>是否发送复试通知：</label>
               <select id="retestFlag" onchange="searchDoctor('');" class="select" style="width:70px;">
                   <option value="">请选择</option>
                   <option value="Y" ${param.retestFlag eq 'Y'?'selected':''}>是</option>
                   <option value="N" ${param.retestFlag eq 'N'?'selected':''}>否</option>
               </select>&#12288;
           </c:if>
           <c:if test='${param.personType eq "reExamStu"}'>
           <label>是否发送录取通知：</label>
           <select id="admitFlag" onchange="searchDoctor('');" class="select" style="width:70px;">
               <option value="">请选择</option>
               <option value="Y" ${param.admitFlag eq 'Y'?'selected':''}>是</option>
               <option value="N" ${param.admitFlag eq 'N'?'selected':''}>否</option>
           </select>&#12288;
           </c:if>
           <c:if test='${param.personType eq "enterStu"}'>
               <label>学员录取状态：</label>
               <select id="batchOper" name="batchOper" onchange="searchDoctor('');" class="select">
                   <option value="">请选择</option>
                   <option value="3" ${param.batchOper eq '3'?'selected':''}>待学员确认</option>
                   <option value="4" ${param.batchOper eq '4'?'selected':''}>录取</option>
                   <option value="5" ${param.batchOper eq '5'?'selected':''}>不录取</option>
                   <option value="6" ${param.batchOper eq '6'?'selected':''}>放弃录取</option>
               </select>&#12288;
           </c:if>
			<%--<label>毕业院校：</label>--%>
			<%--<select id="graduatedId" onchange="searchDoctor('');" class="select" style="width:150px;">--%>
			    <%--<option value="">请选择</option>--%>
				    <%--<c:forEach var="dict" items="${dictTypeEnumGraduateSchoolList}">--%>
						<%--<option value="${dict.dictId}" <c:if test='${dict.dictId == param.graduatedId}'>selected="selected"</c:if>>${dict.dictName}</option>--%>
					<%--</c:forEach>--%>
				    <%--<option value="00" <c:if test="${'00' == param.graduatedId}">selected="selected"</c:if>>其它</option>--%>
			<%--</select>&#12288;--%>
			<input type="text" id="key" name="key" value="${param.key}" class="input" placeholder="姓名/手机号/邮件/身份证" onchange="searchDoctor('');" style="width:150px;"/>
           &#12288;<a onclick="searchDoctor('');" class="btn_green">查询</a>
           &#12288;<a onclick="main();" class="btn_green">返回</a>
           <br/> <br/>
           按成绩排序：<input type="checkbox" id="order" name="order" value="Y" ${param.order eq 'Y'?'checked':''} onchange="searchDoctor('');">&#12288;
            <c:if test='${param.personType eq "enterStu"}'>
           只省厅调剂：<input type="checkbox" id="adminSwapFlag" value="Y" ${param.adminSwapFlag eq 'Y'?'checked':''} onchange="searchDoctor('');">&#12288;
            </c:if>
           学员类型：<label><input type="checkbox" id="all" value="all" name="all" checked
                              onclick="checkAll2(this);"/>全部</label>
           <c:forEach items="${hBRecDocTypeEnumList}" var="type">
               <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                             name="datas" onclick="changeAllBox();"/>${type.name}</label>
           </c:forEach>
            <c:if test='${param.personType eq "reExamStu"}'>
                &emsp;
                <label><input title="选择后学员页面复试成绩可见" type="radio" value="Y" name="showScore" onchange="showScore('Y')"
                    ${cfg.cfgValue eq 'Y'?'checked':''}>开放成绩展示</label>
                <label><input title="选择后学员页面复试成绩不可见" type="radio" value="N" name="showScore" onchange="showScore('N')"
                    ${cfg.cfgValue ne 'Y'?'checked':''}>关闭成绩展示</label>
            </c:if>
       </div>
		<div class="search_table cz" style="text-align:left; padding: 5px 10px; margin: 0 40px;">
        <c:if test='${param.personType ne "enterStu"}'>
            <label>批量操作：</label>
               <select style="width:100px; margin-top: -4px;" class="select" id="batchOper" name="batchOper" onchange="searchDoctor('');">
                <option value=''>请选择</option>
                <c:if test='${param.personType eq "signStu"}'>
                <option value='1' <c:if test='${param.batchOper eq "1"}'>selected="selected"</c:if>>复试通知</option>
                </c:if>
                <c:if test='${param.personType eq "reExamStu"}'>
                <option value='2' <c:if test='${param.batchOper eq "2"}'>selected="selected"</c:if>>录取通知</option>
                </c:if>
               </select>
            </c:if>
               <span id="batchOperDiv"  style="margin-left:10px; display:inline-block;">
		         <c:if test="${param.batchOper eq '1'}">
		           <a id="batchRetest" class="btn_green" href="javascript:void(0);" onclick="noticeRetestMainForBatch();">批量复试通知</a>
		         </c:if>
		         <c:if test="${param.batchOper eq '2'}">
                   <c:if test="${!isAdminDate}">不在录取时间内</c:if>
                   <c:if test="${isAdminDate}">
		           <a id="batchtRecruit" class="btn_green" href="javascript:void(0);" onclick="noticeRecruitMainForBatch();">批量录取通知</a>
                   </c:if>
                 </c:if>
		       </span>
		       <span style="margin-left:10px;">总成绩=笔试分*0.4/1.5 + 面试分*0.2 + 操作技能分*0.4</span>
                <c:if test='${param.personType ne "signStu"}'>
               <span style="margin-left:20px;">
                   Tip：<span style="color: red;">*</span>表示省厅调剂学员。
               </span>
                </c:if>
        </div>
   </div>
   <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
            <c:if test='${not empty param.batchOper}'>
              <th>
                  <input type="checkbox" onclick="checkAll(this);">
              </th>
            </c:if>
              <th style="text-align: center;">序号</th>
              <th style="text-align: center;">姓名</th>
              <th style="text-align: center;">笔试成绩</th>
              <c:if test='${param.personType eq "signStu"}'>
                  <th style="text-align: center;">最高学历</th>
                  <th style="text-align: center;">联系方式</th>
              </c:if>
              <c:if test='${param.personType eq "reExamStu"}'>
                  <th style="text-align: center;">面试/操作<br>&#12288;成绩</th>
                  <th style="text-align: center;">总成绩</th>
              </c:if>
              <th style="text-align: center;">服从调剂</th>
              <th style="text-align: center;" width="80px;">人员信息</th>
              <th style="text-align: center;" width="160px;">操作</th>
            </tr>
            <c:forEach items="${doctorRecruitExts }" var="doctorExt" varStatus="status">
            <tr>
            <c:if test='${not empty param.batchOper}'>
              <td style="text-align: center;">
                  <input type="checkbox" name="recruitFlow_check"  value="${doctorExt.recruitFlow}">
                  <input type="hidden" name="adminSwapFlag"  value="${doctorExt.adminSwapFlag}">
              </td>
            </c:if>
              <td style="text-align: center;">${status.index+1}</td>
              <td style="text-align: center;" <c:if test="${sysCfgMap['res_specified_hospital_oper'] eq 'Y'}">onclick='speciftedOper("${doctorExt.recruitFlow}");'</c:if>>
                <c:if test="${GlobalConstant.FLAG_Y eq doctorExt.adminSwapFlag}">
                    <span style="color:red;">*</span>
                </c:if>
                    ${doctorExt.doctor.doctorName}
              </td>
              <td style="text-align: center;">${doctorExt.examResult}</td>
              <c:if test='${param.personType eq "signStu"}'>
                  <td>${doctorExt.sysUser.educationName}</td>
                  <td>${doctorExt.sysUser.userPhone}</td>
              </c:if>
              <c:if test='${param.personType eq "reExamStu"}'>
                  <td style="text-align: center;"><a href='javascript:void(0);' onclick="gradeEdit('${doctorExt.recruitFlow}','${doctorExt.examResult}');" title="修改">${doctorExt.auditionResult}/${doctorExt.operResult}</a></td>
                  <td style="text-align: center;">${doctorExt.totleResult}</td>
              </c:if>
              <td style="text-align: center;">${GlobalConstant.FLAG_Y eq doctorExt.swapFlag?'是':'否' }</td>
              <td style="text-align: center;" width="110px;">
                  <a class="btn" onclick="getInfo('${doctorExt.recruitFlow}');" style="width: 40px;padding: 0">详情</a>
                  <a class="btn" onclick="changeInfo('${doctorExt.doctorFlow}');" style="width: 40px;padding: 0">编辑</a>
              </td>
              <td style="text-align: center;" width="160px;" id="operTd_${doctorExt.doctorFlow}">
                  <c:set value="N" var="more"></c:set>
                  <c:set value="N" var="a"></c:set>
                  <c:set value="N" var="b"></c:set>
                  <c:set value="N" var="c"></c:set>
                  <c:set value="N" var="d"></c:set>
                  <c:set value="N" var="e"></c:set>
                  <c:set value="N" var="f"></c:set>
                  <c:set value="N" var="g"></c:set>
                  <c:choose>
                      <c:when test='${not empty doctorExt.swapSpeId && param.personType eq "reExamStu"}'>
                          <span title="被调剂至${doctorExt.swapSpeName}">被调剂...</span>
                      </c:when>
                      <c:when test="${'N' eq doctorExt.recruitFlag && !(param.personType eq 'signStu')}">
                          <span>不录取</span>
                      </c:when>
                      <c:when test="${doctorExt.retestFlag eq 'N'}">
                          <%--<c:set value="Y" var="more"></c:set>--%>
                          <%--<c:set value="Y" var="a"></c:set>--%>
                          <a href="javascript:void(0);" onclick="returnBackPage('${doctorExt.recruitFlow}')">退回</a>
                          <a href="javascript:void(0);" onclick="noticeRetestMain('${doctorExt.recruitFlow}')">复试通知</a>
                      </c:when>
                      <c:when test="${(doctorExt.retestFlag eq 'Y') and ((empty doctorExt.auditionResult) or (empty doctorExt.operResult))}">
                          <c:set value="Y" var="more"></c:set>
                          <c:set value="Y" var="b"></c:set>
                          <%--<a href='javascript:void(0);' onclick="gradeEdit('${doctorExt.recruitFlow}','${doctorExt.examResult}');">录入成绩</a>--%>
                      </c:when>
                      <c:when test="${(doctorExt.retestFlag eq 'Y') and ((not empty doctorExt.auditionResult) and (not empty doctorExt.operResult)) and (doctorExt.admitFlag eq 'N')}">
                        <c:if test="${isAdminDate}">
                          <c:if test="${(assignPlan - confirmResult)>0}">
                              <%--机构专业招收计划数必须大于等于已经录取人数--%>
                              <%--<a href="javascript:void(0);" onclick="admitOper('${doctorExt.recruitFlow}' , '${GlobalConstant.FLAG_Y}' , '${doctorExt.adminSwapFlag}');">录取</a>--%>
                              <c:set value="Y" var="more"></c:set>
                              <c:set value="Y" var="c"></c:set>
                          </c:if>
                          <%--<a href="javascript:void(0);" onclick="admitOper('${doctorExt.recruitFlow}' , '${GlobalConstant.FLAG_N}');">不录取</a>--%>
                            <c:set value="Y" var="d"></c:set>
                            <c:set value="Y" var="more"></c:set>
                          <c:if test='${doctorExt.swapFlag eq "Y" && !(param.personType eq "signStu")}'>
                              <a href="javascript:void(0);" onclick="swapOper('${doctorExt.recruitFlow}' , '${doctorExt.speId}');">调剂</a>
                              <c:set value="Y" var="e"></c:set>
                              <c:set value="Y" var="more"></c:set>
                          </c:if>
                        </c:if>
                        <c:if test="${!isAdminDate && !(param.personType eq 'signStu')}">
                            不在录取时间内<br/>
                        </c:if>
                      </c:when>
                      <c:when test="${doctorExt.recruitFlag eq 'Y' and empty doctorExt.confirmFlag && !(param.personType eq 'signStu')}">
                          <span>等待学员确认</span>
                      </c:when>
                      <c:when test="${doctorExt.recruitFlag eq 'Y' and doctorExt.confirmFlag eq 'Y' && !(param.personType eq 'signStu')}">
                          <span>
                              录取
                          </span>
                      </c:when>
                      <c:when test="${doctorExt.recruitFlag eq 'Y' and doctorExt.confirmFlag eq 'F' && !(param.personType eq 'signStu')}">
                          <span>未确认(过期)</span>
                      </c:when>
                      <c:when test="${doctorExt.recruitFlag eq 'Y' and doctorExt.confirmFlag eq 'N' && !(param.personType eq 'signStu')}">
                          <span>放弃录取</span>
                      </c:when>
                  </c:choose>
                  <c:if test="${(doctorExt.retestFlag eq 'Y'  && !('N' eq doctorExt.recruitFlag)  && !(param.personType eq 'enterStu'))||
                  (doctorExt.retestFlag eq 'Y' && param.personType eq 'signStu')}">
                      <%--<c:set value="Y" var="more"></c:set>--%>
                      <%--<c:set value="Y" var="f"></c:set>--%>
                      <a href="javascript:void(0);" onclick="sendRetestInfo('${doctorExt.recruitFlow}','fs');">已发复试通知</a>
                      <c:if test="${doctorExt.recruitFlag ne 'Y' && doctorExt.admitFlag ne 'Y' && param.personType eq 'reExamStu' &&
                      GlobalConstant.FLAG_Y ne doctorExt.adminSwapFlag}">
                          <a href="javascript:void(0);" onclick="noticeBack('${doctorExt.recruitFlow}','fs')">撤销</a>
                      </c:if>
                  </c:if>
                  <c:if test="${doctorExt.recruitFlag eq 'Y' && doctorExt.admitFlag eq 'Y' && (param.personType eq 'enterStu') && (empty doctorExt.confirmFlag)}">
                      <c:set value="Y" var="more"></c:set>
                      <c:set value="Y" var="g"></c:set>
                      <br/>
                      <a href="javascript:void(0);" onclick="sendRetestInfo('${doctorExt.recruitFlow}','lq');">已发录取通知</a>
                      <c:if test="${doctorExt.confirmFlag ne 'Y'}">
                            <a href="javascript:void(0);" onclick="noticeBack('${doctorExt.recruitFlow}','lq')">撤销</a>
                      </c:if>
                  </c:if>
                  <c:if test="${more eq 'Y' && param.personType eq 'reExamStu'}">
                      <span class="show" style="color:#459ae9;">录取管理
                        <div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">
                            <div style="display: none;width: 160px;" class="info">
                                <table class="grid" style="background: white;margin-left:-100px;margin-top:-5px;">
                                    <%--<c:if test="${a eq 'Y'}">--%>
                                    <%--<tr>--%>
                                        <%--<td>--%>
                                            <%--<a href="javascript:void(0);" onclick="noticeRetestMain('${doctorExt.recruitFlow}')">复试通知</a>--%>
                                        <%--</td>--%>
                                    <%--</tr>--%>
                                    <%--</c:if>--%>
                                    <c:if test="${b eq 'Y'}">
                                        <tr>
                                            <td>
                                                <a href='javascript:void(0);' onclick="gradeEdit('${doctorExt.recruitFlow}','${doctorExt.examResult}');">录入成绩</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${c eq 'Y'}">
                                        <tr>
                                            <td>
                                                <a href="javascript:void(0);" onclick="admitOper('${doctorExt.recruitFlow}' , '${GlobalConstant.FLAG_Y}' , '${doctorExt.adminSwapFlag}');">录取</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${d eq 'Y'}">
                                        <tr>
                                            <td>
                                                <a href="javascript:void(0);" style="color:red" onclick="admitOper('${doctorExt.recruitFlow}' , '${GlobalConstant.FLAG_N}');">不录取</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <%--<c:if test="${e eq 'Y'}">--%>
                                        <%--<tr>--%>
                                            <%--<td>--%>
                                                <%--<a href="javascript:void(0);" onclick="swapOper('${doctorExt.recruitFlow}' , '${doctorExt.speId}');">调剂</a>--%>
                                            <%--</td>--%>
                                        <%--</tr>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${f eq 'Y'}">--%>
                                        <%--<tr>--%>
                                            <%--<td>--%>
                                                <%--<a href="javascript:void(0);" onclick="sendRetestInfo('${doctorExt.recruitFlow}','fs');">已发复试通知</a>--%>
                                                <%--<c:if test="${doctorExt.recruitFlag ne 'Y' && doctorExt.admitFlag ne 'Y' && empty doctorExt.auditionResult && empty doctorExt.operResult}">--%>
                                                    <%--<a href="javascript:void(0);" onclick="noticeBack('${doctorExt.recruitFlow}','fs')">撤销</a>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>
                                        <%--</tr>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${g eq 'Y'}">--%>
                                        <%--<tr>--%>
                                            <%--<td>--%>
                                                <%--<a href="javascript:void(0);" onclick="sendRetestInfo('${doctorExt.recruitFlow}','lq');">已发录取通知</a>--%>
                                                <%--<c:if test="${doctorExt.confirmFlag ne 'Y'}">--%>
                                                    <%--<a href="javascript:void(0);" onclick="noticeBack('${doctorExt.recruitFlow}','lq')">撤销</a>--%>
                                                <%--</c:if>--%>
                                            <%--</td>--%>
                                        <%--</tr>--%>
                                    <%--</c:if>--%>
                                </table>
                            </div>
                        </div>
                      </span>
                  </c:if>
              </td>
            </tr>
            </c:forEach>
          </table>
       <div class="page" style="text-align: center;">
           <input id="currentPage" type="hidden" name="currentPage" value=""/>
           <c:set var="pageView" value="${pdfn:getPageView(doctorRecruitExts)}" scope="request"></c:set>
           <pd:pagination-jsres toPage="toPage"/>
       </div>
    </div>
<%--</div>--%>
