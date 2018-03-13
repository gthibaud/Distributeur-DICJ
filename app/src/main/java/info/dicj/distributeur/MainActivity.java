package info.dicj.distributeur;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import info.dicj.distributeur.Distributeur.Distributeur;
import info.dicj.distributeur.Distributeur.Recette;
import info.dicj.distributeur.exception.AucunDistribuableException;
import info.dicj.distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.exception.DebordementMelangeException;

public class MainActivity extends AppCompatActivity {

    private Distributeur distributeur;
    private NotificationManager nm;
    private static final int ID_NOTIFICATION = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distributeur);
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        distributeur = new Distributeur();

        RadioGroup radiogroupe = findViewById(R.id.appreciation_cours);
        final TextView message_utilisateur = findViewById(R.id.message_utilisateur);
        final EditText nom_utilisateur = findViewById(R.id.nom);
        radiogroupe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int choix = group.getCheckedRadioButtonId();
                if (!nom_utilisateur.getText().toString().isEmpty()) {
                    if (choix == R.id.reponse1) {
                        message_utilisateur.setText("Bonjour " + nom_utilisateur.getText().toString() + ", merci pour votre enthousiasme !");
                    } else {
                        message_utilisateur.setText("Bonjour " + nom_utilisateur.getText().toString() + ", c'est pas gentil :(");
                    }
                }
            }
        });

        CheckBox verification = findViewById(R.id.verification);
        final Button verser = findViewById(R.id.verser_precedent);
        verification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    verser.setVisibility(View.VISIBLE);
                } else {
                    verser.setVisibility(View.GONE);
                }
            }
        });

        Log.i("DICJ", "MainActivity.oncreate");

    }

    public void reverser(View view) {

        Log.i("DICJ", "MainActivity.reverser");


        try {
            distributeur.dupliquerMelange();
            verser(view);
        } catch (AucunMelangeException e) {
            e.printStackTrace();
        } catch (AucunDistribuableException e) {
            e.printStackTrace();
        } catch (DebordementMelangeException e) {
            e.printStackTrace();
        }

    }

    public void verser(View view) {

        Log.i("DICJ", "MainActivity.verser");

        this.creerNotification(view);

        try {
            afficherRecette(distributeur.melangerRecette());

        } catch (AucunMelangeException e) {
            e.printStackTrace();
        }

    }


    public void ajouterSaveur(View view) {

        Log.i("DICJ", "MainActivity.ajouterSaveur");


        try {
            switch (view.getId()) {

                case R.id.sEpice:
                    distributeur.ajouterSaveur("EPICE");
                    break;
                case R.id.sGingembre:
                    distributeur.ajouterSaveur("GINGEMBRE");
                    break;
                case R.id.sBacon:
                    distributeur.ajouterSaveur("BACON");
                    break;
            }
        } catch (AucunDistribuableException e) {
            e.printStackTrace();
        } catch (DebordementMelangeException e) {
            e.printStackTrace();
        }

    }

    public void ajouterBoisson(View view) {

        Log.i("DICJ", "MainActivity.ajouterBoisson");


        try {
            switch (view.getId()) {
                case R.id.bPepsi:
                    distributeur.ajouterBoisson("PEPSI");
                    break;
                case R.id.bOrangeade:
                    distributeur.ajouterBoisson("ORANGEADE");
                    break;
                case R.id.bRacinette:
                    distributeur.ajouterBoisson("RACINETTE");
                    break;
                case R.id.bFraise:
                    distributeur.ajouterBoisson("FRAISE");
                    break;
            }
        } catch (AucunDistribuableException e) {
            e.printStackTrace();
        } catch (DebordementMelangeException e) {
            e.printStackTrace();
        }

    }

    public void nouveau(View view) {

        Log.i("DICJ", "MainActivity.nouveau");


        distributeur.nouveauMelange();

    }

    public void afficherRecette(Recette recette) {


        Context context = getApplicationContext();
        CharSequence information = recette.getInformation();
        int duree = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, information, duree);
        toast.show();

    }

    public void creerNotification(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification n = new Notification.Builder(this)
                .setContentText("Félicitations !")
                .setContentTitle("Votre boisson a été bien envoyée")
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSound(soundUri)
                .build();

        try
        {
            nm.notify(ID_NOTIFICATION, n);
            Log.i("DIM", "Notification envoyée ?");
        }
        catch (Exception e)
        {
            Log.i("DIM", e.getMessage(), e);
        }
    }
}
