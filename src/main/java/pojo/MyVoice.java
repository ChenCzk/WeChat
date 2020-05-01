package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Map;
@XStreamAlias("xml")
public class MyVoice extends base{
    @XStreamAlias("Voice")
    private Voice voice;
    public MyVoice(Map<String, String> requestMap,Voice voice) {
        super(requestMap);
        this.setMsgType("voice");
        this.voice = voice;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
