
public class Produit {
	private String pdt_code;
	private String pdt_nom;
	private String pdt_description;
	private String pdt_categorie;
	private double pdt_prixHT;
	private double pdt_tauxTVA;
	
	public Produit(String code, String nom, String description, String categorie, double prix) {
		pdt_code = code;
		pdt_nom = nom;
		pdt_description = description;
		pdt_categorie = categorie;
		pdt_prixHT = prix;
		pdt_tauxTVA = 20;
	}
	
	public double calcul_montantTVA () {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		double taux = (1+pdt_tauxTVA/100);
		double TTC = pdt_prixHT*taux;
		double tva = TTC - pdt_prixHT;
		double value = Double.parseDouble( df.format(tva).replace(",",".") );
		return value;
		
	}
	
	public double calcul_montantTTC () {
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.##");
		double taux = (1+pdt_tauxTVA/100);
		double calcul = pdt_prixHT*taux;
		double value = Double.parseDouble( df.format(calcul).replace(",",".") );
		return value;
	}
	
	public void setCode(String code) {
		pdt_code = code;
	}
	
	public String getCode() {
		return pdt_code;
	}
	
	public void setNom(String nom) {
		pdt_code = nom;
	}
	
	public String getNom() {
		return pdt_nom;
	}
	
	public void setDescription(String description) {
		pdt_code = description;
	}
	
	public String getDescription() {
		return pdt_description;
	}
	
	public void setCategorie(String categorie) {
		pdt_code = categorie;
	}
	
	public String getCategorie() {
		return pdt_categorie;
	}
	
	public void setPrixHT(String prix) {
		pdt_code = prix;
	}
	
	public double getPrixHT() {
		return pdt_prixHT;
	}
	
	public void setTauxTVA(String taux) {
		pdt_code = taux;
	}
	
	public double getTauxTVA() {
		return pdt_tauxTVA;
	}
	
}
