package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.ContactDto;
import com.nails.nastya.nailsme.persistance.Contact;
import com.nails.nastya.nailsme.web.response.MasterContactsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContactMapper {

    MasterContactsResponse dtoToMasterContactsResponse(ContactDto dto);

    ContactDto entityToDto(Contact entity);

    Contact dtoToEntity(ContactDto dto);

    List<ContactDto> entityListToDtoList(List<Contact> entities);

    List<Contact> dtoListToEntityList(List<ContactDto> dtos);
}
