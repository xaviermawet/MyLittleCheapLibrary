package DES;

import SPF.Chiffrement;
import SPF.CryptoProvider;

/**
 *
 * @author Nakim
 */
public class ProCrypto implements CryptoProvider
{
    // <editor-fold defaultstate="collapsed" desc=" Constructeur ">
    public ProCrypto()
    {
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public Chiffrement newChiffrement()
    {
        return new CryptoDES();
    }
    // </editor-fold>
}
