package ficheProduit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClassFicheProduit {
	
	public static void returnListe(String sourceFile) throws IOException
	{
		// Initialisation variable
		String line ="";
		int compteur = 0;
		// Traitement
		String leCSV = sourceFile;	
		
		
		BufferedReader leReader = new BufferedReader(new FileReader(leCSV));
		
		
		while ((line = leReader.readLine()) != null)
		{
			// use comma as separator
            String[] arrayInfo = line.split(";");
			System.out.println("Code: " + arrayInfo[0] +" | Titre: " +  arrayInfo[1] + " | Description: " + arrayInfo[2] + " | Type: " + arrayInfo[3] + " | Prix: " + arrayInfo[4] + "€");
			compteur++;
			//System.out.println("test");
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		returnListe(args[0]);
	}

}
