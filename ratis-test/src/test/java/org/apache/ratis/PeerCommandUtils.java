package org.apache.ratis;

import org.apache.ratis.protocol.RaftPeer;

/**
 * Command utilities for testing peer operations.
 */
public class PeerCommandUtils {

    /**
     * Execute a basic peer command.
     */
    public static void executeBasicCommand(RaftPeer peer) {
        // Basic command execution
        System.out.println("Executing command for peer: " + peer.getId());
    }
}