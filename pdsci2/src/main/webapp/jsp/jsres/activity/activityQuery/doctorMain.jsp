
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
</style>
<script type="text/javascript">
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus",function(){
                var boxHome = $("#"+spaceId+"Sel");
                boxHome.show();
                if($.trim(this.value)){
                    $("p."+spaceId+".item",boxHome).hide();
                    var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
                    if(!items){
                        boxHome.hide();
                    }
                }else{
                    $("p."+spaceId+".item",boxHome).show();
                }
            });
            searchInput.on("blur",function(){
                var boxHome = $("#"+ spaceId+"Sel");
                if(!$("."+spaceId+".boxHome.on").length){
                    boxHome.hide();
                }
            });
            $("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });

            $("."+spaceId+".boxHome .item").click(function(){
                var boxHome = $("."+spaceId+".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#"+spaceId);
                input.val(value);
                if(option.clickActive){
                    option.clickActive($(this).attr("flow"));
                }
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
            });
        };
    })(jQuery);
    $(document).ready(function(){
        toPage(1);
        $("#ksmc").likeSearchInit({
            clickActive:function(flow){
                $("."+flow).show();
            }
        });
    });
    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi","<s:url value='/jsres/activityQuery/list'/>?roleFlag=${param.roleFlag}&isNew=${param.isNew}&isEval=${param.isEval}",$("#searchForm").serialize(),false);
    }
</script>
<div class="main_bd" id="div_table_0" style="margin-top: 5px;">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">
                        活动名称：
                    </td>
                    <td>
                        <input type="text" name="activityName" value="" class="input" />
                    </td>
                    <td class="td_left">
                        主&nbsp;讲&nbsp;人：
                    </td>
                    <td>
                        <input type="text" name="userName" value="" class="input" />
                    </td>
                    <td class="td_left">
                        活动形式：
                    </td>
                    <td>
                        <select name="activityTypeId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${activityTypeEnumList}" var="a">
                                <option value="${a.id}" >${a.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">
                        科&#12288;&#12288;室：
                    </td>
<%--                    <td>--%>
<%--                        <select name="deptFlow" class="select">--%>
<%--                            <option value="">全部</option>--%>
<%--                            <c:forEach items="${depts}" var="dept">--%>
<%--                                <option value="${dept.deptFlow}" >${dept.deptName}<c:if test="${dept.orgFlow ne doctor.orgFlow}">(${dept.orgName})</c:if>--%>
<%--                                </option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
<%--                    </td>--%>
                    <td><input type="text" id="ksmc" name="deptName" value="${param.deptName}" class="input" autocomplete="off"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">
                            <div class="boxHome ksmc" id="ksmcSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${depts}" var="dept" >
                                    <p  class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}" style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        开始时间：
                    </td>
                    <td>
                        <input type="text" id="startDate" name="startTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <td class="td_left">
                        结束时间：
                    </td>
                    <td>
                        <input type="text" id="endDate" name="endTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <td id="func" colspan="3">
                        &nbsp;<input type="checkbox" name="isCurrent" checked value="Y">当前轮转科室
                        &nbsp;<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>