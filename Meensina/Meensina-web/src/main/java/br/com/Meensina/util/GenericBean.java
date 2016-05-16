package br.com.Meensina.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class GenericBean implements Serializable{

	private static final long serialVersionUID = -856056855939194577L;

	
	public static String getMensagem(String key, Object... args) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ResourceBundle mensagens = ctx.getApplication().getResourceBundle(ctx, "mensagens");
		String msg = mensagens.getString(key);
		return MessageFormat.format(msg, args);
	}

	
}
