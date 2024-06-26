$(function(){
  function change() {
    var $body = $('body');
    var $footer = $('.footer');

    var bodyHeight = $body.height();  //网页可见元素的高度

    var allHeight = bodyHeight;

    var isAbsExist = false;

    if($footer.hasClass('abs-bottom')){  //如果尾部存在绝对定位,网页总高度要加上尾部的高度
        isAbsExist = true;
        allHeight += $footer.height();
    }
    if(getWinHeight() < allHeight){  //窗口高度小于网页总高度时
        if(isAbsExist){
            $('.footer').removeClass("abs-bottom");
        }
    }else {  //当窗口高度大于网页总高度时
        if(!isAbsExist){
            $('.footer').addClass("abs-bottom");
        }
    }
  }
  change();

  $(window).resize(function(){  //窗口大小改变时进行改变
      change();
  })
  function getWinHeight(){ //获取窗口高度的函数。
      var e = window,
      a = 'inner';
      if (!('innerWidth' in window )){
          a = 'client';
          e = document.documentElement || document.body;
      }
      return e[ a+'Height'];
      
  }
})