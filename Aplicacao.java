package modelo;

import java.util.Scanner;

import excecao.Explosao;

public class Aplicacao {

    public static void main(String[] args) {
        // Criando scanner para ler entradas do usuário
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Inicializando o tabuleiro com 6x6 e 6 minas
            Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);

            while (true) {
                System.out.println(tabuleiro); // Exibindo o tabuleiro atual

                // Exibindo a pontuação
                System.out.println("Pontuação: " + tabuleiro.getPontuacao());

                System.out.println("Digite a linha (0-5) e a coluna (0-5) para abrir o campo (ex: 1 2): ");
                int linha = scanner.nextInt();
                int coluna = scanner.nextInt();

                try {
                    // Tentar abrir o campo
                    boolean sucesso = tabuleiro.executarAcao(linha, coluna, "abrir");

                    if (sucesso) {
                        System.out.println("Campo aberto com sucesso.");
                    } else {
                        System.out.println("Ação inválida ou erro ao abrir o campo.");
                    }

                } catch (Explosao e) {
                    // O jogo será reiniciado automaticamente após a explosão
                    System.out.println("BOOM! Você abriu uma mina. O jogo será reiniciado.");
                    
                    // Exibe o tabuleiro após reiniciar
                    System.out.println(tabuleiro); 
                    break; // Sai do loop atual e reinicia o jogo
                }

                // Verificando se o objetivo foi alcançado
                if (tabuleiro.objetivoAlcancado()) {
                    System.out.println("Você venceu!");
                    break; // Sai do loop se o jogador vencer
                }
            }
        }
    }
      
}
