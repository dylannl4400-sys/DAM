Course: LEIM
Student(s): Dylan Loyola
Date: 12/04/2026

## 1. Introduction
Este projeto foi desenvolvido no âmbito da unidade curricular de DAM (Desenvolvimento de Aplicações Móveis), especificamente para o exercício **MIP-2 (Mission Impossible Possible - 2)**. O objetivo principal foi a criação de uma aplicação Android robusta, utilizando **Kotlin** e **XML Views**, com auxílio da plataforma **AntiGravity AI**. A aplicação foca-se na integração com uma API pública de imagens (Dog API), seguindo uma metodologia rigorosa de "Planning-First", onde todas as especificações e o design foram documentados antes da geração de código.

## 2. System Overview
A aplicação **Dog Image App** permite aos utilizadores visualizar imagens aleatórias de cães. As principais funcionalidades incluem:
- **Visualização de Imagens**: Lista dinâmica de imagens obtidas através da Dog API.
- **Extração de Metadados**: Identificação automática da raça do cão através da análise do URL da imagem.
- **Ecrã de Detalhes**: Ao clicar numa imagem, o utilizador pode vê-la em detalhe.
- **Favoritos (FIFO)**: Sistema que permite marcar até 5 imagens como favoritas, geridas por uma fila onde a mais antiga é removida quando o limite é excedido.
- **Cache Local**: Armazenamento de até 50 imagens previamente carregadas para melhorar a performance.
- **Suporte Offline**: Caso não haja ligação à internet, a aplicação tenta carregar imagens a partir da cache local.
- **Atualização Dinâmica**: Botão para carregar novas imagens de forma instantânea.

## 3. Architecture and Design
A aplicação segue o padrão de arquitetura **MVVM (Model-View-ViewModel)**, garantindo uma separação clara de responsabilidades:
- **UI (Activities & Adapters)**: `MainActivity` gere a lista principal, enquanto `DetailsActivity` trata da vista detalhada. O `DogImageAdapter` utiliza `ListAdapter` para atualizações eficientes com `DiffUtil`.
- **ViewModel**: `DogViewModel` mantém o estado da UI, lida com a lógica de favoritos e gere o carregamento assíncrono através de Coroutines.
- **Repository**: `DogRepository` centraliza o acesso aos dados, gerindo tanto a API (via Retrofit) como o sistema de cache em memória.
- **Data Model**: `ImageItem` é a classe de dados principal, contendo o URL, estado de favorito e a raça extraída.

## 4. Implementation
A implementação foi dividida em várias fases estratégicas:
1.  **Configuração e Modelos**: Criação do serviço Retrofit e definição do modelo de dados.
2.  **Lógica de Extração**: Implementação no Repository de uma função para processar o URL e extrair a raça (ex: `images.dog.ceo/breeds/beagle/...` -> "Beagle").
3.  **UI e Adapters**: Desenho dos layouts XML com `ViewBinding` e implementação do RecyclerView.
4.  **Extensões Avançadas**: Adição do sistema FIFO para favoritos e gestão de erros na API com mensagens de feedback ao utilizador (Toasts).

**Exemplo de raciocínio para Metadados:**
```kotlin
// Lógica para extrair e formatar a raça do URL
private fun formatBreedName(rawBreed: String): String {
    return rawBreed.split("-").joinToString(" ") { it.uppercase() }
}
```

## 5. Testing and Validation
A validação foi feita em várias etapas dentro do ambiente AntiGravity:
- **Build**: Verificação de dependências e compilação via Gradle.
- **Execução em Emulador**: Testes unitários funcionais para garantir que o clique no botão de "Refresh" trazia novas imagens e que os favoritos eram persistidos corretamente na lista.
- **Testes de Conetividade**: Simulação de modo avião para verificar se o fallback para a cache estava a funcionar conforme esperado.

## 6. Usage Instructions
1.  Clonar o repositório.
2.  Abrir o projeto no AntiGravity IDE.
3.  Certificar-se de que o dispositivo Android (físico ou emulador) está ligado.
4.  Executar o comando de build e run.
5.  Navegar pela lista, clicar nas imagens para detalhes e usar o ícone da estrela para gerir favoritos.

# Autonomous Software Engineering Sections- only for [AC OK, AI OK] sections

## 7. Prompting Strategy
A estratégia de prompts focou-se em ser **incremental e orientada a especificações**. Em vez de pedir a aplicação "toda de uma vez", os prompts foram guiados pelos ficheiros em `/docs`:
- **Prompt 1**: "Lê a documentação e resume a arquitetura."
- **Prompts seguintes**: Pedidos de "Continue" baseados no plano de implementação aprovado.
- **Evolução**: Quando surgiram erros de ambiente (conflito de versões de Java), o prompt foi ajustado para focar na resolução técnica antes de prosseguir com as funcionalidades.

## 8. Autonomous Agent Workflow
O AntiGravity atuou como um parceiro de pair programming. O workflow seguido foi:
1.  **Documentação**: Eu escrevi os ficheiros MD.
2.  **Planeamento**: O agente propôs um plano de implementação.
3.  **Execução**: O agente gerou os esqueletos das classes e a lógica core.
4.  **Depuração**: Usei o agente para analisar logs de erro durante o build e corrigir referências de recursos em falta.

## 9. Verification of AI-Generated Artifacts
Todo o código gerado foi revisto manualmente. Pequenas inconsistências, como o uso de bibliotecas de imagem diferentes, foram corrigidas pedindo especificamente o uso de **Glide**. A lógica de `extractBreedFromUrl` foi testada com vários URLs de exemplo para garantir que não ocorriam crashes com nomes de raças compostos.

## 10. Human vs AI Contribution
- **Humano (70%)**: Definição de requisitos, escrita das especificações detalhadas em Markdown, decisão sobre o sistema de armazenamento de favoritos (FIFO) e revisão final do código.
- **IA (30%)**: Geração de código boilerplate (Retrofit setup, Adapters), criação de layouts XML complexos e auxílio na resolução de erros de build.

## 11. Ethical and Responsible Use
O uso da IA foi transparente e restrito ao desenvolvimento técnico baseado em diretrizes humanas. Evitou-se o "copy-paste" cego, garantindo que o comportamento da aplicação (especialmente o sistema de cache e favoritos) seguia as regras pedagógicas do exercício.

# Development Process

## 12. Version Control and Commit History
O histórico de commits reflete o workflow de "Specification → Architecture → Implementation". Cada funcionalidade adicionada foi precedida por uma atualização no plano de implementação (docs/08 e docs/09).

## 13. Difficulties and Lessons Learned
- **Dificuldade**: Configuração inicial do Gradle devido a incompatibilidades entre a versão do Java no ambiente e as permissões de rede.
- **Resolução**: Ajuste das propriedades do Gradle e uso do agent para sugerir as permissões corretas no `AndroidManifest.xml`.
- **Lição**: Aprendi a importância de ter um modelo de dados flexível que permitisse adicionar metadados (raça) sem quebrar a lógica da API original.

## 14. Future Improvements
- Implementar uma base de dados **Room** para persistência real entre sessões da aplicação.
- Adicionar animações de transição entre a lista e o ecrã de detalhes.
- Permitir a pesquisa de imagens por raça específica.

## 15. AI Usage Disclosure
Este projeto utilizou o AntiGravity AI para assistência na codificação e planeamento estrutural, seguindo as diretrizes de integridade académica e responsabilidade partilhada.
