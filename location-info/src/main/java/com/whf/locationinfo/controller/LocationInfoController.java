package com.whf.locationinfo.controller;

import com.whf.locationinfo.utils.IpUtils;

/**
 * @author whf
 * @date 2022/8/30
 */
public class LocationInfoController {

    public static String locationInfo() throws Exception {
        //国内ip
        String ip1 = "220.248.12.158";

        String cityInfo1 = IpUtils.getCityInfo(ip1);
        System.out.println(cityInfo1);
        String address1 = IpUtils.getIpPossession(ip1);
        System.out.println(address1);
        return "111";
    }

    public static void main(String[] args) {
        try {
            locationInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
