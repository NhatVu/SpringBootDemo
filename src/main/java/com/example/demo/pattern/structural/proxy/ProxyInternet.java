package com.example.demo.pattern.structural.proxy;

import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements Internet{
    private Internet internet = new RealInternet();
    private static List<String> bannedSites;

    static
    {
        bannedSites = new ArrayList<>();
        bannedSites.add("abc.com");
        bannedSites.add("def.com");
        bannedSites.add("ijk.com");
        bannedSites.add("lnm.com");
    }

    @Override
    public void connectTo(String serverhost) throws Exception
    {
        // this is proxy, doing something before or after the actual operation
        if(bannedSites.contains(serverhost.toLowerCase()))
        {
            throw new Exception("Access Denied for " + serverhost);
        }

        internet.connectTo(serverhost);
    }
}
