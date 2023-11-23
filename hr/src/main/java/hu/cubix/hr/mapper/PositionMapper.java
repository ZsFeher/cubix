package hu.cubix.hr.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.cubix.hr.dto.PositionDto;
import hu.cubix.hr.model.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {
	public PositionDto positionToDto(Position position);

	@InheritInverseConfiguration
	public Position dtoToPosition(PositionDto companyDto);

}
