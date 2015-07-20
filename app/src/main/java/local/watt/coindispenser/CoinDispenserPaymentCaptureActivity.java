package local.watt.coindispenser;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CoinDispenserPaymentCaptureActivity extends Activity {

    // Declare global variables
    String bill;
    String payment;
    Double capturedPaymentAsDouble;
    Double billAsDouble;

    // Declare constants
    //
    public final static String BILL = "local.watt.coindispenser.bill";
    public final static String CAPTURED_PAYMENT = "local.watt.coindispenser.payment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_dispenser_payment_capture);

        // Get the outstanding amount from the intent
        //
        Intent intent = getIntent();
        bill = intent.getStringExtra(CoinDispenserMainActivity.OUTSTANDING_AMOUNT);

        // Create the text view
        //
        final TextView pleasePayTextView = (TextView) findViewById(R.id.pleasePayTextView);
        pleasePayTextView.setText("Please pay:\n" + bill);

        // Create the buttons
        //
        Button submitButton = (Button) findViewById(R.id.submitButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);

        // EditText member variable
        final EditText capturedPayment = (EditText) findViewById(R.id.paymentCaptureED);

        // Grab the amount entered at the EditText widget and perform some checking
        //
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    payment = capturedPayment.getText().toString();
                    capturedPaymentAsDouble = Double.parseDouble(payment);
                    billAsDouble = Double.parseDouble(bill);

                    if(capturedPaymentAsDouble < billAsDouble) {
                        Toast.makeText(getApplicationContext(), "Incorrect amount!", Toast.LENGTH_LONG).show();
                        pleasePayTextView.setText("Please pay:\n" + bill);
                    }
                    else {
                        passToDenominationsCalculations(findViewById(R.id.denominationsRelativeLayout));
                    }
                }
                catch (NumberFormatException e) {
                    // payment did not contain a valid double
                }
            }
        });

        // Close the activity if the cancel button is clicked
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void passToDenominationsCalculations(View view) {
        Intent intent = new Intent(this, CoinDispenserDenominationsCalculationActivity.class);
        intent.putExtra(CAPTURED_PAYMENT, payment);
        intent.putExtra(BILL, bill);
        startActivity(intent);
    }
}