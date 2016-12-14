package com.upwork.agora.helloDS.command;

import javax.annotation.Generated;

import com.google.inject.assistedinject.Assisted;

import com.odesk.agora.thrift.hello.THello;

@Generated(value = "com.upwork.agora.swagger.codegen.AgoraV2ClientGenerator", date = "2016-12-14T16:40:42.757+07:00")
public interface HelloDSHysCmdFactory {

    AddHelloHysCmd createAddHelloHysCmd(@Assisted("tHello") THello tHello);

    GetHelloHysCmd createGetHelloHysCmd(@Assisted("id") String id);

    UpdateHelloHysCmd createUpdateHelloHysCmd(@Assisted("id") String id, @Assisted("tHello") THello tHello);

}
