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

public class TestFailingAssert extends InternalSelenseTestBase {
  @Test
  void testFailingAssert() {
    selenium.open("test_verifications.html");
    try {
      assertEquals(selenium.getValue("theText"), "not the text value");
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      assertNotEquals("the text value", selenium.getValue("theText"));
      fail("expected failure");
    } catch (Throwable e) {
    }
    try {
      assertEquals(selenium.getValue("theTable"), "x");
      fail("expected failure");
    } catch (Throwable e) {
    }
  }
}
