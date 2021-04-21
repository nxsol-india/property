package com.nxsol.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.nxsol.entity.Property;
import com.nxsol.repository.PropertyRespository;

@Service
public class PropertyServiceImpl extends BasicService<Property, PropertyRespository> implements PropertyService{

	@Override
	public Page<Property> search(Pageable pageable, String searchText) {
		String queriableText = new StringBuilder("%").append(searchText).append("%").toString();
		return repository.search(pageable, queriableText);
	}
			
}