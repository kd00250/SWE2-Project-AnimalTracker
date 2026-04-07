package edu.westga.cs3211.animaltracker.model.server.request.data;

import edu.westga.cs3211.animaltracker.model.Project;
import edu.westga.cs3211.animaltracker.model.server.request.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetProjectResponse extends Response {
    private JSONObject response;

    public GetProjectResponse(JSONObject response) {
        this.response = response;
    }

    public JSONObject getResponse() {
        return this.response;
    }

}
