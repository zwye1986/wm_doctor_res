
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
    <script type="text/javascript">

        function reCheck(obj) {
            var td = $(obj).parents('td');//查找当前对象
//            var td = $(obj).parent('td');//查找当前对象
            $(td).find("a").hide();//遍历每个a标签元素并隐藏
            $(td).find("input[name='file']").show();//遍历每个input标签[name为file]元素并隐藏
        }

        function saveIrbList() {
            //校验必填项
//            if(false==$("#srmIrbForm").validationEngine("validate")){
//                return ;
//            }
            var datas = [];
            var tb = $('#tb');//query选择器:id为tb的元素
            var trs = tb.children();//.children()方法获取tbody的子元素
            $.each(trs, function (i, n) {//jquery迭代器,用于循环数组和json  i表示索引   n表示当前循环的元素
                var irbTypeId = $(n).find("input[name='irbTypeId']").val().trim();
                var irbApplyDate = $(n).find("input[name='irbApplyDate']").val().trim();
                var irbReviewDate = $(n).find("input[name='irbReviewDate']").val().trim();
                var irbFlow = $(n).find("input[name='irbFlow']").val();
                var data = {//json对象   {key:value}
                    "irbFlow":irbFlow,
                    "irbTypeId": irbTypeId,
                    "irbApplyDate": irbApplyDate,
                    "irbReviewDate": irbReviewDate
                };
                datas.push(data);//数组的push方法

            });
            var requestData = JSON.stringify(datas);//将数组对象转换成json字符串
            $("#jsondata").val(requestData);
            var url = "<s:url value='/srm/ethical/save'/>";

            jboxSubmit($("#srmIrbForm"),url,function(resp){
                if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
                    jboxClose();
                    window.parent.frames['mainIframe'].window.searchPubProjInfo();
                }
            },null,true);

            /*jboxPostJson(url, requestData, function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功！");
                    window.parent.frames['mainIframe'].window.searchPubProjInfo();
                    jboxClose();
                }
            }, null, true);*/
        }
        $(document).ready(function(){
            if( ${empty flag}){
                $("table").find("tr").each(function(){
                    $(this).find("td").each(function(){
                        $(this).find("input").attr("disabled","disabled");
                        $(this).find("input").removeAttr("onclick");
                        $(this).find("input").removeClass("validate[required]");
                        $(this).find("input[type='file']").hide();
                    })
                });
//
            }
        });
    </script>
</head>
<body>

<form id="srmIrbForm"  enctype="multipart/form-data" method="POST" style="padding-left: 30px;height: 100px;" >
    <input value="" id="jsondata" name="jsondata" type="hidden">
    <input type="hidden" name="projFlow" value="${projFlow}">
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <%--<div class="tagContent selectTag" id="tagContent0">--%>
                     <table width="100%" cellpadding="0" cellspacing="0" class="basic">
                            <thead>
                            <tr>
                                <th style="display:none">伦理审查类别ID</th>
                                <th width="30%">伦理审查类别</th>
                                <th width="20%">送审时间</th>
                                <th width="20%">审查时间</th>
                                <th width="30%">审查报告</th>
                            </tr>
                            </thead>
                                <tbody id="tb">
                                <c:forEach items="${srmIrbTypeEnumList}" var="irbType"  >

                                        <c:if test="${empty irbMap}">
                                            <tr>
                                            <td style="display:none">
                                                <input type="hidden" value="${irbType.id}" name="irbTypeId"/>
                                            </td>
                                            <td>${irbType.name }</td>
                                            <td>
                                                <input  type="text"   name="irbApplyDate" value="${param.irbApplyDate}" class="ctime <%--validate[required]--%>"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                            </td>
                                            <td>
                                                <input  type="text"   name="irbReviewDate" value="${param.irbReviewDate}" class="ctime <%--validate[required]--%>"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                            </td>

                                            <td>
                                                <input type="file" id="file" class="<%--validate[required]--%>" name="file"/>
                                            </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${not empty irbMap}">
                                                <tr>
                                                <td style="display:none">
                                                    <input type="hidden" value="${irbType.id}" name="irbTypeId"/>
                                                    <input type="hidden" value="${irbMap[irbType.id].irbFlow}" name="irbFlow">
                                                </td>
                                                <td>${irbType.name }</td>
                                                <td>
                                                    <input type="text"   name="irbApplyDate" value="${irbMap[irbType.id].irbApplyDate}"
                                                           class="ctime validate[required]"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                                </td>
                                                <td>
                                                    <input type="text"   name="irbReviewDate" value="${irbMap[irbType.id].irbReviewDate} "
                                                           class="ctime validate[required]"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                                </td>
                                                <td>
                                                        <c:if test="${empty fileMap}">
                                                            <input type="file" id="file" name="file" class="validate[required]"/>
                                                        </c:if>
                                                    <input type="hidden" value="${fileMap[irbType.id].fileFlow}" name="fileFlow">
                                                        <c:if test="${not empty fileMap}">
                                                            <c:choose>
                                                                <c:when test="${not empty fileMap[irbType.id]}">
                                                                    <a  href="${sysCfgMap['upload_base_url']}${fileMap[irbType.id].filePath}" target="_blank">[查看附件]</a>
                                                                    <input type="file" class="file1" name="file" style="display: none"/>
                                                                    <c:if test="${not empty flag}">
                                                                    <a id="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>
                                                                    </c:if>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="file" id="file" name="file" class="validate[required]"/>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:if>
                                                 </td>
                                                </tr>
                                        </c:if>
                                </c:forEach>
                                </tbody>
                        </table>
                    <c:if test="${not empty flag}">
                        <div class="button" style="width: 100%">
                            <input class="search" type="button" value="保&#12288;存" onclick="saveIrbList();" />
                            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
                        </div>
                    </c:if>
                <%--</div>--%>
            </div>
        </div>
    </div>
</form>
</body>
</html>