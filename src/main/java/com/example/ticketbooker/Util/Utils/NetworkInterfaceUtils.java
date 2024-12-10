package com.example.ticketbooker.Util.Utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkInterfaceUtils {

    public static String getIPv4Address() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        // lap qua tung mang
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            String interfaceName = networkInterface.getName().toLowerCase();

            if (networkInterface.isUp() && !networkInterface.isLoopback() && (interfaceName.contains("wlan") || interfaceName.contains("wi-fi"))) {
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                // lap qua tung ip cua mang
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof java.net.Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        }
        return null;
    }
}
