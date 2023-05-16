package fr.eni.projet.dal;

public class CategorieDALException extends Exception {

	// Constructors
		public CategorieDALException() 
		{
			super();
		}
		
		public CategorieDALException(String message)
		{
			super(message);
		}
		
		public CategorieDALException(String message, Throwable exception)
		{
			super(message, exception);
		}
		
	// Methods
		@Override
		public String getMessage()
		{
			StringBuffer sb = new StringBuffer("Couche DAL - CategorieD - ");
			sb.append(super.getMessage()); 
			
			return super.getMessage().toString();
		}
	}