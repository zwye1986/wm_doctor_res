<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.lang.management.ThreadInfo"%>
<%@page import="java.lang.management.ThreadMXBean"%>
<%@page import="java.lang.management.ManagementFactory"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Threads in ldcstudy.com</title>
    <style>
        body {font-size:8pt;}
        ol {line-height:18px;}
    </style>
</head>
<body>
<strong>java.io.tmpdir:</strong>
<ul>
    <li><%=System.getProperty("java.io.tmpdir")%></li>
</ul>
<br/>
<strong>Memory:</strong>
<ol>
    <li>freeMemory=<%=Runtime.getRuntime().freeMemory()/(1024*1024)%>M</li>
    <li>totalMemory=<%=Runtime.getRuntime().totalMemory()/(1024*1024)%>M</li>
    <li>maxMemory=<%=Runtime.getRuntime().maxMemory()/(1024*1024)%>M</li>
</ol>
<br/>
<strong>Thread:</strong>
<ol>
    <%
        for(Thread t : list_threads()){%>
    <li><%=t.getName()%>(<b><%=t.getState()%></b>)[<%=timemap.get(t.getId()) %>] : <%=t.getClass().getName()%>================<%=t.getContextClassLoader()%></li>
    <%}%>
</ol>
<%!
    public static java.util.List<Thread> list_threads(){
        int tc = Thread.activeCount();
        Thread[] ts = new Thread[tc];
        Thread.enumerate(ts);
        return java.util.Arrays.asList(ts);
    }

    public static Map<Long, Long> cputime(){
        ThreadMXBean tm = ManagementFactory.getThreadMXBean();
        tm.setThreadContentionMonitoringEnabled(true);
        long [] tid = tm.getAllThreadIds();
        ThreadInfo [] tia = tm.getThreadInfo(tid, Integer.MAX_VALUE);

        long [][] threadArray = new long[tia.length][2];

        Map<Long, Long> map = new HashMap<Long, Long>();

        for (int i = 0; i < tia.length; i++) {
            long threadId = tia[i].getThreadId();

            long cpuTime = tm.getThreadCpuTime(tia[i].getThreadId())/(1000*1000*1000);
            //threadArray[i][0] = threadId;
            //threadArray[i][1] = cpuTime;
            map.put(threadId, cpuTime);
        }
        return map;
    }

    Map<Long, Long> timemap = cputime();

    Map cpuTimes = new HashMap();
    Map cpuTimeFetch = new HashMap();
%>
<hr/>

<%

    long cpus = Runtime.getRuntime().availableProcessors();
    ThreadMXBean threads = ManagementFactory.getThreadMXBean();
    threads.setThreadContentionMonitoringEnabled(true);
    long now = System.currentTimeMillis();
    ThreadInfo[] t = threads.dumpAllThreads(false, false);
    out.print("t.length...." + t.length);
    int blockCount = 0;
    for (int i = 0; i < t.length; i++) {
        long id = t[i].getThreadId();
        Long idObj = new Long(id);
        long current = 0;
        if (cpuTimes.get(idObj) != null) {
            long prev = ((Long) cpuTimes.get(idObj)).longValue();//前一次的时间点
            current = threads.getThreadCpuTime(t[i].getThreadId())*1000;//当前系统时间

            double percent = (double)(current - prev) / (double)((now - prev) * cpus * 1000);

            out.print("<li> ThreadName:" + t[i].getThreadName()+ "BlockedTime:" + t[i].getBlockedTime() + "percent:" + percent + " prev: " + prev + " current: " + current + "</li>");
            if (percent > 0 && prev > 0) {

                out.println("<li>" + t[i].getThreadName()+"#"+t[i].getThreadId() + " Time: " + percent + " (" + prev + ", " + current + ") ");
                String locked = t[i].getLockInfo()==null?"":t[i].getLockInfo().getClass().toString();
                out.println(" Blocked: (" + t[i].getBlockedTime() + ", " + t[i].getBlockedCount() + ", " + locked + ")</li>");
                StackTraceElement[] te = t[i].getStackTrace();
                out.println("<ul>");
                for(int j = 0; j < te.length; j++){
                    out.println("<li>" + te[j].getClassName()+"#"+te[j].getMethodName() + " (LineNumber: " + te[j].getLineNumber() +  ") ");
                }
                out.println("</ul>");
                blockCount ++;
            }
        }
        cpuTimes.put(idObj, new Long(current));
        cpuTimes.put(idObj, new Long(now));
    }
    out.print("<li>总阻塞线程:" + blockCount  +" 个</li>");
%>
</body>
</html>
