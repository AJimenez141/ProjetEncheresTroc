package fr.eni.projet.dal;

public class DALException extends Exception {

	private static final long serialVersionUID = 5908397361511142446L;

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
