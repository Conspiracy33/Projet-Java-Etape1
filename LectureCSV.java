import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.itextpdf.text.pdf.qrcode.WriterException;


public class LectureCSV {

	static String[] arrayInfo = null ;
	
	public ArrayList<Produit> listeProduits(String sourceFile) throws IOException, WriterException {
		
		// Initialisation variables
		String line ="";
		int compteur = 0;
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		
		// Traitement
		String leCSV = sourceFile;	
		BufferedReader leReader = new BufferedReader(new FileReader(leCSV));
		
		
		while ((line = leReader.readLine()) != null) {
			//GESTION DE LA COLLECTION D'INFORMATIONS
            arrayInfo = line.split(";");
            Map <String, String> produitFinale = recupererDonnees(line);
    		String prix = produitFinale.get("prix");
    		double value = Double.parseDouble( prix.replace(",",".") );
    		Produit unProduit = new Produit (produitFinale.get("code"),produitFinale.get("nom"),produitFinale.get("description"),produitFinale.get("categorie"), value);
    		lesProduits.add(unProduit);
			compteur++;	
		}
		return lesProduits;
	}
	
	 public Map<String, String> recupererDonnees(String produit) {

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
	

	public static void main(String[] args) throws IOException, WriterException, com.google.zxing.WriterException {

		LectureCSV liste= new LectureCSV();
		ArrayList<Produit> lesProduits = liste.listeProduits("C:\\Users\\Mathilde RG\\eclipse-workspace\\ProjetTransverse\\ListeProduits.csv");
		
		CreatePDF pdf = new CreatePDF();
		pdf.creationPDF(lesProduits);
		
		CreateEtiquette pdfEtiquette = new CreateEtiquette();
		pdfEtiquette.creationEtiquette(lesProduits);
	}
}
