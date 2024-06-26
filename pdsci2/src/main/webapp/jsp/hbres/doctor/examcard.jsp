<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>

<script type="text/javascript">
function pt(){
	window.print();
}

function down() {
	var url ="<s:url value='print'/>?watermarkFlag=${GlobalConstant.FLAG_N}&recTypeId=ExamCard";
 	window.location.href=url;
}

function downPdf() {
	/* var url ="<s:url value='print'/>?watermarkFlag=${GlobalConstant.FLAG_N}&recTypeId=ExamCard&printType=pdf"; */
	var url = "<s:url value='downPdfExamCard'/>";
 	window.location.href=url;
}
</script>
</head>
<style media=print type="text/css">   
.noprint{visibility:hidden}   
</style>  

<body>
  <div class="main_wrap">
    <div class="user-contain">
      <p class="ticket_title">${sysCfgMap['res_reg_year']}年湖北省住院医师规范化培训招录考试第一阶段（笔试）</p>
      <h5>准考证</h5>
      <div class="user-bd">
        <table cellpadding="0" cellspacing="0" border="0" class="form_tab" style="width:93%;margin:0 auto;">
          <colgroup>
            <col width="18%" />
            <col width="20%" />
            <col width="10%" />
            <col width="20%" />
            <col width="32%" />
          </colgroup>
          <tr class="odd">
            <th>姓&#12288;&#12288;名：</th>
            <td style=" border-bottom:1px solid #ccc;">${user.userName}</td>
            <th>性&#12288;&#12288;别：</th>
            <td style=" border-bottom:1px solid #ccc;">${user.sexName}</td>
            <td rowspan="6" style="padding-left: 20px;">
            	<img src="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}" width="140px" height="200px"/>
			</td>
          </tr>
          <tr>
		      <th>身份证号：</th>
              <td style=" border-bottom:1px solid #ccc;">${user.idNo}</td>
			  <th></th>
			  <td></td>
		  </tr>
		  <tr>
		      <th>准考证号：</th>
			  <td style=" border-bottom:1px solid #ccc;">${examDoctor.ticketNum}</td>
			  <th></th>
			  <td></td>
		  </tr>
		  <tr>
		      <th>考点名称：</th>
			  <td style=" border-bottom:1px solid #ccc;">${examDoctor.siteName}</td>
			  <th></th>
			  <td></td>
		  </tr>
		  <tr>
		      <th>考点地址：</th>
			  <td style=" border-bottom:1px solid #ccc;">${examSite.siteAddress}</td>
			  <th></th>
			  <td></td>
		  </tr>
		  <tr>
		      <th>考&nbsp;&nbsp;场&nbsp;&nbsp;号：</th>
			  <td style=" border-bottom:1px solid #ccc;">${examDoctor.roomCode}</td>
			  <th></th>
			  <td></td>
		  </tr>
		  <tr>
		      <th>考试日期：</th>
			  <td style=" border-bottom:1px solid #ccc;">${exam.examDate}</td>
			  <th>考试时间：</th>
			  <td style=" border-bottom:1px solid #ccc;">${exam.examTime}</td>
		  </tr>
        </table>
        
        <div class="rules" id="">
        <p style="line-height: 30px;text-align: center;">
        	<b>考场规则</b>
        </p>
        <p style="line-height: 30px;">
&#12288;&#12288;一、考生必须自觉服从监考员等考试工作人员管理，不得以任何理由妨碍监考员等考试工作人员履行职责，不得扰乱考场及其他工作地点的秩序。<br/>
&#12288;&#12288;二、考生凭《准考证》、《身份证》，按规定的时间，到指定地点参加考试。<br/>
&#12288;&#12288;三、考前15分钟考生开始入场，除书写用黑色签字笔、填涂用2B铅笔、橡皮擦等，不准携带书包、文具盒、自备垫板和任何书籍、报纸、纸张以及其他任何与考试无关物品进入考场。考试正式开始后，迟到考生不准入场。考生中途不得退场，考试结束，确认试卷提交成功无误后方可离场。<br/>
&#12288;&#12288;严禁携带各种电子录放器材（如录音笔）以及修正液（带）等物品进入考场。考生在考场内不得自行传递文具用品。<br/>
&#12288;&#12288;四、考生入场后按准考证号对号入座，并将《准考证》、《身份证》（或《临时身份证》）放在座位左上角。扫码获得试卷后，应首先核对专业，核对无误，然后在规定位置准确填写和涂划自己的姓名、准考证号、身份证号。不得在规定位置以外书写姓名、准考号或做记号，违者取消考试成绩。<br/>
&#12288;&#12288;五、如不遵守考场纪律，不服从考试工作人员管理，涉嫌违纪、舞弊等行为的考生，将按照《国家教育考试违规处理办法》及其它有关规定进行处理。</p>
        </div>

		  <div class="rules" id="">
			  <p style="line-height: 30px;text-align: center;">
				  <b>考生须知</b>
			  </p>
			  <p style="line-height: 30px;">
				  &#12288;&#12288;2019年度湖北省住院医师规范化培训全省统一招录理论考试采取用智能手机扫描微信二维码在手机上答卷的方式进行，为了帮助学员顺利完成理论考试，现将有关事宜告知如下：<br/>
				  &#12288;&#12288;一、考试有关要求<br/>
				  &#12288;&#12288;1、2019年湖北省基地医院招录培训学员按照（理论成绩*40%+技能操作考试*40%+面试成绩*20%），从高分到低分进行录取。其中技能操作考试和面试，由基地医院组织进行，占总成绩的60%；理论考试由省级组织统一考试，只占总成绩的40%。<br/>
				  &#12288;&#12288;2、省级组织的理论考试主要目的是对考生在院校学习期间掌握基础理论知识情况进行测试，为下一步招录和培训提供参考。<br/>
				  &#12288;&#12288;3、鉴于今年我省是第二次实行智能手机在线考试，由于各位考生的手机和各通信公司的信号等条件还难以达到相关要求，因此,在考试过程中会出现登录速度较慢和停顿等情况，考生遇到问题可举手向监考人员询问，自觉维护考场秩序，保持安静，不要大声喧哗。<br/>
				  &#12288;&#12288;二、考试试卷说明<br/>
				  &#12288;&#12288;1、试卷分临床类和口腔类<br/>
				  &#12288;&#12288;2、每类试卷共计150道小题，试题均为最佳选择题，即每一道题在答题时只需从备选答案中选择一个最合适的作为正确答案。<br/>
				  &#12288;&#12288;3、本次考试答题时间为90分钟，考试时间结束系统会自动统一强制交卷，请考生合理分配自己的答题时间。<br/>
				  &#12288;&#12288;4、通过“上一题”，“下一题”查阅所有题目，在确认交卷前所有题目允许重复修改。<br/>
				  &#12288;&#12288;三、考试操作要求<br/>
				  &#12288;&#12288;1、登录页面需按准考证上准考证号码及身份证号码（两者需匹配确认无误，且身份证号末尾X不区分大小写）正确输入，点击开始考试，进入考试页面进行答题。<br/>
				  &#12288;&#12288;2、考试已答题情况可以通过点击【答题卡】按钮进入查看详情。<br/>
				  &#12288;&#12288;3、考试过程中不能退出考试页面，同时考生不能关闭微信程序后台，避免关机情况发生。<br/>
				  &#12288;&#12288;4、考试期间，若试题中存在图片，请点击【点击预览】查看图片，轻点图片右上角×可关闭图片。不可点击左上角的返回键，否则会退出考试页面，需重新点击开始考试，继续答题。<br/>
				  &#12288;&#12288;5、完成答题，点击右下角交卷按钮进行试卷提交，系统提示提交成功即完成考试。如答题时间结束后考生未点击交卷，系统仍终止答题并自动交卷。<br/>
				  &#12288;&#12288;6、考试过程中，若手机突然来电请考生自觉挂断电话，之后系统会自动返回考试页面。<br/>
				  &#12288;&#12288;7、考试过程中手机出现断网、关机情况，请重新扫描二维码进入考试界面继续完成后面的考试，所有之前的答题记录仍然存在。<br/>
				  &#12288;&#12288;8、考试过程中，若出现手机无法使用需更换手机进行考试的情况，需找监考老师进行登记，如考试结束后在后台发现考试时有多个手机ID账号登录却未找监考老师进行登记的，将按作弊处理。<br/>
				  &#12288;&#12288;9、考试过程中，若出现无法答题情况，请及时联系监考老师处理。<br/>


			  </p>
		  </div>
        
        <p class="noprint" style="text-align: center;">
        	<!-- 
	        <a onclick="pt();"  class="btn blue-btn" id="pt" target="_self">打印</a>
	        <a onclick="downPdf();"  class="btn blue-btn">pdf下载</a>
        	 -->
	        <a onclick="down();"  class="btn_blue">word下载</a>
        </p>
      </div>
    </div>
  </div>
</body>
</html>
