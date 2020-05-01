package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;

@XStreamAlias("xml")
public class MyMusic extends base{
    @XStreamAlias("Music")
    private Music music;

    public MyMusic(Map<String, String> requestMap) {
        super(requestMap);
    }
}
