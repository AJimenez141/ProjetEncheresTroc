package fr.eni.projet.dal;

public class ArticleVenduDALException extends Exception {

	private static final long serialVersionUID = -7943201587074670175L;

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