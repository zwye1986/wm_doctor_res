(function($) {
    $.itemSelect = function(target, data, otherName,defaultItemId,checkData) {
	var left = $('#' + target).offset().left;
        var topPx = $('#' + target).offset().top + $('#' + target).outerHeight();
		//
		//if(char)
		//{
		//	$('#' + target).val($('#' + target).val()+char);
		//}

		var suggestContainerWidth = $('#' + target).innerWidth();
		var homeDefaulOption ={
			'color':'#000000',
			'background-color':'white',
			'width':'100%',
			'position': 'relative',
			'display':'none',
			'max-height': '200px',
			'overflow-y': 'auto',
			'overflow-x': 'hidden',
			'border': '1px #bbb solid',
			'white-space':'nowrap'
		};
		var itemDefaulOption={
			'background-color':'white',
			'width': '100%',
			'color':'#000000',
			'background-color':'white',
			'height': '30px',
			'border-bottom': '1px #bbb solid',
			'border-left': '0px #bbb solid',
			'border-right': '0px #bbb solid',
			'padding-left': '2px'
		};
		
        var divhomeId = target + "-reqHome";//��������DIV
        var divhomeContainer;
        if ($('#' + divhomeId)[0]) {
            divhomeContainer = $('#' + divhomeId);
            divhomeContainer.empty();
        } else {
            divhomeContainer = $('<div></div>'); 
        }
        divhomeContainer.attr('id', divhomeId);
        divhomeContainer.attr('tabindex', '0');
        divhomeContainer.css(homeDefaulOption);
		
		var itemNameHomeDivId = target + "-itemNameHome";//ѡ�к��������div
		
        divhomeContainer.hide();
		var _selCheckboxByDiv=function(div){
			var box = $(":checkbox",div)[0];
			box.checked = !box.checked;
			_showOther();
		};
		var _showOther=function()
		{
			if(otherName) {
				$("#" + otherName).toggle($("#" + divhomeId).find("[name='itemId'][value='" + defaultItemId + "']").attr("checked"));
			}
		}
		var _viewSelReqs=function(){
			var result = "";
			var hidden = "";
			$("#" + divhomeId).find(".itemCheckbox:checked+font").each(function(){
				var currName = $(this).text();
				if(!result){
				result+=currName;
				}else{
					result+=(","+currName);
				}
				hidden+=('<input type="hidden" name="itemName" value="'+currName+'"/>');
			});
			$("#"+itemNameHomeDivId).html(hidden);
			$("#"+target).val(result);
		};

		var  _loadOther=function(box){
			_showOther();
			_viewSelReqs();
			return false;
		};
        var _initItems = function(items,checkItems) {
			divhomeContainer.empty();
            for (var i = 0; i < items.length; i++) {
				var Item = $("<div class='itemDiv'></div>"); //����һ����<div>
				var checkboxItem=$("<input class='itemCheckbox' style='margin-left: 8px;' type='checkbox' name='itemId' value='"+items[i].id+"'/>");
				if(checkItems&&checkboxItem.length>0)
				{
					for(var j=0;j<checkItems.length;j++)
					{
						if(items[i].id == checkItems[j].id)
						{
							$(checkboxItem).attr("checked","checked");
						}
					}
				}
				checkboxItem.bind('change',function(){
					_loadOther(this);
				});
				var fontItem=$("<font style='cursor: default;'></font>");
				fontItem.append(items[i].text);
				
				checkboxItem.appendTo(Item);
				fontItem.appendTo(Item);
				
                Item.css(itemDefaulOption);
				Item.bind("click", function(){
					_selCheckboxByDiv(this);
					_viewSelReqs();
				});
                Item.appendTo(divhomeContainer);
			}
			
			var divNameHomeContainer;
			if ($('#' + itemNameHomeDivId)[0]) {
				divNameHomeContainer = $('#' + itemNameHomeDivId);
				divNameHomeContainer.empty();
			} else {
				divNameHomeContainer = $('<div></div>'); 
			}
			divNameHomeContainer.attr('id', itemNameHomeDivId);
			divNameHomeContainer.appendTo(divhomeContainer);
			$('#' + target).after(divhomeContainer);
			if(checkItems&&checkboxItem.length>0)
			{
				_showOther();
				_viewSelReqs();
			}
			_initClick();
        };
		var _initClick=function()
		{
			$("input[type='checkbox'][name='itemId']").click(function(e){
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
        _initItems(data,checkData);
    }
})(jQuery);
