/*
 * Copyright 2002-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.test.context.bean.override.mockito;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.example.ExampleService;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link MockitoBean @MockitoBean} where duplicate mocks
 * are created for the same nonexistent type.
 *
 * @author Sam Brannen
 * @since 6.2.1
 * @see <a href="https://github.com/spring-projects/spring-framework/issues/34025">gh-34025</a>
 * @see MockitoSpyBeanDuplicateTypeIntegrationTests
 * @see MockitoSpyBeanDuplicateTypeAndNameIntegrationTests
 */
@SpringJUnitConfig
public class MockitoBeanDuplicateTypeIntegrationTests {

	@MockitoBean
	ExampleService service1;

	@MockitoBean
	ExampleService service2;

	@Autowired
	List<ExampleService> services;


	@Test
	void duplicateMocksShouldHaveBeenCreated() {
		assertThat(service1).isNotSameAs(service2);
		assertThat(services).hasSize(2);
	}

}