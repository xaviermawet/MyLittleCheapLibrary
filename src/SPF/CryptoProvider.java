package SPF;

/**
 *
 * @author Nakim
 */

// Interface provider de service (Chiffrement)
public interface CryptoProvider 
{
    Chiffrement newChiffrement();
}
