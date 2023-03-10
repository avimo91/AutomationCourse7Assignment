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

package com.thoughtworks.selenium.corebased;

import com.thoughtworks.selenium.InternalSelenseTestBase;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("WebDriverBackedSelenium uses webdriver for xpath evaluation")
public class TestUseXpathLibrary extends InternalSelenseTestBase {
  @Test
  void testUseXpathLibrary() {
    selenium.useXpathLibrary("ajaxslt");
    assertEquals(selenium.getEval("this.browserbot.xpathEvaluator.getCurrentEngine()"), "ajaxslt");
    assertEquals(selenium.getXpathCount("//"), "1");
    selenium.useXpathLibrary("javascript-xpath");
    assertEquals(selenium.getEval("this.browserbot.xpathEvaluator.getCurrentEngine()"),
        "javascript-xpath");
    assertEquals(selenium.getXpathCount("//"), "1");
    selenium.useXpathLibrary("");
    assertEquals(selenium.getEval("this.browserbot.xpathEvaluator.getCurrentEngine()"),
        "javascript-xpath");
    selenium.useXpathLibrary("nonExisting-xpath-library");
    assertEquals(selenium.getEval("this.browserbot.xpathEvaluator.getCurrentEngine()"),
        "javascript-xpath");
    selenium.useXpathLibrary("default");
    assertEquals(selenium.getEval("this.browserbot.xpathEvaluator.getCurrentEngine()"), "ajaxslt");
  }
}
