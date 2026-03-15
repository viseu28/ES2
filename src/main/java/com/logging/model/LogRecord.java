package com.logging.model;

import com.logging.enums.LogLevel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * M2 - Padrão Factory
 * 
 * Classe abstrata que representa um registo de log.
 * Define a estrutura comum para todos os tipos de logs.
 * 
 * Papel no padrão: Product (produto abstrato)
 */
public abstract class LogRecord {
    
    protected LogLevel level;
    protected String message;
    protected LocalDateTime timestamp;
    protected String source;
    
    private static final DateTimeFormatter formatter = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public LogRecord(LogLevel level, String message) {
        this.level = level;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.source = detectSource();
    }
    
    /**
     * Método abstrato que cada tipo de log deve implementar
     * para formatar sua mensagem de forma específica
     */
    public abstract String format();
    
    /**
     * Detecta a origem do log (classe e método que o criou)
     */
    private String detectSource() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Pula os primeiros elementos (getStackTrace, detectSource, constructor)
        if (stackTrace.length > 4) {
            StackTraceElement element = stackTrace[4];
            return element.getClassName() + "." + element.getMethodName();
        }
        return "Unknown";
    }
    
    // Getters
    
    public LogLevel getLevel() {
        return level;
    }
    
    public String getMessage() {
        return message;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getFormattedTimestamp() {
        return timestamp.format(formatter);
    }
    
    public String getSource() {
        return source;
    }
    
    @Override
    public String toString() {
        return format();
    }
}
