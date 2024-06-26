
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
        function save(){
            if($("#newScheme").validationEngine("validate")){
                var url = "<s:url value='/srm/fund/scheme/saveProjSourceScheme'/>?schemeFlow=${srmFundScheme.schemeFlow}";
                var trs = $("#sourceSchemeInfo").children();
                var datas = [];
                var schemeFlow = $("#schemeFlow").val();
                var schemeName = $("#schemeName").val();
                $.each(trs, function (i, n) {
                    var sourceFlow = $(n).find("input[name='sourceFlow']").val();
                    var projFirstSourceId = $(n).find("select[name='projFirstSourceId']").val();
                    var projFirstSourceName =  $(n).find("input[name='projFirstSourceName']").val();
                    var projSecondSourceName = $(n).find("input[name='projSecondSourceName']").val();
                    var projSecondSourceId = $(n).find("select[name='projSecondSourceId']").val();

                    var data = {
                        "schemeFlow": schemeFlow,
                        "schemeName": schemeName,
                        "sourceFlow": sourceFlow,
                        "projFirstSourceId": projFirstSourceId,
                        "projFirstSourceName": projFirstSourceName,
                        "projSecondSourceName": projSecondSourceName,
                        "projSecondSourceId": projSecondSourceId
                    };
                    datas.push(data);
                });
                var requestData = JSON.stringify(datas);
                jboxPostJson(url ,requestData, function(){
                    $("#schemeForm",window.parent.frames['mainIframe'].document).submit();
                    jboxClose();
                } , null , true);
            }
        }
        function searchProjCategory(obj){
            var dictFlow = $(obj).find("option:selected").attr("dictFlow");
            var dictName = $(obj).find("option:selected").text();
            var dictTypeId = $(obj).val();
            $(obj).next().val(dictName);
            var data = {
                dictFlow : dictFlow,
                dictTypeId : dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url , data , function(data){
                var sel = $(obj).parent().next().find("select[name=projSecondSourceId]")
                $(sel).find("option[value != '']").remove();
                var dataObj = data;
                for(var i =0;i<dataObj.length;i++){
                    var cId =dataObj[i].dictId;
                    var cName =dataObj[i].dictName;
                    $option =$("<option></option>");
                    $option.attr("value",cId);
                    $option.text(cName);
                    $(sel).append($option);
                }
            } , null , false);
        }

        function attrOptionText(obj){
            var text = $(obj).find("option:selected").text();
//            alert(text);
            if(text) {
//                $("input[name='" + name + "']").val(text);
                $(obj).next().val(text);

            }
        }
        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
            }
        }

        function del(obj) {
                jboxConfirm('确认删除？', function () {
                    $(obj).closest('tr').remove();
                    reSeq('sourceSchemeList');
                }, null);
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="newScheme" action="<s:url value="/srm/fund/scheme/saveScheme"/>" method="post">
                <input type="hidden" id="schemeFlow" name="schemeFlow" value="${srmFundScheme.schemeFlow}" />
                <input type="hidden" id="schemeName" name="schemeName" value="${srmFundScheme.schemeName}" />
                <table class="basic" style="width: 100%;margin-top: 10px;">
                    <tr>
                        <th colspan="8" style="text-align: left;padding-left: 15px;">
                            添加项目来源
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('sourceSchemeList');"/>
							<%--<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('learningPapersCensus');"/>--%>
						</span>
                        </th>
                    </tr>
                    <tr>
                        <td style="text-align: center;" width="10%">序号</td>
                        <td style="text-align: center;" width="35%"><font color="red">*</font>一级来源</td>
                        <td style="text-align: center;" width="35%"><font color="red">*</font>二级来源</td>
                        <td style="text-align: center;" width="20%"><font color="red">*</font>操作</td>
                    </tr>
                    <tbody id="sourceSchemeInfo" class="sourceSchemeList">
                        <c:forEach var="sourceScheme" items="${sourceSchemeList}"
                                   varStatus="status">
                            <tr>
                                <td class="seq">${status.count}
                                    <input type="hidden" name="sourceFlow" value="${sourceScheme.sourceFlow}" />
                                </td>
                                <td>${sourceScheme.projFirstSourceName}</td>
                                <td>${sourceScheme.projSecondSourceName}</td>
                                <td><a href="javascript:void(0)" onclick="del(this);">删除</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
        <div class="button" style="width: 400px;">
            <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
            <input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
<table id="template" style="display: none">
    <tr id="sourceSchemeList">
        <td class="seq"></td>
        <td>
            <select id="projFirstSourceId" name="projFirstSourceId" class="xlselect validate[required]" style="width: 90%;" onchange="searchProjCategory(this);">
                <option value="">请选择</option>
                <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                    <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" >${dict.dictName}</option>
                </c:forEach>
            </select>
            <input type="hidden" name="projFirstSourceName" />
        </td>
        <td>
            <select name="projSecondSourceId"  style="width: 90%;" class="xlselect validate[required]" onchange="attrOptionText(this);">
                <option value="">请选择</option>
            </select>
            <input type="hidden" name="projSecondSourceName" />
        </td>
        <td><a href="javascript:void(0)" onclick="del(this);">删除</a></td>
    </tr>
</table>