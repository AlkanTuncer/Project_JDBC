package utilities;

public enum RDMS {

    // Relational Database Management Systems

    mysql_sakila(1,"A"),
    mysql_tempdb(2,"B"),
    sqlite(3,"C");

    private int i;
    private String str;

    RDMS(int i, String str) {
        this.i = i;
        this.str = str;
    }

    public int getI() {
        return i;
    }
    public String getStr() {
        return str;
    }

}
