package modelo;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import excecao.Explosao;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;
    private int pontuacao; // Adicionando a variável de pontuação

    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;
        this.pontuacao = 0; // Inicializando a pontuação com 0

        gerarCampos();
        associarVizinhos();
        sortearMinas();
    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adicionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        int minasArmadas = 0;
        Random random = new Random();

        do {
            int aleatorio = random.nextInt(campos.size());
            Campo campoSorteado = campos.get(aleatorio);
            if (!campoSorteado.isMinado()) {
                campoSorteado.minar();
                minasArmadas++;
            }
        } while (minasArmadas < minas);
    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(Campo::objetivoAlcancado);
    }

    public void reiniciar() {
        campos.forEach(Campo::reiniciar);
        sortearMinas();
        pontuacao = 0; // Reinicia a pontuação quando o jogo for reiniciado
    }

    public int getPontuacao() {
        return pontuacao;
    }

    // Método para atualizar a pontuação
    private void aumentarPontuacao(int pontos) {
        pontuacao += pontos;
    }

    private void diminuirPontuacao(int pontos) {
        pontuacao -= pontos;
    }

    public boolean executarAcao(int linha, int coluna, String acao) {
        try {
            Campo campo = campos.stream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Posição inválida"));

            if (acao.equalsIgnoreCase("abrir")) {
                boolean sucesso = campo.abrir();
                if (sucesso) {
                    // Se o campo for aberto com sucesso e não houver mina, ganha pontos
                    if (!campo.isMinado()) {
                        aumentarPontuacao(10); // Ganha 10 pontos por abrir um campo seguro
                    }
                    return true;
                } else {
                    return false;
                }
            } else if (acao.equalsIgnoreCase("marcar")) {
                campo.alternarMarcacao();
                // Se o campo for marcado e for uma mina, ganha pontos
                if (campo.isMinado() && campo.objetivoAlcancado()) {
                    aumentarPontuacao(10); // Ganha 20 pontos por marcar uma mina corretamente
                }
                return true;
            } else {
                throw new IllegalArgumentException("Ação inválida: Use 'abrir' ou 'marcar'");
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Explosao e) {
            // Se explodir, perde pontos
            diminuirPontuacao(pontuacao); // Perde toda a pontuação obtida
            System.out.println("BOOM! Você abriu uma mina. O jogo será reiniciado.");
            
            // Reinicia o tabuleiro após a explosão
            reiniciar();
            
            return false; // Retorna false para indicar que a ação não foi bem-sucedida
        }
    }

   

    @Override
    public String toString() {
        int i = 0;
        StringBuilder sb = new StringBuilder();

        // Exibindo a numeração das colunas
        sb.append("   ");
        for (int c = 0; c < colunas; c++) {
            sb.append(c).append(" ");
        }
        sb.append("\n");

        for (int l = 0; l < linhas; l++) {
            sb.append(l).append("  "); // Numeração das linhas

            for (int c = 0; c < colunas; c++) {
                sb.append(campos.get(i)).append(" "); // Adicionando o campo
                i++;
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
