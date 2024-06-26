<script type="text/javascript">
	function toPage(page) {
		if(page!=undefined){
			$("#currentPage").val(page);			
		}
		var currentPage = $("#currentPage").val();
		if(!currentPage){
			currentPage = 1;
		}
		gradeInput(currentPage);
		//jboxLoad("gradelist","<s:url value='/hbres/grade/gradelist'/>?currentPage="+currentPage,false);
	} 
	function getInfo(userFlow){
		jboxOpen("<s:url value='/hbres/singup/manage/userInfo'/>?userFlow="+userFlow+"&isActive=","用户信息",1000,550);
	}
	function inputDoctorGrade(examFlow , doctorFlow , obj){
		if($(obj).validationEngine("validate")){
			return false;
		}
		var examResult = $(obj).val();
		var url = "<s:url value='/hbres/grade/inputdoctorgrade'/>";
		var reqData = {"examFlow":examFlow , "doctorFlow":doctorFlow , "examResult":examResult};
		jboxPost(url , reqData , function(resp){
			if(resp=="1"){
				var currentPage = $("#currentPage").val();
				gradeInput(currentPage);
			}else{
				jboxTip("操作失败");
			}
			
		} , null , false);
	}
</script>

<div class="main_bd" id="div_table_0" >
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">准考证号</th>
                <th style="text-align: center;">毕业院校</th>
                <th style="text-align: center;">毕业专业</th>
                <th style="text-align: center;">身份证号</th>
                <th>人员信息</th>
                <th>分数</th>
            </tr>
            <c:forEach items="${userList}" var="user">
            <tr>
                <td style="text-align: center;">${user.doctor.doctorName}</td>
                <td style="text-align: center;">${user.ticketNum}</td>
                <td style="text-align: center;">${user.doctor.graduatedName}</td>
                <td style="text-align: center;">
                   <c:forEach var="dict" items="${dictTypeEnumGraduateMajorList}">
              	        <c:if test='${user.doctor.specialized==dict.dictId}'>${dict.dictName}</c:if>
              	    </c:forEach>
                </td>
                <td style="text-align: center;">${user.user.idNo}</td>
                <td>
	                <a class="btn" onclick="getInfo('${user.doctorFlow}');">详细</a>
                </td>
				<c:if test="${!param.isFree}">
               	 <td><input name="examResult" class="validate[required,custom[number],min[0],max[999]] inp" value="${user.examResult}" onchange="inputDoctorGrade('${user.examFlow}' , '${user.doctorFlow}' , this)" style="width: 50px;text-align: center;"/></td>
            	</c:if>
				<c:if test="${param.isFree}">
               	 <td>${user.examResult}</td>
            	</c:if>
			</tr>
            </c:forEach>
            <c:if test="${empty userList}">
            	<tr>
	                <td colspan="7">无记录</td>
	            </tr>
            </c:if>
        </table>
    </div>
     <div class="page" style="text-align: center;">
       	 <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
           <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
	  		 <pd:pagination-jsres toPage="toPage"/>
        </div>
</div>
      
