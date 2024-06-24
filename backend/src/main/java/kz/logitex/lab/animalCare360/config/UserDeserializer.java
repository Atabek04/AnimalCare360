package kz.logitex.lab.animalCare360.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import kz.logitex.lab.animalCare360.entity.User;
import kz.logitex.lab.animalCare360.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class UserDeserializer extends JsonDeserializer<User> {

    private final UserRepository userRepository;

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Long ownerId = jsonParser.getLongValue();
        return userRepository.findById(ownerId).orElse(null);
    }
}