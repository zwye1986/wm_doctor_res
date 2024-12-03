<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>
    });
    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                $(pDiv).css("left", $("#train").outerWidth());
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
                $(pDiv).css({"top":"35px","left":"70px"});
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
                    $("p." + spaceId + ".item", boxHome).show();
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#" + spaceId);
                input.val(value);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                    $("#orgFlow").val($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
    }
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
    function toPage(page) {
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        searchRecInfo();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function searchRecInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/backTrainInfo?roleFlag=global'/>";
        jboxPostLoad("hosContent", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        var url = "<s:url value='/jsres/doctor/exportForBack'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function change() {
        $("#trainOrg").val("");
    }
    //上传退培附件
    function uploadFile(recordFlow) {
        if (${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}) {
            jboxTip("退培附件由基地上传！");
            return;
        }
        var url = "<s:url value='/jsres/doctor/showUpload?recordFlow='/>" + recordFlow;
        typeName = "上传退培附件";
        jboxOpen(url, typeName, 500, 260);
    }
    function doctorPassedList(doctorFlow,recruitFlow){
        var hideApprove="hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?isRetrunShow=Y&info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
        jboxOpen(url,"学员信息",1050,550);
    }
</script>
<body>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
                <div>
                    <table>
                        <tr>
                            <td style="position: relative">
                                <label id="train">培训基地：</label>
                                <input id="trainOrg" oncontextmenu="return false" value="${param.name}" name="name"
                                       class="toggleView input" type="text" autocomplete="off"
                                       style="margin-left: 0px;width: 150px" onkeydown="changeStatus();"
                                       onkeyup="changeStatus();" onchange="change();"/>
                                <div id="pDiv"
                                     style="width: 0px;height: 0px;overflow: visible;float: left; position:absolute; top:35px;">
                                    <div class="boxHome trainOrg" id="trainOrgSel"
                                         style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 275px;border-top: none;position: relative;display:none; box-sizing: border-box;padding: 10px">
                                        <c:forEach items="${orgs}" var="org">
                                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
                                               value="${org.orgName}"
                                               <c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>
                                               <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
                                               style="line-height: 30px; cursor: default;height: 30px "
                                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                            >${org.orgName}</p>
                                        </c:forEach>
                                    </div>
                                    <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
                                           style="display: none;"/>
                                </div>
                            </td>
                            <td>
                                &#12288;学&nbsp;&nbsp;员&nbsp;&nbsp;姓&nbsp;&nbsp;名：
                                <input type="text" value="${param.doctorName}" class="input" name="doctorName"
                                       style="width: 100px;"/>
                            </td>
                            <td>
                                &#12288;培训年级：
                                <input type="text" id="sessionNumber" name="sessionNumber"
                                       value="${param.sessionNumber}" class="input" readonly="readonly"
                                       style="width: 100px;margin-left: 0px"/>
                            </td>
                            <c:if test="${isQuality ne 'Y'}">
                            <td>
                                &#12288;培训专业：
                                <input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName"
                                       style="width: 100px;"/>
                            </td>
                            </c:if>
                        </tr>
                        <tr>
                            <td>
                                退培类型：
                                <select class="select" id="policyId" name="policyId" style="width: 157px;">
                                    <option value="">请选择</option>
                                    <option value="1" <c:if test="${param.policyId eq 1}">selected="selected"</c:if>>
                                        协议退培
                                    </option>
                                    <option value="2" <c:if test="${param.policyId eq 2}">selected="selected"</c:if>>
                                        违约退培
                                    </option>
                                </select>
                            </td>
                            <td>
                                &#12288;退培主要原因：&nbsp;
                                <select class="select" id="reasonId" name="reasonId" style="width: 107px;">
                                    <option value="">请选择</option>
                                    <option value="1" <c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>
                                        辞职
                                    </option>
                                    <option value="2" <c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>
                                        考研
                                    </option>
                                    <option value="3" <c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>
                                        其他
                                    </option>
                                </select>
                            </td>
                            <td colspan="2">&#12288;人员类型：
                                <c:forEach items="${resDocTypeEnumList}" var="type">
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  />${type.name}&nbsp;</label>
                                </c:forEach>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                审核结果：
                                <select class="select" id="auditStatusId" name="auditStatusId" style="width: 157px;">
                                    <option value="">请选择</option>
                                    <option value="0"
                                            <c:if test="${param.auditStatusId eq '0'}">selected="selected"</c:if>>不通过
                                    </option>
                                    <option value="1"
                                            <c:if test="${param.auditStatusId eq '1'}">selected="selected"</c:if>>通过
                                    </option>
                                </select>
                            </td>
                            <td>&#12288;
                                <input class="btn_green" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
                                &#12288;
                                <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="10%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
            </colgroup>
            <tr>
                <th>培训基地</th>
                <th>培训年级</th>
                <th>学员姓名</th>
                <th>培训专业</th>
                <th>退培主要<br/>原因</th>
                <th>退培类型</th>
                <th>学员去向</th>
                <th>备注(培训基地意见)</th>
                <th>附件</th>
                <th>省厅审核结果</th>
                <th>省厅意见</th>
            </tr>
            <c:forEach items="${resRecList}" var="rec">
                <tr>
                    <td title="${rec.orgName}">${pdfn:cutString(rec.orgName,4,true,3) }</td>
                    <td>${rec.sessionNumber}</td>
                    <td><a style="color:#1e7db3;" href="javascript:void(0);" onclick="doctorPassedList('${rec.doctorFlow}','${rec.recruitFlow}');">${rec.doctorName}</a></td>
                    <td title="${rec.trainingSpeName}">${pdfn:cutString(rec.trainingSpeName,7,true,3) }</td>
                    <td>${rec.reasonName}
                        <label title="${rec.reason}">
                            <c:if test="${not empty rec.reason }">
                                (${pdfn:cutString(rec.reason,5,true,3) })
                            </c:if>
                        </label>
                    </td>
                    <td>${rec.policyName}
                        <label title="${rec.policy }">
                            <c:if test="${not empty rec.policy }">
                                (${pdfn:cutString(rec.policy,5,true,3) })
                            </c:if>
                        </label>
                    </td>
                    <td><label title="${rec.dispositon}">${pdfn:cutString(rec.dispositon,5,true,3) }</label></td>
                    <td><label title="${rec.remark}">${pdfn:cutString(rec.remark,5,true,3) }</label></td>
                    <td>
                        <c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
                            [<a target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">查看</a>]</br>
                            <%--[<a target="_blank" href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">下载</a>]--%>
                            <%--</br>--%>
                        </c:forEach>
                        <c:if test="${empty fileMaps[rec.recordFlow]}">
                            暂未上传附件！
                        </c:if>
                    </td>
                    <td><label title="${rec.auditStatusName}">${pdfn:cutString(rec.auditStatusName,7,true,3) }</label>
                    </td>
                    <c:set var="modifyTime" value="${pdfn:transDateTime(rec.modifyTime)}"></c:set>
                    <c:set var="modifyTime" value="${ '  审核时间：'.concat(modifyTime)}"></c:set>
                    <c:if test="${empty rec.auditOpinion}">
                        <td><label title="${modifyTime}">${pdfn:cutString(modifyTime,10,true,3) }</label></td>
                    </c:if>
                    <c:if test="${not empty rec.auditOpinion}">
                        <td><label
                                title="${rec.auditOpinion.concat(modifyTime)}">${pdfn:cutString(rec.auditOpinion.concat(modifyTime),10,true,3) }</label>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${(not empty resRecList and GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope and (not empty param.orgFlow and not empty param.sessionNumber))or(not empty resRecList and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and (not empty param.orgFlow and not empty param.sessionNumber))or(GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and not empty param.sessionNumber and not empty resRecList)}">
                <tr>
                    <td colspan="11" style="text-align: center;">合计退培：${resRecListSize}&#12288;退培比例：${percent}</td>
                </tr>
            </c:if>
            <c:if test="${empty resRecList}">
                <tr>
                    <td colspan="11">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope}">
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </c:if>
</div>
</body>
