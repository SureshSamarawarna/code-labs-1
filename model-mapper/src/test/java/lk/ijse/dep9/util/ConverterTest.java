package lk.ijse.dep9.util;

import com.github.javafaker.Faker;
import lk.ijse.dep9.dto.CustomerDTO;
import lk.ijse.dep9.dto.OrderDTO;
import lk.ijse.dep9.dto.OrderDetailDTO;
import lk.ijse.dep9.entity.Customer;
import lk.ijse.dep9.entity.Order;
import lk.ijse.dep9.entity.OrderDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Challenge: Revamp the converter class to pass below test suite
 */

class ConverterTest {

    private Converter converter;
    private Faker faker;

    @BeforeEach
    void setUp() {
        converter = new Converter();
        faker = new Faker();
    }

    @Test
    void toCustomer() {
        CustomerDTO customerDTO = new CustomerDTO(faker.idNumber().valid(), faker.name().fullName(), faker.address().fullAddress());
        Customer customer = converter.toCustomer(customerDTO);
        assertEquals(customerDTO.getCustomerId(), customer.getId());
        assertEquals(customerDTO.getFullName(), customer.getName());
        System.out.println(customer);
        assertEquals(customerDTO.getResidence(), customer.getAddress());
    }

    @Test
    void toOrder() {
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        OrderDTO orderDTO = new OrderDTO(faker.number().randomDigit() + "", LocalDate.now(),
                faker.idNumber().valid(), orderDetailList);
        Order order = converter.toOrder(orderDTO);
        assertEquals(orderDTO.getId(), order.getId() + "");
        System.out.println(order);
        assertEquals(orderDTO.getClientId(), order.getCustomerId());
        assertEquals(orderDTO.getDate().toString(), order.getOrderDate().toString());
    }

    @Test
    void toOrderDetailList() {
        List<OrderDetailDTO> orderDetailList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            orderDetailList.add(new OrderDetailDTO(faker.regexify("I\\d{3}"), faker.number().randomDigit(),
                    new BigDecimal(faker.number().numberBetween(2500, 5000))));
        }
        OrderDTO orderDTO = new OrderDTO(faker.number().randomDigit() + "", LocalDate.now(),
                faker.idNumber().valid(), orderDetailList);
        List<OrderDetail> orderDetails = converter.toOrderDetailList(orderDTO);
        assertEquals(orderDetailList.size(), orderDetails.size());
        for (int i = 0; i < orderDetails.size(); i++) {
            assertEquals(orderDetailList.get(i).getCode(), orderDetails.get(i).getCode());
            assertEquals(orderDetailList.get(i).getPrice(), orderDetails.get(i).getUnitPrice());
            assertEquals(orderDetailList.get(i).getQty(), orderDetails.get(i).getQty());
        }
    }
}