package org.spburegistry.backend.service.implementation;

import java.util.stream.Collectors;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.entity.Client;
import org.spburegistry.backend.entity.User;
import org.spburegistry.backend.enums.Role;
import org.spburegistry.backend.repository.ClientRepo;
import org.spburegistry.backend.repository.UserRepo;
import org.spburegistry.backend.service.ClientService;
import org.spburegistry.backend.utils.ConvertToTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final UserRepo userRepo;

    @Autowired
    public ClientServiceImpl(ClientRepo clientRepo, UserRepo userRepo) {
        this.clientRepo = clientRepo;
        this.userRepo = userRepo;
    }

    @Override
    public Iterable<UserTO> findAll() {
        return clientRepo.findAll().stream()
                .map(client -> ConvertToTO.userToTO(client.getUser()))
                .collect(Collectors.toSet());
    }

    @Override
    public UserTO findById(long id) {
        return clientRepo.findById(id)
                .map(Client::getUser)
                .map(ConvertToTO::userToTO)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found"));
    }

    @Override
    public ClientTO addClient(ClientTO clientTO) {
        User newUser = User.builder()
                .role(Role.USER)
                .name(clientTO.getName())
                .email(clientTO.getEmail())
                .build();
        User user = userRepo.save(newUser);
        Client newClient = Client.builder()
                .link(clientTO.getLink())
                .organizationName(clientTO.getOrgName())
                .phone(clientTO.getPhone())
                .user(user)
                .build();
        Client client = clientRepo.save(newClient);
        return ConvertToTO.clientToTO(client);
    }

}
