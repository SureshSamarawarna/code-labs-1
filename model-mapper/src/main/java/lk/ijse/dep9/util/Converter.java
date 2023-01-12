package lk.ijse.dep9.util;

import lk.ijse.dep9.dto.CustomerDTO;
import lk.ijse.dep9.dto.OrderDTO;
import lk.ijse.dep9.dto.OrderDetailDTO;
import lk.ijse.dep9.entity.Customer;
import lk.ijse.dep9.entity.Order;
import lk.ijse.dep9.entity.OrderDetail;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    private final ModelMapper mapper = new ModelMapper();

    public Customer toCustomer(CustomerDTO customerDTO){
        mapper.typeMap(CustomerDTO.class, Customer.class)
                .addMapping(CustomerDTO::getResidence, Customer::setAddress);
        return mapper.map(customerDTO, Customer.class);
    }

    public Order toOrder(OrderDTO orderDTO){
        mapper.typeMap(OrderDTO.class, Order.class)
                .addMapping(OrderDTO::getClientId, Order::setCustomerId);
        mapper.typeMap(OrderDTO.class, Order.class)
                .addMapping(OrderDTO::getDate, Order::setOrderDate);

        mapper.typeMap(LocalDate.class, Date.class).setConverter(mc -> Date.valueOf(mc.getSource()));

        return mapper.map(orderDTO, Order.class);
    }

    public List<OrderDetail> toOrderDetailList(OrderDTO orderDTO){
        mapper.typeMap(OrderDTO.class, ArrayList.class).setProvider(pr -> {
            OrderDTO order = (OrderDTO) pr.getSource();
//            return (ArrayList) order.getOrderDetails().stream().map(dto->
//                    new OrderDetail(dto.getCode(), dto.getQty(), dto.getPrice()))
//                    .collect(Collectors.toList());
            return (ArrayList) order.getOrderDetails().stream().map(this::toOrderDetail)
                    .collect(Collectors.toList());
//            ArrayList<OrderDetail> orderDetails = new ArrayList<>();
//            for (OrderDetailDTO dto : order.getOrderDetails()) {
//                orderDetails.add(new OrderDetail(dto.getCode(), dto.getQty(), dto.getPrice()));
//            }
//            return orderDetails;
        });
        return mapper.map(orderDTO, ArrayList.class);
    }

    public OrderDetail toOrderDetail(OrderDetailDTO orderDetailDTO){
        mapper.typeMap(OrderDetailDTO.class, OrderDetail.class).
                addMapping(OrderDetailDTO::getPrice, OrderDetail::setUnitPrice);
        return mapper.map(orderDetailDTO, OrderDetail.class);
    }
}
