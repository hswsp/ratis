package org.apache.ratis;

import org.apache.ratis.protocol.RaftPeer;
import org.apache.ratis.protocol.RaftPeerId;
import org.junit.jupiter.api.Test;

/**
 * Utility class for minimal peer testing functionality.
 */
public class PeerTestUtils {

    /**
     * Creates a minimal peer for testing purposes.
     */
    public static RaftPeer createMinimalTestPeer(String id) {
        RaftPeerId peerId = RaftPeerId.valueOf(id);
        return RaftPeer.newBuilder()
            .setId(peerId)
            .setAddress("localhost:8080")
            .build();
    }

    /**
     * Validates that a peer has minimal required properties.
     */
    public static boolean validateMinimalPeer(RaftPeer peer) {
        return peer != null && 
               peer.getId() != null && 
               peer.getAddress() != null;
    }

    /**
     * Test method for minimal peer creation.
     */
    @Test
    public void testMinimalPeerCreation() {
        RaftPeer peer = createMinimalTestPeer("test-peer-1");
        assert validateMinimalPeer(peer);
    }
}