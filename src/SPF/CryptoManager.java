package SPF;

import DB.BeanDBAccessMySQL;
import java.beans.Beans;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nakim
 */

/* Classe non instanciable régissant l'enregistrement 
 * des providers et les accès au service
 */
public class CryptoManager
{
    // <editor-fold defaultstate="collapsed" desc="Constructeur Privé">
    
    // Constructeur privé pour empêcher l'instantiation
    private CryptoManager() 
    {
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fonctions statiques">
    
    public static Chiffrement getChiffrement(String nomProvider)
    {
        CryptoProvider provider = providers.get(nomProvider);
        
        if(provider == null)
            throw new IllegalArgumentException("Provider " + nomProvider + " non trouvé !");
        
        return provider.newChiffrement();
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variables statiques et initialisation">
    // Association nom du provider - Provider implémenté
    private static final Map<String, CryptoProvider> providers;
    
    static
    {
        System.out.println("CryptoManager : Initialisation des variables statiques");
        providers = new ConcurrentHashMap<>();
        
        try
        {            
            // Initialisation du Bean pour accéder à la base de données
            BeanDBAccessMySQL dbaMySQL;
            dbaMySQL = (BeanDBAccessMySQL)Beans.instantiate(
                    null, "DB.BeanDBAccessMySQL");
            dbaMySQL.init();
            
            // Récupération de toutes les données sur les providers
            ResultSet rs = dbaMySQL.selectAll("provider");
            for (int i = 0; rs.next(); i++)
            {
                String providerName      = rs.getString("name");
                String providerClassName = rs.getString("class_name");
                
                // Instanciation du provider
                Class classProvider = Class.forName(providerClassName);
                CryptoProvider provider = (CryptoProvider) classProvider.newInstance();
                
                providers.put(providerName, provider);
            }
        }
        catch (IOException | ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) 
        {
            Logger.getLogger(CryptoManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // </editor-fold>
}
