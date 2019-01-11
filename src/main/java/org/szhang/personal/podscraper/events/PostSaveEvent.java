package org.szhang.personal.podscraper.events;

import org.neo4j.ogm.session.event.Event;

public class PostSaveEvent extends ModificationEvent {
  public PostSaveEvent(Event event) {
    super(event);
  }
}
