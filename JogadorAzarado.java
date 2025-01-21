public class JogadorAzarado extends Jogador {
    //Construtor
    public JogadorAzarado(String cor){
        super(cor);
        this.tipo = TipoJogador.AZARADO;
        this.pularRodada = false;
    }

    @Override
    public int RolarDados(){
        return Math.min(6, random.nextInt(6) + random.nextInt(6) + 2);
    }
}