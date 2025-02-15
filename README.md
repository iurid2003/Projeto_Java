# Campo_Minado

Campo Minado em Java

Este é um jogo de Campo Minado desenvolvido em Java, onde o objetivo é abrir todos os campos seguros sem detonar nenhuma mina. O jogo inclui funcionalidades como  pontuação dinâmica e reinicialização automática após uma explosão.
📋 Funcionalidades

    Tabuleiro personalizável: Escolha o tamanho do tabuleiro e a quantidade de minas.

    Pontuação dinâmica:

        Ganhe pontos ao abrir campos seguros.

        Ganhe mais pontos ao marcar minas corretamente.

        Perde toda a pontuação ao abrir uma mina.

    Marcação de campos: Marque campos suspeitos de conterem minas.

    Reinicialização automática: O jogo reinicia automaticamente após uma explosão.

    Interface simples: Jogue diretamente no terminal.

🚀 Como Executar o Jogo
Pré-requisitos

    Java Development Kit (JDK) instalado (versão 8 ou superior).

    Um terminal ou prompt de comando.

🎯 Como Jogar

    Início do Jogo:

        O tabuleiro será exibido no terminal.

        Cada campo é representado por:

            #: Campo fechado. 
            
           Número: Quantidade de minas vizinhas.
           Espaço em branco: Campo seguro sem minas vizinhas.

    Ações Disponíveis:

        Abrir um campo:

            Digite a linha e a coluna do campo que deseja abrir.

            Exemplo: 1 2 para abrir o campo na linha 1 e coluna 2.
    Pontuação:

        Ganhe 10 pontos por abrir um campo seguro.
        Perde toda a pontuação ao abrir uma mina.

    Fim do Jogo:

        Vitória: Abra todos os campos seguros sem detonar nenhuma mina.

        Derrota: Abra um campo minado, resultando em uma explosão e reinicialização do jogo.

🛠️ Estrutura do Projeto

    modelo/

        Tabuleiro.java: Gerencia o tabuleiro, minas e pontuação.

        Campo.java: Representa um campo individual no tabuleiro.

        Aplicacao.java: Contém o método main para executar o jogo.

    excecao/

        Explosao.java: Exceção lançada quando uma mina é detonada.

📝 Exemplo de Jogo
Copy

   0 1 2 3 4 5
0  # # # # # #
1  # # # # # #
2  # # # # # #
3  # # # # # #
4  # # # # # #
5  # # # # # #
Pontuação: 0
Digite a linha (0-5) e a coluna (0-5) para abrir o campo (ex: 1 2):

📜 Licença

Este projeto está licenciado sob a MIT License.
👨‍💻 Autor

[iuri dantas]
Seu GitHub
Seu iuri.d2003@aluno.ifsc.edu.br

Divirta-se jogando! 🎉
Se encontrar bugs ou tiver sugestões, sinta-se à vontade para abrir uma issue ou contribuir com o projeto. 😊    
