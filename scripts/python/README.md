# Scripts Python para Vision Computacional

## 📁 Estrutura

```
scripts/python/
├── README.md (este arquivo)
├── requirements.txt (dependências Python)
└── gesture_detector.py (detecção de gestos com MediaPipe)
```

## 🐍 Requisitos

- Python 3.8+
- pip (gerenciador de pacotes)

## 📦 Instalação de Dependências

```bash
pip install -r requirements.txt
```

Ou manualmente:

```bash
pip install mediapipe
pip install opencv-python
pip install numpy
```

## 🎯 Scripts Disponíveis

### gesture_detector.py
Detecta gestos de mão usando MediaPipe.

**Uso:**
```bash
python gesture_detector.py C:\Temp\frame_atual.jpg
```

**Entrada:** Caminho de uma imagem JPG
**Saída:** Arquivo JSON em `C:\Temp\gesto_resultado.json` com o gesto detectado

**Exemplo de saída:**
```json
{
  "gesto": "A",
  "confianca": 0.95,
  "timestamp": "2026-07-20 20:05:12"
}
```

## 🔄 Integração com Java

Java chama o script Python:
```java
ProcessBuilder pb = new ProcessBuilder("python", "scripts/python/gesture_detector.py", caminhoFrame);
pb.start();
```

## 📝 Notas

- Os arquivos temporários ficam em `C:\Temp\`
- MediaPipe requer pip instalado e atualizado
- Primeiro uso pode ser lento (carrega modelos)

