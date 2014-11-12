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
        Cle cle = chiffrement.genererCle(5);
        
        chiffrement.init(cle);
        String msg = "Hello World";
        System.out.println("Message clair    : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("Message crypté   : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("Message décrypté : " + msg);
        
        System.out.println("----------------------------------");
        
        /* Cesar */
        chiffrement = CryptoManager.getChiffrement("Triumvirat");
        cle = chiffrement.genererCle(3);
        
        chiffrement.init(cle);
        msg = "Hello World";
        System.out.println("Message clair    : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("Message crypté   : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("Message décrypté : " + msg);
    }
}
