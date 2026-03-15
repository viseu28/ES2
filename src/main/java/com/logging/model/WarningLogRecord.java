package com.logging.model;

import com.logging.enums.LogLevel;

/**
 * M2 - Padrão Factory
 * 
 * Implementação concreta de LogRecord para logs de nível WARNING.
 * Usado para registar avisos sobre situações que merecem atenção.
 * 
 * Papel no padrão: ConcreteProduct
 */
public class WarningLogRecord extends LogRecord {
    
    public WarningLogRecord(String message) {
        super(LogLevel.WARNING, message);
    }
    
    @Override
    public String format() {
        return String.format("[%s] [WARNING] %s - Source: %s",
            getFormattedTimestamp(),
            message,
            source
        );
    }
}
