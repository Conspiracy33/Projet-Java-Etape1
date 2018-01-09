
public class Produit {
	private String pdtcode;
	private String pdtnom;
	private String pdtdescription;
	private String pdtcategorie;
	private double pdtprixHT;
	private double pdttauxTVA;
	
	public Produit(String code, String nom, String description, String categorie, double prix) {
		pdtcode = code;
		pdtnom = nom;
		pdtdescription = description;
		pdtcategorie = categorie;
		pdtprixHT = prix;
		pdttauxTVA = 20;
	}
	
	public double calculMontantTVA () {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		double taux = (1+pdttauxTVA/100);
		double TTC = pdtprixHT*taux;
		double tva = TTC - pdtprixHT;
		double value = Double.parseDouble( df.format(tva).replace(",",".") );
		return value;
		
	}
	
	public double calculMontantTTC () {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		double taux = (1+pdttauxTVA/100);
		double calcul = pdtprixHT*taux;
		double value = Double.parseDouble( df.format(calcul).replace(",",".") );
		return value;
	}
	
	public void setCode(String code) {
		pdtcode = code;
	}
	
	public String getCode() {
		return pdtcode;
	}
	
	public void setNom(String nom) {
		pdtcode = nom;
	}
	
	public String getNom() {
		return pdtnom;
	}
	
	public void setDescription(String description) {
		pdtcode = description;
	}
	
	public String getDescription() {
		return pdtdescription;
	}
	
	public void setCategorie(String categorie) {
		pdtcode = categorie;
	}
	
	public String getCategorie() {
		return pdtcategorie;
	}
	
	public void setPrixHT(String prix) {
		pdtcode = prix;
	}
	
	public double getPrixHT() {
		return pdtprixHT;
	}
	
	public void setTauxTVA(String taux) {
		pdtcode = taux;
	}
	
	public double getTauxTVA() {
		return pdttauxTVA;
	}
	
}
