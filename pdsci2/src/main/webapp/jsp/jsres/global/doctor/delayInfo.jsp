<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }

    .searchTable .td_left{
	width: 82px;
	word-wrap:break-word;
	/*width:6em;*/
	height: auto;
	line-height: auto;
	/*text-align: right;*/
    }

    .searchTable .td_right{
        width: 220px;
        text-align:left;
    }
</style>
<script type="text/javascript">
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
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
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
    $(document).ready(function () {
        $('#time').datepicker({
            startDate: "${doctor.sessionNumber}",
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
    });
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/delay'/>";
        jboxPostLoad("content", url, $("#submitForm").serialize(), false);
    }
    function toPage(page) {
        var currentPage;
        if (page != undefined) {
            currentPage = page;
        }
        $("#currentPage").val(currentPage);
        search();
    }
</script>
<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
    <div class="main_hd">
        <h2 class="underline">延期学员查询</h2>
    </div>
</c:if>
<div>

    <div class="div_search">
        <form id="submitForm">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">

                <table class="searchTable" style="margin-top: 15px">
                    <tr>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL eq  sessionScope.userListScope}">
<%--                        <td colspan="2">--%>
                        <c:if test="${JointOrgCount ne '0'}">
                        <td class="td_left">
                                <%--<c:if test="${countryOrgFlag eq 'Y'}">--%>
                                <%--<input type="checkbox" id="jointOrg" name="jointOrg"--%>
                                <%--<c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label--%>
                                <%--for="jointOrg">协同基地</label>&#12288;--%>
                                <%--</c:if>--%>

                                培训基地：
                        </td>
                        <td class="td_right">
                                <select class="select" name="orgFlow0" onchange="searchDeptList(this.value)">
                                    <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>&#12288;

                        </td>
                        </c:if>
                    </c:if>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL != sessionScope.userListScope}">
                        <td class="td_left">培训基地：</td>
                        <td class="td_right">
                            <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                                   class="toggleView input" type="text" autocomplete="off" style="margin-left: 0px;"
                                   onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                            <div id="pDiv"
                                 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                <div class="boxHome trainOrg" id="trainOrgSel"
                                     style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;top: -5px">
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                           style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                           <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                        >${org.orgName}</p>
                                    </c:forEach>
                                </div>
                                <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                            </div>
                        </td>
                    </c:if>
                        <td class="td_left">学员姓名:</td>
                        <td class="td_right">
                            <input type="text"  name="doctorName" value="${param.doctorName}" class="input"  />
                        </td>
                        <td class="td_left">人员类型:</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                            </c:forEach>&#12288;
                        </td>
                    </tr>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL !=  sessionScope.userListScope}">
                        <tr>
                            <td colspan="8">
                                <%--<c:if test="${countryOrgFlag eq 'Y'}">--%>
                                    <%--<input type="checkbox" id="jointOrg" name="jointOrg"--%>
                                           <%--<c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label--%>
                                        <%--for="jointOrg">协同基地</label>&#12288;--%>
                                <%--</c:if>--%>
                                <c:if test="${JointOrgCount ne '0'}">

                                    <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                </table>

                <div style="margin-bottom: 15px;margin-top: 15px">
                    <c:if test="${JointOrgCount eq '0' ||GlobalConstant.USER_LIST_LOCAL ==  sessionScope.userListScope}">
                        <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                    </c:if>
                </div>
            </c:if>
        </form>
    </div>
    <div class="search_table" <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
                                    style="max-height: 500px;overflow: auto;"
                                </c:if>>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>学员姓名</th>
                <th>培训类型</th>
                <th>培训专业</th>
                <th>年级</th>
                <th>结业考核年份</th>
                <th>延期原因</th>
                <th>附件信息</th>
                <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
                    <th>详细</th>
                </c:if>
            </tr>
            <c:forEach items="${resRecList }" var="resRec">
                <tr>
                    <td title="${resRec.orgName}">${pdfn:cutString(resRec.orgName,6,true,3) }</td>
                    <td>${resRec.doctorName }</td>
                    <td>${resRec.trainingTypeName }</td>
                    <td>${resRec.trainingSpeName }</td>
                    <td>${resRec.sessionNumber }</td>
                    <td>${resRec.graduationYear }</td>
                    <c:set var="modifyTime" value="${pdfn:transDateTime(resRec.modifyTime)}"></c:set>
                    <c:set var="modifyTime" value="${ '  审核时间：'.concat(modifyTime)}"></c:set>
                    <c:if test="${empty resRec.delayreason}">
                        <td><label title="${modifyTime}">${pdfn:cutString(modifyTime,6,true,3) }</label></td>
                    </c:if>
                    <c:if test="${not empty resRec.delayreason}">
                        <td><label
                                title="${resRec.delayreason.concat(modifyTime)}">${pdfn:cutString(resRec.delayreason.concat(modifyTime),6,true,3) }</label>
                        </td>
                    </c:if>
                        <%--<td title="${resRec.delayreason }">${pdfn:cutString(resRec.delayreason,6,true,3)}</td>--%>
                    <td>
                        <c:if test="${not empty resRec.delayFilePath}">
                            [<a href="${sysCfgMap['upload_base_url']}/${resRec.delayFilePath}" target="_blank">查看附件</a>]
                        </c:if>
                    </td>
                    <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
                        <td>
                            <a class="btn"
                               onclick="getChangeOrgDetail('${resRec.doctorFlow}','${resRec.recruitFlow}');">详情</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty resRecList}">
                <tr>
                    <td colspan="9">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 0px;">
        <c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
