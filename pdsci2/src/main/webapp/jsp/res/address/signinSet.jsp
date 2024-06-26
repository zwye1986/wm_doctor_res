<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(function(){
			<c:if test="${empty addressList}">
				addMember('');
			</c:if>
			<c:forEach var="address" items="${addressList}">
				addMember("${address.recordFlow}");
			</c:forEach>
		})
	function save(){
		if($("#memberList").find("tr").length<=0)
		{
			jboxTip("至少设置一个签到地点");
			return false;
		}
		var bean={};
		bean.orgFlow="${sessionScope.currUser.orgFlow }";
		var addresses=[];
		var i=0;
		var i2=0;
		var f=true;
		$("#memberList").find("tr").each(function(index){
			var iframe=$(this).find("iframe");
			var longitude=$(iframe).contents().find("input[name=longitude]").val();
			var latitude=$(iframe).contents().find("input[name=latitude]").val();
			var recordFlow=$(iframe).contents().find("input[name=recordFlow]").val();
			var orgAddress=$(iframe).contents().find("input[name=orgAddress]").val();
			var scopeLength=$(iframe).contents().find("input[name=scopeLength]").val();
			var  address={};
				address.recordFlow=recordFlow;
				address.orgAddress=orgAddress;
				address.orgFlow=bean.orgFlow;
				address.scopeLength=scopeLength;
				address.latitude=latitude;
				address.longitude=longitude;
				addresses.push(address);
			if(!orgAddress)
			{
				jboxTip("请为第"+(index+1)+"签到点，设置签到地址！");
				f=false ;
				return false;
			}
			if(!scopeLength)
			{
				jboxTip("请为第"+(index+1)+"签到点，设置签到半径！");
				f=false ;
				return false;
			}
			if(!isPositiveInteger(scopeLength)){//非数字
				jboxTip("第"+(index+1)+"签到点的签到半径只能是正整数！");
				f=false ;
				return false;
			}
		});
		bean.addresses=addresses;
		if(f)
		{
			console.log(JSON.stringify(bean));
			var url = "<s:url value='/res/address/saveSigninSetList'/>";
			jboxPostJson(url,  JSON.stringify(bean), function(resp) {
				if(resp=="保存成功！") {
					window.location.reload(true);
				}
			}, null, true);
		}
	}
		function isPositiveInteger(s){//是否为正整数
			var re = /^[1-9][0-9]+$/ ;
			return re.test(s)
		}
		function addMember(recordFlow){
		var index=$("#memberList").find("tr").length+1;
		var url ='<s:url value="/res/address/oneAddress?recordFlow="/>'+recordFlow;
		var iframe ="<iframe  width='100%' height='100%' " + " id='iframeId"+index+"'"+
				"marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		var tr =$("#moban tr:eq(0)").clone();
		$(tr).find("td[name='iframeTd']").html(iframe)
		$('#memberList').append($(tr));
		jboxStartLoading();
	}
	function moveTr(obj){
		if($("#memberList").find("tr").length<=1)
		{
			jboxTip("至少设置一个签到地点");
			return false;
		}
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
		});
	}
</script>
	<style type="text/css">
		.xllist th, .xllist td {
			 border: 1px solid #bbbbbb;
		}
		.iframeTd {
			height: 500px;
		}
	</style>
</head>
<body>

<div class="mainright">
		<fieldset style="margin-top: 20px;">
			<table class="xllist nofix" id="moban" style="display: none" style="width: 100%">
				<tr>
					<td name="iframeTd" class="iframeTd" style="height: 481px;">

					</td>
					<td style="width:100px;">
						<img class="opBtn" title="删除" src="/pdsci/css/skin/LightBlue/images/del1.png"
							 style="cursor: pointer;" onclick="moveTr(this);"/>
					</td>
				</tr>
			</table>
			<div style="min-height: 486px;margin-top: 20px;">
				<table class="xllist nofix" style="font-size: 25px;width:100%;border:1px solid #bbbbbb;">
					<thead>
						<tr>
							<th colspan="2">考勤定位地点设置

								<span style="padding-right: 0px;">
									<img class="opBtn" title="新增"
										 src="/pdsci/css/skin/LightBlue/images/add3.png"
										 style="cursor: pointer;" onclick="addMember('');"/>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="memberList">
					</tbody>
				</table>
			</div>
			<div style="width: 100%">
				<div class="button">
					<input type="button" class="search" onclick="save();" value="保&#12288;存"/>
				</div>
			</div>
		</fieldset>
</div>
</body>
</html>