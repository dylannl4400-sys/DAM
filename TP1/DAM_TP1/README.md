# Assignment 1 — Getting Started with Kotlin
Course: LEIM
Student: Dylan Loyola (a51609)
Date: 8 de Março de 2026


## 1. Introduction
Este trabalho prático marca o início da exploração da linguagem Kotlin no contexto do desenvolvimento de aplicações móveis. O objetivo principal foi familiarizar-me com a sintaxe básica, tipos de dados, estruturas de controlo e os princípios fundamentais da Programação Orientada a Objetos (POO) em Kotlin, utilizando o IntelliJ IDEA e o Maven como ferramentas de suporte.

## 2. System Overview
O projeto está dividido em quatro partes principais:
- **Exercício 1**: Demonstração de diferentes formas de inicializar arrays em Kotlin.
- **Exercício 2**: Uma calculadora funcional via consola que suporta operações aritméticas, booleanas e bitwise.
- **Exercício 3**: Utilização de Sequences para modelar o comportamento físico de uma bola ao saltar.
- **Virtual Library**: Um sistema de gestão de biblioteca que aplica conceitos avançados de POO como herança, abstração e encapsulamento.

## 3. Architecture and Design
O projeto segue uma estrutura organizada por pacotes, conforme solicitado no enunciado, facilitando a separação de responsabilidades:
- `dam.exer_1`: Lógica de arrays.
- `dam.exer_2`: Implementação da calculadora.
- `org.example.dam.exer_3`: Lógica de sequências e matemática.
- `org.example.dam.virtual_library`: Classes e lógica do sistema de biblioteca.

A **Virtual Library** utiliza uma classe base `Book` e subclasses `DigitalBook` e `PhysicalBook`, demonstrando o uso de polimorfismo através da especialização de métodos como `getStorageInfo()`.

## 4. Implementation

### 4.1 Exercise 1 - Arrays
Foram exploradas três abordagens para criar um array de quadrados perfeitos:
- **Constructor de IntArray**: Ideal para tipos primitivos.
- **Range e map()**: Uma abordagem funcional e elegante.
- **Array com Constructor**: Versátil para tipos Genéricos.

### 4.2 Exercise 2 - Calculator
A calculadora utiliza um menu num `while` loop. As decisões de operação são tomadas através de uma expressão `when`.
- **Destaque**: Implementação de tratamento de erros com `try-catch` para evitar falhas em inputs inválidos (ex: letras em vez de números) e uma verificação específica para não permitir divisões por zero.
- **Output**: Os resultados são apresentados em decimal, hexadecimal e booleano, utilizando interpolação de strings.

### 4.3 Exercise 3 - Sequences
Utilizei `generateSequence` para criar um modelo infinito de saltos, que é depois transformado e limitado.
```kotlin
val seq = generateSequence(i) { (it * 0.6) }
    .map { (it * 100).roundToInt() / 100.00 }
```
A linha de pensamento foi aplicar o fator de 60% sucessivamente e arredondar cada valor para duas casas decimais recorrendo a `roundToInt()`.

### 4.4 Virtual Library
A implementação focou-se em:
- **Custom Getters/Setters**: Na classe `Book`, criei um setter para validar que o stock nunca é negativo e um getter para classificar a "era" do livro com base no ano.
- **Abstração**: O método `getStorageInfo()` é abstrato na base e implementado especificamente em cada formato.
- **Companion Object**: Utilizado na classe `Library` para manter um contador global de livros criados, partilhado entre todas as instâncias.

## 5. Testing and Validation
Os testes foram realizados manualmente via consola:
1. **Arrays**: Verificação visual da lista de quadrados perfeitos.
2. **Calculadora**: Testes com números positivos, negativos, zeros e inputs não numéricos para validar a robustez do `try-catch`.
3. **Sequências**: Validação de que os saltos param conforme os filtros de altura aplicados (> 1m).
4. **Biblioteca**: Fluxos de emprestar e devolver livros, confirmando que o stock atualiza corretamente e impede empréstimos quando chega a zero.

## 6. Usage Instructions
1. Abrir o projeto no **IntelliJ IDEA**.
2. Garantir que o Maven descarrega as dependências definidas no `pom.xml`.
3. Executar as funções `main()` em cada ficheiro:
    - `dam.exer_1.exer_1.kt`
    - `dam.exer_2.exer_2.kt`
    - `dam.exer_3.exer_3.kt`
    - `dam.virtual_library.exer_6.kt`

# Development Process

## 12. Version Control and Commit History
O desenvolvimento foi acompanhado pelo uso de Git local. Realizei o commit inicial com a mensagem "initialize project structure with Kotlin and Maven configuration" e fui registando o progresso à medida que cada exercício era concluído.

## 13. Difficulties and Lessons Learned
- **Arredondamentos**: No exercício das sequências, foi desafiante encontrar a forma correta de arredondar para duas casas decimais diretamente na sequência sem perder a precisão necessária para o filtro. Resolvi utilizando a técnica de multiplicar por 100, arredondar e dividir por 100.0.
- **Input Validation**: Na calculadora, a gestão de tipos (String para Float/Int) exigiu atenção para garantir que o utilizador não "partisse" o programa. Aprendi que o uso de `readln().lowercase()` ajuda imenso a normalizar inputs de texto ("true", "TRUE", etc).
- **Herança**: Compreender como chamar o constructor da superclasse (`Book`) a partir das subclasses foi uma lição importante sobre a hierarquia de objetos em Kotlin.

## 14. Future Improvements
- Implementar uma interface gráfica (GUI) para a calculadora.
- Adicionar persistência de dados (JSON ou SQL) à Virtual Library para que os livros não se percam ao fechar o programa.
- Expandir a Biblioteca com uma funcionalidade de data de devolução prevista.

## 15. AI Usage Disclosure
Neste exercício, não foram utilizadas ferramentas de assistência por IA para a geração de código, seguindo o pedido expresso no enunciado para desenvolver competências fundamentais de lógica e depuração através da documentação oficial da Kotlin.
