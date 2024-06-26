<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
	.outerDiv{
		width: 19%;
		height: 170px;
		float: left;
		margin-left: 50px;
		margin-bottom: 60px;
		border-radius: 3%;
		background: white;
		border: 2px solid;
		border-color: #69bbe8;
	}
	.innerTopDiv{
		width: 100%;
		height: 50px;
		background: #69bbe8;
		border-radius: 3%;
		text-align: center;
		line-height:50px;
	}
	.innerBottomDiv{
		text-align: center;
		width: 100%;
		height: 120px;
		background: white;
		line-height:100px;
	}
	.examDiv{
		width: 73px;
		height: 64px;
		border-radius: 50%;
		margin-top: -32px;
		margin-left: 35%;
	}
	.canExamDiv{
		background:url(<s:url value='/jsp/res/images/exam2.png'/>);
	}
	.cannotExamDiv{
		background:url(<s:url value='/jsp/res/images/exam-no.png'/>);
	}
	.hovExamDiv{
		background:url(<s:url value='/jsp/res/images/exam-hover.png'/>);
	}
</style>
<script type="text/javascript">

	function toTest(arrangeFlow){
		var url = '<s:url value="/res/test/toYearTest"/>?arrangeFlow='+arrangeFlow;
		window.open(url);
	}
	$(document).ready(function(){
		$("div[name='examBtn']").each(function(index, domEle){
			var canExam = $(domEle).attr("canExam");
			if(canExam == 'Y'){
				$(domEle).addClass("canExamDiv");
				$(domEle).bind({
					click:function(){
						toTest($(this).attr("arrangeFlow"));
					},
					mouseover:function(){changeExamClass(this);},
					mouseout:function(){changeExamClass(this);}
				});
			}else {
				$(domEle).addClass("cannotExamDiv");
			}
		});
	})
	function changeExamClass(resp){
		if($(resp).hasClass("canExamDiv")){
			$(resp).removeClass("canExamDiv").addClass("hovExamDiv");
		}else {
			$(resp).removeClass("hovExamDiv").addClass("canExamDiv");
		}
	}
	function downLoadPaper(recordFlow){
		var url = "<s:url value='/res/examCfg/downloadExamPaper?recordFlow='/>" + recordFlow;
		jboxGet(url,null,function(resp){
			var data=eval("("+resp+")");
			if(data.result=="1")
			{
//				window.open(data.url);
				$("#url").val(data.url);
				jboxExp($("#exportFrom"),"<s:url value='/res/examCfg/downloadFile'/>");
			}else{
				jboxTip(data.msg);
			}
		},null,false);
	}
</script>


<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
</form>
<div class="main_hd" style="margin-top: 50px;">
	<c:if test="${empty examArrangements}">
		<span style="font-size: x-large; font-weight: 500;margin-left: 20px;">暂无年度考核！</span>
	</c:if>
	<c:forEach items="${examArrangements}" var="examArrangement" >
		<div class="outerDiv">
			<div class="innerTopDiv">
				<span style="color:white;font-size: large;">
					${examArrangement.assessmentYear}年度
				</span>
			</div>
			<div class="innerBottomDiv">
				<span style="font-size: xx-large; font-weight: 500;">
					<c:if test="${examArrangement.isOpen eq 'Y'}">
						<c:if test="${empty examLogMaps[examArrangement.arrangeFlow]['maxScore']}">
							一 一
						</c:if>
						<c:if test="${not empty examLogMaps[examArrangement.arrangeFlow]['maxScore']}">
							<c:set var="key" value="${examArrangement.arrangeFlow}"></c:set>
							<span style="cursor: pointer;" onclick="downLoadPaper('${daMap[key].recordFlow}');">${daMap[key].examScore}分</span>
						</c:if>
					</c:if>
					<c:if test="${examArrangement.isOpen ne 'Y'}">
						一 一
					</c:if>
				</span>
			</div>
			<div name="examBtn" class="examDiv" arrangeFlow="${examArrangement.arrangeFlow}" canExam="${examLogMaps[examArrangement.arrangeFlow]['canExam']}" >

			</div>
		</div>
	</c:forEach>
</div>
</html>