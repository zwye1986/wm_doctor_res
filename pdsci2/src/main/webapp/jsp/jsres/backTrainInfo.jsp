<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
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
                $(pDiv).css("top", w);
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
        var url = "<s:url value='/jsres/doctor/backTrainInfo'/>";
        jboxPostLoad("content", url, $("#searchForm").serialize(), false);
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

       //显示隐藏
    let flag = false;
    function showOrHide(){

        if(flag){
            $(`.form_item_hide`).hide();
            // document.getElementById("hideForm").style.display='none';
            $("#open").text("展开")
            flag = false;
        }else {
            $(`.form_item_hide`).css('display','flex');
            // document.getElementById("hideForm").style.display='flex';
            $("#open").text("收起")
            flag = true;
        }

    }

    //上传退培附件
    function uploadFile(recordFlow) {
        if (${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}) {
            jboxTip("退培附件由基地上传！");
            return;
        }
        var url = "<s:url value='/jsres/doctor/showUpload?recordFlow='/>" + recordFlow+"&currentPage="+$("#currentPage").val();
        typeName = "上传退培附件";
        jboxOpen(url, typeName, 500, 260);
    }
</script>
<body>
<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">
    <div class="main_hd">
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
            <h2 class="underline">退培学员管理</h2>
        </c:if>
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope and param.isAudio !=GlobalConstant.FLAG_N}">
            <h2 class="underline">退培学员查询</h2>
        </c:if>
    </div>
</c:if>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}"/>
            <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y and param.isAudio !=GlobalConstant.FLAG_N}">

                <div class="form_search">
                    <div class="form_item">

                        <div class="form_label">学员姓名：</div>
                        <div class="form_content">
                            <input type="text"  name="doctorName" value="${param.doctorName}" class="input" />
                        </div>

                    </div>

                     <div class="form_item">

                        <div class="form_label">年&#12288;&#12288;级：</div>
                        <div class="form_content">
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" />
                        </div>

                    </div>

                    <div class="form_item">

                        <div class="form_label">培训专业：</div>
                        <div class="form_content" >
                            <input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName" />
                        </div>

                    </div>


                    <div class="form_item">

                        <c:if test="${GlobalConstant.USER_LIST_LOCAL eq  sessionScope.userListScope}">
                            <c:if test="${JointOrgCount ne '0'}">
                                <div class="form_label">培训基地：</div>
                                <div class="form_content">
                                    <select class="select" name="orgFlow0"  onchange="searchDeptList(this.value)">
                                        <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                        <c:forEach items="${orgList}" var="org">
                                            <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                        </c:if>

                        <c:if test="${GlobalConstant.USER_LIST_LOCAL != sessionScope.userListScope}">
                            <div class="form_label">培训基地：</div>

                            <div class="form_content">
                                <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                                       class="toggleView input" type="text" autocomplete="off"
                                       onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                                <div id="pDiv"
                                     style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                    <div class="boxHome trainOrg" id="trainOrgSel"
                                         style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;top: -5px;left:80px;">
                                        <c:forEach items="${orgs}" var="org">
                                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                               style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                            >${org.orgName}</p>
                                        </c:forEach>
                                    </div>
                                    <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                                </div>
                            </div>
                        </c:if>

                    </div>



                    <div class="form_item form_item_hide">

                        <div class="form_label" style="width: 100px;">退培主要原因：</div>
                        <div class="form_content">
                            <select class="select" id="reasonId" name="reasonId"   onchange="changeReason(this);">
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职</option>
                                <option value="2" <c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研</option>
                                <option value="3" <c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他</option>
                            </select>
                        </div>

                    </div>
                    
                     <div class="form_item form_item_hide">

                        <div class="form_label">退培类型：</div>
                        <div class="form_content">
                            <select class="select" id="policyId" name="policyId"  >
                                <option value="">请选择</option>
                                <option value="1" <c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培</option>
                                <option value="2" <c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培</option>
                            </select>
                        </div>

                    </div>





                     <div class="form_item form_item_hide">

                        <div class="form_label" style="width: 100px;">省厅审核结果：</div>
                        <div class="form_content">
                            <select class="select" id="auditStatusId" name="auditStatusId" >
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${param.auditStatusId eq '1'}">selected="selected"</c:if>>${jsResAuditStatusEnumGlobalPassed.name}</option>
                                <option value="0"
                                        <c:if test="${param.auditStatusId eq '0'}">selected="selected"</c:if>>${jsResAuditStatusEnumGlobalNotPassed.name}</option>
                                <option value="2"
                                        <c:if test="${param.auditStatusId eq '2'}">selected="selected"</c:if>>${jsResAuditStatusEnumWaitGlobalPass.name}</option>
                                <option value="ChargeNotPassed"
                                        <c:if test="${param.auditStatusId eq '2'}">selected="selected"</c:if>>${jsResAuditStatusEnumWaitChargeNotPassed.name}</option>
                                <option value="ChargeAudit"
                                        <c:if test="${param.auditStatusId eq '2'}">selected="selected"</c:if>>${jsResAuditStatusEnumWaitChargeAudit.name}</option>
                                <option value="ChargePassed"
                                        <c:if test="${param.auditStatusId eq '2'}">selected="selected"</c:if>>${jsResAuditStatusEnumWaitChargePassed.name}</option>
                            </select>
                        </div>

                    </div>



                     <div class="form_item form_item_hide" style="width: 400px">

                        <div class="form_label" >人员类型：</div>
                        <div class="form_content">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </div>
                    </div>


                </div>

                <div style="margin-top: 15px;margin-bottom: 15px">

                    <input class="btn_green" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>&#12288;
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                        <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                    </c:if>

                    <a style="color: #54B2E5; margin: auto 0 auto 0;" onclick="showOrHide()" id="open">展开</a>

                </div>


<%--                <table class="searchTable">--%>
<%--                    &lt;%&ndash;<td style="position: relative">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;<label id="train">培训基地：</label>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;<input id="trainOrg" oncontextmenu="return false" value="${param.name}" name="name"&ndash;%&gt;--%>
<%--                               &lt;%&ndash;class="toggleView input" type="text" autocomplete="off"&ndash;%&gt;--%>
<%--                               &lt;%&ndash;style="margin-left: 0px;width: 150px" onkeydown="changeStatus();"&ndash;%&gt;--%>
<%--                               &lt;%&ndash;onkeyup="changeStatus();" onchange="change();"/>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;<div id="pDiv"&ndash;%&gt;--%>
<%--                             &lt;%&ndash;style="width: 0px;height: 0px;overflow: visible;float: left; position:absolute; top:35px;">&ndash;%&gt;--%>
<%--                            &lt;%&ndash;<div class="boxHome trainOrg" id="trainOrgSel"&ndash;%&gt;--%>
<%--                                 &lt;%&ndash;style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 275px;border-top: none;position: relative;display:none; box-sizing: border-box;padding: 10px">&ndash;%&gt;--%>
<%--                                &lt;%&ndash;<c:forEach items="${orgs}" var="org">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"&ndash;%&gt;--%>
<%--                                       &lt;%&ndash;value="${org.orgName}"&ndash;%&gt;--%>
<%--                                       &lt;%&ndash;<c:if test="${empty org.orgLevelId}">type="allOrg" </c:if>&ndash;%&gt;--%>
<%--                                       &lt;%&ndash;<c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>&ndash;%&gt;--%>
<%--                                       &lt;%&ndash;style="line-height: 30px; cursor: default;height: 30px "&ndash;%&gt;--%>
<%--                                       &lt;%&ndash;<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;>${org.orgName}</p>&ndash;%&gt;--%>
<%--                                &lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;</div>&ndash;%&gt;--%>
<%--                            &lt;%&ndash;<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"&ndash;%&gt;--%>
<%--                                   &lt;%&ndash;style="display: none;"/>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;</div>&ndash;%&gt;--%>
<%--                    &lt;%&ndash;</td>&ndash;%&gt;--%>
<%--                    <tr>--%>
<%--                        --%>
<%--                       --%>
<%--                        --%>
<%--                        --%>
<%--                        --%>
<%--                    </tr>--%>
<%--                    <tr>--%>
<%--                        --%>


<%--                        --%>

<%--                        --%>
<%--                    </tr>--%>
<%--                        <tr>--%>
<%--                            <td colspan="8">--%>
<%--&lt;%&ndash;                            <c:if test="${GlobalConstant.USER_LIST_LOCAL eq  sessionScope.userListScope}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <c:if test="${JointOrgCount ne '0'}">培训基地：&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <select class="select" name="orgFlow0" style="width: 120px;margin-left: -4px;" onchange="searchDeptList(this.value)">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <c:forEach items="${orgList}" var="org">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </select>&#12288;&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <c:if test="${GlobalConstant.USER_LIST_LOCAL != sessionScope.userListScope}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                培训基地：&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                           class="toggleView input" type="text" autocomplete="off" style="margin-left: 0px;width: 120px"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                           onkeydown="changeStatus();" onkeyup="changeStatus();"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <div id="pDiv"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                         style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <div class="boxHome trainOrg" id="trainOrgSel"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                             style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;top: -5px;left:80px;">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <c:forEach items="${orgs}" var="org">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                   style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                >${org.orgName}</p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </c:if>&ndash;%&gt;--%>
<%--                                <input class="btn_green" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>&#12288;--%>
<%--                                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">--%>
<%--                                    <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>--%>
<%--                                </c:if>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                </table>--%>
            </c:if>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="12%">
                <col width="6%">
                <col width="9%">
                <col width="9%">
                <col width="9%">
                <col width="9%">
                <col width="9%">
                <col width="9%">
                <col width="9%">
                <col width="9%">
                <col width="10%">
            </colgroup>
            <tr>
                <th>培训基地</th>
                <th>年级</th>
                <th>学员姓名</th>
                <th>培训专业</th>
                <th>退培主要原因</th>
                <th>退培类型</th>
                <th>学员去向</th>
                <th>备注(培训基地意见)</th>
                <th>附件</th>
                <th>审核状态</th>
                <th>省厅意见</th>
            </tr>
            <c:forEach items="${resRecList}" var="rec">
                <tr>
                    <td><label title="${rec.orgName}">${pdfn:cutString(rec.orgName,6,true,3) }</label></td>
                    <td>${rec.sessionNumber}</td>
                    <td>${rec.doctorName}</td>
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
                            [<a href="javascript:uploadFile('${rec.recordFlow}');">补传附件</a>]
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${'2' ne rec.auditStatusId and '1' ne rec.auditStatusId and '0' ne rec.auditStatusId }">
                            <label title="${rec.auditStatusName}">
                                    ${pdfn:cutString(rec.auditStatusName,7,true,3) }
                            </label>
                        </c:if>
                        <c:if test="${'2' eq rec.auditStatusId}">
                            <label title="待省厅审核">
                                    ${pdfn:cutString('待省厅审核',7,true,3) }
                            </label>
                        </c:if>
                        <c:if test="${'0' eq rec.auditStatusId}">
                            <label title="省厅审核不通过">
                                    ${pdfn:cutString('省厅审核不通过',7,true,3) }
                            </label>
                        </c:if>
                        <c:if test="${'1' eq rec.auditStatusId}">
                            <label title="省厅审核通过">
                                    ${pdfn:cutString('省厅审核通过',7,true,3) }
                            </label>
                        </c:if>
                    </td>
                    <c:if test="${'0' eq rec.auditStatusId or '1' eq rec.auditStatusId}">
                        <c:set var="auditTime" value="${pdfn:transDateTime(rec.auditTime)}"></c:set>
                        <c:set var="auditTime" value="${ '  审核时间：'.concat(auditTime)}"></c:set>
                        <c:if test="${empty rec.auditOpinion}">
                            <td><label title="${auditTime}">${pdfn:cutString(auditTime,6,true,3) }</label></td>
                        </c:if>
                        <c:if test="${not empty rec.auditOpinion}">
                            <td><label
                                    title="${rec.auditOpinion.concat(auditTime)}">${pdfn:cutString(rec.auditOpinion.concat(auditTime),6,true,3) }</label>
                            </td>
                        </c:if>
                    </c:if>
                    <c:if test="${'2' eq rec.auditStatusId}">
                        <td></td>
                    </c:if>
                    <c:if test="${'0' ne rec.auditStatusId and '1' ne rec.auditStatusId and '2' ne rec.auditStatusId}">
                        <td></td>
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
    <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.isAudio !=GlobalConstant.FLAG_N}">
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </c:if>
</div>
</body>
