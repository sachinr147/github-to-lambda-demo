package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import lombok.RequiredArgsConstructor;
import org.example.entity.Order;

@Repository
public class OrderRepo {

    @Autowired
    private  DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public void save(Order order) {
        DynamoDbTable<Order> table = dynamoDbEnhancedClient.table("order", TableSchema.fromBean(Order.class));
        table.putItem(order);
    }
}