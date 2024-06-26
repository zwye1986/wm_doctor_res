<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
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
	<jsp:param name="treetable" value="true"/>
</jsp:include>
<script>

	$(function () {

//		var tNodes = [
//			{ id: 1, pId: 0, name: "父节点1", td: ["parent", "1"] },
//			{ id: 11, pId: 1, name: "父节点11", td: ["<a href='javascript:void(0);' onclick=\\"alert('内容为html');\\">parent</a>", "11"] },
//			{ id: 111, pId: 11, name: "叶子节点111", td: ["children", "111"] },
//			{ id: 112, pId: 11, name: "叶子节点112", td: ["children", "112"] },
//			{ id: 113, pId: 11, name: "叶子节点113", td: ["children", "113"] },
//			{ id: 12, pId: 1, name: "父节点12", td: ["parent", "12"] },
//			{ id: 121, pId: 12, name: "叶子节点121", td: ["children", "121"] },
//			{ id: 122, pId: 12, name: "叶子节点122", td: ["children", "122"] },
//			{ id: 123, pId: 12, name: "叶子节点123", td: ["children", "123"] },
//			{ id: 124, pId: 12, name: "叶子节点124", td: ["children", "124"] },
//			{ id: 2, pId: 0, name: "父节点2", td: ["parent", "2"] },
//			{ id: 21, pId: 2, name: "父节点21", td: ["parent", "21"] },
//			{ id: 211, pId: 21, name: "叶子节点211", td: ["children"] },
//			{ id: 212, pId: 21, name: "叶子节点212", td: ["children", "212"] },
//			{ id: 213, pId: 21, name: "叶子节点213", td: ["children", "213"] },
//			{ id: 114, pId: 11, name: "叶子节点114", td: ["children", "114"] },
//			{ id: 214, pId: 21, name: "叶子节点214", td: ["children"] }
//		];
		<c:if test="${not empty list }">
			<%--var tNodes=[--%>
					<%--<c:forEach items="${list}" var="cfg" varStatus="s">--%>
					<%--{--%>
						<%--id:"${cfg.id}",--%>
						<%--pId:	<c:if test="${empty cfg.pId}">--%>
								<%--0,--%>
								<%--</c:if>--%>
								<%--<c:if test="${not empty cfg.pId}">--%>
								<%--"${cfg.pId}",--%>
								<%--</c:if>--%>
						<%--name:"${cfg.name}"--%>
					<%--}--%>
					<%--<c:if test="${not s.last}">--%>
						<%--,--%>
					<%--</c:if>--%>
					<%--</c:forEach>--%>
			<%--];--%>
			<%--var heads = ["<font color='red'>*</font>配置文件信息"];--%>
			<%--$.TreeTable("treeTable", heads, tNodes);--%>

		</c:if>
	});

	<c:if test="${not empty list }">
	$(function () {

		//$("#treeTable1").treetable({onNodeExpand:function(){alert(1);}});
		$("#treeTable").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
		<c:if test="${param.isShow ne 'Y'}">
			$("#tbody tr").on("click",function(){
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
				var isFile=$(this).attr("isFile");
				var name=$(this).attr("name");
				if($("#cfgFlow").val()) {
					if ($("#cfgName").val() == "") {
						$("#cfgName").val(name);
					}
				}else{
					$("#cfgName").val(name);
				}
				$("#filePath").val($(this).attr("speOrgFilePath"));
				$("#isFile").val(isFile);
				if(isFile=="N")
				{
					$("#actionTypeId${actionTypeEnumMANY.id}").attr("checked","checked");
					$("#actionTypeId${actionTypeEnumSINGLE.id}").attr("disabled",true);

				}else{
					$("#actionTypeId${actionTypeEnumSINGLE.id}").removeAttr("disabled");
					if(isFile=="Y")
					{
						$("#actionTypeId${actionTypeEnumSINGLE.id}").attr("checked","checked");
					}else{
						$("#actionTypeId${actionTypeEnumMANY.id}").attr("checked","checked");
					}
				}
				$("[name='isFile1']").removeAttr("checked");
				$("[name='isFile1']").each(function(){
					if($(this).val()==isFile)
					{
						$(this).attr("checked","checked");
					}
				});

			});
		</c:if>
		var pId="";
		$("#tbody tr").each(function(){
			if($("#filePath").val()==$(this).attr("speOrgFilePath")||($("#filePath").val()==""&&$("#pFilePath").val()==$(this).attr("speOrgFilePath"))){
				$(this).siblings().removeClass("selected");
				$(this).addClass("selected");
				pId=$(this).attr("data-tt-id");
				return false;
			};
		});
		showTr(pId);
	});
	function showTr(pId) {
		if (pId)
		{
			var id="";
			id=$("tr[data-tt-id='"+pId+"']").attr("data-tt-parent-id");
			showTr(id);
			$('#treeTable').treetable('collapseOrexpand',pId);
			var container = $('#fileCfg'),
			scrollTo =$("tr[data-tt-id='"+pId+"']");
			container.scrollTop(scrollTo.offset().top - container.offset().top + container.scrollTop());
		}
	}
	</c:if>
</script>
<c:if test="${empty param.year}">
	请选择需要加载评估文件信息的年份！
</c:if>
<c:if test="${not empty param.year}">
	<c:if test="${not empty list }">
		<table class="xllist nofix linetable" style="margin-top: 0px;" id="treeTable">
			<tr>
				<td><font color='red'>*</font>配置文件信息</td>
			</tr>
			<tbody id="tbody">
				<c:forEach items="${list}" var="cfg" varStatus="s">
					<tr data-tt-id="${cfg.id}" isFile="${cfg.isFile}" speOrgFilePath="${cfg.speOrgFilePath}" name="${cfg.name}"
						<c:if test="${not empty cfg.pId}">
							data-tt-parent-id="${cfg.pId}"
						</c:if>
					>
						<td>${cfg.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<c:if test="${empty list }">
		暂无${param.year}年评估配置文件信息，请联系管理员！
	</c:if>
</c:if>
