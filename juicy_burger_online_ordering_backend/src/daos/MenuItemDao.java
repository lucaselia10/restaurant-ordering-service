package daos;

import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import java.io.FileReader;

public class MenuItemDao {
    private JSONParser jsonParser;
    private FileReader fileReader;

    @Inject
    public MenuItemDao(JSONParser jsonParser, FileReader fileReader) {
        this.jsonParser = jsonParser;
        this.fileReader = fileReader;
    }
}
