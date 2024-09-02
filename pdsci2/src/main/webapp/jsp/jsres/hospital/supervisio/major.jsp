<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
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
<style type="text/css">
    .noteItems p {
        white-space: nowrap; /* 强制在同一行内显示所有文本，直到文本结束或者遭遇 br 对象。不换行 */
        overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
        text-overflow: ellipsis; /* IE 专有属性，当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
    }

    .filesTable td{
        text-align: center;
        padding-left: 0px;
    }

    .btn-box input{
        margin-left: 10px;
    }


    #tableContext table {
        border-collapse: collapse;
        border: 1px solid #D3D3D3;
    }

    #tableContext table td {
        border-top: 0;
        border-right: 1px solid #D3D3D3;
        border-bottom: 1px solid #D3D3D3;
        border-left: 0;
        padding-left: 4px;
        padding-right: 2px;
    }

    #tableContext table th {
        border-top: 0;
        border-right: 1px solid #D3D3D3;
        border-bottom: 1px solid #D3D3D3;
        border-left: 0;
    }
    .input{height: 20px;line-height: 20px;padding: 4px 2px; margin:0 5px;vertical-align: middle;
        border: 1px solid #e7e7eb;box-shadow: none;-moz-box-shadow: none;-webkit-box-shadow: none;
        border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;
        font-size: 14px; font-family: "Microsoft YaHei";
    }

</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    function exportReport(subjectActivitiFlows,type) {
        var url ="";
        if(type == 'spe'){
            url ="<s:url value='/jsres/supervisio/exportReport'/>?subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
        }
        if(type == 'major'){
            url ="<s:url value='/jsres/supervisio/exportReport'/>?&subjectFlow="+subjectActivitiFlows+"&type="+type;
        }
        top.jboxTip("导出中…………");
        top.jboxExp(null,url);
        top.jboxEndLoading();
    }

    function shouInfo(recordFlow,obj) {
        var aList = $("a");
        var ddList = $("dd");
        for (var i = 0; i < aList.length; i++) {
            if (aList[i].getAttribute("itemId") == "item"){
                $(aList[i]).css("color","black");
            }
        }
        for (var i = 0; i < ddList.length; i++) {
            $(ddList[i]).css("background-color","white");
        }
        $(obj).parent().css("background-color","#54B2E5");
        $(obj).css("color","white");


        if (recordFlow=='8'){
            $("#contentMas").hide();
            $("#msgSave").hide();
            $("#biaoInfo").show();
            $("#msgSave1").hide();
            $("#msgSave2").hide();
            $("#tdinfo").hide();
            var geturl="<s:url value='/jsres/supervisio/getContent'/>?recordFlow="+recordFlow+"&subjectActivitiFlows=${subjectActivitiFlows}&subjectFlow=${subjectFlow}&san=Y&roleFlag=${roleFlag}";
            $.ajax({
                type : "get",
                url : geturl,
                cache : false,
                success : function(resp) {
                    var itemIdList = $("input");
                    var info = JSON.parse(resp);
                    for (let x in info) {
                        for (var i = 0; i <itemIdList.length; i++) {
                            if ($(itemIdList[i]).attr('id')==x){
                                $(itemIdList[i]).val(info[x]);
                            }
                        }
                    }
                }
            });
            return;
        }
        $("#tdinfo").show()
        $("#biaoInfo").hide();
        $("#msgSave").show();
        $("#contentMas").show();
        if (recordFlow !="1"){
            $("#msgSave1").show();
        }
        if (recordFlow =="1"){
            $("#msgSave1").hide();
        }
        if (recordFlow=='S'){
            recordFlow=$("#recodeFlow1").val();
            recordFlow=Number(recordFlow)-1;
        }
        if (recordFlow=='X'){
            recordFlow=$("#recodeFlow1").val();
            recordFlow=Number(recordFlow)+1;

        }
        if (recordFlow=="8"){
            $("#msgSave2").hide();
        }
        if (recordFlow!="8"){
            $("#msgSave2").show();
        }
        var geturl="<s:url value='/jsres/supervisio/getContent'/>?recordFlow="+recordFlow+"&subjectActivitiFlows=${subjectActivitiFlows}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}";
        $.ajax({
            type : "get",
            url : geturl,
            cache : false,
            success : function(resp) {
                if (recordFlow=='8'){
                    $("#recodeFlow1").val(8);
                    $("#contentMas").hide();
                    $("#msgSave").hide();
                    $("#biaoInfo").show();
                    $("#msgSave1").hide();
                    $("#msgSave2").hide();
                    $("#tdinfo").hide();
                    for (var i = 0; i < aList.length; i++) {
                        if (aList[i].getAttribute("recordId") == recordFlow){
                            $(aList[i]).parent().css("background-color","#54B2E5");
                            $(aList[i]).css("color","white");
                            return;
                        }
                    }
                    var itemIdList = $("input");
                    var info = JSON.parse(resp);
                    for (let x in info) {
                        for (var i = 0; i <itemIdList.length; i++) {
                            if ($(itemIdList[i]).attr('id')==x){
                                $(itemIdList[i]).val(info[x]);
                            }
                        }
                    }
                }

                var info = JSON.parse(resp);
                $("#contentMas").html("");
                $("#contentMas").html();
                $("#contentMas").html(info[1]);
                $("#contentMas").val(info[1]);
                $("#recodeFlow1").val(info[0]);
                if (recordFlow =="1"){
                    $("#msgSave1").hide();
                }
                for (var i = 0; i < aList.length; i++) {
                    if (aList[i].getAttribute("recordId") == recordFlow){
                        $(aList[i]).parent().css("background-color","#54B2E5");
                        $(aList[i]).css("color","white");
                        return;
                    }
                }
            },
            error : function() {
            },
        });
    }

    function saveInfo() {
        var contentMas=$("#contentMas").val();
        var recordFlow=$("#recodeFlow1").val();
        top.jboxPost("<s:url value='/jsres/supervisio/saveReport'/>?subjectActivitiFlows=${subjectActivitiFlows}&subjectFlow=${subjectFlow}", $("#myForm").serialize(), function (resp) {
            if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                shouInfo(recordFlow);
            }
        });
    }
    $(document).ready(function(){
        $('.menu_item a:first').click();
        $("#biaoInfo").hide();
        $("#msgSave1").hide();
        var itemIdList = $("input");
        if (${"local" ne roleFlag or isRead eq 'Y'}){
            document.getElementById("contentMas").readOnly = true;
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "score") {
                    itemIdList[i].readOnly = "true";
                }
            }
        }
    });
    function ZanSaveScore() {
        top.jboxTip("保存成功！");
        top.jboxClose();
    }
    function saveScore(obj) {
        var itemId = $(obj).attr('id');
        var contentMas=obj.value;
        var reg =  /^(\d+|\d+\.\d{1,2})$/;
        if (reg.test(contentMas)) {
            var url = "<s:url value='/jsres/supervisio/saveReportContentMas'/>";
            var data = {
                "itemId": itemId,
                "subjectFlow": '${subjectFlow}',
                "subjectActivitiFlows":'${subjectActivitiFlows}',
                "contentMas":contentMas,
                "type":"major"
            };
            top.jboxPost(url, data, function (resp) {
                top.jboxTip(resp);
            }, null, false);
        }else {
        top.jboxTip("只能输入数字且带有两位小数!");
        }
    }

    function subReport() {
        var url = "<s:url value='/jsres/supervisio/subReport'/>";
        var data = {
            "subjectActivitiFlows": '${subjectActivitiFlows}',
            "sub":'Y'
        };
        top.jboxPost(url, data, function (resp) {
            top.jboxTip(resp);
            top.jboxClose();
        }, null, false);
    }
</script>

<div class="mainright">
    <div class="content">
        <div style="width: 100%;">
            <div style="width: 20%;margin-bottom: 40px; float: left;">
                <div style="height: 450px;width: 100%;margin-bottom: 20px;overflow:auto;">
                    <dl class="menu menu_first noteItems">
                        <dt class="menu_title " style="color: #8d8d8d">
                            <i class="icon_menu menu_management" style="line-height: 34px;"></i>第一部分
                        </dt>
                        <c:forEach items="${reportList}" var="report">
                        <c:set var="statusColor" value="#00CC33"/>
                            <dd class="menu_item" style="line-height: 34px;width: 170px;margin-left: 0px;">
                                <a itemId="item" class="select" style="margin-left: 35px"recordId="${report.recordFlow}" onclick="shouInfo('${report.recordFlow}',this)">${report.contentTitle}</a>
                            </dd>
                        </c:forEach>
                    </dl>
                    <dl class="menu menu_first noteItems">
                        <dt class="menu_title" style="color: #8d8d8d">
                            <i class="icon_menu menu_management" style="line-height: 34px"></i>第二部分
                        </dt>
                        <c:forEach items="${reportList2}" var="report2">
                            <c:set var="statusColor" value="#00CC33"/>
                            <dd class="menu_item" style="line-height: 34px;width: 170px;margin-left: 0px;">
                                <a itemId="item" style="margin-left: 35px" recordId="${report2.recordFlow}"  onclick="shouInfo('${report2.recordFlow}',this)">${report2.contentTitle}</a>
                            </dd>
                        </c:forEach>
                    </dl>
                    <dl class="menu menu_first noteItems">
                        <dt class="menu_title" style="color: #8d8d8d">
                            <i class="icon_menu menu_management" style="line-height: 34px"></i>第三部分
                        </dt>
                        <c:forEach items="${reportList3}" var="report3">
                            <c:set var="statusColor" value="#00CC33"/>
                            <dd class="menu_item" style="line-height: 34px;width: 170px;margin-left: 0px;">
                                <a itemId="item"  style="margin-left: 35px" recordId="7"  onclick="shouInfo('${report3.recordFlow}',this)">${report3.contentTitle}</a></dd>
                        </c:forEach>
                    </dl>
                    <dl class="menu menu_first noteItems">
                        <dt class="menu_title" style="color: #8d8d8d">
                            <i class="icon_menu menu_management" style="line-height: 34px"></i>附件
                        </dt>
                        <dd class="menu_item" style="line-height: 34px;width: 170px;margin-left: 0px;">
                            <a  itemId="item" style="margin-left: 35px" recordId="8"  onclick="shouInfo('8',this)">相关重要数据</a>
                        </dd>
                    </dl>

                </div>
            </div>
            <div id="textContext" style="width: 65%;margin-left: 20px;margin-bottom: 40px; float: left;">
                    <div   style="text-align: right;margin-bottom: 10px">
                        <input class="btn_green" type="button" value="导出Word文件"
                               style="background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="exportReport('${subjectActivitiFlows}','major');" />
                    </div>

                <div style="height: 760px;width: 119%;padding-bottom: 20px;overflow:auto;">
                    <form action="" id="myForm" style="height: 0px">
                        <div id="noteInfos" style="margin-bottom: 20px">
                            <table class="basic" width="90%" style="margin-left: 30px;border-collapse: collapse;border: 1px solid #D3D3D3">
                                <tr>
                                    <td colspan="10" id="tdinfo">
                                        <textarea id="contentMas"  name="contentMas" style="height: 600px;white-space:pre-line;margin-left: -30px;width: 757px;font-size: 15px"></textarea>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <input id="recodeFlow1" name="recordFlow" type="hidden">
                        <input class="btn_green" type="button" value="上一步" id="msgSave1"
                               style="background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="shouInfo('S');"/>&#12288;
                        <input class="btn_green" type="button" value="下一步" id="msgSave2"
                               style="background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="shouInfo('X');"/>&#12288;
                        <c:if test="${roleFlag eq  'local' && isRead ne 'Y'}">
                            <input class="btn_green" type="button" value="保&#12288;存" id="msgSave"
                                   style="background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                                   onclick="saveInfo();"/>&#12288;
                        </c:if>
<%--                        <a class="btn" id="msgSave" style="width: 56px;margin-left: 500px" href="javascript:void(0);" onclick="saveInfo();">保存</a>--%>
                    </form>

                    <div id="biaoInfo">
<%--                        <div>附件：相关重要数据</div>--%>
                        <table  border="0" cellpadding="0" cellspacing="0"  class="grid" style="width: 760px" >
                            <tr style="height:35px;"  >
                                <th style="width: 350px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >指标</th>
                                <th style="width: 125px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >2019年</th>
                                <th style="width: 125px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >2020年</th>
                                <th style="width: 125px;height: 35px; background: #f4f5f9;border-bottom: 1px solid #D3D3D3" >2021年</th>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >门急诊量</td>
                                <td style="width: 125px;height: 35px;border-bottom: 1px solid #D3D3D3" ><input type="text" class="input" name="score" style="width: 100px;" id="nmzl2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="nmzl2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input class="input" type="text"  name="score" style="width: 100px" id="nmzl2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住院量</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zyll2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zyl2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zyl2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >实际床位数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="sjcws2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="sjcws020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="sjcws2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >技能中心面积</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="jnzxmj2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  name="score" class="input" style="width: 100px" id="jnzxmj2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="jnzxmj2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >专职管理人员人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zzglryrs2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input"  name="score"style="width: 100px" id="zzglryrs2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zzglryrs2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >招生人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input"  name="score"style="width: 100px" id="zsrs2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zsrs2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zsrs2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >招生计划完成率</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zsjhwcl2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zsjhwcl2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zsjhwcl2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >招收外单位委派的培训对象和面向社会招收的培训对象占比</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"   name="score"style="width: 100px" id="zswdwwp2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zswdwwp2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zswdwwp2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >参加省级及以上的师资培训人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="cjsjjys2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="cjsjjys2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="cjsjjys2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住培结业考试首考人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zpjyks2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zpjyks2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zpjyks2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住培结业考试首考通过人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zpjykssk2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zpjykssk2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="zpjykssk2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住培结业考试首考通过率</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zpjykstgl2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="zpjykstgl2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text"  class="input"  name="score"style="width: 100px" id="zpjykstgl2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住院医师首次参加执医考试人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="ksrs2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input"  name="score" style="width: 100px" id="ksrs2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="ksrs2021" onchange="saveScore(this);"> </td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住院医师首次参加执医考试通过人数</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input"  name="score"style="width: 100px" id="tgrs2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"class="input"   name="score" style="width: 100px" id="tgrs2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="tgrs2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住院医师首次参加执医考试通过率</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text"  class="input" name="score" style="width: 100px" id="tgl2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input" name="score" style="width: 100px" id="tgl2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input" name="score" style="width: 100px" id="tgl2021" onchange="saveScore(this);"></td>
                            </tr>
                            <tr style="height:35px;">
                                <td style="width: 350px;height: 35px;text-align: left;border-bottom: 1px solid #D3D3D3;border-left: 1px solid #D3D3D3" >住院医师参加年度业务水平测试全国排名</td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input" name="score" style="width: 100px" id="pm2019" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3" ><input type="text" class="input" name="score" style="width: 100px" id="pm2020" onchange="saveScore(this);"></td>
                                <td style="width: 125px;height: 35px; border-bottom: 1px solid #D3D3D3;border-right: 1px solid #D3D3D3" ><input type="text" class="input" name="score" style="width: 100px" id="pm2021" onchange="saveScore(this);"></td>
                            </tr>
                        </table>
                        <div style="margin-top: 10px">
                            <div style="margin-top: 25px"><font color="red" style="font-weight: bold">注：请注意提供数据真实、准确，评估组将与省毕教办数据对比。</font></div>
                        </div>
                        <input class="btn_green" type="button" value="上一步" id="msgSave3"
                               style="background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="shouInfo('S');"/>&#12288;
                        <c:if test="${roleFlag eq 'local' && isRead ne 'Y'}">
                            <input class="btn_green" type="button" value="保&#12288;存" id="msgSave"
                                   style="margin-top: 15px;background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                                   onclick="ZanSaveScore();"/>&#12288;
                            <input class="btn_green" type="button" value="提&#12288;交"
                                   style="margin-top: 15px;background-color: #54B2E5;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                                   onclick="subReport();"/>&#12288;
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>