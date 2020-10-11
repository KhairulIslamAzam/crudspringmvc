package springdemo.dao;

import springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

     List<Customer> getCustomers();

     void saveCustomer(Customer customer);

     Customer getCustomerId(int id);

     void deleteCustomer(int id);

     List<Customer> searchCustomers(String searchName);
}
