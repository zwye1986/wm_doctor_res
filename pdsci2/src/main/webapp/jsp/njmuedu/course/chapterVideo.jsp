<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="jwplayer" value="true"/>
</jsp:include>
 <script type="text/javascript">
 $(function(){
	 /*播放器初始化*/
	    var chapterFinishFlag='${schedule.chapterFinishFlag}';
	    var studyStatusId='${schedule.studyStatusId}';
	 console.log(studyStatusId);
	 console.log(chapterFinishFlag);
	    if(chapterFinishFlag!='Y'){
	    	chapterFinishFlag="${sysCfgMap['njmuedu_player_control']}";
	    }
	 console.log(chapterFinishFlag);
		initPlayer("${chapter.chapterFlow}",chapterFinishFlag,"${sysCfgMap['njmuedu_play_validation']}","${sysCfgMap['upload_base_url']}","Y","${sysCfgMap['upload_stream_url']}",'${level}',studyStatusId);
		if(chapterFinishFlag!='Y'){
			/*播放器音量控制插件*/
			 $("#mySlider").slider({
			      range: "min",
			      value: 70,
			      min: 0,
			      max: 100,
			      stop: function(event,ui) {
			    	  setVolume(ui.value);
			      },
			      change:function(event,ui){
			      	  $("#volume").html(ui.value+"%");
			      },
			      slide:function(event,ui){
			      	  $("#volume").html(ui.value+"%");
			      }
			    });
		}
 });
 </script>
 <div class="videobg">
	  		<div class="video" id="myPlayer">Loading the player...</div>
	  		<div class="next-box " id="completeShowBox" style="display:none">
	            <div class="next-inner">
	                <div><a href="javascript:void(0)" class="again" onclick="doRestart()" title="点击重新播放">重新观看</a></div>
	                <%-- href="<s:url value='/njmuedu/course/nextChapters'/>?chapterFlow=${chapter.chapterFlow}&courseFlow=${chapter.courseFlow}" --%>
	                <div><span class="btn-exam btn-xlarge" onclick="nextChapters('${chapter.chapterFlow}','${chapter.courseFlow}');" >进入下一章节</span></div>
	            </div>
	        </div>
		     <div  id="randomConfirm" class="next-box" style="display:none">
		     	<form id="randForm">
		         <div class="next-inner">
		           <div class="next-line">
		           <h1>请您输入验证码后继续观看</h1>
		                 <h1><font>验证码：</font><span class="logo_text"><input name="verifyCode" type="text" class="validate[required]"
							id="verifyCode"	style="width: 250px; padding-bottom:7px; text-indent:10px;" placeholder=""/></span></h1>
							<h1><img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;padding-left:68px;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /><span id="sec" style="color: red">59</span>秒后重新播放！</h1>
		       	    <h1><input type="button" class="btn-exam btn-refer" style="width: 114px;" value="提交" onclick="doContinue();"/></h1>
		       	   </div>
		      	</div>
		      	</form>
        	</div>
  		</div>
  		 <div class="video-des">
            <ul class="fl">
            	<li class="video-pause"><input type="button" id="playButton"/></li>
                <c:if test="${schedule.chapterFinishFlag != 'Y' and sysCfgMap['njmuedu_player_control'] != 'Y'}">
                  <li  class="video-j" onclick="setMute(this)" style="cursor: pointer;" title="静音"></li>
                </c:if>
            </ul>
            <c:if test="${schedule.chapterFinishFlag != 'Y' and sysCfgMap['njmuedu_player_control'] != 'Y'}">
            <div class="video-v">
            <div  id="mySlider"></div>
            </div>
            <span id="volume">70%</span>
            </c:if>
            <div class="video-des-r fr">
               <span id="changeVideo">
                  <select id="videoLevelSelect" onchange="loadChapterVideo('Y');">
                     <c:forEach items="${njmuEduVideoLevelEnumList}" var="videoLevel">
                         <c:set var='videoLevelId' value="njmuedu_video_level_${videoLevel.id}"></c:set>
                         <c:if test="${sysCfgMap[videoLevelId]=='Y' }">
                             <option value="${videoLevel.id}" <c:if test="${level eq videoLevel.id }">selected</c:if>>${videoLevel.name }</option>
                         </c:if>
                     </c:forEach>
                  </select>
               </span>
               <span id="play_length"><span id="nowMinutes">00</span>:<span id="nowSecond">00</span></span>/<span id="video_length">00:00</span>
            </div>
        </div>