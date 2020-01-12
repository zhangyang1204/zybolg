package main.service;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import main.dao.P_V_T_Dao;
import main.dao.VisitLogDao;
import main.domain.PageVisitedTimes;
import main.domain.VisitLog;

import java.util.ArrayList;
import java.util.List;

public class PageBeVisitedTimesService {
    public void addOneByLog(String pageIDs){
        //        更新页面被访问次数表
        String[] pageIDs_array = pageIDs.split(" ");
        P_V_T_Dao dao = new P_V_T_Dao();
        for (String s:pageIDs_array){
            int id=0;
            try {
                 id = Integer.parseInt(s);
                dao.addOneVisitTimeById(id);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized void updatePageBeVisitedTimes(){
//        更新页面被访问次数表中的所有数据
        VisitLogService service = new VisitLogService();
        P_V_T_Dao pVTDao = new P_V_T_Dao();
        int pageCount = pVTDao.getPageCount();
//        通过网站访问日志表计算每个网页被访问的次数
        ArrayList<Integer> pagesBeVisitedTimes = service.getPagesBeVisitedTimes();
        for (int i=0;i<pageCount;i++){
            pVTDao.setTimesById(i,pagesBeVisitedTimes.get(i));
        }
    }
    public List<PageVisitedTimes> findAll(){
        return new P_V_T_Dao().getAll();
    }
}
