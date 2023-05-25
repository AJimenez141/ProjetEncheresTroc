package fr.eni.projet.bll;

public class BLLException extends Exception {

	private static final long serialVersionUID = 3282722952173825409L;

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