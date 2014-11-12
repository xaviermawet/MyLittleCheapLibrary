package Cesar;

import SPF.Chiffrement;
import SPF.Cle;
import java.util.Random;

/**
 *
 * @author Nakim
 */
public class CryptoCaesar implements Chiffrement
{
    @Override
    public void init(Cle k) 
    {
        if (!(k instanceof CleCesar))
            throw new IllegalArgumentException("Cle invalide");
        
        CleCesar cle;
        cle = (CleCesar) k;

        // TODO : faire des vérifications, comme voir si le nombre est positif
        
        this.key = cle;
    }
    
    @Override
    public Cle genererCle(int longueur) 
    {
        Random random = new Random();
        int decalage = random.nextInt((longueur * 10) + 1 ) % 127;
        
        if (decalage == 0)
            ++decalage;
        
        return new CleCesar(decalage);
    }

    @Override
    public String crypte(String plainText) 
    {        
        int decalage = this.key.getDecalage();
        
        char cipherText[] = new char[plainText.length()];
        plainText.getChars(0, plainText.length(), cipherText, 0);
        
        for(int i = 0; i < plainText.length(); ++i)
            cipherText[i] += decalage;
        
        return new String(cipherText);
    }

    @Override
    public String decrypte(String cipherText) 
    {
        int decalage = this.key.getDecalage();
        
        char plainText[] = new char[cipherText.length()];
        cipherText.getChars(0, cipherText.length(), plainText, 0);
        
        for(int i = 0; i < cipherText.length(); ++i)
            plainText[i] -= decalage;
        
        return new String(plainText);
    }

    @Override
    public String getProvider() 
    {
        // TODO
        return null;
    }
    
    // Clé utilisée pour chiffrer et/ou déchiffrer
    private CleCesar key = null;
}
