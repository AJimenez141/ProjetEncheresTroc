package fr.eni.projet.bll;

public class BLLException extends Exception {

//	Constructors
	public BLLException() 
	{
		super();
	}
	
	public BLLException(String message)
	{
		super(message);
	}
	
	public BLLException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
//	Methods
	@Override
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer("Couche BLL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
	
}