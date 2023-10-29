package de.hf.framework.audit;


import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import static de.hf.framework.audit.LogEvent.Type.CREATE;


@Component
public class PublishLogEventHandler {
    private final StreamBridge streamBridge;

    public PublishLogEventHandler(StreamBridge streamBridge){
        this.streamBridge = streamBridge;
    }

    public void sendPublishLogEvent(String msg, String severity){
        sendMessage("mflog-out-0",
                new LogEvent<>(CREATE, severity, msg));
    }

    private void sendMessage(String bindingName, LogEvent<String, String> event) {
        Message<LogEvent<String, String>> message = MessageBuilder.withPayload(event)
                .setHeader("partitionKey", event.getKey())
                .build();
        streamBridge.send(bindingName, message);
    }
}
