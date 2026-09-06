package Apps;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncryptDecrypt {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String HARD_KEY = "dihardecryptdihardecrypt"; // Kunci tetap
    private static final String STATIC_IV = "1234567890abcdef"; // IV tetap (16 karakter)
    private static final String MARKER = "zmx3Xz"; // String penyisipan

    private static SecretKeySpec secretKey;
    private static IvParameterSpec ivParameterSpec;

    static {
        secretKey = new SecretKeySpec(getSHA256Key(HARD_KEY), ALGORITHM);
        ivParameterSpec = new IvParameterSpec(STATIC_IV.getBytes(StandardCharsets.UTF_8)); // IV tetap
    }

    // Generate kunci 32-byte menggunakan SHA-256
    private static byte[] getSHA256Key(String key) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            return sha.digest(key.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error generating SHA-256 key", e);
        }
    }

    // Menyisipkan MARKER di tengah string
    private static String insertMarker(String data) {
        int midIndex = data.length() / 2;
        return data.substring(0, midIndex) + MARKER + data.substring(midIndex);
    }

    // Menghapus MARKER dari string setelah dekripsi
    private static String removeMarker(String data) {
        if (data.contains(MARKER)) {
            return data.replace(MARKER, "");
        } else {
            throw new RuntimeException("Decryption failed: Marker not found");
        }
    }

    // Encrypt method (menyisipkan "zmx3Xz" di tengah sebelum enkripsi)
    public static String encrypt(String data) {
        try {
            String dataWithMarker = insertMarker(data); // Sisipkan MARKER di tengah
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            byte[] encryptedBytes = cipher.doFinal(dataWithMarker.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    // Decrypt method (menghapus "zmx3Xz" setelah dekripsi)
    public static String decrypt(String encryptedData) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
            return removeMarker(decryptedText); // Hapus MARKER dan kembalikan teks asli
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }

}
