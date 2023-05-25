package fr.eni.projet.dal;

public class RetraitDALException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	Constructors
	public RetraitDALException() 
	{
		super();
	}
	
	public RetraitDALException(String message)
	{
		super(message);
	}
	
	public RetraitDALException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
//	Methods
	@Override
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer("Couche DAL - Retrait - ");
		sb.append(super.getMessage()); 
		
		return super.getMessage().toString();
	}
	
}