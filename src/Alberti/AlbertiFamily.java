package Alberti;

import SPF.Chiffrement;
import SPF.CryptoProvider;

/**
 *
 * @author Nakim
 */
public class AlbertiFamily implements CryptoProvider
{
    // <editor-fold defaultstate="collapsed" desc=" Constructeur ">
    public AlbertiFamily()    
    {
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Overrided methods ">
    @Override
    public Chiffrement newChiffrement() 
    {
        return new ChiffrePoly();
    }
    // </editor-fold>
}
