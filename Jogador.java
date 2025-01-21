import java.util.List;
import java.util.Random;
import java.util.Scanner;

public abstract class Jogador {
    protected static Random random = new Random();
    protected int position;
    protected String cor;
    protected boolean pularRodada;
    protected TipoJogador tipo;
    public enum TipoJogador {
        NORMAL,
        SORTUDO,
        AZARADO
    }

    //Criar Jogadores
    public static Jogador CriarJogador(String cor, boolean debug, TipoJogador escolha){
        TipoJogador tipo = debug ? escolha : TipoJogador.values()[random.nextInt(TipoJogador.values().length)];

        switch(tipo){
            case NORMAL -> {
                return new JogadorNormal(cor);
            }
            case AZARADO -> {
                return new JogadorAzarado(cor);
            }
            case SORTUDO -> {
                return new JogadorSortudo(cor);
            }
            default -> throw new IllegalArgumentException("Tipo de Jogador inválido");
        }
    }

    //mudar tipo de jogador
    public void mudarTipo(){
        TipoJogador[] tipos = TipoJogador.values();
        tipo = tipos[random.nextInt(tipos.length)];
    }

    //escolher outro jogador
    public Jogador escolherJogador(List<Jogador> jogadores){
        System.out.println("Escolha um jogador para voltar ao início:");
        for(int i = 0; i < jogadores.size(); i++){
            System.out.println((i+1)+ "-" + jogadores.get(i).getCor() + " casa: " + jogadores.get(i).getPosition());
        }

        Scanner input = new Scanner(System.in);
        int escolha = input.nextInt()-1;
        input.close();
        return jogadores.get(escolha);
    }

    //Construtor
    public Jogador(String cor){
        this.position = 1;
        this.cor = cor;
    }

    //Rolar Dados
    public abstract int RolarDados();

    //Getters e Setters
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public TipoJogador getTipo(){
        return tipo;
    }

    public String getCor() {
        return cor;
    }

    public boolean getPularRodada() {
        return pularRodada;
    }

    public void setPularRodada(boolean pularRodada) {
        this.pularRodada = pularRodada;
    }
}