package com.nails.nastya.nailsme.mapper;

import com.nails.nastya.nailsme.dto.MasterDto;
import com.nails.nastya.nailsme.persistance.Master;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MasterMapper {

    MasterDto masterToMasterDto(Master entity);

    Master masterDtoToMaster(MasterDto dto);

    List<MasterDto> masterToMasterDtoList(List<Master> entities);

    List<Master> masterDtoToMasterList(List<MasterDto> dtos);
}
