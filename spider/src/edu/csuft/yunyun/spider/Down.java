package edu.csuft.yunyun.spider;
/**
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Down {
	public static void main(String[] args)  {
		String url = "https://movie.douban.com/top250";
		List<Film> films= Collections.synchronizedList(new LinkedList<>());
		
		//创建一个线程池
		CountDownLatch countDownLatch = new CountDownLatch(10);
		ExecutorService pool = Executors.newFixedThreadPool(10);

		for(int i=0;i<250;i+=25) {
			url = String.format("https://movie.douban.com/top250?start=" + i);
			pool.execute(new Spider(url,films));
			 
		}
		pool.shutdown();
		
		while (true) {
			if (pool.isTerminated()) {

				System.out.println("just wait!you kown");
				break;
			}else {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		Collections.sort(films, new Comparator<Film>() {
			@Override
			public int compare(Film o1, Film o2) {
				// TODO Auto-generated method stub
				if (o1.getId() > o2.getId())
					return 1;
				else if (o1.getId() < o2.getId())
					return -1;
				return 0;
			}
		});
		for(Film f:films) {
			System.out.println(f);
		}
		System.out.println(films.size());
	
		//存入数据库
		SqlSessionFactory factory = null;
		try {
			factory = new SqlSessionFactoryBuilder().build(new FileReader("config.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SqlSession session = factory.openSession();
		
		FilmMapper mapper = session.getMapper(FilmMapper.class);
		
		for (Film f : films) {
			mapper.insert(f);
		}
		session.commit();
		session.close();
		System.out.println("存入成功");
	}
}
