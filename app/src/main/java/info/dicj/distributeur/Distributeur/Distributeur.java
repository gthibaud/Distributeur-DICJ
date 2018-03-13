package info.dicj.distributeur.Distributeur;

import java.util.ArrayList;

import info.dicj.distributeur.exception.AucunDistribuableException;
import info.dicj.distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.exception.DebordementMelangeException;
import info.dicj.distributeur.impl.Bacon;
import info.dicj.distributeur.impl.Epice;
import info.dicj.distributeur.impl.Fraise;
import info.dicj.distributeur.impl.Gingembre;
import info.dicj.distributeur.impl.Orangeade;
import info.dicj.distributeur.impl.Pepsi;
import info.dicj.distributeur.impl.Racinette;

public class Distributeur
{
    //Contient les différents types de boissons
    private ArrayList<Boisson> boisson;
    //Contient les différents types de saveurs
    private ArrayList<Saveur> saveur;
    //Mélange précédent
    private Melange melangePrecedent;
    //Mélange en cours dans le distributeur
    private Melange melangeCourant;

    public Distributeur()
    {
        this.melangeCourant = new Melange();

        this.boisson = new ArrayList<>();
        this.saveur = new ArrayList<>();

        this.boisson.add(new Pepsi());
        this.boisson.add(new Orangeade());
        this.boisson.add(new Racinette());
        this.boisson.add(new Fraise());

        this.saveur.add(new Epice());
        this.saveur.add(new Gingembre());
        this.saveur.add(new Bacon());
    }

    private void remplirDistributeur()
    {
        for (Boisson b : this.boisson)
        {
            b.ajouter();
        }
        for (Saveur s : this.saveur)
        {
            s.ajouter();
        }
    }

    public Recette melangerRecette() throws AucunMelangeException
    {
        return this.melangeCourant.getRecette();
    }

    public void nouveauMelange()
    {
        this.melangePrecedent = this.melangeCourant;
        this.melangeCourant = new Melange();
    }

    public Recette dupliquerMelange() throws AucunMelangeException, AucunDistribuableException, DebordementMelangeException
    {
        if (this.melangePrecedent != null)
        {
            this.melangeCourant = this.melangePrecedent;
            return this.melangeCourant.getRecette();
        }
        else
        {
            throw new AucunMelangeException("Aucun mélange précédent n'a été effectué");
        }
    }

    public void ajouterBoisson(String nom) throws DebordementMelangeException, AucunDistribuableException
    {
        switch(nom)
        {
            case "PEPSI":
                melangeCourant.ajouterBoisson(this.boisson.get(0));
                break;
            case "ORANGEADE":
                melangeCourant.ajouterBoisson(this.boisson.get(1));
                break;
            case "RACINETTE":
                melangeCourant.ajouterBoisson(this.boisson.get(2));
                break;
            case "FRAISE":
                melangeCourant.ajouterBoisson(this.boisson.get(3));
                break;
        }
    }

    public void ajouterSaveur(String nom) throws DebordementMelangeException, AucunDistribuableException
    {
        switch(nom)
        {
            case "EPICE":
                melangeCourant.ajouterSaveur(this.saveur.get(0));
                break;
            case "GINGEMBRE":
                melangeCourant.ajouterSaveur(this.saveur.get(1));
                break;
            case "BACON":
                melangeCourant.ajouterSaveur(this.saveur.get(2));
                break;
        }
    }
}
