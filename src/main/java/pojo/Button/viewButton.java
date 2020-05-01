package pojo.Button;

/*
* 链接按钮
* url type name
* */
public class viewButton extends AbstractButton {
    private String url;
    private String type;

    public viewButton(String name, String url) {
        super(name);
        this.type="view";
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
