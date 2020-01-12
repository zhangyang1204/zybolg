package main.dao;

import main.domain.PageVisitedTimes;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class P_V_T_Dao {
    JdbcTemplate template = new JdbcTemplate(main.utils.jdbcUtil.getDatasource());
    public synchronized void addOneVisitTimeById(int id){
//        将指定id的页面访问次数加一
        int count=0;
        String sql1="select beVisitedTimes from page_visited_times where id =?";
        Integer lastCount = template.queryForObject(sql1, Integer.class, id);
        count =lastCount++;
        String sql2="update page_visited_times set beVisitedTimes =? where id =?  ";
        template.update(sql2,count);
    }
    public int getPageCount(){
        String sql="select count(*) from page_visited_times";
        return template.queryForObject(sql,Integer.class);
    }
    public void setTimesById(int id,int times){
        String sql ="update page_visited_times set beVisitedTimes =? where id=?";
        template.update(sql,times,id);
    }
    public List<PageVisitedTimes> getAll(){
        String sql="select * from page_visited_times";
        return template.query(sql, new BeanPropertyRowMapper<PageVisitedTimes>(PageVisitedTimes.class));
    }
}
