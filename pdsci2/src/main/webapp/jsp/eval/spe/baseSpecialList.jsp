<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        #boxHome .item:HOVER{background-color: #eee;}
    </style>
    <script type="text/javascript">
        /**
         *模糊查询加载
         */
        (function ($) {
            $.fn.likeSearchInit = function (option) {
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus", function () {
                    $("#boxHome").show();
                    if ($.trim(this.value)) {
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='" + this.value + "']").show();
                        if (!items) {
                            $("#boxHome").hide();
                        }
                    } else {
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur", function () {
                    if (!$("#boxHome.on").length) {
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave", function () {
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function () {
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    if (option.clickActive) {
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        //======================================
        //页面加载完成时调用
        $(function () {
            $("#orgSel").likeSearchInit({
                clickActive: function (flow) {
                    $("#orgFlow").val(flow).change();
                }
            });
        });
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function removeName(orgName) {
            var $contains = $("p.item:contains('" + orgName + "')");
            console.log($contains);
            var length = $contains.length;
            if (length != 1) {
                $("#orgFlow").val("").change();
            } else {
                if ($contains.text() == orgName) {
                    var orgFlow = $contains.attr("flow");
                    $("#orgFlow").val(orgFlow).change();
                } else {
                    $("#orgFlow").val("").change();
                }
            }
        }
        function saveOrgSpeManage(obj) {
            jboxStartLoading();
            var orgFlow = "${orgFlow}";
            var orgName = "${orgName}";
            var id = $(obj).attr("id");
            var speId = $(obj).val();
            var speName = $("#" + id + "_speName").val();
            var speTypeId = $("#" + id + "_speTypeId").val();
            var speTypeName = $("#" + id + "_speTypeName").val();
            var checkedVal = $(obj).attr("checked");
            var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
            if ("checked" == checkedVal) {
                recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
            }
            var data = {
                "speId": speId,
                "speName": speName,
                "speTypeId": speTypeId,
                "speTypeName": speTypeName,
                "orgFlow": orgFlow,
                "orgName": orgName,
                "recordStatus": recordStatus
            };
            var url = "<s:url value='/eval/orgSpe/saveOrgSpeManage'/>";
            jboxPost(url, data,
                    function (resp) {
                        jboxEndLoading();
                        /* if(resp=="
                        ${GlobalConstant.SAVE_SUCCESSED}"){
                         search();
                         } */
                    }, null, true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="queryDiv">
            <form id="searchForm" action="<s:url value="/eval/orgSpe/orgSpeManage" />" method="post">
                <div class="inputDiv">
                    <label class="qlable">基地名称：</label>
                    <input id="orgSel" class="qtext" type="text" name="orgName" value="${param.orgName}"  autocomplete="off"/>
                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:100px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;
						min-width: 148px;border-top: none;position: relative;display: none;text-align: left;">
                            <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}"
                                   style="padding-left: 10px;">${org.orgName}</p>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" id="orgFlow" name="orgFlow" oldValue="${param.orgFlow}" />
                </div>
                <div class="lastDiv">
                    <input type="button" class="searchInput"  onclick="search();" value="查&#12288;询">
                </div>
            </form>
        </div>
        <div class="resultDiv">
            <table border="0" cellpadding="0" cellspacing="0" class="xllist">
                <tr>
                    <th>专业基地</th>
                </tr>
                <tr>
                    <td>
                        <c:if test="${empty param.orgFlow}">
                            <label style="padding-left: 15px;">
                                请选择基地！
                            </label>
                        </c:if>
                        <c:if test="${not empty param.orgFlow}">
                            <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
                            <c:forEach items="${applicationScope[dictName] }" var="dict">
                                <div style="width: 25%;float: left;margin-top: 8px;margin-bottom: 8px;text-align: left;">
                                    <c:set var="key" value="DoctorTrainingSpe${dict.dictId }"/>
                                    <input type="hidden" id="${key}_speTypeId" name="speTypeId" value="DoctorTrainingSpe"/>
                                    <input type="hidden" id="${key}_speTypeName" name="speTypeName" value="住院医师"/>
                                    <input type="hidden" id="${key}_speName" name="speName" value="${dict.dictName }"/>
                                    <label style="display: inline-block; padding-left: 15px; cursor: pointer;">
                                        <input type="checkbox" id="${key}" name="speId" value="${dict.dictId }"
                                               onclick="saveOrgSpeManage(this)"
                                               style="vertical-align: middle; cursor: pointer;"
                                               <c:if test="${not empty orgSpeMap[key]}">checked="checked"</c:if>/>
                                        &nbsp;${dict.dictName }
                                    </label>
                                </div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>