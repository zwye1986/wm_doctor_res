<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function toPage(page) {
		if(page!=undefined){
			$("#currentPage").val(page);			
		}
        searchAudit('${statusId}');
	} 
	function getInfo(userFlow){
		jboxOpen("<s:url value='/hbres/singup/manage/userInfo'/>?userFlow="+userFlow+"&isActive=","用户信息",1000,550);
	}
	$(document).ready(function(){
		$("li").click(function(){
            $("#sessionNumber").val('')
			$(".tab_select").addClass("tab");
			$(".tab_select").removeClass("tab_select");
			$(this).removeClass("tab");
			$(this).addClass("tab_select");
			audit($(this).attr("statusId"),$("#key").val(),null);
		});
	});
	function searchPerson(){
		audit('${statusId}',$("#key").val(),null);
	}
	
	function withdrawUser(userFlow){
		jboxConfirm("确认退回使其重新填写信息吗?",function(){
			var data = {
					doctorFlow:userFlow,
					reeditFlag:"${GlobalConstant.FLAG_Y}"
			};
			jboxPost("<s:url value='/hbres/singup/manage/withdrawUser'/>",data,
				function(resp){
					if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
						$("#withdraw_"+userFlow).hide();
					}
				}
			,null,true);
		});
	}

    function onAuditAll(){
        var checkLen = $(":checkbox[class='check']:checked").length;
        if(checkLen == 0){
            jboxTip("请勾选待审核信息！");
            return;
        }
        var recordLst = [];
        $(":checkbox[class='check']:checked").each(function(){
            recordLst.push(this.value);
        })
        jboxConfirm("确认一键审核通过勾选信息？", function(){
            var url = "<s:url value='/hbres/singup/manage/auditAll?recordLst='/>"+recordLst;
            jboxPost(url, null, function(resp){
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    audit("Passed",$("#key").val(),null);
                }
            });
        });
    }
    function allCheck(){
        if($("#checkAll").attr("checked")){
            $(".check").attr("checked",true);
        }else{
            $(".check").attr("checked",false);
        }
    }
    function checkSingel(obj){
        if(!$(obj).attr("checked")){
            $("#checkAll").attr("checked",false);
        }else{
            var checkAllLen = $("input[type='checkbox'][class='check']").length;
            var checkLen = $("input[type='checkbox'][class='check']:checked").length;
            if(checkAllLen == checkLen){
                $("#checkAll").attr("checked",true);
            }
        }
    }
    function searchAudit(statusId){
        $("#auditItem").addClass("select");
        var currentPage = '';
        if($("#currentPage").val()) currentPage = $("#currentPage").val();
        var sessionNumber = '';
        if($("#sessionNumber").val()) sessionNumber = $("#sessionNumber").val();
        var data="statusId="+statusId+"&doctorType="+$("#doctorType").val()+"&freshGraduateFlag="+$("#freshGraduateFlag").val()
                +"&educationType="+$("#educationType").val()+"&gainLicenseFlag="+$("#gainLicenseFlag").val()
            +"&sessionNumber="+sessionNumber+"&currentPage="+currentPage;
        jboxPostLoad("content","<s:url value='/hbres/singup/manage/audit'/>",data,true);
    }
</script>
      <div class="main_hd">
          <c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
        <h2>报名审核</h2>
        <div class="title_tab" id="toptab">
          <ul>
            <li class="${statusId==regStatusEnumPassing.id?'tab_select':'tab' }" statusId="${regStatusEnumPassing.id }"><a>待审核</a></li>
            <li class="${statusId==regStatusEnumPassed.id?'tab_select':'tab' }" statusId="${regStatusEnumPassed.id }"><a>审核通过</a></li>
            <li class="${statusId==regStatusEnumUnPassed.id?'tab_select':'tab' }" statusId="${regStatusEnumUnPassed.id }"><a>审核不通过</a></li>
          </ul>
        </div>
      </div>
      <div class="main_bd" id="div_table_0" >
      	<div class="div_search">
            <div style="clear:both;">
                学员类型：
                <select id="doctorType" style="width:115px;" class="select">
                    <option></option>
                    <option value="Social" ${param.doctorType eq 'Social'?'selected':''}>社会人</option>
                    <option value="Company" ${param.doctorType eq 'Company'?'selected':''}>本单位人</option>
                    <option value="CompanyEntrust" ${param.doctorType eq 'CompanyEntrust'?'selected':''}>委培单位人</option>
                    <option value="Graduate" ${param.doctorType eq 'Graduate'?'selected':''}>四证合一</option>
                </select>
                &#12288;是否为应届生：
                <select id="freshGraduateFlag" style="width:115px;" class="select">
                    <option></option>
                    <option value="Y" ${param.freshGraduateFlag eq 'Y'?'selected':''}>是</option>
                    <option value="N" ${param.freshGraduateFlag eq 'N'?'selected':''}>否</option>
                </select>
                &#12288;是否取得执业医师资格证：
                <select id="gainLicenseFlag" style="width:115px;" class="select">
                    <option></option>
                    <option value="Y" ${param.gainLicenseFlag eq 'Y'?'selected':''}>是</option>
                    <option value="N" ${param.gainLicenseFlag eq 'N'?'selected':''}>否</option>
                </select>
                <div style="margin:10px 0px 10px 0px;">
                    最高学历：
                    <select id="educationType" style="width:115px;" class="select">
                        <option></option>
                        <c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
                            <option value="${dict.dictId}" ${param.educationType eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>&#12288;
                    <input type="text" class="input" id="key" name="key" value="${key}" placeholder="姓名/手机号/邮件/身份证" onblur="searchPerson();" style="width:188px; height:20px;"/>&#12288;
                    <c:if test="${(!isFree )and statusId==regStatusEnumPassed.id}">
                        年&#12288;&#12288;份：
                        <select name="sessionNumber" id="sessionNumber" class="select" style="width: 126px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
                                <option value="${dict.dictName}" ${sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <input type="button" value="查&#12288;询" class="btn_green btn_green1" onclick="searchAudit('${statusId}')">

                    <c:if test="${(!isFree )and statusId==regStatusEnumPassing.id and userList.size() > 0}">
                        <input type="button" value="一键审核通过" class="btn_green" onclick="onAuditAll()">
                    </c:if>
                </div>
            </div>
      	</div>
        <div class="div_table">
        <c:if test="${statusId!=regStatusEnumUnPassed.id }">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <c:if test="${!isFree}">
                    <th><input type="checkbox" id="checkAll" onclick="allCheck()"/>&nbsp;&nbsp;</th>
                </c:if>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">身份证</th>
                <th style="text-align: center;">毕业专业</th>
                <th style="text-align: center;">类型</th>
                <th>报名信息</th>
            </tr>
            <c:forEach items="${ userList}" var="user" varStatus="i">
            <tr>
                <c:if test="${!isFree}">
                <td>${i.index ge 9?'&nbsp;&nbsp;':''}<input type="checkbox" class="check" value="${user.sysUser.userFlow}" onclick="checkSingel(this)"/>${i.index +1}</td>
                </c:if>
                <td style="text-align: center;">${user.sysUser.userName}</td>
                <td style="text-align: center;">${user.sysUser.idNo}</td>
                <td style="text-align: center;">
                    <c:forEach var="dict" items="${dictTypeEnumGraduateMajorList}">
                        <c:if test='${user.specialized==dict.dictId}'>${dict.dictName}</c:if>
                    </c:forEach>
                </td>
                <td style="text-align: center;">${user.doctorTypeName}</td>
                <td >

                    <c:if test="${!isFree}">
                        <a class="btn" onclick="getInfo('${user.sysUser.userFlow}');">
                            <c:if test="${statusId==regStatusEnumPassing.id }">审&#12288;核</c:if>
                            <c:if test="${statusId==regStatusEnumPassed.id }">报名信息</c:if>
                        </a>
                    </c:if>
                    <c:if test="${isFree}">
                        <a class="btn" onclick="getInfo('${user.sysUser.userFlow}');">报名信息</a>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
          </table>
          </c:if>
          <c:if test="${statusId==regStatusEnumUnPassed.id }">
          <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
              <th  style="text-align: center;width: 100px;">姓名</th>
              <th  style="text-align: center;">未通过原因</th>
              <th width="120px;">报名信息</th>
              <c:if test="${!isFree}">
              <th width="120px;">操作</th>
              </c:if>
            </tr>
            <c:forEach items="${ userList}" var="user">
            <tr>
              <td style="text-align: center;">${user.sysUser.userName}</td>
              <td style="text-align: center;">${user.disactiveReason}</td>
              <td ><a class="btn" onclick="getInfo('${user.sysUser.userFlow}');">报名信息</a></td>
                <c:if test="${!isFree}">
                  <td >
                      <c:if test="${GlobalConstant.FLAG_Y != user.reeditFlag }">
                        <a class="btn" id="withdraw_${user.sysUser.userFlow}" onclick="withdrawUser('${user.sysUser.userFlow}');">退&#12288;回</a>
                      </c:if>
                  </td>
                </c:if>
            </tr>
            </c:forEach>
          </table>
          </c:if>
        </div>
        <div class="page" style="text-align: center">
       	 <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
           <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
	  		 <pd:pagination-jsres toPage="toPage"/>
        </div>
      </div>
      
