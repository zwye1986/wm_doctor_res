

//拆分单元格
	jQuery.fn.RevertTable = function () {
		$("tbody tr", this).each(function(trindex, tritem) {
			$(tritem).find("td").each(function (tdindex, tditem) {
				$(tditem).attr("Rt","td_"+trindex+"_"+tdindex);
			});
		});
		$("thead tr", this).each(function(trindex, tritem) {
			$(tritem).find("th").each(function (tdindex, tditem) {
				$(tditem).attr("Rt","th_"+trindex+"_"+tdindex);
			});
		});
		$("tbody tr", this).each(function(trindex, tritem) {
			$(tritem).find("td").each(function (tdindex, tditem) {
				var rowspanCount = $(tditem).attr("rowspan");
				var colspanCount = $(tditem).attr("colspan");
				var tdId=$(tditem).attr("Rt");
				var value = $(tditem).html();
				if (colspanCount > 1) {
					var newtd = "<td Rt="+"'"+tdId+"' rowspan='"+rowspanCount+"'>" + value + "</td>";
					while (colspanCount-- > 1) {
						$(tditem).after(newtd);
					}
					$(tditem).attr("colspan", 1);
					$(tditem).removeAttr("colspan");
				}
				
			});
		});
		$("tbody tr", this).each(function(trindex, tritem) {
			$(tritem).find("td").each(function (tdindex, tditem) {
				var rowspanCount = $(tditem).attr("rowspan");
				var tdId=$(tditem).attr("Rt");
				var value = $(tditem).html();
				if (rowspanCount > 1) {
					var newtd = "<td Rt="+"'"+tdId+"'>" + value + "</td>";
					var parent = $(tditem).parent("tr")[0];
					while (rowspanCount-- > 1) {
						var length=$(parent).next().find("td").length;
						if(length<=tdindex)
						{
							$(parent).next().append(newtd);
						}else{
							$(parent).next().find("td").eq(tdindex).before(newtd);
						}
						parent = $(parent).next();
					}
					$(tditem).attr("rowspan", 1);
					$(tditem).removeAttr("rowspan");
				}
				
			});
		});
		$("thead tr", this).each(function(trindex, tritem) {
			$(tritem).find("th").each(function (tdindex, tditem) {
				var rowspanCount = $(tditem).attr("rowspan");
				var colspanCount = $(tditem).attr("colspan");
				var thId=$(tditem).attr("Rt");
				var value = $(tditem).html();
				if (colspanCount > 1) {
					var newtd = "<th Rt="+"'"+thId+"' rowspan='"+rowspanCount+"'>" + value + "</th>";
					while (colspanCount-- > 1) {
						$(tditem).after(newtd);
					}
					$(tditem).attr("colspan", 1);
					$(tditem).removeAttr("colspan");
				}
			});
		});
		$("thead tr", this).each(function(trindex, tritem) {
			$(tritem).find("th").each(function (tdindex, tditem) {
				var rowspanCount = $(tditem).attr("rowspan");
				var colspanCount = $(tditem).attr("colspan");
				var thId=$(tditem).attr("Rt");
				var value = $(tditem).html();
				if (rowspanCount > 1) {
					var newtd = "<th Rt="+"'"+thId+"' >" + value + "</th>";
					var parent = $(tditem).parent("tr")[0];
					while (rowspanCount-- > 1) {
						var length=$(parent).next().find("th").length;
						if(length<=tdindex)
						{
							$(parent).next().append(newtd);
						}else{
							$(parent).next().find("th").eq(tdindex).before(newtd);
						}
						parent = $(parent).next();
					}
					$(tditem).attr("rowspan", 1);
					$(tditem).removeAttr("rowspan");
				}
				
			});
		});
	};
	//合并单元格
	jQuery.fn.TableRowspan = function() { //封装的一个JQuery小插件
		//需要被合并的td或th的id集  id出现的次数大于1
		//=====合并表头
		var thmap=new Map();
		var classStartMap=new Map();//开始位置
		var classEndMap=new Map();//结束位置
		var thClassList=new Array();//id出现的次数大于1
		var thIdList=new Array();
		$("thead tr", this).each(function(trindex, tritem) {
			 thIdList[trindex]=new Array();
			$(tritem).find("th").each(function (tdindex, tditem) {
				var thId=$(tditem).attr("Rt");
				thIdList[trindex][tdindex]=thId+"";
				//计数
				var num=thmap.get(thId);
				if(num!=null)
				{
					num=parseInt(num)+1;
				}else{
					num=1;
				}
				thmap.put(thId,num);
				
			});
		});
		//记录位置
		for(var i=0;i<thIdList.length;i++)
		{
			var list=thIdList[i];
			for(var j=0;j<list.length;j++)
			{
				var num=thmap.get(list[j]);
				if(parseInt(num)>1)
				{	
					if(classStartMap.get(list[j])==null)
					{	
						classStartMap.put(list[j],(i+1)+"_"+(j+1));
					}
					classEndMap.put(list[j],(i+1)+"_"+(j+1));
					if(thClassList.indexOf(list[j])<0)
						thClassList[thClassList.length] = list[j];
				}
				
			}
		}
		//开始合并
		for(var j=0;j<thClassList.length;j++)
		{
			var classes=thClassList[j];
			var startStr=classStartMap.get(classes);
			var start0=startStr.substring(0,startStr.indexOf("_"));
			var start1=startStr.substring(startStr.indexOf("_")+1);
			
			var endStr=classEndMap.get(classes);
			var end0=endStr.substring(0,endStr.indexOf("_"));
			var end1=endStr.substring(endStr.indexOf("_")+1);
			
			var rowspan=end0-start0+1;
			var colspan=end1-start1+1;
			
			$(this).find("thead").find("th[Rt='"+classes+"']").each(function (index) {
				if(index>0) $(this).remove();
            });
			
			$(this).find("thead").find("th[Rt='"+classes+"']").eq(0).attr("rowspan",rowspan);
			$(this).find("thead").find("th[Rt='"+classes+"']").eq(0).attr("colspan",colspan);
		}
		

		//表数据
		var tdClassList=new Array();
		var tdmap=new Map();
		var tdIdList=new Array();
		$("tbody tr", this).each(function(trindex, tritem) {
			 tdIdList[trindex]=new Array();
			$(tritem).find("td").each(function (tdindex, tditem) {
				var tdId=$(tditem).attr("Rt");
				tdIdList[trindex][tdindex]=tdId+"";
				var num=tdmap.get(tdId);
				if(num!=null)
				{
					num=parseInt(num)+1;
				}else{
					num=1;
				}
				tdmap.put(tdId,num);
			});
		});
		
		var classStartMap=new Map();
		var classEndMap=new Map();
		for(var i=0;i<tdIdList.length;i++)
		{
			var list=tdIdList[i];
			for(var j=0;j<list.length;j++)
			{
				var num=tdmap.get(list[j]);
				if(parseInt(num)>1)
				{	if(classStartMap.get(list[j])==null)
					{	
						classStartMap.put(list[j],(i+1)+"_"+(j+1));
					}
					classEndMap.put(list[j],(i+1)+"_"+(j+1));
					if(tdClassList.indexOf(list[j])<0)
						tdClassList[tdClassList.length] = list[j];
				}
				
			}
		}
		for(var j=0;j<tdClassList.length;j++)
		{
			var classes=tdClassList[j];
			var startStr=classStartMap.get(classes);
			var start0=startStr.substring(0,startStr.indexOf("_"));
			var start1=startStr.substring(startStr.indexOf("_")+1);
			
			var endStr=classEndMap.get(classes);
			var end0=endStr.substring(0,endStr.indexOf("_"));
			var end1=endStr.substring(endStr.indexOf("_")+1);
			
			var rowspan=end0-start0+1;
			var colspan=end1-start1+1;
			
			$(this).find("tbody").find("td[Rt='"+classes+"']").each(function (index) {
				if(index>0) $(this).remove();
            });
			$(this).find("tbody").find("td[Rt='"+classes+"']").eq(0).attr("rowspan",rowspan);
			$(this).find("tbody").find("td[Rt='"+classes+"']").eq(0).attr("colspan",colspan);
		}

	};

 
function Map() {  
 var struct = function(key, value) {  
  this.key = key;  
  this.value = value;  
 }  
   
 var put = function(key, value){  
  for (var i = 0; i < this.arr.length; i++) {  
   if ( this.arr[i].key === key ) {  
    this.arr[i].value = value;  
    return;  
   }  
  }  
   this.arr[this.arr.length] = new struct(key, value);  
 }  
   
 var get = function(key) {  
  for (var i = 0; i < this.arr.length; i++) {  
   if ( this.arr[i].key === key ) {  
     return this.arr[i].value;  
   }  
  }  
  return null;  
 }  
   
 var remove = function(key) {  
  var v;  
  for (var i = 0; i < this.arr.length; i++) {  
   v = this.arr.pop();  
   if ( v.key === key ) {  
    continue;  
   }  
   this.arr.unshift(v);  
  }  
 }  
   
 var size = function() {  
  return this.arr.length;  
 }  
   
 var isEmpty = function() {  
  return this.arr.length <= 0;  
 }   
 this.arr = new Array();  
 this.get = get;  
 this.put = put;  
 this.remove = remove;  
 this.size = size;  
 this.isEmpty = isEmpty;  
}; 
 ;(function($) {
	
	$.fn.Scorll= function(options,style,isHaveRowspanOrColspan,widths){
		var table_id=this.attr("id");
		if(table_id==""||table_id==undefined||table_id=="undefined")
		{
			return;
		}
		$.fn.Scorll.methods.initIds(table_id,options);
		$.extend($.fn.Scorll.style,style);
		
		var defaults=$.fn.Scorll.idsMap.get(table_id);
		$.fn.Scorll.methods.createElement(table_id,widths);
		if(defaults.colums<=0)
		return ;
		$.fn.Scorll.methods.getScollByColumNums(table_id,isHaveRowspanOrColspan);
		
	};
	$.fn.Scorll.idsMap=new Map();
	$.fn.Scorll.style={"width":"940px","height":"auto","margin-top":"10px","margin-bottom":"10px","overflow":"auto","margin-left":"30px"};
	$.fn.Scorll.defaults={
		allDivId:null,//总的divId
		firstTableid:null,//第一个tableid
		secondTableid:null,//第二个tableid
		threeTableid:null,//第三个tableid
		
		firstDivid:null,//第一个divid
		secondDivid:null,//第二个divid
		threeDivid:null,//第三个divid
		table_id:null,//原始数据表格id
		
		colums:0,//根据固定列的数量
		clearClass:null,//根据不固定的class与固定的class
		removeClass:null
	};
	$.fn.Scorll.methods={
		//初始化 所有id
		initIds:function(table_id,options)
		{
			var defaults={
				allDivId:null,//总的divId
				firstTableid:null,//第一个tableid
				secondTableid:null,//第二个tableid
				threeTableid:null,//第三个tableid
				
				firstDivid:null,//第一个divid
				secondDivid:null,//第二个divid
				threeDivid:null,//第三个divid
				table_id:null,//原始数据表格id
				
				colums:0,//根据固定列的数量
				clearClass:null,//根据不固定的class与固定的class
				removeClass:null
			};
			defaults.allDivId=table_id+"_layout";
			defaults.firstDivid=defaults.allDivId+"_First";//第一个divid
			defaults.secondDivid=defaults.allDivId+"_Scend";//第二个divid
			defaults.threeDivid=defaults.allDivId+"_Three";//第三个divid
			
			defaults.firstTableid=defaults.firstDivid+"_Table";//第一个tableid
			defaults.secondTableid=defaults.secondDivid+"_Table";//第二个tableid
			defaults.threeTableid=defaults.threeDivid+"_Table";//第三个tableid
		
			defaults.table_id=table_id;//原始数据表格id
			
			$.extend(defaults,options);
			$.fn.Scorll.idsMap.put(table_id,defaults);
			//Scorll.prototype.ids=$.fn.Scorll.defaults;
		},
		//复制与生成所有需要的表
		createElement:function(table_id,widths)
		{
			//div默认参数
			//var defaults={"width":"900px","height":"600px","margin-top":"10px","margin-bottom":"10px","overflow":"auto","margin-left":"30px"};
			// $.extend(defaults,options); 
			 var defaults=$.fn.Scorll.idsMap.get(table_id);
			 
			var table=$("#"+defaults.table_id);
			$(table).find("td").css("word-wrap","break-word");
			$(table).find("th").css("word-wrap","break-word");
			if(widths!=null&&widths.length>0)
			{
				$(table).find("tr").each(function(){
					$(this).children().each(function(i){
						var width=widths[i];
						if(width)
						{
							$(this).css("max-width",width+"px");
							$(this).css("min-width",width+"px");
						}
					});
				});
			}
			var div=$("<div></div>");//总div
			
			$(div).attr("id",defaults.allDivId);
			
			$(div).css($.fn.Scorll.style);
			$(table).before($(div));
			
			//第一个div
			var divFirst=$("<div></div>");
			divFirst.attr("id",defaults.firstDivid);
			divFirst.attr("style","height: 0px; overflow: visible; position: relative; top: 0px;");
			//处理复制一份数据表格，只保留 表格的头信息
			var $FirstTable=$(table).clone(true).removeAttr("id");
			$FirstTable.attr("id",defaults.firstTableid);
			
			$(divFirst).append($FirstTable);
			$(div).append($(divFirst));
			
			//第二个div
			var divScend=$("<div></div>");
			divScend.attr("id",defaults.secondDivid);
			divScend.attr("style","height: 0px; overflow: visible; position: relative; left: 0px;");
			//处理复制一份数据表格，只保留固定不动表格信息
			var $ScendTable=$(table).clone(true).removeAttr("id");
			$ScendTable.attr("id",defaults.secondTableid);
			
			//设置固定表格中数据的背景色为白色
			$ScendTable.find("tbody").find("td").css("background-color","white");
			$ScendTable.attr("style","width: auto;border-right: 0px");
			$(divScend).append($ScendTable);
			$(div).append($(divScend));
			
			//第三个div 
			var divThree=$("<div></div>");
			divThree.attr("id",defaults.threeDivid);
			divThree.attr("style","width: 0px; overflow: visible; position: relative; height: 0px; top: 0px; left: 0px;");
			$ScendTable.find("thead").find("th").css("background-color","white");
			//处理复制一份数据表格，只保留固定不动表格的头信息
			var $ThreeTable=$(table).clone(true).removeAttr("id");
			$ThreeTable.attr("id",defaults.threeTableid);
			//$ThreeTable.find("thead").find("th").css("background-color","white");
			
			$(divThree).append($ThreeTable);
			$(div).append($(divThree));
			
			$(div).append($(table));
			return div;
		},
		onscroll:function(table_id)
		{
			var defaults=$.fn.Scorll.idsMap.get(table_id);
			var div=$("#"+defaults.allDivId);
			$(div).on('scroll',function(){
				// div 滚动了
				//上下移动
				var table_id=$(this).attr("id").substring(0,$(this).attr("id").indexOf("_"));
				var defaults=$.fn.Scorll.idsMap.get(table_id);
				var firstId=defaults.firstDivid;
				var secondId=defaults.secondDivid;
				var threeId=defaults.threeDivid;
				var top=$(div).scrollTop();
					$("#"+firstId).css("top",top+"px");
					$("#"+threeId).css("top",top+"px");
				var left=$(div).scrollLeft();
					$("#"+secondId).css("left",left+"px");
					$("#"+threeId).css("left",left+"px");
					
			});
		},
		//通过指定固定的列数，来固定
		getScollByColumNums:function (table_id,isHaveRowspanOrColspan)
		{
			//
			var defaults=$.fn.Scorll.idsMap.get(table_id);
			var colums=parseInt(defaults.colums)-1;
			//去除不需要的数据
			//生成之后第一个table
			var $FirstTable=$("#"+defaults.firstTableid);
			//去除数据部分表格
			$FirstTable.find("tbody").remove();
			
			//生成之后第二个table
			var $ScendTable=$("#"+defaults.secondTableid);
			//去除不固定的表格信息 需要修改
			//处分单元格
			if(isHaveRowspanOrColspan){
				$ScendTable.RevertTable();
			}
			$ScendTable.find("thead").find("tr").each(function(){
				$(this).find("th").each(function(i){
					if(i>colums)
						$(this).remove();
				});
			});
			$ScendTable.find("tbody").find("tr").each(function(){
				$(this).find("td").each(function(i){
					if(i>colums)
					{	$(this).remove();
					}
				});
			});
			//合并单元格 
			if(isHaveRowspanOrColspan){
				$ScendTable.TableRowspan();
			}
			//生成之后第三个table
			var $ThreeTable=$("#"+defaults.threeTableid);
			//移除数据部分
			$ThreeTable.find("tbody").remove();
			//移除表头不固定部分 需要修改
			//拆分单元格
			if(isHaveRowspanOrColspan){
				$ThreeTable.RevertTable();
			}
			$ThreeTable.find("thead").find("tr").each(function(){
				$(this).find("th").each(function(i){
					if(i>colums)
						$(this).remove();
				});
			});
			//合并单元格
			if(isHaveRowspanOrColspan){
				$ThreeTable.TableRowspan();
			}
			console.log("=======================");
			//最原始的数据 
			
			if(isHaveRowspanOrColspan){
				$("#"+table_id).RevertTable();
			}
			$("#"+table_id).find("tbody").find("tr").each(function(){
				$(this).find("td").each(function(i){
					//console.log(i);
					if(i<=colums)
					{	
						$(this).html(" ");
					}
				});
			});
			//合并单元格
			if(isHaveRowspanOrColspan){
				$("#"+table_id).TableRowspan();
			}
			console.log("=======================");
			
			//修正高度
			$.fn.Scorll.methods.heightFiexed($FirstTable,$ScendTable,$ThreeTable,$("#"+defaults.table_id));
			//绑定滚动方法
			$.fn.Scorll.methods.onscroll(table_id);
			
		},
		
	    heightFiexed:function(FirstTable,ScendTable,ThreeTable,dataTable){
			//数据表格中的 th 相应的高度 td相应的高度
			//修正表头的高度开始
			$(dataTable).find("thead").find("tr").each(function(i){
				//一行一行修正
				var maxThheight=-1;
				$(this).find("th").each(function(j){
					//修正第一个div中的 th高度
					$(FirstTable).find("thead").find("tr").eq(i).find("th").eq(j).height($(this).height());
					//修正第二个div中的 th高度
					$(ScendTable).find("thead").find("tr").eq(i).find("th").eq(j).height($(this).height());
					//修正第三个div中的 th高度
					$(ThreeTable).find("thead").find("tr").eq(i).find("th").eq(j).height($(this).height());
				});
				
			});
			//修正表头的高度结束
			
			//修正数据信息高度开始
			$(dataTable).find("tbody").find("tr").each(function(i){
				//一行一行修正
				var maxThheight=-1;
				$(this).find("td").each(function(){
					if($(this).height()>maxThheight) 
						maxThheight=$(this).height();
				});
				//
				$(ScendTable).find("tbody").find("tr").eq(i).find("td").each(function(){
					if(maxThheight<$(this).height()) {
						maxThheight=$(this).height();
					}
				});
				if(maxThheight>0){
					$(this).find("td").each(function(){
						$(this).height(maxThheight);
					});
					//修正第二个div中的 td高度
					$(ScendTable).find("tbody").find("tr").eq(i).find("td").each(function(){
						$(this).height(maxThheight);
					});
				}
			});
			//修正数据信息高度结束
			//
		}
	};
	
})(jQuery);