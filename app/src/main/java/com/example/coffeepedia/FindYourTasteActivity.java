package com.example.coffeepedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class FindYourTasteActivity extends AppCompatActivity {

    public final static String mes = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_taste);
    }

    public void order(View v)
    {
        RadioGroup g1 = findViewById(R.id.group1);
        EditText et = findViewById(R.id.orderName);
        Switch del = findViewById(R.id.delivery);
        CheckBox c1 = findViewById(R.id.check1);
        CheckBox c2 = findViewById(R.id.check2);
        CheckBox c3 = findViewById(R.id.check3);

        String name = et.getText().toString();
        String beans = "";
        String include = "";
        String delivery = "";

        int id1 = g1.getCheckedRadioButtonId();
        int ck1 = R.id.check1;

        if(id1 == R.id.brazil)
        {
            beans = "brazil";
        }
        else if(id1 == R.id.kenya)
        {
            beans = "kenya";
        }
        else if(id1 == R.id.centralAmerica)
        {
            beans = "Central America";
        }
        else if(id1 == R.id.southAmerica)
        {
            beans = "South America";
        }
        else if(id1 == R.id.ethiopia)
        {
            beans = "Ethiopia";
        }
        else if(id1 == R.id.indonesia)
        {
            beans = "Indonesia";
        }
        if(c1.isChecked() == true)
        {
            include = "sugar ";
        }
        if(c2.isChecked() == true)
        {
            include += "milk ";
        }
        if(c3.isChecked() == true)
        {
            include += "extra espresso shot ";
        }
        if(del.isChecked())
        {
            delivery = "Your coffee is on its way.";
        }
        else
        {
            delivery = "Your coffee is already getting cold.";
        }

        String message = "Hey there " + name + "." +
                    "\nThank you for ordering a " +
                    beans + " with " + include + "included.\n"
                + delivery;


        //Notification
        EditText e1 = findViewById(R.id.orderName);
        EditText e2 = findViewById(R.id.orderAddress);

        String author = e1.getText().toString().trim();
        String subject = e2.getText().toString().trim();
        String body = "You will be redirected to the home page" +
                      "\nin 15 seconds!";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            //create the notification channel
            NotificationChannel channel = new NotificationChannel(
                    "1",
                    "channel1",
                    NotificationManager.IMPORTANCE_DEFAULT);

            //create the notification manager
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            //create the notification
            NotificationCompat.Builder notification = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle("Thank You " + author + " for placing an order")

                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            notification.setStyle(new NotificationCompat.BigTextStyle().bigText("Your address is: " + subject + ".\n" + body));

            NotificationManagerCompat notifyAdmin = NotificationManagerCompat.from(this);
            notifyAdmin.notify(1, notification.build());
        }
        else
        {
            NotificationManager manager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(getApplicationContext())
                    .setSmallIcon(android.R.drawable.ic_dialog_alert)
                    .setContentTitle(author + "-" + subject)
                    .setContentText(body).build();
            manager.notify(1, notification);

        }

        Intent in = new Intent(this, Comfirmation.class);
        in.putExtra(mes, message);
        startActivity(in);
    }
    public void setMsg(View v)
    {
        Switch del = findViewById(R.id.delivery);
        TextView tv = findViewById(R.id.takeAway);
        if (del.isChecked()) {
            tv.setText(" $5 dollar charge will be applied");
        }
        else
        {
            tv.setText("No delivery charges");
        }
    }
}
