package com.pinde.sci;

import com.javax0.license3j.licensor.License;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ServerUtil;
import com.pinde.lic.hardware.MechineInfo;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bouncycastle.crypto.digests.SHA512Digest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LicenseKey {
    private static String MACHINE_ID = "machineId";//电脑物理id
    private static String ISSUE_DATE = "issueDate";//发证时间
    private static String VALID_DATE = "validDate";//到期时间
    private static String WORK_STATION = "workStation";

    private static String machineId = "a65757cd-76b3-3fef-95a5-81d090f4563a";
    private static String issueDate = "2014-04-01";
    private static String validDate = "2014-04-30";
    private static String workStation = "";

    private static byte[] digest = new byte[]{
            (byte) 0x06,
            (byte) 0x86, (byte) 0x22, (byte) 0xBC, (byte) 0x44, (byte) 0xCF, (byte) 0x51, (byte) 0xA6, (byte) 0x39,
            (byte) 0x01, (byte) 0x8F, (byte) 0x3D, (byte) 0xA1, (byte) 0x90, (byte) 0x82, (byte) 0x6F, (byte) 0x4A,
            (byte) 0xBF, (byte) 0x6A, (byte) 0x5A, (byte) 0xF6, (byte) 0x43, (byte) 0x74, (byte) 0x8C, (byte) 0xAC,
            (byte) 0x74, (byte) 0x77, (byte) 0x90, (byte) 0x6B, (byte) 0x80, (byte) 0x05, (byte) 0x82,
    };

    public static void main(String[] args) throws Exception {
        String issueDate = "";
        issueDate = "issueDate=" + StringEscapeUtils.escapeJava("2021-01-01");

        String machineId = "";
        machineId = "machineId=" + StringEscapeUtils.escapeJava("359ed804-75da-3fd1-bcb1-3ff64ae0844b");
        String workStationId = "";
//        workStationId = "workStation=" + StringEscapeUtils.escapeJava("srm,edc,gcp,irb,sch,edu,njmuedu,erp,exam,res,test,fstu,cmis,osca,lcjn,eval,zsey,zseylcjn,portals,study");
        workStationId = "workStation=" + StringEscapeUtils.escapeJava("res,osca,portals");
        String validDate = "";
        validDate = "validDate=" + StringEscapeUtils.escapeJava("2031-01-01");

        String licenseString = issueDate + System.getProperty("line.separator")
                + machineId + System.getProperty("line.separator")
                + workStationId + System.getProperty("line.separator")
                + validDate + System.getProperty("line.separator");
        License license = new License();
        license.setLicense(licenseString);
        InputStream in = LicenseKey.class.getResourceAsStream("/secring.gpg");
        license.loadKey(in, "njpdkj <admin@njpdkj.com>");
        license.setHashAlgorithm(2);
        String encoded = license.encodeLicense("njpdkj123456");
        System.out.println(encoded);
       /* _checkLicense("-----BEGIN PGP MESSAGE-----\n" +
                "Version: BCPG v1.43\n" +
                "\n" +
                "owJ4nJvAy8zAxBivXMZT8//NR8bTB9KSxHMyk1PzilPdMnNS/RJzU3U90/Pyi1JT\n" +
                "UvLORirr6ipApRXSgPK8XMohGaUKvomVCkamCoYGVqZGVgYWCs7BIQpGBkbGvFxl\n" +
                "iTmZKS6JJam2RgbGhroGIMTLVZ5flB1ckliSmZ9nW1yUq5OakqyTnlygk1mUpFOc\n" +
                "nAHkl+rkZeWWgujUogKd1IrEXJ2i1GKdktTiEp204pJSneTczGKd/OLkRJ2c5Kw8\n" +
                "nVSgRTpVxamVYAIsVJBfVJKYU6wDVJ1SCdSdXFSaWcLLlZuYnJGZl+qZYmtonmhq\n" +
                "Zp5kqmtmlpKqa5xskKprmZKWqJtibmBqnmhobmhuDvRBZnFxaSrUB0ZwH3QymbMw\n" +
                "MDIxKLIygcJFSiYvqyAlO0vBJjElNzPPAcLTS87PtWPg4hSAhe9/N/7/6e+5iwTv\n" +
                "v3KuPi798Wnzc66yuQJeT9mdeGsM/LUvhFYsjl9XO08qZfbTN5zul87riC9N1l0w\n" +
                "76LPM1UbG8dtIRK+tZ5Bq03XeHpsfzb/CUfnnOnSPK0h9zfFr5vVcjrkflK3/tKW\n" +
                "zBX+P8tXbYl8Fe2gMN/iPus3zcty0Qd0js2pXGoY49/I5rPhTF5ZW9DcJTt3C9xq\n" +
                "d77z36y5YM5dl7Rnu078/NMy713iFd1US6Yc8+V/eEJ+Tt7L6shuIFl2clPQty1X\n" +
                "52XGrfM9pOC+Xm93B2uNkAhf0rGVD2/W6R8ryFhkX+7g+ln5wN7M34cSE6sWCJe5\n" +
                "5NVea/gsd8CqvurEV9cj81Nsg8+e1v93TWDJ7eVplp/y/8X5LVpyqPi7z5PPx1vD\n" +
                "HPeHRDwWk/Ga6Xl5U4e13MqGudPtZ6xhTtj55viUg2Ksl+d+1bo5jW2We0lJcemy\n" +
                "i1rF/squuVvTGuQPqL/7GMVxfNny57N++vTyXo3bL3LBsr7/uGT9r25FezX+QPVP\n" +
                "0xSZ/a74abw0vbz8W8QPB59F5Rk392zglpq461eIq3OM8cVPjWbsIp687w5YzD0p\n" +
                "YHVg/bKZlxmmXNgp8rrg60qu6WZXn9h/Edq1vC19r2bEnL7njyTuvAw9VaU5vcFU\n" +
                "YuUJ58vXtz4xnKT2J23OdkGj92enXnRbZqIY5+4j+cVsdYXA9ip5A8Xq2LMtzyrb\n" +
                "fVuC4/YHAABl8Hgl\n" +
                "=6baZ\n" +
                "-----END PGP MESSAGE-----");
        getMachineId();
        checkValid();
        System.out.println("score1".substring(5));
        System.out.println("11score1".indexOf("score"));*/
    }

    public static byte[] calculatePublicKeyRingDigest(byte[] publicKeyRing) {
        SHA512Digest dig = new SHA512Digest();
        dig.reset();
        dig.update(publicKeyRing, 0, publicKeyRing.length);
        byte[] digest = new byte[dig.getDigestSize()];
        dig.doFinal(digest, 0);
        return digest;
    }

    public static void dumpPublicKeyRingDigest() throws IOException {

        System.out.println("------------------------------------------------------------------------------------");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = LicenseKey.class.getResourceAsStream("/pubring.gpg");
        int ch;
        while((ch = in.read()) >= 0) {
            baos.write(ch);
        }
        byte[] publicKeyRing = baos.toByteArray();
        byte[] calculatedDigest = calculatePublicKeyRingDigest(publicKeyRing);
        StringBuilder retval = new StringBuilder("byte [] digest = new byte[] {\n");

        for(int i = 0; i < calculatedDigest.length; ++i) {
            int intVal = calculatedDigest[i] & 255;
            retval.append(String.format("(byte)0x%02X, ", intVal));
            if (i % 8 == 0) {
                retval.append("\n");
            }
        }

        retval.append("\n};\n");
        System.out.println(retval.toString());
        System.out.println("------------------------------------------------------------------------------------");
    }

    private static void _checkLicense(String licenseStringEncoded) throws Exception{
        License lic = new License();
        try {
            lic.loadKeyRing(LicenseKey.class.getResourceAsStream("/pubring.gpg"), null);
            lic.setLicenseEncoded(licenseStringEncoded);

            machineId = lic.getFeature(MACHINE_ID);
            issueDate = lic.getFeature(ISSUE_DATE);
            validDate = lic.getFeature(VALID_DATE);
            workStation = lic.getFeature(WORK_STATION);
            System.out.println(machineId+"  "+issueDate+"  "+validDate+"   "+workStation);
            System.out.println(lic.isVerified());
        } catch (Exception e) {

        }
    }
    private static  void getMachineId()
    {
        System.out.println(ServerUtil.getMachineId());
    }
    public static boolean checkValid(){

        String today = DateUtil.getCurrDate();
        if (issueDate != null) {
            if (DateUtil.signDaysBetweenTowDate(issueDate,today)>0) {
                System.out.println("系统验证错误，发行日期太晚了,系统时间被更改了！");
                return false;
            }
        }
        if (validDate != null) {
            if (DateUtil.signDaysBetweenTowDate(today,validDate)>0) {
                System.out.println("系统验证错误，License过期！");
                return false;
            }
        }
        return true;
    }
}
