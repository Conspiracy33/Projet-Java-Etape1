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

public class EtiquetteProduit {
	
	private Image image;
	private Document document = new Document();
	private QRCode leQRCode = new QRCode();
	private File temp = new File ("");
	private Font StyleContenue;
	private Paragraph monProduit;
	private PdfPTable tableau;
	private PdfPTable table;
	private PdfPCell cell;
	private PdfPCell cell2;
    
    private void genererQRCode(Produit unProduit) throws WriterException, IOException {
		BufferedImage imageQRCode = leQRCode.genererQRCODE(unProduit, 150);
		temp = File.createTempFile("qrcode-"+unProduit.getCode(),".png");
		ImageIO.write(imageQRCode, "PNG", temp);
	}
	
    
    private void recupererQRCode(Produit unProduit) throws BadElementException, MalformedURLException, IOException {
		image = Image.getInstance(temp.toString());
		temp.delete();
    	
	}
	
    private void creerStyle() {
		StyleContenue = new Font(Font.FontFamily.HELVETICA, 12);
	}
	
    private void creerParagraphe(Produit unProduit) {
		monProduit = new Paragraph(unProduit.getCode()+"\n" 
										+ " \n" + unProduit.getNom()+"\n" 
										+ " \n" + unProduit.getMontantTTC()+"€ TTC " 
										+ " ("+unProduit.getPrixHT()+"€ HT)\n", StyleContenue);
	}
	
    private void creerCellule(int i) {
		cell = new PdfPCell(image, true);
        cell2 = new PdfPCell(monProduit);
        cell.setPadding(1);
        cell2.setPadding(1);
        cell.setBorderWidthRight(0);
		cell2.setBorderWidthLeft(0);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	}
	
    private void creerTable() {
		table = new PdfPTable(new float[] { 33, 65 });   
        table.addCell(cell);
        table.addCell(cell2);
        table.setSpacingAfter(5);
        //table.setWidthPercentage(50);
        table.setHorizontalAlignment(Element.ALIGN_LEFT); 
	}
	
	public void creerEtiquette(ArrayList <Produit> lesProduits, String nameFichier) throws MalformedURLException, IOException, WriterException {
		creerStyle();
	    try {
	    	PdfWriter.getInstance(document, new FileOutputStream(nameFichier));

	        document.open();
	            
	            tableau = new PdfPTable(2);
	            tableau.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
				tableau.setWidthPercentage(100);
	            int i = lesProduits.size();
	            for (Produit unProduit : lesProduits){
	            	
	            	this.genererQRCode(unProduit);
	            	this.recupererQRCode(unProduit);
	            	creerParagraphe(unProduit);
	            	creerCellule(i);
	            	creerTable();		            
		            tableau.addCell(table);  
	            }
	            
	            if (i%2 == 1) {
	            	cell = new PdfPCell();
	                cell2 = new PdfPCell();
	                cell.setPadding(1);
					cell.setBorderWidthRight(0);
					cell2.setBorderWidthLeft(0);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	                creerTable();
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
