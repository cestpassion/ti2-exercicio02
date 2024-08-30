package model;

import java.util.Random;
import dao.DAO;

public class Music {
    public int codigo;
    private String nome;
    private String artista;
    private String genero;

    public Random gerador = new Random();

    public Music() {
        this.codigo = 0;
        this.nome = "";
        this.artista = "";
        this.genero = "";
    }

    public Music(String nome, String artista, String genero){
        this.codigo = gerarCodigo();
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
    }
    
    public Music(int codigo){
        this.codigo = codigo;
    }
    
    public Music(int codigo, String nome, String artista, String genero){
        this.codigo = codigo;
        this.nome = nome;
        this.artista = artista;
        this.genero = genero;
    }

    // Início Gerar código
    int gerarCodigo() {
        int newCodigo;
        gerador.setSeed(System.currentTimeMillis());
        
        do {
            newCodigo = gerador.nextInt() % 90000 + 10000;
        } while (verificaRepeticao(newCodigo) == 1);
    
        return newCodigo;
    }

    int verificaRepeticao(int newCodigo) {
    	DAO dao = new DAO();
		dao.conectar();
        
		int[] codigos = dao.getCodigo();
        int tam = codigos.length;

        for(int i = 0; i < tam; i++) {
            if (newCodigo == codigos[i])
            return 1;
        }
        return 0;
    }
    
    public int getCodigo() {
		return codigo;
	}// Fim Gerar código

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

    public String getArtista() {
		return artista;
	}

    public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Music [codigo=" + codigo + ", nome=" + nome + ", artista=" + artista + ", genero=" + genero + "]";
	}	
}