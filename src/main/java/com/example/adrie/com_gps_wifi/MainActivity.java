package com.example.adrie.com_gps_wifi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity
{
    // Declaration des boutons sur
    private ImageButton gps;

    private String IPAddress = "192.168.4.1", portNumber = "80", serialNumber = "1234abcd",
            latitude1 = "", longitude1 = "",
            latitude2 = "", longitude2 = "",
            latitude3 = "", longitude3 = "",
            latitude4 = "", longitude4 = "",
            latitude5 = "", longitude5 = "",
            latitude6 = "", longitude6 = "",
            latitude7 = "", longitude7 = "",
            latitude8 = "", longitude8 = "",
            latitude9 = "", longitude9 = "",
            latitude10 = "", longitude10 = "";

    private TextView Value;
    private String level;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign buttons
        gps = findViewById(R.id.gps);

        ImageButton database = findViewById(R.id.database);
        ImageButton info = findViewById(R.id.info);

        info.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent t = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(t);
            }
        });

        gps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v1)
            {
                // get the pin number
                String parameterValue = "";
                // get the ip address
                String ipAddress = IPAddress.trim();
                // get the port number
                String portNumberfinal = portNumber.trim();

                // get the pin number from the button that was clicked
                if(v1.getId()==gps.getId())
                {
                    parameterValue = "13";
                }

                // execute HTTP request
                if (latitude10.length() == 0)
                {
                    if (ipAddress.length() > 0 && portNumberfinal.length() > 0)
                    {
                        new HttpRequestAsyncTask1(v1.getContext(), parameterValue, ipAddress, portNumberfinal, "pin").execute();
                    }
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Il est impossible de relever plus de 10 coordonnées.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        database.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v2)
            {
                String finalLatitude1 = latitude1.trim();
                String finalLongitude1 = longitude1.trim();
                String finalLatitude2 = latitude2.trim();
                String finalLongitude2 = longitude2.trim();
                String finalLatitude3 = latitude3.trim();
                String finalLongitude3 = longitude3.trim();
                String finalLatitude4 = latitude4.trim();
                String finalLongitude4 = longitude4.trim();
                String finalLatitude5 = latitude5.trim();
                String finalLongitude5 = longitude5.trim();
                String finalLatitude6 = latitude6.trim();
                String finalLongitude6 = longitude6.trim();
                String finalLatitude7 = latitude7.trim();
                String finalLongitude7 = longitude7.trim();
                String finalLatitude8 = latitude8.trim();
                String finalLongitude8 = longitude8.trim();
                String finalLatitude9 = latitude9.trim();
                String finalLongitude9 = longitude9.trim();
                String finalLatitude10 = latitude10.trim();
                String finalLongitude10 = longitude10.trim();

                // execute HTTP request
                if (latitude10.length() > 0)
                {
                    if (serialNumber.length() > 0
                            && finalLatitude1.length() > 0 && finalLongitude1.length() > 0
                            && finalLatitude2.length() > 0 && finalLongitude2.length() > 0
                            && finalLatitude3.length() > 0 && finalLongitude3.length() > 0
                            && finalLatitude4.length() > 0 && finalLongitude4.length() > 0
                            && finalLatitude5.length() > 0 && finalLongitude5.length() > 0
                            && finalLatitude6.length() > 0 && finalLongitude6.length() > 0
                            && finalLatitude7.length() > 0 && finalLongitude7.length() > 0
                            && finalLatitude8.length() > 0 && finalLongitude8.length() > 0
                            && finalLatitude9.length() > 0 && finalLongitude9.length() > 0
                            && finalLatitude10.length() > 0 && finalLongitude10.length() > 0)
                    {
                        new MainActivity.HttpRequestAsyncTask2(
                                v2.getContext(), serialNumber,
                                finalLatitude1, finalLongitude1,
                                finalLatitude2, finalLongitude2,
                                finalLatitude3, finalLongitude3,
                                finalLatitude4, finalLongitude4,
                                finalLatitude5, finalLongitude5,
                                finalLatitude6, finalLongitude6,
                                finalLatitude7, finalLongitude7,
                                finalLatitude8, finalLongitude8,
                                finalLatitude9, finalLongitude9,
                                finalLatitude10, finalLongitude10).execute();
                    }
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Il manque des coordonnées.";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        SeekBar seek1 = findViewById(R.id.seek1);
        Value = findViewById(R.id.TextValue);

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int progressChanged = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                progressChanged = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar)
            {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar)
            {
                if (progressChanged <34)
                {
                    level = getString(R.string.low);
                }
                else if (progressChanged >66)
                {
                    level = getString(R.string.total);
                }
                else
                {
                    level = getString(R.string.medium);
                }
                Value.setText(level);
            }
        });
    }

    /**
     * Description: Send an HTTP Get request to a specified ip address and port.
     * Also send a parameter "parameterName" with the value of "parameterValue".
     * @param parameterValue the pin number to toggle
     * @param ipAddress the ip address to send the request to
     * @param portNumber the port number of the ip address
     * @param parameterName
     * @return The ip address' reply text, or an ERROR message is it fails to receive one
     */
    public String sendRequest1(String parameterValue, String ipAddress, String portNumber, String parameterName)
    {
        String serverResponse1 = "ERROR";

        try
        {
            HttpClient httpclient1 = new DefaultHttpClient(); // create an HTTP client
            // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)
            URI website1 = new URI("http://"+ipAddress+":"+portNumber+"/?"+parameterName+"="+parameterValue);
            HttpGet getRequest1 = new HttpGet(); // create an HTTP GET object
            getRequest1.setURI(website1); // set the URL of the GET request
            HttpResponse response1 = httpclient1.execute(getRequest1); // execute the request
            // get the ip address server's reply
            InputStream content1 = null;
            content1 = response1.getEntity().getContent();
            BufferedReader in1 = new BufferedReader(new InputStreamReader(content1));
            serverResponse1 = in1.readLine();
            // Close the connection
            content1.close();
        }
        catch (ClientProtocolException e1)
        {
            // HTTP error
            serverResponse1 = e1.getMessage();
            e1.printStackTrace();
        }
        catch (IOException e1)
        {
            // IO error
            serverResponse1 = e1.getMessage();
            e1.printStackTrace();
        }
        catch (URISyntaxException e1)
        {
            // URL syntax error
            serverResponse1 = e1.getMessage();
            e1.printStackTrace();
        }
        // return the server's reply/response text
        return serverResponse1;
    }

    /**
     * Description: Send an HTTP Get request to a specified ip address and port.
     * Also send a parameter "parameterName" with the value of "parameterValue".
     * @param serialNumber the serial number of the drone.
     * The twenty other parameters are the positions.
     * @return The ip address' reply text, or an ERROR message is it fails to receive one
     */
    public String sendRequest2(String serialNumber,
                               String latitude1, String longitude1,
                               String latitude2, String longitude2,
                               String latitude3, String longitude3,
                               String latitude4, String longitude4,
                               String latitude5, String longitude5,
                               String latitude6, String longitude6,
                               String latitude7, String longitude7,
                               String latitude8, String longitude8,
                               String latitude9, String longitude9,
                               String latitude10, String longitude10)
    {
        String serverResponse2 = "ERROR";
        try
        {
            HttpClient httpclient2 = new DefaultHttpClient(); // create an HTTP client
            // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)
            URI website2 = new URI("http://droninio.ovh/recept.php?serialnumber=" + serialNumber
                    +"&lat_1=" + latitude1 + "&long_1=" + longitude1
                    +"&lat_2=" + latitude2 + "&long_2=" + longitude2
                    +"&lat_3=" + latitude3 + "&long_3=" + longitude3
                    +"&lat_4=" + latitude4 + "&long_4=" + longitude4
                    +"&lat_5=" + latitude5 + "&long_5=" + longitude5
                    +"&lat_6=" + latitude6 + "&long_6=" + longitude6
                    +"&lat_7=" + latitude7 + "&long_7=" + longitude7
                    +"&lat_8=" + latitude8 + "&long_8=" + longitude8
                    +"&lat_9=" + latitude9 + "&long_9=" + longitude9
                    +"&lat_10=" + latitude10 + "&long_10=" + longitude10
                    + "/?");
            HttpGet getRequest2 = new HttpGet(); // create an HTTP GET object
            getRequest2.setURI(website2); // set the URL of the GET request
            HttpResponse response2 = httpclient2.execute(getRequest2); // execute the request
            // get the ip address server's reply
            InputStream content2 = null;
            content2 = response2.getEntity().getContent();
            BufferedReader in2 = new BufferedReader(new InputStreamReader(content2));
            serverResponse2 = in2.readLine();
            // Close the connection
            content2.close();
        }
        catch(ClientProtocolException e2)
        {
            // HTTP error
            serverResponse2 = e2.getMessage();
            e2.printStackTrace();
        }
        catch (IOException e2)
        {
            // IO error
            serverResponse2 = e2.getMessage();
            e2.printStackTrace();
        }
        catch (URISyntaxException e2)
        {
            // URL syntax error
            serverResponse2 = e2.getMessage();
            e2.printStackTrace();
        }
        // return the server's reply/response text
        return serverResponse2;
    }

    /**
     * Description: Send an HTTP Get request to a specified ip address and port.
     * Also send a parameter "parameterName" with the value of "parameterValue".
     * @param parameterValue the pin number to toggle
     * @param ipAddress the ip address to send the request to
     * @param portNumber the port number of the ip address
     * @return The ip address' reply text, or an ERROR message is it fails to receive one

    public String sendRequest3(String parameterValue, String ipAddress, String portNumber)
    {
        String serverResponse3 = "ERROR";

        try
        {
            HttpClient httpclient3 = new DefaultHttpClient(); // create an HTTP client
            // define the URL e.g. http://myIpaddress:myport/?pin=13 (to toggle pin 13 for example)
            URI website3 = new URI("http://"+ipAddress+":"+portNumber+"/?"+parameterValue);
            HttpGet getRequest3 = new HttpGet(); // create an HTTP GET object
            getRequest3.setURI(website3); // set the URL of the GET request
            HttpResponse response3 = httpclient3.execute(getRequest3); // execute the request
            // get the ip address server's reply
            InputStream content3 = null;
            content3 = response3.getEntity().getContent();
            BufferedReader in3 = new BufferedReader(new InputStreamReader(content3));
            serverResponse3 = in3.readLine();
            // Close the connection
            content3.close();
        }
        catch (ClientProtocolException e3)
        {
            // HTTP error
            serverResponse3 = e3.getMessage();
            e3.printStackTrace();
        }
        catch (IOException e3)
        {
            // IO error
            serverResponse3 = e3.getMessage();
            e3.printStackTrace();
        }
        catch (URISyntaxException e3)
        {
            // URL syntax error
            serverResponse3 = e3.getMessage();
            e3.printStackTrace();
        }
        // return the server's reply/response text
        return serverResponse3;
    }*/


    /**
     * An AsyncTask is needed to execute HTTP requests in the background so that they do not
     * block the user interface.
     */
    private class HttpRequestAsyncTask1 extends AsyncTask<Void, Void, Void>
    {
        // declare variables needed
        private String requestReply,ipAddress, portNumber;
        private Context context;
        private String parameter;
        private String parameterValue;

        /**
         * Description: The asyncTask class constructor. Assigns the values used in its other methods.
         * @param context the application context, needed to create the dialog
         * @param parameterValue the pin number to toggle
         * @param ipAddress the ip address to send the request to
         * @param portNumber the port number of the ip address
         */
        public HttpRequestAsyncTask1(Context context, String parameterValue, String ipAddress, String portNumber, String parameter)
        {
            this.context = context;
            this.ipAddress = ipAddress;
            this.parameterValue = parameterValue;
            this.portNumber = portNumber;
            this.parameter = parameter;
        }

        /**
         * Name: doInBackground
         * Description: Sends the request to the ip address
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids)
        {
            requestReply = sendRequest1(parameterValue,ipAddress,portNumber, parameter);
            return null;
        }

        /**
         * Name: onPostExecute
         * Description: This function is executed after the HTTP request returns from the ip address.
         * The function sets the dialog's message with the reply text from the server and display the dialog
         * if it's not displayed already (in case it was closed by accident);
         * @param aVoid void parameter
         */
        @Override
        protected void onPostExecute(Void aVoid)
        {
            String[] temp;
            String delimiter = " / ";
            temp = requestReply.split(delimiter);

            if (latitude1.length()==0)
            {
                latitude1 = temp[0];
                longitude1 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 1 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()==0)
            {
                latitude2 = temp[0];
                longitude2 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 2 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()==0)
            {
                latitude3 = temp[0];
                longitude3 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 3 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()>0
                    && latitude4.length()==0){
                latitude4 = temp[0];
                longitude4 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 4 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()>0
                    && latitude4.length()>0
                    && latitude5.length()==0)
            {
                latitude5 = temp[0];
                longitude5 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 5 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()>0
                    && latitude4.length()>0
                    && latitude5.length()>0
                    && latitude6.length()==0)
            {
                latitude6 = temp[0];
                longitude6 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 6 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()>0
                    && latitude4.length()>0
                    && latitude5.length()>0
                    && latitude6.length()>0
                    && latitude7.length()==0)
            {
                latitude7 = temp[0];
                longitude7 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 7 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()>0
                    && latitude4.length()>0
                    && latitude5.length()>0
                    && latitude6.length()>0
                    && latitude7.length()>0
                    && latitude8.length()==0)
            {
                latitude8 = temp[0];
                longitude8 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 8 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else if (latitude1.length()>0
                    && latitude2.length()>0
                    && latitude3.length()>0
                    && latitude4.length()>0
                    && latitude5.length()>0
                    && latitude6.length()>0
                    && latitude7.length()>0
                    && latitude8.length()>0
                    && latitude9.length()==0)
            {
                latitude9 = temp[0];
                longitude9 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 9 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else
            {
                latitude10 = temp[0];
                longitude10 = temp[1];
                Context context = getApplicationContext();
                CharSequence text = "Position 10 envoyée !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

        /**
         * Name: onPreExecute
         * Description: This function is executed before the HTTP request is sent to ip address.
         * The function will set the dialog's message and display the dialog.
         */
        @Override
        protected void onPreExecute()
        {
            if (latitude1.length()==0)
            {
                Context context = getApplicationContext();
                CharSequence text = "La réception des positions va commencer.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

    }

    /**
     * An AsyncTask is needed to execute HTTP requests in the background so that they do not
     * block the user interface.
     */
    private class HttpRequestAsyncTask2 extends AsyncTask<Void, Void, Void>
    {
        // declare variables needed
        private String requestReply, serialNumber,
                latitude1, longitude1,
                latitude2, longitude2,
                latitude3, longitude3,
                latitude4, longitude4,
                latitude5, longitude5,
                latitude6, longitude6,
                latitude7, longitude7,
                latitude8, longitude8,
                latitude9, longitude9,
                latitude10, longitude10;
        private Context context;

        /*
         * Description: The asyncTask class constructor. Assigns the values used in its other methods.
         * @param context the application context, needed to create the dialog
         * @param parameterValue the pin number to toggle
         * @param ipAddress the ip address to send the request to
         * @param portNumber the port number of the ip address
         */
        public HttpRequestAsyncTask2(Context context, String serialNumber,
                                    String latitude1, String longitude1,
                                    String latitude2, String longitude2,
                                    String latitude3, String longitude3,
                                    String latitude4, String longitude4,
                                    String latitude5, String longitude5,
                                    String latitude6, String longitude6,
                                    String latitude7, String longitude7,
                                    String latitude8, String longitude8,
                                    String latitude9, String longitude9,
                                    String latitude10, String longitude10)
        {
            this.context = context;
            this.serialNumber = serialNumber;
            this.latitude1 = latitude1;
            this.longitude1 = longitude1;
            this.latitude2 = latitude2;
            this.longitude2 = longitude2;
            this.latitude3 = latitude3;
            this.longitude3 = longitude3;
            this.latitude4 = latitude4;
            this.longitude4 = longitude4;
            this.latitude5 = latitude5;
            this.longitude5 = longitude5;
            this.latitude6 = latitude6;
            this.longitude6 = longitude6;
            this.latitude7 = latitude7;
            this.longitude7 = longitude7;
            this.latitude8 = latitude8;
            this.longitude8 = longitude8;
            this.latitude9 = latitude9;
            this.longitude9 = longitude9;
            this.latitude10 = latitude10;
            this.longitude10 = longitude10;
        }

        /**
         * Name: doInBackground
         * Description: Sends the request to the ip address
         * @param voids
         * @return
         */
        @Override
        protected Void doInBackground(Void... voids)
        {
            requestReply = sendRequest2(serialNumber,
                    latitude1, longitude1,
                    latitude2, longitude2,
                    latitude3, longitude3,
                    latitude4, longitude4,
                    latitude5, longitude5,
                    latitude6, longitude6,
                    latitude7, longitude7,
                    latitude8, longitude8,
                    latitude9, longitude9,
                    latitude10, longitude10);
            return null;
        }

        /**
         * Name: onPostExecute
         * Description: This function is executed after the HTTP request returns from the ip address.
         * The function sets the dialog's message with the reply text from the server and display the dialog
         * if it's not displayed already (in case it was closed by accident);
         * @param aVoid void parameter
         */
        @Override
        protected void onPostExecute(Void aVoid)
        {
            Context context = getApplicationContext();
            CharSequence text = "Les positions ont bien été envoyées";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        /**
         * Name: onPreExecute
         * Description: This function is executed before the HTTP request is sent to ip address.
         * The function will set the dialog's message and display the dialog.
         */
        @Override
        protected void onPreExecute()
        {
            Context context = getApplicationContext();
            CharSequence text = "Les positions vont être envoyées au serveur...";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

    }

    /**
     * An AsyncTask is needed to execute HTTP requests in the background so that they do not
     * block the user interface.
    private class HttpRequestAsyncTask3 extends AsyncTask<Void, Void, Void>
    {
        // declare variables needed
        private String requestReply, ipAddress, portNumber;
        private Context context;
        private String parameter;
        private String parameterValue;

         * Description: The asyncTask class constructor. Assigns the values used in its other methods.
         * @param context the application context, needed to create the dialog
         * @param parameterValue the pin number to toggle
         * @param ipAddress the ip address to send the request to
         * @param portNumber the port number of the ip address
        public HttpRequestAsyncTask3(Context context, String parameterValue, String ipAddress, String portNumber)
        {
            this.context = context;
            this.ipAddress = ipAddress;
            this.parameterValue = parameterValue;
            this.portNumber = portNumber;
        }

         * Name: doInBackground
         * Description: Sends the request to the ip address
         * @param voids
         * @return
        @Override
        protected Void doInBackground(Void... voids)
        {
            requestReply = sendRequest3(parameterValue,ipAddress,portNumber);
            return null;
        }

         * Name: onPostExecute
         * Description: This function is executed after the HTTP request returns from the ip address.
         * The function sets the dialog's message with the reply text from the server and display the dialog
         * if it's not displayed already (in case it was closed by accident);
         * @param aVoid void parameter
        @Override
        protected void onPostExecute(Void aVoid)
        {
            serialNumber = requestReply;
        }

         * Name: onPreExecute
         * Description: This function is executed before the HTTP request is sent to ip address.
         * The function will set the dialog's message and display the dialog.
        @Override
        protected void onPreExecute()
        {
            Context context = getApplicationContext();
            CharSequence text = "Envoie de la demande du numéro de série et du niveau de batterie au drone...";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }*/
}