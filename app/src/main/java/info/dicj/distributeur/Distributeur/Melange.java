package info.dicj.distributeur.Distributeur;

import java.util.ArrayList;

import info.dicj.distributeur.exception.AucunDistribuableException;
import info.dicj.distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.exception.DebordementMelangeException;

public class Melange implements Recette
{
    private int nbBoissons;
    private int maxBoissons = 2;
    private ArrayList<Boisson> boisson;
    private ArrayList<Saveur> saveur;

    public Melange()
    {
        this.boisson = new ArrayList<>();
        this.saveur = new ArrayList<>();
    }

    public void ajouterBoisson(Boisson boisson) throws DebordementMelangeException, AucunDistribuableException
    {
        if (nbBoissons < maxBoissons)
        {
            boisson.consommer();
            this.boisson.add(boisson);
            nbBoissons++;
        }
        else
        {
            throw new DebordementMelangeException("Il n'est pas possible d'ajouter plus de 2 boissons");
        }
    }

    public void ajouterSaveur(Saveur saveur) throws DebordementMelangeException, AucunDistribuableException
    {
        if (this.saveur.isEmpty())
        {
            saveur.consommer();
            this.saveur.add(saveur);
        }
        else
        {
            throw new DebordementMelangeException("Il n'est pas possible d'ajouter plus d'une saveur");
        }
    }

    public String getInformation()
    {
        String res = "Liste des boissons :\n";
        for (Boisson b : this.boisson)
        {
            res += b.getNom() + "\n";
        }
        if (!this.saveur.isEmpty())
        {
            res += "Saveur : " + this.saveur.get(0).getNom();
        }
        return res;
    }

    public Recette getRecette() throws AucunMelangeException
    {
        if (this.estPret())
        {
            return this;
        }
        else
        {
            throw new AucunMelangeException("Aucun mélange n'est effectué");
        }
    }

    public boolean estPret()
    {
        return this.boisson.size() == 1 || this.boisson.size() == 2;
    }
}
