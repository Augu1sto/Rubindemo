package com.rubin.demo.Utils;

import java.io.*;

public class ImageRec {
    public static String img2txt(String img_url) throws IOException {
        String exe = "python";
        String command = "D:\\COLLEGE4-1\\小学期\\实训\\Rubindemo\\src\\main\\java\\com\\rubin\\demo\\Utils\\imgrec.py";
        String[] cmdArr = new String[] {exe, command, img_url};
        Process process = Runtime.getRuntime().exec(cmdArr);
        InputStream is = process.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is,"gb2312"));
        String str = br.readLine();
        return str;
    }
}
