package csw.t1.csw.service;

import csw.t1.csw.dto.user.RequestUserDTO;
import csw.t1.csw.dto.user.ResponseUserDTO;
import csw.t1.csw.entities.User;
import csw.t1.csw.repositories.TenantRepository;
import csw.t1.csw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private TenantRepository tenantRepository;

    public ResponseEntity<ResponseUserDTO> createUser(RequestUserDTO dto) {
        var tenant = tenantRepository.findById(dto.tenantId())
                .orElse(null);

        if (tenant != null && !repository.existsByEmail(dto.email())) {
            var user = repository.save(new User(dto, tenant));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseUserDTO.builder()
                            .userId(user.getUserId())
                            .tenantId(user.getTenant().getTenantId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .firebaseToken(user.getFirebaseToken())
                            .build()
                    );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);

        }
    }
}