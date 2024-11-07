package com.gmail.drack.service;

import com.gmail.drack.broker.producer.*;
import com.gmail.drack.commons.constants.PathConstants;
import com.gmail.drack.commons.mapper.BasicMapper;
import com.gmail.drack.repository.*;
import com.gmail.drack.commons.security.JwtProvider;
import com.gmail.drack.commons.utils.TestConstants;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbcstractServiceTest {
    @MockBean
    UserRepository userRepository;

    @MockBean
    BlockUserRepository blockUserRepository;

    @MockBean
    FollowerUserRepository followerUserRepository;

    @MockBean
    MuteUserRepository muteUserRepository;

    @MockBean
    JwtProvider jwtProvider;

    @MockBean
    SendEmailProducer sendEmailProducer;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    BasicMapper basicMapper;

    static final PageRequest pageable = PageRequest.of(0, 20);
    static final List<Long> ids = List.of(1L,2L,3L);

    @Before
    public void setUp() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(PathConstants.AUTH_USER_ID_HEADER, TestConstants.USER_ID);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
    }
}
