package lab14;

import edu.princeton.cs.introcs.StdAudio;
import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        this.state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state++;
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState, period);
    }

    private double normalize(int weirdState, int period) {
        return (double) (weirdState % period - period / 2) / period * 2;
    }
}
