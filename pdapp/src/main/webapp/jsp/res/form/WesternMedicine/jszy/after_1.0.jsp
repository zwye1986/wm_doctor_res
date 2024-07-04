<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${!param.noHead}">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
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
</c:if>
<style type="text/css">
	.teacherItem {
		width: 100px;
		text-align: center;
	}
	.selfItem{
		width: 100px;
		text-align: center;
	}
	.center{
		width: 100px;
		text-align: center;
	}
</style>
<script type="text/javascript">
	
	function saveForm(){
		if($("#campaignForm").validationEngine("validate")){
			var title = "确认提交?";
			<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
				title = "确认填写出科小结？填写后科室审核通过即可出科!";
			</c:if>
			jboxConfirm(title,function(){
				jboxPost("<s:url value='/res/rec/saveRegistryFormNew'/>",$('#campaignForm').serialize(),function(resp){
					if(resp="${GlobalConstant.SAVE_SUCCESSED}"){
						parentRefresh();
						jboxClose();
					}
				},null,true);
			},null);
		}
	}
	function sum(flag,evt){
		//处理谷歌火狐浏览器兼容问题
		evt = (evt) ? evt : ((window.event) ? window.event : "")
		var $item = $("."+flag+"Item");
		if(evt.keyCode==13){
			var currEle = evt.srcElement;
			var index = $item.index(currEle);
			$("."+flag+"Item:eq("+(index+1)+")").focus();
			return;
		}
		var zhonghe = 0;
		$item.each(function(){
			var value = this.value;
			if(value && !isNaN(value)){
				var numItem = parseInt(value);
				zhonghe+=numItem;
			}
		});
		$("#"+flag+"Total").val(zhonghe);
	}
	
	//$(function(){
		//<c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
// 			$("#campaignForm").find(":text,textarea").each(function(){
// 				$(this).replaceWith($('<label>'+$(this).val()+'</label>'));
// 			});
// 			$("#campaignForm select").each(function(){
// 				var text = $(this).find(":selected").text();
// 				$(this).replaceWith($('<label>'+text+'</label>'));
// 			});
// 			$("#campaignForm").find(":checkbox,:radio").attr("disabled",true);
// 			$(":file").remove();
	//	</c:if>
	//});
	$(function(){
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && (!empty rec.auditStatusId || !empty rec.headAuditStatusId)}">
			$(":input").attr("readonly",true);
			$("select,:radio,:checkbox").attr("disabled",true);
			$(".BC").hide();
			hideInput();
		</c:if>
		<c:if test="${(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !(!empty rec.auditStatusId || !empty rec.headAuditStatusId))}">
			$(".teacherItem").attr("readonly",true);
			$("[type='checkbox']").attr("disabled",true);
			$(".otherItem").hide();
// 			$(".teacherItem").removeAttr("onblur");
			hideInput();
		</c:if>
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && !(!empty rec.auditStatusId)}">
			$(".selfItem").attr("readonly",true);
// 			$(".selfItem").removeAttr("onblur");
			hideInput();
		</c:if>
		<c:if test="${(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && (!empty rec.auditStatusId))}">
			$(":input").attr("readonly",true);
			$("select,:radio,:checkbox").attr("disabled",true);
			$(".BC").hide();
			hideInput();
		</c:if>
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
			$(":input").attr("readonly",true);
//			$("select,:radio,:checkbox").attr("disabled",true);
			$(".BC").hide();
			<c:if test="${empty formDataMap['isAgree']}">
				$("[name='isAgree']").attr("disabled",false);
			</c:if>
			<c:if test="${not empty rec.auditStatusId}">
				hideInput4Head();
			</c:if>
			<%--<c:if test="${!(empty rec.headAuditStatusId && (not empty rec.auditStatusId))}">--%>
				<%--hideInput();--%>
			<%--</c:if>--%>
		</c:if>
	});
	  function single(box){
	    var curr=box.checked;
		   if(curr){
			var name=box.name;
			$(":checkbox[name='"+name+"']").attr("checked",false);
		    }
		    box.checked = curr;
	  }

	
	function recSubmit(rec){
		jboxConfirm("确认提交?",function(){
			jboxPost("<s:url value='/res/rec/opreResRec'/>",rec,function(resp){
				if(resp=="${GlobalConstant.DELETE_SUCCESSED}" || resp=="${GlobalConstant.OPRE_SUCCESSED}"){
					parentRefresh();
					jboxClose();
				}
			},null,true);
		},null);
	}
	
	function hideInput(){
		$(":text[readonly='readonly']:not(#teacherTotal,#selfTotal)").each(function(){
			var val = this.value;
			$(this).after('<label>'+val+'<input type="hidden" name="'+this.name+'" value="'+val+'"/></label>').remove();
		});
		$(":disabled").each(function(){
			var val = this.value;
			var $parent = $(this).closest("label");
			if(this.checked){
				$parent.after('<label>'+val+'<input type="hidden" name="'+this.name+'" value="'+val+'"/></label>');
			}
			$parent.remove();
		});
		$("[disabled='disabled']:selected").each(function(){
			var val = this.value;
			var $parent = $(this).closest("select");
			$parent.after('<label>'+val+'<input type="hidden" name="'+$parent.attr("name")+'" value="'+val+'"/></label>').remove();
		});
	}

	function hideInput4Head(){
		$(":text[readonly='readonly']:not(#teacherTotal,#selfTotal,.teacherItem)").each(function(){
			var val = this.value;
			$(this).after('<label>'+val+'<input type="hidden" name="'+this.name+'" value="'+val+'"/></label>').remove();
		});
		$(":disabled").each(function(){
			var val = this.value;
			var $parent = $(this).closest("label");
			if(this.checked){
				$parent.after('<label>'+val+'<input type="hidden" name="'+this.name+'" value="'+val+'"/></label>');
			}
			$parent.remove();
		});
		$("[disabled='disabled']:selected").each(function(){
			var val = this.value;
			var $parent = $(this).closest("select");
			$parent.after('<label>'+val+'<input type="hidden" name="'+$parent.attr("name")+'" value="'+val+'"/></label>').remove();
		});
		$(".teacherItem").attr("readonly",false);
	}
	
	function parentRefresh(){
		//window.parent.frames['mainIframe'].window.$(".recTypeTag.active").click();
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
			window.parent.frames['mainIframe'].$(".box.selected").click();
		</c:if>
		<c:if test="${!(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}">
			top.document.mainIframe.location.reload();
		</c:if>
	}
	function jboxPrint(id) {
		jboxTip("正在准备打印…")
		setTimeout(function(){
			$("#title").show();
			var newstr = $("#"+id).html();
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = newstr;
			window.print();
			document.body.innerHTML = oldstr;
			$("#title").hide();
			jboxEndLoading();
			return false;
		},2000);
	}
</script>
</head>
<body>
	<div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
		<form id="campaignForm">
		<input type="hidden" name="formFileName" value="${formFileName}"/>
		<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
		<input type="hidden" name="processFlow" value="${param.processFlow}"/>
		<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
		<input type="hidden" name="operUserFlow" value="${empty rec.operUserFlow?param.userFlow:rec.operUserFlow}"/>
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
			<input type="hidden" name="auditStatusId" value="${recStatusEnumTeacherAuditY.id}"/>
		</c:if>
		<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
			<input type="hidden" name="headAuditStatusId" value="${recStatusEnumHeadAuditY.id}"/>
		</c:if>
			<div id="printDiv">
				<div id="title" style="width:100%;text-align: center;font-size: 12px;display: none;">
					出科小结
				</div>
   		<table class="basic" width="100%">
   			<tr>
                <th colspan="6" style="text-align: left;">&#12288;思想政治和工作态度</th>
            </tr>
      		<tr>
             	<td style="text-align: left;width: 70%;">项目及标准分</td>
                <td style="text-align: center;width: 15%;">考核分</td>
                <td style="text-align: center;width: 15%;">自评分</td>
             </tr>
             	
             <tr>
                <td>坚持四项基本原则，遵纪守法(20分)</td>
                <td style="text-align: center;">
              		 <input  name="basicCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[20]]]"  value="${formDataMap['basicCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="basicSelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[20]]]" type="text"  value="${formDataMap['basicSelfratingBranch']}" />
				</td>
            </tr>
             <tr>
                <td>组织纪律性(10分)</td>
                <td style="text-align: center;">
              		 <input  name="disciplineCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[10]]]" value="${formDataMap['disciplineCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="disciplineSelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[10]]]" type="text" id="he" value="${formDataMap['disciplineSelfratingBranch']}" />
				</td>
            </tr>
             <tr>
                <td>团结协作(7分)</td>
                <td style="text-align: center;">
              		 <input  name="uniteCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[7]]]"  value="${formDataMap['uniteCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="uniteSelfratingBranch" type="text" class="selfItem validate[required,custom[integer,min[0],max[7]]]" onkeyup="sum('self');" value="${formDataMap['uniteSelfratingBranch']}" />
				</td>
            </tr>
             <tr>
                <td>政治活动和社会活动(8分)</td>
                <td style="text-align: center;">
              		 <input  name="activityCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[8]]]" value="${formDataMap['activityCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="activitySelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[8]]]" type="text" value="${formDataMap['activitySelfratingBranch']}" />
				</td>
            </tr>
            <tr>
                <td>履行本岗位职责,出勤率(20分)</td>
                <td style="text-align: center;">
              		 <input  name="attendanceCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[20]]]" value="${formDataMap['attendanceCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="attendanceSelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[20]]]" type="text" value="${formDataMap['attendanceSelfratingBranch']}" />
				</td>
            </tr>
            <tr>
                <td>职业道德和服务态度(20分)</td>
                <td style="text-align: center;">
              		 <input  name="attitudeCheckBranch" type="text" onkeyup="sum('teacher');"  class="teacherItem validate[required,custom[integer,min[0],max[20]]]" value="${formDataMap['attitudeCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="attitudeSelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[20]]]" type="text" value="${formDataMap['attitudeSelfratingBranch']}" />
				</td>
            </tr>
            <tr>
                <td>技术操作规程(10分)</td>
                <td style="text-align: center;">
              		 <input  name="technologyCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[10]]]" value="${formDataMap['technologyCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="technologySelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[10]]]" type="text" value="${formDataMap['technologySelfratingBranch']}" />
				</td>
            </tr>
            <tr>
                <td>业务活动和学习(5分)</td>
                <td style="text-align: center;">
              		 <input  name="studyCheckBranch" type="text" onkeyup="sum('teacher');" class="teacherItem validate[required,custom[integer,min[0],max[5]]]" value="${formDataMap['studyCheckBranch']}" />
				</td>
				<td style="text-align: center;">
              		 <input  name="studySelfratingBranch" onkeyup="sum('self');" class="selfItem validate[required,custom[integer,min[0],max[5]]]" type="text" value="${formDataMap['studySelfratingBranch']}" />
				</td>
            </tr>
            <tr>
                <td>总计(100分)</td>
                <td style="text-align: center;">
              		 <input style="border: none;" name="checkBranchThetotalscore"  class="center" id="teacherTotal" type="text" value="${formDataMap['checkBranchThetotalscore']}" readonly="readonly" />
				</td>
				<td style="text-align: center;">
              		 <input style="border: none;" name="selfratingBranchThetotalscore" class="center" id="selfTotal"  type="text" value="${formDataMap['selfratingBranchThetotalscore']}" readonly="readonly"/>
				</td>
            </tr>
           
            <tr class="otherItem">
                <th colspan="3" style="text-align: left;">&#12288;其他</th>
            </tr>
            <tr class="otherItem">
            
               	<td colspan="3" style="text-align: left;" >
               		医疗事故：
               		<label>
               			<input type="checkbox"class="validate[required]" onchange="single(this)" name="accident" value="有" <c:if test="${formDataMap['accident']=='有'}">checked</c:if>/>有
               		</label>
               		<label>
	               		<input type="checkbox"class="validate[required]" onchange="single(this)" name="accident" value="无" <c:if test="${formDataMap['accident']=='无'}">checked</c:if>/>无
               		</label>
               		&#12288;
               		&#12288;
               		医疗差错：
               		<label>
						<input type="checkbox"class="validate[required]" onchange="single(this)" name="error" value="有" <c:if test="${formDataMap['error']=='有'}">checked</c:if>/>有
               		</label>
               		<label>
	               		 <input type="checkbox"class="validate[required]" onchange="single(this)" name="error" value="无" <c:if test="${formDataMap['error']=='无'}">checked</c:if>/>无
               		</label>
               		&#12288;
               		&#12288;
               		工作错误：
               		<label>
             	 		<input type="checkbox"class="validate[required]" onchange="single(this)" name="wrong" value="有" <c:if test="${formDataMap['wrong']=='有'}">checked</c:if>/>有
               		</label>
               		<label>
	               		<input type="checkbox"class="validate[required]" onchange="single(this)" name="wrong" value="无" <c:if test="${formDataMap['wrong']=='无'}">checked</c:if>/>无
               		</label>
               	</td>
            
               	
	              	 <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
		            	<input type="hidden" name="accident" value="${formDataMap['accident']}"/>
		            	<input type="hidden" name="error" value="${formDataMap['error']}"/>
		            	<input type="hidden" name="wrong" value="${formDataMap['wrong']}"/>
	             	</c:if>
              	
            </tr>
            <c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
	            <tr>
	            	<td colspan="3">
	            		<label>
	            			<c:if test="${(empty rec.headAuditStatusId) and (empty formDataMap['isAgree'])}">
		            			<input type="checkbox" name="isAgree" value="${GlobalConstant.FLAG_Y}" checked="checked"/>
	            			</c:if>
							<c:if test="${not empty rec.headAuditStatusId}">
							<input type="checkbox" name="isAgree" value="${GlobalConstant.FLAG_Y}"
								<c:if test="${not empty formDataMap['isAgree']}">	checked="checked"</c:if>/>
							</c:if>
		            		同意出科
	            		</label>
	            	</td>
	            </tr>
            </c:if>
            
              </table>

			</div>
			<p align="center">
				<c:if test="${empty rec.auditStatusId && param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !param.isRead}">
					<input class="search BC" type="button" value="保&#12288;存"  onclick="saveForm();"/>
				</c:if>
				<c:if test="${empty rec.headAuditStatusId && param.roleFlag==GlobalConstant.RES_ROLE_SCOPE_HEAD && empty rec.auditStatusId}">
					[<font color="red">带教老师还未审核，请等待！</font>]
				</c:if>
				<c:if test="${(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && (not empty rec.auditStatusId)) || (param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER && empty rec.auditStatusId)}">
					<input class="search" type="button" value="提&#12288;交"  onclick="saveForm();"/>
				</c:if>
				<c:if test="${GlobalConstant.RES_ROLE_SCOPE_DOCTOR eq param.roleFlag && not empty rec.auditStatusId}">
					<input class="search" type="button" value="打&#12288;印"  onclick="jboxPrint('printDiv');"/>
				</c:if>
<%-- 				<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD && empty formDataMap['isAgree']}"> --%>
<!-- 					<input class="search" type="button" value="确&#12288;认"  onclick="saveForm();"/> -->
<%-- 				</c:if> --%>
				<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
			</p>
		</form>
         </div>
        
     </div> 	
   </div>	
</body>
</html>