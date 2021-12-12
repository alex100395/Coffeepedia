package com.example.coffeepedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import static java.lang.System.in;

public class TypeOfCoffeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_coffee);
    }
    public void Brazil(View v)
    {
        TextView tv = findViewById(R.id.textReplace);
        tv.setText("Brazil\n\nBrazil stands alone, because they are such a large producer and have a widely varied output. Brazilian coffee is processed in three ways: dry (natural), wet (washed), and semi-washed (pulp natural). " +
                "During dry processing, coffee is dried while still in the cherry, whereas with the wet process, the coffee is stripped of its outer layers before the drying process. " +
                "Pulped natural is done by pulping a coffee, but emitting the fermentation stage in order to remove the skin. Chocolate and spice flavors are common and these coffees linger in your mouth in comparison to other South American coffees. " +
                "Some Brazilian beans have a pronounced nutty quality and a heavy body which makes them a frequent component in espresso blends.");

        WebView wv = findViewById(R.id.browser);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://en.wikipedia.org/wiki/Brazil");
    }
    public void Kenya(View v)
    {
        TextView tv = findViewById(R.id.textReplace);
        tv.setText("Kenya\n\nMost Kenyan coffee is processed by wet method to ensure the best quality. " +
                "The sugary coating that remains on the beans is removed through a fermentation process and then they are dried. " +
                "Processing, variety, and the fact that most of the coffee is grown without shade, " +
                "allows Kenyan coffee to have savory and sweet characteristics that are noticeable in the form of a black currant tartness or tomato like acidity.");

        WebView wv = findViewById(R.id.browser);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://en.wikipedia.org/wiki/Kenya");
    }
    public void CentralAmerica(View v)
    {
        TextView tv = findViewById(R.id.textReplace);
        tv.setText("Central America\n\nWhen we think of Central America, many of us might think of Guatemala and Costa Rica. " +
                "This region as a whole is one of the largest contributors to the global coffee supply. " +
                "Central America often produces bright and clean coffees, whereas the flavors produced in Guatemala, Mexico and Nicaragua are somewhat less sweet than those from South America. " +
                "Other flavors commonly found in these coffees range from fruit to nuts; these fruity flavors often work well with cocoa and spice flavors.");

        WebView wv = findViewById(R.id.browser);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://en.wikipedia.org/wiki/Central_America");
    }
    public void SouthAmerica(View v)
    {
        TextView tv = findViewById(R.id.textReplace);
        tv.setText("South America\n\nColombian coffee is often thought of when talking about South American coffees, " +
                "which makes perfect sense when thinking about how they rank among the top three coffee producing countries in the world. " +
                "Their coffee also has the most recognizable flavor for most North Americans, which is why the flavor reminds some people of a classic coffee. They tend to stick with wet (washed) processing: " +
                "this produces a mellow acidity and clean cup. Common flavors found in South African coffee include nuts, chocolate and caramel tones.");

        WebView wv = findViewById(R.id.browser);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://en.wikipedia.org/wiki/South_America");
    }
    public void Ethiopia(View v)
    {
        TextView tv = findViewById(R.id.textReplace);
        tv.setText("Ethiopia\n\nEthiopian coffees are processed either natural or washed: " +
                "two different processes that create very different flavor profiles. " +
                "Washed coffees have a floral, tea-like delicacy to them, while natural coffees tend to be heavy, fruity, and wine-like. Washed coffees can often express lemongrass or jasmine characteristics, and they are much lighter and drier on the palate. A naturally processed Ethiopian coffee has more of a syrupy body, along with a strong sweet berry flavor. " +
                "Ethiopia is in a category of its own because of its great biodiversity and the many varieties of coffee grown there.");

        WebView wv = findViewById(R.id.browser);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://en.wikipedia.org/wiki/Ethiopia");
    }
    public void Indonesia(View v)
    {
        TextView tv = findViewById(R.id.textReplace);
        tv.setText("Indonesia\n\nCoffees from Indonesia have dark and deep flavors, with almost an earthiness to them. " +
                "These coffees often have heavy, musty notes with a long lasting finish that feels like unsweetened or dark cocoa. One coffee from this region that is familiar to many would be Sumatran coffee which takes a well to dark roasting. " +
                "Sumatra produces Mandheling and Ankola, which are two of the world’s most famous and high quality coffees. " +
                "There are usually smokey or toasted flavors with a lot of complexity present in a cup of Sumatran coffee.");

        WebView wv = findViewById(R.id.browser);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("https://en.wikipedia.org/wiki/Indonesia");
    }

    public void goToMaps(View v)
    {
        RadioGroup group = findViewById(R.id.locations);
        int id = group.getCheckedRadioButtonId();
        LatLng coordinates;
        Bundle arguments = new Bundle();

        if(id == R.id.radioButton1)
        {
            coordinates = new LatLng(-14.23, -51.92);
            arguments.putString("country", "brazil");
            arguments.putParcelable("coord", coordinates);
        }
        else if(id == R.id.radioButton2)
        {
            coordinates = new LatLng(-0.02, 37.90);
            arguments.putString("country", "kenya");
            arguments.putParcelable("coord", coordinates);
        }
        else if(id == R.id.radioButton3)
        {
            coordinates = new LatLng(-8.78, -55.49);
            arguments.putString("country", "southamerica");
            arguments.putParcelable("coord", coordinates);
        }
        else if(id == R.id.radioButton4)
        {
            coordinates = new LatLng(12.76, -85.60);
            arguments.putString("country", "centralamerica");
            arguments.putParcelable("coord", coordinates);
        }
        else if(id == R.id.radioButton5)
        {
            coordinates = new LatLng(9.14, 40.48);
            arguments.putString("country", "ethiopia");
            arguments.putParcelable("coord", coordinates);
        }
        else if(id == R.id.radioButton6)
        {
            coordinates = new LatLng(-0.78, 113.92);
            arguments.putString("country", "indonesia");
            arguments.putParcelable("coord", coordinates);
        }

        Intent in = new Intent(this, MapsActivity.class);
        in.putExtras(arguments);
        startActivity(in);
    }

}
