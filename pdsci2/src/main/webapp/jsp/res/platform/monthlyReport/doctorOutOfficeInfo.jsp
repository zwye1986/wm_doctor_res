<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
        <jsp:param name="font" value="true"/>
    </jsp:include>

    <%--<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>app</title>--%>
    <link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
  <%--  <link rel="stylesheet" href="<s:url value='/jsp/jsres/hospital/monthlyReportNew/css/common.css'/>">--%>
    <link rel="stylesheet" href="<s:url value='/css/cssPro.css'/>">
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>

    <script type="text/javascript" src="<s:url value='/js/bootstrap-table.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/bootstrap-table-treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/jquery.treegrid.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script src="<s:url value='/jsp/jsres/hospital/monthlyReportNew/js/common.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/echarts/echarts.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <style type="text/css">
        #table thead{
            background: rgba(225, 228, 229, 1);
        }
        #body-tab li a.current{
            color: #E8D5FF;
            background: #2f8cef;
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
        clearfix:after{
            content:"";
            height:0;
            line-height:0;
            display:block;
            clear:both;
            visibility:hidden;
        }
        .clearfix{
            zoom:1;
        }
    </style>
    <script>
        $(function () {
            $("#content2").hide();
            $("#content1").show();
          var date =  new Date();
          var y =date.getFullYear();
          var m =date.getMonth()+1;
          if(m<10){
              m='0'+m;
          }
          if(!$("[name='monthDate']").val()){
              $("[name='monthDate']").val(y+'-'+m);
              $("#yearMonth").text(y+'年'+m+'月');
              /*$("[name='monthDate']").val('2019-02');*/
           }

            var data = "";
            $("input[class='docType']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
            data=$("#orgForm").serialize()+data;
        jboxPostAsync("<s:url value='/res/monthlyReportGlobal/initDoctorOutDept'/>",data, function (res) {
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
        function initTable(data) {
          $("#table").bootstrapTable({
              data: data,
              idField: 'orgFlow',
              dataType: 'jsonp',
              columns: [
                  {
                      field: 'orgFlow',
                      title: '基地号',
                      width: 140,
                      visible:false
                  },
                  {
                      field: 'orgCode',
                      title: '基地编号'
                  },{
                      field: 'orgName',
                      title: '基地名称'
                  },{
                      field: 'outDeptNum',
                      title: '本月应出科人数'
                  },{
                      field: 'realNum',
                      title: '实际出科人数'
                  },{
                      field: 'notNum',
                      title: '未出科人数'
                  },{
                      field: 'notNumScale',
                      title: '出科异常比例',
                      formatter:function(value,row,index) {
                          var notNumScale = row.notNumScale;
                          if (notNumScale == null) {
                              notNumScale = 0 + '%';
                          } else {
                              notNumScale = notNumScale + '%'
                          }
                          return notNumScale;
                      }
                  }
                  // ,{
                  //     field: 'dataFinishScale',
                  //     title: '出科学员数据平均完成率'
                  // }
              ],
              treeShowField: 'orgCode',//在哪一列展开树形
              parentIdField: 'parentOrgFlow', //指定父id列
              onResetView: function(data) {
                  $("#table").treegrid({
                      initialState: 'collapsed', // 所有节点都折叠 expanded：展开
                      treeColumn: 0,
                      onChange: function() {
                          $("#table").bootstrapTable('resetWidth');
                      }
                  });
              }
          });
      }
        function search() {
            var data = "";
            $("input[class='docType']:checked").each(function () {
                data += "&datas=" + $(this).val();
            });
            if (data == "") {
                jboxTip("请选择人员类型！");
                return false;
            }
            data=$("#orgForm").serialize()+data;
            jboxStartLoading();
            jboxPost("<s:url value='/res/monthlyReportGlobal/initDoctorOutDept'/>",/* $("#orgForm").serialize()*/data, function (res) {
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
                jboxEndLoading();
            },null,false);
        }
        function datechange(obj) {
           var y= obj.value.split("-")[0];
           var m=  obj.value.split("-")[1];
            $("#yearMonth").text(y+"年"+m+"月");
        }

        function table() {
            $("#content2").hide();
            $("#content1").show();
            $("#yearMonth").text($("#ym").val().split("-")[0]+'年'+$("#ym").val().split("-")[1]+'月');
        }
        function chart() {
            $("#content1").hide();
            $("#content2").show();
           jboxLoad("content2","<s:url value='/res/monthlyReportGlobal/initDoctorOutOfficeInfoDetail'/>",true);
        }
    </script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0">
    <div class="div_search" style="padding-left: 40px; box-sizing: border-box;">
            <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="yearMonth"></span>学员出科情况统计月度报表</h1>

            <ul id="body-tab" class="title_tab">
                <li><a href="javascript:;" class="current" onclick="table()">学员出科情况统计</a></li>
                <%--<li><a href="javascript:;" onclick="chart()">出科异常学员查询</a></li>--%>
            </ul>
            <div class="main_bd" id="content1">
                <form id="orgForm" action="<%--<s:url value='/res/monthlyReportGlobal/appUseInfo'/>--%>" method="post">
                    <input type="hidden" id="currentPage1" name="currentPage1" value="${param.currentPage1}">

                    <table style="width: 100%;">
                        <tr style="line-height: 55px">
                            <td style="text-align: left;width: 7%">规则排序：</td>
                            <td style="text-align: left">
                                <select name="sortFlag" class="select" style="width: 136px;">
                                    <option value="">请选择</option>
                                    <option value="notNumDesc"  ${param.orgFlow eq 'outOfficeDesc' ?'selected="selected"':''}>按出科异常人数降序</option>
                                    <option value="notNumAsc" ${param.orgFlow eq 'outOfficeAsc' ?'selected="selected"':''}>按出科异常人数升序</option>
                                    <%--<option value="dataFinishDesc" ${param.orgFlow eq 'dataFinishDesc' ?'selected="selected"':''}>按数据平均完成率降序</option>--%>
                                    <%--<option value="dataFinishAsc" ${param.orgFlow eq 'dataFinishAsc' ?'selected="selected"':''}>按数据平均完成率升序</option>--%>
                                </select>
                            </td>
                            <td style="text-align: left;width: 6%">时间：</td>
                            <td style="text-align: left;">
                                <input type="text" name="monthDate" class="input" id="ym" onchange="datechange(this)"  value="${param.monthDate}" onClick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                            </td>
                            <%--<td style="text-align: left;width: 2%">--%>
                                <%--<input type="checkbox" name="isContain" ${param.isContain eq 'isContain'? 'checked':""} value="isContain" />--%>
                            <%--</td>--%>
                            <%--<td style="text-align: left">包含协同</td>--%>
                            <td style="text-align: left;width: 7%">人员类型：</td>
                            <td style="text-align: left">
                                <%--<c:choose>--%>
                                    <%--<c:when test="${university=='university'}">--%>
                                        <%--<input class="docType" type="hidden" name="NotGraduate" checked  value=""  />--%>
                                        <%--<label class="lable" style="display: none">住院医师</label>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <input class="docType" type="checkbox" name="notGraduate" checked  value="Y" />
                                        <label class="lable">住院医师</label>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                                <input class="docType" type="checkbox" name="graduate" checked value="Y" />
                                <label class="lable">在校专硕</label>
                            </td>
                            <td>
                                <input type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
                            </td>
                        </tr>
                    </table>
                </form>
                <table id="table" class="basic" width="100%">

                </table>
            </div>
            <div class="main_bd" id="content2" style="height:550px;width: 100%; ">
                <!--省、市、高校 出科异常学员查询-->
            </div>


    </div>
</div>
<style>
    .div_search span{
        float: left!important;
    }
</style>
</body>
</html>
