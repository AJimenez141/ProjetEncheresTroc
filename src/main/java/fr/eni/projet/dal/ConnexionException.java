package fr.eni.projet.dal;

public class ConnexionException extends Exception {
//	Constructors
	public ConnexionException() 
	{
		super();
	}
	
	public ConnexionException(String message)
	{
		super(message);
	}
	
	public ConnexionException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
//	Methods
	@Override
	public String getMessage()
	{
		
		return super.getMessage().toString();
	}
}
