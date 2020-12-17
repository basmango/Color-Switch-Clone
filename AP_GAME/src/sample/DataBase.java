package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements Serializable {
    private static final long serialVersionUID=72L;
    private static DataBase d = null;

    private ArrayList<DataTable> databaseFiles;
    public static DataBase getInstance(){
        if(d == null){
            d = new DataBase();
        }
        return d;
    }
    private DataBase() {
        databaseFiles = new ArrayList<DataTable>();
    }

    public void addData(DataTable d) {
        databaseFiles.add(d);
    }

    public void removeData(DataTable d) {
        if (databaseFiles.contains(d))
            databaseFiles.remove(d);
    }
    public void removeLast(){
        databaseFiles.remove(0);
    }
    public ArrayList<DataTable> getDatabaseFiles() {
        return databaseFiles;
    }
}
