package service;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/bankingmanager";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";


    public CustomerServiceImpl() {
    }

    protected Connection connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCustomer(Customer customer) throws SQLException {
        String INSERT_CUSTOMERS_SQL = "INSERT INTO customers" + "(name, email, phone, address)VALUES" + "(?, ?, ?, ?);";

        System.out.println(INSERT_CUSTOMERS_SQL);
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getAddress());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    public Customer selectCustomer(int id) throws SQLException {
        String SELECT_CUSTOMER_BY_ID = "select id, name, email, phone, address, balance from customers where id = ?";
        Customer customer = null;
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Double balance = resultSet.getDouble("balance");

                customer = new Customer(id, name, email, phone, address, balance);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }


    public List<Customer> selectAllCustomers() throws SQLException {
        String SELECT_ALL_CUSTOMERS = "select * from customers";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                Double balance = resultSet.getDouble("balance");

                customers.add(new Customer(id, name, email, phone, address, balance));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customers;
    }


    public boolean deleteCustomer(int id) throws SQLException {
        String DELETE_CUSTOMER_SQL = "delete from customers where id = ?;";
        boolean rowDelete;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() > 0;
        }
        return rowDelete;
    }


    public boolean updateCustomer(Customer customer) throws SQLException {
        String UPDATE_CUSTOMER_SQL = "update customers set name = ?, email = ?, phone = ?, address = ? where id = ?;";
        boolean rowUpdate;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());
//            statement.setDouble(5, customer.getBalance());
            statement.setInt(5, customer.getId());

            rowUpdate = statement.executeUpdate() > 0;
        }
        return rowUpdate;
    }

    public boolean findCustomer(Customer customer) throws SQLException {
        String FIND_CUSTOMER_BY_NAME = "find id, name, email, phone, address, balance from customers where name = ?";
        boolean rowFind;
        try (Connection connection = connection(); PreparedStatement statement = connection.prepareStatement(FIND_CUSTOMER_BY_NAME)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());
            statement.setDouble(4, customer.getBalance());

            rowFind = statement.execute();
        }
        return rowFind;
    }


    public boolean deposits(int id, double balance) throws SQLException {
        boolean isDeposit = false;
        String DEPOSITS = "call sp_deposit (?,?)";
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(DEPOSITS)){
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.setDouble(2, balance);
            preparedStatement.executeUpdate();
            isDeposit = true;
        } catch (SQLException e) {
            printSQLException(e);
        }

        return isDeposit;
    }

    public void withdraws(int id, double balance) throws SQLException {
        String WITHDRAWS = "call sp_withdraw (?,?)";
        try (Connection connection = connection(); PreparedStatement preparedStatement = connection.prepareStatement(WITHDRAWS)){
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            preparedStatement.setDouble(2, balance);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }
}
