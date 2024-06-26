<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title>${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'?'苏州市住院医师公共科目学习平台':'南京医科大学研究生在线课程学习平台'}</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="userCenter" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
$(document).ready(function(){
	var id=$("#content").attr("id");
	var url="<s:url value ='/njmuedu/user/teachCourses'/>";
	jboxLoad(id,url,true);
	changeOrder("score");
});

function selectCourseTypeId(typeId){
   	var courseTypeId=$("#courseTypeId");
   	courseTypeId.val(typeId);
   	showCourse();
}

function changeQuestionStatus(statusId){
	$("#questionStatusId").val(statusId);
	showQuestion();
}

function showCourse(){
	$("#registerMsg-r").show();
	var courseTypeIdVal=$("#courseTypeId").val();
	var id=$("#content").attr("id");
	var url="<s:url value ='/njmuedu/user/teachCourses'/>?courseTypeId="+courseTypeIdVal;
	$("#content").html("");
	jboxLoad(id,url,true);
}

function showQuestion(){
	$("#registerMsg-r").hide();
	var questionStatusId=$("#questionStatusId").val();
	var id=$("#content").attr("id");
    var url="<s:url value ='/njmuedu/user/allQuestion'/>?questionStatusId="+questionStatusId;
	$("#content").html("");
	jboxLoad(id,url,true);
}

/**
 * 加载老师发布的作业
 */
function loadTeacherWorkList(){
	$("#registerMsg-r").hide();
	var url = "<s:url value='/njmuedu/user/teacherWorkList'/>";
 	jboxPostLoad("content", url, $("#searchForm").serialize(), true);
}

/**
 * 加载查看学生上传的作业
 */
function studentWorkAnswerList(questionFlow, courseFlow, chapterFlow, quesUserFlow){
	var url ="<s:url value='/njmuedu/user/studentWorkAnswerList'/>?questionFlow=" + questionFlow+"&courseFlow="+courseFlow +"&chapterFlow="+chapterFlow +"&quesUserFlow="+quesUserFlow;
	jboxPostLoad("content", url, $("#searchForm").serialize(), true);
}

function showReturn(questionFlow,obj){
	$("#answer_"+questionFlow).slideToggle("fast",function(){
		if($("#answer_"+questionFlow).is(":hidden")){
			$(obj).text("");
			$(obj).text("展开回复");
		}else if(!$("#answer_"+questionFlow).is(":hidden")){
			$(obj).text("");
			$(obj).text("收起回复");
		}
	});	
}
function submitAnswer(questionFlow){
	var form=$("#aForm_"+questionFlow);
	if(form.validationEngine("validate")){
	var url="<s:url value='/njmuedu/user/submitAnswer'/>";
	var requestData = form.serialize()+"&questionFlow="+questionFlow;
		jboxPost(url,requestData,function(resp){
			if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
				showQuestion();
				jboxTip("回复成功！");
			}
		},null,false);
	}
}
function loadCenter(){
	var url = "<s:url value='/njmuedu/user/student'/>";
	window.location.href = url;
}

function loadSetUserInfo(){
	$.each($(".level") , function(i , n){
		$(n).removeClass("cur");
	});
	$("#a_setInfo").addClass("cur");
	$("#registerMsg-r").hide();
	var url = "<s:url value='/njmuedu/user/setUserInfo'/>";
	jboxLoad("content",url,true);
}

function loadSetUserPasswd(){
	$("#registerMsg-r").hide();
	var url = "<s:url value='/njmuedu/user/setUserPasswd'/>";
	jboxLoad("content",url,true);
}

function loadCourseOrder(){
	var sortType = $('#sortType').val();
	var url = "<s:url value='/njmuedu/user/searchOrder'/>?sortType=" + sortType;
	jboxLoad("right-content",url,true);
}

function changeOrder(sortType){
	$("#sortType").val(sortType);
	loadCourseOrder();
}

function setYQuintessence(questionFlow,obj){
	var url="<s:url value='/njmuedu/user/setYQuintessence'/>?questionFlow="+questionFlow;
	jboxPost(url,null,function(resp){
		if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
			$(obj).hide();
			$(obj).prev().show();
			$("#img_"+questionFlow).show();
			jboxTip("操作成功！");
		}else{
			jboxTip("操作失败！");
		}
	},function(){
		jboxTip("操作失败！");
	},false);
}
function setNQuintessence(questionFlow,obj){
	var url="<s:url value='/njmuedu/user/setNQuintessence'/>?questionFlow="+questionFlow;
	jboxPost(url,null,function(resp){
		if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
			$(obj).hide();
			$(obj).next().show();
			$("#img_"+questionFlow).hide();
			jboxTip("操作成功！");
		}else{
			jboxTip("操作失败！");
		}
	},function(){
		jboxTip("操作失败！");
	},false);
}
</script>
</head>
<body style="background:#f4f4f4;">
 <jsp:include page="/jsp/njmuedu/include/top.jsp"/>
   
<!--content-->
<div class="register cbody clearfix">
   <div class="menu-warp fl">
    <ul class="menu">
      <li class="level1"  onclick="showCourse();"><a href="javascript:void(0);" class="level cur">我的课程</a></li>
      <li class="level1" onclick="showQuestion();"><a href="javascript:void(0);" class="level">全部问答</a></li>
      <li class="level1" onclick="loadTeacherWorkList();"><a href="javascript:void(0);" class="level">全部作业</a></li>
      <li class="level1" onclick="loadSetUserInfo();"><a href="javascript:void(0);" class="level" id="a_setInfo">个人设置</a></li> 
	  <li class="level1" onclick="loadSetUserPasswd();"><a href="javascript:void(0);" class="level">修改密码</a></li> 
     </ul>
   </div>

  <!--registerMsg-m
    <div class="registerMsg-m fl">
    	<div class="registerMsg-m-inner">
        	<div class="registerMsg-tabs">-->
               <!--  <input type="hidden" id="studyStatus"/> -->
                <input type="hidden" id="courseTypeId"/>
                <input type="hidden" id="questionStatusId"/>
                <div id="content">
                	<!-- 这里填充根据条件查询的课程列表 -->
                </div>
            <!-- </div>
        </div>
    </div>  --> 
   <!--/registerMsg-m--> 

  <!--registerMsg-r-->
  <div class="registerMsg-r fr" id="registerMsg-r">
    	<div class="registerMsg-r-top">
        	<dl>
           	  <dt class="fl">
		  			<img class="t-face" id="t-face-r" src="${sysCfgMap['upload_base_url']}/${sessionScope[GlobalConstant.CURRENT_USER].userHeadImg}" onerror="this.src='<s:url value="/jsp/njmuedu/css/images/head-icon.png"/>'"/>
           	  </dt>
              <dd class="fl">
              	<span id="userName-r">${user.userName}</span>
                <p id="orgName-r">${user.orgName}</p>
              </dd>
            </dl>
          <ul>
            	<li>
                	<c:out value="${countCourseTypeMap[njmuEduCourseTypeEnumRequired.id] }" default="0"/><p>${njmuEduCourseTypeEnumRequired.name }</p>
                </li>
                <li>
                	<c:out value="${countCourseTypeMap[njmuEduCourseTypeEnumOptional.id] }" default="0"/><p>${njmuEduCourseTypeEnumOptional.name }</p>
                </li>
                <li>
                	<c:out value="${countCourseTypeMap[njmuEduCourseTypeEnumPublic.id] }" default="0"/><p>${njmuEduCourseTypeEnumPublic.name }</p>
                </li>
            </ul>
            <a href="javascript:void(0);" onclick="loadSetUserInfo();"><img src="<s:url value='/jsp/njmuedu/css/images/pen.png'/>" width="15" height="15" /></a>
      </div>
      
      	<!-- 课程排行 -->
      <input type="hidden" id="sortType"/>
      <div class="registerMsg-r-bottom">
          <div id="right-content">
          
         </div>
      </div>
      
  </div>  
   <!--/registerMsg-r--> 

</div>
<!--footer-->
</body>
</html>