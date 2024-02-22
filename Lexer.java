import java.io.IOException;
import java.io.File;

public class Lexer {
    public static void main(String[] args) {
        try{
            String folderName = new File(".").getCanonicalPath() + "\\" + args[0]; // get folder ('/' is used for file location in MAC)
            File testFolder = new File(folderName);                               // file object that represents folder
            File[] fileList = testFolder.listFiles();                             // creates array of files that are inside the folder
            if (fileList == null){
                folderName = new File(".").getCanonicalPath() + "/" + args[0]; // get folder('\' is used for file location in WINDOWS)
                testFolder = new File(folderName);                               // file object that represents folder
                fileList = testFolder.listFiles();                             // creates array of files that are inside the folder
            }


            if (fileList != null) {
                for (File file : fileList) {
                  if (file.isFile()) {
                    String fileName = file.getAbsolutePath();
                    if(file.getName().equals(".DS_Store")){
                        continue;
                    }
                    System.out.println("\nInput: " + file.getName());
                    System.out.println("Output: ");
                    FindToken tokenFinder = new FindToken();
                    tokenFinder.processFile(fileName);

                   }
                }
            } else {
                System.out.println("No files found in the specified folder.");
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}