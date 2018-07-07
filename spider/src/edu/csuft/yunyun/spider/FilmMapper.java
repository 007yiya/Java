package edu.csuft.yunyun.spider;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


public interface FilmMapper {
	
		@Insert("insert into films(id,title,star,rating,info,poster,quote) values(#{id},#{title},#{star},#{rating},#{info},#{poster},#{quote})")
		void insert(Film m);

		@Select("select * from films where id=#{pk}")
		Film load(int pk);

		@Select("select * from films")
		List<Film> find();


}
