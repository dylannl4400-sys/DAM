# Assignment 1 - Hello World!
Course: LEIM
Student(s): Dylan Loyola
Date: 14/03/2026

## 1. Introduction
Este projeto, intitulado "Meme Maker", foi desenvolvido no âmbito da disciplina de LEIM para a "Assignment 1 - Hello World!". O objetivo principal do exercício era criar uma aplicação móvel em Android Studio utilizando Kotlin e o sistema de XML Views, focando na integração de uma API externa para consumo de imagens (neste caso, a Imgflip API).
Além do desenvolvimento clássico, o trabalho envolveu a exploração ativa do paradigma de Geração de Código Assistida (MIP), utilizando agentes autônomos de IA (Google Antigravity) para suportar o planeamento, a escrita de blocos de código e a resolução de problemas complexos de compilação. A aplicação construída permite aos utilizadores visualizar uma galeria de memes populares e editá-los de forma dinâmica com texto (movimentação, rotação, redimensionamento, mudança de cores) e filtros de cor, podendo no final partilhar as suas criações.

## 2. System Overview
O Meme Maker é composto por duas partes e ecrãs muito distintos:
1.  **Galeria de Memes**: O ecrã inicial apresenta uma grelha intuitiva de thumbnails (miniaturas) de memes obtidos através de uma chamada à API pública do Imgflip. O utilizador pode escolher o "template" que desejar.
2.  **Editor de Memes**: Ao selecionar uma imagem, o utilizador é reencaminhado para um editor dinâmico. Aqui, é possível adicionar dinamicamente múltiplos campos de texto, modificar a sua posição (arrastar as caixas sob a imagem com o dedo), redimensionar o tamanho de letra (com o clássico gesto de curvar os dedos, ou *Pinch-to-zoom*) e modificar a sua rotação (gesto *twist* de torção com dois dedos). Pode-se remover o texto selecionado, alterar a sua cor recorrendo a um seletor e aplicar diferentes filtros visuais à imagem base (Normal, Escala de Cinzentos, Sépia, Inverter Cores). O meme finalizado é "printado" do ecrã e pode ser partilhado nativamente via Intenções (`ACTION_SEND`).

## 3. Architecture and Design
O sistema utiliza o padrão arquitetural **MVVM (Model-View-ViewModel)** recomendado ativamente pela Google, em conjunto com o padrão **Repository**, separando a responsabilidade visual das lógicas de negócio e da comunicação na web.
*   **Camada de Dados**: A interface `ImgflipApi` utiliza a flexibilidade da biblioteca de terceiros **Retrofit** para estabelecer a comunicação HTTP. Os dados em JSON são descodificados e interpretados pela biblioteca *Gson* em Kotlin *data classes* (`MemeModels.kt`). O `MemeRepository` centraliza a fonte de dados e atua de maneira assíncrona usando **Coroutines**, gerindo as chamadas de API em threads secundárias (`Dispatchers.IO`) de modo a nunca bloquear a thread principal (*UI thread*).
*   **Camada de UI / Lógica de Apresentação**: O `GalleryViewModel` gere o estado do ecrã da galeria modelando fluxos de dados reativos via `StateFlow` (Loading, Success, Error). O fragmento visual (`GalleryFragment`) observa (collects) o estado e notifica o `MemeAdapter` para exibir a lista numa listagem `RecyclerView` reciclada.
*   **Navegação**: Integralmente controlada pelo **Jetpack Navigation Component**, definindo grafos lógicos em `nav_graph.xml` com o encapsulamento e transição fluida nativa entre Fragments (usando o recurso poderoso de *SafeArgs*).

## 4. Implementation
Durante a implementação, a linha de raciocínio seguiu uma base iterativa. Comecei a focar na lógica visual pura, interligando a lógica depois.
Em certas circunstâncias deparei-me com a necessidade de explorar soluções avançadas no ecrã de Edição que transcendiam o pedido inicial.
*   **Acesso a Imagens Assíncronas:** A grelha é inteiramente preenchida com requisições por URL a consumir a biblioteca de carregamento eficiente de imagens assíncrona chamada **Glide**.
*   **Gestos Avancados:** A necessidade de edição nativa sem depender de sistemas difíceis impulsionou usar uma lógica em cima de interfaces. Construí implementadores do evento no *canvas* (layout principal). Reduzi dores de cabeça ao ler e interpor ativamente a documentação do `ScaleGestureDetector`, conseguindo extrair fatores e traduzi os mesmos no `scaleX` e `scaleY` sob um elemento do Kotlin `View.setOnTouchListener`.

**Exemplo de Código: Rotação através da trigonometria (atan2)**
Um excelente desafio que obrigou a repensar as posições cartesianas num ecrã do telemóvel Android (coordenadas bidimensionais de pixel X e Y). Deduzi o ângulo na base a dois dedos recorrendo ao comportamento da matemática elementar de translações de arcos num círculo trigonométrico (função arco-tangente).
```kotlin
private fun rotationAngle(event: MotionEvent): Float {
    // Calculo do desvio base entre as cordenadas dos ponteiros do utlizador de Index 0 e Index 1. 
    val dx = (event.getX(0) - event.getX(1)).toDouble()
    val dy = (event.getY(0) - event.getY(1)).toDouble()
    
    // Obtenção na base dos radianos de pi, passando a conversão padrao via java.lang.Math do desvio em graus do declive tangente.
    val radians = Math.atan2(dy, dx)
    return Math.toDegrees(radians).toFloat()
}
```

## 5. Testing and Validation
Os testes da aplicação guiaram-se primordialmente pelos Testes Manuais exploratórios exaustivos acoplados a instrumentação via emulador interno próprio do Android Studio. 
*   **Estabilidade de Requisicão**: Simulou-se o processamento e tratamento de estado, avaliando o ciclo de vida via corrotinas `collect` das subscrições StateFlow do ViewModel e forçando exceções não-capturadas e capturadas a ecoarem visualmente *Toasts* curtas amigáveis alertando a falta de resposta, mantendo assim o ecrã ilibado de erros do runtime abrupto.
*   **Responsividade Física & Virtuais:** Recriei simulações noutros dispositivos por forma a reparar anomalias de formatação (`padding`/`margin`) onde o texto do editor do menu das ferramentas dos filtros de sobrepunha os ecrãs físicos ou comprimia em pequenos devices.

## 6. Usage Instructions
1. Executar o download/clone do repositório local.
2. Inicializar o ambiente integrado do **Android Studio**.
3. Importar a diretoria root (`/MemeMaker/`). O utilizador deve certificar-se de que a instância da sua máquina comporta **Java 21 ou 22** ativo (`Settings > Build, Execution, Deployment > Build Tools > Gradle > Gradle JDK`).
4. Re-sincronizar os pacotes Gradle sob `Build > Rebuild Project`.
5. Pela barra nativa de interface de simulação carregar em "Run" com os devidos SDK ligados. Não esquecer a obrigatoriedade da ligação Wi-Fi no dispositivo do emulador.

# Autonomous Software Engineering Sections

## 7. Prompting Strategy
As *prompts* com a ferramenta de suporte Google Antigravity evoluíram, começando numa estrutura holística para um escopo granular iterativo, como o enunciado aconselha para um começo robusto.
*   **Fase 1 (O Esqueleto do Modelo de Negócios):** Introduzi os propósitos, arquiteturas pretendidas e *constraints*. Pedia-se: *"Quero uma App em Android Studio, utilizando MVVM e Coroutines para conectar a Imgflip API dentro duma recyler de Galeria. Prepara o planeamento ("Planning Mode") em Markdown, e foca com as melhores práticas Android".*
*   **Fase 2 (View System Design):** Interpelou-se pedidos lógicos simples baseados numa iteratividade: *"Agora, quero ir além do tap para abrir foto grande. Quero desenhar botões para adicionar texto dinamicamente num layout."*
*   **Fase 3 (Lógica Matemática & Troubleshooting):** A medida que surgiam entraves no compilador devido a incompatibilidade com classes do Java 22, fornecia a Stack-Trace integral de *Build Errors*: *"Ajuda a entender porque a flag jvmTarget rebenta no compile. Que versão Kotlin é suportada no meu Gradle e no meu ambiente Java 22?"*

## 8. Autonomous Agent Workflow
O ecossistema em Antigravity fluiu formidavelmente auxiliando as tarefas fastidiosas em colaboração harmoniosa com o developer:
*   **Planificador e Arquiteto de Software:** A fase fulcral para documentar a intenção numa arquitetura sã foi concretizada pela geração de *Roadmaps*.
*   **Programador Pair-Review:** Analisando a View de "Canvas" para gestos de torção (twist to Rotate e Pinch), a IA acelerou e explicou classes esquecidas ou novas, e ajudou a criar as implementações abstratas como a matriz de cor (*ColorMatrixColorFilter* do Android API 1).
*   **Engenheiro DevOps & QA:** Num evento de incompatibilidades massivas Gradle sob o sistema Windows local em JDK avançado, a IA inspecionou localmente ficheiros de configuração .kts e autonomizou resoluções, operando com Comandos assíncronos via Shell / Powershell atualizando os `gradle-wrapper.properties` (versão 8.12.1).

## 9. Verification of AI-Generated Artifacts
Ao gerar porções extensas e detalhadas num único fluxo por ferramentas como `write_to_file`, nunca houve compromisso ou execução cega de confiança inescrupulosa.
Revi ativamente (usando os painéis side-by-side de diferenças - diff code) os *IDs* introduzidos com a finalidade de ligar eventos (`findViewById`/`ViewBindings`) atestando compatibilidades na importação sem causar poluição sintática. O processo incluiu confirmar a matemática inferida e compilar frequentemente por forma a averiguar vazamento de memória do emulador em instâncias recursivas ou infinitas do Listeners de eventos tácteis.

## 10. Human vs AI Contribution
Neste desenvolvimento de sinergia cooperativa em dupla:
*   **Humano:** Elaboração fundamental e conceptual (A perspetiva macro), liderança do controlo de projeto sobre qual *Features* reatribuir o mérito e foco para não gerar o infarme *feature creep*, escolha do fluxo estético gráfico, *bugfixing* lógico inter-arquivos sobre layouts estriados. Leitura cuidadosa da documentação da Google atestando as boas práticas sugeridas e testagem e verificação local constante.
*   **Antigravity (Apoio AI):** Responsou a parte rotineira ("*heavy-lifting*" na produção sintática e escrita passiva *boiler-plate* do MVVM), providenciou mentoria para implementação das físicas baseadas em ponteiros múltiplos (`MotionEvents`), forneceu de um suporte valioso desmistificando mensagens críticas complexas da ferramenta *Android Build Toolchain*.

## 11. Ethical and Responsible Use
Exerceu-se ponderamento e análise permanente validando referências providenciadas e avaliou-se um leve enviesamento ("bias") arquitetural, tal como tendências frequentes em utilizar funções *Deprecated* de gestos legados (versões Android precoces) do que novos paradigmas por parte da Inteligência Artificial. Este comportamento mitigou-se efetuando diálogos e estrita solicitude por paradigmas da era de Vida Útil do Material UI moderna e Componentização estendida via Jetpack (Lifecycle-aware components, etc). Os artefactos são propriedade limpa do resultado cooperativo e abstinências de cópias impensadas (*copy-paste without checking*) foi categoricamente proibida pela minha ética formadora académica.

# Development Process

## 12. Version Control and Commit History
Praticou-se um ciclo clássico submetendo de forma persistente os pacotes essenciais em blocos comânticos, por passos demarcando o objetivo concreto: O repositório transitava de uma estrutura base MVVM limpa via *commit* (ex: "Configuração base, repositório"), passando pela Galeria visual e interconexões de Retrofit, estendendo commits posteriores e iterativos focados inteiramente e incrivelmente circunscritos nos *Gestos multi-touch*, *Gestos de Escala*, *Configuração das Cores de seletor* até à alteração de dependências vitais de Build do projeto e por último a inclusão deste README e manuais. Toda este versionamento serviu-se no git local para garantir sempre um ponto seguro e de coesão lógica de restabelecimento.

## 13. Difficulties and Lessons Learned
Destacou-se como principal dificuldade inicial a complexidade associada à manutenção dinâmica com múltiplos *Views Android* simultâneos acopláveis - em específico sobre como garantir à funcionalidade que intercetasse no seletor da paleta o texto certo subjacente (e não um recém-criado, ou até piorar as cores de *Toda a Layer* principal do meme). Identificou-se falhas de priorização de *Focus*, requerendo um tratamento cirúrgico: uso de blocos `setOnFocusChangeListener` guardando de cada vez em memória temporária (*local scope reference* flag `lastFocusedEditText` rastreador) a identidade única das Views.
Passei agora a dominar processos abstratos robustos: fluxo assíncrono real-time (usando Kotlin Coroutines + `StateFlow`, que por substituto dos obsoletos `LiveData` exigiram mais empenho e curva complexa), percebi o poder inerente às matrizes matemáticas trigonométricas em interface Mobile (escales e graus via Atan2), e compreendi de vez em que instância e circunstância difere o `Target JVM` e ferramentas nativas de compilação da plataforma Kotlin e os JDKs associados.

## 14. Future Improvements
Seria cativante modernizar o fluxo estético abandonando o paradigm XML de base para o recente **Jetpack Compose** construindo as componentes reativamente de maneira muito mais eficiente e com um ganho perfomátivo brutal nestes gestos densos baseados em redes sociais contemporâneas modernas. Por seu lado, poder aplicar base de dados locais via padrão *Room Database* de forma perfeitamente cacheable, mantendo assim o uso passivo Offline da infra-extrutura, constituiria os alicerces plenos duma futura aplicação co-comercializável.

## 15. AI Usage Disclosure
Nos termos requeridos da enunciação para este MIP - e de orgulhosa transparência académica - atesto que a solução final deste Workspace operou numa dimensão declarada inteiramente de formato [AC YES, AI YES], aproveitando os ganhos eficientes na Geração Automática assistida e Programação de Sistemas com Agentes Inteligentes autónomos, sendo da minha irrestrita e explícita confirmação e idoneidade autoral os processos deliberativos, análise, inferência analítica manual do esqulímo algoritmico gerado, não constituindo assim num qualquer plágio automático cego mas numa elaboração ponderada, dissecada e avaliada por base em conhecimentos universitários validados pelo discente em epígrafe.

---
