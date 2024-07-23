package lab14;

import edu.princeton.cs.introcs.StdAudio;
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int frequency;
    private int state;
    private double acceleration;

    public AcceleratingSawToothGenerator(int frequency, double acceleration) {
        this.frequency = frequency;
        this.acceleration = acceleration;
        state = 0;
    }

    @Override
    public double next() {
        state++;
        double period = (double) StdAudio.SAMPLE_RATE / frequency;
        if (state % period == 0) {
            period = period * acceleration;
        }
        return normalize(period);
    }


    private double normalize(double period) {
        return (state % period  - period / 2) / period * 2;
    }
}
