package dependencies;

import dagger.Module;
import dagger.Provides;
import org.json.simple.parser.JSONParser;

import javax.inject.Singleton;

@Module
public class JSONParserModule {

    @Singleton
    @Provides
    public JSONParser JSONParseProvider() {
        return new JSONParser();
    }
}
