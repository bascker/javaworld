package com.bascker.restlet.auth;

import org.apache.commons.lang3.StringUtils;
import org.restlet.security.SecretVerifier;

import java.util.Objects;

/**
 * 自定义校验器
 *
 * @author bascker
 */
public class AuthSecretVerifier extends SecretVerifier {

    @Override
    public int verify(final String identifier, final char[] secret) {
        System.out.println(String.format("{username: %s, password: %s}",
                identifier, String.valueOf(secret)));

        // 校验规则
        if (Objects.isNull(identifier) || StringUtils.isEmpty(identifier)) {
            return SecretVerifier.RESULT_INVALID;
        } else if ("admin".equals(identifier)
                && "admin".equals(String.valueOf(secret))) {
            return SecretVerifier.RESULT_VALID;
        } else {
            return SecretVerifier.RESULT_INVALID;
        }
    }

}
