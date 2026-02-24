package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("id-1");
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("id-2");
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setProductId("id-123");
        productRepository.create(product);
        Product found = productRepository.findById("id-123");
        assertNotNull(found);
        assertEquals("id-123", found.getProductId());
    }

    @Test
    void testFindByIdNotFound() {
        Product product = new Product();
        product.setProductId("id-123");
        productRepository.create(product);
        assertNull(productRepository.findById("id-ngasal"));
    }

    @Test
    void testFindByIdInPopulatedListButNotFound() {
        Product p1 = new Product(); p1.setProductId("1"); productRepository.create(p1);
        Product p2 = new Product(); p2.setProductId("2"); productRepository.create(p2);
        assertNull(productRepository.findById("3"));
    }

    @Test
    void testEditProductSuccess() {
        Product product = new Product();
        product.setProductId("id-123");
        product.setProductName("Lama");
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("id-123");
        updatedProduct.setProductName("Baru");
        productRepository.update(updatedProduct);

        assertEquals("Baru", productRepository.findById("id-123").getProductName());
    }

    @Test
    void testEditProductNotFoundWithPopulatedList() {
        Product product = new Product();
        product.setProductId("id-1");
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("id-ngasal");
        assertNull(productRepository.update(updatedProduct));
    }

    @Test
    void testDeleteProductSuccess() {
        Product product = new Product();
        product.setProductId("id-123");
        productRepository.create(product);
        productRepository.delete("id-123");
        assertNull(productRepository.findById("id-123"));
    }

    @Test
    void testDeleteProductNotFound() {
        Product product = new Product();
        product.setProductId("id-123");
        productRepository.create(product);
        productRepository.delete("id-ngasal");
        assertNotNull(productRepository.findById("id-123"));
    }

    @Test
    void testDeleteInEmptyList() {
        productRepository.delete("any-id");
        assertFalse(productRepository.findAll().hasNext());
    }

    @Test
    void testUpdateInEmptyList() {
        Product product = new Product();
        product.setProductId("id-1");
        assertNull(productRepository.update(product));
    }

    @Test
    void testFindByIdNull() {
        assertNull(productRepository.findById(null));
    }

    @Test
    void testCreateProductWithNullId() {
        Product product = new Product();
        product.setProductId(null); // Paksa null
        product.setProductName("Sampo Tanpa ID");
        product.setProductQuantity(100);

        productRepository.create(product);

        // Cek apakah ID-nya otomatis keisi (gak null lagi)
        assertNotNull(product.getProductId());
        assertFalse(product.getProductId().isEmpty());
    }
}