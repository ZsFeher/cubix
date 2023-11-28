package hu.cubix.hr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.cubix.hr.model.Employee;
import hu.cubix.hr.repositories.EmployeeRepository;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee user = employeeRepository.findByUsername(username);

		return new User(username,user.getPassword(), user.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
	}
}
