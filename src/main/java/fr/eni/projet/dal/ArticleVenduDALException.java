package fr.eni.projet.dal;

public class ArticleVenduDALException extends Exception {

// Constructors
	public ArticleVenduDALException() 
	{
		super();
	}
	
	public ArticleVenduDALException(String message)
	{
		super(message);
	}
	
	public ArticleVenduDALException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
// Methods
	@Override
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer("Couche DAL - ArticleVendu - ");
		sb.append(super.getMessage()); 
		
		return super.getMessage().toString();
	}
}