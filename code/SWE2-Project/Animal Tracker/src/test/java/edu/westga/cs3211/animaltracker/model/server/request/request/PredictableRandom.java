package edu.westga.cs3211.animaltracker.model.server.request.request;

import java.util.Random;

public class PredictableRandom extends Random {
    public static long longValue = 806L;
    @Override
    public long nextLong() {
        return PredictableRandom.longValue;
    }
}
