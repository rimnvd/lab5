package lab5;


import lab5.utility.CollectionCreation;
import lab5.utility.ProgramProcess;

public class Main {

    public static void main(String[] args) {
        CollectionCreation collectionCreation = new CollectionCreation();
        collectionCreation.creation();
        ProgramProcess programProcess = new ProgramProcess();
        programProcess.process(CollectionCreation.vector);
    }
}
