package Cesar;

import SPF.Chiffrement;
import SPF.CryptoProvider;

/**
 *
 * @author Nakim
 */
public class Triumvirat implements CryptoProvider
{
    @Override
    public Chiffrement newChiffrement() 
    {
        CryptoCaesar chiffrement = new CryptoCaesar();
        
        // TODO : configurer avec la clé
        
        return chiffrement;
    }
}
