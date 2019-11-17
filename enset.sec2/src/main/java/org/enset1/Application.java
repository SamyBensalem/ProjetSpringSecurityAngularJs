package org.enset1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.enset1.dao.EtudiantRepository;
import org.enset1.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ParseException{
		ApplicationContext context= SpringApplication.run(Application.class, args);
		EtudiantRepository etudiantRepository  = context.getBean(EtudiantRepository.class);

		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/*		etudiantRepository.save(new Etudiant("safouane","safouane",df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("rifaat","rifaat",df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("sara","sara",df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("hamza","hamza",df.parse("1983-11-12")));
		etudiantRepository.save(new Etudiant("driss","driss",df.parse("1983-11-12")));
		
*/		
		etudiantRepository.findAll().forEach(e->System.out.println(e.toString()));
	}

}
