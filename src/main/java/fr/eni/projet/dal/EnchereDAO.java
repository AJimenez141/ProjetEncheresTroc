package fr.eni.projet.dal;

public interface EnchereDAO {
	/**
	 * selectionner une enchere
	 * 
	 * @param pArticleVenduId
	 * @return
	 * @throws EnchereDALException
	 */
	public Enchere selectById(int pArticleVenduId) throws EnchereDALException;
	
	/**
	 * selectionner toutes les encheres en cours
	 * 
	 * @return
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectAll() throws EnchereDALException;
	
	/**
	 * selectionner les encheres d'un utilisateur
	 * 
	 * @param pUtilisateurId
	 * @return
	 * @throws EnchereDALException
	 */
	public List<Enchere> selectEnchereByUtilisateur(int pUtilisateurId) throws EnchereDALException;
	
	/**
	 * effectuer une enchere
	 * 
	 * @param pEnchereId
	 * @throws EnchereDALException
	 */
	public void encherir(Enchere pEnchere) throws EnchereDALException;	
}
