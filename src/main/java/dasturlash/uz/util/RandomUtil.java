package dasturlash.uz.util;

import java.util.Random;

public class RandomUtil {
    private static Random rand = new Random();

    public static int fiveDigit() {
        return 10000 + rand.nextInt(90000);
    }
}
