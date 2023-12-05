package hu.cubix.hr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.cubix.hr.dto.LoginDto;
import hu.cubix.hr.model.HrUser;
import hu.cubix.hr.service.JwtService;

@RestController
public class JwtLoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/api/login")
	public String login(@RequestBody LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		return jwtService.createJwt((HrUser) authentication.getPrincipal());
	}



}
