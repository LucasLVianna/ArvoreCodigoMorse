public class Decodificador {

    private static final String SEPARADOR_DE_PALAVRAS = "/";
    private static final String SEPARADOR_DE_LETRAS = "\\s+";

    private final ArvoreMorse arvore;

    public Decodificador(ArvoreMorse arvore) {
        this.arvore = arvore;
    }

    public String decodificarFrase(String textoMorse) {
        if (textoMorse == null || textoMorse.isBlank()) {
            throw new IllegalArgumentException("Você não digitou nenhum código Morse.");
        }
        String[] palavrasMorse = textoMorse.trim().split(SEPARADOR_DE_PALAVRAS);
        StringBuilder fraseDecodificada = new StringBuilder();
        for (String palavraMorse : palavrasMorse) {
            if (fraseDecodificada.length() > 0) {
                fraseDecodificada.append(' ');
            }
            fraseDecodificada.append(decodificarPalavra(palavraMorse));
        }
        return fraseDecodificada.toString();
    }

    private String decodificarPalavra(String palavraMorse) {
        if (palavraMorse.isBlank()) {
            throw new IllegalArgumentException("Existe uma palavra vazia entre as barras.");
        }
        String[] letrasMorse = palavraMorse.trim().split(SEPARADOR_DE_LETRAS);
        StringBuilder palavraDecodificada = new StringBuilder();
        for (String letraMorse : letrasMorse) {
            palavraDecodificada.append(decodificarLetra(letraMorse));
        }
        return palavraDecodificada.toString();
    }

    private char decodificarLetra(String letraMorse) {
        verificarSimbolos(letraMorse);
        char letraEncontrada = arvore.buscarLetra(letraMorse);
        if (letraEncontrada == NoMorse.LETRA_VAZIA) {
            throw new IllegalArgumentException("O código \"" + letraMorse + "\" não existe na árvore Morse.");
        }
        return letraEncontrada;
    }

    private void verificarSimbolos(String letraMorse) {
        for (char simbolo : letraMorse.toCharArray()) {
            if (simbolo != ArvoreMorse.SIMBOLO_PONTO && simbolo != ArvoreMorse.SIMBOLO_TRACO) {
                throw new IllegalArgumentException("O símbolo '" + simbolo + "' não é válido. Use apenas ponto (.) e traço (-).");
            }
        }
    }
}
