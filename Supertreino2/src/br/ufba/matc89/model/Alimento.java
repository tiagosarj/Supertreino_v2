package br.ufba.matc89.model;

public class Alimento extends Entity {
	protected String nome;
	protected String fonte;
	protected Double carboidrato;
	protected Double proteina;
	protected Double gordura;
	protected Double caloria;

	public Alimento(){}

	public Alimento(String nome, String fonte){
		this.setId(Long.parseLong("-1"));
		this.nome = nome;
		this.fonte = fonte;
		this.carboidrato = 0.0;
		this.proteina = 0.0;
		this.gordura = 0.0;
		this.caloria = 0.0;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFonte() {
		return this.fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Double getCarboidrato() {
		return this.carboidrato;
	}

	public void setCarboidrato(Double carboidrato) {
		this.carboidrato = carboidrato;
	}

	public Double getProteina() {
		return this.proteina;
	}

	public void setProteina(Double proteina) {
		this.proteina = proteina;
	}

	public Double getGordura() {
		return this.gordura;
	}

	public void setGordura(Double gordura) {
		this.gordura = gordura;
	}

	public Double getCaloria() {
		return this.caloria;
	}

	public void setCaloria(Double caloria) {
		this.caloria = caloria;
	}
	
	@Override
	public String toString(){
		return this.nome;
	}
}
