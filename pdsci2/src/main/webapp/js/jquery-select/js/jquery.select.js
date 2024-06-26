(function($) {
    //target input的id
    // data下拉数据list
    // itemSelectFuntion 选中后执行的方法
    // selectId隐藏的input的id
    // loadSelectFunction 当下拉数据div 隐藏时，是否调用【选中后执行的方法，并将条件置空】
    $.selectSuggest = function(target, data, itemSelectFunction,selectId,loadSelectFunction) {
        var defaulOption = {
            suggestMaxHeight: '200px',//弹出框最大高度
            itemColor : '#000000',//默认字体颜色
            itemBackgroundColor:'white',//默认背景颜色
            itemOverColor : '#000000',//选中字体颜色
            itemOverBackgroundColor : '#ecf2fa',//选中背景颜色
            itemPadding : 3 ,//item间距
            fontSize : 12 ,//默认字体大小
            alwaysShowALL : false //点击input是否展示所有可选项
        };
        var maxFontNumber = 0;//最大字数
        var currentItem;
        var suggestContainerId = target + "-suggest";
        var suggestContainerWidth = $('#' + target).innerWidth();

        var showClickTextFunction = function() {
            $('#' + target).val(this.innerText);
            currentItem = null;
            $('#' + suggestContainerId).hide();
        }
        var suggestContainer;
        if ($('#' + suggestContainerId)[0]) {
            suggestContainer = $('#' + suggestContainerId);
            suggestContainer.empty();
        } else {
            suggestContainer = $('<div></div>'); //创建一个子<div>
        }

        suggestContainer.attr('id', suggestContainerId);
        suggestContainer.attr('tabindex', '0');
        suggestContainer.hide();
        var _initItems = function(items) {
            suggestContainer.empty();
            for (var i = 0; i < items.length; i++) {
                if(items[i].text.length > maxFontNumber){
                    maxFontNumber = items[i].text.length;
                }
                var suggestItem = $('<div></div>'); //创建一个子<div>
                suggestItem.attr('id', items[i].id);
                suggestItem.append(items[i].text);
                suggestItem.css({
                    'padding':defaulOption.itemPadding + 'px',
                    'cursor': 'pointer',
                    'width':'auto',
                    //'word-break':'break-all',
                    //'word-wrap':'break-word',
                    'font-size':'14px',
                    'white-space':'normal',
                    'background-color': defaulOption.itemBackgroundColor,
                    'color': defaulOption.itemColor
                });
                suggestItem.bind("mouseover",
                    function() {
                        $(this).css({
                            'background-color': defaulOption.itemOverBackgroundColor,
                            'color': defaulOption.itemOverColor
                        });
                        currentItem = $(this);
                    });
                suggestItem.bind("mouseout",
                    function() {
                        $(this).css({
                            'background-color': defaulOption.itemBackgroundColor,
                            'color': defaulOption.itemColor
                        });
                        currentItem = null;
                    });
                suggestItem.bind("click", showClickTextFunction);
                if(itemSelectFunction){
                    suggestItem.bind("click", itemSelectFunction);
                }
                suggestItem.appendTo(suggestContainer);
                $('#' + target).after(suggestContainer);
            }
        }

        var inputChange = function() {
            var inputValue = $('#' + target).val();
            inputValue = inputValue.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&");
            var matcher = new RegExp(inputValue, "i");
            return $.grep(data,
                function(value) {
                    return matcher.test(value.text);
                });
        }

        $('#' + target).bind("keyup",
            function() {
                $("#"+selectId).val("")
                _initItems(inputChange());
            });
        $('#' + target).bind("blur",
            function() {
                if(!$("#"+selectId).val()) {
                    $('#' + target).val("");
                }
                var suggestContainerLeft = $('#' + target).offset().left;
                var suggestContainerTop = $('#' + target).offset().top + $('#' + target).outerHeight();
                $('#' + suggestContainerId).css("left",suggestContainerLeft);
                $('#' + suggestContainerId).css("top",suggestContainerTop);
               // $('#' + suggestContainerId).focus();
                if(!$('#' + suggestContainerId).is(":focus")){
                    $('#' + suggestContainerId).focus();
                    // $('#' + suggestContainerId).hide();
                    if (currentItem) {
                        currentItem.trigger("click");
                    }
                    currentItem = null;
                    return;
                }
            });

        $('#' + target).bind("click",
            function() {

                var suggestContainerLeft = $('#' + target).offset().left;
                var suggestContainerTop = $('#' + target).offset().top + $('#' + target).outerHeight();
                if (defaulOption.alwaysShowALL) {
                    _initItems(data);
                } else {
                    _initItems(inputChange());
                }
                $('#' + suggestContainerId).removeAttr("style");
                var tempWidth = defaulOption.fontSize*maxFontNumber + 2 * defaulOption.itemPadding + 30;
                var containerWidth = suggestContainerWidth;
                $('#' + suggestContainerId).css({
                    'border': '1px solid #ccc',
                    'max-height': '200px',
                    'top': suggestContainerTop,
                    'left': suggestContainerLeft,
                    'width': containerWidth+'px',
                    'position': 'absolute',
                    'font-size': defaulOption.fontSize+'px',
                    'font-family':'Arial',
                    'z-index': 99999,
                    'background-color': '#FFFFFF',
                    'overflow-y': 'auto',
                    'overflow-x': 'hidden',
                    'white-space':'nowrap',
                    'text-align': 'left'

                });
                $('#' + suggestContainerId).show();
            });
        _initItems(data);

        $('#' + suggestContainerId).bind("blur",
            function() {
                $('#' + suggestContainerId).hide();
                if(loadSelectFunction) {
                    var targetValue = $("#" + target).val();
                    var selectValue = $("#" + selectId).val();
                    if (!targetValue && selectValue) {
                        if (itemSelectFunction) {
                            var func = eval(itemSelectFunction);
                            //创建函数对象，并调用
                            new func();
                        }
                    }
                }
            });

    }
})(jQuery);
