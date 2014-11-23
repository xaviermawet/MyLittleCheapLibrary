package SHA1Simple;

import SPF.Integrity;
import SPF.IntegrityProvider;

/**
 *
 * @author Nakim
 */
public class SHA1MawetProvider implements IntegrityProvider
{
    // <editor-fold defaultstate="collapsed" desc=" Constructeur ">

    public SHA1MawetProvider()
    {
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overrided methods">
    @Override
    public Integrity newIntegrity()
    {
        return new SHA1Mawet();
    }
    // </editor-fold>
}
