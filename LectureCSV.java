import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.itextpdf.text.pdf.qrcode.WriterException;


public class LectureCSV {

	
	public ArrayList<Produit> listerProduits(String sourceFile) throws IOException, WriterException, ParseException {
		
		// Initialisation variables
		String line ="";
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		
		// Traitement
		String leCSV = sourceFile;	
		BufferedReader leReader = new BufferedReader(new FileReader(leCSV));
		
		
		while ((line = leReader.readLine()) != null) {
			//GESTION DE LA COLLECTION D'INFORMATIONS
            Map <String, String> produitFinale = recupererDonnees(line);
    		String prix = produitFinale.get("prix");
    		double value = NumberFormat.getInstance(Locale.FRENCH).parse(prix).doubleValue();
    		int valueInt = (int) (value*100);
    		Produit unProduit = new Produit (produitFinale.get("code"),produitFinale.get("nom"),produitFinale.get("description"),produitFinale.get("categorie"), valueInt);
    		lesProduits.add(unProduit);
		}
		leReader.close();
		return lesProduits;
	}
	
	private Map<String, String> recupererDonnees(String produit) {

		 String NomColonne = "code nom description categorie prix";
		 String[] code = NomColonne.split(" ");
		 int i = 0;
		 Map <String, String> tableauTransfert = new TreeMap<String, String>();

		 for(String value : produit.split(";")) {
			 tableauTransfert.put(code[i], value) ;
			 i++;
		 }
		 return tableauTransfert;
	}
	
}
