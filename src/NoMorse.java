public class NoMorse {

    public static final char LETRA_VAZIA = ' ';

    private char letra;
    private NoMorse esquerda;
    private NoMorse direita;

    public NoMorse() {
        this.letra = LETRA_VAZIA;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public boolean temLetra() {
        return letra != LETRA_VAZIA;
    }

    public NoMorse getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoMorse esquerda) {
        this.esquerda = esquerda;
    }

    public NoMorse getDireita() {
        return direita;
    }

    public void setDireita(NoMorse direita) {
        this.direita = direita;
    }
}
