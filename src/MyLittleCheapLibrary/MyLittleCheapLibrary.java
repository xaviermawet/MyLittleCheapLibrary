package MyLittleCheapLibrary;

import SPF.Chiffrement;
import SPF.Cle;
import SPF.CryptoManager;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

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
        
        System.out.println("----------------------------------");
        
        /* DES */
        chiffrement = CryptoManager.getChiffrement("ProCrypto");
        cle = chiffrement.genererCle(64);
        
        chiffrement.init(cle);
        msg = "Hello World";
        System.out.println("Message clair    : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("Message crypté   : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("Message décrypté : " + msg);
    }
}
