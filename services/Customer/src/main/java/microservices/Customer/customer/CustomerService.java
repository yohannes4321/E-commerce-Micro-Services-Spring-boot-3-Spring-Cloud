package microservices.Customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositery customerRepositery;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        var customer=customerRepositery.save(customerMapper.toCustomer(request));
        return customer.getId();

    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer=customerRepositery.findById(request.id()).orElseThrow(() ->  new CustomerNotFoundException(
                String.format("Can not update customer No customer found ")
        ));
       mergeCustomer(customer,request);
       customerRepositery.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        } if (StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        } if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        } if ((request.address() != null)){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponce> findAllCustomers() {
        return customerRepositery.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepositery.findById(customerId).isPresent();
    }

    public CustomerResponce findbyId(String customerId) {
        return customerRepositery.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() ->  new CustomerNotFoundException(String.format("Can not find customer No customer found ID :: %s",customerId)));
    }

    public String deletebyId(String customerId) {
        customerRepositery.deleteById(customerId);
        return "customer  successfully deleted";
    }
}
