package dependencies;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Module
public class FileReaderModule {

    @Singleton
    @Provides
    public FileReader fileReaderProvider() throws FileNotFoundException {
        return new FileReader("resources/menuItems.json");
    }
}
