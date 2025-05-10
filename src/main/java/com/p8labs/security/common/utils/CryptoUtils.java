package com.p8labs.security.common.utils;

import com.p8labs.security.common.dto.UserPasswordDto;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class CryptoUtils {
    public static String createHashByBCrypt(String text) {
        return BCrypt.hashpw(text, BCrypt.gensalt());
   }

    public static String createHashByBCryptWithSalt(String text, String salt) {
        return BCrypt.hashpw(text, salt);
    }

    public static UserPasswordDto getPasswordInfo(String password) {
        String saltStr = password.substring(0, 29);
        String passwordStr = password.substring(29);
        return new UserPasswordDto(saltStr, passwordStr);
    }
}
