package org.enset1.exceptions;

public class ErrorCreatingUser extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ErrorCreatingUser(String message) {
		super("impossible d'ajouter l'utilisateur : "+ message);
	}
	
	
	

}
