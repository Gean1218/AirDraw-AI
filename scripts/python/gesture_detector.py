import cv2
from datetime import datetime
import mediapipe as mp

mp_hands = mp.solutions.hands

hands = mp_hands.Hands(
    static_image_mode=True,
    max_num_hands=1,
    min_detection_confidence=0.5
)

mp_drawing = mp.solutions.drawing_utils


def detecta_gesto(caminho_imagem):

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


resultado = detecta_gesto(r"C:\Temp\frame_atual.jpg")
print(resultado)