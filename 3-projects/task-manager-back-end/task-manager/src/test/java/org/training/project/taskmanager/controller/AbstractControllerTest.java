package org.training.project.taskmanager.controller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.training.project.taskmanager.TaskManagerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = TaskManagerApplication.class)
@AutoConfigureMockMvc
public abstract class AbstractControllerTest {

  final ResultMatcher ok = status().isOk();
  final ResultMatcher badRequest = status().isBadRequest();
  final ResultMatcher notFound = status().isNotFound();

  String serviceName;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper jsonObjectMapper;

  @Value("${api.baseURL}/${api.version}")
  private String baseURL;

  ResultActions performPost(final Object content) throws Exception {
    return mockMvc.perform(
        post(baseURL + this.serviceName)
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(jsonObjectMapper.writeValueAsString(content))
    );
  }

  ResultActions performPut(final Object content, final Long id) throws Exception {
    return mockMvc.perform(
        put(baseURL + this.serviceName + "/" + id)
            .contentType(APPLICATION_JSON_UTF8_VALUE)
            .content(jsonObjectMapper.writeValueAsString(content))
    );
  }

  ResultActions performDelete(final Long id) throws Exception {
    return mockMvc.perform(
        delete(baseURL + this.serviceName + "/" + id)
    );
  }
}
