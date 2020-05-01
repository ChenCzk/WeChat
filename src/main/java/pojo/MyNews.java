package pojo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Arrays;
import java.util.Map;

@XStreamAlias("xml")
public class MyNews extends base {
    @XStreamAlias("ArticleCount")
    private String articleCount;

    @XStreamAlias("Articles")
    private Articles articles;


    public MyNews(Map<String, String> requestMap, Articles articles) {
        super(requestMap);
        this.setMsgType("news");
        this.articleCount="1";
        this.articles = articles;
    }

    public String getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(String articleCount) {
        this.articleCount = articleCount;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }
}
