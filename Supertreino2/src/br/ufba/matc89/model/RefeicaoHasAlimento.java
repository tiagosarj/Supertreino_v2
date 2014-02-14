package br.ufba.matc89.model;

public class RefeicaoHasAlimento extends Entity {

	private Double quantidade;
	private int id_alimento;
	private int id_refeicao;
	
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double d) {
		this.quantidade = d;
	}
	public int getIdAlimento() {
		return id_alimento;
	}
	public void setIdAlimento(int id_alimento) {
		this.id_alimento = id_alimento;
	}
	public int getIdRefeicao() {
		return id_refeicao;
	}
	public void setIdRefeicao(int id_refeicao) {
		this.id_refeicao = id_refeicao;
	}
}
