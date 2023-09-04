package com.mycompany.javaakkaapp.behavior;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class FireAndForget extends AbstractBehavior<String> {

    public FireAndForget(ActorContext<String> context) {
        super(context);
    }

    public static Behavior<String> create() {
        return Behaviors.setup(FireAndForget::new);
    }
    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onAnyMessage(message -> {
                    System.out.println("Received: "+message);
                    return this;
                })
                .build();
    }
}
