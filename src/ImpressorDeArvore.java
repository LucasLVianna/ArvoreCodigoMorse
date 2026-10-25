public class ImpressorDeArvore {

    private static final String TEXTO_DA_RAIZ = "(raiz)";
    private static final String RECUO_VAZIO = "    ";
    private static final String RECUO_COM_LINHA = "│   ";
    private static final String GALHO_DE_CIMA = "┌── ";
    private static final String GALHO_DE_BAIXO = "└── ";

    public void imprimirArvore(ArvoreMorse arvore) {
        NoMorse raiz = arvore.getRaiz();
        imprimirNo(raiz.getDireita(), RECUO_VAZIO, true);
        System.out.println(TEXTO_DA_RAIZ);
        imprimirNo(raiz.getEsquerda(), RECUO_VAZIO, false);
    }

    private void imprimirNo(NoMorse no, String recuo, boolean ehFilhoDaDireita) {
        if (no == null) {
            return;
        }
        String recuoDeCima = recuo + (ehFilhoDaDireita ? RECUO_VAZIO : RECUO_COM_LINHA);
        String recuoDeBaixo = recuo + (ehFilhoDaDireita ? RECUO_COM_LINHA : RECUO_VAZIO);
        imprimirNo(no.getDireita(), recuoDeCima, true);
        System.out.println(montarLinha(no, recuo, ehFilhoDaDireita));
        imprimirNo(no.getEsquerda(), recuoDeBaixo, false);
    }

    private String montarLinha(NoMorse no, String recuo, boolean ehFilhoDaDireita) {
        String galho = ehFilhoDaDireita ? GALHO_DE_CIMA : GALHO_DE_BAIXO;
        char simbolo = ehFilhoDaDireita ? ArvoreMorse.SIMBOLO_TRACO : ArvoreMorse.SIMBOLO_PONTO;
        String linha = recuo + galho + "(" + simbolo + ")";
        if (no.temLetra()) {
            linha += " " + no.getLetra();
        }
        return linha;
    }
}
