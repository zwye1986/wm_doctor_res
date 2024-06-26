(function($) {
	/**
	 * 得到一个数组不重复的元素集合<br/>
	 * 唯一化一个数组
	 * @returns {Array} 由不重复元素构成的数组
	 */
	Array.prototype.uniquelize = function(){
		var ra = new Array();
		for(var i = 0; i < this.length; i ++){
			if($.inArray(this[i],ra)<0){
				ra.push(this[i]);
			}
		}
		return ra;
	};
	$.checkYear = function(target, data, itemSelectFunction) {
		var left = $('#' + target).offset().left;
		var topPx = $('#' + target).offset().top + $('#' + target).outerHeight();

		var suggestContainerWidth = $('#' + target).innerWidth();
		var homeDefaulOption ={
			'top': topPx,
			'left': left,
			'color':'#000000',
			'background-color':'white',
			'height':'0px',
			'width':suggestContainerWidth,
			'position': 'absolute',
			'display':'none'
		};
		var yearDefaulOption={
			'background-color':'white',
			'width': suggestContainerWidth,
			'color':'#000000',
			'background-color':'white',
			'height': '20px',
			'border-bottom': '1px #bbb solid',
			'border-left': '1px #bbb solid',
			'border-right': '1px #bbb solid'
		};
		var divhomeId = target + "-reqHome";
		var divhomeContainer;
		if ($('#' + divhomeId)[0]) {
			divhomeContainer = $('#' + divhomeId);
			divhomeContainer.empty();
		} else {
			divhomeContainer = $('<div></div>'); //创建一个子<div>
		}
		divhomeContainer.attr('id', divhomeId);
		divhomeContainer.attr('tabindex', '0');
		divhomeContainer.css(homeDefaulOption);
		divhomeContainer.hide();
		var _selCheckboxByDiv=function(div){
			var box = $(":checkbox",div)[0];
			box.checked = !box.checked;
		};
		var _viewSelReqs=function(){
			var result = "";
			var notResult = "";
			var seRe=$("#"+target).val();
			$("#"+divhomeId).find(".itemCheckbox:checked+font").each(function(){
				var currName = $(this).text();
				if(!result){
					result+=currName;
				}else{
					result+=(","+currName);
				}
			});
			$("#"+divhomeId).find(".itemCheckbox:not(:checked)+font").each(function(){
				var currName = $(this).text();
				if(!notResult){
					notResult+=currName;
				}else{
					notResult+=(","+currName);
				}
			});
			var seRes=seRe.split(",");
			var results=result.split(",");
			var notResults=notResult.split(",");
			seRes=$.merge(seRes,results).uniquelize();
			seRes.sort();

			var new_arr=[];
			for(var i=0;i<seRes.length;i++)
			{
				var items=seRes[i];
				if($.inArray(items,notResults)==-1) {
					new_arr.push(items);
				}
			}


			result="";
			for(var i=0;i<new_arr.length;i++)
			{
				if(!result){
					result+=new_arr[i];
				}else{
					result+=(","+new_arr[i]);
				}
			}
			$("#"+target).val(result);
		};

		var  _loadOther=function(box){
			_viewSelReqs();
			return false;
		};
		var _btnClick=function(defaultYear,index){
			var sYear = $("#"+target).val();
			_initItems(defaultYear,sYear,index);
		};
		var _initItems = function(thisYear,selectYear,index) {
			var defaultYear=thisYear;
			if(!thisYear){
				var d = new Date();
				var vYear = d.getFullYear();
				thisYear=vYear;
			}
			if(index==null||isNaN(index))
			{
				index=1;
			}
			thisYear=parseInt(thisYear)+(index-1)*10;
			divhomeContainer.empty();
			var btnDiv=$("<div style='width:100%;height:25px;'></div>")
			var leftBtn=$("<input style='width:50%;height:30px;line-height:20px;vertical-align:center;' type='button' name='year' value='<<'/>");
			leftBtn.appendTo(btnDiv);
			leftBtn.bind('click',function(){
				_btnClick(null,index-1);
			});
			var rightBtn=$("<input style='width:50%;height:30px;line-height:20px;vertical-align:center;' type='button' name='year' value='>>'/>");
			rightBtn.appendTo(btnDiv);
			rightBtn.bind('click',function(){
				_btnClick(null,index+1);
			});

			btnDiv.css(yearDefaulOption);
			btnDiv.css({
				height:'30px'
			});
			btnDiv.appendTo(divhomeContainer);
			var n=thisYear%10-1;
			for(var i=thisYear-n;i<thisYear;i++)
			{
				var yearItem = $("<div class='itemDiv'></div>"); //创建一个子<div>
				var checkboxItem=$("<input class='itemCheckbox' style='margin-left: 8px;' type='checkbox' name='yearId'/>");
				checkboxItem.value=i;
				if(defaultYear)
				{
					var temp=defaultYear.split(",");
					if(temp.length>0)
					{
						for(var j=0;j<temp.length;j++)
						{
							if(i==temp[j])
							{
								$(checkboxItem).attr("checked","checked");
							}
						}
					}
				}
				if(selectYear)
				{
					var temp=selectYear.split(",");
					if(temp.length>0)
					{
						for(var j=0;j<temp.length;j++)
						{
							if(i==temp[j])
							{
								$(checkboxItem).attr("checked","checked");
							}
						}
					}
				}
				checkboxItem.bind('change',function(){
					_loadOther(this);
				});
				var fontItem=$("<font style='cursor: default;line-height: 21px;'></font>");
				fontItem.append(i);

				checkboxItem.appendTo(yearItem);
				fontItem.appendTo(yearItem);

				yearItem.css(yearDefaulOption);
				yearItem.bind("click", function(){
					_selCheckboxByDiv(this);
					_viewSelReqs();
				});
				yearItem.appendTo(divhomeContainer);

			}
			n=10-n-1;
			for(var i=thisYear;i<=thisYear+n;i++)
			{
				var yearItem = $("<div class='itemDiv'></div>"); //创建一个子<div>
				var checkboxItem=$("<input class='itemCheckbox' style='margin-left: 8px;' type='checkbox' name='yearId'/>");
				checkboxItem.value=i;
				if(defaultYear)
				{
					var temp=defaultYear.split(",");
					if(temp.length>0)
					{
						for(var j=0;j<temp.length;j++)
						{
							if(i==temp[j])
							{
								$(checkboxItem).attr("checked","checked");
							}
						}
					}
				}
				if(selectYear)
				{
					var temp=selectYear.split(",");
					if(temp.length>0)
					{
						for(var j=0;j<temp.length;j++)
						{
							if(i==temp[j])
							{
								$(checkboxItem).attr("checked","checked");
							}
						}
					}
				}
				checkboxItem.bind('change',function(){
					_loadOther(this);
				});
				var fontItem=$("<font style='cursor: default;line-height: 21px;'></font>");
				fontItem.append(i);

				checkboxItem.appendTo(yearItem);
				fontItem.appendTo(yearItem);

				yearItem.css(yearDefaulOption);
				yearItem.bind("click", function(){
					_selCheckboxByDiv(this);
					_viewSelReqs();
				});
				yearItem.appendTo(divhomeContainer);
			}
			$('#' + target).after(divhomeContainer);
			_initClick(defaultYear);
		};
		var _initClick=function(year)
		{
			if(year)
			{
				_viewSelReqs();
			}
			$("input[type='checkbox'][name='yearId']").click(function(e){
				e.stopPropagation();
			});
			$("input[type='button'][name='year']").click(function(e){
				e.stopPropagation();
			});
			$("#"+target).click(function(e){
				e.stopPropagation();
			});
			$(".itemDiv").click(function(e){
				e.stopPropagation();
			});
			$(".itemDiv").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});
			$(document).click(function(){
				$("#"+divhomeId).toggle(!!$(".itemDiv.on").length);
			});
		};
		$('#' + target).bind("click",
			function() {
				$("#"+divhomeId).toggle();
			});
		_initItems(data,null,1);
	}
})(jQuery);
