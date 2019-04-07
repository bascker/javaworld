package com.bascker.restlet.auth;

import org.junit.Test;
import org.restlet.data.ChallengeScheme;
import org.restlet.resource.ClientResource;

import java.io.IOException;

/**
 * Auth Unit Test
 *
 * @author bascker
 */
public class AuthTest {

    @Test
    public void testAuth() throws IOException {
        final ClientResource res = new ClientResource("http://localhost/auth");
        res.setChallengeResponse(ChallengeScheme.HTTP_BASIC, "admin", "admin");
        res.get().write(System.out);
    }

}
