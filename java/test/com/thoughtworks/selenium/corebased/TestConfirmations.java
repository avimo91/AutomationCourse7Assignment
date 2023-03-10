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

public class TestConfirmations extends InternalSelenseTestBase {
  @Test
  void testConfirmations() throws Exception {
    selenium.open("test_confirm.html");
    selenium.chooseCancelOnNextConfirmation();
    selenium.click("confirmAndLeave");
    verifyTrue(selenium.isConfirmationPresent());
    for (int second = 0;; second++) {
      if (second >= 60) fail("timeout");
      try {
        if (selenium.isConfirmationPresent()) break;
      } catch (Exception e) {
      }
      Thread.sleep(1000);
    }

    assertTrue(selenium.isConfirmationPresent());
    verifyEquals(selenium.getConfirmation(), "You are about to go to a dummy page.");
    verifyEquals(selenium.getTitle(), "Test Confirm");
    selenium.click("confirmAndLeave");
    selenium.waitForPageToLoad("30000");
    verifyTrue(selenium.getConfirmation().matches("^[\\s\\S]*dummy page[\\s\\S]*$"));
    verifyEquals(selenium.getTitle(), "Dummy Page");
    selenium.open("test_confirm.html");
    verifyEquals(selenium.getTitle(), "Test Confirm");
    selenium.chooseCancelOnNextConfirmation();
    selenium.chooseOkOnNextConfirmation();
    selenium.click("confirmAndLeave");
    selenium.waitForPageToLoad("30000");
    verifyTrue(selenium.getConfirmation().matches("^[\\s\\S]*dummy page[\\s\\S]*$"));
    verifyEquals(selenium.getTitle(), "Dummy Page");
    selenium.open("test_confirm.html");
    try {
      assertEquals(selenium.getConfirmation(), "This should fail - there are no confirmations");
      fail("expected failure");
    } catch (Throwable e) {
    }
    selenium.click("confirmAndLeave");
    selenium.waitForPageToLoad("30000");
    try {
      assertEquals(selenium.getConfirmation(), "this should fail - wrong confirmation");
      fail("expected failure");
    } catch (Throwable e) {
    }
    selenium.open("test_confirm.html");
    selenium.click("confirmAndLeave");
    selenium.waitForPageToLoad("30000");
    try {
      selenium.open("test_confirm.html");
      fail("expected failure");
    } catch (Throwable e) {
    }
  }
}
