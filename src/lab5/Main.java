package lab5;

import lab5.utility.CollectionManager;
import lab5.utility.ProgramProcess;
import lab5.utility.ElementCreation;
import lab5.utility.FileManager;


public class Main {

    public static void main(String[] args) {
        final String env = "FILE";
        ElementCreation elementCreation = new ElementCreation();
        FileManager fileManager = new FileManager(env);
        CollectionManager collectionManager = new CollectionManager(elementCreation, fileManager);
        ProgramProcess programProcess = new ProgramProcess(collectionManager, elementCreation);
        collectionManager.loadCollection();
        programProcess.process();
    }
}
