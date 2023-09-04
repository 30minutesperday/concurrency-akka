package com.mycompany.javaakkaapp.dto;

import akka.actor.Actor;
import akka.actor.typed.ActorRef;
import lombok.Data;

@Data
public class RequestMessage {

    private final String message;
    private final ActorRef<ResponseMessage> replyTo;

    public RequestMessage(String message, ActorRef<ResponseMessage> replyTo) {
        this.message = message;
        this.replyTo = replyTo;
    }
}
