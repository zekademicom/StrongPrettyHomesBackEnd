package com.zekademi.strongprettyhomes.service;

import com.zekademi.strongprettyhomes.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final static String USER_NOT_FOUND_MSG = "user with id %d not found";

    public List<ProjectUser> fetchAllUsers(){
        return userRepository.findAllBy();
    }
    public UserDTO findById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));
        UserDTO userDTO = new UserDTO();
        userDTO.setRole(user.getRole());
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(),
                user.getAddress(), user.getZipCode(), user.getBuiltIn(), userDTO.getRole());
    }

    public void register(User user) throws BadRequestException{

        if(userRepository.existsByEmail(user.getEmail())){

            throw new ConflictException("Error: Email is already in use!");

        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPasword(encodedPassword);
        Role customerRole = roleRepository.findByName(UserRole.ROLE_CUSTOMER).
                orElseThrow(()->new ResourceNotFoundException("Error: Role is not found."));

        user.setRole(customerRole);
        userRepository.save(user);
    }



    public void login(String email, String password) throws AuthException{
        try{
            Optional<User> user = userRepository.findByEmail(email);
            if(!BCrypt.checkpw(password,user.get().getPassword()));
            throw new AuthException("invalid credentials");
        }
        catch(Exception e){
            throw new AuthException("invalid credentials");
        }
    }

    public void updateUser(Long id, UserDTO userDTO) throws BadRequestException {
        boolean emailExists = userRepository.existsByEmail(userDTO.getEmail());
        Optional<User> userDetails = userRepository.findById(id);
        if(userDetails.get().getBuiltIn()) {
            throw new BadRequestException("You dont have permission to update user info!");
        }
        if(emailExists && !userDTO.getEmail().equals(userDetails.get().getEmail())){
            throw new ConflictException("Error: Email is already in use!");
        }
        userRepository.update(id, userDTO.getFirstName(), userDTO.getLastName(), userDTO.getPhoneNumber(),
                userDTO.getEmail(), userDTO.getAddress(), userDTO.getZipCode());
    }
    public void updatePassword(Long id, String newPassword, String oldPassword) throws BadRequestException {
        Optional<User> user = userRepository.findById(id);
        if (user.get().getBuiltIn()) {
            throw new BadRequestException("You dont have permission to update password!");
        }
        if (!BCrypt.hashpw(oldPassword, user.get().getPassword()).equals(user.get().getPassword()))
            throw new BadRequestException("password does not match");
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.get().setPassword(hashedPassword);
        userRepository.save(user.get());
    }
    public void updateUserAuth(Long id, AdminDTO adminDTO) throws BadRequestException {

        boolean emailExist = userRepository.existsByEmail(adminDTO.getEmail());     // database den email kontrolu yapıyoruz.

        Optional<User> userDetails = userRepository.findById(id);       // eski bilgilerine ulaştık.

        if (userDetails.get().getBuiltIn()){
            throw new BadRequestException("You dont have permission to update user info!");
        }
        adminDTO.setBuiltIn(false);

        if (emailExist && !adminDTO.getEmail().equals(userDetails.get().getEmail())){
            throw new ConflictException("Error : Email is already in use!");
        }

        if (adminDTO.getPassword() == null) {
            adminDTO.setPassword(userDetails.get().getPassword());
        }
        else {
            String encodedPassword = passwordEncoder.encode(adminDTO.getPassword());
            adminDTO.setPassword(encodedPassword);
        }

        String userRole = adminDTO.getRole();
        Role role = addRole(userRole);

        User user = new User(id, adminDTO.getFirstName(), adminDTO.getLastName(), adminDTO.getPassword(),
                adminDTO.getPhoneNumber(), adminDTO.getEmail(), adminDTO.getAddress(), adminDTO.getZipCode(), adminDTO.getBuiltIn(), role);

        userRepository.save(user);

    }
    public Role addRole(String userRole) {

        Role role ;

        if (userRole == null ){
            role = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
                    .orElseThrow(()->new RuntimeException("Error : Role is not found."));

        }else {

                switch (userRole){
                    case "Administrator":
                        role = roleRepository.findByName(UserRole.ROLE_ADMIN)
                                .orElseThrow(()->new RuntimeException("Error : Role is not found."));

                        break;

                    default:
                        role = roleRepository.findByName(UserRole.ROLE_CUSTOMER)
                                .orElseThrow(()->new RuntimeException("Error : Role is not found."));
                        break;
                }

        }
        return role;
    }

    public void removeById(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, id)));

        if (user.getBuiltIn())
            throw new BadRequestException("You dont have permission to delete user!");

        userRepository.deleteById(id);
    }



}
