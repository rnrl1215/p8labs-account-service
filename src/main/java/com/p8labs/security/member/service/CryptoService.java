package com.p8labs.security.member.service;

import com.p8labs.security.common.dto.UserPasswordDto;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {
    public static String createHashByBCrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
   }

    public static String createHashByBCryptWithSalt(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static UserPasswordDto getPasswordInfo(String password) {
        String saltStr = password.substring(0, 29);
        String passwordStr = password.substring(29);
        return new UserPasswordDto(saltStr, passwordStr);
    }
}
