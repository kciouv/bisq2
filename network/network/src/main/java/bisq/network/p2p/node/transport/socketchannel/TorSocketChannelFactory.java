/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.network.p2p.node.transport.socketchannel;

import bisq.network.p2p.vo.Address;
import bisq.tor.nio.TorSocketChannel;
import bisq.tor.nio.TorSocksConnectionData;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TorSocketChannelFactory implements SocketChannelFactory {
    private final int torSocksProxyPort;

    public TorSocketChannelFactory(int torSocksProxyPort) {
        this.torSocksProxyPort = torSocksProxyPort;
    }

    @Override
    public SocketChannel getSocketChannelForAddress(Address address) throws IOException {
        try (TorSocketChannel torSocketChannel = TorSocketChannel.open()) {
            var connectionData = new TorSocksConnectionData(torSocksProxyPort, address.getHost(), address.getPort());
            return torSocketChannel.connect(connectionData, ByteBuffer.allocate(1024));
        }
    }
}

