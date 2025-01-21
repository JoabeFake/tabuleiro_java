import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Tabuleiro{
    private static final int NUM_CASAS = 40;
    private final int[] casas;
    private final Map<Integer, CasasEspecial> casasEspeciais;
    private static List<Jogador> jogadores;
    
    //Construtor
    public Tabuleiro(List<Jogador> jogadores){
        this.casas = new int[NUM_CASAS];
        this.casasEspeciais = new HashMap<>();
        this.jogadores = jogadores;
        iniciarCasas();
        iniciarCasasEspeciais();
    }
    
    //Iniciar Casas normais
    private void iniciarCasas(){
        for(int i = 0; i < NUM_CASAS; i++){
            casas[i] = i + 1;
        }
    }

    //Iniciar Casas Especiais;
    private void iniciarCasasEspeciais(){
        casasEspeciais.put(10, CasaEspecialImpl.PULAR_RODADA);
        casasEspeciais.put(25, CasaEspecialImpl.PULAR_RODADA);
        casasEspeciais.put(38, CasaEspecialImpl.PULAR_RODADA);
        casasEspeciais.put(13, CasaEspecialImpl.MUDAR_TIPO);
        casasEspeciais.put(5, CasaEspecialImpl.AVANCAR_TRES_CASAS);
        casasEspeciais.put(15, CasaEspecialImpl.AVANCAR_TRES_CASAS);
        casasEspeciais.put(30, CasaEspecialImpl.AVANCAR_TRES_CASAS);
        casasEspeciais.put(17, CasaEspecialImpl.VOLTAR_AO_INICIO);
        casasEspeciais.put(27, CasaEspecialImpl.VOLTAR_AO_INICIO);
        casasEspeciais.put(20, CasaEspecialImpl.TROCAR_COM_MAIS_ATRAS);
        casasEspeciais.put(35, CasaEspecialImpl.TROCAR_COM_MAIS_ATRAS);
    }
    
    //Verificar Fim de Jogo
    public boolean verificarFimJogo(Jogador player){
        return player.getPosition() >= NUM_CASAS;
    }

    //verifica posicao do jogador e executa efeito da casa especial
    public boolean executarCasaEspecial(Jogador jogador){
        CasasEspecial casaEspecial = casasEspeciais.get(jogador.getPosition());
        if(casaEspecial != null){
            casaEspecial.executar(jogador);
            return true;
        }
        return false;
    }

    //Efeito da casa especial de trocar com o jogador mais atras
    private static void trocarComMaisAtras(Jogador jogadorAtual){
        Jogador maisAtras = encontrarUltimoJogador(jogadores, jogadorAtual);
        if(maisAtras != null){
            int posAtual = jogadorAtual.getPosition();
            jogadorAtual.setPosition(maisAtras.getPosition());
            maisAtras.setPosition(posAtual);

            System.out.println("Jogador " + jogadorAtual.getCor() + " Trocou de lugar com o Jogador " + maisAtras.getCor());
        }
    }

    //Algoritmo pra encontrar o jogador que está mais atrás no tabuleiro
    private static Jogador encontrarUltimoJogador(List<Jogador> jogadores, Jogador jogadorAtual){
        return jogadores.stream()
        .filter(jogador -> !jogador.equals(jogadorAtual) && jogador.getPosition() < jogadorAtual.getPosition())
        .min(Comparator.comparingInt(Jogador::getPosition))
        .orElse(null);
    }
    
    private enum CasaEspecialImpl implements CasasEspecial{
        PULAR_RODADA{
            @Override
            public void executar(Jogador jogador){
                jogador.setPularRodada(true);
                System.out.println("Pule a próxima rodada");
            }
        },
        MUDAR_TIPO{
            @Override
            public void executar(Jogador jogador){
                jogador.mudarTipo();
                System.out.println("Jogador " + jogador.getCor() + " Mudou seu tipo para: " + jogador.getTipo());
            }
        },
        VOLTAR_AO_INICIO{
            @Override
            public void executar(Jogador jogador) {
            Jogador jogadorVoltar = jogador.escolherJogador(jogadores);
            if (jogadorVoltar != null) {
                jogadorVoltar.setPosition(1);
                System.out.println(jogadorVoltar.getCor() + " Voltou ao início");
            }
        }
        },
        TROCAR_COM_MAIS_ATRAS{
            @Override
            public void executar(Jogador jogador) {
                trocarComMaisAtras(jogador);
            }
        },
        AVANCAR_TRES_CASAS{
            @Override
            public void executar(Jogador jogador){
                jogador.setPosition(jogador.getPosition() + 3);
                System.out.println("Casa da Sorte! Jogador " + jogador.getCor() + " Avançou 3 casas");
            }
        }
    }
}