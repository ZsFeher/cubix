package hu.cubix.hr.service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import hu.cubix.hr.config.HRConfigurationProperties;
import hu.cubix.hr.model.Employee;
import hu.cubix.hr.model.HrUser;
import hu.cubix.hr.model.NameAndId;
import hu.cubix.hr.repositories.EmployeeRepository;

@Service
public class JwtService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private HRConfigurationProperties config;

	public String createJwt(HrUser userDetails){

		String[] managedArray = (getManagedEmployees(userDetails.getEmployee().getId())).stream().map(NameAndId::getName).toArray(String[]::new);

		Employee manager = userDetails.getEmployee().getManager();

		//String alg = config.getSecurityConf().getAlgorithm();

		return JWT.create()
				.withSubject(userDetails.getUsername())
				.withClaim("Name", userDetails.getEmployee().getName())
				.withClaim("Id", userDetails.getEmployee().getId())
				.withClaim("Username", userDetails.getUsername())
				.withClaim("Manager", manager != null ? manager.getName() : "")
				.withClaim("Manager Id", manager != null ? manager.getId() : null)
				.withArrayClaim("Managed employees", managedArray)
				.withArrayClaim("auth", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(config.getSecurityConf().getExpiryinterval())))
				.withIssuer(config.getSecurityConf().getIssuer())
				.sign(Algorithm.HMAC256(config.getSecurityConf().getSecret()));

	}

	public UserDetails parseJWT(String jwtToken) {

		DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256("secret"))
				.withIssuer(config.getSecurityConf().getIssuer())
				.build()
				.verify(jwtToken);

		return new User(decodedJwt.getSubject(), "dummy", decodedJwt.getClaim("auth").asList(String.class).stream().map(SimpleGrantedAuthority::new).toList());
	}

	private List<NameAndId> getManagedEmployees(long employeeId)
	{
		return employeeRepository.getManagedEmployees(employeeId);
	}

}
