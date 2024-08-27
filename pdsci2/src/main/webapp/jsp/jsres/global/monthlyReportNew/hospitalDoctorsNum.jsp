<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="<s:url value='/js/layui/css/layui.css'/>">
  <script src="<s:url value='/js/layui/layui.all.js'/>"></script>
  <script type="text/javascript" charset="utf-8"
          src="<s:url value='/js/bootstrap-datepicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
  <link rel="stylesheet" type="text/css"
        href="<s:url value='/css/datepicker.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
  <style>
    .tbCenter tbody{
      text-align: center;
    }
    .tbTitle{
      text-align: center;
      padding-top: 10px;
    }
    .tbTitleTxt{
      font-weight: bold;
      font-size: 18px;
    }
    .tbButtonBox{
      text-align: right;
    }
    /* 添加样式 */
    .clearfix:after{
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
    .setLbtnBg{
      background: #54B2E5;
      border-radius: 3px;
    }
    .tbLeft{
      float: left;
    }
    .tbRight{
      float: right;
    }
    .tbCenter th{
      text-align: center;
    }
    .search-item{
      float: left;
      margin-right: 10px;
      margin-bottom: 10px;
    }
    .layui-form-radio{
      margin: 0;
    }
  </style>
  <title>Document</title>
</head>

<body>
  <div class="layui-container">
    <div class="layui-row">
      <div class="layui-col-xs12">
        <div class="tbTitle clearfix">
          <div class="tbLeft clearfix layui-form">
            <div class="search-item">
              <span>培训基地：</span>
              <div class="layui-input-inline">
                <select name="orgFlow" id="orgFlow" lay-verify="">
                  <option value="">全部</option>
                  <c:forEach items="${orgs}" var="org">
                    <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="search-item">
                <span>年级：</span>
                <div class="layui-input-inline">
                    <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="layui-input"
                           readonly="readonly"/>
                </div>
            </div>
          </div>
          <div class="tbRight">
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="toPage(1)">查询</button>
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="exportHospitalDoctorsNum()">导出</button>
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="exportHospitalDoctorsDetail()">导出详情</button>
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="chart12()">返回</button>
          </div>
        </div>
        <table class="layui-table tbCenter">
          <thead>
            <tr>
              <th>培训基地</th>
              <th>年级</th>
              <th>住院医师</th>
              <th>在校专硕</th>
              <th>总数</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${resultMapList}" var="result">
              <tr>
                <td>${result.ORG_NAME}</td>
                <td>${result.SESSION_NUMBER}</td>
                <td>${result.B}</td>
                <td>${result.A}</td>
                <td>${result.C}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
    <div style="text-align: center">
      <c:set var="pageView" value="${pdfn:getPageView2(resultMapList,10)}" scope="request"></c:set>
      <pd:pagination-jsres toPage="toPage"/>
    </div>
  </div>
  <script>
      ;!function(){
          var layer = layui.layer
              ,form = layui.form;
      }();
      $(document).ready(function () {
          $('#sessionNumber').datepicker({
              startView: 2,
              maxViewMode: 2,
              minViewMode: 2,
              format: 'yyyy'
          });
      });
    function toPage(page) {
        var currentPage = 1;
        if (page) {
            currentPage = page;
        }
        var sessionNumber = $("#sessionNumber").val();
        var orgFlow = $("#orgFlow").val();
        var url="<s:url value='/jsres/monthlyReportGlobalNew/hospitalDoctorsNum'/>?monthDate=${param.monthDate}&currentPage="
            +currentPage+"&sessionNumber="+sessionNumber+"&orgFlow="+orgFlow;
        jboxLoad("content1",url,false);
    }
      function exportHospitalDoctorsNum(){
          var sessionNumber = $("#sessionNumber").val();
          var orgFlow = $("#orgFlow").val();
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportHospitalDoctorsNum'/>?monthDate=${param.monthDate}"
              +"&sessionNumber="+sessionNumber+"&orgFlow="+orgFlow;
          window.open(url);
      }
      function exportHospitalDoctorsDetail(){
          var sessionNumber = $("#sessionNumber").val();
          var orgFlow = $("#orgFlow").val();
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportHospitalDoctorsDetail'/>?monthDate=${param.monthDate}"
              +"&sessionNumber="+sessionNumber+"&orgFlow="+orgFlow;
          window.open(url);
      }
  </script>
</body>
</html>