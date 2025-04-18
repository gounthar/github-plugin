package org.jenkinsci.plugins.github.extension;

import jakarta.servlet.http.HttpServletRequest;
import jenkins.scm.api.SCMEvent;
import org.kohsuke.github.GHEvent;

import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.NonNull;

/**
 * An event for a {@link GHEventsSubscriber}.
 *
 * @since 1.26.0
 */
public class GHSubscriberEvent extends SCMEvent<String> {
    /**
     * The type of event.
     */
    private final GHEvent ghEvent;

    private final String eventGuid;

    /**
     * @deprecated use {@link #GHSubscriberEvent(String, String, GHEvent, String)} instead.
     */
    @Deprecated
    public GHSubscriberEvent(@CheckForNull String origin, @NonNull GHEvent ghEvent, @NonNull String payload) {
        this(null, origin, ghEvent, payload);
    }

    /**
     * Constructs a new {@link GHSubscriberEvent}.
     * @param eventGuid the globally unique identifier (GUID) to identify the event; value of
     * request header {@link com.cloudbees.jenkins.GitHubWebHook#X_GITHUB_DELIVERY}.
     * @param origin  the origin (see {@link SCMEvent#originOf(HttpServletRequest)}) or {@code null}.
     * @param ghEvent the type of event received from GitHub.
     * @param payload the event payload.
     */
    public GHSubscriberEvent(
            @CheckForNull String eventGuid,
            @CheckForNull String origin,
            @NonNull GHEvent ghEvent,
            @NonNull String payload) {
        super(Type.UPDATED, payload, origin);
        this.ghEvent = ghEvent;
        this.eventGuid = eventGuid;
    }

    /**
     * Gets the type of event received.
     *
     * @return the type of event received.
     */
    public GHEvent getGHEvent() {
        return ghEvent;
    }

    @CheckForNull
    public String getEventGuid() {
        return eventGuid;
    }
}
