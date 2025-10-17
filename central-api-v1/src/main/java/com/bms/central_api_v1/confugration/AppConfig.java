package com.bms.central_api_v1.confugration;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    String exchangeName = "bms-notification-exchange";
    String queueName = "bms-notification-queue";

    String routingKey = "bms-notification-route-123";

    @Bean
    public Binding bindQueueWithExchange(DirectExchange exchange,
                                         Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public CachingConnectionFactory getConnection(){
         CachingConnectionFactory connection = new CachingConnectionFactory("localhost");
         connection.setUsername("guest");
         connection.setPassword("guest");
         return connection;
    }

    // To do the operation with the rabbit mq we require RabbitTemplate object
    // So we need to configure RabbitTemplate object here


    @Bean
    public RabbitTemplate getRabbitTemplate(CachingConnectionFactory connection){
        // We are going to use this rabbit template object
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connection);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean

    public Queue getMessagingQueue(){
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public DirectExchange getExchangeObject(){
        DirectExchange exchange = new DirectExchange(exchangeName);
        return exchange;
    }


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
