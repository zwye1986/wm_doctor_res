<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<!-- meta使用viewport以确保页面可自由缩放 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="<s:url value="/jsp/res/eval/js/jquery.mobile-1.4.5.min.css"/>"/>
	<script src="<s:url value="/jsp/res/eval/js/jquery.js"/>"></script>
	<script src="<s:url value="/jsp/res/eval/js/jquery.mobile-1.4.5.min.js"/>"></script>
	<title>请求参数出错</title>
	<style type="text/css">
		.th_group .left{
			text-align: left;
		}
		.th_group .right{
			text-align: right;
		}
		.th_group th {
			background-color: rgba(0,0,0,0.07);
			border: 1px solid rgba(0,0,0,0.07);
			text-align: center;
			vertical-align:middle;
		}
		.td_group td {
			border: 1px solid rgba(0,0,0,0.07);
			text-align: center;
			vertical-align:middle;
		}
		.td_group th {
			border: 1px solid rgba(0,0,0,0.07);
			text-align: center;
			vertical-align:middle;
		}
		body {
			width:100%;
			overflow:auto;
		}
	</style>
</head>
<body >
<div data-role="page" id="pageone">
	<div data-role="header">
		<h1>请求参数出错！！！</h1>
	</div>
	<div data-role="main" class="ui-content">
		<table data-role="table" data-mode=""  class="ui-responsive table-stroke">
			<tbody  id="dataTable">
			<tr class="td_group">
				<th>${resultType}</th>
			</tr>
			</tbody>
		</table>
	</div>
	<div data-role="popup" id="myPopup" class="ui-content">
		<p id="errorMsg"></p>
	</div>

	<div data-role="footer">
		<h1></h1>
	</div>
</div>
</body>
</html>