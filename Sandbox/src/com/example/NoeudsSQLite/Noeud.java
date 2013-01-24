package com.example.NoeudsSQLite;


public class Noeud {
	
	private String nom;
	private String contenuQrcode;
	// l'id du père
	private int pere;
	// l'int qu'on recherchera dans la table des metadatas
	private int meta;
	private int id;
	
	public Noeud(String nom, String contenuQrcode, int pere, int meta) {
		super();
		this.nom = nom;
		this.contenuQrcode = contenuQrcode;
		this.pere = pere;
		this.meta = meta;
		this.id = 0;
	}
	
	
	
	public Noeud() {
		// TODO Auto-generated constructor stub
		super();
		this.nom = "";
		this.contenuQrcode = "";
		this.pere = 0;
		this.meta = 0;
		this.id = 0;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Noeud) {
			if (((Noeud) o).getPere() == pere && ((Noeud) o).getNom() == nom && ((Noeud) o).getContenuQrcode() == contenuQrcode && ((Noeud) o).getMeta() == meta) {
				return true;
			}			
		}
		return false;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String ret = "";
		ret += "Noeud : " + nom + ", QRCode : " + contenuQrcode + ", fils de " + pere + ", MetaData : " + meta;
		return ret;
	}



	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getContenuQrcode() {
		return contenuQrcode;
	}
	public void setContenuQrcode(String contenuQrcode) {
		this.contenuQrcode = contenuQrcode;
	}
	public int getPere() {
		return pere;
	}
	public void setPere(int pere) {
		this.pere = pere;
	}
	public int getMeta() {
		return meta;
	}
	public void setMeta(int meta) {
		this.meta = meta;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
