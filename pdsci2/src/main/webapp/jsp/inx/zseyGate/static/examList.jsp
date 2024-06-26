
<div class="sonbox">
    <div class="sonright fr">
        <div class="sontitle">
            <span class="fl sonnewtitle">最新考试</span>
            <span class="fr sonnewnap"><a>您现在的位置：教学网站>考试通知</a></span>
        </div>
        <ul class="sonnewcon">
            <li>
                <a href="javascript:void(0);" onclick="goStaticHtml('examSingle');">最新2018年外科考试</a>
                &#12288;<span>2018-11-11</span>
            </li>
            <li>
                <a href="javascript:void(0);" onclick="goStaticHtml('examSingle');">最新2018年内科考试</a>
                &#12288;<span>2018-11-11</span>
            </li>
        </ul>

        <div class="fy fr">
            <c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
            <pd:pagination-inx toPage="toPage"/>
        </div>
    </div>
</div>
