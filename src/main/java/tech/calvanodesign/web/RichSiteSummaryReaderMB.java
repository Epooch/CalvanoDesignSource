package tech.calvanodesign.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import tech.calvanodesign.business.RSSReaderBo;
import tech.calvanodesign.object.RSS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Eric
 *
 */
@ManagedBean
@ViewScoped
public class RichSiteSummaryReaderMB implements Serializable {
	
	private static final long serialVersionUID = -5142981300074618434L;

	final static Logger logger = Logger.getLogger(RichSiteSummaryReaderMB.class);
	
	@ManagedProperty(value = "#{rSSReaderBo}")
	private RSSReaderBo rssReaderBo;

	private String rssFeed;
	
	private List<RSS> rssObjs;
	
	private List<String> rssUrls;
	
	@PostConstruct
	public void init () {
		logger.debug(">> RSSReaderMB.init()");
		
		if (rssReaderBo == null)
		{
			logger.error("RSSReaderBo is null");
			return;
		}
		
		rssReaderBo.init();
		logger.debug("<< RSSReaderBoImpl.init()");
		rssFeed = new String();
		rssObjs = new ArrayList<RSS>();
		rssUrls = new ArrayList<String>();
		logger.debug("<< RSSReaderMB.init()");
	}
	
	public void pullRss() {
		logger.debug(">> RSSReaderMB.pullRss");
		
		if (!rssFeed.isEmpty())//Not empty
		{
			rssUrls.add(rssFeed);
		}
		rssObjs = rssReaderBo.readRssFeed(rssUrls);
		logger.debug("<< RSSReaderMB.pullRss");
	}
	
	public void refreshRss() {
		logger.debug(">> RSSReaderMB.refreshRss");
		rssObjs = rssReaderBo.readRssFeed(rssUrls);
		logger.debug("<< RSSReaderMB.refreshRss");
	}
	
	public void addRssUrlToList() {
		logger.debug(">> RSSReaderMB.addRssUrlToList");
		rssUrls.add(rssFeed);
		clearRssFeedInput();
		logger.debug("<< RSSReaderMB.addRssUrlToList");
	}
	
    public void setRssReaderBo(RSSReaderBo rSSReaderBo) {
        this.rssReaderBo = rSSReaderBo;
    }
    
    public RSSReaderBo getRssReaderBo() {
    	return rssReaderBo;
    }

	public String getRssFeed() {
		return rssFeed;
	}
	
	public void setRssFeed(String rssFeed) {
		this.rssFeed = rssFeed;
	}
	
	public List<String> getRssUrls() {
		return rssUrls;
	}
	
	public void setRssUrls(List<String> rssUrls) {
		this.rssUrls = rssUrls;
	}

	public List<RSS> getRssObjs() {
		return rssObjs;
	}
	
	public void setRssObjs(List<RSS> rssObjs) {
		this.rssObjs = rssObjs;
	}
	
	private void clearRssFeedInput() {
		logger.debug("--clearRssFeedInput();");
		rssFeed = "";
		logger.debug("//cleared for new input");
	}
}