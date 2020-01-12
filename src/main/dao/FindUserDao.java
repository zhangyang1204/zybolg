package main.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class FindUserDao {
    JdbcTemplate template = new JdbcTemplate(main.utils.jdbcUtil.getDatasource());
    public  String findUser(String username,String password){
        String sql ="select identity from host_user where username = ? and password = ?";
        String identity = null;
        try {
             identity = template.queryForObject(sql,String.class,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return identity;
    }
}
