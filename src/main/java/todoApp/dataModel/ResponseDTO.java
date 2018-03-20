package todoApp.dataModel;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    private boolean response;

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }
}