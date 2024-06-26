<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="jwplayer" value="true"/>
</jsp:include>
 <script type="text/javascript">
 $(function(){
	 /*播放器初始化*/
		initPlayer("${chapter.chapterFlow}","Y","N","${sysCfgMap['upload_base_url']}","N","${sysCfgMap['upload_stream_url']}",'${level}');
 });
 </script>
 <div class="videobg">
	  		<div class="video" id="myPlayer">Loading the player...</div>
	  		<div class="next-box " id="completeShowBox" style="display:none">
	            <div class="next-inner">
	                <div><a href="javascript:void(0)" class="again" onclick="doRestart()" title="点击重新播放">重新观看</a></div>
	            </div>
	        </div>
  		</div>
  		 <div class="video-des">
            <ul class="fl">
            	<li class="video-pause"><input type="button" id="playButton"/></li>
            </ul>
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