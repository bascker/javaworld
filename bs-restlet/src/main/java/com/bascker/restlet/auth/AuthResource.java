package com.bascker.restlet.auth;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * 认证
 *
 * @author bascker
 */
public class AuthResource extends ServerResource {

    /**
     * GET /auth
     */
    @Get("txt")
    public String auth() {
        return "认证成功";
    }

    /**
     * POST /auth/login
     *
     * @param entity
     */
    @Post
    public void login(final JsonRepresentation entity) {
        final JSONObject object = entity.getJsonObject();
        System.out.println("name:" + object.get("name"));
    }

}