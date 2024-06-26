<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<style type="text/css">
	table.basic th,table.basic td{text-align: center;padding: 0;}
	.highLight{background: rgba(255,0,0,0.8);}
	.lastOper{background: pink;}
	.moveColor{background: rgba(225, 238, 245,0.95);}
</style>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>

<c:set var="isMust" value="${!(param.mustDate eq GlobalConstant.FLAG_N)}"/>

<script type="text/javascript">
	var oldFirst;//排序前第一个元素

// 	String.prototype.htmlFormart = function(){
// 		var html = this;
// 		for(var index in arguments){
// 			var reg = new RegExp('\\{'+index+'\\}','g');
// 			html = html.replace(reg,arguments[index]);
// 		}
// 		return html;
// 	};

	//初始化操作
    <c:if test="${series eq 'N'}">
    $(function(){
        $(".search").hide();
        $(".schMonthInput").attr("readonly","readonly");
        $(".start,.end").removeAttr("onclick");
        $(".aTd a").hide();
    })
    </c:if>

    <c:if test="${series ne 'N'}">//如果存在多个不连续已出科，则不允许更改
	$(function(){
		saveFirstResult();
		<%--<c:if test="${rotation.isCrisscrossFlag eq GlobalConstant.FLAG_Y}">--%>
			var oldData = "";
		    $("#sortResult").sortable({
		    	helper: function(e, ui) {
			   	    ui.children().each(function() {
				        $(this).width($(this).width());
			     	});
				    return ui;
				},
		    	create: function(e,ui){
		    		var oldSortedIds = $("#sortResult").sortable("toArray");
		    		$.each(oldSortedIds,function(i,sortedId){
		    			oldData = oldData+"&recordFlow="+sortedId;
		    		});
		    	},
		    	start:function(e,ui){
		    		$(".lastOper").removeClass("lastOper");
		    	    ui.helper.addClass("moveColor");
		    	    saveFirstResult($(".resultHome:first"));
		    	    return ui;
		    	},
		    	stop: function(event,ui) {
		    		$(".moveColor").removeClass("moveColor");
		    		ui.item.addClass("lastOper");

		    		var operEndIndex = $(".resultHome").index(ui.item);
		    		var startId = ui.item[0].id;
		    		lastOperResultFlow = startId;
		    		if(operEndIndex > 0){
		    			startId = $(".resultHome:eq("+(operEndIndex-1)+")")[0].id;
		    		}
		    		getCurrEleIndex(startId);

// 		    		ui.item.css({"backgroundColor":"#fff"});
		    		var sortedIds = $( "#sortResult" ).sortable("toArray");
		    		var postdata = "";
		    		$.each(sortedIds,function(i,sortedId){
		    			postdata = postdata+"&resultFlow="+sortedId;
		    		});
		    		if(oldData==postdata){
		    			return;
		    		}
		    		jboxPost("<s:url value='/res/doc/sortAndCalculate'/>",postdata+"&resultNum=${resultList.size()}",function(){
		    			jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
		    		},null,false);
		    		oldData = postdata;
		    	}
		    });
		<%--</c:if>--%>

		<%--<c:if test="${!(rotation.isCrisscrossFlag eq GlobalConstant.FLAG_Y)}">--%>
			<%--$("#containerFree").append($(".sort"));--%>
			<%----%>
			<%--var oldDataFree = "";--%>
		    <%--$("#containerFree").sortable({--%>
		    	<%--helper: function(e, ui) {--%>
			   	    <%--ui.children().each(function() {--%>
				        <%--$(this).width($(this).width());--%>
			     	<%--});--%>
				    <%--return ui;--%>
				<%--},  --%>
		    	<%--create: function(e,ui){--%>
		    		<%--var oldSortedIds = $("#containerFree").sortable("toArray");--%>
		    		<%--$.each(oldSortedIds,function(i,sortedId){--%>
		    			<%--oldDataFree = oldDataFree+"&recordFlow="+sortedId;--%>
		    		<%--});--%>
		    	<%--},--%>
		    	<%--start:function(e,ui){--%>
		    		<%--$(".lastOper").removeClass("lastOper");--%>
		    	    <%--ui.helper.addClass("moveColor");--%>
		    	    <%--saveFirstResult($(".resultHome:first"));--%>
		    	    <%--return ui;--%>
		    	<%--}, --%>
		    	<%--stop: function(event,ui) {--%>
		    		<%--$(".moveColor").removeClass("moveColor");--%>
		    		<%--ui.item.addClass("lastOper");--%>
		    		<%--var operEndIndex = $(".resultHome").index(ui.item);--%>
		    		<%--var startId = ui.item[0].id;--%>
		    		<%--lastOperResultFlow = startId;--%>
		    		<%--if(operEndIndex > 0){--%>
		    			<%--startId = $(".resultHome:eq("+(operEndIndex-1)+")")[0].id;--%>
		    		<%--}--%>
		    		<%--getCurrEleIndex(startId);--%>
<%--// 		    		ui.item.css({"backgroundColor":"#fff"});--%>
		    		<%--var sortedIds = $( "#containerFree" ).sortable("toArray");--%>
		    		<%--var postdata = "";--%>
		    		<%--$.each(sortedIds,function(i,sortedId){--%>
		    			<%--postdata = postdata+"&resultFlow="+sortedId;--%>
		    		<%--});--%>
		    		<%--if(oldDataFree==postdata){--%>
		    			<%--return;--%>
		    		<%--}--%>
		    		<%--postdata = "";--%>
		    		<%--$(".resultHome").each(function(){--%>
		    			<%--postdata+=("&resultFlow="+this.id);--%>
		    		<%--});--%>
		    		<%--jboxPost("<s:url value='/res/doc/sortAndCalculate'/>",postdata+"&resultNum=${resultList.size()}",function(){--%>
		    			<%--jboxTip("${GlobalConstant.OPRE_SUCCESSED}");--%>
		    		<%--},null,false);--%>
		    		<%--oldDataFree = postdata;--%>
		    	<%--}--%>
		    <%--});--%>
		    <%----%>
			<%--<c:forEach items="${schStageEnumList}" var="stage">--%>
				<%--$("#container${stage.id}").append($(".sort${stage.id}"));--%>
				<%----%>
				<%--var oldData${stage.id} = "";--%>
			    <%--$("#container${stage.id}").sortable({--%>
			    	<%--helper: function(e, ui) {--%>
				   	    <%--ui.children().each(function() {--%>
					        <%--$(this).width($(this).width());--%>
				     	<%--});--%>
					    <%--return ui;--%>
					<%--},  --%>
			    	<%--create: function(e,ui){--%>
			    		<%--var oldSortedIds = $("#container${stage.id}").sortable("toArray");--%>
			    		<%--$.each(oldSortedIds,function(i,sortedId){--%>
			    			<%--oldData${stage.id} = oldData${stage.id}+"&recordFlow="+sortedId;--%>
			    		<%--});--%>
			    	<%--},--%>
			    	<%--start:function(e,ui){--%>
			    		<%--$(".lastOper").removeClass("lastOper");--%>
			    	    <%--ui.helper.addClass("moveColor");--%>
			    	    <%--saveFirstResult($(".resultHome:first"));--%>
			    	    <%--return ui;--%>
			    	<%--}, --%>
			    	<%--stop: function(event,ui) {--%>
			    		<%--$(".moveColor").removeClass("moveColor");--%>
			    		<%--ui.item.addClass("lastOper");--%>
			    		<%--var operEndIndex = $(".resultHome").index(ui.item);--%>
			    		<%--var startId = ui.item[0].id;--%>
			    		<%--lastOperResultFlow = startId;--%>
			    		<%--if(operEndIndex > 0){--%>
			    			<%--startId = $(".resultHome:eq("+(operEndIndex-1)+")")[0].id;--%>
			    		<%--}--%>
			    		<%--getCurrEleIndex(startId);--%>
<%--// 			    		ui.item.css({"backgroundColor":"#fff"});--%>
			    		<%--var sortedIds = $( "#container${stage.id}" ).sortable("toArray");--%>
			    		<%--var postdata = "";--%>
			    		<%--$.each(sortedIds,function(i,sortedId){--%>
			    			<%--postdata = postdata+"&resultFlow="+sortedId;--%>
			    		<%--});--%>
			    		<%--if(oldData${stage.id}==postdata){--%>
			    			<%--return;--%>
			    		<%--}--%>
			    		<%--postdata = "";--%>
			    		<%--$(".resultHome").each(function(){--%>
			    			<%--postdata+=("&resultFlow="+this.id);--%>
			    		<%--});--%>
			    		<%--jboxPost("<s:url value='/res/doc/sortAndCalculate'/>",postdata+"&resultNum=${resultList.size()}",function(resp){--%>
			    			<%--jboxTip("${GlobalConstant.OPRE_SUCCESSED}");--%>
			    		<%--},null,false);--%>
			    		<%--oldData${stage.id} = postdata;--%>
			    	<%--}--%>
			    <%--});--%>
			<%--</c:forEach>--%>
		<%--</c:if>--%>

	    $(".isAfter").each(function(){
	    	$(this).removeClass("resultHome");
	    	$(".start,.end",this).each(function(){
		    	var value = this.value;
		    	$(this).hide().after('<label>'+value+'</label>');
		    });
	    });
	    $("#afterResult").append($(".isAfter"));

	    <c:if test="${empty rotation && !(GlobalConstant.FLAG_Y eq doctor.schFlag)}">
// 	    	$("#isRequiredTh").css("width","10%");
// 	    	$(".free").show();
	    </c:if>

	    if(lastOperResultFlow){
	    	$("#"+lastOperResultFlow).addClass("lastOper");
	    }
	});
    </c:if>

	//开始排班,创建排班数据
	function startResult(doctorFlow){
		jboxConfirm("开始后该医师的选科将不能修改！确认开始？",function(){
			jboxPost("<s:url value='/sch/doc/createResults'/>?doctorFlow="+doctorFlow,null,function(resp){
				if(resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					$(".selDoc").click();
				}
			},null,false);
		},null);
	}
	
	//保存自定义时间
	function diyDate(data){
		var resultFlow = data.resultFlow;
		var start = $("#"+resultFlow).find(".start").val();
		var end = $("#"+resultFlow).find(".end").val();
		if(start && end && (start>=end)){
			return jboxTip("结束时间必须大于开始时间！");
		}
		
		var result = true;
		$(".resultHome").each(function(){
			var currStart = $(this).find(".start").val();
			var currEnd = $(this).find(".end").val();
			if((start || end) && currStart && currEnd && resultFlow!=this.id){
				if(start)
					result = (start<currStart) || (start>currEnd);
				else if(end)
					result = (end<currStart) || (end>currEnd);
				else if(start && end)
					result = (start<currStart && end<currStart) || (start>currEnd && end>currEnd);
				if(!result) 
					return result;
			}
		});
		if(!result){
			if(data.schStartDate){
				$("#"+resultFlow+" .start").val("");
			}else if(data.schEndDate){
				$("#"+resultFlow+" .end").val("");
			}
			return jboxTip("该日期与其他日期产生重叠！");
		}
		if(data.schStartDate){
			data.schMonth = $("#"+resultFlow).attr("schMonth") || "";
		}
		var url = "<s:url value='/res/doc/saveDiyDate'/>";
		jboxPost(url,data,function(resp){
// 			if(data.schStartDate){
// 				$("#"+resultFlow+" .end").val(resp);
// 			}
			if(data.schEndDate){
				getCurrEleIndex(resultFlow);
			}
		},null,false);
	}
	
	//包装排班列表参数
	function createResults(index){
		var selector = "";
		if(index || index==0){
			selector = ":gt("+index+")";
		}
		var postdata = "";
		$(".resultHome:not(.isAfter)"+selector).each(function(){
			postdata+=("&resultFlow="+(this.id));
		});
		return postdata;
	}
	
	//自动计算日期
	function autoCount(startDate){
		<c:if test="${empty rotation && !(GlobalConstant.FLAG_Y eq doctor.schFlag)}">
// 			$(".schMonthInput").each(function(){
// 			});
		</c:if>
		var startDate = $("#startDate").val() || startDate;
		if(!startDate)
			return jboxTip("请选择开始日期！");
		
		var postdata = createResults();
		
		postdata+=("&startDate="+startDate);
		var mustdate = "";
		if($("#mustDate").length){
			mustdate = "&mustDate="+(($("#mustDate")[0].checked)?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
		}
		var url = "<s:url value='/res/doc/sortAndCalculate'/>";
		jboxPost(url, postdata+"&resultNum=${resultList.size()}"+mustdate, function(){
			$(".selDoc").click();
		},null,false);
	}
	
	//清空排班
	function clearCount(){
		jboxConfirm("确认清空排班数据？",function(){
			var postdata = createResults();
			
			postdata+=("&clear=true");
			var url = "<s:url value='/res/doc/sortAndCalculate'/>";
			jboxPost(url, postdata+"&resultNum=${resultList.size()}", function(){
				$(".selDoc").click();
			},null,false);
		},null);
	}
	
	function checkSelDeptMonth(groups){
		var isOk = true;
		for(var index in groups){
			var groupFlow = groups[index].groupFlow;
			var groupMonth = groups[index].schMonth;
			var countMonth = 0;
			$("."+groupFlow).each(function(){
				countMonth+=(parseFloat(this.value));
			});
			if(countMonth!=groupMonth){
				return isOk = false;
			}
		}
		return isOk;
	}
	
	//完成排班
	function rostingFinish(){
		<c:if test="${!empty groupList}">
			//选科排班的总时间判断
// 			var groups = [
// 			      <c:forEach items="${groupList}" var="group">
// 			      		{"groupFlow":"${group.groupFlow}","schMonth":${group.schMonth}},
// 			      </c:forEach>
// 			                  ];
// 			if(!checkSelDeptMonth(groups)){
// 				return jboxTip("请先完成选科时间调整！");
// 			}
		</c:if>
		
// 		var haveEmpty = false;
// 		$(".start,.end").each(function(){
// 			return !(haveEmpty = !this.value);
// 		});
// 		if(haveEmpty){
// 			return jboxTip("请先完成排班！");
// 		}
		jboxConfirm("确认完成该学员的计划？",function(){
			jboxPost("<s:url value='/res/doc/confirmRosting'/>",
					{doctorFlow:"${doctor.doctorFlow}",schFlag:"${GlobalConstant.FLAG_Y}"},
					function(resp){
				if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					$(".selDoc").click();
				}
			},null,false);
		},null);
	}
	
	function editResult(){
		var content = $("#selSchDeptHome").html();
		jboxOpenContent(content,"选择要轮转的科室",800,500);
	}
	
	function saveSelResult(selResultTd){
		var addResult = [];
		var delResult = [];
		selResultTd.find(":checkbox:checked:not([isResult])").each(function(){
			addResult.push(this.value);
		});
		selResultTd.find(":checkbox[isResult]:not(:checked)").each(function(){
			delResult.push($(this).attr("isResult"));
		});
		
		var data = "";
		if(addResult.length>0){
			data += "&";
			data+=serializeList("addSchDeptFlows",addResult);
		}
		if(delResult.length>0){
			data+=("&"+serializeList("delResultFlows",delResult));
		}
		jboxPost("<s:url value='/sch/template/saveFreeRostering'/>?doctorFlow=${doctor.doctorFlow}"+data,null,function(resp){
			if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
				top.jboxContentClose();
				$(".selDoc").click();
			}
		},null,false);
	}
	
	function serializeList(name,list){
		var result = "";
		for(var index in list){
			if(result){
				result+=("&"+name+"="+list[index]);
			}else{
				result+=(name+"="+list[index]);
			}
		}
		return result;
	}
	
	//保存轮转时长
	function saveSchMonth(resultFlow,schMonth,groupFlow,groupMonth,groupName){
		if(isNaN(schMonth) || schMonth==""){
			return jboxTip("请输入数字！");
		}
		
// 		if(groupFlow){
// 			var countMonth = 0;
// 			$("."+groupFlow).each(function(){
// 				countMonth+=(parseFloat(this.value));
// 			});
// 			if(countMonth>groupMonth){
// 				jboxTip(groupName+"组合的总轮转时间必须等于"+groupMonth+"${applicationScope[unitKey].name}");
// 			}
// 		}
		
		var data = {};
		data.resultFlow = resultFlow;
		schMonth = parseFloat(schMonth);
		
		if(schMonth>99 || schMonth<0.5){
			return jboxTip("请输入合法数字(0.5~99)！");
		}
		
		data.schMonth = schMonth;
		var url = "<s:url value='/res/doc/saveDiyDate'/>";
		jboxPost(url,data,function(resp){
			if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
				$("#"+resultFlow).attr("schMonth",schMonth);
			}
		},null,false);
	}
	
	//获取当前这个元素的位置
	function getCurrEleIndex(id){
		var startResultIndex = $(".resultHome").index($("#"+id));
		if($(".resultHome").length-1!=startResultIndex){
			var startDate = $("#"+id+" .end").val();
			var currStart = $("#"+id+" .start").val();
			
			var fStart = $(".start",saveFirstResult()).val();
			if(startResultIndex == 0 && currStart!=fStart){
				return autoCount(fStart);
			}
			
			if(startDate){
				var postdata = createResults(startResultIndex);
				postdata+=("&startDate="+startDate);
				var mustdate = "";
				if($("#mustDate").length){
					mustdate = "&mustDate="+(($("#mustDate")[0].checked)?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
				}
				var url = "<s:url value='/res/doc/sortAndCalculate'/>";
				jboxPost(url, postdata+"&resultNum=${resultList.size()}&addOneDay=true"+mustdate, function(resp){
					$(".selDoc").click();
				},null,false);
			}
		}
	}
	
	//记录下第一条
	function saveFirstResult(first){
		if(first){
			oldFirst = $(".resultHome:first");
		}
		return oldFirst;
	}
	
	//删除排班
	function delDocRostering(){
		jboxConfirm("确认删除排班？",function(){
			jboxGet("<s:url value='/sch/template/delDocRostering'/>?doctorFlow=${doctor.doctorFlow}",null,function(resp){
				if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					$(".selDoc").click();
				}
			},null,false);
		},null);
	}
	
	//切换时间控件
	function togglePicker(flag){
		$(".selDoc").click();
	}
</script>
</head>
<c:set var="isWeek" value="${sysCfgMap['res_rotation_unit'] eq schUnitEnumWeek.id}"/>
<body>
	<table class="basic" style="margin-left: 10px;width: 98%;">
		<tr>
			<td style="text-align: left;padding-left: 10px;">
			<div>
			姓名：${doctor.doctorName}
			&#12288;
			轮转方案：
			<c:if test="${empty rotation}">
				无
			</c:if>
			<c:if test="${!empty rotation}">
				<a style="color: blue;cursor: pointer;" onclick="openDetail('${rotation.rotationName}','${rotation.rotationFlow}');">${rotation.rotationName}</a>
			</c:if>
			&#12288;
			轮转年限：<c:out value="${rotation.rotationYear}" default="无"/>
			</div>
			<div>
			<c:if test="${empty resultList && !empty doctor.rotationFlow}">
				<input type="button" value="开始排班" class="search" style="float: right;margin-right: 10px;margin-top: 4px;<c:if test="${!(GlobalConstant.FLAG_Y eq doctor.selDeptFlag)}">display: none;</c:if>" onclick="startResult('${doctor.doctorFlow}');">
			</c:if>
			<c:if test="${sysCfgMap['res_diy_sch_dept'] eq GlobalConstant.FLAG_Y}">
				<input type="button" value="增加轮转科室" class="search" style="float: right;margin-right: 10px;margin-top: 4px;" onclick="editResult();"/>
			</c:if>
<%-- 			<c:if test="${!empty resultList && !(GlobalConstant.FLAG_Y eq doctor.schFlag)}"> --%>
			<c:if test="${empty processMap}">
				<input type="button" value="删除排班" class="search" style="float: right;margin-right: 10px;margin-top: 4px;" onclick="delDocRostering();">
				<input type="button" value="清空排班" class="search" style="float: right;margin-right: 10px;margin-top: 4px;" onclick="clearCount();">
				<input type="button" value="自动排班" class="search" style="float: right;margin-right: 10px;margin-top: 4px;" onclick="autoCount();">
				
				<c:if test="${isMust}">
					<input 
					class="mustpicker"
					type="text" 
					style="
					float: right;
					margin-right: 10px;
					margin-top: 7px;
					cursor: pointer;
					width: 100px;
					" 
					id="startDate" 
					readonly="readonly" 
					<c:if test="${!isWeek}">
					onclick="WdatePicker({opposite:true,disabledDates:['0[1]$','1[6]$']});"
					</c:if>
					<c:if test="${isWeek}">
					onclick="WdatePicker({disabledDays:[0,2,3,4,5,6]});"
					</c:if>
					value="${param.startDate}"
					/>
				</c:if>
				
				<c:if test="${!isMust}">
					<input 
					class="mustpicker"
					type="text" 
					style="
					float: right;
					margin-right: 10px;
					margin-top: 7px;
					cursor: pointer;
					width: 100px;
					" 
					id="startDate" 
					readonly="readonly" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
					value="${param.startDate}"
					/>
				</c:if>
			</c:if>
			
<%-- 			</c:if> --%>
			
			<label style="float: right;margin-right: 10px;">
			<input 
			type="checkbox" 
			id="mustDate" 
			<c:if test="${isMust}">
			checked
			</c:if> 
			value="${GlobalConstant.FLAG_Y}" 
			onchange="togglePicker(this.checked);"
			/>
			强制月初[中]入科，月底出科&nbsp;
			</label>
			
			</div>
			</td>
		</tr>
	</table>
	<table class="basic" style="width: 98%;margin-top: 10px;margin-left: 10px;">
		<tr>
			<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
				<th width="150px">轮转阶段</th>
			</c:if>
			<th width="200px">标准科室</th>
			<th width="150px">轮转科室</th>
			<th width="100px">轮转时长</th>
			<th width="220px">轮转时间</th>
			<c:if test="${!empty processMap}">
				<th width="100px">轮转状态</th>
			</c:if>
		</tr>
		
		<tbody id="afterResult"></tbody>
		
		<c:set var="sumMonth" value="0"/>
		<tbody id="sortResult">
			<c:forEach items="${resultList}" var="result">
				<c:set var="sumMonth" value="${sumMonth+result.schMonth }"/>
				<tr id="${result.resultFlow}" schMonth="${result.schMonth}" class="resultHome <c:if test="${processMap[result.resultFlow].schFlag eq GlobalConstant.FLAG_Y}"> isAfter</c:if> sort${groupMap[result.groupFlow].schStageId}" style="cursor: move;">
					<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
						<td>
							${groupMap[result.groupFlow].schStageName}
						</td>
					</c:if>
					<td>
						<c:if test="${!empty result.standardDeptId}">
							${result.standardDeptName}
						</c:if>
						<c:if test="${empty result.standardDeptId}">
							<c:forEach items="${deptRelMap[result.schDeptFlow]}" var="deptRel" varStatus="relStatus">
								<c:if test="${!relStatus.first}">
									,
								</c:if>
								${deptRel.standardDeptName}
							</c:forEach>
						</c:if>
					</td>
					<td>
						${result.schDeptName}
<%-- 						<c:if test="${GlobalConstant.FLAG_N eq result.isRequired && !empty result.rotationFlow}"> --%>
<!-- 							<br/> -->
<%-- 							(${groupMap[result.groupFlow].groupName}：${groupMap[result.groupFlow].schMonth}${applicationScope[unitKey].name}) --%>
<%-- 						</c:if> --%>
					</td>
					<td>
<%-- 						<c:if test="${(empty result.standardDeptId || !(GlobalConstant.FLAG_Y eq result.isRequired)) && empty processMap[result.resultFlow] && !(GlobalConstant.FLAG_Y eq doctor.schFlag)}"> --%>
<%-- 							<input class="schMonthInput <c:if test="${!(GlobalConstant.FLAG_Y eq result.isRequired) && !empty result.rotationFlow}"> ${result.groupFlow}</c:if>" type="text" value="${result.schMonth}" style="width: 20px;text-align: center;" onchange="saveSchMonth('${result.resultFlow}',this.value,'${result.groupFlow}',${groupMap[result.groupFlow].schMonth+0},'${groupMap[result.groupFlow].groupName}');"/> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${!((empty result.standardDeptId || !(GlobalConstant.FLAG_Y eq result.isRequired)) && empty processMap[result.resultFlow] && !(GlobalConstant.FLAG_Y eq doctor.schFlag))}"> --%>
<%-- 							${result.schMonth} --%>
<%-- 						</c:if> --%>
						<c:if test="${empty result.standardDeptId && empty processMap[result.resultFlow]}">
							<input class="schMonthInput" type="text" value="${result.schMonth}" style="width: 55px;text-align: center;" onchange="saveSchMonth('${result.resultFlow}',this.value,'${result.groupFlow}',${groupMap[result.groupFlow].schMonth+0},'${groupMap[result.groupFlow].groupName}');"/>
						</c:if>
						<c:if test="${!(empty result.standardDeptId && empty processMap[result.resultFlow])}">
							${result.schMonth}
						</c:if>
						${applicationScope[unitKey].name}
					</td>
					<td>
						<input class="start" type="text" value="${result.schStartDate}" style="width: 80px;cursor: pointer;" readonly="readonly" 
						<c:if test="${isMust && isWeek}">
							onclick="WdatePicker({disabledDays:[0,2,3,4,5,6]});"
						</c:if>
						<c:if test="${isMust && !isWeek}">
							onclick="WdatePicker({opposite:true,disabledDates:['0[1]$','1[6]$']});" 
						</c:if>
						<c:if test="${!isMust}">
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
						</c:if>
						onchange="diyDate({resultFlow:'${result.resultFlow}',schStartDate:this.value});"
						/>
						~
						<input class="end" type="text" value="${result.schEndDate}" style="width: 80px;cursor: pointer;" readonly="readonly" 
						<c:if test="${isMust && isWeek}">
							onclick="WdatePicker({disabledDays:[1,2,3,4,5,6]});"
						</c:if>
						<c:if test="${isMust && !isWeek}">
							onclick="WdatePicker({opposite:true,disabledDates:['1[5]$'<c:forEach items="${lastDays}" var="day">,'${day}'</c:forEach>]});" 
						</c:if>
						<c:if test="${!isMust}">
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
						</c:if>
						onchange="diyDate({resultFlow:'${result.resultFlow}',schEndDate:this.value});"/>
					</td>
					<c:if test="${!empty processMap}">
						<td>
							<c:if test="${processMap[result.resultFlow].schFlag eq GlobalConstant.FLAG_Y}">
								已出科
							</c:if>
							<c:if test="${processMap[result.resultFlow].isCurrentFlag eq GlobalConstant.FLAG_Y}">
								轮转中
							</c:if>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
		
		<c:forEach items="${schStageEnumList}" var="stage">
			<tbody id="container${stage.id}"></tbody>
		</c:forEach>
		
		<tbody id="containerFree"></tbody>
		
		<c:if test="${empty resultList}">
			<tr>
				<td colspan="4">无记录</td>
			</tr>
		</c:if>
		<c:if test="${!empty resultList}">
			<tr>
				<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
					<th width="150px" colspan="3" style="text-align: right">合计：&#12288;</th>
				</c:if>
				<c:if test="${!(rotation.isStage eq GlobalConstant.FLAG_Y)}">
					<th width="150px" colspan="2" style="text-align: right">合计：&#12288;</th>
				</c:if>
				<th width="220px">${sumMonth }</th>
				<c:if test="${!empty processMap}">
					<th width="100px"></th>
				</c:if>
				<c:if test="${empty processMap}">
					<th width="100px" colspan="2"></th>
				</c:if>
			</tr>
		</c:if>
	</table>
	
	<div align="center" style="margin-top: 10px;margin-bottom: 20px;">
		<c:if test="${!(doctor.schFlag eq GlobalConstant.FLAG_Y) && !empty resultList}">
			<input type="button" value="完成排班" class="search" onclick="rostingFinish();"/>
		</c:if>
	</div>
	
	<div id="selSchDeptHome" style="display: none;">
		<table class="basic" style="width:100%;">
			<tr>
				<th style="text-align: left;padding-left: 10px;">
					选择要轮转的科室
<%-- 					<input id="doctorFlow" type="hidden" value="${doctor.doctorFlow}"/> --%>
				</th>
			</tr>
			<tr>
				<td id="selResultTd">
					<c:forEach items="${schDeptList}" var="schDept">
						<div style="float: left;width:23%;padding-left:10px;text-align: left;"><label><input type="checkbox" value="${schDept.schDeptFlow}" <c:if test="${!empty resultMap[schDept.schDeptFlow]}"> checked isResult="${resultMap[schDept.schDeptFlow].resultFlow}"</c:if> <c:if test="${!empty processMap[resultMap[schDept.schDeptFlow].resultFlow]}">disabled="disabled"</c:if>>${schDept.schDeptName}</label></div>
					</c:forEach>
				</td>
			</tr>
		</table>
		<div align="center" style="margin: 10px 0px;">
			<input type="button" value="保&#12288;存" class="search" onclick="window.document.mainIframe.saveSelResult($('#selResultTd'));"/>
			<input type="button" value="关&#12288;闭" class="search" onclick="top.jboxContentClose();"/>
		</div>
	</div>
</body>
</html>