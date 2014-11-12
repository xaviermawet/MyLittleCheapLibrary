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
        return new ChiffrePoly();
    }
    
}
