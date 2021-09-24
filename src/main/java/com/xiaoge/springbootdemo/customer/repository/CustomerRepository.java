package com.xiaoge.springbootdemo.customer.repository;
import com.xiaoge.springbootdemo.customer.Pojo.Customer;
import com.xiaoge.springbootdemo.customer.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CustomerRepository {
    List<Customer> customers = new ArrayList<>();

    public CustomerRepository(){
        customers.add(new Customer("c-001", "test1", "TestLast1", 25));
        customers.add(new Customer("c-002", "test2", "TestLast1", 215));
        customers.add(new Customer("c-003", "test3", "TestLast1", 252));
        customers.add(new Customer("c-004", "test4", "TestLast1", 253));
        customers.add(new Customer("c-005", "test5", "TestLast1", 254));
        customers.add(new Customer("c-006", "test6", "TestLast1", 255));
        customers.add(new Customer("c-007", "test7", "TestLast1", 256));
        customers.add(new Customer("c-008", "test8", "TestLast1", 257));
        customers.add(new Customer("c-009", "test9", "TestLast12", 258));
        customers.add(new Customer("c-010", "test10", "TestLast10", 259));


    }
    public Customer getCustomerId(String id){
        return this.customers.stream().filter(c->c.getId().equals(id)).findFirst().orElseThrow(UserNotFoundException::new);
    }

    public Customer addCustomer(Customer customer) {
        customer.setId(UUID.randomUUID().toString());
        this.customers.add(customer);
        return customer;
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer c = getCustomerId(id);
        customers.set(customers.indexOf(c), customer);
        return c;

    }


    public List<Customer> getAllCustomer() {
        return this.customers;
    }
}
