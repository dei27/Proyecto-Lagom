package com.lagom.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lagom.core.model.Usuario;
import com.lagom.core.repository.UsarioRolRepository;
import com.lagom.core.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
   
    @Autowired
    private UsarioRolRepository usarioRolRepository;
    
    public static Usuario usuario;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    
    	usuario = usuarioRepository.findByEmail(email);
	   
	    if (usuario == null) {
	        throw new UsernameNotFoundException("Usuario no encontrado");
	    }
	    
	    List<String> roles = usarioRolRepository.findNombresByUsuarioId(usuario.getId());
	    
	    List<GrantedAuthority> authorities = new ArrayList<>();
	
	    for (String rol : roles) {
	    	authorities.add(new SimpleGrantedAuthority(rol));
		}
	    
	
	    UserDetails userDetails = new User(usuario.getUsername(), new BCryptPasswordEncoder().encode(usuario.getPass()), authorities);
	    return userDetails;

    }
}
