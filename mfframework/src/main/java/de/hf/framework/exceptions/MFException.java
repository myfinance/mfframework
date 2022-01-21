/** ----------------------------------------------------------------------------
 *
 * ---          HF - Application Development                       ---
 *              Copyright (c) 2014, ... All Rights Reserved
 *
 *
 *  Project     : mfframework
 *
 *  File        : MFException.java
 *
 *  Author(s)   : hf
 *
 *  Created     : 05.07.2017
 *
 * ----------------------------------------------------------------------------
 */

package de.hf.framework.exceptions;


/** exception for MF in the businesslogic, it is a runtime exception to avoid having throw statement in every method. */
public class MFException extends RuntimeException {
    /**  **/
    private static final long serialVersionUID = 1L;

    /** the message key associated with the exception */
    protected  MsgKey msgKey;


    public MFException(MsgKey msgKey, String message, Throwable e) {
        super(msgKey.getId() + ": " + message , e);
        this.msgKey = msgKey;
    }

    public MFException(MsgKey msgKey, String message) {
        super(msgKey.getId() + ": " + message);
        this.msgKey = msgKey;
    }

    /**
     * Get the message key.
     *
     * @return the message key
     */
    public MsgKey getMsgKey() {
        return msgKey;
    }

    /**
     * Get the id of the message key.
     *
     * @return the message key's id
     */
    public int getMsgKeyId() {
        return msgKey.getId();
    }

    /**
     * Get the message prefixed with the message key's message prefix.
     *
     * @return the prefixed message
     */
    public String getPrefixedMsg() {
        return msgKey.getMsgPrefix() + super.getMessage();
    }

}

