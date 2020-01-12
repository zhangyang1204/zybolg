package main.dao;

import main.utils.jdbcUtil;
import main.domain.VisitLog;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class VisitLogDao {
    JdbcTemplate template = new JdbcTemplate(main.utils.jdbcUtil.getDatasource());
    public void addVisitLog(VisitLog log){
        String sql = "insert into visit_log values(?,?,?,null)";
        template.update(sql,log.getIp(),log.getVisitTime(),log.getPageIDs());
    }
    public void updatePageIDs(VisitLog log){
//        String sql = "update visit_log set pageIDs=? where ip = ? and visitTime = ?";
//        template.update(sql,log.getPageIDs(),log.getIp(),log.getVisitTime());
        String sql = "update visit_log set pageIDs=? where  visitTime = ?";
        template.update(sql,log.getPageIDs(),log.getVisitTime());
    }
    public List<VisitLog> findAllLogs(){
        String sql = "select * from visit_log ";
        List<VisitLog> query = template.query(sql, new BeanPropertyRowMapper<VisitLog>(VisitLog.class));
        return  query;
    }
    public int findTotalCount(){
        String sql = "select count(*) from visit_log";
        Integer count = template.queryForObject(sql, Integer.class);
        return count;
    }

    public List<VisitLog> findLogByPage(int start,int count){
        String sql ="select * from visit_log limit ?,?";
        List<VisitLog> query = template.query(sql, new BeanPropertyRowMapper<VisitLog>(VisitLog.class), start, count);
        return query;
    }
    public List<String> getAllVisitedPageIDs(){
        String sql ="select pageIDs from visit_log ";
        List<String> strings = template.queryForList(sql, String.class);
        return strings;
    }
    public  void deleteLog(int id){
        String sql = "delete  from visit_log where id =?";
        template.update(sql,id);
    }

}
