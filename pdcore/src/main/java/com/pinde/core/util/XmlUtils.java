package com.pinde.core.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class XmlUtils {
    /**
     * 正则表达式构建：<tag[^>]*>(.*?)</tag>，其中：
     *
     * <tag 匹配标签开始
     *
     * [^>]* 匹配标签内的属性（非>字符）
     *
     * >(.*?)</tag> 匹配结束符>并捕获内容，使用非贪婪模式.*?确保匹配到第一个闭合标签
     *
     * Pattern.DOTALL 使.能匹配包括换行符的所有字符
     *
     * 匹配与提取：通过Matcher.find()查找第一个匹配项，返回捕获组的内容并去除首尾空白
     *
     * 异常处理：未找到时返回null
     * @param tag
     * @param xmlStr
     * @return
     */
    public static String getValueByTag(String tag, String xmlStr) {
        String regex = "<" + tag + "[^>]*>(.*?)</" + tag + ">";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xmlStr);
        if (matcher.find()) {
            return matcher.group(1).trim(); // 去除可能的空白字符
        }
        return null; // 未找到时返回null，可根据需求调整
    }

    public static void main(String[] args) {
        String xmlStr = "<SkillRegistry><skill_operName id=\"16b97d08b8a14c6783cc2a7a83f7bcf6\">纤维支气管镜</skill_operName><resultFlow>75666927adf54730bc7a2a81efbcce12</resultFlow><dataType>skill</dataType><userFlow>e79fd44ab58a47f5bc5516029ced9670</userFlow><deptFlow>ef3e13c2e62b4d08bc13493be0e81333</deptFlow><skill_mrNo>653601</skill_mrNo><skill_result id=\"1\">是</skill_result><skill_pName>王含全</skill_pName><skill_operDate>2017-08-21</skill_operDate><cataFlow>16b97d08b8a14c6783cc2a7a83f7bcf6</cataFlow></SkillRegistry>";
        String tag = "skill_pName";
        System.out.println(getValueByTag(tag, xmlStr));
    }
}
