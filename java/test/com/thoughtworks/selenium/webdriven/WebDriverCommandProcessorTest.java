// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.thoughtworks.selenium.webdriven;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WebDriverCommandProcessorTest {
  @Test
  void testDriverNeedNotImplementHasCapabilities() {
    WebDriver driver = mock(WebDriver.class,
                            withSettings().extraInterfaces(JavascriptExecutor.class));

    try {
      new WebDriverCommandProcessor("http://www.example.com", driver);
    } catch (ClassCastException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testRequiresAJavascriptEnabledDriver() {
    WebDriver driver = mock(WebDriver.class);

    try {
      new WebDriverCommandProcessor("http://example.com", driver);
      fail("Was not expected to succeed");
    } catch (IllegalStateException expected) {
    }
  }
}
