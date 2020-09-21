package com.panli.commom.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.Enumeration;

/**
 * @author lipan
 * @date 2020-09-18
 * @desc 获取本机的IP与主机名
 */
public class IPUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPUtils.class);

    private final static String LOCALHOST = "127.0.0.1";

    public static String getHostAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); // Returns all the interfaces on this machine
            if (networkInterfaces == null)
                return null;
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (StringUtils.isNotBlank(hostAddress) && !hostAddress.equals(LOCALHOST))
                            return hostAddress;
                    }
                }
            }
        } catch (SocketException e) {
            LOGGER.error("getHostAddress fail...", e);
        }
        return null;
    }

    public static String getHostName() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null && inetAddress instanceof Inet4Address) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (StringUtils.isNotBlank(hostAddress) && !hostAddress.equals(LOCALHOST))
                            return inetAddress.getHostName();
                    }
                }
            }
        } catch (SocketException e) {
            LOGGER.error("getHostName fail...", e);
        }
        return null;
    }

    public static void main(String[] args) {
        String hostAddress = getHostAddress();
        System.out.println(hostAddress);

        String hostName = getHostName();
        System.out.println(hostName);
    }
}
