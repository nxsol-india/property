package com.nxsol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nxsol.entity.Property;

public interface PropertyService extends IFinder<Property> , IService<Property>{

	Page<Property> search(Pageable pageable, String searchText);

}
