package net.projectrefresh.TrollBot.Streamlabs.Auth;

import com.github.philippheuer.credentialmanager.CredentialManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StreamlabsAuth {

    /**
     * Credential Manager
     */
    private final CredentialManager credentialManager;

    /**
     * Identity Provider
     */
    @Getter
    private StreamlabsIdentityProvider identityProvider;

    /**
     * Streamlabs Identity Provider
     *
     * @param credentialManager Credential Manager
     * @param clientId          OAuth2 Client Id
     * @param clientSecret      OAuth2 Client Secret
     * @param redirectUrl       OAuth2 Redirect Url
     */
    public StreamlabsAuth(CredentialManager credentialManager, String clientId, String clientSecret, String redirectUrl) {
        this.credentialManager = credentialManager;
        this.identityProvider = new StreamlabsIdentityProvider(clientId, clientSecret, redirectUrl);
        this.credentialManager.registerIdentityProvider(identityProvider);
    }

}
