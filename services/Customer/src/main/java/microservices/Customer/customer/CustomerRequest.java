package microservices.Customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is required")
        String firstName,
        @NotNull(message = "Customer lastname is required ")
        String lastName,
        @Email(message = "customer email is not valid email address")
        String email,
        Address address
) {


}
