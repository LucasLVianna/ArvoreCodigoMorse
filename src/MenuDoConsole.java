import java.util.Scanner;
import javax.sound.sampled.LineUnavailableException;

public class MenuDoConsole {

    private static final String OPCAO_DECODIFICAR = "1";
    private static final String OPCAO_CODIFICAR = "2";
    private static final String OPCAO_MOSTRAR_ARVORE = "3";
    private static final String OPCAO_SAIR = "4";
    private static final String RESPOSTA_SIM = "sim";
    private static final String RESPOSTA_SIM_CURTA = "s";

    private final ArvoreMorse arvore;
    private final Decodificador decodificador;
    private final Codificador codificador;
    private final ImpressorDeArvore impressor;
    private final ReprodutorDeSom reprodutor;
    private final Scanner leitor;

    public MenuDoConsole(ArvoreMorse arvore, Decodificador decodificador, Codificador codificador, ImpressorDeArvore impressor, ReprodutorDeSom reprodutor) {
        this.arvore = arvore;
        this.decodificador = decodificador;
        this.codificador = codificador;
        this.impressor = impressor;
        this.reprodutor = reprodutor;
        this.leitor = new Scanner(System.in);
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            mostrarOpcoes();
            String opcaoEscolhida = lerLinha();
            if (opcaoEscolhida == null) {
                break;
            }
            continuar = executarOpcao(opcaoEscolhida.trim());
        }
        System.out.println("Até mais!");
    }

    private void mostrarOpcoes() {
        System.out.println();
        System.out.println("===== Código Morse =====");
        System.out.println(OPCAO_DECODIFICAR + " - Decodificar Morse para texto");
        System.out.println(OPCAO_CODIFICAR + " - Codificar texto para Morse");
        System.out.println(OPCAO_MOSTRAR_ARVORE + " - Mostrar a árvore");
        System.out.println(OPCAO_SAIR + " - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private boolean executarOpcao(String opcaoEscolhida) {
        switch (opcaoEscolhida) {
            case OPCAO_DECODIFICAR -> decodificar();
            case OPCAO_CODIFICAR -> codificar();
            case OPCAO_MOSTRAR_ARVORE -> impressor.imprimirArvore(arvore);
            case OPCAO_SAIR -> {
                return false;
            }
            default -> System.out.println("Opção inválida. Digite um número de " + OPCAO_DECODIFICAR + " a " + OPCAO_SAIR + ".");
        }
        return true;
    }

    private void decodificar() {
        System.out.println("Separe as letras com um espaço e as palavras com \" / \".");
        System.out.print("Digite o código Morse: ");
        String textoMorse = lerLinha();
        try {
            System.out.println("Texto decodificado: " + decodificador.decodificarFrase(textoMorse));
        } catch (IllegalArgumentException erro) {
            System.out.println("Não foi possível decodificar: " + erro.getMessage());
        }
    }

    private void codificar() {
        System.out.print("Digite o texto: ");
        String codigoMorse = tentarCodificar(lerLinha());
        if (codigoMorse != null) {
            System.out.println("Código Morse: " + codigoMorse);
            perguntarSeQuerOuvir(codigoMorse);
        }
    }

    private String tentarCodificar(String texto) {
        try {
            return codificador.codificarFrase(texto);
        } catch (IllegalArgumentException erro) {
            System.out.println("Não foi possível codificar: " + erro.getMessage());
            return null;
        }
    }

    private void perguntarSeQuerOuvir(String codigoMorse) {
        System.out.print("Deseja ouvir o resultado? (s/n): ");
        String resposta = lerLinha();
        if (resposta == null) {
            return;
        }
        String respostaLimpa = resposta.trim();
        if (respostaLimpa.equalsIgnoreCase(RESPOSTA_SIM_CURTA) || respostaLimpa.equalsIgnoreCase(RESPOSTA_SIM)) {
            tocarSom(codigoMorse);
        }
    }

    private void tocarSom(String codigoMorse) {
        System.out.println("Tocando o código Morse...");
        try {
            reprodutor.tocarCodigoMorse(codigoMorse);
        } catch (LineUnavailableException | IllegalArgumentException | SecurityException erro) {
            System.out.println("O som não está disponível neste computador. O programa vai continuar normalmente.");
        }
    }

    private String lerLinha() {
        if (!leitor.hasNextLine()) {
            return null;
        }
        return leitor.nextLine();
    }
}
