package modelo;

import java.util.ArrayList;
import java.util.List;

import excecao.Explosao;

public class Campo {
    private final int linha;
    private final int coluna;

    private boolean minado  = false;
    private boolean aberto  = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<>();

    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    /*Adicionar Vizinho*/
    /**/
    boolean adicionarVizinho(Campo vizinho){
        boolean linhaDiferente = linha != vizinho.linha ;
        boolean ColunaDiferente = linha != vizinho.coluna ;
        boolean diagonal = linhaDiferente && ColunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaGeral = Math.abs(coluna - vizinho.coluna);

        if(deltaLinha == 1 && !diagonal){
            vizinhos.add(vizinho);
            return  true;
        }else if(deltaGeral == 2 && diagonal){
            vizinhos.add(vizinho);
            return  true;
        }else
        return false;
    }

    void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;
        }
    }


    /**/

    boolean abrir() {

        if (!aberto && !marcado) {
            aberto = true;

            if (minado) {
                throw new Explosao();
            }
            if (vizinhacaSegura()) {
                vizinhos.forEach(v -> v.abrir());
            }
            return true;
        }
        return false;
    }

    boolean vizinhacaSegura(){
        return  vizinhos.stream().noneMatch(v -> v.minado);
    }
    
    void minar() {
    	minado = true ;
    }
    
    public boolean isMinado() {
    	return minado; 
    }
       
    public boolean isAberto() {
    	return aberto;
    }
   
    public boolean isFechado() {
    	return !isAberto();
    }
    
    public int getLinha() {
    	return linha;
    }
    
    public int getColuna() {
    	return coluna;
    }
    
    boolean objetivoAlcancado() {
    	boolean desvendado = !minado && aberto;
    	boolean protegido =  minado && marcado ;
		return protegido || desvendado;
    }
    
    long minasNaVizinhaca() {
    	return vizinhos.stream().filter(v -> v.minado).count();
    }
    
    public void reiniciar() {
        aberto = false;
        minado = false;
        marcado = false;
    }

    
    public String toString() {
    	if(marcado) {
    		return "*";
    	}else if(aberto && minasNaVizinhaca() > 0) {
    		return Long.toString(minasNaVizinhaca());
    	}else if(aberto && minado) {
    		return "*";
    	}else if(aberto) {
    		return " ";
    	}else {
    		return "#";
    	}
    }
    
}

