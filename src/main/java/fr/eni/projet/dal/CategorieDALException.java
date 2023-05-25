package fr.eni.projet.dal;

public class CategorieDALException extends Exception {

		private static final long serialVersionUID = 1994704627016284633L;

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