package SPF.Integrity;

/**
 *
 * @author Nakim
 */

// Interface de service
public interface Integrity
{
    byte[] makeCheck(String plainText);
    byte[] makeCheck(String plainText, ByteArrayList salt);
    boolean verifyCheck(String plainText, byte[] digest);
    boolean verifyCheck(String plainText, ByteArrayList salt, byte[] digest);
    String getProvider();
}
