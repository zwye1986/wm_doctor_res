<!--top-text-->
<script type="text/javascript">
  function changeMajorTop(majorId){
     var url="<s:url value ='/njmuedu/course/findCourseList'/>?courseMajorId="+majorId;
     window.location.href=url;
  }

</script>
<div id="top-info">
	<div class="cbody top-text">
      <div class="top-left fl">
          <c:set var="szFlag" value="${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'}"/>
          <%--<a href="<s:url value='/inx/njmuedu' />">--%>
             <span><img  class="fl logo" src="<s:url value='/jsp/njmuedu/css/images/${szFlag?"szlogo1":"logo1"}.png'/>" /> </span>
          <%--</a>--%>
          <c:if test="${!szFlag}">
              <dl class="fl"  id="tab-course" style="cursor: pointer;">
                  <dt class="category-icon">课程类别</dt>
                  <dd></dd>
              </dl>
          </c:if>
        <div class="course-catagory" id="top-course" style="display: none">
          <div class="catagory-links">
            <h6><span>全部课程</span></h6>
            <hr>
            <div class="row">
              <ul>
                <li><img src="<s:url value='/jsp/njmuedu/css/images/all.png'/>" /><a href="<s:url value='/njmuedu/course/findCourseList'/>">发现课程</a></li>
                <li><img src="<s:url value='/jsp/njmuedu/css/images/own.png'/>" /><a href="<s:url value='/${sessionScope.currView}'/>">我的课程</a></li>
              </ul>
            </div>
            <h6><span>课程类别</span></h6>
            <hr>
            <div class="row2">
              <ul>
                <c:forEach items="${dictTypeEnumNjmuCourseMajorList }" var="dict">
                  <li style="cursor: pointer;" onclick="changeMajorTop('${dict.dictId }');">${dict.dictName }</li>
                </c:forEach>
              </ul>
            </div>
          </div>
        </div>
        <!-- <dl class="fl">
              <dt><a href="">高校联盟</a></dt>
              <dd></dd>
          </dl> -->
          <%-- <dl class="fl">
              <dt class="teacher-icon"><a href="<s:url value='/njmuedu/user/tchMienList'/>">教师风采</a></dt>
              <dd></dd>
          </dl> --%>
      </div>
      <div class="top-right fr" id="tabSide">
       	<dl class="fl">
        	<dt>
  			<img class="t-face" id="t-face" src="${sysCfgMap['upload_base_url']}/${sessionScope[GlobalConstant.CURRENT_USER].userHeadImg}" onerror="this.src='<s:url value="/jsp/njmuedu/css/images/head-icon.png"/>'"/>
        	&nbsp;&nbsp;&nbsp;&nbsp;<span id="currUserName">${sessionScope.currUser.userName}</span>&nbsp;&nbsp;&nbsp;&nbsp;<img  src="<s:url value='/jsp/njmuedu/css/images/dropdown.png'/>" width="13" height="8" onclick="showMenu()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dt>
            <dd></dd>
        </dl>
        <ul class="top-tab" id="topMenu" style="display:none;">
          <!-- <li><a href="<s:url value='/inx/njmuedu' />">回到首页</a></li> -->
         <%--  <li><a href="<s:url value='/njmuedu/course/findCourseList'/>">全部课程</a></li> --%>
          <li onclick="loadSetUserInfo();"><a href="javascript:void(0);">个人中心</a></li>
          <li><a href="<s:url value='/inx/njmuedu/logout' />">退出</a></li> 
        </ul>
      </div>
      <script>
      $(function(){
    	    var tabSide = $('#tabSide');
    	    tabSide.on('click',function(e){e.stopPropagation();})
    	    .find('>dl').on('click',function(){
    	    	tabSide.find('>ul').show();
    	    });
    	    $(document).on('click',function(){tabSide.find('>ul').hide();})
    	});
    	
    	$(function(){
    	    var tabCourse = $('#tab-course');
    	    tabCourse.on('click',function(e){e.stopPropagation();});
    	    tabCourse.on('click',function(){
    	    	$("#top-course").show();
    	    });
    	    $(document).on('click',function(){$("#top-course").hide();});
    	});
      </script>
   </div>   
</div>
<!--/top-text-->