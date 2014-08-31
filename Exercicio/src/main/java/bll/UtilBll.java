package bll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Classe utilit�rios implementando Singleton  
 * @author Cristiano Momesso
 */
public class UtilBll {
	private static UtilBll instancia;
	private	Properties props = new Properties();
	private FileInputStream fis = null;  
	private UtilBll() throws IOException {
		File file = new File("conf//conf.properties");
		fis = new FileInputStream(file);  
	    props.load(fis);    
	    fis.close();  
	    
	}

	

	/**
	 * M�todo que implementa o singleton , sincronizando o com inst�ncia j� existente 
	 * @author Cristiano Momesso
	 */
	public static synchronized UtilBll getInstance() throws IOException {
		if (instancia == null) {
			instancia = new UtilBll();
		}
		return instancia;
	}
	

	/**
	 * M�todo que retorna valores contidos no arquivo de propriedades.
	 * 
	 * @author Cristiano Momesso
	 * @param String - String com a chave a ser buscada no arquivo de propriedades 
	 * @return String - retorna String contida no arquivo de propriedades
	 */
	public String getConfProperties(String msg){
		return props.getProperty(msg);
	}
}
