package liuqhahahh.njupt.edu.cn.json_analysis;

/**
 * Created by root on 17-11-18.
 */

public class EntityStudent {
    private int id;
    private String name;
    private String sex;
    private int age;
    private int height;

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getSex(){
        return sex;
    }
    public int getAge(){
        return age;
    }
    public int getHeight(){
        return  height;
    }
    public void show(){
        System.out.print("id=" + id + ",");
        System.out.print("name=" + name+",");
        System.out.print("sex=" + sex+",");
        System.out.print("sex=" + sex+",");
        System.out.print("age=" + age+",    ");
        System.out.println("height=" + height + ",");

    }

}
