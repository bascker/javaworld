package com.bascker.restlet.filter;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.MediaType;

/**
 * Restlet 案例(二) Filter 的使用
 *
 * @author bascker
 */
public class UrlResource extends Restlet {

    public UrlResource(final Context context) {
        super(context);
    }

    @Override
    public void handle(final Request request, final Response response) {
        response.setEntity("Congratulation, You passed the filter"
                + "\nmethod = " + request.getMethod()
                + "\nresource uri =  " + request.getResourceRef()
                + "\nip address = " + request.getClientInfo().getAddress()
                + "\nagent{name: " + request.getClientInfo().getAgentName()
                + ",version: " + request.getClientInfo().getAgentVersion() + "}", MediaType.TEXT_PLAIN);
    }
}
