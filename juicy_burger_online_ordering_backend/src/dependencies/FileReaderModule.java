package dependencies;

import dagger.Module;
import dagger.Provides;
import exceptions.OrderException;

import javax.inject.Singleton;
import java.io.FileReader;

@Module
public class FileReaderModule {

    /**
     * Dagger module to provide a FileReader object
     * @return FileReader the file reader to be returned, null if the file cannot be found
     */
    @Singleton
    @Provides
    public FileReader fileReaderProvider() {
        try {
            return new FileReader("juicy_burger_online_ordering_backend/src/resources/menuItems.json");
        } catch (Exception e) {
            throw new OrderException("Unable to create FileReader", e);
        }
    }
}
