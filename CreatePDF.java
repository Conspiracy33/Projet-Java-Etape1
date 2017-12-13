import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePDF {
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
	
	public void Style () {
		styleTitre = new Font(Font.FontFamily.HELVETICA  , 30, Font.BOLD);
        styleContenue = new Font(Font.FontFamily.HELVETICA  , 14);
	}
	
	public Font GetStyleTitre() {
		return styleTitre;
	}
	
	public Font GetStyleContenue() {
		return styleContenue;
	}
	
	public void mesParagraphes (Produit unProduit) {
    	entete = new Paragraph("Code : "+unProduit.getCode(), GetStyleContenue());
        categ = new Paragraph("Catégorie : "+unProduit.getCategorie(), GetStyleContenue());
        nom = new Paragraph(unProduit.getNom(), GetStyleTitre());
        description = new Paragraph("Description", GetStyleContenue());
        descriptionContenu = new Paragraph(unProduit.getDescription());
        montantHT = new Paragraph("Montant HT : "+unProduit.getPrixHT()+"€", GetStyleContenue());
        tva = new Paragraph("TVA : "+unProduit.calculMontantTVA()+"€", GetStyleContenue());
        montantTTC = new Paragraph("Montant TTC : "+unProduit.calculMontantTTC()+"€", GetStyleContenue());
    }
	
	public void tableau() {
		this.cell = new PdfPCell(descriptionContenu);
		this.table  = new PdfPTable(1);
        cell.setPaddingLeft(8);
        cell.setPaddingBottom(550);
        table.addCell(cell);
	}
	
	public void positionnementElement() {
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
	
	public void ajout () throws DocumentException {
		document.add(entete);
        document.add(categ);
        document.add(nom);
        document.add(description);
        document.add(table);
        document.add(montantHT);
        document.add(tva);
        document.add(montantTTC);
	}
	
	public void creationPDF(ArrayList <Produit> lesProduits) {		 
		 Style ();
		 try {
	     	PdfWriter.getInstance(document, new FileOutputStream("PDFProduits.pdf"));
	        document.open();
	        for (Produit unProduit : lesProduits){
	        	mesParagraphes(unProduit);
	        	tableau();
	        	positionnementElement();
	        	ajout();
	        }
	        document.close(); 
		 } catch (DocumentException e) {
			 e.printStackTrace();
		 } catch (FileNotFoundException e) {
			 e.printStackTrace();
		 }
	}
}
