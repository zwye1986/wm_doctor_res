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
    data = data || {};
   var imgs = data.imgs || null;
   var texts = data.texts || [];
   var canattr = data.canattr;
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
                        if (rowSize != 0) {


                            var allLength = testObj.text.length;
                            var height=style.height || 20;
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
                        }
                    }
                    image.onerror = function(){
                        if(imgs.length>(imgIndex+1)){
                            imgIndex++;
                            loadImg();
                        }else{
                            if(texts){
                                texthander(texts,context);
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
        }
    });
    return this;
}

$.fn.downCanvasImg = function(name){
    if(this[0].tagName == 'CANVAS'){
        var base64 = this[0].toDataURL();
        var obj=$('<a/>').attr({
            'href':base64,
            'download':name
        })[0];
        if (document.all) {//如果支持的话，是ie下，默认有这个事件，
            obj.click();
        } else {   //否则就自己添加一个
            var evt = document.createEvent("MouseEvents");
            evt.initEvent("click", true, true);
            obj.dispatchEvent(evt);
        }
    }
}