<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(function() {
        $("#consultQuestion").on("input propertychange", function() {
            var $this = $(this),
                _val = $this.val(),
                count = "";
            if(_val.length > 500) {
                $this.val(_val.substring(0, 500));
            }
            count = 500 - $this.val().length;
            $("#text-count").text(count);
        });
    })

    function loadConsultSonType() {
        document.getElementById("zxType").style.visibility="visible ";//显示
        //获取一级单选框id
        var consultTypeId =  $('input[name="consultTypeId"]:checked').val();
        if(consultTypeId=='' || consultTypeId==null){
            $("#consultTypeSonDiv").empty();
            return;
        }
        //每次点击前，将所有二级单选框清空
        $("#consultTypeSonDiv").empty();
        var title=document.getElementById("consultTypeSonDiv");
        var consultTypeId = 'ConsultType.'+$('input[name="consultTypeId"]:checked').val();
        if (consultTypeId) {
            var url = "<s:url value='/jsres/consult/loadTitle'/>?consultTypeId=" + consultTypeId;
            $.ajax({
                type : "get",
                url : url,
                cache : false,
                success : function (data) {
                    $("#consultTypeSonDiv").empty();
                    for (var i=0;i<data.length;i++) {
                        title.innerHTML += '<input type="radio" name="consultTypeSonId" id='+ data[i].dictId +' value=' + data[i].dictId + '/>';
                        title.innerHTML += '<label for='+ data[i].dictId +' >'+data[i].dictName+'</label>';
                    }
                }
            })
        }
    }

    function toPage(page) {
        console.log(page);
        jboxStartLoading();
        page=page||1;
        $("#currentPage").val(page);
        var url = "<s:url value='/jsres/consult/myQuestion'/>"
        jboxPostLoad("myQuestion", url,$('#myQuestionForm').serialize(), false);
    }

    function addConsultInfo() {
        if(false==$("#consultInfoForm").validationEngine("validate")){
            return false;
        }
        var url = "<s:url value='/jsres/consult/addConsultInfo'/>";
        var consultInfo = $("#consultInfoForm").serialize();
        jboxPost(url,consultInfo,function (resp) {
            if ("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                jboxTip("提交成功!");
                window.parent.myQuestionForm();
            }else {
                jboxTip("提交失败!");
            }
        }, null, false);
    }
    
    function returnTo() {
        var url = "<s:url value='/jsres/consult/main'/>";
        window.open(url,"_self");
    }

    function detailConsult(consultInfoFlow) {
        var url = "<s:url value='/jsres/consult/detailConsult?consultInfoFlow='/>" + consultInfoFlow;
        jboxOpen(url,"问答详情",800,450);
    }
</script>

<div class="main_hd">
    <div style="margin: 32px">
        <h1>咨询专区</h1>
    </div>
    <div class="title_tab flex just-b" style="padding-right: 30px">
        <ul>
            <li class="tab_select"><a>我的问题</a></li>
        </ul>
        <div class="flex align-c">
            <div class="back-icon">
            </div>
            <a href="javascript:void(0);" onclick="returnTo()" style="color:#54B2E5;">返回</a>
        </div>
    </div>
</div>

<div class="main_bd" id="div_table_0" >
    <div class="cont-box bordbox">
        <form id="consultInfoForm" method="post">
            <div class="gp-textbox">
                <textarea name="consultQuestion"  id="consultQuestion" maxlength="500"  class="validate[required] gp-textarea" placeholder="请输入您的问题!" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的问题!'"></textarea>
                <div class="fs14 gp-tip"><span id="text-count">500</span>/500</div>
            </div>
            <div>
                <label>请选择所问问题类型:</label>
                <div id="consultTypeDiv">
                    <c:forEach items="${dictTypeEnumConsultTypeList}" var="dict" varStatus="status">
                        <c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
                        <input class="consultTypeRadio" type="radio" id="${dict.dictId}" value="${dict.dictId}" name="consultTypeId" onclick="loadConsultSonType()">
                        <label for="${dict.dictId}">${dict.dictName}</label>
                    </c:forEach>
                </div>
            </div>
            <div>
                <div class="td_left" id="zxType" style="visibility:hidden;">请选择具体类型：</div>
                <div id="consultTypeSonDiv"></div>
            </div>

            <div class="flex gp-formbtn">
                <input type="button" value="提&#12288;交" class="btn_green" onclick="addConsultInfo();"/>
            </div>
        </form>
        <div>
            <form id="myQuestionForm">
                <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
            </form>
            <div id="myQuestion">
                <div class="gp-history" style="margin-bottom: 40px">
                    <div>历史问题</div>
                    <ul>
                        <li>
                            <div>序号</div>
                            <div>内容</div>
                            <div>状态</div>
                            <div>操作</div>
                        </li>
                        <c:forEach items="${consultInfos}" varStatus="status" var="consultInfo">
                            <li>
                                <div>${status.index + 1}</div>
                                <div>${consultInfo.consultQuestion}</div>
                                <div>
                                    <c:if test="${consultInfo.isAnswer eq 'Y'}">已回复</c:if>
                                    <c:if test="${consultInfo.isAnswer ne 'Y'}">未回复</c:if>
                                </div>
                                <div>
                                    <a onclick="detailConsult('${consultInfo.consultInfoFlow}')">查看</a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="page" style="padding-right: 40px;">
                    <c:set var="pageView" value="${pdfn:getPageView(consultInfos)}" scope="request"></c:set>
                    <pd:pagination-jsres toPage="toPage"/>
                </div>
            </div>
        </div>
    </div>
</div>


