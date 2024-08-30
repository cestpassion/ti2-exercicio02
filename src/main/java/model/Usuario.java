package model;

import java.util.Random;

import dao.DAO;

public class Usuario{
    public int codigo;
    private String nome;
    private String email;
    private String senha;

    public Random gerador = new Random();

    public Usuario() {
        this.codigo = 0;
        this.nome = "";
        this.email = "";
        this.senha = "";
    }

    public Usuario(String nome, String email, String senha){
        this.codigo = gerarCodigo();
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    
    public Usuario(int codigo){
        this.codigo = codigo;
    }
    
    public Usuario(int codigo, String nome, String email, String senha){
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
		return email;
	}

    public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}	
}