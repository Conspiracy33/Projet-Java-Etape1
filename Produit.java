
public class Produit {
	private String pdtcode;
	private String pdtnom;
	private String pdtdescription;
	private String pdtcategorie;
	private int pdtprixHT;
	private double pdttauxTVA;
	
	public Produit(String code, String nom, String description, String categorie, int prix) {
		pdtcode = code;
		pdtnom = nom;
		pdtdescription = description;
		pdtcategorie = categorie;
		pdtprixHT = prix;
		pdttauxTVA = 20;
	}
	
	public double getMontantTva () {
		double taux = (1+pdttauxTVA/100);
		double montant = (pdtprixHT*taux) - pdtprixHT;
		double montantTVA = (double)Math.round(montant) / 100;
		return montantTVA;
	}
	
	public double getMontantTTC () {
		double taux = (1+pdttauxTVA/100);
		double montant = pdtprixHT*taux;
		double montantTTC = (double)Math.round(montant) / 100;
		return montantTTC;
	}
	
	public void setCode(String code) {
		pdtcode = code;
	}
	
	public String getCode() {
		return pdtcode;
	}
	
	public void setNom(String nom) {
		pdtnom = nom;
	}
	
	public String getNom() {
		return pdtnom;
	}
	
	public void setDescription(String description) {
		pdtdescription = description;
	}
	
	public String getDescription() {
		return pdtdescription;
	}
	
	public void setCategorie(String categorie) {
		pdtcategorie = categorie;
	}
	
	public String getCategorie() {
		return pdtcategorie;
	}
	
	public void setPrixHT(int prix) {
		pdtprixHT = prix;
	}
	
	public double getPrixHT() {
		double prix = (double)Math.round(pdtprixHT) / 100;
		return prix;
	}
	
	public void setTauxTVA(double taux) {
		pdttauxTVA = taux;
	}
	
	public double getTauxTVA() {
		return pdttauxTVA;
	}
	
}
