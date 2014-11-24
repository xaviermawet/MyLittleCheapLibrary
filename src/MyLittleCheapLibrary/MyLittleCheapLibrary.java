package MyLittleCheapLibrary;

import SPF.Integrity.ByteArrayList;
import SPF.Crypto.Chiffrement;
import SPF.Cle;
import SPF.Crypto.CryptoManager;
import SPF.Integrity.Integrity;
import SPF.Integrity.IntegrityManager;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Date;

/**
 *
 * @author Nakim
 */
public class MyLittleCheapLibrary 
{
    public static void main(String[] args) throws Exception 
    {
        System.out.println("-------------------------------------------------");
        System.out.println("|              Service Chiffrement              |");
        System.out.println("-------------------------------------------------");
        System.out.println("");
        
        /* Alberti */
        System.out.println("---------------------Alberti---------------------");
        
        Chiffrement chiffrement = CryptoManager.getChiffrement("AlbertiFamily");
        Cle cle = chiffrement.genererCle(5);
        
        chiffrement.init(cle);
        String msg = "Hello World";
        System.out.println("Message clair    : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("Message crypté   : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("Message décrypté : " + msg);
        
        /* Cesar */
        System.out.println("----------------------Cesar----------------------");
        
        chiffrement = CryptoManager.getChiffrement("Triumvirat");
        cle = chiffrement.genererCle(3);
        
        chiffrement.init(cle);
        msg = "Hello World";
        System.out.println("Message clair    : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("Message crypté   : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("Message décrypté : " + msg);
        
        /* DES */
        System.out.println("-----------------------DES-----------------------");
        
        chiffrement = CryptoManager.getChiffrement("ProCrypto");
        cle = chiffrement.genererCle(64);
        
        chiffrement.init(cle);
        msg = "Hello World";
        System.out.println("Message clair    : " + msg);
        msg = chiffrement.crypte(msg);
        System.out.println("Message crypté   : " + msg);
        msg = chiffrement.decrypte(msg);
        System.out.println("Message décrypté : " + msg);
        
        System.out.println("");
        System.out.println("-------------------------------------------------");
        System.out.println("|               Service Integrity               |");
        System.out.println("-------------------------------------------------");
        System.out.println("");
        
        Integrity integrity = IntegrityManager.getIntegrity("SHA1MawetProvider");
        msg = "Hello World";
        byte[] hash = integrity.makeCheck(msg);
        System.out.println("Hash : " + new String(hash));
        boolean valid = integrity.verifyCheck(msg, hash);
        System.out.println("hash valid for " + msg + " : " + valid);
        
        ByteArrayList salt = new ByteArrayList();
        salt.add("RandomString".getBytes());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        long time = (new Date()).getTime();
        dos.writeLong(time);
        salt.add(baos.toByteArray());
        
        hash = integrity.makeCheck(msg, salt);
        System.out.println("Hash : " + new String(hash));
        valid = integrity.verifyCheck(msg, salt, hash);
        System.out.println("hash valid for " + msg + " : " + valid);
    }
}
