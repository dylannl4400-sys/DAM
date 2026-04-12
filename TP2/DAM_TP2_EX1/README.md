# Assignment 1 — Tutorial 1: Kotlin Exercises
Course: LEIM
Student(s): Dylan Loyola A51609
Date: April 12, 2026

## 1. Introduction
This assignment consists of a series of Kotlin exercises designed to explore and apply fundamental and advanced features of the language. The exercises cover functional programming techniques, generics, higher-order functions, and operator overloading. The goal is to demonstrate a deep understanding of Kotlin's documentation and standard library through practical implementation.

## 2. System Overview
The system is composed of four distinct modules, each addressing a specific Kotlin feature:
1.  **Event Log Processing**: Focused on sealed classes and extension functions for data filtering and processing.
2.  **Type-Safe In-Memory Cache**: A generic cache implementation using type parameters and higher-order functions.
3.  **Configurable Data Pipeline**: A sequence of string transformations built using the builder pattern and function composition.
4.  **2D Vector Library**: A mathematical utility demonstrating operator overloading and data class features.

## 3. Architecture and Design
The project follows a modular structure where each exercise is isolated in its own package under `org.example.dam_A51609.exer_X`.

-   **Sealed Classes**: Used in the Event system to ensure type safety and exhaustive branching in `when` expressions.
-   **Generics**: Applied in the Cache system with upper bounds (`<K : Any, V : Any>`) to ensure non-nullable keys and values.
-   **Functional Programming**: Extensive use of lambdas, `map`, `filter`, `fold`, and extension functions to create concise and readable code.
-   **Operator Overloading**: Implementing standard mathematical operations (`+`, `-`, `*`) and interface contracts (`Comparable`, `get`) to make custom types behave like native ones.

## 4. Implementation

### 4.1. Event Log Processing
**Enunciado**: Criar um sistema de log de eventos usando classes seladas e funções de extensão para filtrar e calcular gastos.
**Raciocínio**:
Utilizei a `sealed class Event` para definir categorias rígidas de eventos (Login, Purchase, Logout). As funções de extensão `filterByUser` e `totalSpent` foram anexadas diretamente a `List<Event>`, permitindo uma sintaxe fluida.
-   **Dificuldades**: Garantir a exaustividade do `when` no processador de eventos.
-   **Documentação**: Consultei `filterIsInstance` para extrair apenas compras de forma segura.

```kotlin
fun List<Event>.totalSpent(username: String): Double {
    return this.filterIsInstance<Purchase>()
        .filter { it.username == username }
        .sumOf { it.amount }
}
```

### 4.2. In-Memory Cache
**Enunciado**: Implementar uma cache genérica com suporte a inserção, remoção, transformações e snapshots.
**Raciocínio**: 
Utilizei um `MutableMap` interno. A função `getOrPut` foi implementada para evitar cálculos desnecessários se a chave já existir. O método `snapshot` garante imutabilidade ao retornar uma cópia do mapa.
-   **Dificuldades**: Manter a segurança de tipos com genéricos `K` e `V`.
-   **Documentação**: A implementação de `getOrPut` foi inspirada na função homónima da biblioteca padrão do Kotlin.

### 4.3. Data Pipeline
**Enunciado**: Construir um pipeline de processamento de strings configurável com estágios nomeados.
**Raciocínio**: 
A classe `Pipeline` armazena uma lista de pares (nome, transformação). O método `execute` utiliza `fold` para passar o resultado de cada estágio para o seguinte. Implementei um `buildPipeline` (DSL-style) usando um lambda com recetor.
-   **Dificuldades**: Composição de funções e manutenção da ordem dos estágios.
-   **Documentação**: Pesquisa sobre o operador `fold` para acumular transformações.

### 4.4. 2D Vector Library
**Enunciado**: Criar uma classe `Vec2` que suporte operações matemáticas via sobrecarga de operadores.
**Raciocínio**: 
Implementei `plus`, `minus`, `times` e `unaryMinus`. Sobrecarreguei `compareTo` para permitir comparações de magnitude. Adicionei o operador `get` para acesso estilo array (`v[0]`, `v[1]`).
-   **Dificuldades**: Decidir como comparar vetores (escolhida a magnitude euclidiana).
-   **Documentação**: Verificação da ordem de precedência de operadores e implementação da interface `Comparable`.

## 5. Testing and Validation
Cada exercício contém uma função `main` que valida a lógica com dados de amostra.
-   **Exemplo Eventos**: Validado o cálculo de gasto total de Alice ($64.99).
-   **Exemplo Cache**: Validada a persistência e transformação de frequências de palavras.
-   **Exemplo Pipeline**: Validada a limpeza e indexação de logs de erro.
-   **Exemplo Vetores**: Validada a soma, produto escalar e normalização.

## 6. Usage Instructions
1.  Navegar até ao diretório do projeto.
2.  Compilar os ficheiros Kotlin usando um compilador compatível (ou importar no IntelliJ IDEA).
3.  Executar os ficheiros `Main.kt` localizados em cada package `exer_X`.
    -   Ex: `src/main/kotlin/dam_A51609/exer_1/Main.kt`

# Development Process

## 12. Version Control and Commit History
O desenvolvimento foi organizado em packages incrementais, permitindo a separação clara de responsabilidades. O histórico de commits reflete a implementação sequencial de cada funcionalidade core seguida pelos desafios propostos.

## 13. Difficulties and Lessons Learned
-   **Classes Seladas**: Aprendi como o compilador Kotlin ajuda a detetar ramos em falta, aumentando a segurança do código.
-   **Pipeline de Dados**: A implementação do builder pattern com `Pipeline.() -> Unit` mostrou a flexibilidade do Kotlin para criar DSLs simples.
-   **Operator Overloading**: Entendi que a sobrecarga deve ser usada com parcimónia para manter o código intuitivo. A principal dificuldade foi configurar corretamente a comparação de magnitudes.

## 14. Future Improvements
-   **Vec2**: Implementar destruturação total (`component1`, `component2`) e suporte completo para esquerda-escalar (`2.0 * v`).
-   **Cache**: Implementar políticas de expiração (TTL) para as entradas da cache.
-   **Pipeline**: Adicionar suporte para estágios assíncronos.

## 15. AI Usage Disclosure
Este documento README foi gerado com o auxílio de inteligência artificial (Antigravity), com base na implementação código fornecida pelo estudante e nas diretrizes do enunciado. O código fonte foi desenvolvido integramente pelo estudante seguindo a política de não utilização de ferramentas de IA para a resolução dos problemas lógicos.
