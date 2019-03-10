package org.softuni.cardealer.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.softuni.cardealer.domain.entities.Supplier;
import org.softuni.cardealer.domain.models.service.SupplierServiceModel;
import org.softuni.cardealer.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SupplierServiceTests {

    @Autowired
    private SupplierRepository supplierRepository;
    private ModelMapper modelMapper;
    private SupplierService supplierService;

    @Before
    public void init(){
        modelMapper =new ModelMapper();
        supplierService = new SupplierServiceImpl(this.supplierRepository, this.modelMapper);
    }

    @Test
    public void supplierService_saveSupplierWithCorrectValues_ReturnsCorrect(){

        SupplierServiceModel toBeSaved = new SupplierServiceModel();
        toBeSaved.setName("Pesho");
        toBeSaved.setImporter(true);

        SupplierServiceModel actual = supplierService.saveSupplier(toBeSaved);
        SupplierServiceModel expected = this.modelMapper
                .map(this.supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_saveSupplierWithNullValues_TrowsException(){

        SupplierServiceModel toBeSaved = new SupplierServiceModel();
        toBeSaved.setName(null);
        toBeSaved.setImporter(true);

        supplierService.saveSupplier(toBeSaved);
    }

    @Test
    public void supplierService_editSupplierWithCorrectValues_ReturnsCorrect(){

        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        supplier = supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel toBeEdited = new SupplierServiceModel();
        toBeEdited.setId(supplier.getId());
        toBeEdited.setName("Gosho");
        toBeEdited.setImporter(false);

        SupplierServiceModel actual = supplierService.editSupplier(toBeEdited);
        SupplierServiceModel expected = this.modelMapper
                .map(supplierRepository.findAll().get(0), SupplierServiceModel.class);

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());

    }

    @Test(expected = Exception.class)
    public void supplierService_editSupplierWithNullValues_ThrowsException(){

        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        supplier = supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel toBeEdited = new SupplierServiceModel();
        toBeEdited.setId(supplier.getId());
        toBeEdited.setName(null);
        toBeEdited.setImporter(false);

        supplierService.editSupplier(toBeEdited);
    }

    @Test
    public void supplierService_deleteSupplierWithCorrectValues_ReturnsCorrect(){

        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        supplier = supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel expected = modelMapper
                .map(supplierRepository.findAll().get(0), SupplierServiceModel.class);

        SupplierServiceModel actual = supplierService.deleteSupplier(supplier.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
        Assert.assertEquals(0, supplierRepository.findAll().size());


    }

    @Test(expected = Exception.class)
    public void supplierService_deleteSupplierWithWrongId_TrowsException(){
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        supplierService.deleteSupplier(supplier.getId());
    }

    @Test
    public void supplierService_findByIdWithCorrectValue_ReturnsCorrect(){
        Supplier supplier = new Supplier();
        supplier.setName("Pesho");
        supplier.setImporter(true);

        supplier = supplierRepository.saveAndFlush(supplier);

        SupplierServiceModel expected = modelMapper
                .map(supplierRepository.findAll().get(0), SupplierServiceModel.class);

        SupplierServiceModel actual = supplierService.findSupplierById(expected.getId());

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.isImporter(), actual.isImporter());
    }

    @Test(expected = Exception.class)
    public void supplierService_findByIdWithNonExistenceId_ThrowsException(){
        supplierService.findSupplierById("wrongID");
    }
}
