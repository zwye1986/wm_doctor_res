
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
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        function toPage(page) {
            var currentPage="";
            if(!page||page!=undefined){
                currentPage=page;
            }
            if(page==undefined||page==""){
                currentPage=1;
            }
            $("#currentPage").val(currentPage);
            searchPubProjInfo();
        }
        //添加
        function addProjInfo(projFlow){
            jboxOpen("<s:url value='/srm/ethical/addInfo?projFlow='/>"+projFlow+"&flag='y'","添加审查信息", 900, 400);
        }
        //查看
        function viewProjInfo(projFlow){
            jboxOpen("<s:url value='/srm/ethical/addInfo?projFlow='/>"+projFlow,"查看审查信息", 900, 400);
        }
        function searchPubProjInfo() {
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function searchProjCategory(){
            var dictFlow = $("select[name='projDeclarerFlow'] option:selected").attr("dictFlow");
            var dictTypeId = $("#projDeclarerFlow").val();
            var data = {
                dictFlow : dictFlow,
                dictTypeId : dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url , data , function(data){
                //清空原类别！
                $("select[name=projSecondSourceId] option[value != '']").remove();
                var dataObj = data;
                for(var i =0;i<dataObj.length;i++){
                    var cId =dataObj[i].dictId;
                    var cName =dataObj[i].dictName;
                    $option =$("<option></option>");
                    $option.attr("value",cId);
                    $option.text(cName);
                    if("${param.projSecondSourceId}" == cId){
                        $option.attr("selected",true);
                    }
                    $("select[name='projSecondSourceId']").append($option);
                }
            } , null , false);
        }

        $(document).ready(function(){
            searchProjCategory();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/ethical/list/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y"/>" method="post">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>

                    <%--项目编号：<input type="text" name="projNo" value="${param.projNo}" class="xltext" />--%>
                <div class="searchDiv">
                    项目名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext" />
                        </div>
                <div class="searchDiv">
                    一级来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect"  onchange="searchProjCategory();">
                       <option value="">请选择</option>
                       <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                       <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                       </c:forEach>
                    </select>
                    </div>
                <div class="searchDiv">
                    二级来源：
                        <select name="projSecondSourceId" class="xlselect">
                            <option value="">请选择</option>
                        </select>
</div>
                <div class="searchDiv">
                    <input type="button" value="查&#12288;询" onclick="searchPubProjInfo()" class="search">
                </div>
            </form>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th width="20%" >项目名称</th>
                <th width="10%" >一级来源</th>
                <th width="15%" >二级来源</th>
                <th width="10%" >承担科室</th>
                <th width="10%" >项目负责人</th>
                <th width="5%" >操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${projList}" var="proj" >
                <tr>
                    <td>${proj.projName}</td>
                    <td>${proj.projDeclarer}</td>
                    <td>${proj.projSecondSourceName}</td>
                    <td>${proj.applyOrgName}</td>
                    <td>${proj.applyUserName}</td>
                    <td>
                        <c:if test="${sessionScope.projListScope eq 'ethic'}">
                        <a href="javascript:void(0)" onclick="addProjInfo('${proj.projFlow}')">添加</a>|
                        </c:if>
                        <a href="javascript:void(0)" onclick="viewProjInfo('${proj.projFlow}')">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${projList == null || projList.size() == 0 }">
                <tr>
                    <td align="center" colspan="11">无记录</td>
                </tr>
            </c:if>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>