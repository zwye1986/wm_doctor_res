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

    <style type="text/css">
        #boxHome .item:HOVER{background-color: #eee;}
    </style>
    <script type="text/javascript">
        $(function(){
            $("#detail").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
            $(".spreadOut").toggle("${param.conditionStatus eq GlobalConstant.FLAG_Y}"=="true");
        });
        //查询
        function search(){
            $("#orgFlow").val("");
            if($("#orgName").val()!=""){
                $("#orgFlow").val($("#orgName").attr("flow"));
            }
            jboxStartLoading();
            $("#searchForm").submit();
        }
        //分页
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        /**
         *模糊查询加载
         */
        (function($){
            $.fn.likeSearchInit = function(option){
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome").show();
                    if($.trim(this.value)){
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='"+this.value+"']").show();
                        if(!items){
                            $("#boxHome").hide();
                        }
                    }else{
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome.on").length){
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function(){
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    searchInput.attr("flow",$(this).attr("flow"));
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        $(function(){
            $("#orgName").likeSearchInit({
            });
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/user/eduGraduateInfoList"/>" method="post">
            <table class="basic" style="width: 100%;margin: 10px 0px 5px -20px;border: none;">
                <tr>
                    <td style="border: none;">
                        <input id="currentPage" type="hidden" name="currentPage" value=""/>
                        <div>&#12288;
                            学&#12288;&#12288;号：<input class="year"  type="text" style="width: 137px;" name="sid" value="${param.sid}" >
                            &#12288;姓名：<input type="text" style="width: 137px;" name="userName" value="${param.userName }">
                            &#12288;<span style="padding-left:26px;"></span>培养单位：<input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value" flow="${param.orgFlow}" style="width: 137px;"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:32px;left:330px;">
                                <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 141px;border-top: none;position: relative;display: none;">
                                    <c:forEach items="${orgList}" var="org">
                                        <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                                    </c:forEach>
                                </div>
                            </div>
                            <input type="hidden" id="orgFlow" name="orgFlow" value="${param.orgFlow }"/>
                            <input type="button" class="search" onclick="search();" value="查&#12288;询" />
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <div class="resultDiv">
            <table class="basic" width="100%">
                <tr style="font-weight: bold;">
                    <td style="text-align: center;padding: 0px;width:80px">学号</td>
                    <td style="text-align: center;padding: 0px;width:80px">姓名</td>
                    <td style="text-align: center;padding: 0px;width:150px">生源地</td>
                    <td style="text-align: center;padding: 0px;width:150px">家庭联系地址</td>
                    <td style="text-align: center;padding: 0px;width:80px">邮编</td>
                    <td style="text-align: center;padding: 0px;width:80px">家庭电话</td>
                    <td style="text-align: center;padding: 0px;width:80px;">移动电话</td>
                </tr>
                <c:forEach items="${graduateInfoList}" var="eduUser">
                    <c:set var="user" value="${userMap[eduUser.userFlow]}"/>
                    <c:set var="doctor" value="${resDoctorMap[eduUser.userFlow]}"/>
                    <tr>
                        <td style="text-align: center;padding: 0px;">${eduUser.sid }</td>
                        <td style="text-align: center;padding: 0px;">${eduUser.sysUser.userName }</td>
                        <td style="text-align: center;padding: 0px;">${eduUser.pollingTableForm.birthPlaceProvince }${eduUser.pollingTableForm.birthPlacePrefectureLevelCity }${eduUser.pollingTableForm.birthPlaceCountyLevelCity }</td>
                        <td style="text-align: center;padding: 0px;">${eduUser.pollingTableForm.homeAddress}</td>
                        <td style="text-align: center;padding: 0px;">${eduUser.pollingTableForm.postCode}</td>
                        <td style="text-align: center;padding: 0px;">${eduUser.pollingTableForm.homePhone}</td>
                        <td style="text-align: center;padding: 0px;">${eduUser.pollingTableForm.mobilePhone}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty graduateInfoList}">
                    <td colspan="15" style="text-align: center;padding: 0px;">无记录！</td>
                </c:if>
            </table>
            <c:set var="pageView" value="${pdfn:getPageView(graduateInfoList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="detail" style="background: white;"></div>
</body>
</html>