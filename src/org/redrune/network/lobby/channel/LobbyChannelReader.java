package org.redrune.network.lobby.channel;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.redrune.game.node.entity.player.Player;
import org.redrune.network.NetworkConstants;
import org.redrune.network.lobby.LobbyNetwork;
import org.redrune.network.world.WorldSession;
import org.redrune.network.world.packet.Packet;

import static org.redrune.network.NetworkConstants.IGNORED_EXCEPTIONS;

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since 7/19/2017
 */
public class LobbyChannelReader extends SimpleChannelInboundHandler<Packet> {
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
		WorldSession session = (WorldSession) ctx.channel().attr(NetworkConstants.SESSION_KEY).get();
		Preconditions.checkArgument(session != null, "No session set for channel.");
		// the player of the session
		final Player player = session.getPlayer();
		// make sure we have a player
		if (player == null) {
			return;
		}
		LobbyNetwork.PACKET_REPOSITORY.handlePacket(player, msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
		if (IGNORED_EXCEPTIONS.stream().noneMatch($it -> Objects.equal($it, e.getMessage()))) {
			e.printStackTrace();
		}
		ctx.channel().close();
	}
}