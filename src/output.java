import Serializables.*;
import Serializables.Types.*;
class Protocol { 
	static class handshaking {
		static class toServer {
			static class packet_set_protocol extends PacketBase{
				VarInt protocolVersion;
				mcString serverHost;
				u16 serverPort;
				VarInt nextState;
				
				public packet_set_protocol(
				VarInt protocolVersion,
				mcString serverHost,
				u16 serverPort,
				VarInt nextState
				){
					super(PacketIDs.handshaking_toServer_packet_set_protocol.getId(), protocolVersion, serverHost, serverPort, nextState);
					this.protocolVersion = protocolVersion;
					this.serverHost =  serverHost;
					this.serverPort =  serverPort;
					this.nextState =  nextState;
				}
			}
			static class packet_legacy_server_list_ping extends PacketBase{
				u8 payload;
				
				public packet_legacy_server_list_ping(
				u8 payload
				){
					super(PacketIDs.handshaking_toServer_packet_legacy_server_list_ping.getId(), payload);
					this.payload = payload;
				}
			}
		}
	}
	static class status {
		static class toClient {
			static class packet_server_info extends PacketBase{
				mcString response;
				
				public packet_server_info(
				mcString response
				){
					super(PacketIDs.status_toClient_packet_server_info.getId(), response);
					this.response = response;
				}
			}
			static class packet_ping extends PacketBase{
				i64 time;
				
				public packet_ping(
				i64 time
				){
					super(PacketIDs.status_toClient_packet_ping.getId(), time);
					this.time = time;
				}
			}
		}
		static class toServer {
			static class packet_ping_start extends PacketBase{
				
				public packet_ping_start(){
					super(PacketIDs.status_toServer_packet_ping_start.getId());
					
				}
			}
			static class packet_ping extends PacketBase{
				i64 time;
				
				public packet_ping(
				i64 time
				){
					super(PacketIDs.status_toServer_packet_ping.getId(), time);
					this.time = time;
				}
			}
		}
	}
	static class login {
		static class toClient {
			static class packet_disconnect extends PacketBase{
				mcString reason;
				
				public packet_disconnect(
				mcString reason
				){
					super(PacketIDs.login_toClient_packet_disconnect.getId(), reason);
					this.reason = reason;
				}
			}
			static class packet_encryption_begin extends PacketBase{
				mcString serverId;
				Bit[] publicKey;
				Bit[] verifyToken;
				
				public packet_encryption_begin(
				mcString serverId,
				Bit[] publicKey,
				Bit[] verifyToken
				){
					super(PacketIDs.login_toClient_packet_encryption_begin.getId(), serverId, publicKey, verifyToken);
					this.serverId = serverId;
					this.publicKey =  publicKey;
					this.verifyToken =  verifyToken;
				}
			}
			static class packet_success extends PacketBase{
				mcString uuid;
				mcString username;
				
				public packet_success(
				mcString uuid,
				mcString username
				){
					super(PacketIDs.login_toClient_packet_success.getId(), uuid, username);
					this.uuid = uuid;
					this.username =  username;
				}
			}
			static class packet_compress extends PacketBase{
				VarInt threshold;
				
				public packet_compress(
				VarInt threshold
				){
					super(PacketIDs.login_toClient_packet_compress.getId(), threshold);
					this.threshold = threshold;
				}
			}
		}
		static class toServer {
			static class packet_login_start extends PacketBase{
				mcString username;
				
				public packet_login_start(
				mcString username
				){
					super(PacketIDs.login_toServer_packet_login_start.getId(), username);
					this.username = username;
				}
			}
			static class packet_encryption_begin extends PacketBase{
				Bit[] sharedSecret;
				Bit[] verifyToken;
				
				public packet_encryption_begin(
				Bit[] sharedSecret,
				Bit[] verifyToken
				){
					super(PacketIDs.login_toServer_packet_encryption_begin.getId(), sharedSecret, verifyToken);
					this.sharedSecret = sharedSecret;
					this.verifyToken =  verifyToken;
				}
			}
		}
	}
	static class play {
		static class toClient {
			static class packet_spawn_entity extends PacketBase{
				VarInt entityId;
				UUID objectUUID;
				i8 type;
				f32 x;
				f32 y;
				f32 z;
				i8 pitch;
				i8 yaw;
				i32 objectData;
				i16 velocityX;
				i16 velocityY;
				i16 velocityZ;
				
				public packet_spawn_entity(
				VarInt entityId,
				UUID objectUUID,
				i8 type,
				f32 x,
				f32 y,
				f32 z,
				i8 pitch,
				i8 yaw,
				i32 objectData,
				i16 velocityX,
				i16 velocityY,
				i16 velocityZ
				){
					super(PacketIDs.play_toClient_packet_spawn_entity.getId(), entityId, objectUUID, type, x, y, z, pitch, yaw, objectData, velocityX, velocityY, velocityZ);
					this.entityId = entityId;
					this.objectUUID =  objectUUID;
					this.type =  type;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.pitch =  pitch;
					this.yaw =  yaw;
					this.objectData =  objectData;
					this.velocityX =  velocityX;
					this.velocityY =  velocityY;
					this.velocityZ =  velocityZ;
				}
			}
			static class packet_spawn_entity_experience_orb extends PacketBase{
				VarInt entityId;
				f32 x;
				f32 y;
				f32 z;
				i16 count;
				
				public packet_spawn_entity_experience_orb(
				VarInt entityId,
				f32 x,
				f32 y,
				f32 z,
				i16 count
				){
					super(PacketIDs.play_toClient_packet_spawn_entity_experience_orb.getId(), entityId, x, y, z, count);
					this.entityId = entityId;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.count =  count;
				}
			}
			static class packet_spawn_entity_weather extends PacketBase{
				VarInt entityId;
				i8 type;
				f32 x;
				f32 y;
				f32 z;
				
				public packet_spawn_entity_weather(
				VarInt entityId,
				i8 type,
				f32 x,
				f32 y,
				f32 z
				){
					super(PacketIDs.play_toClient_packet_spawn_entity_weather.getId(), entityId, type, x, y, z);
					this.entityId = entityId;
					this.type =  type;
					this.x =  x;
					this.y =  y;
					this.z =  z;
				}
			}
			static class packet_spawn_entity_living extends PacketBase{
				VarInt entityId;
				UUID entityUUID;
				VarInt type;
				f32 x;
				f32 y;
				f32 z;
				i8 yaw;
				i8 pitch;
				i8 headPitch;
				i16 velocityX;
				i16 velocityY;
				i16 velocityZ;
				ComplexType metadata;
				
				public packet_spawn_entity_living(
				VarInt entityId,
				UUID entityUUID,
				VarInt type,
				f32 x,
				f32 y,
				f32 z,
				i8 yaw,
				i8 pitch,
				i8 headPitch,
				i16 velocityX,
				i16 velocityY,
				i16 velocityZ,
				ComplexType metadata
				){
					super(PacketIDs.play_toClient_packet_spawn_entity_living.getId(), entityId, entityUUID, type, x, y, z, yaw, pitch, headPitch, velocityX, velocityY, velocityZ, metadata);
					this.entityId = entityId;
					this.entityUUID =  entityUUID;
					this.type =  type;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.headPitch =  headPitch;
					this.velocityX =  velocityX;
					this.velocityY =  velocityY;
					this.velocityZ =  velocityZ;
					this.metadata =  metadata;
				}
			}
			static class packet_spawn_entity_painting extends PacketBase{
				VarInt entityId;
				UUID entityUUID;
				mcString title;
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				u8 direction;
				
				public packet_spawn_entity_painting(
				VarInt entityId,
				UUID entityUUID,
				mcString title,
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				u8 direction
				){
					super(PacketIDs.play_toClient_packet_spawn_entity_painting.getId(), entityId, entityUUID, title, location_x, location_y, location_z, direction);
					this.entityId = entityId;
					this.entityUUID =  entityUUID;
					this.title =  title;
					this.location_x =  location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.direction =  direction;
				}
			}
			static class packet_named_entity_spawn extends PacketBase{
				VarInt entityId;
				UUID playerUUID;
				f32 x;
				f32 y;
				f32 z;
				i8 yaw;
				i8 pitch;
				ComplexType metadata;
				
				public packet_named_entity_spawn(
				VarInt entityId,
				UUID playerUUID,
				f32 x,
				f32 y,
				f32 z,
				i8 yaw,
				i8 pitch,
				ComplexType metadata
				){
					super(PacketIDs.play_toClient_packet_named_entity_spawn.getId(), entityId, playerUUID, x, y, z, yaw, pitch, metadata);
					this.entityId = entityId;
					this.playerUUID =  playerUUID;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.metadata =  metadata;
				}
			}
			static class packet_animation extends PacketBase{
				VarInt entityId;
				u8 animation;
				
				public packet_animation(
				VarInt entityId,
				u8 animation
				){
					super(PacketIDs.play_toClient_packet_animation.getId(), entityId, animation);
					this.entityId = entityId;
					this.animation =  animation;
				}
			}
			static class packet_statistics extends PacketBase{
				//name
				//value
				nArray2<mcString[],VarInt[]> entries;
				
				public packet_statistics(
				//name
				//value
				nArray2<mcString[],VarInt[]> entries
				){
					super(PacketIDs.play_toClient_packet_statistics.getId(), entries);
					this.entries = entries;
				}
			}
			static class packet_advancements extends PacketBase{
				Boolean reset;
				//key
				//optional value_parentId
				//optional value_displayData
				//optional value_displayData
				//optional value_displayData
				//optional Switch: SwitchBuildable{compareToFieldName='blockId', fields={-1=[LSerializables.Refactor.Flattenable;@3232a28a}, defaultField=[LSerializables.Refactor.ContainerField;@73e22a3d}
				//optional value_displayData
				//optional _unused
				//optional hidden
				//optional show_toast
				//optional has_background_texture
				//optional Switch: SwitchBuildable{compareToFieldName='flags/has_background_texture', fields={1=[LSerializables.Refactor.Flattenable;@47faa49c}, defaultField=[LSerializables.Refactor.Flattenable;@28f2a10f}
				//optional value_displayData
				//optional value_displayData
				//value_criteria
				//value_requirements
				nArray16<mcString[],mcString[],mcString[],mcString[],i16[],Object[],VarInt[],i32[],i8[],i8[],i8[],Object[],f32[],f32[],nArray2<mcString,Void>[],mcString[][][]> advancementMapping;
				mcString[] identifiers;
				//key
				//value
				nArray2<mcString[],nArray2<mcString,i64>[]> progressMapping;
				
				public packet_advancements(
				Boolean reset,
				//key
				//optional value_parentId
				//optional value_displayData
				//optional value_displayData
				//optional value_displayData
				//optional Switch: SwitchBuildable{compareToFieldName='blockId', fields={-1=[LSerializables.Refactor.Flattenable,@3232a28a}, defaultField=[LSerializables.Refactor.ContainerField,@73e22a3d}
				//optional value_displayData
				//optional _unused
				//optional hidden
				//optional show_toast
				//optional has_background_texture
				//optional Switch: SwitchBuildable{compareToFieldName='flags/has_background_texture', fields={1=[LSerializables.Refactor.Flattenable,@47faa49c}, defaultField=[LSerializables.Refactor.Flattenable,@28f2a10f}
				//optional value_displayData
				//optional value_displayData
				//value_criteria
				//value_requirements
				nArray16<mcString[],mcString[],mcString[],mcString[],i16[],Object[],VarInt[],i32[],i8[],i8[],i8[],Object[],f32[],f32[],nArray2<mcString,Void>[],mcString[][][]> advancementMapping,
				mcString[] identifiers,
				//key
				//value
				nArray2<mcString[],nArray2<mcString,i64>[]> progressMapping
				){
					super(PacketIDs.play_toClient_packet_advancements.getId(), reset, advancementMapping, identifiers, progressMapping);
					this.reset = reset;
					this.advancementMapping =  advancementMapping;
					this.identifiers =  identifiers;
					this.progressMapping =  progressMapping;
				}
			}
			static class packet_block_break_animation extends PacketBase{
				VarInt entityId;
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				i8 destroyStage;
				
				public packet_block_break_animation(
				VarInt entityId,
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				i8 destroyStage
				){
					super(PacketIDs.play_toClient_packet_block_break_animation.getId(), entityId, location_x, location_y, location_z, destroyStage);
					this.entityId = entityId;
					this.location_x =  location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.destroyStage =  destroyStage;
				}
			}
			static class packet_tile_entity_data extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				u8 action;
				Objects nbtData;
				
				public packet_tile_entity_data(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				u8 action,
				Objects nbtData
				){
					super(PacketIDs.play_toClient_packet_tile_entity_data.getId(), location_x, location_y, location_z, action, nbtData);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.action =  action;
					this.nbtData =  nbtData;
				}
			}
			static class packet_block_action extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				u8 byte1;
				u8 byte2;
				VarInt blockId;
				
				public packet_block_action(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				u8 byte1,
				u8 byte2,
				VarInt blockId
				){
					super(PacketIDs.play_toClient_packet_block_action.getId(), location_x, location_y, location_z, byte1, byte2, blockId);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.byte1 =  byte1;
					this.byte2 =  byte2;
					this.blockId =  blockId;
				}
			}
			static class packet_block_change extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				VarInt type;
				
				public packet_block_change(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				VarInt type
				){
					super(PacketIDs.play_toClient_packet_block_change.getId(), location_x, location_y, location_z, type);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.type =  type;
				}
			}
			static class packet_boss_bar extends PacketBase{
				UUID entityUUID;
				VarInt action;
				//Only present if action is 0 or 3
				mcString c0or3_title_mcString;
				
				//Only present if action is 0 or 2
				f32 c0or2_health_f32;
				
				//Only present if action is 0 or 4
				VarInt c0or4_color_VarInt;
				
				//Only present if action is 0 or 4
				VarInt c0or4_dividers_VarInt;
				
				//Only present if action is 0 or 5
				u8 c0or5_flags_u8;
				
				
				public packet_boss_bar(
				UUID entityUUID,
				VarInt action,
				//Only present if action is 0 or 3
				mcString c0or3_title_mcString,
				
				//Only present if action is 0 or 2
				f32 c0or2_health_f32,
				
				//Only present if action is 0 or 4
				VarInt c0or4_color_VarInt,
				
				//Only present if action is 0 or 4
				VarInt c0or4_dividers_VarInt,
				
				//Only present if action is 0 or 5
				u8 c0or5_flags_u8
				
				){
					super(PacketIDs.play_toClient_packet_boss_bar.getId(), entityUUID, action, c0or3_title_mcString, c0or2_health_f32, c0or4_color_VarInt, c0or4_dividers_VarInt, c0or5_flags_u8);
					this.entityUUID = entityUUID;
					this.action =  action;
					this.c0or3_title_mcString =  c0or3_title_mcString;
					this.c0or2_health_f32 =  c0or2_health_f32;
					this.c0or4_color_VarInt =  c0or4_color_VarInt;
					this.c0or4_dividers_VarInt =  c0or4_dividers_VarInt;
					this.c0or5_flags_u8 =  c0or5_flags_u8;
				}
			}
			static class packet_difficulty extends PacketBase{
				u8 difficulty;
				
				public packet_difficulty(
				u8 difficulty
				){
					super(PacketIDs.play_toClient_packet_difficulty.getId(), difficulty);
					this.difficulty = difficulty;
				}
			}
			static class packet_tab_complete extends PacketBase{
				mcString[] matches;
				
				public packet_tab_complete(
				mcString[] matches
				){
					super(PacketIDs.play_toClient_packet_tab_complete.getId(), matches);
					this.matches = matches;
				}
			}
			static class packet_chat extends PacketBase{
				mcString message;
				i8 position;
				
				public packet_chat(
				mcString message,
				i8 position
				){
					super(PacketIDs.play_toClient_packet_chat.getId(), message, position);
					this.message = message;
					this.position =  position;
				}
			}
			static class packet_multi_block_change extends PacketBase{
				i32 chunkX;
				i32 chunkZ;
				//horizontalPos
				//y
				//blockId
				nArray3<u8[],u8[],VarInt[]> records;
				
				public packet_multi_block_change(
				i32 chunkX,
				i32 chunkZ,
				//horizontalPos
				//y
				//blockId
				nArray3<u8[],u8[],VarInt[]> records
				){
					super(PacketIDs.play_toClient_packet_multi_block_change.getId(), chunkX, chunkZ, records);
					this.chunkX = chunkX;
					this.chunkZ =  chunkZ;
					this.records =  records;
				}
			}
			static class packet_transaction extends PacketBase{
				i8 windowId;
				i16 action;
				Boolean accepted;
				
				public packet_transaction(
				i8 windowId,
				i16 action,
				Boolean accepted
				){
					super(PacketIDs.play_toClient_packet_transaction.getId(), windowId, action, accepted);
					this.windowId = windowId;
					this.action =  action;
					this.accepted =  accepted;
				}
			}
			static class packet_close_window extends PacketBase{
				u8 windowId;
				
				public packet_close_window(
				u8 windowId
				){
					super(PacketIDs.play_toClient_packet_close_window.getId(), windowId);
					this.windowId = windowId;
				}
			}
			static class packet_open_window extends PacketBase{
				u8 windowId;
				mcString inventoryType;
				mcString windowTitle;
				u8 slotCount;
				//Only present if inventoryType is EntityHorse
				i32 cEntityHorse_entityId_i32;
				
				
				public packet_open_window(
				u8 windowId,
				mcString inventoryType,
				mcString windowTitle,
				u8 slotCount,
				//Only present if inventoryType is EntityHorse
				i32 cEntityHorse_entityId_i32
				
				){
					super(PacketIDs.play_toClient_packet_open_window.getId(), windowId, inventoryType, windowTitle, slotCount, cEntityHorse_entityId_i32);
					this.windowId = windowId;
					this.inventoryType =  inventoryType;
					this.windowTitle =  windowTitle;
					this.slotCount =  slotCount;
					this.cEntityHorse_entityId_i32 =  cEntityHorse_entityId_i32;
				}
			}
			static class packet_window_items extends PacketBase{
				u8 windowId;
				//blockId
				//Switch: SwitchBuildable{compareToFieldName='blockId', fields={-1=[LSerializables.Refactor.Flattenable;@3232a28a}, defaultField=[LSerializables.Refactor.ContainerField;@73e22a3d}
				nArray2<i16[],Object[]> items;
				
				public packet_window_items(
				u8 windowId,
				//blockId
				//Switch: SwitchBuildable{compareToFieldName='blockId', fields={-1=[LSerializables.Refactor.Flattenable,@3232a28a}, defaultField=[LSerializables.Refactor.ContainerField,@73e22a3d}
				nArray2<i16[],Object[]> items
				){
					super(PacketIDs.play_toClient_packet_window_items.getId(), windowId, items);
					this.windowId = windowId;
					this.items =  items;
				}
			}
			static class packet_craft_progress_bar extends PacketBase{
				u8 windowId;
				i16 property;
				i16 value;
				
				public packet_craft_progress_bar(
				u8 windowId,
				i16 property,
				i16 value
				){
					super(PacketIDs.play_toClient_packet_craft_progress_bar.getId(), windowId, property, value);
					this.windowId = windowId;
					this.property =  property;
					this.value =  value;
				}
			}
			static class packet_set_slot extends PacketBase{
				i8 windowId;
				i16 slot;
				i16 item_blockId;
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount;
				i16 item_anon_itemDamage;
				Objects item_anon_nbtData;
				//Default field end
				
				
				public packet_set_slot(
				i8 windowId,
				i16 slot,
				i16 item_blockId,
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount,
				i16 item_anon_itemDamage,
				Objects item_anon_nbtData
				//Default field end
				
				){
					super(PacketIDs.play_toClient_packet_set_slot.getId(), windowId, slot, item_blockId, item_anon_itemCount, item_anon_itemDamage, item_anon_nbtData);
					this.windowId = windowId;
					this.slot =  slot;
					this.item_blockId =  item_blockId;
					this.item_anon_itemCount =  item_anon_itemCount;
					this.item_anon_itemDamage =  item_anon_itemDamage;
					this.item_anon_nbtData =  item_anon_nbtData;
				}
			}
			static class packet_set_cooldown extends PacketBase{
				VarInt itemID;
				VarInt cooldownTicks;
				
				public packet_set_cooldown(
				VarInt itemID,
				VarInt cooldownTicks
				){
					super(PacketIDs.play_toClient_packet_set_cooldown.getId(), itemID, cooldownTicks);
					this.itemID = itemID;
					this.cooldownTicks =  cooldownTicks;
				}
			}
			static class packet_custom_payload extends PacketBase{
				mcString channel;
				Byte[] data;
				
				public packet_custom_payload(
				mcString channel,
				Byte[] data
				){
					super(PacketIDs.play_toClient_packet_custom_payload.getId(), channel, data);
					this.channel = channel;
					this.data =  data;
				}
			}
			static class packet_named_sound_effect extends PacketBase{
				mcString soundName;
				VarInt soundCategory;
				i32 x;
				i32 y;
				i32 z;
				f32 volume;
				f32 pitch;
				
				public packet_named_sound_effect(
				mcString soundName,
				VarInt soundCategory,
				i32 x,
				i32 y,
				i32 z,
				f32 volume,
				f32 pitch
				){
					super(PacketIDs.play_toClient_packet_named_sound_effect.getId(), soundName, soundCategory, x, y, z, volume, pitch);
					this.soundName = soundName;
					this.soundCategory =  soundCategory;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.volume =  volume;
					this.pitch =  pitch;
				}
			}
			static class packet_kick_disconnect extends PacketBase{
				mcString reason;
				
				public packet_kick_disconnect(
				mcString reason
				){
					super(PacketIDs.play_toClient_packet_kick_disconnect.getId(), reason);
					this.reason = reason;
				}
			}
			static class packet_entity_status extends PacketBase{
				i32 entityId;
				i8 entityStatus;
				
				public packet_entity_status(
				i32 entityId,
				i8 entityStatus
				){
					super(PacketIDs.play_toClient_packet_entity_status.getId(), entityId, entityStatus);
					this.entityId = entityId;
					this.entityStatus =  entityStatus;
				}
			}
			static class packet_explosion extends PacketBase{
				f32 x;
				f32 y;
				f32 z;
				f32 radius;
				//x
				//y
				//z
				nArray3<i8[],i8[],i8[]> affectedBlockOffsets;
				f32 playerMotionX;
				f32 playerMotionY;
				f32 playerMotionZ;
				
				public packet_explosion(
				f32 x,
				f32 y,
				f32 z,
				f32 radius,
				//x
				//y
				//z
				nArray3<i8[],i8[],i8[]> affectedBlockOffsets,
				f32 playerMotionX,
				f32 playerMotionY,
				f32 playerMotionZ
				){
					super(PacketIDs.play_toClient_packet_explosion.getId(), x, y, z, radius, affectedBlockOffsets, playerMotionX, playerMotionY, playerMotionZ);
					this.x = x;
					this.y =  y;
					this.z =  z;
					this.radius =  radius;
					this.affectedBlockOffsets =  affectedBlockOffsets;
					this.playerMotionX =  playerMotionX;
					this.playerMotionY =  playerMotionY;
					this.playerMotionZ =  playerMotionZ;
				}
			}
			static class packet_unload_chunk extends PacketBase{
				i32 chunkX;
				i32 chunkZ;
				
				public packet_unload_chunk(
				i32 chunkX,
				i32 chunkZ
				){
					super(PacketIDs.play_toClient_packet_unload_chunk.getId(), chunkX, chunkZ);
					this.chunkX = chunkX;
					this.chunkZ =  chunkZ;
				}
			}
			static class packet_game_state_change extends PacketBase{
				u8 reason;
				f32 gameMode;
				
				public packet_game_state_change(
				u8 reason,
				f32 gameMode
				){
					super(PacketIDs.play_toClient_packet_game_state_change.getId(), reason, gameMode);
					this.reason = reason;
					this.gameMode =  gameMode;
				}
			}
			static class packet_keep_alive extends PacketBase{
				i64 keepAliveId;
				
				public packet_keep_alive(
				i64 keepAliveId
				){
					super(PacketIDs.play_toClient_packet_keep_alive.getId(), keepAliveId);
					this.keepAliveId = keepAliveId;
				}
			}
			static class packet_map_chunk extends PacketBase{
				i32 x;
				i32 z;
				Boolean groundUp;
				VarInt bitMap;
				Bit[] chunkData;
				Nbt[] blockEntities;
				
				public packet_map_chunk(
				i32 x,
				i32 z,
				Boolean groundUp,
				VarInt bitMap,
				Bit[] chunkData,
				Nbt[] blockEntities
				){
					super(PacketIDs.play_toClient_packet_map_chunk.getId(), x, z, groundUp, bitMap, chunkData, blockEntities);
					this.x = x;
					this.z =  z;
					this.groundUp =  groundUp;
					this.bitMap =  bitMap;
					this.chunkData =  chunkData;
					this.blockEntities =  blockEntities;
				}
			}
			static class packet_world_event extends PacketBase{
				i32 effectId;
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				i32 data;
				Boolean global;
				
				public packet_world_event(
				i32 effectId,
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				i32 data,
				Boolean global
				){
					super(PacketIDs.play_toClient_packet_world_event.getId(), effectId, location_x, location_y, location_z, data, global);
					this.effectId = effectId;
					this.location_x =  location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.data =  data;
					this.global =  global;
				}
			}
			static class packet_world_particles extends PacketBase{
				i32 particleId;
				Boolean longDistance;
				f32 x;
				f32 y;
				f32 z;
				f32 offsetX;
				f32 offsetY;
				f32 offsetZ;
				f32 particleData;
				i32 particles;
				//Only present if particleId is 36 or 37 or 38
				VarInt[] c36or37or38_data_VarInt;
				
				
				public packet_world_particles(
				i32 particleId,
				Boolean longDistance,
				f32 x,
				f32 y,
				f32 z,
				f32 offsetX,
				f32 offsetY,
				f32 offsetZ,
				f32 particleData,
				i32 particles,
				//Only present if particleId is 36 or 37 or 38
				VarInt[] c36or37or38_data_VarInt
				
				){
					super(PacketIDs.play_toClient_packet_world_particles.getId(), particleId, longDistance, x, y, z, offsetX, offsetY, offsetZ, particleData, particles, c36or37or38_data_VarInt);
					this.particleId = particleId;
					this.longDistance =  longDistance;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.offsetX =  offsetX;
					this.offsetY =  offsetY;
					this.offsetZ =  offsetZ;
					this.particleData =  particleData;
					this.particles =  particles;
					this.c36or37or38_data_VarInt =  c36or37or38_data_VarInt;
				}
			}
			static class packet_login extends PacketBase{
				i32 entityId;
				u8 gameMode;
				i32 dimension;
				u8 difficulty;
				u8 maxPlayers;
				mcString levelType;
				Boolean reducedDebugInfo;
				
				public packet_login(
				i32 entityId,
				u8 gameMode,
				i32 dimension,
				u8 difficulty,
				u8 maxPlayers,
				mcString levelType,
				Boolean reducedDebugInfo
				){
					super(PacketIDs.play_toClient_packet_login.getId(), entityId, gameMode, dimension, difficulty, maxPlayers, levelType, reducedDebugInfo);
					this.entityId = entityId;
					this.gameMode =  gameMode;
					this.dimension =  dimension;
					this.difficulty =  difficulty;
					this.maxPlayers =  maxPlayers;
					this.levelType =  levelType;
					this.reducedDebugInfo =  reducedDebugInfo;
				}
			}
			static class packet_map extends PacketBase{
				VarInt itemDamage;
				i8 scale;
				Boolean trackingPosition;
				//directionAndType
				//x
				//z
				nArray3<i8[],i8[],i8[]> icons;
				i8 columns;
				//if columns is any of [0]all fields are empty
				//Default field: 
				i8 rows;
				//Default field end
				
				//if columns is any of [0]all fields are empty
				//Default field: 
				i8 x;
				//Default field end
				
				//if columns is any of [0]all fields are empty
				//Default field: 
				i8 y;
				//Default field end
				
				//if columns is any of [0]all fields are empty
				//Default field: 
				Bit[] data;
				//Default field end
				
				
				public packet_map(
				VarInt itemDamage,
				i8 scale,
				Boolean trackingPosition,
				//directionAndType
				//x
				//z
				nArray3<i8[],i8[],i8[]> icons,
				i8 columns,
				//if columns is any of [0]all fields are empty
				//Default field: 
				i8 rows,
				//Default field end
				
				//if columns is any of [0]all fields are empty
				//Default field: 
				i8 x,
				//Default field end
				
				//if columns is any of [0]all fields are empty
				//Default field: 
				i8 y,
				//Default field end
				
				//if columns is any of [0]all fields are empty
				//Default field: 
				Bit[] data
				//Default field end
				
				){
					super(PacketIDs.play_toClient_packet_map.getId(), itemDamage, scale, trackingPosition, icons, columns, rows, x, y, data);
					this.itemDamage = itemDamage;
					this.scale =  scale;
					this.trackingPosition =  trackingPosition;
					this.icons =  icons;
					this.columns =  columns;
					this.rows =  rows;
					this.x =  x;
					this.y =  y;
					this.data =  data;
				}
			}
			static class packet_rel_entity_move extends PacketBase{
				VarInt entityId;
				i16 dX;
				i16 dY;
				i16 dZ;
				Boolean onGround;
				
				public packet_rel_entity_move(
				VarInt entityId,
				i16 dX,
				i16 dY,
				i16 dZ,
				Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_rel_entity_move.getId(), entityId, dX, dY, dZ, onGround);
					this.entityId = entityId;
					this.dX =  dX;
					this.dY =  dY;
					this.dZ =  dZ;
					this.onGround =  onGround;
				}
			}
			static class packet_entity_move_look extends PacketBase{
				VarInt entityId;
				i16 dX;
				i16 dY;
				i16 dZ;
				i8 yaw;
				i8 pitch;
				Boolean onGround;
				
				public packet_entity_move_look(
				VarInt entityId,
				i16 dX,
				i16 dY,
				i16 dZ,
				i8 yaw,
				i8 pitch,
				Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_entity_move_look.getId(), entityId, dX, dY, dZ, yaw, pitch, onGround);
					this.entityId = entityId;
					this.dX =  dX;
					this.dY =  dY;
					this.dZ =  dZ;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.onGround =  onGround;
				}
			}
			static class packet_entity_look extends PacketBase{
				VarInt entityId;
				i8 yaw;
				i8 pitch;
				Boolean onGround;
				
				public packet_entity_look(
				VarInt entityId,
				i8 yaw,
				i8 pitch,
				Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_entity_look.getId(), entityId, yaw, pitch, onGround);
					this.entityId = entityId;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.onGround =  onGround;
				}
			}
			static class packet_entity extends PacketBase{
				VarInt entityId;
				
				public packet_entity(
				VarInt entityId
				){
					super(PacketIDs.play_toClient_packet_entity.getId(), entityId);
					this.entityId = entityId;
				}
			}
			static class packet_vehicle_move extends PacketBase{
				f32 x;
				f32 y;
				f32 z;
				f32 yaw;
				f32 pitch;
				
				public packet_vehicle_move(
				f32 x,
				f32 y,
				f32 z,
				f32 yaw,
				f32 pitch
				){
					super(PacketIDs.play_toClient_packet_vehicle_move.getId(), x, y, z, yaw, pitch);
					this.x = x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
				}
			}
			static class packet_open_sign_entity extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				
				public packet_open_sign_entity(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z
				){
					super(PacketIDs.play_toClient_packet_open_sign_entity.getId(), location_x, location_y, location_z);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
				}
			}
			static class packet_craft_recipe_response extends PacketBase{
				i8 windowId;
				VarInt recipe;
				
				public packet_craft_recipe_response(
				i8 windowId,
				VarInt recipe
				){
					super(PacketIDs.play_toClient_packet_craft_recipe_response.getId(), windowId, recipe);
					this.windowId = windowId;
					this.recipe =  recipe;
				}
			}
			static class packet_abilities extends PacketBase{
				i8 flags;
				f32 flyingSpeed;
				f32 walkingSpeed;
				
				public packet_abilities(
				i8 flags,
				f32 flyingSpeed,
				f32 walkingSpeed
				){
					super(PacketIDs.play_toClient_packet_abilities.getId(), flags, flyingSpeed, walkingSpeed);
					this.flags = flags;
					this.flyingSpeed =  flyingSpeed;
					this.walkingSpeed =  walkingSpeed;
				}
			}
			static class packet_combat_event extends PacketBase{
				VarInt event;
				//Only present if event is 1
				VarInt c1_duration_VarInt;
				
				//Only present if event is 2
				VarInt c2_playerId_VarInt;
				
				//Only present if event is 1 or 2
				i32 c1or2_entityId_i32;
				
				//Only present if event is 2
				mcString c2_message_mcString;
				
				
				public packet_combat_event(
				VarInt event,
				//Only present if event is 1
				VarInt c1_duration_VarInt,
				
				//Only present if event is 2
				VarInt c2_playerId_VarInt,
				
				//Only present if event is 1 or 2
				i32 c1or2_entityId_i32,
				
				//Only present if event is 2
				mcString c2_message_mcString
				
				){
					super(PacketIDs.play_toClient_packet_combat_event.getId(), event, c1_duration_VarInt, c2_playerId_VarInt, c1or2_entityId_i32, c2_message_mcString);
					this.event = event;
					this.c1_duration_VarInt =  c1_duration_VarInt;
					this.c2_playerId_VarInt =  c2_playerId_VarInt;
					this.c1or2_entityId_i32 =  c1or2_entityId_i32;
					this.c2_message_mcString =  c2_message_mcString;
				}
			}
			static class packet_player_info extends PacketBase{
				VarInt action;
				//UUID
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable;@169e6180}, defaultField=[LSerializables.Refactor.Flattenable;@35aea049}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable;@7205765b}, defaultField=[LSerializables.Refactor.Flattenable;@47987356}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable;@22ef9844, 1=[LSerializables.Refactor.Flattenable;@6283d8b8}, defaultField=[LSerializables.Refactor.Flattenable;@3b6ddd1d}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable;@3f6b0be5, 2=[LSerializables.Refactor.Flattenable;@611889f4}, defaultField=[LSerializables.Refactor.Flattenable;@1da2cb77}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable;@48f278eb, 3=[LSerializables.Refactor.Flattenable;@2f217633}, defaultField=[LSerializables.Refactor.Flattenable;@a530d0a}
				nArray6<UUID[],Object[],Object[],Object[],Object[],Object[]> data;
				
				public packet_player_info(
				VarInt action,
				//UUID
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable,@169e6180}, defaultField=[LSerializables.Refactor.Flattenable,@35aea049}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable,@7205765b}, defaultField=[LSerializables.Refactor.Flattenable,@47987356}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable,@22ef9844, 1=[LSerializables.Refactor.Flattenable,@6283d8b8}, defaultField=[LSerializables.Refactor.Flattenable,@3b6ddd1d}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable,@3f6b0be5, 2=[LSerializables.Refactor.Flattenable,@611889f4}, defaultField=[LSerializables.Refactor.Flattenable,@1da2cb77}
				//Switch: SwitchBuildable{compareToFieldName='../action', fields={0=[LSerializables.Refactor.Flattenable,@48f278eb, 3=[LSerializables.Refactor.Flattenable,@2f217633}, defaultField=[LSerializables.Refactor.Flattenable,@a530d0a}
				nArray6<UUID[],Object[],Object[],Object[],Object[],Object[]> data
				){
					super(PacketIDs.play_toClient_packet_player_info.getId(), action, data);
					this.action = action;
					this.data =  data;
				}
			}
			static class packet_position extends PacketBase{
				f32 x;
				f32 y;
				f32 z;
				f32 yaw;
				f32 pitch;
				i8 flags;
				VarInt teleportId;
				
				public packet_position(
				f32 x,
				f32 y,
				f32 z,
				f32 yaw,
				f32 pitch,
				i8 flags,
				VarInt teleportId
				){
					super(PacketIDs.play_toClient_packet_position.getId(), x, y, z, yaw, pitch, flags, teleportId);
					this.x = x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.flags =  flags;
					this.teleportId =  teleportId;
				}
			}
			static class packet_bed extends PacketBase{
				VarInt entityId;
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				
				public packet_bed(
				VarInt entityId,
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z
				){
					super(PacketIDs.play_toClient_packet_bed.getId(), entityId, location_x, location_y, location_z);
					this.entityId = entityId;
					this.location_x =  location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
				}
			}
			static class packet_unlock_recipes extends PacketBase{
				VarInt action;
				Boolean craftingBookOpen;
				Boolean filteringCraftable;
				VarInt[] recipes1;
				//Only present if action is 0
				VarInt[] c0_recipes2_VarInt;
				
				
				public packet_unlock_recipes(
				VarInt action,
				Boolean craftingBookOpen,
				Boolean filteringCraftable,
				VarInt[] recipes1,
				//Only present if action is 0
				VarInt[] c0_recipes2_VarInt
				
				){
					super(PacketIDs.play_toClient_packet_unlock_recipes.getId(), action, craftingBookOpen, filteringCraftable, recipes1, c0_recipes2_VarInt);
					this.action = action;
					this.craftingBookOpen =  craftingBookOpen;
					this.filteringCraftable =  filteringCraftable;
					this.recipes1 =  recipes1;
					this.c0_recipes2_VarInt =  c0_recipes2_VarInt;
				}
			}
			static class packet_entity_destroy extends PacketBase{
				VarInt[] entityIds;
				
				public packet_entity_destroy(
				VarInt[] entityIds
				){
					super(PacketIDs.play_toClient_packet_entity_destroy.getId(), entityIds);
					this.entityIds = entityIds;
				}
			}
			static class packet_remove_entity_effect extends PacketBase{
				VarInt entityId;
				i8 effectId;
				
				public packet_remove_entity_effect(
				VarInt entityId,
				i8 effectId
				){
					super(PacketIDs.play_toClient_packet_remove_entity_effect.getId(), entityId, effectId);
					this.entityId = entityId;
					this.effectId =  effectId;
				}
			}
			static class packet_resource_pack_send extends PacketBase{
				mcString url;
				mcString hash;
				
				public packet_resource_pack_send(
				mcString url,
				mcString hash
				){
					super(PacketIDs.play_toClient_packet_resource_pack_send.getId(), url, hash);
					this.url = url;
					this.hash =  hash;
				}
			}
			static class packet_respawn extends PacketBase{
				i32 dimension;
				u8 difficulty;
				u8 gamemode;
				mcString levelType;
				
				public packet_respawn(
				i32 dimension,
				u8 difficulty,
				u8 gamemode,
				mcString levelType
				){
					super(PacketIDs.play_toClient_packet_respawn.getId(), dimension, difficulty, gamemode, levelType);
					this.dimension = dimension;
					this.difficulty =  difficulty;
					this.gamemode =  gamemode;
					this.levelType =  levelType;
				}
			}
			static class packet_entity_head_rotation extends PacketBase{
				VarInt entityId;
				i8 headYaw;
				
				public packet_entity_head_rotation(
				VarInt entityId,
				i8 headYaw
				){
					super(PacketIDs.play_toClient_packet_entity_head_rotation.getId(), entityId, headYaw);
					this.entityId = entityId;
					this.headYaw =  headYaw;
				}
			}
			static class packet_world_border extends PacketBase{
				VarInt action;
				//Only present if action is 0
				f32 c0_radius_f32;
				
				//Only present if action is 2 or 3
				f32 c2or3_x_f32;
				
				//Only present if action is 2 or 3
				f32 c2or3_z_f32;
				
				//Only present if action is 1 or 3
				f32 c1or3_old_radius_f32;
				
				//Only present if action is 1 or 3
				f32 c1or3_new_radius_f32;
				
				//Only present if action is 1 or 3
				VarLong c1or3_speed_VarLong;
				
				//Only present if action is 3
				VarInt c3_portalBoundary_VarInt;
				
				//Only present if action is 3 or 4
				VarInt c3or4_warning_time_VarInt;
				
				//Only present if action is 3 or 5
				VarInt c3or5_warning_blocks_VarInt;
				
				
				public packet_world_border(
				VarInt action,
				//Only present if action is 0
				f32 c0_radius_f32,
				
				//Only present if action is 2 or 3
				f32 c2or3_x_f32,
				
				//Only present if action is 2 or 3
				f32 c2or3_z_f32,
				
				//Only present if action is 1 or 3
				f32 c1or3_old_radius_f32,
				
				//Only present if action is 1 or 3
				f32 c1or3_new_radius_f32,
				
				//Only present if action is 1 or 3
				VarLong c1or3_speed_VarLong,
				
				//Only present if action is 3
				VarInt c3_portalBoundary_VarInt,
				
				//Only present if action is 3 or 4
				VarInt c3or4_warning_time_VarInt,
				
				//Only present if action is 3 or 5
				VarInt c3or5_warning_blocks_VarInt
				
				){
					super(PacketIDs.play_toClient_packet_world_border.getId(), action, c0_radius_f32, c2or3_x_f32, c2or3_z_f32, c1or3_old_radius_f32, c1or3_new_radius_f32, c1or3_speed_VarLong, c3_portalBoundary_VarInt, c3or4_warning_time_VarInt, c3or5_warning_blocks_VarInt);
					this.action = action;
					this.c0_radius_f32 =  c0_radius_f32;
					this.c2or3_x_f32 =  c2or3_x_f32;
					this.c2or3_z_f32 =  c2or3_z_f32;
					this.c1or3_old_radius_f32 =  c1or3_old_radius_f32;
					this.c1or3_new_radius_f32 =  c1or3_new_radius_f32;
					this.c1or3_speed_VarLong =  c1or3_speed_VarLong;
					this.c3_portalBoundary_VarInt =  c3_portalBoundary_VarInt;
					this.c3or4_warning_time_VarInt =  c3or4_warning_time_VarInt;
					this.c3or5_warning_blocks_VarInt =  c3or5_warning_blocks_VarInt;
				}
			}
			static class packet_camera extends PacketBase{
				VarInt cameraId;
				
				public packet_camera(
				VarInt cameraId
				){
					super(PacketIDs.play_toClient_packet_camera.getId(), cameraId);
					this.cameraId = cameraId;
				}
			}
			static class packet_held_item_slot extends PacketBase{
				i8 slot;
				
				public packet_held_item_slot(
				i8 slot
				){
					super(PacketIDs.play_toClient_packet_held_item_slot.getId(), slot);
					this.slot = slot;
				}
			}
			static class packet_scoreboard_display_objective extends PacketBase{
				i8 position;
				mcString name;
				
				public packet_scoreboard_display_objective(
				i8 position,
				mcString name
				){
					super(PacketIDs.play_toClient_packet_scoreboard_display_objective.getId(), position, name);
					this.position = position;
					this.name =  name;
				}
			}
			static class packet_entity_metadata extends PacketBase{
				VarInt entityId;
				ComplexType metadata;
				
				public packet_entity_metadata(
				VarInt entityId,
				ComplexType metadata
				){
					super(PacketIDs.play_toClient_packet_entity_metadata.getId(), entityId, metadata);
					this.entityId = entityId;
					this.metadata =  metadata;
				}
			}
			static class packet_attach_entity extends PacketBase{
				i32 entityId;
				i32 vehicleId;
				
				public packet_attach_entity(
				i32 entityId,
				i32 vehicleId
				){
					super(PacketIDs.play_toClient_packet_attach_entity.getId(), entityId, vehicleId);
					this.entityId = entityId;
					this.vehicleId =  vehicleId;
				}
			}
			static class packet_entity_velocity extends PacketBase{
				VarInt entityId;
				i16 velocityX;
				i16 velocityY;
				i16 velocityZ;
				
				public packet_entity_velocity(
				VarInt entityId,
				i16 velocityX,
				i16 velocityY,
				i16 velocityZ
				){
					super(PacketIDs.play_toClient_packet_entity_velocity.getId(), entityId, velocityX, velocityY, velocityZ);
					this.entityId = entityId;
					this.velocityX =  velocityX;
					this.velocityY =  velocityY;
					this.velocityZ =  velocityZ;
				}
			}
			static class packet_entity_equipment extends PacketBase{
				VarInt entityId;
				VarInt slot;
				i16 item_blockId;
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount;
				i16 item_anon_itemDamage;
				Objects item_anon_nbtData;
				//Default field end
				
				
				public packet_entity_equipment(
				VarInt entityId,
				VarInt slot,
				i16 item_blockId,
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount,
				i16 item_anon_itemDamage,
				Objects item_anon_nbtData
				//Default field end
				
				){
					super(PacketIDs.play_toClient_packet_entity_equipment.getId(), entityId, slot, item_blockId, item_anon_itemCount, item_anon_itemDamage, item_anon_nbtData);
					this.entityId = entityId;
					this.slot =  slot;
					this.item_blockId =  item_blockId;
					this.item_anon_itemCount =  item_anon_itemCount;
					this.item_anon_itemDamage =  item_anon_itemDamage;
					this.item_anon_nbtData =  item_anon_nbtData;
				}
			}
			static class packet_experience extends PacketBase{
				f32 experienceBar;
				VarInt level;
				VarInt totalExperience;
				
				public packet_experience(
				f32 experienceBar,
				VarInt level,
				VarInt totalExperience
				){
					super(PacketIDs.play_toClient_packet_experience.getId(), experienceBar, level, totalExperience);
					this.experienceBar = experienceBar;
					this.level =  level;
					this.totalExperience =  totalExperience;
				}
			}
			static class packet_update_health extends PacketBase{
				f32 health;
				VarInt food;
				f32 foodSaturation;
				
				public packet_update_health(
				f32 health,
				VarInt food,
				f32 foodSaturation
				){
					super(PacketIDs.play_toClient_packet_update_health.getId(), health, food, foodSaturation);
					this.health = health;
					this.food =  food;
					this.foodSaturation =  foodSaturation;
				}
			}
			static class packet_scoreboard_objective extends PacketBase{
				mcString name;
				i8 action;
				//Only present if action is 0 or 2
				mcString c0or2_displayText_mcString;
				
				//Only present if action is 0 or 2
				mcString c0or2_type_mcString;
				
				
				public packet_scoreboard_objective(
				mcString name,
				i8 action,
				//Only present if action is 0 or 2
				mcString c0or2_displayText_mcString,
				
				//Only present if action is 0 or 2
				mcString c0or2_type_mcString
				
				){
					super(PacketIDs.play_toClient_packet_scoreboard_objective.getId(), name, action, c0or2_displayText_mcString, c0or2_type_mcString);
					this.name = name;
					this.action =  action;
					this.c0or2_displayText_mcString =  c0or2_displayText_mcString;
					this.c0or2_type_mcString =  c0or2_type_mcString;
				}
			}
			static class packet_set_passengers extends PacketBase{
				VarInt entityId;
				VarInt[] passengers;
				
				public packet_set_passengers(
				VarInt entityId,
				VarInt[] passengers
				){
					super(PacketIDs.play_toClient_packet_set_passengers.getId(), entityId, passengers);
					this.entityId = entityId;
					this.passengers =  passengers;
				}
			}
			static class packet_teams extends PacketBase{
				mcString team;
				i8 mode;
				//Only present if mode is 0 or 2
				mcString c0or2_name_mcString;
				
				//Only present if mode is 0 or 2
				mcString c0or2_prefix_mcString;
				
				//Only present if mode is 0 or 2
				mcString c0or2_suffix_mcString;
				
				//Only present if mode is 0 or 2
				i8 c0or2_friendlyFire_i8;
				
				//Only present if mode is 0 or 2
				mcString c0or2_nameTagVisibility_mcString;
				
				//Only present if mode is 0 or 2
				mcString c0or2_collisionRule_mcString;
				
				//Only present if mode is 0 or 2
				i8 c0or2_color_i8;
				
				//Only present if mode is 0 or 3 or 4
				mcString[] c0or3or4_players_mcString;
				
				
				public packet_teams(
				mcString team,
				i8 mode,
				//Only present if mode is 0 or 2
				mcString c0or2_name_mcString,
				
				//Only present if mode is 0 or 2
				mcString c0or2_prefix_mcString,
				
				//Only present if mode is 0 or 2
				mcString c0or2_suffix_mcString,
				
				//Only present if mode is 0 or 2
				i8 c0or2_friendlyFire_i8,
				
				//Only present if mode is 0 or 2
				mcString c0or2_nameTagVisibility_mcString,
				
				//Only present if mode is 0 or 2
				mcString c0or2_collisionRule_mcString,
				
				//Only present if mode is 0 or 2
				i8 c0or2_color_i8,
				
				//Only present if mode is 0 or 3 or 4
				mcString[] c0or3or4_players_mcString
				
				){
					super(PacketIDs.play_toClient_packet_teams.getId(), team, mode, c0or2_name_mcString, c0or2_prefix_mcString, c0or2_suffix_mcString, c0or2_friendlyFire_i8, c0or2_nameTagVisibility_mcString, c0or2_collisionRule_mcString, c0or2_color_i8, c0or3or4_players_mcString);
					this.team = team;
					this.mode =  mode;
					this.c0or2_name_mcString =  c0or2_name_mcString;
					this.c0or2_prefix_mcString =  c0or2_prefix_mcString;
					this.c0or2_suffix_mcString =  c0or2_suffix_mcString;
					this.c0or2_friendlyFire_i8 =  c0or2_friendlyFire_i8;
					this.c0or2_nameTagVisibility_mcString =  c0or2_nameTagVisibility_mcString;
					this.c0or2_collisionRule_mcString =  c0or2_collisionRule_mcString;
					this.c0or2_color_i8 =  c0or2_color_i8;
					this.c0or3or4_players_mcString =  c0or3or4_players_mcString;
				}
			}
			static class packet_scoreboard_score extends PacketBase{
				mcString itemName;
				VarInt action;
				mcString scoreName;
				//if action is any of [1]all fields are empty
				//Default field: 
				VarInt value;
				//Default field end
				
				
				public packet_scoreboard_score(
				mcString itemName,
				VarInt action,
				mcString scoreName,
				//if action is any of [1]all fields are empty
				//Default field: 
				VarInt value
				//Default field end
				
				){
					super(PacketIDs.play_toClient_packet_scoreboard_score.getId(), itemName, action, scoreName, value);
					this.itemName = itemName;
					this.action =  action;
					this.scoreName =  scoreName;
					this.value =  value;
				}
			}
			static class packet_spawn_position extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				
				public packet_spawn_position(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z
				){
					super(PacketIDs.play_toClient_packet_spawn_position.getId(), location_x, location_y, location_z);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
				}
			}
			static class packet_update_time extends PacketBase{
				i64 age;
				i64 time;
				
				public packet_update_time(
				i64 age,
				i64 time
				){
					super(PacketIDs.play_toClient_packet_update_time.getId(), age, time);
					this.age = age;
					this.time =  time;
				}
			}
			static class packet_title extends PacketBase{
				VarInt action;
				//Only present if action is 0 or 1 or 2
				mcString c0or1or2_text_mcString;
				
				//Only present if action is 3
				i32 c3_fadeIn_i32;
				
				//Only present if action is 3
				i32 c3_stay_i32;
				
				//Only present if action is 3
				i32 c3_fadeOut_i32;
				
				
				public packet_title(
				VarInt action,
				//Only present if action is 0 or 1 or 2
				mcString c0or1or2_text_mcString,
				
				//Only present if action is 3
				i32 c3_fadeIn_i32,
				
				//Only present if action is 3
				i32 c3_stay_i32,
				
				//Only present if action is 3
				i32 c3_fadeOut_i32
				
				){
					super(PacketIDs.play_toClient_packet_title.getId(), action, c0or1or2_text_mcString, c3_fadeIn_i32, c3_stay_i32, c3_fadeOut_i32);
					this.action = action;
					this.c0or1or2_text_mcString =  c0or1or2_text_mcString;
					this.c3_fadeIn_i32 =  c3_fadeIn_i32;
					this.c3_stay_i32 =  c3_stay_i32;
					this.c3_fadeOut_i32 =  c3_fadeOut_i32;
				}
			}
			static class packet_sound_effect extends PacketBase{
				VarInt soundId;
				VarInt soundCategory;
				i32 x;
				i32 y;
				i32 z;
				f32 volume;
				f32 pitch;
				
				public packet_sound_effect(
				VarInt soundId,
				VarInt soundCategory,
				i32 x,
				i32 y,
				i32 z,
				f32 volume,
				f32 pitch
				){
					super(PacketIDs.play_toClient_packet_sound_effect.getId(), soundId, soundCategory, x, y, z, volume, pitch);
					this.soundId = soundId;
					this.soundCategory =  soundCategory;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.volume =  volume;
					this.pitch =  pitch;
				}
			}
			static class packet_playerlist_header extends PacketBase{
				mcString header;
				mcString footer;
				
				public packet_playerlist_header(
				mcString header,
				mcString footer
				){
					super(PacketIDs.play_toClient_packet_playerlist_header.getId(), header, footer);
					this.header = header;
					this.footer =  footer;
				}
			}
			static class packet_collect extends PacketBase{
				VarInt collectedEntityId;
				VarInt collectorEntityId;
				VarInt pickupItemCount;
				
				public packet_collect(
				VarInt collectedEntityId,
				VarInt collectorEntityId,
				VarInt pickupItemCount
				){
					super(PacketIDs.play_toClient_packet_collect.getId(), collectedEntityId, collectorEntityId, pickupItemCount);
					this.collectedEntityId = collectedEntityId;
					this.collectorEntityId =  collectorEntityId;
					this.pickupItemCount =  pickupItemCount;
				}
			}
			static class packet_entity_teleport extends PacketBase{
				VarInt entityId;
				f32 x;
				f32 y;
				f32 z;
				i8 yaw;
				i8 pitch;
				Boolean onGround;
				
				public packet_entity_teleport(
				VarInt entityId,
				f32 x,
				f32 y,
				f32 z,
				i8 yaw,
				i8 pitch,
				Boolean onGround
				){
					super(PacketIDs.play_toClient_packet_entity_teleport.getId(), entityId, x, y, z, yaw, pitch, onGround);
					this.entityId = entityId;
					this.x =  x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.onGround =  onGround;
				}
			}
			static class packet_entity_update_attributes extends PacketBase{
				VarInt entityId;
				//key
				//value
				//modifiers
				nArray3<mcString[],f32[],nArray3<UUID,f32,i8>[]> properties;
				
				public packet_entity_update_attributes(
				VarInt entityId,
				//key
				//value
				//modifiers
				nArray3<mcString[],f32[],nArray3<UUID,f32,i8>[]> properties
				){
					super(PacketIDs.play_toClient_packet_entity_update_attributes.getId(), entityId, properties);
					this.entityId = entityId;
					this.properties =  properties;
				}
			}
			static class packet_entity_effect extends PacketBase{
				VarInt entityId;
				i8 effectId;
				i8 amplifier;
				VarInt duration;
				i8 hideParticles;
				
				public packet_entity_effect(
				VarInt entityId,
				i8 effectId,
				i8 amplifier,
				VarInt duration,
				i8 hideParticles
				){
					super(PacketIDs.play_toClient_packet_entity_effect.getId(), entityId, effectId, amplifier, duration, hideParticles);
					this.entityId = entityId;
					this.effectId =  effectId;
					this.amplifier =  amplifier;
					this.duration =  duration;
					this.hideParticles =  hideParticles;
				}
			}
			static class packet_select_advancement_tab extends PacketBase{
				mcString id;
				
				public packet_select_advancement_tab(
				mcString id
				){
					super(PacketIDs.play_toClient_packet_select_advancement_tab.getId(), id);
					this.id = id;
				}
			}
		}
		static class toServer {
			static class packet_teleport_confirm extends PacketBase{
				VarInt teleportId;
				
				public packet_teleport_confirm(
				VarInt teleportId
				){
					super(PacketIDs.play_toServer_packet_teleport_confirm.getId(), teleportId);
					this.teleportId = teleportId;
				}
			}
			static class packet_tab_complete extends PacketBase{
				mcString text;
				Boolean assumeCommand;
				//lookedAtBlock_x is a bitmask of size 26
				u32 lookedAtBlock_x;
				//lookedAtBlock_y is a bitmask of size 12
				u16 lookedAtBlock_y;
				//lookedAtBlock_z is a bitmask of size 26
				u32 lookedAtBlock_z;
				
				public packet_tab_complete(
				mcString text,
				Boolean assumeCommand,
				//lookedAtBlock_x is a bitmask of size 26
				u32 lookedAtBlock_x,
				//lookedAtBlock_y is a bitmask of size 12
				u16 lookedAtBlock_y,
				//lookedAtBlock_z is a bitmask of size 26
				u32 lookedAtBlock_z
				){
					super(PacketIDs.play_toServer_packet_tab_complete.getId(), text, assumeCommand, lookedAtBlock_x, lookedAtBlock_y, lookedAtBlock_z);
					this.text = text;
					this.assumeCommand =  assumeCommand;
					this.lookedAtBlock_x =  lookedAtBlock_x;
					this.lookedAtBlock_y =  lookedAtBlock_y;
					this.lookedAtBlock_z =  lookedAtBlock_z;
				}
			}
			static class packet_chat extends PacketBase{
				mcString message;
				
				public packet_chat(
				mcString message
				){
					super(PacketIDs.play_toServer_packet_chat.getId(), message);
					this.message = message;
				}
			}
			static class packet_client_command extends PacketBase{
				VarInt actionId;
				
				public packet_client_command(
				VarInt actionId
				){
					super(PacketIDs.play_toServer_packet_client_command.getId(), actionId);
					this.actionId = actionId;
				}
			}
			static class packet_settings extends PacketBase{
				mcString locale;
				i8 viewDistance;
				VarInt chatFlags;
				Boolean chatColors;
				u8 skinParts;
				VarInt mainHand;
				
				public packet_settings(
				mcString locale,
				i8 viewDistance,
				VarInt chatFlags,
				Boolean chatColors,
				u8 skinParts,
				VarInt mainHand
				){
					super(PacketIDs.play_toServer_packet_settings.getId(), locale, viewDistance, chatFlags, chatColors, skinParts, mainHand);
					this.locale = locale;
					this.viewDistance =  viewDistance;
					this.chatFlags =  chatFlags;
					this.chatColors =  chatColors;
					this.skinParts =  skinParts;
					this.mainHand =  mainHand;
				}
			}
			static class packet_transaction extends PacketBase{
				i8 windowId;
				i16 action;
				Boolean accepted;
				
				public packet_transaction(
				i8 windowId,
				i16 action,
				Boolean accepted
				){
					super(PacketIDs.play_toServer_packet_transaction.getId(), windowId, action, accepted);
					this.windowId = windowId;
					this.action =  action;
					this.accepted =  accepted;
				}
			}
			static class packet_enchant_item extends PacketBase{
				i8 windowId;
				i8 enchantment;
				
				public packet_enchant_item(
				i8 windowId,
				i8 enchantment
				){
					super(PacketIDs.play_toServer_packet_enchant_item.getId(), windowId, enchantment);
					this.windowId = windowId;
					this.enchantment =  enchantment;
				}
			}
			static class packet_window_click extends PacketBase{
				u8 windowId;
				i16 slot;
				i8 mouseButton;
				i16 action;
				i8 mode;
				i16 item_blockId;
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount;
				i16 item_anon_itemDamage;
				Objects item_anon_nbtData;
				//Default field end
				
				
				public packet_window_click(
				u8 windowId,
				i16 slot,
				i8 mouseButton,
				i16 action,
				i8 mode,
				i16 item_blockId,
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount,
				i16 item_anon_itemDamage,
				Objects item_anon_nbtData
				//Default field end
				
				){
					super(PacketIDs.play_toServer_packet_window_click.getId(), windowId, slot, mouseButton, action, mode, item_blockId, item_anon_itemCount, item_anon_itemDamage, item_anon_nbtData);
					this.windowId = windowId;
					this.slot =  slot;
					this.mouseButton =  mouseButton;
					this.action =  action;
					this.mode =  mode;
					this.item_blockId =  item_blockId;
					this.item_anon_itemCount =  item_anon_itemCount;
					this.item_anon_itemDamage =  item_anon_itemDamage;
					this.item_anon_nbtData =  item_anon_nbtData;
				}
			}
			static class packet_close_window extends PacketBase{
				u8 windowId;
				
				public packet_close_window(
				u8 windowId
				){
					super(PacketIDs.play_toServer_packet_close_window.getId(), windowId);
					this.windowId = windowId;
				}
			}
			static class packet_custom_payload extends PacketBase{
				mcString channel;
				Byte[] data;
				
				public packet_custom_payload(
				mcString channel,
				Byte[] data
				){
					super(PacketIDs.play_toServer_packet_custom_payload.getId(), channel, data);
					this.channel = channel;
					this.data =  data;
				}
			}
			static class packet_use_entity extends PacketBase{
				VarInt target;
				VarInt mouse;
				//Only present if mouse is 2
				f32 c2_x_f32;
				
				//Only present if mouse is 2
				f32 c2_y_f32;
				
				//Only present if mouse is 2
				f32 c2_z_f32;
				
				//Only present if mouse is 0 or 2
				VarInt c0or2_hand_VarInt;
				
				
				public packet_use_entity(
				VarInt target,
				VarInt mouse,
				//Only present if mouse is 2
				f32 c2_x_f32,
				
				//Only present if mouse is 2
				f32 c2_y_f32,
				
				//Only present if mouse is 2
				f32 c2_z_f32,
				
				//Only present if mouse is 0 or 2
				VarInt c0or2_hand_VarInt
				
				){
					super(PacketIDs.play_toServer_packet_use_entity.getId(), target, mouse, c2_x_f32, c2_y_f32, c2_z_f32, c0or2_hand_VarInt);
					this.target = target;
					this.mouse =  mouse;
					this.c2_x_f32 =  c2_x_f32;
					this.c2_y_f32 =  c2_y_f32;
					this.c2_z_f32 =  c2_z_f32;
					this.c0or2_hand_VarInt =  c0or2_hand_VarInt;
				}
			}
			static class packet_keep_alive extends PacketBase{
				i64 keepAliveId;
				
				public packet_keep_alive(
				i64 keepAliveId
				){
					super(PacketIDs.play_toServer_packet_keep_alive.getId(), keepAliveId);
					this.keepAliveId = keepAliveId;
				}
			}
			static class packet_position extends PacketBase{
				f32 x;
				f32 y;
				f32 z;
				Boolean onGround;
				
				public packet_position(
				f32 x,
				f32 y,
				f32 z,
				Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_position.getId(), x, y, z, onGround);
					this.x = x;
					this.y =  y;
					this.z =  z;
					this.onGround =  onGround;
				}
			}
			static class packet_position_look extends PacketBase{
				f32 x;
				f32 y;
				f32 z;
				f32 yaw;
				f32 pitch;
				Boolean onGround;
				
				public packet_position_look(
				f32 x,
				f32 y,
				f32 z,
				f32 yaw,
				f32 pitch,
				Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_position_look.getId(), x, y, z, yaw, pitch, onGround);
					this.x = x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
					this.onGround =  onGround;
				}
			}
			static class packet_look extends PacketBase{
				f32 yaw;
				f32 pitch;
				Boolean onGround;
				
				public packet_look(
				f32 yaw,
				f32 pitch,
				Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_look.getId(), yaw, pitch, onGround);
					this.yaw = yaw;
					this.pitch =  pitch;
					this.onGround =  onGround;
				}
			}
			static class packet_flying extends PacketBase{
				Boolean onGround;
				
				public packet_flying(
				Boolean onGround
				){
					super(PacketIDs.play_toServer_packet_flying.getId(), onGround);
					this.onGround = onGround;
				}
			}
			static class packet_vehicle_move extends PacketBase{
				f32 x;
				f32 y;
				f32 z;
				f32 yaw;
				f32 pitch;
				
				public packet_vehicle_move(
				f32 x,
				f32 y,
				f32 z,
				f32 yaw,
				f32 pitch
				){
					super(PacketIDs.play_toServer_packet_vehicle_move.getId(), x, y, z, yaw, pitch);
					this.x = x;
					this.y =  y;
					this.z =  z;
					this.yaw =  yaw;
					this.pitch =  pitch;
				}
			}
			static class packet_steer_boat extends PacketBase{
				Boolean leftPaddle;
				Boolean rightPaddle;
				
				public packet_steer_boat(
				Boolean leftPaddle,
				Boolean rightPaddle
				){
					super(PacketIDs.play_toServer_packet_steer_boat.getId(), leftPaddle, rightPaddle);
					this.leftPaddle = leftPaddle;
					this.rightPaddle =  rightPaddle;
				}
			}
			static class packet_craft_recipe_request extends PacketBase{
				i8 windowId;
				VarInt recipe;
				Boolean makeAll;
				
				public packet_craft_recipe_request(
				i8 windowId,
				VarInt recipe,
				Boolean makeAll
				){
					super(PacketIDs.play_toServer_packet_craft_recipe_request.getId(), windowId, recipe, makeAll);
					this.windowId = windowId;
					this.recipe =  recipe;
					this.makeAll =  makeAll;
				}
			}
			static class packet_abilities extends PacketBase{
				i8 flags;
				f32 flyingSpeed;
				f32 walkingSpeed;
				
				public packet_abilities(
				i8 flags,
				f32 flyingSpeed,
				f32 walkingSpeed
				){
					super(PacketIDs.play_toServer_packet_abilities.getId(), flags, flyingSpeed, walkingSpeed);
					this.flags = flags;
					this.flyingSpeed =  flyingSpeed;
					this.walkingSpeed =  walkingSpeed;
				}
			}
			static class packet_block_dig extends PacketBase{
				VarInt status;
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				i8 face;
				
				public packet_block_dig(
				VarInt status,
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				i8 face
				){
					super(PacketIDs.play_toServer_packet_block_dig.getId(), status, location_x, location_y, location_z, face);
					this.status = status;
					this.location_x =  location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.face =  face;
				}
			}
			static class packet_entity_action extends PacketBase{
				VarInt entityId;
				VarInt actionId;
				VarInt jumpBoost;
				
				public packet_entity_action(
				VarInt entityId,
				VarInt actionId,
				VarInt jumpBoost
				){
					super(PacketIDs.play_toServer_packet_entity_action.getId(), entityId, actionId, jumpBoost);
					this.entityId = entityId;
					this.actionId =  actionId;
					this.jumpBoost =  jumpBoost;
				}
			}
			static class packet_steer_vehicle extends PacketBase{
				f32 sideways;
				f32 forward;
				u8 jump;
				
				public packet_steer_vehicle(
				f32 sideways,
				f32 forward,
				u8 jump
				){
					super(PacketIDs.play_toServer_packet_steer_vehicle.getId(), sideways, forward, jump);
					this.sideways = sideways;
					this.forward =  forward;
					this.jump =  jump;
				}
			}
			static class packet_crafting_book_data extends PacketBase{
				VarInt type;
				//Only present if type is 0
				i32 c0_anon_i32_displayedRecipe;
				//Only present if type is 1
				Boolean c1_anon_Boolean_craftingBookOpen;
				Boolean c1_anon_Boolean_craftingFilter;
				
				
				public packet_crafting_book_data(
				VarInt type,
				//Only present if type is 0
				i32 c0_anon_i32_displayedRecipe,
				//Only present if type is 1
				Boolean c1_anon_Boolean_craftingBookOpen,
				Boolean c1_anon_Boolean_craftingFilter
				
				){
					super(PacketIDs.play_toServer_packet_crafting_book_data.getId(), type, c0_anon_i32_displayedRecipe, c1_anon_Boolean_craftingBookOpen, c1_anon_Boolean_craftingFilter);
					this.type = type;
					this.c0_anon_i32_displayedRecipe =  c0_anon_i32_displayedRecipe;
					this.c1_anon_Boolean_craftingBookOpen =  c1_anon_Boolean_craftingBookOpen;
					this.c1_anon_Boolean_craftingFilter =  c1_anon_Boolean_craftingFilter;
				}
			}
			static class packet_resource_pack_receive extends PacketBase{
				VarInt result;
				
				public packet_resource_pack_receive(
				VarInt result
				){
					super(PacketIDs.play_toServer_packet_resource_pack_receive.getId(), result);
					this.result = result;
				}
			}
			static class packet_held_item_slot extends PacketBase{
				i16 slotId;
				
				public packet_held_item_slot(
				i16 slotId
				){
					super(PacketIDs.play_toServer_packet_held_item_slot.getId(), slotId);
					this.slotId = slotId;
				}
			}
			static class packet_set_creative_slot extends PacketBase{
				i16 slot;
				i16 item_blockId;
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount;
				i16 item_anon_itemDamage;
				Objects item_anon_nbtData;
				//Default field end
				
				
				public packet_set_creative_slot(
				i16 slot,
				i16 item_blockId,
				//if blockId is any of [-1]all fields are empty
				//Default field: 
				i8 item_anon_itemCount,
				i16 item_anon_itemDamage,
				Objects item_anon_nbtData
				//Default field end
				
				){
					super(PacketIDs.play_toServer_packet_set_creative_slot.getId(), slot, item_blockId, item_anon_itemCount, item_anon_itemDamage, item_anon_nbtData);
					this.slot = slot;
					this.item_blockId =  item_blockId;
					this.item_anon_itemCount =  item_anon_itemCount;
					this.item_anon_itemDamage =  item_anon_itemDamage;
					this.item_anon_nbtData =  item_anon_nbtData;
				}
			}
			static class packet_update_sign extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				mcString text1;
				mcString text2;
				mcString text3;
				mcString text4;
				
				public packet_update_sign(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				mcString text1,
				mcString text2,
				mcString text3,
				mcString text4
				){
					super(PacketIDs.play_toServer_packet_update_sign.getId(), location_x, location_y, location_z, text1, text2, text3, text4);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.text1 =  text1;
					this.text2 =  text2;
					this.text3 =  text3;
					this.text4 =  text4;
				}
			}
			static class packet_arm_animation extends PacketBase{
				VarInt hand;
				
				public packet_arm_animation(
				VarInt hand
				){
					super(PacketIDs.play_toServer_packet_arm_animation.getId(), hand);
					this.hand = hand;
				}
			}
			static class packet_spectate extends PacketBase{
				UUID target;
				
				public packet_spectate(
				UUID target
				){
					super(PacketIDs.play_toServer_packet_spectate.getId(), target);
					this.target = target;
				}
			}
			static class packet_block_place extends PacketBase{
				//location_x is a bitmask of size 26
				u32 location_x;
				//location_y is a bitmask of size 12
				u16 location_y;
				//location_z is a bitmask of size 26
				u32 location_z;
				VarInt direction;
				VarInt hand;
				f32 cursorX;
				f32 cursorY;
				f32 cursorZ;
				
				public packet_block_place(
				//location_x is a bitmask of size 26
				u32 location_x,
				//location_y is a bitmask of size 12
				u16 location_y,
				//location_z is a bitmask of size 26
				u32 location_z,
				VarInt direction,
				VarInt hand,
				f32 cursorX,
				f32 cursorY,
				f32 cursorZ
				){
					super(PacketIDs.play_toServer_packet_block_place.getId(), location_x, location_y, location_z, direction, hand, cursorX, cursorY, cursorZ);
					this.location_x = location_x;
					this.location_y =  location_y;
					this.location_z =  location_z;
					this.direction =  direction;
					this.hand =  hand;
					this.cursorX =  cursorX;
					this.cursorY =  cursorY;
					this.cursorZ =  cursorZ;
				}
			}
			static class packet_use_item extends PacketBase{
				VarInt hand;
				
				public packet_use_item(
				VarInt hand
				){
					super(PacketIDs.play_toServer_packet_use_item.getId(), hand);
					this.hand = hand;
				}
			}
			static class packet_advancement_tab extends PacketBase{
				VarInt action;
				//if action is any of [1]all fields are empty
				//Only present if action is 0
				mcString c0_tabId_mcString;
				
				
				public packet_advancement_tab(
				VarInt action,
				//if action is any of [1]all fields are empty
				//Only present if action is 0
				mcString c0_tabId_mcString
				
				){
					super(PacketIDs.play_toServer_packet_advancement_tab.getId(), action, c0_tabId_mcString);
					this.action = action;
					this.c0_tabId_mcString =  c0_tabId_mcString;
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
		login_toServer_packet_login_start(0x00),
		login_toServer_packet_encryption_begin(0x01),
		play_toClient_packet_spawn_entity(0x00),
		play_toClient_packet_spawn_entity_experience_orb(0x01),
		play_toClient_packet_spawn_entity_weather(0x02),
		play_toClient_packet_spawn_entity_living(0x03),
		play_toClient_packet_spawn_entity_painting(0x04),
		play_toClient_packet_named_entity_spawn(0x05),
		play_toClient_packet_animation(0x06),
		play_toClient_packet_statistics(0x07),
		play_toClient_packet_block_break_animation(0x08),
		play_toClient_packet_tile_entity_data(0x09),
		play_toClient_packet_block_action(0x0a),
		play_toClient_packet_block_change(0x0b),
		play_toClient_packet_boss_bar(0x0c),
		play_toClient_packet_difficulty(0x0d),
		play_toClient_packet_tab_complete(0x0e),
		play_toClient_packet_chat(0x0f),
		play_toClient_packet_multi_block_change(0x10),
		play_toClient_packet_transaction(0x11),
		play_toClient_packet_close_window(0x12),
		play_toClient_packet_open_window(0x13),
		play_toClient_packet_window_items(0x14),
		play_toClient_packet_craft_progress_bar(0x15),
		play_toClient_packet_set_slot(0x16),
		play_toClient_packet_set_cooldown(0x17),
		play_toClient_packet_custom_payload(0x18),
		play_toClient_packet_named_sound_effect(0x19),
		play_toClient_packet_kick_disconnect(0x1a),
		play_toClient_packet_entity_status(0x1b),
		play_toClient_packet_explosion(0x1c),
		play_toClient_packet_unload_chunk(0x1d),
		play_toClient_packet_game_state_change(0x1e),
		play_toClient_packet_keep_alive(0x1f),
		play_toClient_packet_map_chunk(0x20),
		play_toClient_packet_world_event(0x21),
		play_toClient_packet_world_particles(0x22),
		play_toClient_packet_login(0x23),
		play_toClient_packet_map(0x24),
		play_toClient_packet_entity(0x25),
		play_toClient_packet_rel_entity_move(0x26),
		play_toClient_packet_entity_move_look(0x27),
		play_toClient_packet_entity_look(0x28),
		play_toClient_packet_vehicle_move(0x29),
		play_toClient_packet_open_sign_entity(0x2a),
		play_toClient_packet_craft_recipe_response(0x2b),
		play_toClient_packet_abilities(0x2c),
		play_toClient_packet_combat_event(0x2d),
		play_toClient_packet_player_info(0x2e),
		play_toClient_packet_position(0x2f),
		play_toClient_packet_bed(0x30),
		play_toClient_packet_unlock_recipes(0x31),
		play_toClient_packet_entity_destroy(0x32),
		play_toClient_packet_remove_entity_effect(0x33),
		play_toClient_packet_resource_pack_send(0x34),
		play_toClient_packet_respawn(0x35),
		play_toClient_packet_entity_head_rotation(0x36),
		play_toClient_packet_select_advancement_tab(0x37),
		play_toClient_packet_world_border(0x38),
		play_toClient_packet_camera(0x39),
		play_toClient_packet_held_item_slot(0x3a),
		play_toClient_packet_scoreboard_display_objective(0x3b),
		play_toClient_packet_entity_metadata(0x3c),
		play_toClient_packet_attach_entity(0x3d),
		play_toClient_packet_entity_velocity(0x3e),
		play_toClient_packet_entity_equipment(0x3f),
		play_toClient_packet_experience(0x40),
		play_toClient_packet_update_health(0x41),
		play_toClient_packet_scoreboard_objective(0x42),
		play_toClient_packet_set_passengers(0x43),
		play_toClient_packet_teams(0x44),
		play_toClient_packet_scoreboard_score(0x45),
		play_toClient_packet_spawn_position(0x46),
		play_toClient_packet_update_time(0x47),
		play_toClient_packet_title(0x48),
		play_toClient_packet_sound_effect(0x49),
		play_toClient_packet_playerlist_header(0x4a),
		play_toClient_packet_collect(0x4b),
		play_toClient_packet_entity_teleport(0x4c),
		play_toClient_packet_advancements(0x4d),
		play_toClient_packet_entity_update_attributes(0x4e),
		play_toClient_packet_entity_effect(0x4f),
		play_toServer_packet_teleport_confirm(0x00),
		play_toServer_packet_tab_complete(0x01),
		play_toServer_packet_chat(0x02),
		play_toServer_packet_client_command(0x03),
		play_toServer_packet_settings(0x04),
		play_toServer_packet_transaction(0x05),
		play_toServer_packet_enchant_item(0x06),
		play_toServer_packet_window_click(0x07),
		play_toServer_packet_close_window(0x08),
		play_toServer_packet_custom_payload(0x09),
		play_toServer_packet_use_entity(0x0a),
		play_toServer_packet_keep_alive(0x0b),
		play_toServer_packet_flying(0x0c),
		play_toServer_packet_position(0x0d),
		play_toServer_packet_position_look(0x0e),
		play_toServer_packet_look(0x0f),
		play_toServer_packet_vehicle_move(0x10),
		play_toServer_packet_steer_boat(0x11),
		play_toServer_packet_craft_recipe_request(0x12),
		play_toServer_packet_abilities(0x13),
		play_toServer_packet_block_dig(0x14),
		play_toServer_packet_entity_action(0x15),
		play_toServer_packet_steer_vehicle(0x16),
		play_toServer_packet_crafting_book_data(0x17),
		play_toServer_packet_resource_pack_receive(0x18),
		play_toServer_packet_advancement_tab(0x19),
		play_toServer_packet_held_item_slot(0x1a),
		play_toServer_packet_set_creative_slot(0x1b),
		play_toServer_packet_update_sign(0x1c),
		play_toServer_packet_arm_animation(0x1d),
		play_toServer_packet_spectate(0x1e),
		play_toServer_packet_block_place(0x1f),
		play_toServer_packet_use_item(0x20);
		
		private final int id;
		PacketIDs(int id){
			this.id = id;
		}
		public int getId(){
			return this.id;
		}
	}
}

