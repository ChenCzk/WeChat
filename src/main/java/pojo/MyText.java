package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class MyText extends base {
    @XStreamAlias("Content")
    private String Content;


    public MyText(Map<String, String> requestMap, String content) {
        super(requestMap);
        this.setMsgType("text");
        this.setContent(content);
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "MyText{" +
                "Content='" + Content + '\'' + super.toString() +
                '}';
    }


}
