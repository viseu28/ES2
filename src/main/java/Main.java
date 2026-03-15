import com.logging.config.LogConfig;
import com.logging.enums.LogLevel;
import com.logging.factory.LogFactory;
import com.logging.model.LogRecord;

/**
 * Classe principal para demonstração do sistema de logs
 * Sprint 1: M1 (Singleton) + M2 (Factory)
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("Sistema de Logs - Sprint 1 Demonstração");
        System.out.println("========================================\n");
        
        // ============================================
        // DEMONSTRAÇÃO M1 - SINGLETON (LogConfig)
        // ============================================
        System.out.println("--- M1: Padrão Singleton ---\n");
        
        // Obter a instância única da configuração
        LogConfig config = LogConfig.getInstance();
        System.out.println("1. Configuração obtida (primeira vez)");
        config.displayConfig();
        
        // Modificar configurações
        System.out.println("\n2. Modificando configurações...");
        config.setMinLogLevel(LogLevel.DEBUG);
        config.setOutputDestination("file");
        config.setMessageFormat("[{level}] {message}");
        config.setCustomSetting("max_file_size", "10MB");
        
        // Obter novamente a instância (deve ser a mesma)
        LogConfig config2 = LogConfig.getInstance();
        System.out.println("\n3. Configuração obtida novamente (mesma instância)");
        config2.displayConfig();
        
        // Verificar se são a mesma instância
        System.out.println("\n4. Verificação: config == config2? " + (config == config2));
        System.out.println("   Endereço config:  " + System.identityHashCode(config));
        System.out.println("   Endereço config2: " + System.identityHashCode(config2));
        
        
        // ============================================
        // DEMONSTRAÇÃO M2 - FACTORY (LogFactory)
        // ============================================
        System.out.println("\n\n--- M2: Padrão Factory ---\n");
        
        System.out.println("Criando diferentes tipos de logs através da Factory:\n");
        
        // Usando o método genérico com LogLevel
        LogRecord log1 = LogFactory.createLogRecord(LogLevel.INFO, 
            "Aplicação iniciada com sucesso");
        System.out.println(log1);
        
        LogRecord log2 = LogFactory.createLogRecord(LogLevel.DEBUG, 
            "Carregando configurações do ficheiro config.properties");
        System.out.println(log2);
        
        LogRecord log3 = LogFactory.createLogRecord(LogLevel.WARNING, 
            "Memória em uso: 85% - Considere otimização");
        System.out.println(log3);
        
        // Usando métodos de conveniência
        LogRecord log4 = LogFactory.createError("Falha ao conectar à base de dados");
        System.out.println(log4);
        
        // Criando log de erro com exceção
        try {
            int result = 10 / 0;  // Simular erro
        } catch (Exception e) {
            LogRecord log5 = LogFactory.createError(
                "Erro ao processar cálculo", e);
            System.out.println(log5);
        }
        
        // Mais exemplos
        LogRecord log6 = LogFactory.createInfo("Utilizador 'admin' autenticado");
        System.out.println(log6);
        
        LogRecord log7 = LogFactory.createDebug("Query SQL: SELECT * FROM users WHERE id=123");
        System.out.println(log7);
        
        
        // ============================================
        // DEMONSTRAÇÃO INTEGRADA (M1 + M2)
        // ============================================
        System.out.println("\n\n--- Integração M1 + M2 ---\n");
        
        System.out.println("Simulando uso real do sistema:\n");
        
        // Simular diferentes cenários de uso
        simulateUserAuthentication();
        simulateDatabaseOperation();
        simulateNetworkRequest();
        
        System.out.println("\n========================================");
        System.out.println("Demonstração concluída!");
        System.out.println("========================================");
    }
    
    /**
     * Simula processo de autenticação
     */
    private static void simulateUserAuthentication() {
        LogRecord log = LogFactory.createInfo("Tentativa de autenticação do utilizador 'joao.silva'");
        System.out.println(log);
        
        // Simular validação
        try {
            Thread.sleep(100);  // Simular processamento
            LogRecord success = LogFactory.createInfo("Utilizador 'joao.silva' autenticado com sucesso");
            System.out.println(success);
        } catch (InterruptedException e) {
            LogRecord error = LogFactory.createError("Erro durante autenticação", e);
            System.out.println(error);
        }
    }
    
    /**
     * Simula operação de base de dados
     */
    private static void simulateDatabaseOperation() {
        LogRecord debug = LogFactory.createDebug("Conectando à base de dados...");
        System.out.println(debug);
        
        LogRecord warning = LogFactory.createWarning(
            "Conexão lenta - Tempo de resposta: 3.5s (limite: 2s)");
        System.out.println(warning);
    }
    
    /**
     * Simula requisição de rede
     */
    private static void simulateNetworkRequest() {
        LogRecord info = LogFactory.createInfo("Enviando requisição para API externa");
        System.out.println(info);
        
        LogRecord error = LogFactory.createError("Timeout ao contactar API - host não responde");
        System.out.println(error);
    }
}
