package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Followers;

public interface FollowersDao extends JpaRepository<Followers, Long> {

//	@Query("Select f.follersId, COUNT(f) from Followers f where f.userId = :userId Group By f.userId")
//	List<Object[]> countOfFollwersForUserId(@PathVariable Users userId);
	
	//@Query("insert into Followers f (f.userId, f.followersId) values()")
	
//	@Query("Select f from Followers f where f.followersId= :fId and f.userId= :uId")
//	List<Followers> findByFollowersIdandUserId(@Param("fId") Long followersId,@Param("uId") Long userId);
	
	List<Followers> findByUserId(Long userId);
	
}

