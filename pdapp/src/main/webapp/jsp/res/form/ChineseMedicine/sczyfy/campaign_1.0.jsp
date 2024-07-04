<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<!-- 参加活动 -->
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
	<c:if test="${empty rec}">
		if(!$("#viewContainer").val()){
			return jboxTip("请选择活动形式！");
		}
	</c:if>
	if($("#resRecForm").validationEngine("validate")){
		<%--var d=$(".c").length;--%>
		<%--if(d>=1){--%>
			<%--jboxConfirm("确认保存？",function(){--%>
				<%--autoValue($("#resRecForm"),"autoValue");--%>
			<%--jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$("#resRecForm").serialize(),function(resp){--%>
				<%--if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){--%>
					<%--window.parent.frames['mainIframe'].window.$(".box.selected").click();--%>
				   <%--jboxClose();--%>
				<%--}				--%>
			<%--},null,true);--%>
			<%--});--%>
		<%--}else{--%>
			<%--jboxTip("请上传附件！");--%>
		<%--}--%>

        jboxConfirm("确认保存？",function(){
            autoValue($("#resRecForm"),"autoValue");
            jboxPost("<s:url value='/res/rec/saveRegistryForm'/>",$("#resRecForm").serialize(),function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    window.parent.frames['mainIframe'].window.$(".box.selected").click();
                    jboxClose();
                }
            },null,true);
        });
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

/*function upload(obj){
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
}*/


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
			div.addClass('c').css({
				"marginLeft":"10px",
			});
			if("${GlobalConstant.RES_ROLE_SCOPE_DOCTOR==param.roleFlag}"=="true" && "${empty rec.auditStatusId}"=="true"){
				var check=$("<input />");
				check.attr({
					type:"checkbox",
				}).addClass('fileCheck');
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
 	var mIds = $('.c:has(.fileCheck:checked)');
	if(!mIds.length){
		return jboxTip("请勾选要删除的文件！");
	}
	jboxConfirm("确认删除？", function() {
		mIds.remove();
	}); 
}
</script> 
</head>
<body>
<c:set var="doctor" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR&& empty rec.auditStatusId}"/>
<div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
        <form id="resRecForm" method="post">
          <input type="hidden" name="formFileName" value="${formFileName}"/>
		  <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
	      <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
	      <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
	      <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
   		<table class="basic" width="100%">
             <tr>
				<td style="width: 80px;"><span style="color: red">*</span>&nbsp;活动形式：</td>
				<td colspan="3">
						<script>
	                	$(function(){
							$("input[type='checkbox']").click(function(e){
								e.stopPropagation();
							});
	                		$("[onclick]").click(function(e){
	                			e.stopPropagation();
	                		});
	                		$(".itemDiv").on("mouseenter mouseleave",function(){
	                			$(this).toggleClass("on");
	                		});
	                		$(document).click(function(){
	                			$("#reqHome").toggle(!!$(".itemDiv.on").length);
	                		});
	                		<c:if test="${!empty rec}">
	                			$("[name='itemId']").change();
	                		</c:if>
	                	});
	                	
	                	function viewReqs(){
	                		$("#reqHome").toggle();
	                	}
	                	
	                	function selCheckboxByDiv(div){
	                		var box = $(":checkbox",div)[0];
	                		box.checked = !box.checked;
	                		$("#otherName").toggle($("[name='itemId'][value='${GlobalConstant.RES_REQ_OTHER_ITEM_ID}']")[0].checked);
	                	}
	                	
	                	function viewSelReqs(){
	                		var result = "";
	                		var hidden = "";
	                		$(".itemCheckbox:checked+font").each(function(){
	                			var currName = $(this).text();
	                			if(!result){
	                				result+=currName;
	                			}else{
	                				result+=(","+currName);
	                			}
	                			hidden+=('<input type="hidden" name="itemName" value="'+currName+'"/>');
	                		});
	                		$("#itemNameHome").html(hidden);
	                		$("#viewContainer").val(result);
	                	}
	                	
	                	function loadOther(box){
							$("#otherName").toggle($("[name='itemId'][value='${GlobalConstant.RES_REQ_OTHER_ITEM_ID}']")[0].checked);
							viewSelReqs();
							return false;
	                	}
	                	
	                	function itemNameVal(sel){
	                		$("#itemNameInput").val($(":selected",sel).text());
	                		$("#otherName").toggle(sel.value=="${GlobalConstant.RES_REQ_OTHER_ITEM_ID}");
	                	}
                	</script>
      	 			<input type="hidden" name="activity_way" value="${formDataMap['activity_way']}"/>
      	 			<input type="hidden" name="xmlItemName" value="activity_way"/>
      	 			<c:if test="${empty rec}">
	                	<div style="min-width: 160px;float: left;">
<!-- 	                		<div style="height: 0px;overflow: visible;position: relative;right: -150px;top: 6px;"> -->
<%-- 			      	 			<img src="<s:url value='/css/skin/${skinPath}/images/blackDown.png'/>" style="width: 8px;height: 8px;" onclick="viewReqs();"> --%>
<!-- 	                		</div> -->
		      	 			<input id="viewContainer" type="text" value="${rec.itemName}" style="width: 100%;" readonly="readonly" onclick="viewReqs();" placeholder="点击选择子项"/>
		      	 			<div id="reqHome" style="height: 0px;width: 100%;position: relative;display: none;">
		      	 				<c:forEach items="${deptReqList}" var="req">
		      	 					<div class="itemDiv" style="width: 100%;height: 30px;border-bottom: 1px #bbb solid;border-left: 1px #bbb solid;border-right: 1px #bbb solid;padding-left: 2px;"
		      	 						onclick="selCheckboxByDiv(this);viewSelReqs();">
	      	 							<input class="itemCheckbox" style="margin-left: 8px;" type="checkbox" name="itemId" value="${req.itemId}" onchange="loadOther(this);" <c:if test="${rec.itemId eq req.itemId}">checked</c:if>/>
		      	 						<font style="cursor: default;">${req.itemName}</font>
		      	 					</div>
		      	 				</c:forEach>
		      	 				<div id="itemNameHome"></div>
		      	 			</div>
	                	</div>
      	 			</c:if>
      	 			<c:if test="${!empty rec}">
	      	 			<c:if test="${doctor}">
	      	 				<div style="width: 160px;float: left;">
	      	 					<select class="validate[required]" name="itemId" onchange="itemNameVal(this);" style="width: 164px;">
	      	 						<c:forEach items="${deptReqList}" var="req">
	      	 							<option value="${req.itemId}" <c:if test="${rec.itemId eq req.itemId}">selected</c:if>>${req.itemName}</option>
	      	 						</c:forEach>
	      	 					</select>
		                	</div>
		                	<input type="hidden" id="itemNameInput" name="itemName" value="${rec.itemName}"/>
		                </c:if>
	      	 			<c:if test="${!doctor}">
	      	 			<c:if test="${rec.itemId != GlobalConstant.RES_REQ_OTHER_ITEM_ID}">
	      	 				<div style="width: 100px; float: left;">${rec.itemName}</div>
	      	 				</c:if>
	      	 			</c:if>
      	 			</c:if>
      	 			<c:if test="${doctor}">
	      	 			<div id="otherName" style="float: left;display: none;margin-left: 20px;height: 0px;overflow: visible;margin-top: -9px;">
	      	 				<font color="red">*</font>名称：
		      	 			<input type="text" name="regItem" value="${formDataMap['activity_way']}" style="width: 160px;" class="validate[required]"/>
	      	 			</div>
      	 			</c:if>
      	 			<c:if test="${!doctor}">
      	 				<c:if test="${rec.itemId eq GlobalConstant.RES_REQ_OTHER_ITEM_ID}">
      	 				<div style="margin-right:10%">
      	 				${formDataMap['activity_way']}</div>
      	 				</c:if>
      	 			</c:if>
				</td>      	 			
			</tr>
			<tr>
				<td><span style="color: red">*</span>&nbsp;日期：</td>
	            <td >
                	<c:if test="${doctor}">
                		<input name="activity_date" style="width: 140px;" class="validate[required]" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" type="text" value="${formDataMap['activity_date']}"/>
                	</c:if>
                	<c:if test="${!doctor}">
                		${formDataMap['activity_date']}
                	</c:if>
				</td>
             	<td>学时(小时)：</td>
                <td>
                	<c:if test="${doctor}">
	               		<select name="activity_period" style="width: 144px;" class="autoValue">
	       					<option></option>
	       					<option value="1" <c:if test="${'1' eq formDataMap['activity_period_id']}">selected</c:if>>1</option>
	       					<option value="2" <c:if test="${'2' eq formDataMap['activity_period_id']}">selected</c:if>>2</option>
	       					<option value="3" <c:if test="${'3' eq formDataMap['activity_period_id']}">selected</c:if>>3</option>
	       					<option value="4" <c:if test="${'4' eq formDataMap['activity_period_id']}">selected</c:if>>4</option>
	       					<option value="5" <c:if test="${'5' eq formDataMap['activity_period_id']}">selected</c:if>>5</option>
	       				</select>
                	</c:if>
                	<c:if test="${!doctor}">
                		${formDataMap['activity_period']}
                	</c:if>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>&nbsp;主讲人：</td>
                <td colspan="3">
                	<c:if test="${doctor}">
                		<input class="validate[required]" style="width: 140px;" name="activity_speaker" type="text" value="${formDataMap['activity_speaker']}"/>
					</c:if>
					<c:if test="${!doctor}">
						${formDataMap['activity_speaker']}
					</c:if>
				</td>
			</tr>
			<tr>
				<td><span style="color: red;">*</span>&nbsp;内容：</td>
				<td colspan="3">
					<c:if test="${doctor}">
						<textarea class="validate[required]" style="width: 100%; height:100px; border: none;" name="activity_content">${formDataMap['activity_content']}</textarea>
					</c:if>
					<c:if test="${!doctor}">
						${formDataMap['activity_content']}
					</c:if>
				</td>
			</tr>
			<tr>
        			<td ><%--<span style="color: red;">*</span>--%>&nbsp;附件：</td>
        			<td colspan="3">
	        			<c:if test="${doctor}">
	        					<div id="url" style="margin: 0px;padding: 0px; width: 550px;"></div>
	        					<div style="margin-left: 10px;padding: 0px;float: left; ">
	        					[<a href="javascript:void(0);" id="upload"  onclick="$('#file').click();" style="color: blue;">上传</a>]
	        					&nbsp;&nbsp;[<a href="javascript:void(0);" id="close"  onclick="shanChu();" style="color: blue;">删除</a>] 
	        					&nbsp;&nbsp;<span style="color: red;" id="shuo">（必须上传培训照片）</span></div>
	        			</c:if>
	        			<c:if test="${!doctor}">
        					<div id="url" style="margin: 0px;padding: 0px;"></div>
        				</c:if>
        			</td>
        	</tr>
          </table>
			<p align="center">
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