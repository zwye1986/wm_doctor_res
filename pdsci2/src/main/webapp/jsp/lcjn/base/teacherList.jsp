<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function editInfo(userFlow){
            var title = userFlow == ""?"新增":"编辑";
            var url = "<s:url value='/lcjn/base/editTeaInfo?userFlow='/>"+userFlow;
            jboxOpen(url, title,600,400);
        }
        function importInfo(){
            jboxOpen("<s:url value='/jsp/lcjn/base/importTeacher.jsp'/>", "导入",600,200);
        }
        function exportInfo(){
            var url = "<s:url value='/lcjn/base/expTeacher'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        function stopSelectTea(){
            var checkLen = $(":checkbox[class='check']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选老师信息！");
                return;
            }
            var recordLst = [];
            $(":checkbox[class='check']:checked").each(function(){
                recordLst.push(this.value);
            })
            jboxConfirm("是否确认一键停用？", function(){
                var json = {"recordLst":recordLst,"recordStatus":"N"};
                var url = "<s:url value='/lcjn/base/stopTeaOption'/>";
                jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function startInfo(userFlow,recordStatus){
            var optName = recordStatus == 'Y'?"停用":"启用";
            jboxConfirm("是否确认"+optName+"？", function(){
                var url = "<s:url value='/lcjn/base/teacherOption?userFlow='/>"+userFlow+"&recordStatus="+recordStatus;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function checkAll(){
            if($("#checkAll").attr("checked")){
                $(".check").attr("checked",true);
            }else{
                $(".check").attr("checked",false);
            }
        }
        function checkSingel(obj){
            if(!$(obj).attr("checked")){
                $("#checkAll").attr("checked",false);
            }else{
                var checkAllLen = $("input[type='checkbox'][class='check']").length;
                var checkLen = $("input[type='checkbox'][class='check']:checked").length;
                if(checkAllLen == checkLen){
                    $("#checkAll").attr("checked",true);
                }
            }
        }
    </script>
</head>
<body id="initCont">
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/lcjn/base/teacherList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>职称：
                <select name="titleId" style="width:137px;height:20px;" class="select">
                    <option/>
                    <c:forEach items="${dictTypeEnumLcjnUserTitleList}" var="dict">
                        <option value="${dict.dictId}" ${param.titleId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>姓名：
						<span>
                            <input type="text" name="userName" value="${param.userName}">
                        </span>
                <span style="padding-left:20px;"></span>教师状态：
                <select name="recordStatus" style="width:137px;" class="select">
                    <option/>
                    <option value="Y" ${param.recordStatus eq 'Y'?'selected':''}>启用</option>
                    <option value="N" ${param.recordStatus eq 'N'?'selected':''}>停用</option>
                </select>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <div class="newStyleSubDiv">
                    <input type="button" class="search" value="新&#12288;增" onclick="editInfo('')"/>
                    <input type="button" class="search" value="导&#12288;入" onclick="importInfo()"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
                    <input type="button" class="search" value="一键停用" onclick="stopSelectTea()"/>
                </div>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th><input type="checkbox" id="checkAll" onclick="checkAll()">&nbsp;序号</th>
                <th>用户名</th>
                <th>姓名</th>
                <th>性别</th>
                <th>职称</th>
                <th>联系方式</th>
                <th>所在单位</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${userList}" var="user" varStatus="i">
                <tr>
                    <td><input type="checkbox" class="check" value="${user.userFlow}" onclick="checkSingel(this)">&nbsp;${i.index + 1}</td>
                    <td>${user.userCode}</td>
                    <td>${user.userName}</td>
                    <td>${user.sexName}</td>
                    <td>${user.titleName}</td>
                    <td>${user.userPhone}</td>
                    <td>${user.workOrgName}</td>
                    <td>
                        <a onclick="editInfo('${user.userFlow}');" style="cursor:pointer;color:#4195c5;">编辑</a>
                        <a onclick="startInfo('${user.userFlow}','${user.recordStatus}');" style="cursor:pointer;color:#4195c5;">${user.recordStatus eq 'Y'?'停用':'启用'}</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>