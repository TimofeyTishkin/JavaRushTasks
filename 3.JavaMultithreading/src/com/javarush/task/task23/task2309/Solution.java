package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.List;

/* 
Анонимность иногда так приятна!
*/
public class Solution {
    public List<User> getUsers(){
        return new AbstractDbSelectExecutor<User>(){
            @Override
            public String getQuery() {
                return User.query;
            }

            @Override
            public List<User> execute() {
                if(getQuery().equals("SELECT * FROM USER")) {
                    return super.execute();
                }
                return null;
            }
        }.execute();
    }
    public List<Location> getLocations(){
        return new AbstractDbSelectExecutor<Location>(){
            @Override
            public String getQuery() {
                return Location.query;
            }

            @Override
            public List<Location> execute() {
                if(getQuery().equals("SELECT * FROM LOCATION")) {
                    return super.execute();
                }
                return null;
            }
        }.execute();
    }
    public List<Subject> getSubjects(){
        return new AbstractDbSelectExecutor<Subject>(){
            @Override
            public String getQuery() {
                return Subject.query;
            }

            @Override
            public List<Subject> execute() {
                if(getQuery().equals("SELECT * FROM SUBJECT")) {
                    return super.execute();
                }
                return null;
            }
        }.execute();
    }
    public List<Subscription> getSubscriptions(){
        return new AbstractDbSelectExecutor<Subscription>(){
            @Override
            public String getQuery() {
                return Subscription.query;
            }

            @Override
            public List<Subscription> execute() {
                if(getQuery().equals("SELECT * FROM SUBSCRIPTION")) {
                    return super.execute();
                }
                return null;
            }
        }.execute();
    }
    public List<Server> getServers(){
        return new AbstractDbSelectExecutor<Server>(){
            @Override
            public String getQuery() {
                return Server.query;
            }

            @Override
            public List<Server> execute() {
                if(getQuery().equals("SELECT * FROM SERVER")) {
                    return super.execute();
                }
                return null;
            }
        }.execute();
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }
}
