package log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Log {
    private Log() {
    }

    public static void log(String log) {
        try {


            Date date = new Date();
//        String fileName = date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
            File file = new File("log.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fileOutputStream = new FileOutputStream(file, true)) {
                String text = ("[" + date + "]: " + log + "\n");
                fileOutputStream.write(text.getBytes(StandardCharsets.UTF_8));
                System.out.println(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
