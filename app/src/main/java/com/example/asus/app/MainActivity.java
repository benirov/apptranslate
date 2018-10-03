package com.example.asus.app;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name;
    Spinner from;
    Spinner to;
    TextView viewtext;
    Button save;
    HttpClient user;
    HttpGet userGet;
    HttpResponse response;



    private RequestQueue queue;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        name = (EditText)findViewById(R.id.string);
        viewtext = (TextView)findViewById(R.id.textTraductor);
        from = (Spinner)findViewById(R.id.from);
        to = (Spinner)findViewById(R.id.to);
        save = (Button) findViewById(R.id.traducir);
        save.setOnClickListener(new View.OnClickListener()
                                {
                                    public  void onClick(View v)
                                    {

                                        obtenerData();
                                    }
                                }


        );


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public static boolean compruebaConexion(Context context)
    {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    public boolean compare(String to, String from)
    {
        boolean compare = false;
        if(to.equals(from))
        {
            compare = true;
        }
        return compare;
    };

    public  String returniso(String languages)
    {
        String ISO = "auto";
        switch (languages)
        {
            case "Automatic" :
                ISO =   "auto" ;
                break;

            case "Afrikaans" :
                ISO =   "af" ;
                break;

            case "Albanian" :
                ISO =   "sq" ;
                break;

            case "am" :
                ISO =   "Amharic" ;
                break;

            case "Arabic" :
                ISO =   "ar" ;
                break;

            case "Armenian" :
                ISO =   "hy" ;
                break;

            case "Azerbaijani" :
                ISO =   "az" ;
                break;

            case "Basque" :
                ISO =   "eu" ;
                break;

            case "Belarusian" :
                ISO =   "be" ;
                break;

            case "Bengali" :
                ISO =   "bn" ;
                break;

            case "Bosnian" :
                ISO =   "bs" ;
                break;

            case "Bulgarian" :
                ISO =   "bg" ;
                break;

            case "Catalan" :
                ISO =   "ca" ;
                break;

            case "Cebuano" :
                ISO =   "ceb" ;
                break;

            case "Chichewa" :
                ISO =   "ny" ;
                break;

            case "Chinese Simplified" :
                ISO =   "zh-cn" ;
                break;

            case "Chinese Traditional" :
                ISO =   "zh-tw" ;
                break;

            case "Corsican" :
                ISO =   "co" ;
                break;

            case "Croatian" :
                ISO =   "hr" ;
                break;

            case "Czech" :
                ISO =   "cs" ;
                break;

            case "Danish" :
                ISO =   "da" ;
                break;

            case "Dutch" :
                ISO =   "nl" ;
                break;

            case "English" :
                ISO =   "en" ;
                break;

            case "Esperanto" :
                ISO =   "eo" ;
                break;

            case "Estonian" :
                ISO =   "et" ;
                break;

            case "Filipino" :
                ISO =   "tl" ;
                break;

            case "Finnish" :
                ISO =   "fi" ;
                break;

            case "French" :
                ISO =   "fr" ;
                break;

            case "Frisian" :
                ISO =   "fy" ;
                break;

            case "Galician" :
                ISO =   "gl" ;
                break;

            case "Georgian" :
                ISO =   "ka" ;
                break;

            case "German" :
                ISO =   "de" ;
                break;

            case "Greek" :
                ISO =   "el" ;
                break;

            case "Gujarati" :
                ISO =   "gu" ;
                break;

            case "Haitian Creole" :
                ISO =   "ht" ;
                break;

            case "Hausa" :
                ISO =   "ha" ;
                break;

            case "Hawaiian" :
                ISO =   "haw" ;
                break;

            case "Hebrew" :
                ISO =   "iw" ;
                break;

            case "Hindi" :
                ISO =   "hi" ;
                break;

            case "Hmong" :
                ISO =   "hmn" ;
                break;

            case "Hungarian" :
                ISO =   "hu" ;
                break;

            case "Icelandic" :
                ISO =   "is" ;
                break;

            case "Igbo" :
                ISO =   "ig" ;
                break;

            case "Indonesian" :
                ISO =   "id" ;
                break;

            case "Irish" :
                ISO =   "ga" ;
                break;

            case "Italian" :
                ISO =   "it" ;
                break;

            case "Japanese" :
                ISO =   "ja" ;
                break;

            case "Javanese" :
                ISO =   "jw" ;
                break;

            case "Kannada" :
                ISO =   "kn" ;
                break;

            case "Kazakh" :
                ISO =   "kk" ;
                break;

            case "Khmer" :
                ISO =   "km" ;
                break;

            case "Korean" :
                ISO =   "ko" ;
                break;

            case "Kurdish (Kurmanji)" :
                ISO =   "ku" ;
                break;

            case "Kyrgyz" :
                ISO =   "ky" ;
                break;

            case "Lao" :
                ISO =   "lo" ;
                break;

            case "Latin" :
                ISO =   "la" ;
                break;

            case "Latvian" :
                ISO =   "lv" ;
                break;

            case "Lithuanian" :
                ISO =   "lt" ;
                break;

            case "Luxembourgish" :
                ISO =   "lb" ;
                break;

            case "Macedonian" :
                ISO =   "mk" ;
                break;

            case "Malagasy" :
                ISO =   "mg" ;
                break;

            case "Malay" :
                ISO =   "ms" ;
                break;

            case "Malayalam" :
                ISO =   "ml" ;
                break;

            case "Maltese" :
                ISO =   "mt" ;
                break;

            case "Maori" :
                ISO =   "mi" ;
                break;

            case "Marathi" :
                ISO =   "mr" ;
                break;

            case "Mongolian" :
                ISO =   "mn" ;
                break;

            case "Myanmar (Burmese)" :
                ISO =   "my" ;
                break;

            case "Nepali" :
                ISO =   "ne" ;
                break;

            case "Norwegian" :
                ISO =   "no" ;
                break;

            case "Pashto" :
                ISO =   "ps" ;
                break;

            case "Persian" :
                ISO =   "fa" ;
                break;

            case "Polish" :
                ISO =   "pl" ;
                break;

            case "Portuguese" :
                ISO =   "pt" ;
                break;

            case "Punjabi" :
                ISO =   "ma" ;
                break;

            case "Romanian" :
                ISO =   "ro" ;
                break;

            case "Russian" :
                ISO =   "ru" ;
                break;

            case "Samoan" :
                ISO =   "sm" ;
                break;

            case "Scots Gaelic" :
                ISO =   "gd" ;
                break;

            case "Serbian" :
                ISO =   "sr" ;
                break;

            case "Sesotho" :
                ISO =   "st" ;
                break;

            case "Shona" :
                ISO =   "sn" ;
                break;

            case "Sindhi" :
                ISO =   "sd" ;
                break;

            case "Sinhala" :
                ISO =   "si" ;
                break;

            case "Slovak" :
                ISO =   "sk" ;
                break;

            case "Slovenian" :
                ISO =   "sl" ;
                break;

            case "Somali" :
                ISO =   "so" ;
                break;

            case "Spanish" :
                ISO =   "es" ;
                break;

            case "Sundanese" :
                ISO =   "su" ;
                break;

            case "Swahili" :
                ISO =   "sw" ;
                break;

            case "Swedish" :
                ISO =   "sv" ;
                break;

            case "Tajik" :
                ISO =   "tg" ;
                break;

            case "Tamil" :
                ISO =   "ta" ;
                break;

            case "Telugu" :
                ISO =   "te" ;
                break;

            case "Thai" :
                ISO =   "th" ;
                break;

            case "Turkish" :
                ISO =   "tr" ;
                break;

            case "Ukrainian" :
                ISO =   "uk" ;
                break;

            case "Urdu" :
                ISO =   "ur" ;
                break;

            case "Uzbek" :
                ISO =   "uz" ;
                break;

            case "Vietnamese" :
                ISO =   "vi" ;
                break;

            case "Welsh" :
                ISO =   "cy" ;
                break;

            case "Xhosa" :
                ISO =   "xh" ;
                break;

            case "Yiddish" :
                ISO =   "yi" ;
                break;

            case "Yoruba" :
                ISO =   "yo" ;
                break;

            case "Zulu" :
                ISO =   "zu";
                break;
        }
        return ISO;

    };

    public  boolean validate()
    {

        if(name.getText().toString().equals("") )
        {
            return true;
        }
        return false;
    }

    //enviar datos

    private void obtenerData() {

        if (!compruebaConexion(this)) {
            Toast.makeText(getBaseContext(),"Necesaria conexión a internet ", Toast.LENGTH_SHORT).show();
        }
        else
            {
                if (validate())
                {
                    Toast.makeText(getApplicationContext(), "debe ingresar texto a traducir", Toast.LENGTH_SHORT).show();
                }
                else {
                    String url = "http://192.168.1.120:3000/translate/";
                    String data = name.getText().toString();
                    String textfrom = returniso(from.getSelectedItem().toString());
                    String textto = returniso(to.getSelectedItem().toString());
                    if(compare(textfrom, textto))
                    {
                        Toast.makeText(getApplicationContext(), "Idiomas deben ser diferentes", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        JSONObject jsonBody = new JSONObject();

                        try
                        {
                            jsonBody.put("data", data);
                            jsonBody.put("from", textfrom);
                            jsonBody.put("to", textto);
                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try
                                {
                                    String  status = response.getString("status");
                                    int numberStatus = Integer.parseInt(status);
                                    if(numberStatus == 200)
                                    {
                                        JSONObject  message = response.getJSONObject("message");

                                        String value = message.getString("text");
                                        viewtext.setText(value);

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Error de solicitud", Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Toast.makeText(getApplicationContext(),"Servidor no esta disponible" , Toast.LENGTH_SHORT).show();

                            }
                        }

                        );

                        queue.add(request);
                    }

                }
            }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
