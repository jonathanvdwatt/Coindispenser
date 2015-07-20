package local.watt.coindispenser;

import java.util.Random;

/**
 * Created by f4720431 on 2015/07/16.
 */
public class CoinDispenserBillGenerationUtil {
    public String randomNumber() {
        double minX = 50.0f;
        double maxX = 200.0f;

        Random randomNo = new Random();
        double roundOff = (double) (Math.round(randomNo.nextDouble() * (maxX - minX) + minX));

        String returnVal = String.valueOf(roundOff + 0.95);
        return returnVal;
    }
}
