package pojo.Button;


/*
*  点击菜单(三个属性 key,type,name)
* */
public class clickButton extends AbstractButton {
    private String key;
    private String type;
    public clickButton(String name, String key) {
        super(name);
        this.type= "click";
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
