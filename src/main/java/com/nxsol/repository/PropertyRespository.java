package com.nxsol.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nxsol.entity.Property;

@Repository
public interface PropertyRespository extends JpaRepository<Property, Long>, JpaSpecificationExecutor<Property>{

	@Query(value = "select p from Property p where p.name in (?1) or p.address in (?1) ")
	Page<Property> search(Pageable pageable, String queriableText);
}

