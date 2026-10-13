package br.com.unitalent.msnotificacoes;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "unitalent.exchange";
    public static final String QUEUE = "notificacoes.queue";
    public static final String ROUTING_KEY = "candidatura.enviada";

    @Bean
    public TopicExchange unitalentExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue notificacoesQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding notificacoesBinding(
            Queue notificacoesQueue,
            TopicExchange unitalentExchange) {
        return BindingBuilder
                .bind(notificacoesQueue)
                .to(unitalentExchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}