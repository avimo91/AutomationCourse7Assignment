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

import org.junit.jupiter.api.Test;

public class TestErrorChecking extends InternalSelenseTestBase {
  @Test
  void testErrorChecking() {
    selenium.open("test_click_page1.html");
    // These tests should all fail, as they are checking the error checking commands.
    try {
      assertEquals(selenium.getText("link"), "Click here for next page");
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      System.out.println("foo");
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      assertEquals(selenium.getText("link"), "foo");
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      assertEquals(selenium.getText("link"), "Click here for next page");
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      assertEquals(selenium.getText("link"), "foo");
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      assertEquals(selenium.getText("notAlink"), "foo");
      fail("expected failure");
    } catch (Throwable e) {
    }
  }
}
