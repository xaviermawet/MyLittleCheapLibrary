package MyLittleCheapLibrary;

import SPF.Chiffrement;
import SPF.Cle;
import SPF.CryptoManager;

/**
 *
 * @author Nakim
 */
public class MyLittleCheapLibrary 
{
    public static void main(String[] args) 
    {
        /* Alberti */
        Chiffrement chiffrement = CryptoManager.getChiffrement("AlbertiFamily");
        System.out.println("Chiffrement récupéré : " + chiffrement.toString());
        
        Cle cle = chiffrement.genererCle(5);
        System.out.println(cle);
        
        chiffrement.init(cle);
        
        /* Cesar */
        chiffrement = CryptoManager.getChiffrement("Triumvirat");
        System.out.println("Chiffrement récupéré : " + chiffrement.toString());
        
        cle = chiffrement.genererCle(3); // génère une clé composée de 3 chiffres % 127
        System.out.println(cle);
        
        chiffrement.init(cle); // Doit générer une exception
        String msg = "Hello";
        System.out.println("Clair : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("crypté : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("décrypté : " + msg);
    }
}
