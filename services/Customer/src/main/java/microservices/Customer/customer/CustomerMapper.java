package microservices.Customer.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address()).build();
    }

    public CustomerResponce fromCustomer(Customer customer) {
        return new CustomerResponce(
                customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getAddress()
        );
    }
}
