
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jquery_form" value="true" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>">
<link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
<link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
<link rel="stylesheet" href="<s:url value='/css/bootstrap-table-fixed-columns.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
<script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/bootstrap-table-fixed-columns.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	html { overflow-x: scroll;}
    #table thead th{
        background: rgba(225, 228, 229, 1);
    }
    #body-tab li a.current{
        color: #E8D5FF;
        background: #2f8cef;
    }
    #table tr:nth-of-type(1) td{
        line-height: 25px;
        padding-top: 18px;
    }
    #body-tab{
        border-bottom: none;
        margin-bottom: 10px;
    }
    #body-tab li{
        line-height: 14px;
        float: left;
        margin-left: 5px;
    }
    #body-tab li a {
        position: relative;
        background: #ddd;
        padding: 10px 20px;
        float: left;
        text-decoration: none;
        color: #444;
        text-shadow: 0 1px 0 rgba(255, 255, 255, .8);
        border-radius: 10px 10px 0 0;
        box-shadow: 0 0px 2px rgba(0, 0, 0, .4);
        font-size: 13px;
    }
    #body-tab li a:hover{
        color: #597EF7;
    }
    #body-tab li a.current{
        background: #DAE9FF;
        color: #597EF7;
    }
    .bootstrap-table .table:not(.table-condensed) > tbody > tr > td:first-child{
          /*  display: inline-block;*/
        width: 220px;
        overflow: hidden;
    }
    .div_search11 span {
        float: left!important;
    }
    /*.fixed-table-container{
        height:  calc(100vh);
    }*/
   /* #table{
        table-layout: fixed;
    }*/
    .bootstrap-table .table:not(.table-condensed) > thead > tr:first-child > th:first-child{
       min-width: 200px;
    }
</style>
<script type="text/javascript">
    // var w = window.innerWidth;
    // var h=window.innerHeight;
    // window.onload=function () {
    //     console.log("-------");
    //     console.log(w);
    //     console.log(h);
    //     window.resizeTo(w,h-50)
    // }
 function search(){
		//$("#searchForm").submit();
		 jboxPostAsync("<s:url value='/jsres/manage/initpersonStatic'/>", $("#searchForm").serialize(), function (res) {
			 if(res.length>0){
				 if(res[0].error){
					 jboxTip(res[0].error);
					 initTable([]);
					 $('#table').bootstrapTable('load', []);
				 }else{
					 initTable(res)
					 $('#table').bootstrapTable('load', res);
				 }
			 }else{
				 initTable([]);
				 $('#table').bootstrapTable('load', []);
			 }
		 },null,false);
	}
         function initTable(data) {
             $("#table").bootstrapTable({
                 data: data,
                 idField: 'orgFlow',
                 dataType: 'jsonp',
                 height:$(window).height() - 200,
                 columns: [[
                     {
                         field: 'orgName',
                         title: '基地名称',
                         valign:"middle",
                         rowspan:2

                     },{
                         title: '上月在培人数',
                         rowspan:1,
                         colspan:3
                     },{
                         title: '住院医师本月变动',
                         rowspan:1,
                         colspan:6
                     },{
                         title: '在校专硕本月变动',
                         rowspan:1,
                         colspan:6
                     },{
                         title: '本月人数',
                         rowspan:1,
                         colspan:3
                     }
                 ],
                     [  {
                         field: 'lastResidentNum',
                         title: '住院医师'
                     },{
                         field: 'lastInSchoolNum',
                         title: '在校专硕',
                     },{
                         field: 'lastBothNum',
                         title: '合计'
                     },
                     {
                         field: 'residentRecruitNum',
                         title: '入培'
                     },{
                         field: 'residentGraduatNum',
                         title: '结业',
                     },{
                         field: 'residentInNum',
                         title: '转入'
                     },
                     {
                         field: 'residentOutNum',
                         title: '转出'
                     },{
                         field: 'residentReturnNum',
                         title: '退培',
                     },{
                         field: 'residentExaminedNum',
                         title: '已考核待结业'
                     },{
                         field: 'inSchoolRecruitNum',
                         title: '入培'
                     },{
                         field: 'inSchoolGraduatNum',
                         title: '结业',
                     },{
                         field: 'inSchoolInNum',
                         title: '转入'
                     },
                     {
                         field: 'inSchoolOutNum',
                         title: '转出'
                     },{
                         field: 'inSchoolReturnNum',
                         title: '退培',
                     },{
                         field: 'inSchoolExaminedNum',
                         title: '已考核待结业'
                     },{
                         field: 'residentNum',
                         title: '住院医师'
                     },{
                         field: 'inSchoolNum',
                         title: '在校专硕',
                     },{
                         field: 'bothNum',
                         title: '合计'
                     },
                     {
                         field: 'no',
                         title: '序号',
                         visible:false
                     },
                     ]
                 ],
                 treeShowField: 'orgName',//在哪一列展开树形
                 parentIdField: 'parentOrgFlow', //指定父id列
                /* fixedColumns: true,
                 fixedNumber: 1,*/
                 onResetView: function(data) {
                     $("#table").treegrid({
                         initialState: 'collapsed', // 所有节点都折叠 expanded：展开
                         // expanderExpandedClass: 'glyphicon glyphicon-minus',
                         treeColumn: 0,
                         onChange: function() {
                             $("#table").bootstrapTable('resetWidth');
                         }
                     });
                 }
             });
         }
 
	$(document).ready(function(){
        var w = window.innerWidth;
        var h=window.innerHeight;
        window.resizeTo(w,h-50);
        table();
        var date =  new Date();
        var y =date.getFullYear();
        var m =date.getMonth()+1;
        if(m<10){
            m='0'+m;
        }
        if(!$("[name='monthDate']").val()){
            $("[name='monthDate']").val(y+'-'+m);
            $("#yearMonth").text(y+'年'+m+'月');
        }
        jboxPostAsync("<s:url value='/jsres/manage/initpersonStatic'/>", $("#searchForm").serialize(), function (res) {
            if(res.length>0){
                if(res[0].error){
                    jboxTip(res[0].error);
                    initTable([]);
                    $('#table').bootstrapTable('load', []);
                }else{
                    initTable(res);
                    $('#table').bootstrapTable('load', res);
                }
            }else{
                initTable([]);
                $('#table').bootstrapTable('load', []);
            }
        },null,false);
	});
	 function datechange(obj) {
		 var y= obj.value.split("-")[0];
		 var m=  obj.value.split("-")[1];
		 $("#yearMonth").text(y+"年"+m+"月");
	 }

	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
 function datechange(obj) {
     var y= obj.value.split("-")[0];
     var m=  obj.value.split("-")[1];
     $("#yearMonth").text(y+"年"+m+"月");
 }

     function table() {
        var y= $("[name='monthDate']").val().split("-")[0];
        var m= $("[name='monthDate']").val().split("-")[1];
         $("#yearMonth").text(y+'年'+m+'月');
         $("#content2").hide();
         $("#content1").show();
     }
     function chart() {
         $("#content1").hide();
         $("#content2").show();
         jboxLoad("content2","<s:url value='/res/monthlyReportGlobal/toPageDelaySpeChange?roleFlag='/>"+"${roleFlag}",true);
     }

</script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0">
<div class="div_search11"  style="padding-left: 5px;    box-sizing: border-box;">
    <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>人员情况统计</h1>
    <ul id="body-tab" class="title_tab">
        <li><a href="javascript:;" class="current" onclick="table()">人员情况统计</a></li>
        <%--<li><a href="javascript:;" onclick="chart()">人员异动报表</a></li>--%>
    </ul>
    <br>
   <%-- <br>--%>
    <div id="content1"  style=" width: 980px; overflow-x: scroll; box-sizing: border-box;padding-bottom: 80px;">
    <form id="searchForm" method="post" style="padding: 10px;margin-left: -23px" <%--action="<s:url value='/jsres/manage/doctorStatistics'/>"--%>>
        <input hidden name="role" class="role" value="${roleFlag}"/>
        <input type="hidden" id="currentPage" name="currentPage" value="" >
        <div class="inputDiv" style="text-align: left;margin-left: 20px;    line-height: 45px;">
            <td style="text-align: left;width: 6%">时间：</td>
            <td style="text-align: left;">
                <input type="text" name="monthDate" class="input" id="ym" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
            </td>
            <input type="checkbox" name="isContain" onclick="search()" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />
            <label class="qlable">包含协同</label>
            <td style="text-align: left;width: 77%;">
                <input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
            </td>
        </div>
    </form>

        <table id="table" class="grid">
        </table>
       <%-- <div class="page" style="padding-right: 24px;margin-left: 25%; ">
            <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>--%>
    </div>
    <div id="content2" style=" width: 980px; overflow-x: scroll; box-sizing: border-box;padding-bottom: 80px;">
       <%-- <table id="chart" border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>基地名称</th>
                <th>延培</th>
                <th>专业变更</th>
            </tr>
            <c:forEach items="${resBaseExtList}" var="resExt">
                <tr>
                    <td>${resExt.sysOrg.orgName}</td>
                    <td>${resExt.baseGradeName}</td>
                    <td>${resExt.baseTypeName}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty resBaseExtList}">
                <td colspan="5"> 无记录</td>
            </c:if>
        </table>--%>
    </div>
</div>
</div>
</body>
</html>