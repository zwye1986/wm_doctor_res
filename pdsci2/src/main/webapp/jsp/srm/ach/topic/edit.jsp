<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
</jsp:include>
<script type="text/javascript">
function saveTopic(){
  	if(false==$("#srmTopic").validationEngine("validate")){
		return false;
	}

  	/*var topic = $("#srmTopic").serializeJson();
  	var achFile = $("#srmAchFile").serializeJson();

	var t = {'topic':topic,"srmAchFile":achFile};
   	$("#jsondata").val(JSON.stringify(t));*/
   	var url = "<s:url value='/srm/ach/topic/save'/>";
   	jboxStartLoading();
	jboxSubmit($('#srmTopic'),url,function(resp){
			window.parent.frames['mainIframe'].location.reload(true);
			jboxClose();
		},
		function(resp){
 			alert("error");
		});
}
    
function addAuthor(){
 	$('#test').append($("#moban tr:eq(0)").clone());
}

function delAuthorTr(obj){
	jboxConfirm("确认删除？" , function(){
	 	var tr=obj.parentNode.parentNode;
	    tr.remove();
	});
}

function reCheck(obj){
 	   $(obj).hide();
 	   $("#down").hide();
 	   $("#file").show();
}

$(function(){
 	  <c:if test="${param.editFlag == GlobalConstant.FLAG_N}">
 	  	$('#look input[type!=button]').attr('disabled' , "disabled");
 	  	$('#look textarea').attr('readonly' , "readonly");
 	  	$('#look select').attr('disabled' , "disabled");
 	  	$("#reCheck").css("display", "none");
 	  </c:if>
});
function strChar(obj,limitLength){
	var str = $(obj).val();
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		if (str[i].match(/[^\x00-\xff]/ig) != null){  //全角
			len += 2;
		}
		else
			len += 1;
	}
	if(len > parseInt(limitLength)){
		jboxTip("当前字符长度超过限制");
		$(obj).val("");
	}
}
$(function(){
    var url = "<s:url value='/srm/ach/topic/searchDept'/>";
    var selectedFlow="${topic.deptFlow}";
    jboxPost(url,null,function(resp){
        $.each(resp,function(i,n){
            if(selectedFlow == n.deptFlow){
            $("#selectDept").append("<option selected='selected' value='"+ n.deptFlow +"'>"+ n.deptName +"</option>");
            }else {
                $("#selectDept").append("<option  value='" + n.deptFlow + "'>" + n.deptName + "</option>");
            }
        });
    },null,false);
});
    function getDeptName(obj){
        $("#deptName").val($(obj).find("option:selected").text());
    }
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
		<div id="look">
		<form id="srmTopic" style="position: relative;" enctype="multipart/form-data" method="post">
			<input id="jsondata" type="hidden" name="jsondata" value=""/>
			<table class="basic" style="width: 100%">
				<tr class="bs_name" style="height: 26px">
					<td colspan="4">基本信息</td>
				</tr>
                <tr>
                 	<th width="17%">课题名称：</th>
                 	<td colspan="3">
                 	<input type="hidden" name="topicFlow" value="${topic.topicFlow }" >
                 	<input class="validate[required,maxSize[100]] name xltext" type="text" name="topicName" value="${topic.topicName }" style="width: 97%;"></td>
                </tr>
                <tr>
                    <th>姓&#12288;&#12288;名：</th>
                    <td >
                        <input type="hidden" name="applyUserFlow" value="${sessionScope.currUser.userFlow}"/>
                        <input type="text" class="xltext" name="applyUserName"  value="<c:if test="${empty topic.applyUserName}">${sessionScope.currUser.userName}</c:if>${topic.applyUserName}"/>
                    </td>
                     <th>所在科室：</th>
                     <td>
                         <input type="hidden" id="deptName" name="deptName" value="${topic.deptName}"/>
                         <select name="deptFlow" id="selectDept" class="xlselect" onchange="getDeptName(this);">
                             <option value="">请选择</option>
                         </select>
                     </td>
                </tr>
                <tr>
                	<th>批准文号：</th>
                 	<td>
                        <input type="text" class="xltext" name="approveNumber"  value="${topic.approveNumber }">
                  	</td>
                    <th>主管部门：</th>
                    <td>
                        <input type="text" class="xltext" name="chargeOrg"  value="${topic.chargeOrg}">
                    </td>
				</tr>
                <tr>
                    <th>课题类别：</th>
                    <td>
                        <select name="typeId" class="xlselect">
                            <option value=""></option>
                            <c:forEach items="${dictTypeEnumAchTopicTypeList}" var="dict" >
                                <option value="${dict.dictId }" <c:if test="${topic.typeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>拨款（万元）：</th>
                    <td>
                        <input type="text" class="xltext" name="topicMoney"  value="${topic.topicMoney }">
                    </td>
                </tr>
                <tr>
                    <th>研究周期：</th>
                    <td colspan="3"><input class="xltext ctime" style="width:160px;" type="text" name="topicStartTime" value="${topic.topicStartTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly">
                        ~&#12288;&#12288;<input class="xltext ctime" style="width:160px;" type="text" name="topicEndTime" value="${topic.topicEndTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly">
                    </td>
                </tr>
			</table>
          
          
			<table class="basic" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4" class="bs_name">附件上传</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="17%">附件：</th>
						<td colspan="3">
                            <input type="hidden" name="fileFlow" value="${file.fileFlow }"/>
							<c:choose>
								<c:when test="${not empty file.fileName}">
								<a id="down" href='<s:url value="/srm/file/achDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
								<input type="file" id="file" name="file" style="display: none;"/>
								<span style="float: right; padding-right: 10px;">
								<a id="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>
								</span>
							</c:when>
							<c:otherwise>
								<input type="file" id="file" name="file"/>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
			      
			<table class="basic" style="width: 100%">
				<thead>
				    <tr>
				        <th colspan="4" class="bs_name">备注信息</th>
				    </tr>
				</thead>
				<tbody>
				    <tr>
						<th width="17%">备注信息：</th>
						<td>
							<textarea class="xltxtarea" style="margin-left: 0px;" placeholder="100个字符以内" name="remark" onblur="strChar(this,20)">${topic.remark}</textarea>
						</td>
					</tr>
			 	</tbody>
			</table>
    </form>
		<p align="center">
			<c:if test="${param.editFlag != GlobalConstant.FLAG_N}">   
			<input type="button" onClick="saveTopic();" value="保&#12288;存" class="search"/>
			<input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/> 
			</c:if> 
			
		</p>
		
		</div>
		</div>
	</div> 	
</div>
</body>
</html>