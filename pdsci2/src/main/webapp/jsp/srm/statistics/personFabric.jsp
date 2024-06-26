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
	<h1 style="font-size: 18px">人员结构分析表</h1>
     <div class="box">
                <table class="xllist" style="width:92%; margin:auto; margin-top:20px;margin-bottom:20px;">
			<tbody>
            <tr>
				<th  rowspan="2"  style="width:100px;">职称</th>
				<th  colspan="2"  style="width:100px;">学历</th>
				<th  colspan="4"  style="width:100px;">学位</th>
		
			</tr>
               <tr>
				<th   style="width:100px;">研究生</th>
				<th   style="width:100px;">本科</th>
				<th   style="width:100px;">博士后</th>
		        <th   style="width:100px;">博士</th>
                <th   style="width:100px;">硕士</th>    
                 <th   style="width:100px;">学士</th>  
			</tr>
            
            
			<tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主治医师</td>
					<td class="appoint" title="请点击进入">45</td>
					<td class="appoint"></td>
					<td class="appoint">7</td>
					<td class="appoint">15</td>  
                    <td class="appoint">23</td>
					<td class="appoint"></td> 
			</tr>
            	<tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">副主任医师</td>
					<td class="appoint" title="请点击进入">60</td>
					<td class="appoint"></td>
					<td class="appoint">2</td>
					<td class="appoint">15</td>  
                    <td class="appoint">43</td>
					<td class="appoint"></td> 
			</tr>
            
                   	<tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主治医师</td>
					<td class="appoint" title="请点击进入">22</td>
					<td class="appoint">145</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">22</td>
					<td class="appoint">245</td> 
			</tr>
                <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">医师</td>
					<td class="appoint" title="请点击进入">88</td>
					<td class="appoint">158</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">88</td>
					<td class="appoint">158</td> 
			</tr>
            	<tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主任护师</td>
					<td class="appoint" title="请点击进入">18</td>
					<td class="appoint">22</td>
					<td class="appoint"></td>
					<td class="appoint">2</td>  
                    <td class="appoint">16</td>
					<td class="appoint">22</td> 
			</tr>
            	<tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">副主任护师</td>
					<td class="appoint" title="请点击进入">9</td>
					<td class="appoint">36</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">9</td>
					<td class="appoint">36</td> 
			</tr>
           <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主管护师</td>
					<td class="appoint" title="请点击进入">1</td>
					<td class="appoint">58</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">1</td>
					<td class="appoint">58</td> 
			</tr>
            <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">护师</td>
					<td class="appoint" title="请点击进入"></td>
					<td class="appoint">428</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint"></td>
					<td class="appoint">428</td> 
			</tr>
            <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主任药师</td>
					<td class="appoint" title="请点击进入">2</td>
					<td class="appoint"></td>
					<td class="appoint"></td>
					<td class="appoint">1</td>  
                    <td class="appoint">1</td>
					<td class="appoint"></td> 
			</tr>
             <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">副主任药师</td>
					<td class="appoint" title="请点击进入">4</td>
					<td class="appoint"></td>
					<td class="appoint"></td>
					<td class="appoint">1</td>  
                    <td class="appoint">3</td>
					<td class="appoint"></td> 
			</tr>
             <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主管药师</td>
					<td class="appoint" title="请点击进入">6</td>
					<td class="appoint">4</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">6</td>
					<td class="appoint">4</td> 
			</tr>
                <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">药师</td>
					<td class="appoint" title="请点击进入">6</td>
					<td class="appoint">16</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">6</td>
					<td class="appoint">16</td> 
			</tr>
               <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主任技师</td>
					<td class="appoint" title="请点击进入">18</td>
					<td class="appoint"></td>
					<td class="appoint"></td>
					<td class="appoint">6</td>  
                    <td class="appoint">12</td>
					<td class="appoint"></td> 
			</tr>
             <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">副主任技师</td>
					<td class="appoint" title="请点击进入">7</td>
					<td class="appoint">22</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">7</td>
					<td class="appoint">22</td> 
			</tr>
            
               <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">主管技师</td>
					<td class="appoint" title="请点击进入">10</td>
					<td class="appoint">45</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint">10</td>
					<td class="appoint">45</td> 
			</tr>
                  <tr  subjectflow="40c660c45b3c4dfe9d6fd3010ee3b5e2"s>
					<td class="appoint" title="请点击进入">技师</td>
					<td class="appoint" title="请点击进入"></td>
					<td class="appoint">77</td>
					<td class="appoint"></td>
					<td class="appoint"></td>  
                    <td class="appoint"></td>
					<td class="appoint">77</td> 
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
