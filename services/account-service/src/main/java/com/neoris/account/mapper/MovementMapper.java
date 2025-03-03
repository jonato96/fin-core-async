package com.neoris.account.mapper;

import com.neoris.account.domain.Movement;
import com.neoris.account.dto.MovementDto;
import com.neoris.account.dto.MovementResponseDto;
import com.neoris.account.dto.ReportMovementDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovementMapper {

    MovementResponseDto toResponseDto(Movement movement);
    List<MovementResponseDto> toListResponseDto(List<Movement> movementList);
    Movement toTransaction(MovementDto dto);
    List<ReportMovementDto> toReportDto (List<Movement> movementList);
}
