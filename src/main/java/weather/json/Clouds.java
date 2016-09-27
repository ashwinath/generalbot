package weather.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {
    private long all;

    public long getAll() {
        return all;
    }

    public void setAll(long all) {
        this.all = all;
    }
}
