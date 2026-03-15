package com.logging.enums;

/**
 * Enum que define os níveis de log disponíveis no sistema
 */
public enum LogLevel {
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");
    
    private final String level;
    
    LogLevel(String level) {
        this.level = level;
    }
    
    public String getLevel() {
        return level;
    }
    
    @Override
    public String toString() {
        return level;
    }
}
