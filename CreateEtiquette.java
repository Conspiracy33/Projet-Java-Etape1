import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
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
	Image image;
	Document document = new Document();
	QRCode leQRCode = new QRCode();
    Font StyleContenue;
    Paragraph monProduit;
    PdfPTable tableau;
    PdfPTable table;
    PdfPCell cell;
    PdfPCell cell2;
    
    public void creationQRCode (Produit unProduit) throws WriterException, IOException {
		BufferedImage imageQRCode = leQRCode.generateQRCODE(unProduit, 150);
    	String filename = "qrcode-"+ unProduit.getCode() +".png";
    	ImageIO.write(imageQRCode, "PNG", new File(filename));
	}
	
	public void recuperationQRCode (Produit unProduit) throws BadElementException, MalformedURLException, IOException {
		String lien = "C:\\Users\\Mathilde RG\\eclipse-workspace\\ProjetTransverse\\qrcode-"+ unProduit.getCode() +".png";
		image = Image.getInstance(lien);
	}
	
	public void Style () {
		StyleContenue = new Font(Font.FontFamily.HELVETICA, 12);
	}
	
	public void monParagraph (Produit unProduit) {
		monProduit = new Paragraph(unProduit.getCode()+"\n" 
										+ " \n" + unProduit.getNom()+"\n" 
										+ " \n" + unProduit.calculMontantTTC()+"� TTC " 
										+ " ("+unProduit.getPrixHT()+"� HT)\n", StyleContenue);
	}
	
	public void mesCells(int i) {
		cell = new PdfPCell(image, true);
        cell2 = new PdfPCell(monProduit);
        cell.setPadding(2);
        cell2.setPadding(12);
        cell.setBorderWidthRight(0);
        cell2.setBorderWidthLeft(0);
	}
	
	public void maTable() {
		table = new PdfPTable(2);   
        table.addCell(cell);
        table.addCell(cell2);
        table.setSpacingAfter(5);
        table.setWidthPercentage(50);
        table.setHorizontalAlignment(Element.ALIGN_LEFT); 
	}
	
	public void creationEtiquette(ArrayList <Produit> lesProduits) throws MalformedURLException, IOException, WriterException {
		Style();
	    try {
	    	PdfWriter.getInstance(document, new FileOutputStream("EtiquettesProduits.pdf"));

	        document.open();
	            
	            tableau = new PdfPTable(2);
	            int i = lesProduits.size();
	            for (Produit unProduit : lesProduits){
	            	
	            	this.creationQRCode(unProduit);
	            	this.recuperationQRCode(unProduit);
	            	monParagraph(unProduit);
	            	mesCells(i);
	            	maTable();		            
		            tableau.addCell(table);  
	            }
	            
	            if (i%2 == 1) {
	            	cell = new PdfPCell();
	                cell2 = new PdfPCell();
	                cell.setPadding(2);
	                cell2.setPadding(12);
	                cell.setBorderWidthRight(0);
	                cell2.setBorderWidthLeft(0);
	                maTable();
	                tableau.addCell(table); 
	        	}
	            
	            document.add(tableau);
	            document.close(); 

	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	}	
}
//float[] columnWidths = {15, 35};