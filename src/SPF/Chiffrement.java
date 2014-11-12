package SPF;

/**
 *
 * @author Nakim
 */

// Interface de service
public interface Chiffrement 
{
    void init(Cle k);
    void crypte(String plainText);
    void decrypte(String cipherText);
    String getProvider();
}
