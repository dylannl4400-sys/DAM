# Assignment 4 — Building Your First Android App
Course: Desenvolvimento de Aplicações Móveis (DAM)  
Student: Dylan (a51609)  
Date: 8 de Março de 2026  
Repository URL: [Inserir URL do Repositório aqui]

## 1. Introduction
Este trabalho teve como objetivo o desenvolvimento da primeira aplicação nativa Android, focando-se na familiarização com o ambiente de desenvolvimento Android Studio, a linguagem Kotlin e o sistema de gestão de recursos e layouts do Android. O exercício foi dividido em duas fases principais: a criação de uma aplicação "Hello World" básica (v1) e a sua posterior expansão para uma interface mais rica e complexa (v2).

## 2. System Overview
A aplicação desenvolvida, denominada "Hello World V2", apresenta uma interface composta por:
- Títulos informativos utilizando `TextView`.
- Uma imagem ilustrativa carregada a partir dos recursos do projeto.
- Um elemento interativo `CalendarView`.
- Suporte para diferentes orientações de ecrã (Portrait e Landscape).
- Ícone de aplicação personalizado.

## 3. Architecture and Design
A arquitetura da aplicação segue o padrão padrão do Android para aplicações simples:
- **Layout**: Utilização de `ConstraintLayout` para um posicionamento flexível e responsivo dos elementos.
- **Lógica**: A `MainActivity.kt` gere o ciclo de vida da atividade, embora nesta fase inicial a maior parte da lógica resida na definição declarativa do layout XML.
- **Recursos**: Separação clara de responsabilidades através dos ficheiros:
    - `strings.xml`: Centralização de todos os textos para facilitar a internacionalização.
    - `colors.xml`: Definição de cores reutilizáveis (ex: Azul para o título).
    - `drawable`: Armazenamento de imagens e ícones.

## 4. Implementation

### Parte 4.1: Hello World v1
Nesta fase inicial, o foco foi a configuração do projeto (API 24, Kotlin, Empty Views Activity).
- **Raciocínio**: Segui as instruções para criar a estrutura base e renomear o pacote para `dam_a51609.helloworld`. A extração da string para o ficheiro `strings.xml` foi um passo crucial para aprender boas práticas de desenvolvimento Android.
- **Código Exemplo**:
```xml
<string name="hello_string">Hello Android World!</string>
```

### Parte 4.2: Hello World v2 (Melhorias)
A segunda fase envolveu uma evolução estética e funcional significativa.
- **Raciocínio**:
    - **Constraints**: Removi e adicionei restrições no `ConstraintLayout` para posicionar o título no topo.
    - **Estilização**: Apliquei cores personalizadas e tamanhos de texto (ex: `textSize="24sp"`, `textColor="#FFFFFF"` com fundo azul no título principal).
    - **Elementos Dinâmicos**: Adicionei um `ImageView` com uma descrição de conteúdo (acessibilidade) e um `CalendarView`.
    - **Adaptabilidade**: Criei uma variante de layout para modo paisagem (`layout-land/alternativa.xml`) para garantir que a interface permanece utilizável ao rodar o dispositivo.

## 5. Testing and Validation
A aplicação foi validada em dois ambientes:
1.  **Emulador (AVD)**: Testada no Pixel 9 Pro para verificar o posicionamento dos elementos e a renderização das cores.
2.  **Dispositivo Físico**: Utilizei o ADB (Android Debug Bridge) para correr a app num telemóvel real, garantindo que o ícone e o nome da aplicação apareciam corretamente no launcher.
3.  **Rotação**: Verifiquei a transição suave entre o modo vertical e horizontal, confirmando que o layout alternativo era carregado como esperado.

## 6. Usage Instructions
Para executar este projeto:
1.  Clonar o repositório.
2.  Abrir o Android Studio e importar o projeto `HelloWorld2`.
3.  Garantir que o Gradle JDK está configurado corretamente (conforme a *Note 6* do enunciado).
4.  Selecionar um dispositivo (AVD ou físico) e clicar no botão "Run" (Seta verde).

---

# Development Process

## 12. Version Control and Commit History
Utilizei o Git para manter um histórico das alterações. Os commits foram estruturados para refletir as diferentes fases do exercício:
- `Initial commit`: Estrutura base v1.
- `feat: upgrade to v2`: Adição de novos elementos (Imagem, Calendário).
- `docs: add README`: Finalização da documentação.

## 13. Difficulties and Lessons Learned
- **Gestão de Constraints**: No início, foi desafiante alinhar os elementos sem que estes se sobrepusessem. Aprendi que o `ConstraintLayout` é extremamente poderoso mas requer rigor nas âncoras.
- **Resolução de Erros de Gradle**: Deparei-me com inconformidades de versão do JDK (mencionado na Note 6), o que me obrigou a explorar as definições de `Build Tools > Gradle` e descarregar uma versão compatível.
- **Importância dos Recursos**: Compreendi o porquê de não usar "hardcoded strings" — torna o código mais limpo e preparado para tradução.

## 14. Future Improvements
- Adicionar lógica em Kotlin para interagir com o `CalendarView` (ex: mostrar a data selecionada num `Toast`).
- Implementar um tema Dark Mode mais elaborado.
- Adicionar animações simples ao carregar a imagem.

## 15. AI Usage Disclosure
A inteligência artificial foi utilizada como assistente para:
- Estruturação deste documento README de forma profissional.
- Sugestões de nomes de recursos seguindo as convenções do Android (ex: `just_smile`).
- Esclarecimento de dúvidas sobre a hierarquia de layouts na criação da variante landscape.
