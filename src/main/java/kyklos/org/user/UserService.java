package kyklos.org.user;

import kyklos.org.authority.AuthorityRepository;
import kyklos.org.role.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Log4j2
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepo userRepository;

    String userText = "User with ID ";

    public UserService(UserRepo usersRepository) {

        log.info("Create repository for communication");
        this.userRepository = usersRepository;
    }

    // endpoint 1
    public void postUser(User user) throws InstanceAlreadyExistsException {
        if (!(userRepository.existsById(user.getId()))) {
            log.info("User was added");
            userRepository.save(user);
        } else {
            log.warn("User already exists");
            throw new InstanceAlreadyExistsException(userText + user.getId() + " already exist");
        }
    }

    //endpoint 2
    public void putUser(User user) {
        log.info("User was updatet");
        userRepository.save(user);
    }

    //endpoint 3
    public void deleteUser(Integer id) throws InstanceNotFoundException {
        userRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException(userText + id + " doesn't exist"));
        log.info(userText + id + " was deleted");
        userRepository.deleteById(id);

    }

    //endpoint 4
    public User findById(Integer id) throws InstanceNotFoundException {
        log.info(userText + id + " was found");
        return userRepository.findById(id).orElseThrow(() -> new InstanceNotFoundException(userText + id + " doesn't exist"));

    }

    //endpoint 5
    public List<User> findAll() {
        log.info("All users are displayed");
        return userRepository.findAll();

    }

  @Override
//    This method is used for security authentication, use caution when changing this
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
//          Construct a valid set of Authorities (needs to implement Granted Authorities)
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
            user.getRole().getAuthorities().forEach(authority ->
                    authorities.add(new SimpleGrantedAuthority(authority.getName())));
//            return a spring internal user object that contains authorities and roles
            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        }

    }
}

