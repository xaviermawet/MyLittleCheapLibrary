package Cesar;

import SPF.Chiffrement;
import SPF.CryptoProvider;

/**
 *
 * @author Nakim
 */
public class Triumvirat implements CryptoProvider
{
    // <editor-fold defaultstate="collapsed" desc="Constructeur">
    public Triumvirat() 
    {
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public Chiffrement newChiffrement() 
    {
        return new CryptoCaesar();
    }
    // </editor-fold>
}
