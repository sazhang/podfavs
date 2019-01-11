package org.szhang.personal.podscraper.events;

import org.neo4j.ogm.session.event.Event;

public class PreSaveEvent extends ModificationEvent {

  public PreSaveEvent(Event event) {
    super(event);
  }
}
