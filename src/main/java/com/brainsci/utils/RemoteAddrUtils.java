package com.brainsci.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class RemoteAddrUtils {
    private static RemoteAddrUtils remoteAddrUtils = new RemoteAddrUtils();
    public static RemoteAddrUtils getRemoteAddrUtils(){
        return remoteAddrUtils;
    }
    public String getRemoteIP(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "本地";
        }
        return ip;
    }
}
