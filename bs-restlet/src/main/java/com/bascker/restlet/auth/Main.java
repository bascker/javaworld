package com.bascker.restlet.auth;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * 入口
 *
 * @author bascker
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final Component component = new Component();
        component.getServers().add(Protocol.HTTP);
        component.getDefaultHost().attach(new AuthApplication());
        component.start();
    }

}
