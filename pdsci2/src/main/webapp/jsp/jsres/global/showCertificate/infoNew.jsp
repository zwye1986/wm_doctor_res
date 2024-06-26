<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
<script type="text/javascript" src="<s:url value='/js/jsres/jquery-1.11.3.min.js'/>"></script>
<script src="<s:url value='/js/jsres/screenshot/html2canvas.min.js'/>"></script>
<script src="<s:url value='/js/jsres/screenshot/canvas2image.js'/>"></script>
<script src="<s:url value='/js/jsres/jszyCanvasPutImg.js'/>"></script>
<script type="text/javascript">
    <c:set var="thisTime" value="${sysCfgMap['res_certificateDate']}"></c:set>
    <c:if test="${empty thisTime }">
    	<c:set var="thisTime" value="${recruit.certificateDate}"></c:set>
    </c:if>
	var time = '${thisTime}'.split("-");
	var certificateDate = time[0]+"年 "+time[1]+"月 "+time[2]+"日";
	var map={
		//国家证书样式
		"country":{
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jsres/daochu/certificateNew.jpg'/>",
                    args: [0, 0, 1240, 1754, 0, 0, 1240, 1754]
                },
                {
                    //人物照片
                    url:
						<c:if test="${empty  sysUser.userHeadImg }">
                        	"<s:url value='/css/skin/up-pic.jpg'/>",
						</c:if>
						<c:if test="${not empty sysUser.userHeadImg }">
							"${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
						</c:if>
                    args: [522, 472, 209, 314]
                },
                {
                    //培训基地院长签字
                    <%--url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${recruit.orgFlow}' + ".png",--%>
                    url: "${sysCfgMap['upload_base_url']}/"+'${signUrl}',
                    args: [200, 1400, 300, 180]
                }
                ,
                {
					//红章
					url: "${sysCfgMap['upload_base_url']}/"+'${filePath}',
					args: [760, 1295, 248, 248]
                }
            ],
            texts2:{
                startX: 190,//每一行文字开始的X
                endX: 1060,//每一行文字结束的X
                x: 280,
                y: 940,
                width: 870,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '${sysUser.userName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '于',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //开始培训时间
                        text: '${completeStartDate}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '至',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //结束培训时间
                        text: '${completeEndDate}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '期间 ，在',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //基地名称
                        text: '${recruit.orgName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '培训基地参加 ',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //培训专业
                        text: '${recruit.speName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                        style: '41px 微软雅黑',
                        width:41
                    }
                ]
            },
            texts: [
                {
                    //身份证号
                    text:'${sysUser.idNo}',
                    x:552,
                    y:750,
                    style: '15px 微软雅黑'
                },
                {
                    //证书编号
                    text: '${pdfn:completeNoSplit(recruit.graduationCertificateNo)}',
                    x: 552,
                    y: 850,
                    style: '26px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    text: certificateDate,
                    x: 760,
                    y: 1515,
                    height: 28,
                    style: '28px 楷体',
                    rowSize: 13
                },
                {
                    //流水号
                    text: '${recruit.certificateFlow}',
                    x: 290,
                    y: 1680,
                    style: '20px 华文中宋'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1240,
                height: 1754
            }
        },
        //省級证书样式
        "province":{
            imgs: [
                {
                    //结业证书
                    url: "<s:url value='/jsp/jsres/daochu/certificateNew.jpg'/>",
                    args: [0, 0, 1240, 1754, 0, 0, 1240, 1754]
                },
                {
                    //人物照片
                    url:
                        <c:if test="${empty  sysUser.userHeadImg }">
                        "<s:url value='/css/skin/up-pic.jpg'/>",
                    </c:if>
                    <c:if test="${not empty sysUser.userHeadImg }">
                    "${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}",
                    </c:if>
                    args: [522, 472, 209, 314]
                },
                {
                    //培训基地院长签字
                    <%--url: "<s:url value='/jsp/jszy/graduationManager/images/'/>" + '${recruit.orgFlow}' + ".png",--%>
                    url: "${sysCfgMap['upload_base_url']}/"+'${signUrl}',
                    args: [200, 1400, 300, 180]
                }
                ,
                {
                    //红章
                    url: "${sysCfgMap['upload_base_url']}/"+'${filePath}',
                    args: [760, 1295, 248, 248]
                }
            ],
            texts2:{
                startX: 190,//每一行文字开始的X
                endX: 1060,//每一行文字结束的X
                x: 280,
                y: 940,
                width: 870,
                height: 55,
                hj: 10,
                items:[
                    {
                        //姓名
                        text: '${sysUser.userName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '于',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //开始培训时间
                        text: '${completeStartDate}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '至',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //结束培训时间
                        text: '${completeEndDate}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '期间 ，在',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //基地名称
                        text: '${recruit.orgName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: '培训基地参加 ',
                        style: '41px 微软雅黑',
                        width:41
                    },
                    {
                        //培训专业
                        text: '${recruit.speName}',
                        style: '39px 楷体',
                        width:39
                    },
                    {
                        text: ' 专业住院医师规范化培训，经考核合格，特发此证。',
                        style: '41px 微软雅黑',
                        width:41
                    }
                ]
            },
            texts: [
                {
                    //身份证号
                    text:'${sysUser.idNo}',
                    x:552,
                    y:750,
                    style: '15px 微软雅黑'
                },
                {
                    //证书编号
                    text: '${pdfn:completeNoSplit(recruit.graduationCertificateNo)}',
                    x: 552,
                    y: 850,
                    style: '26px 微软雅黑',
                    height: 30,
                    rowSize: 20
                },
                {
                    //日期
                    text: certificateDate,
                    x: 760,
                    y: 1515,
                    height: 28,
                    style: '28px 楷体',
                    rowSize: 13
                },
                {
                    //流水号
                    text: '${recruit.certificateFlow}',
                    x: 290,
                    y: 1680,
                    style: '20px 华文中宋'
                }
            ],
            canattr: {
                id: 'downId',
                width: 1240,
                height: 1754
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
    name = name + ".png";
    var url="<s:url value='/jsres/certificateManage/downCertificate' />?recruitFlow=${recruit.recruitFlow}";
    $('#testDiv canvas').downCanvasImg(name,url);
}
</script>
<div id="all" style="width: auto;text-align: center" onResize="Resize()" onLoad="Resize()">
	<c:if test="${notHave eq 'Y'}">
		<div id="testDiv" style="width: 588px;height: 816px;border: 0px solid;" onResize="Resize()" onLoad="Resize()">

		</div>
	</c:if>
	<c:if test="${notHave ne 'Y'}">
		<%--国家证书--%>
		<%--<div id="testDiv" style="width: 588px;height: 816px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">--%>
		<div id="testDiv" style="height: 816px;border: 0px solid;padding-top: 30px;" onResize="Resize()" onLoad="Resize()">
			<div align="right">
				<c:if test="${empty param.noDown}">
					<input type="button" id="submitBtn" class="btn_green" onclick="down('${sysUser.userName}${recruit.graduationCertificateNo}');" value="下&#12288;载"/>&nbsp;
				</c:if>
			</div>
		</div>
	</c:if>
</div>

