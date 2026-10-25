import java.io.ByteArrayOutputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class ReprodutorDeSom {

    private static final float TAXA_DE_AMOSTRAGEM = 44100f;
    private static final int BITS_POR_AMOSTRA = 8;
    private static final int QUANTIDADE_DE_CANAIS = 1;
    private static final boolean AMOSTRA_COM_SINAL = true;
    private static final boolean ORDEM_BIG_ENDIAN = false;

    private static final double FREQUENCIA_DO_TOM = 600.0;
    private static final double VOLUME = 100.0;
    private static final double VOLTA_COMPLETA = 2 * Math.PI;

    private static final int DURACAO_DA_UNIDADE_MS = 100;
    private static final int MILISSEGUNDOS_POR_SEGUNDO = 1000;
    private static final int UNIDADES_DO_PONTO = 1;
    private static final int UNIDADES_DO_TRACO = 3;
    private static final int UNIDADES_ENTRE_SIMBOLOS = 1;
    private static final int UNIDADES_ENTRE_LETRAS = 3;
    private static final int UNIDADES_ENTRE_PALAVRAS = 7;

    private static final String SEPARADOR_DE_PALAVRAS = "/";
    private static final String SEPARADOR_DE_LETRAS = "\\s+";

    public void tocarCodigoMorse(String textoMorse) throws LineUnavailableException {
        byte[] somCompleto = montarSom(textoMorse);
        AudioFormat formato = new AudioFormat(TAXA_DE_AMOSTRAGEM, BITS_POR_AMOSTRA, QUANTIDADE_DE_CANAIS, AMOSTRA_COM_SINAL, ORDEM_BIG_ENDIAN);
        try (SourceDataLine saidaDeSom = AudioSystem.getSourceDataLine(formato)) {
            saidaDeSom.open(formato);
            saidaDeSom.start();
            saidaDeSom.write(somCompleto, 0, somCompleto.length);
            saidaDeSom.drain();
        }
    }

    private byte[] montarSom(String textoMorse) {
        ByteArrayOutputStream som = new ByteArrayOutputStream();
        String[] palavrasMorse = textoMorse.trim().split(SEPARADOR_DE_PALAVRAS);
        for (int posicao = 0; posicao < palavrasMorse.length; posicao++) {
            if (posicao > 0) {
                adicionarSilencio(som, UNIDADES_ENTRE_PALAVRAS);
            }
            adicionarPalavra(som, palavrasMorse[posicao]);
        }
        return som.toByteArray();
    }

    private void adicionarPalavra(ByteArrayOutputStream som, String palavraMorse) {
        String[] letrasMorse = palavraMorse.trim().split(SEPARADOR_DE_LETRAS);
        for (int posicao = 0; posicao < letrasMorse.length; posicao++) {
            if (posicao > 0) {
                adicionarSilencio(som, UNIDADES_ENTRE_LETRAS);
            }
            adicionarLetra(som, letrasMorse[posicao]);
        }
    }

    private void adicionarLetra(ByteArrayOutputStream som, String letraMorse) {
        char[] simbolos = letraMorse.toCharArray();
        for (int posicao = 0; posicao < simbolos.length; posicao++) {
            if (posicao > 0) {
                adicionarSilencio(som, UNIDADES_ENTRE_SIMBOLOS);
            }
            adicionarSimbolo(som, simbolos[posicao]);
        }
    }

    private void adicionarSimbolo(ByteArrayOutputStream som, char simbolo) {
        if (simbolo == ArvoreMorse.SIMBOLO_PONTO) {
            adicionarTom(som, UNIDADES_DO_PONTO);
        } else if (simbolo == ArvoreMorse.SIMBOLO_TRACO) {
            adicionarTom(som, UNIDADES_DO_TRACO);
        }
    }

    private void adicionarTom(ByteArrayOutputStream som, int unidades) {
        int quantidadeDeAmostras = calcularAmostras(unidades);
        for (int amostra = 0; amostra < quantidadeDeAmostras; amostra++) {
            double angulo = VOLTA_COMPLETA * amostra * FREQUENCIA_DO_TOM / TAXA_DE_AMOSTRAGEM;
            som.write((byte) (Math.sin(angulo) * VOLUME));
        }
    }

    private void adicionarSilencio(ByteArrayOutputStream som, int unidades) {
        int quantidadeDeAmostras = calcularAmostras(unidades);
        som.write(new byte[quantidadeDeAmostras], 0, quantidadeDeAmostras);
    }

    private int calcularAmostras(int unidades) {
        return (int) (TAXA_DE_AMOSTRAGEM * unidades * DURACAO_DA_UNIDADE_MS / MILISSEGUNDOS_POR_SEGUNDO);
    }
}
