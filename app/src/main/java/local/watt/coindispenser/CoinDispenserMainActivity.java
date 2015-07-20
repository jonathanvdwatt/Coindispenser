package local.watt.coindispenser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.PasswordAuthentication;


public class CoinDispenserMainActivity extends Activity {

    // Login counter - three max attempts
    int counter = 3;

    // Declare constants
    //
    public final static String OUTSTANDING_AMOUNT = "local.watt.coindispenser.bill";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_dispenser);

        // Declare our view variables and assign them the views from the layout file
        //
        final RelativeLayout mainRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);
        final EditText username = (EditText) findViewById(R.id.userName);
        final EditText password = (EditText) findViewById(R.id.passWord);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);

        // Capture login details and perform very basic authentication
        //
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    passToPaymentCapture(findViewById(R.id.paymentCaptureRelativeLayout));
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong credentials!", Toast.LENGTH_LONG).show();
                    mainRelativeLayout.setVisibility(View.VISIBLE);
                    mainRelativeLayout.setBackgroundColor(Color.RED);
                    counter--;

                    if (counter == 0) {
                        loginButton.setEnabled(false);
                    }
                }
            }
        });

        // Close the Activity if the user clicks the cancel button
        //
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Redirect to the next Activity in the pipeline after successful log on
    //
    public void passToPaymentCapture(View view) {
        String bill = returnRandomBill();
        Intent intent = new Intent(this, CoinDispenserPaymentCaptureActivity.class);
        intent.putExtra(OUTSTANDING_AMOUNT, bill);
        startActivity(intent);
    }

    // Random bill generation happens here.
    //
    public String returnRandomBill() {
        CoinDispenserBillGenerationUtil generateBill = new CoinDispenserBillGenerationUtil();
        String bill = generateBill.randomNumber();
        return bill;
    }
}