package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.junit.jupiter.api.Test;

public class MyToken {
    private String ACCESS_TOKEN;
    private long expiredtime;

    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    public void setACCESS_TOKEN(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public long getExpiredtime() {
        return expiredtime;
    }

    public void setExpiredtime(long expiredtime) {
        this.expiredtime = System.currentTimeMillis()+expiredtime;
    }

    public boolean isExpired(){
        return this.expiredtime<System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "MyToken{" +
                "ACCESS_TOKEN='" + ACCESS_TOKEN + '\'' +
                ", expiredtime=" + expiredtime +
                '}';
    }
}
