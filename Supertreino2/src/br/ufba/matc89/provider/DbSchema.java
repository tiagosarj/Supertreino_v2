package br.ufba.matc89.provider;

interface DbSchema {
	String DB_NAME = "supertreino";
	
	String TBL_ATLETA = "atleta";
	String TBL_MEDIDAS = "medida";
	String TBL_DIETAS = "dieta";
	String TBL_REFEICOES = "refeicao";
	String TBL_REFEICAO_ALIMENTO = "refeicao_alimento";
	String TBL_ALIMENTOS = "alimento";
	String TBL_TREINOS = "treino";
	String TBL_EXERCICIOS = "exercicio";
}
