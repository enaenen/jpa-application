package jpabook.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;


    public List<OrderQueryDto> findOrderQueryDtos() {
        List<OrderQueryDto> result = findOrders();
        result.forEach(o->{
            //OrderItems 를 루프돌면서 가져와 채워준다
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
            o.setOrderItems(orderItems);
        });
        return result;
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();
        //OrderId의 리스트
        List<Long> orderIds = toOrderIds(result);
        //쿼리는 한번
        Map<Long, List<OrderItemQueryDto>> orderItemMap = findOrderItemMap(orderIds);
        //MAP에서 찾아서 Order에 셋팅
        result.forEach(o -> o.setOrderItems(orderItemMap.get(o.getOrderId())));
        return result;
    }

    private Map<Long, List<OrderItemQueryDto>> findOrderItemMap(List<Long> orderIds) {
        List<OrderItemQueryDto> orderItems = em.createQuery(
                "SELECT new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id,oi.item.name,oi.orderPrice,oi.count)" +
                        " FROM OrderItem oi" +
                        " JOIN oi.item i" +
                        " WHERE oi.order.id IN :orderIds", OrderItemQueryDto.class)
                .setParameter("orderIds", orderIds)
                .getResultList();
        //MAP으로 변경- 메모리에 로딩 후 forEach로 OrderQueryDto에 셋팅
        return orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));
    }

    private List<Long> toOrderIds(List<OrderQueryDto> result) {
        return result.stream()
                    .map(o -> o.getOrderId())
                    .collect(Collectors.toList());
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "SELECT new jpabook.jpashop.repository.order.query.OrderItemQueryDto(oi.order.id,oi.item.name,oi.orderPrice,oi.count)" +
                " FROM OrderItem oi" +
                " JOIN oi.item i" +
                " WHERE oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId",orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return  em.createQuery(
                "SELECT new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id,m.name,o.orderDate,o.status,d.address)" +
                        " FROM Order o" +
                        " JOIN o.member m " +
                        " JOIN o.delivery d", OrderQueryDto.class)
                .getResultList();
    }


}
