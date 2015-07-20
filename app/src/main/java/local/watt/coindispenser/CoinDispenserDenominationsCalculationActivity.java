package local.watt.coindispenser;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CoinDispenserDenominationsCalculationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_dispenser_denominations_calculation);

        // Get the bill and the amount paid from the intent
        //
        Intent intent = getIntent();
        final String payment = intent.getStringExtra(CoinDispenserPaymentCaptureActivity.CAPTURED_PAYMENT);
        final String bill = intent.getStringExtra(CoinDispenserPaymentCaptureActivity.BILL);

        // Create the buttons
        //
        Button closeButton = (Button) findViewById(R.id.closeButton);

        // Create the TextView
        //
        final TextView denominationsTextView = (TextView) findViewById(R.id.denominationsTextView);

        // Calculate the change and break it down into its denominations
        //
        Double paymentAsDouble = Double.valueOf(payment);
        Double billAsDouble = Double.valueOf(bill);
        Double changeAsDouble = (double) Math.round(paymentAsDouble - billAsDouble);
        String change = changeAsDouble.toString();
        denominationsTextView.setTextSize(16);
        denominationsTextView.setText("\nYour change is:\n R" + change + "\n\nThe denominations of R" + change + " are:\n\n" + processPayment(changeAsDouble));

        // Close the activity if the cancel button is clicked
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }

    // Denominations break down happens here
    //
    public String processPayment(double amountPaid) {
        double payment = amountPaid;
        double[] denominations = {200.00, 100.00, 50.00, 20.00, 10.00, 5.00, 2.00, 1.00, 0.50, 0.20, 0.10, 0.05 };

        CoinDispenserDenominationUtil denominator = new CoinDispenserDenominationUtil();
        int[] count = denominator.Breakdown(denominations, payment);
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder returnMessage = null;

        for(int i=0; i<denominations.length; i++) {
            if(count[i]>0) {
                returnMessage = stringBuilder.append(count[i] + " x R" + denominations[i] + "\n");
            }
        }
        return returnMessage.toString();
    }
}
