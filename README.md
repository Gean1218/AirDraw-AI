# 🎨 AirDraw AI - Versão Beta Incompleta

Sistema de desenho inteligente com visão computacional para reconhecimento de linguagem de sinais e leitura labial.

## 📋 Funcionalidades Implementadas ✅

### Desenho Básico
- ✅ Canvas interativo para desenho livre
- ✅ Caneta (traços contínuos)
- ✅ Borracha (apagar)
- ✅ Seleção de cores (ColorPicker)
- ✅ Ajuste de espessura do pincel (Spinner)
- ✅ Limpar tela
- ✅ Salvar desenho em PNG
- ✅ Abrir imagens PNG

### Integração de Câmera
- ✅ Captura de vídeo em tempo real
- ✅ Exibição de câmera em ImageView
- ✅ Controle de câmera (Ligar/Desligar)
- ✅ Otimização (~30 FPS)

## 🚧 Funcionalidades em Desenvolvimento

### Visão Computacional
- 🔄 Detecção de gestos de mão (MediaPipe)
- 🔄 Leitura labial (OpenCV)
- 🔄 Conversão de gestos em texto
- 🔄 Escrita automática no canvas

## 🛠️ Tecnologias Usadas

- **JavaFX 21.0.2** - Interface gráfica
- **OpenCV 4.5.2** - Processamento de imagens
- **MediaPipe** - Detecção de gestos (em desenvolvimento)
- **Maven** - Gerenciador de dependências
- **Python 3.8+** - Scripts de visão computacional

## 📦 Estrutura do Projeto

```
src/main/java/org/example/
├── MainApp.java                 # Ponto de entrada da aplicação
├── Cam/
│   └── Cam_Capture.java        # Captura de câmera com OpenCV
├── ui/
│   └── ImagineVideo.java       # Interface gráfica principal
├── drawing/
│   ├── DrawingTools.java       # Configurações de desenho
│   └── Mouse_Events.java       # Eventos de mouse
└── file/
    └── FileManager.java        # Gerenciamento de arquivos PNG
```

## 🚀 Como Usar

### Requisitos
- JDK 25+
- Maven 3.6+
- Python 3.8+ (para visão computacional)

### Instalação

1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/AirDraw-AI.git
cd AirDraw-AI
```

2. Compile o projeto
```bash
mvn clean compile
```

3. Execute a aplicação
```bash
mvn javafx:run
```

## 📚 Funcionalidades Detalhadas

### Desenho Livre
- Clique e arraste para desenhar
- Use "Caneta" para traçar
- Use "Borracha" para apagar
- Escolha cores com o ColorPicker
- Ajuste espessura com o Spinner

### Câmera
- Clique "Ligar Câmera" para iniciar captura
- Vídeo ao vivo aparece no lado esquerdo
- Clique "Desligar Câmera" para parar

### Salvamento
- Clique "Salvar PNG" para exportar desenho
- Clique "Abrir" para importar imagens

## 🔮 Próximas Etapas

1. **Integração Python-Java** para detecção de gestos
2. **Reconhecimento de sinais** com MediaPipe
3. **Leitura labial** com OpenCV
4. **Escrita automática** no canvas
5. **Testes e validação**



**Status:** 🟡 Beta Incompleta - Em desenvolvimento ativo

