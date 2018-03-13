package info.dicj.distributeur.Distributeur;

public class Boisson extends Distribuable
{
    public Boisson(String nom, String description)
    {
        super(10, nom, description);
    }

    public String toString()
    {
        return this.getNom();
    }
}
