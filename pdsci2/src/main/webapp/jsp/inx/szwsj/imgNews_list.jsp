<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:if test="${fn:length(imgNewsList)>0 }">
	<ul class="Limg">
	 <c:forEach items="${imgNewsList}" var="info">
		<li>
			<a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${info.infoFlow}'/>" target="_blank">
			<img src="${imageBaseUrl}${info.titleImg}" alt="${info.infoTitle}" /></a>
			<p><a href="<s:url value='/inx/szwsj/getByInfoFlow?infoFlow=${info.infoFlow}'/>" target="_blank">${pdfn:cutString(info.infoTitle,16,true,3)}</a></p>
		</li>
	</c:forEach>
	</ul>
	<cite class="Nubbt">
		<c:forEach items="${imgNewsList}" var="info" varStatus="statu">
		<span <c:if test="${statu.count ==1}">class="on"</c:if> >${statu.count }</span>
		</c:forEach>
	</cite>
<script  type="text/javascript">
//*焦点切换
(function(){
    if(!Function.prototype.bind){
        Function.prototype.bind = function(obj){
            var owner = this,args = Array.prototype.slice.call(arguments),callobj = Array.prototype.shift.call(args);
            return function(e){e=e||top.window.event||window.event;owner.apply(callobj,args.concat([e]));};
        };
    }
})();
var player = function(id){
    this.ctn = document.getElementById(id);
    this.adLis = null;
    this.btns = null;
    this.animStep = 0.2;//动画速度0.1～0.9
    this.switchSpeed = 4;//自动播放间隔(s)
    this.defOpacity = 1;
    this.tmpOpacity = 1;
    this.crtIndex = 0;
    this.crtLi = null;
    this.adLength = 0;
    this.timerAnim = null;
    this.timerSwitch = null;
    this.init();
};
player.prototype = {
    fnAnim:function(toIndex){
        if(this.timerAnim){window.clearTimeout(this.timerAnim);}
        if(this.tmpOpacity <= 0){
            this.crtLi.style.opacity = this.tmpOpacity = this.defOpacity;
            this.crtLi.style.filter = 'Alpha(Opacity=' + this.defOpacity*100 + ')';
            this.crtLi.style.zIndex = 0;
            this.crtIndex = toIndex;
            return;
        }
        this.crtLi.style.opacity = this.tmpOpacity = this.tmpOpacity - this.animStep;
        this.crtLi.style.filter = 'Alpha(Opacity=' + this.tmpOpacity*100 + ')';
        this.timerAnim = window.setTimeout(this.fnAnim.bind(this,toIndex),50);
    },
    fnNextIndex:function(){
        return (this.crtIndex >= this.adLength-1)?0:this.crtIndex+1;
    },
    fnSwitch:function(toIndex){
        if(this.crtIndex==toIndex){return;}
        this.crtLi = this.adLis[this.crtIndex];
        for(var i=0;i<this.adLength;i++){
            this.adLis[i].style.zIndex = 0;
        }
        this.crtLi.style.zIndex = 2;
        this.adLis[toIndex].style.zIndex = 1;
        for(var i=0;i<this.adLength;i++){
            this.btns[i].className = '';
        }
        this.btns[toIndex].className = 'on'
        this.fnAnim(toIndex);
    },
    fnAutoPlay:function(){
        this.fnSwitch(this.fnNextIndex());
    },
    fnPlay:function(){
        this.timerSwitch = window.setInterval(this.fnAutoPlay.bind(this),this.switchSpeed*1000);
    },
    fnStopPlay:function(){
        window.clearTimeout(this.timerSwitch);
    },
    init:function(){
        this.adLis = this.ctn.getElementsByTagName('li');
        this.btns = this.ctn.getElementsByTagName('cite')[0].getElementsByTagName('span');
        this.adLength = this.adLis.length;
        for(var i=0,l=this.btns.length;i<l;i++){
            with({i:i}){
                this.btns[i].index = i;
                this.btns[i].onclick = this.fnSwitch.bind(this,i);
                this.btns[i].onclick = this.fnSwitch.bind(this,i);
            }
        }
        this.adLis[this.crtIndex].style.zIndex = 2;
        this.fnPlay();
        this.ctn.onmouseover = this.fnStopPlay.bind(this);
        this.ctn.onmouseout = this.fnPlay.bind(this);
    }
};
var player1 = new player('player');
</script> <!--/*焦点图切换JS结束*/-->
</c:if>


