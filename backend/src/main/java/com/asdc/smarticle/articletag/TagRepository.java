package com.asdc.smarticle.articletag;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findByIdIn(Set<Tag> set);

	//List<Tag> findAllById(Set<Tag> tagId);

}
