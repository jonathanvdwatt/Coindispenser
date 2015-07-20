package local.watt.coindispenser;

/**
 * Created by f4720431 on 2015/07/16.
 */
public class CoinDispenserDenominationUtil {
    public int[] Breakdown(double[] denominations, double amountPaid) {
        int count[] = new int[denominations.length];

        for(int i=0; i<denominations.length; i++) {
            while(amountPaid >= denominations[i]) {
                count[i]++;
                amountPaid -= denominations[i];
            }
        }
        return count;
    }
}