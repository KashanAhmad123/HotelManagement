package com.example.dao;

import com.example.datamodel.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDaoImpl implements CustomerDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getString("phone"));
            customer.setAddress(rs.getString("address"));

            // Set other customer properties
            return customer;
        });
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setName(rs.getString("name"));
            customer.setEmail(rs.getString("email"));
            customer.setPhone(rs.getString("phone"));
            customer.setAddress(rs.getString("address"));
            // Set other customer properties
            return customer;
        });
    }

    @Override
    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO customers (name, email,phone,address) VALUES (:name, :email,:phone,:address)";
        Map<String, Object> params = new HashMap<>();
        params.put("name", customer.getName());
        params.put("email", customer.getEmail());
        params.put("phone",customer.getPhone());
        params.put("address",customer.getAddress());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void updateCustomer(Customer customer) {
        String sql = "UPDATE customers SET name = :name, email = :email WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("name", customer.getName());
        params.put("email", customer.getEmail());
        params.put("id", customer.getId());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteCustomer(int id) {
        String sql = "DELETE FROM customers WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        jdbcTemplate.update(sql, params);
    }
}