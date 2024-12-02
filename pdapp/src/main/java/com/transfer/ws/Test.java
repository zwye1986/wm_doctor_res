package com.transfer.ws;

/**
 * Created by Administrator on 2017/11/6.
 */
public class Test {
    public static void main(String[] args) {// 尝试加载Bouncy Castle的SecurityProvider
        try {
            Class.forName("org.bouncycastle.jce.provider.BouncyCastleProvider");
            System.out.println("Bouncy Castle Provider found in classpath.");
        } catch (ClassNotFoundException e) {
            System.out.println("Bouncy Castle Provider not found in classpath.");
        }
    }
}
