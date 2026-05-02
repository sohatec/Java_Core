import java.io.*;
import java.util.List;

public class DataFile {

    public DataFile() {
    }

    public void saveDataDeveloperTXT(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("developer.txt"))){
            for(Developer x : Developer.getListOfDeveloper()){
                bw.write(x.toString());
            }
            System.out.println("Save Data Developer .txt Successfully !!!");
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN DEVELOPER.TXT !!!");
        }
    }

    public void headerDeveloperTable(){
        System.out.printf("\n|%-10s|%-30s|%-7s|%-15s|%-25s|%-12s|\n", "ID", "Name", "Year", "Language", "Salary", "Number Obj");
    }

    public void readDataDeveloperTXT(){
        try(BufferedReader br = new BufferedReader(new FileReader("developer.txt"))){
            String line;
            while((line = br.readLine()) != null){
                if (!line.trim().isEmpty()) {
                    System.out.println(line);
                }
            }
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN DEVELOPER.TXT !!!");
        }
    }

    public void saveDataProjectTXT(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("project.txt"))){
            for(Project x : Project.getListOfProject()){
                bw.write(x.toString());
            }
            System.out.println("Save Data Project .txt Successfully !!!");
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN PROJECT.TXT !!!");
        }
    }

    public void headerProjectTable(){
        System.out.printf("\n|%-10s|%-30s|%-10s|%-15s|%-10s|\n", "PRO ID", "Name", "Duration", "Start Time", "DEV Id");
    }

    public void readDataProjectTXT(){
        try(BufferedReader br = new BufferedReader(new FileReader("project.txt"))){
            String line;
            while((line = br.readLine()) != null){
                if (!line.trim().isEmpty()) {
                    System.out.println(line);
                }
            }
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN PROJECT.TXT !!!");
        }
    }

    public void saveDataDeveloperBIN(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("developer.bin"))){
            oos.writeObject(Developer.getListOfDeveloper());
            System.out.println("Save Data Developer .bin Successfully !!!");
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN DEVELOPER.BIN !!!");
        }
    }

    public List<Developer> readDataDeveloperBIN(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("developer.bin"))){
            return (List<Developer>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("THERE'S NO DATA IN DEVELOPER.BIN !!!");
            return null;
        }
    }

    public void saveDataProjectBIN(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("project.bin"))){
            oos.writeObject(Project.getListOfProject());
            System.out.println("Save Data Project .bin Successfully !!!");
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN PROJECT.BIN !!!");
        }
    }

    public List<Project> readDataProjectBIN(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("project.bin"))){
            return (List<Project>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("THERE'S NO DATA IN PROJECT.BIN !!!");
            return null;
        }
    }

    public void saveDataUserBIN(User user){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.bin"))){
            oos.writeObject(user);
            System.out.println("Save Data User .bin Successfully !!!");
        }catch(IOException e){
            System.out.println("THERE'S NO DATA IN USER.BIN !!!");
        }

    }

    public User readDataUserBIN(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.bin"))){
            return (User) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            System.out.println("THERE'S NO DATA IN USER.BIN !!!");
            return null;
        }
    }
}