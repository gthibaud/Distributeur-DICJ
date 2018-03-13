package info.dicj.distributeur.Distributeur;

import info.dicj.distributeur.exception.AucunDistribuableException;

public class Distribuable implements Produit
{
    private int quantite;
    private String nom;
    private String description;
    public int MAX = 10;

    public Distribuable(int quantite, String nom, String description)
    {
        this.quantite = quantite;
        this.nom = nom;
        this.description = description;
    }

    @Override
    public boolean estVide()
    {
        return this.quantite == 0;
    }

    @Override
    public String getDescription()
    {
        return this.description;
    }

    @Override
    public String getNom()
    {
        return this.nom;
    }

    public void ajouter()
    {
        this.quantite++;
    }

    public void consommer() throws AucunDistribuableException
    {
        if (this.quantite == 0)
        {
            throw new AucunDistribuableException("Ce distribuable n'est plus disponible, stock épuisé !");
        }
        this.quantite--;
    }
}
