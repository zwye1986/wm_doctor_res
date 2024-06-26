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
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <style type="text/css">
        /*table{ margin:10px 0;border-collapse: collapse;}*/
        /*caption,th,td{height:40px;}*/
        caption {
            line-height: 40px;
            text-align: left;
            font-weight: bold;
            padding-left: 10px;
            border-bottom: 1px solid #ddd;
            color: #f60;
        }

        /*th{text-align:right;font-weight:500;padding-right:5px; color:#333;}*/
        /*td{text-align:left; padding-left:5px; color:#999;}*/
    </style>

    <script type="text/javascript">
        function editRotation(rotationFlow) {
            var title = "新增轮转方案";
            if (rotationFlow != null && rotationFlow != '') {
                title = "编辑轮转方案";
            }
            jboxOpen("<s:url value='/schTwo/template/editRotation'/>?rotationFlow=" + rotationFlow, title, 800, 500);
        }

        function rule(rotationFlow) {
            var orgFlow = $("#orgFlow").val() || "";
            window.location.href = "<s:url value='/schTwo/template/rule'/>?rotationFlow=" + rotationFlow + "&roleFlag=${param.roleFlag}&currRoleFlag=${param.currRoleFlag}&orgFlow=" + orgFlow;
        }

        function publish(rotationFlow) {
            jboxConfirm("确认发布?发布后将对所有培训医院可见!", function () {
                updateRotation({rotationFlow: rotationFlow, publishFlag: "${GlobalConstant.FLAG_Y}"});
            });
        }

        function delRotation(rotationFlow) {
            jboxConfirm("确认删除?", function () {
                updateRotation({rotationFlow: rotationFlow, recordStatus: "${GlobalConstant.FLAG_N}"});
            });
        }

        function updateRotation(rotation) {
            jboxPost("<s:url value='/schTwo/template/publishRotation'/>", rotation, function (resp) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                    search();
                    top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                }
            }, null, false);
        }

        function search() {
            $("#rotationForm").submit();
        }

        function openDetail(rotationName, rotationFlow) {
            //jboxOpenContent($("#"+rotationFlow),"轮转说明",800,300);
            var url = "<s:url value='/schTwo/template/showExplanation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow=" + rotationFlow;
            jboxOpen(url, rotationName, 800, 500);
        }

        //方案详情
        function rotationDetail(rotationFlow, title) {
            jboxOpen("<s:url value='/schTwo/template/ruleView'/>?rotationFlow=" + rotationFlow, title, 900, 500);
        }

        //记录复制所需修改的年份
        var cloneYear = 0;
        //复制方案
        function rotationClone(rotationFlow, rotationName, year) {
            cloneYear = year;
            var select = '<select onchange="window.parent.document.mainIframe.cloneYear = this.value;">' +
                    '<option value="1" ' + (cloneYear == "1" ? "selected" : "") + '>1</option>' +
                    '<option value="2" ' + (cloneYear == "2" ? "selected" : "") + '>2</option>' +
                    '<option value="3" ' + (cloneYear == "3" ? "selected" : "") + '>3</option>' +
                    '</select>';
            //请选择年限"+select+"后确认！
            jboxConfirm("确认复制方案：<font color='red'>" + rotationName + "</font>" + "？", function () {
                jboxPost("<s:url value='/schTwo/template/rotationClone'/>?rotationFlow=" + rotationFlow, null, function (resp) {
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                        top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                        location.reload(true);
                    }
                }, null, false);
            }, null);
        }

        <c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
        function save(rotationFlow) {
            var url = "<s:url value='/sys/cfg/save'/>";
            var data = $('#saveCfgForm' + rotationFlow).serialize();
            jboxPost(url, data, function (resp) {
                setEditDeptFormStatus(rotationFlow);
            }, null, false);
        }

        function toggleCfgModel() {
            $(".rotationData td:not(.rotationName),.rotationData th:not(.rotationName)").toggle();
        }

        function viewForm(form, type, typeName) {
            if (form) {
                jboxOpen("<s:url value='/res/rec/viewForm'/>?type=open&recTypeId=" + type + "&formId=" + form, typeName, 1000, 550);
            } else {
                return jboxTip("请先选择表单！");
            }
        }
        </c:if>


        $(function () {
            $(".oper").each(function () {
                if ($(this).find("a").length > 1) {
                    $(this).find("a:not(:last)").after('<label>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;</label>')
                }
            });
            $("[onclick]").click(function (e) {
                e.stopPropagation();
            });
        });
        function schRotationExport() {
            jboxOpenContent($("#toOutConfirm").html(), "导出选项", 700, 40);

        }
        function schExports(checkbox) {
            var url = "<s:url value='/res/doc/schRotationExport?'/>" + checkbox.serialize();
            jboxConfirm('确认导出吗?', function () {
                location.href = url;
                jboxContentClose();
            }, null);

        }
        function schRotationImport() {
            $("#file").click();
        }
        function checkFile(file) {
            var filePath = file.value;
            var suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
            if ("xml" == suffix) {
                $("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
                jboxConfirm("确认导入标准轮转方案?", function () {
                    var url = "<s:url value='/res/doc/checkFile'/>";
                    jboxSubmit(
                            $('#xmlForm'),
                            url,
                            function (s) {
                                top.jboxTip("新增  " + "<span style='color: red'>" + s + "</span>" + " 条方案！");
                                window.location.href = window.location.href;
                            },
                            function (resp) {

                            }, false);
                });
            } else {
                $("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
                $(file).val(null);
                jboxTip("请上传xml文件");
            }

        }
        function guanLian(flow) {
            jboxOpen("<s:url value='/schTwo/template/guanLian'/>?flow=" + flow, "关联基地", 850, 500);
        }

        function setEditDeptFormStatus(rotationFlow) {
            var isDeptForm = $('#' + rotationFlow + 'Form :selected')[0].dataset.isDeptForm == 'true';
            $('#' + rotationFlow + 'EditDeptForm').toggle(isDeptForm);
        }

        var orgFlow = '';
        function settingDeptForm(rotationFlow) {
            rotationFlow = rotationFlow || '';
            var formName = $('#' + rotationFlow + 'Form').val();
            jboxOpen('<s:url value="/res/cfg/deptFormCfgPage"/>?rotationFlow=' + rotationFlow + '&formName=' + formName + '&orgFlow=' + orgFlow,
                    '科室表单配置', 800, 510, true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="rotationForm" action="<s:url value='/schTwo/template/list'/>" method="post">
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
                    <input type="hidden" name="publishFlag" value="${param.publishFlag}"/>
                </c:if>
                <div class="queryDiv">
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.currRoleFlag}">
                        <input type="hidden" name="currRoleFlag" value="${param.currRoleFlag}"/>
                        <div class="inputDiv">
                            <label class="qlable">机构：</label>
                            <select id="orgFlow" name="orgFlow" class="qselect">
                                <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                    <option value="${org.orgFlow}"
                                            <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="inputDiv">
                        <label class="qlable">培训方案：</label>
                        <input id="rotationName" name="rotationName" type="text" class="qtext" value="${rotationName}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">人员类型：</label>
                        <select name="doctorCategoryId" class="qselect">
                            <option value="all">全部</option>
                            <c:forEach items="${recDocCategoryEnumList}" var="category">
                                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                                    <option value="${category.id}" ${schRotation.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">培训专业：</label>
                        <select id="speId" name="speId" class="qselect">
                            <option/>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}(二级专业)</option>
                            </c:forEach>
                        </select>
                    </div>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                        <div class="inputDiv">
                            <label class="qlable">发布状态：</label>
                            <select id="publishFlag" name="publishFlag" class="qselect">
                                <option/>
                                <option value="${GlobalConstant.FLAG_Y}" ${param.publishFlag eq GlobalConstant.FLAG_Y?'selected':''}>
                                    已发布
                                </option>
                                <option value="${GlobalConstant.FLAG_N}" ${param.publishFlag eq GlobalConstant.FLAG_N?'selected':''}>
                                    未发布
                                </option>
                            </select>
                        </div>
                    </c:if>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                </div>
                <div class="funcDiv">
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                        <input type="button" value="新&#12288;增" onclick="editRotation('');" class="search">
                    </c:if>
                    <c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW && GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                        <input type="button" value="配置表单" onclick="toggleCfgModel();" class="search">
                    </c:if>
                    <c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW && GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                        <input type="button" value="导出方案" onclick="schRotationExport();" class="search">
                    </c:if>
                    <c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW && GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                        <input type="button" value="导入方案" onclick="schRotationImport();" class="search">
                    </c:if>
                </div>
            </form>
        </div>
        <div id="toOutConfirm" style="display: none;">
			<span style="padding-left: 100px;">
				<label>
                    <input type="checkbox" checked="checked" disabled="disabled"/>是否导出标准科室
                    <input type="checkbox" name="standardDoc" checked="checked" value="${dictTypeEnumStandardDept.id}"
                           style="display: none;"/>
                </label>
			</span>
            <span style="padding-left: 100px;"><label><input type="checkbox" name="standardDoc"
                                                             value="${dictTypeEnumDoctorTrainingSpe.id}"/>是否导出培训专业</label></span>
            <span style="padding-left: 100px;"><input type="button" value="导&#12288;出"
                                                      onclick="top.document.mainIframe.schExports($(':checkbox:checked'));"
                                                      class="search"></span>
        </div>
        <input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
        <form id="xmlForm" method="post" enctype="multipart/form-data" style="display:none;">
            <input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
        </form>
        <!-- 		<table border="0"  cellspacing="0" cellpadding="0" style="width: 100%;margin-bottom: 10px;"> -->
        <!-- 			<caption>轮转方案</caption> -->
        <!-- 		</table> -->
        <table class="xllist rotationData" style="width: 100%;">
            <tr>
                <th width="5%">序号</th>
                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                    <th width="8%">发布状态</th>
                </c:if>
                <th width="10%">类型</th>
                <th width="20%" style="text-align: center" class="rotationName">培训方案名称</th>
                <th width="15%" style="text-align: center">培训专业</th>
                <th width="10%" style="text-align: center">年限(总时间)</th>
                <th style="text-align: center">操作</th>
                <c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
                    <th style="display: none;text-align: center;width: 20%;">表单配置</th>
                    <th style="display: none;text-align: center;width: 50%;">表单预览</th>
                </c:if>
            </tr>
            <tbody>
            <c:forEach items="${rotationList}" var="rotation" varStatus="seq">
                <tr class="body _${rotation.speId}">
                    <td style="text-align: center">${seq.index + 1}</td>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                        <td>
                            <c:if test="${GlobalConstant.FLAG_Y eq rotation.publishFlag}">
                                已发布
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_N eq rotation.publishFlag}">
                                未发布
                            </c:if>
                        </td>
                    </c:if>
                    <td>
                            ${rotation.doctorCategoryName}
                    </td>
                    <td class="rotationName" style="text-align: left;padding-left: 10px;">
                            ${rotation.rotationName}
                    </td>
                    <td>${rotation.speName}</td>
                    <td>${rotation.rotationYear}年(${mustSumMap[rotation.rotationFlow] + groupSumMap[rotation.rotationFlow] + 0}月)</td>
                    <td class="oper" style="text-align: left;padding-left: 10px;">
                        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                            <a onclick="editRotation('${rotation.rotationFlow}');" style="color: blue;cursor: pointer;">编辑</a>
                            <c:if test="${GlobalConstant.FLAG_N eq rotation.publishFlag}">
                                <a style="color: blue;cursor: pointer;"
                                   onclick="delRotation('${rotation.rotationFlow}');">删除</a>
                                <a style="color: blue;cursor: pointer;" onclick="publish('${rotation.rotationFlow}');">发布</a>
                            </c:if>
                            <c:if test="${GlobalConstant.FLAG_Y eq rotation.publishFlag}">
                            </c:if>
                            <a onclick="rotationClone('${rotation.rotationFlow}','${rotation.rotationName}','${rotation.rotationYear}');"
                               style="color: blue;cursor: pointer;">复制方案</a>
                        </c:if>
                            <%-- 							<a onclick="rotationDetail('${rotation.rotationFlow}','${rotation.rotationName}');" style="color: blue;cursor: pointer;">方案详情</a> --%>
                        <a style="color: blue;cursor: pointer;" onclick="rule('${rotation.rotationFlow}');">轮转规则</a>
                        <c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
                            <a style="color: blue;cursor: pointer;"
                               onclick="rotationDetail('${rotation.rotationFlow}','${rotation.rotationName}');">轮转详情</a>
                            <c:if test="${rotation.isOrgView eq GlobalConstant.FLAG_Y}">
                                <a style="color: blue;cursor: pointer;" onclick="guanLian('${rotation.rotationFlow}');">关联机构</a>
                            </c:if>
                        </c:if>
                        <c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag)}">
                            <a onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');" class="edit"
                               style="color: blue;cursor: pointer;">方案说明</a>
                        </c:if>
                    </td>
                    <c:if test="${sessionScope.currUser.userFlow eq GlobalConstant.ROOT_USER_FLOW}">
                        <td style="display: none;">
                            <script>
                                $(function () {
                                    setEditDeptFormStatus('${rotation.rotationFlow}');
                                });
                            </script>
                            <form id="saveCfgForm${rotation.rotationFlow}">
                                <c:set var="key" value="res_form_category_${rotation.rotationFlow}"/>
                                <input type="hidden" name="cfgCode" value="${key}">
                                <select id="${rotation.rotationFlow}Form" name="${key}"
                                        onchange="save('${rotation.rotationFlow}');">
                                    <option></option>
                                    <c:forEach items="${applicationScope.resFormDictList}" var="formDict">
                                        <option data-is-dept-form="${formDict.hasDeptForm}" value="${formDict.dictId}"
                                                <c:if test="${sysCfgMap[key] eq formDict.dictId}">selected</c:if>>${formDict.dictDesc}</option>
                                    </c:forEach>
                                </select>

                                <input type="hidden" name="${key}_ws_id" value="res">
                                <input type="hidden" name="${key}_desc" value="${rotation.rotationName}表单来源">
                            </form>
                            <a style="color: blue;cursor: pointer;" id="${rotation.rotationFlow}EditDeptForm"
                               onclick="settingDeptForm('${rotation.rotationFlow}');">编辑科室表单</a>
                        </td>
                        <td style="display: none;">
                            <c:forEach items="${preRecTypeEnumList}" var="eu">
                                <c:set var="key" value="res_${eu.id}_form_flag"/>
                                <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[key]}">
                                    <div style="float: left;">
                                        <a style="color: blue;cursor: pointer;margin: 0px 10px;"
                                           onclick="viewForm($('#${rotation.rotationFlow}Form').val(),'${eu.id}','${eu.name}');">${eu.name}</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${registryTypeEnumList}" var="eu">
                                <c:set var="key" value="res_registry_type_${eu.id}"/>
                                <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[key]}">
                                    <div style="float: left;">
                                        <a style="color: blue;cursor: pointer;margin: 0px 10px;"
                                           onclick="viewForm($('#${rotation.rotationFlow}Form').val(),'${eu.id}','${eu.name}');">${eu.name}</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${afterRecTypeEnumList}" var="eu">
                                <c:set var="key" value="res_${eu.id}_form_flag"/>
                                <c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap[key]}">
                                    <div style="float: left;">
                                        <a style="color: blue;cursor: pointer;margin: 0px 10px;"
                                           onclick="viewForm($('#${rotation.rotationFlow}Form').val(),'${eu.id}','${eu.name}');">${eu.name}</a>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty rotationList}">
                <tr>
                    <td align="center" colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>