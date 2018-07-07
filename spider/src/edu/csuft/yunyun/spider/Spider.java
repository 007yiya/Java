package edu.csuft.yunyun.spider;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 
 * @author yunyun
 *
 */
public class Spider extends Thread{
	
	String url;
//	static List<Film> filmList = new ArrayList<>();
	List<Film> films;
	public Spider(String url,List<Film> films) {
		this.url = url;
		this.films=films;
	}

	public void run() {
		//抓取数据
		try {
			Document doc = Jsoup.connect(url).get();
			
			Elements items=doc.select(".grid_view .item");
			
			
			//遍历
			for(Element item :items) {
				Film film = new Film();
				film.setTitle(item.select(".title").get(0).text());
				String img = item.select("img").get(0).attr("src");
				film.setPoster(img);
				film.setInfo(item.select(".info .bd p").get(0).text());
				film.setQuote(item.select(".info .bd .inq").text());
				String num = item.select(".star span").get(1).text();
				film.setStar(Double.parseDouble(num));
				film.setRating(item.select(".star span").last().text());
				String id = item.select("em").get(0).text();
				film.setId(Integer.parseInt(id));
				

				films.add(film);
				
				//下载海报
				ImgFile imgfile = new ImgFile(img);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println();
		}
		
	}

}