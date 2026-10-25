public class Principal {

    public static void main(String[] args) {
        ArvoreMorse arvore = new ArvoreMorse();
        arvore.carregarAlfabetoPadrao();

        Decodificador decodificador = new Decodificador(arvore);
        Codificador codificador = new Codificador(arvore);
        ImpressorDeArvore impressor = new ImpressorDeArvore();
        ReprodutorDeSom reprodutor = new ReprodutorDeSom();

        MenuDoConsole menu = new MenuDoConsole(arvore, decodificador, codificador, impressor, reprodutor);
        menu.iniciar();
    }
}
