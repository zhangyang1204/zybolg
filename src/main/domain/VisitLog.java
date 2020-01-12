package main.domain;

public class VisitLog {
//    封装用户访问信息
    private String ip;
    private String visitTime;
    private String pageIDs;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "VisitLog{" +
                "ip='" + ip + '\'' +
                ", visitTime='" + visitTime + '\'' +
                ", pageIDs='" + pageIDs + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String getPageIDs() {
        return pageIDs;
    }

    public void setPageIDs(String pageIDs) {
        this.pageIDs = pageIDs;
    }
}
