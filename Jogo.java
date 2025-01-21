import java.util.ArrayList;
import java.util.Scanner;

public class Jogo{
    private boolean isDebugMode;
    private Tabuleiro tabuleiro;
    private ArrayList<Jogador> jogadores;
    private Scanner input;
    
    public Jogo(boolean isDebugMode){
        input = new Scanner(System.in);
        jogadores = new ArrayList<Jogador>();
        tabuleiro = new Tabuleiro(jogadores);
        this.isDebugMode = isDebugMode;
    }

    public boolean jogarTurno(Jogador jogador){
        System.out.println("Vez do Jogador " + jogador.getCor());

        if(jogador.getPularRodada()){
            jogador.setPularRodada(false);
            System.out.println("Jogador " + jogador.getCor() + " Pulou sua vez!");
            return true;
        }

        System.out.println("1. Lançar Dado");
        System.out.println("2. Pular Vez");

        int escolha = input.nextInt();
        int result = 0;

        switch (escolha) {
            case 1:
                if(isDebugMode){
                    System.out.println("Digite a posição de destino:");
                    int destino = input.nextInt();
                    result = destino - jogador.getPosition();
                }else{
                    result = jogador.RolarDados();
                }
                break;
            case 2:
                System.out.println("Jogador " + jogador.getCor() + " Pulou a vez");
                return true;
            default:
                System.out.println("Opção inválida.");
        }

        jogador.setPosition(jogador.getPosition() + result);
        
        System.out.println("\n" + jogador.getCor() + " rolou: " + result);
        System.out.println(jogador.getCor() + " está na casa " + jogador.getPosition());

        tabuleiro.executarCasaEspecial(jogador);

        if(tabuleiro.verificarFimJogo(jogador)){
            System.out.println(jogador.getCor() + " Ganhou!");
            return false;
        }
        return true;
    }

    public void IniciarJogo(){
        //quantidade de jogadores
        System.out.println("Selecione o número de jogadores (2-6):");
        int numJogadores = input.nextInt();
        if(numJogadores > 6 || numJogadores < 2){
            System.out.println("Digite um número de jogadores válido");
            return;
        }

        // Criação de jogadores
        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Digite a cor para o jogador " + (i+1));
            String cor = input.next();
            int escolha = 0;
            if(isDebugMode){
                System.out.println("Digite o tipo de Jogador para o jogador " + (i+1));
                System.out.println("1 - NORMAL");
                System.out.println("2 - SORTUDO");
                System.out.println("3 - AZARADO");                
                escolha = input.nextInt()-1;
            }
            Jogador.TipoJogador tipo = Jogador.TipoJogador.values()[escolha];

            Jogador jogador = Jogador.CriarJogador(cor, isDebugMode, tipo);
            System.out.println("Jogador " + (i+1) + ": " + jogador.getCor() + " - " + jogador.getTipo());
            jogadores.add(jogador);
            
        }


        boolean end = false;
        while (!end) { 
            for(Jogador jogador : jogadores){
                end = !jogarTurno(jogador);
                if(end){
                    break;
                }
            }
        }
    }
}