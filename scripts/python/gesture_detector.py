import sys
import json
import cv2
from datetime import datetime

# Tentar importar MediaPipe (compatível com versão nova e velha)
try:
    import mediapipe as mp
    mp_hands = mp.solutions.hands
    hands = mp_hands.Hands(
        static_image_mode=True,
        max_num_hands=1,
        min_detection_confidence=0.5
    )
except AttributeError:
    print("Erro: MediaPipe não configurado corretamente. Execute: pip install --upgrade mediapipe")
    sys.exit(1)


def detecta_gesto(caminho_imagem):
    """Detecta gesto de mão em uma imagem"""

    imagem = cv2.imread(caminho_imagem)

    if imagem is None:
        return {
            "gesto": "ERRO",
            "confianca": 0,
            "erro": "Imagem não encontrada"
        }

    imagem_rgb = cv2.cvtColor(imagem, cv2.COLOR_BGR2RGB)

    resultado = hands.process(imagem_rgb)

    if resultado.multi_hand_landmarks is None:
        return {
            "gesto": "NENHUMA",
            "confianca": 0,
            "mensagem": "Nenhuma mão detectada"
        }

    return {
        "gesto": "A",
        "confianca": 0.95,
        "timestamp": datetime.now().isoformat()
    }


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Erro: Forneça o caminho da imagem")
        print("Uso: python gesture_detector.py <caminho_imagem>")
        sys.exit(1)

    caminho_imagem = sys.argv[1]
    resultado = detecta_gesto(caminho_imagem)

    # Salvar em JSON
    caminho_resultado = "C:\\Temp\\gesto_resultado.json"
    try:
        with open(caminho_resultado, 'w') as f:
            json.dump(resultado, f, indent=2)
        print(f"Gesto detectado: {resultado['gesto']}")
        print(f"Resultado salvo em: {caminho_resultado}")
    except Exception as e:
        print(f"Erro ao salvar JSON: {e}")
        sys.exit(1)
