package br.ufba.matc89.model;

public class Medida extends Entity{
	
	private Double peso;
	private Double altura;
	private Double cintura;
	private Double quadril;	
	private Double subescapular;
	private Double tripicial;
	private Double peitoral;
	private Double axilar_media;
	private Double supra_iliaca;
	private Double abdominal;
	private Double antebraco;
	private String data_afericao;
	
	public Medida(){}
	
	public Medida(Double peso, Double altura){
		setPeso(peso);
		setAltura(altura);		
	}
	
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getAltura() {
		return altura;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public Double getCintura() {
		return cintura;
	}
	public void setCintura(Double cintura) {
		this.cintura = cintura;
	}
	public Double getQuadril() {
		return quadril;
	}
	public void setQuadril(Double quadril) {
		this.quadril = quadril;
	}
	public String getDataAfericao() {
		return data_afericao;
	}
	public void setDataAfericao(String data_afericao) {
		this.data_afericao = data_afericao;
	}
	public void setSubescapular(Double subescapular) {
		this.subescapular = subescapular;
	}

	public Double getSubescapular() {
		return subescapular;
	}

	public void setTripicial(Double tripicial) {
		this.tripicial = tripicial;
	}

	public Double getTripicial() {
		return tripicial;
	}

	public Double getPeitoral() {
		return peitoral;
	}

	public void setPeitoral(Double peitoral) {
		this.peitoral = peitoral;
	}

	public Double getAxilarMedia() {
		return axilar_media;
	}

	public void setAxilarMedia(Double axilar_media) {
		this.axilar_media = axilar_media;
	}

	public Double getAbdominal() {
		return abdominal;
	}

	public void setAbdominal(Double abdominal) {
		this.abdominal = abdominal;
	}

	public Double getAntebraco() {
		return antebraco;
	}

	public void setAntebraco(Double antebraco) {
		this.antebraco = antebraco;
	}

	public Double getImc(){
		return getPeso()/getAltura()*getAltura();
	}

	public void setSupraIliaca(Double supra_iliaca) {
		this.supra_iliaca = supra_iliaca;
	}

	public Double getSupraIliaca() {
		return supra_iliaca;
	}
}
