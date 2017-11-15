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

	public static void main(String[] args) {
		
		Produit Produit1 = new Produit ("XV13948573","Mysterium","jeu d'enquête coopératif","jeu",30.21);
		ArrayList<Produit> lesProduits = new ArrayList<Produit> ();
		lesProduits.add(Produit1);
        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("FicheProduit.pdf"));

            document.open();
            
            for (Produit item : lesProduits){
           
	            // Instances de la classe Font pour la taille et le style du texte
	            Font StyleTitre = new Font(Font.FontFamily.HELVETICA  , 30, Font.BOLD);
	            Font StyleContenue = new Font(Font.FontFamily.HELVETICA  , 14);
	            
	            //Création des instances de Paragraph 
	            Paragraph Entete = new Paragraph("Code : "+item.getCode(), StyleContenue);
	            Paragraph Categ = new Paragraph("Catégorie : "+item.getCategorie(), StyleContenue);
	            Paragraph Nom = new Paragraph(item.getNom(), StyleTitre);
	            Paragraph Description = new Paragraph("Description", StyleContenue);
	            Paragraph DescriptionContenu = new Paragraph(item.getDescription());
	            Paragraph MontantHT = new Paragraph("Montant HT : "+item.getPrixHT()+"€", StyleContenue);
	            Paragraph Tva = new Paragraph("TVA : "+item.calcul_montantTVA()+"€", StyleContenue);
	            Paragraph MontantTTC = new Paragraph("Montant TTC : "+item.calcul_montantTTC()+"€", StyleContenue);
	            
	            //Positionnement de l'en-tête
	            Entete.setAlignment(Element.ALIGN_LEFT);
	            
	            //Positionnement de la catégorie
	            Categ.setAlignment(Element.ALIGN_RIGHT);
	            
	            //Positionnement de la Nom
	            Nom.setAlignment(Element.ALIGN_LEFT);
	            
	            //Positionnement de la Description
	            Description.setAlignment(Element.ALIGN_LEFT);
	            Description.setSpacingBefore(10);
	            Description.setIndentationLeft(10);
	            Description.setSpacingAfter(10);
	            
	            // Création d'un tableau avec une seule colonne
	            PdfPTable table = new PdfPTable(1);
	            PdfPCell cell = new PdfPCell(DescriptionContenu);
	            cell.setPaddingLeft(8);
	            cell.setPaddingBottom(550);
	            table.addCell(cell);
	            
	            
	            //Positionnement du Montant HT
	            MontantHT.setAlignment(Element.ALIGN_RIGHT);
	            
	            //Positionnement du Montant TTC
	            MontantTTC.setAlignment(Element.ALIGN_RIGHT);
	        
	            //Positionnement du TVA
	            Tva.setAlignment(Element.ALIGN_RIGHT);
	            
	            document.add(Entete);
	            document.add(Categ);
	            document.add(Nom);
	            document.add(Description);
	            document.add(table);
	            document.add(MontantHT);
	            document.add(Tva);
	            document.add(MontantTTC);
	    
            }
            
            document.close(); 

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
