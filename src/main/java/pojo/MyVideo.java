package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class MyVideo extends base{
    @XStreamAlias("Video")
    private Video video;

    public MyVideo(Map<String, String> requestMap,Video video) {
        super(requestMap);
        this.setMsgType("video");
        this.video=video;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
