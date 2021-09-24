package com.xiaoge.springbootdemo.customer.Controller;

import com.xiaoge.springbootdemo.customer.Pojo.Customer;
import com.xiaoge.springbootdemo.customer.exception.UserNotFoundException;
import com.xiaoge.springbootdemo.customer.repository.CustomerRepository;
import com.xiaoge.springbootdemo.customer.response.ErrorResponse;
import com.xiaoge.springbootdemo.customer.response.PageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("customer")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    CustomerRepository customerRepository;

//    @GetMapping
//    public ResponseEntity<List<Customer>> getCustomerAll(){
//        List<Customer> cList = customerRepository.getAllCustomer();
//        return new ResponseEntity<>(cList, HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<PageResponse> getCustomerAll(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                       @RequestParam(value = "rows", required = false, defaultValue = "5") int rowsPerPage,
                                                       @RequestParam(value = "orderby", required = false, defaultValue = "name") String order){
        log.debug("page {} , rows {} , order {} ", page, rowsPerPage, order);
        PageResponse response = customerRepository.getAllCustomerByPage(page, rowsPerPage, order);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{cid}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("cid") String id){
        Customer c = customerRepository.getCustomerId(id);
        if(c == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customer>(customerRepository.getCustomerId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> createNewCustomer(@RequestBody @Valid Customer customer){

        Customer saveCustomer = customerRepository.addCustomer(customer);
//        if(customer.getFirstName() == null || customer.getId() == null || customer.getLastName() == null || customer.getAge() == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }//or Valid annotation
        return new ResponseEntity<>(saveCustomer, HttpStatus.CREATED);
    }
    @PutMapping(value = "{cid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("cid") String id, @RequestBody @Valid Customer customer){

        Customer saveCustomer = customerRepository.updateCustomer(id, customer);
        return new ResponseEntity<>(saveCustomer, HttpStatus.OK);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity exceptionHandler(Exception e){

        return new ResponseEntity(new ErrorResponse("User can not found", "404"), HttpStatus.NOT_FOUND);
    }
}
