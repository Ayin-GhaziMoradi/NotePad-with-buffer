import java.io.*;
import java.util.ArrayList;

public class Main {
    static int ctr = 0;
    static ArrayList<Boolean> exists = new ArrayList<>();

    /**
     * adds a note to the directory
     * @param note note to be added
     * @throws IOException file io error handling
     */
    public static void addNote(String note){
        try (FileWriter out= new FileWriter("./files/note" + (ctr++) + ".txt");){
            BufferedWriter buffer = new BufferedWriter(out);
            buffer.write(note);
            buffer.close();
            exists.add(true);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads a note with give index
     * @param index index of the file
     * @throws IOException file io error handling
     */
    public static void readNote(int index){
        if(exists.get(index)) {
            //FileReader fr=new FileReader("D:\\testout.txt");
            try (FileReader in = new FileReader("./files/note" + (index) + ".txt");) {
                BufferedReader br=new BufferedReader(in);
                int i = 0;
                while ((i = br.read()) != -1) {
                    System.out.print((char) i);
                }
                System.out.println();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deletes a note with given index
     * @param index index of the file to delete
     */
    public static void deleteNote(int index){
        File file = new File("./files/note" + (index) + ".txt");
        if(file.delete()) {
            System.out.println("deleted successfuly");
            exists.set(index , false);
        }else
            System.out.println("could not delete");
    }

    /**
     * shows every note's first 10 chars
     * @throws IOException file io error handling
     */
    public static void showNotes(){
        for (int i = 0 ; i<ctr ; i++){
            if(exists.get(i)) {
                //FileReader in = new FileReader("./files/note" + (index) + ".txt");
                try (FileReader in = new FileReader("./files/note" + (i) + ".txt");) {
                    System.out.print("" + (i) + " : ");
                    BufferedReader br=new BufferedReader(in);
                    int j = 0;
                    int index = 0;
                    while ((j = br.read()) != -1 && index < 10) {
                        System.out.print((char) j);
                        index++;
                    }
                    System.out.print(" ...");
                }catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        addNote("this is my first note to add . i hope it works !");
        addNote("let's add the second note so we can test it");
        addNote("3rd note to be added");

        showNotes();

        readNote(2);

        deleteNote(2);

        showNotes();
    }
}
