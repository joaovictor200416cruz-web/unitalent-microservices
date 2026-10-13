package br.com.unitalent.msnotificacoes;

import br.com.unitalent.msnotificacoes.dto.NotificacaoRequest;
import br.com.unitalent.msnotificacoes.service.NotificacaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoConsumer {

    private final NotificacaoService notificacaoService;

    public NotificacaoConsumer(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receberNotificacao(NotificacaoRequest evento) {
        notificacaoService.create(evento);
        System.out.println("Notificação recebida e salva pelo RabbitMQ!");
    }
}