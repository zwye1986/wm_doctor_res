<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link href="<s:url value="/jsp/srm/statistics/css/stylesheet.css"/>" rel="stylesheet" type="text/css">
	<title>科研项目管理</title>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
	</jsp:include>
	<%--<script type="text/javascript" src="js/echarts.min.js"></script>--%>
</head>
<body id="initCont" marginwidth="0" marginheight="0">
<div class="mainright">
	<h1 style="font-size: 18px">2016年经费到账个人龙虎榜</h1>
		
     <div class="box">
                <table class="xllist" style="width:92%; margin:auto; margin-top:20px;margin-bottom:20px;">
			<tbody>
            <tr>
				<th style="width:60px;">姓名</th>
				<th style="width:100px;">科室</th>
				<th style="width:100px;">科研经费</th>
                	<th style="width:60px;">到账经费</th>
				<th style="width:60px;">排名</th>
		
			</tr>
            
			<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">陈强</td>
					<td class="appoint" title="请点击进入">心胸外科</td>
					<td class="appoint">230</td>
					<td class="appoint">170</td>
					<td class="appoint">1</td>  
			</tr>
            
            	<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">韩冰</td>
					<td class="appoint" title="请点击进入">心内科</td>
					<td class="appoint">160</td>
					<td class="appoint">150</td>
					<td class="appoint">2</td>  
			</tr>
            
            	<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">殷海涛</td>
					<td class="appoint" title="请点击进入">放疗科</td>
					<td class="appoint">100</td>
					<td class="appoint">100</td>
					<td class="appoint">3</td>  
			</tr>
            
            	<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">孙玲</td>
					<td class="appoint" title="请点击进入">肾内科</td>
					<td class="appoint">300</td>
					<td class="appoint">80</td>
					<td class="appoint">4</td>  
			</tr>
            
            	<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">刘光旺</td>
					<td class="appoint" title="请点击进入">骨脊柱</td>
					<td class="appoint">120</td>
					<td class="appoint">80</td>
					<td class="appoint">5</td>  
			</tr>
            
            	<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">张文达</td>
					<td class="appoint" title="请点击进入">泌尿外科</td>
					<td class="appoint">170</td>
					<td class="appoint">70</td>
					<td class="appoint">6</td>  
			</tr>
            	
              	<tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">田志龙</td>
					<td class="appoint" title="请点击进入">血甲疝外科</td>
					<td class="appoint">85</td>
					<td class="appoint">68</td>
					<td class="appoint">7</td>  
			</tr>
            <tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">严晓南</td>
					<td class="appoint" title="请点击进入">生殖中心</td>
					<td class="appoint">77</td>
					<td class="appoint">55</td>
					<td class="appoint">8 </td>  
			</tr>
                 <tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">张培影</td>
					<td class="appoint" title="请点击进入">院机关</td>
					<td class="appoint">67</td>
					<td class="appoint">50</td>
					<td class="appoint">9</td>  
			</tr>
                     <tr id="933f16d9162d454aae2af158ce6aeb8b" subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">韩从辉</td>
					<td class="appoint" title="请点击进入">泌尿外科</td>
					<td class="appoint">87</td>
					<td class="appoint">40</td>
					<td class="appoint">10</td>  
			</tr>
           
			</tbody>
            </table>
    <div class="fenye fr">
        	<div class="fy fr" id="pageBox">
        	<a href="javascript:void(0)">上一页</a>
            <a class="fyon" href="javascript:void(0)">1</a><a href="javascript:void(0)">2</a><a href="javascript:void(0)">3</a>
            <a href="javascript:void(0)">4</a><a href="javascript:void(0)">5</a><a href="javascript:void(0)">6</a><a href="javascript:void(0)">7</a><a href="javascript:void(0)">8</a><a href="javascript:void(0)">9</a>
            <a href="javascript:void(0)">下一页</a>
        	</div>
        </div>
</div>


</div>

</body>
</html>
