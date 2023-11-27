package services;

import database.Database;
import entities.Transazione;
import entities.Utente;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class SignatureService {

    private static final Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static final KeyPairGenerator keyPairGenerator;

    static {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final SecureRandom secureRandom = new SecureRandom();

    /**
     * Questo metodo genera una coppia di chiavi pubblica-privata
     * @return una KeyPair contenente due chiavi a crittografia asimmetrica.
     */
    public static KeyPair keyPair() {
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] sign(byte[] message, PrivateKey privateKey) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance("SHA256WithDSA");
        signature.initSign(privateKey, secureRandom);
        signature.update(message);
        return signature.sign();
    }

    public static boolean verify(byte[] signature, PublicKey publicKey, byte[] message) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signature_verify = Signature.getInstance("SHA256WithDSA");
        signature_verify.initVerify(publicKey);
        signature_verify.update(message);
        return signature_verify.verify(signature);
    }

    public static boolean verifyTransaction(Transazione t, byte[] signature) throws UnsupportedEncodingException, SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Utente u = Database.getUtenti().get(t.getId_utente());
        return verify(signature, u.getKeyPair().getPublic(), String.valueOf(t.hashCode()).getBytes());
    }

}
