package com.hatefulbug.authserver.service;

import com.hatefulbug.authserver.dto.ClientDto;
import com.hatefulbug.authserver.dto.MessageDto;
import com.hatefulbug.authserver.entity.Client;
import com.hatefulbug.authserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private Client createClientFromDto(ClientDto clientDto) {
        return Client.builder()
                .clientId(clientDto.getClientId())
                .clientSecret(passwordEncoder.encode(clientDto.getClientSecret()))
                .authenticationMethods(clientDto.getAuthenticationMethods())
                .authorizationGrantTypes(clientDto.getAuthorizationGrantTypes())
                .redirectUris(clientDto.getRedirectUris())
                .scopes(clientDto.getScopes())
                .requireProofKey(clientDto.isRequireProofKey())
                .build();
    }

    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findByClientId(id)
                .orElseThrow(()-> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(()-> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    public MessageDto create(ClientDto clientDto) {
        Client client = createClientFromDto(clientDto);
        clientRepository.save(client);
        return new MessageDto(String.format("Client created: %s", clientDto.getClientId()));
    }

}
