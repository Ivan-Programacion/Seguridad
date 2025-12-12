package prueba2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EjemploAES {
	public static void main(String[] args) {
		try {
			System.out.println("Obteniendo generador de claves con cifrado AES");
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			System.out.println("Generando la clave");
			SecretKey key = keygen.generateKey();
			System.out.println("Obteniendo objeto Cipher con cifrado AES");
			Cipher aesCipher = Cipher.getInstance("AES");
			System.out.println("Configurando Cipher para encriptar");
			aesCipher.init(Cipher.ENCRYPT_MODE, key);
			String mensaje = "Mensaje que se cifrará con AES";
			System.out.println("mensaje original: " + mensaje);
			// Lo añadimos a un array de byte
			byte[] bytesCifrados = aesCipher.doFinal(mensaje.getBytes(StandardCharsets.UTF_8));
			// Lo transformamos en Base64 para que sea seguro y pueda leer todo
			// correctamente
			String mensajeCifrado = Base64.getEncoder().encodeToString(bytesCifrados);
			System.out.println("Mensaje cifrado: " + mensajeCifrado);
			System.out.println("Configurando Cipher para desencriptar");
			aesCipher.init(Cipher.DECRYPT_MODE, key);
			// Para descifrar necesitamos decodear en base64 el mensaje
			byte[] bytesDescifrados = aesCipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
			// seguido de hacer el string con ese byte, y en utf8
			String mensajeDescifrado = new String(bytesDescifrados, StandardCharsets.UTF_8);
			System.out.println("Mensaje descifrado: " + mensajeDescifrado);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}