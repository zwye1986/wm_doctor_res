<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <script type="text/javascript"
            src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style>
        .searchDiv {
            height: 40px;
            min-width: 260px;
            float: left;
        }
    </style>
    <script type="text/javascript">
        var currSelDoc;
        $(function () {
            $("#detail").slideInit({
                width: 800,
                speed: 500,
                outClose: true
            });
            $("#internshipArchives").slideInit({
                width: 1000,
                speed: 300,
                outClose: true
            });
        });

        // 	function toggleView(flag){
        // 		var right = parseFloat($("#detail").css("right").replace("px",""));
        // 		slide(right,flag);
        // 	}

        // 	function slide(right,flag){
        // 		setTimeout(function(){
        // 			var endFlag;
        // 			if(flag)
        // 				endFlag = right<0;
        // 			else
        // 				endFlag = right>=-800;
        // 			if(endFlag){
        // 				if(flag)
        // 				right+=8;
        // 				else
        // 				right-=8;
        // 				$("#detail").css("right",right+"px");
        // 				slide(right,flag);
        // 			}else{
        // 				if(!flag){
        // 					$("#detail").hide();
        // 				}
        // 			}
        // 		},1);
        // 	}

        function search() {
            $("#recSearchForm").submit();
        }

        function toPage(page) {
            if (page) {
                $("#currentPage").val(page);
            }
            search();
        }

        function loadDetail(doctorFlow) {
            jboxStartLoading();
            jboxGet("<s:url value='/res/manager/doctorDetail'/>?roleFlag=${scope}&doctorFlow=" + doctorFlow, null, function (resp) {
                $("#detail").html(resp).rightSlideOpen();
                jboxEndLoading();
            }, function () {
                jboxEndLoading();
            }, false);
        }

        function processViewMain(doctorFlow) {
            window.open("<s:url value='/res/manager/doctorRegistryDetatil'/>?userFlow=" + doctorFlow, "_blank");
        }

        function internshipArchives(doctorFlow) {
            currSelDoc = doctorFlow;
            $("#internshipArchives").rightSlideOpen(function () {
                $(".selectTag").click();
            });
        }

        function archivesSelTag(selObj, type) {
            $(".selectTag").removeClass("selectTag");
            $(selObj).addClass("selectTag");

            var url = "<s:url value='/res/rec/showRecForm'/>?roleFlag=${scope}&recTypeId=" + type + "&operUserFlow=" + currSelDoc;
            jboxLoad("tagContent", url, false);
        }

        function defaultImg(img) {
            img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
        }

        function edit(flow) {
            var url = "<s:url value='/res/teacher/showDocAndUser'/>?flow=" + flow;
            jboxOpen(url, "信息", 1000, 500);
        }
        $(document).ready(function () {
            loadMajorJson();
        });
        function loadMajorJson() {
            var url = "<s:url value='/res/manager/searchSysOrg'/>";
            var courseArray = new Array();
            jboxGetAsync(url, null, function (data) {
                if (data) {
                    for (var i = 0; i < data.length; i++) {
                        var orgName = data[i].orgName;
                        if (data[i].orgName == null) {
                            orgName = "";
                        }
                        courseArray[i] = new Array(orgName, orgName);
                        if ($("#result_Course").val() == orgName) {
                            $("#searchParam_Course").val(orgName);
                        }
                    }
                    jboxStartLoading();
                    $("#searchParam_Course").suggest(courseArray, {
                        attachObject: '#suggest_Course',
                        dataContainer: '#result_Course',
                        triggerFunc: function (orgName) {
                            search();
                        },
                        enterFunc: function (orgName) {
                            search();
                        }
                    });
                    jboxEndLoading();
                }
            }, null, false);
        }
        function adjustResults() {
            $("#suggest_Course").css("left", $("#searchParam_Course").offset().left);
            $("#suggest_Course").css("top", $("#searchParam_Course").offset().top + $("#searchParam_Course").outerHeight());
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="recSearchForm" method="post" action="<s:url value='/res/manager/registryNote/${scope}'/>">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <table style="width: 100%;border: none;margin: 10px 0px;">
            <tr>
                <td>
                    <c:if test="${scope==GlobalConstant.USER_LIST_GLOBAL }">
                        <div class="searchDiv" style="width: 260px;">
                            &#12288;培训基地：
                            <input id="searchParam_Course" name="searchParam_Course"
                                   style="width: 130px;"
                                   value="${param.orgName }"
                                   onchange="$('#result_Course').val(this.value);search();"
                                   placeholder="输入基地名称" onkeydown="adjustResults();"
                                   onfocus="adjustResults();"/>
                            <div id="suggest_Course" class="ac_results"
                                 style="margin:0;position: fixed; z-index: 100;width: 130px;white-space:nowrap;"></div>
                            &#12288;
                            <input type="hidden" id="result_Course" name="orgName"
                                   value="${param.orgName}"/>
                            <input type="text" style="display:none" name="sss"/>
                            &#12288;
                        </div>
                    </c:if>
                    <c:if test="${scope==GlobalConstant.USER_LIST_CHARGE }">
                        <div class="searchDiv" style="width: 260px;">
                            &#12288;学&#12288;&#12288;院：
                            <select name="deptFlow"
                                    style="width: 130px;"
                                    onchange="search();">
                                <option></option>
                                <c:forEach items="${deptList}" var="dept">
                                    <option value="${dept.deptFlow}" ${param.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="searchDiv">
                        &#12288;人员类型：
                        <select name="doctorCategoryId"
                                style="width: 133px;"
                                onchange="search();">
                            <option></option>
                            <c:forEach items="${recDocCategoryEnumList}" var="category">
                                <c:set var="res_doctor_category_key"
                                       value="res_doctor_category_${category.id}"/>
                                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
                                    <option value="${category.id}" ${param.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchDiv">
                        &#12288;年&#12288;&#12288;级：
                        <select name="sessionNumber"
                                style="width: 133px;"
                                onchange="search();">
                            <option></option>
                            <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
                                <option value="${dict.dictName}" ${param.sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchDiv">
                        是否5+3培养模式：
                        <select name="is5plus3" onchange="search();"
                                style="width: 133px;">
                            <option value="" <c:if test="${empty param.is5plus3}">selected</c:if>>全部
                            </option>
                            <option value="Y" <c:if test="${param.is5plus3 eq 'Y'}">selected</c:if>>是
                            </option>
                            <option value="N" <c:if test="${param.is5plus3 eq 'N'}">selected</c:if>>否
                            </option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="searchDiv">
                        &#12288;培训专业：
                        <select name="trainingSpeId"
                                style="width: 133px;"
                                onchange="search();">
                            <option></option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchDiv">
                        &#12288;姓&#12288;&#12288;名：
                        <input type="text" name="userName" value="${param.userName}"
                               style="width: 130px;"
                               onchange="search();"/>
                    </div>
                </td>
            </tr>
            </table>
        </form>

        <table class="xllist" width="100%">
            <tr>
                <th style="width:10%">姓名</th>
                <th style="width:5%">性别</th>
                <th style="width:8%">电话</th>
                <th style="width:5%">年级</th>
                <th style="width:15%">专业</th>
                <c:if test="${scope==GlobalConstant.USER_LIST_GLOBAL }">
                    <th style="width:15%">培训基地</th>
                </c:if>
                <th style="width:8%">入院时间</th>
                <th style="width:15%">身份证号</th>
                <th style="width:8%">轮转进度</th>
                <th style="width:14%">操作</th>
            </tr>
            <c:forEach items="${doctorExtList}" var="doctorExt">
                <tr>
                    <td onclick="edit('${doctorExt.doctorFlow}');"
                        title="<img src='${sysCfgMap['upload_base_url']}/${doctorExt.sysUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>"
                        style="cursor:pointer;"><a>${doctorExt.sysUser.userName}</a></td>
                    <td>${doctorExt.sysUser.sexName}</td>
                    <td>${doctorExt.sysUser.userPhone}</td>
                    <td>${doctorExt.sessionNumber}</td>
                    <td>${doctorExt.trainingSpeName}</td>
                    <c:if test="${scope==GlobalConstant.USER_LIST_GLOBAL }">
                        <td>${doctorExt.orgName}</td>
                    </c:if>
                    <td>${doctorExt.inHosDate}</td>
                    <td>${doctorExt.sysUser.idNo}</td>
                    <td>${processCountMap[doctorExt.doctorFlow]+0}/${resultCountMap[doctorExt.doctorFlow]+0}</td>
                    <td>
                        <a style="color: blue;cursor: pointer;"
                           onclick="loadDetail('${doctorExt.doctorFlow}');">轮转详情</a>
                            <%-- 					<a style="color: blue;cursor: pointer;" onclick="processViewMain('${doctorExt.doctorFlow}');">轮转详情</a> --%>
                        <!-- 					| -->
                        <c:if test="${recDocCategoryEnumIntern.id eq doctorExt.doctorCategoryId}">
                            |
                            <a style="color: blue;cursor: pointer;"
                               onclick="internshipArchives('${doctorExt.doctorFlow}');">实习档案</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty doctorExtList}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(doctorExtList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="detail" style="height: 100%;
        background: url(<s:url value='/css/skin/${skinPath}/images/detail_shadow.jpg'/>) left repeat-y;
        width: 800px;
        padding-left:11px;
        position: fixed;
        z-index: 1000;
        display: none;
        right: -800px;
        top: 0"></div>
<div id="internshipArchives">
    <div id="archivesInfo" style="width: 98%;height: 100%;background-color: white;padding: 10px;overflow: auto;">
        <ul id="tags">
            <c:set value="res_registry_type_${globalRecTypeEnumEthics.id}" var="viewCfgKey"/>
            <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                <li class="selectTag" onclick="archivesSelTag(this,'${resRecTypeEnumEthics.id}');"
                    style="cursor: pointer;"><a>${globalRecTypeEnumEthics.name }</a></li>
            </c:if>

            <c:set value="res_registry_type_${globalRecTypeEnumDocument.id}" var="viewCfgKey"/>
            <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                <li onclick="archivesSelTag(this,'${resRecTypeEnumDocument.id}');" style="cursor: pointer;">
                    <a>${globalRecTypeEnumDocument.name }</a></li>
            </c:if>

            <c:set value="res_registry_type_${globalRecTypeEnumNursingSkills.id}" var="viewCfgKey"/>
            <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                <li onclick="archivesSelTag(this,'${globalRecTypeEnumNursingSkills.id}');" style="cursor: pointer;">
                    <a>${globalRecTypeEnumNursingSkills.name }</a></li>
            </c:if>

            <c:set value="res_registry_type_${globalRecTypeEnumClinicalEnglish.id}" var="viewCfgKey"/>
            <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                <li onclick="archivesSelTag(this,'${globalRecTypeEnumClinicalEnglish.id}');" style="cursor: pointer;">
                    <a>${globalRecTypeEnumClinicalEnglish.name }</a></li>
            </c:if>

            <c:set value="res_registry_type_${globalRecTypeEnumAppraisal.id}" var="viewCfgKey"/>
            <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                <li onclick="archivesSelTag(this,'${globalRecTypeEnumAppraisal.id}');" style="cursor: pointer;">
                    <a>${globalRecTypeEnumAppraisal.name}</a></li>
            </c:if>

            <c:set value="res_registry_type_${globalRecTypeEnumCourseScore.id}" var="viewCfgKey"/>
            <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                <li onclick="archivesSelTag(this,'${globalRecTypeEnumCourseScore.id}');" style="cursor: pointer;">
                    <a>${globalRecTypeEnumCourseScore.name}</a></li>
            </c:if>
            <%-- 				<li onclick="archivesSelTag(this,'${resRecTypeEnumAppraisal.id}');" style="cursor: pointer;"><a>实习档案</a></li> --%>
        </ul>
        <div id="tagContent" class="divContent">
        </div>
    </div>
</div>
</body>
</html>