<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
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
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>

<script src="<s:url value='/js/jsres/canvasPutImg.js'/>"></script>
<script type="text/javascript">
	var map={
		//国家证书样式
		"country":{
			<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/guojiazhengshu.png'/>",
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
					text:'${pdfn:completeNoSplit(resDoctor.completeNo)}',
					x:253,
					y:360,
					style: '18px Arial'
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:95,
					y:443,
					style: '15px Arial',
					rowSize:4
				},
				{
					//开始培训年份
					text:'${fn:substring(resDoctor.completeStartDate,0,4)}',
					x:207,
					y:445,
					style: '18px Arial'
				},
				{
					//开始培训月份
					text:'${fn:substring(resDoctor.completeStartDate,5,7)}',
					x:277,
					y:445,
					style: '18px Arial'
				},
				{
					//结束培训年份
					text:'${fn:substring(resDoctor.completeEndDate,0,4)}',
					x:347,
					y:445,
					style: '18px Arial'
				},
				{
					//结束培训月份
					text:'${fn:substring(resDoctor.completeEndDate,5,7)}',
					x:420,
					y:445,
					style: '18px Arial'
				},
				{
					//基地名称
					text:'${resDoctor.orgName}',
					x:94,
					y:482,
					style: '15px Arial',
					rowSize:6
				},
				{
					//培训专业
					text:'${resDoctor.trainingSpeName}',
					x:310,
					y:482,
					style: '15px Arial',
					witdh:212
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:360,
					y:712,
					style: '18px Arial'
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:435,
					y:712,
					style: '18px Arial'
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:480,
					y:712,
					style: '18px Arial'
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
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/jssquanke.png'/>",
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
					text:'${resDoctor.completeNo}',
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
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/jssfeiquanke.jpg'/>",
					args:[10,10,1011,724,0,0,1031,744]
				},
				{
					//人物照片
					url: <c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin/up-pic.jpg'/>",
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
					text:'${fn:substring(resDoctor.completeNo,7,fn:length(resDoctor.completeNo))}',
					x:779,
					y:271
				},
				{
					//结业证书
					text:'${fn:substring(resDoctor.completeNo,0,6)}',
					x:556,
					y:271,
					style:"19px 隶书"
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:335,
					y:345,
					style: '30px 隶书',
					rowSize:4
				},
				{
					//培训专业
					text:'${resDoctor.trainingSpeName}',
					x:580,
					y:350,
					style: '30px 隶书',
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
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/zhuliquanke.jpg'/>",
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
					text:'${resDoctor.completeNo}',
					x:243,
					y:592
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:300,
					y:331,
					style: '30px 隶书',
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
	var mapShow={
		//国家证书样式
		"country":{
			<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/guojiazhengshu.png'/>",
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
					text:'${pdfn:completeNoSplit(resDoctor.completeNo)}',
					x:253,
					y:360,
					style: '18px Arial'
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:95,
					y:443,
					style: '15px Arial',
					rowSize:4
				},
				{
					//开始培训年份
					text:'${fn:substring(resDoctor.completeStartDate,0,4)}',
					x:207,
					y:445,
					style: '18px Arial'
				},
				{
					//开始培训月份
					text:'${fn:substring(resDoctor.completeStartDate,5,7)}',
					x:277,
					y:445,
					style: '18px Arial'
				},
				{
					//结束培训年份
					text:'${fn:substring(resDoctor.completeEndDate,0,4)}',
					x:347,
					y:445,
					style: '18px Arial'
				},
				{
					//结束培训月份
					text:'${fn:substring(resDoctor.completeEndDate,5,7)}',
					x:420,
					y:445,
					style: '18px Arial'
				},
				{
					//基地名称
					text:'${resDoctor.orgName}',
					x:94,
					y:482,
					style: '15px Arial',
					rowSize:6
				},
				{
					//培训专业
					text:'${resDoctor.trainingSpeName}',
					x:310,
					y:482,
					style: '15px Arial',
					witdh:212
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:360,
					y:712,
					style: '18px Arial'
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:435,
					y:712,
					style: '18px Arial'
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:480,
					y:712,
					style: '18px Arial'
				}
			],
			canattr:{
				id:'showDownId'
			}
		},
		//江苏省全科
		"provinceAll":{<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/doc/jssquanke.png'/>",
					args:[5,5,820,586,0,0,830,596]
				},
				{
					//人物照片
					url: <c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin//up-pic.jpg'/>",
					</c:if>
					<c:if test="${not empty sysUser.userHeadImg }">
					"${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
					</c:if>
					args:[92,203,115,153]
				}
			],
			texts:[
				{
					//结业证书编号
					text:'${resDoctor.completeNo}',
					x:190,
					y:490
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:370,
					y:310,
					style: '25px 隶书',
					rowSize:4
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:450,
					y:490
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:545,
					y:490
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:616,
					y:490
				}
			],
			canattr:{
				id:'showDownId'
			}
		},
		//江苏省非全科
		"provinceNoAll":{<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/doc/jssfeiquanke.png'/>",
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
					args:[65,196,115,152]
				}
			],
			texts:[
				{
					//结业证书
					text:'${fn:substring(resDoctor.completeNo,7,fn:length(resDoctor.completeNo))}',
					x:625,
					y:216,
					style:"15px Arial"
				},
				{
					//结业证书
					text:'${fn:substring(resDoctor.completeNo,0,6)}',
					x:445,
					y:216,
					style:"15px 隶书"
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:263,
					y:276,
					style: '25px 隶书',
					rowSize:4
				},
				{
					//培训专业
					text:'${resDoctor.trainingSpeName}',
					x:470,
					y:278,
					style: '25px 隶书',
					witdh:180
				},
				{
					text:'${fn:substring(thisTime,0,4)}',
					x:460,
					y:500
				},
				{
					text:'${fn:substring(thisTime,5,7)}',
					x:550,
					y:500
				},
				{
					text:'${fn:substring(thisTime,8,10)}',
					x:625,
					y:500
				}
			],
			canattr:{
				id:'showDownId'
			}
		},
		//不分年份的助理全科
		"AssiGeneral":{<c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
			<c:if test="${empty thisTime }">
			<c:set var="thisTime" value="${date}"></c:set>
			</c:if>
			imgs:[
				{
					//结业证书
					url:"<s:url value='/jsp/jsres/daochu/zhuliquanke.jpg'/>",
					args:[10,10,988,701,0,0,1008,721]
				},
				{
					//人物照片
					url: <c:if test="${empty  sysUser.userHeadImg }">
							"<s:url value='/css/skin/up-pic.jpg'/>",
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
					text:'${resDoctor.completeNo}',
					x:243,
					y:592
				},
				{
					//姓名
					text:'${sysUser.userName}',
					x:295,
					y:331,
					style: '30px 隶书',
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
				id:'showDownId'
			}
		},
	};
$(document).ready(function(){
	<c:if test="${resDoctor.graduationStatusId eq 'GrantCertf'}">show();</c:if>
});
function  down(name)
{
	name=name+".jpg";
	$('#testDivShow canvas').downCanvasImg(name);
}
	function  show()
	{
		<%--var options=map['${completeNo}'];--%>
		<%--if(options!=undefined){--%>
			<%--var f=$('#testDiv').drawCertificate(options).isfinish;--%>
			<%--console.log(f);--%>
			<%--$('#testDiv canvas').attr("hidden","hidden");--%>
		<%--}--%>
		var option=mapShow['${completeNo}'];
		console.log(option);
		if(option!=undefined){
			$('#testDivShow').drawCertificate(option);
		}
	}
</script>
<div class="main_hd">
		<h2 class="underline">证书查询</h2>
</div>

	<c:if test="${ resDoctor.graduationStatusId != 'GrantCertf'}">
		<div id="downDiv"  class="main_hd" align="center" style="margin-top:20px;border: 0px;">
			<h2 class="underline"style="border: 0px;">暂未发证</h2>
		</div>
	</c:if>
	<c:if test="${resDoctor.graduationStatusId eq 'GrantCertf'}">
		<div align="center">
			<font color="red"style="margin-top:20px;font-size:20px">仅供网络查看</font>
			<c:if test="${completeNo eq 'country'}">
				<div id="downDiv" align="right" style="width: 588px;">
					<input  type="button" id="submitBtn" class="btn_green" onclick="down('${sysUser.userName}${resDoctor.completeNo}');" value="下&#12288;载"/>&nbsp;
				</div>
				<div id="testDiv"   style="width: 588px;height: 816px;border: 0px solid;margin-top: 20px; display: none" >

				</div>
				<div id="testDivShow" style="width: 588px;height: 816px;border: 0px solid;margin-top: 20px;" >

				</div>
			</c:if>
				<%--助理全科证书--%>
			<c:if test="${completeNo eq 'AssiGeneral'}">
				<div id="downDiv" align="right" style="width: 588px;">
					<input  type="button" id="submitBtn" class="btn_green" onclick="down('${sysUser.userName}${resDoctor.completeNo}');" value="下&#12288;载"/>&nbsp;
				</div>
				<div id="testDiv" style="width: 1008px;height: 721px;border: 0px solid;margin-top: 20px; display: none" >

				</div>
				<div id="testDivShow" style="width: 1008px;height: 721px;border: 0px solid;margin-top: 20px;" >

				</div>
			</c:if>
				<%--非全科证书--%>
			<c:if test="${completeNo eq 'provinceNoAll'}">
				<div id="downDiv" align="right" style="width: 588px;">
					<input  type="button" id="submitBtn" class="btn_green" onclick="down('${sysUser.userName}${resDoctor.completeNo}');" value="下&#12288;载"/>&nbsp;
				</div>
				<div id="testDiv" style="width: 1031px;height: 744px;border: 0px solid;margin-top: 20px;display: none" >

				</div>
				<div id="testDivShow" style="width: 830px;height: 596px;border: 0px solid;margin-top: 20px;" >

				</div>
			</c:if>
				<%--全科证书--%>
			<c:if test="${completeNo eq 'provinceAll'}">
				<div id="downDiv" align="right" style="width: 588px;">
					<input  type="button" id="submitBtn" class="btn_green" onclick="down('${sysUser.userName}${resDoctor.completeNo}');" value="下&#12288;载"/>&nbsp;
				</div>
				<div id="testDiv" style="width: 1080px;height: 775px;border: 0px solid;margin-top: 20px;display: none">

				</div>
				<div id="testDivShow" style="width: 830px;height: 596px;border: 0px solid;margin-top: 20px;" >

				</div>
			</c:if>
		</div>
	</c:if>


