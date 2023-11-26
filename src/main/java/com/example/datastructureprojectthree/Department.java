package com.example.datastructureprojectthree;

import java.nio.file.Path;

public class Department implements Comparable<Department>  {
    private String depName;
    private String depFilePath;
    private Hash_Quadratic<Student> dep;




    public Department(String depName  , String depFilePath ) {
        super();
        this.depName = depName;
        this.depFilePath = depFilePath;
        this.dep = new Hash_Quadratic<>();
    }
    


    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getDepFilePath() {
        return depFilePath;
    }

    public void setDepFilePath(String depFilePath) {
        this.depFilePath = depFilePath;
    }

    public Hash_Quadratic<Student> getDep() {
        return dep;
    }

    public void setDep(Hash_Quadratic<Student> dep) {
        this.dep = dep;
    }

    @Override
    public int compareTo(Department o) {

        return this.depName.compareTo(o.depName);
    }

    @Override
    public String toString() {
        return "" + depName + "/" + depFilePath+ "\n";
    }




}
