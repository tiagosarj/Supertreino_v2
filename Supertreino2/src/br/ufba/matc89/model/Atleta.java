package br.ufba.matc89.model;

import java.util.List;

public class Atleta extends Usuario{

	private int genero;
	private Medida medidaAtual;
	private List<Medida> historicoMedidas;
	private List<Ciclo> ciclos;
	private Dieta dietaAtual;
	private List<Dieta> historicoDietas;
	
	public Atleta(String nome){
		setNome(nome);
	}
	
	public Medida getMedidaAtual() {
		return medidaAtual;
	}
	public void setMedidaAtual(Medida medidaAtual) {
		this.medidaAtual = medidaAtual;
	}
	public List<Medida> getHistoricoMedidas() {
		return historicoMedidas;
	}
	public void setHistoricoMedidas(List<Medida> historicoMedidas) {
		this.historicoMedidas = historicoMedidas;
	}
	public List<Ciclo> getCiclos() {
		return ciclos;
	}
	public void setCiclos(List<Ciclo> ciclos) {
		this.ciclos = ciclos;
	}
	public Dieta getDietaAtual() {
		return dietaAtual;
	}
	public void setDietaAtual(Dieta dietaAtual) {
		this.dietaAtual = dietaAtual;
	}
	public List<Dieta> getHistoricoDietas() {
		return historicoDietas;
	}
	public void setHistoricoDietas(List<Dieta> historicoDietas) {
		this.historicoDietas = historicoDietas;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
	
}
