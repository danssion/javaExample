package com.danssion.java.exec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExecExample {
    public static void main(String[] args) {

        testCurl();
//        testCurl1();
    }

    public static void testCurl() {
        String[] cmds = {"curl", "-s",
//                "--form-string","text='Testing some Mailgun heeeellllllooooo!'",
                "--user", "api:14dc5c76561c54e45e6435edff5cc884-",

//        " https://api.mailgun.net/v3/mg.mytokenmsg.com/messages  -F from='no-reply<no-reply@mg.mytokenmsg.com>'  -F to=duanxiang@haodelian.com  -F to=dev@haodelian.com -F subject='Hello' -F text='Testing some Mailgun awesomeness!",
//        "-F","from='no-reply<no-reply@mg.mytokenmsg.com>'",
                "-F", "to=duanxiang@haodelian.com",
//        "-F to=dev@haodelian.com",
                "-F", "subject='Hello' ",
                "--form-string", "text='Testing some Mailgun heeeellllllooooo!'",
                "--form-string", "html='<html>Inline image here: <img src=\"cid:cartman.jpg\"></html>'",
//                " https://api.mailgun.net/v3/mg.mytokenmsg.com/messages ",
                "--form-string", "from=no-reply@mg.mytokenmsg.com",
//               "--form-string ", "to=duanxiang@haodelian.com","  --form-string to=dev@haodelian.com --form-string subject='Hello' --form-string text='Testing some Mailgun awesomeness!'",
                "https://api.mailgun.net/v3/mg.mytokenmsg.com/messages",
        };

        ProcessBuilder process = new ProcessBuilder(cmds);
        System.out.println(process.environment());

        Process p;
        try {
//      Process p = Runtime.getRuntime().exec(cmds);
            p = process.start();
//      System.out.println(process.command());

            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            System.out.println(builder.toString());

            reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            builder = new StringBuilder();
            line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            System.out.println(builder.toString());

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
    }

    public static void testCurl1() {
        String s = null;

        try {

            // run the Unix "ps -ef" command
            // using the Runtime exec method:
            //Process p = Runtime.getRuntime().exec("ps -ef");
            Process p = Runtime.getRuntime().exec("curl -s --user api:14dc5c76561c54e45e6435edff5cc884" +
                    " https://api.mailgun.net/v3/mg.mytokenmsg.com/messages  " +
                    "--form-string from=no-reply@mg.mytokenmsg.com  --form-string to=duanxiang@haodelian.com  --form-string to=dev@haodelian.com --form-string subject=Hello " +
                    "--form-string html='<html>Inline image here: <img src=\"cid:cartman.jpg\"></html>'" +
                    "--form-string text='Testing some Mailgun awesomeness!'"
            );

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }

            System.exit(0);
        } catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
    }

}
