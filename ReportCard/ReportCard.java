package com.example.android.reportcard;

/**
 * Created by Vedant Singh on 01-02-2017.
 */

public class ReportCard {

    private char Section;
    private String Name;
    private int Os;
    private int Csa;
    private int Adbms;
    private int Web;
    private int Sum;
    public ReportCard(char section,String name,int Marks1,int Marks2,
                      int Marks3,int Marks4){
        Name= name;
        Section=section;
        Os=Marks1;
        Csa=Marks2;
        Adbms=Marks3;
        Web=Marks4;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name){
        Name=name;
    }

    public char getSection(){
        return Section;
    }

    public void setSection(char section){
        Section=section;
    }

    public int getOs(){
        return Os;
    }

    public void setOs(int marks){
        Os=marks;
    }

    public int getCsa(){
        return Csa;
    }

    public void setCsa(int marks){
        Csa=marks;
    }

    public int getAdbms(){
        return Adbms;
    }

    public void setAdbms(int marks){
        ADdbms=marks;
    }

    public int getWeb(){
        return Web;
    }

    public void setWeb(int marks){
        Web=marks;
    }

    public int getSum(){
        Sum = Os + Csa + Adbms + Web;
        return Sum;
    }

    public double getpercentage(){
        return Sum/4;
    }

    @Override
    public String toString() {
        return "Result { " +
                "OS = " + OS_Marks +
                "\nCSA = " + CSA_Marks +
                "\nADBMS = " + ADBMS_marks +
                "\nDS = " + DS_marks +
                "\nSUM = " + Sum +
                " }";
    }
}
