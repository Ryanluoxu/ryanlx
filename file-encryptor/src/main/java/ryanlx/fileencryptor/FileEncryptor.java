package ryanlx.fileencryptor;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileEncryptor {
    private static boolean printLog = false;

    public static void main(String[] args) throws Exception {
        /**
         * java -jar file-encryptor.jar password file action
         */

        // get parameters from args
        if (args.length != 3) {
            return;
        }
        String password = args[0];
        String filePath = args[1];
        String action = args[2]; // en->encrypt, de->decrypt

        // get rawText
        String rawText = readFile(filePath);

        if (printLog) {
            System.out.println("password: " + password);
            System.out.println("filePath: " + filePath);
            System.out.println("action: " + action);
            System.out.println("rawText: " + rawText);
        }

        // encrypt / decrypt -> delete current file -> save new file
        String outputText;
        if (action.equals("en")) {
            outputText = AesEncryptor.encrypt(rawText, password);
        } else {
            outputText = AesEncryptor.decrypt(rawText, password);
        }

        // delete file
        deleteFile(filePath);

        // save new file
        exportFile(outputText, filePath);
    }

    private static void deleteFile(String filePath) throws Exception {
        File file = new File(filePath);
        file.delete();
    }

    private static void exportFile(String text, String filePath) throws IOException {
        FileUtils.writeStringToFile(new File(filePath), text, StandardCharsets.UTF_8);
        if (printLog) {
            System.out.println("fileName: " + filePath);
        }
    }

    private static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
