package Alberti;

import SPF.Chiffrement;
import SPF.CryptoProvider;

/**
 *
 * @author Nakim
 */
public class AlbertiFamily implements CryptoProvider
{
    @Override
    public Chiffrement newChiffrement() 
    {
        ChiffrePoly chiffrement = new ChiffrePoly();
        
        // TODO : le configurer avec la clé
        
        return chiffrement;
    }
    
}
