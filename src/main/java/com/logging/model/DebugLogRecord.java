package com.logging.model;

import com.logging.enums.LogLevel;

/**
 * M2 - Padrão Factory
 * 
 * Implementação concreta de LogRecord para logs de nível DEBUG.
 * Usado para registar informações detalhadas úteis durante o desenvolvimento.
 * 
 * Papel no padrão: ConcreteProduct
 */
public class DebugLogRecord extends LogRecord {
    
    public DebugLogRecord(String message) {
        super(LogLevel.DEBUG, message);
    }
    
    @Override
    public String format() {
        return String.format("[%s] [DEBUG] %s - Source: %s",
            getFormattedTimestamp(),
            message,
            source
        );
    }
}
