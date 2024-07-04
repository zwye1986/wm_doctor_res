package com.transfer;

import com.transfer.ws.InfoTransfer;
import com.transfer.ws.InfoTransferService;

/**
 * Created by Administrator on 2017/11/6.
 */
public class Test {
    public static void main(String[] args) {
        InfoTransferService ser = new InfoTransferService();
        InfoTransfer sere = ser.getInfoTransferPort();
        sere.getResrecData("rrwe", "fdsfds");
    }
}
