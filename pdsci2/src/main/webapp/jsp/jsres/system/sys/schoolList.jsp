<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
</script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">学校管理</h2> 
</div>
<div class="main_bd">
	<div class="div_search">
		学校名称：<input type="text" class="input" style="width: 240px;"/><input class="btn_green" type="button" value="查&#12288;询"/>
	</div>
	<div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
        		<col width="50%"/>
        		<col width="50%"/>
        	</colgroup>
        	<tr>
                <th>学校名称</th>
                <th>操作</th>
            </tr>
             <tr>
                <td>南洋医学高等专科学校</td>
                <td><a class="btn">编辑</a>&#12288;<a class="btn">删除</a></td>
            </tr>
            <tr>
                <td>安徽医学高等专科学校</td>
                <td><a class="btn">编辑</a>&#12288;<a class="btn">删除</a></td>
            </tr>
            <tr>
                <td>卡罗林斯卡医学院</td>
                <td><a class="btn">编辑</a>&#12288;<a class="btn">删除</a></td>
            </tr>
            <tr>
                <td>浙江嘉兴学院</td>
                <td><a class="btn">编辑</a>&#12288;<a class="btn">删除</a></td>
            </tr>
        </table>
	</div>
	<div class="page" style="padding-right: 40px;">
       	 <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
           <c:set var="pageView" value="${pdfn:getPageView(hospitalList)}" scope="request"></c:set>
	  		 <pd:pagination-jsres toPage="toPage"/>	 
        </div>
    <div class="btn_info">
      <input class="btn_blue" style="width:100px;" onclick="" type="button" value="添加"/>
    </div>
	
</div>
</body>
</html>