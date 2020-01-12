package main.domain;

public class PageVisitedTimes {
    //  封装记录每个页面被访问次数表中·的数据
    private int id;
    private String name;
    private int beVisitedTimes;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PageVisitedTimes{" +
                "id=" + id +
                ", pageName='" + name + '\'' +
                ", times=" + beVisitedTimes +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeVisitedTimes() {
        return beVisitedTimes;
    }

    public void setBeVisitedTimes(int beVisitedTimes) {
        this.beVisitedTimes = beVisitedTimes;
    }


}

