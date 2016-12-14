package com.upwork.agora.helloDS.command;

import javax.annotation.Generated;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.odesk.agora.hystrix.GenericWsDependencyHystrixCommand;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import java.text.MessageFormat;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.odesk.agora.exception.HystrixClientErrorException;

import com.odesk.agora.thrift.hello.THello;

@Generated(value = "com.upwork.agora.swagger.codegen.AgoraV2ClientGenerator", date = "2016-12-14T17:50:33.251+07:00")

public class GetHelloHysCmd extends GenericWsDependencyHystrixCommand<THello> {
    private final String id;

    @Inject
    protected GetHelloHysCmd(@Assisted("id") String id) {
        super("helloDS", "getHello");
        this.id = id;
    }

    protected GetHelloHysCmd(String id) {
        super("helloDS", "getHello");
        this.id = id;
    }

    @Override
    public boolean validateInput() {
        
        if (id == null) {
            return false;
        }
        
        return true;
    }

    @Override
    protected void specifyUri(UriBuilder uriBuilder) {
        uriBuilder.path("/hellos/{id}"
            .replaceAll("\\{" + "id" + "\\}", id.toString()));
    }

    @Override
    protected Response invoke(Invocation.Builder invocationBuilder) {
        return invocationBuilder.get();
    }

    @Override
    public THello getResultFromSuccessfulResponse(Response response) {
        return response.readEntity(THello.class);
    }
}
