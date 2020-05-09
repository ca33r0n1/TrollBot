package net.projectrefresh.TrollBot.Streamlabs;

import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.philippheuer.events4j.core.EventManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import lombok.extern.slf4j.Slf4j;
import net.projectrefresh.TrollBot.Streamlabs.Auth.StreamlabsAuth;
import net.projectrefresh.TrollBot.Streamlabs.api.StreamlabsApi;
import net.projectrefresh.TrollBot.Streamlabs.api.StreamlabsApiBuilder;

/**
 * Builder to get a StreamlabsClient Instance by provided various options, to provide the user with a lot of customizable options.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamlabsClientBuilder {

    /**
     * Client ID
     */
    @Wither
    private String clientId;

    /**
     * Client Secret
     */
    @Wither
    private String clientSecret;

    /**
     * Redirect Url
     */
    @Wither
    private String redirectUrl = "http://localhost";

    /**
     * EventManager
     */
    @Wither
    private EventManager eventManager = new EventManager();

    /**
     * CredentialManager
     */
    @Wither
    private CredentialManager credentialManager = CredentialManagerBuilder.builder().build();

    /**
     * Initialize the builder
     *
     * @return Streamlabs Client Builder
     */
    public static StreamlabsClientBuilder builder() {
        return new StreamlabsClientBuilder();
    }

    /**
     * Initialize
     *
     * @return {@link StreamlabsClient} initialized class
     */
    public StreamlabsClient build() {
        // Client Id / Client Secret
        if (this.clientId == null || this.clientSecret == null) {
            throw new RuntimeException("You have not provided the required Client-Id/Client-Secret to use the rest api");
        }

        // Module: AUTH
        StreamlabsAuth auth = new StreamlabsAuth(credentialManager, clientId, clientSecret, redirectUrl);

        // Module: API
       // StreamlabsApi api = StreamlabsApiBuilder.builder().setClientId(clientId);

        // Client Builder
       // final StreamlabsClient client = new StreamlabsClient(eventManager, auth, api);

        // Return new Client Instance
        //return client;
        return null;
    }

}