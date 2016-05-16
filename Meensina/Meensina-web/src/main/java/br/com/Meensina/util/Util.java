package br.com.Meensina.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import br.com.Meensina.entity.Usuario;

public class Util {

	public static void copyFile(File source, File destination) throws IOException {
        if (destination.exists())
            destination.delete();
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen())
                sourceChannel.close();
            if (destinationChannel != null && destinationChannel.isOpen())
                destinationChannel.close();
       }
   }
	
	public static void createDir(Usuario usuario){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
		new File(scontext.getRealPath("/resources/fotos")).mkdir();
		File dir = new File(scontext.getRealPath("/resources/fotos/" + usuario.getCpf()));
		if (dir.exists()) {
			dir.delete();
		}
		dir.mkdir();
		File source = new File (scontext.getRealPath("/resources/images/default.png"));
		File destination = new File (scontext.getRealPath("/resources/fotos/"+ usuario.getCpf())+"/default.png");
		
		try {
			copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void removerArquivos(File f) {
	     if (f.isDirectory()) {
	         File[] files = f.listFiles();
	         for (File file : files) {
	             removerArquivos(file);
	         }
	     }
	     f.delete();
	  }
	
}
