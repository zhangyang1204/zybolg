package main.service;

import main.dao.FindUserDao;

public class FindUserService {
    public String findUser(String username,String password){
        return new FindUserDao().findUser(username,password);
    }
}
