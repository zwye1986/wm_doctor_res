<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">

    /**
     * 对Date的扩展，将 Date 转化为指定格式的String
     * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
     * eg:
     * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
     * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
     * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
     * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
     * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
     */
    Date.prototype.pattern = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        var week = {
            "0": "\u65e5",
            "1": "\u4e00",
            "2": "\u4e8c",
            "3": "\u4e09",
            "4": "\u56db",
            "5": "\u4e94",
            "6": "\u516d"
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        if (/(E+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
    function getNowFormatDate() {
        var date = new Date();
        return date.pattern("yyyy-MM-dd HH:mm:ss");
//		var seperator1 = "-";
//		var seperator2 = ":";
//		var month = date.getMonth() + 1;
//		var strDate = date.getDate();
//		var m=""+ month.toString();
//		if (parseInt(month)  <= 9) {
//			m = "0" + month.toString();
//		}
//		var d=""+strDate.toString();
//		if (parseInt(strDate) <= 9) {
//			d=strDate = "0" + strDate.toString();
//		}
//		var currentdate = date.getFullYear() + seperator1 + m + seperator1 + d
//				+ " " + date.getHours() + seperator2 + date.getMinutes()
//				+ seperator2 + date.getSeconds();
//		return currentdate;
    }
    var timer = setInterval(showButton, 1000);
    function showButton() {
        var nottime = getNowFormatDate();
//		console.log(nottime);
        var maxTime = "2017-03-26 00:00:00";
        if (nottime >= maxTime) {
            //$(".audit").hide();
            clearInterval(timer);
        }
    }


    $(document).ready(function () {
        clearInterval(timer);
        showButton();
        timer = setInterval(showButton, 1000);
    });
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        if ("${baseFlag}" == "0") {
            $("#baseFlag").hide();
        }
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>

    });
    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }

    function turnOutOrg(recordFlow, auditFlag, doctorFlow) {
        var title = "不通过";
        var changeStatusId = "${jsResChangeApplyStatusEnumOutApplyUnPass.id}";//转出审核不通过
        if ("${GlobalConstant.FLAG_Y}" == auditFlag) {
            title = "通过";
            changeStatusId = "${jsResChangeApplyStatusEnumInApplyWaiting.id}";//待转入审核
        }
        if (title == "不通过") {
            var url = "<s:url value='/jsres/manage/auditInfo'/>?recordFlow=" + recordFlow + "&changeStatusId=" + changeStatusId + "&doctorFlow=" + doctorFlow;
            jboxOpen(url, "审核意见", 680, 400);
        } else {
            jboxConfirm("确认转出" + title + "?", function () {
                var url = "<s:url value='/jsres/manage/turnOutOrg'/>?recordFlow=" + recordFlow + "&changeStatusId=" + changeStatusId + "&doctorFlow=" + doctorFlow;
                jboxPost(url, null, function (resp) {
                    if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                        searchTurnOut();
                    }
                }, null, true);
            });
        }
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchOut();
    }
    function searchOut() {
        var url = "<s:url value='/jsres/manage/turnOutMain'/>";
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        jboxPostLoad("doctorContent", url, $("#outForm").serialize(), true);
    }
</script>

<div class="main_bd">
    <div class="div_search">
        <form id="outForm">
            <input type="hidden" name="currentPage" id="currentPage"/>


            <div class="form_search">

                <div class="form_item">
                    <div class="form_label">姓&#12288;&#12288;名：</div>
                    <div class="form_content">
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                    </div>
                </div>

                <div class="form_item">
                    <div class="form_label">年&#12288;&#12288;级：</div>
                    <div class="form_content">
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                    </div>
                </div>


                 <c:if test="${JointOrgCount ne '0'}">
                    <div class="form_item">
                        <div class="form_label">培训基地：</div>
                        <div class="form_content">
                            <select class="select" name="orgFlow0"  onchange="searchDeptList(this.value)">
                                <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                <c:forEach items="${orgList}" var="org">
                                    <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                </c:forEach>
                            </select>&#12288;
                        </div>
                    </div>
                 </c:if>

                 <div class="form_item" style="width:400px ;">
                     <div class="form_label">人员类型：</div>
                    <div class="form_content">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </div>
                 </div>

            </div>

            <div style="margin-top: 15px;margin-bottom: 15px">
                 <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
             </div>

        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="6%"/>
                <col width="5%"/>
                <col width="7%"/>
                <col width="11%"/>
                <col width="11%"/>
                <col width="13%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="28%"/>
            </colgroup>
            <thead>
            <tr>
                <th>姓名</th>
                <th>年级</th>
                <th>人员类型</th>
                <th>原培训基地</th>
                <th>申请转入基地</th>
                <th>审核状态</th>
                <th>审核时间</th>
                <th>附件</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${docOrgHistoryExtList }" var="docOrgHistoryExt">
                <tr>

                    <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
                    <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
                    <td>${docOrgHistoryExt.resDoctor.doctorTypeName}</td>
                         <td>${docOrgHistoryExt.historyOrgName}</td>
                    <td>${docOrgHistoryExt.orgName}</td>
                    <td>${jsResChangeApplyStatusEnumInApplyWaiting.id == docOrgHistoryExt.changeStatusId or jsResChangeApplyStatusEnumInApplyPass.id == docOrgHistoryExt.changeStatusId or jsResChangeApplyStatusEnumInApplyUnPass.id == docOrgHistoryExt.changeStatusId?'转出审核通过':docOrgHistoryExt.changeStatusName}</td>
                    <td>${pdfn:transDate(docOrgHistoryExt.outDate)}</td>
                    <td>
                        <c:if test="${not empty docOrgHistoryExt.speChangeApplyFile }">
                            [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]
                        </c:if>
                    </td>
                    <td>
                        <a class="btn"
                           onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
                        <c:if test="${jsResChangeApplyStatusEnumOutApplyWaiting.id == docOrgHistoryExt.changeStatusId}">
                            <a class="btn audit"
                               onclick="turnOutOrg('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_Y}','${docOrgHistoryExt.doctorFlow}')">通过</a>
                            <a class="btn audit"
                               onclick="turnOutOrg('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_N}','${docOrgHistoryExt.doctorFlow}')">不通过</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty docOrgHistoryExtList }">
                <tr>
                    <td colspan="7">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(docOrgHistoryExtList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
