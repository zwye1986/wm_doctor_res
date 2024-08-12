<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
    </jsp:include>
    <style type="text/css">
        .grid{ width:100%; text-align:center; border:1px solid #e7e7eb; color:#353535; border-collapse:collapse;font-size: 14px }
        .grid th{ background:#f4f5f9; color:#222222;border-bottom:1px solid #e7e7eb;}
        .grid th,.grid td{ line-height:26px; height:40px; padding:0 5px;}
        .grid td{border-bottom:1px solid #e7e7eb;}
        .grid td a{color:#3AA8E3;}
        .grid td a:hover{text-decoration:underline;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/Scoll/Scorll.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function(){
//            var style={"width":"100%","height":"auto","margin-top":"10px","margin-bottom":"10px","margin-left":"8px"};
//            var options ={
//                "colums":3//根据固定列的数量
//            };
//            $("#myTable").Scorll(options,style,false,null);

            $("#tableDiv").scroll(function(){//给table外面的div滚动事件绑定一个函数
                var left=$("#tableDiv").scrollLeft();//获取滚动的距离
                var trs=$("#tableDiv table tr[name='titleTr']");//获取表格的所有tr
                trs.each(function(i){//对每一个tr（每一行）进行处理
                    //获得每一行下面的所有的td，然后选中下标为0的，即第一列，设置position为相对定位
                    //相对于父div左边的距离为滑动的距离，然后设置个背景颜色，覆盖住后面几列数据滑动到第一列下面的情况
                    //如果有必要也可以设置一个z-index属性
                    if($(this).attr("name")){
                        $(this).children().eq(0).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
                        $(this).children().eq(1).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
                        $(this).children().eq(2).css({"position":"relative","top":"0px","left":left,"background-color":"white"});
                    }
                });
            });
        });

        function delSkills(skillFlow){
            jboxConfirm("是否确认删除？", function(){
                var url = "<s:url value='/lcjn/base/delSkillsConfig?skillFlow='/>"+skillFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, false);
            });
        }
        function addSkills(skillFlow){
            var title = skillFlow == ""?"新增":"编辑";
            var url = "<s:url value='/lcjn/base/addSkillOption?skillFlow='/>"+skillFlow;
            jboxOpen(url, title,800,400,false);
        }

        function toPage(page){
            location.href = "<s:url value='/lcjn/base/skillsConfigList?currentPage='/>"+page;
        }
    </script>
</head>
<body id="initCont">
<div class="mainright">
    <div class="content"><br/>
        <form id="searchForm">
            <span style="margin-left:-8px;"></span>
            <input class="search" value="新&#12288;增" onclick="addSkills('')" type="button">
        </form><br/>
        <div id="tableDiv" style="min-width:999px;overflow-x:auto;">
        <c:if test="${not empty dataList}">
            <table id="myTable" class="xllist" >
                <thead>
                <tr name="titleTr">
                    <th style="min-width:40px;" class="toFiexdDept" rowspan="2" >序号</th>
                    <th style="min-width:120px;" class="toFiexdDept" rowspan="2" >技能名称</th>
                    <th style="min-width:100px;" class="toFiexdDept" rowspan="2" >操作</th>
                    <c:set var="count" value="0" />
                    <c:forEach items="${materialLst}" var="dict"><c:if test="${dict.dictName ne '其他耗材'}"><c:set var="count" value="${count+1}" /></c:if></c:forEach>
                    <th colspan="${count}" >耗材（单价*数量）</th>
                    <th colspan="${fn:length(fixedAssetsLst)}" >固定资产（数量）</th>
                </tr>
                <tr>
                    <c:forEach items="${materialLst}" var="dict"><c:if test="${dict.dictName ne '其他耗材'}"><th style="min-width:50px">${dict.dictName}</th></c:if></c:forEach>
                    <c:if test="${count eq 0}"><th></th></c:if>
                    <c:forEach items="${fixedAssetsLst}" var="dict"><th style="min-width:50px" >${dict.dictName}</th></c:forEach>
                    <c:if test="${fn:length(fixedAssetsLst) eq 0}"><th></th></c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dataList}" var="info" varStatus="i">
                    <tr class="fixTrTd" name="titleTr">
                        <td class="by">${i.index + 1}</td>
                        <td class="by" style="line-height: 150%;">${info.SKILL_NAME}</td>
                        <td class="by"><a onclick="addSkills('${info.SKILL_FLOW}');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
                            <a onclick="delSkills('${info.SKILL_FLOW}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
                        </td>
                        <c:forEach items="${materialLst}" var="dict">
                            <c:if test="${dict.dictName ne '其他耗材'}">
                                <c:set var="exitFlag" value="N"/>
                                <c:forEach items="${fn:split(info.SKILLS_MATERIA_LIST,',')}" var="material" varStatus="i">
                                    <c:if test="${material eq dict.dictName}"><c:set var="exitFlag" value="Y" /><td class="by">${fn:split(info.MATERIA_PRICE_LIST,',')[i.index]}</td></c:if>
                                </c:forEach>
                                <c:if test="${exitFlag eq 'N'}"><td class="by"></td></c:if>
                            </c:if>
                        </c:forEach>
                        <c:if test="${count eq 0}"><td></td></c:if>
                        <c:forEach items="${fixedAssetsLst}" var="dict">
                            <c:set var="exitFlag" value="N"/>
                            <c:forEach items="${fn:split(info.SKILLS_MATERIA_LIST,',')}" var="material" varStatus="i">
                                <c:if test="${material eq dict.dictName}"><c:set var="exitFlag" value="Y" /><td class="by">${fn:split(fn:split(info.MATERIA_PRICE_LIST,',')[i.index],'*')[1]}</td></c:if>
                            </c:forEach>
                            <c:if test="${exitFlag eq 'N'}"><td class="by"></td></c:if>
                        </c:forEach>
                        <c:if test="${fn:length(fixedAssetsLst) eq 0}"><td></td></c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        </div>
        <c:if test="${empty dataList}">
            <br/>
            <table class="xllist" style="width:100%;margin-left: 8px;">
                <tr>
                    <th>序号</th>
                    <th>技能名称</th>
                    <th>操作</th>
                    <th>耗材（单价*数量）</th>
                    <th>固定资产（数量）</th>
                </tr>
                <tr><td colspan="99">暂无记录</td></tr>
            </table>
        </c:if>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>