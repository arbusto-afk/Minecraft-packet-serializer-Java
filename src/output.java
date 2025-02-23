import Serializables.*;
import Serializables.Types.*;

import java.util.Objects;

record handshaking_toServer_packet_set_protocol(
	VarInt protocolVersion,
	String serverHost,
	u16 serverPort,
	VarInt nextState
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{protocolVersion, serverHost, serverPort, nextState};
		return new Byte[0];
	}
}
record handshaking_toServer_packet_legacy_server_list_ping( 
	u8 payload
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{payload};
		return new Byte[0];
	}
}
record status_toClient_packet_server_info( 
	String response
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{response};
		return new Byte[0];
	}
}
record status_toClient_packet_ping( 
	i64 time
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{time};
		return new Byte[0];
	}
}
record status_toServer_packet_ping_start( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record status_toServer_packet_ping( 
	i64 time
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{time};
		return new Byte[0];
	}
}
record login_toClient_packet_disconnect( 
	String reason
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{reason};
		return new Byte[0];
	}
}
record login_toClient_packet_encryption_begin( 
	String serverId,
	Bit[] publicKey,
	Bit[] verifyToken,
	Boolean shouldAuthenticate
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{serverId, publicKey, verifyToken, shouldAuthenticate};
		return new Byte[0];
	}
}
record login_toClient_packet_success( 
	UUID uuid,
	String username,
	//name
	//value
	//optional signature
	nArray3<String,String,String> properties
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{uuid, username, properties};
		return new Byte[0];
	}
}
record login_toClient_packet_compress( 
	VarInt threshold
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{threshold};
		return new Byte[0];
	}
}
record login_toClient_packet_login_plugin_request( 
	VarInt messageId,
	String channel,
	Byte[] data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{messageId, channel, data};
		return new Byte[0];
	}
}
record login_toServer_packet_login_start( 
	String username,
	UUID playerUUID
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{username, playerUUID};
		return new Byte[0];
	}
}
record login_toServer_packet_encryption_begin( 
	Bit[] sharedSecret,
	Bit[] verifyToken
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{sharedSecret, verifyToken};
		return new Byte[0];
	}
}
record login_toServer_packet_login_plugin_response( 
	VarInt messageId,
	Byte[] data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{messageId, data};
		return new Byte[0];
	}
}
record login_toServer_packet_login_acknowledged( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record configuration_toClient_packet_custom_payload( 
	String channel,
	Byte[] data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{channel, data};
		return new Byte[0];
	}
}
record configuration_toClient_packet_disconnect( 
	String reason
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{reason};
		return new Byte[0];
	}
}
record configuration_toClient_packet_finish_configuration( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record configuration_toClient_packet_keep_alive( 
	i64 keepAliveId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{keepAliveId};
		return new Byte[0];
	}
}
record configuration_toClient_packet_ping( 
	i32 id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record configuration_toClient_packet_reset_chat( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record configuration_toClient_packet_registry_data( 
	String id,
	//key
	//optional value
	nArray2<String,String> entries
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id, entries};
		return new Byte[0];
	}
}
record configuration_toClient_packet_feature_flags( 
	String[] features
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{features};
		return new Byte[0];
	}
}
record configuration_toClient_packet_tags( 
	//tagType
	//tags
	nArray2<String,nArray2<String,VarInt[]>> tags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{tags};
		return new Byte[0];
	}
}
record configuration_toServer_packet_settings( 
	String locale,
	i8 viewDistance,
	VarInt chatFlags,
	Boolean chatColors,
	u8 skinParts,
	VarInt mainHand,
	Boolean enableTextFiltering,
	Boolean enableServerListing,
	VarInt particles
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{locale, viewDistance, chatFlags, chatColors, skinParts, mainHand, enableTextFiltering, enableServerListing, particles};
		return new Byte[0];
	}
}
record configuration_toServer_packet_custom_payload( 
	String channel,
	Byte[] data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{channel, data};
		return new Byte[0];
	}
}
record configuration_toServer_packet_finish_configuration( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record configuration_toServer_packet_keep_alive( 
	i64 keepAliveId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{keepAliveId};
		return new Byte[0];
	}
}
record configuration_toServer_packet_pong( 
	i32 id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record configuration_toServer_packet_resource_pack_receive( 
	UUID uuid,
	VarInt result
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{uuid, result};
		return new Byte[0];
	}
}
record play_toClient_packet_spawn_entity( 
	VarInt entityId,
	UUID objectUUID,
	VarInt type,
	f32 x,
	f32 y,
	f32 z,
	i8 pitch,
	i8 yaw,
	i8 headPitch,
	VarInt objectData,
	i16 velocityX,
	i16 velocityY,
	i16 velocityZ
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, objectUUID, type, x, y, z, pitch, yaw, headPitch, objectData, velocityX, velocityY, velocityZ};
		return new Byte[0];
	}
}
record play_toClient_packet_spawn_entity_experience_orb( 
	VarInt entityId,
	f32 x,
	f32 y,
	f32 z,
	i16 count
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, x, y, z, count};
		return new Byte[0];
	}
}
record play_toClient_packet_animation( 
	VarInt entityId,
	u8 animation
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, animation};
		return new Byte[0];
	}
}
record play_toClient_packet_statistics( 
	//categoryId
	//statisticId
	//value
	nArray3<VarInt,VarInt,VarInt> entries
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entries};
		return new Byte[0];
	}
}
record play_toClient_packet_acknowledge_player_digging( 
	VarInt sequenceId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{sequenceId};
		return new Byte[0];
	}
}
record play_toClient_packet_block_break_animation( 
	VarInt entityId,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	i8 destroyStage
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, location_x, location_z, location_y, destroyStage};
		return new Byte[0];
	}
}
record play_toClient_packet_tile_entity_data( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt action,
	String nbtData
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, action, nbtData};
		return new Byte[0];
	}
}
record play_toClient_packet_block_action( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	u8 byte1,
	u8 byte2,
	VarInt blockId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, byte1, byte2, blockId};
		return new Byte[0];
	}
}
record play_toClient_packet_block_change( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt type
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, type};
		return new Byte[0];
	}
}
record play_toClient_packet_boss_bar( 
	UUID entityUUID,
	VarInt action,
	//Only present if action is 0 or 3
	String c0or3_title_String,
	
	//Only present if action is 0 or 2
	f32 c0or2_health_f32,
	
	//Only present if action is 0 or 4
	VarInt c0or4_color_VarInt,
	
	//Only present if action is 0 or 4
	VarInt c0or4_dividers_VarInt,
	
	//Only present if action is 0 or 5
	u8 c0or5_flags_u8
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityUUID, action, c0or3_title_String, c0or2_health_f32, c0or4_color_VarInt, c0or4_dividers_VarInt, c0or5_flags_u8};
		return new Byte[0];
	}
}
record play_toClient_packet_difficulty( 
	u8 difficulty,
	Boolean difficultyLocked
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{difficulty, difficultyLocked};
		return new Byte[0];
	}
}
record play_toClient_packet_chunk_batch_finished( 
	VarInt batchSize
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{batchSize};
		return new Byte[0];
	}
}
record play_toClient_packet_chunk_batch_start( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record play_toClient_packet_chunk_biomes( 
	//position_z
	//position_x
	//data
	nArray3<i32,i32,Bit[]> biomes
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{biomes};
		return new Byte[0];
	}
}
record play_toClient_packet_clear_titles( 
	Boolean reset
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{reset};
		return new Byte[0];
	}
}
record play_toClient_packet_tab_complete( 
	VarInt transactionId,
	VarInt start,
	VarInt length,
	//match
	//optional tooltip
	nArray2<String,String> matches
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{transactionId, start, length, matches};
		return new Byte[0];
	}
}
record play_toClient_packet_declare_commands( 
	//unused
	//has_custom_suggestions
	//has_redirect_node
	//has_command
	//command_node_type
	//children
	//Switch: SwitchBuildable{compareToFieldName='flags/has_redirect_node', fields={1=[LSerializables.Refactor.Flattenable,@194bcebf}, defaultField=[LSerializables.Refactor.Flattenable,@17497425}
	//Switch: SwitchBuildable{compareToFieldName='flags/command_node_type', fields={0=[LSerializables.Refactor.Flattenable,@f0da945, 1=[LSerializables.Refactor.ContainerField,@4803b726, 2=[LSerializables.Refactor.ContainerField,@ffaa6af}, defaultField=null}
	nArray8<Byte,Byte,Byte,Byte,Byte,VarInt[],Object,Object> nodes,
	VarInt rootIndex
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{nodes, rootIndex};
		return new Byte[0];
	}
}
record play_toClient_packet_close_window( 
	VarInt windowId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId};
		return new Byte[0];
	}
}
record play_toClient_packet_window_items( 
	VarInt windowId,
	VarInt stateId,
	//itemCount
	//Switch: SwitchBuildable{compareToFieldName='itemCount', fields={0=[LSerializables.Refactor.Flattenable,@53ce1329}, defaultField=[LSerializables.Refactor.ContainerField,@316bcf94}
	nArray2<VarInt,Object> items,
	VarInt carriedItem_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt carriedItem_anon_item,
	VarInt carriedItem_anon_addedComponentCount,
	VarInt carriedItem_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> carriedItem_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> carriedItem_anon_removeComponents
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, stateId, items, carriedItem_itemCount, carriedItem_anon_item, carriedItem_anon_addedComponentCount, carriedItem_anon_removedComponentCount, carriedItem_anon_components, carriedItem_anon_removeComponents};
		return new Byte[0];
	}
}
record play_toClient_packet_craft_progress_bar( 
	VarInt windowId,
	i16 property,
	i16 value
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, property, value};
		return new Byte[0];
	}
}
record play_toClient_packet_set_slot( 
	VarInt windowId,
	VarInt stateId,
	i16 slot,
	VarInt item_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt item_anon_item,
	VarInt item_anon_addedComponentCount,
	VarInt item_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> item_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> item_anon_removeComponents
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, stateId, slot, item_itemCount, item_anon_item, item_anon_addedComponentCount, item_anon_removedComponentCount, item_anon_components, item_anon_removeComponents};
		return new Byte[0];
	}
}
record play_toClient_packet_set_cooldown( 
	String cooldownGroup,
	VarInt cooldownTicks
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{cooldownGroup, cooldownTicks};
		return new Byte[0];
	}
}
record play_toClient_packet_chat_suggestions( 
	VarInt action,
	String[] entries
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{action, entries};
		return new Byte[0];
	}
}
record play_toClient_packet_custom_payload( 
	String channel,
	Byte[] data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{channel, data};
		return new Byte[0];
	}
}
record play_toClient_packet_damage_event( 
	VarInt entityId,
	VarInt sourceTypeId,
	VarInt sourceCauseId,
	VarInt sourceDirectId,
	f32 sourcePosition_x,
	f32 sourcePosition_y,
	f32 sourcePosition_z
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, sourceTypeId, sourceCauseId, sourceDirectId, sourcePosition_x, sourcePosition_y, sourcePosition_z};
		return new Byte[0];
	}
}
record play_toClient_packet_debug_sample( 
	i64[] sample,
	VarInt type
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{sample, type};
		return new Byte[0];
	}
}
record play_toClient_packet_hide_message( 
	VarInt id,
	//Only present if id is 0
	Bit[] c0_signature_Bit
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id, c0_signature_Bit};
		return new Byte[0];
	}
}
record play_toClient_packet_kick_disconnect( 
	String reason
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{reason};
		return new Byte[0];
	}
}
record play_toClient_packet_profileless_chat( 
	String message,
	VarInt type_registryIndex,
	//Only present if registryIndex is 0
	String c0_type_anon_String_chat_translationKey,
	VarInt[] c0_type_anon_VarInt_chat_parameters,
	String c0_type_anon_String_chat_style,
	String c0_type_anon_String_narration_translationKey,
	VarInt[] c0_type_anon_VarInt_narration_parameters,
	String c0_type_anon_String_narration_style,
	
	String name,
	String target
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{message, type_registryIndex, c0_type_anon_String_chat_translationKey, c0_type_anon_VarInt_chat_parameters, c0_type_anon_String_chat_style, c0_type_anon_String_narration_translationKey, c0_type_anon_VarInt_narration_parameters, c0_type_anon_String_narration_style, name, target};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_status( 
	i32 entityId,
	i8 entityStatus
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, entityStatus};
		return new Byte[0];
	}
}
record play_toClient_packet_sync_entity_position( 
	VarInt entityId,
	f32 x,
	f32 y,
	f32 z,
	f32 dx,
	f32 dy,
	f32 dz,
	f32 yaw,
	f32 pitch,
	Boolean onGround
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, x, y, z, dx, dy, dz, yaw, pitch, onGround};
		return new Byte[0];
	}
}
record play_toClient_packet_explosion( 
	f32 center_x,
	f32 center_y,
	f32 center_z,
	f32 playerKnockback_x,
	f32 playerKnockback_y,
	f32 playerKnockback_z,
	//possible values: 0=angry_villager, 1=block, 2=block_marker, 3=bubble, 4=cloud, 5=crit, 6=damage_indicator, 7=dragon_breath, 8=dripping_lava, 9=falling_lava, 10=landing_lava, 11=dripping_water, 12=falling_water, 13=dust, 14=dust_color_transition, 15=effect, 16=elder_guardian, 17=enchanted_hit, 18=enchant, 19=end_rod, 20=entity_effect, 21=explosion_emitter, 22=explosion, 23=gust, 24=small_gust, 25=gust_emitter_large, 26=gust_emitter_small, 27=sonic_boom, 28=falling_dust, 29=firework, 30=fishing, 31=flame, 32=infested, 33=cherry_leaves, 34=sculk_soul, 35=sculk_charge, 36=sculk_charge_pop, 37=soul_fire_flame, 38=soul, 39=flash, 40=happy_villager, 41=composter, 42=heart, 43=instant_effect, 44=item, 45=vibration, 46=trail, 47=item_slime, 48=item_cobweb, 49=item_snowball, 50=large_smoke, 51=lava, 52=mycelium, 53=note, 54=poof, 55=portal, 56=rain, 57=smoke, 58=white_smoke, 59=sneeze, 60=spit, 61=squid_ink, 62=sweep_attack, 63=totem_of_undying, 64=underwater, 65=splash, 66=witch, 67=bubble_pop, 68=current_down, 69=bubble_column_up, 70=nautilus, 71=dolphin, 72=campfire_cosy_smoke, 73=campfire_signal_smoke, 74=dripping_honey, 75=falling_honey, 76=landing_honey, 77=falling_nectar, 78=falling_spore_blossom, 79=ash, 80=crimson_spore, 81=warped_spore, 82=spore_blossom_air, 83=dripping_obsidian_tear, 84=falling_obsidian_tear, 85=landing_obsidian_tear, 86=reverse_portal, 87=white_ash, 88=small_flame, 89=snowflake, 90=dripping_dripstone_lava, 91=falling_dripstone_lava, 92=dripping_dripstone_water, 93=falling_dripstone_water, 94=glow_squid_ink, 95=glow, 96=wax_on, 97=wax_off, 98=electric_spark, 99=scrape, 100=shriek, 101=egg_crack, 102=dust_plume, 103=trial_spawner_detected_player, 104=trial_spawner_detected_player_ominous, 105=vault_connection, 106=dust_pillar, 107=ominous_spawning, 108=raid_omen, 109=trial_omen, 110=block_crumble, 
	VarInt explosionParticle_type,
	//Only present if type is dust_pillar or block_marker or falling_dust or block_crumble or block or shriek
	VarInt cdust_pillarorblock_markerorfalling_dustorblock_crumbleorblockorshriek_explosionParticle_data_VarInt,
	//Only present if type is item
	VarInt citem_explosionParticle_data_VarInt_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt citem_explosionParticle_data_Object_anon_item,
	VarInt citem_explosionParticle_data_Object_anon_addedComponentCount,
	VarInt citem_explosionParticle_data_Object_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> citem_explosionParticle_data_Object_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> citem_explosionParticle_data_Object_anon_removeComponents,
	//Default field end
	
	//Only present if type is entity_effect
	i32 centity_effect_explosionParticle_data_i32,
	//Only present if type is vibration
	//possible values: 0=block, 1=entity, 
	VarInt cvibration_explosionParticle_data_VarInt_position_type,
	//Only present if position_type is block
	Objects cblock_cvibration_explosionParticle_data_Object_position_Objects,
	//Only present if position_type is entity
	VarInt centity_cvibration_explosionParticle_data_Object_position_VarInt_entityId,
	f32 centity_cvibration_explosionParticle_data_Object_position_f32_entity_eye_height,
	
	VarInt cvibration_explosionParticle_data_VarInt_ticks,
	//Only present if type is trail
	f32 ctrail_explosionParticle_data_f32_target_x,
	f32 ctrail_explosionParticle_data_f32_target_y,
	f32 ctrail_explosionParticle_data_f32_target_z,
	u8 ctrail_explosionParticle_data_u8_color,
	//Only present if type is dust_color_transition
	f32 cdust_color_transition_explosionParticle_data_f32_fromRed,
	f32 cdust_color_transition_explosionParticle_data_f32_fromGreen,
	f32 cdust_color_transition_explosionParticle_data_f32_fromBlue,
	f32 cdust_color_transition_explosionParticle_data_f32_scale,
	f32 cdust_color_transition_explosionParticle_data_f32_toRed,
	f32 cdust_color_transition_explosionParticle_data_f32_toGreen,
	f32 cdust_color_transition_explosionParticle_data_f32_toBlue,
	//Only present if type is dust
	f32 cdust_explosionParticle_data_f32_red,
	f32 cdust_explosionParticle_data_f32_green,
	f32 cdust_explosionParticle_data_f32_blue,
	f32 cdust_explosionParticle_data_f32_scale,
	//Only present if type is sculk_charge
	f32 csculk_charge_explosionParticle_data_f32,
	
	VarInt soundId,
	//Only present if soundId is 0
	String c0_anon_String_soundName,
	f32 c0_anon_f32_range
	//Default field: 
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{center_x, center_y, center_z, playerKnockback_x, playerKnockback_y, playerKnockback_z, explosionParticle_type, cdust_pillarorblock_markerorfalling_dustorblock_crumbleorblockorshriek_explosionParticle_data_VarInt, citem_explosionParticle_data_VarInt_itemCount, citem_explosionParticle_data_Object_anon_item, citem_explosionParticle_data_Object_anon_addedComponentCount, citem_explosionParticle_data_Object_anon_removedComponentCount, citem_explosionParticle_data_Object_anon_components, citem_explosionParticle_data_Object_anon_removeComponents, centity_effect_explosionParticle_data_i32, cvibration_explosionParticle_data_VarInt_position_type, bloc, cblock_cvibration_explosionParticle_data_Object_position_Objects, centity_cvibration_explosionParticle_data_Object_position_VarInt_entityId, centity_cvibration_explosionParticle_data_Object_position_f32_entity_eye_height, cvibration_explosionParticle_data_VarInt_ticks, ctrail_explosionParticle_data_f32_target_x, ctrail_explosionParticle_data_f32_target_y, ctrail_explosionParticle_data_f32_target_z, ctrail_explosionParticle_data_u8_color, cdust_color_transition_explosionParticle_data_f32_fromRed, cdust_color_transition_explosionParticle_data_f32_fromGreen, cdust_color_transition_explosionParticle_data_f32_fromBlue, cdust_color_transition_explosionParticle_data_f32_scale, cdust_color_transition_explosionParticle_data_f32_toRed, cdust_color_transition_explosionParticle_data_f32_toGreen, cdust_color_transition_explosionParticle_data_f32_toBlue, cdust_explosionParticle_data_f32_red, cdust_explosionParticle_data_f32_green, cdust_explosionParticle_data_f32_blue, cdust_explosionParticle_data_f32_scale, csculk_charge_explosionParticle_data_f32, soundId, c0_anon_String_soundName, c0_anon_f32_range};
		return new Byte[0];
	}
}
record play_toClient_packet_unload_chunk( 
	i32 chunkZ,
	i32 chunkX
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{chunkZ, chunkX};
		return new Byte[0];
	}
}
record play_toClient_packet_game_state_change( 
	u8 reason,
	f32 gameMode
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{reason, gameMode};
		return new Byte[0];
	}
}
record play_toClient_packet_open_horse_window( 
	VarInt windowId,
	VarInt nbSlots,
	i32 entityId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, nbSlots, entityId};
		return new Byte[0];
	}
}
record play_toClient_packet_hurt_animation( 
	VarInt entityId,
	f32 yaw
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, yaw};
		return new Byte[0];
	}
}
record play_toClient_packet_initialize_world_border( 
	f32 x,
	f32 z,
	f32 oldDiameter,
	f32 newDiameter,
	VarInt speed,
	VarInt portalTeleportBoundary,
	VarInt warningBlocks,
	VarInt warningTime
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, z, oldDiameter, newDiameter, speed, portalTeleportBoundary, warningBlocks, warningTime};
		return new Byte[0];
	}
}
record play_toClient_packet_keep_alive( 
	i64 keepAliveId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{keepAliveId};
		return new Byte[0];
	}
}
record play_toClient_packet_map_chunk( 
	i32 x,
	i32 z,
	String heightmaps,
	Bit[] chunkData,
	//x
	//z
	//y
	//type
	//nbtData
	nArray5<Byte,Byte,i16,VarInt,String> blockEntities,
	i64[] skyLightMask,
	i64[] blockLightMask,
	i64[] emptySkyLightMask,
	i64[] emptyBlockLightMask,
	u8[][] skyLight,
	u8[][] blockLight
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, z, heightmaps, chunkData, blockEntities, skyLightMask, blockLightMask, emptySkyLightMask, emptyBlockLightMask, skyLight, blockLight};
		return new Byte[0];
	}
}
record play_toClient_packet_world_event( 
	i32 effectId,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	i32 data,
	Boolean global
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{effectId, location_x, location_z, location_y, data, global};
		return new Byte[0];
	}
}
record play_toClient_packet_world_particles( 
	Boolean longDistance,
	f32 x,
	f32 y,
	f32 z,
	f32 offsetX,
	f32 offsetY,
	f32 offsetZ,
	f32 velocityOffset,
	i32 amount,
	//possible values: 0=angry_villager, 1=block, 2=block_marker, 3=bubble, 4=cloud, 5=crit, 6=damage_indicator, 7=dragon_breath, 8=dripping_lava, 9=falling_lava, 10=landing_lava, 11=dripping_water, 12=falling_water, 13=dust, 14=dust_color_transition, 15=effect, 16=elder_guardian, 17=enchanted_hit, 18=enchant, 19=end_rod, 20=entity_effect, 21=explosion_emitter, 22=explosion, 23=gust, 24=small_gust, 25=gust_emitter_large, 26=gust_emitter_small, 27=sonic_boom, 28=falling_dust, 29=firework, 30=fishing, 31=flame, 32=infested, 33=cherry_leaves, 34=sculk_soul, 35=sculk_charge, 36=sculk_charge_pop, 37=soul_fire_flame, 38=soul, 39=flash, 40=happy_villager, 41=composter, 42=heart, 43=instant_effect, 44=item, 45=vibration, 46=trail, 47=item_slime, 48=item_cobweb, 49=item_snowball, 50=large_smoke, 51=lava, 52=mycelium, 53=note, 54=poof, 55=portal, 56=rain, 57=smoke, 58=white_smoke, 59=sneeze, 60=spit, 61=squid_ink, 62=sweep_attack, 63=totem_of_undying, 64=underwater, 65=splash, 66=witch, 67=bubble_pop, 68=current_down, 69=bubble_column_up, 70=nautilus, 71=dolphin, 72=campfire_cosy_smoke, 73=campfire_signal_smoke, 74=dripping_honey, 75=falling_honey, 76=landing_honey, 77=falling_nectar, 78=falling_spore_blossom, 79=ash, 80=crimson_spore, 81=warped_spore, 82=spore_blossom_air, 83=dripping_obsidian_tear, 84=falling_obsidian_tear, 85=landing_obsidian_tear, 86=reverse_portal, 87=white_ash, 88=small_flame, 89=snowflake, 90=dripping_dripstone_lava, 91=falling_dripstone_lava, 92=dripping_dripstone_water, 93=falling_dripstone_water, 94=glow_squid_ink, 95=glow, 96=wax_on, 97=wax_off, 98=electric_spark, 99=scrape, 100=shriek, 101=egg_crack, 102=dust_plume, 103=trial_spawner_detected_player, 104=trial_spawner_detected_player_ominous, 105=vault_connection, 106=dust_pillar, 107=ominous_spawning, 108=raid_omen, 109=trial_omen, 110=block_crumble, 
	VarInt particle_type,
	//Only present if type is dust_pillar or block_marker or falling_dust or block_crumble or block or shriek
	VarInt cdust_pillarorblock_markerorfalling_dustorblock_crumbleorblockorshriek_particle_data_VarInt,
	//Only present if type is item
	VarInt citem_particle_data_VarInt_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt citem_particle_data_Object_anon_item,
	VarInt citem_particle_data_Object_anon_addedComponentCount,
	VarInt citem_particle_data_Object_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> citem_particle_data_Object_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> citem_particle_data_Object_anon_removeComponents,
	//Default field end
	
	//Only present if type is entity_effect
	i32 centity_effect_particle_data_i32,
	//Only present if type is vibration
	//possible values: 0=block, 1=entity, 
	VarInt cvibration_particle_data_VarInt_position_type,
	//Only present if position_type is block
	Objects cblock_cvibration_particle_data_Object_position_Objects,
	//Only present if position_type is entity
	VarInt centity_cvibration_particle_data_Object_position_VarInt_entityId,
	f32 centity_cvibration_particle_data_Object_position_f32_entity_eye_height,
	
	VarInt cvibration_particle_data_VarInt_ticks,
	//Only present if type is trail
	f32 ctrail_particle_data_f32_target_x,
	f32 ctrail_particle_data_f32_target_y,
	f32 ctrail_particle_data_f32_target_z,
	u8 ctrail_particle_data_u8_color,
	//Only present if type is dust_color_transition
	f32 cdust_color_transition_particle_data_f32_fromRed,
	f32 cdust_color_transition_particle_data_f32_fromGreen,
	f32 cdust_color_transition_particle_data_f32_fromBlue,
	f32 cdust_color_transition_particle_data_f32_scale,
	f32 cdust_color_transition_particle_data_f32_toRed,
	f32 cdust_color_transition_particle_data_f32_toGreen,
	f32 cdust_color_transition_particle_data_f32_toBlue,
	//Only present if type is dust
	f32 cdust_particle_data_f32_red,
	f32 cdust_particle_data_f32_green,
	f32 cdust_particle_data_f32_blue,
	f32 cdust_particle_data_f32_scale,
	//Only present if type is sculk_charge
	f32 csculk_charge_particle_data_f32
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{longDistance, x, y, z, offsetX, offsetY, offsetZ, velocityOffset, amount, particle_type, cdust_pillarorblock_markerorfalling_dustorblock_crumbleorblockorshriek_particle_data_VarInt, citem_particle_data_VarInt_itemCount, citem_particle_data_Object_anon_item, citem_particle_data_Object_anon_addedComponentCount, citem_particle_data_Object_anon_removedComponentCount, citem_particle_data_Object_anon_components, citem_particle_data_Object_anon_removeComponents, centity_effect_particle_data_i32, cvibration_particle_data_VarInt_position_type, bloc, cblock_cvibration_particle_data_Object_position_Objects, centity_cvibration_particle_data_Object_position_VarInt_entityId, centity_cvibration_particle_data_Object_position_f32_entity_eye_height, cvibration_particle_data_VarInt_ticks, ctrail_particle_data_f32_target_x, ctrail_particle_data_f32_target_y, ctrail_particle_data_f32_target_z, ctrail_particle_data_u8_color, cdust_color_transition_particle_data_f32_fromRed, cdust_color_transition_particle_data_f32_fromGreen, cdust_color_transition_particle_data_f32_fromBlue, cdust_color_transition_particle_data_f32_scale, cdust_color_transition_particle_data_f32_toRed, cdust_color_transition_particle_data_f32_toGreen, cdust_color_transition_particle_data_f32_toBlue, cdust_particle_data_f32_red, cdust_particle_data_f32_green, cdust_particle_data_f32_blue, cdust_particle_data_f32_scale, csculk_charge_particle_data_f32};
		return new Byte[0];
	}
}
record play_toClient_packet_update_light( 
	VarInt chunkX,
	VarInt chunkZ,
	i64[] skyLightMask,
	i64[] blockLightMask,
	i64[] emptySkyLightMask,
	i64[] emptyBlockLightMask,
	u8[][] skyLight,
	u8[][] blockLight
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{chunkX, chunkZ, skyLightMask, blockLightMask, emptySkyLightMask, emptyBlockLightMask, skyLight, blockLight};
		return new Byte[0];
	}
}
record play_toClient_packet_login( 
	i32 entityId,
	Boolean isHardcore,
	String[] worldNames,
	VarInt maxPlayers,
	VarInt viewDistance,
	VarInt simulationDistance,
	Boolean reducedDebugInfo,
	Boolean enableRespawnScreen,
	Boolean doLimitedCrafting,
	VarInt worldState_dimension,
	String worldState_name,
	i64 worldState_hashedSeed,
	//possible values: 0=survival, 1=creative, 2=adventure, 3=spectator, 
	i8 worldState_gamemode,
	u8 worldState_previousGamemode,
	Boolean worldState_isDebug,
	Boolean worldState_isFlat,
	String worldState_death_dimensionName,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt worldState_portalCooldown,
	VarInt worldState_seaLevel,
	Boolean enforcesSecureChat
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, isHardcore, worldNames, maxPlayers, viewDistance, simulationDistance, reducedDebugInfo, enableRespawnScreen, doLimitedCrafting, worldState_dimension, worldState_name, worldState_hashedSeed, worldState_gamemode, worldState_previousGamemode, worldState_isDebug, worldState_isFlat, worldState_death_dimensionName, location_x, location_z, location_y, worldState_portalCooldown, worldState_seaLevel, enforcesSecureChat};
		return new Byte[0];
	}
}
record play_toClient_packet_map( 
	VarInt itemDamage,
	i8 scale,
	Boolean locked,
	//type
	//x
	//z
	//direction
	//optional displayName
	nArray5<VarInt,i8,i8,u8,String> icons,
	u8 columns,
	//if columns is any of [0]all fields are empty
	//Default field: 
	u8 rows,
	//Default field end
	
	//if columns is any of [0]all fields are empty
	//Default field: 
	u8 x,
	//Default field end
	
	//if columns is any of [0]all fields are empty
	//Default field: 
	u8 y,
	//Default field end
	
	//if columns is any of [0]all fields are empty
	//Default field: 
	Bit[] data
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{itemDamage, scale, locked, icons, columns, rows, x, y, data};
		return new Byte[0];
	}
}
record play_toClient_packet_trade_list( 
	VarInt windowId,
	//inputItem1_itemId
	//inputItem1_itemCount
	//inputItem1_addedComponentCount
	//inputItem1_components
	//outputItem_itemCount
	//Switch: SwitchBuildable{compareToFieldName='itemCount', fields={0=[LSerializables.Refactor.Flattenable,@53ce1329}, defaultField=[LSerializables.Refactor.ContainerField,@316bcf94}
	//optional inputItem2
	//optional inputItem2
	//optional inputItem2
	//optional inputItem2
	//tradeDisabled
	//nbTradeUses
	//maximumNbTradeUses
	//xp
	//specialPrice
	//priceMultiplier
	//demand
	nArray17<VarInt,VarInt,VarInt,nArray2<VarInt,Object>,VarInt,Object,VarInt,VarInt,VarInt,nArray2<VarInt,Object>,Boolean,i32,i32,i32,i32,f32,i32> trades,
	VarInt villagerLevel,
	VarInt experience,
	Boolean isRegularVillager,
	Boolean canRestock
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, trades, villagerLevel, experience, isRegularVillager, canRestock};
		return new Byte[0];
	}
}
record play_toClient_packet_rel_entity_move( 
	VarInt entityId,
	i16 dX,
	i16 dY,
	i16 dZ,
	Boolean onGround
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, dX, dY, dZ, onGround};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_move_look( 
	VarInt entityId,
	i16 dX,
	i16 dY,
	i16 dZ,
	i8 yaw,
	i8 pitch,
	Boolean onGround
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, dX, dY, dZ, yaw, pitch, onGround};
		return new Byte[0];
	}
}
record play_toClient_packet_move_minecart( 
	VarInt entityId,
	//position_x
	//position_y
	//position_z
	//movement_x
	//movement_y
	//movement_z
	//yaw
	//pitch
	//weight
	nArray9<f32,f32,f32,f32,f32,f32,f32,f32,f32> steps
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, steps};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_look( 
	VarInt entityId,
	i8 yaw,
	i8 pitch,
	Boolean onGround
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, yaw, pitch, onGround};
		return new Byte[0];
	}
}
record play_toClient_packet_vehicle_move( 
	f32 x,
	f32 y,
	f32 z,
	f32 yaw,
	f32 pitch
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, y, z, yaw, pitch};
		return new Byte[0];
	}
}
record play_toClient_packet_open_book( 
	VarInt hand
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{hand};
		return new Byte[0];
	}
}
record play_toClient_packet_open_window( 
	VarInt windowId,
	VarInt inventoryType,
	String windowTitle
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, inventoryType, windowTitle};
		return new Byte[0];
	}
}
record play_toClient_packet_open_sign_entity( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	Boolean isFrontText
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, isFrontText};
		return new Byte[0];
	}
}
record play_toClient_packet_ping( 
	i32 id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record play_toClient_packet_ping_response( 
	i64 id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record play_toClient_packet_craft_recipe_response( 
	VarInt windowId,
	//possible values: 0=crafting_shapeless, 1=crafting_shaped, 2=furnace, 3=stonecutter, 4=smithing, 
	VarInt recipeDisplay_type,
	//Only present if type is crafting_shapeless
	Objects[] ccrafting_shapeless_recipeDisplay_data_Objects_ingredients,
	Objects ccrafting_shapeless_recipeDisplay_data_Objects_result,
	Objects ccrafting_shapeless_recipeDisplay_data_Objects_craftingStation,
	//Only present if type is crafting_shaped
	VarInt ccrafting_shaped_recipeDisplay_data_VarInt_width,
	VarInt ccrafting_shaped_recipeDisplay_data_VarInt_height,
	Objects[] ccrafting_shaped_recipeDisplay_data_Objects_ingredients,
	Objects ccrafting_shaped_recipeDisplay_data_Objects_result,
	Objects ccrafting_shaped_recipeDisplay_data_Objects_craftingStation,
	//Only present if type is smithing
	Objects csmithing_recipeDisplay_data_Objects_template,
	Objects csmithing_recipeDisplay_data_Objects_base,
	Objects csmithing_recipeDisplay_data_Objects_addition,
	Objects csmithing_recipeDisplay_data_Objects_result,
	Objects csmithing_recipeDisplay_data_Objects_craftingStation,
	//Only present if type is furnace
	Objects cfurnace_recipeDisplay_data_Objects_ingredient,
	Objects cfurnace_recipeDisplay_data_Objects_fuel,
	Objects cfurnace_recipeDisplay_data_Objects_result,
	Objects cfurnace_recipeDisplay_data_Objects_craftingStation,
	VarInt cfurnace_recipeDisplay_data_VarInt_duration,
	f32 cfurnace_recipeDisplay_data_f32_experience,
	//Only present if type is stonecutter
	Objects cstonecutter_recipeDisplay_data_Objects_ingredient,
	Objects cstonecutter_recipeDisplay_data_Objects_result,
	Objects cstonecutter_recipeDisplay_data_Objects_craftingStation
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, recipeDisplay_type, ccrafting_shapeless_recipeDisplay_data_Objects_ingredients, ccrafting_shapeless_recipeDisplay_data_Objects_result, ccrafting_shapeless_recipeDisplay_data_Objects_craftingStation, ccrafting_shaped_recipeDisplay_data_VarInt_width, ccrafting_shaped_recipeDisplay_data_VarInt_height, ccrafting_shaped_recipeDisplay_data_Objects_ingredients, ccrafting_shaped_recipeDisplay_data_Objects_result, ccrafting_shaped_recipeDisplay_data_Objects_craftingStation, csmithing_recipeDisplay_data_Objects_template, csmithing_recipeDisplay_data_Objects_base, csmithing_recipeDisplay_data_Objects_addition, csmithing_recipeDisplay_data_Objects_result, csmithing_recipeDisplay_data_Objects_craftingStation, cfurnace_recipeDisplay_data_Objects_ingredient, cfurnace_recipeDisplay_data_Objects_fuel, cfurnace_recipeDisplay_data_Objects_result, cfurnace_recipeDisplay_data_Objects_craftingStation, cfurnace_recipeDisplay_data_VarInt_duration, cfurnace_recipeDisplay_data_f32_experience, cstonecutter_recipeDisplay_data_Objects_ingredient, cstonecutter_recipeDisplay_data_Objects_result, cstonecutter_recipeDisplay_data_Objects_craftingStation};
		return new Byte[0];
	}
}
record play_toClient_packet_abilities( 
	i8 flags,
	f32 flyingSpeed,
	f32 walkingSpeed
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{flags, flyingSpeed, walkingSpeed};
		return new Byte[0];
	}
}
record play_toClient_packet_player_chat( 
	UUID senderUuid,
	VarInt index,
	Bit[] signature,
	String plainMessage,
	i64 timestamp,
	i64 salt,
	//id
	//Switch: SwitchBuildable{compareToFieldName='id', fields={0=[LSerializables.Refactor.Flattenable,@368247b9}, defaultField=[LSerializables.Refactor.Flattenable,@1a6d8329}
	nArray2<VarInt,Object> previousMessages,
	String unsignedChatContent,
	VarInt filterType,
	//Only present if filterType is 2
	i64[] c2_filterTypeMask_i64,
	
	VarInt type_registryIndex,
	//Only present if registryIndex is 0
	String c0_type_anon_String_chat_translationKey,
	VarInt[] c0_type_anon_VarInt_chat_parameters,
	String c0_type_anon_String_chat_style,
	String c0_type_anon_String_narration_translationKey,
	VarInt[] c0_type_anon_VarInt_narration_parameters,
	String c0_type_anon_String_narration_style,
	
	String networkName,
	String networkTargetName
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{senderUuid, index, signature, plainMessage, timestamp, salt, previousMessages, unsignedChatContent, filterType, c2_filterTypeMask_i64, type_registryIndex, c0_type_anon_String_chat_translationKey, c0_type_anon_VarInt_chat_parameters, c0_type_anon_String_chat_style, c0_type_anon_String_narration_translationKey, c0_type_anon_VarInt_narration_parameters, c0_type_anon_String_narration_style, networkName, networkTargetName};
		return new Byte[0];
	}
}
record play_toClient_packet_end_combat_event( 
	VarInt duration
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{duration};
		return new Byte[0];
	}
}
record play_toClient_packet_enter_combat_event( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record play_toClient_packet_death_combat_event( 
	VarInt playerId,
	String message
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{playerId, message};
		return new Byte[0];
	}
}
record play_toClient_packet_player_remove( 
	UUID[] players
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{players};
		return new Byte[0];
	}
}
record play_toClient_packet_player_info( 
	//action_unused is a bitmask of size 1
	Byte action_unused,
	//action_update_priority is a bitmask of size 1
	Byte action_update_priority,
	//action_update_display_name is a bitmask of size 1
	Byte action_update_display_name,
	//action_update_latency is a bitmask of size 1
	Byte action_update_latency,
	//action_update_listed is a bitmask of size 1
	Byte action_update_listed,
	//action_update_game_mode is a bitmask of size 1
	Byte action_update_game_mode,
	//action_initialize_chat is a bitmask of size 1
	Byte action_initialize_chat,
	//action_add_player is a bitmask of size 1
	Byte action_add_player,
	//uuid
	//Switch: SwitchBuildable{compareToFieldName='../action/add_player', fields={1=[LSerializables.Refactor.ContainerField,@1a942c18}, defaultField=[LSerializables.Refactor.Flattenable,@55a147cc}
	//Switch: SwitchBuildable{compareToFieldName='../action/initialize_chat', fields={1=[LSerializables.Refactor.Flattenable,@71ba6d4e}, defaultField=[LSerializables.Refactor.Flattenable,@738dc9b}
	//Switch: SwitchBuildable{compareToFieldName='../action/update_game_mode', fields={1=[LSerializables.Refactor.Flattenable,@3c77d488}, defaultField=[LSerializables.Refactor.Flattenable,@63376bed}
	//Switch: SwitchBuildable{compareToFieldName='../action/update_listed', fields={1=[LSerializables.Refactor.Flattenable,@4145bad8}, defaultField=[LSerializables.Refactor.Flattenable,@d86a6f}
	//Switch: SwitchBuildable{compareToFieldName='../action/update_latency', fields={1=[LSerializables.Refactor.Flattenable,@2892d68}, defaultField=[LSerializables.Refactor.Flattenable,@5ab956d7}
	//Switch: SwitchBuildable{compareToFieldName='../action/update_display_name', fields={1=[LSerializables.Refactor.Flattenable,@3646a422}, defaultField=[LSerializables.Refactor.Flattenable,@750e2b97}
	//Switch: SwitchBuildable{compareToFieldName='../action/update_priority', fields={1=[LSerializables.Refactor.Flattenable,@3e27aa33}, defaultField=[LSerializables.Refactor.Flattenable,@2e385cce}
	nArray8<UUID,Object,Object,Object,Object,Object,Object,Object> data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{action_unused, action_update_priority, action_update_display_name, action_update_latency, action_update_listed, action_update_game_mode, action_initialize_chat, action_add_player, data};
		return new Byte[0];
	}
}
record play_toClient_packet_face_player( 
	VarInt feet_eyes,
	f32 x,
	f32 y,
	f32 z,
	Boolean isEntity,
	//Only present if isEntity is true
	VarInt ctrue_entityId_VarInt,
	
	//Only present if isEntity is true
	VarInt ctrue_entity_feet_eyes_VarInt
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{feet_eyes, x, y, z, isEntity, ctrue_entityId_VarInt, ctrue_entity_feet_eyes_VarInt};
		return new Byte[0];
	}
}
record play_toClient_packet_position( 
	VarInt teleportId,
	f32 x,
	f32 y,
	f32 z,
	f32 dx,
	f32 dy,
	f32 dz,
	f32 yaw,
	f32 pitch,
	//possible values: 1=x, 2=y, 3=z, 4=yaw, 5=pitch, 6=dx, 7=dy, 8=dz, 9=yawDelta, 
	u32 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{teleportId, x, y, z, dx, dy, dz, yaw, pitch, flags};
		return new Byte[0];
	}
}
record play_toClient_packet_player_rotation( 
	f32 yaw,
	f32 pitch
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{yaw, pitch};
		return new Byte[0];
	}
}
record play_toClient_packet_recipe_book_add( 
	//recipe_displayId
	//VarInt mapper: 0=crafting_shapeless, 1=crafting_shaped, 2=furnace, 3=stonecutter, 4=smithing, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={crafting_shapeless=[LSerializables.Refactor.ContainerField,@298a5e20, crafting_shaped=[LSerializables.Refactor.ContainerField,@2a7f1f10, smithing=[LSerializables.Refactor.ContainerField,@46cdf8bd, furnace=[LSerializables.Refactor.ContainerField,@f0c8a99, stonecutter=[LSerializables.Refactor.ContainerField,@740cae06}, defaultField=null}
	//recipe_group
	//VarInt mapper: 0=crafting_building_blocks, 1=crafting_redstone, 2=crafting_equipment, 3=crafting_misc, 4=furnace_food, 5=furnace_blocks, 6=furnace_misc, 7=blast_furnace_blocks, 8=blast_furnace_misc, 9=smoker_food, 10=stonecutter, 11=smithing, 12=campfire, 
	//optional recipe_craftingRequirements
	//u8 mapper: 1=notification, 2=highlight, 
	nArray7<VarInt,VarInt,Object,VarInt,VarInt,ComplexType[],u8> entries,
	Boolean replace
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entries, replace};
		return new Byte[0];
	}
}
record play_toClient_packet_recipe_book_remove( 
	VarInt[] recipeIds
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{recipeIds};
		return new Byte[0];
	}
}
record play_toClient_packet_recipe_book_settings( 
	Boolean craftingGuiOpen,
	Boolean craftingFilteringCraftable,
	Boolean smeltingGuiOpen,
	Boolean smeltingFilteringCraftable,
	Boolean blastGuiOpen,
	Boolean blastFilteringCraftable,
	Boolean smokerGuiOpen,
	Boolean smokerFilteringCraftable
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{craftingGuiOpen, craftingFilteringCraftable, smeltingGuiOpen, smeltingFilteringCraftable, blastGuiOpen, blastFilteringCraftable, smokerGuiOpen, smokerFilteringCraftable};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_destroy( 
	VarInt[] entityIds
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityIds};
		return new Byte[0];
	}
}
record play_toClient_packet_remove_entity_effect( 
	VarInt entityId,
	VarInt effectId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, effectId};
		return new Byte[0];
	}
}
record play_toClient_packet_reset_score( 
	String entity_name,
	String objective_name
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entity_name, objective_name};
		return new Byte[0];
	}
}
record play_toClient_packet_respawn( 
	VarInt worldState_dimension,
	String worldState_name,
	i64 worldState_hashedSeed,
	//possible values: 0=survival, 1=creative, 2=adventure, 3=spectator, 
	i8 worldState_gamemode,
	u8 worldState_previousGamemode,
	Boolean worldState_isDebug,
	Boolean worldState_isFlat,
	String worldState_death_dimensionName,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt worldState_portalCooldown,
	VarInt worldState_seaLevel,
	u8 copyMetadata
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{worldState_dimension, worldState_name, worldState_hashedSeed, worldState_gamemode, worldState_previousGamemode, worldState_isDebug, worldState_isFlat, worldState_death_dimensionName, location_x, location_z, location_y, worldState_portalCooldown, worldState_seaLevel, copyMetadata};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_head_rotation( 
	VarInt entityId,
	i8 headYaw
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, headYaw};
		return new Byte[0];
	}
}
record play_toClient_packet_multi_block_change( 
	//chunkCoordinates_x is a bitmask of size 22
	Integer chunkCoordinates_x,
	//chunkCoordinates_z is a bitmask of size 22
	Integer chunkCoordinates_z,
	//chunkCoordinates_y is a bitmask of size 20
	Integer chunkCoordinates_y,
	VarInt[] records
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{chunkCoordinates_x, chunkCoordinates_z, chunkCoordinates_y, records};
		return new Byte[0];
	}
}
record play_toClient_packet_select_advancement_tab( 
	String id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record play_toClient_packet_server_data( 
	String motd,
	Bit[] iconBytes
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{motd, iconBytes};
		return new Byte[0];
	}
}
record play_toClient_packet_action_bar( 
	String text
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{text};
		return new Byte[0];
	}
}
record play_toClient_packet_world_border_center( 
	f32 x,
	f32 z
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, z};
		return new Byte[0];
	}
}
record play_toClient_packet_world_border_lerp_size( 
	f32 oldDiameter,
	f32 newDiameter,
	VarInt speed
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{oldDiameter, newDiameter, speed};
		return new Byte[0];
	}
}
record play_toClient_packet_world_border_size( 
	f32 diameter
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{diameter};
		return new Byte[0];
	}
}
record play_toClient_packet_world_border_warning_delay( 
	VarInt warningTime
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{warningTime};
		return new Byte[0];
	}
}
record play_toClient_packet_world_border_warning_reach( 
	VarInt warningBlocks
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{warningBlocks};
		return new Byte[0];
	}
}
record play_toClient_packet_camera( 
	VarInt cameraId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{cameraId};
		return new Byte[0];
	}
}
record play_toClient_packet_update_view_position( 
	VarInt chunkX,
	VarInt chunkZ
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{chunkX, chunkZ};
		return new Byte[0];
	}
}
record play_toClient_packet_update_view_distance( 
	VarInt viewDistance
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{viewDistance};
		return new Byte[0];
	}
}
record play_toClient_packet_set_cursor_item( 
	VarInt contents_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt contents_anon_item,
	VarInt contents_anon_addedComponentCount,
	VarInt contents_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> contents_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> contents_anon_removeComponents
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{contents_itemCount, contents_anon_item, contents_anon_addedComponentCount, contents_anon_removedComponentCount, contents_anon_components, contents_anon_removeComponents};
		return new Byte[0];
	}
}
record play_toClient_packet_spawn_position( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	f32 angle
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, angle};
		return new Byte[0];
	}
}
record play_toClient_packet_scoreboard_display_objective( 
	VarInt position,
	String name
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{position, name};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_metadata( 
	VarInt entityId,
	ComplexType metadata
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, metadata};
		return new Byte[0];
	}
}
record play_toClient_packet_attach_entity( 
	i32 entityId,
	i32 vehicleId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, vehicleId};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_velocity( 
	VarInt entityId,
	i16 velocityX,
	i16 velocityY,
	i16 velocityZ
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, velocityX, velocityY, velocityZ};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_equipment( 
	VarInt entityId,
	ComplexType equipments
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, equipments};
		return new Byte[0];
	}
}
record play_toClient_packet_experience( 
	f32 experienceBar,
	VarInt level,
	VarInt totalExperience
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{experienceBar, level, totalExperience};
		return new Byte[0];
	}
}
record play_toClient_packet_update_health( 
	f32 health,
	VarInt food,
	f32 foodSaturation
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{health, food, foodSaturation};
		return new Byte[0];
	}
}
record play_toClient_packet_held_item_slot( 
	i8 slot
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slot};
		return new Byte[0];
	}
}
record play_toClient_packet_scoreboard_objective( 
	String name,
	i8 action,
	//Only present if action is 0 or 2
	String c0or2_displayText_String,
	
	//Only present if action is 0 or 2
	VarInt c0or2_type_VarInt,
	
	//Only present if action is 0
	VarInt c0_number_format_VarInt,
	//Only present if action is 2
	VarInt c2_number_format_VarInt,
	
	//Only present if action is 0
	//Only present if number_format is 1 or 2
	String c1or2_c0_styling_Object_String,
	
	//Only present if action is 2
	//Only present if number_format is 1 or 2
	String c1or2_c2_styling_Object_String
	
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{name, action, c0or2_displayText_String, c0or2_type_VarInt, c0_number_format_VarInt, c2_number_format_VarInt, , c1or2_c0_styling_Object_String, , c1or2_c2_styling_Object_String};
		return new Byte[0];
	}
}
record play_toClient_packet_set_passengers( 
	VarInt entityId,
	VarInt[] passengers
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, passengers};
		return new Byte[0];
	}
}
record play_toClient_packet_set_player_inventory( 
	VarInt slotId,
	VarInt contents_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt contents_anon_item,
	VarInt contents_anon_addedComponentCount,
	VarInt contents_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> contents_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> contents_anon_removeComponents
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slotId, contents_itemCount, contents_anon_item, contents_anon_addedComponentCount, contents_anon_removedComponentCount, contents_anon_components, contents_anon_removeComponents};
		return new Byte[0];
	}
}
record play_toClient_packet_teams( 
	String team,
	i8 mode,
	//Only present if mode is 0 or 2
	String c0or2_name_String,
	
	//Only present if mode is 0 or 2
	i8 c0or2_friendlyFire_i8,
	
	//Only present if mode is 0 or 2
	String c0or2_nameTagVisibility_String,
	
	//Only present if mode is 0 or 2
	String c0or2_collisionRule_String,
	
	//Only present if mode is 0 or 2
	VarInt c0or2_formatting_VarInt,
	
	//Only present if mode is 0 or 2
	String c0or2_prefix_String,
	
	//Only present if mode is 0 or 2
	String c0or2_suffix_String,
	
	//Only present if mode is 0 or 3 or 4
	String[] c0or3or4_players_String
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{team, mode, c0or2_name_String, c0or2_friendlyFire_i8, c0or2_nameTagVisibility_String, c0or2_collisionRule_String, c0or2_formatting_VarInt, c0or2_prefix_String, c0or2_suffix_String, c0or3or4_players_String};
		return new Byte[0];
	}
}
record play_toClient_packet_scoreboard_score( 
	String itemName,
	String scoreName,
	VarInt value,
	String display_name,
	VarInt number_format,
	//Only present if number_format is 1 or 2
	String c1or2_styling_String
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{itemName, scoreName, value, display_name, number_format, c1or2_styling_String};
		return new Byte[0];
	}
}
record play_toClient_packet_simulation_distance( 
	VarInt distance
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{distance};
		return new Byte[0];
	}
}
record play_toClient_packet_set_title_subtitle( 
	String text
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{text};
		return new Byte[0];
	}
}
record play_toClient_packet_update_time( 
	i64 age,
	i64 time,
	Boolean tickDayTime
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{age, time, tickDayTime};
		return new Byte[0];
	}
}
record play_toClient_packet_set_title_text( 
	String text
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{text};
		return new Byte[0];
	}
}
record play_toClient_packet_set_title_time( 
	i32 fadeIn,
	i32 stay,
	i32 fadeOut
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{fadeIn, stay, fadeOut};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_sound_effect( 
	VarInt soundId,
	//Only present if soundId is 0
	String c0_soundEvent_String_resource,
	f32 c0_soundEvent_f32_range,
	
	//possible values: 0=master, 1=music, 2=record, 3=weather, 4=block, 5=hostile, 6=neutral, 7=player, 8=ambient, 9=voice, 
	VarInt soundCategory,
	VarInt entityId,
	f32 volume,
	f32 pitch,
	i64 seed
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{soundId, c0_soundEvent_String_resource, c0_soundEvent_f32_range, soundCategory, entityId, volume, pitch, seed};
		return new Byte[0];
	}
}
record play_toClient_packet_sound_effect( 
	VarInt soundId,
	//Only present if soundId is 0
	String c0_soundEvent_String_resource,
	f32 c0_soundEvent_f32_range,
	
	//possible values: 0=master, 1=music, 2=record, 3=weather, 4=block, 5=hostile, 6=neutral, 7=player, 8=ambient, 9=voice, 
	VarInt soundCategory,
	i32 x,
	i32 y,
	i32 z,
	f32 volume,
	f32 pitch,
	i64 seed
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{soundId, c0_soundEvent_String_resource, c0_soundEvent_f32_range, soundCategory, x, y, z, volume, pitch, seed};
		return new Byte[0];
	}
}
record play_toClient_packet_start_configuration( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record play_toClient_packet_stop_sound( 
	i8 flags,
	//Only present if flags is 1 or 3
	VarInt c1or3_source_VarInt,
	
	//Only present if flags is 2 or 3
	String c2or3_sound_String
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{flags, c1or3_source_VarInt, c2or3_sound_String};
		return new Byte[0];
	}
}
record play_toClient_packet_system_chat( 
	String content,
	Boolean isActionBar
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{content, isActionBar};
		return new Byte[0];
	}
}
record play_toClient_packet_playerlist_header( 
	String header,
	String footer
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{header, footer};
		return new Byte[0];
	}
}
record play_toClient_packet_nbt_query_response( 
	VarInt transactionId,
	String nbt
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{transactionId, nbt};
		return new Byte[0];
	}
}
record play_toClient_packet_collect( 
	VarInt collectedEntityId,
	VarInt collectorEntityId,
	VarInt pickupItemCount
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{collectedEntityId, collectorEntityId, pickupItemCount};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_teleport( 
	VarInt entityId,
	f32 x,
	f32 y,
	f32 z,
	i8 yaw,
	i8 pitch,
	Boolean onGround
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, x, y, z, yaw, pitch, onGround};
		return new Byte[0];
	}
}
record play_toClient_packet_set_ticking_state( 
	f32 tick_rate,
	Boolean is_frozen
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{tick_rate, is_frozen};
		return new Byte[0];
	}
}
record play_toClient_packet_step_tick( 
	VarInt tick_steps
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{tick_steps};
		return new Byte[0];
	}
}
record play_toClient_packet_advancements( 
	Boolean reset,
	//key
	//optional value_parentId
	//optional value_displayData
	//optional value_displayData
	//optional value_displayData
	//optional Switch: SwitchBuildable{compareToFieldName='itemCount', fields={0=[LSerializables.Refactor.Flattenable,@53ce1329}, defaultField=[LSerializables.Refactor.ContainerField,@316bcf94}
	//optional value_displayData
	//optional unused
	//optional hidden
	//optional show_toast
	//optional has_background_texture
	//optional Switch: SwitchBuildable{compareToFieldName='flags/has_background_texture', fields={1=[LSerializables.Refactor.Flattenable,@449a4f23}, defaultField=[LSerializables.Refactor.Flattenable,@1530c739}
	//optional value_displayData
	//optional value_displayData
	//value_requirements
	//value_sendsTelemtryData
	nArray16<String,String,String,String,VarInt,Object,VarInt,Integer,Byte,Byte,Byte,Object,f32,f32,String[][],Boolean> advancementMapping,
	String[] identifiers,
	//key
	//value
	nArray2<String,nArray2<String,i64>> progressMapping
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{reset, advancementMapping, identifiers, progressMapping};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_update_attributes( 
	VarInt entityId,
	//VarInt mapper: 0=generic.armor, 1=generic.armor_toughness, 2=generic.attack_damage, 3=generic.attack_knockback, 4=generic.attack_speed, 5=player.block_break_speed, 6=player.block_interaction_range, 7=player.entity_interaction_range, 8=generic.fall_damage_multiplier, 9=generic.flying_speed, 10=generic.follow_range, 11=generic.gravity, 12=generic.jump_strength, 13=generic.knockback_resistance, 14=generic.luck, 15=generic.max_absorption, 16=generic.max_health, 17=generic.movement_speed, 18=generic.safe_fall_distance, 19=generic.scale, 20=zombie.spawn_reinforcements, 21=generic.step_height, 
	//value
	//modifiers
	nArray3<VarInt,f32,nArray3<String,f32,i8>> properties
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, properties};
		return new Byte[0];
	}
}
record play_toClient_packet_entity_effect( 
	VarInt entityId,
	VarInt effectId,
	VarInt amplifier,
	VarInt duration,
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, effectId, amplifier, duration, flags};
		return new Byte[0];
	}
}
record play_toClient_packet_declare_recipes( 
	//name
	//items
	nArray2<String,VarInt[]> recipes,
	//input
	//VarInt mapper: 0=empty, 1=any_fuel, 2=item, 3=item_stack, 4=tag, 5=smithing_trim, 6=with_remainder, 7=composite, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={simthing_trim=[LSerializables.Refactor.ContainerField,@5b1669c0, item=[LSerializables.Refactor.Flattenable,@78e4deb0, composite=[LSerializables.Refactor.Flattenable,@6e9175d8, item_stack=[LSerializables.Refactor.Flattenable,@7d0b7e3c, tag=[LSerializables.Refactor.Flattenable,@15bb5034, with_remainder=[LSerializables.Refactor.ContainerField,@4b741d6d, any_fuel=[LSerializables.Refactor.Flattenable,@2eae8e6e, empty=[LSerializables.Refactor.Flattenable,@8f2ef19}, defaultField=null}
	nArray3<ComplexType,VarInt,Object> stoneCutterRecipes
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{recipes, stoneCutterRecipes};
		return new Byte[0];
	}
}
record play_toClient_packet_tags( 
	//tagType
	//tags
	nArray2<String,nArray2<String,VarInt[]>> tags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{tags};
		return new Byte[0];
	}
}
record play_toClient_packet_set_projectile_power( 
	VarInt id,
	f32 accelerationPower
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id, accelerationPower};
		return new Byte[0];
	}
}
record play_toServer_packet_teleport_confirm( 
	VarInt teleportId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{teleportId};
		return new Byte[0];
	}
}
record play_toServer_packet_query_block_nbt( 
	VarInt transactionId,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{transactionId, location_x, location_z, location_y};
		return new Byte[0];
	}
}
record play_toServer_packet_select_bundle_item( 
	VarInt slotId,
	VarInt selectedItemIndex
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slotId, selectedItemIndex};
		return new Byte[0];
	}
}
record play_toServer_packet_set_difficulty( 
	u8 newDifficulty
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{newDifficulty};
		return new Byte[0];
	}
}
record play_toServer_packet_message_acknowledgement( 
	VarInt count
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{count};
		return new Byte[0];
	}
}
record play_toServer_packet_chat_command( 
	String command
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{command};
		return new Byte[0];
	}
}
record play_toServer_packet_chat_command_signed( 
	String command,
	i64 timestamp,
	i64 salt,
	//argumentName
	//signature
	nArray2<String,Bit[]> argumentSignatures,
	VarInt messageCount,
	Bit[] acknowledged
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{command, timestamp, salt, argumentSignatures, messageCount, acknowledged};
		return new Byte[0];
	}
}
record play_toServer_packet_chat_message( 
	String message,
	i64 timestamp,
	i64 salt,
	Bit[] signature,
	VarInt offset,
	Bit[] acknowledged
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{message, timestamp, salt, signature, offset, acknowledged};
		return new Byte[0];
	}
}
record play_toServer_packet_chat_session_update( 
	UUID sessionUUID,
	i64 expireTime,
	Bit[] publicKey,
	Bit[] signature
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{sessionUUID, expireTime, publicKey, signature};
		return new Byte[0];
	}
}
record play_toServer_packet_chunk_batch_received( 
	f32 chunksPerTick
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{chunksPerTick};
		return new Byte[0];
	}
}
record play_toServer_packet_client_command( 
	VarInt actionId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{actionId};
		return new Byte[0];
	}
}
record play_toServer_packet_tick_end( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record play_toServer_packet_settings( 
	String locale,
	i8 viewDistance,
	VarInt chatFlags,
	Boolean chatColors,
	u8 skinParts,
	VarInt mainHand,
	Boolean enableTextFiltering,
	Boolean enableServerListing,
	//possible values: 0=all, 1=decreased, 2=minimal, 
	VarInt particleStatus
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{locale, viewDistance, chatFlags, chatColors, skinParts, mainHand, enableTextFiltering, enableServerListing, particleStatus};
		return new Byte[0];
	}
}
record play_toServer_packet_tab_complete( 
	VarInt transactionId,
	String text
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{transactionId, text};
		return new Byte[0];
	}
}
record play_toServer_packet_configuration_acknowledged( 
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{};
		return new Byte[0];
	}
}
record play_toServer_packet_enchant_item( 
	VarInt windowId,
	i8 enchantment
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, enchantment};
		return new Byte[0];
	}
}
record play_toServer_packet_window_click( 
	VarInt windowId,
	VarInt stateId,
	i16 slot,
	i8 mouseButton,
	VarInt mode,
	//location
	//item_itemCount
	//Switch: SwitchBuildable{compareToFieldName='itemCount', fields={0=[LSerializables.Refactor.Flattenable,@53ce1329}, defaultField=[LSerializables.Refactor.ContainerField,@316bcf94}
	nArray3<i16,VarInt,Object> changedSlots,
	VarInt cursorItem_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt cursorItem_anon_item,
	VarInt cursorItem_anon_addedComponentCount,
	VarInt cursorItem_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> cursorItem_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> cursorItem_anon_removeComponents
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, stateId, slot, mouseButton, mode, changedSlots, cursorItem_itemCount, cursorItem_anon_item, cursorItem_anon_addedComponentCount, cursorItem_anon_removedComponentCount, cursorItem_anon_components, cursorItem_anon_removeComponents};
		return new Byte[0];
	}
}
record play_toServer_packet_close_window( 
	VarInt windowId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId};
		return new Byte[0];
	}
}
record play_toServer_packet_set_slot_state( 
	VarInt slot_id,
	VarInt window_id,
	Boolean state
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slot_id, window_id, state};
		return new Byte[0];
	}
}
record play_toServer_packet_custom_payload( 
	String channel,
	Byte[] data
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{channel, data};
		return new Byte[0];
	}
}
record play_toServer_packet_debug_sample_subscription( 
	VarInt type
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{type};
		return new Byte[0];
	}
}
record play_toServer_packet_edit_book( 
	VarInt hand,
	String[] pages,
	String title
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{hand, pages, title};
		return new Byte[0];
	}
}
record play_toServer_packet_query_entity_nbt( 
	VarInt transactionId,
	VarInt entityId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{transactionId, entityId};
		return new Byte[0];
	}
}
record play_toServer_packet_use_entity( 
	VarInt target,
	VarInt mouse,
	//Only present if mouse is 2
	f32 c2_x_f32,
	
	//Only present if mouse is 2
	f32 c2_y_f32,
	
	//Only present if mouse is 2
	f32 c2_z_f32,
	
	//Only present if mouse is 0 or 2
	VarInt c0or2_hand_VarInt,
	
	Boolean sneaking
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{target, mouse, c2_x_f32, c2_y_f32, c2_z_f32, c0or2_hand_VarInt, sneaking};
		return new Byte[0];
	}
}
record play_toServer_packet_generate_structure( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt levels,
	Boolean keepJigsaws
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, levels, keepJigsaws};
		return new Byte[0];
	}
}
record play_toServer_packet_keep_alive( 
	i64 keepAliveId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{keepAliveId};
		return new Byte[0];
	}
}
record play_toServer_packet_lock_difficulty( 
	Boolean locked
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{locked};
		return new Byte[0];
	}
}
record play_toServer_packet_position( 
	f32 x,
	f32 y,
	f32 z,
	//possible values: 1=onGround, 2=hasHorizontalCollision, 
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, y, z, flags};
		return new Byte[0];
	}
}
record play_toServer_packet_position_look( 
	f32 x,
	f32 y,
	f32 z,
	f32 yaw,
	f32 pitch,
	//possible values: 1=onGround, 2=hasHorizontalCollision, 
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, y, z, yaw, pitch, flags};
		return new Byte[0];
	}
}
record play_toServer_packet_look( 
	f32 yaw,
	f32 pitch,
	//possible values: 1=onGround, 2=hasHorizontalCollision, 
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{yaw, pitch, flags};
		return new Byte[0];
	}
}
record play_toServer_packet_flying( 
	//possible values: 1=onGround, 2=hasHorizontalCollision, 
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{flags};
		return new Byte[0];
	}
}
record play_toServer_packet_vehicle_move( 
	f32 x,
	f32 y,
	f32 z,
	f32 yaw,
	f32 pitch
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{x, y, z, yaw, pitch};
		return new Byte[0];
	}
}
record play_toServer_packet_steer_boat( 
	Boolean leftPaddle,
	Boolean rightPaddle
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{leftPaddle, rightPaddle};
		return new Byte[0];
	}
}
record play_toServer_packet_pick_item( 
	VarInt slot
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slot};
		return new Byte[0];
	}
}
record play_toServer_packet_ping_request( 
	i64 id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record play_toServer_packet_craft_recipe_request( 
	VarInt windowId,
	VarInt recipeId,
	Boolean makeAll
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{windowId, recipeId, makeAll};
		return new Byte[0];
	}
}
record play_toServer_packet_abilities( 
	i8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{flags};
		return new Byte[0];
	}
}
record play_toServer_packet_block_dig( 
	VarInt status,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	i8 face,
	VarInt sequence
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{status, location_x, location_z, location_y, face, sequence};
		return new Byte[0];
	}
}
record play_toServer_packet_entity_action( 
	VarInt entityId,
	VarInt actionId,
	VarInt jumpBoost
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, actionId, jumpBoost};
		return new Byte[0];
	}
}
record play_toServer_packet_player_input( 
	//possible values: 1=forward, 2=backward, 3=left, 4=right, 5=jump, 6=shift, 7=sprint, 
	u8 inputs
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{inputs};
		return new Byte[0];
	}
}
record play_toServer_packet_pong( 
	i32 id
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{id};
		return new Byte[0];
	}
}
record play_toServer_packet_recipe_book( 
	VarInt bookId,
	Boolean bookOpen,
	Boolean filterActive
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{bookId, bookOpen, filterActive};
		return new Byte[0];
	}
}
record play_toServer_packet_displayed_recipe( 
	VarInt recipeId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{recipeId};
		return new Byte[0];
	}
}
record play_toServer_packet_name_item( 
	String name
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{name};
		return new Byte[0];
	}
}
record play_toServer_packet_resource_pack_receive( 
	UUID uuid,
	VarInt result
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{uuid, result};
		return new Byte[0];
	}
}
record play_toServer_packet_advancement_tab( 
	VarInt action,
	//if action is any of [1]all fields are empty
	//Only present if action is 0
	String c0_tabId_String
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{action, c0_tabId_String};
		return new Byte[0];
	}
}
record play_toServer_packet_select_trade( 
	VarInt slot
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slot};
		return new Byte[0];
	}
}
record play_toServer_packet_set_beacon_effect( 
	VarInt primary_effect,
	VarInt secondary_effect
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{primary_effect, secondary_effect};
		return new Byte[0];
	}
}
record play_toServer_packet_held_item_slot( 
	i16 slotId
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slotId};
		return new Byte[0];
	}
}
record play_toServer_packet_update_command_block( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	String command,
	VarInt mode,
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, command, mode, flags};
		return new Byte[0];
	}
}
record play_toServer_packet_update_command_block_minecart( 
	VarInt entityId,
	String command,
	Boolean track_output
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{entityId, command, track_output};
		return new Byte[0];
	}
}
record play_toServer_packet_set_creative_slot( 
	i16 slot,
	VarInt item_itemCount,
	//if itemCount is any of [0]all fields are empty
	//Default field: 
	VarInt item_anon_item,
	VarInt item_anon_addedComponentCount,
	VarInt item_anon_removedComponentCount,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	//Switch: SwitchBuildable{compareToFieldName='type', fields={container=[LSerializables.Refactor.ContainerField,@6404f418, note_block_sound=[LSerializables.Refactor.Flattenable,@3e11f9e9, max_stack_size=[LSerializables.Refactor.Flattenable,@1de5f259, firework_explosion=[LSerializables.Refactor.Flattenable,@729d991e, enchantable=[LSerializables.Refactor.Flattenable,@31fa1761, attribute_modifiers=[LSerializables.Refactor.ContainerField,@957e06, custom_name=[LSerializables.Refactor.Flattenable,@32502377, writable_book_content=[LSerializables.Refactor.ContainerField,@2c1b194a, death_protection=[LSerializables.Refactor.ContainerField,@4dbb42b7, trim=[LSerializables.Refactor.ContainerField,@66f57048, unbreakable=[LSerializables.Refactor.Flattenable,@550dbc7a, lock=[LSerializables.Refactor.Flattenable,@21282ed8, suspicious_stew_effects=[LSerializables.Refactor.ContainerField,@36916eb0, use_cooldown=[LSerializables.Refactor.ContainerField,@7bab3f1a, bees=[LSerializables.Refactor.ContainerField,@437da279, custom_model_data=[LSerializables.Refactor.Flattenable,@23c30a20, bundle_contents=[LSerializables.Refactor.ContainerField,@1e1a0406, recipes=[LSerializables.Refactor.Flattenable,@3cebbb30, pot_decorations=[LSerializables.Refactor.ContainerField,@12aba8be, profile=[LSerializables.Refactor.ContainerField,@290222c1, written_book_content=[LSerializables.Refactor.ContainerField,@67f639d3, intangible_projectile=[LSerializables.Refactor.Flattenable,@6253c26, tool=[LSerializables.Refactor.ContainerField,@49049a04, item_model=[LSerializables.Refactor.Flattenable,@71a8adcf, map_color=[LSerializables.Refactor.Flattenable,@27462a88, lodestone_tracker=[LSerializables.Refactor.ContainerField,@82de64a, stored_enchantments=[LSerializables.Refactor.ContainerField,@659499f1, custom_data=[LSerializables.Refactor.Flattenable,@51e69659, can_place_on=[LSerializables.Refactor.ContainerField,@47e2e487, can_break=[LSerializables.Refactor.ContainerField,@201a4587, base_color=[LSerializables.Refactor.Flattenable,@61001b64, rarity=[LSerializables.Refactor.Flattenable,@4310d43, tooltip_style=[LSerializables.Refactor.Flattenable,@54a7079e, damage=[LSerializables.Refactor.Flattenable,@26e356f0, lore=[LSerializables.Refactor.Flattenable,@47d9a273, debug_stick_state=[LSerializables.Refactor.Flattenable,@4b8ee4de, hide_tooltip=[LSerializables.Refactor.Flattenable,@27f981c6, dyed_color=[LSerializables.Refactor.ContainerField,@1b11171f, instrument=[LSerializables.Refactor.ContainerField,@1151e434, hide_additional_tooltip=[LSerializables.Refactor.Flattenable,@2dc54ad4, repair_cost=[LSerializables.Refactor.Flattenable,@4659191b, fireworks=[LSerializables.Refactor.ContainerField,@55634720, block_entity_data=[LSerializables.Refactor.Flattenable,@4b0d79fc, banner_patterns=[LSerializables.Refactor.ContainerField,@4c1909a3, map_id=[LSerializables.Refactor.Flattenable,@428640fa, container_loot=[LSerializables.Refactor.Flattenable,@d9345cd, entity_data=[LSerializables.Refactor.Flattenable,@2d710f1a, map_decorations=[LSerializables.Refactor.Flattenable,@29215f06, ominous_bottle_amplifier=[LSerializables.Refactor.Flattenable,@59505b48, equippable=[LSerializables.Refactor.ContainerField,@4efac082, item_name=[LSerializables.Refactor.Flattenable,@6bd61f98, enchantments=[LSerializables.Refactor.ContainerField,@48aca48b, food=[LSerializables.Refactor.ContainerField,@13fd2ccd, use_remainder=[LSerializables.Refactor.Flattenable,@b9b00e0, repairable=[LSerializables.Refactor.ContainerField,@506ae4d4, bucket_entity_data=[LSerializables.Refactor.Flattenable,@7d4f9aae, max_damage=[LSerializables.Refactor.Flattenable,@72e5a8e, enchantment_glint_override=[LSerializables.Refactor.Flattenable,@54e1c68b, creative_slot_lock=[LSerializables.Refactor.Flattenable,@53aac487, block_state=[LSerializables.Refactor.ContainerField,@52b1beb6, consumable=[LSerializables.Refactor.ContainerField,@273e7444, damage_resistant=[LSerializables.Refactor.Flattenable,@7db12bb6, potion_contents=[LSerializables.Refactor.ContainerField,@783a467b, jukebox_playable=[LSerializables.Refactor.ContainerField,@272113c4, charged_projectiles=[LSerializables.Refactor.ContainerField,@73e9cf30, map_post_processing=[LSerializables.Refactor.Flattenable,@771a660}, defaultField=null}
	nArray2<VarInt,Object> item_anon_components,
	//VarInt mapper: 0=custom_data, 1=max_stack_size, 2=max_damage, 3=damage, 4=unbreakable, 5=custom_name, 6=item_name, 7=item_model, 8=lore, 9=rarity, 10=enchantments, 11=can_place_on, 12=can_break, 13=attribute_modifiers, 14=custom_model_data, 15=hide_additional_tooltip, 16=hide_tooltip, 17=repair_cost, 18=creative_slot_lock, 19=enchantment_glint_override, 20=intangible_projectile, 21=food, 22=consumable, 23=use_remainder, 24=use_cooldown, 25=damage_resistant, 26=tool, 27=enchantable, 28=equippable, 29=repairable, 30=glider, 31=tooltip_style, 32=death_protection, 33=stored_enchantments, 34=dyed_color, 35=map_color, 36=map_id, 37=map_decorations, 38=map_post_processing, 39=charged_projectiles, 40=bundle_contents, 41=potion_contents, 42=suspicious_stew_effects, 43=writable_book_content, 44=written_book_content, 45=trim, 46=debug_stick_state, 47=entity_data, 48=bucket_entity_data, 49=block_entity_data, 50=instrument, 51=ominous_bottle_amplifier, 52=jukebox_playable, 53=recipes, 54=lodestone_tracker, 55=firework_explosion, 56=fireworks, 57=profile, 58=note_block_sound, 59=banner_patterns, 60=base_color, 61=pot_decorations, 62=container, 63=block_state, 64=bees, 65=lock, 66=container_loot, 
	nArray1<VarInt> item_anon_removeComponents
	//Default field end
	
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{slot, item_itemCount, item_anon_item, item_anon_addedComponentCount, item_anon_removedComponentCount, item_anon_components, item_anon_removeComponents};
		return new Byte[0];
	}
}
record play_toServer_packet_update_jigsaw_block( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	String name,
	String target,
	String pool,
	String finalState,
	String jointType,
	VarInt selection_priority,
	VarInt placement_priority
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, name, target, pool, finalState, jointType, selection_priority, placement_priority};
		return new Byte[0];
	}
}
record play_toServer_packet_update_structure_block( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt action,
	VarInt mode,
	String name,
	i8 offset_x,
	i8 offset_y,
	i8 offset_z,
	i8 size_x,
	i8 size_y,
	i8 size_z,
	VarInt mirror,
	VarInt rotation,
	String metadata,
	f32 integrity,
	VarInt seed,
	u8 flags
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, action, mode, name, offset_x, offset_y, offset_z, size_x, size_y, size_z, mirror, rotation, metadata, integrity, seed, flags};
		return new Byte[0];
	}
}
record play_toServer_packet_update_sign( 
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	Boolean isFrontText,
	String text1,
	String text2,
	String text3,
	String text4
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{location_x, location_z, location_y, isFrontText, text1, text2, text3, text4};
		return new Byte[0];
	}
}
record play_toServer_packet_arm_animation( 
	VarInt hand
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{hand};
		return new Byte[0];
	}
}
record play_toServer_packet_spectate( 
	UUID target
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{target};
		return new Byte[0];
	}
}
record play_toServer_packet_block_place( 
	VarInt hand,
	//location_x is a bitmask of size 26
	Integer location_x,
	//location_z is a bitmask of size 26
	Integer location_z,
	//location_y is a bitmask of size 12
	Short location_y,
	VarInt direction,
	f32 cursorX,
	f32 cursorY,
	f32 cursorZ,
	Boolean insideBlock,
	Boolean worldBorderHit,
	VarInt sequence
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{hand, location_x, location_z, location_y, direction, cursorX, cursorY, cursorZ, insideBlock, worldBorderHit, sequence};
		return new Byte[0];
	}
}
record play_toServer_packet_use_item( 
	VarInt hand,
	VarInt sequence,
	f32 rotation_x,
	f32 rotation_y
	){
	public Byte[] serialize() {
		Object[] fields = new Object[]{hand, sequence, rotation_x, rotation_y};
		return new Byte[0];
	}
}
