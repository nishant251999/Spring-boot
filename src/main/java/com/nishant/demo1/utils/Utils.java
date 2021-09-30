package com.nishant.demo1.utils;

import java.util.regex.Pattern;

public class Utils {
    public static Boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+91-[5-9][0-9]{9}$";
        if (Pattern.matches(regex, phoneNumber)) {
            return true;
        }
        return false;
    }

    public static Boolean isValidPincode(String pincode) {
        String regex = "^[1-9][0-9]{5}$";
        if (Pattern.matches(regex, pincode)) {
            return true;
        }
        return false;
    }
}
