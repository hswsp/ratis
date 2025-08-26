/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ratis.examples.common;

import java.util.Collection;
import java.util.Collections;
import org.apache.ratis.protocol.RaftPeer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class TestSubCommand {

  public static Collection<String> data() {
    return Collections.singleton("127.0.0.1:6667");
  }

  @ParameterizedTest
  @MethodSource("data")
  public void testParsePeers(String peers) {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> SubCommandBase.parsePeers(peers));
  }

  @Test
  public void testParseValidPeers() {
    // Test valid peer format
    String validPeers = "peer1:localhost:9999:10000:10001:10002,peer2:127.0.0.1:9998:10003:10004:10005";
    RaftPeer[] peers = SubCommandBase.parsePeers(validPeers);
    
    Assertions.assertEquals(2, peers.length);
    
    // Test first peer
    RaftPeer peer1 = peers[0];
    Assertions.assertEquals("peer1", peer1.getId().toString());
    Assertions.assertEquals("localhost:9999", peer1.getAddress());
    Assertions.assertEquals("localhost:10000", peer1.getDataStreamAddress());
    Assertions.assertEquals("localhost:10001", peer1.getClientAddress());
    Assertions.assertEquals("localhost:10002", peer1.getAdminAddress());
    
    // Test second peer
    RaftPeer peer2 = peers[1];
    Assertions.assertEquals("peer2", peer2.getId().toString());
    Assertions.assertEquals("127.0.0.1:9998", peer2.getAddress());
    Assertions.assertEquals("127.0.0.1:10003", peer2.getDataStreamAddress());
    Assertions.assertEquals("127.0.0.1:10004", peer2.getClientAddress());
    Assertions.assertEquals("127.0.0.1:10005", peer2.getAdminAddress());
  }

  @Test
  public void testParseMinimalValidPeers() {
    // Test minimal valid peer format (name:host:port)
    String minimalPeers = "peer1:localhost:9999";
    RaftPeer[] peers = SubCommandBase.parsePeers(minimalPeers);
    
    Assertions.assertEquals(1, peers.length);
    
    RaftPeer peer = peers[0];
    Assertions.assertEquals("peer1", peer.getId().toString());
    Assertions.assertEquals("localhost:9999", peer.getAddress());
  }

}
