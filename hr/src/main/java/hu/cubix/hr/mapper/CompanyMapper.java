package hu.cubix.hr.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.cubix.hr.dto.CompanyDto;
import hu.cubix.hr.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	public CompanyDto companyToDto(Company company);

	public Company dtoToCompany(CompanyDto companyDto);

	public List<CompanyDto> companyListToDto(List<Company> companies);

	public List<Company> dtoListToCompany(List<CompanyDto> companies);

}
