<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(function(){
            $("#detail").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        });
        function toPage(page){
            jboxStartLoading();
            if($("#pydwOrgName").val() != ""){
                $("#pydwOrgFlow").val($("#pydwOrgName").attr("flow"));
            }else{
                $("#pydwOrgFlow").val("");
            }
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function showInfo(recordFlow){
            var url ="<s:url value='/xjgl/abroadApply/showInfo?recordFlow='/>"+recordFlow+"&roleFlag=${param.roleFlag}";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"详情",650,350,false);
        }
        function returnInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/abroadApply/showEditSheet'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}&formType=${param.formType}","编辑",960,600);
        }


        function exportData(){
            var url = "<s:url value='/xjgl/abroadApply/exportInfos'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }

        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }

        $(function(){
            $("#pydwOrgName").likeSearchInit({});
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/abroadApply/abroadApplyList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <input type="hidden" name="formType" value="${param.formType}"/>
                姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" style="width: 137px;"/>&#12288;
                学&#12288;&#12288;号：<input type="text" name="stuNo" value="${param.stuNo}" style="width: 137px;"/>&#12288;
                培养单位：<input id="pydwOrgName" type="text" name="pydwOrgName" value="${param.pydwOrgName}" autocomplete="off" title="${param.pydwOrgName}" onmouseover="this.title = this.value" flow="${param.pydwOrgFlow}" style="width: 137px;"/>&#12288;
                <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:508px;">
                    <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                        <c:forEach items="${orgList}" var="org">
                            <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                        </c:forEach>
                    </div>
                </div>
                <input type="hidden" id="pydwOrgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                培养层次：<select style="width: 141px;" name="stuLevelId">
                    <option/>
                    <option value="master" ${param.stuLevelId eq 'master'?'selected':''}>硕士研究生</option>
                    <option value="doctor" ${param.stuLevelId eq 'doctor'?'selected':''}>博士研究生</option>
                </select><br/>
                专&#12288;&#12288;业：<select style="width: 141px;" name="majorId">
                    <option/>
                    <c:forEach items="${dictTypeEnumMajorList}" var="major">
                        <option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>[${major.dictId}]${major.dictName}</option>
                    </c:forEach>
                </select>&#12288;
                年&#12288;&#12288;级：<input type="text" name="period" value="${param.period}" style="width: 137px;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" />&#12288;
                出国类别：<select style="width:141px" name="goAbroadId">
                    <option value="">全部</option>
                    <c:forEach items="${abroadCategoryEnumList}" var="category">
                        <option value="${category.id}" <c:if test="${param.goAbroadId==category.id}">selected="selected"</c:if>>${category.name}</option>
                    </c:forEach>
                </select>&#12288;
                <span>开始时间：<input type="text" style="width:137px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginDate" value="${param.beginDate}" onchange="checkTime(this)"/><br/>
                    结束时间：<input type="text" style="width:137px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endDate" value="${param.endDate}" onchange="checkTime(this)"/></span>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" onclick="exportData();" value="导&#12288;出"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:80px;">入学年级</td>
                <td style="width:80px;">培养层次</td>
                <td style="width:80px;">学号</td>
                <td style="width:100px;">姓名</td>
                <td style="width:50px;">性别</td>
                <td style="width:120px;">出国起止时间</td>
                <td style="width:80px;">专业名称</td>
                <td style="width:120px;">培养单位</td>
                <td style="width:100px;">导师</td>
                <td style="width:100px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${info.period}</td>
                    <td>${info.stuLevelName}</td>
                    <td>${info.stuNo}</td>
                    <td>${info.userName}</td>
                    <td>${info.sexName}</td>
                    <td>${info.beginDate}~${info.endDate}</td>
                    <td>${info.majorName}</td>
                    <td>${info.pydwOrgName}</td>
                    <td>${info.tutorName}</td>
                    <td>
                        <c:if test="${param.formType ne 'return'}">
                            <a onclick="showInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                        <c:if test="${param.formType eq 'return'}">
                            <a onclick="returnInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </tr>
            </c:if>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="detail" style="background: white;">
</div>
</body>
</html>