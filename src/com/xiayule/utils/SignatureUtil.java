package com.xiayule.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Logger;

public class SignatureUtil {
    private static Logger log = Logger.getLogger(SignatureUtil.class.getName());

    private static final String token = "xiayule";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        if (signature == null || timestamp == null || nonce == null) return false;

        log.info(signature + ":" + timestamp + ":" + nonce);

        String[] str = new String[] {token, timestamp, nonce};

        Arrays.sort(str);

        String inStr = str[0] + str[1] + str[2];

        String outStr = SHA1(inStr);

        if (outStr.equals(signature.toLowerCase()))
            return true;
        else
            return false;
    }

    public static String SHA1(String inStr) {
        String outStr = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(inStr.getBytes());
            outStr = byteToString(digest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return outStr;
    }

    public static String byteToString(byte[] digest) {
        String str = "";
        String tempStr = "";

        for (int i = 0; i < digest.length; i++) {
            tempStr = (Integer.toHexString(digest[i] & 0xff));
            if (tempStr.length() == 1) {
                str = str + "0" + tempStr;
            }
            else {
                str = str + tempStr;
            }
        }
        return str.toLowerCase();
    }
}
