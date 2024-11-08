<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
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

    <link rel="stylesheet" href="<s:url value='/css/bootstrap-table.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
    <link rel="stylesheet" href="<s:url value='/css/jquery.treegrid.css'/>?v=${applicationScope.sysCfgMap['sys_version']}" type="text/css">
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
            var y = date.getFullYear();
            if(!$("[name='yearDate']").val()){
                $("[name='yearDate']").val(y);
                $("#year").text(y+'年');
            }

            var data=$("#orgForm").serialize();
            jboxPostAsync("<s:url value='/jsres/graduation/examStatisticsList'/>",data, function (res) {
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
              idField: 'id',
              dataType: 'jsonp',
              striped: true,          // 显示条纹表格
              columns: [
                  {
                      field: 'orgFlow',
                      title: '基地号',
                      width: 140,
                      visible:false
                  },
                  {
                      field: 'orgName',
                      title: '名称',
                      formatter:function(value,row,index) {
                          var orgName = row.orgName;
                          if (orgName == null) {
                              orgName = row.speName;
                              if(orgName == null){
                                  orgName = row.orgCityName;
                              }
                          }
                          return orgName;
                      }
                  },{
                      field: 'examTotal',
                      title: '应参考人数',
                      formatter:function(value,row,index) {
                          var examTotal = row.examTotal;
                          if (examTotal == null) {
                              examTotal = 0;
                          }
                          return examTotal;
                      }
                  },{
                      field: 'realTotal',
                      title: '实际参考人数',
                      formatter:function(value,row,index) {
                          var realTotal = row.realTotal;
                          if (realTotal == null) {
                              realTotal = 0;
                          }
                          return realTotal;
                      }
                  },{
                      field: 'firstExamTotal',
                      title: '首考人数',
                      formatter:function(value,row,index) {
                          var fristExamTotal = row.fristExamTotal;
                          if (fristExamTotal == null) {
                              fristExamTotal = 0;
                          }
                          return fristExamTotal;
                      }
                  },{
                      field: 'secondExamTotal',
                      title: '补考人数',
                      formatter:function(value,row,index) {
                          var secondExamTotal = row.secondExamTotal;
                          if (secondExamTotal == null) {
                              secondExamTotal = 0;
                          }
                          return secondExamTotal;
                      }
                  },{
                      field: 'missExamTotal',
                      title: '缺考人数',
                      formatter:function(value,row,index) {
                          var missExamTotal = row.missExamTotal;
                          if (missExamTotal == null) {
                              missExamTotal = 0;
                          }
                          return missExamTotal;
                      }
                  }
              ],
              treeShowField: 'orgName',//在哪一列展开树形
              parentIdField: 'pid', //指定父id列
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
            var typeId = $("#tabTag").val();
            var data=$("#orgForm").serialize();
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/graduation/examStatisticsList'/>",data, function (res) {
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
            $("#yearDate").text(obj+"年");
        }

        function table() {
            $("#content1").show();
            $("#yearMonth").text($("#ym").val()+'年');
        }

    </script>
</head>
<body>
<div class="main_bd define_bd" id="div_table_0">
    <div class="div_search" style="padding-left: 40px; box-sizing: border-box;">
            <h1 style="font-size: 20px;display: inline-block;font-size: 20px;height: 70px;"><span id="year"></span>
                <c:if test="${catSpeId eq 'DoctorTrainingSpe'}">&nbsp;住院医师</c:if>
                <c:if test="${catSpeId eq 'AssiGeneral'}">&nbsp;助理全科</c:if>
                考试人员情况统计
            </h1>

            <ul id="body-tab" class="title_tab">
                <li><a href="javascript:;" class="current" onclick="table()">考试人员情况统计</a></li>
            </ul>
            <div class="main_bd" id="content1">
                <form id="orgForm"  method="post">
                    <input type="hidden" id="catSpeId" name="catSpeId" value="${catSpeId}">
                    <table style="width: 100%;">
                        <tr style="line-height: 55px">
                            <td style="text-align: left;width: 8%">结业年份：</td>
                            <td style="text-align: left;">
                                <input type="text" name="yearDate" class="input" id="ym" onchange="datechange(this)"  value="${param.yearDate}" onClick="WdatePicker({dateFmt:'yyyy',isShowClear:false,onpicked:function(dp){$dp.$('ym').blur();}})" readonly="readonly"/>
                            </td>
                            <td style="text-align: left;width: 7%">统计类型：</td>
                            <td style="text-align: left">
                                <select id="tabTag" name="tabTag" class="select" style="width: 159px;">
                                    <option value="Org" selected>基地</option>
                                    <option value="Spe" <c:if test="${tabTag eq 'Spe'}">selected</c:if>>专业</option>
                                    <option value="City" <c:if test="${tabTag eq 'City'}">selected</c:if>>地区</option>
                                </select>
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
    </div>
</div>
<style>
    .div_search span{
        float: left!important;
    }
</style>
</body>
</html>
