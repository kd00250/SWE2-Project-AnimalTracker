package edu.westga.cs3211.animaltracker.model.client;

import edu.westga.cs3211.animaltracker.model.server.request.Request;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClose {
    private static class FakeRequest extends Request {
        private final JSONObject json;

        FakeRequest(JSONObject json) {
            this.json = json;
        }

        @Override
        public void validateRequest() {
        }

        @Override
        public JSONObject toJson() {
            return this.json;
        }
    }

    @Test
    void shouldReturnErrorResponseAfterCloseIsCalled() {
        Context context = ZMQ.context(1);
        Client client = new Client(context);

        client.close();

        JSONObject requestJson = new JSONObject();
        requestJson.put("action", "test");
        FakeRequest request = new FakeRequest(requestJson);

        JSONObject result = client.send(request);

        assertFalse(result.getBoolean("success"));
        assertTrue(result.getString("message").contains("Communication error"));
    }
}
