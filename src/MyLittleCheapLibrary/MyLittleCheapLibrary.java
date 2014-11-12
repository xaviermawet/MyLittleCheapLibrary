package MyLittleCheapLibrary;

import SPF.Chiffrement;
import SPF.CryptoManager;

/**
 *
 * @author Nakim
 */
public class MyLittleCheapLibrary 
{
    public static void main(String[] args) 
    {
        Chiffrement chiffrement = CryptoManager.getChiffrement("AlbertiFamily");
        System.out.println("Chiffrement récupéré : " + chiffrement.toString());
        
        chiffrement = CryptoManager.getChiffrement("Triumvirat");
        System.out.println("Chiffrement récupéré : " + chiffrement.toString());
    }
}
