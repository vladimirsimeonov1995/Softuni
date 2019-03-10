package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Customer;
import org.softuni.cardealer.domain.models.service.CustomerServiceModel;
import org.softuni.cardealer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerServiceTests {

    @Autowired
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private CustomerService customerService;

    @Before
    public void init(){
        modelMapper = new ModelMapper();
        customerService = new CustomerServiceImpl(customerRepository, modelMapper);
    }

    @Test
    public void customerService_saveCustomerWithCorrectValues_ReturnsCorrect(){

        Customer customer = getCustomer();

        CustomerServiceModel actual = customerService.saveCustomer(modelMapper
                .map(customer,CustomerServiceModel.class));
        CustomerServiceModel expected = this.modelMapper
                .map(customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void customerService_saveCustomerWithNullValues_TrowsException(){

        CustomerServiceModel toBeSaved = new CustomerServiceModel();
        toBeSaved.setName(null);
        toBeSaved.setBirthDate(null);
        toBeSaved.setYoungDriver(true);

        customerService.saveCustomer(toBeSaved);
    }

    @Test
    public void customerService_editCustomerWithCorrectValues_ReturnsCorrect(){

        Customer customer = getCustomer();

        customer = customerRepository.saveAndFlush(customer);

        CustomerServiceModel toBeEdited = new CustomerServiceModel();
        toBeEdited.setId(customer.getId());
        toBeEdited.setName("Gosho");
        toBeEdited.setBirthDate(LocalDate.now());
        toBeEdited.setYoungDriver(false);

        CustomerServiceModel actual = customerService.editCustomer(toBeEdited);
        CustomerServiceModel expected = this.modelMapper
                .map(customerRepository.findAll().get(0), CustomerServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());

    }



    @Test(expected = Exception.class)
    public void customerService_editCustomerWithNullValues_ThrowsException(){

        Customer customer = getCustomer();

        customer = customerRepository.saveAndFlush(customer);

        CustomerServiceModel toBeEdited = new CustomerServiceModel();
        toBeEdited.setId(customer.getId());
        toBeEdited.setName(null);
        toBeEdited.setBirthDate(null);
        toBeEdited.setYoungDriver(false);

        customerService.editCustomer(toBeEdited);
    }

    @Test
    public void customerService_deleteCustomerWithCorrectValues_ReturnsCorrect(){

        Customer customer = getCustomer();

        customer= customerRepository.saveAndFlush(customer);

        CustomerServiceModel expected = modelMapper
                .map(customerRepository.findAll().get(0), CustomerServiceModel.class);

        CustomerServiceModel actual = customerService.deleteCustomer(customer.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
        Assert.assertEquals(0, customerRepository.findAll().size());


    }

    @Test(expected = Exception.class)
    public void customerService_deleteCustomerWithWrongId_TrowsException(){
        Customer customer = getCustomer();
        customer.setId("wrongID");

        customerService.deleteCustomer(customer.getId());
    }

    @Test
    public void customerService_findCustomerByIdWithCorrectValue_ReturnsCorrect(){
        Customer customer = getCustomer();

        customer = customerRepository.saveAndFlush(customer);

        CustomerServiceModel expected = modelMapper
                .map(customerRepository.findAll().get(0), CustomerServiceModel.class);

        CustomerServiceModel actual = customerService.findCustomerById(expected.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assert.assertEquals(expected.isYoungDriver(), actual.isYoungDriver());
    }

    @Test(expected = Exception.class)
    public void customerService_findCustomerByIdWithNonExistenceId_ThrowsException(){
        customerService.findCustomerById("wrongID");
    }


    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setName("Pesho");
        customer.setBirthDate(LocalDate.now());
        customer.setYoungDriver(true);

        return customer;
    }

}
