package chickencode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncriptedProperties extends Properties {

	
	public boolean encript = true;

	public void storeEncripted(OutputStream os, String comment, String encoding) {
		if (encript) {
			try {

				KeyGenerator kg = KeyGenerator.getInstance("AES");
				kg.init(new SecureRandom(new byte[] { 1, 2, 3 }));
				final SecretKey key = kg.generateKey();
				final Cipher c = Cipher.getInstance("AES");
				c.init(Cipher.ENCRYPT_MODE, key);
				CipherOutputStream output = new CipherOutputStream(os, c);
				storeToXML(output, comment, encoding);
				output.close();

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				storeToXML(os, comment, encoding);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void storeEncripted(OutputStream os, String comment) {
		if (encript) {
			try {

				KeyGenerator kg = KeyGenerator.getInstance("AES");
				kg.init(new SecureRandom(new byte[] { 1, 2, 3 }));
				final SecretKey key = kg.generateKey();
				final Cipher c = Cipher.getInstance("AES");
				c.init(Cipher.ENCRYPT_MODE, key);
				CipherOutputStream output = new CipherOutputStream(os, c);
				storeToXML(output, comment);
				output.close();

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				storeToXML(os, comment);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void loadDecripted(InputStream is) {
		if (encript) {
			try {

				KeyGenerator kg2 = KeyGenerator.getInstance("AES");
				kg2.init(new SecureRandom(new byte[] { 1, 2, 3 }));
				final SecretKey key2 = kg2.generateKey();
				final Cipher c2 = Cipher.getInstance("AES");
				c2.init(Cipher.DECRYPT_MODE, key2);
				CipherInputStream input = new CipherInputStream(is, c2);
				loadFromXML(input);

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				loadFromXML(is);
			} catch (InvalidPropertiesFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
