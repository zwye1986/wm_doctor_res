function getdates()
{
var d=new Date();
var year=d.getFullYear();
var month=d.getMonth()+1;
var day=d.getDate();
if(month<10) month="0" + month
if(day<10) day="0" + day
var shows="今天是" + "<span>" + year + "年" + month + "月" + day + "日" + "</span>";
$("#date").html(shows);
$("#date1").html(shows);
}
getdates();
$(function(){
    var mySwiper = new Swiper ('.swiper-container1', {
        loop: true,
        autoplay:true,
        // 如果需要分页器
        pagination: {
          el: '.swiper-pagination',
        },
        
        // 如果需要前进后退按钮
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev',
        }
        
      }) 



  var swiper = new Swiper('.swiper-container2', {
    slidesPerView: '3',
    loop: true,
    spaceBetween: 30,
    speed:2000,
    autoplay: {
      delay: 2000,
      disableOnInteraction: false,
    },
    waitForTransition: false,
  });


  $("#login").click(function(){
      if($(".userBox").hide()){
        $(".login-box").hide();
        $(".userBox").show();
      }else{
        $(".login-box").show();
        $(".userBox").hide();
      }
  })
  $("#logout").click(function(){
    if($(".userBox").hide()){
      $(".login-box").show();
      $(".userBox").hide();
    }else{
      $(".login-box").hide();
      $(".userBox").show();
    }
})
})

