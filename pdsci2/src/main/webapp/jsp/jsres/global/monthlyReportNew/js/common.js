$(function(){
  $("#tab a").on({
    click:function(){
      if(!$(this).parent("li").hasClass("choose")){
        $("#tab a").parent("li").removeClass("choose");
        $("#tab a").parent("li").removeAttr("style");
        $(this).removeAttr("style");
        $(this).parent("li").addClass("choose");
      }else{
        
      }
    },
      mouseover:function(){
          if(!$(this).parent("li").hasClass("choose")){
              $(this).parent('li').css({
                  "margin-left":"10px",
                  "background":"#44b549",
              });
              $(this).css("color","#fff")
          }

      },
      mouseout:function(){
          if(!$(this).parent("li").hasClass("choose")){
              $(this).parent('li').css({
                  "margin-left":"15px",
                  "background":"#ddd"
              });
              $(this).css("color","#ffffff")
          }
      }
  })
  $("#body-tab li a").click(function(){
    $("#body-tab li a").removeClass("current");
    $(this).addClass("current");
  })
  $(".title-tab li a").click(function() {
    $(this).parent('li').parent("ul").find("a").removeClass("active");
    $(this).addClass("active");
  })
})