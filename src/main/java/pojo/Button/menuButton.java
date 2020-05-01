package pojo.Button;

import java.util.ArrayList;
import java.util.List;

/*
* 菜单按钮
* */
public class menuButton  extends AbstractButton {

     private List<AbstractButton> sub_button = new ArrayList<>();


    public menuButton(String name, List<AbstractButton> sub_button) {
        super(name);
        this.sub_button = sub_button;
    }

    public List<AbstractButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<AbstractButton> sub_button) {
        this.sub_button = sub_button;
    }
}
