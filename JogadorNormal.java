public class JogadorNormal extends Jogador {
    //Constructor
    public JogadorNormal(String cor){
        super(cor);
        this.tipo = TipoJogador.NORMAL;
        this.pularRodada = false;
    }

    @Override
    public int RolarDados(){
        return (random.nextInt(6) + random.nextInt(6) + 2);
    }
}