# Sprint 1 - Diagrama de Classes

## Padrões Implementados
- **M1**: Singleton (LogConfig)
- **M2**: Factory (LogFactory + LogRecord)

---

## Diagrama de Classes (Notação UML)

```
┌─────────────────────────────────────────────────────────────┐
│                      M1 - SINGLETON                         │
└─────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────┐
│         «Singleton»                      │
│            LogConfig                     │
├──────────────────────────────────────────┤
│ - instance: LogConfig (static)           │
│ - minLogLevel: LogLevel                  │
│ - outputDestination: String              │
│ - messageFormat: String                  │
│ - customSettings: Map<String, String>    │
│ - timestampEnabled: boolean              │
├──────────────────────────────────────────┤
│ - LogConfig()                            │
│ + getInstance(): LogConfig (static)      │
│ + getMinLogLevel(): LogLevel             │
│ + setMinLogLevel(level: LogLevel): void  │
│ + getOutputDestination(): String         │
│ + setOutputDestination(dest: String)     │
│ + getMessageFormat(): String             │
│ + setMessageFormat(format: String)       │
│ + displayConfig(): void                  │
│ + setCustomSetting(key, value): void     │
│ + getCustomSetting(key): String          │
└──────────────────────────────────────────┘


┌─────────────────────────────────────────────────────────────┐
│                      M2 - FACTORY                           │
└─────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────┐
│         «Factory»                        │
│          LogFactory                      │
├──────────────────────────────────────────┤
│                                          │
├──────────────────────────────────────────┤
│ + createLogRecord(level, msg): LogRecord │
│ + createErrorLog(msg, ex): LogRecord     │
│ + createInfo(msg): LogRecord             │
│ + createWarning(msg): LogRecord          │
│ + createError(msg): LogRecord            │
│ + createDebug(msg): LogRecord            │
└──────────────────────────────────────────┘
                    │
                    │ creates
                    ↓
┌──────────────────────────────────────────┐
│      «Abstract Product»                  │
│          LogRecord                       │
├──────────────────────────────────────────┤
│ # level: LogLevel                        │
│ # message: String                        │
│ # timestamp: LocalDateTime               │
│ # source: String                         │
├──────────────────────────────────────────┤
│ + LogRecord(level, message)              │
│ + format(): String {abstract}            │
│ + getLevel(): LogLevel                   │
│ + getMessage(): String                   │
│ + getTimestamp(): LocalDateTime          │
│ + getFormattedTimestamp(): String        │
│ + getSource(): String                    │
└──────────────────────────────────────────┘
                    △
                    │
        ┌───────────┼───────────┬───────────┐
        │           │           │           │
┌───────┴──────┐ ┌──┴────────┐ ┌┴──────────┐ ┌┴─────────────┐
│«ConcreteProduct»│«ConcreteProduct»│«ConcreteProduct»│«ConcreteProduct»│
│InfoLogRecord │ │WarningLogRecord│ │ErrorLogRecord │ │DebugLogRecord│
├─────────────┤ ├────────────┤ ├───────────┤ ├──────────────┤
│             │ │            │ │-exception │ │              │
│             │ │            │ │ :Exception│ │              │
├─────────────┤ ├────────────┤ ├───────────┤ ├──────────────┤
│+format():   │ │+format():  │ │+format(): │ │+format():    │
│  String     │ │  String    │ │  String   │ │  String      │
└─────────────┘ └────────────┘ └───────────┘ └──────────────┘


┌─────────────────────────────────────────────────────────────┐
│                    ENUMERAÇÃO                               │
└─────────────────────────────────────────────────────────────┘

┌──────────────────────────────────────────┐
│         «Enumeration»                    │
│            LogLevel                      │
├──────────────────────────────────────────┤
│ DEBUG                                    │
│ INFO                                     │
│ WARNING                                  │
│ ERROR                                    │
├──────────────────────────────────────────┤
│ + getLevel(): String                     │
│ + toString(): String                     │
└──────────────────────────────────────────┘
```

---

## Descrição dos Papéis no Padrão

### M1 - Singleton Pattern

| Classe | Papel | Responsabilidade |
|--------|-------|------------------|
| **LogConfig** | Singleton | Garante uma única instância de configuração global. Construtor privado e método `getInstance()` controlam o acesso. |

**Características do Singleton implementado:**
- ✅ Instância privada e estática
- ✅ Construtor privado
- ✅ Método público estático `getInstance()`
- ✅ Thread-safe (synchronized)
- ✅ Lazy initialization

---

### M2 - Factory Pattern

| Classe | Papel | Responsabilidade |
|--------|-------|------------------|
| **LogFactory** | Factory (Creator) | Encapsula a lógica de criação de objetos LogRecord. Decide qual classe concreta instanciar. |
| **LogRecord** | Product (Abstract) | Define a interface comum para todos os produtos criados pela factory. |
| **InfoLogRecord** | ConcreteProduct | Implementação concreta para logs de nível INFO. |
| **WarningLogRecord** | ConcreteProduct | Implementação concreta para logs de nível WARNING. |
| **ErrorLogRecord** | ConcreteProduct | Implementação concreta para logs de nível ERROR. |
| **DebugLogRecord** | ConcreteProduct | Implementação concreta para logs de nível DEBUG. |

**Características da Factory implementada:**
- ✅ Encapsulamento da criação de objetos
- ✅ Extensível (novos tipos podem ser adicionados)
- ✅ Sem necessidade de usar `new` diretamente no código cliente
- ✅ Métodos de conveniência para criação rápida

---

## Relacionamentos Entre Classes

```
Main (Cliente)
  │
  ├──→ LogConfig.getInstance()      [usa M1]
  │
  └──→ LogFactory.createXXX()        [usa M2]
         │
         └─→ cria ──→ LogRecord subclasses
```

---

## Vantagens da Implementação

### M1 - Singleton
1. **Consistência**: Uma única fonte de verdade para configurações
2. **Acesso Global**: Disponível em qualquer ponto da aplicação
3. **Controlo**: Nenhuma instância duplicada possível
4. **Eficiência**: Apenas uma instância em memória

### M2 - Factory
1. **Encapsulamento**: Lógica de criação centralizada
2. **Extensibilidade**: Novos tipos de log sem alterar código existente
3. **Flexibilidade**: Fácil trocar implementações
4. **Manutenibilidade**: Mudanças de criação num único local

---

## Como Compilar e Executar

```bash
# Na raiz do projeto (onde está a pasta src/)
javac -d bin src/main/java/com/logging/**/*.java src/main/java/*.java

# Executar
java -cp bin Main
```

---

## Exemplos de Uso

### Usando o Singleton (M1)
```java
// Obter configuração (sempre a mesma instância)
LogConfig config = LogConfig.getInstance();

// Modificar configurações
config.setMinLogLevel(LogLevel.DEBUG);
config.setOutputDestination("file");
config.displayConfig();
```

### Usando a Factory (M2)
```java
// Criar logs de diferentes tipos
LogRecord info = LogFactory.createInfo("Sistema iniciado");
LogRecord warning = LogFactory.createWarning("Memória baixa");
LogRecord error = LogFactory.createError("Conexão falhou");
LogRecord debug = LogFactory.createDebug("Variável x = 10");

// Imprimir logs
System.out.println(info);
System.out.println(warning);
```

---

## Notas de Implementação Sprint 1

- ✅ M1 (Singleton) totalmente implementado e funcional
- ✅ M2 (Factory) totalmente implementado e funcional
- ✅ 4 tipos de logs implementados (INFO, WARNING, ERROR, DEBUG)
- ✅ Código documentado com Javadoc
- ✅ Papéis dos padrões claramente identificados
- ✅ Classe Main com demonstração completa
- ✅ Extensível para próximos sprints (M3-M7)

---

**Product Owner do Sprint 1:** [Nome a preencher]  
**Data de Conclusão:** [Data a preencher]  
**Commit Tag:** Sprint 1
