<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
    <jsp:param name="jquery_ztree" value="true"/>
</jsp:include>
<script type="text/javascript">
	function saveExpertGroupUser() {
		var url = "<s:url value='/srm/expert/groupUser/saveExpertGroupUser'/>";
		jboxStartLoading();
		jboxPost(url , $('#expertGroupUserForm').serialize() , function(){
			window.parent.frames['jbox-message-iframe'].window.location.reload(true);
		} , null , true);
	}
	function search(){
		var url = "<s:url value='/srm/expert/groupUser/search'/>";
		jboxStartLoading();
		jboxPost(url , $('#expertGroupUserForm').serialize() , function(resp){
			$("#tbody").empty();
			var techAreaName = "";
            var sexName = "";
            var titleName = "";
            var degreeName = "";
            var orgName = "";
			$.each(resp , function(i , n){
				techAreaName = n.expert.techAreaName;
                sexName = n.user.sexName;
                titleName = n.user.titleName;
                degreeName = n.user.degreeName;
                orgName = n.user.orgName;
				if(techAreaName==null){
					techAreaName = "";
				}
                if(sexName==null){
                    sexName = "";
                }
                if(titleName==null){
                    titleName = "";
                }
                if(degreeName==null){
                    degreeName = "";
                }
                if(orgName==null){
                    orgName = "";
                }
				$("#tbody").append("<tr>"+"<td style='text-align: center;'>"+"<input type='checkBox' id='"+n.expert.userFlow+"' name='userFlow' value='"+n.expert.userFlow+"' class='validate[required]''>"+"</td>"+"<td style='text-align: center;'>"+n.user.userName+"</td>"+"<td style='text-align: center;'  >"+sexName+"/"+titleName+"</td>"+"<td style='text-align: center;'>"+degreeName+"</td>"+"<td style='text-align: center;'>"+orgName+"</td>"+"<td style='text-align: center;'>"+techAreaName+"</td>"+"</tr>");
			});
		} , null , false);
		   
	}
	function doClose() {
		jboxClose();
	}
	function c(obj){
		alert(obj.id);
	}

    var setting = {
        view: {
            dblClickExpand: false,
            showIcon: false,
            showTitle:false,
            selectedMulti:false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };

    function beforeClick(treeId, treeNode) {
        var check = (treeNode.id!=0);
        if (!check) jboxTip('不能选择根节点');
        return check;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getSelectedNodes(),
                v = "";
        id = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
            id += nodes[i].id + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        if (id.length > 0 ) id = id.substring(0, id.length-1);
        var cityObj = $("#majorName");
        $("#majorId").val(id);
        cityObj.attr("value", v);
    }
    function showMenu() {
        $("#majorId").val("");
        $("#majorName").val("");
        var cityObj = $("#majorName");
        var cityOffset = $("#majorName").offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }
    $(document).ready(function(){
        var url = "<s:url value='/sys/subject/getAllDataJson'/>";
        jboxPostJson(url,null,function(data){
            if(data){
                zNodes = $.parseJSON(data);
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);//所属学科
            }
        },null,false);
    });
</script>
</head>
<body>
	
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
			<form id="expertGroupUserForm" action="/srm/expert/groupUser/search" method="post">
                <p>专家姓名：<input type="text" name="userName" value="${param.userName}" class="xltext"/>
				机构名称：<input type="text" name="orgName" value="${param.orgName}" class="xltext"/>
                </p>
                <p>
                    <input type="hidden" id="majorId" name="majorId" value="${expert.majorId}"/>
                    专&#12288;&#12288;业：<input type="text" id="majorName" name="majorName" value="${param.majorName}" class="xltext" readonly="readonly" onclick="showMenu(); return false;"/>
                <input type="button" class="search" onclick="search();"  value="查&#12288;询"/>
						<input type="hidden" name="groupFlow" value="${param.groupFlow}" /> 
				</p>
                <div style="margin-top: 10px">
			<table class="xllist" style="width: 100%">
			  <thead>
				<tr>
					<th width="5%">选择</th>
					<th width="15%">姓名</th>
					<th width="20%">性别/职称</th>
					<th width="10%">学位</th>
					<th width="35%">机构名称</th>
					<th width="15%">技术领域</th>
				<tr>
			   </thead>
			   <tbody id="tbody">
				<c:forEach items="${expertInfoList}" var="expertInfo">
					<tr>
						<td >
							<input id="${expertInfo.expert.userFlow}" name="userFlow" type="checkbox" value="${expertInfo.expert.userFlow}" class="validate[required]" >
						</td>
						<td>${expertInfo.user.userName}</td>
						<td>${expertInfo.user.sexName}/${expertInfo.user.titleName}</td>
						<td>${expertInfo.user.degreeName}</td>
						<td>${expertInfo.user.orgName}</td>
						<td>${expertInfo.expert.techAreaName}</td>	
					</tr>													
				</c:forEach>
				</tbody>
			</table>
                    </div>
			</form>
            <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
                <ul id="treeDemo" class="ztree" style="margin-top:0; width:159px;"></ul>
            </div>
			<p align="center">
				<input type="button" value="保&#12288;存" class="search"  onclick="saveExpertGroupUser();"  />
				<input type="button" class="search" onclick="doClose();" value="关&#12288;闭">
			</p>
		</div>
	</div>
</div>
</body>
</html>