package lab14;

import edu.princeton.cs.introcs.StdAudio;
import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int frequency;
    private int state;

    public SawToothGenerator(int frequency) {
        this.state = -1;
        this.frequency = frequency;
    }

    @Override
    public double next() {
        state++;
        double period = (double) StdAudio.SAMPLE_RATE / frequency;
        return normalize(period);
    }

    private double normalize(double period) {
        return (state % period - period / 2) / period * 2;
    }
}
