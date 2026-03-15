package com.logging.model;

import com.logging.enums.LogLevel;

/**
 * M2 - Padrão Factory
 * 
 * Implementação concreta de LogRecord para logs de nível ERROR.
 * Usado para registar erros que ocorreram na aplicação.
 * 
 * Papel no padrão: ConcreteProduct
 */
public class ErrorLogRecord extends LogRecord {
    
    private Exception exception;
    
    public ErrorLogRecord(String message) {
        super(LogLevel.ERROR, message);
    }
    
    public ErrorLogRecord(String message, Exception exception) {
        super(LogLevel.ERROR, message);
        this.exception = exception;
    }
    
    @Override
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s] [ERROR] %s - Source: %s",
            getFormattedTimestamp(),
            message,
            source
        ));
        
        if (exception != null) {
            sb.append("\n  Exception: ").append(exception.getClass().getSimpleName());
            sb.append(" - ").append(exception.getMessage());
        }
        
        return sb.toString();
    }
    
    public Exception getException() {
        return exception;
    }
}
