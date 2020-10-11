package springdemo.dao;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import springdemo.entity.Customer;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {

        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //create a query
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer", Customer.class); //abr run koren

        //execute query and get the all result
        List<Customer> customers = theQuery.getResultList();

        return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {

        Session currentSession = sessionFactory.getCurrentSession();

        //save or update customer
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomerId(int id) {

        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

       Query theQuery = session.createQuery("delete from Customer where id=:customerId");

       theQuery.setParameter("customerId",id);

       theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String searchName) {

        Session session = sessionFactory.getCurrentSession();

        Query theQuery = null;

        if (searchName != null && searchName.trim().length() > 0){
            // search for firstName or lastName ... case insensitive
            theQuery =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + searchName.toLowerCase() + "%");
        }  else {
            // theSearchName is empty ... so just get all customers
            theQuery =session.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
}
