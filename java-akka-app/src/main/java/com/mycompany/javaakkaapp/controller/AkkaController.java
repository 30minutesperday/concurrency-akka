package com.mycompany.javaakkaapp.controller;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Props;
import akka.actor.typed.javadsl.AskPattern;
import akka.actor.typed.javadsl.Behaviors;
import com.mycompany.javaakkaapp.behavior.FireAndForget;
import com.mycompany.javaakkaapp.behavior.RequestResponse;
import com.mycompany.javaakkaapp.dto.RequestMessage;
import com.mycompany.javaakkaapp.dto.ResponseMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

@RestController
public class AkkaController {

    @GetMapping("/fireandforget")
    public void fireandforget(@RequestParam String message) {
        ActorSystem<String> system = ActorSystem.create(FireAndForget.create(),
                "fireAndForget");
        system.tell(message);
    }

    @GetMapping("/requestresponse")
    public CompletionStage<ResponseMessage> requestresponse(@RequestParam String message) {

        ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "RequestResponse");

        ActorRef<RequestMessage> requestHandler = system.systemActorOf(RequestResponse.create(),
                "requestHandler", Props.empty());

        CompletionStage<ResponseMessage> response = AskPattern.ask(requestHandler, (replyTo)->
                new RequestMessage(message, replyTo),
                Duration.ofMillis(500), system.scheduler());

        return response;
    }


}
