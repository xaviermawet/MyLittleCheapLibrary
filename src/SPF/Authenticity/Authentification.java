package SPF.Authenticity;

import SPF.Cle;

/**
 *
 * @author Nakim
 */

// Interface de service
public interface Authentification
{
    void init(Cle k);
    void makeAuthenticate(String plainText);
    void verifyAuthenticate(String cipherText);
    String getProvider();
}
