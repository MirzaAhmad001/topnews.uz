package dasturlash.uz.services;

import dasturlash.uz.dto.JwtDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.entities.EmailHistoryEntity;
import dasturlash.uz.entities.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.repository.EmailHistoryRepository;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.repository.ProfileRoleRepository;
import dasturlash.uz.util.JwtUtil;
import dasturlash.uz.util.RandomUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ProfileRoleService profileRoleService;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    private SmsSendService smsSendService;
    @Autowired
    private SmsHistoryService smsHistoryService;

    public String registration(RegistrationDTO dto) {

        Optional<ProfileEntity> existOptional = profileRepository.findByUsernameAndVisibleIsTrue(dto.getUsername());
        if (existOptional.isPresent()) {
            ProfileEntity existsProfile = existOptional.get();
            if (existsProfile.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                profileRoleRepository.deleteAllByProfileId(existsProfile.getId());
                profileRepository.deleteById(existsProfile.getId()); // delete
            } else {
                throw new AppBadException("Username already exists");
            }
        }
        // create profile
        ProfileEntity profile = new ProfileEntity();
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setUsername(dto.getUsername());
        profile.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        profile.setVisible(true);
        profile.setCreatedDate(LocalDateTime.now());
        profile.setStatus(ProfileStatus.NOT_ACTIVE);
        profileRepository.save(profile);
        // create profile roles
        profileRoleService.create(profile.getId(), ProfileRole.ROLE_USER);
        // send verification code
        //emailSenderService.sendRegistrationStyledEmail(profile.getUsername());
        smsSendService.sendRegistrationSMS(dto.getUsername());
        return "Tastiqlash kodi ketdi mazgi qara.";
    }

    public String regEmailVerification(String token) {
        JwtDTO jwtDTO = null;
        try {
            jwtDTO = JwtUtil.decodeRegistrationToken(token);
        } catch (ExpiredJwtException e) {
            throw new AppBadException("JWT Expired");
        } catch (JwtException e) {
            throw new AppBadException("JWT Not Valid");
        }
        String username = jwtDTO.getUsername();

        Optional<ProfileEntity> existOptional = profileRepository.findByUsernameAndVisibleIsTrue(username);
        if (existOptional.isEmpty()) {
            throw new AppBadException("Username not found");
        }
        ProfileEntity profile = existOptional.get();
        if (!profile.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            throw new AppBadException("Username int wrong status");
        }
        // check fo sms code and time
        if (emailHistoryService.isSmsSendToAccount(username, jwtDTO.getCode())) {
            profile.setStatus(ProfileStatus.ACTIVE);
            profileRepository.save(profile);
            return "Verification successfully completed";
        }

        throw new AppBadException("Not completed");
    }

    public String regSmsVerification(String token) {
        JwtDTO jwtDTO = null;
        try {
            jwtDTO = JwtUtil.decodeRegistrationToken(token);
        } catch (ExpiredJwtException e) {
            throw new AppBadException("JWT Expired");
        } catch (JwtException e) {
            throw new AppBadException("JWT Not Valid");
        }
        String username = jwtDTO.getUsername();

        Optional<ProfileEntity> existOptional = profileRepository.findByUsernameAndVisibleIsTrue(username);
        if (existOptional.isEmpty()) {
            throw new AppBadException("Username not found");
        }
        ProfileEntity profile = existOptional.get();

        if (!profile.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            throw new AppBadException("Username int wrong status");
        }

        if (profile.getAttemptCount() < 3) {
            profile.setAttemptCount(profile.getAttemptCount() + 1);
            // check fo sms code and time
            if (smsHistoryService.isSmsSendToAccount(username, jwtDTO.getCode())) {
                profile.setStatus(ProfileStatus.ACTIVE);
                profileRepository.save(profile);
                return "Verification successfully completed";
            }
        }

        throw new AppBadException("Not completed");
    }


    public String login(String username, String password) {
        Optional<ProfileEntity> existOptional = profileRepository.findByUsernameAndVisibleIsTrue(username);
        if (existOptional.isEmpty()) {
            throw new AppBadException("Username not found");
        }
        if (existOptional.get().getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            profileRoleRepository.deleteAllByProfileId(existOptional.get().getId());
            profileRepository.deleteById(existOptional.get().getId());
            throw new AppBadException("You haven't verified your account");
        }
        if (!bCryptPasswordEncoder.matches(password, existOptional.get().getPassword())) {
            throw new AppBadException("Something went wrong");
        }
        ProfileEntity profile = existOptional.get();
        profileRepository.save(profile);
        return "Login successful";
    }

}
