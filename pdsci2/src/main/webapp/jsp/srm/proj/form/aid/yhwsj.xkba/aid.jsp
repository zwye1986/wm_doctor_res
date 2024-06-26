<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">

function back(){
	history.back();
}
function selectTag(showContent, selfObj) {
	if(false==$("#projAidForm").validationEngine("validate")){
		return false;
	}
    // 操作标签
    var tag = document.getElementById("tags").getElementsByTagName("li");
    var taglength = tag.length;
    for (var i = 0; i < taglength; i++) {
        tag[i].className = "";
    }
    $('#'+selfObj).addClass("selectTag");
    // 操作内容
    for (var i = 0; j = document.getElementById("tagContent" + i); i++) {
        j.style.display = "none";
    }
    document.getElementById(showContent).style.display = "block";
    $("#flag").val("flag");
}

function add(tb){
 	$("#"+tb+"Tb").append($("#"+tb+"Template tr:eq(0)").clone());
 	
 	var length = $("#"+tb+"Tb").children().length;
 	//序号
	$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length);
}

function delTr(tb){
	//alert("input[name="+tb+"Ids]:checked");
	var checkboxs = $("input[name='"+tb+"Ids']:checked");
	if(checkboxs.length==0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除?",function () {
		var trs = $('#'+tb+'Tb').find(':checkbox:checked');
		$.each(trs , function(i , n){
			$(n).parent('td').parent("tr").remove();
		});
		//删除后序号
		var serial = 0;
		$("."+tb+"Serial").each(function(){
			serial ++;
			$(this).text(serial);
		});
	});
}

function delFile(obj){
	jboxConfirm("确认删除?",function () {
		$(obj).parent().parent().remove();
		//删除后序号
		var serial = 0;
		$(".fileListSerial").each(function(){
			serial ++;
			$(this).text(serial);
		});
	});
	
}
function delFile1(obj){
	jboxConfirm("确认删除?",function () {
		var $td = $(obj).parent();
		$td.html(null);
		$td.prev().html(null);
		var fileInput = "<input type='file' name='fileEdit_file'/>";
		$td.append(fileInput);
	});
}

function nextOpt(step){
	var form = $("#projAidForm");
	if(false==form.validationEngine("validate")){
		return false;
	}
	jboxStartLoading();
	form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
	form.submit();
}

function selectSingle(obj) {
	var value = $(obj).val();
	var name = $(obj).attr("name");
	$("input[name="+name+"][value!="+value+"]:checked").attr("checked",false);
}

function changeType(obj){
	$("select[name='projTypeName2'] option[value!='']").remove();
	var projTypeName = obj.value;
	if(projTypeName != ""){
		var $projTypeName2 = $("select[name='projTypeName2']");
		if(projTypeName == "浙江省医学重点学科"){
			$option1 =$("<option value='重点学科群'>重点学科群</option>");
			$option2 =$("<option value='支撑学科'>支撑学科</option>");
			$option3 =$("<option value='创新学科'>创新学科</option>");
			$option4 =$("<option value='省市共建学科'>省市共建学科</option>");
			$option5 =$("<option value='县级龙头学科（全称：浙江省县级医学龙头学科）'>县级龙头学科（全称：浙江省县级医学龙头学科）</option>");
			$option6 =$("<option value='其他'>其他</option>");
			$projTypeName2.append($option1);
			$projTypeName2.append($option2);
			$projTypeName2.append($option3);
			$projTypeName2.append($option4);
			$projTypeName2.append($option5);
			$projTypeName2.append($option6);
		}else if(projTypeName == "浙江省中医药重点学科"){
			$option1 =$("<option value='学科群'>学科群</option>");
			$option2 =$("<option value='继承类'>继承类</option>");
			$option3 =$("<option value='创新类'>创新类</option>");
			$option4 =$("<option value='服务类'>服务类</option>");
			$option5 =$("<option value='基层优势类'>基层优势类</option>");
			$projTypeName2.append($option1);
			$projTypeName2.append($option2);
			$projTypeName2.append($option3);
			$projTypeName2.append($option4);
			$projTypeName2.append($option5);
		}else if(projTypeName == "杭州市医学重点学科"){
			$option1 =$("<option value='一类'>一类</option>");
			$option2 =$("<option value='二类'>二类</option>");
			$option3 =$("<option value='三类'>三类</option>");
			$projTypeName2.append($option1);
			$projTypeName2.append($option2);
			$projTypeName2.append($option3);
		}
	}
}

$(document).ready(function(){
	changeType($("#projTypeName")[0]);
	var projTypeName2 = "${resultMap.projTypeName2}";
	if(projTypeName2 != ""){
		$("select[name='projTypeName2'] option[value='"+projTypeName2+"']").attr("selected",true);
	}
});
</script>

</head>
<body>
<div id="main" >
    <div class="mainright">
        <div class="content">
    	    <ul id="tags">
                <li class="selectTag" id="tag0"><a onclick="selectTag('tagContent0','tag0')" href="javascript:void(0)">基本信息</a></li>
                <li id="tag1"><a onclick="selectTag('tagContent1','tag1')" href="javascript:void(0)">附件上传</a></li>
            </ul>
    	    <form  method="post" action="<s:url value="/srm/aid/proj/saveStepForXk"/>" id="projAidForm"  style="position: relative;" enctype="multipart/form-data">
                <input type="hidden" id="pageName" name="pageName" value="step1">
            	<input type="hidden" id="projFlow" name="projFlow" value="${aidProj.projFlow}"/>
            	<input type="hidden" id="projSubCategoryId" name="projSubCategoryId" value="${param.typeId}"/>
            	<input type="hidden" id="projSubCategoryName" name="学科备案"/>
            	
            	<input type="hidden" name="projYear" value="${pdfn:getCurrYear()}"/>
            	<input type="hidden" id="projCategoryId" name="${ProjCategroyEnumXk.id}"/>
            	<input type="hidden" id="projCategoryName" name="${ProjCategroyEnumXk.name}"/>
            	
    	        <div id="tagContent">
                    <div class="tagContent selectTag" id="tagContent0">
    	                <table width="100%" class="basic"> 
    	                    <tr>
    	                        <th class="theader" colspan="4" style="text-align: left;padding-left: 20px">基本信息</th>
    	                    </tr>
    	                    <tr>
   	                            <td width="15%" style="text-align: right;"><span style="color: red;">*</span>&nbsp;学科名称：</td>
   	                            <td width="35%" colspan="3">
   	                            	<input type="text" class="validate[required] inputText" name="projName" value="${resultMap.projName}" style="width:90%;text-align: left;"/>
   	                            </td>
   	                        </tr>
    	                    <tr>
    	                    	<td width="15%" style="text-align: right;">学科类型：</td>
   	                            <td width="35%">
   	                            	<select id="projTypeName" name="projTypeName" onchange="changeType(this);" class="inputText" style="width: 90%;">
										<option value="">请选择</option>
										<option value="浙江省医学重点学科" <c:if test="${resultMap.projTypeName eq '浙江省医学重点学科'}">selected="selected"</c:if>>浙江省医学重点学科</option>
										<option value="浙江省中医药重点学科" <c:if test="${resultMap.projTypeName eq '浙江省中医药重点学科'}">selected="selected"</c:if>>浙江省中医药重点学科</option>
										<option value="杭州市医学重点学科" <c:if test="${resultMap.projTypeName eq '杭州市医学重点学科'}">selected="selected"</c:if>>杭州市医学重点学科</option>
										<option value="其他学科" <c:if test="${resultMap.projTypeName eq '其他学科'}">selected="selected"</c:if>>其他学科</option>
									</select>
   	                            </td>
   	                            <td width="15%" style="text-align: right;">详细类型：</td>
   	                            <td width="35%">
   	                            	<select id="projTypeName2" name="projTypeName2" class="inputText" style="width: 90%;">
										<option value="">请选择</option>
									</select>
   	                            </td>
   	                        </tr>
   	                        <tr>
   	                            <td style="text-align: right;">单位：</td>
   	                            <td>
   	                            	<input type="text" class="inputText" name="applyOrgName" value="${empty resultMap.applyOrgName?sessionScope.currUser.orgName:resultMap.applyOrgName}" style="width:90%;text-align: left;"/>
   	                            </td>
   	                            <td style="text-align: right;">学科带头人：</td>
   	                            <td>
   	                            	<input type="text" class="inputText" name="applyUserName" value="${empty resultMap.applyUserName?sessionScope.currUser.userName:resultMap.applyUserName}" style="width:90%;text-align: left;"/>
   	                            </td>
   	                        </tr>
   	                        <tr>
   	                            <td style="text-align: right;">建设周期：</td>
   	                            <td>
   	                            	<input type="text" class="inputText" name="buildPeriod" value="${resultMap.buildPeriod}" style="width:90%;text-align: left;"/>
   	                            </td>
   	                            <td style="text-align: right;">经费补助：</td>
   	                            <td>
   	                            	<input type="text" class="validate[custom[number]] inputText" name="fundSubsidy" value="${resultMap.fundSubsidy}" style="width:90%;text-align: left;"/>
   	                            </td>
   	                        </tr>
   	                        <tr>
   	                            <td style="text-align: right;">申报结果：</td>
   	                            <td colspan="3" style="text-align: left; padding-left: 2%;">
   	                            	<input type="checkbox" name="applyResult" id="applyResult_Y" value="通过" onchange="selectSingle(this)" <c:if test="${resultMap.applyResult=='通过'}">checked="checked"</c:if>/>&nbsp;<label for="applyResult_Y">通过</label>
   	                            	&#12288;<input type="checkbox" name="applyResult" id="applyResult_N" value="不通过" onchange="selectSingle(this)" <c:if test="${resultMap.applyResult=='不通过'}">checked="checked"</c:if>/>&nbsp;<label for="applyResult_N">不通过</label>
   	                            </td>
   	                        </tr>
    	                </table>
    	                
    	                <table width="100%" class="basic" style="margin-top: 20px;"> 
    	                    <tr>
    	                        <th style="text-align: left;padding-left: 20px;">中期考核结果：</th>
    	                    </tr>
    	                    <tr>
    	                        <td>
    	                            <textarea name="mediumResult" placeholder="" class="xltxtarea">${resultMap.mediumResult}</textarea>
    	                        </td>
    	                    </tr>
    	                    <tr>
    	                        <th style="text-align: left;padding-left: 20px;">验收结果：</th>
    	                    </tr>
    	                    <tr>
    	                        <td>
    	                            <textarea name="completeResult" placeholder="" class="xltxtarea">${resultMap.completeResult}</textarea>
    	                        </td>
    	                    </tr>
    	                </table>
    	                <div class="button" style="width: 100%; text-align: center;margin-top: 20px;">
    	                    <input type="button" onclick="selectTag('tagContent1','tag1')" class="search" value="下一步"/>
	                    </div>
    	            </div>
    	        
    	        
    	        <div class="tagContent" id="tagContent1">
    	        	<c:set var="file1"/>
    	        	<c:set var="file2"/>
    	        	<c:set var="file3"/>
    	        	<c:set var="file4"/>
    	        	<c:forEach items="${resultMap.fileEdit}" var="fe">
						<c:if test="${fe.objMap.fileEdit_fileRemark eq '1'}">
							<c:set var="file1" value="${fe.objMap.fileEdit_file}"/>
						</c:if>
						<c:if test="${fe.objMap.fileEdit_fileRemark eq '2'}">
							<c:set var="file2" value="${fe.objMap.fileEdit_file}"/>
						</c:if>
						<c:if test="${fe.objMap.fileEdit_fileRemark eq '3'}">
							<c:set var="file3" value="${fe.objMap.fileEdit_file}"/>
						</c:if>
						<c:if test="${fe.objMap.fileEdit_fileRemark eq '4'}">
							<c:set var="file4" value="${fe.objMap.fileEdit_file}"/>
						</c:if>
					</c:forEach>
					
			        <table class="bs_tb" style="width: 100%">
					    <tr>
			                <th colspan="5" class="theader">附件
                				<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                					<span style="float: right;padding-right: 10px">
									<a href="javascript:void(0)"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('fileList')"></img></a>&#12288;
									<a href="javascript:void(0)"><img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('fileList')"></img></a>
									</span>
								</c:if>
			                </th>
			            </tr>
					    <tr>
			           		<td width="3%"></td>
			           		<td width="5%" style="font-weight:bold;padding: 0px;">序号</td>
			                <td width="50%" style="font-weight:bold;padding: 0px;">附件名称</td>
			                <td>有（√）</td>
			                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
			                	<td width="30%">操作</td>
			                </c:if>
			            </tr>
						<tbody id="fileListTb">
		    			<tr>
			   				 <td width="3%"></td>
				             <td style="text-align: center;" class="fileListSerial">1</td>
				             <td>
				             	<c:if test="${not empty file1}">
									<a href='<s:url value="/pub/file/down?fileFlow=${file1}"/>'>申报材料</a>
									<input type="hidden" name="fileEdit_fileRemark" value="1"/>
									<input type="hidden" name="fileEdit_fileName" value="申报材料"/>
								</c:if>
				             	<c:if test="${empty file1}">申报材料</c:if>
				             </td>
				             <td>
								<c:if test="${not empty file1}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/>
								</c:if>
				             </td>
							<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
								<td style="padding-left: 20px;">
									<c:if test="${not empty file1}">
										[<a href="javascript:void(0)" onclick="delFile1(this)">删除</a>]
										<input type="hidden" name="fileEdit_file" value="${file1}"/>
									</c:if>
									<c:if test="${empty file1}">
									   <input type="file" name="fileEdit_file" />
									   <input type="hidden" name="fileEdit_fileRemark" value="1"/>
									   <input type="hidden" name="fileEdit_fileName" value="申报材料"/>
									</c:if>
								</td>
							</c:if>
						</tr>
						
		    			<tr>
			   				 <td width="3%"></td>
				             <td style="text-align: center;" class="fileListSerial">2</td>
				             <td>
				             	<c:if test="${not empty file2}">
									<a href='<s:url value="/pub/file/down?fileFlow=${file2}"/>'>学科合同书</a>
									<input type="hidden" name="fileEdit_fileRemark" value="2"/>
									<input type="hidden" name="fileEdit_fileName" value="学科合同书"/>
								</c:if>
				             	<c:if test="${empty file2}">学科合同书</c:if>
				             </td>
				             <td>
								<c:if test="${not empty file2}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/>
								</c:if>
				             </td>
							<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
								<td style="padding-left: 20px;">
									<c:if test="${not empty file2}">
										[<a href="javascript:void(0)" onclick="delFile1(this)">删除</a>]
										<input type="hidden" name="fileEdit_file" value="${file2}"/>
									</c:if>
									<c:if test="${empty file2}">
									   <input type="file" name="fileEdit_file" />
									   <input type="hidden" name="fileEdit_fileRemark" value="2"/>
									   <input type="hidden" name="fileEdit_fileName" value="学科合同书"/>
									</c:if>
								</td>
							</c:if>
						</tr>
						
		    			<tr>
			   				 <td width="3%"></td>
				             <td style="text-align: center;" class="fileListSerial">3</td>
				             <td>
				             	<c:if test="${not empty file3}">
									<a href='<s:url value="/pub/file/down?fileFlow=${file3}"/>'>评估检查表</a>
									<input type="hidden" name="fileEdit_fileRemark" value="3"/>
									<input type="hidden" name="fileEdit_fileName" value="评估检查表"/>
								</c:if>
				             	<c:if test="${empty file3}">评估检查表</c:if>
				             </td>
				             <td>
								<c:if test="${not empty file3}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/>
								</c:if>
				             </td>
							<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
								<td style="padding-left: 20px;">
									<c:if test="${not empty file3}">
										[<a href="javascript:void(0)" onclick="delFile1(this)">删除</a>]
										<input type="hidden" name="fileEdit_file" value="${file3}"/>
									</c:if>
									<c:if test="${empty file3}">
									   <input type="file" name="fileEdit_file" />
									   <input type="hidden" name="fileEdit_fileRemark" value="3"/>
									   <input type="hidden" name="fileEdit_fileName" value="评估检查表"/>
									</c:if>
								</td>
							</c:if>
						</tr>
						
		    			<tr>
			   				 <td width="3%"></td>
				             <td style="text-align: center;" class="fileListSerial">4</td>
				             <td>
				             	<c:if test="${not empty file4}">
									<a href='<s:url value="/pub/file/down?fileFlow=${file4}"/>'>学科验收表</a>
									<input type="hidden" name="fileEdit_fileRemark" value="4"/>
									<input type="hidden" name="fileEdit_fileName" value="学科验收表"/>
								</c:if>
				             	<c:if test="${empty file4}">学科验收表</c:if>
				             </td>
				             <td>
								<c:if test="${not empty file4}">
									<img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/>
								</c:if>
				             </td>
							<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
								<td style="padding-left: 20px;">
									<c:if test="${not empty file4}">
										[<a href="javascript:void(0)" onclick="delFile1(this)">删除</a>]
										<input type="hidden" name="fileEdit_file" value="${file4}"/>
									</c:if>
									<c:if test="${empty file4}">
									   <input type="file" name="fileEdit_file" />
									   <input type="hidden" name="fileEdit_fileRemark" value="4"/>
									   <input type="hidden" name="fileEdit_fileName" value="学科验收表"/>
									</c:if>
								</td>
							</c:if>
						</tr>
						
						<c:set var="count" value="4"/>
						<c:forEach items="${resultMap.fileEdit}" var="fe">
						<c:if test="${fe.objMap.fileEdit_fileRemark =='other'}">
						<tr>
							<td></td>
							<td class="fileListSerial">${count+1}<c:set var="count" value="${count+1}"/></td>
							<td>
								<a href='<s:url value="/pub/file/down?fileFlow=${fe.objMap.fileEdit_file}"/>'>${fe.objMap.fileEdit_fileName}</a>
								<input type="hidden" name="fileEdit_fileRemark" value="${fe.objMap.fileEdit_fileRemark}"/>
								<input type="hidden" name="fileEdit_fileName" value="${fe.objMap.fileEdit_fileName}"/>
								<input type="hidden" name="fileEdit_file" value="${fe.objMap.fileEdit_file}"/>
							</td>
							<td><img src="<s:url value='/css/skin/${skinPath}/images/gou.gif'/>"/></td>
							<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
								<td style="padding-left: 20px;">
									[<a href="javascript:void(0)" onclick="delFile(this)">删除</a>]
								</td>
							</c:if>
						</tr>
						</c:if>
						</c:forEach>
						</tbody>
					</table>
    	            <div class="button" style="width: 100%;text-align: center; margin-top: 20px;">
    	            	<input type="button" onclick="selectTag('tagContent0','tag0')" class="search" value="上一步"/>
    	            	<input type="button" onclick="nextOpt('finish')" class="search" value="保&#12288;存"/>
	                </div>
    	        </div>
	       	</div>
    	    </form>
        </div>
    </div>
</div>


<div style="display: none;">
  		<!-- 文件 -->
  		<table class="basic" id="fileListTemplate" style="width: 100%">
        <tr>
             <td width="3%" style="text-align: center;"><input name="fileListIds" type="checkbox"/></td>
             <td width="5%" style="text-align: center;" class="fileListSerial"></td>
             <td>
             	<input type="text" name="fileEdit_fileName" class="inputText" style="width: 80%"/>
             	<input type="hidden" name="fileEdit_fileRemark" class="validate[required]" value="other"/>
             </td>
             <td></td>
             <td style="padding-left: 20px;">
             	<input type="file" name="fileEdit_file" class="validate[required]"/>
             </td>
		</tr>
		</table>
</div>
</body>
</html>