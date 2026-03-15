import com.logging.config.LogConfig;
import com.logging.enums.LogLevel;
import com.logging.factory.LogFactory;
import com.logging.model.LogRecord;
import java.util.Scanner;

/**
 * Demonstração Interativa para apresentação ao professor
 * Sprint 1: M1 (Singleton) + M2 (Factory)
 */
public class DemoInterativa {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        exibirCabecalho();
        
        boolean continuar = true;
        while (continuar) {
            exibirMenu();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    demonstrarSingleton();
                    break;
                case 2:
                    demonstrarFactory();
                    break;
                case 3:
                    testarLog();
                    break;
                case 4:
                    configurarSistema();
                    break;
                case 5:
                    mostrarCodigo();
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\nDemonstracao concluida!");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
            
            if (continuar) {
                pausar();
            }
        }
        
        scanner.close();
    }
    
    private static void exibirCabecalho() {
        System.out.println("\n============================================================");
        System.out.println("     SISTEMA DE LOGS - DEMONSTRACAO SPRINT 1              ");
        System.out.println("     M1: Singleton | M2: Factory                          ");
        System.out.println("============================================================\n");
    }
    
    private static void exibirMenu() {
        System.out.println("\n================= MENU DE DEMONSTRACAO ==================");
        System.out.println("  1. Demonstrar M1 - SINGLETON (LogConfig)");
        System.out.println("  2. Demonstrar M2 - FACTORY (LogFactory)");
        System.out.println("  3. Testar criacao de logs");
        System.out.println("  4. Configurar sistema");
        System.out.println("  5. Mostrar estrutura do codigo");
        System.out.println("  0. Sair");
        System.out.println("=========================================================\n");
        System.out.print("Escolha uma opcao: ");
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void demonstrarSingleton() {
        System.out.println("\n============================================================");
        System.out.println("           M1 - PADRAO SINGLETON (LogConfig)              ");
        System.out.println("============================================================");
        
        System.out.println("\nCaracteristica: Garante uma UNICA instancia da classe\n");
        
        // 1. Obter primeira instancia
        System.out.println("1. Obtendo primeira instancia...");
        LogConfig config1 = LogConfig.getInstance();
        System.out.println("   Instancia 1 criada");
        System.out.println("   Endereco memoria: " + System.identityHashCode(config1));
        
        // 2. Modificar configuracao
        System.out.println("\n2. Modificando configuracao...");
        config1.setMinLogLevel(LogLevel.DEBUG);
        config1.setCustomSetting("teste", "valor_original");
        System.out.println("   Nivel alterado para: " + config1.getMinLogLevel());
        System.out.println("   Setting adicionado: teste=valor_original");
        
        // 3. Obter segunda instancia
        System.out.println("\n3. Obtendo segunda 'instancia'...");
        LogConfig config2 = LogConfig.getInstance();
        System.out.println("   Instancia 2 obtida");
        System.out.println("   Endereco memoria: " + System.identityHashCode(config2));
        
        // 4. Verificar se e a mesma
        System.out.println("\n4. VERIFICACAO DO SINGLETON:");
        System.out.println("   ------------------------------------------------");
        System.out.println("   config1 == config2? " + (config1 == config2));
        System.out.println("   Mesmo endereco?     " + (System.identityHashCode(config1) == System.identityHashCode(config2)));
        System.out.println("   Configuracao?       " + config2.getMinLogLevel());
        System.out.println("   Setting mantido?    " + config2.getCustomSetting("teste"));
        System.out.println("   ------------------------------------------------");
        
        if (config1 == config2) {
            System.out.println("\n   SINGLETON FUNCIONANDO CORRETAMENTE!");
            System.out.println("   Existe apenas UMA instancia em toda aplicacao");
            System.out.println("   Modificacoes sao persistentes");
        }
    }
    
    private static void demonstrarFactory() {
        System.out.println("\n============================================================");
        System.out.println("        M2 - PADRAO FACTORY (LogFactory)                  ");
        System.out.println("============================================================");
        
        System.out.println("\nCaracteristica: Encapsula criacao de objetos\n");
        
        // Criar logs de cada tipo
        System.out.println("1. Criando logs atraves da Factory...\n");
        
        LogRecord info = LogFactory.createInfo("Sistema iniciado com sucesso");
        System.out.println("   INFO criado:");
        System.out.println("      " + info);
        System.out.println("      Classe: " + info.getClass().getSimpleName());
        
        LogRecord warning = LogFactory.createWarning("Memoria em 80% - atencao!");
        System.out.println("\n   WARNING criado:");
        System.out.println("      " + warning);
        System.out.println("      Classe: " + warning.getClass().getSimpleName());
        
        LogRecord error = LogFactory.createError("Falha na conexao com BD");
        System.out.println("\n   ERROR criado:");
        System.out.println("      " + error);
        System.out.println("      Classe: " + error.getClass().getSimpleName());
        
        LogRecord debug = LogFactory.createDebug("Valor da variavel X = 42");
        System.out.println("\n   DEBUG criado:");
        System.out.println("      " + debug);
        System.out.println("      Classe: " + debug.getClass().getSimpleName());
        
        // Mostrar vantagens
        System.out.println("\n2. VANTAGENS DO FACTORY:");
        System.out.println("   ------------------------------------------------");
        System.out.println("   - Cliente nao usa 'new' diretamente");
        System.out.println("   - Logica de criacao encapsulada");
        System.out.println("   - Facil adicionar novos tipos de log");
        System.out.println("   - Cada tipo tem comportamento proprio");
        System.out.println("   ------------------------------------------------");
    }
    
    private static void testarLog() {
        System.out.println("\n============================================================");
        System.out.println("              TESTE DE CRIACAO DE LOGS                    ");
        System.out.println("============================================================\n");
        
        System.out.println("Escolha o tipo de log:");
        System.out.println("  1. INFO");
        System.out.println("  2. WARNING");
        System.out.println("  3. ERROR");
        System.out.println("  4. DEBUG");
        System.out.print("\nTipo: ");
        
        int tipo = lerOpcao();
        System.out.print("Mensagem: ");
        String mensagem = scanner.nextLine();
        
        // Mapear opção para LogLevel
        LogLevel level;
        switch (tipo) {
            case 1:
                level = LogLevel.INFO;
                break;
            case 2:
                level = LogLevel.WARNING;
                break;
            case 3:
                level = LogLevel.ERROR;
                break;
            case 4:
                level = LogLevel.DEBUG;
                break;
            default:
                System.out.println("Tipo invalido!");
                return;
        }
        
        // Usar o metodo principal da Factory
        LogRecord log = LogFactory.createLogRecord(level, mensagem);
        
        System.out.println("\nLog criado:");
        System.out.println("   " + log);
        System.out.println("   Nivel: " + log.getLevel());
        System.out.println("   Classe concreta: " + log.getClass().getSimpleName());
        System.out.println("   Timestamp: " + log.getFormattedTimestamp());
        System.out.println("   Source: " + log.getSource());
    }
    
    private static void configurarSistema() {
        System.out.println("\n============================================================");
        System.out.println("           CONFIGURACAO DO SISTEMA (Singleton)            ");
        System.out.println("============================================================\n");
        
        LogConfig config = LogConfig.getInstance();
        config.displayConfig();
        
        System.out.println("\nDeseja modificar? (s/n): ");
        String resposta = scanner.nextLine();
        
        if (resposta.equalsIgnoreCase("s")) {
            System.out.println("\nNivel minimo (1=DEBUG, 2=INFO, 3=WARNING, 4=ERROR): ");
            int nivel = lerOpcao();
            switch (nivel) {
                case 1: config.setMinLogLevel(LogLevel.DEBUG); break;
                case 2: config.setMinLogLevel(LogLevel.INFO); break;
                case 3: config.setMinLogLevel(LogLevel.WARNING); break;
                case 4: config.setMinLogLevel(LogLevel.ERROR); break;
            }
            
            System.out.print("Destino (console/file/database): ");
            String destino = scanner.nextLine();
            config.setOutputDestination(destino);
            
            System.out.println("\nConfiguracao atualizada:");
            config.displayConfig();
        }
    }
    
    private static void mostrarCodigo() {
        System.out.println("\n============================================================");
        System.out.println("              ESTRUTURA DO CODIGO                         ");
        System.out.println("============================================================\n");
        
        System.out.println("com.logging");
        System.out.println("  |-- config");
        System.out.println("  |   |-- LogConfig.java          (M1 - Singleton)");
        System.out.println("  |-- factory");
        System.out.println("  |   |-- LogFactory.java         (M2 - Factory/Creator)");
        System.out.println("  |-- model");
        System.out.println("  |   |-- LogRecord.java          (M2 - Product)");
        System.out.println("  |   |-- InfoLogRecord.java      (M2 - ConcreteProduct)");
        System.out.println("  |   |-- WarningLogRecord.java   (M2 - ConcreteProduct)");
        System.out.println("  |   |-- ErrorLogRecord.java     (M2 - ConcreteProduct)");
        System.out.println("  |   |-- DebugLogRecord.java     (M2 - ConcreteProduct)");
        System.out.println("  |-- enums");
        System.out.println("      |-- LogLevel.java           (Enum auxiliar)");
        
        System.out.println("\nPADROES IMPLEMENTADOS:");
        System.out.println("  Singleton: 1 classe  (LogConfig)");
        System.out.println("  Factory:   6 classes (1 Factory + 1 Product + 4 ConcreteProducts)");
        System.out.println("\n  TOTAL: 8 classes Java");
    }
    
    private static void pausar() {
        System.out.print("\n[Pressione ENTER para continuar]");
        scanner.nextLine();
        limparTela();
    }
    
    private static void limparTela() {
        // Windows
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // Se falhar, apenas imprime linhas vazias
            for (int i = 0; i < 3; i++) {
                System.out.println("\n");
            }
        }
    }
}
