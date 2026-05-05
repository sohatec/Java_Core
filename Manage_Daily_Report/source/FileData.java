package com.sohatec;
import java.io.*;

public class FileData {

    public FileData() {
    }

    public void saveDataManagerBIN(Manager manager){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("manager.bin"))){
            oos.writeObject(manager);
            System.out.println("=== Save Data Manager Successfully ===");
        }catch(IOException e){
        }
    }

    public Manager readDataManagerBIN(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("manager.bin"))){
            return (Manager) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            return null;
        }
    }
}
