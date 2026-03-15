package com.logging.factory;

import com.logging.enums.LogLevel;
import com.logging.model.*;

/**
 * M2 - Padrão Factory
 * 
 * Factory responsável pela criação de diferentes tipos de registos de log.
 * Encapsula a lógica de criação dos objetos LogRecord, permitindo a adição
 * futura de novos tipos sem alterar o código existente.
 * 
 * Papel no padrão: Factory (Creator)
 */
public class LogFactory {
    
    /**
     * Cria um registo de log baseado no nível especificado
     * 
     * @param level nível do log (INFO, WARNING, ERROR, DEBUG)
     * @param message mensagem do log
     * @return instância apropriada de LogRecord
     * @throws IllegalArgumentException se o nível for nulo
     */
    public static LogRecord createLogRecord(LogLevel level, String message) {
        if (level == null) {
            throw new IllegalArgumentException("Log level cannot be null");
        }
        
        switch (level) {
            case INFO:
                return new InfoLogRecord(message);
            case WARNING:
                return new WarningLogRecord(message);
            case ERROR:
                return new ErrorLogRecord(message);
            case DEBUG:
                return new DebugLogRecord(message);
            default:
                throw new IllegalArgumentException("Unknown log level: " + level);
        }
    }
    
    /**
     * Cria um registo de log de erro com exceção associada
     * 
     * @param message mensagem do log
     * @param exception exceção capturada
     * @return instância de ErrorLogRecord
     */
    public static LogRecord createErrorLog(String message, Exception exception) {
        return new ErrorLogRecord(message, exception);
    }
    
    /**
     * Métodos de conveniência para criação rápida de logs
     */
    
    public static LogRecord createInfo(String message) {
        return new InfoLogRecord(message);
    }
    
    public static LogRecord createWarning(String message) {
        return new WarningLogRecord(message);
    }
    
    public static LogRecord createError(String message) {
        return new ErrorLogRecord(message);
    }
    
    public static LogRecord createError(String message, Exception exception) {
        return new ErrorLogRecord(message, exception);
    }
    
    public static LogRecord createDebug(String message) {
        return new DebugLogRecord(message);
    }
}
