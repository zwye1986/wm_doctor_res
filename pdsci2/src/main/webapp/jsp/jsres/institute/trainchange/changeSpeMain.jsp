<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript">
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		var spaceId = this[0].id;
		searchInput.on("keyup focus",function(){
			var boxHome = $("#"+spaceId+"Sel");
			boxHome.show();
			 var pDiv=$(boxHome).parent();
			 $(pDiv).css("left",$("#train").outerWidth());
			 var w=$(this).css("marginTop").replace("px","");
			 w=w-0+$(this).outerHeight()+6+"px";
			 $(pDiv).css("top",w);
			if($.trim(this.value)){
				$("p."+spaceId+".item",boxHome).hide();
				var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
				changeOrgFlow(this);
			}else{
				$("p."+spaceId+".item",boxHome).show();
			}
		});
		searchInput.on("blur",function(){
			var boxHome = $("#"+ spaceId+"Sel");
			if(!$("."+spaceId+".boxHome.on").length){
				boxHome.hide();
			}
		});
		$("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		
		$("."+spaceId+".boxHome .item").click(function(){
			var boxHome = $("."+spaceId+".boxHome");
			boxHome.hide();
			var value = $(this).attr("value");
			var input = $("#"+spaceId);
			input.val(value);
			if(option.clickActive){
				option.clickActive($(this).attr("flow"));
				$("#orgFlow").val($(this).attr("flow"));
			}
		});
	};
})(jQuery);
function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
 }
$(function(){
	if($("#trainOrg").length){
		$("#trainOrg").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			}
		});
	}
});
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	if("${speFlag}"=="0"){
		$("#speFlag").hide();
	}
});
function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/jsres/institute/getChangeOrgDetail'/>?doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow;
	jboxOpen(url, "详情", 1050, 550);
}
function search(){
	var url="<s:url value='/jsres/institute/searchChangeSpe'/>?viewFlag=${param.viewFlag}";
	jboxPostLoad("content",url,$("#inForm").serialize(),false);
}
function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);			
	}
	search();
}
function changeStatus(){
	 if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 $("#orgFlow").val("");
	 }
}
</script>
<div class="main_hd">
	<h2 class="underline">专业变更查询</h2>
</div>
<div class="main_bd">
	<div class="div_table">
		<div class="div_search" style="padding:0px;margin:0px;">
		<form id="inForm">
			<input type="hidden" name="currentPage" id="currentPage"/>
				<label id="train">培训基地：</label>
				<input id="trainOrg" oncontextmenu="return false"  name="orgName" value="${param.orgName}" class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 156px" onkeydown="changeStatus();" onkeyup="changeStatus();" />
				<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
			    <div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
				    <c:forEach items="${orgs}" var="org">
				    	<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}"  style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
				    	<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
				    	>${org.orgName}</p>
				    </c:forEach>
				 </div>
			   <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
			</div>&nbsp;
	      	姓&#12288;名：<input type="text" name="doctorName" value="${param.doctorName}" class="input" style="width: 100px;"/>&nbsp;
      	 	届&#12288;别：
			<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&nbsp;
		   	<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
			   	 是否通过：
			   	 <select name="passFlag"  class="select" style="width:107px;">
		        	<option value="">请选择</option>
		        	<option value="${GlobalConstant.FLAG_Y}" <c:if test="${param.passFlag eq GlobalConstant.FLAG_Y }">selected="selected"</c:if>>是</option>
		        	<option value="${GlobalConstant.FLAG_N }" <c:if test="${param.passFlag eq GlobalConstant.FLAG_N  }">selected="selected"</c:if>>否</option>
				</select>&nbsp;
			</c:if>
		    <input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>
         </form>
         </div>
      
           <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
              <col width="10%"/>
              <col width="5%"/>
              <col width="18%"/>
              <col width="15%"/>
              <col width="11%"/>
              <col width="11%"/>
              <c:if test="${GlobalConstant.FLAG_Y eq param.viewFlag }">
              <col width="10%"/>
              </c:if>
              <col width="20%"/>
            </colgroup>
	           <thead>
	           <tr>
	           	  <th>姓名</th>
	           	  <th>届别</th>
	              <th>培训基地</th>
	              <th>审核状态</th>
	              <th>原培训专业</th>
	              <th>变更后专业</th>
	              <c:if test="${GlobalConstant.FLAG_Y eq param.viewFlag }">
	              <th>附件</th>
	              </c:if>
	              <th>操作</th>
	           </tr>
	           </thead>
	           <tbody>
	           <c:forEach items="${historyExts }" var="docOrgHistoryExt">
	           <tr>
	           	   <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
	           	   <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
	           	   <td>${docOrgHistoryExt.historyOrgName}</td>
	           	  <td>${docOrgHistoryExt.changeStatusName}</td>
	           	   <td>${docOrgHistoryExt.historyTrainingSpeName}</td>
	           	   <td>${docOrgHistoryExt.trainingSpeName}</td>
	           	   <c:if test="${GlobalConstant.FLAG_Y eq param.viewFlag }">
	           	   <td>[<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]</td>
	               </c:if>
	               <td>
	               		<a class="btn" onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
	               </td>	
	           </tr>
	           </c:forEach>
	           
	           <c:if test="${empty historyExts }">
	           <tr>
	           	   <td colspan="8">无记录</td>
           	   </tr>
	           </c:if>
	           </tbody>
           </table>
		</div>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(historyExts)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>	 
        </div>
</div>
