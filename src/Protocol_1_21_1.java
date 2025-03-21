import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Map;
import java.nio.ByteBuffer;
import Serializables.*;
import Serializables.Types.*;
import static Serializables.Types.Tuples.Tuples.*;
public class Protocol_1_21_1 {
	public static class handshaking{
		public static class toServer{
			static class packet_set_protocol extends PacketBase{
				Integer protocolVersion;
				String serverHost;
				Integer serverPort;
				Integer nextState;
				public packet_set_protocol(
					Integer protocolVersion,
					String serverHost,
					Integer serverPort,
					Integer nextState
				){
					super(PacketIDs.handshaking_toServer_packet_set_protocol.getId());
					this.protocolVersion = protocolVersion;
					this.serverHost = serverHost;
					this.serverPort = serverPort;
					this.nextState = nextState;
				}
				public static packet_set_protocol readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer protocolVersion = ProtocolDeserializers.readVarInt(buf);
						String serverHost = ProtocolDeserializers.readString(buf);
						Integer serverPort = ProtocolDeserializers.readU16(buf);
						Integer nextState = ProtocolDeserializers.readVarInt(buf);
						return new packet_set_protocol(
							protocolVersion,
							serverHost,
							serverPort,
							nextState
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_legacy_server_list_ping extends PacketBase{
				Short payload;
				public packet_legacy_server_list_ping(
					Short payload
				){
					super(PacketIDs.handshaking_toServer_packet_legacy_server_list_ping.getId());
					this.payload = payload;
				}
				public static packet_legacy_server_list_ping readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short payload = ProtocolDeserializers.readU8(buf);
						return new packet_legacy_server_list_ping(
							payload
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
	}
	public static class status{
		public static class toClient{
			static class packet_server_info extends PacketBase{
				String response;
				public packet_server_info(
					String response
				){
					super(PacketIDs.status_toClient_packet_server_info.getId());
					this.response = response;
				}
				public static packet_server_info readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String response = ProtocolDeserializers.readString(buf);
						return new packet_server_info(
							response
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_ping extends PacketBase{
				Long time;
				public packet_ping(
					Long time
				){
					super(PacketIDs.status_toClient_packet_ping.getId());
					this.time = time;
				}
				public static packet_ping readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long time = ProtocolDeserializers.readI64(buf);
						return new packet_ping(
							time
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
		public static class toServer{
			static class packet_ping_start extends PacketBase{
				public packet_ping_start(
				){
					super(PacketIDs.status_toServer_packet_ping_start.getId());
				}
				public static packet_ping_start readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_ping_start(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_ping extends PacketBase{
				Long time;
				public packet_ping(
					Long time
				){
					super(PacketIDs.status_toServer_packet_ping.getId());
					this.time = time;
				}
				public static packet_ping readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long time = ProtocolDeserializers.readI64(buf);
						return new packet_ping(
							time
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
	}
	public static class login{
		public static class toClient{
			static class packet_disconnect extends PacketBase{
				String reason;
				public packet_disconnect(
					String reason
				){
					super(PacketIDs.login_toClient_packet_disconnect.getId());
					this.reason = reason;
				}
				public static packet_disconnect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String reason = ProtocolDeserializers.readString(buf);
						return new packet_disconnect(
							reason
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_encryption_begin extends PacketBase{
				String serverId;
				BitSet publicKey;
				BitSet verifyToken;
				Boolean shouldAuthenticate;
				public packet_encryption_begin(
					String serverId,
					BitSet publicKey,
					BitSet verifyToken,
					Boolean shouldAuthenticate
				){
					super(PacketIDs.login_toClient_packet_encryption_begin.getId());
					this.serverId = serverId;
					this.publicKey = publicKey;
					this.verifyToken = verifyToken;
					this.shouldAuthenticate = shouldAuthenticate;
				}
				public static packet_encryption_begin readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String serverId = ProtocolDeserializers.readString(buf);
						BitSet publicKey = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						BitSet verifyToken = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						Boolean shouldAuthenticate = ProtocolDeserializers.readBool(buf);
						return new packet_encryption_begin(
							serverId,
							publicKey,
							verifyToken,
							shouldAuthenticate
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_success extends PacketBase{
				BigInteger uuid;
				String username;
				Tuple3<String, String, String>[] properties;
				Boolean strictErrorHandling;
				public packet_success(
					BigInteger uuid,
					String username,
					Tuple3<String, String, String>[] properties,
					Boolean strictErrorHandling
				){
					super(PacketIDs.login_toClient_packet_success.getId());
					this.uuid = uuid;
					this.username = username;
					this.properties = properties;
					this.strictErrorHandling = strictErrorHandling;
				}
				public static packet_success readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						String username = ProtocolDeserializers.readString(buf);
						Tuple3<String, String, String>[] properties = ProtocolDeserializers.readVarintPrefixedArr(buf, aux1 ->Tuple3.readFrom(buf, aux2 ->ProtocolDeserializers.readString(buf), aux3 ->ProtocolDeserializers.readString(buf), aux4 ->ProtocolDeserializers.readString(buf)), Tuple3[]::new);
						Boolean strictErrorHandling = ProtocolDeserializers.readBool(buf);
						return new packet_success(
							uuid,
							username,
							properties,
							strictErrorHandling
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_compress extends PacketBase{
				Integer threshold;
				public packet_compress(
					Integer threshold
				){
					super(PacketIDs.login_toClient_packet_compress.getId());
					this.threshold = threshold;
				}
				public static packet_compress readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer threshold = ProtocolDeserializers.readVarInt(buf);
						return new packet_compress(
							threshold
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_login_plugin_request extends PacketBase{
				Integer messageId;
				String channel;
				BitSet data;
				public packet_login_plugin_request(
					Integer messageId,
					String channel,
					BitSet data
				){
					super(PacketIDs.login_toClient_packet_login_plugin_request.getId());
					this.messageId = messageId;
					this.channel = channel;
					this.data = data;
				}
				public static packet_login_plugin_request readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer messageId = ProtocolDeserializers.readVarInt(buf);
						String channel = ProtocolDeserializers.readString(buf);
						BitSet data = Bitset.valueOf(buf.get(new byte[packetSize- (buf.position() - startingPos)]));
						return new packet_login_plugin_request(
							messageId,
							channel,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
		public static class toServer{
			static class packet_login_start extends PacketBase{
				String username;
				BigInteger playerUUID;
				public packet_login_start(
					String username,
					BigInteger playerUUID
				){
					super(PacketIDs.login_toServer_packet_login_start.getId());
					this.username = username;
					this.playerUUID = playerUUID;
				}
				public static packet_login_start readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String username = ProtocolDeserializers.readString(buf);
						BigInteger playerUUID = ProtocolDeserializers.readUUID(buf);
						return new packet_login_start(
							username,
							playerUUID
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_encryption_begin extends PacketBase{
				BitSet sharedSecret;
				BitSet verifyToken;
				public packet_encryption_begin(
					BitSet sharedSecret,
					BitSet verifyToken
				){
					super(PacketIDs.login_toServer_packet_encryption_begin.getId());
					this.sharedSecret = sharedSecret;
					this.verifyToken = verifyToken;
				}
				public static packet_encryption_begin readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BitSet sharedSecret = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						BitSet verifyToken = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						return new packet_encryption_begin(
							sharedSecret,
							verifyToken
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_login_plugin_response extends PacketBase{
				Integer messageId;
				BitSet data;
				public packet_login_plugin_response(
					Integer messageId,
					BitSet data
				){
					super(PacketIDs.login_toServer_packet_login_plugin_response.getId());
					this.messageId = messageId;
					this.data = data;
				}
				public static packet_login_plugin_response readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer messageId = ProtocolDeserializers.readVarInt(buf);
						BitSet data = Bitset.valueOf(buf.get(new byte[packetSize- (buf.position() - startingPos)]));
						return new packet_login_plugin_response(
							messageId,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_login_acknowledged extends PacketBase{
				public packet_login_acknowledged(
				){
					super(PacketIDs.login_toServer_packet_login_acknowledged.getId());
				}
				public static packet_login_acknowledged readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_login_acknowledged(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
	}
	public static class configuration{
		public static class toClient{
			static class packet_custom_payload extends PacketBase{
				String channel;
				BitSet data;
				public packet_custom_payload(
					String channel,
					BitSet data
				){
					super(PacketIDs.configuration_toClient_packet_custom_payload.getId());
					this.channel = channel;
					this.data = data;
				}
				public static packet_custom_payload readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String channel = ProtocolDeserializers.readString(buf);
						BitSet data = Bitset.valueOf(buf.get(new byte[packetSize- (buf.position() - startingPos)]));
						return new packet_custom_payload(
							channel,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_disconnect extends PacketBase{
				String reason;
				public packet_disconnect(
					String reason
				){
					super(PacketIDs.configuration_toClient_packet_disconnect.getId());
					this.reason = reason;
				}
				public static packet_disconnect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String reason = ProtocolDeserializers.readString(buf);
						return new packet_disconnect(
							reason
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_finish_configuration extends PacketBase{
				public packet_finish_configuration(
				){
					super(PacketIDs.configuration_toClient_packet_finish_configuration.getId());
				}
				public static packet_finish_configuration readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_finish_configuration(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_keep_alive extends PacketBase{
				Long keepAliveId;
				public packet_keep_alive(
					Long keepAliveId
				){
					super(PacketIDs.configuration_toClient_packet_keep_alive.getId());
					this.keepAliveId = keepAliveId;
				}
				public static packet_keep_alive readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long keepAliveId = ProtocolDeserializers.readI64(buf);
						return new packet_keep_alive(
							keepAliveId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_ping extends PacketBase{
				Integer id;
				public packet_ping(
					Integer id
				){
					super(PacketIDs.configuration_toClient_packet_ping.getId());
					this.id = id;
				}
				public static packet_ping readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer id = ProtocolDeserializers.readI32(buf);
						return new packet_ping(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_reset_chat extends PacketBase{
				public packet_reset_chat(
				){
					super(PacketIDs.configuration_toClient_packet_reset_chat.getId());
				}
				public static packet_reset_chat readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_reset_chat(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_registry_data extends PacketBase{
				String id;
				Tuple2<String, String>[] entries;
				public packet_registry_data(
					String id,
					Tuple2<String, String>[] entries
				){
					super(PacketIDs.configuration_toClient_packet_registry_data.getId());
					this.id = id;
					this.entries = entries;
				}
				public static packet_registry_data readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String id = ProtocolDeserializers.readString(buf);
						Tuple2<String, String>[] entries = ProtocolDeserializers.readVarintPrefixedArr(buf, aux5 ->Tuple2.readFrom(buf, aux6 ->ProtocolDeserializers.readString(buf), aux7 ->ProtocolDeserializers.readString(buf)), Tuple2[]::new);
						return new packet_registry_data(
							id,
							entries
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_remove_resource_pack extends PacketBase{
				BigInteger uuid;
				public packet_remove_resource_pack(
					BigInteger uuid
				){
					super(PacketIDs.configuration_toClient_packet_remove_resource_pack.getId());
					this.uuid = uuid;
				}
				public static packet_remove_resource_pack readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						return new packet_remove_resource_pack(
							uuid
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_add_resource_pack extends PacketBase{
				BigInteger uuid;
				String url;
				String hash;
				Boolean forced;
				String promptMessage;
				public packet_add_resource_pack(
					BigInteger uuid,
					String url,
					String hash,
					Boolean forced,
					String promptMessage
				){
					super(PacketIDs.configuration_toClient_packet_add_resource_pack.getId());
					this.uuid = uuid;
					this.url = url;
					this.hash = hash;
					this.forced = forced;
					this.promptMessage = promptMessage;
				}
				public static packet_add_resource_pack readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						String url = ProtocolDeserializers.readString(buf);
						String hash = ProtocolDeserializers.readString(buf);
						Boolean forced = ProtocolDeserializers.readBool(buf);
						String promptMessage = ProtocolDeserializers.readString(buf);
						return new packet_add_resource_pack(
							uuid,
							url,
							hash,
							forced,
							promptMessage
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_feature_flags extends PacketBase{
				String[] features;
				public packet_feature_flags(
					String[] features
				){
					super(PacketIDs.configuration_toClient_packet_feature_flags.getId());
					this.features = features;
				}
				public static packet_feature_flags readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String[] features = ProtocolDeserializers.readVarintPrefixedArr(buf, aux8 ->ProtocolDeserializers.readString(buf), String[]::new);
						return new packet_feature_flags(
							features
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_tags extends PacketBase{
				Tuple2<String, Tuple2<String, Integer[]>[]>[] tags;
				public packet_tags(
					Tuple2<String, Tuple2<String, Integer[]>[]>[] tags
				){
					super(PacketIDs.configuration_toClient_packet_tags.getId());
					this.tags = tags;
				}
				public static packet_tags readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Tuple2<String, Tuple2<String, Integer[]>[]>[] tags = ProtocolDeserializers.readVarintPrefixedArr(buf, aux9 ->Tuple2.readFrom(buf, aux10 ->ProtocolDeserializers.readString(buf), aux11 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux12 ->Tuple2.readFrom(buf, aux13 ->ProtocolDeserializers.readString(buf), aux14 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux15 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new)), Tuple2[]::new)), Tuple2[]::new);
						return new packet_tags(
							tags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
		public static class toServer{
			static class packet_settings extends PacketBase{
				String locale;
				Byte viewDistance;
				Integer chatFlags;
				Boolean chatColors;
				Short skinParts;
				Integer mainHand;
				Boolean enableTextFiltering;
				Boolean enableServerListing;
				public packet_settings(
					String locale,
					Byte viewDistance,
					Integer chatFlags,
					Boolean chatColors,
					Short skinParts,
					Integer mainHand,
					Boolean enableTextFiltering,
					Boolean enableServerListing
				){
					super(PacketIDs.configuration_toServer_packet_settings.getId());
					this.locale = locale;
					this.viewDistance = viewDistance;
					this.chatFlags = chatFlags;
					this.chatColors = chatColors;
					this.skinParts = skinParts;
					this.mainHand = mainHand;
					this.enableTextFiltering = enableTextFiltering;
					this.enableServerListing = enableServerListing;
				}
				public static packet_settings readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String locale = ProtocolDeserializers.readString(buf);
						Byte viewDistance = ProtocolDeserializers.readI8(buf);
						Integer chatFlags = ProtocolDeserializers.readVarInt(buf);
						Boolean chatColors = ProtocolDeserializers.readBool(buf);
						Short skinParts = ProtocolDeserializers.readU8(buf);
						Integer mainHand = ProtocolDeserializers.readVarInt(buf);
						Boolean enableTextFiltering = ProtocolDeserializers.readBool(buf);
						Boolean enableServerListing = ProtocolDeserializers.readBool(buf);
						return new packet_settings(
							locale,
							viewDistance,
							chatFlags,
							chatColors,
							skinParts,
							mainHand,
							enableTextFiltering,
							enableServerListing
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_custom_payload extends PacketBase{
				String channel;
				BitSet data;
				public packet_custom_payload(
					String channel,
					BitSet data
				){
					super(PacketIDs.configuration_toServer_packet_custom_payload.getId());
					this.channel = channel;
					this.data = data;
				}
				public static packet_custom_payload readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String channel = ProtocolDeserializers.readString(buf);
						BitSet data = Bitset.valueOf(buf.get(new byte[packetSize- (buf.position() - startingPos)]));
						return new packet_custom_payload(
							channel,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_finish_configuration extends PacketBase{
				public packet_finish_configuration(
				){
					super(PacketIDs.configuration_toServer_packet_finish_configuration.getId());
				}
				public static packet_finish_configuration readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_finish_configuration(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_keep_alive extends PacketBase{
				Long keepAliveId;
				public packet_keep_alive(
					Long keepAliveId
				){
					super(PacketIDs.configuration_toServer_packet_keep_alive.getId());
					this.keepAliveId = keepAliveId;
				}
				public static packet_keep_alive readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long keepAliveId = ProtocolDeserializers.readI64(buf);
						return new packet_keep_alive(
							keepAliveId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_pong extends PacketBase{
				Integer id;
				public packet_pong(
					Integer id
				){
					super(PacketIDs.configuration_toServer_packet_pong.getId());
					this.id = id;
				}
				public static packet_pong readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer id = ProtocolDeserializers.readI32(buf);
						return new packet_pong(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_resource_pack_receive extends PacketBase{
				BigInteger uuid;
				Integer result;
				public packet_resource_pack_receive(
					BigInteger uuid,
					Integer result
				){
					super(PacketIDs.configuration_toServer_packet_resource_pack_receive.getId());
					this.uuid = uuid;
					this.result = result;
				}
				public static packet_resource_pack_receive readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						Integer result = ProtocolDeserializers.readVarInt(buf);
						return new packet_resource_pack_receive(
							uuid,
							result
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
	}
	public static class play{
		public static class toClient{
			nullstatic class packet_spawn_entity extends PacketBase{
				Integer entityId;
				BigInteger objectUUID;
				Integer type;
				Double x;
				Double y;
				Double z;
				Byte pitch;
				Byte yaw;
				Byte headPitch;
				Integer objectData;
				Short velocityX;
				Short velocityY;
				Short velocityZ;
				public packet_spawn_entity(
					Integer entityId,
					BigInteger objectUUID,
					Integer type,
					Double x,
					Double y,
					Double z,
					Byte pitch,
					Byte yaw,
					Byte headPitch,
					Integer objectData,
					Short velocityX,
					Short velocityY,
					Short velocityZ
				){
					super(PacketIDs.play_toClient_packet_spawn_entity.getId());
					this.entityId = entityId;
					this.objectUUID = objectUUID;
					this.type = type;
					this.x = x;
					this.y = y;
					this.z = z;
					this.pitch = pitch;
					this.yaw = yaw;
					this.headPitch = headPitch;
					this.objectData = objectData;
					this.velocityX = velocityX;
					this.velocityY = velocityY;
					this.velocityZ = velocityZ;
				}
				public static packet_spawn_entity readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						BigInteger objectUUID = ProtocolDeserializers.readUUID(buf);
						Integer type = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Byte pitch = ProtocolDeserializers.readI8(buf);
						Byte yaw = ProtocolDeserializers.readI8(buf);
						Byte headPitch = ProtocolDeserializers.readI8(buf);
						Integer objectData = ProtocolDeserializers.readVarInt(buf);
						Short velocityX = ProtocolDeserializers.readI16(buf);
						Short velocityY = ProtocolDeserializers.readI16(buf);
						Short velocityZ = ProtocolDeserializers.readI16(buf);
						return new packet_spawn_entity(
							entityId,
							objectUUID,
							type,
							x,
							y,
							z,
							pitch,
							yaw,
							headPitch,
							objectData,
							velocityX,
							velocityY,
							velocityZ
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_spawn_entity_experience_orb extends PacketBase{
				Integer entityId;
				Double x;
				Double y;
				Double z;
				Short count;
				public packet_spawn_entity_experience_orb(
					Integer entityId,
					Double x,
					Double y,
					Double z,
					Short count
				){
					super(PacketIDs.play_toClient_packet_spawn_entity_experience_orb.getId());
					this.entityId = entityId;
					this.x = x;
					this.y = y;
					this.z = z;
					this.count = count;
				}
				public static packet_spawn_entity_experience_orb readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Short count = ProtocolDeserializers.readI16(buf);
						return new packet_spawn_entity_experience_orb(
							entityId,
							x,
							y,
							z,
							count
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_animation extends PacketBase{
				Integer entityId;
				Short animation;
				public packet_animation(
					Integer entityId,
					Short animation
				){
					super(PacketIDs.play_toClient_packet_animation.getId());
					this.entityId = entityId;
					this.animation = animation;
				}
				public static packet_animation readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Short animation = ProtocolDeserializers.readU8(buf);
						return new packet_animation(
							entityId,
							animation
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_statistics extends PacketBase{
				Tuple3<Integer, Integer, Integer>[] entries;
				public packet_statistics(
					Tuple3<Integer, Integer, Integer>[] entries
				){
					super(PacketIDs.play_toClient_packet_statistics.getId());
					this.entries = entries;
				}
				public static packet_statistics readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Tuple3<Integer, Integer, Integer>[] entries = ProtocolDeserializers.readVarintPrefixedArr(buf, aux16 ->Tuple3.readFrom(buf, aux17 ->ProtocolDeserializers.readVarInt(buf), aux18 ->ProtocolDeserializers.readVarInt(buf), aux19 ->ProtocolDeserializers.readVarInt(buf)), Tuple3[]::new);
						return new packet_statistics(
							entries
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_acknowledge_player_digging extends PacketBase{
				Integer sequenceId;
				public packet_acknowledge_player_digging(
					Integer sequenceId
				){
					super(PacketIDs.play_toClient_packet_acknowledge_player_digging.getId());
					this.sequenceId = sequenceId;
				}
				public static packet_acknowledge_player_digging readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer sequenceId = ProtocolDeserializers.readVarInt(buf);
						return new packet_acknowledge_player_digging(
							sequenceId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_block_break_animation extends PacketBase{
				Integer entityId;
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Byte destroyStage;
				public packet_block_break_animation(
					Integer entityId,
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Byte destroyStage
				){
					super(PacketIDs.play_toClient_packet_block_break_animation.getId());
					this.entityId = entityId;
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.destroyStage = destroyStage;
				}
				public static packet_block_break_animation readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Byte destroyStage = ProtocolDeserializers.readI8(buf);
						return new packet_block_break_animation(
							entityId,
							location,
							location_x,
							location_z,
							location_y,
							destroyStage
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_tile_entity_data extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Integer action;
				String nbtData;
				public packet_tile_entity_data(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Integer action,
					String nbtData
				){
					super(PacketIDs.play_toClient_packet_tile_entity_data.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.action = action;
					this.nbtData = nbtData;
				}
				public static packet_tile_entity_data readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Integer action = ProtocolDeserializers.readVarInt(buf);
						String nbtData = ProtocolDeserializers.readString(buf);
						return new packet_tile_entity_data(
							location,
							location_x,
							location_z,
							location_y,
							action,
							nbtData
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_block_action extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Short byte1;
				Short byte2;
				Integer blockId;
				public packet_block_action(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Short byte1,
					Short byte2,
					Integer blockId
				){
					super(PacketIDs.play_toClient_packet_block_action.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.byte1 = byte1;
					this.byte2 = byte2;
					this.blockId = blockId;
				}
				public static packet_block_action readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Short byte1 = ProtocolDeserializers.readU8(buf);
						Short byte2 = ProtocolDeserializers.readU8(buf);
						Integer blockId = ProtocolDeserializers.readVarInt(buf);
						return new packet_block_action(
							location,
							location_x,
							location_z,
							location_y,
							byte1,
							byte2,
							blockId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_block_change extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Integer type;
				public packet_block_change(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Integer type
				){
					super(PacketIDs.play_toClient_packet_block_change.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.type = type;
				}
				public static packet_block_change readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Integer type = ProtocolDeserializers.readVarInt(buf);
						return new packet_block_change(
							location,
							location_x,
							location_z,
							location_y,
							type
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_boss_bar extends PacketBase{
				BigInteger entityUUID;
				Integer action;
				String title;
				Float health;
				Integer color;
				Integer dividers;
				Short flags;
				public packet_boss_bar(
					BigInteger entityUUID,
					Integer action,
					String title,
					Float health,
					Integer color,
					Integer dividers,
					Short flags
				){
					super(PacketIDs.play_toClient_packet_boss_bar.getId());
					this.entityUUID = entityUUID;
					this.action = action;
					this.title = title;
					this.health = health;
					this.color = color;
					this.dividers = dividers;
					this.flags = flags;
				}
				public static packet_boss_bar readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger entityUUID = ProtocolDeserializers.readUUID(buf);
						Integer action = ProtocolDeserializers.readVarInt(buf);
						String title = (action == 0) || (action == 3) ? ProtocolDeserializers.readString(buf) : null;
						Float health = (action == 0) || (action == 2) ? ProtocolDeserializers.readF32(buf) : null;
						Integer color = (action == 0) || (action == 4) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer dividers = (action == 0) || (action == 4) ? ProtocolDeserializers.readVarInt(buf) : null;
						Short flags = (action == 0) || (action == 5) ? ProtocolDeserializers.readU8(buf) : null;
						return new packet_boss_bar(
							entityUUID,
							action,
							title,
							health,
							color,
							dividers,
							flags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_difficulty extends PacketBase{
				Short difficulty;
				Boolean difficultyLocked;
				public packet_difficulty(
					Short difficulty,
					Boolean difficultyLocked
				){
					super(PacketIDs.play_toClient_packet_difficulty.getId());
					this.difficulty = difficulty;
					this.difficultyLocked = difficultyLocked;
				}
				public static packet_difficulty readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short difficulty = ProtocolDeserializers.readU8(buf);
						Boolean difficultyLocked = ProtocolDeserializers.readBool(buf);
						return new packet_difficulty(
							difficulty,
							difficultyLocked
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chunk_batch_finished extends PacketBase{
				Integer batchSize;
				public packet_chunk_batch_finished(
					Integer batchSize
				){
					super(PacketIDs.play_toClient_packet_chunk_batch_finished.getId());
					this.batchSize = batchSize;
				}
				public static packet_chunk_batch_finished readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer batchSize = ProtocolDeserializers.readVarInt(buf);
						return new packet_chunk_batch_finished(
							batchSize
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chunk_batch_start extends PacketBase{
				public packet_chunk_batch_start(
				){
					super(PacketIDs.play_toClient_packet_chunk_batch_start.getId());
				}
				public static packet_chunk_batch_start readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_chunk_batch_start(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chunk_biomes extends PacketBase{
				Tuple3<Integer, Integer, BitSet>[] biomes;
				public packet_chunk_biomes(
					Tuple3<Integer, Integer, BitSet>[] biomes
				){
					super(PacketIDs.play_toClient_packet_chunk_biomes.getId());
					this.biomes = biomes;
				}
				public static packet_chunk_biomes readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Tuple3<Integer, Integer, BitSet>[] biomes = ProtocolDeserializers.readVarintPrefixedArr(buf, aux20 ->Tuple3.readFrom(buf, aux21 ->ProtocolDeserializers.readI32(buf), aux22 ->ProtocolDeserializers.readI32(buf), aux23 ->BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]))), Tuple3[]::new);
						return new packet_chunk_biomes(
							biomes
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_clear_titles extends PacketBase{
				Boolean reset;
				public packet_clear_titles(
					Boolean reset
				){
					super(PacketIDs.play_toClient_packet_clear_titles.getId());
					this.reset = reset;
				}
				public static packet_clear_titles readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Boolean reset = ProtocolDeserializers.readBool(buf);
						return new packet_clear_titles(
							reset
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_tab_complete extends PacketBase{
				Integer transactionId;
				Integer start;
				Integer length;
				Tuple2<String, String>[] matches;
				public packet_tab_complete(
					Integer transactionId,
					Integer start,
					Integer length,
					Tuple2<String, String>[] matches
				){
					super(PacketIDs.play_toClient_packet_tab_complete.getId());
					this.transactionId = transactionId;
					this.start = start;
					this.length = length;
					this.matches = matches;
				}
				public static packet_tab_complete readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer transactionId = ProtocolDeserializers.readVarInt(buf);
						Integer start = ProtocolDeserializers.readVarInt(buf);
						Integer length = ProtocolDeserializers.readVarInt(buf);
						Tuple2<String, String>[] matches = ProtocolDeserializers.readVarintPrefixedArr(buf, aux24 ->Tuple2.readFrom(buf, aux25 ->ProtocolDeserializers.readString(buf), aux26 ->ProtocolDeserializers.readString(buf)), Tuple2[]::new);
						return new packet_tab_complete(
							transactionId,
							start,
							length,
							matches
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_declare_commands extends PacketBase{
				Tuple8<Integer, Integer, Integer, Integer, Integer, Integer[], Object, Object>[] nodes;
				Integer rootIndex;
				public packet_declare_commands(
					Tuple8<Integer, Integer, Integer, Integer, Integer, Integer[], Object, Object>[] nodes,
					Integer rootIndex
				){
					super(PacketIDs.play_toClient_packet_declare_commands.getId());
					this.nodes = nodes;
					this.rootIndex = rootIndex;
				}
				public static packet_declare_commands readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Tuple8<Integer, Integer, Integer, Integer, Integer, Integer[], Object, Object>[] nodes = ProtocolDeserializers.readVarintPrefixedArr(buf, aux27 ->Tuple8.readFrom(buf, aux28 ->(int)(ProtocolDeserializers.peekI8(buf) & 22), aux29 ->(int)(ProtocolDeserializers.peekI8(buf) & 22), aux30 ->(int)(ProtocolDeserializers.peekI8(buf) & 22), aux31 ->(int)(ProtocolDeserializers.peekI8(buf) & 22), aux32 ->(int)(ProtocolDeserializers.readI8(buf) & 23), aux33 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux34 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new), aux35 ->readSwitchOfflags/has_redirect_node(), aux36 ->readSwitchOfflags/command_node_type()), Tuple8[]::new);
						Integer rootIndex = ProtocolDeserializers.readVarInt(buf);
						return new packet_declare_commands(
							nodes,
							rootIndex
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_close_window extends PacketBase{
				Short windowId;
				public packet_close_window(
					Short windowId
				){
					super(PacketIDs.play_toClient_packet_close_window.getId());
					this.windowId = windowId;
				}
				public static packet_close_window readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short windowId = ProtocolDeserializers.readU8(buf);
						return new packet_close_window(
							windowId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_window_items extends PacketBase{
				Short windowId;
				Integer stateId;
				Tuple2<Byte, Object>[] items;
				Byte carriedItem_itemCount;
				Integer carriedItem_itemId;
				Integer carriedItem_addedComponentCount;
				Integer carriedItem_removedComponentCount;
				Tuple2<Integer, Object>[] carriedItem_components;
				Integer[] carriedItem_removeComponents;
				public packet_window_items(
					Short windowId,
					Integer stateId,
					Tuple2<Byte, Object>[] items,
					Byte carriedItem_itemCount,
					Integer carriedItem_itemId,
					Integer carriedItem_addedComponentCount,
					Integer carriedItem_removedComponentCount,
					Tuple2<Integer, Object>[] carriedItem_components,
					Integer[] carriedItem_removeComponents
				){
					super(PacketIDs.play_toClient_packet_window_items.getId());
					this.windowId = windowId;
					this.stateId = stateId;
					this.items = items;
					this.carriedItem_itemCount = carriedItem_itemCount;
					this.carriedItem_itemId = carriedItem_itemId;
					this.carriedItem_addedComponentCount = carriedItem_addedComponentCount;
					this.carriedItem_removedComponentCount = carriedItem_removedComponentCount;
					this.carriedItem_components = carriedItem_components;
					this.carriedItem_removeComponents = carriedItem_removeComponents;
				}
				public static packet_window_items readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short windowId = ProtocolDeserializers.readU8(buf);
						Integer stateId = ProtocolDeserializers.readVarInt(buf);
						Tuple2<Byte, Object>[] items = ProtocolDeserializers.readVarintPrefixedArr(buf, aux37 ->Tuple2.readFrom(buf, aux38 ->ProtocolDeserializers.readI8(buf), aux39 ->readSwitchOfitemCount()), Tuple2[]::new);
						Byte carriedItem_itemCount = ProtocolDeserializers.readI8(buf);
						Integer carriedItem_itemId = (carriedItem_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer carriedItem_addedComponentCount = (carriedItem_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer carriedItem_removedComponentCount = (carriedItem_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Tuple2<Integer, Object>[] carriedItem_components = (carriedItem_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux40 ->Tuple2.readFrom(buf, aux41 ->ProtocolDeserializers.readVarInt(buf), aux42 ->readSwitchOftype()), Tuple2[]::new, carriedItem_anon_components_addedComponentCount) : null;
						Integer[] carriedItem_removeComponents = (carriedItem_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux43 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, carriedItem_anon_removeComponents_removedComponentCount) : null;
						return new packet_window_items(
							windowId,
							stateId,
							items,
							carriedItem_itemCount,
							carriedItem_itemId,
							carriedItem_addedComponentCount,
							carriedItem_removedComponentCount,
							carriedItem_components,
							carriedItem_removeComponents
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_craft_progress_bar extends PacketBase{
				Short windowId;
				Short property;
				Short value;
				public packet_craft_progress_bar(
					Short windowId,
					Short property,
					Short value
				){
					super(PacketIDs.play_toClient_packet_craft_progress_bar.getId());
					this.windowId = windowId;
					this.property = property;
					this.value = value;
				}
				public static packet_craft_progress_bar readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short windowId = ProtocolDeserializers.readU8(buf);
						Short property = ProtocolDeserializers.readI16(buf);
						Short value = ProtocolDeserializers.readI16(buf);
						return new packet_craft_progress_bar(
							windowId,
							property,
							value
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_slot extends PacketBase{
				Byte windowId;
				Integer stateId;
				Short slot;
				Byte item_itemCount;
				Integer item_itemId;
				Integer item_addedComponentCount;
				Integer item_removedComponentCount;
				Tuple2<Integer, Object>[] item_components;
				Integer[] item_removeComponents;
				public packet_set_slot(
					Byte windowId,
					Integer stateId,
					Short slot,
					Byte item_itemCount,
					Integer item_itemId,
					Integer item_addedComponentCount,
					Integer item_removedComponentCount,
					Tuple2<Integer, Object>[] item_components,
					Integer[] item_removeComponents
				){
					super(PacketIDs.play_toClient_packet_set_slot.getId());
					this.windowId = windowId;
					this.stateId = stateId;
					this.slot = slot;
					this.item_itemCount = item_itemCount;
					this.item_itemId = item_itemId;
					this.item_addedComponentCount = item_addedComponentCount;
					this.item_removedComponentCount = item_removedComponentCount;
					this.item_components = item_components;
					this.item_removeComponents = item_removeComponents;
				}
				public static packet_set_slot readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte windowId = ProtocolDeserializers.readI8(buf);
						Integer stateId = ProtocolDeserializers.readVarInt(buf);
						Short slot = ProtocolDeserializers.readI16(buf);
						Byte item_itemCount = ProtocolDeserializers.readI8(buf);
						Integer item_itemId = (item_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer item_addedComponentCount = (item_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer item_removedComponentCount = (item_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Tuple2<Integer, Object>[] item_components = (item_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux44 ->Tuple2.readFrom(buf, aux45 ->ProtocolDeserializers.readVarInt(buf), aux46 ->readSwitchOftype()), Tuple2[]::new, item_anon_components_addedComponentCount) : null;
						Integer[] item_removeComponents = (item_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux47 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, item_anon_removeComponents_removedComponentCount) : null;
						return new packet_set_slot(
							windowId,
							stateId,
							slot,
							item_itemCount,
							item_itemId,
							item_addedComponentCount,
							item_removedComponentCount,
							item_components,
							item_removeComponents
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_cooldown extends PacketBase{
				Integer itemID;
				Integer cooldownTicks;
				public packet_set_cooldown(
					Integer itemID,
					Integer cooldownTicks
				){
					super(PacketIDs.play_toClient_packet_set_cooldown.getId());
					this.itemID = itemID;
					this.cooldownTicks = cooldownTicks;
				}
				public static packet_set_cooldown readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer itemID = ProtocolDeserializers.readVarInt(buf);
						Integer cooldownTicks = ProtocolDeserializers.readVarInt(buf);
						return new packet_set_cooldown(
							itemID,
							cooldownTicks
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chat_suggestions extends PacketBase{
				Integer action;
				String[] entries;
				public packet_chat_suggestions(
					Integer action,
					String[] entries
				){
					super(PacketIDs.play_toClient_packet_chat_suggestions.getId());
					this.action = action;
					this.entries = entries;
				}
				public static packet_chat_suggestions readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer action = ProtocolDeserializers.readVarInt(buf);
						String[] entries = ProtocolDeserializers.readVarintPrefixedArr(buf, aux48 ->ProtocolDeserializers.readString(buf), String[]::new);
						return new packet_chat_suggestions(
							action,
							entries
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_custom_payload extends PacketBase{
				String channel;
				BitSet data;
				public packet_custom_payload(
					String channel,
					BitSet data
				){
					super(PacketIDs.play_toClient_packet_custom_payload.getId());
					this.channel = channel;
					this.data = data;
				}
				public static packet_custom_payload readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String channel = ProtocolDeserializers.readString(buf);
						BitSet data = Bitset.valueOf(buf.get(new byte[packetSize- (buf.position() - startingPos)]));
						return new packet_custom_payload(
							channel,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_damage_event extends PacketBase{
				Integer entityId;
				Integer sourceTypeId;
				Integer sourceCauseId;
				Integer sourceDirectId;
				Double sourcePosition_x;
				Double sourcePosition_y;
				Double sourcePosition_z;
				public packet_damage_event(
					Integer entityId,
					Integer sourceTypeId,
					Integer sourceCauseId,
					Integer sourceDirectId,
					Double sourcePosition_x,
					Double sourcePosition_y,
					Double sourcePosition_z
				){
					super(PacketIDs.play_toClient_packet_damage_event.getId());
					this.entityId = entityId;
					this.sourceTypeId = sourceTypeId;
					this.sourceCauseId = sourceCauseId;
					this.sourceDirectId = sourceDirectId;
					this.sourcePosition_x = sourcePosition_x;
					this.sourcePosition_y = sourcePosition_y;
					this.sourcePosition_z = sourcePosition_z;
				}
				public static packet_damage_event readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Integer sourceTypeId = ProtocolDeserializers.readVarInt(buf);
						Integer sourceCauseId = ProtocolDeserializers.readVarInt(buf);
						Integer sourceDirectId = ProtocolDeserializers.readVarInt(buf);
						Double sourcePosition_x = ProtocolDeserializers.readF64(buf);
						Double sourcePosition_y = ProtocolDeserializers.readF64(buf);
						Double sourcePosition_z = ProtocolDeserializers.readF64(buf);
						return new packet_damage_event(
							entityId,
							sourceTypeId,
							sourceCauseId,
							sourceDirectId,
							sourcePosition_x,
							sourcePosition_y,
							sourcePosition_z
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_debug_sample extends PacketBase{
				Long[] sample;
				Integer type;
				public packet_debug_sample(
					Long[] sample,
					Integer type
				){
					super(PacketIDs.play_toClient_packet_debug_sample.getId());
					this.sample = sample;
					this.type = type;
				}
				public static packet_debug_sample readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long[] sample = ProtocolDeserializers.readVarintPrefixedArr(buf, aux49 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Integer type = ProtocolDeserializers.readVarInt(buf);
						return new packet_debug_sample(
							sample,
							type
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_hide_message extends PacketBase{
				Integer id;
				BitSet signature;
				public packet_hide_message(
					Integer id,
					BitSet signature
				){
					super(PacketIDs.play_toClient_packet_hide_message.getId());
					this.id = id;
					this.signature = signature;
				}
				public static packet_hide_message readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer id = ProtocolDeserializers.readVarInt(buf);
						BitSet signature = (id == 0) ? Bitset.valueOf(buf.get(new byte[256])) : null;
						return new packet_hide_message(
							id,
							signature
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_kick_disconnect extends PacketBase{
				String reason;
				public packet_kick_disconnect(
					String reason
				){
					super(PacketIDs.play_toClient_packet_kick_disconnect.getId());
					this.reason = reason;
				}
				public static packet_kick_disconnect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String reason = ProtocolDeserializers.readString(buf);
						return new packet_kick_disconnect(
							reason
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			nullnullnullstatic class packet_profileless_chat extends PacketBase{
				String message;
				Integer type_registryIndex;
				String type_chat_translationKey;
				Integer[] type_chat_parameters;
				String type_chat_style;
				String type_narration_translationKey;
				Integer[] type_narration_parameters;
				String type_narration_style;
				String name;
				String target;
				public packet_profileless_chat(
					String message,
					Integer type_registryIndex,
					String type_chat_translationKey,
					Integer[] type_chat_parameters,
					String type_chat_style,
					String type_narration_translationKey,
					Integer[] type_narration_parameters,
					String type_narration_style,
					String name,
					String target
				){
					super(PacketIDs.play_toClient_packet_profileless_chat.getId());
					this.message = message;
					this.type_registryIndex = type_registryIndex;
					this.type_chat_translationKey = type_chat_translationKey;
					this.type_chat_parameters = type_chat_parameters;
					this.type_chat_style = type_chat_style;
					this.type_narration_translationKey = type_narration_translationKey;
					this.type_narration_parameters = type_narration_parameters;
					this.type_narration_style = type_narration_style;
					this.name = name;
					this.target = target;
				}
				public static packet_profileless_chat readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String message = ProtocolDeserializers.readString(buf);
						Integer type_registryIndex = ProtocolDeserializers.readVarInt(buf);
						String type_chat_translationKey = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						Integer[] type_chat_parameters = (type_registryIndex == 0) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux50 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new) : null;
						String type_chat_style = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						String type_narration_translationKey = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						Integer[] type_narration_parameters = (type_registryIndex == 0) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux51 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new) : null;
						String type_narration_style = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						String name = ProtocolDeserializers.readString(buf);
						String target = ProtocolDeserializers.readString(buf);
						return new packet_profileless_chat(
							message,
							type_registryIndex,
							type_chat_translationKey,
							type_chat_parameters,
							type_chat_style,
							type_narration_translationKey,
							type_narration_parameters,
							type_narration_style,
							name,
							target
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_status extends PacketBase{
				Integer entityId;
				Byte entityStatus;
				public packet_entity_status(
					Integer entityId,
					Byte entityStatus
				){
					super(PacketIDs.play_toClient_packet_entity_status.getId());
					this.entityId = entityId;
					this.entityStatus = entityStatus;
				}
				public static packet_entity_status readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readI32(buf);
						Byte entityStatus = ProtocolDeserializers.readI8(buf);
						return new packet_entity_status(
							entityId,
							entityStatus
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_explosion extends PacketBase{
				Double x;
				Double y;
				Double z;
				Float radius;
				Tuple3<Byte, Byte, Byte>[] affectedBlockOffsets;
				Float playerMotionX;
				Float playerMotionY;
				Float playerMotionZ;
				Integer block_interaction_type;
				Integer small_explosion_particle_type;
				Map<String, Integer> small_explosion_particle_type_map;
				Integer small_explosion_particle_data;
				Byte small_explosion_particle_data_itemCount;
				Integer small_explosion_particle_data_itemId;
				Integer small_explosion_particle_data_addedComponentCount;
				Integer small_explosion_particle_data_removedComponentCount;
				Tuple2<Integer, Object>[] small_explosion_particle_data_components;
				Integer[] small_explosion_particle_data_removeComponents;
				Float small_explosion_particle_data_fromRed;
				Float small_explosion_particle_data_fromGreen;
				Float small_explosion_particle_data_fromBlue;
				Float small_explosion_particle_data_scale;
				Float small_explosion_particle_data_toRed;
				Float small_explosion_particle_data_toGreen;
				Float small_explosion_particle_data_toBlue;
				Integer small_explosion_particle_data_position_type;
				Map<String, Integer> small_explosion_particle_data_position_type_map;
				//Unknown type: position
				Object small_explosion_particle_data_position;
				Integer small_explosion_particle_data_position_entityId;
				Float small_explosion_particle_data_position_entity_eye_height;
				Integer small_explosion_particle_data_ticks;
				Float small_explosion_particle_data_red;
				Float small_explosion_particle_data_green;
				Float small_explosion_particle_data_blue;
				Float small_explosion_particle_data_scale;
				Float small_explosion_particle_data;
				Integer large_explosion_particle_type;
				Map<String, Integer> large_explosion_particle_type_map;
				Integer large_explosion_particle_data;
				Byte large_explosion_particle_data_itemCount;
				Integer large_explosion_particle_data_itemId;
				Integer large_explosion_particle_data_addedComponentCount;
				Integer large_explosion_particle_data_removedComponentCount;
				Tuple2<Integer, Object>[] large_explosion_particle_data_components;
				Integer[] large_explosion_particle_data_removeComponents;
				Float large_explosion_particle_data_fromRed;
				Float large_explosion_particle_data_fromGreen;
				Float large_explosion_particle_data_fromBlue;
				Float large_explosion_particle_data_scale;
				Float large_explosion_particle_data_toRed;
				Float large_explosion_particle_data_toGreen;
				Float large_explosion_particle_data_toBlue;
				Integer large_explosion_particle_data_position_type;
				Map<String, Integer> large_explosion_particle_data_position_type_map;
				//Unknown type: position
				Object large_explosion_particle_data_position;
				Integer large_explosion_particle_data_position_entityId;
				Float large_explosion_particle_data_position_entity_eye_height;
				Integer large_explosion_particle_data_ticks;
				Float large_explosion_particle_data_red;
				Float large_explosion_particle_data_green;
				Float large_explosion_particle_data_blue;
				Float large_explosion_particle_data_scale;
				Float large_explosion_particle_data;
				Integer soundId;
				String soundName;
				Float range;
				public packet_explosion(
					Double x,
					Double y,
					Double z,
					Float radius,
					Tuple3<Byte, Byte, Byte>[] affectedBlockOffsets,
					Float playerMotionX,
					Float playerMotionY,
					Float playerMotionZ,
					Integer block_interaction_type,
					Integer small_explosion_particle_type,
					Map<String, Integer> small_explosion_particle_type_map,
					Integer small_explosion_particle_data,
					Byte small_explosion_particle_data_itemCount,
					Integer small_explosion_particle_data_itemId,
					Integer small_explosion_particle_data_addedComponentCount,
					Integer small_explosion_particle_data_removedComponentCount,
					Tuple2<Integer, Object>[] small_explosion_particle_data_components,
					Integer[] small_explosion_particle_data_removeComponents,
					Float small_explosion_particle_data_fromRed,
					Float small_explosion_particle_data_fromGreen,
					Float small_explosion_particle_data_fromBlue,
					Float small_explosion_particle_data_scale,
					Float small_explosion_particle_data_toRed,
					Float small_explosion_particle_data_toGreen,
					Float small_explosion_particle_data_toBlue,
					Integer small_explosion_particle_data_position_type,
					Map<String, Integer> small_explosion_particle_data_position_type_map,
					Object small_explosion_particle_data_position,
					Integer small_explosion_particle_data_position_entityId,
					Float small_explosion_particle_data_position_entity_eye_height,
					Integer small_explosion_particle_data_ticks,
					Float small_explosion_particle_data_red,
					Float small_explosion_particle_data_green,
					Float small_explosion_particle_data_blue,
					Float small_explosion_particle_data_scale,
					Float small_explosion_particle_data,
					Integer large_explosion_particle_type,
					Map<String, Integer> large_explosion_particle_type_map,
					Integer large_explosion_particle_data,
					Byte large_explosion_particle_data_itemCount,
					Integer large_explosion_particle_data_itemId,
					Integer large_explosion_particle_data_addedComponentCount,
					Integer large_explosion_particle_data_removedComponentCount,
					Tuple2<Integer, Object>[] large_explosion_particle_data_components,
					Integer[] large_explosion_particle_data_removeComponents,
					Float large_explosion_particle_data_fromRed,
					Float large_explosion_particle_data_fromGreen,
					Float large_explosion_particle_data_fromBlue,
					Float large_explosion_particle_data_scale,
					Float large_explosion_particle_data_toRed,
					Float large_explosion_particle_data_toGreen,
					Float large_explosion_particle_data_toBlue,
					Integer large_explosion_particle_data_position_type,
					Map<String, Integer> large_explosion_particle_data_position_type_map,
					Object large_explosion_particle_data_position,
					Integer large_explosion_particle_data_position_entityId,
					Float large_explosion_particle_data_position_entity_eye_height,
					Integer large_explosion_particle_data_ticks,
					Float large_explosion_particle_data_red,
					Float large_explosion_particle_data_green,
					Float large_explosion_particle_data_blue,
					Float large_explosion_particle_data_scale,
					Float large_explosion_particle_data,
					Integer soundId,
					String soundName,
					Float range
				){
					super(PacketIDs.play_toClient_packet_explosion.getId());
					this.x = x;
					this.y = y;
					this.z = z;
					this.radius = radius;
					this.affectedBlockOffsets = affectedBlockOffsets;
					this.playerMotionX = playerMotionX;
					this.playerMotionY = playerMotionY;
					this.playerMotionZ = playerMotionZ;
					this.block_interaction_type = block_interaction_type;
					this.small_explosion_particle_type = small_explosion_particle_type;
					this.small_explosion_particle_type_map = small_explosion_particle_type_map;
					this.small_explosion_particle_data = small_explosion_particle_data;
					this.small_explosion_particle_data_itemCount = small_explosion_particle_data_itemCount;
					this.small_explosion_particle_data_itemId = small_explosion_particle_data_itemId;
					this.small_explosion_particle_data_addedComponentCount = small_explosion_particle_data_addedComponentCount;
					this.small_explosion_particle_data_removedComponentCount = small_explosion_particle_data_removedComponentCount;
					this.small_explosion_particle_data_components = small_explosion_particle_data_components;
					this.small_explosion_particle_data_removeComponents = small_explosion_particle_data_removeComponents;
					this.small_explosion_particle_data_fromRed = small_explosion_particle_data_fromRed;
					this.small_explosion_particle_data_fromGreen = small_explosion_particle_data_fromGreen;
					this.small_explosion_particle_data_fromBlue = small_explosion_particle_data_fromBlue;
					this.small_explosion_particle_data_scale = small_explosion_particle_data_scale;
					this.small_explosion_particle_data_toRed = small_explosion_particle_data_toRed;
					this.small_explosion_particle_data_toGreen = small_explosion_particle_data_toGreen;
					this.small_explosion_particle_data_toBlue = small_explosion_particle_data_toBlue;
					this.small_explosion_particle_data_position_type = small_explosion_particle_data_position_type;
					this.small_explosion_particle_data_position_type_map = small_explosion_particle_data_position_type_map;
					this.small_explosion_particle_data_position = small_explosion_particle_data_position;
					this.small_explosion_particle_data_position_entityId = small_explosion_particle_data_position_entityId;
					this.small_explosion_particle_data_position_entity_eye_height = small_explosion_particle_data_position_entity_eye_height;
					this.small_explosion_particle_data_ticks = small_explosion_particle_data_ticks;
					this.small_explosion_particle_data_red = small_explosion_particle_data_red;
					this.small_explosion_particle_data_green = small_explosion_particle_data_green;
					this.small_explosion_particle_data_blue = small_explosion_particle_data_blue;
					this.small_explosion_particle_data_scale = small_explosion_particle_data_scale;
					this.small_explosion_particle_data = small_explosion_particle_data;
					this.large_explosion_particle_type = large_explosion_particle_type;
					this.large_explosion_particle_type_map = large_explosion_particle_type_map;
					this.large_explosion_particle_data = large_explosion_particle_data;
					this.large_explosion_particle_data_itemCount = large_explosion_particle_data_itemCount;
					this.large_explosion_particle_data_itemId = large_explosion_particle_data_itemId;
					this.large_explosion_particle_data_addedComponentCount = large_explosion_particle_data_addedComponentCount;
					this.large_explosion_particle_data_removedComponentCount = large_explosion_particle_data_removedComponentCount;
					this.large_explosion_particle_data_components = large_explosion_particle_data_components;
					this.large_explosion_particle_data_removeComponents = large_explosion_particle_data_removeComponents;
					this.large_explosion_particle_data_fromRed = large_explosion_particle_data_fromRed;
					this.large_explosion_particle_data_fromGreen = large_explosion_particle_data_fromGreen;
					this.large_explosion_particle_data_fromBlue = large_explosion_particle_data_fromBlue;
					this.large_explosion_particle_data_scale = large_explosion_particle_data_scale;
					this.large_explosion_particle_data_toRed = large_explosion_particle_data_toRed;
					this.large_explosion_particle_data_toGreen = large_explosion_particle_data_toGreen;
					this.large_explosion_particle_data_toBlue = large_explosion_particle_data_toBlue;
					this.large_explosion_particle_data_position_type = large_explosion_particle_data_position_type;
					this.large_explosion_particle_data_position_type_map = large_explosion_particle_data_position_type_map;
					this.large_explosion_particle_data_position = large_explosion_particle_data_position;
					this.large_explosion_particle_data_position_entityId = large_explosion_particle_data_position_entityId;
					this.large_explosion_particle_data_position_entity_eye_height = large_explosion_particle_data_position_entity_eye_height;
					this.large_explosion_particle_data_ticks = large_explosion_particle_data_ticks;
					this.large_explosion_particle_data_red = large_explosion_particle_data_red;
					this.large_explosion_particle_data_green = large_explosion_particle_data_green;
					this.large_explosion_particle_data_blue = large_explosion_particle_data_blue;
					this.large_explosion_particle_data_scale = large_explosion_particle_data_scale;
					this.large_explosion_particle_data = large_explosion_particle_data;
					this.soundId = soundId;
					this.soundName = soundName;
					this.range = range;
				}
				public static packet_explosion readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Float radius = ProtocolDeserializers.readF32(buf);
						Tuple3<Byte, Byte, Byte>[] affectedBlockOffsets = ProtocolDeserializers.readVarintPrefixedArr(buf, aux52 ->Tuple3.readFrom(buf, aux53 ->ProtocolDeserializers.readI8(buf), aux54 ->ProtocolDeserializers.readI8(buf), aux55 ->ProtocolDeserializers.readI8(buf)), Tuple3[]::new);
						Float playerMotionX = ProtocolDeserializers.readF32(buf);
						Float playerMotionY = ProtocolDeserializers.readF32(buf);
						Float playerMotionZ = ProtocolDeserializers.readF32(buf);
						Integer block_interaction_type = ProtocolDeserializers.readVarInt(buf);
						Integer small_explosion_particle_type = ProtocolDeserializers.readVarInt(buf);
						Map<String, Integer> small_explosion_particle_type_map = Map.ofEntries(Map.entry("angry_villager",0), Map.entry("block",1), Map.entry("block_marker",2), Map.entry("bubble",3), Map.entry("cloud",4), Map.entry("crit",5), Map.entry("damage_indicator",6), Map.entry("dragon_breath",7), Map.entry("dripping_lava",8), Map.entry("falling_lava",9), Map.entry("landing_lava",10), Map.entry("dripping_water",11), Map.entry("falling_water",12), Map.entry("dust",13), Map.entry("dust_color_transition",14), Map.entry("effect",15), Map.entry("elder_guardian",16), Map.entry("enchanted_hit",17), Map.entry("enchant",18), Map.entry("end_rod",19), Map.entry("entity_effect",20), Map.entry("explosion_emitter",21), Map.entry("explosion",22), Map.entry("gust",23), Map.entry("small_gust",24), Map.entry("gust_emitter_large",25), Map.entry("gust_emitter_small",26), Map.entry("sonic_boom",27), Map.entry("falling_dust",28), Map.entry("firework",29), Map.entry("fishing",30), Map.entry("flame",31), Map.entry("infested",32), Map.entry("cherry_leaves",33), Map.entry("sculk_soul",34), Map.entry("sculk_charge",35), Map.entry("sculk_charge_pop",36), Map.entry("soul_fire_flame",37), Map.entry("soul",38), Map.entry("flash",39), Map.entry("happy_villager",40), Map.entry("composter",41), Map.entry("heart",42), Map.entry("instant_effect",43), Map.entry("item",44), Map.entry("vibration",45), Map.entry("item_slime",46), Map.entry("item_cobweb",47), Map.entry("item_snowball",48), Map.entry("large_smoke",49), Map.entry("lava",50), Map.entry("mycelium",51), Map.entry("note",52), Map.entry("poof",53), Map.entry("portal",54), Map.entry("rain",55), Map.entry("smoke",56), Map.entry("white_smoke",57), Map.entry("sneeze",58), Map.entry("spit",59), Map.entry("squid_ink",60), Map.entry("sweep_attack",61), Map.entry("totem_of_undying",62), Map.entry("underwater",63), Map.entry("splash",64), Map.entry("witch",65), Map.entry("bubble_pop",66), Map.entry("current_down",67), Map.entry("bubble_column_up",68), Map.entry("nautilus",69), Map.entry("dolphin",70), Map.entry("campfire_cosy_smoke",71), Map.entry("campfire_signal_smoke",72), Map.entry("dripping_honey",73), Map.entry("falling_honey",74), Map.entry("landing_honey",75), Map.entry("falling_nectar",76), Map.entry("falling_spore_blossom",77), Map.entry("ash",78), Map.entry("crimson_spore",79), Map.entry("warped_spore",80), Map.entry("spore_blossom_air",81), Map.entry("dripping_obsidian_tear",82), Map.entry("falling_obsidian_tear",83), Map.entry("landing_obsidian_tear",84), Map.entry("reverse_portal",85), Map.entry("white_ash",86), Map.entry("small_flame",87), Map.entry("snowflake",88), Map.entry("dripping_dripstone_lava",89), Map.entry("falling_dripstone_lava",90), Map.entry("dripping_dripstone_water",91), Map.entry("falling_dripstone_water",92), Map.entry("glow_squid_ink",93), Map.entry("glow",94), Map.entry("wax_on",95), Map.entry("wax_off",96), Map.entry("electric_spark",97), Map.entry("scrape",98), Map.entry("shriek",99), Map.entry("egg_crack",100), Map.entry("dust_plume",101), Map.entry("trial_spawner_detected_player",102), Map.entry("trial_spawner_detected_player_ominous",103), Map.entry("vault_connection",104), Map.entry("dust_pillar",105), Map.entry("ominous_spawning",106), Map.entry("raid_omen",107), Map.entry("trial_omen",108));
						Integer small_explosion_particle_data = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_pillar"))) || (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("entity_effect"))) || (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("block"))) || (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("block_marker"))) || (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("falling_dust"))) || (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("shriek"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Byte small_explosion_particle_data_itemCount = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("item"))) ? ProtocolDeserializers.readI8(buf) : null;
						Integer small_explosion_particle_data_itemId = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("item"))) ? (small_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Integer small_explosion_particle_data_addedComponentCount = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("item"))) ? (small_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Integer small_explosion_particle_data_removedComponentCount = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("item"))) ? (small_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Tuple2<Integer, Object>[] small_explosion_particle_data_components = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("item"))) ? (small_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux56 ->Tuple2.readFrom(buf, aux57 ->ProtocolDeserializers.readVarInt(buf), aux58 ->readSwitchOftype()), Tuple2[]::new, small_explosion_particle_data_anon_components_addedComponentCount) : null : null;
						Integer[] small_explosion_particle_data_removeComponents = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("item"))) ? (small_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux59 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, small_explosion_particle_data_anon_removeComponents_removedComponentCount) : null : null;
						Float small_explosion_particle_data_fromRed = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_fromGreen = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_fromBlue = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_scale = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_toRed = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_toGreen = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_toBlue = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Integer small_explosion_particle_data_position_type = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("vibration"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Map<String, Integer> small_explosion_particle_data_position_type_map = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("vibration"))) ? Map.ofEntries(Map.entry("block",0), Map.entry("entity",1)) : null;
						Object small_explosion_particle_data_position = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("vibration"))) ? (small_explosion_particle_data_position_type.equals(small_explosion_particle_data_position_type_map.get("block"))) ? "nonDefinedNative-position@FlattenableBuilder" : null : null;
						Integer small_explosion_particle_data_position_entityId = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("vibration"))) ? (small_explosion_particle_data_position_type.equals(small_explosion_particle_data_position_type_map.get("entity"))) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Float small_explosion_particle_data_position_entity_eye_height = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("vibration"))) ? (small_explosion_particle_data_position_type.equals(small_explosion_particle_data_position_type_map.get("entity"))) ? ProtocolDeserializers.readF32(buf) : null : null;
						Integer small_explosion_particle_data_ticks = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("vibration"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Float small_explosion_particle_data_red = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_green = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_blue = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data_scale = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float small_explosion_particle_data = (small_explosion_particle_type.equals(small_explosion_particle_type_map.get("sculk_charge"))) ? ProtocolDeserializers.readF32(buf) : null;
						Integer large_explosion_particle_type = ProtocolDeserializers.readVarInt(buf);
						Map<String, Integer> large_explosion_particle_type_map = Map.ofEntries(Map.entry("angry_villager",0), Map.entry("block",1), Map.entry("block_marker",2), Map.entry("bubble",3), Map.entry("cloud",4), Map.entry("crit",5), Map.entry("damage_indicator",6), Map.entry("dragon_breath",7), Map.entry("dripping_lava",8), Map.entry("falling_lava",9), Map.entry("landing_lava",10), Map.entry("dripping_water",11), Map.entry("falling_water",12), Map.entry("dust",13), Map.entry("dust_color_transition",14), Map.entry("effect",15), Map.entry("elder_guardian",16), Map.entry("enchanted_hit",17), Map.entry("enchant",18), Map.entry("end_rod",19), Map.entry("entity_effect",20), Map.entry("explosion_emitter",21), Map.entry("explosion",22), Map.entry("gust",23), Map.entry("small_gust",24), Map.entry("gust_emitter_large",25), Map.entry("gust_emitter_small",26), Map.entry("sonic_boom",27), Map.entry("falling_dust",28), Map.entry("firework",29), Map.entry("fishing",30), Map.entry("flame",31), Map.entry("infested",32), Map.entry("cherry_leaves",33), Map.entry("sculk_soul",34), Map.entry("sculk_charge",35), Map.entry("sculk_charge_pop",36), Map.entry("soul_fire_flame",37), Map.entry("soul",38), Map.entry("flash",39), Map.entry("happy_villager",40), Map.entry("composter",41), Map.entry("heart",42), Map.entry("instant_effect",43), Map.entry("item",44), Map.entry("vibration",45), Map.entry("item_slime",46), Map.entry("item_cobweb",47), Map.entry("item_snowball",48), Map.entry("large_smoke",49), Map.entry("lava",50), Map.entry("mycelium",51), Map.entry("note",52), Map.entry("poof",53), Map.entry("portal",54), Map.entry("rain",55), Map.entry("smoke",56), Map.entry("white_smoke",57), Map.entry("sneeze",58), Map.entry("spit",59), Map.entry("squid_ink",60), Map.entry("sweep_attack",61), Map.entry("totem_of_undying",62), Map.entry("underwater",63), Map.entry("splash",64), Map.entry("witch",65), Map.entry("bubble_pop",66), Map.entry("current_down",67), Map.entry("bubble_column_up",68), Map.entry("nautilus",69), Map.entry("dolphin",70), Map.entry("campfire_cosy_smoke",71), Map.entry("campfire_signal_smoke",72), Map.entry("dripping_honey",73), Map.entry("falling_honey",74), Map.entry("landing_honey",75), Map.entry("falling_nectar",76), Map.entry("falling_spore_blossom",77), Map.entry("ash",78), Map.entry("crimson_spore",79), Map.entry("warped_spore",80), Map.entry("spore_blossom_air",81), Map.entry("dripping_obsidian_tear",82), Map.entry("falling_obsidian_tear",83), Map.entry("landing_obsidian_tear",84), Map.entry("reverse_portal",85), Map.entry("white_ash",86), Map.entry("small_flame",87), Map.entry("snowflake",88), Map.entry("dripping_dripstone_lava",89), Map.entry("falling_dripstone_lava",90), Map.entry("dripping_dripstone_water",91), Map.entry("falling_dripstone_water",92), Map.entry("glow_squid_ink",93), Map.entry("glow",94), Map.entry("wax_on",95), Map.entry("wax_off",96), Map.entry("electric_spark",97), Map.entry("scrape",98), Map.entry("shriek",99), Map.entry("egg_crack",100), Map.entry("dust_plume",101), Map.entry("trial_spawner_detected_player",102), Map.entry("trial_spawner_detected_player_ominous",103), Map.entry("vault_connection",104), Map.entry("dust_pillar",105), Map.entry("ominous_spawning",106), Map.entry("raid_omen",107), Map.entry("trial_omen",108));
						Integer large_explosion_particle_data = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_pillar"))) || (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("entity_effect"))) || (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("block"))) || (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("block_marker"))) || (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("falling_dust"))) || (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("shriek"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Byte large_explosion_particle_data_itemCount = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("item"))) ? ProtocolDeserializers.readI8(buf) : null;
						Integer large_explosion_particle_data_itemId = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("item"))) ? (large_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Integer large_explosion_particle_data_addedComponentCount = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("item"))) ? (large_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Integer large_explosion_particle_data_removedComponentCount = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("item"))) ? (large_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Tuple2<Integer, Object>[] large_explosion_particle_data_components = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("item"))) ? (large_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux60 ->Tuple2.readFrom(buf, aux61 ->ProtocolDeserializers.readVarInt(buf), aux62 ->readSwitchOftype()), Tuple2[]::new, large_explosion_particle_data_anon_components_addedComponentCount) : null : null;
						Integer[] large_explosion_particle_data_removeComponents = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("item"))) ? (large_explosion_particle_data_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux63 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, large_explosion_particle_data_anon_removeComponents_removedComponentCount) : null : null;
						Float large_explosion_particle_data_fromRed = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_fromGreen = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_fromBlue = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_scale = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_toRed = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_toGreen = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_toBlue = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Integer large_explosion_particle_data_position_type = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("vibration"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Map<String, Integer> large_explosion_particle_data_position_type_map = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("vibration"))) ? Map.ofEntries(Map.entry("block",0), Map.entry("entity",1)) : null;
						Object large_explosion_particle_data_position = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("vibration"))) ? (large_explosion_particle_data_position_type.equals(large_explosion_particle_data_position_type_map.get("block"))) ? "nonDefinedNative-position@FlattenableBuilder" : null : null;
						Integer large_explosion_particle_data_position_entityId = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("vibration"))) ? (large_explosion_particle_data_position_type.equals(large_explosion_particle_data_position_type_map.get("entity"))) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Float large_explosion_particle_data_position_entity_eye_height = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("vibration"))) ? (large_explosion_particle_data_position_type.equals(large_explosion_particle_data_position_type_map.get("entity"))) ? ProtocolDeserializers.readF32(buf) : null : null;
						Integer large_explosion_particle_data_ticks = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("vibration"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Float large_explosion_particle_data_red = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_green = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_blue = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data_scale = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float large_explosion_particle_data = (large_explosion_particle_type.equals(large_explosion_particle_type_map.get("sculk_charge"))) ? ProtocolDeserializers.readF32(buf) : null;
						Integer soundId = ProtocolDeserializers.readVarInt(buf);
						String soundName = (soundId == 0) ? ProtocolDeserializers.readString(buf) : null;
						Float range = (soundId == 0) ? ProtocolDeserializers.readF32(buf) : null;
						return new packet_explosion(
							x,
							y,
							z,
							radius,
							affectedBlockOffsets,
							playerMotionX,
							playerMotionY,
							playerMotionZ,
							block_interaction_type,
							small_explosion_particle_type,
							small_explosion_particle_type_map,
							small_explosion_particle_data,
							small_explosion_particle_data_itemCount,
							small_explosion_particle_data_itemId,
							small_explosion_particle_data_addedComponentCount,
							small_explosion_particle_data_removedComponentCount,
							small_explosion_particle_data_components,
							small_explosion_particle_data_removeComponents,
							small_explosion_particle_data_fromRed,
							small_explosion_particle_data_fromGreen,
							small_explosion_particle_data_fromBlue,
							small_explosion_particle_data_scale,
							small_explosion_particle_data_toRed,
							small_explosion_particle_data_toGreen,
							small_explosion_particle_data_toBlue,
							small_explosion_particle_data_position_type,
							small_explosion_particle_data_position_type_map,
							small_explosion_particle_data_position,
							small_explosion_particle_data_position_entityId,
							small_explosion_particle_data_position_entity_eye_height,
							small_explosion_particle_data_ticks,
							small_explosion_particle_data_red,
							small_explosion_particle_data_green,
							small_explosion_particle_data_blue,
							small_explosion_particle_data_scale,
							small_explosion_particle_data,
							large_explosion_particle_type,
							large_explosion_particle_type_map,
							large_explosion_particle_data,
							large_explosion_particle_data_itemCount,
							large_explosion_particle_data_itemId,
							large_explosion_particle_data_addedComponentCount,
							large_explosion_particle_data_removedComponentCount,
							large_explosion_particle_data_components,
							large_explosion_particle_data_removeComponents,
							large_explosion_particle_data_fromRed,
							large_explosion_particle_data_fromGreen,
							large_explosion_particle_data_fromBlue,
							large_explosion_particle_data_scale,
							large_explosion_particle_data_toRed,
							large_explosion_particle_data_toGreen,
							large_explosion_particle_data_toBlue,
							large_explosion_particle_data_position_type,
							large_explosion_particle_data_position_type_map,
							large_explosion_particle_data_position,
							large_explosion_particle_data_position_entityId,
							large_explosion_particle_data_position_entity_eye_height,
							large_explosion_particle_data_ticks,
							large_explosion_particle_data_red,
							large_explosion_particle_data_green,
							large_explosion_particle_data_blue,
							large_explosion_particle_data_scale,
							large_explosion_particle_data,
							soundId,
							soundName,
							range
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_unload_chunk extends PacketBase{
				Integer chunkZ;
				Integer chunkX;
				public packet_unload_chunk(
					Integer chunkZ,
					Integer chunkX
				){
					super(PacketIDs.play_toClient_packet_unload_chunk.getId());
					this.chunkZ = chunkZ;
					this.chunkX = chunkX;
				}
				public static packet_unload_chunk readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer chunkZ = ProtocolDeserializers.readI32(buf);
						Integer chunkX = ProtocolDeserializers.readI32(buf);
						return new packet_unload_chunk(
							chunkZ,
							chunkX
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_game_state_change extends PacketBase{
				Short reason;
				Float gameMode;
				public packet_game_state_change(
					Short reason,
					Float gameMode
				){
					super(PacketIDs.play_toClient_packet_game_state_change.getId());
					this.reason = reason;
					this.gameMode = gameMode;
				}
				public static packet_game_state_change readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short reason = ProtocolDeserializers.readU8(buf);
						Float gameMode = ProtocolDeserializers.readF32(buf);
						return new packet_game_state_change(
							reason,
							gameMode
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_open_horse_window extends PacketBase{
				Short windowId;
				Integer nbSlots;
				Integer entityId;
				public packet_open_horse_window(
					Short windowId,
					Integer nbSlots,
					Integer entityId
				){
					super(PacketIDs.play_toClient_packet_open_horse_window.getId());
					this.windowId = windowId;
					this.nbSlots = nbSlots;
					this.entityId = entityId;
				}
				public static packet_open_horse_window readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short windowId = ProtocolDeserializers.readU8(buf);
						Integer nbSlots = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readI32(buf);
						return new packet_open_horse_window(
							windowId,
							nbSlots,
							entityId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_hurt_animation extends PacketBase{
				Integer entityId;
				Float yaw;
				public packet_hurt_animation(
					Integer entityId,
					Float yaw
				){
					super(PacketIDs.play_toClient_packet_hurt_animation.getId());
					this.entityId = entityId;
					this.yaw = yaw;
				}
				public static packet_hurt_animation readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Float yaw = ProtocolDeserializers.readF32(buf);
						return new packet_hurt_animation(
							entityId,
							yaw
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_initialize_world_border extends PacketBase{
				Double x;
				Double z;
				Double oldDiameter;
				Double newDiameter;
				Integer speed;
				Integer portalTeleportBoundary;
				Integer warningBlocks;
				Integer warningTime;
				public packet_initialize_world_border(
					Double x,
					Double z,
					Double oldDiameter,
					Double newDiameter,
					Integer speed,
					Integer portalTeleportBoundary,
					Integer warningBlocks,
					Integer warningTime
				){
					super(PacketIDs.play_toClient_packet_initialize_world_border.getId());
					this.x = x;
					this.z = z;
					this.oldDiameter = oldDiameter;
					this.newDiameter = newDiameter;
					this.speed = speed;
					this.portalTeleportBoundary = portalTeleportBoundary;
					this.warningBlocks = warningBlocks;
					this.warningTime = warningTime;
				}
				public static packet_initialize_world_border readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Double oldDiameter = ProtocolDeserializers.readF64(buf);
						Double newDiameter = ProtocolDeserializers.readF64(buf);
						Integer speed = ProtocolDeserializers.readVarInt(buf);
						Integer portalTeleportBoundary = ProtocolDeserializers.readVarInt(buf);
						Integer warningBlocks = ProtocolDeserializers.readVarInt(buf);
						Integer warningTime = ProtocolDeserializers.readVarInt(buf);
						return new packet_initialize_world_border(
							x,
							z,
							oldDiameter,
							newDiameter,
							speed,
							portalTeleportBoundary,
							warningBlocks,
							warningTime
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_keep_alive extends PacketBase{
				Long keepAliveId;
				public packet_keep_alive(
					Long keepAliveId
				){
					super(PacketIDs.play_toClient_packet_keep_alive.getId());
					this.keepAliveId = keepAliveId;
				}
				public static packet_keep_alive readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long keepAliveId = ProtocolDeserializers.readI64(buf);
						return new packet_keep_alive(
							keepAliveId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_map_chunk extends PacketBase{
				Integer x;
				Integer z;
				String heightmaps;
				BitSet chunkData;
				Tuple5<Integer, Integer, Short, Integer, String>[] blockEntities;
				Long[] skyLightMask;
				Long[] blockLightMask;
				Long[] emptySkyLightMask;
				Long[] emptyBlockLightMask;
				Short[][] skyLight;
				Short[][] blockLight;
				public packet_map_chunk(
					Integer x,
					Integer z,
					String heightmaps,
					BitSet chunkData,
					Tuple5<Integer, Integer, Short, Integer, String>[] blockEntities,
					Long[] skyLightMask,
					Long[] blockLightMask,
					Long[] emptySkyLightMask,
					Long[] emptyBlockLightMask,
					Short[][] skyLight,
					Short[][] blockLight
				){
					super(PacketIDs.play_toClient_packet_map_chunk.getId());
					this.x = x;
					this.z = z;
					this.heightmaps = heightmaps;
					this.chunkData = chunkData;
					this.blockEntities = blockEntities;
					this.skyLightMask = skyLightMask;
					this.blockLightMask = blockLightMask;
					this.emptySkyLightMask = emptySkyLightMask;
					this.emptyBlockLightMask = emptyBlockLightMask;
					this.skyLight = skyLight;
					this.blockLight = blockLight;
				}
				public static packet_map_chunk readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer x = ProtocolDeserializers.readI32(buf);
						Integer z = ProtocolDeserializers.readI32(buf);
						String heightmaps = ProtocolDeserializers.readString(buf);
						BitSet chunkData = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						Tuple5<Integer, Integer, Short, Integer, String>[] blockEntities = ProtocolDeserializers.readVarintPrefixedArr(buf, aux64 ->Tuple5.readFrom(buf, aux65 ->(int)(ProtocolDeserializers.peekI8(buf) & 22), aux66 ->(int)(ProtocolDeserializers.readI8(buf) & 23), aux67 ->ProtocolDeserializers.readI16(buf), aux68 ->ProtocolDeserializers.readVarInt(buf), aux69 ->ProtocolDeserializers.readString(buf)), Tuple5[]::new);
						Long[] skyLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux70 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Long[] blockLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux71 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Long[] emptySkyLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux72 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Long[] emptyBlockLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux73 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Short[][] skyLight = ProtocolDeserializers.readVarintPrefixedArr(buf, aux74 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux75 ->ProtocolDeserializers.readU8(buf), Short[]::new), Short[]::new);
						Short[][] blockLight = ProtocolDeserializers.readVarintPrefixedArr(buf, aux76 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux77 ->ProtocolDeserializers.readU8(buf), Short[]::new), Short[]::new);
						return new packet_map_chunk(
							x,
							z,
							heightmaps,
							chunkData,
							blockEntities,
							skyLightMask,
							blockLightMask,
							emptySkyLightMask,
							emptyBlockLightMask,
							skyLight,
							blockLight
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_event extends PacketBase{
				Integer effectId;
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Integer data;
				Boolean global;
				public packet_world_event(
					Integer effectId,
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Integer data,
					Boolean global
				){
					super(PacketIDs.play_toClient_packet_world_event.getId());
					this.effectId = effectId;
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.data = data;
					this.global = global;
				}
				public static packet_world_event readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer effectId = ProtocolDeserializers.readI32(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Integer data = ProtocolDeserializers.readI32(buf);
						Boolean global = ProtocolDeserializers.readBool(buf);
						return new packet_world_event(
							effectId,
							location,
							location_x,
							location_z,
							location_y,
							data,
							global
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_particles extends PacketBase{
				Boolean longDistance;
				Double x;
				Double y;
				Double z;
				Float offsetX;
				Float offsetY;
				Float offsetZ;
				Float velocityOffset;
				Integer amount;
				Integer particle_type;
				Map<String, Integer> particle_type_map;
				Integer particle_data;
				Byte particle_data_itemCount;
				Integer particle_data_itemId;
				Integer particle_data_addedComponentCount;
				Integer particle_data_removedComponentCount;
				Tuple2<Integer, Object>[] particle_data_components;
				Integer[] particle_data_removeComponents;
				Float particle_data_fromRed;
				Float particle_data_fromGreen;
				Float particle_data_fromBlue;
				Float particle_data_scale;
				Float particle_data_toRed;
				Float particle_data_toGreen;
				Float particle_data_toBlue;
				Integer particle_data_position_type;
				Map<String, Integer> particle_data_position_type_map;
				//Unknown type: position
				Object particle_data_position;
				Integer particle_data_position_entityId;
				Float particle_data_position_entity_eye_height;
				Integer particle_data_ticks;
				Float particle_data_red;
				Float particle_data_green;
				Float particle_data_blue;
				Float particle_data_scale;
				Float particle_data;
				public packet_world_particles(
					Boolean longDistance,
					Double x,
					Double y,
					Double z,
					Float offsetX,
					Float offsetY,
					Float offsetZ,
					Float velocityOffset,
					Integer amount,
					Integer particle_type,
					Map<String, Integer> particle_type_map,
					Integer particle_data,
					Byte particle_data_itemCount,
					Integer particle_data_itemId,
					Integer particle_data_addedComponentCount,
					Integer particle_data_removedComponentCount,
					Tuple2<Integer, Object>[] particle_data_components,
					Integer[] particle_data_removeComponents,
					Float particle_data_fromRed,
					Float particle_data_fromGreen,
					Float particle_data_fromBlue,
					Float particle_data_scale,
					Float particle_data_toRed,
					Float particle_data_toGreen,
					Float particle_data_toBlue,
					Integer particle_data_position_type,
					Map<String, Integer> particle_data_position_type_map,
					Object particle_data_position,
					Integer particle_data_position_entityId,
					Float particle_data_position_entity_eye_height,
					Integer particle_data_ticks,
					Float particle_data_red,
					Float particle_data_green,
					Float particle_data_blue,
					Float particle_data_scale,
					Float particle_data
				){
					super(PacketIDs.play_toClient_packet_world_particles.getId());
					this.longDistance = longDistance;
					this.x = x;
					this.y = y;
					this.z = z;
					this.offsetX = offsetX;
					this.offsetY = offsetY;
					this.offsetZ = offsetZ;
					this.velocityOffset = velocityOffset;
					this.amount = amount;
					this.particle_type = particle_type;
					this.particle_type_map = particle_type_map;
					this.particle_data = particle_data;
					this.particle_data_itemCount = particle_data_itemCount;
					this.particle_data_itemId = particle_data_itemId;
					this.particle_data_addedComponentCount = particle_data_addedComponentCount;
					this.particle_data_removedComponentCount = particle_data_removedComponentCount;
					this.particle_data_components = particle_data_components;
					this.particle_data_removeComponents = particle_data_removeComponents;
					this.particle_data_fromRed = particle_data_fromRed;
					this.particle_data_fromGreen = particle_data_fromGreen;
					this.particle_data_fromBlue = particle_data_fromBlue;
					this.particle_data_scale = particle_data_scale;
					this.particle_data_toRed = particle_data_toRed;
					this.particle_data_toGreen = particle_data_toGreen;
					this.particle_data_toBlue = particle_data_toBlue;
					this.particle_data_position_type = particle_data_position_type;
					this.particle_data_position_type_map = particle_data_position_type_map;
					this.particle_data_position = particle_data_position;
					this.particle_data_position_entityId = particle_data_position_entityId;
					this.particle_data_position_entity_eye_height = particle_data_position_entity_eye_height;
					this.particle_data_ticks = particle_data_ticks;
					this.particle_data_red = particle_data_red;
					this.particle_data_green = particle_data_green;
					this.particle_data_blue = particle_data_blue;
					this.particle_data_scale = particle_data_scale;
					this.particle_data = particle_data;
				}
				public static packet_world_particles readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Boolean longDistance = ProtocolDeserializers.readBool(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Float offsetX = ProtocolDeserializers.readF32(buf);
						Float offsetY = ProtocolDeserializers.readF32(buf);
						Float offsetZ = ProtocolDeserializers.readF32(buf);
						Float velocityOffset = ProtocolDeserializers.readF32(buf);
						Integer amount = ProtocolDeserializers.readI32(buf);
						Integer particle_type = ProtocolDeserializers.readVarInt(buf);
						Map<String, Integer> particle_type_map = Map.ofEntries(Map.entry("angry_villager",0), Map.entry("block",1), Map.entry("block_marker",2), Map.entry("bubble",3), Map.entry("cloud",4), Map.entry("crit",5), Map.entry("damage_indicator",6), Map.entry("dragon_breath",7), Map.entry("dripping_lava",8), Map.entry("falling_lava",9), Map.entry("landing_lava",10), Map.entry("dripping_water",11), Map.entry("falling_water",12), Map.entry("dust",13), Map.entry("dust_color_transition",14), Map.entry("effect",15), Map.entry("elder_guardian",16), Map.entry("enchanted_hit",17), Map.entry("enchant",18), Map.entry("end_rod",19), Map.entry("entity_effect",20), Map.entry("explosion_emitter",21), Map.entry("explosion",22), Map.entry("gust",23), Map.entry("small_gust",24), Map.entry("gust_emitter_large",25), Map.entry("gust_emitter_small",26), Map.entry("sonic_boom",27), Map.entry("falling_dust",28), Map.entry("firework",29), Map.entry("fishing",30), Map.entry("flame",31), Map.entry("infested",32), Map.entry("cherry_leaves",33), Map.entry("sculk_soul",34), Map.entry("sculk_charge",35), Map.entry("sculk_charge_pop",36), Map.entry("soul_fire_flame",37), Map.entry("soul",38), Map.entry("flash",39), Map.entry("happy_villager",40), Map.entry("composter",41), Map.entry("heart",42), Map.entry("instant_effect",43), Map.entry("item",44), Map.entry("vibration",45), Map.entry("item_slime",46), Map.entry("item_cobweb",47), Map.entry("item_snowball",48), Map.entry("large_smoke",49), Map.entry("lava",50), Map.entry("mycelium",51), Map.entry("note",52), Map.entry("poof",53), Map.entry("portal",54), Map.entry("rain",55), Map.entry("smoke",56), Map.entry("white_smoke",57), Map.entry("sneeze",58), Map.entry("spit",59), Map.entry("squid_ink",60), Map.entry("sweep_attack",61), Map.entry("totem_of_undying",62), Map.entry("underwater",63), Map.entry("splash",64), Map.entry("witch",65), Map.entry("bubble_pop",66), Map.entry("current_down",67), Map.entry("bubble_column_up",68), Map.entry("nautilus",69), Map.entry("dolphin",70), Map.entry("campfire_cosy_smoke",71), Map.entry("campfire_signal_smoke",72), Map.entry("dripping_honey",73), Map.entry("falling_honey",74), Map.entry("landing_honey",75), Map.entry("falling_nectar",76), Map.entry("falling_spore_blossom",77), Map.entry("ash",78), Map.entry("crimson_spore",79), Map.entry("warped_spore",80), Map.entry("spore_blossom_air",81), Map.entry("dripping_obsidian_tear",82), Map.entry("falling_obsidian_tear",83), Map.entry("landing_obsidian_tear",84), Map.entry("reverse_portal",85), Map.entry("white_ash",86), Map.entry("small_flame",87), Map.entry("snowflake",88), Map.entry("dripping_dripstone_lava",89), Map.entry("falling_dripstone_lava",90), Map.entry("dripping_dripstone_water",91), Map.entry("falling_dripstone_water",92), Map.entry("glow_squid_ink",93), Map.entry("glow",94), Map.entry("wax_on",95), Map.entry("wax_off",96), Map.entry("electric_spark",97), Map.entry("scrape",98), Map.entry("shriek",99), Map.entry("egg_crack",100), Map.entry("dust_plume",101), Map.entry("trial_spawner_detected_player",102), Map.entry("trial_spawner_detected_player_ominous",103), Map.entry("vault_connection",104), Map.entry("dust_pillar",105), Map.entry("ominous_spawning",106), Map.entry("raid_omen",107), Map.entry("trial_omen",108));
						Integer particle_data = (particle_type.equals(particle_type_map.get("dust_pillar"))) || (particle_type.equals(particle_type_map.get("entity_effect"))) || (particle_type.equals(particle_type_map.get("block"))) || (particle_type.equals(particle_type_map.get("block_marker"))) || (particle_type.equals(particle_type_map.get("falling_dust"))) || (particle_type.equals(particle_type_map.get("shriek"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Byte particle_data_itemCount = (particle_type.equals(particle_type_map.get("item"))) ? ProtocolDeserializers.readI8(buf) : null;
						Integer particle_data_itemId = (particle_type.equals(particle_type_map.get("item"))) ? (particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Integer particle_data_addedComponentCount = (particle_type.equals(particle_type_map.get("item"))) ? (particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Integer particle_data_removedComponentCount = (particle_type.equals(particle_type_map.get("item"))) ? (particle_data_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Tuple2<Integer, Object>[] particle_data_components = (particle_type.equals(particle_type_map.get("item"))) ? (particle_data_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux78 ->Tuple2.readFrom(buf, aux79 ->ProtocolDeserializers.readVarInt(buf), aux80 ->readSwitchOftype()), Tuple2[]::new, particle_data_anon_components_addedComponentCount) : null : null;
						Integer[] particle_data_removeComponents = (particle_type.equals(particle_type_map.get("item"))) ? (particle_data_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux81 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, particle_data_anon_removeComponents_removedComponentCount) : null : null;
						Float particle_data_fromRed = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_fromGreen = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_fromBlue = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_scale = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_toRed = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_toGreen = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_toBlue = (particle_type.equals(particle_type_map.get("dust_color_transition"))) ? ProtocolDeserializers.readF32(buf) : null;
						Integer particle_data_position_type = (particle_type.equals(particle_type_map.get("vibration"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Map<String, Integer> particle_data_position_type_map = (particle_type.equals(particle_type_map.get("vibration"))) ? Map.ofEntries(Map.entry("block",0), Map.entry("entity",1)) : null;
						Object particle_data_position = (particle_type.equals(particle_type_map.get("vibration"))) ? (particle_data_position_type.equals(particle_data_position_type_map.get("block"))) ? "nonDefinedNative-position@FlattenableBuilder" : null : null;
						Integer particle_data_position_entityId = (particle_type.equals(particle_type_map.get("vibration"))) ? (particle_data_position_type.equals(particle_data_position_type_map.get("entity"))) ? ProtocolDeserializers.readVarInt(buf) : null : null;
						Float particle_data_position_entity_eye_height = (particle_type.equals(particle_type_map.get("vibration"))) ? (particle_data_position_type.equals(particle_data_position_type_map.get("entity"))) ? ProtocolDeserializers.readF32(buf) : null : null;
						Integer particle_data_ticks = (particle_type.equals(particle_type_map.get("vibration"))) ? ProtocolDeserializers.readVarInt(buf) : null;
						Float particle_data_red = (particle_type.equals(particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_green = (particle_type.equals(particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_blue = (particle_type.equals(particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data_scale = (particle_type.equals(particle_type_map.get("dust"))) ? ProtocolDeserializers.readF32(buf) : null;
						Float particle_data = (particle_type.equals(particle_type_map.get("sculk_charge"))) ? ProtocolDeserializers.readF32(buf) : null;
						return new packet_world_particles(
							longDistance,
							x,
							y,
							z,
							offsetX,
							offsetY,
							offsetZ,
							velocityOffset,
							amount,
							particle_type,
							particle_type_map,
							particle_data,
							particle_data_itemCount,
							particle_data_itemId,
							particle_data_addedComponentCount,
							particle_data_removedComponentCount,
							particle_data_components,
							particle_data_removeComponents,
							particle_data_fromRed,
							particle_data_fromGreen,
							particle_data_fromBlue,
							particle_data_scale,
							particle_data_toRed,
							particle_data_toGreen,
							particle_data_toBlue,
							particle_data_position_type,
							particle_data_position_type_map,
							particle_data_position,
							particle_data_position_entityId,
							particle_data_position_entity_eye_height,
							particle_data_ticks,
							particle_data_red,
							particle_data_green,
							particle_data_blue,
							particle_data_scale,
							particle_data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_light extends PacketBase{
				Integer chunkX;
				Integer chunkZ;
				Long[] skyLightMask;
				Long[] blockLightMask;
				Long[] emptySkyLightMask;
				Long[] emptyBlockLightMask;
				Short[][] skyLight;
				Short[][] blockLight;
				public packet_update_light(
					Integer chunkX,
					Integer chunkZ,
					Long[] skyLightMask,
					Long[] blockLightMask,
					Long[] emptySkyLightMask,
					Long[] emptyBlockLightMask,
					Short[][] skyLight,
					Short[][] blockLight
				){
					super(PacketIDs.play_toClient_packet_update_light.getId());
					this.chunkX = chunkX;
					this.chunkZ = chunkZ;
					this.skyLightMask = skyLightMask;
					this.blockLightMask = blockLightMask;
					this.emptySkyLightMask = emptySkyLightMask;
					this.emptyBlockLightMask = emptyBlockLightMask;
					this.skyLight = skyLight;
					this.blockLight = blockLight;
				}
				public static packet_update_light readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer chunkX = ProtocolDeserializers.readVarInt(buf);
						Integer chunkZ = ProtocolDeserializers.readVarInt(buf);
						Long[] skyLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux82 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Long[] blockLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux83 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Long[] emptySkyLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux84 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Long[] emptyBlockLightMask = ProtocolDeserializers.readVarintPrefixedArr(buf, aux85 ->ProtocolDeserializers.readI64(buf), Long[]::new);
						Short[][] skyLight = ProtocolDeserializers.readVarintPrefixedArr(buf, aux86 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux87 ->ProtocolDeserializers.readU8(buf), Short[]::new), Short[]::new);
						Short[][] blockLight = ProtocolDeserializers.readVarintPrefixedArr(buf, aux88 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux89 ->ProtocolDeserializers.readU8(buf), Short[]::new), Short[]::new);
						return new packet_update_light(
							chunkX,
							chunkZ,
							skyLightMask,
							blockLightMask,
							emptySkyLightMask,
							emptyBlockLightMask,
							skyLight,
							blockLight
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_login extends PacketBase{
				Integer entityId;
				Boolean isHardcore;
				String[] worldNames;
				Integer maxPlayers;
				Integer viewDistance;
				Integer simulationDistance;
				Boolean reducedDebugInfo;
				Boolean enableRespawnScreen;
				Boolean doLimitedCrafting;
				Integer worldState_dimension;
				String worldState_name;
				Long worldState_hashedSeed;
				Byte worldState_gamemode;
				Map<String, Integer> worldState_gamemode_map;
				Short worldState_previousGamemode;
				Boolean worldState_isDebug;
				Boolean worldState_isFlat;
				//optionthew exception: java.lang.RuntimeException: java.lang.UnsupportedOperationException: Attempting to create a optionwith: [container, [{name=dimensionName, type=string}, {name=location, type=position}]]
				Object worldState_death_option;
				Integer worldState_portalCooldown;
				Boolean enforcesSecureChat;
				public packet_login(
					Integer entityId,
					Boolean isHardcore,
					String[] worldNames,
					Integer maxPlayers,
					Integer viewDistance,
					Integer simulationDistance,
					Boolean reducedDebugInfo,
					Boolean enableRespawnScreen,
					Boolean doLimitedCrafting,
					Integer worldState_dimension,
					String worldState_name,
					Long worldState_hashedSeed,
					Byte worldState_gamemode,
					Map<String, Integer> worldState_gamemode_map,
					Short worldState_previousGamemode,
					Boolean worldState_isDebug,
					Boolean worldState_isFlat,
					Object worldState_death_option,
					Integer worldState_portalCooldown,
					Boolean enforcesSecureChat
				){
					super(PacketIDs.play_toClient_packet_login.getId());
					this.entityId = entityId;
					this.isHardcore = isHardcore;
					this.worldNames = worldNames;
					this.maxPlayers = maxPlayers;
					this.viewDistance = viewDistance;
					this.simulationDistance = simulationDistance;
					this.reducedDebugInfo = reducedDebugInfo;
					this.enableRespawnScreen = enableRespawnScreen;
					this.doLimitedCrafting = doLimitedCrafting;
					this.worldState_dimension = worldState_dimension;
					this.worldState_name = worldState_name;
					this.worldState_hashedSeed = worldState_hashedSeed;
					this.worldState_gamemode = worldState_gamemode;
					this.worldState_gamemode_map = worldState_gamemode_map;
					this.worldState_previousGamemode = worldState_previousGamemode;
					this.worldState_isDebug = worldState_isDebug;
					this.worldState_isFlat = worldState_isFlat;
					this.worldState_death_option = worldState_death_option;
					this.worldState_portalCooldown = worldState_portalCooldown;
					this.enforcesSecureChat = enforcesSecureChat;
				}
				public static packet_login readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readI32(buf);
						Boolean isHardcore = ProtocolDeserializers.readBool(buf);
						String[] worldNames = ProtocolDeserializers.readVarintPrefixedArr(buf, aux90 ->ProtocolDeserializers.readString(buf), String[]::new);
						Integer maxPlayers = ProtocolDeserializers.readVarInt(buf);
						Integer viewDistance = ProtocolDeserializers.readVarInt(buf);
						Integer simulationDistance = ProtocolDeserializers.readVarInt(buf);
						Boolean reducedDebugInfo = ProtocolDeserializers.readBool(buf);
						Boolean enableRespawnScreen = ProtocolDeserializers.readBool(buf);
						Boolean doLimitedCrafting = ProtocolDeserializers.readBool(buf);
						Integer worldState_dimension = ProtocolDeserializers.readVarInt(buf);
						String worldState_name = ProtocolDeserializers.readString(buf);
						Long worldState_hashedSeed = ProtocolDeserializers.readI64(buf);
						Byte worldState_gamemode = ProtocolDeserializers.readI8(buf);
						Map<String, Integer> worldState_gamemode_map = Map.ofEntries(Map.entry("survival",0), Map.entry("creative",1), Map.entry("adventure",2), Map.entry("spectator",3));
						Short worldState_previousGamemode = ProtocolDeserializers.readU8(buf);
						Boolean worldState_isDebug = ProtocolDeserializers.readBool(buf);
						Boolean worldState_isFlat = ProtocolDeserializers.readBool(buf);
						Object worldState_death_option = nullNOKNOWNCLASS;
						Integer worldState_portalCooldown = ProtocolDeserializers.readVarInt(buf);
						Boolean enforcesSecureChat = ProtocolDeserializers.readBool(buf);
						return new packet_login(
							entityId,
							isHardcore,
							worldNames,
							maxPlayers,
							viewDistance,
							simulationDistance,
							reducedDebugInfo,
							enableRespawnScreen,
							doLimitedCrafting,
							worldState_dimension,
							worldState_name,
							worldState_hashedSeed,
							worldState_gamemode,
							worldState_gamemode_map,
							worldState_previousGamemode,
							worldState_isDebug,
							worldState_isFlat,
							worldState_death_option,
							worldState_portalCooldown,
							enforcesSecureChat
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_map extends PacketBase{
				Integer itemDamage;
				Byte scale;
				Boolean locked;
				//optionthew exception: java.lang.RuntimeException: java.lang.UnsupportedOperationException: Attempting to create a optionwith: [array, {countType=varint, type=[container, [{name=type, type=varint}, {name=x, type=i8}, {name=z, type=i8}, {name=direction, type=u8}, {name=displayName, type=[option, anonymousNbt]}]]}]
				Object icons_option;
				Short columns;
				Short rows;
				Short x;
				Short y;
				BitSet data;
				public packet_map(
					Integer itemDamage,
					Byte scale,
					Boolean locked,
					Object icons_option,
					Short columns,
					Short rows,
					Short x,
					Short y,
					BitSet data
				){
					super(PacketIDs.play_toClient_packet_map.getId());
					this.itemDamage = itemDamage;
					this.scale = scale;
					this.locked = locked;
					this.icons_option = icons_option;
					this.columns = columns;
					this.rows = rows;
					this.x = x;
					this.y = y;
					this.data = data;
				}
				public static packet_map readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer itemDamage = ProtocolDeserializers.readVarInt(buf);
						Byte scale = ProtocolDeserializers.readI8(buf);
						Boolean locked = ProtocolDeserializers.readBool(buf);
						Object icons_option = nullNOKNOWNCLASS;
						Short columns = ProtocolDeserializers.readU8(buf);
						Short rows = (columns != 0) ? ProtocolDeserializers.readU8(buf) : null;
						Short x = (columns != 0) ? ProtocolDeserializers.readU8(buf) : null;
						Short y = (columns != 0) ? ProtocolDeserializers.readU8(buf) : null;
						BitSet data = (columns != 0) ? BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)])) : null;
						return new packet_map(
							itemDamage,
							scale,
							locked,
							icons_option,
							columns,
							rows,
							x,
							y,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_trade_list extends PacketBase{
				Integer windowId;
				Tuple14<Integer, Integer, Integer, Tuple2<Integer, Object>[], Byte, Object, Object, Boolean, Integer, Integer, Integer, Integer, Float, Integer>[] trades;
				Integer villagerLevel;
				Integer experience;
				Boolean isRegularVillager;
				Boolean canRestock;
				public packet_trade_list(
					Integer windowId,
					Tuple14<Integer, Integer, Integer, Tuple2<Integer, Object>[], Byte, Object, Object, Boolean, Integer, Integer, Integer, Integer, Float, Integer>[] trades,
					Integer villagerLevel,
					Integer experience,
					Boolean isRegularVillager,
					Boolean canRestock
				){
					super(PacketIDs.play_toClient_packet_trade_list.getId());
					this.windowId = windowId;
					this.trades = trades;
					this.villagerLevel = villagerLevel;
					this.experience = experience;
					this.isRegularVillager = isRegularVillager;
					this.canRestock = canRestock;
				}
				public static packet_trade_list readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer windowId = ProtocolDeserializers.readVarInt(buf);
						Tuple14<Integer, Integer, Integer, Tuple2<Integer, Object>[], Byte, Object, Object, Boolean, Integer, Integer, Integer, Integer, Float, Integer>[] trades = ProtocolDeserializers.readVarintPrefixedArr(buf, aux91 ->Tuple14.readFrom(buf, aux92 ->ProtocolDeserializers.readVarInt(buf), aux93 ->ProtocolDeserializers.readVarInt(buf), aux94 ->ProtocolDeserializers.readVarInt(buf), aux95 ->ProtocolDeserializers.readFixedArr(buf, aux96 ->Tuple2.readFrom(buf, aux97 ->ProtocolDeserializers.readVarInt(buf), aux98 ->readSwitchOftype()), Tuple2[]::new, trades_addedComponentCount), aux99 ->ProtocolDeserializers.readI8(buf), aux100 ->readSwitchOfitemCount(), nullNOKNOWNCLASS, aux101 ->ProtocolDeserializers.readBool(buf), aux102 ->ProtocolDeserializers.readI32(buf), aux103 ->ProtocolDeserializers.readI32(buf), aux104 ->ProtocolDeserializers.readI32(buf), aux105 ->ProtocolDeserializers.readI32(buf), aux106 ->ProtocolDeserializers.readF32(buf), aux107 ->ProtocolDeserializers.readI32(buf)), Tuple14[]::new);
						Integer villagerLevel = ProtocolDeserializers.readVarInt(buf);
						Integer experience = ProtocolDeserializers.readVarInt(buf);
						Boolean isRegularVillager = ProtocolDeserializers.readBool(buf);
						Boolean canRestock = ProtocolDeserializers.readBool(buf);
						return new packet_trade_list(
							windowId,
							trades,
							villagerLevel,
							experience,
							isRegularVillager,
							canRestock
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_rel_entity_move extends PacketBase{
				Integer entityId;
				Short dX;
				Short dY;
				Short dZ;
				Boolean onGround;
				public packet_rel_entity_move(
					Integer entityId,
					Short dX,
					Short dY,
					Short dZ,
					Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_rel_entity_move.getId());
					this.entityId = entityId;
					this.dX = dX;
					this.dY = dY;
					this.dZ = dZ;
					this.onGround = onGround;
				}
				public static packet_rel_entity_move readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Short dX = ProtocolDeserializers.readI16(buf);
						Short dY = ProtocolDeserializers.readI16(buf);
						Short dZ = ProtocolDeserializers.readI16(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_rel_entity_move(
							entityId,
							dX,
							dY,
							dZ,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_move_look extends PacketBase{
				Integer entityId;
				Short dX;
				Short dY;
				Short dZ;
				Byte yaw;
				Byte pitch;
				Boolean onGround;
				public packet_entity_move_look(
					Integer entityId,
					Short dX,
					Short dY,
					Short dZ,
					Byte yaw,
					Byte pitch,
					Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_entity_move_look.getId());
					this.entityId = entityId;
					this.dX = dX;
					this.dY = dY;
					this.dZ = dZ;
					this.yaw = yaw;
					this.pitch = pitch;
					this.onGround = onGround;
				}
				public static packet_entity_move_look readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Short dX = ProtocolDeserializers.readI16(buf);
						Short dY = ProtocolDeserializers.readI16(buf);
						Short dZ = ProtocolDeserializers.readI16(buf);
						Byte yaw = ProtocolDeserializers.readI8(buf);
						Byte pitch = ProtocolDeserializers.readI8(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_entity_move_look(
							entityId,
							dX,
							dY,
							dZ,
							yaw,
							pitch,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_look extends PacketBase{
				Integer entityId;
				Byte yaw;
				Byte pitch;
				Boolean onGround;
				public packet_entity_look(
					Integer entityId,
					Byte yaw,
					Byte pitch,
					Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_entity_look.getId());
					this.entityId = entityId;
					this.yaw = yaw;
					this.pitch = pitch;
					this.onGround = onGround;
				}
				public static packet_entity_look readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Byte yaw = ProtocolDeserializers.readI8(buf);
						Byte pitch = ProtocolDeserializers.readI8(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_entity_look(
							entityId,
							yaw,
							pitch,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_vehicle_move extends PacketBase{
				Double x;
				Double y;
				Double z;
				Float yaw;
				Float pitch;
				public packet_vehicle_move(
					Double x,
					Double y,
					Double z,
					Float yaw,
					Float pitch
				){
					super(PacketIDs.play_toClient_packet_vehicle_move.getId());
					this.x = x;
					this.y = y;
					this.z = z;
					this.yaw = yaw;
					this.pitch = pitch;
				}
				public static packet_vehicle_move readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Float yaw = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						return new packet_vehicle_move(
							x,
							y,
							z,
							yaw,
							pitch
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_open_book extends PacketBase{
				Integer hand;
				public packet_open_book(
					Integer hand
				){
					super(PacketIDs.play_toClient_packet_open_book.getId());
					this.hand = hand;
				}
				public static packet_open_book readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer hand = ProtocolDeserializers.readVarInt(buf);
						return new packet_open_book(
							hand
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_open_window extends PacketBase{
				Integer windowId;
				Integer inventoryType;
				String windowTitle;
				public packet_open_window(
					Integer windowId,
					Integer inventoryType,
					String windowTitle
				){
					super(PacketIDs.play_toClient_packet_open_window.getId());
					this.windowId = windowId;
					this.inventoryType = inventoryType;
					this.windowTitle = windowTitle;
				}
				public static packet_open_window readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer windowId = ProtocolDeserializers.readVarInt(buf);
						Integer inventoryType = ProtocolDeserializers.readVarInt(buf);
						String windowTitle = ProtocolDeserializers.readString(buf);
						return new packet_open_window(
							windowId,
							inventoryType,
							windowTitle
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_open_sign_entity extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Boolean isFrontText;
				public packet_open_sign_entity(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Boolean isFrontText
				){
					super(PacketIDs.play_toClient_packet_open_sign_entity.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.isFrontText = isFrontText;
				}
				public static packet_open_sign_entity readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Boolean isFrontText = ProtocolDeserializers.readBool(buf);
						return new packet_open_sign_entity(
							location,
							location_x,
							location_z,
							location_y,
							isFrontText
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_ping extends PacketBase{
				Integer id;
				public packet_ping(
					Integer id
				){
					super(PacketIDs.play_toClient_packet_ping.getId());
					this.id = id;
				}
				public static packet_ping readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer id = ProtocolDeserializers.readI32(buf);
						return new packet_ping(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_ping_response extends PacketBase{
				Long id;
				public packet_ping_response(
					Long id
				){
					super(PacketIDs.play_toClient_packet_ping_response.getId());
					this.id = id;
				}
				public static packet_ping_response readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long id = ProtocolDeserializers.readI64(buf);
						return new packet_ping_response(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_craft_recipe_response extends PacketBase{
				Byte windowId;
				String recipe;
				public packet_craft_recipe_response(
					Byte windowId,
					String recipe
				){
					super(PacketIDs.play_toClient_packet_craft_recipe_response.getId());
					this.windowId = windowId;
					this.recipe = recipe;
				}
				public static packet_craft_recipe_response readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte windowId = ProtocolDeserializers.readI8(buf);
						String recipe = ProtocolDeserializers.readString(buf);
						return new packet_craft_recipe_response(
							windowId,
							recipe
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_abilities extends PacketBase{
				Byte flags;
				Float flyingSpeed;
				Float walkingSpeed;
				public packet_abilities(
					Byte flags,
					Float flyingSpeed,
					Float walkingSpeed
				){
					super(PacketIDs.play_toClient_packet_abilities.getId());
					this.flags = flags;
					this.flyingSpeed = flyingSpeed;
					this.walkingSpeed = walkingSpeed;
				}
				public static packet_abilities readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte flags = ProtocolDeserializers.readI8(buf);
						Float flyingSpeed = ProtocolDeserializers.readF32(buf);
						Float walkingSpeed = ProtocolDeserializers.readF32(buf);
						return new packet_abilities(
							flags,
							flyingSpeed,
							walkingSpeed
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_player_chat extends PacketBase{
				BigInteger senderUuid;
				Integer index;
				//optionthew exception: java.lang.RuntimeException: java.lang.UnsupportedOperationException: Attempting to create a optionwith: [buffer, {count=256}]
				Object signature_option;
				String plainMessage;
				Long timestamp;
				Long salt;
				Tuple2<Integer, Object>[] previousMessages;
				String unsignedChatContent;
				Integer filterType;
				Long[] filterTypeMask;
				Integer type_registryIndex;
				String type_chat_translationKey;
				Integer[] type_chat_parameters;
				String type_chat_style;
				String type_narration_translationKey;
				Integer[] type_narration_parameters;
				String type_narration_style;
				String networkName;
				String networkTargetName;
				public packet_player_chat(
					BigInteger senderUuid,
					Integer index,
					Object signature_option,
					String plainMessage,
					Long timestamp,
					Long salt,
					Tuple2<Integer, Object>[] previousMessages,
					String unsignedChatContent,
					Integer filterType,
					Long[] filterTypeMask,
					Integer type_registryIndex,
					String type_chat_translationKey,
					Integer[] type_chat_parameters,
					String type_chat_style,
					String type_narration_translationKey,
					Integer[] type_narration_parameters,
					String type_narration_style,
					String networkName,
					String networkTargetName
				){
					super(PacketIDs.play_toClient_packet_player_chat.getId());
					this.senderUuid = senderUuid;
					this.index = index;
					this.signature_option = signature_option;
					this.plainMessage = plainMessage;
					this.timestamp = timestamp;
					this.salt = salt;
					this.previousMessages = previousMessages;
					this.unsignedChatContent = unsignedChatContent;
					this.filterType = filterType;
					this.filterTypeMask = filterTypeMask;
					this.type_registryIndex = type_registryIndex;
					this.type_chat_translationKey = type_chat_translationKey;
					this.type_chat_parameters = type_chat_parameters;
					this.type_chat_style = type_chat_style;
					this.type_narration_translationKey = type_narration_translationKey;
					this.type_narration_parameters = type_narration_parameters;
					this.type_narration_style = type_narration_style;
					this.networkName = networkName;
					this.networkTargetName = networkTargetName;
				}
				public static packet_player_chat readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger senderUuid = ProtocolDeserializers.readUUID(buf);
						Integer index = ProtocolDeserializers.readVarInt(buf);
						Object signature_option = nullNOKNOWNCLASS;
						String plainMessage = ProtocolDeserializers.readString(buf);
						Long timestamp = ProtocolDeserializers.readI64(buf);
						Long salt = ProtocolDeserializers.readI64(buf);
						Tuple2<Integer, Object>[] previousMessages = ProtocolDeserializers.readVarintPrefixedArr(buf, aux108 ->Tuple2.readFrom(buf, aux109 ->ProtocolDeserializers.readVarInt(buf), aux110 ->readSwitchOfid()), Tuple2[]::new);
						String unsignedChatContent = ProtocolDeserializers.readString(buf);
						Integer filterType = ProtocolDeserializers.readVarInt(buf);
						Long[] filterTypeMask = (filterType == 2) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux111 ->ProtocolDeserializers.readI64(buf), Long[]::new) : null;
						Integer type_registryIndex = ProtocolDeserializers.readVarInt(buf);
						String type_chat_translationKey = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						Integer[] type_chat_parameters = (type_registryIndex == 0) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux112 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new) : null;
						String type_chat_style = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						String type_narration_translationKey = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						Integer[] type_narration_parameters = (type_registryIndex == 0) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux113 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new) : null;
						String type_narration_style = (type_registryIndex == 0) ? ProtocolDeserializers.readString(buf) : null;
						String networkName = ProtocolDeserializers.readString(buf);
						String networkTargetName = ProtocolDeserializers.readString(buf);
						return new packet_player_chat(
							senderUuid,
							index,
							signature_option,
							plainMessage,
							timestamp,
							salt,
							previousMessages,
							unsignedChatContent,
							filterType,
							filterTypeMask,
							type_registryIndex,
							type_chat_translationKey,
							type_chat_parameters,
							type_chat_style,
							type_narration_translationKey,
							type_narration_parameters,
							type_narration_style,
							networkName,
							networkTargetName
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_end_combat_event extends PacketBase{
				Integer duration;
				public packet_end_combat_event(
					Integer duration
				){
					super(PacketIDs.play_toClient_packet_end_combat_event.getId());
					this.duration = duration;
				}
				public static packet_end_combat_event readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer duration = ProtocolDeserializers.readVarInt(buf);
						return new packet_end_combat_event(
							duration
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_enter_combat_event extends PacketBase{
				public packet_enter_combat_event(
				){
					super(PacketIDs.play_toClient_packet_enter_combat_event.getId());
				}
				public static packet_enter_combat_event readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_enter_combat_event(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_death_combat_event extends PacketBase{
				Integer playerId;
				String message;
				public packet_death_combat_event(
					Integer playerId,
					String message
				){
					super(PacketIDs.play_toClient_packet_death_combat_event.getId());
					this.playerId = playerId;
					this.message = message;
				}
				public static packet_death_combat_event readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer playerId = ProtocolDeserializers.readVarInt(buf);
						String message = ProtocolDeserializers.readString(buf);
						return new packet_death_combat_event(
							playerId,
							message
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_player_remove extends PacketBase{
				BigInteger[] players;
				public packet_player_remove(
					BigInteger[] players
				){
					super(PacketIDs.play_toClient_packet_player_remove.getId());
					this.players = players;
				}
				public static packet_player_remove readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger[] players = ProtocolDeserializers.readVarintPrefixedArr(buf, aux114 ->ProtocolDeserializers.readUUID(buf), BigInteger[]::new);
						return new packet_player_remove(
							players
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_player_info extends PacketBase{
				Byte action;
				Tuple7<BigInteger, Object, Object, Object, Object, Object, Object>[] data;
				public packet_player_info(
					Byte action,
					Tuple7<BigInteger, Object, Object, Object, Object, Object, Object>[] data
				){
					super(PacketIDs.play_toClient_packet_player_info.getId());
					this.action = action;
					this.data = data;
				}
				public static packet_player_info readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte action = ProtocolDeserializers.readI8(buf);
						Tuple7<BigInteger, Object, Object, Object, Object, Object, Object>[] data = ProtocolDeserializers.readVarintPrefixedArr(buf, aux115 ->Tuple7.readFrom(buf, aux116 ->ProtocolDeserializers.readUUID(buf), aux117 ->readSwitchOf../action(), aux118 ->readSwitchOf../action(), aux119 ->readSwitchOf../action(), aux120 ->readSwitchOf../action(), aux121 ->readSwitchOf../action(), aux122 ->readSwitchOf../action()), Tuple7[]::new);
						return new packet_player_info(
							action,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_face_player extends PacketBase{
				Integer feet_eyes;
				Double x;
				Double y;
				Double z;
				Boolean isEntity;
				Integer entityId;
				Integer entity_feet_eyes;
				public packet_face_player(
					Integer feet_eyes,
					Double x,
					Double y,
					Double z,
					Boolean isEntity,
					Integer entityId,
					Integer entity_feet_eyes
				){
					super(PacketIDs.play_toClient_packet_face_player.getId());
					this.feet_eyes = feet_eyes;
					this.x = x;
					this.y = y;
					this.z = z;
					this.isEntity = isEntity;
					this.entityId = entityId;
					this.entity_feet_eyes = entity_feet_eyes;
				}
				public static packet_face_player readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer feet_eyes = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Boolean isEntity = ProtocolDeserializers.readBool(buf);
						Integer entityId = (isEntity == isEntity_map.get(true) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer entity_feet_eyes = (isEntity == isEntity_map.get(true) ? ProtocolDeserializers.readVarInt(buf) : null;
						return new packet_face_player(
							feet_eyes,
							x,
							y,
							z,
							isEntity,
							entityId,
							entity_feet_eyes
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_position extends PacketBase{
				Double x;
				Double y;
				Double z;
				Float yaw;
				Float pitch;
				Byte flags;
				Integer teleportId;
				public packet_position(
					Double x,
					Double y,
					Double z,
					Float yaw,
					Float pitch,
					Byte flags,
					Integer teleportId
				){
					super(PacketIDs.play_toClient_packet_position.getId());
					this.x = x;
					this.y = y;
					this.z = z;
					this.yaw = yaw;
					this.pitch = pitch;
					this.flags = flags;
					this.teleportId = teleportId;
				}
				public static packet_position readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Float yaw = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						Byte flags = ProtocolDeserializers.readI8(buf);
						Integer teleportId = ProtocolDeserializers.readVarInt(buf);
						return new packet_position(
							x,
							y,
							z,
							yaw,
							pitch,
							flags,
							teleportId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_unlock_recipes extends PacketBase{
				Integer action;
				Boolean craftingBookOpen;
				Boolean filteringCraftable;
				Boolean smeltingBookOpen;
				Boolean filteringSmeltable;
				Boolean blastFurnaceOpen;
				Boolean filteringBlastFurnace;
				Boolean smokerBookOpen;
				Boolean filteringSmoker;
				String[] recipes1;
				String[] recipes2;
				public packet_unlock_recipes(
					Integer action,
					Boolean craftingBookOpen,
					Boolean filteringCraftable,
					Boolean smeltingBookOpen,
					Boolean filteringSmeltable,
					Boolean blastFurnaceOpen,
					Boolean filteringBlastFurnace,
					Boolean smokerBookOpen,
					Boolean filteringSmoker,
					String[] recipes1,
					String[] recipes2
				){
					super(PacketIDs.play_toClient_packet_unlock_recipes.getId());
					this.action = action;
					this.craftingBookOpen = craftingBookOpen;
					this.filteringCraftable = filteringCraftable;
					this.smeltingBookOpen = smeltingBookOpen;
					this.filteringSmeltable = filteringSmeltable;
					this.blastFurnaceOpen = blastFurnaceOpen;
					this.filteringBlastFurnace = filteringBlastFurnace;
					this.smokerBookOpen = smokerBookOpen;
					this.filteringSmoker = filteringSmoker;
					this.recipes1 = recipes1;
					this.recipes2 = recipes2;
				}
				public static packet_unlock_recipes readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer action = ProtocolDeserializers.readVarInt(buf);
						Boolean craftingBookOpen = ProtocolDeserializers.readBool(buf);
						Boolean filteringCraftable = ProtocolDeserializers.readBool(buf);
						Boolean smeltingBookOpen = ProtocolDeserializers.readBool(buf);
						Boolean filteringSmeltable = ProtocolDeserializers.readBool(buf);
						Boolean blastFurnaceOpen = ProtocolDeserializers.readBool(buf);
						Boolean filteringBlastFurnace = ProtocolDeserializers.readBool(buf);
						Boolean smokerBookOpen = ProtocolDeserializers.readBool(buf);
						Boolean filteringSmoker = ProtocolDeserializers.readBool(buf);
						String[] recipes1 = ProtocolDeserializers.readVarintPrefixedArr(buf, aux123 ->ProtocolDeserializers.readString(buf), String[]::new);
						String[] recipes2 = (action == 0) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux124 ->ProtocolDeserializers.readString(buf), String[]::new) : null;
						return new packet_unlock_recipes(
							action,
							craftingBookOpen,
							filteringCraftable,
							smeltingBookOpen,
							filteringSmeltable,
							blastFurnaceOpen,
							filteringBlastFurnace,
							smokerBookOpen,
							filteringSmoker,
							recipes1,
							recipes2
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_destroy extends PacketBase{
				Integer[] entityIds;
				public packet_entity_destroy(
					Integer[] entityIds
				){
					super(PacketIDs.play_toClient_packet_entity_destroy.getId());
					this.entityIds = entityIds;
				}
				public static packet_entity_destroy readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer[] entityIds = ProtocolDeserializers.readVarintPrefixedArr(buf, aux125 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new);
						return new packet_entity_destroy(
							entityIds
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_remove_entity_effect extends PacketBase{
				Integer entityId;
				Integer effectId;
				public packet_remove_entity_effect(
					Integer entityId,
					Integer effectId
				){
					super(PacketIDs.play_toClient_packet_remove_entity_effect.getId());
					this.entityId = entityId;
					this.effectId = effectId;
				}
				public static packet_remove_entity_effect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Integer effectId = ProtocolDeserializers.readVarInt(buf);
						return new packet_remove_entity_effect(
							entityId,
							effectId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_reset_score extends PacketBase{
				String entity_name;
				String objective_name;
				public packet_reset_score(
					String entity_name,
					String objective_name
				){
					super(PacketIDs.play_toClient_packet_reset_score.getId());
					this.entity_name = entity_name;
					this.objective_name = objective_name;
				}
				public static packet_reset_score readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String entity_name = ProtocolDeserializers.readString(buf);
						String objective_name = ProtocolDeserializers.readString(buf);
						return new packet_reset_score(
							entity_name,
							objective_name
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_remove_resource_pack extends PacketBase{
				BigInteger uuid;
				public packet_remove_resource_pack(
					BigInteger uuid
				){
					super(PacketIDs.play_toClient_packet_remove_resource_pack.getId());
					this.uuid = uuid;
				}
				public static packet_remove_resource_pack readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						return new packet_remove_resource_pack(
							uuid
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_add_resource_pack extends PacketBase{
				BigInteger uuid;
				String url;
				String hash;
				Boolean forced;
				String promptMessage;
				public packet_add_resource_pack(
					BigInteger uuid,
					String url,
					String hash,
					Boolean forced,
					String promptMessage
				){
					super(PacketIDs.play_toClient_packet_add_resource_pack.getId());
					this.uuid = uuid;
					this.url = url;
					this.hash = hash;
					this.forced = forced;
					this.promptMessage = promptMessage;
				}
				public static packet_add_resource_pack readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						String url = ProtocolDeserializers.readString(buf);
						String hash = ProtocolDeserializers.readString(buf);
						Boolean forced = ProtocolDeserializers.readBool(buf);
						String promptMessage = ProtocolDeserializers.readString(buf);
						return new packet_add_resource_pack(
							uuid,
							url,
							hash,
							forced,
							promptMessage
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_respawn extends PacketBase{
				Integer worldState_dimension;
				String worldState_name;
				Long worldState_hashedSeed;
				Byte worldState_gamemode;
				Map<String, Integer> worldState_gamemode_map;
				Short worldState_previousGamemode;
				Boolean worldState_isDebug;
				Boolean worldState_isFlat;
				//optionthew exception: java.lang.RuntimeException: java.lang.UnsupportedOperationException: Attempting to create a optionwith: [container, [{name=dimensionName, type=string}, {name=location, type=position}]]
				Object worldState_death_option;
				Integer worldState_portalCooldown;
				Short copyMetadata;
				public packet_respawn(
					Integer worldState_dimension,
					String worldState_name,
					Long worldState_hashedSeed,
					Byte worldState_gamemode,
					Map<String, Integer> worldState_gamemode_map,
					Short worldState_previousGamemode,
					Boolean worldState_isDebug,
					Boolean worldState_isFlat,
					Object worldState_death_option,
					Integer worldState_portalCooldown,
					Short copyMetadata
				){
					super(PacketIDs.play_toClient_packet_respawn.getId());
					this.worldState_dimension = worldState_dimension;
					this.worldState_name = worldState_name;
					this.worldState_hashedSeed = worldState_hashedSeed;
					this.worldState_gamemode = worldState_gamemode;
					this.worldState_gamemode_map = worldState_gamemode_map;
					this.worldState_previousGamemode = worldState_previousGamemode;
					this.worldState_isDebug = worldState_isDebug;
					this.worldState_isFlat = worldState_isFlat;
					this.worldState_death_option = worldState_death_option;
					this.worldState_portalCooldown = worldState_portalCooldown;
					this.copyMetadata = copyMetadata;
				}
				public static packet_respawn readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer worldState_dimension = ProtocolDeserializers.readVarInt(buf);
						String worldState_name = ProtocolDeserializers.readString(buf);
						Long worldState_hashedSeed = ProtocolDeserializers.readI64(buf);
						Byte worldState_gamemode = ProtocolDeserializers.readI8(buf);
						Map<String, Integer> worldState_gamemode_map = Map.ofEntries(Map.entry("survival",0), Map.entry("creative",1), Map.entry("adventure",2), Map.entry("spectator",3));
						Short worldState_previousGamemode = ProtocolDeserializers.readU8(buf);
						Boolean worldState_isDebug = ProtocolDeserializers.readBool(buf);
						Boolean worldState_isFlat = ProtocolDeserializers.readBool(buf);
						Object worldState_death_option = nullNOKNOWNCLASS;
						Integer worldState_portalCooldown = ProtocolDeserializers.readVarInt(buf);
						Short copyMetadata = ProtocolDeserializers.readU8(buf);
						return new packet_respawn(
							worldState_dimension,
							worldState_name,
							worldState_hashedSeed,
							worldState_gamemode,
							worldState_gamemode_map,
							worldState_previousGamemode,
							worldState_isDebug,
							worldState_isFlat,
							worldState_death_option,
							worldState_portalCooldown,
							copyMetadata
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_head_rotation extends PacketBase{
				Integer entityId;
				Byte headYaw;
				public packet_entity_head_rotation(
					Integer entityId,
					Byte headYaw
				){
					super(PacketIDs.play_toClient_packet_entity_head_rotation.getId());
					this.entityId = entityId;
					this.headYaw = headYaw;
				}
				public static packet_entity_head_rotation readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Byte headYaw = ProtocolDeserializers.readI8(buf);
						return new packet_entity_head_rotation(
							entityId,
							headYaw
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_multi_block_change extends PacketBase{
				Long chunkCoordinates;
				//bitfield of size: 22
				Integer chunkCoordinates_x;
				//bitfield of size: 22
				Integer chunkCoordinates_z;
				//bitfield of size: 20
				Integer chunkCoordinates_y;
				Integer[] records;
				public packet_multi_block_change(
					Long chunkCoordinates,
					Integer chunkCoordinates_x,
					Integer chunkCoordinates_z,
					Integer chunkCoordinates_y,
					Integer[] records
				){
					super(PacketIDs.play_toClient_packet_multi_block_change.getId());
					this.chunkCoordinates = chunkCoordinates;
					this.chunkCoordinates_x = chunkCoordinates_x;
					this.chunkCoordinates_z = chunkCoordinates_z;
					this.chunkCoordinates_y = chunkCoordinates_y;
					this.records = records;
				}
				public static packet_multi_block_change readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long chunkCoordinates = ProtocolDeserializers.readI64(buf);
						Integer chunkCoordinates_x = (int)(chunkCoordinates & 1);
						Integer chunkCoordinates_z = (int)(chunkCoordinates & 1);
						Integer chunkCoordinates_y = (int)(chunkCoordinates & 1);
						Integer[] records = ProtocolDeserializers.readVarintPrefixedArr(buf, aux126 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new);
						return new packet_multi_block_change(
							chunkCoordinates,
							chunkCoordinates_x,
							chunkCoordinates_z,
							chunkCoordinates_y,
							records
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_select_advancement_tab extends PacketBase{
				String id;
				public packet_select_advancement_tab(
					String id
				){
					super(PacketIDs.play_toClient_packet_select_advancement_tab.getId());
					this.id = id;
				}
				public static packet_select_advancement_tab readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String id = ProtocolDeserializers.readString(buf);
						return new packet_select_advancement_tab(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_server_data extends PacketBase{
				String motd;
				BitSet iconBytes;
				public packet_server_data(
					String motd,
					BitSet iconBytes
				){
					super(PacketIDs.play_toClient_packet_server_data.getId());
					this.motd = motd;
					this.iconBytes = iconBytes;
				}
				public static packet_server_data readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String motd = ProtocolDeserializers.readString(buf);
						BitSet iconBytes = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						return new packet_server_data(
							motd,
							iconBytes
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_action_bar extends PacketBase{
				String text;
				public packet_action_bar(
					String text
				){
					super(PacketIDs.play_toClient_packet_action_bar.getId());
					this.text = text;
				}
				public static packet_action_bar readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String text = ProtocolDeserializers.readString(buf);
						return new packet_action_bar(
							text
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_border_center extends PacketBase{
				Double x;
				Double z;
				public packet_world_border_center(
					Double x,
					Double z
				){
					super(PacketIDs.play_toClient_packet_world_border_center.getId());
					this.x = x;
					this.z = z;
				}
				public static packet_world_border_center readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						return new packet_world_border_center(
							x,
							z
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_border_lerp_size extends PacketBase{
				Double oldDiameter;
				Double newDiameter;
				Integer speed;
				public packet_world_border_lerp_size(
					Double oldDiameter,
					Double newDiameter,
					Integer speed
				){
					super(PacketIDs.play_toClient_packet_world_border_lerp_size.getId());
					this.oldDiameter = oldDiameter;
					this.newDiameter = newDiameter;
					this.speed = speed;
				}
				public static packet_world_border_lerp_size readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double oldDiameter = ProtocolDeserializers.readF64(buf);
						Double newDiameter = ProtocolDeserializers.readF64(buf);
						Integer speed = ProtocolDeserializers.readVarInt(buf);
						return new packet_world_border_lerp_size(
							oldDiameter,
							newDiameter,
							speed
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_border_size extends PacketBase{
				Double diameter;
				public packet_world_border_size(
					Double diameter
				){
					super(PacketIDs.play_toClient_packet_world_border_size.getId());
					this.diameter = diameter;
				}
				public static packet_world_border_size readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double diameter = ProtocolDeserializers.readF64(buf);
						return new packet_world_border_size(
							diameter
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_border_warning_delay extends PacketBase{
				Integer warningTime;
				public packet_world_border_warning_delay(
					Integer warningTime
				){
					super(PacketIDs.play_toClient_packet_world_border_warning_delay.getId());
					this.warningTime = warningTime;
				}
				public static packet_world_border_warning_delay readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer warningTime = ProtocolDeserializers.readVarInt(buf);
						return new packet_world_border_warning_delay(
							warningTime
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_world_border_warning_reach extends PacketBase{
				Integer warningBlocks;
				public packet_world_border_warning_reach(
					Integer warningBlocks
				){
					super(PacketIDs.play_toClient_packet_world_border_warning_reach.getId());
					this.warningBlocks = warningBlocks;
				}
				public static packet_world_border_warning_reach readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer warningBlocks = ProtocolDeserializers.readVarInt(buf);
						return new packet_world_border_warning_reach(
							warningBlocks
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_camera extends PacketBase{
				Integer cameraId;
				public packet_camera(
					Integer cameraId
				){
					super(PacketIDs.play_toClient_packet_camera.getId());
					this.cameraId = cameraId;
				}
				public static packet_camera readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer cameraId = ProtocolDeserializers.readVarInt(buf);
						return new packet_camera(
							cameraId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_held_item_slot extends PacketBase{
				Byte slot;
				public packet_held_item_slot(
					Byte slot
				){
					super(PacketIDs.play_toClient_packet_held_item_slot.getId());
					this.slot = slot;
				}
				public static packet_held_item_slot readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte slot = ProtocolDeserializers.readI8(buf);
						return new packet_held_item_slot(
							slot
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_view_position extends PacketBase{
				Integer chunkX;
				Integer chunkZ;
				public packet_update_view_position(
					Integer chunkX,
					Integer chunkZ
				){
					super(PacketIDs.play_toClient_packet_update_view_position.getId());
					this.chunkX = chunkX;
					this.chunkZ = chunkZ;
				}
				public static packet_update_view_position readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer chunkX = ProtocolDeserializers.readVarInt(buf);
						Integer chunkZ = ProtocolDeserializers.readVarInt(buf);
						return new packet_update_view_position(
							chunkX,
							chunkZ
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_view_distance extends PacketBase{
				Integer viewDistance;
				public packet_update_view_distance(
					Integer viewDistance
				){
					super(PacketIDs.play_toClient_packet_update_view_distance.getId());
					this.viewDistance = viewDistance;
				}
				public static packet_update_view_distance readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer viewDistance = ProtocolDeserializers.readVarInt(buf);
						return new packet_update_view_distance(
							viewDistance
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_spawn_position extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Float angle;
				public packet_spawn_position(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Float angle
				){
					super(PacketIDs.play_toClient_packet_spawn_position.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.angle = angle;
				}
				public static packet_spawn_position readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Float angle = ProtocolDeserializers.readF32(buf);
						return new packet_spawn_position(
							location,
							location_x,
							location_z,
							location_y,
							angle
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_scoreboard_display_objective extends PacketBase{
				Integer position;
				String name;
				public packet_scoreboard_display_objective(
					Integer position,
					String name
				){
					super(PacketIDs.play_toClient_packet_scoreboard_display_objective.getId());
					this.position = position;
					this.name = name;
				}
				public static packet_scoreboard_display_objective readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer position = ProtocolDeserializers.readVarInt(buf);
						String name = ProtocolDeserializers.readString(buf);
						return new packet_scoreboard_display_objective(
							position,
							name
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_metadata extends PacketBase{
				Integer entityId;
				//Unresolved buildable entityMetadataLoop
				Object metadata_entityMetadataLoop;
				public packet_entity_metadata(
					Integer entityId,
					Object metadata_entityMetadataLoop
				){
					super(PacketIDs.play_toClient_packet_entity_metadata.getId());
					this.entityId = entityId;
					this.metadata_entityMetadataLoop = metadata_entityMetadataLoop;
				}
				public static packet_entity_metadata readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Object metadata_entityMetadataLoop = "nullNOKNOWNCLASS@FlattenableBuilder";
						return new packet_entity_metadata(
							entityId,
							metadata_entityMetadataLoop
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_attach_entity extends PacketBase{
				Integer entityId;
				Integer vehicleId;
				public packet_attach_entity(
					Integer entityId,
					Integer vehicleId
				){
					super(PacketIDs.play_toClient_packet_attach_entity.getId());
					this.entityId = entityId;
					this.vehicleId = vehicleId;
				}
				public static packet_attach_entity readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readI32(buf);
						Integer vehicleId = ProtocolDeserializers.readI32(buf);
						return new packet_attach_entity(
							entityId,
							vehicleId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_velocity extends PacketBase{
				Integer entityId;
				Short velocityX;
				Short velocityY;
				Short velocityZ;
				public packet_entity_velocity(
					Integer entityId,
					Short velocityX,
					Short velocityY,
					Short velocityZ
				){
					super(PacketIDs.play_toClient_packet_entity_velocity.getId());
					this.entityId = entityId;
					this.velocityX = velocityX;
					this.velocityY = velocityY;
					this.velocityZ = velocityZ;
				}
				public static packet_entity_velocity readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Short velocityX = ProtocolDeserializers.readI16(buf);
						Short velocityY = ProtocolDeserializers.readI16(buf);
						Short velocityZ = ProtocolDeserializers.readI16(buf);
						return new packet_entity_velocity(
							entityId,
							velocityX,
							velocityY,
							velocityZ
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_equipment extends PacketBase{
				Integer entityId;
				//Unresolved buildable topBitSetTerminatedArray
				Object equipments_topBitSetTerminatedArray;
				public packet_entity_equipment(
					Integer entityId,
					Object equipments_topBitSetTerminatedArray
				){
					super(PacketIDs.play_toClient_packet_entity_equipment.getId());
					this.entityId = entityId;
					this.equipments_topBitSetTerminatedArray = equipments_topBitSetTerminatedArray;
				}
				public static packet_entity_equipment readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Object equipments_topBitSetTerminatedArray = "nullNOKNOWNCLASS@FlattenableBuilder";
						return new packet_entity_equipment(
							entityId,
							equipments_topBitSetTerminatedArray
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_experience extends PacketBase{
				Float experienceBar;
				Integer level;
				Integer totalExperience;
				public packet_experience(
					Float experienceBar,
					Integer level,
					Integer totalExperience
				){
					super(PacketIDs.play_toClient_packet_experience.getId());
					this.experienceBar = experienceBar;
					this.level = level;
					this.totalExperience = totalExperience;
				}
				public static packet_experience readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Float experienceBar = ProtocolDeserializers.readF32(buf);
						Integer level = ProtocolDeserializers.readVarInt(buf);
						Integer totalExperience = ProtocolDeserializers.readVarInt(buf);
						return new packet_experience(
							experienceBar,
							level,
							totalExperience
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_health extends PacketBase{
				Float health;
				Integer food;
				Float foodSaturation;
				public packet_update_health(
					Float health,
					Integer food,
					Float foodSaturation
				){
					super(PacketIDs.play_toClient_packet_update_health.getId());
					this.health = health;
					this.food = food;
					this.foodSaturation = foodSaturation;
				}
				public static packet_update_health readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Float health = ProtocolDeserializers.readF32(buf);
						Integer food = ProtocolDeserializers.readVarInt(buf);
						Float foodSaturation = ProtocolDeserializers.readF32(buf);
						return new packet_update_health(
							health,
							food,
							foodSaturation
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_scoreboard_objective extends PacketBase{
				String name;
				Byte action;
				String displayText;
				Integer type;
				Integer number_format;
				String styling;
				public packet_scoreboard_objective(
					String name,
					Byte action,
					String displayText,
					Integer type,
					Integer number_format,
					String styling
				){
					super(PacketIDs.play_toClient_packet_scoreboard_objective.getId());
					this.name = name;
					this.action = action;
					this.displayText = displayText;
					this.type = type;
					this.number_format = number_format;
					this.styling = styling;
				}
				public static packet_scoreboard_objective readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String name = ProtocolDeserializers.readString(buf);
						Byte action = ProtocolDeserializers.readI8(buf);
						String displayText = (action == 0) || (action == 2) ? ProtocolDeserializers.readString(buf) : null;
						Integer type = (action == 0) || (action == 2) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer number_format = (action == 0) || (action == 2) ? ProtocolDeserializers.readVarInt(buf) : null;
						String styling = (action == 0) || (action == 2) ? (styling_number_format == 1) || (styling_number_format == 2) ? ProtocolDeserializers.readString(buf) : null : null;
						return new packet_scoreboard_objective(
							name,
							action,
							displayText,
							type,
							number_format,
							styling
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_passengers extends PacketBase{
				Integer entityId;
				Integer[] passengers;
				public packet_set_passengers(
					Integer entityId,
					Integer[] passengers
				){
					super(PacketIDs.play_toClient_packet_set_passengers.getId());
					this.entityId = entityId;
					this.passengers = passengers;
				}
				public static packet_set_passengers readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Integer[] passengers = ProtocolDeserializers.readVarintPrefixedArr(buf, aux127 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new);
						return new packet_set_passengers(
							entityId,
							passengers
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_teams extends PacketBase{
				String team;
				Byte mode;
				String name;
				Byte friendlyFire;
				String nameTagVisibility;
				String collisionRule;
				Integer formatting;
				String prefix;
				String suffix;
				String[] players;
				public packet_teams(
					String team,
					Byte mode,
					String name,
					Byte friendlyFire,
					String nameTagVisibility,
					String collisionRule,
					Integer formatting,
					String prefix,
					String suffix,
					String[] players
				){
					super(PacketIDs.play_toClient_packet_teams.getId());
					this.team = team;
					this.mode = mode;
					this.name = name;
					this.friendlyFire = friendlyFire;
					this.nameTagVisibility = nameTagVisibility;
					this.collisionRule = collisionRule;
					this.formatting = formatting;
					this.prefix = prefix;
					this.suffix = suffix;
					this.players = players;
				}
				public static packet_teams readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String team = ProtocolDeserializers.readString(buf);
						Byte mode = ProtocolDeserializers.readI8(buf);
						String name = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readString(buf) : null;
						Byte friendlyFire = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readI8(buf) : null;
						String nameTagVisibility = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readString(buf) : null;
						String collisionRule = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readString(buf) : null;
						Integer formatting = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readVarInt(buf) : null;
						String prefix = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readString(buf) : null;
						String suffix = (mode == 0) || (mode == 2) ? ProtocolDeserializers.readString(buf) : null;
						String[] players = (mode == 0) || (mode == 3) || (mode == 4) ? ProtocolDeserializers.readVarintPrefixedArr(buf, aux128 ->ProtocolDeserializers.readString(buf), String[]::new) : null;
						return new packet_teams(
							team,
							mode,
							name,
							friendlyFire,
							nameTagVisibility,
							collisionRule,
							formatting,
							prefix,
							suffix,
							players
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_scoreboard_score extends PacketBase{
				String itemName;
				String scoreName;
				Integer value;
				String display_name;
				Integer number_format;
				String styling;
				public packet_scoreboard_score(
					String itemName,
					String scoreName,
					Integer value,
					String display_name,
					Integer number_format,
					String styling
				){
					super(PacketIDs.play_toClient_packet_scoreboard_score.getId());
					this.itemName = itemName;
					this.scoreName = scoreName;
					this.value = value;
					this.display_name = display_name;
					this.number_format = number_format;
					this.styling = styling;
				}
				public static packet_scoreboard_score readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String itemName = ProtocolDeserializers.readString(buf);
						String scoreName = ProtocolDeserializers.readString(buf);
						Integer value = ProtocolDeserializers.readVarInt(buf);
						String display_name = ProtocolDeserializers.readString(buf);
						Integer number_format = ProtocolDeserializers.readVarInt(buf);
						String styling = (number_format == 1) || (number_format == 2) ? ProtocolDeserializers.readString(buf) : null;
						return new packet_scoreboard_score(
							itemName,
							scoreName,
							value,
							display_name,
							number_format,
							styling
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_simulation_distance extends PacketBase{
				Integer distance;
				public packet_simulation_distance(
					Integer distance
				){
					super(PacketIDs.play_toClient_packet_simulation_distance.getId());
					this.distance = distance;
				}
				public static packet_simulation_distance readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer distance = ProtocolDeserializers.readVarInt(buf);
						return new packet_simulation_distance(
							distance
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_title_subtitle extends PacketBase{
				String text;
				public packet_set_title_subtitle(
					String text
				){
					super(PacketIDs.play_toClient_packet_set_title_subtitle.getId());
					this.text = text;
				}
				public static packet_set_title_subtitle readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String text = ProtocolDeserializers.readString(buf);
						return new packet_set_title_subtitle(
							text
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_time extends PacketBase{
				Long age;
				Long time;
				public packet_update_time(
					Long age,
					Long time
				){
					super(PacketIDs.play_toClient_packet_update_time.getId());
					this.age = age;
					this.time = time;
				}
				public static packet_update_time readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long age = ProtocolDeserializers.readI64(buf);
						Long time = ProtocolDeserializers.readI64(buf);
						return new packet_update_time(
							age,
							time
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_title_text extends PacketBase{
				String text;
				public packet_set_title_text(
					String text
				){
					super(PacketIDs.play_toClient_packet_set_title_text.getId());
					this.text = text;
				}
				public static packet_set_title_text readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String text = ProtocolDeserializers.readString(buf);
						return new packet_set_title_text(
							text
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_title_time extends PacketBase{
				Integer fadeIn;
				Integer stay;
				Integer fadeOut;
				public packet_set_title_time(
					Integer fadeIn,
					Integer stay,
					Integer fadeOut
				){
					super(PacketIDs.play_toClient_packet_set_title_time.getId());
					this.fadeIn = fadeIn;
					this.stay = stay;
					this.fadeOut = fadeOut;
				}
				public static packet_set_title_time readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer fadeIn = ProtocolDeserializers.readI32(buf);
						Integer stay = ProtocolDeserializers.readI32(buf);
						Integer fadeOut = ProtocolDeserializers.readI32(buf);
						return new packet_set_title_time(
							fadeIn,
							stay,
							fadeOut
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_sound_effect extends PacketBase{
				Integer soundId;
				String soundEvent_resource;
				Float soundEvent_range;
				Integer soundCategory;
				Map<String, Integer> soundCategory_map;
				Integer entityId;
				Float volume;
				Float pitch;
				Long seed;
				public packet_entity_sound_effect(
					Integer soundId,
					String soundEvent_resource,
					Float soundEvent_range,
					Integer soundCategory,
					Map<String, Integer> soundCategory_map,
					Integer entityId,
					Float volume,
					Float pitch,
					Long seed
				){
					super(PacketIDs.play_toClient_packet_entity_sound_effect.getId());
					this.soundId = soundId;
					this.soundEvent_resource = soundEvent_resource;
					this.soundEvent_range = soundEvent_range;
					this.soundCategory = soundCategory;
					this.soundCategory_map = soundCategory_map;
					this.entityId = entityId;
					this.volume = volume;
					this.pitch = pitch;
					this.seed = seed;
				}
				public static packet_entity_sound_effect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer soundId = ProtocolDeserializers.readVarInt(buf);
						String soundEvent_resource = (soundId == 0) ? ProtocolDeserializers.readString(buf) : null;
						Float soundEvent_range = (soundId == 0) ? ProtocolDeserializers.readF32(buf) : null;
						Integer soundCategory = ProtocolDeserializers.readVarInt(buf);
						Map<String, Integer> soundCategory_map = Map.ofEntries(Map.entry("master",0), Map.entry("music",1), Map.entry("record",2), Map.entry("weather",3), Map.entry("block",4), Map.entry("hostile",5), Map.entry("neutral",6), Map.entry("player",7), Map.entry("ambient",8), Map.entry("voice",9));
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Float volume = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						Long seed = ProtocolDeserializers.readI64(buf);
						return new packet_entity_sound_effect(
							soundId,
							soundEvent_resource,
							soundEvent_range,
							soundCategory,
							soundCategory_map,
							entityId,
							volume,
							pitch,
							seed
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_sound_effect extends PacketBase{
				Integer soundId;
				String soundEvent_resource;
				Float soundEvent_range;
				Integer soundCategory;
				Map<String, Integer> soundCategory_map;
				Integer x;
				Integer y;
				Integer z;
				Float volume;
				Float pitch;
				Long seed;
				public packet_sound_effect(
					Integer soundId,
					String soundEvent_resource,
					Float soundEvent_range,
					Integer soundCategory,
					Map<String, Integer> soundCategory_map,
					Integer x,
					Integer y,
					Integer z,
					Float volume,
					Float pitch,
					Long seed
				){
					super(PacketIDs.play_toClient_packet_sound_effect.getId());
					this.soundId = soundId;
					this.soundEvent_resource = soundEvent_resource;
					this.soundEvent_range = soundEvent_range;
					this.soundCategory = soundCategory;
					this.soundCategory_map = soundCategory_map;
					this.x = x;
					this.y = y;
					this.z = z;
					this.volume = volume;
					this.pitch = pitch;
					this.seed = seed;
				}
				public static packet_sound_effect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer soundId = ProtocolDeserializers.readVarInt(buf);
						String soundEvent_resource = (soundId == 0) ? ProtocolDeserializers.readString(buf) : null;
						Float soundEvent_range = (soundId == 0) ? ProtocolDeserializers.readF32(buf) : null;
						Integer soundCategory = ProtocolDeserializers.readVarInt(buf);
						Map<String, Integer> soundCategory_map = Map.ofEntries(Map.entry("master",0), Map.entry("music",1), Map.entry("record",2), Map.entry("weather",3), Map.entry("block",4), Map.entry("hostile",5), Map.entry("neutral",6), Map.entry("player",7), Map.entry("ambient",8), Map.entry("voice",9));
						Integer x = ProtocolDeserializers.readI32(buf);
						Integer y = ProtocolDeserializers.readI32(buf);
						Integer z = ProtocolDeserializers.readI32(buf);
						Float volume = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						Long seed = ProtocolDeserializers.readI64(buf);
						return new packet_sound_effect(
							soundId,
							soundEvent_resource,
							soundEvent_range,
							soundCategory,
							soundCategory_map,
							x,
							y,
							z,
							volume,
							pitch,
							seed
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_start_configuration extends PacketBase{
				public packet_start_configuration(
				){
					super(PacketIDs.play_toClient_packet_start_configuration.getId());
				}
				public static packet_start_configuration readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_start_configuration(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_stop_sound extends PacketBase{
				Byte flags;
				Integer source;
				String sound;
				public packet_stop_sound(
					Byte flags,
					Integer source,
					String sound
				){
					super(PacketIDs.play_toClient_packet_stop_sound.getId());
					this.flags = flags;
					this.source = source;
					this.sound = sound;
				}
				public static packet_stop_sound readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte flags = ProtocolDeserializers.readI8(buf);
						Integer source = (flags == 1) || (flags == 3) ? ProtocolDeserializers.readVarInt(buf) : null;
						String sound = (flags == 2) || (flags == 3) ? ProtocolDeserializers.readString(buf) : null;
						return new packet_stop_sound(
							flags,
							source,
							sound
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_system_chat extends PacketBase{
				String content;
				Boolean isActionBar;
				public packet_system_chat(
					String content,
					Boolean isActionBar
				){
					super(PacketIDs.play_toClient_packet_system_chat.getId());
					this.content = content;
					this.isActionBar = isActionBar;
				}
				public static packet_system_chat readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String content = ProtocolDeserializers.readString(buf);
						Boolean isActionBar = ProtocolDeserializers.readBool(buf);
						return new packet_system_chat(
							content,
							isActionBar
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_playerlist_header extends PacketBase{
				String header;
				String footer;
				public packet_playerlist_header(
					String header,
					String footer
				){
					super(PacketIDs.play_toClient_packet_playerlist_header.getId());
					this.header = header;
					this.footer = footer;
				}
				public static packet_playerlist_header readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String header = ProtocolDeserializers.readString(buf);
						String footer = ProtocolDeserializers.readString(buf);
						return new packet_playerlist_header(
							header,
							footer
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_nbt_query_response extends PacketBase{
				Integer transactionId;
				String nbt;
				public packet_nbt_query_response(
					Integer transactionId,
					String nbt
				){
					super(PacketIDs.play_toClient_packet_nbt_query_response.getId());
					this.transactionId = transactionId;
					this.nbt = nbt;
				}
				public static packet_nbt_query_response readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer transactionId = ProtocolDeserializers.readVarInt(buf);
						String nbt = ProtocolDeserializers.readString(buf);
						return new packet_nbt_query_response(
							transactionId,
							nbt
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_collect extends PacketBase{
				Integer collectedEntityId;
				Integer collectorEntityId;
				Integer pickupItemCount;
				public packet_collect(
					Integer collectedEntityId,
					Integer collectorEntityId,
					Integer pickupItemCount
				){
					super(PacketIDs.play_toClient_packet_collect.getId());
					this.collectedEntityId = collectedEntityId;
					this.collectorEntityId = collectorEntityId;
					this.pickupItemCount = pickupItemCount;
				}
				public static packet_collect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer collectedEntityId = ProtocolDeserializers.readVarInt(buf);
						Integer collectorEntityId = ProtocolDeserializers.readVarInt(buf);
						Integer pickupItemCount = ProtocolDeserializers.readVarInt(buf);
						return new packet_collect(
							collectedEntityId,
							collectorEntityId,
							pickupItemCount
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_teleport extends PacketBase{
				Integer entityId;
				Double x;
				Double y;
				Double z;
				Byte yaw;
				Byte pitch;
				Boolean onGround;
				public packet_entity_teleport(
					Integer entityId,
					Double x,
					Double y,
					Double z,
					Byte yaw,
					Byte pitch,
					Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_entity_teleport.getId());
					this.entityId = entityId;
					this.x = x;
					this.y = y;
					this.z = z;
					this.yaw = yaw;
					this.pitch = pitch;
					this.onGround = onGround;
				}
				public static packet_entity_teleport readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Byte yaw = ProtocolDeserializers.readI8(buf);
						Byte pitch = ProtocolDeserializers.readI8(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_entity_teleport(
							entityId,
							x,
							y,
							z,
							yaw,
							pitch,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_ticking_state extends PacketBase{
				Float tick_rate;
				Boolean is_frozen;
				public packet_set_ticking_state(
					Float tick_rate,
					Boolean is_frozen
				){
					super(PacketIDs.play_toClient_packet_set_ticking_state.getId());
					this.tick_rate = tick_rate;
					this.is_frozen = is_frozen;
				}
				public static packet_set_ticking_state readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Float tick_rate = ProtocolDeserializers.readF32(buf);
						Boolean is_frozen = ProtocolDeserializers.readBool(buf);
						return new packet_set_ticking_state(
							tick_rate,
							is_frozen
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_step_tick extends PacketBase{
				Integer tick_steps;
				public packet_step_tick(
					Integer tick_steps
				){
					super(PacketIDs.play_toClient_packet_step_tick.getId());
					this.tick_steps = tick_steps;
				}
				public static packet_step_tick readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer tick_steps = ProtocolDeserializers.readVarInt(buf);
						return new packet_step_tick(
							tick_steps
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_advancements extends PacketBase{
				Boolean reset;
				Tuple5<String, String, Object, String[][], Boolean>[] advancementMapping;
				String[] identifiers;
				Tuple2<String, Tuple2<String, Long>[]>[] progressMapping;
				public packet_advancements(
					Boolean reset,
					Tuple5<String, String, Object, String[][], Boolean>[] advancementMapping,
					String[] identifiers,
					Tuple2<String, Tuple2<String, Long>[]>[] progressMapping
				){
					super(PacketIDs.play_toClient_packet_advancements.getId());
					this.reset = reset;
					this.advancementMapping = advancementMapping;
					this.identifiers = identifiers;
					this.progressMapping = progressMapping;
				}
				public static packet_advancements readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Boolean reset = ProtocolDeserializers.readBool(buf);
						Tuple5<String, String, Object, String[][], Boolean>[] advancementMapping = ProtocolDeserializers.readVarintPrefixedArr(buf, aux129 ->Tuple5.readFrom(buf, aux130 ->ProtocolDeserializers.readString(buf), aux131 ->ProtocolDeserializers.readString(buf), nullNOKNOWNCLASS, aux132 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux133 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux134 ->ProtocolDeserializers.readString(buf), String[]::new), String[]::new), aux135 ->ProtocolDeserializers.readBool(buf)), Tuple5[]::new);
						String[] identifiers = ProtocolDeserializers.readVarintPrefixedArr(buf, aux136 ->ProtocolDeserializers.readString(buf), String[]::new);
						Tuple2<String, Tuple2<String, Long>[]>[] progressMapping = ProtocolDeserializers.readVarintPrefixedArr(buf, aux137 ->Tuple2.readFrom(buf, aux138 ->ProtocolDeserializers.readString(buf), aux139 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux140 ->Tuple2.readFrom(buf, aux141 ->ProtocolDeserializers.readString(buf), aux142 ->ProtocolDeserializers.readI64(buf)), Tuple2[]::new)), Tuple2[]::new);
						return new packet_advancements(
							reset,
							advancementMapping,
							identifiers,
							progressMapping
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_update_attributes extends PacketBase{
				Integer entityId;
				Tuple3<Integer, Double, Tuple3<String, Double, Byte>[]>[] properties;
				public packet_entity_update_attributes(
					Integer entityId,
					Tuple3<Integer, Double, Tuple3<String, Double, Byte>[]>[] properties
				){
					super(PacketIDs.play_toClient_packet_entity_update_attributes.getId());
					this.entityId = entityId;
					this.properties = properties;
				}
				public static packet_entity_update_attributes readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Tuple3<Integer, Double, Tuple3<String, Double, Byte>[]>[] properties = ProtocolDeserializers.readVarintPrefixedArr(buf, aux143 ->Tuple3.readFrom(buf, aux144 ->ProtocolDeserializers.readVarInt(buf), aux145 ->ProtocolDeserializers.readF64(buf), aux146 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux147 ->Tuple3.readFrom(buf, aux148 ->ProtocolDeserializers.readString(buf), aux149 ->ProtocolDeserializers.readF64(buf), aux150 ->ProtocolDeserializers.readI8(buf)), Tuple3[]::new)), Tuple3[]::new);
						return new packet_entity_update_attributes(
							entityId,
							properties
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_effect extends PacketBase{
				Integer entityId;
				Integer effectId;
				Integer amplifier;
				Integer duration;
				Short flags;
				public packet_entity_effect(
					Integer entityId,
					Integer effectId,
					Integer amplifier,
					Integer duration,
					Short flags
				){
					super(PacketIDs.play_toClient_packet_entity_effect.getId());
					this.entityId = entityId;
					this.effectId = effectId;
					this.amplifier = amplifier;
					this.duration = duration;
					this.flags = flags;
				}
				public static packet_entity_effect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Integer effectId = ProtocolDeserializers.readVarInt(buf);
						Integer amplifier = ProtocolDeserializers.readVarInt(buf);
						Integer duration = ProtocolDeserializers.readVarInt(buf);
						Short flags = ProtocolDeserializers.readU8(buf);
						return new packet_entity_effect(
							entityId,
							effectId,
							amplifier,
							duration,
							flags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_declare_recipes extends PacketBase{
				Tuple3<String, Integer, Object>[] recipes;
				public packet_declare_recipes(
					Tuple3<String, Integer, Object>[] recipes
				){
					super(PacketIDs.play_toClient_packet_declare_recipes.getId());
					this.recipes = recipes;
				}
				public static packet_declare_recipes readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Tuple3<String, Integer, Object>[] recipes = ProtocolDeserializers.readVarintPrefixedArr(buf, aux151 ->Tuple3.readFrom(buf, aux152 ->ProtocolDeserializers.readString(buf), aux153 ->ProtocolDeserializers.readVarInt(buf), aux154 ->readSwitchOftype()), Tuple3[]::new);
						return new packet_declare_recipes(
							recipes
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_tags extends PacketBase{
				Tuple2<String, Tuple2<String, Integer[]>[]>[] tags;
				public packet_tags(
					Tuple2<String, Tuple2<String, Integer[]>[]>[] tags
				){
					super(PacketIDs.play_toClient_packet_tags.getId());
					this.tags = tags;
				}
				public static packet_tags readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Tuple2<String, Tuple2<String, Integer[]>[]>[] tags = ProtocolDeserializers.readVarintPrefixedArr(buf, aux155 ->Tuple2.readFrom(buf, aux156 ->ProtocolDeserializers.readString(buf), aux157 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux158 ->Tuple2.readFrom(buf, aux159 ->ProtocolDeserializers.readString(buf), aux160 ->ProtocolDeserializers.readVarintPrefixedArr(buf, aux161 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new)), Tuple2[]::new)), Tuple2[]::new);
						return new packet_tags(
							tags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_projectile_power extends PacketBase{
				Integer id;
				Double accelerationPower;
				public packet_set_projectile_power(
					Integer id,
					Double accelerationPower
				){
					super(PacketIDs.play_toClient_packet_set_projectile_power.getId());
					this.id = id;
					this.accelerationPower = accelerationPower;
				}
				public static packet_set_projectile_power readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer id = ProtocolDeserializers.readVarInt(buf);
						Double accelerationPower = ProtocolDeserializers.readF64(buf);
						return new packet_set_projectile_power(
							id,
							accelerationPower
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
		public static class toServer{
			static class packet_teleport_confirm extends PacketBase{
				Integer teleportId;
				public packet_teleport_confirm(
					Integer teleportId
				){
					super(PacketIDs.play_toServer_packet_teleport_confirm.getId());
					this.teleportId = teleportId;
				}
				public static packet_teleport_confirm readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer teleportId = ProtocolDeserializers.readVarInt(buf);
						return new packet_teleport_confirm(
							teleportId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_query_block_nbt extends PacketBase{
				Integer transactionId;
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				public packet_query_block_nbt(
					Integer transactionId,
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y
				){
					super(PacketIDs.play_toServer_packet_query_block_nbt.getId());
					this.transactionId = transactionId;
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
				}
				public static packet_query_block_nbt readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer transactionId = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						return new packet_query_block_nbt(
							transactionId,
							location,
							location_x,
							location_z,
							location_y
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_difficulty extends PacketBase{
				Short newDifficulty;
				public packet_set_difficulty(
					Short newDifficulty
				){
					super(PacketIDs.play_toServer_packet_set_difficulty.getId());
					this.newDifficulty = newDifficulty;
				}
				public static packet_set_difficulty readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short newDifficulty = ProtocolDeserializers.readU8(buf);
						return new packet_set_difficulty(
							newDifficulty
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_message_acknowledgement extends PacketBase{
				Integer count;
				public packet_message_acknowledgement(
					Integer count
				){
					super(PacketIDs.play_toServer_packet_message_acknowledgement.getId());
					this.count = count;
				}
				public static packet_message_acknowledgement readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer count = ProtocolDeserializers.readVarInt(buf);
						return new packet_message_acknowledgement(
							count
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chat_command extends PacketBase{
				String command;
				public packet_chat_command(
					String command
				){
					super(PacketIDs.play_toServer_packet_chat_command.getId());
					this.command = command;
				}
				public static packet_chat_command readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String command = ProtocolDeserializers.readString(buf);
						return new packet_chat_command(
							command
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chat_command_signed extends PacketBase{
				String command;
				Long timestamp;
				Long salt;
				Tuple2<String, BitSet>[] argumentSignatures;
				Integer messageCount;
				//Unresolved buildable buffer
				Object acknowledged_buffer;
				public packet_chat_command_signed(
					String command,
					Long timestamp,
					Long salt,
					Tuple2<String, BitSet>[] argumentSignatures,
					Integer messageCount,
					Object acknowledged_buffer
				){
					super(PacketIDs.play_toServer_packet_chat_command_signed.getId());
					this.command = command;
					this.timestamp = timestamp;
					this.salt = salt;
					this.argumentSignatures = argumentSignatures;
					this.messageCount = messageCount;
					this.acknowledged_buffer = acknowledged_buffer;
				}
				public static packet_chat_command_signed readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String command = ProtocolDeserializers.readString(buf);
						Long timestamp = ProtocolDeserializers.readI64(buf);
						Long salt = ProtocolDeserializers.readI64(buf);
						Tuple2<String, BitSet>[] argumentSignatures = ProtocolDeserializers.readVarintPrefixedArr(buf, aux162 ->Tuple2.readFrom(buf, aux163 ->ProtocolDeserializers.readString(buf), aux164 ->Bitset.valueOf(buf.get(new byte[256]))), Tuple2[]::new);
						Integer messageCount = ProtocolDeserializers.readVarInt(buf);
						Object acknowledged_buffer = "nullNOKNOWNCLASS@FlattenableBuilder";
						return new packet_chat_command_signed(
							command,
							timestamp,
							salt,
							argumentSignatures,
							messageCount,
							acknowledged_buffer
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chat_message extends PacketBase{
				String message;
				Long timestamp;
				Long salt;
				//optionthew exception: java.lang.RuntimeException: java.lang.UnsupportedOperationException: Attempting to create a optionwith: [buffer, {count=256}]
				Object signature_option;
				Integer offset;
				//Unresolved buildable buffer
				Object acknowledged_buffer;
				public packet_chat_message(
					String message,
					Long timestamp,
					Long salt,
					Object signature_option,
					Integer offset,
					Object acknowledged_buffer
				){
					super(PacketIDs.play_toServer_packet_chat_message.getId());
					this.message = message;
					this.timestamp = timestamp;
					this.salt = salt;
					this.signature_option = signature_option;
					this.offset = offset;
					this.acknowledged_buffer = acknowledged_buffer;
				}
				public static packet_chat_message readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String message = ProtocolDeserializers.readString(buf);
						Long timestamp = ProtocolDeserializers.readI64(buf);
						Long salt = ProtocolDeserializers.readI64(buf);
						Object signature_option = nullNOKNOWNCLASS;
						Integer offset = ProtocolDeserializers.readVarInt(buf);
						Object acknowledged_buffer = "nullNOKNOWNCLASS@FlattenableBuilder";
						return new packet_chat_message(
							message,
							timestamp,
							salt,
							signature_option,
							offset,
							acknowledged_buffer
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chat_session_update extends PacketBase{
				BigInteger sessionUUID;
				Long expireTime;
				BitSet publicKey;
				BitSet signature;
				public packet_chat_session_update(
					BigInteger sessionUUID,
					Long expireTime,
					BitSet publicKey,
					BitSet signature
				){
					super(PacketIDs.play_toServer_packet_chat_session_update.getId());
					this.sessionUUID = sessionUUID;
					this.expireTime = expireTime;
					this.publicKey = publicKey;
					this.signature = signature;
				}
				public static packet_chat_session_update readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger sessionUUID = ProtocolDeserializers.readUUID(buf);
						Long expireTime = ProtocolDeserializers.readI64(buf);
						BitSet publicKey = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						BitSet signature = BitSet.valueOf(buf.get(new byte[ProtocolDeserializers.readVarInt(buf)]));
						return new packet_chat_session_update(
							sessionUUID,
							expireTime,
							publicKey,
							signature
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_chunk_batch_received extends PacketBase{
				Float chunksPerTick;
				public packet_chunk_batch_received(
					Float chunksPerTick
				){
					super(PacketIDs.play_toServer_packet_chunk_batch_received.getId());
					this.chunksPerTick = chunksPerTick;
				}
				public static packet_chunk_batch_received readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Float chunksPerTick = ProtocolDeserializers.readF32(buf);
						return new packet_chunk_batch_received(
							chunksPerTick
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_client_command extends PacketBase{
				Integer actionId;
				public packet_client_command(
					Integer actionId
				){
					super(PacketIDs.play_toServer_packet_client_command.getId());
					this.actionId = actionId;
				}
				public static packet_client_command readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer actionId = ProtocolDeserializers.readVarInt(buf);
						return new packet_client_command(
							actionId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_settings extends PacketBase{
				String locale;
				Byte viewDistance;
				Integer chatFlags;
				Boolean chatColors;
				Short skinParts;
				Integer mainHand;
				Boolean enableTextFiltering;
				Boolean enableServerListing;
				public packet_settings(
					String locale,
					Byte viewDistance,
					Integer chatFlags,
					Boolean chatColors,
					Short skinParts,
					Integer mainHand,
					Boolean enableTextFiltering,
					Boolean enableServerListing
				){
					super(PacketIDs.play_toServer_packet_settings.getId());
					this.locale = locale;
					this.viewDistance = viewDistance;
					this.chatFlags = chatFlags;
					this.chatColors = chatColors;
					this.skinParts = skinParts;
					this.mainHand = mainHand;
					this.enableTextFiltering = enableTextFiltering;
					this.enableServerListing = enableServerListing;
				}
				public static packet_settings readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String locale = ProtocolDeserializers.readString(buf);
						Byte viewDistance = ProtocolDeserializers.readI8(buf);
						Integer chatFlags = ProtocolDeserializers.readVarInt(buf);
						Boolean chatColors = ProtocolDeserializers.readBool(buf);
						Short skinParts = ProtocolDeserializers.readU8(buf);
						Integer mainHand = ProtocolDeserializers.readVarInt(buf);
						Boolean enableTextFiltering = ProtocolDeserializers.readBool(buf);
						Boolean enableServerListing = ProtocolDeserializers.readBool(buf);
						return new packet_settings(
							locale,
							viewDistance,
							chatFlags,
							chatColors,
							skinParts,
							mainHand,
							enableTextFiltering,
							enableServerListing
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_tab_complete extends PacketBase{
				Integer transactionId;
				String text;
				public packet_tab_complete(
					Integer transactionId,
					String text
				){
					super(PacketIDs.play_toServer_packet_tab_complete.getId());
					this.transactionId = transactionId;
					this.text = text;
				}
				public static packet_tab_complete readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer transactionId = ProtocolDeserializers.readVarInt(buf);
						String text = ProtocolDeserializers.readString(buf);
						return new packet_tab_complete(
							transactionId,
							text
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_configuration_acknowledged extends PacketBase{
				public packet_configuration_acknowledged(
				){
					super(PacketIDs.play_toServer_packet_configuration_acknowledged.getId());
				}
				public static packet_configuration_acknowledged readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						return new packet_configuration_acknowledged(
							
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_enchant_item extends PacketBase{
				Byte windowId;
				Byte enchantment;
				public packet_enchant_item(
					Byte windowId,
					Byte enchantment
				){
					super(PacketIDs.play_toServer_packet_enchant_item.getId());
					this.windowId = windowId;
					this.enchantment = enchantment;
				}
				public static packet_enchant_item readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte windowId = ProtocolDeserializers.readI8(buf);
						Byte enchantment = ProtocolDeserializers.readI8(buf);
						return new packet_enchant_item(
							windowId,
							enchantment
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_window_click extends PacketBase{
				Short windowId;
				Integer stateId;
				Short slot;
				Byte mouseButton;
				Integer mode;
				Tuple3<Short, Byte, Object>[] changedSlots;
				Byte cursorItem_itemCount;
				Integer cursorItem_itemId;
				Integer cursorItem_addedComponentCount;
				Integer cursorItem_removedComponentCount;
				Tuple2<Integer, Object>[] cursorItem_components;
				Integer[] cursorItem_removeComponents;
				public packet_window_click(
					Short windowId,
					Integer stateId,
					Short slot,
					Byte mouseButton,
					Integer mode,
					Tuple3<Short, Byte, Object>[] changedSlots,
					Byte cursorItem_itemCount,
					Integer cursorItem_itemId,
					Integer cursorItem_addedComponentCount,
					Integer cursorItem_removedComponentCount,
					Tuple2<Integer, Object>[] cursorItem_components,
					Integer[] cursorItem_removeComponents
				){
					super(PacketIDs.play_toServer_packet_window_click.getId());
					this.windowId = windowId;
					this.stateId = stateId;
					this.slot = slot;
					this.mouseButton = mouseButton;
					this.mode = mode;
					this.changedSlots = changedSlots;
					this.cursorItem_itemCount = cursorItem_itemCount;
					this.cursorItem_itemId = cursorItem_itemId;
					this.cursorItem_addedComponentCount = cursorItem_addedComponentCount;
					this.cursorItem_removedComponentCount = cursorItem_removedComponentCount;
					this.cursorItem_components = cursorItem_components;
					this.cursorItem_removeComponents = cursorItem_removeComponents;
				}
				public static packet_window_click readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short windowId = ProtocolDeserializers.readU8(buf);
						Integer stateId = ProtocolDeserializers.readVarInt(buf);
						Short slot = ProtocolDeserializers.readI16(buf);
						Byte mouseButton = ProtocolDeserializers.readI8(buf);
						Integer mode = ProtocolDeserializers.readVarInt(buf);
						Tuple3<Short, Byte, Object>[] changedSlots = ProtocolDeserializers.readVarintPrefixedArr(buf, aux165 ->Tuple3.readFrom(buf, aux166 ->ProtocolDeserializers.readI16(buf), aux167 ->ProtocolDeserializers.readI8(buf), aux168 ->readSwitchOfitemCount()), Tuple3[]::new);
						Byte cursorItem_itemCount = ProtocolDeserializers.readI8(buf);
						Integer cursorItem_itemId = (cursorItem_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer cursorItem_addedComponentCount = (cursorItem_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer cursorItem_removedComponentCount = (cursorItem_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Tuple2<Integer, Object>[] cursorItem_components = (cursorItem_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux169 ->Tuple2.readFrom(buf, aux170 ->ProtocolDeserializers.readVarInt(buf), aux171 ->readSwitchOftype()), Tuple2[]::new, cursorItem_anon_components_addedComponentCount) : null;
						Integer[] cursorItem_removeComponents = (cursorItem_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux172 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, cursorItem_anon_removeComponents_removedComponentCount) : null;
						return new packet_window_click(
							windowId,
							stateId,
							slot,
							mouseButton,
							mode,
							changedSlots,
							cursorItem_itemCount,
							cursorItem_itemId,
							cursorItem_addedComponentCount,
							cursorItem_removedComponentCount,
							cursorItem_components,
							cursorItem_removeComponents
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_close_window extends PacketBase{
				Short windowId;
				public packet_close_window(
					Short windowId
				){
					super(PacketIDs.play_toServer_packet_close_window.getId());
					this.windowId = windowId;
				}
				public static packet_close_window readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short windowId = ProtocolDeserializers.readU8(buf);
						return new packet_close_window(
							windowId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_slot_state extends PacketBase{
				Integer slot_id;
				Integer window_id;
				Boolean state;
				public packet_set_slot_state(
					Integer slot_id,
					Integer window_id,
					Boolean state
				){
					super(PacketIDs.play_toServer_packet_set_slot_state.getId());
					this.slot_id = slot_id;
					this.window_id = window_id;
					this.state = state;
				}
				public static packet_set_slot_state readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer slot_id = ProtocolDeserializers.readVarInt(buf);
						Integer window_id = ProtocolDeserializers.readVarInt(buf);
						Boolean state = ProtocolDeserializers.readBool(buf);
						return new packet_set_slot_state(
							slot_id,
							window_id,
							state
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_custom_payload extends PacketBase{
				String channel;
				BitSet data;
				public packet_custom_payload(
					String channel,
					BitSet data
				){
					super(PacketIDs.play_toServer_packet_custom_payload.getId());
					this.channel = channel;
					this.data = data;
				}
				public static packet_custom_payload readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String channel = ProtocolDeserializers.readString(buf);
						BitSet data = Bitset.valueOf(buf.get(new byte[packetSize- (buf.position() - startingPos)]));
						return new packet_custom_payload(
							channel,
							data
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_debug_sample_subscription extends PacketBase{
				Integer type;
				public packet_debug_sample_subscription(
					Integer type
				){
					super(PacketIDs.play_toServer_packet_debug_sample_subscription.getId());
					this.type = type;
				}
				public static packet_debug_sample_subscription readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer type = ProtocolDeserializers.readVarInt(buf);
						return new packet_debug_sample_subscription(
							type
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_edit_book extends PacketBase{
				Integer hand;
				String[] pages;
				String title;
				public packet_edit_book(
					Integer hand,
					String[] pages,
					String title
				){
					super(PacketIDs.play_toServer_packet_edit_book.getId());
					this.hand = hand;
					this.pages = pages;
					this.title = title;
				}
				public static packet_edit_book readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer hand = ProtocolDeserializers.readVarInt(buf);
						String[] pages = ProtocolDeserializers.readVarintPrefixedArr(buf, aux173 ->ProtocolDeserializers.readString(buf), String[]::new);
						String title = ProtocolDeserializers.readString(buf);
						return new packet_edit_book(
							hand,
							pages,
							title
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_query_entity_nbt extends PacketBase{
				Integer transactionId;
				Integer entityId;
				public packet_query_entity_nbt(
					Integer transactionId,
					Integer entityId
				){
					super(PacketIDs.play_toServer_packet_query_entity_nbt.getId());
					this.transactionId = transactionId;
					this.entityId = entityId;
				}
				public static packet_query_entity_nbt readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer transactionId = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						return new packet_query_entity_nbt(
							transactionId,
							entityId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_use_entity extends PacketBase{
				Integer target;
				Integer mouse;
				Float x;
				Float y;
				Float z;
				Integer hand;
				Boolean sneaking;
				public packet_use_entity(
					Integer target,
					Integer mouse,
					Float x,
					Float y,
					Float z,
					Integer hand,
					Boolean sneaking
				){
					super(PacketIDs.play_toServer_packet_use_entity.getId());
					this.target = target;
					this.mouse = mouse;
					this.x = x;
					this.y = y;
					this.z = z;
					this.hand = hand;
					this.sneaking = sneaking;
				}
				public static packet_use_entity readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer target = ProtocolDeserializers.readVarInt(buf);
						Integer mouse = ProtocolDeserializers.readVarInt(buf);
						Float x = (mouse == 2) ? ProtocolDeserializers.readF32(buf) : null;
						Float y = (mouse == 2) ? ProtocolDeserializers.readF32(buf) : null;
						Float z = (mouse == 2) ? ProtocolDeserializers.readF32(buf) : null;
						Integer hand = (mouse == 0) || (mouse == 2) ? ProtocolDeserializers.readVarInt(buf) : null;
						Boolean sneaking = ProtocolDeserializers.readBool(buf);
						return new packet_use_entity(
							target,
							mouse,
							x,
							y,
							z,
							hand,
							sneaking
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_generate_structure extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Integer levels;
				Boolean keepJigsaws;
				public packet_generate_structure(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Integer levels,
					Boolean keepJigsaws
				){
					super(PacketIDs.play_toServer_packet_generate_structure.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.levels = levels;
					this.keepJigsaws = keepJigsaws;
				}
				public static packet_generate_structure readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Integer levels = ProtocolDeserializers.readVarInt(buf);
						Boolean keepJigsaws = ProtocolDeserializers.readBool(buf);
						return new packet_generate_structure(
							location,
							location_x,
							location_z,
							location_y,
							levels,
							keepJigsaws
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_keep_alive extends PacketBase{
				Long keepAliveId;
				public packet_keep_alive(
					Long keepAliveId
				){
					super(PacketIDs.play_toServer_packet_keep_alive.getId());
					this.keepAliveId = keepAliveId;
				}
				public static packet_keep_alive readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long keepAliveId = ProtocolDeserializers.readI64(buf);
						return new packet_keep_alive(
							keepAliveId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_lock_difficulty extends PacketBase{
				Boolean locked;
				public packet_lock_difficulty(
					Boolean locked
				){
					super(PacketIDs.play_toServer_packet_lock_difficulty.getId());
					this.locked = locked;
				}
				public static packet_lock_difficulty readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Boolean locked = ProtocolDeserializers.readBool(buf);
						return new packet_lock_difficulty(
							locked
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_position extends PacketBase{
				Double x;
				Double y;
				Double z;
				Boolean onGround;
				public packet_position(
					Double x,
					Double y,
					Double z,
					Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_position.getId());
					this.x = x;
					this.y = y;
					this.z = z;
					this.onGround = onGround;
				}
				public static packet_position readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_position(
							x,
							y,
							z,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_position_look extends PacketBase{
				Double x;
				Double y;
				Double z;
				Float yaw;
				Float pitch;
				Boolean onGround;
				public packet_position_look(
					Double x,
					Double y,
					Double z,
					Float yaw,
					Float pitch,
					Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_position_look.getId());
					this.x = x;
					this.y = y;
					this.z = z;
					this.yaw = yaw;
					this.pitch = pitch;
					this.onGround = onGround;
				}
				public static packet_position_look readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Float yaw = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_position_look(
							x,
							y,
							z,
							yaw,
							pitch,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_look extends PacketBase{
				Float yaw;
				Float pitch;
				Boolean onGround;
				public packet_look(
					Float yaw,
					Float pitch,
					Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_look.getId());
					this.yaw = yaw;
					this.pitch = pitch;
					this.onGround = onGround;
				}
				public static packet_look readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Float yaw = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_look(
							yaw,
							pitch,
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_flying extends PacketBase{
				Boolean onGround;
				public packet_flying(
					Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_flying.getId());
					this.onGround = onGround;
				}
				public static packet_flying readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Boolean onGround = ProtocolDeserializers.readBool(buf);
						return new packet_flying(
							onGround
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_vehicle_move extends PacketBase{
				Double x;
				Double y;
				Double z;
				Float yaw;
				Float pitch;
				public packet_vehicle_move(
					Double x,
					Double y,
					Double z,
					Float yaw,
					Float pitch
				){
					super(PacketIDs.play_toServer_packet_vehicle_move.getId());
					this.x = x;
					this.y = y;
					this.z = z;
					this.yaw = yaw;
					this.pitch = pitch;
				}
				public static packet_vehicle_move readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Double x = ProtocolDeserializers.readF64(buf);
						Double y = ProtocolDeserializers.readF64(buf);
						Double z = ProtocolDeserializers.readF64(buf);
						Float yaw = ProtocolDeserializers.readF32(buf);
						Float pitch = ProtocolDeserializers.readF32(buf);
						return new packet_vehicle_move(
							x,
							y,
							z,
							yaw,
							pitch
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_steer_boat extends PacketBase{
				Boolean leftPaddle;
				Boolean rightPaddle;
				public packet_steer_boat(
					Boolean leftPaddle,
					Boolean rightPaddle
				){
					super(PacketIDs.play_toServer_packet_steer_boat.getId());
					this.leftPaddle = leftPaddle;
					this.rightPaddle = rightPaddle;
				}
				public static packet_steer_boat readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Boolean leftPaddle = ProtocolDeserializers.readBool(buf);
						Boolean rightPaddle = ProtocolDeserializers.readBool(buf);
						return new packet_steer_boat(
							leftPaddle,
							rightPaddle
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_pick_item extends PacketBase{
				Integer slot;
				public packet_pick_item(
					Integer slot
				){
					super(PacketIDs.play_toServer_packet_pick_item.getId());
					this.slot = slot;
				}
				public static packet_pick_item readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer slot = ProtocolDeserializers.readVarInt(buf);
						return new packet_pick_item(
							slot
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_ping_request extends PacketBase{
				Long id;
				public packet_ping_request(
					Long id
				){
					super(PacketIDs.play_toServer_packet_ping_request.getId());
					this.id = id;
				}
				public static packet_ping_request readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long id = ProtocolDeserializers.readI64(buf);
						return new packet_ping_request(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_craft_recipe_request extends PacketBase{
				Byte windowId;
				String recipe;
				Boolean makeAll;
				public packet_craft_recipe_request(
					Byte windowId,
					String recipe,
					Boolean makeAll
				){
					super(PacketIDs.play_toServer_packet_craft_recipe_request.getId());
					this.windowId = windowId;
					this.recipe = recipe;
					this.makeAll = makeAll;
				}
				public static packet_craft_recipe_request readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte windowId = ProtocolDeserializers.readI8(buf);
						String recipe = ProtocolDeserializers.readString(buf);
						Boolean makeAll = ProtocolDeserializers.readBool(buf);
						return new packet_craft_recipe_request(
							windowId,
							recipe,
							makeAll
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_abilities extends PacketBase{
				Byte flags;
				public packet_abilities(
					Byte flags
				){
					super(PacketIDs.play_toServer_packet_abilities.getId());
					this.flags = flags;
				}
				public static packet_abilities readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Byte flags = ProtocolDeserializers.readI8(buf);
						return new packet_abilities(
							flags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_block_dig extends PacketBase{
				Integer status;
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Byte face;
				Integer sequence;
				public packet_block_dig(
					Integer status,
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Byte face,
					Integer sequence
				){
					super(PacketIDs.play_toServer_packet_block_dig.getId());
					this.status = status;
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.face = face;
					this.sequence = sequence;
				}
				public static packet_block_dig readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer status = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Byte face = ProtocolDeserializers.readI8(buf);
						Integer sequence = ProtocolDeserializers.readVarInt(buf);
						return new packet_block_dig(
							status,
							location,
							location_x,
							location_z,
							location_y,
							face,
							sequence
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_entity_action extends PacketBase{
				Integer entityId;
				Integer actionId;
				Integer jumpBoost;
				public packet_entity_action(
					Integer entityId,
					Integer actionId,
					Integer jumpBoost
				){
					super(PacketIDs.play_toServer_packet_entity_action.getId());
					this.entityId = entityId;
					this.actionId = actionId;
					this.jumpBoost = jumpBoost;
				}
				public static packet_entity_action readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						Integer actionId = ProtocolDeserializers.readVarInt(buf);
						Integer jumpBoost = ProtocolDeserializers.readVarInt(buf);
						return new packet_entity_action(
							entityId,
							actionId,
							jumpBoost
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_steer_vehicle extends PacketBase{
				Float sideways;
				Float forward;
				Short jump;
				public packet_steer_vehicle(
					Float sideways,
					Float forward,
					Short jump
				){
					super(PacketIDs.play_toServer_packet_steer_vehicle.getId());
					this.sideways = sideways;
					this.forward = forward;
					this.jump = jump;
				}
				public static packet_steer_vehicle readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Float sideways = ProtocolDeserializers.readF32(buf);
						Float forward = ProtocolDeserializers.readF32(buf);
						Short jump = ProtocolDeserializers.readU8(buf);
						return new packet_steer_vehicle(
							sideways,
							forward,
							jump
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_pong extends PacketBase{
				Integer id;
				public packet_pong(
					Integer id
				){
					super(PacketIDs.play_toServer_packet_pong.getId());
					this.id = id;
				}
				public static packet_pong readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer id = ProtocolDeserializers.readI32(buf);
						return new packet_pong(
							id
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_recipe_book extends PacketBase{
				Integer bookId;
				Boolean bookOpen;
				Boolean filterActive;
				public packet_recipe_book(
					Integer bookId,
					Boolean bookOpen,
					Boolean filterActive
				){
					super(PacketIDs.play_toServer_packet_recipe_book.getId());
					this.bookId = bookId;
					this.bookOpen = bookOpen;
					this.filterActive = filterActive;
				}
				public static packet_recipe_book readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer bookId = ProtocolDeserializers.readVarInt(buf);
						Boolean bookOpen = ProtocolDeserializers.readBool(buf);
						Boolean filterActive = ProtocolDeserializers.readBool(buf);
						return new packet_recipe_book(
							bookId,
							bookOpen,
							filterActive
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_displayed_recipe extends PacketBase{
				String recipeId;
				public packet_displayed_recipe(
					String recipeId
				){
					super(PacketIDs.play_toServer_packet_displayed_recipe.getId());
					this.recipeId = recipeId;
				}
				public static packet_displayed_recipe readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String recipeId = ProtocolDeserializers.readString(buf);
						return new packet_displayed_recipe(
							recipeId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_name_item extends PacketBase{
				String name;
				public packet_name_item(
					String name
				){
					super(PacketIDs.play_toServer_packet_name_item.getId());
					this.name = name;
				}
				public static packet_name_item readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						String name = ProtocolDeserializers.readString(buf);
						return new packet_name_item(
							name
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_resource_pack_receive extends PacketBase{
				BigInteger uuid;
				Integer result;
				public packet_resource_pack_receive(
					BigInteger uuid,
					Integer result
				){
					super(PacketIDs.play_toServer_packet_resource_pack_receive.getId());
					this.uuid = uuid;
					this.result = result;
				}
				public static packet_resource_pack_receive readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger uuid = ProtocolDeserializers.readUUID(buf);
						Integer result = ProtocolDeserializers.readVarInt(buf);
						return new packet_resource_pack_receive(
							uuid,
							result
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_advancement_tab extends PacketBase{
				Integer action;
				String tabId;
				public packet_advancement_tab(
					Integer action,
					String tabId
				){
					super(PacketIDs.play_toServer_packet_advancement_tab.getId());
					this.action = action;
					this.tabId = tabId;
				}
				public static packet_advancement_tab readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer action = ProtocolDeserializers.readVarInt(buf);
						String tabId = (action == 0) ? ProtocolDeserializers.readString(buf) : null;
						return new packet_advancement_tab(
							action,
							tabId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_select_trade extends PacketBase{
				Integer slot;
				public packet_select_trade(
					Integer slot
				){
					super(PacketIDs.play_toServer_packet_select_trade.getId());
					this.slot = slot;
				}
				public static packet_select_trade readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer slot = ProtocolDeserializers.readVarInt(buf);
						return new packet_select_trade(
							slot
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_beacon_effect extends PacketBase{
				Integer primary_effect;
				Integer secondary_effect;
				public packet_set_beacon_effect(
					Integer primary_effect,
					Integer secondary_effect
				){
					super(PacketIDs.play_toServer_packet_set_beacon_effect.getId());
					this.primary_effect = primary_effect;
					this.secondary_effect = secondary_effect;
				}
				public static packet_set_beacon_effect readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer primary_effect = ProtocolDeserializers.readVarInt(buf);
						Integer secondary_effect = ProtocolDeserializers.readVarInt(buf);
						return new packet_set_beacon_effect(
							primary_effect,
							secondary_effect
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_held_item_slot extends PacketBase{
				Short slotId;
				public packet_held_item_slot(
					Short slotId
				){
					super(PacketIDs.play_toServer_packet_held_item_slot.getId());
					this.slotId = slotId;
				}
				public static packet_held_item_slot readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short slotId = ProtocolDeserializers.readI16(buf);
						return new packet_held_item_slot(
							slotId
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_command_block extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				String command;
				Integer mode;
				Short flags;
				public packet_update_command_block(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					String command,
					Integer mode,
					Short flags
				){
					super(PacketIDs.play_toServer_packet_update_command_block.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.command = command;
					this.mode = mode;
					this.flags = flags;
				}
				public static packet_update_command_block readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						String command = ProtocolDeserializers.readString(buf);
						Integer mode = ProtocolDeserializers.readVarInt(buf);
						Short flags = ProtocolDeserializers.readU8(buf);
						return new packet_update_command_block(
							location,
							location_x,
							location_z,
							location_y,
							command,
							mode,
							flags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_command_block_minecart extends PacketBase{
				Integer entityId;
				String command;
				Boolean track_output;
				public packet_update_command_block_minecart(
					Integer entityId,
					String command,
					Boolean track_output
				){
					super(PacketIDs.play_toServer_packet_update_command_block_minecart.getId());
					this.entityId = entityId;
					this.command = command;
					this.track_output = track_output;
				}
				public static packet_update_command_block_minecart readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer entityId = ProtocolDeserializers.readVarInt(buf);
						String command = ProtocolDeserializers.readString(buf);
						Boolean track_output = ProtocolDeserializers.readBool(buf);
						return new packet_update_command_block_minecart(
							entityId,
							command,
							track_output
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_set_creative_slot extends PacketBase{
				Short slot;
				Byte item_itemCount;
				Integer item_itemId;
				Integer item_addedComponentCount;
				Integer item_removedComponentCount;
				Tuple2<Integer, Object>[] item_components;
				Integer[] item_removeComponents;
				public packet_set_creative_slot(
					Short slot,
					Byte item_itemCount,
					Integer item_itemId,
					Integer item_addedComponentCount,
					Integer item_removedComponentCount,
					Tuple2<Integer, Object>[] item_components,
					Integer[] item_removeComponents
				){
					super(PacketIDs.play_toServer_packet_set_creative_slot.getId());
					this.slot = slot;
					this.item_itemCount = item_itemCount;
					this.item_itemId = item_itemId;
					this.item_addedComponentCount = item_addedComponentCount;
					this.item_removedComponentCount = item_removedComponentCount;
					this.item_components = item_components;
					this.item_removeComponents = item_removeComponents;
				}
				public static packet_set_creative_slot readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Short slot = ProtocolDeserializers.readI16(buf);
						Byte item_itemCount = ProtocolDeserializers.readI8(buf);
						Integer item_itemId = (item_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer item_addedComponentCount = (item_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Integer item_removedComponentCount = (item_itemCount != 0) ? ProtocolDeserializers.readVarInt(buf) : null;
						Tuple2<Integer, Object>[] item_components = (item_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux174 ->Tuple2.readFrom(buf, aux175 ->ProtocolDeserializers.readVarInt(buf), aux176 ->readSwitchOftype()), Tuple2[]::new, item_anon_components_addedComponentCount) : null;
						Integer[] item_removeComponents = (item_itemCount != 0) ? ProtocolDeserializers.readFixedArr(buf, aux177 ->ProtocolDeserializers.readVarInt(buf), Integer[]::new, item_anon_removeComponents_removedComponentCount) : null;
						return new packet_set_creative_slot(
							slot,
							item_itemCount,
							item_itemId,
							item_addedComponentCount,
							item_removedComponentCount,
							item_components,
							item_removeComponents
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_jigsaw_block extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				String name;
				String target;
				String pool;
				String finalState;
				String jointType;
				Integer selection_priority;
				Integer placement_priority;
				public packet_update_jigsaw_block(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					String name,
					String target,
					String pool,
					String finalState,
					String jointType,
					Integer selection_priority,
					Integer placement_priority
				){
					super(PacketIDs.play_toServer_packet_update_jigsaw_block.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.name = name;
					this.target = target;
					this.pool = pool;
					this.finalState = finalState;
					this.jointType = jointType;
					this.selection_priority = selection_priority;
					this.placement_priority = placement_priority;
				}
				public static packet_update_jigsaw_block readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						String name = ProtocolDeserializers.readString(buf);
						String target = ProtocolDeserializers.readString(buf);
						String pool = ProtocolDeserializers.readString(buf);
						String finalState = ProtocolDeserializers.readString(buf);
						String jointType = ProtocolDeserializers.readString(buf);
						Integer selection_priority = ProtocolDeserializers.readVarInt(buf);
						Integer placement_priority = ProtocolDeserializers.readVarInt(buf);
						return new packet_update_jigsaw_block(
							location,
							location_x,
							location_z,
							location_y,
							name,
							target,
							pool,
							finalState,
							jointType,
							selection_priority,
							placement_priority
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_structure_block extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Integer action;
				Integer mode;
				String name;
				Byte offset_x;
				Byte offset_y;
				Byte offset_z;
				Byte size_x;
				Byte size_y;
				Byte size_z;
				Integer mirror;
				Integer rotation;
				String metadata;
				Float integrity;
				Integer seed;
				Short flags;
				public packet_update_structure_block(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Integer action,
					Integer mode,
					String name,
					Byte offset_x,
					Byte offset_y,
					Byte offset_z,
					Byte size_x,
					Byte size_y,
					Byte size_z,
					Integer mirror,
					Integer rotation,
					String metadata,
					Float integrity,
					Integer seed,
					Short flags
				){
					super(PacketIDs.play_toServer_packet_update_structure_block.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.action = action;
					this.mode = mode;
					this.name = name;
					this.offset_x = offset_x;
					this.offset_y = offset_y;
					this.offset_z = offset_z;
					this.size_x = size_x;
					this.size_y = size_y;
					this.size_z = size_z;
					this.mirror = mirror;
					this.rotation = rotation;
					this.metadata = metadata;
					this.integrity = integrity;
					this.seed = seed;
					this.flags = flags;
				}
				public static packet_update_structure_block readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Integer action = ProtocolDeserializers.readVarInt(buf);
						Integer mode = ProtocolDeserializers.readVarInt(buf);
						String name = ProtocolDeserializers.readString(buf);
						Byte offset_x = ProtocolDeserializers.readI8(buf);
						Byte offset_y = ProtocolDeserializers.readI8(buf);
						Byte offset_z = ProtocolDeserializers.readI8(buf);
						Byte size_x = ProtocolDeserializers.readI8(buf);
						Byte size_y = ProtocolDeserializers.readI8(buf);
						Byte size_z = ProtocolDeserializers.readI8(buf);
						Integer mirror = ProtocolDeserializers.readVarInt(buf);
						Integer rotation = ProtocolDeserializers.readVarInt(buf);
						String metadata = ProtocolDeserializers.readString(buf);
						Float integrity = ProtocolDeserializers.readF32(buf);
						Integer seed = ProtocolDeserializers.readVarInt(buf);
						Short flags = ProtocolDeserializers.readU8(buf);
						return new packet_update_structure_block(
							location,
							location_x,
							location_z,
							location_y,
							action,
							mode,
							name,
							offset_x,
							offset_y,
							offset_z,
							size_x,
							size_y,
							size_z,
							mirror,
							rotation,
							metadata,
							integrity,
							seed,
							flags
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_update_sign extends PacketBase{
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Boolean isFrontText;
				String text1;
				String text2;
				String text3;
				String text4;
				public packet_update_sign(
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Boolean isFrontText,
					String text1,
					String text2,
					String text3,
					String text4
				){
					super(PacketIDs.play_toServer_packet_update_sign.getId());
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.isFrontText = isFrontText;
					this.text1 = text1;
					this.text2 = text2;
					this.text3 = text3;
					this.text4 = text4;
				}
				public static packet_update_sign readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Boolean isFrontText = ProtocolDeserializers.readBool(buf);
						String text1 = ProtocolDeserializers.readString(buf);
						String text2 = ProtocolDeserializers.readString(buf);
						String text3 = ProtocolDeserializers.readString(buf);
						String text4 = ProtocolDeserializers.readString(buf);
						return new packet_update_sign(
							location,
							location_x,
							location_z,
							location_y,
							isFrontText,
							text1,
							text2,
							text3,
							text4
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_arm_animation extends PacketBase{
				Integer hand;
				public packet_arm_animation(
					Integer hand
				){
					super(PacketIDs.play_toServer_packet_arm_animation.getId());
					this.hand = hand;
				}
				public static packet_arm_animation readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer hand = ProtocolDeserializers.readVarInt(buf);
						return new packet_arm_animation(
							hand
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_spectate extends PacketBase{
				BigInteger target;
				public packet_spectate(
					BigInteger target
				){
					super(PacketIDs.play_toServer_packet_spectate.getId());
					this.target = target;
				}
				public static packet_spectate readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						BigInteger target = ProtocolDeserializers.readUUID(buf);
						return new packet_spectate(
							target
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_block_place extends PacketBase{
				Integer hand;
				Long location;
				//bitfield of size: 26
				Integer location_x;
				//bitfield of size: 26
				Integer location_z;
				//bitfield of size: 12
				Integer location_y;
				Integer direction;
				Float cursorX;
				Float cursorY;
				Float cursorZ;
				Boolean insideBlock;
				Integer sequence;
				public packet_block_place(
					Integer hand,
					Long location,
					Integer location_x,
					Integer location_z,
					Integer location_y,
					Integer direction,
					Float cursorX,
					Float cursorY,
					Float cursorZ,
					Boolean insideBlock,
					Integer sequence
				){
					super(PacketIDs.play_toServer_packet_block_place.getId());
					this.hand = hand;
					this.location = location;
					this.location_x = location_x;
					this.location_z = location_z;
					this.location_y = location_y;
					this.direction = direction;
					this.cursorX = cursorX;
					this.cursorY = cursorY;
					this.cursorZ = cursorZ;
					this.insideBlock = insideBlock;
					this.sequence = sequence;
				}
				public static packet_block_place readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer hand = ProtocolDeserializers.readVarInt(buf);
						Long location = ProtocolDeserializers.readI64(buf);
						Integer location_x = (int)(location & 1);
						Integer location_z = (int)(location & 1);
						Integer location_y = (int)(location & 1);
						Integer direction = ProtocolDeserializers.readVarInt(buf);
						Float cursorX = ProtocolDeserializers.readF32(buf);
						Float cursorY = ProtocolDeserializers.readF32(buf);
						Float cursorZ = ProtocolDeserializers.readF32(buf);
						Boolean insideBlock = ProtocolDeserializers.readBool(buf);
						Integer sequence = ProtocolDeserializers.readVarInt(buf);
						return new packet_block_place(
							hand,
							location,
							location_x,
							location_z,
							location_y,
							direction,
							cursorX,
							cursorY,
							cursorZ,
							insideBlock,
							sequence
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
			static class packet_use_item extends PacketBase{
				Integer hand;
				Integer sequence;
				Float rotation_x;
				Float rotation_y;
				public packet_use_item(
					Integer hand,
					Integer sequence,
					Float rotation_x,
					Float rotation_y
				){
					super(PacketIDs.play_toServer_packet_use_item.getId());
					this.hand = hand;
					this.sequence = sequence;
					this.rotation_x = rotation_x;
					this.rotation_y = rotation_y;
				}
				public static packet_use_item readFrom(ByteBuffer buf){
					try {
						int packetSize = ProtocolDeserializers.readVarInt(buf);
						int startingPos = ProtocolDeserializers.readVarInt(buf);
						Integer hand = ProtocolDeserializers.readVarInt(buf);
						Integer sequence = ProtocolDeserializers.readVarInt(buf);
						Float rotation_x = ProtocolDeserializers.readF32(buf);
						Float rotation_y = ProtocolDeserializers.readF32(buf);
						return new packet_use_item(
							hand,
							sequence,
							rotation_x,
							rotation_y
						);
					} catch(Exception ex) {
						throw new BadPacketFormatException(ex);
					}
				}
			}
		}
	}
	enum PacketIDs {
		handshaking_toServer_packet_set_protocol(0x00),
		handshaking_toServer_packet_legacy_server_list_ping(0xfe),
		status_toClient_packet_server_info(0x00),
		status_toClient_packet_ping(0x01),
		status_toServer_packet_ping_start(0x00),
		status_toServer_packet_ping(0x01),
		login_toClient_packet_disconnect(0x00),
		login_toClient_packet_encryption_begin(0x01),
		login_toClient_packet_success(0x02),
		login_toClient_packet_compress(0x03),
		login_toClient_packet_login_plugin_request(0x04),
		login_toClient_packet_common_cookie_request(0x05),
		login_toServer_packet_login_start(0x00),
		login_toServer_packet_encryption_begin(0x01),
		login_toServer_packet_login_plugin_response(0x02),
		login_toServer_packet_login_acknowledged(0x03),
		login_toServer_packet_common_cookie_response(0x04),
		configuration_toClient_packet_common_cookie_request(0x00),
		configuration_toClient_packet_custom_payload(0x01),
		configuration_toClient_packet_disconnect(0x02),
		configuration_toClient_packet_finish_configuration(0x03),
		configuration_toClient_packet_keep_alive(0x04),
		configuration_toClient_packet_ping(0x05),
		configuration_toClient_packet_reset_chat(0x06),
		configuration_toClient_packet_registry_data(0x07),
		configuration_toClient_packet_remove_resource_pack(0x08),
		configuration_toClient_packet_add_resource_pack(0x09),
		configuration_toClient_packet_common_store_cookie(0x0a),
		configuration_toClient_packet_common_transfer(0x0b),
		configuration_toClient_packet_feature_flags(0x0c),
		configuration_toClient_packet_tags(0x0d),
		configuration_toClient_packet_common_select_known_packs(0x0e),
		configuration_toClient_packet_common_custom_report_details(0x0f),
		configuration_toClient_packet_common_server_links(0x10),
		configuration_toServer_packet_settings(0x00),
		configuration_toServer_packet_common_cookie_response(0x01),
		configuration_toServer_packet_custom_payload(0x02),
		configuration_toServer_packet_finish_configuration(0x03),
		configuration_toServer_packet_keep_alive(0x04),
		configuration_toServer_packet_pong(0x05),
		configuration_toServer_packet_resource_pack_receive(0x06),
		configuration_toServer_packet_common_select_known_packs(0x07),
		configuration_toServer_packet_common_custom_report_details(0x08),
		configuration_toServer_packet_common_server_links(0x09),
		play_toClient_void(0x00),
		play_toClient_packet_spawn_entity(0x01),
		play_toClient_packet_spawn_entity_experience_orb(0x02),
		play_toClient_packet_animation(0x03),
		play_toClient_packet_statistics(0x04),
		play_toClient_packet_acknowledge_player_digging(0x05),
		play_toClient_packet_block_break_animation(0x06),
		play_toClient_packet_tile_entity_data(0x07),
		play_toClient_packet_block_action(0x08),
		play_toClient_packet_block_change(0x09),
		play_toClient_packet_boss_bar(0x0a),
		play_toClient_packet_difficulty(0x0b),
		play_toClient_packet_chunk_batch_finished(0x0c),
		play_toClient_packet_chunk_batch_start(0x0d),
		play_toClient_packet_chunk_biomes(0x0e),
		play_toClient_packet_clear_titles(0x0f),
		play_toClient_packet_tab_complete(0x10),
		play_toClient_packet_declare_commands(0x11),
		play_toClient_packet_close_window(0x12),
		play_toClient_packet_window_items(0x13),
		play_toClient_packet_craft_progress_bar(0x14),
		play_toClient_packet_set_slot(0x15),
		play_toClient_packet_common_cookie_request(0x16),
		play_toClient_packet_set_cooldown(0x17),
		play_toClient_packet_chat_suggestions(0x18),
		play_toClient_packet_custom_payload(0x19),
		play_toClient_packet_damage_event(0x1a),
		play_toClient_packet_debug_sample(0x1b),
		play_toClient_packet_hide_message(0x1c),
		play_toClient_packet_kick_disconnect(0x1d),
		play_toClient_packet_profileless_chat(0x1e),
		play_toClient_packet_entity_status(0x1f),
		play_toClient_packet_explosion(0x20),
		play_toClient_packet_unload_chunk(0x21),
		play_toClient_packet_game_state_change(0x22),
		play_toClient_packet_open_horse_window(0x23),
		play_toClient_packet_hurt_animation(0x24),
		play_toClient_packet_initialize_world_border(0x25),
		play_toClient_packet_keep_alive(0x26),
		play_toClient_packet_map_chunk(0x27),
		play_toClient_packet_world_event(0x28),
		play_toClient_packet_world_particles(0x29),
		play_toClient_packet_update_light(0x2a),
		play_toClient_packet_login(0x2b),
		play_toClient_packet_map(0x2c),
		play_toClient_packet_trade_list(0x2d),
		play_toClient_packet_rel_entity_move(0x2e),
		play_toClient_packet_entity_move_look(0x2f),
		play_toClient_packet_entity_look(0x30),
		play_toClient_packet_vehicle_move(0x31),
		play_toClient_packet_open_book(0x32),
		play_toClient_packet_open_window(0x33),
		play_toClient_packet_open_sign_entity(0x34),
		play_toClient_packet_ping(0x35),
		play_toClient_packet_ping_response(0x36),
		play_toClient_packet_craft_recipe_response(0x37),
		play_toClient_packet_abilities(0x38),
		play_toClient_packet_player_chat(0x39),
		play_toClient_packet_end_combat_event(0x3a),
		play_toClient_packet_enter_combat_event(0x3b),
		play_toClient_packet_death_combat_event(0x3c),
		play_toClient_packet_player_remove(0x3d),
		play_toClient_packet_player_info(0x3e),
		play_toClient_packet_face_player(0x3f),
		play_toClient_packet_position(0x40),
		play_toClient_packet_unlock_recipes(0x41),
		play_toClient_packet_entity_destroy(0x42),
		play_toClient_packet_remove_entity_effect(0x43),
		play_toClient_packet_reset_score(0x44),
		play_toClient_packet_remove_resource_pack(0x45),
		play_toClient_packet_add_resource_pack(0x46),
		play_toClient_packet_respawn(0x47),
		play_toClient_packet_entity_head_rotation(0x48),
		play_toClient_packet_multi_block_change(0x49),
		play_toClient_packet_select_advancement_tab(0x4a),
		play_toClient_packet_server_data(0x4b),
		play_toClient_packet_action_bar(0x4c),
		play_toClient_packet_world_border_center(0x4d),
		play_toClient_packet_world_border_lerp_size(0x4e),
		play_toClient_packet_world_border_size(0x4f),
		play_toClient_packet_world_border_warning_delay(0x50),
		play_toClient_packet_world_border_warning_reach(0x51),
		play_toClient_packet_camera(0x52),
		play_toClient_packet_held_item_slot(0x53),
		play_toClient_packet_update_view_position(0x54),
		play_toClient_packet_update_view_distance(0x55),
		play_toClient_packet_spawn_position(0x56),
		play_toClient_packet_scoreboard_display_objective(0x57),
		play_toClient_packet_entity_metadata(0x58),
		play_toClient_packet_attach_entity(0x59),
		play_toClient_packet_entity_velocity(0x5a),
		play_toClient_packet_entity_equipment(0x5b),
		play_toClient_packet_experience(0x5c),
		play_toClient_packet_update_health(0x5d),
		play_toClient_packet_scoreboard_objective(0x5e),
		play_toClient_packet_set_passengers(0x5f),
		play_toClient_packet_teams(0x60),
		play_toClient_packet_scoreboard_score(0x61),
		play_toClient_packet_simulation_distance(0x62),
		play_toClient_packet_set_title_subtitle(0x63),
		play_toClient_packet_update_time(0x64),
		play_toClient_packet_set_title_text(0x65),
		play_toClient_packet_set_title_time(0x66),
		play_toClient_packet_entity_sound_effect(0x67),
		play_toClient_packet_sound_effect(0x68),
		play_toClient_packet_start_configuration(0x69),
		play_toClient_packet_stop_sound(0x6a),
		play_toClient_packet_common_store_cookie(0x6b),
		play_toClient_packet_system_chat(0x6c),
		play_toClient_packet_playerlist_header(0x6d),
		play_toClient_packet_nbt_query_response(0x6e),
		play_toClient_packet_collect(0x6f),
		play_toClient_packet_entity_teleport(0x70),
		play_toClient_packet_set_ticking_state(0x71),
		play_toClient_packet_step_tick(0x72),
		play_toClient_packet_common_transfer(0x73),
		play_toClient_packet_advancements(0x74),
		play_toClient_packet_entity_update_attributes(0x75),
		play_toClient_packet_entity_effect(0x76),
		play_toClient_packet_declare_recipes(0x77),
		play_toClient_packet_tags(0x78),
		play_toServer_packet_teleport_confirm(0x00),
		play_toServer_packet_query_block_nbt(0x01),
		play_toServer_packet_set_difficulty(0x02),
		play_toServer_packet_message_acknowledgement(0x03),
		play_toServer_packet_chat_command(0x04),
		play_toServer_packet_chat_command_signed(0x05),
		play_toServer_packet_chat_message(0x06),
		play_toServer_packet_chat_session_update(0x07),
		play_toServer_packet_chunk_batch_received(0x08),
		play_toServer_packet_client_command(0x09),
		play_toServer_packet_settings(0x0a),
		play_toServer_packet_tab_complete(0x0b),
		play_toServer_packet_configuration_acknowledged(0x0c),
		play_toServer_packet_enchant_item(0x0d),
		play_toServer_packet_window_click(0x0e),
		play_toServer_packet_close_window(0x0f),
		play_toServer_packet_set_slot_state(0x10),
		play_toServer_packet_common_cookie_response(0x11),
		play_toServer_packet_custom_payload(0x12),
		play_toServer_null(0x13),
		play_toServer_packet_edit_book(0x14),
		play_toServer_packet_query_entity_nbt(0x15),
		play_toServer_packet_use_entity(0x16),
		play_toServer_packet_generate_structure(0x17),
		play_toServer_packet_keep_alive(0x18),
		play_toServer_packet_lock_difficulty(0x19),
		play_toServer_packet_position(0x1a),
		play_toServer_packet_position_look(0x1b),
		play_toServer_packet_look(0x1c),
		play_toServer_packet_flying(0x1d),
		play_toServer_packet_vehicle_move(0x1e),
		play_toServer_packet_steer_boat(0x1f),
		play_toServer_packet_pick_item(0x20),
		play_toServer_packet_ping_request(0x21),
		play_toServer_packet_craft_recipe_request(0x22),
		play_toServer_packet_abilities(0x23),
		play_toServer_packet_block_dig(0x24),
		play_toServer_packet_entity_action(0x25),
		play_toServer_packet_steer_vehicle(0x26),
		play_toServer_packet_pong(0x27),
		play_toServer_packet_recipe_book(0x28),
		play_toServer_packet_displayed_recipe(0x29),
		play_toServer_packet_name_item(0x2a),
		play_toServer_packet_resource_pack_receive(0x2b),
		play_toServer_packet_advancement_tab(0x2c),
		play_toServer_packet_select_trade(0x2d),
		play_toServer_packet_set_beacon_effect(0x2e),
		play_toServer_packet_held_item_slot(0x2f),
		play_toServer_packet_update_command_block(0x30),
		play_toServer_packet_update_command_block_minecart(0x31),
		play_toServer_packet_set_creative_slot(0x32),
		play_toServer_packet_update_jigsaw_block(0x33),
		play_toServer_packet_update_structure_block(0x34),
		play_toServer_packet_update_sign(0x35),
		play_toServer_packet_arm_animation(0x36),
		play_toServer_packet_spectate(0x37),
		play_toServer_packet_block_place(0x38),
		play_toServer_packet_use_item(0x39);
		
		private final int id;
		PacketIDs(int id){
			this.id = id;
		}
		public int getId(){
			return this.id;
		}
	}
}

