<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="<s:url value='/js/layui/css/layui.css'/>">
  <script src="<s:url value='/js/layui/layui.js'/>"></script>
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
      font-size: 22px;
        display: inline-block;
        margin-bottom: 20px;
    }
    .tbButtonBox{
      text-align: right;
    }
    .layui-col-space10{
      margin: 0;
    }
    .setLbtnBg{
      background: #44b549;
      border-radius: 3px;
    }

  </style>
  <title>Document</title>
  <script>
      function hospitalDoctorsNum(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/hospitalDoctorsNum'/>?monthDate=${param.monthDate}";
          jboxLoad("content1",url,false);
      }
      function exportChart1(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportChart1'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      function exportChart2(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportChart2'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      function exportDifference(obj){
          var sessionNumber = $(obj).closest('tr').find('td').eq(0).text();
          if(sessionNumber=='合计'){
              sessionNumber = '';
          }
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportDifference'/>?monthDate=${param.monthDate}&sessionNumber="+sessionNumber;
          window.open(url);
      }
      function exportReturn(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportReturn'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      function exportDelay(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportDelay'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      function exportBaseChange(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportBaseChange'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      function exportSpeChange(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportSpeChange'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      function exportStatusChange(){
          var url="<s:url value='/jsres/monthlyReportGlobalNew/exportStatusChange'/>?monthDate=${param.monthDate}";
          window.open(url);
      }
      $("#tab a").on({
          mouseover:function(){
              if(!$(this).parent("li").hasClass("choose")){
                  $(this).parent('li').css({
                      "margin-left":"10px",
                      "background":"#44b549",
                  });
                  $(this).css("color","#fff")
              }

          },
          mouseout:function(){
              if(!$(this).parent("li").hasClass("choose")){
                  $(this).parent('li').css({
                      "margin-left":"15px",
                      "background":"#ddd"
                  });
                  $(this).css("color","#ffffff")
              }
          }
      })
  </script>
</head>

<body>
  <div class="layui-container">
    <div class="layui-row layui-col-space10">
      <div class="layui-col-lg6 layui-col-md12">
        <div class="tbTitle">
          <span class="tbTitleTxt">本月在培学员人数统计</span>
          <div class="tbButtonBox">
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="exportChart1()">导出</button>
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="hospitalDoctorsNum()">详情数据</button>
          </div>
        </div>
        <table class="layui-table tbCenter">
          <thead>
            <tr>
              <th>培训年级</th>
              <th>住院医师</th>
              <th>在校专硕</th>
              <th>本月总数</th>
              <th>上月总数</th>
              <th>与上月差异</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${yearList}" var="year">
              <tr>
                <td>${year}</td>
                <td>${notGraduateMap[year]+0}</td>
                <td>${graduateMap[year]+0}</td>
                <td>${totalMap[year]+0}</td>
                <td>${lastTotalMap[year]+0}</td>
                <td style="cursor: pointer;color: #0e8af3" onclick="exportDifference(this)">${totalMap[year]-lastTotalMap[year]+0}</td>
              </tr>
            </c:forEach>
            <tr>
              <td>合计</td>
              <td>${sumMap['notGraduate']}</td>
              <td>${sumMap['graduate']}</td>
              <td>${sumMap['total']}</td>
              <td>${sumMap['lastTotal']}</td>
              <td style="cursor: pointer;color: #0e8af3" onclick="exportDifference(this)">${sumMap['total']-sumMap['lastTotal']}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div class="layui-col-lg6 layui-col-md12">
        <div class="tbTitle">
          <span class="tbTitleTxt">本月异动学员人数统计</span>
          <div class="tbButtonBox">
            <button class="layui-btn layui-btn-normal setLbtnBg" onclick="exportChart2()">导出</button>
          </div>
        </div>
        <table class="layui-table tbCenter">
          <thead>
            <tr>
              <th>培训年级</th>
              <th>退培</th>
              <th>延期</th>
              <th>基地变更</th>
              <th>专业变更</th>
              <th>培训状态变更</th>
            </tr>
          </thead>
          <tbody>
          <c:forEach items="${yearList}" var="year">
            <tr>
              <td>${year}</td>
              <td>${returnTrainingMap[year]+0}</td>
              <td>${delayMap[year]+0}</td>
              <td>${baseChangeMap[year]+0}</td>
              <td>${speChangeMap[year]+0}</td>
              <td>${statusIdChangeMap[year]+0}</td>
            </tr>
          </c:forEach>
            <tr>
              <td>合计</td>
              <td style="cursor: pointer;color: #0e8af3" onclick="exportReturn()">${sumMap['returnTraining']}</td>
              <td style="cursor: pointer;color: #0e8af3" onclick="exportDelay()">${sumMap['delay']}</td>
              <td style="cursor: pointer;color: #0e8af3" onclick="exportBaseChange()">${sumMap['baseChange']}</td>
              <td style="cursor: pointer;color: #0e8af3" onclick="exportSpeChange()">${sumMap['speChange']}</td>
              <td style="cursor: pointer;color: #0e8af3" onclick="exportStatusChange()">${sumMap['statusIdChange']}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>

</html>