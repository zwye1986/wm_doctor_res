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
        var url ="<s:url value='/jsres/supervisio/exportReport'/>?subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
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
        $(obj).parent().css("background-color","#44b549");
        $(obj).css("color","white");

        $("#tdinfo").show()
        $("#biaoInfo").hide();
        $("#msgSave").show();
        $("#contentMas").show();
        if (recordFlow !="18"){
            $("#msgSave1").show();
        }
        if (recordFlow =="11"){
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
        if (recordFlow=="17"){
            $("#msgSave2").hide();
        }
        if (recordFlow!="17"){
            $("#msgSave2").show();
        }

        var geturl="<s:url value='/jsres/supervisio/getContent'/>?recordFlow="+recordFlow+"&subjectActivitiFlows=${subjectActivitiFlows}&subjectFlow=${subjectFlow}&roleFlag=${roleFlag}";
        $.ajax({
            type : "get",
            url : geturl,
            cache : false,
            success : function(resp) {
                var info = JSON.parse(resp);
                $("#contentMas").html("");
                $("#contentMas").html();
                $("#contentMas").html(info[1]);
                $("#contentMas").val(info[1]);
                $("#recodeFlow1").val(info[0]);
                if (recordFlow =="11"){
                    $("#msgSave1").hide();
                }
                if (recordFlow!='17'){
                    $("#tijiao").hide();
                }else {
                    $("#tijiao").show();
                }
                for (var i = 0; i < aList.length; i++) {
                    if (aList[i].getAttribute("recordId") == recordFlow){
                        $(aList[i]).parent().css("background-color","#44b549");
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
        if (${"management" ne roleFlag or isRead eq 'Y'}){
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
            "sub":'Y',
            "roleFlag":'management'
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
                                <a itemId="item" class="select" style="margin-left: 35px" recordId="${report.recordFlow}" onclick="shouInfo('${report.recordFlow}',this)">${report.contentTitle}</a>
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
                                <a itemId="item"  style="margin-left: 35px" recordId="${report3.recordFlow}"  onclick="shouInfo('${report3.recordFlow}',this)">${report3.contentTitle}</a></dd>
                        </c:forEach>
                    </dl>
                </div>
            </div>
            <div id="textContext" style="width: 65%;margin-left: 20px;margin-bottom: 40px; float: left;">
                    <div   style="text-align: right;margin-bottom: 10px">
                        <input class="btn_green" type="button" value="导出Word文件"
                               style="background-color: #44B549;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="exportReport('${subjectActivitiFlows}','management');" />
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
                               style="background-color: #44B549;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="shouInfo('S');"/>&#12288;
                        <input class="btn_green" type="button" value="下一步" id="msgSave2"
                               style="background-color: #44B549;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                               onclick="shouInfo('X');"/>&#12288;
                        <c:if test="${roleFlag eq  'management' && isRead ne 'Y'}">
                            <input class="btn_green" type="button" value="保&#12288;存" id="msgSave"
                                   style="background-color: #44B549;color: #fff;border: none;display: inline-block;height: 30px;line-height: 28px;padding: 0 20px;border-radius: 3px"
                                   onclick="saveInfo();"/>&#12288;
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</div>