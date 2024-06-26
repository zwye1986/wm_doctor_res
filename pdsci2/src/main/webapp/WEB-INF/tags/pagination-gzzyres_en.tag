<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ tag body-content="scriptless" pageEncoding="UTF-8"%>
<%@ attribute name="toPage" required="true" %>
<script type="text/javascript">
<!--
function topage(page){
	if(parseInt(page)>parseInt('${pageView.totalpage}')){
		jboxTip("To the last page!");
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
		jboxTip("The jump page cannot be empty!");
		return;
	}
	var re = /^\+?[1-9][0-9]*$/; 
    if (!re.test($("#jumpPage").val()))  {
		jboxTip("The number of pages must be positive integers!");
		return;
	}
    var jumpPage = parseInt($("#jumpPage").val());
	if(jumpPage>parseInt('${pageView.totalpage}')){
		jboxTip("The jump page cannot be larger than the total page number!");
		return;
	}
	topage(jumpPage);
}
//-->
</script>
<span>
    <!-- 前一页 -->
    <c:if test='${pageView.currentpage > 1}'>
        <input type="button" class="btn_prev" style=" width:35px;" onclick="topage(${pageView.currentpage-1})"></input> 
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
        <input type="button" class="btn_next" style=" width:35px;" onclick="topage(${pageView.currentpage+1})"></input> 
    </c:if>
    <input  type="text" id="jumpPage" class="input" style=" width:50px;"/>
    <a class="btn"  href="javascript:topage1();">Go</a>
</span>
