package edu.westga.cs3211.animaltracker.model.server.request.request;

import edu.westga.cs3211.animaltracker.model.server.request.Request;

import java.util.Random;

public class FakeRequest extends Request {
    public FakeRequest(Random random) {
        super(random);
    }
    @Override
    public void validateRequest() {
        return;
    }
}
