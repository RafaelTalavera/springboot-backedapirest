package com.axiomasi.springboot.backedapirest.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.axiomasi.springboot.backedapirest.models.entity.Sale;

public interface ISaleDao extends CrudRepository<Sale,Long> {

}
