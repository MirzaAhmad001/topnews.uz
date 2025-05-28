package dasturlash.uz.services;

import dasturlash.uz.entities.ProfileEntity;
import dasturlash.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BCryptHandler {

    @Autowired
    private static ProfileRepository profileRepository;

    public static void encodePassword(String password, Integer profileId) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        Optional<ProfileEntity> optional = profileRepository.findById(profileId);
        if (optional.isPresent()) {
            ProfileEntity profileEntity = optional.get();
            profileEntity.setPassword(encodedPassword);
        }
    }
}
