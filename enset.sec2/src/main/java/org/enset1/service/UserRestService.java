package org.enset1.service;

import java.util.List;

import org.enset1.dao.RoleRepository;
import org.enset1.dao.UserRepository;
import org.enset1.entities.Role;
import org.enset1.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured(value= {"ROLE_ADMIN"})//toutes les methodes de ce service ne doivent etre accessible qu'a l'administrateur
public class UserRestService {
	
	
	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@RequestMapping(value="/addUser")
	public User save(User u) {
		return userRepository.save(u);
	}
	
	@RequestMapping(value="/findUsers")
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/addRole")
	public Role saveRole(Role r) {
		return roleRepository.save(r);
	}

	@RequestMapping(value="/findRoles")
	public List<Role> findRoles(){
		return roleRepository.findAll();
	}
	
	@RequestMapping(value="/addRoleToUser")
	public User addRoleToUSer(String username,String role) {
		
		User u = userRepository.findById(username).orElse(null);//ici il ne voulait pas de findOne: Pour cela les versions spring de série 1.4.5 et quelque autre de suite sont des version qui peuvent l' utiliser findOne() sans aucun souci par contre les nouvelles versions ne l'accepte pas. Et pour cela j'utilise findByid(id).orElse(null);
		Role r = roleRepository.findById(role).orElse(null);//ici il ne voulait pas de findOne: Pour cela les versions spring de série 1.4.5 et quelque autre de suite sont des version qui peuvent l' utiliser findOne() sans aucun souci par contre les nouvelles versions ne l'accepte pas. Et pour cela j'utilise findByid(id).orElse(null);
		
		u.getRoles().add(r);
		userRepository.save(u);
		
		
		return u;
		
		
		
	}
	
	
	
	
	
	
	

}
