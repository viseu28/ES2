package com.logging.model;

import com.logging.enums.LogLevel;

/**
 * M2 - Padrão Factory
 * 
 * Implementação concreta de LogRecord para logs de nível INFO.
 * Usado para registar informações gerais sobre o funcionamento da aplicação.
 * 
 * Papel no padrão: ConcreteProduct
 */
public class InfoLogRecord extends LogRecord {
    
    public InfoLogRecord(String message) {
        super(LogLevel.INFO, message);
    }
    
    @Override
    public String format() {
        return String.format("[%s] [INFO] %s - Source: %s",
            getFormattedTimestamp(),
            message,
            source
        );
    }
}
