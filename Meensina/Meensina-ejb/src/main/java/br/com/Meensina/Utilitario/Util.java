package br.com.Meensina.Utilitario;

public final class Util {

	
	public static String limpaMascara(String str){
		
		return str.replaceAll("\\.|-|/", "");
	}
}
