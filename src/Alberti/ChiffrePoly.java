package Alberti;

import SPF.Chiffrement;
import SPF.Cle;
import java.util.Random;

/**
 *
 * @author Nakim
 */
public class ChiffrePoly implements Chiffrement
{
    @Override
    public void init(Cle k) 
    {
        if (!(k instanceof CleAlberti))
            throw new IllegalArgumentException("Cle invalide");
        
        CleAlberti cle;
        cle = (CleAlberti) k;
        
        /* Normalement la clé est générée par ChiffrePoly mais par précaution
         * On vérifie que la lettre de calage figure bien dans l'alphabet crypté
         */
        if (this.smallRing.indexOf(cle.getSmallRingLetter()) == -1)
            throw new IllegalArgumentException("Clé non valide : lettre de calage inexistante");

        this.key = cle;
    }
    
    @Override
    public Cle genererCle(int longueur)
    {
        if (longueur < 1)
            throw new IllegalArgumentException("La longueur doit être supérieur à 0");
        
        // Génère un string aléatoire
        Random random = new Random();
        char[] randomString = new char[longueur];
        for (int i = 0; i < longueur; i++)
            randomString[i] = largeRing.charAt(random.nextInt(largeRing.length()));
        
        // Choisit aléatoirement la lettre de calage
        char smallRingLetter = smallRing.charAt(random.nextInt(smallRing.length()));
        
        return new CleAlberti(new String(randomString),
                             smallRingLetter, 
                             random.nextInt(9) + 1); // Nombre entre 
    }

    @Override
    public String crypte(String plainText) 
    {
        if (this.key == null)
            throw new IllegalStateException("Chiffrement non initialisé");
        
        String UCPlainText = plainText.toUpperCase();
        final int plainTextLen = plainText.length();
        final int ringLen = largeRing.length();
        
        System.out.println("Taille texte a crypter : " + plainTextLen);
        
        // utiliser le décalage d'indice entre les deux lettre callée + modulo la longueur des texts
        // Avec largeRing.length() pour si on veut pouvoir rajouter des lettres dans la roue
        
        int indiceSmallRing = this.smallRing.indexOf(key.getSmallRingLetter());
        int indiceLargeRing = this.largeRing.indexOf(key.getKey().charAt(0));
        
        int decalage = indiceSmallRing - indiceLargeRing;
        System.out.println("Décalage brut : " + decalage);
        if (decalage < 0)
            decalage += ringLen;
        
        System.out.println("decalage corrigé = " + decalage);
        
        char[] cipherText = new char[plainTextLen];
        for (int i = 0; i < plainTextLen; ++i)
        {
            char lettreACrypter = UCPlainText.charAt(i);
            int indiceLettreCryptee = (decalage + largeRing.indexOf(lettreACrypter)) % ringLen;
            
            if (indiceLettreCryptee < 0)
                indiceLettreCryptee += ringLen;
            
            System.out.println("Lettre a crypter : " + lettreACrypter);
            System.out.println("Indice lettre a crypter : " + largeRing.indexOf(lettreACrypter));
            System.out.println("Indice lettre cryptée   : " + indiceLettreCryptee);
            cipherText[i] = this.smallRing.charAt(indiceLettreCryptee);
        }
        
        return new String(cipherText);
    }

    @Override
    public String decrypte(String cipherText) 
    {
        if (this.key == null)
            throw new IllegalStateException("Chiffrement non initialisé");
        
        final int cipherTextLen = cipherText.length();
        final int ringLen = largeRing.length();
        
        System.out.println("Taille texte a décrypter : " + cipherTextLen);
        
        int indiceSmallRing = this.smallRing.indexOf(key.getSmallRingLetter());
        int indiceLargeRing = this.largeRing.indexOf(key.getKey().charAt(0));
        
        int decalage = indiceSmallRing - indiceLargeRing;
        System.out.println("Décalage brut : " + decalage);
        if (decalage < 0)
            decalage += ringLen;
        
        System.out.println("decalage corrigé = " + decalage);
        
        char[] plainText = new char[cipherTextLen];
        for (int i = 0; i < cipherTextLen; ++i)
        {
            char lettreADecrypter = cipherText.charAt(i);
            int indiceLettreADecrypter = smallRing.indexOf(lettreADecrypter);
            int indiceLettreDecryptee = (-decalage + indiceLettreADecrypter) % ringLen;
            
            if (indiceLettreDecryptee < 0)
                indiceLettreDecryptee += ringLen;
            
            System.out.println("Lettre a decrypter : " + lettreADecrypter);
            System.out.println("Indice lettre a decrypter : " + indiceLettreADecrypter);
            System.out.println("Indice lettre decryptée : " + indiceLettreDecryptee);
            
            plainText[i] = this.largeRing.charAt(indiceLettreDecryptee);
        }
        
        return new String(plainText);
    }

    @Override
    public String getProvider() 
    {
        // TODO
        return null;
    }
    
    // Alphabet clair
    private final String largeRing = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
    // Alphabet crypté
    private final String smallRing = "c&bm+dgpf!zn=xyvto)sk,erl<ha*iqc-juw(";
    // Clé utilisée pour chiffrer et/ou déchiffrer
    private CleAlberti key = null;
}
