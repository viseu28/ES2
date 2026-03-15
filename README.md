# Sistema de Logs - Engenharia de Software 2

## 📋 Descrição do Projeto

Sistema de registo de logs para monitorização de aplicações, desenvolvido aplicando **padrões de desenho de software** (Design Patterns). O objetivo é criar uma solução flexível, extensível e eficiente.

## 🎯 Padrões Implementados

| Sprint | Módulo | Padrão | Estado |
|--------|--------|--------|--------|
| 1 | M1 - Configuração Centralizada | **Singleton** | ✅ Completo |
| 1 | M2 - Criação de Registos | **Factory** | ✅ Completo |
| 2 | M3 - Abstração de Destino | **Bridge** | 🔄 Pendente |
| 3 | M4 - Estruturação de Registos | **Composite** | 🔄 Pendente |
| 4 | M5 - Otimização de Recursos | **Object Pool** | 🔄 Pendente |
| 5 | M6 - Armazenamento de Estado | **Memento** | 🔄 Pendente |
| 6 | M7 - Extensão de Funcionalidades | **Decorator** | 🔄 Pendente |

## 📁 Estrutura do Projeto

```
ES2/
├── src/
│   └── main/
│       └── java/
│           ├── com/
│           │   └── logging/
│           │       ├── config/
│           │       │   └── LogConfig.java          (M1 - Singleton)
│           │       ├── factory/
│           │       │   └── LogFactory.java         (M2 - Factory)
│           │       ├── model/
│           │       │   ├── LogRecord.java          (M2 - Product)
│           │       │   ├── InfoLogRecord.java      (M2 - ConcreteProduct)
│           │       │   ├── WarningLogRecord.java   (M2 - ConcreteProduct)
│           │       │   ├── ErrorLogRecord.java     (M2 - ConcreteProduct)
│           │       │   └── DebugLogRecord.java     (M2 - ConcreteProduct)
│           │       └── enums/
│           │           └── LogLevel.java
│           └── Main.java
├── docs/
│   └── Sprint1_DiagramaClasses.md
└── README.md
```

## 🚀 Como Compilar e Executar

### Compilar

```bash
# Windows (PowerShell)
javac -d bin -encoding UTF-8 src/main/java/com/logging/**/*.java src/main/java/*.java

# Linux/Mac
javac -d bin src/main/java/com/logging/**/*.java src/main/java/*.java
```

### Executar

```bash
java -cp bin Main
```

## 📚 Sprint 1 - Funcionalidades

### M1 - Singleton (Configuração Centralizada)
- ✅ Instância única da configuração
- ✅ Controlo de níveis de log (DEBUG, INFO, WARNING, ERROR)
- ✅ Configuração de destino de saída
- ✅ Formato personalizável de mensagens
- ✅ Configurações customizadas via Map
- ✅ Thread-safe

### M2 - Factory (Criação de Registos)
- ✅ Criação encapsulada de logs
- ✅ 4 tipos implementados: INFO, WARNING, ERROR, DEBUG
- ✅ Suporte a exceções em ErrorLog
- ✅ Timestamp automático
- ✅ Detecção automática da fonte (classe/método)
- ✅ Formatação específica por tipo

## 💡 Exemplos de Uso

### Configurar o Sistema (Singleton)
```java
LogConfig config = LogConfig.getInstance();
config.setMinLogLevel(LogLevel.DEBUG);
config.setOutputDestination("file");
config.displayConfig();
```

### Criar Logs (Factory)
```java
// Método 1: Usando LogLevel
LogRecord log = LogFactory.createLogRecord(LogLevel.INFO, "Sistema iniciado");

// Método 2: Métodos de conveniência
LogRecord info = LogFactory.createInfo("Operação concluída");
LogRecord warning = LogFactory.createWarning("Memória em 85%");
LogRecord error = LogFactory.createError("Falha na conexão");
LogRecord debug = LogFactory.createDebug("Valor de X: 42");

// Imprimir
System.out.println(log);
```

## 📝 Documentação

- **Diagrama de Classes Sprint 1**: [docs/Sprint1_DiagramaClasses.md](docs/Sprint1_DiagramaClasses.md)
- **Especificação do Trabalho**: [trabalho_pratico.txt](trabalho_pratico.txt)

## 👥 Desenvolvimento

### Metodologia: Sprints Semanais
- Sprint duration: 1 semana
- Product Owner rotativo por sprint
- Entregáveis: Código + Diagrama de Classes
- Commit obrigatório com tag "Sprint n"
- Defesa na aula seguinte pelo PO

### Avaliação
- **Cumprimento dos objetivos**: 30%
- **Qualidade da solução**: 40%
- **Qualidade da documentação**: 30%
- **Defesa do trabalho**: Fator transversal

## 🔧 Requisitos

- **Java**: JDK 8 ou superior
- **IDE**: VS Code, IntelliJ IDEA, Eclipse (opcional)

## 📖 Referências

- Design Patterns: Elements of Reusable Object-Oriented Software (Gang of Four)
- Head First Design Patterns

---

**Última atualização**: Sprint 1 - M1 (Singleton) e M2 (Factory)