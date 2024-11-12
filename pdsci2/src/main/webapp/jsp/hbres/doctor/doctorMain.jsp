<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function goScoreInfo(){
		location.href="<s:url value='/hbres/singup/doctor/scoreSearch'/>";
	}
	
	function goRotationTrain(){
		location.href="<s:url value='/hbres/doc/searchRotationList'/>";
	}
	
	function printExamTicket(){
		var url = "<s:url value='/hbres/singup/doctor/printexamticket'/>";
		jboxGet(url , null , function(resp){
			if(resp.allowPrint){
				window.location.href="<s:url value='/hbres/singup/doctor/showexamcard'/>";
			}else{
				jboxTip("当前准考证打印"+resp.msg);
			}
		} , null , false);
	}
</script>
</head>

<body>
  <div class="main_wrap">
  <c:if test="${empty scoreSearchMsg}">
    <div class="enter">
      <h1>准考证</h1>
      <div class="enter_bd">
         <img src="<s:url value='/jsp/hbres/images/basic/ticket.png'/>" />
         <button class="btn_blue mark" style=" width:130px;" onclick="printExamTicket();">打印准考证</button>
         <span style="display:block;margin:-55px 0px 30px 50%;width:50%;text-align:center;">${examDoctor.siteName}<br/>${examSite.siteAddress}</span>
         <span class="enter_tip">打印准考证日期：${recruitCfg.printBeginDate }~${recruitCfg.printEndDate }</span>
      </div>
    </div>
    </c:if>
    <c:if test="${not empty scoreSearchMsg}">
    <div class="enter">
      <h1>成绩查询</h1>
      <div class="enter_bd">
         <img src="<s:url value='/jsp/hbres/images/basic/mark.png'/>" />
         <!-- <button class="btn_blue search" style=" width:130px;" onclick="goScoreInfo();">成绩查询</button> -->
         <span class="enter_tip2">${scoreSearchMsg}</span>
      </div>
    </div>
    </c:if>
    <div class="enter" style="display: none;">
      <h1>轮转培训</h1>
      <div class="enter_bd">
         <img src="<s:url value='/jsp/hbres/images/basic/mark.png'/>" />
    	 <button class="btn_blue" style=" width:130px;" onclick="goRotationTrain();">轮转培训</button>
         <span class="enter_tip"></span>
      </div>
    </div>

      <c:if test="${empty examDoctor && !empty doctor}">
          <c:if test="${!condition}"><!-- 非免试，会有选考点 -->
              <script>
                  $(function () {
                      $('.enter:not(#selExamSites)').hide();
                  });

                  function selExamSites(siteName, siteFlow) {
                      jboxConfirm('确认选择<font color="blue">' + siteName + '</font>为本次考试的考点？<font color="red">选择后将不能修改！</font>',
                              function () {
                                  $('.btn_blue').attr('disabled',true);
                                  jboxPost('<s:url value="/hbres/singup/doctor/selExamSites"/>', {
                                      'doctorFlow': '${doctor.doctorFlow}',
                                      'examSiteFlow': siteFlow
                                  }, function (resp) {
                                      if ('${GlobalConstant.OPRE_SUCCESSED_FLAG}' == resp) {
                                          location.reload();
                                      }
                                  }, function(){$('.btn_blue').attr('disabled',false);}, false);
                              },
                              null
                      );
                  }
              </script>
              <div id="selExamSites" class="enter">
                  <h1>
                      选择考点
                      <font color="red" style="font-size: 14px;">
                       ${empty examSites?'具体考点暂未公布，请随时登录关注系统动态，以免错过考点选择，请相互转告！':
                             ' 请慎重选择考点！确定后无法修改！'}</font>
                  </h1>
                  <table class="enter_bd grid">
                      <tr>
                          <th>考点名称</th>
                          <th>考点代码</th>
                          <th>考点地址</th>
                          <th style="width: 15%;">操作</th>
                      </tr>
                      <c:forEach var="site" items="${examSites}">
                          <tr>
                              <td>${site.siteName}</td>
                              <td>${site.siteCode}</td>
                              <td>${site.siteAddress}</td>
                              <td>
                                  <button class="btn_blue" style=" width:100px;margin: 0;float: none;"
                                          onclick="selExamSites('${site.siteName}','${site.recordFlow}');">选择
                                  </button>
                              </td>
                          </tr>
                      </c:forEach>
                  </table>
              </div>
          </c:if>
      </c:if>
  </div>
</body>
</html>
