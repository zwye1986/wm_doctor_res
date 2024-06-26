<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_sortable" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function edit(dictFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/edit?dictFlow='/>"+dictFlow,"编辑", 500,300);
        }

        function add(){
            jboxOpen("<s:url value='/xjgl/partyBranch/edit?dictTypeId=${param.dictTypeId}'/>","新增", 500,300);
        }
        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function delDict(dictFlow,recordStatus){
            var msg = "";
            if(recordStatus=='${GlobalConstant.RECORD_STATUS_N}'){
                msg = "停用";
            }else if(recordStatus=='${GlobalConstant.RECORD_STATUS_Y}'){
                msg = "启用";
            }
            msg = "确认" + msg + "该记录吗？";
            var url = '<s:url value="/sys/dict/delete"/>?dictFlow='+dictFlow+"&recordStatus="+recordStatus;
            jboxGet(url, null , function() {
                window.parent.frames['mainIframe'].window.search();
                jboxClose();
            });
        }
        function refresh(){
            var url = "<s:url value='/xjgl/partyBranch/doRefresh'/>";
            jboxGet(url,null,function(resp){
                jboxTip(resp);
            });
        }
        var fixHelper = function(e, ui) {
            ui.children().each(function() {
                $(this).width($(this).width());
            });
            return ui;
        };
        $(function() {
            var oldPostData = "";
            $( "#sorttable" ).sortable({
                helper: fixHelper,
                create: function(e, ui){
                    var oldSortedIds = $( "#sorttable" ).sortable( "toArray" );
                    $.each(oldSortedIds,function(i,sortedId){
                        oldPostData = oldPostData+"&dictFlow="+sortedId;
                    });
                },
                start:function(e, ui){
                    //拖动时的行，要用ui.helper
                    ui.helper.css({"background":"#eee"});
                    return ui;
                },
                stop: function( event, ui ) {
                    ui.item.css({"background":"#fff"});
                    var sortedIds = $( "#sorttable" ).sortable( "toArray" );
                    var postdata = "";
                    $.each(sortedIds,function(i,sortedId){
                        postdata = postdata+"&dictFlow="+sortedId;
                    });
                    if(oldPostData==postdata){
                        return;
                    }
                    var url = "<s:url value='/sys/dict/saveorder'/>";
                    jboxPost(url, postdata, function(data) {
                    },null,true);
                    oldPostData = postdata;
                }
            });
            $( "#sorttable" ).disableSelection();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/xjgl/partyBranch/dictList" />" method="post" >
                <div class="choseDivNewStyle">
                    <%--<span></span>字典类型：--%>
                    <%--<select class="select" style="width:137px;" name="dictTypeId" onchange="search()">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach var="dictTypeEnum" items="${dictTypeEnumList}">--%>
                            <%--<c:if test="${dictTypeEnum.wsid eq sessionScope.currWsId}">--%>
                                <%--<option value="${dictTypeEnum.id}" <c:if test="${param.dictTypeId eq dictTypeEnum.id}">selected="selected"</c:if>>${dictTypeEnum.name}</option>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                    <span></span>党支部编码：
                    <input name="dictId" type="text" value="${param.dictId}"/>
                    <span style="padding-left:20px;"></span>党支部名称：
                    <input class="text" name="dictName" type="text" value="${param.dictName}"/>
                    <span style="padding-left:20px;"></span>
                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <input type="button" class="search" onclick="add();" value="新&#12288;增">
                    <div class="newStyleSubDiv">
                        <input type="button" class="search" onclick="refresh();" value="刷&#12288;新">
                        <span style="color:red;">党支部维护好后请刷新生效！</span>
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <th width="10%">党支部编码</th>
                <th width="30%">党支部名称</th>
                <th width="30%">描述</th>
                <th width="20%" >操作</th>
            </tr>
            <tbody id="sorttable">
            <c:forEach items="${dictList}" var="dict">
                <tr id="${dict.dictFlow}">
                    <td>${dict.dictId}</td>
                    <td>${dict.dictName}</td>
                    <td>${dict.dictDesc}</td>
                    <td>
                        <c:if test="${dict.dictIssys != GlobalConstant.RECORD_STATUS_Y }">
                            <c:if test="${dict.recordStatus == GlobalConstant.RECORD_STATUS_Y }">
                                [<a href="javascript:edit('${dict.dictFlow}');" >编辑</a>] | [<a href="javascript:delDict('${dict.dictFlow}','${GlobalConstant.RECORD_STATUS_N}');" >停用</a>]
                            </c:if>
                            <c:if test="${dict.recordStatus == GlobalConstant.RECORD_STATUS_N }">
                                [<a href="javascript:delDict('${dict.dictFlow}','${GlobalConstant.RECORD_STATUS_Y}');" >启用</a>]
                            </c:if>
                        </c:if>
                        <c:if test="${dict.dictIssys == GlobalConstant.RECORD_STATUS_Y }">
                            <span style="color:#999;">系统数据不允许操作</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${dictList == null || dictList.size()==0 }">
                <tr>
                    <td style="text-align:center;" colspan="4">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>