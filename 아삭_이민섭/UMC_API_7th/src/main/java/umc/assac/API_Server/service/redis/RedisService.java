package umc.assac.API_Server.service.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import umc.assac.API_Server.exception.token.TokenExpiredException;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> template;

    public void setValue(String key, String value) {
        ValueOperations<String, String> values = template.opsForValue();
        values.set(key, value);
    }

    public String getValue(String key) {
        ValueOperations<String, String> values = template.opsForValue();
        return values.get(key);
    }

    public void validateToken(String username, String token) {
        String savedToken = this.getValue(username);
        if(!savedToken.equals(token)) throw new TokenExpiredException();
    }
}
