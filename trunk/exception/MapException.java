/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author disavinr
 */
public class MapException extends Exception{

	public MapException(String str) {
		System.err.println("La map "+ str +" n'a pas été trouvée");
	}
}
