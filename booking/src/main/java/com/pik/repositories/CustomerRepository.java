package com.pik.repositories;

import com.pik.entities.Customer;
import com.pik.entities.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByEmail(@Param(value="email") String email);
}
