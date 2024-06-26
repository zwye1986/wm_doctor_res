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
    <script type="text/javascript">
        var mainFrom={};
        var detailMap={};
        function initData()
        {
            mainFrom.mainFlow="${costCfgMain.mainFlow}";
            mainFrom.typeId="${costCfgMain.typeId}";
            mainFrom.teaCost="${costCfgMain.teaCost}";
            mainFrom.kmCost="${costCfgMain.kmCost}";
            mainFrom.headCost="${costCfgMain.headCost}";
            mainFrom.speCost="${costCfgMain.speCost}";
            <c:forEach items="${resCostCfgDetails}" var="resCostCfgDetail">
                var item={};
                item.detailFlow="${resCostCfgDetail.detailFlow}";
                item.mainFlow="${resCostCfgDetail.mainFlow}";
                item.itemId="${resCostCfgDetail.itemId}";
                item.itemName="${resCostCfgDetail.itemName}";
                item.teaCost="${resCostCfgDetail.teaCost}";
                item.kmCost="${resCostCfgDetail.kmCost}";
                item.headCost="${resCostCfgDetail.headCost}";
                item.speCost="${resCostCfgDetail.speCost}";
                detailMap[item.itemId]=item;
            </c:forEach>
        }
        $(document).ready(function(){
            initData();
        });
        function save() {
            if (false == $("#formulaForm").validationEngine("validate")) {
                return;
            }
            var bean={};
            bean.mainFrom=mainFrom;
            mainFrom.mainFlow=$("[name='mainFlow']").val();
            mainFrom.typeId=$("[name='typeId']").val();
            mainFrom.teaCost=$("[name='teaCost']").val();
            mainFrom.kmCost=$("[name='kmCost']").val();
            mainFrom.headCost=$("[name='headCost']").val();
            mainFrom.speCost=$("[name='speCost']").val();
            mainFrom.startDate=$("[name='startDate']").val();
            mainFrom.endDate=$("[name='endDate']").val();

            bean.detailMap=detailMap;


            var url = "<s:url value='/res/performanceManage/savePerformanceFormula'/>";
            //console.log(bean);
            jboxConfirm($("[name='endDate']").val() < $("[name='nowDate']").val()?"设置时间小于当前时间，确认保存后不可编辑！":"确认保存？",function(){
                jboxPostJson(url , JSON.stringify(bean) , function(){
                    window.parent.frames['mainIframe'].window.toPage(1);
                    window.location.reload(true);
                } , null , true);
            })
        }
        function doClose(){
            jboxClose();
        }
        function search(typeId,itemId){
           // console.log(itemId);
            var detail=detailMap[itemId]||{};
            $("[name='teaCost2']").val(detail['teaCost']||"");
            $("[name='kmCost2']").val(detail['kmCost']||"");
            $("[name='headCost2']").val(detail['headCost']||"");
            $("[name='speCost2']").val(detail['speCost']||"");
        }
        function setDetail(obj,name) {
            var v=$(obj).val();
            //var v2 = parseInt(v);
            // if(isNaN(v2)){//判断v只能是数值
            //      $(obj).val("");
            //      jboxTip("只能为数值！");
            // }
            var itemId=$("[name='itemId']").val();
            var options=$(".itemId option:selected");
            var itemName=options.text();
//            console.log(itemName);
            var detail=detailMap[itemId]||{};
            detail.itemId=itemId;
            detail.itemName=itemName;
            detail[name]=v;
            detailMap[itemId]=detail;
        }
    </script>
</head>
<body>

<form id="formulaForm" style="position: relative">
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table cellpadding="0" style="width: 100%;" cellspacing="0" class="basic">
                        <tr>
                            <th>标准配置：</th>
                            <input name="mainFlow" type="hidden" value="${costCfgMain.mainFlow}">
                            <input name="nowDate" type="hidden" value="${pdfn:getCurrDateTime('yyyy-MM-dd')}">
                            <td></td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>带教老师：</th>
                            <td>
                                <input class="validate[required,custom[number],min[0]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="teaCost" value="${costCfgMain.teaCost}" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>教学秘书：</th>
                            <td>
                                <input class="validate[required,custom[number],min[0]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="kmCost" value="${costCfgMain.kmCost}" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>教学主任：</th>
                            <td>
                                <input class="validate[required,custom[number],min[0]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="headCost" value="${costCfgMain.headCost}" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>专业主任：</th>
                            <td>
                                <input class="validate[required,custom[number],min[0]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="speCost" value="${costCfgMain.speCost}" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th>个性配置：</th>
                            <td></td>
                        </tr>
                        <tr>
                            <c:if test="${typeId eq 'ActivityType'}">
                            <th>教学活动：</th>
                            <td>
                                <select name="itemId" class="xlselect itemId" onchange="search('${typeId}',this.value)">
                                    <option value=""></option>
                                    <c:forEach items="${dictTypeEnumActivityTypeList}" var="dict">
                                        <option value="${dict.dictId}" ${param.itemId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                                <input name="typeId" type="hidden" value="${typeId}">
                            </td>
                            </c:if>
                            <c:if test="${typeId eq 'LectureType'}">
                                <th>讲&#12288;&#12288;座：</th>
                                <td>
                                    <select name="itemId" class="xlselect itemId" onchange="search('${typeId}',this.value)">
                                        <option value=""></option>
                                        <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">
                                            <option value="${dict.dictId}" ${param.itemId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    <input name="typeId" type="hidden" value="${typeId}">
                                </td>
                            </c:if>
                            <c:if test="${typeId eq 'DoctorTrainingSpe'}">
                                <th>专业基地：</th>
                                <td>
                                    <select name="itemId" class="xlselect itemId" onchange="search('${typeId}',this.value)">
                                        <option value=""></option>
                                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                            <option value="${dict.dictId}" ${param.itemId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                    <input name="typeId" type="hidden" value="${typeId}">
                                </td>
                            </c:if>
                        </tr>
                        <tr>
                            <th>带教老师：</th>
                            <td>
                                <input class="validate[custom[number]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="teaCost2" onblur="setDetail(this,'teaCost')" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th>教学秘书：</th>
                            <td>
                                <input class="validate[custom[number]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="kmCost2" onblur="setDetail(this,'kmCost')"  type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th>教学主任：</th>
                            <td>
                                <input class="validate[custom[number]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="headCost2" onblur="setDetail(this,'headCost')" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th>专业主任：</th>
                            <td>
                                <input class="validate[custom[number]] xltext" <c:if test="${flag eq 'search'}">readonly</c:if> name="speCost2"  onblur="setDetail(this,'speCost')" type="text"/>元/月
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>有效时间段：</th>
                            <td>
                                <input type="text" id="startDate" name="startDate" value="${costCfgMain.startDate}" class="validate[required] xltext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">~
                                <input type="text" id="endDate" name="endDate" value="${costCfgMain.endDate}" class="validate[required] xltext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                            </td>
                        </tr>
                    </table>
                    <div class="button" >
                        <c:if test="${flag ne 'search'}">
                            所有数据只有点击保存才生效！
                            <br>
                            <input class="search" type="button" value="保&#12288;存" onclick="save();"/>
                            <input type="button" class="search" value="取&#12288;消" onclick='doClose();'/>
                        </c:if>
                        <c:if test="${flag eq 'search'}">
                            <input type="button" class="search" value="关&#12288;闭" onclick='doClose();'/>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>