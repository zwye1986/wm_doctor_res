
<%@include file="/jsp/common/doctype.jsp" %>
<html>
	<head>
		<jsp:include page="/jsp/common/htmlhead.jsp">
			<jsp:param name="basic" value="true"/>
			<jsp:param name="jbox" value="true"/>
			<jsp:param name="jquery_form" value="false"/>
		</jsp:include>
		
		<script type="text/javascript">
			function loadRequire(recTypeId,selTab){
				jboxLoad("tagContent","<s:url value='/res/doc/requireList'/>?roleFlag=${param.roleFlag}&relRecordFlow=${param.recordFlow}&recTypeId="+recTypeId,true);
				if(selTab){
					$("#"+recTypeId).click();
				}
			}
			
			//标签高亮
			function selTag(tag){
				$(".selectTag").removeClass("selectTag");
				$(tag).addClass("selectTag");
			}
			
			$(function(){
				if($("#tags li").length){
					<c:if test="${empty param.recTypeId}">
						$("#tags li:eq(0)").click();
						location.href = $(".selectTag a").attr("href");
					</c:if>
					<c:if test="${!empty param.recTypeId}">
						selTag($("#${param.recTypeId}"));
						location.href = $(".selectTag a").attr("href");
					</c:if>
				}
				
// 				$("#rotationDetailTitle").on("mouseenter mouseleave",function(){
// 					$("#rotationDetail").toggle();
// 				});
			});
			
			function back(){
				$("#reqCfg").rightSlideClose();
				//location.href = "<s:url value='/sch/template/rule'/>?roleFlag=${param.roleFlag}&speId=${param.speId}&sessionNumber=${param.sessionNumber}&rotationFlow=${rotation.rotationFlow}";
			}
			
			function editDeptReq(reqFlow){
				var title = "新增";
				if(reqFlow!=null && reqFlow!=''){
					title = "编辑";
				}
				jboxOpen("<s:url value='/sch/template/editDeptReq'/>?rotationFlow=${rotation.rotationFlow}&relRecordFlow=${rotationDept.recordFlow}&reqFlow="+reqFlow+"&recTypeId="+$(".selectTag").prop("id"),title+"轮转要求",500,350);
			}
			
			function delDeptReq(reqFlow,recTypeId){
				jboxConfirm("确认删除该记录?",function(){
					jboxPost("<s:url value='/sch/template/saveDeptReq'/>",{"reqFlow":reqFlow,"recordStatus":"${GlobalConstant.RECORD_STATUS_N}"},function(resp){
						if(resp=="${GlobalConstant.DELETE_SUCCESSED}"){
							loadRequire(recTypeId,true);
						}
					},null,true);
				},null);
			}
			
			function selRotationDept(recordFlow){
				var url = "<s:url value='/sch/template/rotationRequire'/>?roleFlag=${param.roleFlag}&rotationFlow=${rotation.rotationFlow}&recordFlow="+recordFlow+"&recTypeId="+$(".selectTag")[0].id;
				reqReload(url);
			}
			function synch(){
				var url="<s:url value='/sch/template/synchRequire'/>?relRecordFlow=${rotationDept.recordFlow}";
				jboxOpen(url,"同步要求",600,250);
			}
		</script>
	</head>
<body>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<div class="mainright" align="center" style="background-color: white;">
	<div class="content">
		<div class="title1 clearfix" align="left">
			<div style="float: left;width: 100%;">
				<c:if test="${typeId eq jszyTCMPracticEnumN.id}">
					<c:set var="enumList" value="${registryTypeEnumList}"/>
					<c:set var="enumKey" value="res_registry_type_"/>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumBasicPractice.id}">
					<c:set var="enumList" value="${practicRegistryTypeEnumList}"/>
					<c:set var="enumKey" value="practic_registry_type_"/>
				</c:if>
				<c:if test="${typeId eq jszyTCMPracticEnumTheoreticalStudy.id}">
					<c:set var="enumList" value="${theoreticalRegistryTypeEnumList}"/>
					<c:set var="enumKey" value="theoretical_registry_type_"/>
				</c:if>
				<ul id="tags" style="width: 100%;">
					<c:forEach items="${enumList}" var="registryType">
						<c:set value="${enumKey}${registryType.id}" var="viewCfgKey"/>
						<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y && GlobalConstant.FLAG_Y eq registryType.haveReq}">
		        			<li id="${registryType.id}" onclick="selTag(this);"><a style="cursor: pointer;" href="javascript:loadRequire('${registryType.id}',false);">${registryType.name}</a></li>
		        		</c:if>
					</c:forEach>
		        </ul>
		        
		        <div id="tagContent" style="height: 250px;">
		        	
		        </div>
			</div>
		</div>
		<div>
			<button class="search" onclick="jboxClose();">关&#12288;闭</button>
		</div>
	</div>
</div>
</body>
</html>