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
		String leCSV = sourceFile;	
		
		
		
		BufferedReader leReader = new BufferedReader(new FileReader(leCSV));		
		
		while ((line = leReader.readLine()) != null)
		{
			//GENERATION DU TABLEAU D'INFORMATIONS
            String[] arrayInfo = line.split(";");
            //FIN GENERATION
            
            //TRANSFORMATION PRIX POUR CONVERSION DOUBLE
            Double prix = Double.valueOf(arrayInfo[4].replace(',', '.'));            
            //FIN TRANSFORMATION
            
            //CREATION D'UN PRODUIT
            Produit unProduit = new Produit(arrayInfo[0],arrayInfo[1],arrayInfo[2],arrayInfo[3],prix);
            //FIN CREATION
            
            //AFFICHAGE DES LIGNES DU TABLEAU
            System.out.println("Code: " + unProduit.getCode() +" | Nom: " +  unProduit.getNom() + " | Description: " + unProduit.getDescription() + " | Categorie: " + unProduit.getCategorie() + " | Prix: " + unProduit.getPrixHT() + "€");
			//FIN AFFICHAGE
            
            //DECLARATION DE VARIABLES ET APPEL DE LA FONCTION QRCODE
			String content = arrayInfo[0];
			String filename = "C:\\Users\\Bastien\\Desktop\\Cours\\Java\\Projet1\\qrcode-"+ arrayInfo[0] +".png";
			BufferedImage image = generate(content, 150);
			ImageIO.write(image, "PNG", new File(filename));
			//FIN DECLARATION ET APPEL
		}
	}
	
	//POUR LA GENERATION DE QRCODE
	private static BufferedImage generate(String content, int sizeInPixels) throws WriterException {
		QRCodeWriter qrWriter = new QRCodeWriter();
		Object matrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, sizeInPixels, sizeInPixels);
		return MatrixToImageWriter.toBufferedImage((BitMatrix)matrix);
	}

	//MAIN
	public static void main(String[] args) throws IOException, WriterException{
		// TODO Auto-generated method stub
		returnListe(args[0]);

		
	}

}
