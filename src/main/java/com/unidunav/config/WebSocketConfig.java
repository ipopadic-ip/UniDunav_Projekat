package com.unidunav.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;

import com.unidunav.auth.service.JwtService;
import com.unidunav.user.model.User;
import com.unidunav.user.repository.UserRepository;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;
	
//	@Autowired
//    private JwtHandshakeInterceptor jwtHandshakeInterceptor;
    
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                    MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
                    List<String> authHeaders = accessor.getNativeHeader("Authorization");
                    if (authHeaders != null && !authHeaders.isEmpty()) {
                        String authHeader = authHeaders.get(0);
                        if (authHeader.startsWith("Bearer ")) {
                            String token = authHeader.substring(7);
                            try {
                                String email = jwtService.extractEmail(token);
                                Optional<User> userOpt = userRepository.findByEmail(email);
                                if (userOpt.isPresent() && jwtService.isTokenValid(token, userOpt.get())) {
                                    accessor.getSessionAttributes().put("user", email);
                                }
                            } catch (Exception e) {
                                System.out.println("Greška pri autentifikaciji WebSocketa: " + e.getMessage());
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    // Ostalo ostaje isto...
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*")
            .withSockJS(); // Ne koristi interceptor ovde - nije koristan za SockJS
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws")
//                .addInterceptors(jwtHandshakeInterceptor)
//                .setAllowedOriginPatterns("*")
//                .withSockJS();
//    }

//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        config.enableSimpleBroker("/topic"); // svi koji slušaju /topic/... dobijaju poruke
//        config.setApplicationDestinationPrefixes("/app"); // klijent šalje poruke na /app/...
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws") // endpoint koji se koristi u Angularu
//                .setAllowedOriginPatterns("*")
//                .withSockJS(); // fallback za starije browsere
//    }
}

