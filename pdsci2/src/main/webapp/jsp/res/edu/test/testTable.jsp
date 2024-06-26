 <script type="text/javascript">
 $(document).ready(function(){
	 loadTestList('${param.chapterFlow}');
	});
 function loadTestList(chapterFlow){
 	var selfFlag=$("#selfFlag").val();
 	var url ="<s:url value='/resedu/manage/course/loadTestList?chapterFlow='/>"+chapterFlow+"&selfFlag="+selfFlag;
	    jboxLoad("content",url,false);
 }
 function searchSelfTest(obj){
     if($(obj).attr("checked")=="checked"){
  	   $(obj).val("Y");
     }else{
  	   $(obj).val("N");
     }
     loadTestList('${param.chapterFlow}');
  }
 function bindTest(paperFlow,chapterFlow){
 	var checkBox=$("#check_"+paperFlow);
 	var type="";
 	if(checkBox.is(":checked")){
 		type="add";
 	}else{
 		type="del";
 	}
 	var url="<s:url value='/resedu/manage/course/changePaperBind'/>?paperFlow="+paperFlow+"&chapterFlow="+chapterFlow+"&type="+type;
 	 jboxPost(url,null,function(resp){
		           if(resp=='${GlobalConstant.ONE_LINE}'){
		        	   jboxTip('${GlobalConstant.OPRE_SUCCESSED}');
		           }else if(resp=='${GlobalConstant.CAN_NOT_BIND_FLAG}'){
		        	   jboxTip('${GlobalConstant.CAN_NOT_BIND}');
		        	   checkBox.removeAttr("checked");
		           }else{
		        	  jboxTip('${GlobalConstant.OPRE_FAIL}');
		        	   checkBox.removeAttr("checked");
		           }
		           var testCount=$("#testCount").val();
		           var courseTestCount=$("#content").find('input:checkbox[name="courseTestCount"]:checked').length;
		           if(testCount>0 && courseTestCount>0 && testCount==courseTestCount){
		        	  $("#selfFlag").attr("checked","checked");
		        	   $("#selfFlag").attr("disabled","disabled");
		           }else{
		        	  $("#selfFlag").removeAttr("checked");
		        	   $("#selfFlag").removeAttr("disabled");
		           }
				},
				null,false);
 }
 function addTestPaper(){
 	var w = $('.mainright').width();
 	var h = $('.mainright').height();
 	var url="<s:url value='/resedu/manage/course/addTestPaper'/>?chapterFlow=${param.chapterFlow}&refreshFlag=N";
 	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
 	jboxMessager(iframe,'出题',w,h,null,false);
     
 }
 function testResultInfo(paperFlow){
 	var url="<s:url value='/resedu/manage/course/testResultInfo'/>?paperFlow="+paperFlow;
 	var w = $('.mainright').width()*0.8;
		var h = $('.mainright').height()*0.8;
		jboxOpen(url, "查看考试结果",w, h);
 }
 function back(){
 	var url="<s:url value='/resedu/manage/course/courseList/personal'/>";
 	window.location.href=url;
 }
 </script>
 <table class="xllist" style="width: 100%;margin-top: 10px;">
             <colgroup>
             <col width="9%"/>
   		     <col width="13%"/>
   		     <col width="13%"/>
   		     <col width="13%"/>
   		     <col width="13%"/>
   		     <col width="13%"/>
   		     <col width="13%"/>
   		     <col width="13%"/>
   		  </colgroup>
             <thead>
                <tr>
                   <td colspan="8" style="text-align: left;padding-left: 10px;">
                                                                 试卷信息
                      &#12288;<input type="checkBox" id="selfFlag" value="${param.selfFlag }" class="只显示本课程试卷" onclick="searchSelfTest(this);"
                      <c:if test="${param.selfFlag eq 'Y'}">checked</c:if>/>
                      <label for="selfFlag">只显示本章节试卷</label>
                   </td>
                </tr>
                <tr>
                   <th>选择试卷</th>
                   <th>考卷名称</th>
                   <th>考试时间（分钟）</th>
                   <th>出题方式</th>
                   <th>出卷人</th>
                   <th>总分</th>
                   <th>及格分</th>
                   <th>操作</th>
                </tr>
             </thead>
             <tbody id="content">
              
             </tbody>
         </table>
            <p align="center" style="width:100%">
                <input class="search" type="button" value="出&#12288;题"  onclick="addTestPaper();" />
				<input class="search" type="button" value="返&#12288;回"  onclick="back();" />
			</p>