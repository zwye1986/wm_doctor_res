//=======================================
//        font-style
//        规定字体样式。可能的值：
//        normal
//        italic
//        oblique
//=======================================
//        font-variant
//        规定字体变体。可能的值：
//        normal
//        small-caps
//=======================================
//        font-weight
//        规定字体的粗细。可能的值：
//        normal
//        bold
//        bolder
//        lighter
//        100
//        200
//        300
//        400
//        500
//        600
//        700
//        800
//        900
//=======================================
//        font-size / line-height	规定字号和行高，以像素计。
//=======================================
//        font-family	规定字体系列。
//=======================================
//        caption	使用标题控件的字体（比如按钮、下拉列表等）。
//=======================================
//        icon	使用用于标记图标的字体。
//=======================================
//        menu	使用用于菜单中的字体（下拉列表和菜单列表）。
//=======================================
//        message-box	使用用于对话框中的字体。
//=======================================
//        small-caption	使用用于标记小型控件的字体。
//=======================================
//        status-bar	使用用于窗口状态栏中的字体。
//=======================================
//=======================================
//drawCertificate([options])
//options:
////imgs:图片对象
//////imgs:[{url:图片地址,args:(可不传,传4个数字,8个数字)参数(图片截取起始x坐标,图片截取起始y坐标,截取宽度,截取高度,放置位置x坐标,放置位置y坐标,展示宽度,展示高度)}]
////texts:文字对象
//////texts:[{text:文字内容,x:文字展示x坐标,y:文字展示y坐标,style:文字样式(大小,字体等),rowSize:文字每多少字换行，默认不换行}]
////canattr:设置canvas属性
//{
//    imgs:[
//        {
//            url:'test5.jpg',
//            args:[10,15,565,780,0,0,588,816]
//        }
//    ],
//            texts:[
//    {
//        text:'yyyyyyy',
//        x:150,
//        y:150,
//        rowSize:3
//    },
//    {
//        text:'zzzzzzzz',
//        x:50,
//        y:50,
//          rowSize:3
//    }
// ],
//canattr:{
// id:'xxx'
// }
//}
//=======================================
$.fn.drawCertificate = function(data){
    var data = data || {};
    var    imgs = data.imgs || null;
    var    texts = data.texts || [];
    var    texts2 = data.texts2 || [];
    var    canattr = data.canattr;
    this.each(function(){
        var wid = this.style.width;
        var hei = this.style.height;
        var widi = wid.replace('px','')-0;
        var heii = hei.replace('px','')-0;
        var canvas = $('<canvas/>').attr({
            'width':wid,
            'height':hei
        });
        if(canattr){
            canvas.attr(canattr);
        }
        function texthander(datas,ctx){
            if(datas){
                for(var index in datas){
                    var testObj =  datas[index];
                    if(testObj){
                        var style = testObj.style || '20px Arial';

                        ctx.font = style;
                        var rowSize = testObj.rowSize || 0;
                        var witdh=testObj.witdh||0;
                        if (rowSize != 0 && rowSize<testObj.text.length) {


                            var allLength = testObj.text.length;
                            var height=testObj.height || 20;
                            var alltext=testObj.text;
                            if(allLength>=rowSize){
                                var count=parseFloat(allLength/rowSize)>parseInt(allLength/rowSize) ? parseInt(allLength/rowSize)+1:parseInt(allLength/rowSize);
                                var mid=parseFloat(count/2)>parseInt(count/2) ? parseInt(count/2)+1:parseInt(count/2);
                                for(var i=0;i<count;i++)
                                {
                                    var text=alltext.substring(i*rowSize,(i+1)*rowSize);
                                    var y=testObj.y;
                                    var x=testObj.x;
                                    if(count%2==0)
                                    {
                                        if(i<mid)
                                        {
                                            y=y-((mid-i)*height-0.5*height);
                                        }else{
                                            y=y+(i-mid)*height+0.5*height+4;
                                        }
                                    }else{
                                        if(i<mid)
                                        {
                                            y=y-((mid-i-1)*height);
                                        }else{
                                            y=y+(i-mid+1)*height+4;
                                        }
                                    }
                                    ctx.fillText(text, x, y);
                                }
                            }else{
                                var midwidth=(rowSize-allLength)*height/(allLength*2);
                                for(var i=0;i<allLength;i++)
                                {
                                    var x=testObj.x;
                                    var y=testObj.y;
                                    x=i*height+i*0.5*midwidth+x;
                                    var text=alltext.substring(i,(i+1));
                                    ctx.fillText(text, x, y);
                                }

                            }
                        }
                        else if(witdh!=0){
                            var textwitdh=style.width||20;
                            var allLength = testObj.text.length;
                            var allWitdh=textwitdh*allLength;
                            if(witdh<allWitdh) {
                                ctx.fillText(testObj.text, testObj.x, testObj.y);
                            }else {
                                var x=testObj.x+(witdh-allWitdh)/2;
                                ctx.fillText(testObj.text, x, testObj.y);
                            }
                        }else {
                            ctx.fillText(testObj.text, testObj.x, testObj.y);
                        }
                    }
                }
            }else  if(texts2){
                text2hander(texts2,context);
            }
        }

        function text2hander(texts2,ctx){

            ctx.textAlign="left";//左右居中
            ctx.textBaseline="middle";//上下居中
            if(texts2&&texts2.x&&texts2.y&&texts2.width&&texts2.items){
                var datas=texts2.items;
                var startX=texts2.startX;
                var endX=texts2.endX;
                var x=texts2.x;
                var y=texts2.y;
                var width=texts2.width;//行宽
                var height=texts2.height;//行高
                var hj=texts2.hj;//行距

                var allWidth=startX-x;//首行缩进的距离
                var rowNum=0;
                var itemsWidths={};
                for(var i=0;i<datas.length;i++)
                {
                    var testObj =  datas[i];

                    var style = testObj.style || '20px Arial';
                    ctx.font = style;
                    var text = testObj.text;
                    var textwitdh =ctx.measureText(text).width;//字体总宽度

                    if((x+textwitdh-startX)>width)//如果长度大于设置宽度则需要换行
                    {
                        var k=0;
                        for(var j=0;j<text.length;j++)
                        {
                            var subText=text.substr(0,j+1);
                            var subTextWidth =ctx.measureText(subText).width;//字体总宽度
                            if((x+subTextWidth-startX)>width)
                            {
                                if("，"==subText.substr(subText.length-1,1))
                                {
                                    j++;
                                }
                                k=j;//取长度
                                break;
                            }
                        }
                        if(k==0)//到当前字段时，就需要换行
                        {
                            x=startX;
                            y+=height+hj;
                            ctx.fillText(text, x, y);
                            x += textwitdh;

                        }else if(k>0)//前一半接着后面，后一半需要换行
                        {
                            var subText=text.substr(0,k);
                            var subTextWidth =ctx.measureText(subText).width;//字体总宽度
                            ctx.fillText(subText, x, y);
                            console.log(subText+" x:"+x+" y:"+y);

                            var subText=text.substr(k,text.length-k);
                            var subTextWidth =ctx.measureText(subText).width;//字体总宽度

                            x=startX;
                            y+=height+hj;
                            ctx.fillText(subText, x, y);

                            x += subTextWidth;
                        }
                    }else{
                        ctx.fillText(text, x, y);
                        x += textwitdh;
                    }
                }
            }
        }

        $(this).append(canvas);
        var jsCanvas = canvas[0];
        var context = jsCanvas.getContext('2d');

        var imgIndex = 0;
        function loadImg(){
            var img = imgs[imgIndex];
            if(img){
                var url = img.url;
                if(url){
                    var image = new Image();
                    image.src = url;
                    image.onload = function(){
                        var args = img.args || [];
                        var x = args[0] || 0;
                        var y = args[1] || 0;
                        var w = args[2] || widi;
                        var h = args[3] || heii;
                        if(args.length<=4){
                            context.drawImage(image,x,y,w,h);
                        }else{
                            var xx = args[0] || 0;
                            var xy = args[1] || 0;
                            var xw = args[2] || image.width;
                            var xh = args[3] || image.height;
                            x = args[4] || 0;
                            y = args[5] || 0;
                            w = args[6] || widi;
                            h = args[7] || heii;
                            context.drawImage(image,xx,xy,xw,xh,x,y,w,h);
                        }
                        if(imgs.length>(imgIndex+1)){
                            imgIndex++;
                            loadImg();
                        }else{
                            if(texts){
                                texthander(texts,context);
                            }
                            if(texts2){
                                text2hander(texts2,context);
                            }
                        }
                    };
                    image.onerror = function(){
                        if(imgs.length>(imgIndex+1)){
                            imgIndex++;
                            loadImg();
                        }
                        else{
                            if(texts){
                                texthander(texts,context);
                            }
                            if(texts2){
                                text2hander(texts2,context);
                            }
                        }
                    }
                }
            }
        }

        if(imgs){
            loadImg();
        }else if(texts){
            texthander(texts,context);
        }else if(texts2){
            text2hander(texts2,context);
        }
    });
    return this;
};

function dataURLtoFile(dataurl, filename) {//将base64转换为文件
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}

$.fn.downCanvasImg = function(name,url){
    if(this[0].tagName == 'CANVAS'){
        function downfile(url) {
            $("#exportDataForm").remove();
            var expForm = document.createElement("form");

            $(expForm).attr("display", "none");
            $(expForm).attr("action", url);
            $(expForm).attr("id", "exportDataForm");
            $(expForm).attr("name", "exportDataForm");
            $(expForm).attr("method", "post");
            $(expForm).appendTo('body').submit().remove();
        }
        var isUpload = $("#isUpload").val();
        if(isUpload=="Y"){
            if (url.indexOf('?') > -1) {
                url = url + "&isDown=Y&fileName=" + name;
            } else {
                url = url + "?isDown=Y&fileName=" + name;
            }
            downfile(url);
        }else {
            var base64 = this[0].toDataURL();
            var formData = new FormData();
            var arr = base64.split(','), mime = arr[0].match(/:(.*?);/)[1],
                bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }
            formData.append("myfile", new File([u8arr], name, {type: mime}));
            formData.append("fileName", name);
            $.ajax({
                url: url,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (returndata) {

                    var input = document.createElement("input");
                    $(input).attr("id", "isUpload");
                    $(input).val( "Y");
                    $(input).appendTo('body');
                    if (url.indexOf('?') > -1) {
                        url = url + "&isDown=Y&fileName=" + name;
                    } else {
                        url = url + "?isDown=Y&fileName=" + name;
                    }
                    downfile(url);
                },
                error: function (returndata) {

                }
            });
        }
        //var obj=$('<a/>').attr({
        //    'href':base64,
        //    'download':name
        //})[0];
        //if (document.all) {//如果支持的话，是ie下，默认有这个事件，
        //    obj.click();
        //} else {   //否则就自己添加一个
        //    var evt = document.createEvent("MouseEvents");
        //    evt.initEvent("click", true, true);
        //    obj.dispatchEvent(evt);
        //}
    }
};