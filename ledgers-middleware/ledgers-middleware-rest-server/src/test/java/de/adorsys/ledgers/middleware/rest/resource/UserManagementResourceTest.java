package de.adorsys.ledgers.middleware.rest.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import de.adorsys.ledgers.middleware.api.domain.um.UserRoleTO;
import de.adorsys.ledgers.middleware.api.exception.UserNotFoundMiddlewareException;
import de.adorsys.ledgers.middleware.api.service.MiddlewareOnlineBankingService;
import de.adorsys.ledgers.middleware.rest.exception.ExceptionAdvisor;

public class UserManagementResourceTest {
    private static final String LOGIN = "login";
    private static final String PIN = "pin";

    private MockMvc mockMvc;

    @InjectMocks
    private UserManagementResource resource;

    @Mock
    private MiddlewareOnlineBankingService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders
                          .standaloneSetup(resource)
                          .setControllerAdvice(new ExceptionAdvisor())
                          .setMessageConverters(new MappingJackson2HttpMessageConverter())
                          .build();
    }

    @Test
    public void authorise() throws Exception {
        when(userService.authorise(LOGIN, PIN, UserRoleTO.CUSTOMER)).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .post(UserManagementResource.BASE_PATH + "/authorise")
                                                      .param("login", LOGIN)
                                                      .param("pin", PIN)
        )
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.OK.value()))
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                      .andReturn();

        String content = mvcResult.getResponse().getContentAsString();

        assertThat(mvcResult.getResponse().getStatus(), is(200));
        assertThat(content, is(Boolean.FALSE.toString()));

        verify(userService, times(1)).authorise(LOGIN, PIN, UserRoleTO.CUSTOMER);
    }

    @Test
    public void authoriseUserNotFound() throws Exception {
        when(userService.authorise(LOGIN, PIN, UserRoleTO.CUSTOMER)).thenThrow(new UserNotFoundMiddlewareException("User with login="+LOGIN+" not found"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                                                      .post(UserManagementResource.BASE_PATH + "/authorise")
                                                      .param("login", LOGIN)
                                                      .param("pin", PIN)
        )
                                      .andDo(print())
                                      .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                                      .andReturn();

        verify(userService, times(1)).authorise(LOGIN, PIN, UserRoleTO.CUSTOMER);
    }
}