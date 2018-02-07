package ficheProduit;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.itextpdf.text.pdf.qrcode.WriterException;


public class LigneCommande {
	private Options mesOptions;
	private CommandLineParser parser;
	private HelpFormatter formatter;
	
	public LigneCommande() {
		 mesOptions =  new Options();
		 parser = new DefaultParser();
		 formatter = new HelpFormatter();
	}
	
	public void creerCollOptions() {
		formatter.printHelp("h", mesOptions);
		mesOptions.addOption("i", true, "suivi du chemin du fichier CSV d’entrée");
		mesOptions.addOption("categorie", true, "suivi du nom de la catégorie pour filtrer les produits");
		mesOptions.addOption("etiquette", true, "suivi du chemin du fichier PDF qui contiendra les étiquettes produit");
		mesOptions.addOption("fiche", true, "suivi du chemin du fichier PDF contenant les fiches produit");
		mesOptions.addOption("tva", true, "montant de la TVA à utiliser pour calculer le prix TTC (par défaut 20%)");
	}


	public void controlerParametres(String[] args) throws ParseException, IOException, WriterException, com.google.zxing.WriterException, java.text.ParseException {
		try {
		     CommandLine line = parser.parse( mesOptions, args );
		     

		    if(line.hasOption("i")) {
		    	String fichier = line.getOptionValue("i");
				LectureCSV liste= new LectureCSV();
				ArrayList<Produit> lesProduits = liste.listerProduits(fichier);
				FicheProduit pdtFiche = new FicheProduit();
				EtiquetteProduit pdfEtiquette = new EtiquetteProduit();
		    	
		    	if(line.hasOption("categorie")) {
		    		ArrayList<Produit> lesProduitsParCateg = new ArrayList<Produit>();
		    		for (Produit unProduit : lesProduits) {
		    			if (unProduit.getCategorie().equals(line.getOptionValue("categorie"))) {
		    				lesProduitsParCateg.add(unProduit);
		    			}
		    		}
		    		if (line.hasOption("etiquette")) {
		    			String nameFichier = line.getOptionValue("etiquette");
		    			pdfEtiquette.creerEtiquette(lesProduitsParCateg, nameFichier);
		    		}
		    		
		    		if (line.hasOption("fiche")) {
		    			String nameFichier = line.getOptionValue("fiche");
                        String[] tabNameFichier = nameFichier.split("\\.");
                        if(tabNameFichier[1].contains(("pdf"))) {
                            pdtFiche.creerFiche(lesProduitsParCateg, nameFichier);
                        }
                        else{
                            pdtFiche.creerFicheHTML(lesProduitsParCateg, nameFichier);
                        }
		    		}
		    		
		    	}
		    	else if(line.hasOption("tva")) {
		    		double taux = Double.parseDouble(line.getOptionValue("tva"));
		    		for (Produit unProduit : lesProduits) {
		    			unProduit.setTauxTVA(taux);
		    			
		    		}
		    		if (line.hasOption("etiquette")) {
		    			String nameFichier = line.getOptionValue("etiquette");
		    			pdfEtiquette.creerEtiquette(lesProduits, nameFichier);
		    		}
		    		
		    		if (line.hasOption("fiche")) {
                        String nameFichier = line.getOptionValue("fiche");
                        String[] tabNameFichier = nameFichier.split("\\.");
                        if(tabNameFichier[1].contains(("pdf"))) {
                            pdtFiche.creerFiche(lesProduits, nameFichier);
                        }
                        else{
                            pdtFiche.creerFicheHTML(lesProduits, nameFichier);
                        }
		    		}
		    	}
		    	
		    	if(line.hasOption("etiquette") && (!line.hasOption("tva")) && (!line.hasOption("categorie"))) {
		    		String nameFichier = line.getOptionValue("etiquette");
					pdfEtiquette.creerEtiquette(lesProduits, nameFichier);
		    	}
		    	if(line.hasOption("fiche") && (!line.hasOption("tva")) && (!line.hasOption("categorie"))) {
                    String nameFichier = line.getOptionValue("fiche");
                    String[] tabNameFichier = nameFichier.split("\\.");
                    if(tabNameFichier[1].contains(("pdf"))) {
                        pdtFiche.creerFiche(lesProduits, nameFichier);
                    }
                    else{
                        pdtFiche.creerFicheHTML(lesProduits, nameFichier);
                    }
		    	}
		    }
		}
		catch( ParseException exp ) {
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}
		
	}
	public static void main(String[] args) throws Exception {
		LigneCommande uneCommande = new LigneCommande();
		uneCommande.creerCollOptions();
		uneCommande.controlerParametres(args);

	}
}


