# Assignment 1 — Tutorial 1 - Hello World
**Course:** LEIM
**Student(s):** Dylan Loyola (a51609) 
**Date:** 14 de Março de 2026  

## 1. Introduction
Este projeto surge no âmbito do exercício **"5.3 Building a System Info App"**. O objetivo principal delineado no enunciado consistia em criar uma nova aplicação capaz de extrair propriedades do sistema e apresentar as informações acerca do hardware utilizado e *build* do dispositivo num *MultiLine Text Widget*. Tudo isto tendo como premissa o uso exclusivo do objeto nativo `android.os.Build` do sistema operativo.

## 2. System Overview
O sistema consiste numa aplicação de ecrã único (*single-screen application*). Ao ser inicializada, a *app* acede imediatamente às características do sistema Android em execução (seja ele dispositivo físico ou emulador) e apresenta os metadados de forma estruturada. 

## 3. Architecture and Design
A aplicação é de arquitetura simples, tendo apenas uma `MainActivity`. A Interface Gráfica foi projetada em XML da seguinte forma:
- A `ConstraintLayout` serve como base.
- Um **TextView** alinhado ao topo, que atua como cabeçalho ou título dinâmico com a cor azul de fundo (para destacar o cabeçalho).
- Um **EditText** (*MultiLine Text Widget*) a ocupar a restante porção do ecrã, com as métricas gravadas no mesmo.

## 4. Implementation

### Extração das Informações de Sistema
- **O que era pedido:** Utilizar o objeto `android.os.Build` e mapear os valores necessários.
- **Linha de pensamento:** Sendo o meu objetivo apresentar aspetos fundamentais do telemóvel (ex.: *Manufacturer, Model, Brand*), comecei por consultar e analisar a documentação oficial do Android para o pacote `android.os` a fim de perceber como a *API* estava estruturada. Armazenei as variáveis que julguei pertinentes recorrendo sempre aos métodos *toString()* para garantir que seriam strings ao compor o output final.

*Exemplo da abordagem tomada:*
```kotlin
val manufacturer = Build.MANUFACTURER.toString()
val model = Build.MODEL.toString()
val sdk = Build.VERSION.SDK_INT.toString()
// Entre outros parâmetros, como base, incremental, e afins.
```

### Apresentação da Informação no Ecrã
- **O que era pedido:** Mostrar tudo no widget multilinha.
- **Linha de pensamento:** Primeiramente criei as âncoras na vista XML e configurei o ID `editTextTextMultiLine` apropriadamente com `inputType="textMultiLine"`. Posteriormente, no código nativo em Kotlin, criei a associação a esse objeto através do método estabilizado de busca por vista: o `findViewById`. Depois foi uma questão de encadear o conteúdo extraído previamente usando o método `.append()`.

*Exemplo de código:*
```kotlin
val multitext = findViewById<EditText>(R.id.editTextTextMultiLine)
multitext.text.append("\n",
    "Manufacturer: ", manufacturer, "\n",
    "Model: ", model, "\n",
    // [...] concatenando cada linha usando "\n"
    "SDK: ", sdk, "\n")
```

## 5. Testing and Validation
Os testes foram realizados diretamente usando o *Android Emulator* instanciado no Android Studio. Fui capaz de validar a integração, uma vez que mal a aplicação abriu, devolveu dados como ("SDK: 35" ou "Model: sdk_gphone64_x86_64"), refletindo fielmente tratar-se de uma simulação virtual do sistema Android.

## 6. Usage Instructions
1. Executar o **Android Studio**.
2. Abrir a pasta raiz deste projeto (`SystemInfoApp`).
3. Aguardar que os processos analíticos e o **Gradle Sync** concluam.
4. Selecionar um dispositivo-alvo (Emulador/Telemóvel por *USB Debugging*).
5. Carregar no botão *Run* (ou `Shift + F10`).
6. Observar os dados de registo do telemóvel presentes de imediato no ecrã.

# Development Process

## 12. Version Control and Commit History
O controlo de versões foi gerido através de um repositório local usando Git para consolidar o histórico de desenvolvimento da atividade ou, em alternativa, simplesmente persistido garantindo boas práticas de organização do diretório de *workspace*.

## 13. Difficulties and Lessons Learned

- **Uso de documentação e encontrar as informações certas:** Uma parte considerável de tempo foi investida na leitura dos papéis da plataforma para perceber como desdobrar funções ligadas a `Build.VERSION` em contraste com `Build.VERSION_CODES`, que trazem variáveis totalmente díspares. Essa interpretação foi vital.
- **Integração do View e manipulação de String:** Identificar o componente com o ID correto na árvore de *views* (`findViewById<EditText>`) e compreender que o recurso `append()` aplicável no `text` do `EditText` era a forma mais otimizada de compor os dados em cascata — evitando as complexidades da criação repetitiva de múltiplas `TextViews` dinamicamente no mesmo *Layout* de forma redundante (o que estaria incorreto dado o requisito do "MultiLine Widget").
- Adicionalmente à UI, dediquei algum tempo à formatação, como as *ConstraintLayout margins* e o embelezamento do *header*, contornando os limites básicos das *layouts*.

## 14. Future Improvements
- É notável que o uso de um `EditText` sugira, em parte, editabilidade do lado de uma interface com os dados num formato de caixas de entrada. Para otimizar a consistência UI/UX num cenário mais pragmático, substituiria isto por um simples `TextView` com propriedades de `scrollbars="vertical"` incluídas, para atuar unicamente como objeto de exibição *Read-Only*.
- Explorar a extração de métricas de Hardware via `ActivityManager` para indicar RAM e baterias dinâmicas.

## 15. AI Usage Disclosure
Neste exercício, o código, lógica interna da aplicação nativa (uso do `Build.`), ligação ao ficheiro de propriedades gráficas e testes foram todos planeados, escritos e depurados pessoalmente com o auxílio da devida documentação nativa do Android. As ferramentas de Inteligência Artificial assumiram apenas o papel de apoiar a redação gramatical, clarificação dos descritores e a formatação *Markdown* final deste documento para manter um contexto técnico, coeso e normalizado exigido para a submissão.
