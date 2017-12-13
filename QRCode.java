import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
public class QRCode {
	
	public BufferedImage generateQRCODE(Produit unProduit, int sizeInPixels) throws WriterException {
		QRCodeWriter qrWriter = new QRCodeWriter();
		String content = "";
		Object  matrix = "";
		content = unProduit.getCode();
		matrix = qrWriter.encode(content, BarcodeFormat.QR_CODE, sizeInPixels, sizeInPixels);
		return MatrixToImageWriter.toBufferedImage((BitMatrix)matrix);		
	}
	
}
