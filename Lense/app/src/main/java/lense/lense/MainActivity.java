package lense.lense;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EnviarOnCLick(View v)
    {
        Thread nt = new Thread()
        {
            String res;

            EditText email; // = (EditText) findViewById(R.id.);
            EditText password; // = (EditText) findViewById(R.id.);


            @Override
            public void run()
            {
                String NAMESPACE="";
                String URL="";
                String METHOD_NAME="";
                String SOAP_ACTION="";

                SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME);

                request.addProperty("email",email.getText());
                request.addProperty("password",password.getText());

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12); // version de SOAP, ver en visual
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);

                try {
                    transporte.call(SOAP_ACTION,envelope);
                    SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
                    res = result.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

            }

        };
    }

}
