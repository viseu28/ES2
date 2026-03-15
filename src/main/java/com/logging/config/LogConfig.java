package com.logging.config;

import com.logging.enums.LogLevel;
import java.util.HashMap;
import java.util.Map;

/**
 * M1 - Padrão Singleton
 * 
 * Classe responsável por armazenar as configurações globais do sistema de logs.
 * Garante um ponto de acesso único às configurações, evitando múltiplas instâncias
 * inconsistentes ao longo da aplicação.
 * 
 * Papel no padrão: Singleton
 */
public class LogConfig {
    
    // Instância única (Singleton)
    private static LogConfig instance;
    
    // Configurações do sistema
    private LogLevel minLogLevel;
    private String outputDestination;
    private String messageFormat;
    private Map<String, String> customSettings;
    private boolean timestampEnabled;
    
    /**
     * Construtor privado para prevenir instanciação externa
     */
    private LogConfig() {
        // Configurações padrão
        this.minLogLevel = LogLevel.INFO;
        this.outputDestination = "console";
        this.messageFormat = "[{timestamp}] [{level}] {message}";
        this.customSettings = new HashMap<>();
        this.timestampEnabled = true;
    }
    
    /**
     * Método estático que retorna a única instância da classe (ponto de acesso global)
     * Thread-safe usando sincronização
     * 
     * @return instância única de LogConfig
     */
    public static synchronized LogConfig getInstance() {
        if (instance == null) {
            instance = new LogConfig();
        }
        return instance;
    }
    
    // Getters e Setters
    
    public LogLevel getMinLogLevel() {
        return minLogLevel;
    }
    
    public void setMinLogLevel(LogLevel minLogLevel) {
        this.minLogLevel = minLogLevel;
    }
    
    public String getOutputDestination() {
        return outputDestination;
    }
    
    public void setOutputDestination(String outputDestination) {
        this.outputDestination = outputDestination;
    }
    
    public String getMessageFormat() {
        return messageFormat;
    }
    
    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }
    
    public boolean isTimestampEnabled() {
        return timestampEnabled;
    }
    
    public void setTimestampEnabled(boolean timestampEnabled) {
        this.timestampEnabled = timestampEnabled;
    }
    
    public void setCustomSetting(String key, String value) {
        customSettings.put(key, value);
    }
    
    public String getCustomSetting(String key) {
        return customSettings.get(key);
    }
    
    public Map<String, String> getAllCustomSettings() {
        return new HashMap<>(customSettings);
    }
    
    /**
     * Exibe as configurações atuais
     */
    public void displayConfig() {
        System.out.println("=== Configuração do Sistema de Logs ===");
        System.out.println("Nível mínimo: " + minLogLevel);
        System.out.println("Destino: " + outputDestination);
        System.out.println("Formato: " + messageFormat);
        System.out.println("Timestamp ativado: " + timestampEnabled);
        if (!customSettings.isEmpty()) {
            System.out.println("Configurações customizadas: " + customSettings);
        }
        System.out.println("======================================");
    }
}
