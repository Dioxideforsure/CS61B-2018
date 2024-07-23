package lab14;

import edu.princeton.cs.introcs.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.state = 0;
        this.period = period;
    }

    @Override
    public double next() {
        state++;
        return normalize(period);
    }

    private double normalize(int period) {
        return (double) (state % period - period / 2) / period * 2;
    }
}
