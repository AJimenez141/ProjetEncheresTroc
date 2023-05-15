package fr.eni.projet.dal;

public class EnchereDALException extends Exception {

//	Constructors
	public EnchereDALException() 
	{
		super();
	}
	
	public EnchereDALException(String message)
	{
		super(message);
	}
	
	public EnchereDALException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
//	Methods
	@Override
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer("Couche DAL - Enchere - ");
		sb.append(super.getMessage()); 
		return super.getMessage().toString();
	}
	
}
