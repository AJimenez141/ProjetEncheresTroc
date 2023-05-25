package fr.eni.projet.dal;

public class UtilisateurDALException extends Exception {


	private static final long serialVersionUID = -381986816707372427L;

	//	Constructors
	public UtilisateurDALException() 
	{
		super();
	}
	
	public UtilisateurDALException(String message)
	{
		super(message);
	}
	
	public UtilisateurDALException(String message, Throwable exception)
	{
		super(message, exception);
	}
	
//	Methods
	@Override
	public String getMessage()
	{
		StringBuffer sb = new StringBuffer("Couche DAL - Utilisateur - ");
		sb.append(super.getMessage()); 
		
		return super.getMessage().toString();
	}
	
}
