/** ----------------------------------------------------------------------------
 *
 * ---          HF - Application Development                       ---
 *              Copyright (c) 2014, ... All Rights Reserved
 *
 *
 *  Project     : dac
 *
 *  File        : MFMsgKey.java
 *
 *  Author(s)   : hf
 *
 *  Created     : 05.07.2017
 *
 * ----------------------------------------------------------------------------
 */

package de.hf.myfinance.exception;

import de.hf.framework.exceptions.MsgKey;

/** Message key to be part of MFException providing a unique id and a unique type (message type) */
public enum MFMsgKey implements MsgKey {
    // 10000 - 14999: system errors
    SYSTEM(10001),
    NULL_POINTER(10003),
    ILLEGAL_ARGUMENTS(10004),
    INPUT_OUTPUT(10005),
    FILE_NOT_FOUND(10100),
    INTERRUPTED_EXCEPTION(10101),
    EXECUTION_EXCEPTION(10102),
    // 15000 - 19999: hibernate, database, sybase
    NO_TARGET_CONFIG_EXCEPTION(15001),
    UNABLE_TO_CREATE_ENTITYMANAGER_EXCEPTION(15002),
    // 20000 - 20999: memory, caching, couchbase
    // 21000 - 24999: imports and calculation
    NO_RESPONSE_FROM_URL_EXCEPTION(21001),
    UNKNOWN_SOURCE_EXCEPTION(21002),
    UNKNOWN_INSTRUMENTTYPE_EXCEPTION(21003),
    UNKNOWN_SECURITYTYPE_EXCEPTION(21004),
    WRONG_INSTRUMENTTYPE_EXCEPTION(21005),
    UNKNOWN_INSTRUMENT_EXCEPTION(21006),
    WRONG_TENENT_EXCEPTION(21007),
    UNKNOWN_CURRENCY_EXCEPTION(21008),
    WRONG_REQUEST_EXCEPTION(21009),
    UNKNOWN_PARENT_EXCEPTION(21010),
    NO_VALID_INSTRUMENT_FOR_DEACTIVATION(21011),
    UNKNOWN_TRANSACTION_EXCEPTION(21012),
    NO_INCOMEBUDGET_DEFINED_EXCEPTION(21013),
    ANCESTOR_DOES_NOT_EXIST_EXCEPTION(21014),
    WRONG_TRNSACTIONTYPE_EXCEPTION(21015),
    UNKNOWN_TRNSACTIONTYPE_EXCEPTION(21016),    
    WRONG_OPERATION_EXCEPTION(21017), 
    OBJECT_NOT_INITIALIZED_EXCEPTION(21018), 
    NO_VALID_INSTRUMENT(21019),
    // 25000 - 29999: analysis
    NO_INSTRUMENT_FOUND_EXCEPTION(25001),

    //this number is fix and must not be changed
    UNSPECIFIED(30000);

    private final String globalPrefix = "MF";

    private final int id;

    MFMsgKey(int id) {
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
