package com.m2p.repository;

import java.util.List;

import com.m2p.model.Notice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NoticeRepository extends CrudRepository<Notice, Long> {
	
	@Query(value = "from Notice n ")
	List<Notice> findAllActiveNotices();

}
