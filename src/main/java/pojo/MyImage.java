package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class MyImage extends base{
    @XStreamAlias("Image")
    private Image image ;

    public MyImage(Map<String,String> map, Image image){
        super(map);
        this.setMsgType("image");
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
