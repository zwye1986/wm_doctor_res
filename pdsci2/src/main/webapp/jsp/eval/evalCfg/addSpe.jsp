<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
        #boxHome .item:HOVER{background-color: #eee;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        /**
         *模糊查询加载
         */
        (function ($) {
            $.fn.likeSearchInit = function (option) {
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup", function () {
                    $("#speId").val("");
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
                searchInput.on("focus", function () {
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
                    $("#speId").val(flow);
                }
            });
        });
        function saveAddSpe() {
            if (false == $("#sysUserForm").validationEngine("validate")) {
                return;
            }
           var speId= $("#speId").val();
            if(!$.trim(speId))
            {
                jboxTip("请选择专业基地！");
                return false;
            }
            var url = "<s:url value='/eval/evalCfg/saveAddSpe'/>";
            var getdata = $('#sysUserForm').serialize();
            jboxPost(url, getdata, function (data) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    try {
                        window.parent.frames['mainIframe'].window.search();
                    } catch (e) {

                    }
                    jboxClose();
                }
            }, null, true);
        }
    </script>
</head>
<body>

<form id="sysUserForm" style="height: 100px;">
    <input type="hidden" name="cfgFlow" value="${cfg.cfgFlow}"/>
    <div class="content" style="height: 400px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <div class="" style="text-align: center;width: 100%;">
                        <label class="qlable">专业基地：</label>
                        <input id="orgSel" class="qtext" type="text" name="speName" value="${cfg.speName}"  autocomplete="off"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:26px;left:128px;">
                            <div id="boxHome" style="max-height: 140px;overflow: auto;border: 1px #ccc solid;background-color: white;
						    min-width: 148px;border-top: none;position: relative;display: none;text-align: left;">
                                <c:set var="dictName" value="dictTypeEnumDoctorTrainingSpeList"/>
                                <c:forEach items="${applicationScope[dictName] }" var="dict">
                                    <p class="item" flow="${dict.dictId}" value="${dict.dictName}"
                                       style="padding-left: 10px;">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="hidden" id="speId" name="speId" value="${cfg.speId}" />
                    </div>
                    <div class="button">
                        <input class="search" type="button" value="保&#12288;存" onclick="saveAddSpe();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>