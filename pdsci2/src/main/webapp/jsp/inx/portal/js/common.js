$(function(){
  var mySwiper = new Swiper ('.swiper-container', {
    autoplay:true,
    loop: true,
    // 如果需要分页器
    pagination: {
      el: '.swiper-pagination',
      clickable :true,
    }

  })   

  // $(".ol-service").mouseover(function(){
  //   $(this).animate({width:"164px"});
  // })
  // $(".ol-service").mouseout(function(){
  //   $(this).animate({width:"41px"});
  // })

})