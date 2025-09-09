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
     * UNIQUE TO add-minimal-peer-test: Creates a peer with custom configuration.
     * This function should be KEPT during cherry-pick.
     */
    public static RaftPeer createMinimalPeerWithConfig(String id, String address, boolean prioritized) {
        RaftPeerId peerId = RaftPeerId.valueOf(id);
        
        return RaftPeer.newBuilder()
            .setId(peerId)
            .setAddress(address)
            .setPriority(prioritized ? 1 : 0)
            .build();
    }

    /**
     * UNIQUE TO add-minimal-peer-test: Get peer summary for minimal testing.
     * This function should be KEPT during cherry-pick.
     */
    public static String getMinimalPeerSummary(RaftPeer peer) {
        if (!validateMinimalPeer(peer)) {
            return "Invalid peer";
        }
        return String.format("Peer[id=%s, address=%s]", 
                           peer.getId(), peer.getAddress());
    }

    /**
     * Test method for minimal peer creation.
     */
    @Test
    public void testMinimalPeerCreation() {
        RaftPeer peer = createMinimalTestPeer("test-peer-1");
        
        assert validateMinimalPeer(peer);
    }

    /**
     * UNIQUE TO add-minimal-peer-test: Test custom peer configuration.
     * This test should be KEPT during cherry-pick.
     */
    @Test
    public void testMinimalPeerWithConfig() {
        RaftPeer peer = createMinimalPeerWithConfig("test-peer-config", "localhost:9090", true);
        assert validateMinimalPeer(peer);
        assert peer.getPriority() == 1;
    }
}