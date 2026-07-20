package org.example.gesture;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GestureDetector {
    
    private static final String PYTHON_SCRIPT = "scripts/python/gesture_detector.py";
    private static final String TEMP_RESULT = "C:\\Temp\\gesto_resultado.json";
    
    /**
     * Detecta gesto de mão chamando script Python
     * 
     * @param caminhoImagem Caminho da imagem capturada
     * @return String com o gesto detectado (A, B, C, NENHUMA, ERRO)
     */
    public static String detectarGesto(String caminhoImagem) {
        try {
            // Verificar se arquivo existe
            File imagem = new File(caminhoImagem);
            if (!imagem.exists()) {
                System.err.println("Erro: Imagem não encontrada: " + caminhoImagem);
                return "ERRO";
            }
            
            // Chamar script Python
            System.out.println("Chamando Python para detectar gesto...");
            ProcessBuilder pb = new ProcessBuilder("python", PYTHON_SCRIPT, caminhoImagem);
            pb.redirectErrorStream(true); // redireciona stderr para stdout
            
            Process process = pb.start();
            int codigoSaida = process.waitFor(); // espera terminar
            
            if (codigoSaida != 0) {
                System.err.println("Erro ao executar Python. Código: " + codigoSaida);
                return "ERRO";
            }
            
            // Ler resultado do JSON
            return lerGestoJSON();
            
        } catch (IOException e) {
            System.err.println("Erro de I/O ao chamar Python: " + e.getMessage());
            return "ERRO";
        } catch (InterruptedException e) {
            System.err.println("Processo Python foi interrompido: " + e.getMessage());
            Thread.currentThread().interrupt();
            return "ERRO";
        }
    }
    
    /**
     * Lê o arquivo JSON com resultado do gesto
     * 
     * @return Gesto detectado
     */
    private static String lerGestoJSON() {
        try {
            File jsonFile = new File(TEMP_RESULT);
            
            if (!jsonFile.exists()) {
                System.err.println("Erro: Arquivo JSON não foi criado: " + TEMP_RESULT);
                return "ERRO";
            }
            
            // Ler arquivo JSON
            StringBuilder jsonContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    jsonContent.append(linha);
                }
            }
            
            // Fazer parse do JSON
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(jsonContent.toString(), JsonObject.class);
            
            String gesto = json.get("gesto").getAsString();
            double confianca = json.get("confianca").getAsDouble();
            
            System.out.println("✅ Gesto detectado: " + gesto + " (confiança: " + confianca + ")");
            
            return gesto;
            
        } catch (IOException e) {
            System.err.println("Erro ao ler JSON: " + e.getMessage());
            return "ERRO";
        } catch (Exception e) {
            System.err.println("Erro ao fazer parse do JSON: " + e.getMessage());
            return "ERRO";
        }
    }
    
    /**
     * Método para testar (você pode remover depois)
     */
    public static void main(String[] args) {
        String resultado = detectarGesto("C:\\Temp\\frame_atual.jpg");
        System.out.println("Resultado final: " + resultado);
    }
}

