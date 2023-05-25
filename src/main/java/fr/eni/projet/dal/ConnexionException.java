package fr.eni.projet.dal;

public class ConnexionException extends Exception {

	private static final long serialVersionUID = -3848277146722489342L;

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
