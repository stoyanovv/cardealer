package com.cardealer.cars.service.impl;

import com.cardealer.cars.common.OutputJson;
import com.cardealer.cars.config.security.email.ConfirmationTokenService;
import com.cardealer.cars.config.security.email.EmailService;
import com.cardealer.cars.model.entity.*;
import com.cardealer.cars.model.entity.enums.Role;
import com.cardealer.cars.model.service.UserLoginServiceModel;
import com.cardealer.cars.model.service.UserRegisterServiceModel;
import com.cardealer.cars.model.view.profile.MyProfileView;
import com.cardealer.cars.repository.UserRepository;
import com.cardealer.cars.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Component
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final CityService cityService;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService,
                           CityService cityService, ModelMapper modelMapper,
                           UserRoleService userRoleService, PasswordEncoder passwordEncoder,
                           ConfirmationTokenService confirmationTokenService, EmailService emailService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.emailService = emailService;
    }

    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel) {
        User user = new User();
        UserDetails userDetails = modelMapper.map(userRegisterServiceModel, UserDetails.class);
        Set<UserRole> userRoles = new HashSet<>();
        if(userRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setRole(Role.ADMIN);
            userRoleService.save(adminRole);
            UserRole userRole = new UserRole();
            userRole.setRole(Role.USER);
            userRoleService.save(userRole);
            userRoles.add(adminRole);
            userRoles.add(userRole);
            user.setRoles(userRoles);
        }else {
            UserRole userRole = new UserRole();
            userRole.setRole(Role.USER);
            userRoleService.save(userRole);
            userRoles.add(userRole);
            user.setRoles(userRoles);
        }
        user.setCity(cityService.findCityByName(userRegisterServiceModel.getCity()));
        userRepository.save(user);
        userDetails.setPlayer(user);
        userDetailsService.save(userDetails);
        String emailToken = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(emailToken, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
                userDetails);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String link = "http://localhost:3000/confirm/" + emailToken;
//        emailService.send(userDetails.getEmail(), buildEmail((userDetails.getName() + " " + userDetails.getLastName()), link));
        TimerTask task = new TimerTask() {
            public void run() {
                if (!userDetails.isEmailConfirmed()) {
                    Set<UserRole> userRoles1 = user.getRoles();
                    user.setRoles(null);
                    userRepository.save(user);
                    for (UserRole role : userRoles1) {
                        userRoleService.delete(role);
                    }
                    confirmationTokenService.delete(confirmationToken);
                    userDetailsService.delete(userDetails);
                    userRepository.delete(user);
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 900000);
    }

    @Override
    public UserLoginServiceModel findByEmailAndPassword(String email, String password) {
        UserDetails userDetails = userDetailsService.findByEmail(email).orElse(null);
        if (userDetails == null){
        return null;
        }
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
         return modelMapper.map(userDetailsService.findByEmail(email).orElse(null), UserLoginServiceModel.class) ;
        }
        return null;
    }

    @Override
    public MyProfileView getMyProfile(Long playerDetailsId) {
        UserDetails userDetails = userDetailsService.getById(playerDetailsId);
        MyProfileView myProfileView = modelMapper.map(userDetails, MyProfileView.class);
        myProfileView.setAge(Period.between(userDetails.getBirthdate(), LocalDate.now()).getYears());
        return myProfileView;

    }


    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public OutputJson confirmToken(String token) {
        OutputJson outputJson = new OutputJson();
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElse(null);
        if (confirmationToken == null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Токънът не съвпада");
            return outputJson;
        }
        if (confirmationToken.getConfirmedAt() != null) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Имейл адреса вече е потвърден");
            return outputJson;
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            outputJson.setSuccess(false);
            outputJson.setMessage("Токъна е изтекъл");
            return outputJson;
        }

        confirmationTokenService.setConfirmedAt(token);
        userDetailsService.confirmEmail(
                confirmationToken.getUserDetails().getEmail());
        outputJson.setSuccess(true);
        outputJson.setMessage("Успешно завършихте вашата регистрация!");
        return outputJson;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Потвърди имейла</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Здравейте " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Благодарим ви за регистрацията. Моля кликнете на линка по-долу, за да активирате вашия акаунт: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Активирай сега</a> </p></blockquote>\n Линкът ще изтече след 15 минути. <p>Хубав ден!</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}

