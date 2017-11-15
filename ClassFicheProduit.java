package ficheProduit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


public class ClassFicheProduit {
	
	public static void returnListe(String sourceFile) throws IOException, WriterException
	{
		// Initialisation variable
		String line ="";
		int compteur = 0;
		// Traitement
		String leCSV = sourceFile;	
		
		
		BufferedReader leReader = new BufferedReader(new FileReader(leCSV));
		
		
		while ((line = leReader.readLine()) != null)
		{
			//GESTION DU TABLEAU D'INFORMATIONS
            String[] arrayInfo = line.split(";");
			System.out.println("Code: " + arrayInfo[0] +" | Titre: " +  arrayInfo[1] + " | Description: " + arrayInfo[2] + " | Type: " + arrayInfo[3] + " | Prix: " + arrayInfo[4] + "€");
			compteur++;
			//APPEL DE LA FONCTION QRCODE
			String content = arrayInfo[0];
			String filename = "C:\\Users\\Bastien\\Desktop\\Cours\\Java\\Projet1\\qrcode-"+ arrayInfo[0] +".png";
			BufferedImage image = generate(content, 150);
			ImageIO.write(image, "PNG", new File(filename));
			//System.out.println("test");
		}
	}
	
	private static BufferedImage generate(String content, int sizeInPixels) throws WriterException {
		QRCodeWriter qrWriter = new QRCodeWriter();
		Object matrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, sizeInPixels, sizeInPixels);
		return MatrixToImageWriter.toBufferedImage((BitMatrix)matrix);
	}

	public static void main(String[] args) throws IOException, WriterException{
		// TODO Auto-generated method stub
		returnListe(args[0]);

		
	}

}
