<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 参数说明 -->
<!-- doctorFlow 当前学员的Flow text 必传参数 -->
<!-- resultFlow 轮转计划的Flow text -->
<!-- loadCss 是否加载css true/false -->
<!-- loadScript 是否加载js true/false -->
<!-- loadContent 是否加载控件本体 true/false -->
<!-- topDivClass 最外层标签的class text -->
<!-- formId 承载数据的form的id text -->

<c:set var="topDivClass" value="${empty param.topDivClass?'createResultByStandardDept_topDiv':param.topDivClass}" />

<c:if test="${param.loadCss}">
	<style>
		.${topDivClass}.customTable {cursor: auto;}
		.${topDivClass} .createResultByStandardDept_form_div{display: none;width: 100%;}
		.${topDivClass} .createResultByStandardDept_contentHome{max-height: 350px;overflow: auto;padding: 10px;}
		.${topDivClass} .createResultByStandardDept_cancleBtn{text-align: center;margin-top: 10px;}
		.${topDivClass} .createResultByStandardDept_operContent{}
		.${topDivClass} .createResultByStandardDept_btn{width: 50px;}
		.${topDivClass} .createResultByStandardDept_tipText{padding-left: 10px;color: black;}
		.${topDivClass} a{cursor: pointer;color: blue;}
	</style>
</c:if>
<c:if test="${param.loadScript}">
	<script>
		//展示或隐藏表单
		function slideTheFormToEdit(){
			var operContent = $('.createResultByStandardDept_btn','.${topDivClass}');
			var operIsHide = operContent.is(':hidden');
			
			var fromContent = $('.createResultByStandardDept_tipText,.createResultByStandardDept_form_div','.${topDivClass}');
			
			var toToggle = function(){
				fromContent.toggle(500,function(){
					if(operIsHide){
						operContent.fadeIn();
					}
				});
			};
			
			var form = $('.createResultByStandardDept_form_content','.${topDivClass}');
			
			if(!operIsHide){
				getFormPage('${param.doctorFlow}','${param.resultFlow}',function(resp){
					form.html(resp);
					operContent.fadeOut();
					toToggle();
				});
			}else{
				toToggle();
				form.empty();
			}
		}
		
		//获取表单
		function getFormPage(doctorFlow,resultFlow,callback){
			var url = '<s:url value="/res/doc/editResultByStandardDept"/>?topDivClass=${topDivClass}';
			jboxPost(url,{
				doctorFlow:doctorFlow,
				resultFlow:resultFlow
				},function(resp){
					callback(resp);
				},null,false);
		}
		
		$(function(){
			$('.createResultByStandardDept_toEdit,.createResultByStandardDept_toCancel','.${topDivClass}').on('click',slideTheFormToEdit);
		});
		function cancel(){
			_dialogClose('openDialog');
		}
	</script>
</c:if>
<c:if test="${param.loadContent}">
	<table class="${topDivClass} customTable">
		<tr>
			<td class="createResultByStandardDept_operContent">
				<div class="createResultByStandardDept_contentHome">
					<h3 class="createResultByStandardDept_tipText">
						点击<a class="createResultByStandardDept_toEdit">新增</a>添加轮转科室！
					</h3>
					<div class="createResultByStandardDept_form_div">
						<div class="createResultByStandardDept_form_content"></div>
						<div class="createResultByStandardDept_cancleBtn">
							<a class="createResultByStandardDept_toSave" onclick="saveTheForm(reloadCurrPage);">保存</a>
							&#12288;
							<a class="createResultByStandardDept_toCancel">取消</a>
						</div>
					</div>
				</div>
			</td>
			<td class="createResultByStandardDept_btn">
<!-- 				<a class="createResultByStandardDept_toEdit">新增</a> -->
			</td>
		</tr>
	</table>
</c:if>