<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style>
        .edit3{text-align: center;border:none;}
        .cont_list{
            background:none;
            background-color: #f8f8f8;
            border:1px solid #e3e3e3;
            border-bottom-style: none;
        }
        .cont_list .left .name{
            color: #333;
        }
        [type='text']{width:150px;height: 22px;}
        select{width: 153px;height: 27px;}
        a{color:#4195c5}
    </style>
    <script>
        function addFromTitle(){
            $("#addTitleButton").css("display","none");
            $("#titleParam").css("display","inline");
        }
        function saveFromTitle(titleId,index){
            if(false == $("#fromTitleForm").validationEngine("validate")){
                return false;
            }
            var data = "";
            if(titleId!=""){//修改名称
                //添加序列号
                var inx = parseInt(index)-1;
                var val = parseInt($(".bigOrder_"+inx).val())+1
                var orderNum = index==0?0:val;
                data = {
                    id : titleId,
                    orderNum : orderNum,
                    name : $("#assessType_"+titleId).val(),
                    typeName : $("#assessTitle_"+titleId).val()
                };
            }else {
                var data = $("#fromTitleForm").serialize();
                $("#saveTitleButton").attr("disabled",true);
            }
            var url = "<s:url value='/osca/formCfg/saveOscaFormTitle'/>?fromFlow=${from.fromFlow}";
            jboxPost(url, data, function(resp){
                        if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                            window.location.reload();
                        }
                    }, null, true);
            $("#addTitleButton").css("display","inline");
            $("#titleParam").css("display","none");
        }
        function cancelSaveFromTitle(){
            $("#addTitleButton").css("display","inline");
            $("#titleParam").css("display","none");
        }
        function addItem(titleId){
            $("#"+titleId+"Td").append($("#clone tr:eq(0)").clone());
            $addLastTr = $("#"+titleId+"Td").children("tr:last");
            $addLastTr.find("input[name='titleId']").val(titleId);
            $addLastTr.find("input[name='score']").attr("onblur","scoreCount('"+titleId+"')");
            $("#saveButton").show();
            $("#"+titleId+"noRecoredTd").hide();
            $addLastTr.children("td:last").children().attr("onclick","removeItemTr(this,'"+titleId+"')");
        }
        function removeItemTr(obj, titleId){
            $(obj).parent().parent().remove();
            var trs = $(".itemTd .addTr");
            if(trs.length<=0){
                $("#saveButton").hide();
            }
            scoreCount(titleId);
            var addTrs = $(".itemTd .addTr");
            if(trs.length<=0){
                $("#"+titleId+"noRecoredTd").show();
            }
        }
        function scoreCount(titleId){
            var trs = $("#"+titleId+"Td").children();
            var scoreCount = 0;
            $.each(trs, function(i,n){
                var score = $(n).find("input[name='score']").val();
                if (score == null || score == undefined || score == '' || isNaN(score)){
                    score = 0;
                }
                scoreCount += parseFloat(score);
            });
            $("#scoreCount_"+titleId).text(scoreCount);
        }
        $(document).ready(function(){
            <c:forEach items="${titleFormList}" var="title">
            scoreCount("${title.id}");
            </c:forEach>
        });
        function deleteTitle(id){
            jboxConfirm("确认删除？", function() {
                var fromFlow = $("input[name='fromFlow']").val();
                var url = "<s:url value='/osca/formCfg/deleteTitle'/>?cfgFlow="+fromFlow +"&id=" +id;
                jboxPost(url, null,function(resp){
                            if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
                                window.location.reload();
                            }
                        },
                        null,true);
            });
        }
        function modifyFromTitle(titleId){
            $("#assessTitle_"+titleId).prev().hide();
            $("#assessTitle_"+titleId).show();
            $("#assessType_"+titleId).prev().hide();
            $("#assessType_"+titleId).show();
        }
        function save(){
            if(false == $("#cfgForm").validationEngine("validate")){
                return false;
            }
//            alert($(".littleOrder").length);
            //给所有评分要素加一个排序码
            for(var i = 0;i < $(".littleOrder").length-1; i++) {
                var val = $(".littleOrder").eq(i).val();
                if(i==0&&(val==""||val==null)){
                    $(".littleOrder").eq(i).val(0);
                }
                if((val==""||val==null)&&i>0){
                    $(".littleOrder").eq(i).val(parseInt($(".littleOrder").eq(i-1).val())+1);
                }
            }
            var trs = $(".itemTd .addTr");
            if(trs.length<=0){
                jboxTip("请添加考核指标！");
                return false;
            }
            var datas = [];
            $.each(trs, function(i,n){
                var titleId = $(n).find("input[name='titleId']").val();
                var name = $(n).find("input[name='name']").val();
                var score = $(n).find("input[name='score']").val();
                var order = $(n).find("input[name='order']").val();
                var data={
                    "titleId":titleId,
                    "name":name,
                    "score":score,
                    "order":order
                };
                datas.push(data);
            });
            var fromFlow = $("input[name='fromFlow']").val();
            var requestData = {"itemFormList":datas, "fromFlow":fromFlow};
            var url = "<s:url value='/osca/formCfg/saveOscaFromItemList'/>";
            $("#saveButton").attr("disabled",true);
            jboxPostJson(
                    url,
                    JSON.stringify(requestData),
                    function(resp){
                        if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                            window.location.reload();
                        }
                    }, null, true);
        }
        function changeStyle(obj,stylename){
            $scoreTd = $(obj).parent().parent().prev();
            $score = $scoreTd.children();
            $name = $scoreTd.prev().children();
            $score.removeClass(stylename);
            $name.removeClass(stylename);
            $score.removeAttr("readonly");
            $name.removeAttr("readonly");
            $(obj).parent().hide();
            $(obj).parent().next().show();
        }
        function deleteItem(id){
            jboxConfirm("确认删除？", function() {
                var cfgFlow = $("input[name='fromFlow']").val();
                var url = "<s:url value='/osca/formCfg/deleteItem'/>?cfgFlow="+cfgFlow +"&id=" +id;
                jboxPost(url, null,
                        function(resp){
                            if("${GlobalConstant.DELETE_SUCCESSED}" == resp){
                                window.location.reload();
                            }
                        },
                        null,true);
            });
        }
        function modifyItem(obj, itemId, titleId){
            if(false == $("#assessCfgForm").validationEngine("validate")){
                return false;
            }
            $scoreTd = $(obj).parent().parent().prev();
            $score =$scoreTd.children();
            $name = $scoreTd.prev().children().eq(1);
            $score.addClass("edit3");
            $name.addClass("edit3");
            $score.attr("readonly","readonly");
            $name.attr("readonly","readonly");
            $(obj).parent().hide();
            $(obj).parent().prev().show();
            if($name.val()==$name.attr("oldvalue") && $score.val()==$score.attr("oldvalue")){
                jboxTip("没有修改！");
                return;
            }
            var cfgFlow = $("input[name='fromFlow']").val();
            var data = {
                cfgFlow:cfgFlow,
                id:itemId,
                name:$name.val(),
                score:$score.val()
            };
            jboxPost("<s:url value='/osca/formCfg/modifyItem'/>",data,
                    function(resp){
                        if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                            scoreCount(titleId);
                            $name.attr("oldvalue",$name.val());
                            $score.attr("oldvalue",$score.val());
                        }
                    }
                    ,null,true);
        }
        function saveFirst(param,index){
            if(false == $("#cfgForm").validationEngine("validate")){
                return false;
            }
            //给所有评分要素加一个排序码
            for(var i = 0;i < $(".littleOrder").length-1; i++) {
                var val = $(".littleOrder").eq(i).val();
                if(i==0&&(val==""||val==null)){
                    $(".littleOrder").eq(i).val(0);
                }
                if((val==""||val==null)&&i>0){
                    $(".littleOrder").eq(i).val(parseInt($(".littleOrder").eq(i-1).val())+1);
                }
            }
            var trs = $(".itemTd .addTr");
            if(trs.length<=0){
                modifyFromTitle(param);
                return false;
            }
            var datas = [];
            $.each(trs, function(i,n){
                var titleId = $(n).find("input[name='titleId']").val();
                var name = $(n).find("input[name='name']").val();
                var score = $(n).find("input[name='score']").val();
                var order = $(n).find("input[name='order']").val();
                var data={
                    "titleId":titleId,
                    "name":name,
                    "score":score,
                    "order":order
                };
                datas.push(data);
            });
            var fromFlow = $("input[name='fromFlow']").val();
            var requestData = {"itemFormList":datas, "fromFlow":fromFlow};
            var url = "<s:url value='/osca/formCfg/saveOscaFromItemList'/>";
            $("#saveButton").attr("disabled",true);
            jboxPostJson(
                    url,
                    JSON.stringify(requestData),
                    function(resp){
                        if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                            window.location.reload();
                        }
                    }, null, true);
        }
        function expertFrom(flow) {
            var url='<s:url value="/osca/provincial/exportFrom?fromFlow="/>'+flow;
            jboxTip("导出中…………");
            jboxExp($("#searchForm"), url);
        }
    </script>
</head>
<body style="overflow: auto">
    <div>
    <c:if test="${flag eq 'edit'}">
    <form id="fromTitleForm">
        <input type="hidden" name="id" id="titleId"/>
        <input class="search" type="button" id="addTitleButton" value="添加评分项目" onclick="addFromTitle('');"/>
            <span id="titleParam" style="display:none;">
				评分项目：
				<input type="text" name="name" id="titleName" class="validate[required]" />
                子项目个数：
                <select name="titleSum" id="titleSum" class="xlselect">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                    <option>7</option>
                    <option>8</option>
                    <option>9</option>
                    <option>10</option>
                </select>
                <input class="search" type="button" id="saveTitleButton" value="保&#12288;存" onclick="saveFromTitle('','');"/>
                <input class="search" type="button" id="cancelSaveTitleButton" value="取&#12288;消" onclick="cancelSaveFromTitle();"/>
            </span>
    </form>
    </c:if>
    <c:if test="${flag eq 'read'}">
        <input class="search" type="button"  value="导&#12288;出" onclick="expertFrom('${from.fromFlow}');"/>
    </c:if>
    </div>

    <form id="cfgForm" method="post" style="position: relative;margin-top: 10px;">
        <input type="hidden" name="fromFlow" value="${from.fromFlow}"/>
        <!-- 标题 -->
        <c:forEach items="${titleFormList}" var="title" varStatus="s">
            <div class="cont_list" style="margin-top: 15px;">
                <div class="left" style="width: 880px;cursor: auto">
                            <input id="assessOrder_${title.id}" class="bigOrder_${s.index}" type="hidden" value="${title.orderNum}"/>
                    评分项目：
						<span class="name" style="width: 350px;">
						<font>${title.name}</font>
						<input id="assessType_${title.id}" type="text" class="validate[required]" style="display: none;" onblur="saveFromTitle('${title.id}','${s.index-1}');" value="${title.name}"/>
						</span>
                    评分子项目：
						<span class="name" style="width: 300px;">
						<font>${title.typeName}</font>
						<input id="assessTitle_${title.id}" type="text" class="validate[required]" style="display: none;" onblur="saveFromTitle('${title.id}','${s.index}');" value="${title.typeName}"/>
						</span>
                    总&#12288;分：<font id="scoreCount_${title.id}"></font>
                </div>
                <c:if test="${flag eq 'edit'}">
                <div class="right" style="width: 100px;float:right;padding-right: 15px;">
                    <a style="cursor: pointer;" onclick="addItem('${title.id}')"><img alt="新增" title="新增" src="<s:url value="/css/skin/Blue/images/add.png"/>"/></a>&nbsp;&nbsp;
                    <a style="cursor: pointer;" onclick="saveFirst('${title.id}');"><img alt="修改" title="修改" src="<s:url value="/css/skin/Blue/images/modify.png"/>"/></a>&nbsp;
                    <a style="cursor: pointer;" onclick="deleteTitle('${title.id}')"><img alt="删除" title="删除" src="<s:url value="/css/skin/Blue/images/del2.png"/>"></a>
                </div>
                </c:if>
            </div>
            <div style="padding-top: 0px;">
                <table class="xllist"  cellpadding="0" cellspacing="0" style="width: 100%">
                    <colgroup>
                        <col width="80%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                    <tr>
                        <th style="text-align: center;">评分要素</th>
                        <th style="text-align: center;">分数</th>
                        <th style="text-align: center;">操作</th>
                    </tr>
                    <!-- item -->
                    <tbody id="${title.id}Td" class="itemTd">
                    <c:forEach items="${title.itemList}" var="item" varStatus="status">
                        <tr>
                            <td>
                                <input type="hidden" class="littleOrder" name="order" value="${item.order}"/>
                                <input type="text" class="validate[required] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.name}" value="${item.name}" readonly="readonly"/>
                            </td>

                            <td>
                                <input type="text" name="score" class="validate[required,custom[xiaoshu1]] edit3" style="text-align: left;width: 90%;text-align: center;" oldvalue="${item.score}" value="${item.score}" readonly="readonly"/>
                            </td>

                            <td>
                                <c:if test="${flag eq 'edit'}">
                                <span>[<a href="javascript:void(0)" onclick="changeStyle(this,'edit3');">编辑</a>]</span>
                                <span style="display: none;">[<a href="javascript:void(0)" onclick="modifyItem(this,'${item.id}','${title.id}')">保存</a>]</span>
                                | [<a href="javascript:void(0)" onclick="deleteItem('${item.id}')">删除</a>]
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <c:if test="${empty title.itemList}">
                        <tr  id="${title.id}noRecoredTd">
                            <td colspan="3">无记录！</td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </c:forEach>
    </form>
    <c:if test="${empty titleFormList}">
        <table width="100%" class="basic">
            <tr>
                <th style="text-align: center;">无记录！</th>
            </tr>
        </table>
    </c:if>
    <c:if test="${flag eq 'edit'}">
    <div class="button" style="width: 100%;">
        <input class="search" type="button" id="saveButton" style="display: none;" value="保&#12288;存" onclick="save();" />
    </div>
    </c:if>

    <table style="display: none;" id="clone">
        <tr class="addTr">
            <td>
                <input type="hidden" name="titleId"/>
                <input type="hidden" class="littleOrder" name="order" />
                <input type="text" name="name" class="validate[required]" style="width:90%;"/>
            </td>
            <td><input type="text" name="score" class="validate[required,custom[xiaoshu1]]" style="width:90%;"/></td>
            <td>
                [<a href="javascript:void(0)" >删除</a>]
            </td>
        </tr>
    </table>
</body>
</html>
