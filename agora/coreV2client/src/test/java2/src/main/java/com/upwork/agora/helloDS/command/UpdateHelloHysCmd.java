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

@Generated(value = "com.upwork.agora.swagger.codegen.AgoraV2ClientGenerator", date = "2016-12-14T16:40:42.757+07:00")

public class UpdateHelloHysCmd extends GenericWsDependencyHystrixCommand<Void> {
    private final String id;
    private final THello tHello;

    @Inject
    protected UpdateHelloHysCmd(@Assisted("id") String id, @Assisted("tHello") THello tHello) {
        super("<serviceName>", "<commandIdentifier>");
        this.id = id;
        this.tHello = tHello;
    }

    protected UpdateHelloHysCmd(String id, THello tHello) {
        super("<serviceName>", "<commandIdentifier>");
        this.id = id;
        this.tHello = tHello;
    }

    @Override
    public boolean validateInput() {
        
        if (id == null) {
            return false;
        }
        
        if (tHello == null) {
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
        return invocationBuilder.put(Entity.entity(tHello, "application/x-thrift+json"));
    }

    @Override
    public Void getResultFromSuccessfulResponse(Response response) {
        return response.readEntity(Void.class);
    }
}
