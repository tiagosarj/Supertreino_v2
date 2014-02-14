package br.ufba.matc89.util;

public class ErroUtil {
	public static String erroMensagem;
	public static String erroLocal;

	public static String get() {
		return erroMensagem + " em " + erroLocal + ".";
	}
}
