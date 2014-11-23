package DES;

import SPF.Chiffrement;
import SPF.Cle;
import java.security.*;
import java.util.Base64;
import java.util.logging.*;
import javax.crypto.*;

/**
 *
 * @author Nakim
 */
public class CryptoDES implements Chiffrement
{
    // <editor-fold defaultstate="collapsed" desc=" Constructeur ">
    public CryptoDES()
    {
        try
        {
            // Get cipher Object
            this.cipher = Cipher.getInstance(
                algorithm + "/" + cipherMode + "/" + padding,
            provider);
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex)
        {
            Logger.getLogger(CryptoDES.class.getName()).log(Level.SEVERE, null, ex);
            
            this.cipher = null;
        }
        finally
        {
            this.opmode = -1;
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Overrided methods ">
    @Override
    public void init(Cle k)
    {
        if (!(k instanceof CleDES))
            throw new IllegalArgumentException("Cle invalide");
        
        if(this.cipher == null)
            throw new NullPointerException("No cipher object available");
        
        CleDES DESKey;
        DESKey = (CleDES) k;
        
        this.key = DESKey;
    }

    @Override
    public Cle genererCle(int longueur)
    {
        try
        {
            // Get key generator
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, provider);
            
            /* Initializes this key generator for a certain keysize (longueur) 
             * using a user-provided source of randomness (new SecureRandom())*/
            keyGenerator.init(longueur, new SecureRandom());
            
            // Get key
            SecretKey secretKey = keyGenerator.generateKey();
            
            return new CleDES(secretKey, longueur);
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException ex)
        {
            Logger.getLogger(CryptoDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String crypte(String plainText)
    {
        // Check if cipher and key exists
        if(this.cipher == null)
            throw new NullPointerException("No cipher object available");
        
        if(this.key == null)
            throw new NullPointerException("No key object available");
        
        try
        {
            if (this.opmode != Cipher.ENCRYPT_MODE)
            {
                this.opmode = Cipher.ENCRYPT_MODE;
                this.cipher.init(this.opmode, this.key.getSecretKey());
            }
            
            byte[] cipherTextByteArray = this.cipher.doFinal(plainText.getBytes());
            byte[] cipherTextByteArrayBase64 = Base64.getEncoder().encode(cipherTextByteArray);
            
            return new String(cipherTextByteArrayBase64);
        }
        catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(CryptoDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String decrypte(String cipherText)
    {
        // Check if cipher and key exists
        if(this.cipher == null)
            throw new NullPointerException("No cipher object available");
        
        if(this.key == null)
            throw new NullPointerException("No key object available");
        
        try
        {
            if (this.opmode != Cipher.DECRYPT_MODE)
            {
                this.opmode = Cipher.DECRYPT_MODE;
                this.cipher.init(this.opmode, this.key.getSecretKey());
            }
            
            byte[] encryptedByteArray = Base64.getDecoder().decode(cipherText.getBytes());
            byte[] decodedByte = this.cipher.doFinal(encryptedByteArray);
            
            return new String(decodedByte);
        }
        catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex)
        {
            Logger.getLogger(CryptoDES.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public String getProvider()
    {
        return "ProCrypto";
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Variables membres ">
    
    private Cipher cipher;
    private CleDES key = null;
    private int opmode;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Variables statiques ">
    
    private static final String algorithm;
    private static final String cipherMode;
    private static final String padding;
    private static final String provider;
    
    static
    {
        algorithm  = "DES";
        cipherMode = "ECB";
        padding    = "PKCS5Padding";
        provider   = "BC";
    }
    
    // </editor-fold>
}
