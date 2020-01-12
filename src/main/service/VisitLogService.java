package main.service;

import main.dao.P_V_T_Dao;
import main.dao.VisitLogDao;
import main.domain.PageBean;
import main.domain.VisitLog;

import java.util.ArrayList;
import java.util.List;

public class VisitLogService {
    public void addVisitLog(VisitLog log){

//        更新网站访问记录表
        new VisitLogDao().addVisitLog(log);
    }
    public void updateVisitLog(VisitLog log){

        new VisitLogDao().updatePageIDs(log);

    }

    public List<VisitLog> findAllLogs(){
        return new VisitLogDao().findAllLogs();
    }

    public PageBean<VisitLog> findLogBypage(String _currentPage){
        int rows = 10;
        VisitLogDao visitLogDao = new VisitLogDao();
        int totalCount = visitLogDao.findTotalCount();
        int currentPage= Integer.parseInt(_currentPage);
        int totalPage = totalCount%rows==0 ? totalCount/rows : totalCount/rows+1;
        int _start = rows*(currentPage-1);
        int _count =(totalCount-rows*(currentPage-1))<rows?totalCount-rows*(currentPage-1):rows;
        List<VisitLog> list = visitLogDao.findLogByPage(_start,_count);
//        封装数据
        PageBean<VisitLog> visitLogPageBean = new PageBean<>();
        visitLogPageBean.setCurrentPage(currentPage);
        visitLogPageBean.setList(list);
        visitLogPageBean.setRows(rows);
        visitLogPageBean.setTotalCount(totalCount);
        visitLogPageBean.setTotalPage(totalPage);
        return visitLogPageBean;
    }
    public void deleteLog(String _id){
        int id = Integer.parseInt(_id);
        new VisitLogDao().deleteLog(id);
    }
    public void delSelectLog(String[] ids ){
        VisitLogDao visitLogDao = new VisitLogDao();
        for (String s:ids){
            int i = Integer.parseInt(s);
            visitLogDao.deleteLog(i);
        }
    }
    public ArrayList<Integer> getPagesBeVisitedTimes(){
        ArrayList<Integer> arrayList = new ArrayList<>();
//        初始化 arraylist
        for (int i=0;i<48;i++){
            arrayList.add(0);
        }
        List<String> allVisitedPageIDs = new VisitLogDao().getAllVisitedPageIDs();
        for (String s:allVisitedPageIDs){
//            System.out.println(s);
            String[] pids = s.split(" ");
            for (String pid:pids){
//                System.out.println(pid);
                if(pid!=""){
                    int i = Integer.parseInt(pid);
                    arrayList.set(i,arrayList.get(i)+1);
                }
            }
        }
        return arrayList;
    }
}
