package com.p8labs.security.member.service;

import com.p8labs.security.common.utils.CryptoUtils;
import org.junit.jupiter.api.Test;

class CryptoUtilsTest {

    @Test
    void 사용자_비밀번호_엄호화_테스트() {
        String password1 = "passowrd1";
        String hashByBCrypt = CryptoUtils.createHashByBCrypt(password1);
    }
}