<%@ page language="java" contentType="text/html; charset=UTF-8"	 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<%
		String recordFlow=request.getParameter("recordFlow");
	%>
	<script src="<s:url value='/js/jsres/canvasPutImg.js'/>"></script>
	<script>
		$(document).ready(function(){
			var recordFlow="<%=recordFlow%>";
			var url ="<s:url value='/res/jswjw/showCertificateImage?recordFlow='/>"+recordFlow;
			$.ajax({
				type : "get",
				url : url,
				cache : false,
				success : function(resp) {
					var obj=eval("("+resp+")");
					if(obj.code=="1") {
						var recruit = obj.recruit;
						var sysUser = obj.sysUser;
						var completeStartDate = obj.completeStartDate;
						var completeEndDate = obj.completeEndDate;
						var thisTime = obj.res_certificateDate;
						if (!thisTime)
							thisTime = recruit.certificateDate;
						var headImg = sysUser.userHeadImg;
						if (!headImg) {
							headImg = "<s:url value='/jsp/res/jswjw/showCertificate/up-pic.jpg'/>";
						}else {
							headImg=obj.uploadBaseUrl+"/"+headImg;
						}
						console.log(headImg);
						var result = "";
						try {
							result = recruit.graduationCertificateNo.substring(0, 4) + " " + recruit.graduationCertificateNo.substring(4, 6) +
									" " + recruit.graduationCertificateNo.substring(6, 10) + " " + recruit.graduationCertificateNo.substring(10, 13) +
									" " + recruit.graduationCertificateNo.substring(13, 16);
						} catch (e) {
							result = recruit.graduationCertificateNo;
						}
						if(recruit.graduationCertificateType == 'country')
						{
							$("#testDiv").css("width","588px");
							$("#testDiv").css("height","816px");
						}
						if(recruit.graduationCertificateType == 'AssiGeneral')
						{
							$("#testDiv").css("width","1008px");
							$("#testDiv").css("height","721px");
						}
						if(recruit.graduationCertificateType == 'provinceNoAll')
						{
							$("#testDiv").css("width","1031px");
							$("#testDiv").css("height","744px");
						}
						if(recruit.graduationCertificateType == 'provinceAll')
						{
							$("#testDiv").css("width","1080px");
							$("#testDiv").css("height","775px");
						}
						var map = {
							//国家证书样式
							"country": {
								imgs: [
									{
										//结业证书
										url: "<s:url value='/jsp/res/jswjw/showCertificate/guojiazhengshu.png'/>",
										args: [10, 15, 565, 780, 0, 0, 588, 816]
									},
									{
										//人物照片
										url: headImg,
										args: [237, 159, 106, 150]
									}
								],
								texts: [
									{
										//身份证号
										text: sysUser.idNo,
										x: 237,
										y: 320,
										style: '4px'
									},
									{
										//结业证书编号
										text: result,
										x: 253,
										y: 360,
										style: '18px 楷体'
									},
									{
										//姓名
										text: sysUser.userName,
										x: 95,
										y: 443,
										style: '15px 楷体',
										rowSize: 4
									},
									{
										//开始培训年份
										text: completeStartDate.substring(0, 4),
										x: 207,
										y: 445,
										style: '18px 楷体'
									},
									{
										//开始培训月份
										text: completeStartDate.substring(5, 7),
										x: 277,
										y: 445,
										style: '18px 楷体'
									},
									{
										//结束培训年份
										text: completeEndDate.substring(0, 4),
										x: 347,
										y: 445,
										style: '18px 楷体'
									},
									{
										//结束培训月份
										text: completeEndDate.substring(5, 7),
										x: 420,
										y: 445,
										style: '18px 楷体'
									},
									{
										//基地名称
										text: recruit.orgName,
										x: 94,
										y: 482,
										style: '15px 楷体',
										rowSize: 6
									},
									{
										//培训专业
										text: recruit.speName,
										x: 310,
										y: 482,
										style: '15px 楷体',
										witdh: 212
									},
									{
										text: thisTime.substring(0, 4),
										x: 360,
										y: 712,
										style: '18px 楷体'
									},
									{
										text: thisTime.substring(5, 7),
										x: 435,
										y: 712,
										style: '18px 楷体'
									},
									{
										text: thisTime.substring(8, 10),
										x: 480,
										y: 712,
										style: '18px 楷体'
									}
								],
								canattr: {
									id: 'downId'
								}
							},
							//江苏省全科
							"provinceAll": {
								imgs: [
									{
										//结业证书
										url: "<s:url value='/jsp/res/jswjw/showCertificate/jssquanke.png'/>",
										args: [15, 15, 1050, 745, 0, 0, 1080, 775]
									},
									{
										//人物照片
										url: headImg,
										args: [112, 262, 153, 202]
									}
								],
								texts: [
									{
										//结业证书编号
										text: recruit.graduationCertificateNo,
										x: 243,
										y: 640
									},
									{
										//姓名
										text: sysUser.userName,
										x: 490,
										y: 400,
										style: '30px 楷体',
										rowSize: 4
									},
									{
										text: thisTime.substring(0, 4),
										x: 610,
										y: 640
									},
									{
										text: thisTime.substring(5, 7),
										x: 715,
										y: 640
									},
									{
										text: thisTime.substring(8, 10),
										x: 806,
										y: 640
									}
								],
								canattr: {
									id: 'downId'
								}
							},
							//江苏省非全科
							"provinceNoAll": {
								imgs: [
									{
										//结业证书
										url: "<s:url value='/jsp/res/jswjw/showCertificate/jssfeiquanke.jpg'/>",
										args: [10, 10, 1011, 724, 0, 0, 1031, 744]
									},
									{
										//人物照片
										url: headImg,
										args: [82, 246, 146, 192]
									}
								],
								texts: [
									{
										//结业证书
										text: recruit.graduationCertificateNo.substring(7, recruit.graduationCertificateNo.length),
										x: 779,
										y: 271
									},
									{
										//结业证书
										text: recruit.graduationCertificateNo.substring(0, 6),
										x: 556,
										y: 271,
										style: "19px 楷体"
									},
									{
										//姓名
										text: sysUser.userName,
										x: 335,
										y: 345,
										style: '30px 楷体',
										rowSize: 4
									},
									{
										//培训专业
										text: recruit.speName,
										x: 580,
										y: 350,
										style: '30px 楷体',
										witdh: 225
									},
									{
										text: thisTime.substring(0, 4),
										x: 575,
										y: 623
									},
									{
										text: thisTime.substring(5, 7),
										x: 680,
										y: 623
									},
									{
										text: thisTime.substring(8, 10),
										x: 771,
										y: 623
									}
								],
								canattr: {
									id: 'downId'
								}

							},
							//不分年份的助理全科
							"AssiGeneral": {
								imgs: [
									{
										//结业证书
										url: "<s:url value='/jsp/res/jswjw/showCertificate/zhuliquanke.jpg'/>",
										args: [10, 10, 988, 701, 0, 0, 1008, 721]
									},
									{
										//人物照片
										url: headImg,
										args: [114, 284, 88, 117]
									}
								],
								texts: [
									{
										//结业证书编号
										text: recruit.graduationCertificateNo,
										x: 243,
										y: 592
									},
									{
										//姓名
										text: sysUser.userName,
										x: 300,
										y: 331,
										style: '30px 楷体',
										rowSize: 4
									},
									{
										text: thisTime.substring(0, 4),
										x: 570,
										y: 592
									},
									{
										text: thisTime.substring(5, 7),
										x: 675,
										y: 592
									},
									{
										text: thisTime.substring(8, 10),
										x: 766,
										y: 592
									}
								],
								canattr: {
									id: 'downId'
								}
							}
						};
						var options = map[recruit.graduationCertificateType];
						if (options != undefined) {
							$('#testDiv').drawCertificate(options);
						}
					}else{

					}
				},
				error : function() {

				}
			});

		});
	</script>
</head>
<body>
	<div id="testDiv" style="width: 588px;height: 0px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
	</div>
</body>
</html>