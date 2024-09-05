package backend.server.PostWave.service;

import backend.server.PostWave.dto.AuthenticationRequest;
import backend.server.PostWave.dto.AuthenticationResponse;
import backend.server.PostWave.dto.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IAuthService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse login(AuthenticationRequest request);
}
