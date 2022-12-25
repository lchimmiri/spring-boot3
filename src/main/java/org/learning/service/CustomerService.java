package org.learning.service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.learning.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    ObservationRegistry registry;

    List<Customer> customerList = new ArrayList<>();

    public Customer addCustomer(Customer customer){
        customerList.add(customer);
        return Observation.createNotStarted("addCustomer", registry)
                .observe(() -> customer);
    }

    public List<Customer> getCustomers(){
        return Observation.createNotStarted("getCustomers", registry)
                .observe(() -> customerList);
    }

    public Customer getCustomerById(int id){
        return Observation.createNotStarted("getCustomerById", registry)
                .observe(() ->customerList.stream().filter(customer -> customer.id() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Customer not found with id :: "+id)));
    }
}
