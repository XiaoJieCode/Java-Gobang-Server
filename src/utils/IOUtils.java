package utils;

import java.io.IOException;
import java.io.InputStream;

public class IOUtils {

    private IOUtils() {
    }

    public static synchronized String readAll(InputStream inputStream) throws IOException {

        String s = null;

        while (s==null||s.equals("")) {
            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes, 0, available);
            s = new String(bytes);
        }
        return s;
    }
}
