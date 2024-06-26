<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(function(){
            <c:forEach items="${oscaOrgSpeList}" var="spe">
                $("[typeId='${spe.typeId}'][speId='${spe.speId}']").attr("checked","checked");
            </c:forEach>
        });
        function saveSpes(){
            var dataList = [];
            $("input[type='checkBox']:checked").each(function(){
                var data = {
                    typeId:$(this).attr("typeId"),
                    typeName:$(this).attr("typeName"),
                    speId:$(this).attr("speId"),
                    speName:$(this).attr("speName")
                }
                dataList.push(data);
            });
            var jsonData = {"jsonData":dataList,"orgFlow":"${orgFlow}"};
            console.log(jsonData);
            var url = "<s:url value='/osca/orgSpeGlobal/saveEditInfo2'/>";
            jboxPost(url,"jsonData="+JSON.stringify(jsonData), function(resp) {
                if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                    jboxTip(resp);

                    setTimeout(function(){
                        window.parent.frames['mainIframe'].location.reload(true);
                        jboxClose();
                    },2000);
                }else{
                    jboxTip(resp);
                }
            },null,false);
        }

        //模糊查询
        function likeSearch(name) {
            if (name) {
                $("[speNameTr]").hide();
                $("[speNameTr*='" + name + "']").show();
            } else {
                $("[speNameTr]").show();
            }
        }

        function search(typeId) {
            if (typeId) {
                $("[typeIdTr]").hide();
                $("[typeIdTr='" + typeId + "']").show();
            } else {
                $("[typeIdTr]").show();
            }
        }
    </script>
</head>
<body>
    <div style="width: 100%;height: 40px">
        <span>培训类型：</span>
        <select name="trainingTypeId" class="xlselect" onchange="search(this.value)">
            <option value="">全部</option>
            <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                <option value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>&#12288;&#12288;
        <span>查找：</span>
        <input placeholder="按专业查找" class="xltext" type="text" value="" onkeyup="likeSearch(this.value);"/><br/>
    </div>
    <div id="base" style="overflow: auto;height:75%">
        <table style="border:1px solid #e3e3e3; width:100%;" align="center">
            <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                    <tr speNameTr="${scope.dictName}" typeIdTr="${dict.dictId}">
                        <td style="text-align: left; border-right:1px solid #e3e3e3; border-bottom:1px solid #e3e3e3;line-height:25px; height:30px;width: 50%">
                            &#12288;${dict.dictName}
                        </td>
                        <td style="text-align: left; border-right:1px solid #e3e3e3; border-bottom:1px solid #e3e3e3;line-height:25px; height:30px;width: 50%">
                            &#12288;<label><input type="checkbox" typeId="${dict.dictId}" typeName="${dict.dictName}"
                                speId="${scope.dictId}" speName="${scope.dictName}"/>&nbsp;${scope.dictName}</label>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
        </table>
    </div>
    <div>
        <font class="log_tips" style="color: red"></font>
        <br/>
        <p align="center" style="margin-top: 10px;">
            <input type="button" id="saveBtn" value="保&#12288;存" onclick="saveSpes();" class="search"/>
            <input type="button" value="关&#12288;闭" onclick="jboxClose();" class="search"/>
        </p>
    </div>
</body>
</html>
