<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="consult" value="true"/>
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

<script type="text/javascript">
    $(function() {
        $(".filter").click(function() {
            $(this).addClass("active").siblings(".active").removeClass("active")
        })
        toSearch(1);
    })

    function addConsultForm() {
        var url = "<s:url value='/jsres/consult/addConsultForm'/>";
        jboxOpen(url, "新增", 1100, 650);
    }


    function toSearch(page)
    {
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var orderBy = $("#orderBy").attr("value");
        var url = "<s:url value='/jsres/consult/policyInfoManage'/>?orderBy="+orderBy+"&currentPage="+page
        jboxPostLoad("searchConent", url, $("#searchForm").serialize(), true);
    }

    function searchByOrder(orderBy) {
        jboxStartLoading();
        var url = "<s:url value='/jsres/consult/policyInfoManage'/>?currentPage=1&orderBy="+orderBy;
        jboxPostLoad("searchConent", url, $("#searchForm").serialize(), true);
    }

    function checkAll() {
        var all = document.getElementById("all");
        var mychk = document.getElementsByName("mychk");
        if (all.checked == true) {
            if (mychk.length) {
                for (var i = 0; i < mychk.length; i++) {
                    mychk[i].checked = true;
                }
            }
            mychk.chcked = true;
        } else {
            if (mychk.length) {
                for (var i = 0; i < mychk.length; i++) {
                    mychk[i].checked = false;
                }
            }
        }
    }

    function deleteAll() {
        var num = $(".check:checked").length;
        if(num==0){
            jboxTip("请至少选择一个删除项");
            return;
        }
        jboxConfirm("删除所选问答吗？", function () {
            var array = new Array();
            $.each($('input:checkbox[name=mychk]'),function(){
                if(this.checked){
                    array.push($(this).val());
                }
            });
            var url = "<s:url value='/jsres/consult/deleteAll'/>";
            requestDate = {"chkList":JSON.stringify(array)};
            jboxPost(url,requestDate,function (resp) {
                if ("${GlobalConstant.DELETE_SUCCESSED}" == resp){
                    jboxTip("删除成功!");
                    toPage(1);
                    setTimeout(function(){jboxClose()},2000);
                }else {
                    jboxTip("删除失败!");
                }
            }, null, false);
        });
    }
</script>

<div class="main_bd" id="">
    <div class="div_search">
        <form id="searchForm">
            <div class="flex poli-serchbox" style="margin-top: 5px;padding: 0;">
                <div class="poli-serch">
                    <input type="text" name="consultQuestion" id="consultQuestion" placeholder="请输入问题" onfocus="this.placeholder=''" onblur="this.placeholder='请输入问题'" style="width: 240px;" />
                </div>
                <div class="serch-icon"  onclick="toSearch(1)">
                    <i></i>
                </div>
            </div>
            <div class=" poli-editor">
                <input type="button" value="新增" onclick="addConsultForm()"/>
                <input type="button" value="批量删除" onclick="deleteAll()" />
            </div>
            <div class="poli-select fs14 flex just-b bordbox">
                <label class="flex align-c">
                    <input type="checkbox" id="all" onclick="checkAll()"/>全选
                </label>
                <div class="flex poli-filter">
                    筛选:
                    <div class="filter active" name="time" onclick="searchByOrder('time')">时间倒序</div>
                    <div class="filter" name="number" onclick="searchByOrder('number')">浏览量</div>
                </div>
            </div>
        </form>
    </div>

    <div id="searchConent">
        <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
    </div>

</div>