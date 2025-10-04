package microservices.Customer.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepositery  extends MongoRepository<Customer, String> {
}
