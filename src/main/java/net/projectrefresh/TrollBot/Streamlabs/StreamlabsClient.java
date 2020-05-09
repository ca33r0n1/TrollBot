package net.projectrefresh.TrollBot.Streamlabs;

import com.github.philippheuer.events4j.core.EventManager;
import net.projectrefresh.TrollBot.Streamlabs.Auth.StreamlabsAuth;
import net.projectrefresh.TrollBot.Streamlabs.api.StreamlabsApi;

public class StreamlabsClient {

    private final EventManager eventManager;

    /**
     * Auth
     */
    private final StreamlabsAuth auth;

    /**
     * API
     */
    private final StreamlabsApi api;

    /**
     * Constructor
     *
     * @param eventManager EventManager
     * @param auth         Streamlabs Auth
     * @param api          Streamlabs API
     */
    public StreamlabsClient(EventManager eventManager, StreamlabsAuth auth, StreamlabsApi api) {
        this.eventManager = eventManager;
        this.auth = auth;
        this.api = api;
    }

    /**
     * Get Auth
     *
     * @return StreamlabsAuth
     */
    public StreamlabsAuth getAuth() {
        if (this.auth == null) {
            throw new RuntimeException("You have not enabled the AUTH Module! Please check out the documentation on Streamlabs4J -> AUTH.");
        }

        return this.auth;
    }

    /**
     * Get Api
     *
     * @return StreamlabsApi
     */
    public StreamlabsApi getApi() {
        if (this.api == null) {
            throw new RuntimeException("You have not enabled the API Module! Please check out the documentation on Streamlabs4J -> API.");
        }

        return this.api;
    }
}