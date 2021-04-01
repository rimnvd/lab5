package lab5;

import lab5.utility.*;


public class Main {

    public static void main(String[] args) {
        final String env = "FILE";
        ElementManager elementManager = new ElementManager();
        FileManager fileManager = new FileManager(env);
        CollectionManager collectionManager = new CollectionManager(elementManager, fileManager);
        Console console = new Console(collectionManager, elementManager);
        collectionManager.loadCollection();
        console.process();
    }
}
