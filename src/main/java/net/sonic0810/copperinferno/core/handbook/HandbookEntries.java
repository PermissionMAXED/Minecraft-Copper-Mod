package net.sonic0810.copperinferno.core.handbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sonic0810.copperinferno.CopperInferno;

/**
 * Shared handbook entry registry. Features call {@link #add} from their {@code init()}; the
 * client handbook screen reads {@link #all()} (registration order == page order).
 */
public final class HandbookEntries {
	private HandbookEntries() {
	}

	private static final List<HandbookEntry> ENTRIES = new ArrayList<>();

	/** Appends an entry; a duplicate id is skipped with a warning (first registration wins). */
	public static void add(HandbookEntry entry) {
		for (HandbookEntry existing : ENTRIES) {
			if (existing.id().equals(entry.id())) {
				CopperInferno.LOGGER.warn("[handbook] duplicate entry id {} - skipping", entry.id());
				return;
			}
		}
		ENTRIES.add(entry);
	}

	/** Unmodifiable view of all entries, in registration order. */
	public static List<HandbookEntry> all() {
		return Collections.unmodifiableList(ENTRIES);
	}
}
