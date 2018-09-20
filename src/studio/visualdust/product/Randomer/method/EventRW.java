package studio.visualdust.product.Randomer.method;

import studio.visualdust.product.Randomer.resource.Resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventRW {
    public static File logoutfile = new File(LocalDate.now().toString() + "_" + Resource.softName + ".md");
    public static OutputStream logstream;

    public static void Write(Exception e) {
        FileWriter.WriteLineUTF8(logoutfile, "> " + LocalDateTime.now().toString() + " Exception : " + e.toString(), true);
        System.out.println(e.toString());
        e.printStackTrace();
    }

    public static void Write(String event) {
        FileWriter.WriteLineUTF8(logoutfile, LocalDateTime.now().toString() + " Event : " + event, true);
        System.out.println(event);
    }

    public static void WriteStrOnly(String string) {
        FileWriter.WriteLineUTF8(logoutfile, string, true);
        System.out.println(string);
    }
}