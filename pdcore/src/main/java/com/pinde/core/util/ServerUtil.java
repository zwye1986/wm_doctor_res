package com.pinde.core.util;

import com.pinde.lic.util.LibraryUtil;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.UUID;

public class ServerUtil {
    private static Logger logger = LoggerFactory.getLogger(ServerUtil.class);

    private static Sigar sigar = new Sigar();
    public static String getMachineId() {

        try {
            String cpuId = getCpuId();
            String hostName = InetAddress.getLocalHost().getHostName();
            cpuId = cpuId + hostName;
            return UUID.nameUUIDFromBytes(cpuId.getBytes("utf-8")).toString();
        } catch (Exception var3) {
//            logger.error("get machine id failed", var3);
        }

        return null;
    }

    public static String getCpuId() {
        StringBuffer sb = new StringBuffer();

        try {
            CpuInfo[] infos = sigar.getCpuInfoList();

            for(int i = 0; i < infos.length; ++i) {
                CpuInfo info = infos[i];
                sb.append(info.getVendor());
                sb.append(info.getModel());
                sb.append(info.getTotalCores());
                if (i == 0) {
                    break;
                }
            }
        } catch (SigarException var5) {
            SigarException e = var5;
            logger.error("", e);
        }

        return sb.toString();
    }

}
