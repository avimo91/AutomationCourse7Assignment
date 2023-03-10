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

package org.openqa.selenium.remote.session;

import org.openqa.selenium.Proxy;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Collections.singleton;

public class ProxyTransform implements CapabilityTransform {

  @Override
  public Collection<Map.Entry<String, Object>> apply(Map.Entry<String, Object> entry) {
    if (!"proxy".equals(entry.getKey())) {
      return singleton(entry);
    }

    Object rawProxy = entry.getValue();
    Map<String, Object> proxy;
    if (rawProxy instanceof Proxy) {
      proxy = new TreeMap<>(((Proxy) rawProxy).toJson());
    } else {
      //noinspection unchecked
      proxy = new TreeMap<>((Map<String, Object>) rawProxy);
    }
    if (proxy.containsKey("proxyType")) {
      proxy.put("proxyType", String.valueOf(proxy.get("proxyType")).toLowerCase());
    }
    return singleton(new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), proxy));
  }
}
