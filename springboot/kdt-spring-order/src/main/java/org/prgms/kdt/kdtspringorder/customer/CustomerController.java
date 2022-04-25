package org.prgms.kdt.kdtspringorder.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    @GetMapping(value = "/api/v1/customers")
    @ResponseBody
    public List<Customer> findCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(value = "/api/v1/customers/{customerId}")
    @ResponseBody
    public ResponseEntity<Customer> findCustomer(@PathVariable("customerId") UUID customerId) {
        return customerService.getCustomer(customerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/api/v1/customers/{customerId}")
    @ResponseBody
    public CustomerDto saveCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customer) {
        return customer;
    }

    @GetMapping(value = "/customers")
    public String viewCustomersPage(Model model) {

        List<Customer> allCustomers = customerService.getAllCustomers();
        model.addAttribute("serverTime", LocalDateTime.now());
        model.addAttribute("customers",allCustomers);
        return "views/customers";
    }

    @GetMapping("/customers/{customerId}")
    public String findCustomer(@PathVariable("customerId") UUID customerId, Model model) {
        Optional<Customer> maybeCustomer = customerService.getCustomer(customerId);
        if (maybeCustomer.isPresent()) {
            model.addAttribute("customer", maybeCustomer.get());
            return "views/customer-details";
        }
        else {
            return "views/404";
        }
    }

    @GetMapping("customers/new")
    public String viewNewCustomerPage(){
        return "views/new-customers";
    }

    @PostMapping("customers/new")
    public String addNewCustomer(CreateCustomerRequest createCustomerRequest){
        customerService.createCustomer(createCustomerRequest.email(),createCustomerRequest.name());
        return "redirect:/customers";
    }

}
