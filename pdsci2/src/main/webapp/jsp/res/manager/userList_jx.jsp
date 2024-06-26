<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style type="text/css">
    #boxHome .item:HOVER{background-color: #eee;}
	#searchForm td{border: hidden;width: 10%}
    .basic thead th{
        text-align: center;
    }
    .basic tbody td{
        text-align: center;
    }

</style>
<script type="text/javascript">
	function search(){
        var beginDate = $("input[name='beginDate']").val();
        var endDate = $("input[name='endDate']").val();

        if(beginDate!=null & beginDate!='' & endDate!=null & endDate!='' & beginDate>endDate){
            return jboxTip("开始时间不能大于结束时间！");
        }
        jboxStartLoading();
		$("#searchForm").submit();
	}
	
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);			
		}
		search();
	}
	
	function searchUser(){
		toPage("${param.currentPage}");
	}
	
	function editDoc(doctorFlow){
		<%--jboxOpen("<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}',"编辑医师信息",950,490);--%>
		var url = "<s:url value='/res/manager/editDocSimple'/>?roleFlag=${param.roleFlag}&doctorFlow="+doctorFlow+"&role="+'${role}'
		var mainIframe = window.parent.frames["mainIframe"];
		var width = 950;
		var height = 490;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'用户信息',width,height);
	}
	
	function changeGroupRelated(doctorFlow,value,type){
		var url = "<s:url value='/res/doc/user/saveGroupRelated'/>";
		var data = {
			doctorFlow:doctorFlow,
			value:value,
			type:type
		};
		jboxPost(url, data, null,null,true);
	}
	
	<%--$(function(){--%>
		<%--<c:if test="${empty param.doctorCategoryId}">--%>
		<%--if($("[name='doctorCategoryId'] option").length == 2){--%>
			<%--$("[name='doctorCategoryId'] option:last").attr("selected","selected").parent().change();--%>
		<%--}--%>
		<%--</c:if>--%>
	<%--});--%>

	function exportJxDoc(){
		if(${empty stuUserLst}){
			jboxTip("当前无记录!");
			return;
		}

        var stuBatName = $("select[name='batchFlow']").find("option:selected").text();
		var url = "<s:url value='/res/manager/exportJxDoc?stuBatName='/>"+encodeURI(encodeURI(stuBatName));
//		jboxTip("导出中…………");
		jboxExp($("#searchForm"),url);
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
            $("#boxHome").unbind("mouseenter mouseleave"); //先进行解绑，否则失效
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });

            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#speId").val($(this).attr("flow"));
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));

                }
            });
        };


    })(jQuery);


    /**
     *显示不同的查询专业
     */
    function showSpe(flag){

        $("#boxHome").html("");
        $("#speSel").val("");
        if(flag =='Y'){
            $("#boxHome").append($(".abroad").clone());
        }else if(flag =='N'){
            $("#boxHome").append($(".domestic").clone());
        }

        $("#speSel").likeSearchInit({

        });
    }

    //页面加载完成时调用
    $(function(){
        $("#speSel").likeSearchInit({

        });
//        //解决每次查询之后，进修专业下拉框值就为空了
//        var flag = $("select[name='isForeign']").val();
//        showSpe(flag);

    });

    //查看详情
    function auditInfo(userFlow,batchId,isHide,isForeign){
        if(isForeign =='Y'){
            jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&isHide="+isHide+"&isForeign="+isForeign,"User Infomations",1000,550);
        }
        jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&isHide="+isHide+"&isForeign="+isForeign,"用户信息",1000,550);
    }


</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/manager/getUserList/${role}'/>">
					<input id="currentPage" type="hidden" name="currentPage" value=""/>
					<div class="queryDiv">
                       <%-- <div class="inputDiv">
                            <label class="qlable">进修批次：</label>
                            <select name="batchFlow" class="qselect">
                                <option value="all">全部</option>
                                <c:forEach items="${batchLst}" var="dict">
                                    <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                                </c:forEach>
                            </select>
                        </div>--%>
                           <div class="inputDiv">
                               <label class="qlable">报到时间：</label>
                               <input style="height: 23px;width: 142px" type="text" class="ctime" name="beginDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${param.beginDate}"/>
                           </div>
                           <div class="inputDiv">
                               <label class="qlable">结业时间：</label>
                               <input type="text" style="height: 23px;width: 142px" class="ctime" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${param.endDate}"/>
                           </div>

                        <div class="inputDiv">
                            <label class="qlable">学员类型：</label>
                            <select name="isForeign" class="qselect" onchange="showSpe(this.value)">
                                <option value="all">全部</option>
                                <option value="${GlobalConstant.FLAG_N}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">selected</c:if>>境内</option>
                                <option value="${GlobalConstant.FLAG_Y}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">selected</c:if>>境外</option>
                            </select>
                        </div>
						<div class="inputDiv">
							<label class="qlable">进修专业：</label>
                            <input id="speSel" style="height: 23px" class="toggleView qtext" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                            <input id="speId"  type="hidden" name="speId" value="${param.speId}"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:34px;left:4px;">
                                <div id="boxHome" style="float: left;margin-left: 95px;max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 250px;border-top: none;position: relative;display: none;">
                                      <%--  <c:forEach items="${currDeptList}" var="SysDept">
                                            <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;text-align: left;padding-left: 10px"}>${SysDept.deptName}</p>
                                        </c:forEach>--%>

                                          <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">
                                              <c:forEach items="${currDeptList}" var="SysDept">
                                                  <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="text-align:left;height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
                                              </c:forEach>

                                          </c:if>
                                          <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">
                                              <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                                  <p class="item" flow="${dict.dictId}" value="${dict.dictDesc}" style="text-align:left;height: 40px;padding-left: 10px;">${dict.dictDesc}</p>
                                              </c:forEach>
                                          </c:if>
                                </div>
                            </div>
                        </div>

                        <div class="inputDiv">
                             <label class="qlable">姓&#12288;&#12288;名：</label>
                             <input type="text" style="height: 23px" name="userName" value="${param.userName}" class="qtext">
                        </div>
						<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
							<input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
							<input type="button" class="searchInput" value="导&#12288;出" onclick="exportJxDoc();"/>
						</div>
					</div>
				</form>
				    <table class="basic" width="100%">
                        <colgroup>
                            <col width="10%"/>
                            <col width="10%"/>
                            <col width="15%"/>
                            <col width="5%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                            <col width="10%"/>
                        </colgroup>
                        <thead>
                            <tr>
                                <th>进修批次</th>
                                <th>姓名</th>
                                <th>身份证号</th>
                                <th>性别</th>
                                <th>学员类型</th>
                                <%--<th>开始时间</th>--%>
                                <th colspan="2">进修专业(进修时长)</th>
                                <th>报到时间</th>
                                <th>结业时间</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${stuUserLst}" var="user">
                            <tr>
                                <td>${user.stuBatName}</td>
                                <td>${extInfoMap[user.resumeFlow].userName}</td>
                                <td>${user.sysUser.idNo}</td>
                                <td>${extInfoMap[user.resumeFlow].sexName}</td>
                                <td>
                                <c:if test="${user.sysUser.isForeign eq GlobalConstant.FLAG_N}">境内</c:if>
                                <c:if test="${user.sysUser.isForeign eq GlobalConstant.FLAG_Y}">境外</c:if>
                                </td>
                                <td colspan="2">
                                    <c:set var="unit" value="个月"></c:set>
                                    <c:set var="unit2" value="天"></c:set>
                                    <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                                        ${speForm.speName}
                                        (
                                        ${not empty speForm.stuTimeId?speForm.stuTimeId:(pdfn:signDaysBetweenTowDate(speForm.endDate,speForm.beginDate)+1)}
                                        <c:if test="${user.sysUser.isForeign eq 'Y'}">${unit2}</c:if>
                                        <c:if test="${user.sysUser.isForeign eq 'N'}">${unit}</c:if>
                                        )
                                    </c:forEach>
                                </td>
                                <td>${user.reportDate}</td>
                                <td>${user.certificateDate}</td>
                                <td>
                                    <a style="color: #0a8cd2;cursor: pointer" onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y','${user.sysUser.isForeign}');">[查看详情]</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty stuUserLst}">
                            <tr>
                                <td colspan="99">暂无信息</td>
                            </tr>
                        </c:if>
                        </tbody>
				    </table>
                    <div>
                        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"/>
                        <pd:pagination toPage="toPage"/>
                    </div>

                <div  style="display:none;width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
                    <div  style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 400px;border-top: none;position: relative;display: none;">
                        <c:forEach items="${currDeptList}" var="SysDept">
                            <p class="domestic item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="text-align:left;height: 30px;text-align: left;padding-left: 10px"}>${SysDept.deptName}</p><br/>
                        </c:forEach>
                        <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                            <p class="abroad item" flow="${dict.dictId}" value="${dict.dictDesc}" style="text-align:left;height: 40px;text-align: left;padding-left: 10px">${dict.dictDesc}</p>
                        </c:forEach>
                    </div>
                </div>

			</div>
		</div>
	</div>
</body>
</html>