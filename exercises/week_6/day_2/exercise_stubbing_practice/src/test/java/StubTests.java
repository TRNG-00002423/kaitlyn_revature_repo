import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.Product;

@ExtendWith(MockitoExtension.class)
public class StubTests {
    @Mock
    private ProductRepository productRepository

    @Test
    void calculateOrderTotal_multipleProducts_returnsCorrectSum() {
        // Arrange: Stub product lookups
        Product laptop = new Product("LAPTOP", "MacBook Pro", new BigDecimal("1999.99"));
        Product mouse = new Product("MOUSE", "Magic Mouse", new BigDecimal("79.99"));

        when(productRepository.findById("LAPTOP")).thenReturn(Optional.of(laptop));
        when(productRepository.findById("MOUSE")).thenReturn(Optional.of(mouse));

        // Act
        BigDecimal total = orderService.calculateTotal(List.of("LAPTOP", "MOUSE"));

        // Assert
        assertEquals(new BigDecimal("2079.98"), total);
    }
}
