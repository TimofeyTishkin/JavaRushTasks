package com.javarush.task.task18.task1812;

/* 
Расширяем AmigoOutputStream
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuestionFileOutputStream implements AmigoOutputStream {
    private AmigoOutputStream myAnswer;
    public QuestionFileOutputStream(AmigoOutputStream myAnswer){
        this.myAnswer = myAnswer;
    }

    @Override
    public void flush() throws IOException {
        myAnswer.flush();
    }

    @Override
    public void write(int b) throws IOException {
        myAnswer.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        myAnswer.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        myAnswer.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer = reader.readLine();
        if(answer.equals("Д")){
            myAnswer.close();
        }
    }
}

