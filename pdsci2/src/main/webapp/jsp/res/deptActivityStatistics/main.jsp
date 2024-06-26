<%@include file="/jsp/common/doctype.jsp" %>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="slideRight" value="true"/>
    </jsp:include>
    <style type="text/css">
        .module-tabs{ height:49px; line-height:49px; font-size:15px; color:6e6e6e; border-bottom:1px solid #dadada}
        .module-tabs li{ float:left;  cursor:pointer;display:block;}
        .module-tabs .type li{padding:0 15px;}
        .module-tabs li.on{ height:48px; border-bottom:2px solid blue; color:blue;}
        .module-tabs li a{padding:8px 15px;}
        .module-tabs li a.hove1{ height:48px; color:#fff; padding:8px 15px;  background:blue;}
        .module-tabs li a:hover{color:#000;text-decoration:none;}
        .fl{ float:left;}
        .module-content{ margin-top:10px;}
        .module-content ul li{ display:none;}
        .module-content ul li dl{ margin-left:20px; width:368px;}
        .module-content ul li{background:#fff; overflow:hidden; border:1px solid #dfdfdf; position:relative; margin-bottom:10px;}
        .module-content ul li dt{ line-height:40px; color:#000; font-size:15px; margin-bottom:12px;}
        .module-content li dd p{ color:#9e9e9e; line-height:25px;}
        .module-content dd div{ line-height:30px;}
        .module-content dd div img{ vertical-align:middle;}
        .module-content .btn{ position:absolute; right:10px; bottom:10px; height:30px; padding:0 24px;}
        .module-content dt .fr{margin-right:10px;}
        .module-content .lessonimg{border:4px solid #fff;cursor:default;}
        .module-content .graph{width:142px;background:#dadada;vertical-align:middle;height:11px;border-radius:5px;display:inline-block;}
        table.basic th,table.basic td{text-align: center;padding: 0;}
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            //默认展示基本信息
            <c:if test="${deptActivityTypeEnumDept.id  eq planTypeId }">
                $("#${deptActivityItemTypeEnumJXCFAP.id}").click();
            </c:if>
            <c:if test="${deptActivityTypeEnumScientific.id  eq planTypeId }">
            $("#${deptActivityItemTypeEnumJTBKAP.id}").click();
            </c:if>
        });
        function selTag(typeId,obj){
            $(".selectTag").removeClass("selectTag");
            $(obj).parent().addClass('selectTag');
            $("#itemTypeId").val(typeId);
            $("input[name='isEval']").attr("checked","checked");
            toPage(1);
        }

        function toPage(page) {
            page=page||1;
            $("#currentPage").val(page);
            jboxStartLoading();
            jboxPostLoad("doctorListZi","<s:url value='/res/deptActivityStatistics/lists'/>",$("#searchForm").serialize(),false);
        }
        function exportInfo() {
            var url = "<s:url value='/res/deptActivityStatistics/exportInfo'/>" ;
            jboxExpLoading($("#searchForm"),url);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="top-tab">
            <div class="clearfix" style="padding-top: 10px;">
                <ul id="tags">
                    <c:forEach items="${deptActivityItemTypeEnumList}" var="type">
                        <c:if test="${deptActivityItemTypeEnumCKKHAP.id!=type.id and deptActivityItemTypeEnumDSBGHAP.id!=type.id and
                            deptActivityItemTypeEnumJYSKHAP.id!=type.id}">
                            <c:if test="${role eq 'doctor'}">
                                <c:if test="${(sysCfgMap['dept_activity_type'] eq 'Y' and type.type eq planTypeId)
                                                ||('Y'==type.isCfg and type.type eq planTypeId) }">
                                    <li>
                                        <a id="${type.id}" onclick="selTag('${type.id}',this);" href="javascript:void(0)">${type.name}</a>
                                    </li>
                                </c:if>
                            </c:if>
                            <c:if test="${role ne 'doctor'}">
                                <c:if test="${type.type eq planTypeId }">
                                    <li>
                                        <a id="${type.id}" onclick="selTag('${type.id}',this);" href="javascript:void(0)">${type.name}</a>
                                    </li>
                                </c:if>
                            </c:if>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div id="tagContent">
                <div class="title1 clearfix">
                    <form id="searchForm">
                        <input type="hidden" id="currentPage" name="currentPage"/>
                        <input type="hidden" id="role" name="role" value="${role}"/>
                        <input type="hidden" id="itemTypeId" name="itemTypeId"/>
                        <c:if test="${role ne 'doctor'}">
                            <div class="queryDiv">
                                <div class="inputDiv">
                                    <label class="qlable">科&#12288;&#12288;室：</label>
                                    <select id="deptFlow" name="deptFlow" class="qselect">
                                        <option></option>
                                        <c:forEach items="${deptList}" var="dept">
                                            <option value="${dept.deptFlow}" >${dept.deptName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="inputDiv">
                                    <label class="qlable">月&#12288;&#12288;度：</label>
                                    <input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM'})" name="planDate" class="qtext" readonly="readonly"/>
                                </div>
                                <div class="lastDiv" style="max-width: 200px;min-width: 200px;">
                                    <input class="searchInput" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;
                                    <input class="searchInput" type="button" value="导&#12288;出" onclick="exportInfo();"/>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${role eq 'doctor'}">
                            <div class="queryDiv">
                                <div class="qcheckboxDiv">
                                    <label class="qlable">
                                    <input type="checkbox" name="isEval" checked value="${GlobalConstant.FLAG_Y}"onchange="toPage(1);">
                                    已评价
                                </label>
                            </div>
                        </c:if>
                    </form>
                </div>
                <div id="doctorListZi" style="padding-left: 5px;padding-right: 5px;">

                </div>
        </div>
    </div>

</div>
</body>
</html>