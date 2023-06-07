package restaurant.utils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasher {
    public static String hashPassword (String employeePassword){
        try{
//          Selects the message digest for the hash computation
            MessageDigest md = MessageDigest.getInstance("SHA-256");

//          Generates a random salt
            SecureRandom random = new SecureRandom();
            byte[]salt = new byte[16];
            random.nextBytes(salt);

//          Passes the slt to the digest
            md.update(salt);

//          Generates a salted hash
            byte[] hashedPassword = md.digest(employeePassword.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword){
                sb.append(String.format("%02x", b));
            }

            System.out.println(sb);
//            System.out.println(sb.toString());
            return sb.toString();

        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return null;
    }
}
