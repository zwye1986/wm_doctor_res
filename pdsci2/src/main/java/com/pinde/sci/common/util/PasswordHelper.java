package com.pinde.sci.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.security.MessageDigest;

/**
 * <p/>
 * User: Zhang Kaitao
 * <p/>
 * Date: 14-1-28
 * <p/>
 * Version: 1.0
 */
public class PasswordHelper {

    private static String algorithmName = "md5";
    private static int hashIterations = 2;

    /**
     * 密码加密
     *
     * @param userFlow   用户流水号
     * @param userPasswd 用户填写的密码
     * @return
     */
    public static String encryptPassword(String userFlow, String userPasswd) {

        String newPassword = new SimpleHash(algorithmName, userPasswd, ByteSource.Util.bytes(userFlow), hashIterations).toHex();

        return newPassword;
    }

    /**
     * MD5 加密32位
     *
     * @param encryptStr
     * @return
     */
    public static String encrypt32(String encryptStr) {
        System.out.println("encrypt32:" + encryptStr);
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    public static void main(String[] args) {
//        System.out.println(encrypt32("<REQUEST><ORGID>2001</ORGID><ORGPID>105</ORGPID><ORGNAME>院长办公室2</ORGNAME><ORGTYPE>1</ORGTYPE><SPELLCODE>YZB2</SPELLCODE><ORGSHORTNAME>院长办公室2</ORGSHORTNAME><ORGENABLED>1</ORGENABLED><ORGDELETED>0</ORGDELETED><ORGCODE>00102</ORGCODE></REQUEST>9f9a5bc7111a4677bda79f25c7c98efd"));

        System.out.println(encryptPassword("20190515001","123456"));
        System.out.println(encryptPassword("20190515002","123456"));
        System.out.println(encryptPassword("20190515003","123456"));
        System.out.println(encryptPassword("20190515004","123456"));
        System.out.println(encryptPassword("20190515005","123456"));
        System.out.println(encryptPassword("20190515006","123456"));
        System.out.println(encryptPassword("20190515007","123456"));
        System.out.println(encryptPassword("20190515008","123456"));
        System.out.println(encryptPassword("20190515009","123456"));
        System.out.println(encryptPassword("20190515010","123456"));
        System.out.println(encryptPassword("20190515011","123456"));
        System.out.println(encryptPassword("20190515012","123456"));
        System.out.println(encryptPassword("20190515013","123456"));
        System.out.println(encryptPassword("20190515014","123456"));
        System.out.println(encryptPassword("20190515015","123456"));
        System.out.println(encryptPassword("20190515016","123456"));
        System.out.println(encryptPassword("20190515017","123456"));
        System.out.println(encryptPassword("20190515018","123456"));
        System.out.println(encryptPassword("20190515019","123456"));
        System.out.println(encryptPassword("20190515020","123456"));
        System.out.println(encryptPassword("20190515021","123456"));
        System.out.println(encryptPassword("20190515022","123456"));
        System.out.println(encryptPassword("20190515023","123456"));
        System.out.println(encryptPassword("20190515024","123456"));
        System.out.println(encryptPassword("20190515025","123456"));
        System.out.println(encryptPassword("20190515026","123456"));
        System.out.println(encryptPassword("20190515027","123456"));
        System.out.println(encryptPassword("20190515028","123456"));
        System.out.println(encryptPassword("20190515029","123456"));
        System.out.println(encryptPassword("20190515030","123456"));
        System.out.println(encryptPassword("20190515031","123456"));
        System.out.println(encryptPassword("20190515032","123456"));
        System.out.println(encryptPassword("20190515033","123456"));
        System.out.println(encryptPassword("20190515034","123456"));
        System.out.println(encryptPassword("20190515035","123456"));
        System.out.println(encryptPassword("20190515036","123456"));
        System.out.println(encryptPassword("20190515037","123456"));
        System.out.println(encryptPassword("20190515038","123456"));
        System.out.println(encryptPassword("20190515039","123456"));
        System.out.println(encryptPassword("20190515040","123456"));
        System.out.println(encryptPassword("20190515041","123456"));
        System.out.println(encryptPassword("20190515042","123456"));
        System.out.println(encryptPassword("20190515043","123456"));
        System.out.println(encryptPassword("20190515044","123456"));
        System.out.println(encryptPassword("20190515045","123456"));
        System.out.println(encryptPassword("20190515046","123456"));
        System.out.println(encryptPassword("20190515047","123456"));
        System.out.println(encryptPassword("20190515048","123456"));
        System.out.println(encryptPassword("20190515049","123456"));
        System.out.println(encryptPassword("20190515050","123456"));
        System.out.println(encryptPassword("20190515051","123456"));
        System.out.println(encryptPassword("20190515052","123456"));
        System.out.println(encryptPassword("20190515053","123456"));
        System.out.println(encryptPassword("20190515054","123456"));
        System.out.println(encryptPassword("20190515055","123456"));
        System.out.println(encryptPassword("20190515056","123456"));
        System.out.println(encryptPassword("20190515057","123456"));
        System.out.println(encryptPassword("20190515058","123456"));
        System.out.println(encryptPassword("20190515059","123456"));
        System.out.println(encryptPassword("20190515060","123456"));
        System.out.println(encryptPassword("20190515061","123456"));
        System.out.println(encryptPassword("20190515062","123456"));
        System.out.println(encryptPassword("20190515063","123456"));
        System.out.println(encryptPassword("20190515064","123456"));
        System.out.println(encryptPassword("20190515065","123456"));
        System.out.println(encryptPassword("20190515066","123456"));
        System.out.println(encryptPassword("20190515067","123456"));
        System.out.println(encryptPassword("20190515068","123456"));
        System.out.println(encryptPassword("20190515069","123456"));
        System.out.println(encryptPassword("20190515070","123456"));
        System.out.println(encryptPassword("20190515071","123456"));
        System.out.println(encryptPassword("20190515072","123456"));
        System.out.println(encryptPassword("20190515073","123456"));
        System.out.println(encryptPassword("20190515074","123456"));
        System.out.println(encryptPassword("20190515075","123456"));
        System.out.println(encryptPassword("20190515076","123456"));
        System.out.println(encryptPassword("20190515077","123456"));
        System.out.println(encryptPassword("20190515078","123456"));
        System.out.println(encryptPassword("20190515079","123456"));
        System.out.println(encryptPassword("20190515080","123456"));
        System.out.println(encryptPassword("20190515081","123456"));
        System.out.println(encryptPassword("20190515082","123456"));
        System.out.println(encryptPassword("20190515083","123456"));
        System.out.println(encryptPassword("20190515084","123456"));
        System.out.println(encryptPassword("20190515085","123456"));
        System.out.println(encryptPassword("20190515086","123456"));
        System.out.println(encryptPassword("20190515087","123456"));
        System.out.println(encryptPassword("20190515088","123456"));
        System.out.println(encryptPassword("20190515089","123456"));
        System.out.println(encryptPassword("20190515090","123456"));
        System.out.println(encryptPassword("20190515091","123456"));
        System.out.println(encryptPassword("20190515092","123456"));
        System.out.println(encryptPassword("20190515093","123456"));
        System.out.println(encryptPassword("20190515094","123456"));
        System.out.println(encryptPassword("20190515095","123456"));
        System.out.println(encryptPassword("20190515096","123456"));
        System.out.println(encryptPassword("20190515097","123456"));
        System.out.println(encryptPassword("20190515098","123456"));
        System.out.println(encryptPassword("20190515099","123456"));
        System.out.println(encryptPassword("20190515100","123456"));
        System.out.println(encryptPassword("20190515101","123456"));
        System.out.println(encryptPassword("20190515102","123456"));
        System.out.println(encryptPassword("20190515103","123456"));
        System.out.println(encryptPassword("20190515104","123456"));
        System.out.println(encryptPassword("20190515105","123456"));
        System.out.println(encryptPassword("20190515106","123456"));
        System.out.println(encryptPassword("20190515107","123456"));
        System.out.println(encryptPassword("20190515108","123456"));
        System.out.println(encryptPassword("20190515109","123456"));
        System.out.println(encryptPassword("20190515110","123456"));
        System.out.println(encryptPassword("20190515111","123456"));
        System.out.println(encryptPassword("20190515112","123456"));
        System.out.println(encryptPassword("20190515113","123456"));
        System.out.println(encryptPassword("20190515114","123456"));
        System.out.println(encryptPassword("20190515115","123456"));
        System.out.println(encryptPassword("20190515116","123456"));
        System.out.println(encryptPassword("20190515117","123456"));
        System.out.println(encryptPassword("20190515118","123456"));
        System.out.println(encryptPassword("20190515119","123456"));
        System.out.println(encryptPassword("20190515120","123456"));
        System.out.println(encryptPassword("20190515121","123456"));
        System.out.println(encryptPassword("20190515122","123456"));
        System.out.println(encryptPassword("20190515123","123456"));
        System.out.println(encryptPassword("20190515124","123456"));
        System.out.println(encryptPassword("20190515125","123456"));
        System.out.println(encryptPassword("20190515126","123456"));
        System.out.println(encryptPassword("20190515127","123456"));
        System.out.println(encryptPassword("20190515128","123456"));
        System.out.println(encryptPassword("20190515129","123456"));
        System.out.println(encryptPassword("20190515130","123456"));
        System.out.println(encryptPassword("20190515131","123456"));
        System.out.println(encryptPassword("20190515132","123456"));
        System.out.println(encryptPassword("20190515133","123456"));
        System.out.println(encryptPassword("20190515134","123456"));
        System.out.println(encryptPassword("20190515135","123456"));
        System.out.println(encryptPassword("20190515136","123456"));
        System.out.println(encryptPassword("20190515137","123456"));
        System.out.println(encryptPassword("20190515138","123456"));
        System.out.println(encryptPassword("20190515139","123456"));
        System.out.println(encryptPassword("20190515140","123456"));
        System.out.println(encryptPassword("20190515141","123456"));
        System.out.println(encryptPassword("20190515142","123456"));
        System.out.println(encryptPassword("20190515143","123456"));
        System.out.println(encryptPassword("20190515144","123456"));
        System.out.println(encryptPassword("20190515145","123456"));
        System.out.println(encryptPassword("20190515146","123456"));
        System.out.println(encryptPassword("20190515147","123456"));
        System.out.println(encryptPassword("20190515148","123456"));
        System.out.println(encryptPassword("20190515149","123456"));
        System.out.println(encryptPassword("20190515150","123456"));
        System.out.println(encryptPassword("20190515151","123456"));
        System.out.println(encryptPassword("20190515152","123456"));
        System.out.println(encryptPassword("20190515153","123456"));
        System.out.println(encryptPassword("20190515154","123456"));
        System.out.println(encryptPassword("20190515155","123456"));
        System.out.println(encryptPassword("20190515156","123456"));
        System.out.println(encryptPassword("20190515157","123456"));
        System.out.println(encryptPassword("20190515158","123456"));
        System.out.println(encryptPassword("20190515159","123456"));
        System.out.println(encryptPassword("20190515160","123456"));
        System.out.println(encryptPassword("20190515161","123456"));
        System.out.println(encryptPassword("20190515162","123456"));
        System.out.println(encryptPassword("20190515163","123456"));
        System.out.println(encryptPassword("20190515164","123456"));
        System.out.println(encryptPassword("20190515165","123456"));
        System.out.println(encryptPassword("20190515166","123456"));
        System.out.println(encryptPassword("20190515167","123456"));
        System.out.println(encryptPassword("20190515168","123456"));
        System.out.println(encryptPassword("20190515169","123456"));
        System.out.println(encryptPassword("20190515170","123456"));
        System.out.println(encryptPassword("20190515171","123456"));
        System.out.println(encryptPassword("20190515172","123456"));
        System.out.println(encryptPassword("20190515173","123456"));
        System.out.println(encryptPassword("20190515174","123456"));
        System.out.println(encryptPassword("20190515175","123456"));
        System.out.println(encryptPassword("20190515176","123456"));
        System.out.println(encryptPassword("20190515177","123456"));
        System.out.println(encryptPassword("20190515178","123456"));
        System.out.println(encryptPassword("20190515179","123456"));
        System.out.println(encryptPassword("20190515180","123456"));
        System.out.println(encryptPassword("20190515181","123456"));
        System.out.println(encryptPassword("20190515182","123456"));
        System.out.println(encryptPassword("20190515183","123456"));
        System.out.println(encryptPassword("20190515184","123456"));
        System.out.println(encryptPassword("20190515185","123456"));
        System.out.println(encryptPassword("20190515186","123456"));
        System.out.println(encryptPassword("20190515187","123456"));
        System.out.println(encryptPassword("20190515188","123456"));
        System.out.println(encryptPassword("20190515189","123456"));
        System.out.println(encryptPassword("20190515190","123456"));
        System.out.println(encryptPassword("20190515191","123456"));
        System.out.println(encryptPassword("20190515192","123456"));
        System.out.println(encryptPassword("20190515193","123456"));
        System.out.println(encryptPassword("20190515194","123456"));
        System.out.println(encryptPassword("20190515195","123456"));
        System.out.println(encryptPassword("20190515196","123456"));
        System.out.println(encryptPassword("20190515197","123456"));
        System.out.println(encryptPassword("20190515198","123456"));
        System.out.println(encryptPassword("20190515199","123456"));
        System.out.println(encryptPassword("20190515200","123456"));
        System.out.println(encryptPassword("20190515201","123456"));
        System.out.println(encryptPassword("20190515202","123456"));
        System.out.println(encryptPassword("20190515203","123456"));
        System.out.println(encryptPassword("20190515204","123456"));
        System.out.println(encryptPassword("20190515205","123456"));
        System.out.println(encryptPassword("20190515206","123456"));
        System.out.println(encryptPassword("20190515207","123456"));
        System.out.println(encryptPassword("20190515208","123456"));
        System.out.println(encryptPassword("20190515209","123456"));
        System.out.println(encryptPassword("20190515210","123456"));
        System.out.println(encryptPassword("20190515211","123456"));
        System.out.println(encryptPassword("20190515212","123456"));
        System.out.println(encryptPassword("20190515213","123456"));
        System.out.println(encryptPassword("20190515214","123456"));
        System.out.println(encryptPassword("20190515215","123456"));
        System.out.println(encryptPassword("20190515216","123456"));
        System.out.println(encryptPassword("20190515217","123456"));
        System.out.println(encryptPassword("20190515218","123456"));
        System.out.println(encryptPassword("20190515219","123456"));
        System.out.println(encryptPassword("20190515220","123456"));
        System.out.println(encryptPassword("20190515221","123456"));
        System.out.println(encryptPassword("20190515222","123456"));
        System.out.println(encryptPassword("20190515223","123456"));
        System.out.println(encryptPassword("20190515224","123456"));
        System.out.println(encryptPassword("20190515225","123456"));
        System.out.println(encryptPassword("20190515226","123456"));
        System.out.println(encryptPassword("20190515227","123456"));
        System.out.println(encryptPassword("20190515228","123456"));
        System.out.println(encryptPassword("20190515229","123456"));
        System.out.println(encryptPassword("20190515230","123456"));
        System.out.println(encryptPassword("20190515231","123456"));
        System.out.println(encryptPassword("20190515232","123456"));
        System.out.println(encryptPassword("20190515233","123456"));
        System.out.println(encryptPassword("20190515234","123456"));
        System.out.println(encryptPassword("20190515235","123456"));
        System.out.println(encryptPassword("20190515236","123456"));
        System.out.println(encryptPassword("20190515237","123456"));
        System.out.println(encryptPassword("20190515238","123456"));
        System.out.println(encryptPassword("20190515239","123456"));
        System.out.println(encryptPassword("20190515240","123456"));
        System.out.println(encryptPassword("20190515241","123456"));
        System.out.println(encryptPassword("20190515242","123456"));
        System.out.println(encryptPassword("20190515243","123456"));
        System.out.println(encryptPassword("20190515244","123456"));
        System.out.println(encryptPassword("20190515245","123456"));
        System.out.println(encryptPassword("20190515246","123456"));
        System.out.println(encryptPassword("20190515247","123456"));
        System.out.println(encryptPassword("20190515248","123456"));
        System.out.println(encryptPassword("20190515249","123456"));
        System.out.println(encryptPassword("20190515250","123456"));
        System.out.println(encryptPassword("20190515251","123456"));
        System.out.println(encryptPassword("20190515252","123456"));
        System.out.println(encryptPassword("20190515253","123456"));
        System.out.println(encryptPassword("20190515254","123456"));
        System.out.println(encryptPassword("20190515255","123456"));
        System.out.println(encryptPassword("20190515256","123456"));
        System.out.println(encryptPassword("20190515257","123456"));
        System.out.println(encryptPassword("20190515258","123456"));
        System.out.println(encryptPassword("20190515259","123456"));
        System.out.println(encryptPassword("20190515260","123456"));
        System.out.println(encryptPassword("20190515261","123456"));
        System.out.println(encryptPassword("20190515262","123456"));
        System.out.println(encryptPassword("20190515263","123456"));
        System.out.println(encryptPassword("20190515264","123456"));
        System.out.println(encryptPassword("20190515265","123456"));
        System.out.println(encryptPassword("20190515266","123456"));
        System.out.println(encryptPassword("20190515267","123456"));
        System.out.println(encryptPassword("20190515268","123456"));
        System.out.println(encryptPassword("20190515269","123456"));
        System.out.println(encryptPassword("20190515270","123456"));
        System.out.println(encryptPassword("20190515271","123456"));
        System.out.println(encryptPassword("20190515272","123456"));
        System.out.println(encryptPassword("20190515273","123456"));
        System.out.println(encryptPassword("20190515274","123456"));
        System.out.println(encryptPassword("20190515275","123456"));
        System.out.println(encryptPassword("20190515276","123456"));
        System.out.println(encryptPassword("20190515277","123456"));
        System.out.println(encryptPassword("20190515278","123456"));
        System.out.println(encryptPassword("20190515279","123456"));
        System.out.println(encryptPassword("20190515280","123456"));
        System.out.println(encryptPassword("20190515281","123456"));
        System.out.println(encryptPassword("20190515282","123456"));
        System.out.println(encryptPassword("20190515283","123456"));
        System.out.println(encryptPassword("20190515284","123456"));
        System.out.println(encryptPassword("20190515285","123456"));
        System.out.println(encryptPassword("20190515286","123456"));
        System.out.println(encryptPassword("20190515287","123456"));
        System.out.println(encryptPassword("20190515288","123456"));
        System.out.println(encryptPassword("20190515289","123456"));
        System.out.println(encryptPassword("20190515290","123456"));
        System.out.println(encryptPassword("20190515291","123456"));
        System.out.println(encryptPassword("20190515292","123456"));
        System.out.println(encryptPassword("20190515293","123456"));
        System.out.println(encryptPassword("20190515294","123456"));
        System.out.println(encryptPassword("20190515295","123456"));
        System.out.println(encryptPassword("20190515296","123456"));
        System.out.println(encryptPassword("20190515297","123456"));
        System.out.println(encryptPassword("20190515298","123456"));
        System.out.println(encryptPassword("20190515299","123456"));
        System.out.println(encryptPassword("20190515300","123456"));
        System.out.println(encryptPassword("20190515301","123456"));
        System.out.println(encryptPassword("20190515302","123456"));
        System.out.println(encryptPassword("20190515303","123456"));
        System.out.println(encryptPassword("20190515304","123456"));
        System.out.println(encryptPassword("20190515305","123456"));
        System.out.println(encryptPassword("20190515306","123456"));
        System.out.println(encryptPassword("20190515307","123456"));
        System.out.println(encryptPassword("20190515308","123456"));
        System.out.println(encryptPassword("20190515309","123456"));
        System.out.println(encryptPassword("20190515310","123456"));
        System.out.println(encryptPassword("20190515311","123456"));
        System.out.println(encryptPassword("20190515312","123456"));
        System.out.println(encryptPassword("20190515313","123456"));
        System.out.println(encryptPassword("20190515314","123456"));
        System.out.println(encryptPassword("20190515315","123456"));
        System.out.println(encryptPassword("20190515316","123456"));
        System.out.println(encryptPassword("20190515317","123456"));
        System.out.println(encryptPassword("20190515318","123456"));
        System.out.println(encryptPassword("20190515319","123456"));
        System.out.println(encryptPassword("20190515320","123456"));
        System.out.println(encryptPassword("20190515321","123456"));
        System.out.println(encryptPassword("20190515322","123456"));
        System.out.println(encryptPassword("20190515323","123456"));
        System.out.println(encryptPassword("20190515324","123456"));
        System.out.println(encryptPassword("20190515325","123456"));
        System.out.println(encryptPassword("20190515326","123456"));
        System.out.println(encryptPassword("20190515327","123456"));
        System.out.println(encryptPassword("20190515328","123456"));
        System.out.println(encryptPassword("20190515329","123456"));
        System.out.println(encryptPassword("20190515330","123456"));
        System.out.println(encryptPassword("20190515331","123456"));
        System.out.println(encryptPassword("20190515332","123456"));
        System.out.println(encryptPassword("20190515333","123456"));
        System.out.println(encryptPassword("20190515334","123456"));
        System.out.println(encryptPassword("20190515335","123456"));
        System.out.println(encryptPassword("20190515336","123456"));
        System.out.println(encryptPassword("20190515337","123456"));
        System.out.println(encryptPassword("20190515338","123456"));
        System.out.println(encryptPassword("20190515339","123456"));
        System.out.println(encryptPassword("20190515340","123456"));
        System.out.println(encryptPassword("20190515341","123456"));
        System.out.println(encryptPassword("20190515342","123456"));
        System.out.println(encryptPassword("20190515343","123456"));
        System.out.println(encryptPassword("20190515344","123456"));
        System.out.println(encryptPassword("20190515345","123456"));
        System.out.println(encryptPassword("20190515346","123456"));
        System.out.println(encryptPassword("20190515347","123456"));
        System.out.println(encryptPassword("20190515348","123456"));
        System.out.println(encryptPassword("20190515349","123456"));
        System.out.println(encryptPassword("20190515350","123456"));
        System.out.println(encryptPassword("20190515351","123456"));
        System.out.println(encryptPassword("20190515352","123456"));
        System.out.println(encryptPassword("20190515353","123456"));
        System.out.println(encryptPassword("20190515354","123456"));
        System.out.println(encryptPassword("20190515355","123456"));
        System.out.println(encryptPassword("20190515356","123456"));
        System.out.println(encryptPassword("20190515357","123456"));
        System.out.println(encryptPassword("20190515358","123456"));
        System.out.println(encryptPassword("20190515359","123456"));
        System.out.println(encryptPassword("20190515360","123456"));
        System.out.println(encryptPassword("20190515361","123456"));
        System.out.println(encryptPassword("20190515362","123456"));
        System.out.println(encryptPassword("20190515363","123456"));
        System.out.println(encryptPassword("20190515364","123456"));
        System.out.println(encryptPassword("20190515365","123456"));
        System.out.println(encryptPassword("20190515366","123456"));
        System.out.println(encryptPassword("20190515367","123456"));
        System.out.println(encryptPassword("20190515368","123456"));
        System.out.println(encryptPassword("20190515369","123456"));
        System.out.println(encryptPassword("20190515370","123456"));
        System.out.println(encryptPassword("20190515371","123456"));
        System.out.println(encryptPassword("20190515372","123456"));
        System.out.println(encryptPassword("20190515373","123456"));
        System.out.println(encryptPassword("20190515374","123456"));
        System.out.println(encryptPassword("20190515375","123456"));
        System.out.println(encryptPassword("20190515376","123456"));
        System.out.println(encryptPassword("20190515377","123456"));
        System.out.println(encryptPassword("20190515378","123456"));
        System.out.println(encryptPassword("20190515379","123456"));
        System.out.println(encryptPassword("20190515380","123456"));
        System.out.println(encryptPassword("20190515381","123456"));
        System.out.println(encryptPassword("20190515382","123456"));
        System.out.println(encryptPassword("20190515383","123456"));
        System.out.println(encryptPassword("20190515384","123456"));
        System.out.println(encryptPassword("20190515385","123456"));
        System.out.println(encryptPassword("20190515386","123456"));
        System.out.println(encryptPassword("20190515387","123456"));
        System.out.println(encryptPassword("20190515388","123456"));
        System.out.println(encryptPassword("20190515389","123456"));
        System.out.println(encryptPassword("20190515390","123456"));
        System.out.println(encryptPassword("20190515391","123456"));
        System.out.println(encryptPassword("20190515392","123456"));
        System.out.println(encryptPassword("20190515393","123456"));
        System.out.println(encryptPassword("20190515394","123456"));
        System.out.println(encryptPassword("20190515395","123456"));
        System.out.println(encryptPassword("20190515396","123456"));
        System.out.println(encryptPassword("20190515397","123456"));
        System.out.println(encryptPassword("20190515398","123456"));
        System.out.println(encryptPassword("20190515399","123456"));
        System.out.println(encryptPassword("20190515400","123456"));
        System.out.println(encryptPassword("20190515401","123456"));
        System.out.println(encryptPassword("20190515402","123456"));
        System.out.println(encryptPassword("20190515403","123456"));
        System.out.println(encryptPassword("20190515404","123456"));
        System.out.println(encryptPassword("20190515405","123456"));
        System.out.println(encryptPassword("20190515406","123456"));
        System.out.println(encryptPassword("20190515407","123456"));
        System.out.println(encryptPassword("20190515408","123456"));
        System.out.println(encryptPassword("20190515409","123456"));
        System.out.println(encryptPassword("20190515410","123456"));
        System.out.println(encryptPassword("20190515411","123456"));
        System.out.println(encryptPassword("20190515412","123456"));
        System.out.println(encryptPassword("20190515413","123456"));
        System.out.println(encryptPassword("20190515414","123456"));
        System.out.println(encryptPassword("20190515415","123456"));
        System.out.println(encryptPassword("20190515416","123456"));
        System.out.println(encryptPassword("20190515417","123456"));
        System.out.println(encryptPassword("20190515418","123456"));
        System.out.println(encryptPassword("20190515419","123456"));
        System.out.println(encryptPassword("20190515420","123456"));
        System.out.println(encryptPassword("20190515421","123456"));
        System.out.println(encryptPassword("20190515422","123456"));
        System.out.println(encryptPassword("20190515423","123456"));
        System.out.println(encryptPassword("20190515424","123456"));
        System.out.println(encryptPassword("20190515425","123456"));
        System.out.println(encryptPassword("20190515426","123456"));
        System.out.println(encryptPassword("20190515427","123456"));
        System.out.println(encryptPassword("20190515428","123456"));
        System.out.println(encryptPassword("20190515429","123456"));
        System.out.println(encryptPassword("20190515430","123456"));
        System.out.println(encryptPassword("20190515431","123456"));
        System.out.println(encryptPassword("20190515432","123456"));
        System.out.println(encryptPassword("20190515433","123456"));
        System.out.println(encryptPassword("20190515434","123456"));
        System.out.println(encryptPassword("20190515435","123456"));
        System.out.println(encryptPassword("20190515436","123456"));
        System.out.println(encryptPassword("20190515437","123456"));
        System.out.println(encryptPassword("20190515438","123456"));
        System.out.println(encryptPassword("20190515439","123456"));
        System.out.println(encryptPassword("20190515440","123456"));
        System.out.println(encryptPassword("20190515441","123456"));
        System.out.println(encryptPassword("20190515442","123456"));
        System.out.println(encryptPassword("20190515443","123456"));
        System.out.println(encryptPassword("20190515444","123456"));
        System.out.println(encryptPassword("20190515445","123456"));
        System.out.println(encryptPassword("20190515446","123456"));
        System.out.println(encryptPassword("20190515447","123456"));
        System.out.println(encryptPassword("20190515448","123456"));
        System.out.println(encryptPassword("20190515449","123456"));
        System.out.println(encryptPassword("20190515450","123456"));
        System.out.println(encryptPassword("20190515451","123456"));
        System.out.println(encryptPassword("20190515452","123456"));
        System.out.println(encryptPassword("20190515453","123456"));
        System.out.println(encryptPassword("20190515454","123456"));
        System.out.println(encryptPassword("20190515455","123456"));
        System.out.println(encryptPassword("20190515456","123456"));
        System.out.println(encryptPassword("20190515457","123456"));
        System.out.println(encryptPassword("20190515458","123456"));
        System.out.println(encryptPassword("20190515459","123456"));
        System.out.println(encryptPassword("20190515460","123456"));
        System.out.println(encryptPassword("20190515461","123456"));
        System.out.println(encryptPassword("20190515462","123456"));
        System.out.println(encryptPassword("20190515463","123456"));
        System.out.println(encryptPassword("20190515464","123456"));
        System.out.println(encryptPassword("20190515465","123456"));
        System.out.println(encryptPassword("20190515466","123456"));
        System.out.println(encryptPassword("20190515467","123456"));
        System.out.println(encryptPassword("20190515468","123456"));
        System.out.println(encryptPassword("20190515469","123456"));
        System.out.println(encryptPassword("20190515470","123456"));
        System.out.println(encryptPassword("20190515471","123456"));
        System.out.println(encryptPassword("20190515472","123456"));
        System.out.println(encryptPassword("20190515473","123456"));
        System.out.println(encryptPassword("20190515474","123456"));
        System.out.println(encryptPassword("20190515475","123456"));
        System.out.println(encryptPassword("20190515476","123456"));
        System.out.println(encryptPassword("20190515477","123456"));
        System.out.println(encryptPassword("20190515478","123456"));
        System.out.println(encryptPassword("20190515479","123456"));
        System.out.println(encryptPassword("20190515480","123456"));
        System.out.println(encryptPassword("20190515481","123456"));
        System.out.println(encryptPassword("20190515482","123456"));
        System.out.println(encryptPassword("20190515483","123456"));
        System.out.println(encryptPassword("20190515484","123456"));
        System.out.println(encryptPassword("20190515485","123456"));
        System.out.println(encryptPassword("20190515486","123456"));
        System.out.println(encryptPassword("20190515487","123456"));
        System.out.println(encryptPassword("20190515488","123456"));
        System.out.println(encryptPassword("20190515489","123456"));
        System.out.println(encryptPassword("20190515490","123456"));
        System.out.println(encryptPassword("20190515491","123456"));
        System.out.println(encryptPassword("20190515492","123456"));
        System.out.println(encryptPassword("20190515493","123456"));
        System.out.println(encryptPassword("20190515494","123456"));
        System.out.println(encryptPassword("20190515495","123456"));
        System.out.println(encryptPassword("20190515496","123456"));
        System.out.println(encryptPassword("20190515497","123456"));
        System.out.println(encryptPassword("20190515498","123456"));
        System.out.println(encryptPassword("20190515499","123456"));
        System.out.println(encryptPassword("20190515500","123456"));
        System.out.println(encryptPassword("20190515501","123456"));
        System.out.println(encryptPassword("20190515502","123456"));
        System.out.println(encryptPassword("20190515503","123456"));
        System.out.println(encryptPassword("20190515504","123456"));
        System.out.println(encryptPassword("20190515505","123456"));
        System.out.println(encryptPassword("20190515506","123456"));
        System.out.println(encryptPassword("20190515507","123456"));
        System.out.println(encryptPassword("20190515508","123456"));
        System.out.println(encryptPassword("20190515509","123456"));
        System.out.println(encryptPassword("20190515510","123456"));
        System.out.println(encryptPassword("20190515511","123456"));
        System.out.println(encryptPassword("20190515512","123456"));
        System.out.println(encryptPassword("20190515513","123456"));
        System.out.println(encryptPassword("20190515514","123456"));
        System.out.println(encryptPassword("20190515515","123456"));
        System.out.println(encryptPassword("20190515516","123456"));
        System.out.println(encryptPassword("20190515517","123456"));
        System.out.println(encryptPassword("20190515518","123456"));
        System.out.println(encryptPassword("20190515519","123456"));
        System.out.println(encryptPassword("20190515520","123456"));
        System.out.println(encryptPassword("20190515521","123456"));
        System.out.println(encryptPassword("20190515522","123456"));
        System.out.println(encryptPassword("20190515523","123456"));
        System.out.println(encryptPassword("20190515524","123456"));
        System.out.println(encryptPassword("20190515525","123456"));
        System.out.println(encryptPassword("20190515526","123456"));
        System.out.println(encryptPassword("20190515527","123456"));
        System.out.println(encryptPassword("20190515528","123456"));
        System.out.println(encryptPassword("20190515529","123456"));
        System.out.println(encryptPassword("20190515530","123456"));
        System.out.println(encryptPassword("20190515531","123456"));
















































































































































































































































































































































































































































    }
}
