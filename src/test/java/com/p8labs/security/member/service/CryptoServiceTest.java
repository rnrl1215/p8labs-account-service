package com.p8labs.security.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class CryptoServiceTest {

    @Test
    void 사용자_비밀번호_엄호화_테스트() {
        String password1 = "passowrd1";
        String hashByBCrypt = CryptoService.createHashByBCrypt(password1);
    }
}