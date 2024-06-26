<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ attribute name="toPage" required="true" %>
<script type="text/javascript">
<!--
function topage(page){
	if(parseInt(page)>parseInt('${pageView.totalpage}')){
		jboxTip("已至最后一页!");
		return;
	}
	${toPage}(page);
}
function change(obj){
	if(obj.options[obj.selectedIndex].value!="0"){
		${toPage}(obj.options[obj.selectedIndex].value);
	}
}
function topage1(){
	if($("#jumpPage").val()==""){
		jboxTip("跳转页面不能为空!");
		return;
	}
	var re = /^\+?[1-9][0-9]*$/; 
    if (!re.test($("#jumpPage").val()))  {
		jboxTip("页数必须为正整数");
		return;
	}
    var jumpPage = parseInt($("#jumpPage").val());
	if(jumpPage>parseInt('${pageView.totalpage}')){
		jboxTip("跳转页面不能大于总页数!");
		return;
	}
	topage(jumpPage);
}
function selectPagesize(pageFunc,obj){
    var size =$(obj).val();
    var appName = '${pageContext.request.contextPath}';
    if(appName == '/') {
        appName = '';
    }
    var url = appName+"/sys/cfg/savePageSize?pageSize="+size+"&pageServletPath=${pageServletPath}";
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
<span>
    <strong class="page_num">
<c:if test="${sysCfgMap['sys_page_size_cfg']=='Y' }">
    <%--<span id="pageSpan">--%>
    <c:set var="cfgCode" value="${pageServletPath}/${sessionScope.currUser.userFlow }"/>
    每页<select name="pageSize"  onchange="selectPagesize('${toPage}',this)" class="input" style="height: 30px;width: 50px" >
    <c:forEach varStatus="status" begin="5" end="50" step="5">
        <option value="${status.index }" <c:if test="${status.index==sessionScope[cfgCode] }"> selected="selected"</c:if> >${status.index }</option>
    </c:forEach>
    </select> 条&#12288;
    <%--</span>--%>
</c:if>
<%--共${pageView.totalrecord}条数据--%>
    </strong>
    <!-- 前一页 -->
    <c:if test='${pageView.currentpage > 1}'>
        <input type="button" class="btn_prev" style=" width:35px;" onclick="topage(${pageView.currentpage-1})"/>
    </c:if>
    <strong class="page_num">
        <!-- 当前页 -->
        <c:if test="${pageView.totalrecord > 0}">
            ${pageView.currentpage}
	    </c:if> 
	    <c:if test="${pageView.totalrecord == 0}">${pageView.totalrecord}</c:if>/${pageView.totalpage}
	</strong>
    <!-- 下一页 -->
    <c:if test='${pageView.currentpage<pageView.totalpage}'>
        <input type="button" class="btn_next" style=" width:35px;" onclick="topage(${pageView.currentpage+1})"/>
    </c:if>
    <input  type="text" id="jumpPage" class="input" style=" width:50px;"/>
    <a class="btn"  href="javascript:topage1();">跳转</a>
</span>
