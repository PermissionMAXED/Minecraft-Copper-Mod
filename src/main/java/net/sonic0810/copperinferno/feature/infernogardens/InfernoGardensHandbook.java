package net.sonic0810.copperinferno.feature.infernogardens;

import net.sonic0810.copperinferno.core.handbook.HandbookEntries;
import net.sonic0810.copperinferno.core.handbook.HandbookEntry;

/**
 * Handbook pages for the Inferno gardens: one "dimension" lore entry per worldgen
 * feature added by {@code InfernoGardensFeature} (no recipes — the gardens are
 * found, not crafted). Entry ids mirror the placed-feature ids emitted by
 * {@code devtools/gen/infernogardens_gen.py}; {@code devtools/check_handbook.py}
 * parses the inline {@code new HandbookEntry(...)} literals positionally, so keep
 * them inline.
 */
final class InfernoGardensHandbook {
	private InfernoGardensHandbook() {
	}

	static void register() {
		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_ember_lily", "copper_inferno:ember_lily", null,
				null,
				null, 0, "Ember Lilies (light 5) bloom on the ember moss and glowing soil of the groves.", "Glutlilien (Licht 5) bl\u00fchen auf dem Glutmoos und dem gl\u00fchenden Boden der Haine."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_cinder_fern", "copper_inferno:cinder_fern", null,
				null,
				null, 0, "Cinder Ferns unfurl across cinderstone, ember soil and ash all over the Inferno.", "Schlackenfarne entrollen sich auf Zunderstein, Glutboden und Asche im ganzen Inferno."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_sear_sprigs", "copper_inferno:sear_sprigs", null,
				null,
				null, 0, "Sear Sprigs: stubby scorched twigs with smoldering tips, rooted in bare cinderstone and ash.", "Sengzweiglein: kurze versengte Zweige mit schwelenden Spitzen, verwurzelt in nacktem Zunderstein und Asche."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_molten_bud", "copper_inferno:molten_bud", null,
				null,
				null, 0, "Molten Buds (light 6) swell out of ember soil, their seams glowing with trapped heat.", "Schmelzknospen (Licht 6) quellen aus dem Glutboden, ihre N\u00e4hte gl\u00fchen vor gespeicherter Hitze."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_ash_bramble", "copper_inferno:ash_bramble", null,
				null,
				null, 0, "Ash Brambles tangle over ash drifts and cinder gravel in the wastes.", "Aschendornb\u00fcsche wuchern \u00fcber Aschenverwehungen und Zinderkies der \u00d6de."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_glow_tendrils", "copper_inferno:glow_tendrils", null,
				null,
				null, 0, "Glow Tendrils (light 7) sway over the moss and wart floors of the Ember Grove.", "Leuchtranken (Licht 7) wiegen sich \u00fcber den Moos- und Warzenb\u00f6den des Gluthains."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_soot_puff", "copper_inferno:soot_puff", null,
				null,
				null, 0, "Soot Puffs: dark puffball fungi dotting the slagstone flats of the Slag Sea.", "Ru\u00dfboviste: dunkle Bovistpilze auf den Schlackenstein-Ebenen des Schlackenmeers."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_slag_thistle", "copper_inferno:slag_thistle", null,
				null,
				null, 0, "Slag Thistles bristle from slagstone and cobbled cinderstone, hardy against the ash winds.", "Schlackendisteln spr\u00fce\u00dfen aus Schlackenstein und Bruchzunderstein, unbeeindruckt von den Aschenwinden."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_pyre_reed", "copper_inferno:pyre_reed", null,
				null,
				null, 0, "Pyre Reeds line the scorched-sand banks near the lava-sea level (y 30-36).", "Feuerschilf s\u00e4umt die B\u00e4nke aus verbranntem Sand nahe dem Lavameer-Niveau (y 30-36)."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_copper_rose", "copper_inferno:copper_rose", null,
				null,
				null, 0, "Copper Roses: rare metal-petaled blooms scattered across the grove floors.", "Kupferrosen: seltene Bl\u00fcten mit Metallbl\u00e4ttern, verstreut auf den B\u00f6den des Hains."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_inferno_orchid", "copper_inferno:inferno_orchid", null,
				null,
				null, 0, "The Inferno Orchid (light 4) is the rarest garden bloom \u2014 it only takes root in ember moss.", "Die Inferno-Orchidee (Licht 4) ist die seltenste Gartenbl\u00fcte \u2014 sie wurzelt nur in Glutmoos."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_charred_shrub", "copper_inferno:charred_shrub", null,
				null,
				null, 0, "Charred Shrubs: dead, ember-flecked brushwood clawing out of the cinder wastes.", "Verkohlte Str\u00e4ucher: totes, glutgesprenkeltes Buschwerk, das sich aus der Aschen\u00f6de krallt."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_gilded_clover", "copper_inferno:gilded_clover", null,
				null,
				null, 0, "Gilded Clover carpets lucky corners of the grove floors in gold-green.", "Vergoldeter Klee \u00fcberzieht gl\u00fcckliche Ecken der Hainb\u00f6den in Goldgr\u00fcn."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_garden_wastes_mix", "copper_inferno:sear_sprigs", null,
				null,
				null, 0, "Wastes gardens: mixed stands of sear sprigs, ash brambles, cinder ferns and charred shrubs.", "W\u00fcsteng\u00e4rten: gemischte Best\u00e4nde aus Sengzweiglein, Aschendornb\u00fcschen, Schlackenfarnen und verkohlten Str\u00e4uchern."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_garden_grove_mix", "copper_inferno:ember_lily", null,
				null,
				null, 0, "Grove gardens: lush mixed beds of ember lilies, glow tendrils, molten buds, gilded clover and copper roses.", "Haing\u00e4rten: \u00fcppige Mischbeete aus Glutlilien, Leuchtranken, Schmelzknospen, vergoldetem Klee und Kupferrosen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_garden_slag_mix", "copper_inferno:slag_thistle", null,
				null,
				null, 0, "Slag gardens: sparse beds of slag thistles, soot puffs and cinder ferns on the delta flats.", "Schlackeng\u00e4rten: karge Beete aus Schlackendisteln, Ru\u00dfbovisten und Schlackenfarnen auf den Delta-Ebenen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_ceiling_hanging_ember_roots", "copper_inferno:hanging_ember_roots", null,
				null,
				null, 0, "Hanging Ember Roots (light 3) dangle from the cavern ceilings, tips still glowing.", "H\u00e4ngende Glutwurzeln (Licht 3) baumeln von den H\u00f6hlendecken, die Spitzen gl\u00fchen noch."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_ceiling_ash_veils", "copper_inferno:ash_veil", null,
				null,
				null, 0, "Ash Veils: pale curtains of compacted ash drifting from the Inferno's roof.", "Aschenschleier: fahle Vorh\u00e4nge aus verdichteter Asche, die vom Dach des Infernos wehen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_wall_verdigris_lichen", "copper_inferno:verdigris_lichen", null,
				null,
				null, 0, "Verdigris Lichen (light 7) creeps across cinderstone and slagstone walls and ceilings; shear it to collect it.", "Gr\u00fcnspanflechte (Licht 7) kriecht \u00fcber W\u00e4nde und Decken aus Zunderstein und Schlackenstein; mit der Schere ernten."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_cinder_crystal_cluster", "copper_inferno:cinder_crystal_block", null,
				null,
				null, 0, "Cinder Crystal clusters (light 9) crop out of the wastes floor in tight sparkling clumps.", "Schlackenkristall-Ansammlungen (Licht 9) ragen in dichten, funkelnden Klumpen aus dem Boden der \u00d6de."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_verdigris_crystal_cluster", "copper_inferno:verdigris_crystal_block", null,
				null,
				null, 0, "Verdigris Crystal clusters (light 7) grow along the Slag Sea's stone flats.", "Gr\u00fcnspankristall-Ansammlungen (Licht 7) wachsen auf den Steinebenen des Schlackenmeers."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_buried_verdigris_crystal", "copper_inferno:verdigris_crystal_block", null,
				null,
				null, 0, "Small verdigris crystal pockets lie buried inside cinderstone \u2014 mine for the glow.", "Kleine Gr\u00fcnspankristall-Taschen liegen im Zunderstein vergraben \u2014 dem Leuchten nach graben."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_giant_embercap", "copper_inferno:embercap_block", null,
				null,
				null, 0, "Giant Embercaps: huge scorched-stem fungi with glowing orange caps (light 4), rooted in ember moss.", "Riesen-Glutkappen: gewaltige Pilze mit versengtem Stiel und gl\u00fchend orangen Kappen (Licht 4), verwurzelt im Glutmoos."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_giant_gloomcap", "copper_inferno:gloomcap_block", null,
				null,
				null, 0, "Giant Gloomcaps: towering teal-capped fungi (light 6) hung with fungal lights, rooted in ember wart.", "Riesen-D\u00fcsterkappen: t\u00fcrmende Pilze mit t\u00fcrkisen Kappen (Licht 6) und Pilzlichtern, verwurzelt in Glutwarzen."));

		HandbookEntries.add(new HandbookEntry("dimension", "infernogardens_patch_ember_moss_carpet", "copper_inferno:ember_moss_carpet", null,
				null,
				null, 0, "Moss shelves: ember moss carpets creep over the moss blocks and glowing soil of the groves.", "Moosb\u00e4nke: Glutmoosteppiche kriechen \u00fcber die Moosbl\u00f6cke und den gl\u00fchenden Boden der Haine."));
	}
}
