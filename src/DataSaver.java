import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList <String>recs = new ArrayList<>();
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.csv");
        String firstName = "";
        String lastName = "";
        String id = "";
        String email = "";
        String birthYear = "";

        do{
            firstName =SafeInput.getNonZeroLenString(in,"First name");
            lastName = SafeInput.getNonZeroLenString(in,"Last name");
            id = SafeInput.getRegExString(in,"ID number", "^\\d{6}$" );
            email = SafeInput.getNonZeroLenString(in,"Email");
            birthYear = SafeInput.getRegExString(in,"Birth year", "^\\d{4}$");

            String record = firstName + ", " + lastName + ", " + id + ", " + email + ", " + birthYear;

            recs.add(record);



        try{
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }
            writer.close();
            System.out.println("Data file written!");



        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        }while(SafeInput.getYNConfirm(in,"Do you want to continue?"));
    }

}
