# Sistema de Gerenciamento de Notas de Aluno

Aplicação Android desenvolvida em Kotlin utilizando Jetpack Compose para gerenciar e calcular a média geral de um aluno com base em três notas parciais.

## Autor

**Marcelo Augusto Aguiar da Cruz**

## Descrição do Projeto

Este projeto implementa um sistema completo de cadastro e avaliação de desempenho acadêmico, permitindo ao usuário inserir o nome de um aluno e suas três notas parciais (TP1, TP2, TP3). O aplicativo calcula automaticamente a média aritmética e determina o status final do aluno conforme critérios pré-definidos.

## Funcionalidades

- **Sistema de Abas**: Navegação intuitiva entre tela de cadastro e listagem
- **Cadastro de Aluno**: Campo para inserção do nome completo do estudante
- **Lançamento de Notas**: Três campos numéricos para as notas parciais (TP1, TP2, TP3)
- **Cálculo Automático da Média**: Média aritmética calculada a partir das três notas
- **Avaliação de Desempenho**: Sistema de classificação automática baseado na média:
  - **Reprovado**: Média inferior a 6.0 (indicador vermelho)
  - **Aprovado**: Média entre 6.0 e 9.0 (indicador azul)
  - **Ótimo Aproveitamento**: Média superior a 9.0 (indicador roxo)
- **Conservação de Estado**: Histórico completo de todos os alunos cadastrados mantido na memória
- **Listagem Interativa**: Visualização em cards de todos os alunos com suas respectivas notas, médias e status
- **Exibição de Resultados**: Apresentação clara do nome do aluno, notas individuais, média final e status
- **Validação de Dados**: Verificação de campos obrigatórios com mensagens de erro
- **Limpeza Automática**: Formulário é resetado após cada cadastro bem-sucedido

## Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Framework UI**: Jetpack Compose
- **Arquitetura**: Programação Orientada a Objetos (POO)
- **Gerenciamento de Estado**: `remember` e `mutableStateOf` (Compose State Management)
- **SDK Mínimo**: Android API 24 (Android 7.0)
- **SDK Alvo**: Android API 36

## Estrutura do Projeto

```
app/src/main/java/com/example/atv_2_media_geral/
├── MainActivity.kt          # Activity principal com UI em Compose
├── model/
│   └── Aluno.kt            # Data class com lógica de negócio
└── ui/theme/               # Configurações de tema Material Design
```

## Conceitos Técnicos Implementados

### Programação Orientada a Objetos (POO)

A classe `Aluno` encapsula os dados e comportamentos relacionados ao estudante:

- **Propriedades**: `nome` (String) e `notas` (MutableList<Double>)
- **Métodos**:
  - `media()`: Calcula a média aritmética das notas
  - `statusFinal()`: Determina o status com base na média
  - `withNome()`: Retorna cópia imutável com novo nome
  - `withNota()`: Retorna cópia imutável com nota atualizada

### Jetpack Compose

Interface declarativa e reativa construída com componentes Material Design 3:

- `TabRow` e `Tab`: Sistema de navegação por abas
- `OutlinedTextField`: Campos de entrada de dados
- `Button`: Ação de cadastro
- `LazyColumn`: Lista otimizada com rolagem eficiente
- `Card`: Cards elegantes para exibição de cada aluno
- `Text`: Exibição de resultados e labels
- `Column`, `Row` e `Spacer`: Layouts responsivos

### Gerenciamento de Estado Reativo

Utilização de estado local e global do Compose para sincronização automática entre dados e UI:

- Estado do objeto `Aluno` gerenciado com `mutableStateOf`
- Lista global de alunos com `mutableStateListOf` para conservação de histórico
- Atualização reativa dos campos de entrada
- Recomposição automática da interface ao cadastrar e listar alunos
- Navegação entre abas mantendo estado persistente

## Como Executar

### Pré-requisitos

- Android Studio Hedgehog ou superior
- JDK 11 ou superior
- Dispositivo Android ou Emulador com API 24+

### Passos para Execução

1. Clone o repositório:
```bash
git clone https://github.com/marcelitos1v9/atv_2_mobile.git
```

2. Abra o projeto no Android Studio

3. Aguarde a sincronização do Gradle

4. Execute o aplicativo:
   - Pressione `Shift + F10` ou
   - Clique no botão **Run** (▶️)

### Compilação via Terminal (Windows PowerShell)

```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-11"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
cmd /c gradlew.bat assembleDebug
```

O APK será gerado em: `app/build/outputs/apk/debug/`

## Como Usar

### Aba Cadastro

1. Insira o **nome completo** do aluno no primeiro campo
2. Digite as três notas nos campos **TP1**, **TP2** e **TP3** (aceita vírgula ou ponto como separador decimal)
3. Pressione o botão **Salvar e Ver Listagem**
4. O aluno será adicionado à lista e você será redirecionado automaticamente para a aba de listagem

### Aba Listagem

1. Visualize todos os alunos cadastrados em cards organizados
2. Cada card exibe:
   - Nome do aluno em destaque
   - Três notas individuais (TP1, TP2, TP3)
   - Média geral com duas casas decimais
   - Status de aprovação com indicador visual colorido
3. Role a tela para ver todos os alunos cadastrados
4. Retorne à aba **Cadastro** para adicionar mais alunos

### Navegação

- Toque nas abas **Cadastro** ou **Listagem** para alternar entre as telas
- O histórico de alunos é mantido enquanto o aplicativo estiver em execução

## Tratamento de Erros

- **Nome vazio**: Exibe mensagem "Informe o nome do aluno"
- **Notas inválidas**: Automaticamente convertidas para 0.0
- **Separador decimal**: Aceita vírgula (,) que é convertida automaticamente para ponto (.)

## Requisitos Atendidos

✅ Cadastro de aluno com nome completo  
✅ Lançamento de três notas parciais  
✅ Cálculo automático da média aritmética  
✅ Avaliação de desempenho com três categorias  
✅ Uso de POO com data class  
✅ Interface exclusivamente em Jetpack Compose  
✅ Gerenciamento de estado reativo com `remember` e `mutableStateOf`  
✅ Uso de `MutableList<Double>` para armazenar notas  
✅ Sistema de abas para navegação  
✅ Conservação de estado com histórico de alunos (`mutableStateListOf`)  
✅ Listagem interativa com `LazyColumn`  
✅ UI responsiva com Material Design 3  

## Licença

Este projeto foi desenvolvido para fins educacionais.

## Contato

Desenvolvido por **Marcelo Augusto Aguiar da Cruz**