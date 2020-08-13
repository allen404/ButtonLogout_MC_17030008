package id.mobilecomputing.mc_buttonlogout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.mobilecomputing.mc_buttonlogout.auth.SimpleLoginActivity;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtDialPhone,websiteUri, locationUri, textShare;
    Button btnWebsite, btnLocation, btnPhoneNumber, btnShareText, btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        edtDialPhone = findViewById(R.id.edtPhoneNumber);
        websiteUri = findViewById(R.id.edtWebsiteUri);
        locationUri = findViewById(R.id.edtLocationUri);
        textShare = findViewById(R.id.edtShareText);


        btnPhoneNumber = findViewById(R.id.btnPhoneNumber);
        btnPhoneNumber.setOnClickListener(this);

        btnWebsite = findViewById(R.id.btnWebsiteUri);
        btnWebsite.setOnClickListener(this);

        btnLocation  = findViewById(R.id.btnLocationUri);
        btnLocation.setOnClickListener(this);

        btnShareText = findViewById(R.id.btnShareText);
        btnShareText.setOnClickListener(this);

        btnSignOut = findViewById(R.id.btn_logout);
        btnSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPhoneNumber:
                if(TextUtils.isEmpty(edtDialPhone.getText().toString())){
                    edtDialPhone.setError("Tolong masukkan nomor telepon");
                    return;
                }
                Intent dialPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+ edtDialPhone.getText().toString()));
                startActivity(dialPhone);
                break;

            case R.id.btnWebsiteUri:
                if(TextUtils.isEmpty(websiteUri.getText().toString())){
                    websiteUri.setError("Tolong masukkan alamat website");
                    return;
                }
                Intent openWebsite = new Intent (Intent.ACTION_VIEW).setData(Uri.parse("https://" + websiteUri.getText().toString()));
                startActivity(openWebsite);
                break;

            case R.id.btnLocationUri:
                if(TextUtils.isEmpty(locationUri.getText().toString())){
                    locationUri.setError("Tolong masukkan alamat kota/provinsi/negara");
                    return;
                }
                Intent openLocation = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=" + locationUri.getText().toString()));
                startActivity(openLocation);
                break;

            case R.id.btnShareText:
                if(TextUtils.isEmpty(textShare.getText().toString())){
                    textShare.setError("Tolong masukkan kata/kalimat");
                    return;
                }
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plan")
                        .setChooserTitle("Share this text with : ")
                        .setText(textShare.getText().toString())
                        .startChooser();
                break;

            case R.id.btn_logout:
                Intent intent = new Intent(ImplicitIntentActivity.this, SimpleLoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }

    }

}