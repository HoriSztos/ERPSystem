package com.example.erpsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }
    public Client getClientById(Long id){
        return clientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Klient nie znaleziony"));
    }
    public Client saveClient(Client client){
        return  clientRepository.save(client);
    }
    public  void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}
