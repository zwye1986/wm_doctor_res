<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<script src="<s:url value='/js/jsres/canvasPutImg.js'/>"></script>
<script type="text/javascript">
	var map={
		//国家证书样式
		"country":{
			<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${recruit.certificateDate}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jszy/daochu/guojiazhengshu.png'/>",
					args:[10,15,565,780,0,0,588,816]
				},
				{
					//人物照片
					url:
							<c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin//up-pic.jpg'/>",
					</c:if>
					<c:if test="${not empty sysUser.userHeadImg }">
					"${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
					</c:if>
					args:[237,159,106,150]
				}
			],
			texts:[
				{
					//身份证号
					text:'${sysUser.idNo}',
					x:237,
					y:320,
					style: '4px'
				},
				{
					//结业证书编号
					text:'${pdfn:completeNoSplit(recruit.graduationCertificateNo)}',
					x:253,
					y:360,
					style: '18px 楷体'
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:95,
					y:443,
					style: '15px 楷体',
					rowSize:4
				},
				{
					//开始培训年份
					text:'${fn:substring(completeStartDate,0,4)}',
					x:207,
					y:445,
					style: '18px 楷体'
				},
				{
					//开始培训月份
					text:'${fn:substring(completeStartDate,5,7)}',
					x:277,
					y:445,
					style: '18px 楷体'
				},
				{
					//结束培训年份
					text:'${fn:substring(completeEndDate,0,4)}',
					x:347,
					y:445,
					style: '18px 楷体'
				},
				{
					//结束培训月份
					text:'${fn:substring(completeEndDate,5,7)}',
					x:420,
					y:445,
					style: '18px 楷体'
				},
				{
					//基地名称
					text:'${recruit.orgName}',
					x:94,
					y:482,
					style: '15px 楷体',
					rowSize:6
				},
				{
					//培训专业
					text:'${recruit.speName}',
					x:310,
					y:482,
					style: '15px 楷体',
					witdh:212
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:360,
					y:712,
					style: '18px 楷体'
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:435,
					y:712,
					style: '18px 楷体'
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:480,
					y:712,
					style: '18px 楷体'
				}
			],
			canattr:{
				id:'downId'
			}
		},
		//江苏省全科
		"provinceAll":{
			<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${recruit.certificateDate}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jszy/daochu/jssquanke.png'/>",
					args:[15,15,1050,745,0,0,1080,775]
				},
				{
					//人物照片
					url: <c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin//up-pic.jpg'/>",
					</c:if>
					<c:if test="${not empty sysUser.userHeadImg }">
					"${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
					</c:if>
					args:[112,262,153,202]
				}
			],
			texts:[
				{
					//结业证书编号
					text:'${recruit.graduationCertificateNo}',
					x:243,
					y:640
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:490,
					y:400,
					style: '30px 楷体',
					rowSize:4
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:610,
					y:640
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:715,
					y:640
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:806,
					y:640
				}
			],
			canattr:{
				id:'downId'
			}
		},
		//江苏省非全科
		"provinceNoAll":{
			<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${recruit.certificateDate}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jszy/daochu/jssfeiquanke.jpg'/>",
					args:[10,10,1011,724,0,0,1031,744]
				},
				{
					//人物照片
					url: <c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin//up-pic.jpg'/>",
					</c:if>
					<c:if test="${not empty sysUser.userHeadImg }">
					"${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
					</c:if>
					args:[82,246,146,192]
				}
			],
			texts:[
				{
					//结业证书
					text:'${fn:substring(recruit.graduationCertificateNo,7,fn:length(recruit.graduationCertificateNo))}',
					x:779,
					y:271
				},
				{
					//结业证书
					text:'${fn:substring(recruit.graduationCertificateNo,0,6)}',
					x:556,
					y:271,
					style:"19px 楷体"
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:335,
					y:345,
					style: '30px 楷体',
					rowSize:4
				},
				{
					//培训专业
					text:'${recruit.speName}',
					x:580,
					y:350,
					style: '30px 楷体',
					witdh:225
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:575,
					y:623
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:680,
					y:623
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:771,
					y:623
				}
			],
			canattr:{
				id:'downId'
			}

		},
		//不分年份的助理全科
		"AssiGeneral":{<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${recruit.certificateDate}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jszy/daochu/zhuliquanke.jpg'/>",
					args:[10,10,988,701,0,0,1008,721]
				},
				{
					//人物照片
					url: <c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin//up-pic.jpg'/>",
					</c:if>
					<c:if test="${not empty sysUser.userHeadImg }">
					"${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
					</c:if>
					args:[114,284,88,117]
				}
			],
			texts:[
				{
					//结业证书编号
					text:'${recruit.graduationCertificateNo}',
					x:243,
					y:592
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:300,
					y:331,
					style: '30px 楷体',
					rowSize:4
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:570,
					y:592
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:675,
					y:592
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:766,
					y:592
				}
			],
			canattr:{
				id:'downId'
			}
		},
	};
$(document).ready(function(){
	var options=map['${recruit.graduationCertificateType}'];
	if(options!=undefined){
		$('#testDiv').drawCertificate(options);
	}

});
function  down(name)
{
	name=name+".jpg";
	$('#testDiv canvas').downCanvasImg(name);
}
</script>
<div id="all" style="width: auto;" align="center" onResize="Resize()" onLoad="Resize()">
	<c:if test="${notHave eq 'Y'}">
		<div id="testDiv" style="width: 588px;height: 816px;border: 0px solid;" onResize="Resize()" onLoad="Resize()">

		</div>
	</c:if>
	<c:if test="${notHave ne 'Y'}">
		<%--国家证书--%>
		<c:if test="${recruit.graduationCertificateType eq 'country'}">
			<div id="testDiv" style="width: 588px;height: 816px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
				<div align="right">
					<c:if test="${empty param.noDown}">
						<input type="button" id="submitBtn" class="btn_brown" onclick="down('${sysUser.userName}${recruit.graduationCertificateNo}');" value="下&#12288;载"/>&nbsp;
					</c:if>
				</div>
			</div>
		</c:if>
		<%--助理全科证书--%>
		<c:if test="${recruit.graduationCertificateType eq 'AssiGeneral'}">
			<div id="testDiv" style="width: 1008px;height: 721px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
				<div align="right">
					<c:if test="${empty param.noDown}">
						<input type="button" id="submitBtn" class="btn_brown" onclick="down('${sysUser.userName}${recruit.graduationCertificateNo}');" value="下&#12288;载"/>&nbsp;
					</c:if>
				</div>
			</div>
		</c:if>
		<%--非全科证书--%>
		<c:if test="${recruit.graduationCertificateType eq 'provinceNoAll'}">
			<div id="testDiv" style="width: 1031px;height: 744px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
				<div align="right">
					<c:if test="${empty param.noDown}">
						<input type="button" id="submitBtn" class="btn_brown" onclick="down('${sysUser.userName}${recruit.graduationCertificateNo}');" value="下&#12288;载"/>&nbsp;
					</c:if>
				</div>
			</div>
		</c:if>
		<%--全科证书--%>
		<c:if test="${recruit.graduationCertificateType eq 'provinceAll'}">
			<div id="testDiv" style="width: 1080px;height: 775px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
				<div align="right">
					<c:if test="${empty param.noDown}">
						<input type="button" id="submitBtn" class="btn_brown" onclick="down('${sysUser.userName}${recruit.graduationCertificateNo}');" value="下&#12288;载"/>&nbsp;
					</c:if>
				</div>
			</div>
		</c:if>
	</c:if>
</div>

