<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${!empty sessionScope.currUser.userName?sessionScope.currUser.userName:'' } 打印准考证</title>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
    <jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
    <jsp:param name="jbox" value="true" />
</jsp:include>

<script type="text/javascript">
    function printTickt(){
        jboxTip("打印中，请稍等...");
        setTimeout(function(){
            var headstr = "<html><head><title></title></head><body>";
            var footstr = "</body>";
            var newstr = $(".printDiv").html();
            var oldstr = document.body.innerHTML;
            document.body.innerHTML = headstr+newstr+footstr;
            window.print();
            document.body.innerHTML = oldstr;
            return false;
        },3000);
    }
</script>
<style type="text/css">
    .siteTb td{border: 1px solid gray}
</style>
</head>

<body>
<div class="bd_bg">
<div class="yw">
 <div class="head">
   <div class="head_inner">
     <h1 class="logo">
       <a onclick="printTickt()">打印准考证</a>
     </h1>
   </div>
 </div>

 <div class="body">
   <div class="container">
       <div style="float: right;margin-right: 80px;margin-top:40px;">
           <input type="button" style="width:100px;" class="btn_blue" onclick="printTickt()" value="打印"/>
       </div>
       <div class="printDiv" style="margin:100px 178px;border:1px solid black;width:840px;height: auto;">
           <p style="text-align: center;font-size: 20px;font-weight:bold;margin-top: 15px;">四川省2017年中医住院医师规范化培训结业考试</p>
           <p style="text-align: center;font-size: 20px;font-weight:bold;margin-top: 20px;">准&#12288;考&#12288;证</p>
           <table style="margin-top:20px;width: 100%;text-align: left;line-height: 30px;font-size: 16px;">
                <tr>
                    <td>姓&#12288;&#12288;名：${user.userName}<br/>
                        身份证号：${user.idNo}
                    </td>
                    <td rowspan="2"><div style="border:1px solid gray;text-align:center;width:100px;padding-top: 100px;">贴照片处</div></td>
                </tr>
               <tr>
                   <td>考试日期：技能考试&nbsp;6&nbsp;月&nbsp;3&nbsp;日&#12288;09:00-12:00&#12288;14:00-18:00<br/>
                       &#12288;&#12288;&#12288;&#12288;&#12288;理论考试&nbsp;6&nbsp;月&nbsp;4&nbsp;日&#12288;09:00-11:00&#12288;13:30-15:30
                   </td>
               </tr>
               <tr>
                   <td colspan="2">
                       考&#12288;&#12288;点：1、成都中医药大学温江校区<br/>
                       &#12288;&#12288;&#12288;&#12288;&#12288;2、西南医科大学城北校区

                   </td>
               </tr>
               <tr>
                   <td colspan="2">
                       考点地址：1、成都考点：成都市温江区柳台大道东段1166号成都中医药大学E教（临床技能中心）<br/>
                       &#12288;&#12288;&#12288;&#12288;&#12288;2、泸州考点：（1）理论考核地点：泸州市龙马潭区香林路1段1号西南医科大学荟文楼4楼（2）技能考核地点：泸州市龙马潭区香林路1段1号西南医科大学海川楼5楼中西医结合临床技能培训中心
                   </td>
               </tr>
           </table>

           <div>
               <p style="font-size: 16px;font-weight: bold;text-align: center;">考生须知</p>
               <p style="margin-top:20px;font-weight: bold;line-height: 25px;">1、考生下载准考证后，请仔细核对身份证等信息，信息有误，请及时联系成四川省中医毕业后教育委员会办公室；请将右上角粘贴上本人一寸免冠照，盖培训基地公章。<br/>
                   2、应考人员应提前十分钟凭准考证和二代身份证入场；对号入座后将准考证和身份证放在桌子右上角，以备查对。<br/>
                   3、本次考试按准考证号分段进行考试，3日为技能考试，4日为机考理论考试，具体安排如下：
               </p>
               <div>
                   <p style="font-weight: bold;line-height: 25px;">&nbsp;（1）成都中医药大学考点</p>
                   <table class="siteTb" style="width: 95%;text-align: center;margin-left: 20px;line-height: 25px;">
                       <tr>
                           <td style="width:100px">技能考试时间<br/>（6月3日）</td>
                           <td>考试批次</td>
                           <td style="width:100px;">理论考试时间<br/>（6月4日）</td>
                           <td>考试批次</td>
                       </tr>
                       <tr>
                           <td>09:00-12:00</td>
                           <td>5135002017CD001 - 5135002017CD198</td>
                           <td>9:00-11:00</td>
                           <td>5135002017CD001-5135002017CD198</td>
                       </tr>
                       <tr>
                           <td>14:00-18:00</td>
                           <td>5135002017CD199 - 5135002017CD396</td>
                           <td>13:30-15:30</td>
                           <td>5135002017CD199-5135002017CD396</td>
                       </tr>
                   </table>
                   <p style="font-weight: bold;line-height: 25px;">&nbsp;（2）西南医科大学考点</p>
                   <table class="siteTb" style="width: 95%;text-align: center;margin-left: 20px;line-height: 25px;">
                       <tr>
                           <td style="width:100px">技能考试时间<br/>（6月3日）</td>
                           <td>考试批次</td>
                           <td style="width:100px;">理论考试时间<br/>（6月4日）</td>
                           <td>考试批次</td>
                       </tr>
                       <tr>
                           <td>09:00-12:00</td>
                           <td>5135002017LZ001 - 5135002017LZ181</td>
                           <td>9:00-11:00</td>
                           <td>5135002017LZ001-5135002017LZ181</td>
                       </tr>
                       <tr>
                           <td>14:00-18:00</td>
                           <td>5135002017LZ182 - 5135002017LZ371</td>
                           <td>13:30-15:30</td>
                           <td>5135002017LZ182-5135002017LZ371</td>
                       </tr>
                   </table>
               </div>
               <p style="font-weight: bold;line-height: 25px;">4、考试中，严格遵守考场纪律；严禁将通讯工具、书本带进考场。<br/>
                   5、考试结束后请快速离开考场。
               </p>
           </div>
           <div style="margin-top: 20px;">
               <p style="font-weight:bold;font-size: 16px;margin-bottom: 10px;"><span>请使用A4纸打印，彩色或黑白打印均可！</span><span style="float: right;">四川省中医毕业后教育委员会办公室&#12288;制&#12288;</span></p>
           </div>
       </div>
   </div>
 </div>
</div>
</div>
 
 <div class="foot">
   <div class="foot_inner">
   </div>
 </div>

</body>
</html>
