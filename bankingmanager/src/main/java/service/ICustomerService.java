package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerService {
    public void insertCustomer (Customer customer) throws SQLException;

    public Customer selectCustomer(int id) throws SQLException;

    public List<Customer>selectAllCustomers() throws SQLException;

    public boolean deleteCustomer(int id) throws SQLException;

    public boolean updateCustomer(Customer customer) throws SQLException;

    public boolean findCustomer(Customer customer) throws SQLException;

    public boolean deposits (int id, double balance) throws SQLException;

    public void withdraws (int id, double balance) throws SQLException;
}
