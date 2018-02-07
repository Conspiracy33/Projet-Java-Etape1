package ficheProduit;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import freemarker.cache.FileTemplateLoader;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FicheProduit {
	
	private Font styleTitre;
	private Font styleContenue;
	private Paragraph entete;
	private Paragraph categ;
	private Paragraph nom;
	private Paragraph description;
	private Paragraph descriptionContenu;
	private Paragraph montantHT;
	private Paragraph tva;
	private Paragraph montantTTC;
	private Document document = new Document();
	private PdfPTable table;
	private PdfPCell cell;
	
	private void creerStyle() {
		styleTitre = new Font(Font.FontFamily.HELVETICA  , 30, Font.BOLD);
        styleContenue = new Font(Font.FontFamily.HELVETICA  , 14);
	}
	
	private Font getStyleTitre() {
		return styleTitre;
	}
	
	private Font getStyleContenue() {
		return styleContenue;
	}
	
	private void creerParagraphe (Produit unProduit) {
    	entete = new Paragraph("Code : "+unProduit.getCode(), getStyleContenue());
        categ = new Paragraph("Catégorie : "+unProduit.getCategorie(), getStyleContenue());
        nom = new Paragraph(unProduit.getNom(), getStyleTitre());
        description = new Paragraph("Description", getStyleContenue());
        descriptionContenu = new Paragraph(unProduit.getDescription());
        montantHT = new Paragraph("Montant HT : "+unProduit.getPrixHT()+"€", getStyleContenue());
        tva = new Paragraph("TVA : "+unProduit.getMontantTva()+"€", getStyleContenue());
        montantTTC = new Paragraph("Montant TTC : "+unProduit.getMontantTTC()+"€", getStyleContenue());
    }
	
	private void creertableau() {
		this.cell = new PdfPCell(descriptionContenu);
		this.table  = new PdfPTable(1);
        cell.setPaddingLeft(8);
        cell.setPaddingBottom(550);
        table.addCell(cell);
	}
	
	private void positionnerElement() {
		//Positionnement de l'en-tête
        entete.setAlignment(Element.ALIGN_LEFT);
        
        //Positionnement de la catégorie
        categ.setAlignment(Element.ALIGN_RIGHT);
        
        //Positionnement de la Nom
        nom.setAlignment(Element.ALIGN_LEFT);
        
        //Positionnement de la Description
        description.setAlignment(Element.ALIGN_LEFT);
        description.setSpacingBefore(10);
        description.setIndentationLeft(10);
        description.setSpacingAfter(10);
        
        //Positionnement du Montant HT
        montantHT.setAlignment(Element.ALIGN_RIGHT);
        
        //Positionnement du Montant TTC
        montantTTC.setAlignment(Element.ALIGN_RIGHT);
    
        //Positionnement du TVA
        tva.setAlignment(Element.ALIGN_RIGHT);
	}
	
	private void ajouter() throws DocumentException {
		document.add(entete);
        document.add(categ);
        document.add(nom);
        document.add(description);
        document.add(table);
        document.add(montantHT);
        document.add(tva);
        document.add(montantTTC);
	}
	
	public void creerFiche(ArrayList <Produit> lesProduits, String nameFichier) {		 
		 creerStyle ();
		 try {
	     	PdfWriter.getInstance(document, new FileOutputStream(nameFichier));
	        document.open();
	        for (Produit unProduit : lesProduits){
	        	creerParagraphe(unProduit);
	        	creertableau();
	        	positionnerElement();
	        	ajouter();
	        }
	        document.close(); 
		 } catch (DocumentException e) {
			 e.printStackTrace();
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
	}

	public void creerFicheHTML(ArrayList <Produit> lesProduits, String nameFichier){
		//StringWriter writer = new StringWriter();
		try {
            Configuration configuration = new Configuration();
            configuration.setDirectoryForTemplateLoading(new File("/"));
            configuration.setClassForTemplateLoading(this.getClass(), "/template/");
            //configuration.getEncoding(Locale.FRENCH);
            //configuration.setDefaultEncoding("utf-8");
            Map<String, Object> input = new HashMap<String, Object>();
            input.put("ListeProduit",lesProduits);
            Template template = configuration.getTemplate("ficheProduit.ftl");

			Writer file = new FileWriter (new File("C:/Users/Bastien/eclipse-workspace-2/FicheProduit/ficheProduit.html"));
			//template.process(input, file);
			template.process(input,file);
			//Template freemarkerTemplate = configuration.getTemplate(nameFichier);
        }
        catch (IOException e){
		    e.printStackTrace();
        } catch (TemplateException e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) throws Exception {
        //creerFicheHTML();
    }
}
