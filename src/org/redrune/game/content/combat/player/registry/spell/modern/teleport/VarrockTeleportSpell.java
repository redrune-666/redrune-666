package org.redrune.game.content.combat.player.registry.spell.modern.teleport;

import org.redrune.game.content.combat.player.registry.wrapper.magic.TeleportationSpellEvent;
import org.redrune.game.node.Location;

/**
 * @author Tyluur <itstyluur@gmail.com>
 * @since 7/27/2017
 */
public class VarrockTeleportSpell implements TeleportationSpellEvent {
	
	@Override
	public int levelRequired() {
		return 25;
	}
	
	@Override
	public int[] runesRequired() {
		return arguments(FIRE_RUNE, 1, AIR_RUNE, 3, LAW_RUNE, 1);
	}
	
	@Override
	public Location destination() {
		return new Location(3212, 3424, 0);
	}
	
	@Override
	public int spellId() {
		return 40;
	}
	
	@Override
	public double exp() {
		return 19;
	}
	
	@Override
	public MagicBook book() {
		return MagicBook.REGULAR;
	}
}
