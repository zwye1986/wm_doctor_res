<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="consult" value="true"/>
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
    .searchTable>tbody>tr>td>input[type:text]{
        margin-right: 100px;
    }
    .searchTable>tbody>tr>td>select{
        margin-right: 100px;

    }
</style>
<script type="text/javascript">
    $(function() {
        $('textarea').each(function() {
            this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
        }).on('input', function() {
            this.style.height = 'auto';
            this.style.height = (this.scrollHeight) + 'px';
        });
        toSearch(1);
    })

    function toSearch(page)
    {
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var url = "<s:url value='/jsres/consult/enquireInfoManage'/>";
        jboxPostLoad("searchConent", url, $("#searchForm").serialize(), true);
    }

    //加载二级菜单
    function loadConsultType(){
        var consultTypeId = $("#consultTypeId").val();
        if(consultTypeId=='' || consultTypeId==null){
            $("#consultTypeSonId").empty();
            return;
        }
        //首先要刷新一下 下拉框
        $("#consultTypeSonId").empty();
        var paramConsultId = '${consultInfo.consultTypeSonId}';
        var title = $("#consultTypeSonId");

        var consultTypeId = 'ConsultType.'+$("#consultTypeId").val();
        if (consultTypeId) {
            var url = "<s:url value='/jsres/consult/loadTitle'/>?consultTypeId=" + consultTypeId;
            // jboxStartLoading();
            jboxGet(url, null, function (data) {
                title.append('<option  value="">全部</option>');
                for (var i=0;i<data.length;i++) {
                    var sel = "";
                    if (data[i].dictId == paramConsultId) {
                        sel = "selected";
                    }
                    title.append('<option value="' + data[i].dictId + '" ' + sel + '>' + data[i].dictName + '</option>');
                }
            }, null, false);
        }
    }
</script>

<div class="main_bd" id="" >
    <div class="div_search">
        <form id="searchForm">
            <table class="searchTable" style="width: auto">
                <tr>
                    <td class="td_left">
                        问&#12288;&#12288;题：
                    </td>
                    <td>
                        <input type="text" id="consultQuestion" name="consultQuestion" value="${param.consultQuestion}" class="input" />
                    </td>
                    <td class="td_left" width="100px;">咨询类型：</td>
                    <td>
                        <select id="consultTypeId" name="consultTypeId" onchange="loadConsultType()" class="select" style="width: 127px;">
                            <option  value="">全部</option>
                            <c:forEach items="${dictTypeEnumConsultTypeList}" var="dict">
                                <c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
                                <option value="${dict.dictId}">${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left" width="100px">具体类型：</td>
                    <td>
                        <select id="consultTypeSonId" name="consultTypeSonId" class="select" style="width: 127px;">
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        角&#12288;&#12288;色：
                    </td>
                    <td>
                        <select name="consultQuestionRoleId" class="select" style="width: 127px;">
                            <option value="">全部</option>
                            <option value="Doctor" <c:if test="${consultQuestionRoleId eq 'Docor'}">selected</c:if>>学员</option>
                            <option value="Admin" <c:if test="${consultQuestionRoleId eq 'Admin'}">selected</c:if>>基地</option>
                        </select>
                    </td>
                    <td colspan="2"><input type="checkbox" id="isAnswer" name="isAnswer" onclick="toSearch(1)"><label>仅显示未回复的问题</label></td>
                    <td colspan="2"><input type="button" style="margin-left: 0px" class="btn_green" onclick="toSearch(1)" value="查&#12288;询"></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </div>

    <div id="searchConent" name="searchConent">
        <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
    </div>
</div>