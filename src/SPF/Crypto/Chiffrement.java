package SPF.Crypto;

import SPF.Cle;

/**
 *
 * @author Nakim
 */

// Interface de service
public interface Chiffrement 
{
    void init(Cle k);
    Cle genererCle(int longueur);
    String crypte(String plainText);
    String decrypte(String cipherText);
    String getProvider();
}
