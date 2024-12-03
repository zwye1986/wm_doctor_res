<script>
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
                $(pDiv).css({"top": "35px","left":"70px"});
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
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function change() {
        $("#trainOrg").val("");
    }
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
    function searchRecInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/showBackCheck'/>";
        jboxPostLoad("hosContent", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        var url = "<s:url value='/jsres/doctor/exportForBack?auditStatusId=2'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function showCheck(recordFlow) {
        jboxOpen("<s:url value='/jsres/doctor/showCheck?recordFlow='/>" + recordFlow, "退培医师审核", 500, 300);
    }
    function doctorPassedList(doctorFlow,recruitFlow){
        var hideApprove="hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?isRetrunShow=Y&info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
        jboxOpen(url,"学员信息",1050,550);
    }
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
</script>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <input type="hidden" name="isQuery" id="" value="Y"/>
            <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
                <div>
                    <table>
                        <tr>
                            <td style="position: relative">
                                <label id="train">培训基地：</label>
                                <input id="trainOrg" oncontextmenu="return false" value="${param.name}" name="name"
                                       class="toggleView input" type="text" autocomplete="off"
                                       style="margin-left: 0px;width: 156px" onkeydown="changeStatus();"
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
                                <select class="select" id="policyId" name="policyId" style="width: 163px;"
                                        onchange="changeBackType(this);">
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
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input class="btn_green" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>
                                &#12288;<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </form>
    </div>

    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>培训年级</th>
                <th>学员姓名</th>
                <th>培训专业</th>
                <th>退培主要原因</th>
                <th>退培类型</th>
                <th>学员去向</th>
                <th>备注(培训基地意见)</th>
                <th style="width: 80px;">附件</th>
                <th style="width: 50px;">操作</th>
            </tr>
            <c:forEach items="${resRecList}" var="rec">
                <tr>
                    <td title="${rec.orgName}">${pdfn:cutString(rec.orgName,6,true,3) }</td>
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
                    <c:set var="policyTime" value="${pdfn:transDateTime(rec.policyTime)}"></c:set>
                    <c:set var="policyTime" value="${ '  审核时间：'.concat(policyTime)}"></c:set>
                    <c:if test="${empty rec.remark}">
                        <td><label title="${policyTime}">${pdfn:cutString(policyTime,10,true,3) }</label></td>
                    </c:if>
                    <c:if test="${not empty rec.remark}">
                        <td><label
                                title="${rec.remark.concat(policyTime)}">${pdfn:cutString(rec.remark.concat(policyTime),5,true,3) }</label>
                        </td>
                    </c:if>
                        <%--<td><label title="${rec.remark}">${pdfn:cutString(rec.remark,5,true,3) }</label></td>--%>
                    <td>
                        <c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
                            <%--[<a target="_blank" href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">查看附件</a>]--%>
                            [<a target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">查看</a>]</br>
                        </c:forEach>
                        <c:if test="${empty fileMaps[rec.recordFlow]}">
                            暂未上传附件！
                        </c:if>
                    </td>
                    <td>

                        <a class="btn" href="javascript:void(0)" onclick="showCheck('${rec.recordFlow}')">审&nbsp;核</a>
                            <%--<input class="btn_green" type="button" onclick="showCheck('${rec.recordFlow}')" value="审&nbsp;核"/>--%>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty resRecList}">
                <tr>
                    <td colspan="10">无记录</td>
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

