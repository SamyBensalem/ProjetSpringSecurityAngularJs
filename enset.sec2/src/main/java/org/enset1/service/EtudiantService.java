package org.enset1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.enset1.dao.EtudiantRepository;
import org.enset1.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtudiantService {
	
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	
//	@Secured(value= {"ROLE_ADMIN","ROLE_SCOLARITE"})
//	@RequestMapping(path = "/saveEtudiant",method = RequestMethod.GET)
//	public Etudiant savetEtudiant(Etudiant e){
//		return etudiantRepository.save(e);
//		
//	}
	
//	@Secured(value= {"ROLE_ADMIN","ROLE_SCOLARITE"})
//	@RequestMapping(path = "/etudiants",method = RequestMethod.POST)
//	public Etudiant savetEtudiant(@RequestBody @Valid Etudiant e,BindingResult bindingResult){     //@requestBody indique que Etudiant sera recu par la methode au format JSon  @Valid vient de spring et Binding result gere les messages d'erreur
//		return etudiantRepository.save(e);
//		
//	}
	
	@Secured(value= {"ROLE_ADMIN","ROLE_SCOLARITE"})
	@RequestMapping(path = "/etudiants",method = RequestMethod.POST)
	public Object savetEtudiant(@RequestBody @Valid Etudiant e,BindingResult bindingResult){     // on passe a Object en retour (rcommandation de Youssfi)
	    if(bindingResult.hasErrors()) {  
	    	//si on travaille MVC cote serveur pas de problemes car il suffit d'utiliser return vers la vue et la vue recupere l'objet bindingResult et ensuite affiche les messages
	    	Map<String,Object> errors = new HashMap<>();
	    	errors.put("errors", true);
	    	for(FieldError fe:bindingResult.getFieldErrors()) {
	    		errors.put(fe.getCode(), fe.getDefaultMessage());
	    	}
	    	return errors;										//ici on ne passe pas par une exception, meme les messages d'erreurs sont des resultats
	    	                                                    //la methode savetEtudiant retourne un objet Json, la methode effectue un travail et renvoie un resultat, meme les erreurs sont des resultats (voila pourquoi on a pas a passer des exceptions).
	    	    	                                            //il y a deux scenarios: soit on travaille avec des exceptions personnalisées soit on travaille avec des resultats
	    }
			return etudiantRepository.save(e);
		
	}
	
	
	@Secured(value= {"ROLE_ADMIN","ROLE_SCOLARITE","ROLE_PROF","ROLE_ETUDIANT"})
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/etudiants")
	public Page<Etudiant> listEtudiants(int page,int size){
		return etudiantRepository.findAll(new PageRequest(page,size));
	}
	
	
	@RequestMapping(value="/getLogedUser")
	public Map<String,Object>getLogedUser(HttpServletRequest httpServletRequest){
		
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext securityContext =  (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT"); //on recupere le contexte de securité de spring
		String username = securityContext.getAuthentication().getName();
		List<String> roles =  new ArrayList<>();
		for(GrantedAuthority ga : securityContext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		
		
		Map<String,Object> params = new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
		
	}

	
	
	
	
	
}
