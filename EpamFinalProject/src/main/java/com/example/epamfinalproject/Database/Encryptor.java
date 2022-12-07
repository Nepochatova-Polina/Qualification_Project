package com.example.epamfinalproject.Database;

import com.lambdaworks.crypto.SCryptUtil;

public class Encryptor {
    public static String encrypt(String password) {
        return SCryptUtil.scrypt(password, 16, 16, 16);
    }
    public static boolean check(String hash, String password) {return SCryptUtil.check(password, hash);}
}
