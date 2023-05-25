package fr.eni.projet.dal;

public class EnchereDALException extends Exception {

	private static final long serialVersionUID = -3620075932633086893L;

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
