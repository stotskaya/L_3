package Model;


public class GroupItem {

    private String nameGroup;

    private Integer id;

    public String getNameGroup() {
        return nameGroup;
    }
    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public String toString(){
        return nameGroup;
    }

    public void setID(Integer id){
        this.id = id;
    }

    public Integer getID(){
        return id;
    }

}
