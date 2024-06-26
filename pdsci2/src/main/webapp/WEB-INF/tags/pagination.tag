<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ attribute name="toPage" required="true" %>
<script type="text/javascript">
<%--function topage(page){--%>
	<%--${toPage}(page);--%>
<%--}--%>
function change(pageFunc,obj){
	if(obj.options[obj.selectedIndex].value!="0"){
		var  func=eval(pageFunc);
		//创建函数对象，并调用
		new func(obj.options[obj.selectedIndex].value);
	}
}
function changeInput(pageFunc,obj){

	var page = $(obj).prev(".fenye_input").val();
	var totalPage = parseInt("${pageView.totalpage}");
	if(page==""){
		return false;
	}
	if(parseInt(page) > totalPage ){
		page = totalPage;
	}
	var  func=eval(pageFunc);
	//创建函数对象，并调用
	new func(page);
}

function selectPagesize(pageFunc,obj){
	var size =$(obj).val();
	var appName = '${pageContext.request.contextPath}';
	if(appName == '/') {
		appName = '';
	}
	var url = appName +"/sys/cfg/savePageSize?pageSize="+size+"&pageServletPath=${pageServletPath}";
	if(jboxPost){
		jboxPost(url,null,function(resp){
			if(resp){
				var  func=eval(pageFunc);
				//创建函数对象，并调用
				new func(1);
			}
		},null,false);
	}
}
//-->
</script>
<div style="margin-top: 15px; width:100%;text-align:center;">
	<div id="fenye">
	<c:if test="${sysCfgMap['sys_page_size_cfg']=='Y' }">
	<span id="pageSpan">
	<c:set var="cfgCode" value="${pageServletPath}/${sessionScope.currUser.userFlow }"/>
	每页<select name="pageSize"  onchange="selectPagesize('${toPage}',this)" class="fenye_select">
	   <c:forEach varStatus="status" begin="10" end="50" step="5">
	    	<option value="${status.index }" <c:if test="${status.index==sessionScope[cfgCode] }"> selected="selected"</c:if> >${status.index }</option>
	   </c:forEach>
    </select> 条&nbsp;
    </span>
    </c:if>
	共${pageView.totalrecord}条数据 页次: 
	  <c:if test="${pageView.totalrecord > 0}">
	     ${pageView.currentpage}
	  </c:if> 
	  <c:if test="${pageView.totalrecord == 0}">
	     ${pageView.totalrecord}</c:if>/${pageView.totalpage}页
	  <c:if test="${pageView.currentpage<=1}" >
	     <a class="fenye_button">首页</a>
	  </c:if>
	  <c:if test="${pageView.currentpage>1}" >
	     <a href="javascript:${toPage}('1')" class="fenye_button">首页</a>
	  </c:if>
	  <c:if test="${pageView.currentpage<=1}" >
	     <a class="fenye_button">上一页</a>
	  </c:if>
	  <c:if test="${pageView.currentpage>1}" >
	     <a href="javascript:${toPage}('${pageView.currentpage-1}')" class="fenye_button">上一页</a>
	  </c:if>
	  <c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
	    <c:if test="${pageView.currentpage==wp}">
	      <a class="fenye_num_s">${wp}</a>
	    </c:if>
	    <c:if test="${pageView.currentpage!=wp}">
	      <a href="javascript:${toPage}('${wp}')" class="fenye_num">${wp}</a>
	    </c:if>
	  </c:forEach>
	   <c:if test="${pageView.currentpage>=pageView.totalpage}" >
	      <a class="fenye_button">下一页</a>
	   </c:if>
	   <c:if test="${pageView.currentpage<pageView.totalpage}" >
	      <a href="javascript:${toPage}('${pageView.currentpage+1}')" class="fenye_button">下一页</a>
	   </c:if>
	   <c:if test="${pageView.currentpage>=pageView.totalpage}" >
	      <a class="fenye_button">尾页</a>
	   </c:if>
	   <c:if test="${pageView.currentpage<pageView.totalpage}" >
	      <a href="javascript:${toPage}('${pageView.totalpage}')" class="fenye_button">尾页</a>
	   </c:if> 
	   <c:choose>
		   	<c:when test="${sysCfgMap['sys_page_style']=='input'}">
				页码<input class="fenye_input" id="inputPage" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" /><a href="javascript:void(0);" onclick="changeInput('${toPage}',this)"class="fenye_button">GO</a>
		    </c:when>
		    <c:otherwise>
		    	跳转到<select name="selectPage" onchange="change('${toPage}',this)" class="fenye_select">
			      <option value="0">页码</option>
			      <c:forEach begin="1" end="${pageView.totalpage}" var="sp">
			        <option value="${sp}"  <c:if test="${sp==pageView.currentpage}">selected="selected"</c:if> >${sp}</option>
			      </c:forEach>
			    </select>
		    </c:otherwise>
	    </c:choose>
	</div>
</div>
