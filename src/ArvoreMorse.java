public class ArvoreMorse {

    public static final char SIMBOLO_PONTO = '.';
    public static final char SIMBOLO_TRACO = '-';

    private static final String[][] TABELA_MORSE = {
            {"A", ".-"}, {"B", "-..."}, {"C", "-.-."}, {"D", "-.."}, {"E", "."},
            {"F", "..-."}, {"G", "--."}, {"H", "...."}, {"I", ".."}, {"J", ".---"},
            {"K", "-.-"}, {"L", ".-.."}, {"M", "--"}, {"N", "-."}, {"O", "---"},
            {"P", ".--."}, {"Q", "--.-"}, {"R", ".-."}, {"S", "..."}, {"T", "-"},
            {"U", "..-"}, {"V", "...-"}, {"W", ".--"}, {"X", "-..-"}, {"Y", "-.--"},
            {"Z", "--.."}
    };

    private final NoMorse raiz;

    public ArvoreMorse() {
        this.raiz = new NoMorse();
    }

    public NoMorse getRaiz() {
        return raiz;
    }

    public void carregarAlfabetoPadrao() {
        for (String[] linhaDaTabela : TABELA_MORSE) {
            char letra = linhaDaTabela[0].charAt(0);
            String codigoMorse = linhaDaTabela[1];
            inserirLetra(letra, codigoMorse);
        }
    }

    public void inserirLetra(char letra, String codigoMorse) {
        NoMorse noAtual = raiz;
        for (char simbolo : codigoMorse.toCharArray()) {
            noAtual = descerOuCriar(noAtual, simbolo);
        }
        noAtual.setLetra(Character.toUpperCase(letra));
    }

    private NoMorse descerOuCriar(NoMorse noAtual, char simbolo) {
        if (simbolo == SIMBOLO_PONTO) {
            if (noAtual.getEsquerda() == null) {
                noAtual.setEsquerda(new NoMorse());
            }
            return noAtual.getEsquerda();
        }
        if (noAtual.getDireita() == null) {
            noAtual.setDireita(new NoMorse());
        }
        return noAtual.getDireita();
    }

    public char buscarLetra(String codigoMorse) {
        NoMorse noAtual = raiz;
        for (char simbolo : codigoMorse.toCharArray()) {
            noAtual = descer(noAtual, simbolo);
            if (noAtual == null) {
                return NoMorse.LETRA_VAZIA;
            }
        }
        return noAtual.getLetra();
    }

    private NoMorse descer(NoMorse noAtual, char simbolo) {
        if (simbolo == SIMBOLO_PONTO) {
            return noAtual.getEsquerda();
        }
        if (simbolo == SIMBOLO_TRACO) {
            return noAtual.getDireita();
        }
        return null;
    }

    public String buscarCaminho(char letra) {
        return procurarCaminho(raiz, Character.toUpperCase(letra), "");
    }

    private String procurarCaminho(NoMorse noAtual, char letraProcurada, String caminhoAteAqui) {
        if (noAtual == null) {
            return null;
        }
        if (noAtual.temLetra() && noAtual.getLetra() == letraProcurada) {
            return caminhoAteAqui;
        }
        String caminhoPelaEsquerda = procurarCaminho(noAtual.getEsquerda(), letraProcurada, caminhoAteAqui + SIMBOLO_PONTO);
        if (caminhoPelaEsquerda != null) {
            return caminhoPelaEsquerda;
        }
        return procurarCaminho(noAtual.getDireita(), letraProcurada, caminhoAteAqui + SIMBOLO_TRACO);
    }
}
