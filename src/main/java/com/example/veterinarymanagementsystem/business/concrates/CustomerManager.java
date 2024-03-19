package com.example.veterinarymanagementsystem.business.concrates;

import com.example.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.example.veterinarymanagementsystem.dto.request.CustomerRequest;
import com.example.veterinarymanagementsystem.dto.response.CustomerResponse;
import com.example.veterinarymanagementsystem.entities.Customer;
import com.example.veterinarymanagementsystem.mapper.CustomerMapper;
import com.example.veterinarymanagementsystem.repository.CustomerDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerDao customerDao;

    public CustomerManager(CustomerMapper customerMapper, CustomerDao customerDao) {
        this.customerMapper = customerMapper;
        this.customerDao = customerDao;
    }

    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        Optional<Customer> isCustomerExist=customerDao.findByNameAndAddressAndCityAndEmailAndPhone(customerRequest.getName(),customerRequest.getAddress(),customerRequest.getCity(),customerRequest.getEmail(),customerRequest.getPhone());
        if (isCustomerExist.isEmpty())
        {
            Customer customer=customerDao.save(customerMapper.asEntity(customerRequest));
            return customerMapper.asOutput(customer);
        }
        throw new RuntimeException("Bu müşteri daha önce sisteme kaydedilmiştir.");
    }

    @Override
    public List<CustomerResponse> findAll() {
        return customerMapper.asOutput(customerDao.findAll());
    }

    @Override
    public CustomerResponse getById(Long id) {
        return customerMapper.asOutput(customerDao.findById(id).orElseThrow(() ->new RuntimeException(id+" id li müşteri bulunamadı")));
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        Optional<Customer> customerFromDB=customerDao.findById(id);
        Optional<Customer> isCustomerExist=customerDao.findByNameAndAddressAndCityAndEmailAndPhone(request.getName(),request.getAddress(),request.getCity(),request.getEmail(),request.getPhone());

        if (customerFromDB.isEmpty()){
            throw new RuntimeException(" Güncellemeye çalıştığınız müşteri sistemde bulunamadı.");
        }
        if (isCustomerExist.isPresent()){
            throw new RuntimeException(" Bu müşteri daha önce sisteme kayıt olmuştur.");
        }
        Customer customer=customerFromDB.get();
        customer.setName(request.getName());
        customer.setPhone(request.getPhone());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        return customerMapper.asOutput(customerDao.save(customer));
    }

    @Override
    public void deleteById(Long id) {
    Optional<Customer> customerFromDB=customerDao.findById(id);
    if(customerFromDB.isPresent()){
        customerDao.delete(customerFromDB.get());
    }else {
        throw new RuntimeException(id + " id li Müşteri sistemde bulunamadı !!!");
    }
    }
}
