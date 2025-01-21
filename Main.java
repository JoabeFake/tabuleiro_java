import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Modo Debug? (true/false)");
        boolean debugMode = input.nextBoolean();
        Jogo jogo = new Jogo(debugMode);

        jogo.IniciarJogo();
    }
}