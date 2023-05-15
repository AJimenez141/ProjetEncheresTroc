package fr.eni.projet.dal;

public class DALException extends Exception {

//	Constructors
	public DALException() 
	{
		super();
	}
	
	public DALException(String message)
	{
		super(message);
	}
	
	public DALException(String message, Throwable exception)
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
