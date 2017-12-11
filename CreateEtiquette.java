import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreateEtiquette {
	
	public void creationEtiquette(ArrayList <Produit> lesProduits) throws MalformedURLException, IOException {
		 Document document = new Document();

	        try {
	            PdfWriter.getInstance(document, new FileOutputStream("EtiquettesProduits.pdf"));

	            document.open();
	            
	            PdfPTable tableau = new PdfPTable(2);
	            
	            for (Produit item : lesProduits){
	            	
	            	//On récupère le qrcode
	            	String lien = "C:\\Users\\Mathilde RG\\eclipse-workspace\\ProjetTransverse\\qrcode-"+ item.getCode() +".png";
	            	Image image = Image.getInstance(lien);
	            	//document.add(image);
	            	
		            // Instances de la classe Font pour la taille et le style du texte
		            Font StyleContenue = new Font(Font.FontFamily.HELVETICA  , 12);
		            
		            //Création des instances de Paragraph 
		            Paragraph Produit = new Paragraph(item.getCode()+"\n" + " \n" + item.getNom()+"\n" + " \n" + item.calcul_montantTTC()+"€ TTC " + " ("+item.getPrixHT()+"€ HT)\n", StyleContenue);
		            
		            // Création d'un tableau avec de deux colonnes
		            float[] columnWidths = {15, 35};
		            PdfPTable table = new PdfPTable(columnWidths);
		            PdfPCell cell = new PdfPCell(image, true);
		            PdfPCell cell2 = new PdfPCell(Produit);
		            cell.setPadding(2);
		            cell2.setPadding(12);
		            cell.setBorderWidthRight(0);
		            cell2.setBorderWidthLeft(0);
		            table.addCell(cell);
		            table.addCell(cell2);
		            table.setSpacingAfter(5);
		            table.setWidthPercentage(50);
		            table.setHorizontalAlignment(Element.ALIGN_LEFT);
		            
		            tableau.addCell(table);
		            document.add(tableau);
		            
		            //document.add(table);
		            
	            }
	            
	            document.close(); 

	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}	
}