package com.bascker.restlet.mail.resource;

import org.restlet.resource.Get;
import org.restlet.resource.Options;

/**
 * RootResource
 *
 * @author bascker
 */
public interface RootResource {

    @Get
    String represent();

    @Options
    String describe();

}
