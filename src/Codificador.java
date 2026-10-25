public class Codificador {

    private static final String SEPARADOR_DE_PALAVRAS = " / ";
    private static final String SEPARADOR_DE_LETRAS = " ";
    private static final String ESPACOS = "\\s+";

    private final ArvoreMorse arvore;

    public Codificador(ArvoreMorse arvore) {
        this.arvore = arvore;
    }

    public String codificarFrase(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Você não digitou nenhum texto.");
        }
        String[] palavras = texto.trim().split(ESPACOS);
        StringBuilder fraseEmMorse = new StringBuilder();
        for (String palavra : palavras) {
            if (fraseEmMorse.length() > 0) {
                fraseEmMorse.append(SEPARADOR_DE_PALAVRAS);
            }
            fraseEmMorse.append(codificarPalavra(palavra));
        }
        return fraseEmMorse.toString();
    }

    private String codificarPalavra(String palavra) {
        StringBuilder palavraEmMorse = new StringBuilder();
        for (char letra : palavra.toCharArray()) {
            if (palavraEmMorse.length() > 0) {
                palavraEmMorse.append(SEPARADOR_DE_LETRAS);
            }
            palavraEmMorse.append(codificarLetra(letra));
        }
        return palavraEmMorse.toString();
    }

    private String codificarLetra(char letra) {
        if (!Character.isLetter(letra)) {
            throw new IllegalArgumentException("O caractere '" + letra + "' não é uma letra.");
        }
        String caminho = arvore.buscarCaminho(letra);
        if (caminho == null) {
            throw new IllegalArgumentException("A letra '" + letra + "' não existe na árvore Morse.");
        }
        return caminho;
    }
}
