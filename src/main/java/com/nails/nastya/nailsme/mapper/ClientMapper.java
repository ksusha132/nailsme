package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ClientDto;
import com.nails.nastya.nailsme.persistance.Client;
import com.nails.nastya.nailsme.web.request.RegisterClientRequest;
import com.nails.nastya.nailsme.web.response.RegisterNewClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    ClientDto registerClientRequestToClientDto(RegisterClientRequest request);

    RegisterNewClientResponse clientDtoToRegisterNewClientResponse(ClientDto dto);

    ClientDto entityToDto(Client entity);

    Client dtoToEntity(ClientDto dto);

    List<ClientDto> entityListToDtoList(List<Client> entities);

    List<Client> dtoListToEntityList(List<ClientDto> dtos);
}
