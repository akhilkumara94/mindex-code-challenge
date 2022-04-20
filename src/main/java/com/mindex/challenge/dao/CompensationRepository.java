package com.mindex.challenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

/**
 * Repository class for {@link Compensation}.
 * 
 * @author akhil
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
	Compensation findFirstCompensationByEmployee(Employee employee);
}