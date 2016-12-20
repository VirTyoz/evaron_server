package client;
import java.io.*;
import java.net.*;
/**
 * Created by nyanta on 15.04.16.
 */
public class client {

    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to Client side");

        Socket fromserver = null;

        System.out.println("Connecting to... 127.0.0.1:8888");

        fromserver = new Socket("127.0.0.1", 8888);
        BufferedReader in = new
                BufferedReader(new
                InputStreamReader(fromserver.getInputStream()));
        PrintWriter out = new
                PrintWriter(fromserver.getOutputStream(), true);
        BufferedReader inu = new
                BufferedReader(new InputStreamReader(System.in));

        String fuser, fserver;
        for (; ; ){
            fuser = inu.readLine();
            byte[] test = fuser.getBytes();
            System.out.println("test1");
            out.println(test);

    }
        //out.close();
        //in.close();
        //inu.close();
        //fromserver.close();
    }
}
