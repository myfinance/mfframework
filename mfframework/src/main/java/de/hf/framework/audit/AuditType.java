package de.hf.framework.audit;

public enum AuditType {
    USERINFO, // informations for the user like instrument saved
    INSTRUMENTEVENT,    // A Pushevent via Websocket to trigger Frontendupdates
    TRANSACTIONEVENT,   // A Pushevent via Websocket to trigger Frontendupdates
    ERROR,  // A business error
    FATAL, // A fatal error in the Backend e.G:server crash
    DEBUG, //not published via Websocket only logged to the console

}
