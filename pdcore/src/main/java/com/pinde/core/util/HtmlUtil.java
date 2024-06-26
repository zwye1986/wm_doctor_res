package com.pinde.core.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * @ClassName HtmlUtil
 * @Description html文本处理
 * @Author fengxf
 * @Date 2020/11/19
 */
public class HtmlUtil {

	/**
	 * 特殊字符转义，避免XSS
	 * @param content
	 * @return
	 */
	public static String escapehtml(String content){
		return StringEscapeUtils.escapeHtml4(content);
	}

	/**
	 * 特殊字符转义，避免XSS
	 * @param content
	 * @return
	 */
	public static String unescapehtml(String content){
		return StringEscapeUtils.unescapeHtml4(content);
	}

	/**
	 * 富文本内容处理返回纯文本
	 * @param unsafe
	 * @return
	 */
	public static String cleanHtml(String unsafe){
		String clear = Jsoup.clean(unsafe, Whitelist.simpleText());
		return clear;
	}

	/**
	 * 富文本内容处理返回安全文本
	 * @param unsafe
	 * @return
	 */
	public static String safeHtml(String unsafe){
		String safe = Jsoup.clean(unsafe, Whitelist.basic());
		return safe;
	}

	public static void main(String[] args){
		String str = "<p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-size: 18px; text-align: justify; background-color: rgb(250, 251, 252); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">新华社北京3月16日电</span></p><p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255);\"><span style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-size: 18px; text-align: justify; background-color: rgb(250, 251, 252); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><br/></span></p><p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255); line-height: 2em;\"><span style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-size: 18px; text-align: justify; background-color: rgb(250, 251, 252); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">北京大学援鄂医疗队全体&ldquo;90后&rdquo;党员：</span></p><p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255); line-height: 2em;\"><span style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-size: 18px; text-align: justify; background-color: rgb(250, 251, 252); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp; &nbsp; &nbsp; 来信收悉。在新冠肺炎疫情防控斗争中，你们青年人同在一线英勇奋战的广大疫情防控人员一道，不畏艰险、冲锋在前、舍生忘死，彰显了青春的蓬勃力量，交出了合格答卷。广大青年用行动证明，新时代的中国青年是好样的，是堪当大任的！我向你们、向奋斗在疫情防控各条战线上的广大青年，致以诚挚的问候！</span></p><p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255); line-height: 2em;\"><span style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-size: 18px; text-align: justify; background-color: rgb(250, 251, 252); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">&nbsp; &nbsp; &nbsp; 青年一代有理想、有本领、有担当，国家就有前途，民族就有希望。希望你们努力在为人民服务中茁壮成长、在艰苦奋斗中砥砺意志品质、在实践中增长工作本领，继续在救死扶伤的岗位上拼搏奋战，带动广大青年不惧风雨、勇挑重担，让青春在党和人民最需要的地方绽放绚丽之花。</span></p><p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255); text-align: right; line-height: 2em;\"><span data-node-type=\"InlineFormat\" style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-family: FZYaSongS-R-GB, FZYaSong-M-GBK, &quot;PingFang SC&quot;, miui, system-ui, -apple-system, BlinkMacSystemFont, &quot;Helvetica Neue&quot;, Helvetica, sans-serif; font-size: 18px; background-color: rgb(250, 251, 252);\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span><span style=\"margin: 0px; padding: 0px; border: medium none; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\"><span data-node-type=\"InlineFormat\" style=\"margin: 0px; padding: 0px; border: medium none; color: rgb(51, 51, 51); font-size: 18px; background-color: rgb(250, 251, 252); font-weight: bolder;\">习近平</span><span style=\"margin: 0px; padding: 0px; border: medium none;\"></span></span></p><p style=\"margin-top: 10px; margin-bottom: 10px; padding: 0px; border: medium none; font-family: &quot;Microsoft Yahei&quot;; font-size: 13px; white-space: normal; background-color: rgb(255, 255, 255); text-align: right; line-height: 2em;\"><span style=\"margin: 0px; padding: 0px; border: medium none; font-weight: bolder; color: rgb(51, 51, 51); font-size: 18px; background-color: rgb(250, 251, 252); text-align: left; word-break: break-word; font-family: 微软雅黑, &quot;Microsoft YaHei&quot;;\">2020年3月15日</span></p><p><br/></p>";
//		System.out.println(escapehtml(str));
		System.out.println(unescapehtml(str));
//		System.out.println(unescapehtml(escapehtml(str)));
//		System.out.println(cleanHtml(str));
//		System.out.println(safeHtml(str));
	}
}