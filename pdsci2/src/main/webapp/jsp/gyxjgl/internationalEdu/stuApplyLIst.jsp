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
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function addInfo(){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/editAbroadApply'/>?roleFlag=${param.roleFlag}","编辑",960,600);
        }
        function editInfo(recordFlow){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/editAbroadApply'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}","编辑",960,600);
        }
        function upFile(recordFlow,type){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/showUploadFile'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}&type="+type,"编辑",500,180);
        }
        function editTrip(recordFlow){
            var url ="<s:url value='/gyxjgl/abroadApply/showTripInfo?recordFlow='/>"+recordFlow+"&roleFlag=${param.roleFlag}";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"编辑",960,600,false);
        }
        function editCost(recordFlow){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/showEditSheet'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}&formType=${param.formType}","编辑",900,500);
        }
        function editReturn(recordFlow){
            jboxOpen("<s:url value='/gyxjgl/abroadApply/showEditSheet'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}&formType=${param.formType}","编辑",960,600);
        }
        function delTrip(recordFlow){
            var url = "<s:url value='/gyxjgl/abroadApply/delTrip'/>?recordFlow="+recordFlow;
            jboxConfirm("确定删除该行程记录？",function(){
                jboxPost(url,null,function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        toPage(1);
                    }
                },null,true);
            },null);
        }
        function delSheet(recordFlow,sheetType){
            var url = "<s:url value='/gyxjgl/abroadApply/delSheetInfo'/>?recordFlow="+recordFlow+"&sheetType="+sheetType;
            jboxConfirm("确定删除该登记信息？",function(){
                jboxPost(url,null,function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        toPage(1);
                    }
                },null,true);
            },null);
        }

        function delInfo(recordFlow){
            var url = "<s:url value='/gyxjgl/abroadApply/delAbroadApply'/>?recordFlow="+recordFlow;
            jboxConfirm("确定删除该记录？",function(){
                jboxPost(url,null,function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        toPage(1);
                    }
                },null,true);
            },null);
        }

        function exportData(){
            var url = "<s:url value='/gyxjgl/abroadApply/exportInfos'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/abroadApply/abroadApplyList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <input type="hidden" name="formType" value="${param.formType}"/>
                起止时间：<span><input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginDate" value="${param.beginDate}" onchange="checkTime(this)"/>
                 -- <input type="text" style="width:100px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endDate" value="${param.endDate}" onchange="checkTime(this)"/></span>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <c:if test="${empty param.formType}">
                    <input type="button" class="search" onclick="addInfo();" value="新&#12288;增"/>
                </c:if>
                <input type="button" class="search" onclick="exportData();" value="导&#12288;出"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:60px;">入学年级</td>
                <td style="width:80px;">培养层次</td>
                <td style="width:80px;">学号</td>
                <td style="width:100px;">姓名</td>
                <td style="width:50px;">性别</td>
                <td style="width:120px;">出国起止时间</td>
                <td style="width:100px;">专业名称</td>
                <td style="width:120px;">二级机构</td>
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
                        <c:choose>
                            <c:when test="${param.formType eq 'upFile'}">
                                <a onclick="upFile('${info.recordFlow}');" style="cursor:pointer;color:blue;">上传</a>
                            </c:when>
                            <c:when test="${param.formType eq 'trip'}">
                                <a onclick="editTrip('${info.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                                <a onclick="delTrip('${info.recordFlow}');" style="cursor:pointer;color:blue;">删除</a>
                            </c:when>
                            <c:when test="${param.formType eq 'cost'}">
                                <a onclick="editCost('${info.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                                <a onclick="delSheet('${info.recordFlow}','costSheet');" style="cursor:pointer;color:blue;">删除</a>
                            </c:when>
                            <c:when test="${param.formType eq 'return'}">
                                <a onclick="editReturn('${info.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                                <a onclick="delSheet('${info.recordFlow}','returnSheet');" style="cursor:pointer;color:blue;">删除</a>
                            </c:when>
                            <c:when test="${param.formType eq 'file'}">
                                <a onclick="upFile('${info.recordFlow}','proj');" style="cursor:pointer;color:blue;">上传</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="editInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                                <a onclick="delInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">删除</a>
                            </c:otherwise>
                        </c:choose>
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