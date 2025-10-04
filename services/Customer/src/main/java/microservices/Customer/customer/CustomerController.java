package microservices.Customer.customer;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<String> CreateCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));

    }
    @PutMapping
    public ResponseEntity<String> UpdateCustomer(@RequestBody @Valid CustomerRequest request) {
        customerService.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponce>> getAllCustomers() {
       return  ResponseEntity.ok(customerService.findAllCustomers());
    }
    @GetMapping("exists/{customer-id}")
    public ResponseEntity<Boolean> existsCustomer(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.existsById(customerId));
    }
    @GetMapping("findbyid/{customer-id}")
    public ResponseEntity<CustomerResponce> findbyid(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findbyId(customerId));
    }
    @DeleteMapping("delete/customerid")
    public ResponseEntity<String> deletebyid(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.deletebyId(customerId));
    }

}
