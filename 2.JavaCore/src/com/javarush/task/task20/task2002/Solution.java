package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (!users.isEmpty()) {
                printWriter.println(users.size());
                for (User a : users) {
                    printWriter.println(a.getFirstName() == null ? "null" : a.getFirstName());
                    printWriter.println(a.getLastName() == null ? "null" : a.getLastName());
                    printWriter.println(a.getBirthDate() == null ? "null" : new SimpleDateFormat(
                            "dd MM yyyy HH mm ss S", Locale.ENGLISH).format(a.getBirthDate()));
                    printWriter.println(a.isMale());
                    printWriter.println(a.getCountry() == null ? "null" : a.getCountry());
                }
            } else printWriter.println(-1);
            printWriter.flush();
            //implement this method - реализуйте этот метод
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream));
            while (bfr.ready()) {
                int x = Integer.parseInt(bfr.readLine());
                if (x > 0) {
                    User us;
                    for (int i = 0; i < x; i++) {
                        us = new User();
                        String s;
                        us.setFirstName(bfr.readLine());
                        us.setLastName(bfr.readLine());
                        us.setBirthDate(new SimpleDateFormat(
                                "dd MM yyyy HH mm ss S", Locale.ENGLISH)
                                .parse(bfr.readLine()));
                        us.setMale(Boolean.parseBoolean(bfr.readLine()));
                        s = bfr.readLine();
                        us.setCountry(s.equals("null") ? null : (User.Country.valueOf(s)));
                        this.users.add(us);
                    }
                }
            }
            //implement this method - реализуйте этот метод
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
