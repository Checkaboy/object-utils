package com.checkaboy.objectutils.util;

import javax.sql.rowset.serial.SerialClob;
import java.io.BufferedReader;
import java.io.Reader;
import java.sql.Clob;

public class ClobUtil {

    public static Clob getClob(String data) throws Exception {
        if(data == null) throw new NullPointerException();
        return new SerialClob(data.toCharArray());
    }

    public static String getText(Clob clob) throws Exception {
        StringBuilder sb = new StringBuilder();

        Reader reader = clob.getCharacterStream();
        BufferedReader br = new BufferedReader(reader);

        String line;
        while (null != (line = br.readLine())) {
            sb.append(line);
        }
        br.close();

        return sb.toString();
    }

}
