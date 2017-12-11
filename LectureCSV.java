import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class LectureCSV {

	static String[] arrayInfo = null ;
	
	public static ArrayList<String> ListeProduitsCSV(String sourceFile) throws IOException, WriterException {
		// Initialisation variables
		String line ="";
		int compteur = 0;
		ArrayList<String> lesProduitsduCSV = new ArrayList<String>();
		// Traitement
		String leCSV = sourceFile;	
		
		BufferedReader leReader = new BufferedReader(new FileReader(leCSV));
		
		
		while ((line = leReader.readLine()) != null)
		{
			//GESTION DU TABLEAU D'INFORMATIONS
            arrayInfo = line.split(";");
            lesProduitsduCSV.add(line);
			System.out.println("Code: " + arrayInfo[0] +" | Nom: " +  arrayInfo[1] + " | Description: " + arrayInfo[2] + " | Catégorie: " + arrayInfo[3] + " | Prix: " + arrayInfo[4] + "€");
			compteur++;
			
			//APPEL DE LA FONCTION QRCODE
			String content = arrayInfo[0];
			String filename = "C:\\Users\\Mathilde RG\\eclipse-workspace\\ProjetTransverse\\qrcode-"+ arrayInfo[0] +".png";
			BufferedImage image = generate(content, 150);
			ImageIO.write(image, "PNG", new File(filename));
			//System.out.println("test");		
		}
		return lesProduitsduCSV;
	}
	
	 public static Map<String, String> RecupererDonnees(String produit) {

		 String NomColonne = "code nom description categorie prix";
		 String[] code = NomColonne.split(" ");
		 int i = 0;
		 Map <String, String> tableauTransfert = new TreeMap();

		 for(String value : produit.split(";")) {
			 tableauTransfert.put(code[i], value) ;
			 i++;
		 }
		 return tableauTransfert;
	}
	
	private static BufferedImage generate(String content, int sizeInPixels) throws WriterException {
		QRCodeWriter qrWriter = new QRCodeWriter();
		Object matrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, sizeInPixels, sizeInPixels);
		return MatrixToImageWriter.toBufferedImage((BitMatrix)matrix);
	}

	public static void main(String[] args) throws IOException, WriterException {
		// TODO Auto-generated method stub
		//returnListe(args[0]);
		ArrayList<String> DonnesFinale = ListeProduitsCSV("C:\\Users\\Mathilde RG\\eclipse-workspace\\ProjetTransverse\\ListeProduits.csv");
		ArrayList<Produit> lesProduits= new ArrayList<Produit> ();
		
		// on remplit un collection de produits avec les données du csv
		for (String uneDonne : DonnesFinale) {
			Map <String, String> produitFinale = RecupererDonnees(uneDonne);
			String prix = produitFinale.get("prix");
			double value = Double.parseDouble( prix.replace(",",".") );
			Produit Produit = new Produit (produitFinale.get("code"),produitFinale.get("nom"),produitFinale.get("description"),produitFinale.get("categorie"), value);
			lesProduits.add(Produit);
		}
		CreatePDF pdf = new CreatePDF();
		pdf.creationPDF(lesProduits);
		CreateEtiquette pdfEtiquette = new CreateEtiquette();
		pdfEtiquette.creationEtiquette(lesProduits);
		
	}
}
