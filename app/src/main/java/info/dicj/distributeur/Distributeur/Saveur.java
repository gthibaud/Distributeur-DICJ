package info.dicj.distributeur.Distributeur;

public class Saveur extends Distribuable
{
    public Saveur(String nom, String description)
    {
        super(1, nom, description);
    }

    public String toString()
    {
        return this.getNom();
    }
}
