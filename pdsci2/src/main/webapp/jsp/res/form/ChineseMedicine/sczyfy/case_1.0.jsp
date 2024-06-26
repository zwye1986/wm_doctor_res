<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${!param.noHead}">
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="ueditor" value="true"/>
	</jsp:include>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	.itemDiv {background: white;}
	.itemDiv:HOVER{background: #ccc;}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
function save(){
	if($("#dblForm").validationEngine("validate")){
			var d=$(".c").length;
			if(d>=1){
				jboxConfirm("确认保存？",function(){
					autoValue($("#dblForm"),"autoValue");
				jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$("#dblForm").serialize(),function(resp){
					if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
						window.parent.frames['mainIframe'].window.$(".box.selected").click();
					   jboxClose();
					}				
				},null,true);
				});
			}else{
				jboxTip("请上传附件！");
			}
	}
}

function upload(obj){
    obj=this;
    var id = obj.id;
    $.ajaxFileUpload({
        url:"<s:url value='/res/rec/uploads'/>?date="+new Date(),
        secureuri:false,
        fileElementId:id,
        dataType: 'json',
        success: function (data){
            data=eval("("+data+")");
            if(data){
                var fileName=data.fileName;
                var fileUrl=data.fileUrl;
                var div=$("<div></div>");
                var check=$("<input />");
                var a = $('<a/>');
                div.addClass('c').css({
                    "marginLeft":"10px",
                });
                check.attr({
                    type:"checkbox",
                }).addClass('fileCheck');
                div.append(check);
                a.attr({
                    href:"${sysCfgMap['upload_base_url']}"+fileUrl,
                    target:"_blank",
                }).text("["+fileName+"]").css({
                    "color":"blue"
                });
                div.append(a);

                var url_name=$("<input />");
                url_name.attr({
                    type:"hidden",
                    name:"fileUrl_name"
                }).val(fileName);

                var url=$("<input/>");
                url.attr({
                    type:"hidden",
                    name:"fileUrl"
                }).val(fileUrl);
                div.append(url_name);
                div.append(url);
                $("#url").append(div);
                $("#file").change(upload);
            }else{
                jboxTip(data.error);
            }
        },
        error: function (data, status, e){
            jboxTip('${GlobalConstant.UPLOAD_FAIL}');
        },
        complete:function(){
        }
    });
}
//
function upload(obj){
	obj=this;
	var id = obj.id;
	$.ajaxFileUpload({
		url:"<s:url value='/res/rec/uploads'/>?date="+new Date(),
		secureuri:false,
		fileElementId:id,
		dataType: 'json',
		success: function (data){
			data=eval("("+data+")");
			if(data){
				var fileName=data.fileName;
				var fileUrl=data.fileUrl;
				var div=$("<div></div>");
				var check=$("<input />");
				var a = $('<a/>');
				div.attr({
					class:"c"
				}).css({
					"margin-left":"10px",
				});
				check.attr({
					type:"checkbox",
				});
				div.append(check);
				a.attr({
					href:"${sysCfgMap['upload_base_url']}"+fileUrl,
					target:"_blank",
				}).text("["+fileName+"]").css({
					"color":"blue",
				});
				div.append(a);

				var url_name=$("<input />");
				url_name.attr({
					type:"hidden",
					name:"fileUrl_name"
				}).val(fileName);

				var url=$("<input/>");
				url.attr({
					type:"hidden",
					name:"fileUrl"
				}).val(fileUrl);
				div.append(url_name);
				div.append(url);
				$("#url").append(div);
				$("#file").change(upload);
			}else{
				jboxTip(data.error);
			}
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
		}
	});
}




$(function(){
	$("#file").change(upload);
	var url="${formDataMap['fileUrl']}";
	var urlId="${formDataMap['fileUrl_id']}";
	var urlList=url.split(",");
	var urlIdList=urlId.split(",");
	if(url){
		for(var i in urlList){
			var url = urlIdList[i];
			var name = urlList[i];
			var div=$("<div></div>");
			var a = $('<a/>');
			div.attr({
				class:"c"
			}).css({
				"margin-left":"10px",
			});
			if("${GlobalConstant.RES_ROLE_SCOPE_DOCTOR==param.roleFlag}"=="true" && "${empty rec.auditStatusId}"=="true"){
				var check=$("<input />");
				check.attr({
					type:"checkbox",
				});
				div.append(check);
			}
			a.attr({
				href:"${sysCfgMap['upload_base_url']}"+url,
				target:"_blank",
			}).text("["+name+"]").css({
				"color":"blue",
			});
			div.append(a);
			
			var url_name=$("<input />");
			url_name.attr({
				type:"hidden",
				name:"fileUrl_name"
			}).val(name);
			
			var url_id=$("<input />");
			url_id.attr({
				type:"hidden",
				name:"fileUrl"
			}).val(url);
			div.append(url_name);
			div.append(url_id);
			$("#url").append(div);
		}
	}
});
function shanChu(){
 	var mIds = $('[type="checkbox"]:checked');
	if(mIds.length == 0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除？", function() {
		mIds.each(function(){
			$(this).parent().remove();
		});
	}); 
}
</script> 
</head>
<body>
<c:set var="doctor" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && empty rec.auditStatusId}"/>
<c:set var="teacher" value="false"/>
<c:set var="head" value="false"/>
<c:set var="manager" value="false"/>
<c:set var="global" value="false"/>
<div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
        <form id="dblForm">
        		<input type="hidden" name="formFileName" value="${formFileName}"/>
				<input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
				<input type="hidden" name="roleFlag" value="${roleFlag}"/>
				<input type="hidden" name="operUserFlow" value="${param.operUserFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
				<input type="hidden" name="recFlow" value="${rec.recFlow}"/>
				<input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
        	<table class="basic" width="100%" style="margin-top: 10px;">
        		<tr>
        			<td style="width: 20%;"><span style="color: red">*</span>&nbsp;病人姓名：</td>
        			<td style="width: 30%;">
        			<c:if test="${doctor}">
        			<input type="text" style="width:140px;" class="validate[required]" name="mr_pName" value="${formDataMap['mr_pName']}"/>
        			</c:if>
        			<c:if test="${!doctor}">
        				<label>${formDataMap['mr_pName']}</label>
        			</c:if>
        			</td>
        			<td style="width: 20%;"><span style="color: red">*</span>&nbsp;病历号：</td>
        			<td style="width: 30%;">
        			<c:if test="${doctor}">
        				<input type="text" style="width: 140px;" class="validate[required]" name="mr_no" value="${formDataMap['mr_no']}"/>
        			</c:if>
        			<c:if test="${!doctor}">
        				<label>${formDataMap['mr_no']}</label>
        			</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td><span style="color: red">*</span>&nbsp;疾病名称：</td>
        			<td>
        				<c:if test="${doctor}">
        					<input type="text" style="width: 140px;" class="validate[required]" name="disease_pName" value="${formDataMap['disease_pName']}"/>
        				</c:if>
        				<c:if test="${!doctor}">
        					<label>${formDataMap['disease_pName']}</label>
        				</c:if>
        				</td>
        			<td><span style="color: red">*</span>&nbsp;诊断类型：</td>
        			<td>
        				<c:if test="${doctor}">
       					<select name="mr_diagType" style="width: 144px;" class="autoValue validate[required]">
        					<option></option>
        					<option value="1" <c:if test="${'1' eq formDataMap['mr_diagType_id']}">selected</c:if>>主要诊断</option>
        					<option value="2" <c:if test="${'2' eq formDataMap['mr_diagType_id']}">selected</c:if>>次要诊断</option>
        					<option value="3" <c:if test="${'3' eq formDataMap['mr_diagType_id']}">selected</c:if>>并行诊断</option>
        				</select>
        				</c:if>
        				<c:if test="${!doctor}">
        					${formDataMap['mr_diagType']}
        					<input type="hidden" name="mr_diagType" value="${formDataMap['mr_diagType']}"/>
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td>床号：</td>
        				<td style="width:">
	        				<c:if test="${doctor}">
	        					<input type="text" style="width: 140px; " name="bedNo" value="${formDataMap['bedNo']}"/>
	        				</c:if>
	        				<c:if test="${!doctor}">
	        					<label>${formDataMap['bedNo']}</label>
	        				</c:if>
        				</td>
        			<td>备注：</td>
        				<td>
	        				<c:if test="${doctor}">
	        					<input type="text" style="width: 140px;" name="remarks" value="${formDataMap['remarks']}"/>
	        				</c:if>
	        				<c:if test="${!doctor}">
	        					<label>${formDataMap['remarks']}</label>
	        				</c:if>
        				</td>
        		</tr>
        		
        		<tr>
                    <td><span style="color: red">*</span>&nbsp;附件：</td>
        			<td colspan="3">
	        			<c:if test="${doctor}">
	        					<div id="url" style="margin: 0px;padding: 0px;"></div>
	        					<div style="margin-left: 10px;padding: 0px;float: left;">
	        					[<a href="javascript:void(0);" id="upload"  onclick="$('#file').click();" style="color: blue;">上传</a>]
	        					&nbsp;&nbsp;[<a href="javascript:void(0);" id="close"  onclick="shanChu();" style="color: blue;">删除</a>] 
	        					&nbsp;&nbsp;<span style="color: red;" id="shuo">（手写大病历图片,自2017届开始要求上传）</span></div>
	        			</c:if>
	        			<c:if test="${!doctor}">
        					<div id="url" style="margin: 0px;padding: 0px;"></div>
        				</c:if>
        			</td>
        		</tr>
        	</table>
        	<p align="center" style="margin-top: 10px;">
        		<c:if test="${doctor}">
					<input class="search" type="button" value="保&#12288;存"  onclick="save();"/>
				</c:if>
					<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
				</p>
        </form>
        <input id="file" style="display: none;" type="file" name="checkFile" />
     </div> 	
     </div>
   </div>
</body>
</html>