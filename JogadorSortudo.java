public class JogadorSortudo extends Jogador{
    
    public JogadorSortudo(String cor){
        super(cor);
        this.tipo = TipoJogador.SORTUDO;
        this.pularRodada = false;
    }

    @Override
    public int RolarDados(){
        return Math.max(7, random.nextInt(6) + random.nextInt(6) + 2);
    }
}