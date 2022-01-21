/** ----------------------------------------------------------------------------
 *
 * ---          HF - Application Development                       ---
 *              Copyright (c) 2014, ... All Rights Reserved
 *
 *
 *  Project     : dac
 *
 *  File        : DACMsgKey.java
 *
 *  Author(s)   : hf
 *
 *  Created     : 05.07.2017
 *
 * ----------------------------------------------------------------------------
 */

package de.hf.framework.exceptions;

public enum BaseMsgKey implements MsgKey {
    // 10000 - 14999: system errors
    SYSTEM(10001),
    NULL_POINTER(10003),
    ILLEGAL_ARGUMENTS(10004),
    INPUT_OUTPUT(10005),
    FILE_NOT_FOUND(10100),
    PROCESS_KILLED_BY_EVENT_EXCEPTION(10105),

    INTERRUPTED_EXCEPTION(10101),

    EXECUTION_EXCEPTION(10102),

    // 15000 - 19999: hibernate, database, sybase
    ENTITYMANAGER_NULL(15000),


    // 22000 - 22999: security
    WRONG_SECURITY_CONFIG(22000),
    UNSPECIFIED(30000);


    private final String globalPrefix = "Framework";

    private final int id;

    BaseMsgKey(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMsgPrefix() {
        if (id != 1) {
            return globalPrefix + "-" + getId() + ": ";
        }
        return globalPrefix + "-" + getId() + ": System error - ";
    }

    public String getName() {
        return toString();
    }
}

