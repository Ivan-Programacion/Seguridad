package cifrado_asimetrico_firma_digital;

import java.security.*;

public class FirmaDigital {
	public static void main(String[] args) {
		try {
			System.out.println("Obteniendo generador de claves con cifrado DSA");
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
			// Fuerza que la clave sea de 1024
			keygen.initialize(1024, new SecureRandom());
			System.out.println("Generando par de claves");
			KeyPair keypair = keygen.generateKeyPair();
			System.out.println("Creando el objeto Signature");
			Signature signature = Signature.getInstance("DSA");
			System.out.println("Firmando mensaje");
			signature.initSign(keypair.getPrivate());
			String mensaje = "Mensaje para firmar";
			signature.update(mensaje.getBytes());
			byte[] firma = signature.sign();
			System.out.println("Comprobando el mensaje firmado");
			signature.initVerify(keypair.getPublic());
			signature.update(mensaje.getBytes());
			if (signature.verify(firma))
				System.out.println("El mensaje es auténtico :-)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}