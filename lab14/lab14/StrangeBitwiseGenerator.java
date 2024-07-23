package lab14;

import edu.princeton.cs.introcs.StdAudio;
import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int frequency;
    private int state;

    public StrangeBitwiseGenerator(int frequency) {
        this.state = -1;
        this.frequency = frequency;
    }

    @Override
    public double next() {
        state++;
        int period = StdAudio.SAMPLE_RATE / frequency;
        int weirdState = state & (state >> 7) % period;
        return normalize(weirdState, period);
    }

    private double normalize(int weirdState, double period) {
        return (weirdState % period - period / 2) / period * 2;
    }
}
