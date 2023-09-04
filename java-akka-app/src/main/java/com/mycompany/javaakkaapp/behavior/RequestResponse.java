package com.mycompany.javaakkaapp.behavior;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import com.mycompany.javaakkaapp.dto.RequestMessage;
import com.mycompany.javaakkaapp.dto.ResponseMessage;

public class RequestResponse extends AbstractBehavior<RequestMessage> {
    @Override
    public Receive<RequestMessage> createReceive() {
        return newReceiveBuilder()
                .onMessage(RequestMessage.class, (request) -> {
                    System.out.println("[Request Response] :"+request.getMessage());
                    request.getReplyTo().tell(new ResponseMessage("Responded With: "+request.getMessage()));
                    return this;
                })
                .build();
    }

    public RequestResponse(ActorContext<RequestMessage> context) {
        super(context);
    }

    public static Behavior<RequestMessage> create() {
        return Behaviors.setup(RequestResponse::new);
    }


}
